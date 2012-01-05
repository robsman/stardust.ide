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
package org.eclipse.stardust.modeling.data.structured.properties;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.XSDMapping;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDRepeatableFacet;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;

import com.gface.date.DatePickerCombo;

public class DelegateCellEditor extends CellEditor
{
   private TreeEditor treeEditor;
   private TreeItem previousTreeItem;
   private TreeItem treeItem;
   
   private TableEditor tableEditor;
   private TableItem previousTableItem;
   private TableItem tableItem;
   
   private boolean enableEditor;
   
   private TextCellEditor textCellEditor;  
   private ComboBoxCellEditor booleanEditor;
   private ComboBoxCellEditor enumerationEditor;
   private DatePickerCombo calendarEditor;
   
   private Object currentEditor;
   
   private DateFormat dateFormat = new SimpleDateFormat(Structured_Messages.SIMPLE_DATE_FORMAT, Locale.GERMANY);
   
   public DelegateCellEditor(Composite parent)
   {
      if(parent instanceof Table)         
      {
         tableEditor = new TableEditor((Table) parent);
         tableEditor.grabHorizontal = true;
         tableEditor.grabVertical = true;
      }
      else
      {
         treeEditor = new TreeEditor((Tree) parent);
         treeEditor.grabHorizontal = true;
         treeEditor.grabVertical = true;
      }
      
      textCellEditor = new TextCellEditor(parent);
      booleanEditor = new ComboBoxCellEditor(parent, new String[0])
      {
         public void setItems(String[] items)
         {
            String[] booleanValues = new String[] {
                  "", //$NON-NLS-1$ 
                  "true", //$NON-NLS-1$
                  "false"}; //$NON-NLS-1$
            super.setItems(booleanValues);
         }         
      };      
      enumerationEditor = new ComboBoxCellEditor(parent, new String[0]);
      
      calendarEditor = new DatePickerCombo(parent, SWT.BORDER);
      calendarEditor.setDateFormat(dateFormat);
   }
   
   protected Control createControl(Composite parent)
   {
      return null;
   }

   protected Object doGetValue()
   {
      if(currentEditor != null)
      {
         if(currentEditor instanceof CellEditor)
         {
            Object object = ((CellEditor) currentEditor).getValue();
            if(currentEditor.equals(booleanEditor))
            {
               if(((Integer) object).intValue() < 0)
               {
                  return null;
               }
               String[] items = booleanEditor.getItems();
               List<String> list = Arrays.asList(items);               
               booleanEditor.setValue((Integer) object);
               return list.get(((Integer) object).intValue());
            }
            else if(currentEditor.equals(enumerationEditor))
            {
               if(((Integer) object).intValue() < 0)
               {
                  return null;
               }
               String[] items = enumerationEditor.getItems();
               List<String> list = Arrays.asList(items); 
               enumerationEditor.setValue((Integer) object);
               return list.get(((Integer) object).intValue());
            }
            ((CellEditor) currentEditor).setValue(object);
            return object;
         }
         if(currentEditor instanceof DatePickerCombo)
         {
            Object object = ((DatePickerCombo) currentEditor).getText();
            return object;
         }         
      }
      return null;
   }

   protected void doSetFocus()
   {
   }

   protected void doSetValue(Object value)
   {
   }

   public void enableEditor(boolean enable)
   {
      enableEditor = enable;
      if(!enableEditor)
      {
         calendarEditor.setVisible(false);
         currentEditor = null;
         if(treeEditor != null)
         {
            treeEditor.setEditor(null);         
            treeEditor.layout();      
         }
         else if(tableEditor != null)
         {
            tableEditor.setEditor(null);
            tableEditor.layout();
         }               
      }
   }

