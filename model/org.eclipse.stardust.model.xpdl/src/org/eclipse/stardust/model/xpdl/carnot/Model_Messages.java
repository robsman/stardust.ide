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

public class Model_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.model.xpdl.carnot.model-messages"; //$NON-NLS-1$

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Model_Messages.class);
	}



   private Model_Messages()
   {}

   public static String MSG_ATTRIBUTE_CATEGORY_EXISTS;

   public static String MSG_ATTRIBUTE_EXISTS;

   public static String MSG_NO_VALID_ENUMERATION;

   public static String MSG_CLASS_NOT_VALID;

   public static String MSG_DATATYPE_NOT_VALID;

   public static String MSG_FAILED_LOADING_MODEL;

   public static String MSG_INVALID_JAXP_SETUP;

   public static String MSG_UNABLE_TO_LOAD_XPDL_IMPORT;

   public static String MSG_UNABLE_TO_FIND_XPDL_IMPORT;

   public static String MSG_FATAL_ERR;

   public static String MSG_ERR;

   public static String MSG_WARN;

   public static String EXC_CANNOT_PARSE_QNAME_FROM_NULL;

   public static String EXC_COULD_NOT_FIND_PREDEFINED_XPATHS_FOR_DATA_TYPE_NULL;

   public static String EXC_COULD_NOT_LOAD_MODEL_DOC_ROOT_NOT_FOUND;

   public static String EXC_INVALID_JAXP_SETUP;

   public static String EXC_MISSING_SERIALIZED_PACKAGE_NULL;

   public static String EXC_NEITHER_EXTERNAL_REFERENCE_NOR_SCHEME_TYPE_IS_SET_FOR_NULL;

   public static String EXC_NO_TYPE_DECLARATION_SPECIFIED_FOR_DATA_NULL;

   public static String EXC_RECURSIVE_CONTAINMENT_NOT_ALLOWED_FOR_NULL;

   public static String EXC_THE_CLASS_NULL_IS_NOT_A_VALID_CLASSIFIER;

   public static String EXC_THE_DATATYPE_NULL_IS_NOT_VALID_CLASSIFIER;

   public static String EXC_THE_VALUE_NULL_IS_NOT_VALID_ENUMERATION_OF_ONE;

   public static String EXC_UNSUPPORTED_BOOLEAN_EXPRESSION_NULL;

   public static String EXC_WITH_IS_NOT_SUPPORTED;

   public static String ROUTE_ACTIVITY;

   public static String MANUAL_ACTIVITY;

   public static String APPLICATION_ACTIVITY;

   public static String SUBPROCESS_ACTIVITY;

   public static String JOIN_SPLIT_LOOP_AND;

   public static String JOIN_SPLIT_LOOP_XOR;

   public static String JOIN_SPLIT_LOOP_NONE;

   public static String JOIN_SPLIT_LOOP_NOLOOP;

   public static String JOIN_SPLIT_LOOP_WHILE;

   public static String JOIN_SPLIT_LOOP_REPEAT;

   public static String JOIN_SPLIT_LOOP_UNKNOWN;

   public static String SYNC_SHARED;

   public static String SYNC_SEPARATE;
   
   public static String ASYNC_SEPARATE;
   
   public static String NEW_ENTRY;
   
   public static String EXC_FAILED_INSTANTIATING_EXTENSION;


}
