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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.Pair;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.dnd.IGatewayLocator;
import org.eclipse.stardust.modeling.core.editors.dnd.ModelElementSymbolCreationFactory;
import org.eclipse.stardust.modeling.core.editors.dnd.SymbolCreationFactory;
import org.eclipse.stardust.modeling.core.editors.figures.ActivitySymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.GatewayFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateConnectionSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class CreateActivityGraphAction extends SelectionAction
{
   // the real targetEP if bacardi mode (lane or pool)
   private EditPart targetEditPart = null;
   
   public static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private final WorkflowModelEditor editor;

   protected ActivityType startActivity;

   protected DiagramType diagram;

   private Map activityBlocks = new HashMap();

   private Map/* <ActivityType, Map<IModelElement, Pair<Point, Point>>> */figureLocations = new HashMap();

   private List/* <List<ActivityType>> */rows = new ArrayList();

   private Map/* <ActivityType, ActivityLayoutData> */layoutSpecs = new HashMap();

   private Set/* <ActivityType> */visitedActivities = new HashSet();

   private boolean isVertical;

   public CreateActivityGraphAction(WorkflowModelEditor part)
   {
      super(part);

      setId(DiagramActionConstants.CREATE_ACTIVITY_GRAPH);
      setText(Diagram_Messages.LB_ACTION_CreateActivityGraph);
      setDescription(Diagram_Messages.MSG_ACTION_CreateActivityGraph);

      this.editor = part;
   }

   protected boolean calculateEnabled()
   {
      targetEditPart = null;
      boolean isDiagram = (!getSelectedObjects().isEmpty())
            && getSelectedObjects().get(0) instanceof DiagramRootEditPart;
      boolean isProcessDiagram = isDiagram
            ? ((DiagramType) ((DiagramRootEditPart) getSelectedObjects().get(0))
                  .getContents().getModel()).eContainer() instanceof ProcessDefinitionType
            : false;
      // find real target EP, if bpmn mode
      if (isDiagram && isProcessDiagram)
      {         
         DiagramEditPart diagramEditPart = (DiagramEditPart) ((DiagramRootEditPart) getSelectedObjects()
                                             .get(0)).getContents();
         DiagramType diagram = (DiagramType) diagramEditPart.getModel();
         ProcessDefinitionType process = (ProcessDefinitionType) diagram.eContainer();
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(process);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            return false;
         }         
         
         DiagramModeType diagramMode = diagram.getMode();
         if(diagramMode.equals(DiagramModeType.MODE_450_LITERAL))
         {
            targetEditPart = PoolLaneUtils.findTargetEditPart((WorkflowModelEditor) getWorkbenchPart());
            if(!(targetEditPart instanceof AbstractSwimlaneEditPart))
            {
               return false;
            }
            // not on collapsed lane
            if(targetEditPart instanceof LaneEditPart 
                  && ((LaneEditPart) targetEditPart).getLaneFigure().isCollapsed())
            {
               return false;
            }
            if(PoolLaneUtils.containsLanes(targetEditPart))
            {
               return false;
            }
            if(process.getActivity().isEmpty())
            {
               return false;
            }
            return true;
         }
         else
         {
            if(process.getActivity().isEmpty())
            {
               return false;
            }
            return true;
         }
      }
      return isDiagram && isProcessDiagram;
   }

   public void run()
   {
      CompoundCommand cmd = new CompoundCommand();
      DiagramEditPart diagramEditPart = (DiagramEditPart) ((DiagramRootEditPart) getSelectedObjects()
            .get(0)).getContents();
      reset((DiagramType) diagramEditPart.getModel());
      Dialog dialog = createDialog();
      if ((dialog.open() == Window.OK) && (null != startActivity))
      {
         populateRows(0, startActivity);
         layoutSpecs.put(startActivity, new LayoutSpec(null, 0, 0));
         populateColumns(0, startActivity);

         if (!isVertical)
         {
            reverseGrid();
         }

         createSymbols(cmd, diagramEditPart.getRoot().getViewer());

         // in bpmn, if swimlane, may resize lane         
         if(targetEditPart != null)
         {
            final EditPart useEP = targetEditPart;
            cmd.add(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.resizeLane((AbstractSwimlaneEditPart) useEP);
               }
            });
            // here children of siblings must be ordered (if there are any)   
            cmd.add(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.reorderSiblings(useEP, null);
               }
            });
         }         
         execute(cmd);
         PoolLaneUtils.refreshLaneContent();
      }
   }

   // can change in SnapCenterToGrid
   private int getPxPerUnit()
   {
      return SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
   }
   
   private void reverseGrid()
   {
      List newRows = new ArrayList();
      HashMap newLayouts = new HashMap();
      for (int i = 0; i < rows.size(); i++)
      {
         List elements = (List) rows.get(i);
         for (int j = 0; j < elements.size(); j++)
         {
            ActivityType activity = (ActivityType) elements.get(j);
            int col = getColumnPlacement(activity);
            LayoutSpec spec = (LayoutSpec) layoutSpecs.get(activity);
            newLayouts.put(activity, new LayoutSpec(spec.predecessor, col,
                  spec.predecessor == null ? 0 : 1));
            addElement(newRows, activity, col);
         }
      }
      rows = newRows;
      layoutSpecs = newLayouts;
   }

   private void addElement(List rows, ActivityType activity, int rowId)
   {
      while (rows.size() <= rowId)
      {
         rows.add(new ArrayList());
      }

      List row = (List) rows.get(rowId);
      row.add(activity);
   }

   private void reset(DiagramType diagram)
   {
      this.diagram = diagram;

      startActivity = null;

      rows.clear();
      layoutSpecs.clear();
      activityBlocks.clear();

      visitedActivities.clear();

      isVertical = DiagramPlugin.isVerticalModelling(diagram);
   }

   private void createSymbols(CompoundCommand cmd, final EditPartViewer viewer)
   {
      CompoundCommand cmdFixDiagramLayout = new CompoundCommand();

      // (fh) unfortunately we have to traverse the layout twice, once for computing
      // the grid size, another for computing layout sizes

      // compute grid size
      int rowCount = rows.size();
      int columnCount = 0;
      for (int i = 0; i < rowCount; i++)
      {
         ArrayList row = (ArrayList) rows.get(i);
         for (int j = 0; j < row.size(); j++)
         {
            int column = getColumnPlacement((ActivityType) row.get(j));
            columnCount = Math.max(columnCount, column + 1);
         }
      }

      // compute layout sizes
      int[] widths = new int[columnCount];
      int[] heights = new int[rowCount];
      for (int i = 0; i < rowCount; i++)
      {
         ArrayList row = (ArrayList) rows.get(i);
         for (int j = 0; j < row.size(); j++)
         {
            ActivityType activity = (ActivityType) row.get(j);
            int column = getColumnPlacement(activity);
            ActivityBlock block = (ActivityBlock) activityBlocks.get(activity);
            heights[i] = Math.max(heights[i], block.getHeight(isVertical) * getPxPerUnit());
            widths[column] = Math.max(widths[column], block.getWidth(isVertical)
                  * getPxPerUnit());
         }
      }

      // cumulate sizes for easier computation
      for (int i = 1; i < heights.length; i++)
      {
         heights[i] += heights[i - 1];
      }
      for (int i = 1; i < widths.length; i++)
      {
         widths[i] += widths[i - 1];
      }

      for (int i = 0; i < rows.size(); i++)
      {
         ArrayList row = (ArrayList) rows.get(i);
         for (int j = 0; j < row.size(); j++)
         {
            final ActivityType activity = (ActivityType) row.get(j);
            final ActivityBlock block = (ActivityBlock) activityBlocks.get(activity);
            int column = getColumnPlacement(activity);

            figureLocations.put(activity, new HashMap());

            int xleft = column == 0 ? 0 : widths[column - 1];
            int ytop = i == 0 ? 0 : heights[i - 1];

            int xright = widths[column];
            int ybottom = heights[i];

            int activityXCenter = (xleft + xright) / 2;
            int activityYCenter = (ytop + ybottom) / 2;

            Point centerActivity = new Point(activityXCenter, activityYCenter);

            Point locData = new Point(isVertical ? xleft + getPxPerUnit()
                  * ActivityBlock.dxData / 2 : activityXCenter - getPxPerUnit()
                  * (block.data.size() - 1) * ActivityBlock.dxData / 2, isVertical
                  ? activityYCenter - getPxPerUnit() * (block.data.size() - 1)
                        * ActivityBlock.dyData / 2
                  : ytop + getPxPerUnit() * ActivityBlock.dyData / 2);

            locData = renderDataSymbol(cmd, activity, centerActivity, block,
                  DirectionType.IN_LITERAL, locData);
            locData = renderDataSymbol(cmd, activity, centerActivity, block,
                  DirectionType.INOUT_LITERAL, locData);
            locData = renderDataSymbol(cmd, activity, centerActivity, block,
                  DirectionType.OUT_LITERAL, locData);

            // the following code assumes there is MAX 1 performer and MAX 1 application
            Point locPerformer = new Point(isVertical ? xright - getPxPerUnit()
                  * ActivityBlock.dxParticipant / 2 : activityXCenter - getPxPerUnit()
                  * (block.nApplications) * ActivityBlock.dxParticipant / 2, isVertical
                  ? activityYCenter - getPxPerUnit() * (block.nApplications)
                        * ActivityBlock.dyParticipant / 2
                  : ybottom - getPxPerUnit() * ActivityBlock.dyParticipant / 2);

            Point locApplication = new Point(isVertical ? xright - getPxPerUnit()
                  * ActivityBlock.dxApplication / 2 : activityXCenter + getPxPerUnit()
                  * (block.nParticipants) * ActivityBlock.dxApplication / 2, isVertical
                  ? activityYCenter + getPxPerUnit() * (block.nParticipants)
                        * ActivityBlock.dyApplication / 2
                  : ybottom - getPxPerUnit() * ActivityBlock.dyApplication / 2);

            if (0 < block.nParticipants)
            {
               createSymbol(cmd, activity.getPerformer(), centerActivity, null);
               Map centers = (Map) figureLocations.get(activity);
               centers.put(activity.getPerformer(),
                     new Pair(centerActivity, locPerformer));
            }

            if (0 < block.nApplications)
            {
               createSymbol(cmd, activity.getApplication(), centerActivity, null);
               Map centers = (Map) figureLocations.get(activity);
               centers.put(activity.getApplication(), new Pair(centerActivity,
                     locApplication));
            }

            Map centers = (Map) figureLocations.get(activity);
            centers.put(activity, new Pair(centerActivity, centerActivity));
            createSymbol(cmd, activity, centerActivity, new IGatewayLocator()
            {
               public Dimension getGatewayLocation(FlowControlType gatewayKind,
                     Dimension activitySize, int gatewaySize)
               {
                  int width = (activitySize.width - gatewaySize) / 2;
                  int height = 0;

                  if (isVertical)
                  {
                     if (FlowControlType.JOIN_LITERAL.equals(gatewayKind))
                     {
                        height = -ActivityBlock.dyJoinGateway * getPxPerUnit();
                     }
                     else if (FlowControlType.SPLIT_LITERAL.equals(gatewayKind))
                     {
                        height = (ActivityBlock.dySplitGateway) * getPxPerUnit();
                     }
                  }
                  else
                  {
                     if (FlowControlType.JOIN_LITERAL.equals(gatewayKind))
                     {
                        width -= ActivityBlock.dyJoinGateway * getPxPerUnit();
                     }
                     else if (FlowControlType.SPLIT_LITERAL.equals(gatewayKind))
                     {
                        width += (ActivityBlock.dySplitGateway) * getPxPerUnit();
                     }
                  }

                  return new Dimension(width, height);
               }
            });

            cmdFixDiagramLayout.add(new Command()
            {
               private boolean firstRun = true;

               private CompoundCommand cmdReloadConnections = new CompoundCommand();

               public void execute()
               {
                  // adjusting position of new activity
                  for (Iterator i = activity.getActivitySymbols().iterator(); i.hasNext();)
                  {
                     ActivitySymbolType symActivity = (ActivitySymbolType) i.next();
                     if (symActivity.eContainer() == diagram)
                     {
                        GraphicalEditPart epActivity = ((GraphicalEditPart) viewer
                              .getEditPartRegistry().get(symActivity));

                        if (firstRun)
                        {
                           ReloadConnectionsRequest rqReloadConnections = new ReloadConnectionsRequest();
                           Command cmdReloadActivityConnections = epActivity
                                 .getCommand(rqReloadConnections);
                           addReloadConnectionsCommand(symActivity,
                                 cmdReloadActivityConnections, block);
                        }

                        Map centers = (Map) figureLocations.get(activity);

                        Point locActivity = (Point) ((Pair) centers.get(activity))
                              .getSecond();
                        ActivitySymbolFigure figActivity = (ActivitySymbolFigure) epActivity
                              .getFigure();

                        // TODO: (fh) use the common code in
                        // SymbolContainerLayoutEditPolicy
                        // snap activity center to grid
                        Dimension szActivity = getPreferredSize(figActivity);
                        symActivity.setXPos(locActivity.x - szActivity.width / 2);
                        symActivity.setYPos(locActivity.y - szActivity.height / 2);

                        for (Iterator itrGateways = symActivity.getGatewaySymbols()
                              .iterator(); itrGateways.hasNext();)
                        {
                           GatewaySymbol symGateway = (GatewaySymbol) itrGateways.next();
                           GatewayFigure figGateway = (GatewayFigure) ((GraphicalEditPart) viewer
                                 .getEditPartRegistry().get(symGateway)).getFigure();

                           Dimension szGateway = figGateway.getPreferredSize();

                           double gatewayXPos = symActivity.getXPos() + szActivity.width
                                 / 2;
                           double gatewayYPos = symActivity.getYPos() + szActivity.height
                                 / 2;
                           if (FlowControlType.JOIN_LITERAL.equals(symGateway
                                 .getFlowKind()))
                           {
                              if (isVertical)
                              {
                                 gatewayYPos -= getPxPerUnit() * ActivityBlock.dyJoinGateway;
                              }
                              else
                              {
                                 gatewayXPos -= getPxPerUnit() * ActivityBlock.dxJoinGateway
                                       + szActivity.width / 2 - szActivity.height / 2;
                              }
                           }
                           else if (FlowControlType.SPLIT_LITERAL.equals(symGateway
                                 .getFlowKind()))
                           {
                              if (isVertical)
                              {
                                 gatewayYPos += getPxPerUnit() * ActivityBlock.dyJoinGateway;
                              }
                              else
                              {
                                 gatewayXPos += getPxPerUnit() * ActivityBlock.dxJoinGateway
                                       + szActivity.width / 2 - szActivity.height / 2;
                              }
                           }
                           gatewayXPos -= szGateway.width / 2;
                           gatewayYPos -= szGateway.height / 2;
                           symGateway.setXPos((int) gatewayXPos);
                           symGateway.setYPos((int) gatewayYPos);

                           // adjusting anchors of in and out transitions
                           // setInTransitionAnchors(symGateway);
                           // setOutTransitionAnchors(symGateway, layout);
                        }

                        // adjusting anchors of in and out transitions
                        // setInTransitionAnchors(symActivity);
                        // setOutTransitionAnchors(symActivity, layout);

                        for (Iterator itrNodes = centers.keySet().iterator(); itrNodes
                              .hasNext();)
                        {
                           IIdentifiableElement node = (IIdentifiableElement) itrNodes
                                 .next();

                           Pair mrkLocation = (Pair) centers.get(node);
                           Point oldLocation = (Point) mrkLocation.getFirst();
                           Point newLocation = (Point) mrkLocation.getSecond();

                           INodeSymbol symNode = null;
                           if (node instanceof DataType)
                           {
                              for (Iterator itrSymbols = ((DataType) node)
                                    .getDataSymbols().iterator(); itrSymbols.hasNext();)
                              {
                                 DataSymbolType symData = (DataSymbolType) itrSymbols
                                       .next();
                                 if ((symData.eContainer() == diagram)
                                       && (oldLocation.x == symData.getXPos())
                                       && oldLocation.y == symData.getYPos())
                                 {

                                    symNode = symData;
                                    break;
                                 }
                              }
                           }
                           else if (node instanceof IModelParticipant)
                           {
                              for (Iterator itrSymbols = ((IModelParticipant) node)
                                    .getSymbols().iterator(); itrSymbols.hasNext();)
                              {
                                 INodeSymbol symParticipant = (INodeSymbol) itrSymbols
                                       .next();
                                 if ((symParticipant.eContainer() == diagram)
                                       && (oldLocation.x == symParticipant.getXPos())
                                       && oldLocation.y == symParticipant.getYPos())
                                 {

                                    symNode = symParticipant;
                                    break;
                                 }
                              }
                           }
                           else if (node instanceof ApplicationType)
                           {
                              for (Iterator itrSymbols = ((ApplicationType) node)
                                    .getApplicationSymbols().iterator(); itrSymbols
                                    .hasNext();)
                              {
                                 ApplicationSymbolType symApp = (ApplicationSymbolType) itrSymbols
                                       .next();
                                 if ((symApp.eContainer() == diagram)
                                       && (oldLocation.x == symApp.getXPos())
                                       && oldLocation.y == symApp.getYPos())
                                 {

                                    symNode = symApp;
                                    break;
                                 }
                              }
                           }

                           if (null != symNode)
                           {
                              GraphicalEditPart epNode = (GraphicalEditPart) viewer
                                    .getEditPartRegistry().get(symNode);
                              if (null != epNode)
                              {
                                 IFigure figNode = epNode.getFigure();

                                 Dimension szNode = figNode.getPreferredSize();

                                 symNode.setXPos(newLocation.x - szNode.width / 2);
                                 symNode.setYPos(newLocation.y - szNode.height / 2);
                              }
                           }
                        }
                     }
                  }

                  if (firstRun)
                  {
                     cmdReloadConnections.execute();
                  }
                  else
                  {
                     cmdReloadConnections.redo();
                  }
                  firstRun = false;
               }

               public void undo()
               {
                  cmdReloadConnections.undo();

                  super.undo();
               }

               private void addReloadConnectionsCommand(ActivitySymbolType symActivity,
                     Command cmd, ActivityBlock block)
               {
                  // TODO Auto-generated method stub
                  if (cmd instanceof CompoundCommand)
                  {
                     for (Iterator i = ((CompoundCommand) cmd).getCommands().iterator(); i
                           .hasNext();)
                     {
                        addReloadConnectionsCommand(symActivity, (Command) i.next(),
                              block);
                     }
                  }
                  else if (cmd instanceof CreateConnectionSymbolCommand)
                  {
                     CreateConnectionSymbolCommand cmdCreateSymbol = (CreateConnectionSymbolCommand) cmd;
                     INodeSymbol symOther;
                     if (cmdCreateSymbol.getSourceSymbol() == symActivity)
                     {
                        symOther = cmdCreateSymbol.getTargetSymbol();
                     }
                     else if (cmdCreateSymbol.getTargetSymbol() == symActivity)
                     {
                        symOther = cmdCreateSymbol.getSourceSymbol();
                     }
                     else
                     {
                        symOther = null;
                     }

                     // filter for intended connections
                     if ((symOther instanceof DataSymbolType)
                           && block.data.containsKey(((DataSymbolType) symOther)
                                 .getData()))
                     {
                        cmdReloadConnections.add(cmd);
                     }
                     else if ((symOther instanceof IModelParticipantSymbol)
                           && (activity.getPerformer() == ((IModelParticipantSymbol) symOther)
                                 .getModelElement()))
                     {
                        cmdReloadConnections.add(cmd);
                     }
                     else if ((symOther instanceof ApplicationSymbolType)
                           && (activity.getApplication() == ((ApplicationSymbolType) symOther)
                                 .getApplication()))
                     {
                        cmdReloadConnections.add(cmd);
                     }
                  }
               }
            });
         }
      }

      cmd.add(cmdFixDiagramLayout.unwrap());
   }

   private Dimension getPreferredSize(Figure fig)
   {
      Dimension dim = fig.getPreferredSize();
      if (dim.width / 2 * 2 == dim.width)
      {
         dim.width++;
      }
      if (dim.height / 2 * 2 == dim.height)
      {
         dim.height++;
      }
      // if snap2grid is enabled 
      if(targetEditPart != null)
      {
         dim = SnapGridUtils.getSnapDimension(dim, (AbstractGraphicalEditPart) targetEditPart, 2, true);         
      }      
      return dim;
   }

   /*
    * private void setInTransitionAnchors(IFlowObjectSymbol symGateway) { int thisCol =
    * getColumnPlacement(findActivitySymbol(symGateway).getActivity());
    * 
    * for (Iterator itrTransitions = symGateway.getInTransitions().iterator();
    * itrTransitions.hasNext();) { TransitionConnectionType transition =
    * (TransitionConnectionType) itrTransitions.next();
    * 
    * IFlowObjectSymbol srcSymbol = transition.getSourceActivitySymbol();
    * ActivitySymbolType srcActivity = findActivitySymbol(srcSymbol);
    * 
    * if (null != srcActivity) { int colActivity =
    * getColumnPlacement(srcActivity.getActivity());
    * 
    * transition.setTargetAnchor((thisCol >= colActivity) ? TransitionConnectionAnchor.TOP :
    * TransitionConnectionAnchor.RIGHT); } } }
    */

   /*
    * private ActivitySymbolType findActivitySymbol(IFlowObjectSymbol srcSymbol) {
    * ActivitySymbolType srcActivity = null; if (srcSymbol instanceof ActivitySymbolType) {
    * srcActivity = (ActivitySymbolType) srcSymbol; } else if (srcSymbol instanceof
    * GatewaySymbol) { srcActivity = ((GatewaySymbol) srcSymbol).getActivitySymbol(); }
    * return srcActivity; }
    */

   private int getColumnPlacement(ActivityType srcActivity)
   {
      LayoutSpec lSpec = (LayoutSpec) layoutSpecs.get(srcActivity);
      int col = lSpec.idxColumn;
      while (null != lSpec.predecessor)
      {
         lSpec = (LayoutSpec) layoutSpecs.get(lSpec.predecessor);
         col += lSpec.idxColumn;
      }

      return col;
   }

   /*
    * private void setOutTransitionAnchors(IFlowObjectSymbol symGateway, LayoutSpec
    * layout) { boolean firstOut = true; for (Iterator itrTransitions =
    * symGateway.getOutTransitions().iterator(); itrTransitions.hasNext();) {
    * TransitionConnectionType transition = (TransitionConnectionType)
    * itrTransitions.next();
    * 
    * IFlowObjectSymbol targetSymbol = transition.getTargetActivitySymbol(); ActivityType
    * targetActivity = (targetSymbol instanceof ActivitySymbolType) ?
    * ((ActivitySymbolType) targetSymbol).getActivity() : (targetSymbol instanceof
    * GatewaySymbol) ? ((GatewaySymbol) targetSymbol).getActivitySymbol().getActivity() :
    * null; LayoutSpec targetSpec = (LayoutSpec) layoutSpecs.get(targetActivity); if
    * ((null != targetSpec) && targetSpec.idxRow < layout.idxRow) {
    * transition.setSourceAnchor(TransitionConnectionAnchor.RIGHT); } else {
    * transition.setSourceAnchor(firstOut ? TransitionConnectionAnchor.BOTTOM :
    * TransitionConnectionAnchor.RIGHT); } firstOut = false; } }
    */

   private Point renderDataSymbol(CompoundCommand cmd, ActivityType activity,
         Point locActivity, ActivityBlock block, DirectionType direction, Point locData)
   {
      for (Iterator itrData = block.data.keySet().iterator(); itrData.hasNext();)
      {
         DataType data = (DataType) itrData.next();
         DirectionType dataDirection = (DirectionType) block.data.get(data);
         if (direction.equals(dataDirection))
         {
            createSymbol(cmd, data, locActivity, null);

            Map centers = (Map) figureLocations.get(activity);
            centers.put(data, new Pair(locActivity, locData));

            locData = locData.getTranslated(isVertical ? 0 : getPxPerUnit()
                  * ActivityBlock.dxData, isVertical
                  ? getPxPerUnit() * ActivityBlock.dyData
                  : 0);
         }
      }
      return locData;
   }
   
   private void createSymbol(CompoundCommand cmd, IIdentifiableModelElement element,
         Point point, IGatewayLocator locator)
   {
      SymbolCreationFactory factory = ModelElementSymbolCreationFactory.getFactory(
            element, locator);
      // TODO: snap2grid location
      factory.setLocation(point);
      factory.setEditor(editor);
      factory.setEditDomain(editor.getEditDomain());
      
      // can be lane or pool     
      if(targetEditPart != null)
      {         
         factory.setSymbolContainer((ISymbolContainer) targetEditPart.getModel());
      }
      else
      {
         factory.setSymbolContainer(diagram);         
      }
      factory.setTransferredModelElement(element);
      cmd.add((Command) factory.getNewObject());
   }

   private Dialog createDialog()
   {
      return new Dialog(getWorkbenchPart().getSite().getShell())
      {
         Button okButton;
         
         protected Control createButtonBar(Composite parent)
         {
            Control control =  super.createButtonBar(parent);
            okButton = getButton(OK);
            okButton.setEnabled(false);
            
            return control;
         }
         
         protected Control createDialogArea(Composite parent)
         {
            getShell().setText(getText());
            Composite composite = (Composite) super.createDialogArea(parent);
            GridData gd = (GridData) composite.getLayoutData();
            gd.minimumHeight = 250;
            gd.minimumWidth = 200;
            FormBuilder.createLabel(composite, getDescription());
            ListViewer viewer = new ListViewer(FormBuilder.createList(composite));
            viewer.setLabelProvider(new LabelProvider()
            {
               public Image getImage(Object element)
               {
                  return DiagramPlugin.getImage(editor.getIconFactory().getIconFor((IIdentifiableModelElement) element));
               }

               public String getText(Object element)
               {
                  return ((IIdentifiableModelElement) element).getName();
               }

            });
            viewer.addSelectionChangedListener(new ISelectionChangedListener()
            {
               public void selectionChanged(SelectionChangedEvent event)
               {
                  if (event.getSelection() instanceof IStructuredSelection)
                  {
                     startActivity = (ActivityType) ((IStructuredSelection) event
                           .getSelection()).getFirstElement();
                     if(startActivity != null)
                     {
                        okButton.setEnabled(true);
                     }
                  }
               }
            });

            viewer
                  .add(getStartActivities(
                        ((ProcessDefinitionType) diagram.eContainer()).getActivity())
                        .toArray());
            
            return composite;
         }

         private List getStartActivities(EList activities)
         {
            List startActivities = new ArrayList();
            for (Iterator iter = activities.iterator(); iter.hasNext();)
            {
               ActivityType next = (ActivityType) iter.next();
               if (next.getInTransitions().isEmpty())
               {
                  startActivities.add(next);
               }
            }
            return startActivities;
         }
      };
   }

   private void populateRows(int rowId, ActivityType activity)
   {
      visitedActivities.add(activity);

      if (!activityBlocks.containsKey(activity))
      {
         activityBlocks.put(activity, new ActivityBlock(activity));
      }

      addElement(rows, activity, rowId);

      int nextRowId = rowId + 1;

      for (Iterator i = ActivityUtil.getOutActivities(activity).iterator(); i.hasNext();)
      {
         ActivityType followActivity = (ActivityType) i.next();

         if (!visitedActivities.contains(followActivity))
         {
            populateRows(nextRowId, followActivity);
         }
      }
   }

   private List/* <int> */populateColumns(int idxRow, ActivityType activity)
   {
      List spread = new ArrayList();
      spread.add(new Integer(1));

      if (rows.size() > (idxRow + 1))
      {
         List/* <ActivityType> */nextRow = (List) rows.get(idxRow + 1);

         for (Iterator i = ActivityUtil.getOutActivities(activity).iterator(); i
               .hasNext();)
         {
            ActivityType followActivity = (ActivityType) i.next();

            if (nextRow.contains(followActivity)
                  && !layoutSpecs.containsKey(followActivity))
            {
               List followSpread = populateColumns(idxRow + 1, followActivity);

               // find maximum spread in range of follow spread
               int maxSpread = 0;
               for (int rowId = 0; rowId < followSpread.size(); rowId++)
               {
                  if (spread.size() > (1 + rowId))
                  {
                     maxSpread = Math.max(maxSpread, ((Integer) spread.get(1 + rowId))
                           .intValue());
                  }
               }

               int colBase = maxSpread;

               // apply follow spread
               for (int rowId = 0; rowId < followSpread.size(); rowId++)
               {
                  Integer newSpread = new Integer(colBase
                        + ((Integer) followSpread.get(rowId)).intValue());

                  if (spread.size() > (1 + rowId))
                  {
                     spread.set(1 + rowId, newSpread);
                  }
                  else
                  {
                     spread.add(newSpread);
                  }
               }

               layoutSpecs.put(followActivity, new LayoutSpec(activity, idxRow + 1,
                     colBase));
            }
         }
      }

      return spread;
   }

   // TODO
   // private List getActivityRefs(ActivityType activity)
   // {
   // List activityRefs = new ArrayList();
   // if (activity.getApplication() != null)
   // {
   // activityRefs.add(activity.getApplication());
   // }
   // if (activity.getPerformer() != null)
   // {
   // activityRefs.add(activity.getPerformer());
   // }
   // for (Iterator iter = activity.getDataMapping().iterator(); iter.hasNext();)
   // {
   // DataMappingType element = (DataMappingType) iter.next();
   // activityRefs.add(element.getData());
   // }
   // return activityRefs;
   // }

   private static class ActivityBlock
   {
      public static final int dxParticipant = 6;

      public static final int dxApplication = 6;

      public static final int dxActivity = 8;

      public static final int dyActivity = 4;

      public static final int dxData = 6;

      public static final int dyData = 4;

      public static final int dyParticipant = 3;

      public static final int dyApplication = 3;

      public static final int dxJoinGateway = 3;

      public static final int dyJoinGateway = 3;

      public static final int dxSplitGateway = 3;

      public static final int dySplitGateway = 3;

      public final boolean joinGateway;

      public final boolean splitGateway;

      public final Map data;

      public final int nParticipants;

      public final int nApplications;

      public ActivityBlock(ActivityType activity)
      {
         this.joinGateway = !JoinSplitType.NONE_LITERAL.equals(activity.getJoin());
         this.splitGateway = !JoinSplitType.NONE_LITERAL.equals(activity.getSplit());

         this.nParticipants = (ActivityUtil.isInteractive(activity) && (null != activity
               .getPerformer())) ? 1 : 0;
         this.nApplications = (null != activity.getApplication()) ? 1 : 0;

         this.data = new HashMap();
         for (Iterator i = activity.getDataMapping().iterator(); i.hasNext();)
         {
            DataMappingType mapping = (DataMappingType) i.next();
            DirectionType direction = (DirectionType) data.get(mapping.getData());
            if (null == direction)
            {
               direction = mapping.getDirection();
            }
            else if (!direction.equals(mapping.getDirection()))
            {
               direction = DirectionType.INOUT_LITERAL;
            }
            data.put(mapping.getData(), direction);
         }
      }

      public int getWidth(boolean isVertical)
      {
         if (isVertical)
         {
            return dxData + dxActivity + Math.max(dxParticipant, dxApplication);
         }
         int activityWidth = dxJoinGateway + dxActivity + dxSplitGateway;
         int dataWidth = dxData * data.size();
         int operationalWidth = dxParticipant * nParticipants + dxApplication
               * nApplications;
         return Math.max(activityWidth, Math.max(dataWidth, operationalWidth));
      }

      public int getHeight(boolean isVertical)
      {
         if (!isVertical)
         {
            return dyData + dyActivity + Math.max(dyParticipant, dyApplication);
         }
         int activityHeight = dyJoinGateway + dyActivity + dySplitGateway;
         int dataHeight = dyData * data.size();
         int operationalHeight = dyParticipant * nParticipants + dyApplication
               * nApplications;
         return Math.max(activityHeight, Math.max(dataHeight, operationalHeight));
      }
   }

   private static class LayoutSpec
   {
      final ActivityType predecessor;

      final int idxRow;

      final int idxColumn;

      public LayoutSpec(ActivityType predecessor, int idxRow, int idxColumn)
      {
         this.predecessor = predecessor;
         this.idxRow = idxRow;
         this.idxColumn = idxColumn;
      }
   }
}