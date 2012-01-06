/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.audittrail;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.core.persistence.jdbc.Session;
import org.eclipse.stardust.engine.core.runtime.beans.SchemaHelper;

import ag.carnot.thirdparty.db.derby.DerbyDbActivator;
import ag.carnot.thirdparty.db.derby.IDerbyProperties;

/**
 * @author rsauer
 * @version $Revision$
 */
public class AuditTrailDbManager
{
   public static final String DEFAULT_AUDIT_TRAIL_NAME = "carnot"; //$NON-NLS-1$

   public static IFolder findDb(String dbName)
   {
      IFolder folder = null;

      try
      {
         IProject dbProject = getDbProject(false);
         if ((null != dbProject) && dbProject.exists())
         {
            folder = dbProject.getFolder(dbName);
            if ((null != folder) && !folder.exists())
            {
               folder = null;
            }
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return folder;
   }

   public static List<IFolder> listDbs()
   {
      List<IFolder> result = new ArrayList<IFolder>();

      try
      {
         IProject dbProject = getDbProject(false);
         if ((null != dbProject) && dbProject.exists())
         {
            dbProject.refreshLocal(IProject.DEPTH_INFINITE, null);

            IResource[] members = dbProject.members();
            if (null != members)
            {
               for (int i = 0; i < members.length; i++ )
               {
                  if ((members[i] instanceof IFolder) && members[i].exists())
                  {
                     result.add((IFolder) members[i]);
                  }
               }
            }
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return result;
   }

   public static IFolder createDb(String dbName) throws AuditTrailManagementException
   {
      if (null != findDb(dbName))
      {
         return null;
      }

      IFolder dbFolder = null;
      try
      {
         IProject dbProject = getDbProject(true);
         if (null != dbProject)
         {
            dbFolder = dbProject.getFolder(dbName);
            if ((null != dbFolder) && !dbFolder.exists())
            {
               dbFolder.create(false, true, null);

               IPath dbLocation = ResourcesPlugin.getWorkspace()
                     .getRoot()
                     .getLocation()
                     .append(dbFolder.getFullPath());

               createDbSystem(dbLocation.toFile());
               initializeAuditTrailDb(dbLocation.toFile(), DEFAULT_AUDIT_TRAIL_NAME,
                     DEFAULT_AUDIT_TRAIL_NAME);

               dbFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);

               AuditTrailActivator.instance().notifyDbListeners(dbFolder,
                     IAuditTrailDbListener.CREATED);
            }
         }
      }
      catch (CoreException ce)
      {
         throw new AuditTrailManagementException(Messages.FailedCreateDB, ce); //$NON-NLS-1$
      }

      return dbFolder;
   }

   public static void resetDb(String dbName) throws AuditTrailManagementException
   {
      IFolder dbFolder = findDb(dbName);
      if (null != dbFolder)
      {
         try
         {
            dbFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);

            IPath dbLocation = ResourcesPlugin.getWorkspace()
                  .getRoot()
                  .getLocation()
                  .append(dbFolder.getFullPath());

            resetAuditTrailDb(dbLocation.toFile(), DEFAULT_AUDIT_TRAIL_NAME,
                  DEFAULT_AUDIT_TRAIL_NAME);

            dbFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);

            AuditTrailActivator.instance().notifyDbListeners(dbFolder,
                  IAuditTrailDbListener.RESET);
         }
         catch (AuditTrailManagementException atme)
         {
            throw atme;
         }
         catch (CoreException ce)
         {
            throw new AuditTrailManagementException(Messages.FailedDeleteDB, ce); //$NON-NLS-1$
         }
         catch (PublicException pe)
         {
            throw new AuditTrailManagementException(Messages.FailedResetDB, pe); //$NON-NLS-1$
         }
      }
   }

   public static void deleteDb(String dbName) throws AuditTrailManagementException
   {
      IFolder dbFolder = findDb(dbName);
      if (null != dbFolder)
      {
         try
         {
            dbFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);

            IPath dbLocation = ResourcesPlugin.getWorkspace()
                  .getRoot()
                  .getLocation()
                  .append(dbFolder.getFullPath());

            cleanAuditTrailDb(dbLocation.toFile(), DEFAULT_AUDIT_TRAIL_NAME,
                  DEFAULT_AUDIT_TRAIL_NAME);

            dbFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);

            dbFolder.delete(false, null);

            dbFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);

            AuditTrailActivator.instance().notifyDbListeners(dbFolder,
                  IAuditTrailDbListener.DELETED);
         }
         catch (CoreException ce)
         {
            throw new AuditTrailManagementException(Messages.FailedDeleteDB, ce); //$NON-NLS-1$
         }
      }
   }

   public static IProject getDbProject(boolean create) throws CoreException
   {
      IProject project = null;

      project = ResourcesPlugin.getWorkspace().getRoot().getProject("Infinity Audit Trails"); //$NON-NLS-1$
      if ((null != project) && !project.exists())
      {
         if (create)
         {
            project.create(null);
         }
         else
         {
            project = null;
         }
      }

      if ((null != project) && !project.isOpen())
      {
         project.open(IProject.BACKGROUND_REFRESH, null);
      }

      return project;
   }

   public static void initializeAuditTrailDb(File dbSystemHome, String dbName,
         final String schemaName) throws AuditTrailManagementException
   {
      try
      {
         new EmbeddedDerbyTemplate(new SqlTemplate()
         {
            protected void executeSql(Statement stmt) throws SQLException
            {
               stmt.execute("CREATE SCHEMA " + schemaName); //$NON-NLS-1$
            }
         }).performOperation(dbSystemHome, dbName, schemaName, true);
   
         new EmbeddedDerbySessionTemplate()
         {
            protected Object performDbOperation(Session session) throws SQLException
            {
               SchemaHelper.createSchema(session);
               return null;
            }
         }.performOperation(dbSystemHome, dbName, schemaName);
      }
      catch (PublicException pe)
      {
         throw new AuditTrailManagementException(Messages.FailedInitDB, pe); //$NON-NLS-1$
      }
   }

   public static void cleanAuditTrailDb(File dbSystemHome, String dbName,
         final String schemaName) throws AuditTrailManagementException
   {
      try
      {
         new EmbeddedDerbySessionTemplate()
         {
            protected Object performDbOperation(Session session) throws SQLException
            {
               SchemaHelper.dropSchema(session, "sysop"); //$NON-NLS-1$
               return null;
            }
         }.performOperation(dbSystemHome, dbName, schemaName);
   
         new EmbeddedDerbyTemplate(new SqlTemplate()
         {
            protected void executeSql(Statement stmt) throws SQLException
            {
               stmt.execute("DROP SCHEMA " + schemaName + " RESTRICT"); //$NON-NLS-1$ //$NON-NLS-2$
            }
         }).performOperation(dbSystemHome, dbName, schemaName, true);
      }
      catch (PublicException pe)
      {
         throw new AuditTrailManagementException(Messages.FailedCleanDB, pe); //$NON-NLS-1$
      }
   }

   public static void resetAuditTrailDb(File dbSystemHome, String dbName,
         final String schemaName) throws AuditTrailManagementException
   {
      cleanAuditTrailDb(dbSystemHome, dbName, schemaName);

      initializeAuditTrailDb(dbSystemHome, dbName, schemaName);
   }

   public static void createDbSystem(File dbSystemHome)
   {
      try
      {
         Properties dbProps = new Properties();
         dbProps.put(IDerbyProperties.DERBY_START_NETWORK_SERVER, Boolean.TRUE.toString());
         dbProps.put(IDerbyProperties.DERBY_LOG_CONNECTIONS, Boolean.TRUE.toString());

         DerbyDbActivator.getDefault().startDbms(dbSystemHome, 0, dbProps);
      }
      finally
      {
         try
         {
            DerbyDbActivator.getDefault().stopDbms();
         }
         catch (Exception stopException)
         {
            // TODO Auto-generated catch block
            stopException.printStackTrace();
         }
      }
   }
}