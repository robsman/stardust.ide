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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.properties.LaneParticipantCommandFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import ag.carnot.workflow.model.PredefinedConstants;

public class SetSymbolContainerCommand extends Command
{
   private ISymbolContainer symbolContainer;

   private IGraphicalObject symbol;

   private EStructuralFeature symbolContainmentFeature;

   private Long symbolElementOidBackup;

   private CommandUtils.ContainmentState containmentBackup;

   private Command dependentCmd;

   private EditPart targetEditPart;

   public ISymbolContainer getContainer()
   {
      return symbolContainer;
   }

   public void setContainer(ISymbolContainer container, EditPart targetEditPart)
   {
      this.symbolContainer = container;
      this.targetEditPart = targetEditPart;
   }

   public IGraphicalObject getSymbol()
   {
      return symbol;
   }

   public void setSymbol(IGraphicalObject symbol)
   {
      this.symbol = symbol;
   }

   public void execute()
   {
      if (null != symbol)
      {
         if (symbol instanceof INodeSymbol)
         {
            this.symbolContainmentFeature = CommandUtils.findContainmentFeature(
                  symbolContainer.getNodeContainingFeatures(), symbol);
         }
         else if (symbol instanceof IConnectionSymbol)
         {
            this.symbolContainmentFeature = CommandUtils.findContainmentFeature(
                  symbolContainer.getConnectionContainingFeatures(), symbol);
         }
         else
         {
            // TODO
            throw new RuntimeException(symbol
                  + Diagram_Messages.EX_isNeitherNodeNorConnectionSymbol);
         }

         this.symbolElementOidBackup = symbol.isSetElementOid() ? new Long(symbol
               .getElementOid()) : null;
         this.containmentBackup = CommandUtils.backupContainment(symbol);

         redo();
      }
   }

   public void redo()
   {
      if (null != symbol)
      {
         if ((null == symbolContainer) || (null == symbolContainmentFeature))
         {
            throw new RuntimeException(Diagram_Messages.EX_MissingNodeSymbolContainer
                  + symbol);
         }

         EList symbolContainment = (EList) symbolContainer.eGet(symbolContainmentFeature);
         if (!symbol.isSetElementOid())
         {
            symbol.setElementOid(ModelUtils.getElementOid(symbol, ModelUtils
                  .findContainingModel(symbolContainer)));
         }
         if (dependentCmd == null)
         {
            dependentCmd = checkActivityPerformer(symbolContainer);
            dependentCmd = dependentCmd.canExecute()
                  ? dependentCmd
                  : checkManualTriggerPerformer(symbolContainer);
            dependentCmd.execute();
         }
         else
         {
            dependentCmd.redo();
         }
         symbolContainment.add(symbol);
      }
   }

   public void undo()
   {
      if (null != symbol)
      {
         if ((null == symbolContainer) || (null == symbolContainmentFeature))
         {
            throw new RuntimeException(Diagram_Messages.EX_MissingSymbolContainer + symbol);
         }

         dependentCmd.undo();
         EList symbolContainment = (EList) symbolContainer.eGet(symbolContainmentFeature);
         symbolContainment.remove(symbol);
         if (null != symbolElementOidBackup)
         {
            symbol.setElementOid(symbolElementOidBackup.longValue());
         }
         else
         {
            symbol.unsetElementOid();
         }

         CommandUtils.redoContainment(containmentBackup);
      }
   }

   private Command checkActivityPerformer(EObject newContainer)
   {
      CompoundCommand command = new CompoundCommand();
      if (symbol instanceof ActivitySymbolType)
      {
         ActivitySymbolType activitySymbol = (ActivitySymbolType) symbol;
         ActivityType activity = (ActivityType) activitySymbol.getModelElement();

         IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
               .getActivePage().getActiveEditor();
         if (activity != null
               && (ActivityUtil.isInteractive(activity)
                     || ( !ActivityUtil.isSubprocessActivity(activity)
                           && activeEditor instanceof WorkflowModelEditor
                           && DiagramPlugin.isBusinessView((WorkflowModelEditor) activeEditor))))
         {
            // if not leaving a swimlane, keep the newPerformer
            IModelParticipant originalPerformer = activity.getPerformer();
            EObject originalContainer = activitySymbol.eContainer();
            IModelParticipant newPerformer = originalContainer instanceof ISwimlaneSymbol
                  ? null
                  : originalPerformer;
            if (newContainer instanceof ISwimlaneSymbol
                  && !DiagramUtil.isDefaultPool((ISwimlaneSymbol) newContainer)
                  && changePerformer())
            {
               ISwimlaneSymbol swimlane = (ISwimlaneSymbol) newContainer;
               while (swimlane.getParticipant() == null
                     && swimlane.eContainer() instanceof ISwimlaneSymbol)
               {
                  swimlane = (ISwimlaneSymbol) swimlane.eContainer();
               }
               newPerformer = swimlane.getParticipant();

               LaneParticipantCommandFactory.addSetPerformerCommands(command, activity,
                     newPerformer, originalPerformer, false, targetEditPart);               
            }
         }
      }
      return command;
   }

   public boolean changePerformer()
   {
      return true;
   }

   private Command checkManualTriggerPerformer(EObject newContainer)
   {
      CompoundCommand command = new CompoundCommand();
      if (symbol instanceof StartEventSymbol)
      {
         StartEventSymbol startEventSymbol = (StartEventSymbol) symbol;
         TriggerType trigger = (TriggerType) startEventSymbol.getModelElement();

         if (trigger != null
               && (PredefinedConstants.SCAN_TRIGGER.equals(trigger.getType().getId()) || PredefinedConstants.MANUAL_TRIGGER
                     .equals(trigger.getType().getId())))
         {
            IModelParticipant originalPerformer = null;
            AttributeType performerAtt = AttributeUtil.getAttribute(trigger,
                  PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT);
            if(performerAtt != null)
            {
               IdentifiableReference reference = performerAtt.getReference();
               // if not leaving a swimlane, keep the newPerformer
               if(reference != null)
               {
                  originalPerformer = (IModelParticipant) reference.getIdentifiable();
               }               
            }
            EObject originalContainer = startEventSymbol.eContainer();
            IModelParticipant newPerformer = originalContainer instanceof ISwimlaneSymbol
                  ? null
                  : originalPerformer;
            if (newContainer instanceof ISwimlaneSymbol && changePerformer())
            {
               ISwimlaneSymbol swimlane = (ISwimlaneSymbol) newContainer;
               while (swimlane.getParticipant() == null
                     && swimlane.eContainer() instanceof ISwimlaneSymbol)
               {
                  swimlane = (ISwimlaneSymbol) swimlane.eContainer();
               }
               newPerformer = swimlane.getParticipant() != null ? swimlane
                     .getParticipant() : newPerformer;
                     
               LaneParticipantCommandFactory.addSetPerformerCommands(command, trigger,
                     newPerformer, originalPerformer, performerAtt, false, targetEditPart);
            }
         }
      }
      return command;
   }
}