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
package org.eclipse.stardust.model.xpdl.carnot;

import org.eclipse.osgi.util.NLS;

public class ModelMessages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.model.xpdl.carnot.model_messages"; //$NON-NLS-1$

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ModelMessages.class);
	}

	public static String MSG_ATTRIBUTE_CATEGORY_EXISTS = "MSG_AttributeCategoryExists";
	public static String MSG_ATTRIBUTE_EXISTS = "MSG_AttributeExists";
	public static String MSG_NO_VALID_ENUMERATION = "MSG_NoValidEnum";
	public static String MSG_CLASS_NOT_VALID = "MSG_ClassNotValid";
	public static String MSG_DATATYPE_NOT_VALID = "MSG_DatatypeNotValid";
	public static String MSG_FAILED_LOADING_MODEL = "MSG_FailedLoadingModel";
	public static String MSG_INVALID_JAXP_SETUP = "MSG_InvalidJaxpSetup";
	public static String MSG_UNABLE_TO_LOAD_XPDL_IMPORT = "MSG_UnableToLoadXpdlImport";
	public static String MSG_UNABLE_TO_FIND_XPDL_IMPORT = "MSG_UnableToFindXpdlImport";
	public static String MSG_FATAL_ERR = "MSG_FatalErr";
	public static String MSG_ERR = "MSG_Err";
	public static String MSG_WARN = "MSG_Warn";

   private ModelMessages()
   {}

}
