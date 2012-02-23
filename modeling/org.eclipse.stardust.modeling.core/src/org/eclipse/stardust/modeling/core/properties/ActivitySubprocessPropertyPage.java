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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.IdentifiableLabelProvider;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
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
   
   private ReferencedModelSorter procSorter = new ReferencedModelSorter();
   private IdentifiableLabelProvider labelProvider;

   // used to signal the process table listener that it should not change the name
   // during loading
   private boolean loading;

   private Button groupingCheckbox;

   private List<ProcessDefinitionType> processes;
   
   private ComboViewer comboViewer;

   private ModelType model;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
	  loading = true;
      ActivityType activity = (ActivityType) element;
      model = (ModelType) activity.eContainer().eContainer();
      labelProvider.setModel(model);
      TableViewer viewer = (TableViewer) labeledViewer.getViewer();
      viewer.getTable().removeAll();

      processes = this.collectProcessDefinitions(model);
      viewer.add(processes.toArray());
      comboViewer.getCombo().removeAll();
      
      comboViewer.add(SubProcessModeType.VALUES.toArray());
      comboViewer.setSelection(new StructuredSelection(activity.getSubProcessMode()));
      
      if (activity.getImplementationProcess() != null)
      {
          viewer.setSelection(new StructuredSelection(activity.getImplementationProcess()));    	  
      }
 
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
      Table table = new Table(composite, SWT.BORDER);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      final TableViewer tableViewer = new TableViewer(table);
      labelProvider = new  IdentifiableLabelProvider(getEditor());
      tableViewer.setLabelProvider(labelProvider);
      tableViewer.setSorter(procSorter);   
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
                  ((ActivityType) getModelElement()).setName(defaultName);
                  ModelType externalModel = (ModelType)pdt.eContainer();
                  comboViewer.refresh();
                  comboViewer.add(SubProcessModeType.VALUES.toArray());  
                  if (((ActivityType) getModelElement()).getSubProcessMode().equals(SubProcessModeType.SYNC_SHARED_LITERAL))
                  {
                     if (externalModel != model)
                     {
                        comboViewer.setSelection(new StructuredSelection(SubProcessModeType.SYNC_SEPARATE_LITERAL));                        
                     }
                     else
                     {
                        comboViewer.setSelection(new StructuredSelection(((ActivityType) getModelElement()).getSubProcessMode()));
                     }
                  }
                  else
                  {
                     comboViewer.setSelection(new StructuredSelection(((ActivityType) getModelElement()).getSubProcessMode()));
                  }
                  
                  ActivitySubprocessPropertyPage.this.removePreferenceNodes(PAGE_ID, false);
                  ActivitySubprocessPropertyPage.this.addNodesTo(PAGE_ID);
               }
            }
         }
      });
      labeledViewer = new LabeledViewer(tableViewer, label);
      
      groupingCheckbox = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_GroupModelElements);
      groupingCheckbox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            procSorter.setGrouped(groupingCheckbox.getSelection());
            labelProvider.setShowGroupInfo(groupingCheckbox.getSelection());        
            TableViewer viewer = (TableViewer) labeledViewer.getViewer();
            ISelection selection = viewer.getSelection();
            viewer.getTable().removeAll();
            viewer.add(processes.toArray());
            viewer.setSelection(selection);
         }
      });
      
      LabelWithStatus comboLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, Diagram_Messages.FORMBUILDER_LB_ExecutionType);
      comboViewer = new ComboViewer(FormBuilder.createCombo(composite));
      comboViewer.add(SubProcessModeType.VALUES.toArray()); 
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
   
   private List<ProcessDefinitionType> collectProcessDefinitions(ModelType model)
   {
      List<ProcessDefinitionType> processesList = CollectionUtils.newList();
      List<ProcessDefinitionType> processTypes = model.getProcessDefinition();
      if (processTypes != null)
      {
         processesList.addAll(processTypes);
      }
      ExternalPackages packages = model.getExternalPackages();
      if (packages != null)
      {
         for (ExternalPackage pkg : packages.getExternalPackage())
         {
            String uri = ExtendedAttributeUtil.getAttributeValue(pkg, IConnectionManager.URI_ATTRIBUTE_NAME);
            if (!StringUtils.isEmpty(uri))
            {
               IConnectionManager manager = model.getConnectionManager();
               if (manager != null)
               {
                  EObject externalModel = manager.find(uri);
                  if (externalModel instanceof IObjectReference)
                  {
                     externalModel = ((IObjectReference) externalModel).getEObject();
                  }
                  if (externalModel instanceof ModelType)
                  {
               	     List<ProcessDefinitionType> externalProcesses = ((ModelType) externalModel).getProcessDefinition();
                     if (externalProcesses != null)                    	 
                     {
                     	for (ProcessDefinitionType extProcess : externalProcesses)
                        {
                           if (extProcess.getFormalParameters() != null)
                           {
                              processesList.add(extProcess);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return processesList;
   }
   
   public ProcessDefinitionType getSelectedSubprocess()
   {
	   TableViewer viewer = (TableViewer) labeledViewer.getViewer();
	   IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
	   return (ProcessDefinitionType) selection.getFirstElement();
   }
}