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

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
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

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      ActivityType activity = (ActivityType) element;
      if (ActivityUtil.isInteractive(activity))
      {
         abortCheck.setEnabled(true);
         abortCheck.setSelection(activity.isAllowsAbortByPerformer());
      }
      else
      {
         abortCheck.setEnabled(false);
         abortCheck.setSelection(false);
      }
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

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
      ActivityType activity = (ActivityType) element;
      if (activity != null)
      {
         if (ActivityUtil.isInteractive(activity))
         {
            activity.setAllowsAbortByPerformer(abortCheck.getSelection());
         }
         else
         {
            activity.unsetAllowsAbortByPerformer();
         }
         activity.setHibernateOnCreation(hibernateCheck.getSelection());
      }
   }

   public void contributeExtraControls(Composite composite)
   {
      abortCheck = FormBuilder.createCheckBox(composite,
            Diagram_Messages.CHECKBOX_AllowsAbortByParticipant, 2);
      hibernateCheck = FormBuilder.createCheckBox(composite,
            Diagram_Messages.CHECKBOX_HibernateInitially, 2);
   }
}
