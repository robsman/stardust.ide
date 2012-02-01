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
package org.eclipse.stardust.modeling.project;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Project_Messages
{
	private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.project.project-messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = (ResourceBundle
			.getBundle(BUNDLE_NAME));

	/**
	 * 
	 * 
	 */
	private Project_Messages()
	{
	}

	/**
	 * 
	 * @return
	 */
	public static ResourceBundle getResourceBundle()
	{
		return RESOURCE_BUNDLE;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key)
	{
		String message = null;

		try
		{
			message = RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e)
		{
			return '!' + key + '!';
		}

		return message;
	}

	public static String getFormattedString(String key, Object[] arguments)
	{
		return MessageFormat.format(getString(key), arguments);
	}
}
