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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

public class ActivityGeneralPropertyPage extends IdentifiablePropertyPage
{
   private ConfigurationElement performer;
   private ConfigurationElement qualityControl;
   private ConfigurationElement qualityControlCodes;

   private Button abortCheck;
   private Button hibernateCheck;
   private Button relocateSourceCheck;
   private Button relocateTargetCheck;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      ActivityType activity = (ActivityType) element;
      if (ActivityUtil.isInteractive(activity))
      {
         setButtonState(abortCheck, true, activity.isAllowsAbortByPerformer());
         setButtonState(relocateSourceCheck, true,
               AttributeUtil.getBooleanValue(activity, PredefinedConstants.ACTIVITY_IS_RELOCATE_SOURCE_ATT));
      }
      else
      {
         setButtonState(abortCheck, false, false);
         setButtonState(relocateSourceCheck, false, false);
      }
      setButtonState(relocateTargetCheck, true,
            AttributeUtil.getBooleanValue(activity, PredefinedConstants.ACTIVITY_IS_RELOCATE_TARGET_ATT));
      hibernateCheck.setSelection(activity.isHibernateOnCreation());

      if (ActivityUtil.isInteractive(activity)
            || DiagramPlugin.isBusinessView((WorkflowModelEditor) PlatformUI
                  .getWorkbench().getActiveWorkbenchWindow().getActivePage()
                  .getActiveEditor()))
      {
         if (performer == null)
         {
            performer = ConfigurationElement.createPageConfiguration(
                  ActivityParticipantPropertyPage.PARTICIPANT_ID,
                  ActivityParticipantPropertyPage.PARTICIPANT_LABEL,
                  "icons/full/obj16/role.gif", ActivityParticipantPropertyPage.class //$NON-NLS-1$
                        .getName(), "core"); //$NON-NLS-1$
            addNodeTo(null, new CarnotPreferenceNode(performer, getElement(), 2), null);
            refreshTree();
         }
      }
      
      
      if (ActivityUtil.isInteractive(activity))
      {
         if (qualityControl == null)
         {
            qualityControl = ConfigurationElement.createPageConfiguration(
                  ActivityQualityControlPropertyPage.QUALITY_CONTROL_ID,
                  ActivityQualityControlPropertyPage.QUALITY_CONTROL_LABEL,
                  "icons/full/obj16/activity.gif", ActivityQualityControlPropertyPage.class //$NON-NLS-1$
                        .getName(), "core"); //$NON-NLS-1$
            addNodeTo(null, new CarnotPreferenceNode(qualityControl, getElement(), 2), null);
            refreshTree();
         }
         if (qualityControlCodes == null)
         {
            qualityControlCodes = ConfigurationElement.createPageConfiguration(
                  ActivityQualityControlCodesPropertyPage.QUALITY_CONTROL_CODES_ID,
                  ActivityQualityControlCodesPropertyPage.QUALITY_CONTROL_CODES_LABEL,
                  "icons/full/obj16/activity.gif", ActivityQualityControlCodesPropertyPage.class //$NON-NLS-1$
                        .getName(), "core"); //$NON-NLS-1$
            addNodeTo(null, new CarnotPreferenceNode(qualityControlCodes, getElement(), 2), null);
            refreshTree();
         }         
      }      
   }

   private void setButtonState(Button button, boolean enabled, boolean selected)
   {
      button.setEnabled(enabled);
      button.setSelection(selected);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
      ActivityType activity = (ActivityType) element;
      if (activity != null)
      {
         if (ActivityUtil.isInteractive(activity))
         {
            activity.setAllowsAbortByPerformer(abortCheck.getSelection());
            AttributeUtil.setBooleanAttribute(activity,
                  PredefinedConstants.ACTIVITY_IS_RELOCATE_SOURCE_ATT,
                  relocateSourceCheck.getSelection());
         }
         else
         {
            activity.unsetAllowsAbortByPerformer();
            AttributeUtil.setBooleanAttribute(activity,
                  PredefinedConstants.ACTIVITY_IS_RELOCATE_SOURCE_ATT, null);
         }
         AttributeUtil.setBooleanAttribute(activity,
               PredefinedConstants.ACTIVITY_IS_RELOCATE_TARGET_ATT,
               relocateTargetCheck.getSelection() ? Boolean.TRUE : null);
         activity.setHibernateOnCreation(hibernateCheck.getSelection());
         
         updateRelocateTransition((ProcessDefinitionType) activity.eContainer());
      }
   }

   private void updateRelocateTransition(ProcessDefinitionType process)
   {
      boolean supportsRelocation = false;
      for (ActivityType activity : process.getActivity())
      {
         if (AttributeUtil.getBooleanValue(activity, PredefinedConstants.ACTIVITY_IS_RELOCATE_SOURCE_ATT))
         {
            supportsRelocation = true;
            break;
         }
      }
      TransitionType relocationTransition = ModelUtils.findElementById(process.getTransition(),
            PredefinedConstants.RELOCATION_TRANSITION_ID);
      if (supportsRelocation)
      {
         if (relocationTransition == null)
         {
            relocationTransition = CarnotWorkflowModelFactory.eINSTANCE.createTransitionType();
            process.getTransition().add(relocationTransition);
         }
         relocationTransition.setId(PredefinedConstants.RELOCATION_TRANSITION_ID);
         relocationTransition.setName("Relocation Transition");
         relocationTransition.setCondition("TRUE");
         relocationTransition.setFrom(null);
         relocationTransition.setTo(null);
         relocationTransition.setForkOnTraversal(false);
      }
      else
      {
         if (relocationTransition != null)
         {
            process.getTransition().remove(relocationTransition);
         }
      }
   }

   public void contributeExtraControls(Composite composite)
   {
      Composite panel = FormBuilder.createComposite(composite, 2, 2);
      GridLayout grid = (GridLayout) panel.getLayout();
      grid.makeColumnsEqualWidth = true;
      grid.marginWidth = 0;
      grid.marginHeight = 0;
      abortCheck = FormBuilder.createCheckBox(panel, Diagram_Messages.CHECKBOX_AllowsAbortByParticipant,
            new GridData(SWT.LEAD, SWT.CENTER, true, false));
      relocateSourceCheck = FormBuilder.createCheckBox(panel, "Supports relocation",
            new GridData(SWT.LEAD, SWT.CENTER, true, false));
      hibernateCheck = FormBuilder.createCheckBox(panel, Diagram_Messages.CHECKBOX_HibernateInitially,
            new GridData(SWT.LEAD, SWT.CENTER, true, false));
      relocateTargetCheck = FormBuilder.createCheckBox(panel, "Is relocation target",
            new GridData(SWT.LEAD, SWT.CENTER, true, false));
   }
}
