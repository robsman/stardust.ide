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
package org.eclipse.stardust.modeling.common.worktime;

import java.util.Date;

import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;

/**
 * Defines a Holiday
 *
 */
public class Holiday
{
	private static final Logger log = LogManager.getLogger(Holiday.class);

	private String name;
	private long time;
	private boolean holiday;
	private boolean nationWide;
	private String[] states;

	/**
	 * @param name name of holiday
	 * @param time date of holiday in long time format
	 * @param holiday true or false
	 * @param nationWide valid for whole nation
	 * @param states valid only for certain states
	 */
	public Holiday(String name, long time, boolean holiday,
				boolean nationWide, String[] states)
	{
		super();
		
		this.name = name;
		this.time = time;
		this.holiday = holiday;
		this.nationWide = nationWide;
		this.states = states;
		
		log.debug("Holiday " + name + " created for date " + new Date(time));
	}

	public boolean isHoliday()
	{
		return holiday;
	}

	public void setHoliday(boolean holiday)
	{
		this.holiday = holiday;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isNationWide()
	{
		return nationWide;
	}

	public void setNationWide(boolean nationWide)
	{
		this.nationWide = nationWide;
	}

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public String[] getStates()
	{
		return states;
	}

	public void setStates(String[] states)
	{
		this.states = states;
	}
}
