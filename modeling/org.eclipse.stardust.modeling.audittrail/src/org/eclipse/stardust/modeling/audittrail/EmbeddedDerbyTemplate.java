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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.core.persistence.jdbc.QueryUtils;

import ag.carnot.thirdparty.db.derby.IDerbyProperties;

/**
 * @author rsauer
 * @version $Revision$
 */
public class EmbeddedDerbyTemplate
{
   private final SqlTemplate sqlTemplate;

   public EmbeddedDerbyTemplate()
   {
      this.sqlTemplate = null;
   }

   public EmbeddedDerbyTemplate(SqlTemplate sqlTemplate)
   {
      this.sqlTemplate = sqlTemplate;
   }

   public Object performOperation(File dbSystemHome, String dbName, String schemaName)
   {
      return performOperation(dbSystemHome, dbName, schemaName, false);
   }

   public Object performOperation(File dbSystemHome, String dbName, String schemaName,
         boolean createDb)
   {
      Object result = null;

      try
      {
         // boot Derby into dbFolder
         System.setProperty(IDerbyProperties.DERBY_SYSTEM_HOME, dbSystemHome.getAbsolutePath());

         EmbeddedDataSource dataSource = new EmbeddedDataSource();
         dataSource.setDatabaseName(dbName);
         dataSource.setUser(schemaName);

         if (createDb)
         {
            dataSource.setCreateDatabase(IDerbyProperties.VERB_CREATE);
         }
         
         try
         {
            result = performDbOperation(dataSource);
         }
         finally
         {
            try
            {
               dataSource.setShutdownDatabase(IDerbyProperties.VERB_SHUTDOWN);
               dataSource.getConnection();
            }
            catch (SQLException sqle)
            {
               // ignore
            }

            try
            {
               DriverManager.getConnection(IDerbyProperties.EMBEDDED_SHUTDOWN_URL);
            }
            catch (SQLException sqle)
            {
               // ignore
            }
         }
      }
      catch (SQLException e)
      {
         SQLException nextE = e.getNextException();
         if (null != nextE)
         {
            throw new PublicException(nextE.getMessage(), e);
         }
         else
         {
            throw new PublicException(e);
         }
      }
      catch (Exception e)
      {
         throw new PublicException(e);
      }
      finally
      {
         System.getProperties().remove(IDerbyProperties.DERBY_SYSTEM_HOME);
      }

      return result;
   }

   protected Object performDbOperation(DataSource dataSource) throws SQLException
   {
      Object result = null;

      if (null != sqlTemplate)
      {
         Connection conn = dataSource.getConnection();
         conn.setAutoCommit(false);
         
         try
         {
            result = sqlTemplate.executeSql(conn, null);
            
            conn.commit();
         }
         catch (SQLException sqle)
         {
            conn.rollback();
         }
         finally
         {
            QueryUtils.closeConnection(conn);
         }
      }

      return result;
   }
}
