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
package org.eclipse.stardust.modeling.integration.mail;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author mgille
 */
public class Mail_Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.integration.mail.mail-messages"; //$NON-NLS-1$

	private Mail_Messages()
	{
	}

	static
	{
		// Initialize resource bundle
		
		NLS.initializeMessages(BUNDLE_NAME, Mail_Messages.class);
	}

	public static String BOX_HTML;
	public static String BUT_TEST;
	public static String PLAIN_TEXT_TEMPLATE_LABEL;
	public static String HTML_HEADER_LABEL;
	public static String HTML_TEMPLATE_LABEL;
	public static String HTML_FOOTER_LABEL;
	public static String DEFAULT_TO_LABEL;
	public static String DEFAULT_CC_LABEL;
	public static String DEFAULT_BCC_LABEL;
	public static String DEFAULT_PRIORITY_LABEL;
	public static String MAIL_SERVER_LABEL;
	public static String URL_PREFIX_LABEL;
	public static String DEFAULT_FROM_LABEL;
	public static String DEFAULT_SUBJECT_LABEL;
	public static String SUBJECT_INCLUDE_UNIQUE_IDENTIFIED;
	public static String COMBO_BOX_NORMAL;
	public static String CREATE_PROCESS_HISTORY_LINK_LABEL;
	public static String MAIL_RESPONSE_LABEL;
	public static String MSG_TXT_TEST_MESSAGE_FOR_INFINITY_MAIL_APPLICATION_TYPE;
	public static String PRIORITY_LOWEST_VALUE;
	public static String PRIORITY_LOW_VALUE;
	public static String PRIORITY_NORMAL_VALUE;
	public static String PRIORITY_HIGH_VALUE;
	public static String PRIORITY_HIGHEST_VALUE;
	public static String TExT_PLAIN_TEXT_TEMPLATE;
	public static String TXT_DEFAULT_SETTINGS;
	public static String TXT_HTML_TEMPLATE;
	public static String TXT_INVALID_MAIL_ADRESS;
	public static String TXT_NO_MAIL_SERVER_SPECIFIED;
	public static String TXT_NO_RECEIVER_MAIL_ADDRESS_SPECIFIED;
	public static String TXT_NO_SENDER_MAIL_ADDRESS_SPECIFIED;
	public static String TXT_OUTPUT_VALUES;
	
}
