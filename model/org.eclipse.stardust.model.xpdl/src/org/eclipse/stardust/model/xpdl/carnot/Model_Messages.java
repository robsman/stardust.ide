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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * I18N properties, using a non-eclipse approach to ensure compatibility with plain JAR deployments.
 */
public class Model_Messages
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.model.xpdl.carnot.model-messages"; //$NON-NLS-1$

   private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
         .getBundle(BUNDLE_NAME);

   private Model_Messages()
   {}

   public static final String MSG_ATTRIBUTE_CATEGORY_EXISTS = getString("MSG_ATTRIBUTE_CATEGORY_EXISTS"); //$NON-NLS-1$

   public static String MSG_ATTRIBUTE_EXISTS = getString("MSG_ATTRIBUTE_EXISTS"); //$NON-NLS-1$

   public static String MSG_NO_VALID_ENUMERATION = getString("MSG_NO_VALID_ENUMERATION"); //$NON-NLS-1$

   public static String MSG_CLASS_NOT_VALID = getString("MSG_CLASS_NOT_VALID"); //$NON-NLS-1$

   public static String MSG_DATATYPE_NOT_VALID = getString("MSG_DATATYPE_NOT_VALID"); //$NON-NLS-1$

   public static String MSG_FAILED_LOADING_MODEL = getString("MSG_FAILED_LOADING_MODEL"); //$NON-NLS-1$

   public static String MSG_INVALID_JAXP_SETUP = getString("MSG_INVALID_JAXP_SETUP"); //$NON-NLS-1$

   public static String MSG_UNABLE_TO_LOAD_XPDL_IMPORT = getString("MSG_UNABLE_TO_LOAD_XPDL_IMPORT"); //$NON-NLS-1$

   public static String MSG_UNABLE_TO_FIND_XPDL_IMPORT = getString("MSG_UNABLE_TO_FIND_XPDL_IMPORT"); //$NON-NLS-1$

   public static String MSG_FATAL_ERR = getString("MSG_FATAL_ERR"); //$NON-NLS-1$

   public static String MSG_ERR = getString("MSG_ERR"); //$NON-NLS-1$

   public static String MSG_WARN = getString("MSG_WARN"); //$NON-NLS-1$

   public static String EXC_CANNOT_PARSE_QNAME_FROM_NULL = getString("EXC_CANNOT_PARSE_QNAME_FROM_NULL"); //$NON-NLS-1$

   public static String EXC_COULD_NOT_FIND_PREDEFINED_XPATHS_FOR_DATA_TYPE_NULL = getString("EXC_COULD_NOT_FIND_PREDEFINED_XPATHS_FOR_DATA_TYPE_NULL"); //$NON-NLS-1$

   public static String EXC_COULD_NOT_LOAD_MODEL_DOC_ROOT_NOT_FOUND = getString("EXC_COULD_NOT_LOAD_MODEL_DOC_ROOT_NOT_FOUND"); //$NON-NLS-1$

   public static String EXC_INVALID_JAXP_SETUP = getString("EXC_INVALID_JAXP_SETUP"); //$NON-NLS-1$

   public static String EXC_MISSING_SERIALIZED_PACKAGE_NULL = getString("EXC_MISSING_SERIALIZED_PACKAGE_NULL"); //$NON-NLS-1$

   public static String EXC_NEITHER_EXTERNAL_REFERENCE_NOR_SCHEME_TYPE_IS_SET_FOR_NULL = getString("EXC_NEITHER_EXTERNAL_REFERENCE_NOR_SCHEME_TYPE_IS_SET_FOR_NULL"); //$NON-NLS-1$

   public static String EXC_NO_TYPE_DECLARATION_SPECIFIED_FOR_DATA_NULL = getString("EXC_NO_TYPE_DECLARATION_SPECIFIED_FOR_DATA_NULL"); //$NON-NLS-1$

   public static String EXC_RECURSIVE_CONTAINMENT_NOT_ALLOWED_FOR_NULL = getString("EXC_NO_TYPE_DECLARATION_SPECIFIED_FOR_DATA_NULL"); //$NON-NLS-1$

   public static String EXC_THE_CLASS_NULL_IS_NOT_A_VALID_CLASSIFIER = getString("EXC_THE_CLASS_NULL_IS_NOT_A_VALID_CLASSIFIER"); //$NON-NLS-1$

   public static String EXC_THE_DATATYPE_NULL_IS_NOT_VALID_CLASSIFIER = getString("EXC_THE_DATATYPE_NULL_IS_NOT_VALID_CLASSIFIER"); //$NON-NLS-1$

   public static String EXC_THE_VALUE_NULL_IS_NOT_VALID_ENUMERATION_OF_ONE = getString("EXC_THE_VALUE_NULL_IS_NOT_VALID_ENUMERATION_OF_ONE"); //$NON-NLS-1$

   public static String EXC_UNSUPPORTED_BOOLEAN_EXPRESSION_NULL = getString("EXC_UNSUPPORTED_BOOLEAN_EXPRESSION_NULL"); //$NON-NLS-1$

   public static String EXC_WITH_IS_NOT_SUPPORTED = getString("EXC_WITH_IS_NOT_SUPPORTED"); //$NON-NLS-1$

   public static String ROUTE_ACTIVITY = getString("ROUTE_ACTIVITY"); //$NON-NLS-1$

   public static String MANUAL_ACTIVITY = getString("MANUAL_ACTIVITY"); //$NON-NLS-1$

   public static String APPLICATION_ACTIVITY = getString("APPLICATION_ACTIVITY"); //$NON-NLS-1$

   public static String SUBPROCESS_ACTIVITY = getString("SUBPROCESS_ACTIVITY"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_AND = getString("JOIN_SPLIT_LOOP_AND"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_MULTI_INSTANCE = getString("JOIN_SPLIT_LOOP_MULTI_INSTANCE"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_XOR = getString("JOIN_SPLIT_LOOP_XOR"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_NONE = getString("JOIN_SPLIT_LOOP_NONE"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_NOLOOP = getString("JOIN_SPLIT_LOOP_NOLOOP"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_WHILE = getString("JOIN_SPLIT_LOOP_WHILE"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_REPEAT = getString("JOIN_SPLIT_LOOP_REPEAT"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_STANDARD = getString("JOIN_SPLIT_LOOP_STANDARD"); //$NON-NLS-1$

   public static String JOIN_SPLIT_LOOP_UNKNOWN = getString("JOIN_SPLIT_LOOP_UNKNOWN"); //$NON-NLS-1$

   public static String SYNC_SHARED = getString("SYNC_SHARED"); //$NON-NLS-1$

   public static String SYNC_SEPARATE = getString("SYNC_SEPARATE"); //$NON-NLS-1$

   public static String ASYNC_SEPARATE = getString("ASYNC_SEPARATE"); //$NON-NLS-1$

   public static String NEW_ENTRY = getString("NEW_ENTRY"); //$NON-NLS-1$

   public static String EXC_FAILED_INSTANTIATING_EXTENSION = getString("EXC_FAILED_INSTANTIATING_EXTENSION"); //$NON-NLS-1$

   public static String AUDITTRAIL_PERSISTENCE_TRANSIENT = getString("AUDITTRAIL_PERSISTENCE_TRANSIENT"); //$NON-NLS-1$

   public static String AUDITTRAIL_PERSISTENCE_DEFERRED = getString("AUDITTRAIL_PERSISTENCE_DEFERRED"); //$NON-NLS-1$

   public static String AUDITTRAIL_PERSISTENCE_IMMEDIATE = getString("AUDITTRAIL_PERSISTENCE_IMMEDIATE"); //$NON-NLS-1$

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
