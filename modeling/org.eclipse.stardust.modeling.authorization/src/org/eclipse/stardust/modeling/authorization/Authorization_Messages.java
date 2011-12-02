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
package org.eclipse.stardust.modeling.authorization;

import org.eclipse.osgi.util.NLS;

public class Authorization_Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.authorization.authorization-messages"; //$NON-NLS-1$

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Authorization_Messages.class);
	}
	
	public static String ERR_MSG_SCOPED_PARTICIPANTS_ARE_NOT_ALLOWED_FOR_MODEL_LEVEL_GRANTS;
	public static String LBL_ALL;
	public static String LBL_DEFAULT;
	public static String LBL_OWNER;
	public static String PERMISSION_COLUMN_LABEL;
	public static String PARTICIPANT_COLUMN_LABEL;


	/**
	 * 
	 * 
	 */
	private Authorization_Messages()
	{
	}


}
