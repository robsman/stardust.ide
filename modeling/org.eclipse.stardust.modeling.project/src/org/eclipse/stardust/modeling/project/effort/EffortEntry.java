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
package org.eclipse.stardust.modeling.project.effort;

import org.eclipse.stardust.modeling.project.Project_Messages;

public class EffortEntry
{
	private EffortParameterScope scope;
    
	private String name;
	private String costDriver;
	private String valueString;
    
	private double[] effort;

	public EffortEntry(EffortParameterScope scope,
          String name, String costDriver, String valueString)
	{
		super();
		
		this.scope = scope;
		this.name = name;
		this.costDriver = costDriver;
		
		EffortParameter effortParameter = scope.getParameter(costDriver); 

		this.valueString = valueString;
		this.effort = effortParameter.calculateEffort(valueString);
	}

	public String getCostDriver()
	{
		return costDriver;
	}

	public String getName()
	{
		return name;
	}

	public String getValueString()
	{
		return valueString;
	}
	
	public double[] getEffort()
	{
		return effort;
	}

	public EffortParameterScope getScope()
	{
		return scope;
	}
    
    public String getSimpleName()
    {
       return Project_Messages.getString(getScope().getSimpleName());
    }
}
