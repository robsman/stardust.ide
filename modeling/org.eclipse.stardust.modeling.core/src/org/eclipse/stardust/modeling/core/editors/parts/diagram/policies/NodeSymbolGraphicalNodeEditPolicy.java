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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.policies;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.ui.PlatformUI;

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DynamicConnectionFactory;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ReloadConnectionsAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateConnectionSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegateCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IConnectionCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.ModifyConnectionSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.ReloadConnectionCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetActivityControlFlowCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.properties.LinkTypeGeneralPropertyPage;


public class NodeSymbolGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy
{
   private WorkflowModelEditor editor;

   public NodeSymbolGraphicalNodeEditPolicy(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   protected AbstractNodeSymbolEditPart getFlowObjectEditPart()
   {
      return (AbstractNodeSymbolEditPart) getHost();
   }

   protected INodeSymbol getFlowObjectModel()
   {
      return (INodeSymbol) getFlowObjectEditPart().getModel();
   }

   protected Command getConnectionCreateCommand(CreateConnectionRequest request)
   {
      Command result = null;

      INodeSymbol sourceSymbol = (INodeSymbol) getHost().getModel();
      ISymbolContainer symbolContainer = (ISymbolContainer) sourceSymbol.eContainer();

      Object part = request.getNewObject();

      if (part instanceof IConnectionCommand)
      {
         CompoundCommand compound = new CompoundCommand();
         if (canStartConnection(request.getNewObjectType(), sourceSymbol,
               (IConnectionCommand) part, compound))
         {
            ((IConnectionCommand) part).setParent(symbolContainer);
            ((IConnectionCommand) part).setSourceSymbol(sourceSymbol);

            CompoundCommand cmds = new CompoundCommand();
            cmds.add((Command) part);
            for (Iterator iter = compound.getCommands().iterator(); iter.hasNext();)
            {
               cmds.add((Command) iter.next());
            }
            request.setStartCommand(cmds);

            result = cmds.unwrap();
         }
      }
      else if (request.getNewObject() instanceof DynamicConnectionFactory)
      {
         return ((DynamicConnectionFactory) request.getNewObject())
               .getStartCommand(sourceSymbol);
      }

      // return null if connection would be invalid, to force feedback being denial
      return result;
   }

   private boolean canStartConnection(Object newObjectType, INodeSymbol sourceSymbol,
         IConnectionCommand command, CompoundCommand compound)
   {
      boolean canStart = false;
      if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getTransitionConnectionType()))
      {
         if (sourceSymbol instanceof ActivitySymbolType)
         {
            ActivitySymbolType activitySymbol = (ActivitySymbolType) sourceSymbol;
            ActivityType activity = (ActivityType) activitySymbol.getModelElement();
            canStart = activity.getSplit().getValue() == JoinSplitType.NONE
                  && activity.getOutTransitions().size() < 2;

            if (compound != null)
            {
               createAutomaticSplitCmd(sourceSymbol, compound);
            }
         }
         else if (sourceSymbol instanceof GatewaySymbol)
         {
            GatewaySymbol gateway = (GatewaySymbol) sourceSymbol;
            ActivitySymbolType activitySymbol = gateway.getActivitySymbol();
            ActivityType activity = (ActivityType) activitySymbol.getModelElement();
            canStart = gateway.getFlowKind().getValue() == FlowControlType.SPLIT
                  && activity.getSplit().getValue() != JoinSplitType.NONE;
         }
         if (sourceSymbol instanceof StartEventSymbol)
         {
            StartEventSymbol startEventSymbol = (StartEventSymbol) sourceSymbol;
            canStart = startEventSymbol.getOutTransitions().size() == 0;
         }
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getRefersToConnectionType()))
      {
         canStart = sourceSymbol instanceof AnnotationSymbolType;
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getDataMappingConnectionType()))
      {
         canStart = sourceSymbol instanceof ActivitySymbolType
               || sourceSymbol instanceof DataSymbolType;
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getWorksForConnectionType()))
      {
         canStart = sourceSymbol instanceof RoleSymbolType;
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getPartOfConnectionType()))
      {
         canStart = sourceSymbol instanceof OrganizationSymbolType;
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getExecutedByConnectionType()))
      {
         canStart = sourceSymbol instanceof ApplicationSymbolType;
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getPerformsConnectionType()))
      {
         canStart = sourceSymbol instanceof IModelParticipantSymbol;
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getGenericLinkConnectionType()))
      {
         if (command instanceof CreateConnectionSymbolCommand)
         {
            canStart = isCompatible(sourceSymbol,
                  ((LinkTypeType) ((CreateConnectionSymbolCommand) command)
                        .getIdFactory().getReferingElement()).getSourceClass());
         }
      }
      return canStart;
   }

   private boolean isCompatible(INodeSymbol sourceSymbol, String sourceClass)
   {
      return LinkTypeGeneralPropertyPage.TYPE_LABELS[0][0].equals(sourceClass)
            && sourceSymbol instanceof ActivitySymbolType
            || LinkTypeGeneralPropertyPage.TYPE_LABELS[1][0].equals(sourceClass)
            && sourceSymbol instanceof DataSymbolType
            || LinkTypeGeneralPropertyPage.TYPE_LABELS[2][0].equals(sourceClass)
            && sourceSymbol instanceof RoleSymbolType
            || LinkTypeGeneralPropertyPage.TYPE_LABELS[3][0].equals(sourceClass)
            && sourceSymbol instanceof ProcessSymbolType
            || LinkTypeGeneralPropertyPage.TYPE_LABELS[4][0].equals(sourceClass)
            && sourceSymbol instanceof TransitionConnectionType
            || LinkTypeGeneralPropertyPage.TYPE_LABELS[5][0].equals(sourceClass)
            && sourceSymbol instanceof OrganizationSymbolType
            || LinkTypeGeneralPropertyPage.TYPE_LABELS[6][0].equals(sourceClass)
            && sourceSymbol instanceof IModelParticipantSymbol;
   }

   protected Command getConnectionCompleteCommand(CreateConnectionRequest request)
   {
      Command result = null;

      INodeSymbol sourceSymbol = (INodeSymbol) request.getSourceEditPart().getModel();
      INodeSymbol targetSymbol = (INodeSymbol) getHost().getModel();

      if (sourceSymbol != targetSymbol)
      {
         Object part = request.getNewObject();

         if (part instanceof IConnectionCommand)
         {
            Object newObjectType = request.getNewObjectType();
            CompoundCommand compound = new CompoundCommand();
            if (canEndConnection(newObjectType, targetSymbol, sourceSymbol,
                  (IConnectionCommand) part, compound))
            {
               ((IConnectionCommand) part).setTargetSymbol(targetSymbol);

               CompoundCommand cmds = new CompoundCommand();
               cmds.add((Command) part);
               CompoundCommand starter = (CompoundCommand) request.getStartCommand();
               for (Iterator iter = starter.getCommands().iterator(); iter.hasNext();)
               {
                  Command x = (Command) iter.next();
                  if (x != part)
                  {
                     cmds.add(x);
                  }
               }
               for (Iterator iter = compound.getCommands().iterator(); iter.hasNext();)
               {
                  cmds.add((Command) iter.next());
               }

               result = cmds;
            }
         }
      }

      if (request.getNewObject() instanceof DynamicConnectionFactory)
      {
         return ((DynamicConnectionFactory) request.getNewObject())
               .getCompleteCommand(targetSymbol);
      }

      // return null if connection would be invalid, to force feedback being denial
      return result;
   }

   private boolean canEndConnection(Object newObjectType, INodeSymbol targetSymbol,
         INodeSymbol sourceSymbol, IConnectionCommand command, CompoundCommand compound)
   {
      boolean canEnd = false;
      if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getTransitionConnectionType())
            && !transitionExists(sourceSymbol, targetSymbol))
      {
         if (targetSymbol instanceof ActivitySymbolType)
         {
            ActivitySymbolType activitySymbol = (ActivitySymbolType) targetSymbol;
            ActivityType activity = (ActivityType) activitySymbol.getModelElement();
            canEnd = activity.getJoin().getValue() == JoinSplitType.NONE
                  && (((sourceSymbol instanceof ActivitySymbolType || sourceSymbol instanceof GatewaySymbol) && activity
                        .getInTransitions().size() < 2) || activity.getInTransitions()
                        .size() == 0)
                  && checkStartEvents(activitySymbol, sourceSymbol, compound);
            canEnd = canEnd && sourceSymbol instanceof ActivitySymbolType
                  ? !((ActivitySymbolType) sourceSymbol).getActivity().equals(activity)
                  : canEnd;

            if (compound != null)
            {
               createAutomaticJoinCmd(activity, sourceSymbol, compound);
            }
         }
         else if (targetSymbol instanceof GatewaySymbol)
         {
            GatewaySymbol gateway = (GatewaySymbol) targetSymbol;
            ActivitySymbolType activitySymbol = gateway.getActivitySymbol();
            ActivityType activity = (ActivityType) activitySymbol.getModelElement();
            canEnd = gateway.getFlowKind().getValue() == FlowControlType.JOIN
                  && activity.getJoin().getValue() != JoinSplitType.NONE
                  && !hasTransition(sourceSymbol, activity)
                  && !(sourceSymbol instanceof StartEventSymbol);
         }
         else if (targetSymbol instanceof EndEventSymbol)
         {
            EndEventSymbol endEventSymbol = (EndEventSymbol) targetSymbol;
            canEnd = endEventSymbol.getInTransitions().size() == 0
                  && sourceSymbol instanceof ActivitySymbolType
                  && (((ActivitySymbolType) sourceSymbol).getOutTransitions().isEmpty() || command == null);
            // since we delete the transition we don't need to check for it (only when
            // reconnect)
         }
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getRefersToConnectionType()))
      {
         canEnd = (targetSymbol instanceof INodeSymbol || targetSymbol instanceof IConnectionSymbol)
               && !(targetSymbol instanceof TextSymbolType)
               && !(targetSymbol instanceof AnnotationSymbolType)
               && !(targetSymbol instanceof RefersToConnectionType);
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getDataMappingConnectionType()))
      {
         canEnd = sourceSymbol instanceof ActivitySymbolType
               && targetSymbol instanceof DataSymbolType
               || sourceSymbol instanceof DataSymbolType
               && targetSymbol instanceof ActivitySymbolType;
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getWorksForConnectionType()))
      {
         if (targetSymbol instanceof OrganizationSymbolType)
         {
            RoleType source = (RoleType) ((RoleSymbolType) sourceSymbol)
                  .getModelElement();
            OrganizationType target = (OrganizationType) ((OrganizationSymbolType) targetSymbol)
                  .getModelElement();
            canEnd = !isContained(source, target);
         }
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getPartOfConnectionType()))
      {
         if (targetSymbol instanceof OrganizationSymbolType)
         {
            OrganizationType source = (OrganizationType) ((OrganizationSymbolType) sourceSymbol)
                  .getModelElement();
            OrganizationType target = (OrganizationType) ((OrganizationSymbolType) targetSymbol)
                  .getModelElement();
            ModelType model = ModelUtils.findContainingModel(targetSymbol);
            canEnd = !source.equals(target) && !isContained(source, target)
                  && !isPartOf(model, target, source);
         }
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getExecutedByConnectionType()))
      {
         if (targetSymbol instanceof ActivitySymbolType)
         {
            ActivityType activity = ((ActivitySymbolType) targetSymbol).getActivity();
            canEnd = (null != activity)
                  && (ActivityUtil
                        .isApplicationActivity(activity))
                  && (null == activity.getApplication());
         }
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getPerformsConnectionType()))
      {
         if (targetSymbol instanceof ActivitySymbolType)
         {
            ActivityType activity = ((ActivitySymbolType) targetSymbol).getActivity();
            canEnd = (null != activity)
                  && (ActivityUtil
                        .isInteractive(activity)) && (null == activity.getPerformer());
            if (!canEnd
                  && (!ActivityUtil.isInteractive(activity) || (ActivityImplementationType.APPLICATION_LITERAL
                        .equals(activity.getImplementation()) && activity
                        .getApplication() == null)) && compound != null)
            {
               createChangeActivityImplTypeCmd(activity, compound,
                     (ActivitySymbolType) targetSymbol, command);
               canEnd = true;
            }
         }
      }
      else if (newObjectType.equals(CarnotWorkflowModelPackage.eINSTANCE
            .getGenericLinkConnectionType()))
      {
         if (command instanceof CreateConnectionSymbolCommand)
         {
            canEnd = isCompatible(targetSymbol,
                  ((LinkTypeType) ((CreateConnectionSymbolCommand) command)
                        .getIdFactory().getReferingElement()).getTargetClass());
         }
      }
      return canEnd;
   }

   private void createChangeActivityImplTypeCmd(final ActivityType activity,
         CompoundCommand compound, final ActivitySymbolType activitySymbol,
         final IConnectionCommand command)
   {
      DelegateCommand delegate = new DelegateCommand()
      {
         public void execute()
         {
            MessageDialogWithToggle dialog = null;
            if (PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE))
            {
               dialog = MessageDialogWithToggle.openYesNoQuestion(null,
                     Diagram_Messages.TITLE_ChangeToManualActivity,
                     Diagram_Messages.MSG_ChangeImplTypeActivity,
                     Diagram_Messages.LB_RememberDecision, false, PlatformUI
                           .getPreferenceStore(),
                     BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE);
               if (dialog.getToggleState())
               {
                  PlatformUI.getPreferenceStore().setValue(
                        BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE, false);
                  PlatformUI.getPreferenceStore().setValue(
                        BpmProjectNature.PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE,
                        IDialogConstants.YES_ID == dialog.getReturnCode());
                  PlatformUI.getPreferenceStore().setValue(
                        BpmProjectNature.PREFERENCE_NEVER_SWITCH_ACTIVITY_TYPE,
                        IDialogConstants.NO_ID == dialog.getReturnCode());
               }
            }
            boolean canSwitch = PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE)
                  ? IDialogConstants.YES_ID == dialog.getReturnCode()
                  : PlatformUI.getPreferenceStore().getBoolean(
                        BpmProjectNature.PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE);
            if (canSwitch)
            {
               super.execute();
            }
            else
            {
               if (activitySymbol.getPerformsConnections().size() == 1)
               {
                  ((CreateConnectionSymbolCommand) command).undo();
               }
            }
         }
      };
      delegate.setDelegate(ActivityCommandFactory.getSetImplementationCommand(
            ActivityImplementationType.MANUAL_LITERAL, activity));
      compound.add(delegate);
   }

   private boolean transitionExists(INodeSymbol sourceSymbol, INodeSymbol targetSymbol)
   {
      boolean transitionExists = false;

      ActivityType srcActicity = null;
      if (sourceSymbol instanceof ActivitySymbolType)
      {
         srcActicity = ((ActivitySymbolType) sourceSymbol).getActivity();
      }
      else if ((sourceSymbol instanceof GatewaySymbol)
            && (null != ((GatewaySymbol) sourceSymbol).getActivitySymbol()))
      {
         srcActicity = ((GatewaySymbol) sourceSymbol).getActivitySymbol().getActivity();
      }

      ActivityType tgtActicity = null;
      if (targetSymbol instanceof ActivitySymbolType)
      {
         tgtActicity = ((ActivitySymbolType) targetSymbol).getActivity();
      }
      else if ((targetSymbol instanceof GatewaySymbol)
            && (null != ((GatewaySymbol) targetSymbol).getActivitySymbol()))
      {
         tgtActicity = ((GatewaySymbol) targetSymbol).getActivitySymbol().getActivity();
      }

      if ((null != srcActicity) && (null != tgtActicity))
      {
         for (Iterator i = srcActicity.getOutTransitions().iterator(); i.hasNext();)
         {
            TransitionType transition = (TransitionType) i.next();
            if (tgtActicity == transition.getTo())
            {
               transitionExists = true;
               break;
            }
         }
      }

      return transitionExists;
   }

   private void createAutomaticJoinCmd(ActivityType activity, INodeSymbol sourceSymbol,
         CompoundCommand cmd)
   {
      if ((!(sourceSymbol instanceof StartEventSymbol))
            && activity.getInTransitions().size() == 1)
      {
         cmd.add(new SetActivityControlFlowCmd(editor, activity, FlowControlType.JOIN_LITERAL,
               JoinSplitType.AND_LITERAL)
         {
            public void execute()
            {
               canExecute();
               super.execute();
            }
         });
      }
   }

   private void createAutomaticSplitCmd(INodeSymbol sourceSymbol, CompoundCommand cmd)
   {
      if (sourceSymbol instanceof ActivitySymbolType)
      {
         ActivityType sourceActivity = ((ActivitySymbolType) sourceSymbol).getActivity();
         if (sourceActivity.getOutTransitions().size() == 1)
         {
            cmd.add(new SetActivityControlFlowCmd(editor, sourceActivity,
                  FlowControlType.SPLIT_LITERAL, JoinSplitType.AND_LITERAL)
            {
               public void execute()
               {
                  canExecute();
                  super.execute();
               }
            });
         }
      }
   }

   private boolean checkStartEvents(ActivitySymbolType activitySymbol,
         INodeSymbol sourceSymbol, CompoundCommand compound)
   {
      if ((sourceSymbol instanceof StartEventSymbol)
            && ((StartEventSymbol) sourceSymbol).getTrigger() == null)
      {
         for (Iterator iter = activitySymbol.getInTransitions().iterator(); iter
               .hasNext();)
         {
            TransitionConnectionType conn = (TransitionConnectionType) iter.next();
            if ((conn.getSourceNode() instanceof StartEventSymbol)
                  && ((StartEventSymbol) conn.getSourceNode()).getTrigger() == null)
            {
               return false;
            }
         }
      }
      else if (sourceSymbol instanceof ActivitySymbolType)
      {
         for (int i = 0; i < activitySymbol.getInConnectionFeatures().size(); i++)
         {
            EStructuralFeature feature = (EStructuralFeature) activitySymbol
                  .getInConnectionFeatures().get(i);
            Object connection = activitySymbol.eGet(feature);
            if (connection instanceof List)
            {
               List connectionList = (List) connection;
               for (int j = 0; j < connectionList.size(); j++)
               {
                  if (connectionList.get(j) instanceof TransitionConnectionType)
                  {
                     if (((TransitionConnectionType) connectionList.get(j))
                           .getSourceNode() instanceof StartEventSymbol)
                     {
                        compound.add(new DeleteConnectionSymbolCmd(
                              (TransitionConnectionType) connectionList.get(j)));
                     }
                  }
               }
            }

         }
      }
      return true;
   }

   private boolean hasTransition(INodeSymbol sourceSymbol, ActivityType target)
   {
      ActivityType source = getActivityType(sourceSymbol);

      if (source != null && target != null)
      {
         ProcessDefinitionType process = ModelUtils.findContainingProcess(sourceSymbol);
         List transitions = process.getTransition();
         for (int i = 0; i < transitions.size(); i++)
         {
            TransitionType type = (TransitionType) transitions.get(i);
            if (type.getFrom().equals(source) && type.getTo().equals(target))
            {
               return true;
            }
         }
      }
      return false;
   }

   private static ActivityType getActivityType(INodeSymbol symbol)
   {
      if (symbol instanceof GatewaySymbol)
      {
         symbol = ((GatewaySymbol) symbol).getActivitySymbol();
      }
      return symbol instanceof ActivitySymbolType ? ((ActivitySymbolType) symbol)
            .getActivity() : null;
   }

   private boolean isPartOf(ModelType model, OrganizationType child,
         OrganizationType parent)
   {
      if (isContained(child, parent))
      {
         return true;
      }
      for (Iterator i = parent.getParticipant().iterator(); i.hasNext();)
      {
         ParticipantType participantRef = (ParticipantType) i.next();
         IModelParticipant participant = participantRef.getParticipant();
         if ((participant instanceof OrganizationType)
               && isPartOf(model, child, (OrganizationType) participant))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isContained(IModelParticipant child, OrganizationType parent)
   {
      for (Iterator i = parent.getParticipant().iterator(); i.hasNext();)
      {
         ParticipantType participant = (ParticipantType) i.next();
         if (participant.getParticipant() == child)
         {
            return true;
         }
      }
      return false;
   }

   protected Command getReconnectSourceCommand(ReconnectRequest request)
   {
      CompoundCommand cmds = new CompoundCommand();
      CompoundCommand compound = new CompoundCommand();

      // new point
      AbstractNodeSymbolEditPart target = (AbstractNodeSymbolEditPart) request
            .getTarget();
      AbstractConnectionSymbolEditPart connectionPart = (AbstractConnectionSymbolEditPart) request
            .getConnectionEditPart();
      // old point
      EditPart currentSource = connectionPart.getSource();
      EditPart currentTarget = connectionPart.getTarget();
      // the other unchanged point of the connection
      EditPart otherPoint = connectionPart.getTarget();

      if (connectionPart.getModel() instanceof TransitionConnectionType
            && (target.getModel().equals(
                  connectionPart.getConnectionSymbolModel().getSourceNode()) || canStartConnection(
                  connectionPart.getConnectionSymbolModel().eClass(),
                  (INodeSymbol) target.getModel(), null, compound)))
      {
         TransitionConnectionType connection = (TransitionConnectionType) connectionPart
               .getConnectionSymbolModel();
         // do not reconnect to same symbol
         if (!target.equals(currentTarget)
               && ((target == currentSource) || isValidReconnect(connection)))
         {
            ModifyConnectionSymbolCommand modifyCmd = new ModifyConnectionSymbolCommand();
            modifyCmd.setAnchorType((String) request.getExtendedData().get(
                  CarnotConstants.DIAGRAM_PLUGIN_ID + ".sourceAnchor")); //$NON-NLS-1$
            modifyCmd.setSource((INodeSymbol) target.getModel());
            modifyCmd.setConnection((IConnectionSymbol) connectionPart.getModel());

            boolean needTransition;
            if (isActivityRelated((IFlowObjectSymbol) target.getModel())
                  && isActivityRelated((IFlowObjectSymbol) otherPoint.getModel())
                  && isNoGatewayTransition((IFlowObjectSymbol) target.getModel(),
                        (IFlowObjectSymbol) otherPoint.getModel(), connection))
            {
               needTransition = true;
            }
            else
            {
               needTransition = false;
            }
            if (connection.getTransition() != null)
            {
               TransitionType transition = connection.getTransition();
               if (needTransition == true)
               {
                  cmds.add(new SetValueCmd(transition,
                        CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_From(),
                        getActivityType((INodeSymbol) target.getModel())));

                  // when reconnecting to another source all other connection symbols have
                  // to be deleted
                  if (target != currentSource)
                  {
                     deleteTransitionConnections(cmds, connection, transition);
                  }
               }
               else
               {
                  deleteTransition(cmds, connection, transition, target, otherPoint, true);
               }
            }
            else
            {
               if (needTransition == true)
               {
                  createTransition(cmds, target, otherPoint, connection, true);
               }
            }
            cmds.add(modifyCmd);
            for (Iterator iter = compound.getCommands().iterator(); iter.hasNext();)
            {
               cmds.add((Command) iter.next());
            }
            return cmds;
         }
      }
      return UnexecutableCommand.INSTANCE;
   }

   private boolean isNoGatewayTransition(IFlowObjectSymbol sourceSymbol,
         IFlowObjectSymbol targetSymbol, TransitionConnectionType connection)
   {
      boolean isNoGatewayTransition = true;
      if ((sourceSymbol instanceof ActivitySymbolType && targetSymbol instanceof ActivitySymbolType)
            || (sourceSymbol instanceof GatewaySymbol && targetSymbol instanceof GatewaySymbol))
      {
         isNoGatewayTransition = true;
      }
      else
      {
         ActivitySymbolType activitySymbol = (ActivitySymbolType) (sourceSymbol instanceof ActivitySymbolType
               ? sourceSymbol
               : targetSymbol);
         GatewaySymbol gateway = (GatewaySymbol) (sourceSymbol instanceof GatewaySymbol
               ? sourceSymbol
               : targetSymbol);
         if (activitySymbol.getGatewaySymbols().contains(gateway))
         {
            if (!JoinSplitType.NONE_LITERAL.equals(activitySymbol.getActivity()
                  .getSplit()))
            {
               isNoGatewayTransition = connection.getTargetActivitySymbol().equals(
                     activitySymbol);
            }
            if (isNoGatewayTransition
                  && (!JoinSplitType.NONE_LITERAL.equals(activitySymbol.getActivity()
                        .getJoin())))
            {
               isNoGatewayTransition = connection.getSourceActivitySymbol().equals(
                     activitySymbol);
            }
         }
         else
         {
            isNoGatewayTransition = true;
         }
      }
      return isNoGatewayTransition;
   }

   private boolean isActivityRelated(IFlowObjectSymbol symbol)
   {
      return symbol instanceof ActivitySymbolType || symbol instanceof GatewaySymbol;
   }

   protected Command getReconnectTargetCommand(ReconnectRequest request)
   {
      CompoundCommand cmds = new CompoundCommand();
      CompoundCommand compound = new CompoundCommand();

      // new point
      AbstractNodeSymbolEditPart target = (AbstractNodeSymbolEditPart) request
            .getTarget();
      AbstractConnectionSymbolEditPart connectionPart = (AbstractConnectionSymbolEditPart) request
            .getConnectionEditPart();
      // previous one
      EditPart currentTarget = connectionPart.getTarget();
      // other point
      EditPart otherPoint = connectionPart.getSource();

      if (connectionPart.getModel() instanceof TransitionConnectionType
            && (target.getModel().equals(
                  connectionPart.getConnectionSymbolModel().getTargetNode()) || canEndConnection(
                  connectionPart.getConnectionSymbolModel().eClass(),
                  (INodeSymbol) target.getModel(), connectionPart
                        .getConnectionSymbolModel().getSourceNode(), null, compound)))
      {
         TransitionConnectionType connection = (TransitionConnectionType) connectionPart
               .getConnectionSymbolModel();

         if ((target == currentTarget) || isValidReconnect(connection))
         {
            ModifyConnectionSymbolCommand modifyCmd = new ModifyConnectionSymbolCommand();
            modifyCmd.setAnchorType((String) request.getExtendedData().get(
                  CarnotConstants.DIAGRAM_PLUGIN_ID + ".targetAnchor")); //$NON-NLS-1$
            modifyCmd.setTarget((INodeSymbol) target.getModel());
            modifyCmd.setConnection((IConnectionSymbol) connectionPart.getModel());

            boolean needTransition;
            if (isActivityRelated((IFlowObjectSymbol) target.getModel())
                  && isActivityRelated((IFlowObjectSymbol) otherPoint.getModel())
                  && isNoGatewayTransition((IFlowObjectSymbol) target.getModel(),
                        (IFlowObjectSymbol) otherPoint.getModel(), connection))
            {
               needTransition = true;
            }
            else
            {
               needTransition = false;
            }
            if (connection.getTransition() != null)
            {
               TransitionType transition = connection.getTransition();
               if (needTransition == true)
               {
                  cmds.add(new SetValueCmd(transition,
                        CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_To(),
                        getActivityType((INodeSymbol) target.getModel())));

                  // when reconnecting to another target all other connection symbols have
                  // to be deleted
                  if (target != currentTarget)
                  {
                     deleteTransitionConnections(cmds, connection, transition);
                  }
               }
               else
               {
                  if (needTransition == false)
                  {
                     deleteTransition(cmds, connection, transition, target, otherPoint,
                           false);
                  }
               }
            }
            else
            {
               if (needTransition == true)
               {
                  createTransition(cmds, target, otherPoint, connection, false);
               }
            }
            cmds.add(modifyCmd);
            for (Iterator iter = compound.getCommands().iterator(); iter.hasNext();)
            {
               cmds.add((Command) iter.next());
            }
            return cmds;
         }

      }
      return UnexecutableCommand.INSTANCE;
   }

   private void deleteTransitionConnections(CompoundCommand cmds,
         TransitionConnectionType connection, TransitionType transition)
   {
      for (Iterator iter = transition.getSymbols().iterator(); iter.hasNext();)
      {
         TransitionConnectionType conn = (TransitionConnectionType) iter.next();
         if (!conn.equals(connection))
         {
            cmds.add(new DeleteConnectionSymbolCmd(conn));
         }
      }
   }

   /*
    * isSource - target is the value for
    * CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_From()
    */
   private void createTransition(CompoundCommand cmds, EditPart target,
         EditPart otherPoint, TransitionConnectionType connection, boolean isSource)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      // new ModelElement
      TransitionType transitionModelElement = factory.createTransitionType();
      ModelType model = ModelUtils.findContainingModel(connection);
      transitionModelElement.setElementOid(ModelUtils.getElementOid(
            transitionModelElement, model));

      IdFactory idFactory = new IdFactory("Transition", //$NON-NLS-1$
            Diagram_Messages.BASENAME_Transition);
      ProcessDefinitionType process = ModelUtils.findContainingProcess(connection);
      idFactory.computeNames(process.getTransition());
      transitionModelElement.setId(idFactory.getId());
      transitionModelElement.setName(idFactory.getName());

      // add default condition
      transitionModelElement.setCondition("CONDITION"); //$NON-NLS-1$
      XmlTextNode expression = factory.createXmlTextNode();
      transitionModelElement.setExpression(expression);
      ModelUtils.setCDataString(expression.getMixed(), "true", true); //$NON-NLS-1$

      cmds.add(new SetValueCmd(process, CarnotWorkflowModelPackage.eINSTANCE
            .getProcessDefinitionType_Transition(), transitionModelElement));
      // change direction
      if (isSource == true)
      {
         EditPart tmp = target;
         target = otherPoint;
         otherPoint = tmp;
      }

      cmds.add(new SetValueCmd(transitionModelElement,
            CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_From(),
            getActivityType((INodeSymbol) otherPoint.getModel())));
      cmds.add(new SetValueCmd(transitionModelElement,
            CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_To(),
            getActivityType((INodeSymbol) target.getModel())));
      cmds.add(new SetValueCmd(connection, CarnotWorkflowModelPackage.eINSTANCE
            .getTransitionConnectionType_Transition(), transitionModelElement));
   }

   /*
    * isSource - target is the value for
    * CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_From()
    */
   private void deleteTransition(CompoundCommand cmds,
         TransitionConnectionType connection, TransitionType transition, EditPart target,
         EditPart otherPoint, boolean isSource)
   {
      // change direction
      if (isSource == true)
      {
         EditPart tmp = target;
         target = otherPoint;
         otherPoint = tmp;
      }

      // reference
      cmds
            .add(new DeleteValueCmd(connection, transition,
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getTransitionConnectionType_Transition()));

      cmds.add(new DeleteValueCmd(transition, getActivityType((INodeSymbol) target
            .getModel()), CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_To()));
      cmds.add(new DeleteValueCmd(transition, getActivityType((INodeSymbol) otherPoint
            .getModel()), CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_From()));

      ProcessDefinitionType process = ModelUtils.findContainingProcess(connection);
      // modelelement
      cmds.add(new DeleteValueCmd(process, transition,
            CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_Transition()));
   }

   private boolean isValidReconnect(TransitionConnectionType connection)
   {
      boolean validReconnect = false;
      if (connection.getTransition() != null
            || (connection.getTargetActivitySymbol() instanceof EndEventSymbol)
            || (connection.getSourceActivitySymbol() instanceof StartEventSymbol))
      {
         validReconnect = true;
      }
      return validReconnect;
   }

   public boolean understandsRequest(Request request)
   {
      if (ReloadConnectionsAction.REQ_RELOAD_CONNECTIONS.equals(request.getType()))
      {
         return true;
      }
      return super.understandsRequest(request);
   }

   public Command getCommand(Request request)
   {
      if (RequestConstants.REQ_CONNECTION_START.equals(request.getType())
            || RequestConstants.REQ_CONNECTION_END.equals(request.getType())
            || RequestConstants.REQ_RECONNECT_SOURCE.equals(request.getType())
            || RequestConstants.REQ_RECONNECT_TARGET.equals(request.getType())
            || ReloadConnectionsAction.REQ_RELOAD_CONNECTIONS.equals(request.getType()))
      {
         EditPart part = getHost();
         Object model = part.getModel();

         if (model instanceof INodeSymbol)
         {
            ModelServer server = editor.getModelServer();
            if (server != null && server.requireLock((EObject) model))
            {
               return UnexecutableCommand.INSTANCE;
            }
         }
      }

      if (ReloadConnectionsAction.REQ_RELOAD_CONNECTIONS.equals(request.getType()))
      {
         return getReloadConnectionsCommand();
      }
      return super.getCommand(request);
   }

   private Command getReloadConnectionsCommand()
   {
      Command command = UnexecutableCommand.INSTANCE;
      EditPart part = getHost();
      Object model = part.getModel();

      if (model instanceof INodeSymbol)
      {
         command = ReloadConnectionCommandFactory.INSTANCE
               .createReloadConnectionCmd((INodeSymbol) model);
      }
      return command;
   }

   protected IFigure getFeedbackLayer()
   {
      return getLayer(LayerConstants.SCALED_FEEDBACK_LAYER);
   }
}