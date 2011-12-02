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
/*
 */
package org.eclipse.stardust.modeling.integration.mail.application;

import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.JavaApplicationInitializer;

/**
 * 
 * @author mgille
 */
public class MailApplicationInitializer extends JavaApplicationInitializer
{
	public MailApplicationInitializer(String sourceName, String targetName)
	{
		super(sourceName, targetName);
	}
}
