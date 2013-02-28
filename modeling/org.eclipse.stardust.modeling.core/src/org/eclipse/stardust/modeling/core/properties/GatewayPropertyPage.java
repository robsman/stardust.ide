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
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

public class GatewayPropertyPage extends AbstractModelElementPropertyPage
{
   protected void performDefaults()
   {
      super.performDefaults();
      upButton.setEnabled(false);
      downButton.setEnabled(false);
   }

   private static final int[] elementFeatureIds = {
      CarnotWorkflowModelPackage.TRANSITION_TYPE__ID,
      CarnotWorkflowModelPackage.TRANSITION_TYPE__CONDITION,
      CarnotWorkflowModelPackage.TRANSITION_TYPE__FORK_ON_TRAVERSAL,
      CarnotWorkflowModelPackage.TRANSITION_TYPE__TO
   };
   
   private static final String[] labelProperties = {"id"};//$NON-NLS-1$;
   private TableViewer viewer;

   private Button upButton;
   private Button downButton;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      GatewaySymbol gateway = getGateway();
      ProcessDefinitionType process = ModelUtils.findContainingProcess(gateway);
      viewer.setInput(process);
      upButton.setEnabled(false);
      downButton.setEnabled(false);
   }

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
   }

   private GatewaySymbol getGateway()
   {
      Object element = getElement();
      if (element instanceof EditPart)
      {
         Object model = ((EditPart) element).getModel();
         if (model instanceof GatewaySymbol)
         {
            return (GatewaySymbol) model;
         }
      }
      return null;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public void contributeVerticalButtons(Composite parent)
   {
      upButton = FormBuilder.createButton(parent, Diagram_Messages.B_MoveUp,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performUp();
               }
            });

      downButton = FormBuilder.createButton(parent, Diagram_Messages.B_MoveDown,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDown();
               }
            });
   }

   private boolean canMoveDown(Object item)
   {
      int selection = viewer.getTable().getSelectionIndex();
      return selection >= 0 && selection < viewer.getTable().getItemCount() - 1;
   }

   private boolean canMoveUp(Object item)
   {
      int selection = viewer.getTable().getSelectionIndex();
      return selection > 0;
   }

   private void performDown()
   {
      int selection = viewer.getTable().getSelectionIndex();
      int otherSelection = selection + 1;
      
      moveItem(selection, otherSelection);
   }

   private void performUp()
   {
      int selection = viewer.getTable().getSelectionIndex();
      int otherSelection = selection - 1;
      
      moveItem(selection, otherSelection);
   }

   private void moveItem(int selection, int otherSelection)
   {
      GatewaySymbol gateway = getGateway();
      ProcessDefinitionType process = ModelUtils.findContainingProcess(gateway);
      EList allTransitions = process.getTransition();
      
      TransitionType transition = (TransitionType) viewer.getElementAt(selection);
      TransitionType other = (TransitionType) viewer.getElementAt(otherSelection);
      
      int oldPos = allTransitions.indexOf(transition);
      int newPos = allTransitions.indexOf(other);

      allTransitions.move(newPos, oldPos);
      updateButtons(transition);
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(table);
      table.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateButtons(getSelectedItem());
         }
      });
      table.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if (selection instanceof TransitionType)
            {
               selectPageForObject(selection);
            }
         }
      });

      viewer = new TableViewer(table);
      TableUtil.createColumns(table, new String[] {
            Diagram_Messages.COL_NAME_Id,
            Diagram_Messages.COL_NAME_Condition,
            Diagram_Messages.COL_NAME_Fork,
            Diagram_Messages.COL_NAME_Activity});
      TableUtil.setInitialColumnSizes(table, new int[] {30, 30, 15, 25});
      
      TableLabelProvider labelProvider = new EObjectLabelProvider(getEditor())
      {
         public String getText(String name, Object element)
         {
            TransitionType transition = (TransitionType) element;
            if (name.equals("condition")) //$NON-NLS-1$
            {
               XmlTextNode type = transition.getExpression();
               String expression = type == null ? null : ModelUtils
                     .getCDataString(transition.getExpression().getMixed());
               if (expression == null || expression.trim().length() == 0)
               {
                  if(transition.getCondition() != null)
                  {
                     expression = transition.getCondition().trim();
                  }
               }
               return expression;
            }
            if (name.equals("fork")) //$NON-NLS-1$
            {
               if (transition.isForkOnTraversal())
               {
                  return "true"; //$NON-NLS-1$
               }
            }
            if (name.equals("activity")) //$NON-NLS-1$
            {
               return transition.getTo().getName();
            }
            return super.getText(name, element);
         }
      };

      TableUtil.setLabelProvider(viewer, labelProvider,
            new String[] {"id", "condition", "fork", "activity"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

      GatewaySymbol gateway = getGateway();
      FlowControlType kind = gateway.getFlowKind();
      final boolean isIn = kind != null && kind.getValue() == FlowControlType.JOIN;
      setTitle(isIn ? Diagram_Messages.LBL_Incoming_Transitions : Diagram_Messages.LBL_Outgoing_Transitions); 

      ActivitySymbolType activitySymbol = gateway.getActivitySymbol();
      final ActivityType activity = activitySymbol == null ? null : activitySymbol.getActivity();

      viewer.setContentProvider(new ModelElementsTableContentProvider(
            CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_Transition(),
            elementFeatureIds, labelProperties, new IFilter()
            {
               public boolean select(Object toTest)
               {
                  TransitionType trans = (TransitionType) toTest;
                  return (null != activity) &&
                     ((isIn && (trans.getTo() == activity))
                           || (!isIn && (trans.getFrom() == activity)));
               }
            }));

      return composite;
   }

   private void updateButtons(Object item)
   {
      GatewaySymbol gateway = getGateway();
      FlowControlType kind = gateway.getFlowKind();
      if (kind != null && kind.getValue() == FlowControlType.SPLIT)
      {
         upButton.setEnabled(item != null && canMoveUp(item));
         downButton.setEnabled(item != null && canMoveDown(item));
      }
   }
}