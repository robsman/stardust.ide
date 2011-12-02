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
package org.eclipse.stardust.modeling.integration.ejb30;

import org.eclipse.osgi.util.NLS;

public class EJB30_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.integration.ejb30.ejb30-messages"; //$NON-NLS-1$

  
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EJB30_Messages.class);
	}

	public static String LB_JNDIPath;
	public static String LBL_BEAN_CLASS_NAME;
	public static String LBL_ENTITY_MANAGER;
	public static String LBL_FACTORY_JNDI;
	public static String LBL_JNDI;
	public static String LBL_PRIMARY_KEY_CL;
	public static String LBL_PRIMARY_KEY_ELEMENT;
	public static String LBL_PRIMARY_KEY_TYPE;
	public static String LBL_UNIT_NAME;
	public static String LBL_UNIT_NAMEE;
	public static String MSG_TYPE_IS_NOT_ANNOTATED_AS_ENTITY;
	public static String MSG_TYPE_STELLE_NULL_IS_NOT_ANNTATED;
	public static String SESSION_BEAN30_PROPERTY_PAGE_LABEL_COMPLETION_METHOD;
	public static String SESSION_BEAN30_PROPERTY_PAGE_LABEL_INITIALIZATION_METHOD;
	public static String SESSION_BEAN30_PROPERTY_PAGE_LABEL_JNDI_PATH;
	public static String SESSION_BEAN30_PROPERTY_PAGE_TITLE_BUSINESS_INTERFACE;
	public static String SESSION_BEAN30_PROPERTY_PAGE_LABEL_BUSINESS_INTERFACE;
	public static String SESSION_BEAN30_PROPERTY_PAGE_TITLE_BEAN_CLASS;
	public static String SESSION_BEAN30_PROPERTY_PAGE_LABEL_BEAN_CLASS;

   public static String TXT_EMBEDDED_ID;
	public static String TXT_ENTITY_BEAN_CL;
	public static String TXT_FIELD;
	public static String TXT_ID;
	public static String TXT_ID_CL;
	public static String TXT_ID_CLASS;
	public static String TXT_PROPERTY;


private EJB30_Messages()
   {}

}
