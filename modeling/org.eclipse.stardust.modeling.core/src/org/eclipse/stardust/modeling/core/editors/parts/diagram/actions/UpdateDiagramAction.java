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

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.ActivitySymbolFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateConnectionSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegateCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.swt.graphics.Font;


/**
 * @author fherinean
 * @version $Revision$
 */
public class UpdateDiagramAction extends SelectionAction
{
   public UpdateDiagramAction(WorkflowModelEditor editor)
   {
      super(editor);
      initUI();
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1 && getDiagram() != null
            && createUpdateDiagramCommand(getDiagram()).canExecute();
   }

   public void run()
   {
      execute(createUpdateDiagramCommand(getDiagram()));
   }

   public void runEmbedded(DelegateCommand compound)
   {
      compound.setDelegate(createUpdateDiagramCommand(getDiagram()));
   }

   protected Command createUpdateDiagramCommand(DiagramType diagram)
   {
      CompoundCommand command = new CompoundCommand();
      if (diagram != null)
      {
         List activitySymbols = getActivitySymbols(diagram);
         for (int i = 0; i < activitySymbols.size(); i++)
         {
            if (activitySymbols.get(i) instanceof ActivitySymbolType)
            {
               ActivitySymbolType symbol = (ActivitySymbolType) activitySymbols.get(i);
               ActivityType activity = symbol.getActivity();
               if (activity != null)
               {
                  if (activity.getJoin() != JoinSplitType.NONE_LITERAL
                        && !hasGateway(diagram, symbol, FlowControlType.JOIN_LITERAL))
                  {
                     createGatewayCommand(command, symbol, FlowControlType.JOIN_LITERAL);
                  }
                  if (activity.getSplit() != JoinSplitType.NONE_LITERAL
                        && !hasGateway(diagram, symbol, FlowControlType.SPLIT_LITERAL))
                  {
                     createGatewayCommand(command, symbol, FlowControlType.SPLIT_LITERAL);
                  }
               }
            }
         }
      }
      return command;
   }

   private List getActivitySymbols(DiagramType diagram)
   {
      return DiagramUtil.getSymbols(diagram, CarnotWorkflowModelPackage.eINSTANCE
            .getISymbolContainer_ActivitySymbol(), null);
   }

   private void createGatewayCommand(CompoundCommand command,
         final ActivitySymbolType symbol, final FlowControlType flowType)
   {
      final GatewaySymbol[] gateway = new GatewaySymbol[1];

      IdFactory id = new IdFactory("Gateway", Diagram_Messages.BASENAME_Gateway); //$NON-NLS-1$
      CreateSymbolCommand cmd = new CreateSymbolCommand(IDiagramCommand.PARENT, id,
            CarnotWorkflowModelPackage.eINSTANCE.getGatewaySymbol())
      {
         protected IModelElement createModelElement()
         {
            gateway[0] = (GatewaySymbol) super.createModelElement();
            gateway[0].setFlowKind(flowType);
            gateway[0].setActivitySymbol(symbol);
            return gateway[0];
         }
      };
      cmd.setParent(symbol.eContainer());

      ActivitySymbolFigure[] figure = new ActivitySymbolFigure[1];
      int symbolWidth = symbol.getWidth();
      if (symbolWidth <= 0)
      {
         symbolWidth = getActivitySymbolFigure(figure, symbol.getActivity())
               .getPreferredSize().width;
      }
      int symbolHeight = symbol.getHeight();
      if (symbolHeight <= 0)
      {
         symbolHeight = getActivitySymbolFigure(figure, symbol.getActivity())
               .getPreferredSize().height;
      }
      int size = 40;

      Dimension delta = getGatewayDelta(flowType, new Dimension(symbolWidth, symbolHeight), size);

      cmd.setLocation(new Rectangle((int) symbol.getXPos() + delta.width,
            (int) symbol.getYPos() + delta.height, size, size));

      command.add(cmd);

      ActivitySymbolType[] activity = {symbol};

      if (flowType.getValue() == FlowControlType.JOIN)
      {
         setTargetSymbol(command, symbol.getInTransitions(), gateway);
         createTransitionConnection(command, ModelUtils.findContainingDiagram(symbol),
               gateway, activity);
      }

      if (flowType.getValue() == FlowControlType.SPLIT)
      {
         setSourceSymbol(command, symbol.getOutTransitions(), gateway);
         createTransitionConnection(command, ModelUtils.findContainingDiagram(symbol),
               activity, gateway);
      }
      
      Command gatewayCommand = createGatewayUpdateCommand(gateway);
      if (gatewayCommand != null)
      {
         command.add(gatewayCommand);
      }
   }

