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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;


public class ComplexAttributeUtils
{
	/**
	 * 
	 * @param element
	 * @param property
	 * @param table
	 * @param identificationMethodName
	 * @return
	 */
	public static void selectValuesInTable(IExtensibleElement element,
				String property, Table table, String identificationMethodName)
	{
		int n = 0;
		ArrayList list = new ArrayList();

		while (true)
		{
			String value = AttributeUtil.getAttributeValue(element, property + "["
						+ n + "]");

			if (value != null)
			{
				list.add(value);
			}
			else
			{
				break;
			}

			++n;
		}

		TableItem[] items = table.getItems();
		TableItem[] selectedItems = new TableItem[list.size()];
		Method identificationMethod = null;

		n = 0;

		for (int m = 0; m < items.length; ++m)
		{
			if (identificationMethod == null)
			{
				try
				{
					identificationMethod = items[m].getData().getClass().getMethod(
								identificationMethodName, null);
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

			Object object = items[m].getData();

			for (int l = 0; l < list.size(); ++l)
			{
				String applicationID = (String) list.get(l);

				try
				{
					if (applicationID.equals(identificationMethod.invoke(object,
								null)))
					{
						selectedItems[n] = items[m];

						++n;

						break;
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
		}

		table.setSelection(selectedItems);
	}

	/**
	 * 
	 * @param element
	 * @param property
	 * @param table
	 * @param identificationMethodName
	 */
	public static void getSelectedValuesFromTable(IExtensibleElement element,
				String property, Table table, String identificationMethodName)
	{
		TableItem[] selectedItems = table.getSelection();
		Method identificationMethod = null;

		for (int n = 0; n < selectedItems.length; ++n)
		{
			if (identificationMethod == null)
			{
				try
				{
					identificationMethod = selectedItems[n].getData().getClass()
								.getMethod(identificationMethodName, null);
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

			Object object = selectedItems[n].getData();

			try
			{
				AttributeUtil.setAttribute((IExtensibleElement) element, property
							+ "[" + n + "]", identificationMethod.invoke(object, null)
							.toString());
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
	}

	/**
	 * 
	 * @param element
	 * @param property
	 * @param table
	 * @param identificationMethodName
	 * @return
	 */
	public static void setValuesInObjectTable(IExtensibleElement element,
				String property, ObjectTable objectTable)
	{
		int n = 0;
		ArrayList list = new ArrayList();

		while (true)
		{
			Object object = null;

			for (int m = 0; m < objectTable.getPropertyNames().length; ++m)
			{
				String value = AttributeUtil.getAttributeValue(element, property
							+ "[" + n + "]." + objectTable.getPropertyNames()[m]);

				if (value != null)
				{
					if (object == null)
					{
						object = objectTable.createObject();

						list.add(object);
					}

					// TODO Number column handling

					objectTable.setValueFromString(object, m + 1, value);
				}
			}

			if (object == null)
			{
				objectTable.setObjects(list);

				return;
			}

			++n;
		}
	}

	/**
	 * 
	 * @param element
	 * @param property
	 * @param table
	 */
	public static void getValuesFromObjectTable(IExtensibleElement element,
				String property, ObjectTable table)
	{
		// Nullify original properties
		
		int k = 0;
		
		while (true)
		{
			String value = null;
			
			for (int m = 0; m < table.getPropertyNames().length; ++m)
			{
				value = AttributeUtil.getAttributeValue(element, property
							+ "[" + k + "]." + table.getPropertyNames()[m]);

				if (value != null)
				{
					AttributeUtil
					.setAttribute((IExtensibleElement) element,
								property + "[" + k + "]."
											+ table.getPropertyNames()[m], null);
				}
				else
				{
					break;
				}
			}

			if (value == null)
			{
				break;
			}
			
			++k;
		}

		// Set properties for new objects
		
		for (int n = 0; n < table.getObjects().size(); ++n)
		{
			Object object = table.getObjects().get(n);

			for (int m = 0; m < table.getPropertyNames().length; ++m)
			{
				// TODO Number column

				String value = table.getStringifiedValue(object, m + 1);

				if (value != null)
				{
					AttributeUtil
								.setAttribute((IExtensibleElement) element,
											property + "[" + n + "]."
														+ table.getPropertyNames()[m], value);
				}
			}
		}
	}
	/**
	 * 
	 * @param element
	 * @param property
	 * @param table
	 * @param identificationMethodName
	 * @return
	 */
	public static List getListValues(IExtensibleElement element,
				String property, MetadataManager metadataManager)
	{
		int n = 0;
		ArrayList list = new ArrayList();

		while (true)
		{
			Object object = null;

			for (int m = 0; m < metadataManager.getPropertyNames().length; ++m)
			{
				String value = AttributeUtil.getAttributeValue(element, property
							+ "[" + n + "]." + metadataManager.getPropertyNames()[m]);

				if (value != null)
				{
					if (object == null)
					{
						object = metadataManager.createObject();

						list.add(object);
					}

					metadataManager.setPropertyFromString(object, m, value);
				}
			}

			if (object == null)
			{
				return list;
			}

			++n;
		}
	}
}
