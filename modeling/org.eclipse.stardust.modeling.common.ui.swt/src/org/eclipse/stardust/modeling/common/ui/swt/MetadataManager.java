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
package org.eclipse.stardust.modeling.common.ui.swt;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Manages metadata for a given class and perfomes operations like creation or
 * invocation of getters and setters.
 * 
 * @author gille
 * 
 */
public class MetadataManager
{
	private Class type;
	private Constructor defaultConstructor;
	private String[] propertyNames;
	private Method[] getters;
	private Method[] setters;
	private DateFormat dateFormat;

	public MetadataManager(Class type, String[] columnProperties)
	{
		this.type = type;

		try
		{
			defaultConstructor = type.getConstructor(null);
			
			// Ignored, because it may not always be needed
		}
		catch (SecurityException e)
		{
		}
		catch (NoSuchMethodException e)
		{
		}

		propertyNames = columnProperties;
		getters = new Method[columnProperties.length];
		setters = new Method[columnProperties.length];

		for (int n = 0; n < columnProperties.length; ++n)
		{
			try
			{
				getters[n] = type.getMethod(
							getGetterMethodName(columnProperties[n]), null);
			}
			catch (SecurityException e)
			{
				throw new RuntimeException(e);
			}
			catch (NoSuchMethodException e)
			{
				throw new RuntimeException(e);
			}
		}

		for (int n = 0; n < columnProperties.length; ++n)
		{
			try
			{
				Class returnType = getters[n].getReturnType();

				setters[n] = type.getMethod(
							getSetterMethodName(columnProperties[n]), new Class[]
							{ returnType });
			}
			catch (SecurityException e)
			{
				// Ignore
			}
			catch (NoSuchMethodException e)
			{
				// Ignore
			}
		}

		dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	}

	public String[] getPropertyNames()
	{
		return propertyNames;
	}

	/**
	 * 
	 * 
	 */
	public Object createObject()
	{
		if (defaultConstructor == null)
		{
			throw new UnsupportedOperationException("No default constructor for class " + type.getName() + ".");
		}
		
		try
		{
			return defaultConstructor.newInstance(null);
		}
		catch (IllegalArgumentException e)
		{
			throw new RuntimeException(e);
		}
		catch (InstantiationException e)
		{
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param object
	 * @param index
	 * @param value
	 */
	public void setPropertyFromString(Object object, int index, String value)
	{
		try
		{
			if (getters[index].getReturnType() == Date.class)
			{
				try
				{
					setters[index].invoke(object, new Object[]
					{ dateFormat.parse(value) });
				}
				catch (ParseException e)
				{
					throw new RuntimeException(e);
				}
			}
			else
			{
				setters[index].invoke(object, new Object[]
				{ value });
			}
		}
		catch (IllegalArgumentException e)
		{
			throw new RuntimeException("Illegal argument \"" + value
						+ "\" for property \"" + propertyNames[index] + "\".");
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param object
	 * @param index
	 * @return
	 */
	public String getStringifiedValue(Object object, int index)
	{
		try
		{
			Object value = getters[index].invoke(object, null);

			if (value == null)
			{
				return null;
			}

			if (getters[index].getReturnType() == Date.class)
			{
				return dateFormat.format((Date) value);
			}
			else
			{
				return (String) value;
			}
		}
		catch (IllegalArgumentException e)
		{
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e)
		{
			throw new RuntimeException(e);
		}
	}

	public Class getType(int index)
	{
		return getters[index].getReturnType();
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public static String getGetterMethodName(String property)
	{
		String suffix = property.substring(0, 1).toUpperCase()
					+ property.substring(1);

		return "get" + suffix;
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public static String getSetterMethodName(String property)
	{
		String suffix = property.substring(0, 1).toUpperCase()
					+ property.substring(1);

		return "set" + suffix;
	}
}
