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
package org.eclipse.stardust.model;

import org.eclipse.osgi.util.NLS;

public class API_Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.stardust.model.api-messages"; //$NON-NLS-1$

	private API_Messages() {
	}

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, API_Messages.class);
	}

	public static String EXC_NOT_IMPLEMENTED_YET;

	public static String EXC_UNEXPECTED_ACTIVITY_IMPLEMENTATION_TYPE;

	public static String EXC_UNEXPECTED_SPLIT_TYPE;

	public static String EXC_UNEXPECTED_SUBPROCESSMODEKEY;

	public static String EXC_UNEXPECTEDT_SPLIT_TYPE;

	public static String STR_ModelParticipant;

	public static String STR_DynParticipant;

	public static String STR_Org;

	public static String STR_Role;

	public static String STR_CondPerformer;

	public static String STR_User;

	public static String STR_UserGroup;

	public static String STR_ParticipantOrGroup;

	public static String STR_Manual;

	public static String STR_Application;

	public static String STR_Subprocess;

	public static String STR_Route;

	public static String STR_Unknown;

	public static String STR_NoLoop;

	public static String STR_While;

	public static String STR_Repeat;

	public static String STR_BindAct;

	public static String STR_EventAct;

	public static String STR_UnbindAct;

	public static String MSG_InvalidSym;
}