   public Item setItem(Item item)
   {
      currentEditor = null;
      calendarEditor.setVisible(false);
      enumerationEditor.setItems(new String[0]);
      
      Object element = item.getData();
      Object setValue = null;
      String javaTypeName = null;
      XSDSimpleTypeDefinition simpleType = null;
      
      if(enableEditor)
      {
         if (element instanceof XSDAttributeDeclaration)
         {
            XSDTypeDefinition typeDefinition = ((XSDAttributeDeclaration) element).getTypeDefinition();            
            if(typeDefinition.getComplexType() == null)
            {
               simpleType = typeDefinition.getSimpleType();               
               javaTypeName = XSDMapping.getJavaTypeForXSDType(typeDefinition.getName());
               setValue = DefaultValueModifier.getStringForElement((XSDComponent) element);
            }         
         }
         else if(element instanceof XSDElementDeclaration)
         {
            XSDTypeDefinition typeDefinition = ((XSDElementDeclaration) element).getTypeDefinition();            
            if(typeDefinition != null && typeDefinition.getComplexType() == null)
            {
               simpleType = typeDefinition.getSimpleType();               
               javaTypeName = XSDMapping.getJavaTypeForXSDType(typeDefinition.getName());
               setValue = DefaultValueModifier.getStringForElement((XSDComponent) element);
            }         
         }
      }
      
      if(item instanceof TreeItem)
      {
         if(treeItem != null)
         {
            previousTreeItem = treeItem;
         }
         else
         {
            previousTreeItem = (TreeItem) item;
         }
         treeItem = (TreeItem) item;         
      }
      else if(item instanceof TableItem)
      {
         if(tableItem != null)
         {
            previousTableItem = tableItem;
         }
         else
         {
            previousTableItem = (TableItem) item;
         }
         tableItem = (TableItem) item;         
      }
      
      if(!StringUtils.isEmpty(javaTypeName))
      {
         if(javaTypeName.equals(Date.class.getName()))
         {
            calendarEditor.setVisible(true);
            currentEditor = calendarEditor;
            if(treeEditor != null)
            {
               treeEditor.setEditor(calendarEditor, treeItem, 3);  
            }
            else if(tableEditor != null)
            {
               tableEditor.setEditor(calendarEditor, tableItem, 1);                    
            }    
            calendarEditor.setFocus();
         }         
         else if(javaTypeName.equals(Boolean.class.getName()))
         {
            currentEditor = booleanEditor;
            if(treeEditor != null)
            {
               treeEditor.setEditor(booleanEditor.getControl(), treeItem, 3);  
            }
            else if(tableEditor != null)
            {
               tableEditor.setEditor(booleanEditor.getControl(), tableItem, 1);                    
            }               
            booleanEditor.setFocus();
         }
         else
         {
            currentEditor = textCellEditor;
            if(treeEditor != null)
            {
               treeEditor.setEditor(textCellEditor.getControl(), treeItem, 3);         
            }
            else if(tableEditor != null)
            {
               tableEditor.setEditor(textCellEditor.getControl(), tableItem, 1);                    
            }     
            textCellEditor.setFocus();
         }      
      }
      else if(simpleType != null)
      {
         List<? extends XSDRepeatableFacet> elements = simpleType.getEnumerationFacets();
         if (elements.isEmpty())
         {
            elements = simpleType.getPatternFacets();
         }
         if (!elements.isEmpty())
         {
            String[] namesArray = new String[elements.size() + 1];
            namesArray[0] = ""; //$NON-NLS-1$            
            for (int i = 0; i < elements.size(); i++)
            {
               XSDConstrainingFacet entry = (XSDConstrainingFacet) elements.get(i);
               namesArray[i + 1] = entry.getLexicalValue(); 
            }            
            enumerationEditor.setItems(namesArray);
            
            currentEditor = enumerationEditor;
            if(treeEditor != null)
            {
               treeEditor.setEditor(enumerationEditor.getControl(), treeItem, 3);  
            }
            else if(tableEditor != null)
            {
               tableEditor.setEditor(enumerationEditor.getControl(), tableItem, 1);                    
            }               
            enumerationEditor.setFocus();
         }
      }
         
      if(currentEditor != null)
      {
         if(currentEditor.equals(calendarEditor))
         {
            if(!StringUtils.isEmpty((String) setValue))
            {
               Date date;
               try {
                  date = dateFormat.parse((String) setValue);
               } catch (ParseException e) {
                  date = null;
               }
               if(date != null)
               {
                  ((DatePickerCombo) currentEditor).setDate(date);
               }
            }
         }            
         else if(currentEditor.equals(booleanEditor))
         {
            ((ComboBoxCellEditor) currentEditor).setFocus();
            String[] items = ((ComboBoxCellEditor) currentEditor).getItems();
            List<String> list = Arrays.asList(items);               
            ((ComboBoxCellEditor) currentEditor).setValue(new Integer(list.lastIndexOf(setValue)));
         }
         else if(currentEditor.equals(enumerationEditor))
         {
            ((ComboBoxCellEditor) enumerationEditor).setFocus();
            String[] items = ((ComboBoxCellEditor) enumerationEditor).getItems();
            List<String> list = Arrays.asList(items);               
            ((ComboBoxCellEditor) enumerationEditor).setValue(new Integer(list.lastIndexOf(setValue)));
         }         
         else if(currentEditor.equals(textCellEditor))
         {
            if(setValue == null)
            {
               ((TextCellEditor) currentEditor).setValue("");                   //$NON-NLS-1$
            }
            else
            {
               ((TextCellEditor) currentEditor).setValue(setValue);                  
            }
         }
      }            
   
      if(treeEditor != null)
      {
         treeEditor.layout();      
      }
      else if(tableEditor != null)
      {
         tableEditor.layout();
      }    

      Item returnValue = null;
      if(item instanceof TreeItem)
      {
         returnValue = previousTreeItem;         
      }
      else if(item instanceof TableItem)
      {
         returnValue = previousTableItem;         
      }
      return returnValue;
   }
}