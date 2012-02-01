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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelVariable;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;



public class VariablesConfigurationPage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   static final int ADD_BUTTON = 0;

   static final int DELETE_BUTTON = ADD_BUTTON + 1;

   static final int REFRESH_BUTTON = DELETE_BUTTON + 1;

   static final int BUTTON_COUNT = REFRESH_BUTTON + 1;

   private Button[] buttons;

   private TableViewer viewer;

   List<ModelVariable> variablesOrg = new ArrayList<ModelVariable>();

   private TableViewer viewer2;

   private ModelType model;

   protected ModelVariable selectedVariable;

   private VariableContext variablesContext;

   protected String getOidLabel()
   {
      return Diagram_Messages.LB_OID;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      variablesContext.saveVariables();
      handleModifications();      
   }
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (element instanceof ModelType)
      {
         model = (ModelType) element;
      }      
      variablesContext = VariableContextHelper.getInstance().getContext(model);
      variablesContext.cleanupReferences();
      viewer.setInput(variablesContext.getVariables());
      variablesOrg = cloneVariables();
      validateVariables();
   }

   private void handleModifications()
   {
      for (Iterator<ModelVariable> i = variablesOrg.iterator(); i.hasNext();)
      {
         ModelVariable modelVariable = i.next();
         String newName = getChangedName(modelVariable);
         if (newName != null)
         {
            variablesContext.replaceVariable(modelVariable, newName);
         }
      }
   }

   private List<ModelVariable> cloneVariables()
   {
      List<ModelVariable> result = new ArrayList<ModelVariable>();
      for (Iterator<ModelVariable> i = variablesContext.getVariables().iterator(); i
            .hasNext();)
      {
         ModelVariable m = i.next();
         result.add(m.clone());
      }
      return result;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      FormBuilder.createLabel(composite, Diagram_Messages.LBL_VARIABLES);

      Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(table);

      viewer = new TableViewer(table);
      viewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            Object o = selection.getFirstElement();
            if (o instanceof ModelVariable)
            {
               ModelVariable modelVariable = (ModelVariable) o;
               List<EObject> refList = variablesContext.getVariableReferences().get(
                     modelVariable.getName());
               if (refList != null)
               {
                  viewer2.setInput(refList.toArray());
               }
               else
               {
                  viewer2.setInput(new ArrayList<EObject>());
               }
            }
         }
      });
      TableUtil.createColumns(table, new String[] {
            Diagram_Messages.COL_NAME_Name, Diagram_Messages.COL_NAME_DefaultValue,
            Diagram_Messages.COL_NAME_Description});
      TableUtil.setInitialColumnSizes(table, new int[] {35, 35, 30});

      viewer.setLabelProvider(new ModelVariableLabelProvider());
      viewer.setContentProvider(new ModelVariableContentProvider());
      viewer.setFilters(new ViewerFilter[] {new VariableFilter()});

      attachCellEditors(viewer, parent);

      FormBuilder.createLabel(composite, Diagram_Messages.LBL_REFERENCES);

      Table table2 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table2.setHeaderVisible(true);
      table2.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(table2);

      TableUtil.createColumns(table2, new String[] {Diagram_Messages.COL_MD_ELEMENT, Diagram_Messages.COL_ATTRIBUTE, Diagram_Messages.COL_VALUE});
      TableUtil.setInitialColumnSizes(table2, new int[] {35, 35, 35});

      viewer2 = new TableViewer(table2);
      viewer2.setLabelProvider(new VariableReferencesLabelProvider(this.getEditor()));
      viewer2.setContentProvider(new ArrayContentProvider());

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
                  variablesContext.getVariables().add(new ModelVariable(Diagram_Messages.CONFIGURATION_VARIABLE_NEW, "", ""));       //$NON-NLS-1$ //$NON-NLS-2$
                  viewer.refresh();
                  validateVariables();
               }
            });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_Delete, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  if (selectedVariable != null)
                  {
                     ModelVariable modelVariable = selectedVariable;
                     List<EObject> refList = variablesContext.getVariableReferences()
                           .get(modelVariable.getName());
                     if (refList != null && !refList.isEmpty())
                     {
                        LiteralSelectionDialog dialog = new LiteralSelectionDialog(
                              Display.getCurrent().getActiveShell(), modelVariable);
                        dialog.open();
                        if (!dialog.isCanceled())
                        {
                           modelVariable.setRemoved(true);
                           if (dialog.isDefaultValueSelected())
                           {
                              modelVariable.setName(modelVariable.getDefaultValue());
                           }
                           if (dialog.isEmptyLiteralSelected())
                           {
                              modelVariable.setName(""); //$NON-NLS-1$
                           }
                           if (dialog.isLiteralSelected())
                           {
                              modelVariable.setName(dialog.getLiteral());
                           }
                        }
                        validateVariables();
                     }
                     else
                     {
                        modelVariable.setRemoved(true);
                        modelVariable.setName(""); //$NON-NLS-1$
                        validateVariables();
                     }
                     viewer.refresh();                     
                  }
               }
            });
      return buttons;
   }

   public Object getSelection()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
   // TODO Auto-generated method stub

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
            if (element instanceof ModelVariable)
            {
               ModelVariable modelVariable = (ModelVariable) element;
               if (Diagram_Messages.COL_NAME_Name.equals(property))
               {
                  String name = modelVariable.getName();
                  name = name.replace("${", ""); //$NON-NLS-1$ //$NON-NLS-2$
                  name = name.replace("}", ""); //$NON-NLS-1$ //$NON-NLS-2$
                  return name;
               }
               if (Diagram_Messages.COL_NAME_DefaultValue.equals(property))
               {
                  return modelVariable.getDefaultValue();
               }
               if (Diagram_Messages.COL_NAME_Description.equals(property))
               {
                  return modelVariable.getDescription();
               }
            }
            return ""; //$NON-NLS-1$
         }

         public void modify(Object element, String property, Object value)
         {
            if (element instanceof TableItem)
            {
               TableItem tableItem = (TableItem) element;
               ModelVariable modelVariable = (ModelVariable) tableItem.getData();
               selectedVariable = modelVariable;
               if (Diagram_Messages.COL_NAME_Name.equals(property))
               {                  
                  if (variablesContext.isValidName(value.toString()))
                  {
                     modelVariable.setName("${" + value.toString() + "}"); //$NON-NLS-1$ //$NON-NLS-2$
                  }
               }
               if (Diagram_Messages.COL_NAME_DefaultValue.equals(property))
               {
                  modelVariable.setDefaultValue(value.toString());
               }
               if (Diagram_Messages.COL_NAME_Description.equals(property))
               {
                  modelVariable.setDescription(value.toString());
               }
            }
            validateVariables();
            viewer.refresh();
         }
      });

      viewer.setCellEditors(new CellEditor[] {
            new TextCellEditor(viewer.getTable()), new TextCellEditor(viewer.getTable()),
            new TextCellEditor(viewer.getTable())});

      viewer.setColumnProperties(new String[] {
            Diagram_Messages.COL_NAME_Name, Diagram_Messages.COL_NAME_DefaultValue,
            Diagram_Messages.COL_NAME_Description});
   }
   
   private void validateVariables()
   {
      provideError(null, true);
      for (Iterator<ModelVariable> i = variablesContext.getVariables().iterator(); i
            .hasNext();)
      {
         ModelVariable modelVariable = i.next();
         if (!modelVariable.isRemoved())
         {
            if (modelVariable.getName().equalsIgnoreCase(Diagram_Messages.CONFIGURATION_VARIABLE_NEW))
            {
               provideError(Diagram_Messages.PROVIDE_ERROR_PROVIDE_A_VALID_NAME_FOR_NEW_VARIABLE, false);
            }
            if (nameExists(modelVariable, modelVariable.getName()))
            {
               provideError(MessageFormat.format(
                     Diagram_Messages.PROVIDE_ERROR_DUPLICATE_VARIABLE,
                     modelVariable.getName()), false);
            }
            if (!variablesContext.isValidName(modelVariable.getName()))
            {
               provideError(MessageFormat.format(
                     Diagram_Messages.PROVIDE_ERROR_IS_NOT_A_VALID_VARIABLE_NAME,
                     new Object[] {modelVariable.getName()}), false);
            }
         }
      }
   }

   private void provideError(String errorMessage, boolean valid)
   {
      setErrorMessage(errorMessage);
      setValid(valid);
      getApplyButton().setEnabled(valid);
      buttons[ADD_BUTTON].setEnabled(valid);      
   }

   protected boolean nameExists(ModelVariable editedVariable, String string)
   {
      for (Iterator<ModelVariable> i = this.variablesContext.getVariables().iterator(); i
            .hasNext();)
      {
         ModelVariable modelVariable = i.next();
         if (!modelVariable.isRemoved()
               && modelVariable.getName().equals(string))
         {
            if (!editedVariable.equals(modelVariable))
            {
               return true;
            }
         }
      }
      return false;
   }

   private String getChangedName(ModelVariable orgModelVariable)
   {
      ModelVariable modelVariable = variablesContext.getVariables().get(
            variablesOrg.indexOf(orgModelVariable));
      if (!modelVariable.getName().equals(orgModelVariable.getName()))
      {
         return modelVariable.getName();
      }
      return null;
   }

   protected void performDefaults()
   {
      if (variablesContext != null && variablesContext.getVariables() != null)
      {
         variablesContext.getVariables().clear();
         variablesContext.getVariables().addAll(variablesOrg);
      }
      viewer.refresh(true);
      super.performDefaults();
      validateVariables();
   }

   public boolean performCancel()
   {
      if (variablesContext != null && variablesContext.getVariables() != null)
      {
         variablesContext.getVariables().clear();
         variablesContext.getVariables().addAll(variablesOrg);
      }
      return super.performCancel();
   }

   private class VariableFilter extends ViewerFilter
   {
      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         return (!((ModelVariable) element).isRemoved());
      }
   }

}
