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
package org.eclipse.stardust.modeling.audittrail.ui;

import org.eclipse.osgi.util.NLS;

public class Audittrail_UI_Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.audittrail.ui.audittrail-ui-messages"; //$NON-NLS-1$
	public static String BUT_DELETE;
	public static String BUT_NEW;
	public static String BUT_RESET;
	public static String DIA_NEW_AUDITTRAIL_DB;
	public static String IP_DIA_ENTER_NAME_OF_NEW_AUDITTRAIL_DB;
	public static String LBL_DERBY_AUDITTRAIL_DBS;
	public static String MSG_ERR_FAILED_RESETTING_AUDITTRAIL_DB;
	public static String TXT_DB_ALREADY_EXISTS;
	public static String TXT_NAME_MUST_NOT_BE_EMPTY;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Audittrail_UI_Messages.class);
	}

	private Audittrail_UI_Messages() {
	}
}
