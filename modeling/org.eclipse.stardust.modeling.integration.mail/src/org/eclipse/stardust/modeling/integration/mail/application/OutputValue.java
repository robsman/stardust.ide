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
package org.eclipse.stardust.modeling.integration.mail.application;

import org.eclipse.stardust.modeling.common.ui.swt.MetadataManager;

public class OutputValue
{
	String name;
	String value;
	
	public static MetadataManager metadataManager = new MetadataManager(OutputValue.class, new String[]
	                                                                     					{ "name" , "value"}); //$NON-NLS-1$ //$NON-NLS-2$
	
	public OutputValue()
	{
		super();
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
}
