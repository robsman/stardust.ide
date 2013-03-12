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
package org.eclipse.stardust.modeling.core.spi.triggerTypes;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.DefaultOutlineProvider;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.properties.ModelElementsOutlineSynchronizer;
import org.eclipse.stardust.modeling.core.properties.ModelElementsTableContentProvider;
import org.eclipse.stardust.modeling.core.properties.OutlineProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;


public class ParameterMappingTablePage extends AbstractModelElementPropertyPage
    implements IButtonManager
{
   public static final String PARAMETER_MAPPING_TABLE_ID = "_cwm_parameter_mapping_table_"; //$NON-NLS-1$

   private static final String[] labelProperties = {"data", "parameter"}; //$NON-NLS-1$ //$NON-NLS-2$

   private static final int[] elementFeatureIds = {
      CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA,
      CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER,
   };

   private Button[] buttons;
   private Object selection;

   private TableViewer viewer;
   private ModelElementsOutlineSynchronizer outlineSynchronizer;

   private EObjectLabelProvider labelProvider;

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
   }

   public void dispose()
   {
      outlineSynchronizer.dispose();
      super.dispose();
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      viewer.setInput(element);
      outlineSynchronizer.init(element);
      updateButtons(null, buttons);
      expandTree();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      ((GridLayout) composite.getLayout()).marginWidth = 0;

      Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(table);
      table.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateButtons(getSelectedItem(), buttons);
         }
      });
      table.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if (selection instanceof ParameterMappingType)
            {
               selectPageForObject(selection);
            }
         }
      });

      viewer = new TableViewer(table);
      TableUtil.createColumns(table, new String[] {
    		  Diagram_Messages.LB_Data,
            Diagram_Messages.LB_Parameter});
      TableUtil.setInitialColumnSizes(table, new int[] {50, 50});
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public String getText(Object element)
         {
            if (element instanceof ParameterMappingType)
            {
               return getText("data", element); //$NON-NLS-1$
            }
            return super.getText(element);
         }

         public String getText(String name, Object element)
         {
            if (name.equals("data")) //$NON-NLS-1$
            {
               if (element instanceof ParameterMappingType)
               {
                  DataType data = ((ParameterMappingType) element).getData();
                  return data != null ? getText(data) : Diagram_Messages.TXT_undefined;
               }
            }
            if (name.equals("parameter")) //$NON-NLS-1$
            {
               if (element instanceof ParameterMappingType)
               {
                  String param = ((ParameterMappingType) element).getParameter();
                  if (param == null || param.length() == 0)
                  {
                     return Diagram_Messages.TXT_undefined;
                  }
                  TriggerType trigger = (TriggerType)
                     ((ParameterMappingType) element).eContainer();
                  AccessPointType ap = (AccessPointType) ModelUtils.findIdentifiableElement(
                     trigger.getAccessPoint(), param);
                  return ap != null ? getText(ap) : param;
               }
            }
            return super.getText(name, element);
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider, labelProperties);
      viewer.setContentProvider(new ModelElementsTableContentProvider(
            CarnotWorkflowModelPackage.eINSTANCE.getTriggerType_ParameterMapping(),
            elementFeatureIds, labelProperties));
      
      OutlineProvider op = new DefaultOutlineProvider(this,
            CarnotWorkflowModelPackage.eINSTANCE.getTriggerType_ParameterMapping(),
            CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid(),
            null,
            PARAMETER_MAPPING_TABLE_ID,
            getParameterMappingPageClass())
      {
         public String getName(IModelElement element)
         {
            return labelProvider.getText(element);
         }

         public void addNodeTo(String parentNodeId, final CarnotPreferenceNode node)
         {
            ParameterMappingTablePage.this.addNodeTo(parentNodeId, node,
                  new EObjectLabelProvider(getEditor())
            {
               public String getText(String name, Object element)
               {
                  return labelProvider.getText(element);
               }
            });
         }
      };

      outlineSynchronizer = new ModelElementsOutlineSynchronizer(op);
      addModelElementsOutlineSynchronizer(outlineSynchronizer);
      
      return composite;
   }

   protected Class<? extends ParameterMappingPage> getParameterMappingPageClass()
   {
      return ParameterMappingPage.class;
   }

   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(parent);
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
      this.selection = selection;
      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i].isDisposed())
         {
            return;
         }
      }

      buttons[ADD_BUTTON].setEnabled(true);
      buttons[DELETE_BUTTON].setEnabled(selection instanceof ParameterMappingType);
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[DELETE_BUTTON + 1];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performAdd(buttons);
         }
      });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Delete, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performDelete(buttons);
         }
      });

      return buttons;
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
      }
      super.setVisible(visible);
   }

   public Object getSelection()
   {
      return selection == null ? getSelectedItem() : selection;
   }

   protected void performAdd(Button[] buttons)
   {
      ParameterMappingType parameterMappingType = CarnotWorkflowModelFactory.eINSTANCE
            .createParameterMappingType();
      TriggerType trigger = (TriggerType) getModelElement();
      trigger.getParameterMapping().add(parameterMappingType);
      if (preselect)
      {
         selectPageForObject(parameterMappingType);
      }
   }

   protected void performDelete(Button[] buttons)
   {
      ParameterMappingType parameterMappingType =  (ParameterMappingType) getSelection();
      TriggerType trigger = (TriggerType) getModelElement();
      trigger.getParameterMapping().remove(parameterMappingType);
      updateButtons(null, buttons);
      selectPage(PARAMETER_MAPPING_TABLE_ID);
   }
}