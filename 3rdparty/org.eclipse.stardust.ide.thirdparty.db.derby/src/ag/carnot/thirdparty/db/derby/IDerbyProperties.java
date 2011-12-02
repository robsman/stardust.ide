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

/**
 * @author rsauer
 * @version $Revision$
 */
public interface IDerbyProperties
{
   String VERB_CREATE = "create"; //$NON-NLS-1$

   String VERB_SHUTDOWN = "shutdown"; //$NON-NLS-1$

   String DERBY_SYSTEM_HOME = "derby.system.home"; //$NON-NLS-1$

   String DERBY_LOG_CONNECTIONS = "derby.drda.logConnections"; //$NON-NLS-1$
   
   String DERBY_START_NETWORK_SERVER = "derby.drda.startNetworkServer"; //$NON-NLS-1$
   
   String DERBY_PROPERTIES = "derby.properties"; //$NON-NLS-1$

   String JDBC_URL_PREFIX = "jdbc:derby:"; //$NON-NLS-1$

   String JDBC_CREATE_SUFFIX = ";" + VERB_CREATE + "=true"; //$NON-NLS-1$ //$NON-NLS-2$

   String JDBC_SHUTDOWN_SUFFIX = ";" + VERB_SHUTDOWN + "=true"; //$NON-NLS-1$ //$NON-NLS-2$

   String EMBEDDED_SHUTDOWN_URL = JDBC_URL_PREFIX + JDBC_SHUTDOWN_SUFFIX;
}
