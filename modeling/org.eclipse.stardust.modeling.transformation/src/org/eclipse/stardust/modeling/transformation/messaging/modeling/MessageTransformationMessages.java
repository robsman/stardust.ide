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
package org.eclipse.stardust.modeling.transformation.messaging.modeling;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author mgille
 */
public class MessageTransformationMessages extends NLS
{
	private static final String BUNDLE_NAME = "com.sungard.bpm.messaging.transformation.modeling.message-transformation-messages"; //$NON-NLS-1$

	private MessageTransformationMessages()
	{
	}

	static
	{
		// Initialize resource bundle
		
		NLS.initializeMessages(BUNDLE_NAME, MessageTransformationMessages.class);
	}

	public static String PLAIN_TEXT_TEMPLATE_LABEL;
}
