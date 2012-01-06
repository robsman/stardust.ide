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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetAttributeReferenceCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class LaneParticipantCommandFactory extends DefaultPropSheetCmdFactory
{
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;

   public static final LaneParticipantCommandFactory INSTANCE = new LaneParticipantCommandFactory();

   public Command createSetValueCommand(UndoablePropSheetEntry entry,
         IPropertyDescriptor descriptor, IPropertySource target, Object value)
   {
      if (null == value)
      {
         return createResetValueCommand(descriptor, target);
      }
      else
      {
         CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
         result.add(super.createSetValueCommand(entry, descriptor, target, value));
         addSetParticipantCommands(result, (LaneSymbol) target.getEditableValue(),
               (IModelParticipant) value);
         return result.unwrap();
      }
   }

   public Command createResetValueCommand(IPropertyDescriptor descriptor,
         IPropertySource target)
   {
      CompoundCommand result = new CompoundCommand(descriptor.getDisplayName());
      result.add(super.createResetValueCommand(descriptor, target));
      addSetParticipantCommands(result, (LaneSymbol) target.getEditableValue(), null);
      return result.unwrap();
   }

   private void addSetParticipantCommands(CompoundCommand command, LaneSymbol lane,
         IModelParticipant participant)
   {
      List activitySymbols = lane.getActivitySymbol();
      for (int i = 0; i < activitySymbols.size(); i++)
      {
         ActivitySymbolType activitySymbol = (ActivitySymbolType) activitySymbols.get(i);
         ActivityType activity = (ActivityType) activitySymbol.getModelElement();

         if (ActivityUtil.isInteractive(activity)
               || DiagramPlugin.isBusinessView((WorkflowModelEditor) PlatformUI
                     .getWorkbench().getActiveWorkbenchWindow().getActivePage()
                     .getActiveEditor()))
         {
            addSetPerformerCommands(command, activity, participant, activity
                  .getPerformer(), false, null);
         }
      }
      List startEventSymbols = lane.getStartEventSymbols();
      for (Iterator iter = startEventSymbols.iterator(); iter.hasNext();)
      {
         StartEventSymbol startEventSymbol = (StartEventSymbol) iter.next();
         TriggerType trigger = startEventSymbol.getTrigger();
         if ((null != trigger) && (null != trigger.getType())
               && PredefinedConstants.MANUAL_TRIGGER.equals(trigger.getType().getId())
               || PredefinedConstants.SCAN_TRIGGER.equals(trigger.getType().getId()))
         {
            AttributeType attribute = AttributeUtil.getAttribute(trigger,
                  PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT);
            IModelParticipant element = null;
            if(attribute != null)
            {
               IdentifiableReference reference = attribute.getReference();
               if(reference != null)
               {
                  element = (IModelParticipant) reference.getIdentifiable();
               }               
            }
            addSetPerformerCommands(command, trigger, participant, element, attribute, false, null);               
         }
      }
   }

   public static void addSetPerformerCommands(CompoundCommand command,
         TriggerType trigger, IModelParticipant newPerformer, IModelParticipant originalPerformer,
         AttributeType attribute, boolean updateConnectionsOnly, EditPart target)
   {
      boolean setPerformer = false;
      if (!updateConnectionsOnly && newPerformer != originalPerformer)
      {
         if (target == null
               || originalPerformer == null
               || MessageDialog.openQuestion(target.getViewer().getControl().getShell(),
                     Diagram_Messages.MSG_DIA_SET_PERFORMANCER,
                     getMessage(newPerformer == null ? null : newPerformer.getName(), 
                           originalPerformer.getName(), trigger)))
         {
            setPerformer = true;
            boolean addAttribute = false;
            if(attribute == null)
            {
               addAttribute = true;
               attribute = AttributeUtil.createAttribute(PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT);
            }
            IdentifiableReference reference = attribute.getReference();
            if (reference == null)
            {
               reference = CarnotWorkflowModelFactory.eINSTANCE.createIdentifiableReference();
               attribute.setReference(reference);
            }
            if(addAttribute)
            {
               command.add(new SetValueCmd(trigger, CWM_PKG.getIExtensibleElement_Attribute(),
                     attribute));               
            }            
            command.add(new SetAttributeReferenceCmd(attribute, newPerformer));
         }
      }
      if(updateConnectionsOnly || setPerformer)
      {
         addUpdateConnectionsCommand(command, trigger, newPerformer, originalPerformer,
               CWM_PKG.getStartEventSymbol_TriggersConnections(),
               getSymbolFeature(newPerformer), CWM_PKG
                     .getTriggersConnectionType_ParticipantSymbol());
      }      
   }

   public static void addSetPerformerCommands(CompoundCommand command,
         ActivityType activity, IModelParticipant newPerformer,
         IModelParticipant originalPerformer, boolean updateConnectionsOnly,
         EditPart target)
   {
      boolean setPerformer = false;
      if (!updateConnectionsOnly && newPerformer != originalPerformer)
      {
         if (target == null
               || originalPerformer == null
               || MessageDialog.openQuestion(target.getViewer().getControl().getShell(),
                     Diagram_Messages.MSG_DIA_SET_PERFORMANCER, getMessage(newPerformer, originalPerformer,
                           activity)))
         {
            setPerformer = true;
            command.add(new SetValueCmd(activity, CWM_PKG.getActivityType_Performer(),
                  newPerformer));
         }
      }

      if(updateConnectionsOnly || setPerformer)
      {
         addUpdateConnectionsCommand(command, activity, newPerformer, originalPerformer,
               CWM_PKG.getActivitySymbolType_PerformsConnections(),
               getSymbolFeature(newPerformer), CWM_PKG
                     .getPerformsConnectionType_ParticipantSymbol());
      }
   }

   private static String getMessage(IModelParticipant newPerformer,
         IModelParticipant originalPerformer, ActivityType activity)
   {

      String returnCondition1 = Diagram_Messages.TXT_REMOVE_PERFORMER_NULL_FROM_ACTIVITY_ONE;
      String returnCondition2 = Diagram_Messages.TXT_REPLACE_PERFORMER_NULL_WITH_PERFORMER_ONE_FOR_ACTIVITY_TWO;

      return newPerformer == null ? MessageFormat.format(returnCondition1, new Object[] {
            getPerformerName(originalPerformer), activity.getName()}) : MessageFormat
            .format(returnCondition2, new Object[] {
                  getPerformerName(originalPerformer), getPerformerName(newPerformer),
                  activity.getName()});

   }

   private static String getMessage(String newPerformer, String originalPerformer,
         TriggerType trigger)
   {
      return newPerformer == null ? MessageFormat.format(
            Diagram_Messages.MSG_REMOVE_PERFORMER_FROM_MANUAL_TRIGGER, new Object[] {
                  originalPerformer, trigger.getName()}) : MessageFormat.format(
            Diagram_Messages.MSG_RPLACE_PERFORMER_WITH_PERFORMER_FOR_TRIGGER,
            new Object[] {originalPerformer, newPerformer, trigger.getName()});
   }

   public static String getPerformerName(IModelParticipant performer)
   {
      return performer == null ? "" : performer.getName() != null //$NON-NLS-1$
            ? performer.getName()
            : performer.getId() != null ? performer.getId() : Long.toString(performer
                  .getElementOid());
   }

   private static EStructuralFeature getSymbolFeature(IModelParticipant newPerformer)
   {
      EStructuralFeature symbolFeature = null;
      if (newPerformer instanceof RoleType)
      {
         symbolFeature = CWM_PKG.getISymbolContainer_RoleSymbol();
      }
      else if (newPerformer instanceof OrganizationType)
      {
         symbolFeature = CWM_PKG.getISymbolContainer_OrganizationSymbol();
      }
      else if (newPerformer instanceof ConditionalPerformerType)
      {
         symbolFeature = CWM_PKG.getISymbolContainer_ConditionalPerformerSymbol();
      }

      return symbolFeature;
   }
}