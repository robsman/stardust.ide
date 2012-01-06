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
import java.sql.Statement;

import org.eclipse.stardust.engine.core.persistence.jdbc.QueryUtils;

/**
 * @author rsauer
 * @version $Revision$
 */
public abstract class SqlTemplate
{
   protected Object executeSql(Connection conn, Object[] args) throws SQLException
   {
      Statement stmt = conn.createStatement();
      try
      {
         return executeSql(stmt, args);
      }
      finally
      {
         QueryUtils.closeStatement(stmt);
      }
   }

   protected Object executeSql(Statement stmt, Object[] args) throws SQLException
   {
      executeSql(stmt);
      return null;
   }

   protected void executeSql(Statement stmt) throws SQLException
   {
   }
}
