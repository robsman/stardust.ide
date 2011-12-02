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

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.ActivitySymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.anchors.TransitionConnectionAnchor;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.ui.PlatformUI;


public class SetActivityControlFlowCmd extends Command
{
   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private final ActivityType activity;
   private final FlowControlType flowControlAspect;
   private final JoinSplitType newBehavior;
   private CompoundCommand cmds;
   private WorkflowModelEditor editor;

   public SetActivityControlFlowCmd(WorkflowModelEditor editor, ActivityType activity,
         FlowControlType flowControlAspect, JoinSplitType newBehavior)
   {
      this.activity = activity;
      this.flowControlAspect = flowControlAspect;
      this.newBehavior = newBehavior;
      this.editor = editor;
   }

   public boolean canExecute()
   {
      this.cmds = new CompoundCommand();

      if ((!ActivityUtil.hasEndEvent(activity) && FlowControlType.SPLIT_LITERAL
            .equals(flowControlAspect))
            || (!ActivityUtil.hasStartEvent(activity) && FlowControlType.JOIN_LITERAL
                  .equals(flowControlAspect)))
      {

         if (!getCurrentBehavior(activity, flowControlAspect).equals(newBehavior))
         {
            WorkflowModelEditor editor = getWorkflowModelEditor();               
            DiagramEditorPage diagramEditorPage = (DiagramEditorPage) editor.getCurrentPage();
            DiagramType diagram = diagramEditorPage.getDiagram();
            List<ActivitySymbolType> activitySymbols = DiagramUtil.getSymbols(diagram, CarnotWorkflowModelPackage.eINSTANCE
                                       .getISymbolContainer_ActivitySymbol(), null);
            
            createSetJoinSplitTypeCmd(cmds, activity, flowControlAspect, newBehavior);
            for (Iterator<ActivitySymbolType> itr = activity.getActivitySymbols().iterator(); itr.hasNext();)
            {
               ActivitySymbolType symbol = itr.next();
                              
               if(activitySymbols.contains(symbol))
               {                  
                  if (JoinSplitType.NONE_LITERAL.equals(newBehavior))
                  {
                     createRemoveGatewayCmds(cmds, symbol, flowControlAspect);
                  }
                  else
                  {
                     createAddGatewayCmds(cmds, symbol, flowControlAspect);
                  }                  
               }
            }
         }
         else
         {
            cmds.add(UnexecutableCommand.INSTANCE);
         }
      }
      return super.canExecute() && cmds.canExecute();
   }

   private WorkflowModelEditor getWorkflowModelEditor()
   {
      return editor == null
         ? (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()
         : editor;
   }

   public void execute()
   {
      cmds.execute();
   }

   public void redo()
   {
      cmds.redo();
   }

   public void undo()
   {
      cmds.undo();
   }

   private void createSetJoinSplitTypeCmd(CompoundCommand cmds, ActivityType activity,
         FlowControlType flowType, JoinSplitType newBehavior)
   {
      EStructuralFeature feature;
      if (FlowControlType.JOIN_LITERAL.equals(flowType))
      {
         feature = PKG_CWM.getActivityType_Join();
      }
      else if (FlowControlType.SPLIT_LITERAL.equals(flowType))
      {
         feature = PKG_CWM.getActivityType_Split();
      }
      else
      {
         feature = null;
      }

      if (null != feature)
      {
         cmds.add(new SetValueCmd(activity, feature, newBehavior));
      }
   }

   private JoinSplitType getCurrentBehavior(ActivityType activity,
         FlowControlType flowType)
   {
      JoinSplitType result = FlowControlType.JOIN_LITERAL.equals(flowType) ? activity
            .getJoin() : FlowControlType.SPLIT_LITERAL.equals(flowType) ? activity
            .getSplit() : null;

      return (null != result) ? result : JoinSplitType.NONE_LITERAL;
   }

   private GatewaySymbol findGateway(ActivitySymbolType symbol, FlowControlType flowType)
   {
      List<GatewaySymbol> gatewaySymbols = symbol.getGatewaySymbols();
      for (GatewaySymbol gateway : gatewaySymbols)
      {
         if (flowType.equals(gateway.getFlowKind()))
         {
            return gateway;
         }
      }
      return null;
   }

   private void createAddGatewayCmds(CompoundCommand cmds, ActivitySymbolType activitySymbol,
         FlowControlType flowType)
   {
      GatewaySymbol gateway = findGateway(activitySymbol, flowType);

      if (gateway == null)
      {
         gateway = CarnotWorkflowModelFactory.eINSTANCE.createGatewaySymbol();
         // TODO: (fh) duplicate code with UpdateDiagramAction and possibly CreateActivityGraphAction
         // TODO: (fh) get the figure from the edit part
         ActivitySymbolFigure[] figure = new ActivitySymbolFigure[1];
         int symbolWidth = activitySymbol.getWidth();
         if (symbolWidth < 0)
         {
            symbolWidth = getActivitySymbolFigure(figure, activitySymbol.getActivity())
                  .getPreferredSize().width;
         }
         int symbolHeight = activitySymbol.getHeight();
         if (symbolHeight < 0)
         {
            symbolHeight = getActivitySymbolFigure(figure, activitySymbol.getActivity())
                  .getPreferredSize().height;
         }
         int size = 40;
         
         WorkflowModelEditor editor = getWorkflowModelEditor();               
         DiagramEditorPage diagramEditorPage = (DiagramEditorPage) editor.getCurrentPage();
         EditPart part = diagramEditorPage.findEditPart(activitySymbol);    

         // get size for new Symbol and set size
         Dimension newSize = SnapGridUtils.getSnapDimension(new Dimension(size, size), (AbstractGraphicalEditPart) part.getParent(), 2, false);         
         // add 2 points to snap all edges (should be 1 point but this works not)
         newSize.width += 2;
         newSize.height += 2;
         if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) part.getParent()) != null)
         {
            size = newSize.width;
         }
         
