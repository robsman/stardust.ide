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

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.ui.ProcessDefinitionSelectionViewer;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ActivitySubprocessPropertyPage extends AbstractModelElementPropertyPage
{
   private static final String PAGE_ID = "_cwm_subprocess_"; //$NON-NLS-1$

   public static final String[] LABELS = {
         Diagram_Messages.LABEL_SyncShared, "Sync Separate", //$NON-NLS-1$
         Diagram_Messages.LABEL_AsyncSeparate};

   private LabeledViewer labeledComboViewer;

   private LabeledViewer labeledViewer;

   private Button checkCopyData;

   // used to signal the process table listener that it should not change the name
   // during loading
   private boolean loading;

   private ComboViewer comboViewer;

   private ModelType model;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      loading = true;
      ActivityType activity = (ActivityType) element;
      model = (ModelType) activity.eContainer().eContainer();

      ProcessDefinitionSelectionViewer viewer = (ProcessDefinitionSelectionViewer) labeledViewer.getViewer();
      viewer.reset(model);

      updateSubprocessMode();

      /*if (activity.getImplementationProcess() != null)
      {
          viewer.setSelection(new StructuredSelection(activity.getImplementationProcess()));
      }*/

      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      /*wBndMgr.bind(labeledComboViewer, element, PKG_CWM.getActivityType_SubProcessMode());
      wBndMgr.getModelBindingManager().updateWidgets(element);*/

      wBndMgr.bind(labeledViewer, element, PKG_CWM.getActivityType_ImplementationProcess());
      wBndMgr.getModelBindingManager().updateWidgets(element);

      checkCopyData.setSelection(Boolean.valueOf(
            AttributeUtil.getAttributeValue(activity,
                  CarnotConstants.ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT)).booleanValue());
      loading = false;
   }

   private ActivityType getActivity()
   {
      return (ActivityType) getModelElement();
   }

   @Override
   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateSubprocessMode();
      }
      super.setVisible(visible);
   }

   private void updateSubprocessMode()
   {
      ActivityType activity = getActivity();
      SubProcessModeType mode = activity.getSubProcessMode();
      List<SubProcessModeType> input = SubProcessModeType.VALUES;
      if (isParallelMultiInstance(activity) && mode != SubProcessModeType.SYNC_SHARED_LITERAL)
      {
         input = CollectionUtils.newListFromElements(input);
         input.remove(SubProcessModeType.SYNC_SHARED);
      }
      comboViewer.setInput(input);
      comboViewer.setSelection(new StructuredSelection(mode));
   }

   private boolean isParallelMultiInstance(ActivityType activity)
   {
      LoopType loop = activity.getLoop();
      if (loop != null && loop.getLoopType() == LoopTypeType.MULTI_INSTANCE)
      {
         LoopMultiInstanceType mi = loop.getLoopMultiInstance();
         if (mi != null && mi.getMIOrdering() == MIOrderingType.PARALLEL)
         {
            return true;
         }
      }
      return false;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AttributeUtil.setBooleanAttribute((IExtensibleElement) element,
            CarnotConstants.ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT, checkCopyData
                  .getSelection());
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.FORMBUILDER_LB_Subprocess);

      final ProcessDefinitionSelectionViewer tableViewer = new ProcessDefinitionSelectionViewer(composite, getEditor());
      tableViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            if (!loading && PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_AUTO_SUBPROCESS_NAME_GENERATION))
            {
               IStructuredSelection selection = ((IStructuredSelection) tableViewer .getSelection());
               if (selection != null
                     && selection.getFirstElement() instanceof ProcessDefinitionType)
               {
                  ProcessDefinitionType pdt = (ProcessDefinitionType) selection.getFirstElement();
                  String defaultName = ((ProcessDefinitionType) selection.getFirstElement()).getName();
                  ActivityType activity = getActivity();
                  activity.setName(defaultName);
                  ModelType externalModel = (ModelType) pdt.eContainer();
                  if (((ActivityType) getModelElement()).getSubProcessMode().equals(SubProcessModeType.SYNC_SHARED_LITERAL))
                  {
                     if (externalModel != model)
                     {
                        activity.setSubProcessMode(SubProcessModeType.SYNC_SEPARATE_LITERAL);
                     }
                  }
                  updateSubprocessMode();

                  removePreferenceNodes(PAGE_ID, false);
                  addNodesTo(PAGE_ID);
               }
            }
         }
      });
      labeledViewer = new LabeledViewer(tableViewer, label);

      final Button groupingCheckbox = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_GroupModelElements);
      groupingCheckbox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            ((ProcessDefinitionSelectionViewer) labeledViewer.getViewer()).setGrouped(groupingCheckbox.getSelection());
         }
      });

      LabelWithStatus comboLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, Diagram_Messages.FORMBUILDER_LB_ExecutionType);
      comboViewer = new ComboViewer(FormBuilder.createCombo(composite));
      comboViewer.setContentProvider(new ArrayContentProvider());
      comboViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            return ModelUtils.getSubprocessModeTypeText((SubProcessModeType) element);
         }
      });


      labeledComboViewer = new LabeledViewer(comboViewer, comboLabel);

      checkCopyData = FormBuilder.createCheckBox(composite, Diagram_Messages.BOX_COPY_ALL_DATA, 2);

      comboViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            Object obj = ((EditPart) getElement()).getModel();
            ActivityType activity = (obj instanceof ActivitySymbolType
                  ? ((ActivitySymbolType) obj).getActivity()
                  : (ActivityType) obj);

            Object selection = ((IStructuredSelection) comboViewer.getSelection())
                  .getFirstElement();
            if (selection != null)
            {
                boolean isCopyAllData = selection.equals(SubProcessModeType.SYNC_SHARED_LITERAL) ? false
                      : AttributeUtil.getBooleanValue(activity,CarnotConstants.ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT);

                checkCopyData.setSelection(isCopyAllData);
                checkCopyData.setEnabled(!selection.equals(SubProcessModeType.SYNC_SHARED_LITERAL));
                updateModelData();
            }
         }
      });

      checkCopyData.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateModelData();
         }
      });

      return composite;
   }

   private void updateModelData()
   {
      Object obj = ((EditPart) getElement()).getModel();
      ActivityType activity = (obj instanceof ActivitySymbolType
            ? ((ActivitySymbolType) obj).getActivity()
            : (ActivityType) obj);

      IStructuredSelection selection = (IStructuredSelection) labeledComboViewer.getViewer().getSelection();
      SubProcessModeType subProcMode = (SubProcessModeType) selection.getFirstElement();
      if (!CompareHelper.areEqual(subProcMode, activity.getSubProcessMode()))
      {
         activity.setSubProcessMode(subProcMode);
      }

      AttributeUtil.setBooleanAttribute(activity,
            CarnotConstants.ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT, checkCopyData.getSelection());
   }
}