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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.Code;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.QualityControlType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class QualityControlCodesPropertyPage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   private CarnotWorkflowModelFactory cwm = CarnotWorkflowModelFactory.eINSTANCE;
   
   static final int ADD_BUTTON = 0;
   static final int DELETE_BUTTON = ADD_BUTTON + 1;
   private Button[] buttons;

   private String newCode = "<new>"; //$NON-NLS-1$
   
   private TableViewer viewer;

   protected Code selectedVariable;
   private EList<Code> qualityControlCode;

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (element instanceof ModelType)
      {
         ModelType model = (ModelType) element;
         QualityControlType qualityControl = model.getQualityControl();
         if(qualityControl == null)
         {
            qualityControl = cwm.createQualityControlType();
            model.setQualityControl(qualityControl);
         }         
         qualityControlCode = qualityControl.getCode();         
      }      
      
      viewer.setInput(qualityControlCode);
      viewer.refresh();
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(table);

      viewer = new TableViewer(table);
      TableUtil.createColumns(table, new String[] {
            Diagram_Messages.QUALITY_CONTROL_CODE, Diagram_Messages.QUALITY_CONTROL_DESCRIPTION});
      TableUtil.setInitialColumnSizes(table, new int[] {30, 70});

      viewer.setContentProvider(new IStructuredContentProvider()
      {
         public void dispose()
         {
         }

         public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
         {
         }

         public Object[] getElements(Object inputElement)
         {
            EList<Code> list = (EList<Code>) inputElement;
            Object[] data = list.toArray(new Object[list.size()]);
            
            return data;
         }         
      });
      viewer.setLabelProvider(new ITableLabelProvider()
      {         
         public void removeListener(ILabelProviderListener listener)
         {
         }
         
         public boolean isLabelProperty(Object element, String property)
         {
            return true;
         }
         
         public void dispose()
         {
         }
         
         public void addListener(ILabelProviderListener listener)
         {
         }
         
         public String getColumnText(Object element, int columnIndex)
         {
            Code entry = (Code) element;
            return (columnIndex > 0 ? entry.getValue() : entry.getCode());
         }
         
         public Image getColumnImage(Object element, int columnIndex)
         {
            return null;
         }
      });
            
      attachCellEditors(viewer, parent);

      return composite;
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[BUTTON_COUNT];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {                  
                  Code newQualityControlCode = cwm.createCode();
                  newQualityControlCode.setCode(newCode); 
                  newQualityControlCode.setValue(""); //$NON-NLS-1$

                  qualityControlCode.add(newQualityControlCode);
                  viewer.refresh();
                  validateCodes(newQualityControlCode);
               }
            });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_Delete, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  if (selectedVariable != null)
                  {
                     qualityControlCode.remove(selectedVariable);
                     selectedVariable = null;                     
                     viewer.refresh();                     
                  }
               }
            });
      return buttons;
   }

   public Object getSelection()
   {
      return null;
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
   }

   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(parent);
   }

   private void attachCellEditors(final TableViewer viewer, Composite parent)
   {
      viewer.setCellModifier(new ICellModifier()
      {
         public boolean canModify(Object element, String property)
         {
            return true;
         }

         public Object getValue(Object element, String property)
         {
            if (element instanceof Code)
            {
               Code modelVariable = (Code) element;
               if (Diagram_Messages.QUALITY_CONTROL_CODE.equals(property))
               {
                  return modelVariable.getCode();
               }
               if (Diagram_Messages.QUALITY_CONTROL_DESCRIPTION.equals(property))
               {
                  return modelVariable.getValue();
               }
            }
            return element;
         }

         public void modify(Object element, String property, Object value)
         {
            if (element instanceof TableItem)
            {
               TableItem tableItem = (TableItem) element;
               selectedVariable = (Code) tableItem.getData();
               if (Diagram_Messages.QUALITY_CONTROL_CODE.equals(property))
               {
                  selectedVariable.setCode((String) value);
               }
               if (Diagram_Messages.QUALITY_CONTROL_DESCRIPTION.equals(property))
               {
                  selectedVariable.setValue((String) value);
               }
            }
            viewer.refresh();
            validateCodes(selectedVariable);
         }
      });

      viewer.setCellEditors(new CellEditor[] {
            new TextCellEditor(viewer.getTable())
            {
               protected void doSetValue(Object value)
               {
                  super.doSetValue(value);
               }
               
            },
            new TextCellEditor(viewer.getTable())
            {
               protected void doSetValue(Object value)
               {
                  super.doSetValue(value);
               }               
            }});

      viewer.setColumnProperties(new String[] {
            Diagram_Messages.QUALITY_CONTROL_CODE, Diagram_Messages.QUALITY_CONTROL_DESCRIPTION});
   } 
   
   private void validateCodes(Code selectedCode)
   {
      if(selectedCode.getCode().equals(newCode))
      {
         setMessage(Diagram_Messages.QUALITY_CONTROL_VALIDATION_CODE, ERROR);
         setValid(false);                  
         return;         
      }
      
      if(StringUtils.isEmpty(selectedCode.getCode()))
      {
         setMessage(Diagram_Messages.QUALITY_CONTROL_VALIDATION_EMPTY, ERROR);
         setValid(false);         
         return;         
      }
      
      if(!ModelUtils.isValidId(selectedCode.getCode()))
      {
         setMessage(Diagram_Messages.QUALITY_CONTROL_VALIDATION_CODE, ERROR);
         setValid(false);
         return;         
      }
      
      for(Code code : qualityControlCode)
      {
         if(!selectedCode.equals(code)
               && selectedCode.getCode().equals(code.getCode()))
         {
            setMessage(Diagram_Messages.QUALITY_CONTROL_VALIDATION_DUPLICATE, ERROR);
            setValid(false);
            return;
         }         
      }

      if(StringUtils.isEmpty(selectedCode.getValue()))
      {
         setMessage(Diagram_Messages.QUALITY_CONTROL_VALIDATION_EMPTY, ERROR);
         setValid(false);         
         return;         
      }      
      
      setMessage(null);
      setValid(true);      
   }   
}