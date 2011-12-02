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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;

import ag.carnot.workflow.model.PredefinedConstants;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SetActivityImplementationAction extends Action
{
   private ActivityType activity;

   private EditDomain domain;

   private ActivityImplementationType implType;

   public SetActivityImplementationAction(ActivityImplementationType implType,
         ActivityType activity, EditDomain domain)
   {
      super(implType.getName());
      this.implType = implType;
      this.activity = activity;
      this.domain = domain;
   }

   public void run()
   {
      IModelParticipant performer = null;
      AttributeType auxiliaryAttr = AttributeUtil.getAttribute(activity, PredefinedConstants.ACTIVITY_IS_AUXILIARY_ATT);
      
      CompoundCommand cmd = new CompoundCommand();
      cmd.add(ActivityCommandFactory.getSetImplementationCommand(implType, activity));
      if(implType.equals(ActivityImplementationType.MANUAL_LITERAL))
      {
         performer = GenericUtils.getLanePerformerForActivity(activity);
         
         if(performer != null)
         {
            cmd.add(new SetValueCmd(activity, CarnotWorkflowModelPackage.eINSTANCE.getActivityType_Performer(), performer));
         }         
      }
      
      if(!ActivityUtil.isInteractive(activity, implType) && activity.getPerformer() != null)
      {
         cmd.add(new SetValueCmd(activity, CarnotWorkflowModelPackage.eINSTANCE.getActivityType_Performer(), null));
      }      
      if(!implType.equals(ActivityImplementationType.APPLICATION_LITERAL) && activity.getApplication() != null)
      {
         cmd.add(new SetValueCmd(activity, CarnotWorkflowModelPackage.eINSTANCE.getActivityType_Application(), null));
      }
      
      if(auxiliaryAttr != null)
      {
         cmd.add(new DeleteValueCmd(auxiliaryAttr.eContainer(), auxiliaryAttr, auxiliaryAttr.eContainingFeature()));
      }
      domain.getCommandStack().execute(cmd);
   }

   public ActivityImplementationType getImplType()
   {
      return implType;
   }
}