   public Command createGatewayUpdateCommand(GatewaySymbol[] gateway)
   {
      return null;
   }

   protected Dimension getGatewayDelta(final FlowControlType flowType, Dimension activitySize,
         int gatewaySize)
   {
      int width = flowType.getValue() == FlowControlType.JOIN
                  ? -3 * gatewaySize / 2
                  : activitySize.width + gatewaySize / 2;
      int height = (activitySize.height - gatewaySize) / 2;

      return new Dimension(width, height);
   }

   private void createTransitionConnection(CompoundCommand command, DiagramType diagram,
         final IFlowObjectSymbol[] source, final IFlowObjectSymbol[] target)
   {
      IdFactory id = new IdFactory(Diagram_Messages.ID_TransitionConnection,
            Diagram_Messages.BASENAME_TransitionConnection);
      CreateConnectionSymbolCommand createCmd = new CreateConnectionSymbolCommand(id,
            CarnotWorkflowModelPackage.eINSTANCE.getTransitionConnectionType())
      {
         public INodeSymbol getSourceSymbol()
         {
            return source[0];
         }

         public INodeSymbol getTargetSymbol()
         {
            return target[0];
         }
      };
      createCmd.setParent(diagram);
      command.add(createCmd);
   }

   private void setSourceSymbol(CompoundCommand command, List transitions,
         final GatewaySymbol[] symbol)
   {
      for (int i = 0; i < transitions.size(); i++)
      {
         TransitionConnectionType ts = (TransitionConnectionType) transitions.get(i);
         command.add(new SetValueCmd(ts, CarnotWorkflowModelPackage.eINSTANCE
               .getTransitionConnectionType_SourceActivitySymbol(), null)
         {
            public Object getValue()
            {
               return symbol[0];
            }
         });
      }
   }

   private void setTargetSymbol(CompoundCommand command, List transitions,
         final GatewaySymbol[] symbol)
   {
      for (int i = 0; i < transitions.size(); i++)
      {
         TransitionConnectionType ts = (TransitionConnectionType) transitions.get(i);
         command.add(new SetValueCmd(ts, CarnotWorkflowModelPackage.eINSTANCE
               .getTransitionConnectionType_TargetActivitySymbol(), null)
         {
            public Object getValue()
            {
               return symbol[0];
            }
         });
      }
   }

   private ActivitySymbolFigure getActivitySymbolFigure(ActivitySymbolFigure[] figure,
         ActivityType activity)
   {
      if (figure[0] == null)
      {
         figure[0] = new ActivitySymbolFigure();
         figure[0].setFont(getFont());
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
            figure[0].setIconPath(((WorkflowModelEditor) getWorkbenchPart()).getIconFactory().getIconFor(activity));
         }
         figure[0].validate();
      }
      return figure[0];
   }

   private Font getFont()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
      DiagramEditPart editPart = (editor.getCurrentPage() instanceof DiagramEditorPage)
            ? (DiagramEditPart) ((DiagramEditorPage) editor.getCurrentPage())
                  .getGraphicalViewer().getRootEditPart().getChildren().get(0)
            : null;
      if (editPart != null)
      {
         return editPart.getFigure().getFont();
      }
      return editor.getSite().getShell().getFont();
   }

   private boolean hasGateway(DiagramType diagram, ActivitySymbolType target,
         FlowControlType flow)
   {
      List gateways = DiagramUtil.getSymbols(diagram,
            CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GatewaySymbol(),
            null);
      for (int i = 0; i < gateways.size(); i++)
      {
         GatewaySymbol gateway = (GatewaySymbol) gateways.get(i);
         if (flow.equals(gateway.getFlowKind())
               && gateway.getActivitySymbol() != null
               && gateway.getActivitySymbol().equals(target))
         {
            return true;
         }
      }
      return false;
   }

   private DiagramType getDiagram()
   {
      Object selection = getSelectedObjects().get(0);
      if (!(selection instanceof EditPart))
      {
         return null;
      }
      Object element = ((EditPart) selection).getModel();
      if (element instanceof DiagramType)
      {
         return (DiagramType) element;
      }
      return null;
   }

   protected void initUI()
   {
      super.init();
      setId(DiagramActionConstants.DIAGRAM_UPDATE);
      setText(Diagram_Messages.TXT_UpdateDiagram);
   }
}