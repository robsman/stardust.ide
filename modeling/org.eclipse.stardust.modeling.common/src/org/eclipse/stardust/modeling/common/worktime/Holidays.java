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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;

/**
 * Represents the german holidays of a year.
 * 
 */
public class Holidays
{
	private static final Logger log = LogManager.getLogger(Holidays.class);

	public static Holidays[] HOLIDAYS = new Holidays[] { new Holidays(2001),
         new Holidays(2002), new Holidays(2003), new Holidays(2004), new Holidays(2005),
         new Holidays(2006), new Holidays(2007), new Holidays(2008), new Holidays(2009),
         new Holidays(2010), new Holidays(2011), new Holidays(2012), new Holidays(2013),
         new Holidays(2014), new Holidays(2015), new Holidays(2016), new Holidays(2017),
         new Holidays(2018), new Holidays(2019), new Holidays(2020), new Holidays(2021),
         new Holidays(2022), new Holidays(2023), new Holidays(2024), new Holidays(2025),
         new Holidays(2026), new Holidays(2027), new Holidays(2028), new Holidays(2029),
         new Holidays(2030) };
   
   private List<Holiday> days = new ArrayList<Holiday>(34);

	/**
	 * Sets all the holidays (national and regional) for a given year.
	 * @param year
	 */
	public Holidays(int year)
	{
		log.debug("Creating holidays for year " + year);

		GregorianCalendar eastern = eastern(year);
		GregorianCalendar temp;

		days.add(new Holiday("Neujahr", new GregorianCalendar(year, 0, 1)
					.getTimeInMillis(), true, true, null));
		days.add(new Holiday("Heilige drei Könige", new GregorianCalendar(year,
					0, 6).getTimeInMillis(), true, true, new String[]
		{ States.BAYERN, States.BADEN_WUERTTEMBERG, States.SACHSEN_ANHALT }));

		// Osterfeiertage

		temp = (GregorianCalendar) eastern.clone();

		temp.add(Calendar.DAY_OF_MONTH, -2);
		days.add(new Holiday("Karfreitag", temp.getTimeInMillis(), true, true,
					null));
		temp.add(Calendar.DAY_OF_MONTH, +2);
		days.add(new Holiday("Ostersonntag", temp.getTimeInMillis(), true, true,
					null));
		temp.add(Calendar.DAY_OF_MONTH, +1);
		days.add(new Holiday("Ostermontag", temp.getTimeInMillis(), true, true,
					null));

		// Maifeiertag

		days.add(new Holiday("Tag der Arbeit", new GregorianCalendar(year, 4, 1)
					.getTimeInMillis(), true, true, null));

		// Himmelfahrt

		temp = (GregorianCalendar) eastern.clone();

		temp.add(Calendar.DAY_OF_MONTH, 39);

		days.add(new Holiday("Himmelfahrt", temp.getTimeInMillis(), true, true,
					null));

		// Pfingsten

		temp = (GregorianCalendar) eastern.clone();

		temp.add(Calendar.DAY_OF_MONTH, 49);
		days.add(new Holiday("Pfingssonntag", temp.getTimeInMillis(), true, true,
					null));
		temp.add(Calendar.DAY_OF_MONTH, 1);
		days.add(new Holiday("Pfingsmontag", temp.getTimeInMillis(), true, true,
					null));

		// Fronleichnam

		temp = (GregorianCalendar) eastern.clone();

		temp.add(Calendar.DAY_OF_MONTH, 60);

		days.add(new Holiday("Fronleichnam", temp.getTimeInMillis(), true, true,
					new String[]
					{ States.BAYERN, States.BADEN_WUERTTEMBERG,
								States.NORDRHEIN_WESTFALEN, States.HESSEN,
								States.RHEINLAND_PFALZ, States.SAARLAND,
								States.SACHSEN, States.THUERINGEN }));

		// Tag der Deutschen Einheit

		days.add(new Holiday("Tag der deutschen Einheit", new GregorianCalendar(
					year, 9, 3).getTimeInMillis(), true, true, null));

		// Reformationstag

		days.add(new Holiday("Reformationstag",
					new GregorianCalendar(year, 9, 31).getTimeInMillis(), true,
					true, new String[]
					{ States.BRANDENBURG, States.MECKLENBURG_VORPOMMERN,
								States.SACHSEN, States.SACHSEN_ANHALT,
								States.THUERINGEN }));

		days.add(new Holiday("Allerheiligen", new GregorianCalendar(year, 10, 1)
					.getTimeInMillis(), true, true, new String[]
		{ States.BADEN_WUERTTEMBERG, States.BAYERN, States.NORDRHEIN_WESTFALEN,
					States.RHEINLAND_PFALZ, States.SAARLAND }));

		// Weihnachtsfeiertage

		days.add(new Holiday("Heiligabend", new GregorianCalendar(year, 11, 24)
					.getTimeInMillis(), true, true, null));
		days.add(new Holiday("1. Weihnachtstag", new GregorianCalendar(year, 11,
					25).getTimeInMillis(), true, true, null));
		days.add(new Holiday("2. Weihnachtstage", new GregorianCalendar(year, 11,
					26).getTimeInMillis(), true, true, null));
	}

	/**
	 * Returns true if the holiday is a common (national) holiday.
	 * @param day
	 * @return
	 */
	public static boolean isNationalHoliday(Calendar day)
	{
		Holiday calendarDay = getHolidaysForYear(day).findHoliday(day);

		return calendarDay != null;
	}

	/**
	 * Returns true if the holiday is a national or a regional holiday.
	 * @param day
	 * @param state
	 * @return
	 */
	public static boolean isRegionalHoliday(Calendar day, String region)
	{
		Holiday calendarDay = getHolidaysForYear(day).findHoliday(day);

		if (calendarDay == null)
		{
			return false;
		}
		
		if (calendarDay.getStates() == null)
		{
			return true;
		}
		
		for (int n = 0; n < calendarDay.getStates().length; ++n)
		{
			if (calendarDay.getStates()[n].equals(region))
			{
				return true; 
			}
		}
		
		return false;
	}

	/**
	 * Finds a particular holiday for a given day.
	 * @param day
	 * @return
	 */
	private Holiday findHoliday(Calendar day)
	{
		for (int n = 0; n < days.size(); ++n)
		{
			Holiday currentDay = days.get(n);

			if (currentDay.getTime() == day.getTimeInMillis())
			{
				log.debug("Day found " + currentDay.getName());

				return currentDay;
			}
		}

		return null;
	}

	/**
	 * Retrieves all holidays for the year of day.
	 * 
	 * @param day
	 * @return
	 */
	private static Holidays getHolidaysForYear(Calendar day)
	{
		int year = day.get(Calendar.YEAR);

		if (year < 2001 || year > 2030)
		{
			throw new UnsupportedOperationException("Year " + year
						+ " is not supported.");
		}

		return HOLIDAYS[year - 2001];
	}

	/**
	 * Compute Eastern of the year provided in <code>ï¿½ear</code>. Butcher's
	 * algorithm.
	 * 
	 * @param year
	 * @return
	 */
	private static GregorianCalendar eastern(int year)
	{
		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int j = c % 4;
		int k = (32 + 2 * e + 2 * i - h - j) % 7;
		int l = (a + 11 * h + 22 * k) / 451;
		int x = h + k - 7 * l + 114;
		int mon = x / 31;
		int tag = (x % 31) + 1;

		return new GregorianCalendar(year, mon - 1, tag);
	}
}
