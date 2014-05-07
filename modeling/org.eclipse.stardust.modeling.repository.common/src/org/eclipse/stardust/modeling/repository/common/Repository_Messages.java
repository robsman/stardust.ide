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
package org.eclipse.stardust.modeling.repository.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * I18N properties, using a non-eclipse approach to ensure compatibility with plain JAR deployments.
 */
public class Repository_Messages {

   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.repository.common.repository-messages"; //$NON-NLS-1$

   private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
         .getBundle(BUNDLE_NAME);

   public static final String BUT_REPLACE_EXISTING_ELEMENT_WITH_NEW_ONE = getString("BUT_REPLACE_EXISTING_ELEMENT_WITH_NEW_ONE"); //$NON-NLS-1$
	public static final String BUT_REUSE_ELEMENT_IN_THE_MD = getString("BUT_REUSE_ELEMENT_IN_THE_MD"); //$NON-NLS-1$
	public static final String BUT_USE_OPTION_FOR_ALL_DUPLICATE_ELEMENTS = getString("BUT_USE_OPTION_FOR_ALL_DUPLICATE_ELEMENTS"); //$NON-NLS-1$
	public static final String DIA_ERROR = getString("DIA_ERROR"); //$NON-NLS-1$
	public static final String DIA_ERROR_OPENING_CONNECTION = getString("DIA_ERROR_OPENING_CONNECTION"); //$NON-NLS-1$
	public static final String DIA_LOGIN = getString("DIA_LOGIN"); //$NON-NLS-1$
	public static final String EXC_THE_CLASS = getString("EXC_THE_CLASS"); //$NON-NLS-1$
	public static final String LBL_ANOTHER = getString("LBL_ANOTHER"); //$NON-NLS-1$
	public static final String LBL_CONTAINER = getString("LBL_CONTAINER"); //$NON-NLS-1$
	public static final String LBL_FEATURE = getString("LBL_FEATURE"); //$NON-NLS-1$
	public static final String LBL_ID = getString("LBL_ID"); //$NON-NLS-1$
	public static final String LBL_ID_KLEIN_GESCHRIEBEN = getString("LBL_ID_KLEIN_GESCHRIEBEN"); //$NON-NLS-1$
	public static final String LBL_NAME = getString("LBL_NAME"); //$NON-NLS-1$
	public static final String LBL_NAME_KLEIN_GESCHRIEBEN = getString("LBL_NAME_KLEIN_GESCHRIEBEN"); //$NON-NLS-1$
	public static final String LBL_NULL = getString("LBL_NULL"); //$NON-NLS-1$
	public static final String LBL_REQUIRES_THE_ELEMENTS_LISTED_BELOW = getString("LBL_REQUIRES_THE_ELEMENTS_LISTED_BELOW"); //$NON-NLS-1$
	public static final String MSG_FORMAT_CONNECTION_NULL_DOES_NOT_EXIST = getString("MSG_FORMAT_CONNECTION_NULL_DOES_NOT_EXIST"); //$NON-NLS-1$
	public static final String MSG_FORMAT_CONNECTION_NULL_IS_CLOSED = getString("MSG_FORMAT_CONNECTION_NULL_IS_CLOSED"); //$NON-NLS-1$
	public static final String MSG_FORMAT_NO_HANDLER_FOUND_FOR_CONNECTION_TYPE_NULL = getString("MSG_FORMAT_NO_HANDLER_FOUND_FOR_CONNECTION_TYPE_NULL"); //$NON-NLS-1$
	public static final String TXT_CONFLICT = getString("TXT_CONFLICT"); //$NON-NLS-1$
   public static final String TXT_CONFLICT_Replace = getString("TXT_CONFLICT_Replace"); //$NON-NLS-1$
	public static final String TXT_ERROR = getString("TXT_ERROR"); //$NON-NLS-1$
	public static final String TXT_INVALID_CONTAINING_FEATURE = getString("TXT_INVALID_CONTAINING_FEATURE"); //$NON-NLS-1$
	public static final String EXC_IS_NOT_VALID_CLASSIFIER = getString("EXC_IS_NOT_VALID_CLASSIFIER"); //$NON-NLS-1$
	public static final String TXT_NAME = getString("TXT_NAME"); //$NON-NLS-1$
	public static final String TXT_REQUIRED_ELEMENTS = getString("TXT_REQUIRED_ELEMENTS"); //$NON-NLS-1$
	public static final String TXT_TYPELESS = getString("TXT_TYPELESS"); //$NON-NLS-1$
	public static final String TXT_VALUE = getString("TXT_VALUE"); //$NON-NLS-1$
	public static final String LBL_ANOTHER_TYPE_WITH_ID_ALREADY_EXISTS_IN_MODEL = getString("LBL_ANOTHER_TYPE_WITH_ID_ALREADY_EXISTS_IN_MODEL"); //$NON-NLS-1$
	public static final String TXT_INVISIBLE = getString("TXT_INVISIBLE"); //$NON-NLS-1$
	public static final String LBL_STRUCTURED_TYPES = getString("LBL_STRUCTURED_TYPES");
	public static final String LBL_APPLICATIONS = getString("LBL_APPLICATIONS");
	public static final String LBL_DATA = getString("LBL_DATA");
	public static final String LBL_PARTICIPANTS = getString("LBL_PARTICIPANTS");


	private Repository_Messages() {
	}

   private static String getString(String key)
   {
      try
      {
         return RESOURCE_BUNDLE.getString(key);
      }
      catch (MissingResourceException e)
      {
         return '!' + key + '!';
      }
   }
}

