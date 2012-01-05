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

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.eclipse.stardust.common.config.Parameters;

import ag.carnot.db.jdbc.DBMSKey;
import ag.carnot.db.jdbc.QueryUtils;
import ag.carnot.db.jdbc.Session;
import ag.carnot.db.jdbc.SessionFactory;
import ag.carnot.db.jdbc.SessionProperties;

/**
 * @author rsauer
 * @version $Revision$
 */
public abstract class EmbeddedDerbySessionTemplate extends EmbeddedDerbyTemplate
{
   protected Object performDbOperation(DataSource dataSource) throws SQLException
   {
      // test connection
      Connection conn = dataSource.getConnection();
      QueryUtils.closeConnection(conn);
      
      Parameters.instance().set(
            SessionFactory.AUDIT_TRAIL + SessionProperties.DS_TYPE_SUFFIX,
            DBMSKey.DERBY.getId());
      Parameters.instance().set(
            SessionFactory.AUDIT_TRAIL + SessionProperties.DS_USE_LOCK_TABLES_SUFFIX,
            Boolean.TRUE.toString());

      Session session = SessionFactory.createSession(SessionFactory.AUDIT_TRAIL,
            dataSource);
      try
      {
         return performDbOperation(session);
      }
      finally
      {
         session.disconnect();
      }
   }

   protected abstract Object performDbOperation(Session session) throws SQLException;
}
