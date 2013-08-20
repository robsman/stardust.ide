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
package org.eclipse.stardust.modeling.core.editors;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.beans.TransitionBean;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
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
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.SelectionPopup;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.figures.anchors.TransitionConnectionAnchor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateConnectionSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IContainedElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetActivityControlFlowCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetAttributeReferenceCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ActivityCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.properties.LaneParticipantCommandFactory;
import org.eclipse.stardust.modeling.core.editors.tools.CommandCanceledException;
import org.eclipse.stardust.modeling.core.ui.SplitJoinDialog;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

public class DynamicConnectionCommand extends Command
{
   /*
    * List of all possible conections (as of version 4.3):
    *
    * i) Connections with underlying model element
    * - TransitionConnectionType
    * - DataMappingConnectionType
    *
    * ii) Connections with references
    * - ExecutedByConnectionType
    * - PerformsConnectionType
    * - PartOfConnectionType
    * - WorksForConnectionType
    * - SubProcessOfConnectionType
    *
    * iii) Annotation connections
    * - RefersToConnectionType
    *
    * iv) User defined connections
    * - GenericLinkConnectionType
    */

   private static final CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;

   private static final String CONNECTION_TYPE_SUFFIX = "ConnectionType"; //$NON-NLS-1$

   private static final Map<String, EClass> LINK_TYPE_MAPPING = CollectionUtils.newMap();
   static
   {
      LINK_TYPE_MAPPING.put("org.eclipse.stardust.model.xpdl.IActivity", PKG.getActivitySymbolType()); //$NON-NLS-1$
      LINK_TYPE_MAPPING.put("org.eclipse.stardust.model.xpdl.IData", PKG.getDataSymbolType()); //$NON-NLS-1$
      LINK_TYPE_MAPPING.put("org.eclipse.stardust.model.xpdl.IRole", PKG.getRoleSymbolType()); //$NON-NLS-1$
      LINK_TYPE_MAPPING.put("org.eclipse.stardust.model.xpdl.IProcessDefinition", PKG.getProcessSymbolType()); //$NON-NLS-1$
      LINK_TYPE_MAPPING.put("org.eclipse.stardust.model.xpdl.ITransition", PKG.getTransitionConnectionType()); //$NON-NLS-1$
      LINK_TYPE_MAPPING.put("org.eclipse.stardust.model.xpdl.IOrganization", PKG.getOrganizationSymbolType()); //$NON-NLS-1$
      LINK_TYPE_MAPPING.put("org.eclipse.stardust.model.xpdl.IParticipant", PKG.getIModelParticipantSymbol()); //$NON-NLS-1$
   };

   private static final EClass[] DIRECTIONLESS_CONNECTION_TYPES = {
         PKG.getTriggersConnectionType(),
         PKG.getExecutedByConnectionType(), PKG.getPerformsConnectionType(),
         PKG.getWorksForConnectionType(), PKG.getTeamLeadConnectionType()};

   private static final EClass[] UNSUPPORTED_CONNECTION_TYPES = {PKG
         .getSubProcessOfConnectionType()};


   private WorkflowModelEditor editor;

   private INodeSymbol sourceSymbol;

   private INodeSymbol targetSymbol;

   private String sourceAnchor;

   private String targetAnchor;

   private Command command;

   private List matchingFeatures;

   private List reverseFeatures;

   public DynamicConnectionCommand(WorkflowModelEditor editor)
   {
      super("Connect"); //$NON-NLS-1$
      this.editor = editor;
   }

   public void setSourceSymbol(INodeSymbol sourceSymbol)
   {
      this.sourceSymbol = sourceSymbol;
   }

   public void setTargetSymbol(INodeSymbol targetSymbol)
   {
      this.targetSymbol = targetSymbol;
   }

   public void setSourceAnchorType(String anchorType)
   {
      sourceAnchor = anchorType;
   }

   public void setTargetAnchorType(String anchorType)
   {
      targetAnchor = anchorType;
   }

   public boolean canExecute()
   {
      // isValidSourceSymbol MUST NOT be invoked if targetSymbol is not null because
      // of infinite loops generated by isTargetSymbolAvailable()
      return targetSymbol == null ? isValidSourceSymbol() : isValidTargetSymbol();
   }

   public void dispose()
   {
      editor = null;
      sourceSymbol = null;
      targetSymbol = null;
      if (command != null)
      {
         command.dispose();
         command = null;
      }
      sourceAnchor = null;
      targetAnchor = null;
      super.dispose();
   }

   public void execute()
   {
      Object feature = getConnectionFeature();
      if (feature == null)
      {
         cancelCommand();
      }
      setLabel(computeLabel(feature));
      switchSymbols(feature);
      command = createCommand(feature);
      switchSymbols(feature);
      command.execute();
   }

   private void switchSymbols(Object feature)
   {
      if (reverseFeatures.contains(feature))
      {
         INodeSymbol symbol = sourceSymbol;
         sourceSymbol = targetSymbol;
         targetSymbol = symbol;
      }
   }

   public void redo()
   {
      command.redo();
   }

   public void undo()
   {
      command.undo();
   }

