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
package ag.carnot.thirdparty.db.derby;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * @author rsauer
 * @version $Revision$
 */
public class DerbyDbActivator extends Plugin
{
   public static final String BUNDLE_ID = "org.eclipse.stardust.ide.thirdparty.db.derby";

   private static DerbyDbActivator defaultInstance;

   private boolean dbmsStarted;

   private Set<String> dbs = new HashSet<String>();

   public DerbyDbActivator()
   {
      // TODO Auto-generated constructor stub
   }

   public void start(BundleContext context) throws Exception
   {
      super.start(context);

      defaultInstance = this;
   }

   public void stop(BundleContext context) throws Exception
   {
      defaultInstance = null;

      // cleanup DB data
      stopDbms();

      super.stop(context);
   }

   public static DerbyDbActivator getDefault()
   {
      return defaultInstance;
   }

   public Driver startDbms(File dbFolder, int port, Properties props)
   {
      if (null == props)
      {
         props = new Properties();
      }

      if (0 < port)
      {
         props.put(IDerbyProperties.DERBY_START_NETWORK_SERVER, Boolean.TRUE.toString());
      }
      props.put(IDerbyProperties.DERBY_LOG_CONNECTIONS, "true");

      return bootDbms(dbFolder, props);
   }

   public Driver startDbms(File dbFolder, Properties props)
   {
      return bootDbms(dbFolder, props);
   }

   public void stopDbms() throws Exception
   {
      if (dbmsStarted)
      {
         try
         {
            DriverManager.getConnection(IDerbyProperties.EMBEDDED_SHUTDOWN_URL);
         }
         catch (SQLException sqle)
         {
            // ignore
            dbmsStarted = false;
         }
         // TODO empty dbFolder
      }
   }

   public String startTemporaryDb(String dbPrefix) throws SQLException
   {
      // TODO

      if ( !dbmsStarted)
      {
         Properties props = new Properties();

         // TODO more properties?
         props.put(IDerbyProperties.DERBY_START_NETWORK_SERVER, "true");
         props.put(IDerbyProperties.DERBY_LOG_CONNECTIONS, "true");

         startTemporaryDbms(props);
      }

      StringBuffer dbName;
      do
      {
         dbName = new StringBuffer();
         // dbName.append(dbPrefix).append("-").append(System.currentTimeMillis());

         // TODO rsauer revert to dynamic db name after debugging
         dbName.append("carnot");
         dbs.remove(dbName.toString());
      }
      while (dbs.contains(dbName.toString()));

      StringBuffer url = new StringBuffer();
      url.append(IDerbyProperties.JDBC_URL_PREFIX).append(dbName).append(
            IDerbyProperties.JDBC_CREATE_SUFFIX);

      Connection conn = DriverManager.getConnection(url.toString());
      dbs.add(dbName.toString());
      conn.close();

      return dbName.toString();
   }

   private void startTemporaryDbms(Properties props)
   {
      try
      {
         // ackquire temporary path name, and convert to folder
         File dbFolderMarker = File.createTempFile("carnot-", "-derby-db");
         File tmpFolder = dbFolderMarker.getParentFile();

         File dbFolder = new File(tmpFolder, dbFolderMarker.getName());
         dbFolderMarker.delete();
         dbFolder.mkdir();

         // cleaning up on regular shutdown
         dbFolder.deleteOnExit();

         bootDbms(dbFolder, props);
      }
      catch (IOException ioe)
      {
         // TODO
      }
   }

   private Driver bootDbms(File dbFolder, Properties props)
   {
      startEmbeddedDriver();
      EmbeddedDriver embeddedDriver = null;

      if (dbmsStarted)
      {
         // TODO
         throw new RuntimeException("DBMS was already started.");
      }

      try
      {
         if (dbFolder.exists() && dbFolder.isDirectory())
         {
            OutputStream fos = new FileOutputStream(new File(dbFolder,
                  IDerbyProperties.DERBY_PROPERTIES));
            props.store(fos, "Infinity Derby DB");
            fos.close();

            try
            {
               // boot Derby into dbFolder
               System.setProperty(IDerbyProperties.DERBY_SYSTEM_HOME, dbFolder.getAbsolutePath());

               embeddedDriver = new EmbeddedDriver();

               this.dbmsStarted = true;
            }
            finally
            {
               System.getProperties().remove(IDerbyProperties.DERBY_SYSTEM_HOME);
            }
         }
      }
      catch (IOException ioe)
      {
         // TODO
      }

      return embeddedDriver;
   }

   private void startEmbeddedDriver()
   {
      //rpielmann:CRNT-15258
      try
      {
         Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
      }
      catch (Throwable e)
      {
         e.printStackTrace();
      }
   }
}