         int centerX = Math.round((2 * activitySymbol.getXPos() + symbolWidth) / 2); 
         int centerY = Math.round((2 * activitySymbol.getYPos() + symbolHeight) / 2);
         
         boolean isVertical = DiagramPlugin.isVerticalModelling(activitySymbol);
         
         int deltaX = isVertical ? 0 : Math.round(symbolWidth / 2) + size;
         int deltaY = isVertical ? Math.round(symbolHeight / 2) + size : 0;
         
         if (FlowControlType.JOIN_LITERAL.equals(flowType))
         {
            deltaX = -deltaX;
            deltaY = -deltaY;
         }
         
         int x = centerX + deltaX - (size / 2);
         int y = centerY + deltaY - (size / 2);
         
         
         Point newLocation = new Point(x, y);  
         Rectangle newBounds = new Rectangle(newLocation, newSize);
         
         // new location if snaptogrid is enabled
         Point setLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) part.getParent(), (AbstractNodeSymbolEditPart) part, 
               new PrecisionRectangle(newBounds), null, null);               
         
         gateway.setXPos(setLocation.x);
         gateway.setYPos(setLocation.y);
         
         gateway.setWidth(size);
         gateway.setHeight(size);

         gateway.setFlowKind(flowType);

         EObject container = activitySymbol.eContainer();
         if(container instanceof GroupSymbolType)
         {
            while(container instanceof GroupSymbolType)
            {
               container = container.eContainer();
            }
         }
         
         cmds.add(new SetValueCmd(container, PKG_CWM
               .getISymbolContainer_GatewaySymbol(), gateway));
         cmds.add(new SetElementOidCmd(gateway));

         cmds.add(new SetValueCmd(gateway, PKG_CWM.getGatewaySymbol_ActivitySymbol(),
               activitySymbol));

         if (FlowControlType.JOIN_LITERAL.equals(flowType))
         {
            createSetTargetSymbolCmds(cmds, activitySymbol.getInTransitions(), gateway);
            createAddPseudoTransitionCmds(cmds, getSymbolContainer(activitySymbol),
                  gateway, activitySymbol);
         }
         else if (FlowControlType.SPLIT_LITERAL.equals(flowType))
         {
            createSetSourceSymbolCmds(cmds, activitySymbol.getOutTransitions(), gateway);
            createAddPseudoTransitionCmds(cmds, getSymbolContainer(activitySymbol),
                  activitySymbol, gateway);
         }
         
         // add command to resize container if necessary
         if(part != null)
         {
            DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) activitySymbol);      
            
            if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL))
            {               
               EditPart host = part.getParent();
               while(!(host instanceof AbstractSwimlaneEditPart) && host != null)
               {
                  host = host.getParent();
               }
               
               if(host instanceof AbstractSwimlaneEditPart)
               { 
                  final AbstractSwimlaneEditPart hostEditPart = (AbstractSwimlaneEditPart) host;
                  
                  cmds.add(new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        return PoolLaneUtils.resizeLane(hostEditPart);
                     }
                  });
                  // here children of siblings must be ordered (if there are any)   
                  cmds.add(new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        return PoolLaneUtils.reorderSiblings(hostEditPart, null);
                     }
                  });
               }
            }
         }
      }
   }

   private ISymbolContainer getSymbolContainer(ActivitySymbolType symbol)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram(symbol);
      PoolSymbol defaultPool = DiagramUtil.getDefaultPool(diagram);     
      return defaultPool == null ? (ISymbolContainer) diagram : defaultPool;
   }

   private void createRemoveGatewayCmds(CompoundCommand cmds, ActivitySymbolType symbol,
         FlowControlType flowType)
   {
      ISymbolContainer diagram = getSymbolContainer(symbol);

      List<GatewaySymbol> gateways = symbol.getGatewaySymbols();      
      for (GatewaySymbol gateway : gateways)
      {
         if (flowType.equals(gateway.getFlowKind()))
         {
            if (FlowControlType.JOIN_LITERAL.equals(flowType))
            {
               createSetTargetSymbolCmds(cmds, gateway.getInTransitions(), symbol);
               removeTransitionConnections(cmds, diagram, gateway.getOutTransitions());
            }
            else if (FlowControlType.SPLIT_LITERAL.equals(flowType))
            {
               createSetSourceSymbolCmds(cmds, gateway.getOutTransitions(), symbol);
               removeTransitionConnections(cmds, diagram, gateway.getInTransitions());
            }

            cmds.add(new SetValueCmd(gateway, PKG_CWM.getGatewaySymbol_ActivitySymbol(),
                  null));
            cmds.add(new DeleteNodeSymbolCmd(gateway));
         }
      }
   }

   private ActivitySymbolFigure getActivitySymbolFigure(ActivitySymbolFigure[] figure,
         ActivityType activity)
   {
      if (figure[0] == null)
      {
         figure[0] = new ActivitySymbolFigure();
         // TODO rsauer applying dialog font?
         figure[0].setFont(JFaceResources.getFontRegistry().get(
               JFaceResources.DIALOG_FONT));
         if (activity != null)
         {
            if (activity.getName() != null)
            {
               figure[0].setName(activity.getName());
            }
            else if (activity.getId() != null)
            {
               figure[0].setName(activity.getId());
            }
            figure[0].setIconPath(getIconFactory().getIconFor(activity));
         }
         figure[0].validate();
      }
      return figure[0];
   }

   private IconFactory getIconFactory()
   {
      WorkflowModelEditor editor = getWorkflowModelEditor();
      return editor == null ? IconFactory.getDefault() : editor.getIconFactory();
   }

   private void createAddPseudoTransitionCmds(CompoundCommand cmds, ISymbolContainer diagram,
         IFlowObjectSymbol source, IFlowObjectSymbol target)
   {
      TransitionConnectionType newTS = CarnotWorkflowModelFactory.eINSTANCE
            .createTransitionConnectionType();
      newTS.setSourceAnchor(TransitionConnectionAnchor.CENTER);
      newTS.setTargetAnchor(TransitionConnectionAnchor.CENTER);

      cmds.add(new SetValueCmd(diagram, PKG_CWM
            .getISymbolContainer_TransitionConnection(), newTS));
      cmds.add(new SetElementOidCmd(newTS));
      cmds.add(new SetValueCmd(newTS, PKG_CWM
            .getTransitionConnectionType_SourceActivitySymbol(), source));
      cmds.add(new SetValueCmd(newTS, PKG_CWM
            .getTransitionConnectionType_TargetActivitySymbol(), target));
   }

   private void removeTransitionConnections(CompoundCommand cmds, ISymbolContainer diagram,
         List<TransitionConnectionType> transitions)
   {
      for (TransitionConnectionType ts : transitions)
      {
         cmds.add(new DeleteConnectionSymbolCmd(ts));
      }
   }

   private void createSetSourceSymbolCmds(CompoundCommand cmds, List<TransitionConnectionType> transitions,
         IFlowObjectSymbol symbol)
   {
      for (TransitionConnectionType ts : transitions)
      {
         cmds.add(new SetValueCmd(ts, PKG_CWM
               .getTransitionConnectionType_SourceActivitySymbol(), symbol));
      }
   }

   private void createSetTargetSymbolCmds(CompoundCommand cmds, List<TransitionConnectionType> transitions,
         IFlowObjectSymbol symbol)
   {
      for (TransitionConnectionType ts : transitions)
      {
         cmds.add(new SetValueCmd(ts, PKG_CWM
               .getTransitionConnectionType_TargetActivitySymbol(), symbol));
      }
   }
}