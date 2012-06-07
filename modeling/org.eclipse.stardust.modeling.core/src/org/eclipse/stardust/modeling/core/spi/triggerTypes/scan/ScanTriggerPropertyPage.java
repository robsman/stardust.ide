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
package org.eclipse.stardust.modeling.core.spi.triggerTypes.scan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.IValidationStatus;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.validation.PageValidationManager;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ScanTriggerPropertyPage extends AbstractModelElementPropertyPage
{
   private IModelParticipant originalPerformer = null;

   private TriggerType trigger = null;

   private LabeledViewer dataCombo;

   private List<DataType> datas = new ArrayList<DataType>();

   private ModelType model;

   private LabeledText metaTypeText;

   private CarnotPreferenceNode participantNode;
   
   private CarnotPreferenceNode triggerNode;
   
   private boolean pageValidationManagerSet = false;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      trigger = (TriggerType) element;
      model = ModelUtils.findContainingModel(element);
      datas.clear();
      for (Iterator<DataType> i = model.getData().iterator(); i.hasNext();)
      {
         DataType dataType = i.next();
         if (dataType.getType().getId().equals("dmsDocument") //$NON-NLS-1$
               || dataType.getType().getId().equals("dmsDocumentList")) //$NON-NLS-1$
         {
            datas.add(dataType);
         }
      }
      dataCombo.getViewer().setInput(datas);
      if (!trigger.getAccessPoint().isEmpty())
      {
         AccessPointType apt = trigger.getAccessPoint().get(0);
         DataType dataType = getSelectedDataType(apt.getId());
         if (dataType != null)
         {
            dataCombo.getViewer().setSelection(new StructuredSelection(dataType));
         }
      }
      
      TriggerType trigger = (TriggerType) getModelElement();
      AttributeType attribute = AttributeUtil.getAttribute(trigger,
            PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT);
      if (attribute != null)
      {
         originalPerformer = (IModelParticipant) AttributeUtil
               .getReferenceElement(attribute);
      }
      validateParticipant(originalPerformer);         
      validateTrigger();
   }

   public void loadElementFromFields(final IModelElementNodeSymbol symbol,
         IModelElement element)
   {

   }

   private DataType getSelectedDataType(String id)
   {
      for (Iterator<DataType> i = model.getData().iterator(); i.hasNext();)
      {
         DataType dt = i.next();
         if (dt.getId().equalsIgnoreCase(id))
         {
            return dt;
         }
      }
      return null;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      dataCombo = FormBuilder.createComboViewer(composite, Diagram_Messages.LB_Data,
            new ArrayList<Object>());
      dataCombo.getViewer().getControl().setEnabled(enablePage);
      dataCombo.getViewer().setContentProvider(new ArrayContentProvider());
      dataCombo.getViewer().setLabelProvider(new EObjectLabelProvider(getEditor()));
      dataCombo.getViewer().addSelectionChangedListener(new ISelectionChangedListener()
      {

         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            DataType dataType = (DataType) selection.getFirstElement();
            AccessPointType apt = AccessPointUtil.createAccessPoint(dataType.getId(),
                  dataType.getName(), DirectionType.OUT_LITERAL, dataType.getType());
            trigger.getAccessPoint().clear();
            trigger.getAccessPoint().add(apt);

            ParameterMappingType parameterMappingType = CarnotWorkflowModelFactory.eINSTANCE
                  .createParameterMappingType();
            parameterMappingType.setElementOid(ModelUtils.getElementOid(
                  parameterMappingType, ModelUtils.findContainingModel(getModelElement())));
            TriggerType trigger = (TriggerType) getModelElement();
            trigger.getParameterMapping().clear();
            trigger.getParameterMapping().add(parameterMappingType);
            parameterMappingType.setData(dataType);
            parameterMappingType.setParameter(dataType.getId());

            String typeName = AttributeUtil.getAttributeValue(dataType.getAttribute(),
                  "carnot:engine:dms:resourceMetadataSchema"); //$NON-NLS-1$
            if (typeName == null)
            {
               typeName = "Default"; //$NON-NLS-1$
            }

            metaTypeText.getText().setText(typeName);
            validateTrigger();
         }

      });

      metaTypeText = FormBuilder.createLabeledText(composite,
            Diagram_Messages.LB_MetaType);
      metaTypeText.getText().setEnabled(false);

      String iconName = "{org.eclipse.stardust.modeling.core}icons/full/obj16/participants.gif"; //$NON-NLS-1$
      ConfigurationElement element = ConfigurationElement
            .createPageConfiguration(
                  "org.eclipse.stardust.modeling.core.spi.triggerTypes.scan.ParticipantPropertyPage", //$NON-NLS-1$
                  Diagram_Messages.LB_Participants,                  
                  iconName,
                  org.eclipse.stardust.modeling.core.spi.triggerTypes.scan.ParticipantPropertyPage.class);
      participantNode = new CarnotPreferenceNode(element, getElement(), 0);
      getPreferenceManager().addToRoot(participantNode);
      triggerNode = (CarnotPreferenceNode) this
            .getPreferenceManager().find("scan");
      return composite;
   }
   
   private void validateParticipant(IModelParticipant performer)
   {
      if (performer != null)
      {
         setParticipantValidationState(IQuickValidationStatus.OK);
      }
      else
      {
         setParticipantValidationState(IQuickValidationStatus.ERRORS);
      }
   }
   
   private void validateTrigger()
   {
      if (!trigger.getAccessPoint().isEmpty())
      {
         setTriggerValidationState(IQuickValidationStatus.OK);
         dataCombo.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      else
      {
         setTriggerValidationState(IQuickValidationStatus.ERRORS);
         dataCombo.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
      }      
   }
   
   private void setParticipantValidationState(IQuickValidationStatus state)
   {
      this.participantNode.updatePageStatus(state);
      TreeViewer parentTreeViewer = (TreeViewer) Reflect.getFieldValue(
            this.getContainer(), "treeViewer");
      parentTreeViewer.refresh(true);
   }
   
   private void setTriggerValidationState(IQuickValidationStatus state)
   {
      this.triggerNode.updatePageStatus(state);
      TreeViewer parentTreeViewer = (TreeViewer) Reflect.getFieldValue(
            this.getContainer(), "treeViewer");
      parentTreeViewer.refresh(true);
   }

   public boolean performCancel()
   {
      AttributeUtil.setReference(trigger,
            PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT, originalPerformer);
      return super.performCancel();
   }
   
   public WidgetBindingManager getWidgetBindingManager()
   {
      if (pageValidationManagerSet == false)
      {
         pageValidationManagerSet = true;
         WidgetBindingManager wBndMgr = super.getWidgetBindingManager();
         PageValidationManager pm = new PageValidationManager(this)
         {

            @Override
            public void onIssuesUpdated(EObject element,
                  IValidationStatus validationStatus)
            {

            }

         };
         Reflect.setFieldValue(wBndMgr, "vBndMgr", pm);
         return wBndMgr;
      }
      return super.getWidgetBindingManager();
   }

}