   private Command createCommand(Object feature)
   {
      CompoundCommand command = new CompoundCommand();
      boolean shouldCreateConnection = true;
      final Object[] reference = new Object[1];

      // set also participant here
      if (feature == PKG.getTriggersConnectionType())
      {
         IModelParticipant participant = (IModelParticipant) extractModelElement(sourceSymbol);
         TriggerType trigger = (TriggerType) extractModelElement(targetSymbol);
         AttributeType attribute = AttributeUtil.getAttribute(trigger, PredefinedConstants.PARTICIPANT_ATT);
         if (attribute == null)
         {
            attribute = AttributeUtil.createAttribute(PredefinedConstants.PARTICIPANT_ATT);
            command.add(new SetValueCmd(trigger, PKG.getIExtensibleElement_Attribute(), attribute));
         }
         command.add(new SetAttributeReferenceCmd(attribute, participant));
      }

      if (feature == PKG.getTransitionConnectionType())
      {
         addIfNotNull(command, getCreateTransitionCommand(reference));
      }

      if (feature == PKG.getPerformsConnectionType())
      {
         ActivityType activity = (ActivityType) extractModelElement(targetSymbol);
         IModelParticipant originalPerformer = activity.getPerformer();
         IModelParticipant newPerformer = (IModelParticipant) extractModelElement(sourceSymbol);

         if (!ActivityUtil.isInteractive(activity))
         {
            if (!canChangeImplementation())
            {
               cancelCommand();
            }
            command.add(ActivityCommandFactory.getSetImplementationCommand(
                  ActivityImplementationType.MANUAL_LITERAL, activity));
         }
         for (PerformsConnectionType performsConnection : ((ActivitySymbolType) targetSymbol).getPerformsConnections())
         {
            if (performsConnection.getParticipantSymbol().getModelElement().equals(originalPerformer))
            {
               command.add(new DeleteConnectionSymbolCmd(performsConnection));
            }
         }
         command.add(getSetValueCommand(PKG.getActivityType_Performer()));
         // view a message - user can confirm or cancel to change the performer
         if(originalPerformer != null && !originalPerformer.equals(newPerformer))
         {
        	 String message = MessageFormat.format(Diagram_Messages.MSG_Replace_performer_with_performer_for_activity,
        			     new Object[]{LaneParticipantCommandFactory.getPerformerName(originalPerformer),
        			     LaneParticipantCommandFactory.getPerformerName(newPerformer),activity.getName()});
            if(!MessageDialog.openQuestion(editor.getSite().getShell(),Diagram_Messages.MSG_DIA_SET_PERFORMER, message)) {
                cancelCommand();

            }
         }
      }

      if (feature == PKG.getDataMappingConnectionType())
      {
         reference[0] = getExistingDataMappingConnection();
         shouldCreateConnection = reference[0] == null;
         command.add(getDataMappingCommand(reference));
      }

      if (feature == PKG.getExecutedByConnectionType())
      {
         command.add(getSetValueCommand(PKG.getActivityType_Application()));
         // target is activity
         final ActivityType activity = (ActivityType) extractModelElement(targetSymbol);
         IIdentifiableModelElement modelElement = extractModelElement(sourceSymbol);
         // if source is application
         if (modelElement instanceof ApplicationType)
         {
            // source is applications symbol, depends on context
            final ApplicationType application = (ApplicationType) modelElement;

            IModelParticipant lanePerformer = GenericUtils.getLanePerformerForActivity(activity);
            if (lanePerformer != null && application.isInteractive())
            {
               command.add(new SetValueCmd(activity, PKG.getActivityType_Performer(), lanePerformer));
            }

            List<DataMappingType> dataMappings = activity.getDataMapping();
            for (final DataMappingType dm : dataMappings)
            {
               final DirectionType direction = dm.getDirection();
               // move all IN data mappings to the application context
               if(direction.equals(DirectionType.IN_LITERAL))
               {
                  final DataType data = dm.getData();
                  final String context = PredefinedConstants.APPLICATION_CONTEXT;
                  // must be a command
                  command.add(new SetValueCmd(dm, PKG.getDataMappingType_Context(), context));
                  Command myCmd = new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        String accessPointId = getAccessPoint(activity, application, data, direction, context);
                        if(accessPointId != null)
                        {
                           return new SetValueCmd(dm, PKG.getDataMappingType_ApplicationAccessPoint(), accessPointId);
                        }
                        return null;
                     }
                  };
                  command.add(myCmd);
               }
               else if(direction.equals(DirectionType.OUT_LITERAL))
               {
                  final DataType data = dm.getData();
                  final String context = PredefinedConstants.APPLICATION_CONTEXT;
                  // if empty, switch context
                  if(org.eclipse.stardust.common.StringUtils.isEmpty(dm.getApplicationAccessPoint()))
                  {
                     command.add(new SetValueCmd(dm, PKG.getDataMappingType_Context(), context));
                     Command myCmd = new DelegatingCommand()
                     {
                        public Command createDelegate()
                        {
                           String accessPointId = getAccessPoint(activity, application, data, direction, context);
                           if(accessPointId != null)
                           {
                              return new SetValueCmd(dm, PKG.getDataMappingType_ApplicationAccessPoint(), accessPointId);
                           }
                           return null;
                        }
                     };
                     command.add(myCmd);
                  }
               }
            }
         }
      }

      if (feature == PKG.getWorksForConnectionType()
            || feature == PKG.getPartOfConnectionType()
            || feature == PKG.getTeamLeadConnectionType())
      {
         IModelParticipant role = (IModelParticipant) extractModelElement(sourceSymbol);
         OrganizationType organization = (OrganizationType) extractModelElement(targetSymbol);
         if (feature == PKG.getTeamLeadConnectionType())
         {
            if (null != organization.getTeamLead())
            {
               command.add(UnexecutableCommand.INSTANCE);
            }

            command.add(getSetValueCommand(PKG.getOrganizationType_TeamLead(), role));
         }
         else
         {
            ParticipantType participant = CarnotWorkflowModelFactory.eINSTANCE
                  .createParticipantType();
            participant.setParticipant(role);
            command.add(getSetValueCommand(PKG.getOrganizationType_Participant(),
                  participant));
         }
      }

      if (shouldCreateConnection)
      {
         CreateConnectionSymbolCommand cmd = feature instanceof EClass
               ? createConnectionCommand((EClass) feature, reference)
               : createGenericLinkCommand((LinkTypeType) feature);
         DiagramType diagram = ModelUtils.findContainingDiagram(sourceSymbol);
         PoolSymbol pool = DiagramUtil.getDefaultPool(diagram);
         /*
         ISymbolContainer container = (ISymbolContainer) sourceSymbol.eContainer();
         EditPart host = editor.findEditPart(container);
         if(host instanceof AbstractSwimlaneEditPart)
         {
            cmd.setParent(container);
         }
         else
         {
            cmd.setParent(pool == null ? (ISymbolContainer) diagram : pool);
         }
         */
         cmd.setParent(pool == null ? (ISymbolContainer) diagram : pool);
         cmd.setSourceSymbol(sourceSymbol);
         cmd.setTargetSymbol(targetSymbol);
         cmd.setSourceAnchorType(sourceAnchor);
         cmd.setTargetAnchorType(targetAnchor);
         command.add(cmd);
      }

      if (feature == PKG.getTransitionConnectionType())
      {
         addIfNotNull(command, createAutomaticJoinCmd());
         addIfNotNull(command, createAutomaticSplitCmd());
      }

      return command;
   }

   private void addIfNotNull(CompoundCommand command, Command cmd)
   {
      if (cmd != null)
      {
         command.add(cmd);
      }
   }

   private Command createAutomaticJoinCmd()
   {
      if (targetSymbol instanceof ActivitySymbolType
            && !(sourceSymbol instanceof StartEventSymbol)
            && ((ActivitySymbolType) targetSymbol).getActivity().getInTransitions()
                  .size() == 1
                  && !isBoundaryTransition(((ActivitySymbolType) targetSymbol).getActivity().getInTransitions().get(0)))
      {
         return getSetActivityControlFlowCmd(((ActivitySymbolType) targetSymbol)
               .getActivity(), FlowControlType.JOIN_LITERAL);
      }
      return null;
   }

   private Command createAutomaticSplitCmd()
   {
      if (sourceSymbol instanceof ActivitySymbolType
            && ((ActivitySymbolType) sourceSymbol).getActivity().getOutTransitions()
                  .size() == 1
                  && !isBoundaryTransition(((ActivitySymbolType) sourceSymbol).getActivity().getOutTransitions().get(0)))
      {
         return getSetActivityControlFlowCmd(((ActivitySymbolType) sourceSymbol)
               .getActivity(), FlowControlType.SPLIT_LITERAL);
      }
      return null;
   }

   private boolean isBoundaryTransition(TransitionType transition)
   {
      XmlTextNode type = transition.getExpression();
      String expression = type == null ? null : ModelUtils.getCDataString(transition.getExpression().getMixed());
      if (expression != null && expression.startsWith(TransitionBean.ON_BOUNDARY_EVENT_PREDICATE + "("))
      {
         return true;
      }
      
      return false;
   }
      
   private Command getSetActivityControlFlowCmd(ActivityType activity,
         FlowControlType flow)
   {
	   JoinSplitType type = JoinSplitType.XOR_LITERAL;

	   if(flow.equals(FlowControlType.SPLIT_LITERAL))
	   {
		   if(PlatformUI.getPreferenceStore().getBoolean(
				   BpmProjectNature.PREFERENCE_SPLIT_PROMPT))
		   {
			   SplitJoinDialog dialog = new SplitJoinDialog(Display.getDefault().getActiveShell(), flow);
			   if (Dialog.OK == dialog.open())
			   {
					if(SplitJoinDialog.isAnd())
					{
						type = JoinSplitType.AND_LITERAL;
					}
					else
					{
						type = JoinSplitType.XOR_LITERAL;
					}
			   }
			   else
			   {
			      return UnexecutableCommand.INSTANCE;
			   }
		   }
		   else if(PlatformUI.getPreferenceStore().getBoolean(
				   BpmProjectNature.PREFERENCE_SPLIT_AND))
		   {
			   type = JoinSplitType.AND_LITERAL;
		   }
		   else
		   {
			   type = JoinSplitType.XOR_LITERAL;
		   }
		}
		else
		{
			if(PlatformUI.getPreferenceStore().getBoolean(
					BpmProjectNature.PREFERENCE_JOIN_PROMPT))
			{
				SplitJoinDialog dialog = new SplitJoinDialog(Display.getDefault().getActiveShell(), flow);
				if (Dialog.OK == dialog.open())
				{
					if(SplitJoinDialog.isAnd())
					{
						type = JoinSplitType.AND_LITERAL;
					}
					else
					{
						type = JoinSplitType.XOR_LITERAL;
					}
				}
				else
				{
               return UnexecutableCommand.INSTANCE;				   
				}
			}
			else if(PlatformUI.getPreferenceStore().getBoolean(
		              BpmProjectNature.PREFERENCE_JOIN_AND))
			{
				type = JoinSplitType.AND_LITERAL;
			}
			else
			{
				type = JoinSplitType.XOR_LITERAL;
			}
		}

      Command cmd = new SetActivityControlFlowCmd(editor, activity, flow, type)
      {
         public void execute()
         {
            canExecute(); // initializes the commands
            super.execute();
         }
      };
      return cmd;
   }

   private DataMappingConnectionType getExistingDataMappingConnection()
   {
      ActivitySymbolType activitySymbol = (ActivitySymbolType) (sourceSymbol instanceof ActivitySymbolType
            ? sourceSymbol
            : targetSymbol);
      DataSymbolType dataSymbol = (DataSymbolType) (sourceSymbol instanceof DataSymbolType
            ? sourceSymbol
            : targetSymbol);
      for (DataMappingConnectionType connection : activitySymbol.getDataMappings())
      {
         if (connection.getDataSymbol() == dataSymbol)
         {
            return connection;
         }
      }
      return null;
   }

   private Command getDataMappingCommand(final Object[] reference)
   {
      ActivitySymbolType activitySymbol = (ActivitySymbolType) (sourceSymbol instanceof ActivitySymbolType
            ? sourceSymbol
            : targetSymbol);
      DataSymbolType dataSymbol = (DataSymbolType) (sourceSymbol instanceof DataSymbolType
            ? sourceSymbol
            : targetSymbol);

      final ActivityType activity = activitySymbol.getActivity();
      final ApplicationType application = activity.getApplication();

      final DataType data = dataSymbol.getData();

      final DirectionType direction = sourceSymbol == dataSymbol
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL;

      if (sourceSymbol == activitySymbol)
      {
         INodeSymbol symbol = sourceSymbol;
         sourceSymbol = targetSymbol;
         targetSymbol = symbol;
      }

      String mappingId = !StringUtils.isEmpty(data.getId())
         ? data.getId()
         : DirectionType.IN_LITERAL == direction ? "in" //$NON-NLS-1$
            : DirectionType.OUT_LITERAL == direction ? "out" : "DataMapping"; //$NON-NLS-1$ //$NON-NLS-2$

      IdFactory idFactory = new IdFactory(mappingId, null,
            CarnotWorkflowModelPackage.eINSTANCE.getDataMappingType(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(), null);
      List<DataMappingType> dataMappings = activity.getDataMapping();
      List<DataMappingType> idDomain = CollectionUtils.newList(dataMappings.size());
      for (DataMappingType mapping : dataMappings)
      {
         if (direction == mapping.getDirection())
         {
            idDomain.add(mapping);
         }
      }
      idFactory.computeNames(idDomain, false);
      final String id = idFactory.getId();

      final String context = getDataMappingContext(activity);

      CreateModelElementCommand command = new CreateModelElementCommand(
            IContainedElementCommand.PARENT, null, PKG.getDataMappingType())
      {
         protected IModelElement createModelElement()
         {
            DataMappingType dm = (DataMappingType) super.createModelElement();
            dm.setName(id);
            dm.setId(id);
            dm.setData(data);
            dm.setDirection(direction);
            dm.setContext(context);

            if(application != null)
            {
               String accessPointId = DynamicConnectionCommand.this.getAccessPoint(activity, application, data, direction, context);
               if(accessPointId != null)
               {
                  dm.setApplicationAccessPoint(accessPointId);
               }
            }
            return dm;
         }

         public void redo()
         {
            super.redo();
            ((DataMappingType) getModelElement()).setData(data);
            updateArrows(reference[0]);
         }

         public void undo()
         {
            super.undo();
            ((DataMappingType) getModelElement()).setData(null);
            updateArrows(reference[0]);
         }

         private void updateArrows(Object reference)
         {
            if (reference instanceof DataMappingConnectionType)
            {
               DataMappingConnectionType connection = (DataMappingConnectionType) reference;
               connection.setStyle(connection.getStyle());
            }
         }
      };
      command.setParent(activity);
      return command;
   }

   private String getAccessPoint(final ActivityType activity,
         final ApplicationType application, final DataType data,
         final DirectionType direction, final String context)
   {
      String accessPointId = null;
      // get type of the data
      String referenceClassName = GenericUtils.getReferenceClassName(data);
      Object dataClass = null;
      if(referenceClassName != null)
      {
         try
         {
            dataClass = Class.forName(referenceClassName);
         }
         catch (ClassNotFoundException e)
         {
            if(GenericUtils.dataHasClassAssigned(data))
            {
               dataClass = referenceClassName;
            }
         }
      }

      // get all access points for activity
      List accessPoints = ActivityUtil.getAccessPoints(activity,
            DirectionType.IN_LITERAL.equals(direction), context);

      // special case for primitive types
      if (data.getType().getId().equals(PredefinedConstants.PRIMITIVE_DATA))
      {
         accessPointId = getAccessPoint(activity, accessPoints, data);
      }

      // 1st iteration check for types
      if(accessPointId == null)
      {
         accessPointId = getAccessPoint(activity, dataClass, accessPoints, true, data);
      }
      if(accessPointId == null)
      {
         accessPointId = getAccessPoint(activity, dataClass, accessPoints, false, data);
      }
      return accessPointId;
   }

   //

   private String getAccessPoint(final ActivityType activity,
         Object dataClass, List accessPoints, boolean checkType, DataType data)
   {
      TypeFinder typeFinder = new TypeFinder((EObject) activity);

      for (Iterator i = accessPoints.iterator(); i.hasNext();)
      {
         AccessPointType accessPoint = (AccessPointType) i.next();
         DirectionType accessPointDirection = accessPoint.getDirection();
         String attrValue = AccessPointUtil.getTypeAttributeValue(accessPoint);

         // DMS
         if(GenericUtils.isDMSDataType(data))
         {
            DataTypeType type = accessPoint.getType();
            if(type != null && type.getId().equals(data.getType().getId()))
            {
               return accessPoint.getId();
            }
         }
         // Knitware
         else if(GenericUtils.isStructuredDataType(data))
         {
            String structuredTypeData = GenericUtils.getReferenceClassName(data);
            String structuredType = AttributeUtil.getAttributeValue(accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
            if(!StringUtils.isEmpty(structuredTypeData)
                  && !StringUtils.isEmpty(structuredType)
                  && structuredTypeData.equals(structuredType))
            {
               return accessPoint.getId();
            }
         }

         // compare types
         // should not be connected already!
         if(!GenericUtils.isConnected(activity, accessPoint.getId())
               && dataClass != null)
         {
            if(attrValue != null)
            {
               if(dataClass instanceof Class)
               {
                  Class applicationClass;
                  try
                  {
                     applicationClass = Class.forName(attrValue);
                     if(((Class) dataClass).isInterface())
                     {
                        if(DirectionType.IN_LITERAL == accessPointDirection)
                        {
                           if(applicationClass.isAssignableFrom((Class) dataClass))
                           {
                              return accessPoint.getId();
                           }
                        }
                        else
                        {
                           if(((Class) dataClass).isAssignableFrom(applicationClass))
                           {
                              return accessPoint.getId();
                           }
                        }
                     }
                     else
                     {
                        // 1st iteration
                        if(checkType)
                        {
                           if(applicationClass.equals(dataClass))
                           {
                              return accessPoint.getId();
                           }
                        }
                        else
                        {
                           if(DirectionType.IN_LITERAL == accessPointDirection)
                           {
                              if(applicationClass.isAssignableFrom((Class) dataClass))
                              {
                                 return accessPoint.getId();
                              }
                           }
                           else
                           {
                              if(((Class) dataClass).isAssignableFrom(applicationClass))
                              {
                                 return accessPoint.getId();
                              }
                           }
                        }
                     }
                  }
                  catch (ClassNotFoundException e)
                  {
                     TypeInfo typeInfo = typeFinder.findType(attrValue);
                     if(typeInfo != null && typeInfo.isSameType(((Class) dataClass).getName()))
                     {
                        return accessPoint.getId();
                     }
                  }
               }
               else
               {
                  TypeInfo typeInfo = typeFinder.findType(attrValue);
                  if(typeInfo != null && typeInfo.isSameType((String) dataClass))
                  {
                     return accessPoint.getId();
                  }
               }
            }
         }
      }
      return null;
   }

   // special case for primitive data
   private String getAccessPoint(final ActivityType activity,
         List accessPoints, DataType data)
   {
      String dataType = AttributeUtil.getAttributeValue(data, PredefinedConstants.TYPE_ATT);

      // 1st iteration, check for parameter
      for (Iterator i = accessPoints.iterator(); i.hasNext();)
      {
         AccessPointType accessPoint = (AccessPointType) i.next();
         String attrValue = AccessPointUtil.getTypeAttributeValue(accessPoint);
         String accessPointId = accessPoint.getId();

         // should not be connected already!
         if(!GenericUtils.isConnected(activity, accessPoint.getId()))
         {
            if(attrValue != null)
            {
               // is parameter, not method (prefer)
               if(accessPointId.indexOf("(") == -1) //$NON-NLS-1$
               {
                  // attrValue could be something like 'long', parameter
                  if(attrValue.equals(dataType))
                  {
                     return accessPointId;
                  }
               }
            }
         }
      }
      return null;
   }

   //

   private String getDataMappingContext(ActivityType activity)
   {
      if (ActivityImplementationType.ROUTE_LITERAL == activity.getImplementation())
      {
         return PredefinedConstants.DEFAULT_CONTEXT;
      }
      if (ActivityImplementationType.MANUAL_LITERAL == activity.getImplementation())
      {
         return PredefinedConstants.DEFAULT_CONTEXT;
      }
      if (ActivityImplementationType.APPLICATION_LITERAL == activity.getImplementation()
            && activity.getApplication() != null)
      {
         ApplicationType application = activity.getApplication();
         if (application.isInteractive())
         {
            if (application.getContext().size() > 0)
            {
               ContextType context = (ContextType) application.getContext().get(0);
               return context.getType().getId();
            }
            return PredefinedConstants.DEFAULT_CONTEXT;
         }
         return PredefinedConstants.APPLICATION_CONTEXT;
      }
      if (ActivityImplementationType.SUBPROCESS_LITERAL == activity.getImplementation()
            && activity.getImplementationProcess() != null)
      {
         ProcessDefinitionType process = activity.getImplementationProcess();
         if (process.getFormalParameters() != null)
         {
            return PredefinedConstants.PROCESSINTERFACE_CONTEXT;
         }
      }
      return PredefinedConstants.ENGINE_CONTEXT;
   }

   private boolean canChangeImplementation()
   {
      MessageDialogWithToggle dialog = null;
      if (PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE))
      {
         dialog = MessageDialogWithToggle.openYesNoQuestion(editor.getSite().getShell(),
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
      return PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_WARN_SWITCH_ACTIVITY_TYPE)
            ? IDialogConstants.YES_ID == dialog.getReturnCode()
            : PlatformUI.getPreferenceStore().getBoolean(
                  BpmProjectNature.PREFERENCE_ALWAYS_SWITCH_ACTIVITY_TYPE);
   }

   private Command getSetValueCommand(EStructuralFeature feature)
   {
      IIdentifiableModelElement value = extractModelElement(sourceSymbol);
      return getSetValueCommand(feature, value);
   }

   private Command getSetValueCommand(EStructuralFeature feature, Object value)
   {
      IIdentifiableModelElement parent = extractModelElement(targetSymbol);
      return new SetValueCmd(parent, feature, value);
   }

   private Command getCreateTransitionCommand(final Object[] reference)
   {
      IModelElement sourceModelElement = extractModelElement(sourceSymbol);
      final ActivityType sourceActivity = sourceModelElement instanceof ActivityType
            ? (ActivityType) sourceModelElement
            : null;
      final ActivityType targetActivity = (ActivityType) extractModelElement(targetSymbol);
      if (sourceActivity != null && targetActivity != null)
      {
         for (Iterator i = sourceActivity.getOutTransitions().iterator(); i.hasNext();)
         {
            TransitionType transition = (TransitionType) i.next();
            if (transition.getTo() == targetActivity)
            {
               return null;
            }
         }
         IdFactory id = new IdFactory("Transition", Diagram_Messages.BASENAME_Transition); //$NON-NLS-1$
         CreateModelElementCommand cmd = new CreateModelElementCommand(
               IContainedElementCommand.PROCESS, id, CarnotWorkflowModelPackage.eINSTANCE
                     .getTransitionType())
         {
            protected IModelElement createModelElement()
            {
               reference[0] = super.createModelElement();
               ((TransitionType) reference[0]).setCondition("CONDITION"); //$NON-NLS-1$
               XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
               ((TransitionType) reference[0]).setExpression(expression);
               ModelUtils.setCDataString(expression.getMixed(), "true", true); //$NON-NLS-1$
               return (IModelElement) reference[0];
            }

            public void redo()
            {
               super.redo();
               TransitionType transition = (TransitionType) getModelElement();
               transition.setFrom((ActivityType) sourceActivity);
               transition.setTo((ActivityType) targetActivity);
            }

            public void undo()
            {
               super.undo();
               TransitionType transition = (TransitionType) getModelElement();
               transition.setFrom(null);
               transition.setTo(null);
            }
         };
         cmd.setParent(sourceActivity);
         return cmd;
      }
      return null;
   }

   private CreateConnectionSymbolCommand createConnectionCommand(EClass eClass,
         final Object[] reference)
   {
      String label = computeLabel(eClass);
      IdFactory id = new IdFactory(label + Diagram_Messages.LBL_CONNECTION, label + Diagram_Messages.LBL_CONNECTION);
      return new CreateConnectionSymbolCommand(id, eClass)
      {
         protected IModelElement createModelElement()
         {
            IConnectionSymbol connection = (IConnectionSymbol) super.createModelElement();
            if (connection instanceof TransitionConnectionType)
            {
               connection.setSourceAnchor(sourceAnchorType == null
                     ? TransitionConnectionAnchor.CENTER
                     : sourceAnchorType);
               connection.setTargetAnchor(targetAnchorType == null
                     ? TransitionConnectionAnchor.CENTER
                     : targetAnchorType);
               ((TransitionConnectionType) connection)
                     .setTransition((TransitionType) reference[0]);
            }
            return connection;
         }
      };
   }

   private CreateConnectionSymbolCommand createGenericLinkCommand(
         final LinkTypeType linkType)
   {
      IdFactory id = new IdFactory("Link", "Link"); //$NON-NLS-1$ //$NON-NLS-2$
      EClass eClass = PKG.getGenericLinkConnectionType();
      return new CreateConnectionSymbolCommand(id, eClass)
      {
         protected IModelElement createModelElement()
         {
            GenericLinkConnectionType link = (GenericLinkConnectionType) super
                  .createModelElement();
            link.setLinkType(linkType);
            return link;
         }
      };
   }

   private Object getConnectionFeature()
   {
      Object feature = null;
      if (matchingFeatures.size() == 1)
      {
         feature = matchingFeatures.get(0);
      }
      else if (matchingFeatures.size() > 1)
      {
         // Popup
         SelectionPopup popup = new SelectionPopup(editor.getSite().getShell());
         popup.setContentProvider(new ArrayContentProvider());
         popup.setLabelProvider(new LabelProvider()
         {
            public Image getImage(Object element)
            {
               if (element instanceof EObject)
               {
                  return DiagramPlugin.getImage(editor.getIconFactory().getIconFor((EObject) element));
               }
               return super.getImage(element);
            }

            public String getText(Object element)
            {
               if (element instanceof EClass)
               {
                  String eClassName = ((EClass) element).getName();
                  return getExternalLabel(eClassName);
               }
               else if (element instanceof LinkTypeType)
               {
                  return ((LinkTypeType) element).getName();
               }
               return super.getText(element);
            }

            private String getExternalLabel(String label)
            {
               try
               {
                  Field field = Diagram_Messages.class.getField("ConnectAction_" + label);//$NON-NLS-1$
                  return (String) field.get(null);
               }
               catch (Exception e)
               {
                  e.printStackTrace();
                  return label;
               }
            }
         });
         popup.setInput(matchingFeatures);
         feature = popup.open();
      }
      return feature;
   }

   private void cancelCommand()
   {
      throw new CommandCanceledException(Diagram_Messages.EXC_USER_CANCELLED);
   }

   private IIdentifiableModelElement extractModelElement(INodeSymbol symbol)
   {
      if (symbol instanceof GatewaySymbol)
      {
         symbol = ((GatewaySymbol) symbol).getActivitySymbol();
      }
      if (symbol instanceof IModelElementNodeSymbol)
      {
         return ((IModelElementNodeSymbol) symbol).getModelElement();
      }
      return null;
   }

   private boolean isValidSourceSymbol()
   {
      // source symbol must not be null
      if (sourceSymbol == null)
      {
         return false;
      }

      // there must be at least one out connection feature
      if (sourceSymbol.getOutConnectionFeatures().isEmpty())
      {
         return false;
      }

      // special constraint: start event symbols may have at most 1 transition
      if (sourceSymbol instanceof StartEventSymbol
            && !((StartEventSymbol) sourceSymbol).getOutTransitions().isEmpty())
      {
         return false;
      }

      // check if there is any available end
      return isTargetSymbolAvailable();
   }

   private boolean isTargetSymbolAvailable()
   {
      DynamicConnectionCommand helper = new DynamicConnectionCommand(null);
      try
      {
         helper.setSourceSymbol(sourceSymbol);
         DiagramType diagram = ModelUtils.findContainingDiagram(sourceSymbol);
         if (diagram == null)
         {
            return false;
         }
         for (Iterator i = diagram.eAllContents(); i.hasNext();)
         {
            EObject eObject = (EObject) i.next();
            if (eObject instanceof INodeSymbol)
            {
               helper.setTargetSymbol((INodeSymbol) eObject);
               if (helper.canExecute())
               {
                  return true;
               }
            }
         }
         return false;
      }
      finally
      {
         helper.dispose();
      }
   }

   private boolean isValidTargetSymbol()
   {
      // target symbol must not be the same with source symbol
      if (sourceSymbol == targetSymbol)
      {
         return false;
      }

      // there must be at least one in connection feature
      if (targetSymbol.getInConnectionFeatures().isEmpty())
      {
         return false;
      }

      // special constraint
      if (targetSymbol instanceof EndEventSymbol
            && !isValidActivityRelated((INodeSymbol) sourceSymbol))
      {
         return false;
      }
      else if ((targetSymbol instanceof OrganizationSymbolType)
            && (sourceSymbol instanceof IModelParticipantSymbol))
      {
         //return false;
      }

      computeMatchingFeatures();

      return !matchingFeatures.isEmpty();
   }

   // check for transitions
   private boolean isValidActivityRelated(INodeSymbol symbol)
   {
      if (symbol instanceof GatewaySymbol)
      {
         symbol = ((GatewaySymbol) symbol).getActivitySymbol();
      }
      if (symbol instanceof ActivitySymbolType)
      {
         ActivityType activity = ((ActivitySymbolType) symbol).getActivity();
         return activity != null && activity.getOutTransitions().isEmpty();
      }
      return false;
   }

   private void computeMatchingFeatures()
   {
      matchingFeatures = new ArrayList();
      reverseFeatures = new ArrayList();

      // directional connections
      addMatchingFeatures(sourceSymbol, targetSymbol);
      // directionless connections
      addMatchingFeatures(targetSymbol, sourceSymbol);

      addGenericLinkFeatures();
   }

   private void addGenericLinkFeatures()
   {
      ModelType model = ModelUtils.findContainingModel(sourceSymbol);
      if(model == null)
      {
         return;
      }
      for (Iterator i = model.getLinkType().iterator(); i.hasNext();)
      {
         LinkTypeType link = (LinkTypeType) i.next();
         EClass sourceClass = (EClass) LINK_TYPE_MAPPING.get(link.getSourceClass());
         EClass targetClass = (EClass) LINK_TYPE_MAPPING.get(link.getTargetClass());
         if (sourceClass != null && targetClass != null
               && sourceClass.isInstance(sourceSymbol)
               && targetClass.isInstance(targetSymbol)
               && !existsLink(link, sourceSymbol, targetSymbol))
         {
            matchingFeatures.add(link);
         }
      }
   }

   private void addMatchingFeatures(INodeSymbol sourceSymbol, INodeSymbol targetSymbol)
   {
      boolean reverse = this.sourceSymbol != sourceSymbol;

      List outFeatures = sourceSymbol.getOutConnectionFeatures();
      List inFeatures = targetSymbol.getInConnectionFeatures();

      for (Iterator i = inFeatures.iterator(); i.hasNext();)
      {
         EStructuralFeature in = (EStructuralFeature) i.next();
         for (Iterator j = outFeatures.iterator(); j.hasNext();)
         {
            EStructuralFeature out = (EStructuralFeature) j.next();
            EClass type = (EClass) out.getEType();
            if (in.getEType().equals(type)
                  && !Arrays.asList(UNSUPPORTED_CONNECTION_TYPES).contains(type)
                  && !matchingFeatures.contains(type)
                  && (!reverse || Arrays.asList(DIRECTIONLESS_CONNECTION_TYPES).contains(
                        type)))
            {
               if (matches(out, type, sourceSymbol, targetSymbol))
               {
                  matchingFeatures.add(type);
                  if (reverse)
                  {
                     reverseFeatures.add(type);
                  }
               }
            }
         }
      }
   }

   private boolean matches(EStructuralFeature out, EClass type, INodeSymbol sourceSymbol,
         INodeSymbol targetSymbol)
   {
      if (PKG.getTriggersConnectionType() == type)
      {
         StartEventSymbol triggerSymbol = null;
         if(targetSymbol != null && targetSymbol instanceof StartEventSymbol)
         {
            triggerSymbol = (StartEventSymbol) targetSymbol;
         }
         else if(sourceSymbol != null && sourceSymbol instanceof StartEventSymbol)
         {
            triggerSymbol = (StartEventSymbol) sourceSymbol;
         }
         TriggerType trigger = triggerSymbol.getTrigger();
         if(trigger == null)
         {
            return false;
         }
         else
         {
            String triggerTypeId = trigger.getType().getId();
            if (!PredefinedConstants.MANUAL_TRIGGER.equals(triggerTypeId) && !PredefinedConstants.SCAN_TRIGGER.equals(triggerTypeId))
            {
               return false;
            }
            if (!triggerSymbol.getTriggersConnections().isEmpty())
            {
               return false;
            }
         }
      }

      if (PKG.getDataMappingConnectionType() == type)
      {
         // special constraint: data mappings must have source & target of different types
         if (PKG.getDataMappingConnectionType().equals(type))
         {
            if (sourceSymbol.eClass().equals(targetSymbol.eClass()))
            {
               return false;
            }
         }

         // Drawing of in data mappings on sub process with copy all data should be blocked.
         ActivitySymbolType activitySymbol = null;
         if(targetSymbol != null && targetSymbol instanceof ActivitySymbolType)
         {
            activitySymbol = (ActivitySymbolType) targetSymbol;
            ActivityType activity = activitySymbol.getActivity();
            if(ActivityImplementationType.SUBPROCESS_LITERAL.equals(activity.getImplementation()))
            {
               if(Boolean.valueOf(AttributeUtil.getAttributeValue(activity,
                     CarnotConstants.ACTIVITY_SUBPROCESS_COPY_ALL_DATA_ATT)).booleanValue())
               {
                  return false;
               }
            }
         }
      }
      else
      {
         if (PKG.getTransitionConnectionType() != type)
         {
            // with the exception of DataMappings, all other connections must be unique
            if (!isUnique(sourceSymbol.eGet(out), targetSymbol))
            {
               return false;
            }
            if (targetSymbol.getOutConnectionFeatures().contains(out))
            {
               if (!isUnique(targetSymbol.eGet(out), sourceSymbol))
               {
                  return false;
               }
            }
         }

         // special constraint: if an activity has a gateway, the respective
         // transition connections must start / end to the gateway
         if (PKG.getTransitionConnectionType().equals(type))
         {
            if (!isValidTransitionConnection(sourceSymbol, targetSymbol))
            {
               return false;
            }
         }

         // special constraint: circular organization hierarchies are not allowed
         if (PKG.getPartOfConnectionType().equals(type))
         {
            if (!isValidPartOfConnection(sourceSymbol, targetSymbol))
            {
               return false;
            }
         }
         else if (PKG.getWorksForConnectionType().equals(type))
         {
            if(!isValidOrganizationMemberConnection(sourceSymbol, targetSymbol))
            {
               return false;
            }
            IModelParticipant child = (IModelParticipant) extractModelElement(sourceSymbol);
            if(child != null && child.getId().equals(PredefinedConstants.ADMINISTRATOR_ROLE))
            {
               return false;
            }
         }
         else if (PKG.getTeamLeadConnectionType().equals(type))
         {
            OrganizationType parent = (OrganizationType) extractModelElement(targetSymbol);
            IModelParticipant child = (IModelParticipant) extractModelElement(sourceSymbol);
            if(parent == null || child == null)
            {
               return false;
            }
            if (parent.getTeamLead() == child)
            {
               return false;
            }
            if(child.getId().equals(PredefinedConstants.ADMINISTRATOR_ROLE))
            {
               return false;
            }

            return (null == parent.getTeamLead());
         }

         // special constraint: executedBy connections are allowed only if the
         // target activity is application activity without application.
         if (PKG.getExecutedByConnectionType().equals(type))
         {
            if (!isValidExecutedByConnection(targetSymbol))
            {
               return false;
            }
         }

         // special constraint (may be removed in the future)
         if (PKG.getPerformsConnectionType().equals(type))
         {
            if (!isValidPerformsConnection(targetSymbol))
            {
               return false;
            }
         }
      }
      return true;
   }

   private boolean existsLink(LinkTypeType link, INodeSymbol sourceSymbol,
         INodeSymbol targetSymbol)
   {
      for (Iterator j = link.getLinkInstances().iterator(); j.hasNext();)
      {
         GenericLinkConnectionType conn = (GenericLinkConnectionType) j.next();
         if (conn.getSourceNode() == sourceSymbol && conn.getTargetNode() == targetSymbol)
         {
            return true;
         }
      }
      return false;
   }

   private boolean isValidPerformsConnection(INodeSymbol targetSymbol)
   {
      ActivityType activity = (ActivityType) extractModelElement(targetSymbol);
/*      // special check if Container is a LaneSymbol
      if (activity != null && activity.getPerformer() != null &&
    		  targetSymbol.eContainer() instanceof LaneSymbol)
      {
         // and has a participant
         // and this participant is the same as the one from the container
         if(((LaneSymbol) targetSymbol.eContainer()).getParticipant() == null
         || ((LaneSymbol) targetSymbol.eContainer()).getParticipant().equals(activity.getPerformer()))
         {
            return true;
         }
         return false;
      }*/
      return activity != null;
   }

   private boolean isValidExecutedByConnection(INodeSymbol targetSymbol)
   {
      ActivityType activity = (ActivityType) extractModelElement(targetSymbol);
      return activity != null
            && (ActivityImplementationType.APPLICATION_LITERAL == activity
                  .getImplementation()) && activity.getApplication() == null;
   }

   private boolean isValidOrganizationMemberConnection(INodeSymbol sourceSymbol,
         INodeSymbol targetSymbol)
   {
      IModelParticipant member = (IModelParticipant) extractModelElement(sourceSymbol);
      OrganizationType parent = (OrganizationType) extractModelElement(targetSymbol);
      return (member != parent) && !isContained(member, parent);
   }

   private boolean isContained(IModelParticipant child, OrganizationType parent)
   {
      boolean isContained = false;
      for (Iterator i = parent.getParticipant().iterator(); i.hasNext();)
      {
         ParticipantType participant = (ParticipantType) i.next();
         if (participant.getParticipant() == child)
         {
            isContained = true;
         }
      }

      return isContained;
   }

   private boolean isValidPartOfConnection(INodeSymbol sourceSymbol,
         INodeSymbol targetSymbol)
   {
      OrganizationType child = (OrganizationType) extractModelElement(sourceSymbol);
      OrganizationType parent = (OrganizationType) extractModelElement(targetSymbol);
      return child != parent && !isChildOrganization(child, parent, new HashSet());
   }

   private boolean isChildOrganization(OrganizationType parent, OrganizationType child,
         HashSet visited)
   {
      if (visited.contains(parent))
      {
         return false;
      }
      visited.add(parent);
      for (Iterator i = parent.getParticipant().iterator(); i.hasNext();)
      {
         ParticipantType participant = (ParticipantType) i.next();
         IModelParticipant modelParticipant = participant.getParticipant();
         if (child == modelParticipant
               || modelParticipant instanceof OrganizationType
               && isChildOrganization((OrganizationType) modelParticipant, child, visited))
         {
            return true;
         }
      }
      return false;
   }

   private boolean isValidTransitionConnection(INodeSymbol sourceSymbol,
         INodeSymbol targetSymbol)
   {
      boolean valid = true;
      if (sourceSymbol instanceof ActivitySymbolType
            && hasGateway((ActivitySymbolType) sourceSymbol,
                  FlowControlType.SPLIT_LITERAL))
      {
         valid = false;
      }
      else if (targetSymbol instanceof ActivitySymbolType
            && hasGateway((ActivitySymbolType) targetSymbol, FlowControlType.JOIN_LITERAL))
      {
         valid = false;
      }
      /*
      // not valid if targetSymbol has already a connection from start event symbol
      else if (targetSymbol instanceof ActivitySymbolType
            && hasConnectionFromStartEvent((ActivitySymbolType) targetSymbol))
      {
         valid = false;
      }
      */
      else if (sourceSymbol instanceof GatewaySymbol
            && FlowControlType.JOIN_LITERAL == ((GatewaySymbol) sourceSymbol)
                  .getFlowKind()
            && targetSymbol != ((GatewaySymbol) sourceSymbol).getActivitySymbol())
      {
         valid = false;
      }
      else if (targetSymbol instanceof GatewaySymbol
            && FlowControlType.SPLIT_LITERAL == ((GatewaySymbol) targetSymbol)
                  .getFlowKind()
            && sourceSymbol != ((GatewaySymbol) targetSymbol).getActivitySymbol())
      {
         valid = false;
      }
      else if ((sourceSymbol instanceof StartEventSymbol)
            && (targetSymbol instanceof ActivitySymbolType || targetSymbol instanceof GatewaySymbol))
      {
         valid = ((StartEventSymbol) sourceSymbol).getOutTransitions().size() == 0;
         if (targetSymbol instanceof ActivitySymbolType
               && ((ActivitySymbolType) targetSymbol).getActivity() != null)
         {
            /*
            valid = ((ActivitySymbolType) targetSymbol).getActivity().getInTransitions()
                  .size() == 0;
                  */
         }
         else if (targetSymbol instanceof GatewaySymbol)
         {
            valid = JoinSplitType.XOR_LITERAL.equals(((GatewaySymbol) targetSymbol)
                  .getActivitySymbol().getActivity().getJoin());
         }
      }
      else if ((sourceSymbol instanceof ActivitySymbolType)
            && (targetSymbol instanceof EndEventSymbol))
      {
         valid = ((EndEventSymbol) targetSymbol).getInTransitions().size() == 0;
      }
      else
      {
         IModelElement sourceModelElement = extractModelElement(sourceSymbol);
         IModelElement targetModelElement = extractModelElement(targetSymbol);

         if ((sourceModelElement instanceof ActivityType)
               && (targetModelElement instanceof ActivityType))
         {
            // support (at most one) loop back to self
            ActivityType activity = (ActivityType) sourceModelElement;
            for (Iterator i = activity.getOutTransitions().iterator(); i.hasNext();)
            {
               TransitionType outTransition = (TransitionType) i.next();
               if (outTransition.getTo() == targetModelElement)
               {
                  // there already exists a transition between source and target
                  valid = false;
                  break;
               }
            }
         }
         else
         {
            // source and target for transitions must be activities
            valid = false;
         }
      }
      return valid;
   }

/*   private boolean hasConnectionFromStartEvent(ActivitySymbolType type)
   {
      List in = type.getInTransitions();
      for (int i = 0; i < in.size(); i++)
      {
         TransitionConnectionType connection = (TransitionConnectionType) in.get(i);
         if (connection.getSourceActivitySymbol() instanceof StartEventSymbol)
         {
            return true;
         }
      }
      return false;
   }*/

   private boolean hasGateway(ActivitySymbolType type, FlowControlType flow)
   {
      for (Iterator i = type.getGatewaySymbols().iterator(); i.hasNext();)
      {
         GatewaySymbol gateway = (GatewaySymbol) i.next();
         if (flow == gateway.getFlowKind())
         {
            return true;
         }
      }
      return false;
   }

   private boolean isUnique(Object outConnections, INodeSymbol toSymbol)
   {
      if (outConnections instanceof List)
      {
         for (Iterator i = ((List) outConnections).iterator(); i.hasNext();)
         {
            if (toSymbol == ((IConnectionSymbol) i.next()).getTargetNode())
            {
               return false;
            }
         }
         return true;
      }
      return toSymbol != ((IConnectionSymbol) outConnections).getTargetNode();
   }

   private String computeLabel(Object in)
   {
      if (in instanceof EClass)
      {
         String typeName = ((EClass) in).getName();
         return typeName.endsWith(CONNECTION_TYPE_SUFFIX) ? typeName.substring(0,
               typeName.length() - CONNECTION_TYPE_SUFFIX.length()) : typeName;
      }
      return in instanceof LinkTypeType ? ((LinkTypeType) in).getName() : String
            .valueOf(in);
   }
}