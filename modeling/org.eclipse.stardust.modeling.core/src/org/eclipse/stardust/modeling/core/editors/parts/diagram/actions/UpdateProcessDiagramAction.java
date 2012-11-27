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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.compatibility.el.DataTypeResolver;
import org.eclipse.stardust.engine.core.compatibility.el.JsConverter;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ScriptType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.NodeCreationFactory;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteNodeSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetSymbolContainerCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;

public class UpdateProcessDiagramAction extends SelectionAction
{
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;  
   
   private WorkflowModelEditor editor;
   private Map defaultPoolsMap = new HashMap();

   public UpdateProcessDiagramAction(WorkflowModelEditor editor)
   {
      super(editor);
      this.editor = editor;
      setId(DiagramActionConstants.PROCESS_DIAGRAM_UPDATE);
      setText(Diagram_Messages.LB_UpdateProcessDiagram);
   }

   public boolean isEnabled()
   {
      return calculateEnabled();
   }

   protected boolean calculateEnabled()
   {
      Command cmd = createUpdateDiagramCmd();
      return cmd != null && cmd.canExecute();
   }

   public void run()
   {
      execute(createUpdateDiagramCmd());
      // defaultPool = null;
   }

   public Command createUpdateDiagramCmd()
   {
      CompoundCommand command = new CompoundCommand();
      final ModelType model = editor.getWorkflowModel();
      if (model == null)
      {
    	 return command.unwrap();
      }
      JsConverter converter = new JsConverter(new DataTypeResolver()
      {
         public String resolveDataType(String dataId)
         {
            DataType data = (DataType) ModelUtils.findElementById(model.getData(), dataId);
            if (data == null)
            {
               return null;
            }
            DataTypeType type = data.getType();
            if (type == null)
            {
               return null;
            }
            return type.getId();
         }
      });
      // TODO: (fh) review that, looks ugly and definitely will not undo correctly.
      ScriptType script = model.getScript();
      boolean needScriptUpdate = false;
      if (script == null || StringUtils.isEmpty(script.getType())
            || script.getType().equals("text/carnotEL")) //$NON-NLS-1$
      {
         needScriptUpdate = true;
         ScriptType newScript = XpdlFactory.eINSTANCE.createScriptType();
         newScript.setType("text/ecmascript"); //$NON-NLS-1$
         Command cmdScript = new SetValueCmd(model, CWM_PKG.getModelType_Script(), newScript);
         command.add(cmdScript);                                                     
      }
      for (Iterator iter = model.getProcessDefinition().iterator(); iter.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) iter.next();
         for (Iterator iterator = process.getDiagram().iterator(); iterator.hasNext();)
         {
            final DiagramType diagram = (DiagramType) iterator.next();
            if (!checkDefaultPool(diagram))
            {
               command.add(createDefaultPoolCmd(model, diagram));
            }
         }
         
         if (needScriptUpdate)
         {
            for (Iterator iterator = process.getTransition().iterator(); iterator.hasNext();)
            {
               TransitionType transition = (TransitionType) iterator.next();
               XmlTextNode expression = transition.getExpression();   
               // <Condition Type="OTHERWISE"/>
               String condition = transition.getCondition();
               if (condition == null
                  || !condition.equals("OTHERWISE")) //$NON-NLS-1$
               {
                  boolean setExpression = false;
                  String expressionValue = null;
                  if(expression == null)
                  {
                     expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();                  
                  }
                  else
                  {
                     expressionValue = ModelUtils.getCDataString(expression.getMixed());
                     expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();                     
                  }
                  if (condition != null)
                  {
                     if (condition.equals("FALSE")) //$NON-NLS-1$
                     {
                        setExpression = true;
                        ModelUtils.setCDataString(expression.getMixed(), "false", true); //$NON-NLS-1$                  
                     }
                     else if (condition.equals("TRUE")) //$NON-NLS-1$
                     {
                        setExpression = true;
                        ModelUtils.setCDataString(expression.getMixed(), "true", true); //$NON-NLS-1$                  
                     }
                     // CONDITION
                     else
                     {
                        if (condition.equals("CONDITION") & !StringUtils.isEmpty(expressionValue)) //$NON-NLS-1$
                        {
                           if (!"false".equals(expressionValue) && !"true".equals(expressionValue)) //$NON-NLS-1$ //$NON-NLS-2$
                           {
                              setExpression = true;
                              ModelUtils.setCDataString(expression.getMixed(), converter.convert(expressionValue), true);
                           }
                        }
                     }
                  }
                  else
                  {
                     if(StringUtils.isEmpty(expressionValue))
                     {
                        setExpression = true;
                        ModelUtils.setCDataString(expression.getMixed(), "true", true); //$NON-NLS-1$                     
                     }
                     else if (expressionValue.trim().length() > 0)
                     {
                        setExpression = true;
                        ModelUtils.setCDataString(expression.getMixed(), converter.convert(expressionValue), true);                  
                     }
                  }
                  if(condition == null
                        || !condition.equals("CONDITION")) //$NON-NLS-1$
                  {
                     condition = "CONDITION"; //$NON-NLS-1$
                     Command cmdCondition = new SetValueCmd(transition, CWM_PKG.getTransitionType_Condition(), 
                           condition);
                     command.add(cmdCondition);   
                  }
                  if(setExpression)
                  {
                     Command cmdExpression = new SetValueCmd(transition, CWM_PKG.getTransitionType_Expression(), 
                           expression);
                     command.add(cmdExpression);                                   
                  }
               }
               // OTHERWISE
               else
               {
                  if(expression != null)
                  {
                     Command cmdExpression = new SetValueCmd(transition, CWM_PKG.getTransitionType_Expression(), 
                           null);
                     command.add(cmdExpression);                                                     
                  }
               }
            }
         }
      }
      
      for (Iterator iter = model.getProcessDefinition().iterator(); iter.hasNext();)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) iter.next();
         for (Iterator iterator = process.getDiagram().iterator(); iterator.hasNext();)
         {
            final DiagramType diagram = (DiagramType) iterator.next();
            int maxNumPools = 1;
            if (diagram.getPoolSymbols().size() > maxNumPools)
            {
               createMergePoolsCmd(diagram, command);
            }
            if (!diagram.getNodes().isEmpty())
            {
               moveSymbolsToContainer(command, diagram, null, -23, -1);
            }
            if (!diagram.getConnections().isEmpty())
            {
               moveConnectionsToDefaultPool(diagram, command);
            }
            PoolSymbol pool = DiagramUtil.getDefaultPool(diagram);  
            if(pool != null)
            {
               checkLaneParticipant(pool, command);
            }
            
            // change to BPMN Mode (old model containing lanes)?
            DiagramModeType mode = diagram.getMode();
            if(mode.equals(DiagramModeType.MODE_400_LITERAL)
                  && pool != null
                  && !pool.getLanes().isEmpty())
            {               
               Command cmd = new SetValueCmd(diagram, CWM_PKG.getDiagramType_Mode(), 
                      DiagramModeType.MODE_450_LITERAL);
               command.add(cmd);  
               // if pool contains also other symbols than lane symbols
               if(containsSymbols(pool))
               {
                  // create lane and move symbols to lane
                  command.add(createLaneCmd(pool));                     
               }
               // now iterate over all lanes and check for nested lanes
               // a lane should not contain both lanes and other symbols
               // in such cases a new lane should be created
               checkLaneActions(pool, command, true);
               Command reorder = new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     PoolSymbol pool = DiagramUtil.getDefaultPool(diagram);            
                     // reorder lanes
                     return PoolLaneUtils.updateProcessDiagram(pool);               
                  }
               };
               command.add(reorder);
               // reorder nested lanes
               checkLaneActions(pool, command, false);
            }
         }
      }
      return command.isEmpty() ? null : command.unwrap();
   }

   public void checkLaneParticipant(ISwimlaneSymbol container, CompoundCommand cmd)
   {
      List childLanes = container.getChildLanes();
      if(!childLanes.isEmpty())
      {         
         for (Iterator i = childLanes.listIterator(); i.hasNext();)
         {
            LaneSymbol lane = (LaneSymbol) i.next();
            IModelParticipant participant = lane.getParticipant();
            if(participant != null)
            {
               cmd.add(new SetValueCmd(lane, CWM_PKG.getISwimlaneSymbol_ParticipantReference(), participant));
               cmd.add(new DeleteValueCmd(lane, CWM_PKG.getISwimlaneSymbol_Participant()));               
            }
            
            checkLaneParticipant(lane, cmd);
         }
      }
   }
   
   
   // check all lanes
   public void checkLaneActions(PoolSymbol pool, CompoundCommand cmd, boolean create)
   {
      for (Iterator i = pool.getLanes().listIterator(); i.hasNext();)
      {
         LaneSymbol lane = (LaneSymbol) i.next();
         checkLanes(lane, cmd, create);
      }
   }

   // helper method to check nested lanes, create new lane or reorder nested lanes
   private void checkLanes(final ISwimlaneSymbol container, CompoundCommand cmd, boolean create)
   {
      List childLanes = container.getChildLanes();
      if(!childLanes.isEmpty())
      {         
         if(create)
         {
            // if container contains also other symbols than lane symbols
            if(containsSymbols(container))
            {
               // create lane and move symbols to lane
               cmd.add(createLaneCmd(container));                     
            }                     
         }
         else
         {
            Command reorder = new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  // reorder lanes
                  return PoolLaneUtils.updateProcessDiagram(container);               
               }
            };
            cmd.add(reorder);
         }
         for (Iterator i = childLanes.listIterator(); i.hasNext();)
         {
            LaneSymbol lane = (LaneSymbol) i.next();
            checkLanes(lane, cmd, create);
         }
      }
   }
   
   // check if container contains symbols other than lanes
   private boolean containsSymbols(ISwimlaneSymbol container)
   {
      for (Iterator i = ((ISymbolContainer) container).getNodes().valueListIterator(); i.hasNext();)
      {
         INodeSymbol node = (INodeSymbol) i.next();
         if (!(node instanceof ISwimlaneSymbol))
         {
            return true;
         }
      }
      return false;      
   }
   
   private void createMergePoolsCmd(DiagramType diagram, CompoundCommand command)
   {
      for (Iterator iter = diagram.getPoolSymbols().iterator(); iter.hasNext();)
      {
         PoolSymbol pool = (PoolSymbol) iter.next();
         if (!DiagramUtil.isDefaultPool(pool))
         {
            moveSymbolsToContainer(command, pool, diagram, pool.getXPos(), pool.getYPos());
            moveConnectionsToDefaultPool(pool, command);
            command.add(new DeleteNodeSymbolCmd(pool));
         }
      }
   }

   private Dimension moveSymbolsToContainer(CompoundCommand command,
         final ISymbolContainer container, final ISymbolContainer target, 
         long xPos, long yPos)
   {      
      // take a default size if symbols size is -1
      int defaultSize = 50;
      int x = 0;
      int y = 0;
      for (Iterator i = container.getNodes().valueListIterator(); i.hasNext();)
      {
         final INodeSymbol node = (INodeSymbol) i.next();
         int nodeWidth = node.getWidth();
         int nodeHeight = node.getHeight();
         if(nodeWidth == -1)
         {
            nodeWidth = defaultSize;
         }
         if(nodeHeight == -1)
         {
            nodeHeight = defaultSize;
         }
         if((node.getXPos() + nodeWidth + PoolLaneUtils.space) > x)
         {
            x = (int) node.getXPos() + nodeWidth + PoolLaneUtils.space;
         }
         if((node.getYPos() + nodeHeight + PoolLaneUtils.space) > y)
         {
            y = (int) node.getYPos() + nodeHeight + PoolLaneUtils.space;
         }
         
         if (!(node instanceof ISwimlaneSymbol))
         {
            CompoundCommand moveSymbolsCmd = new CompoundCommand();
            if (null != node.eContainmentFeature())
            {
               moveSymbolsCmd.add(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     SetSymbolContainerCommand cmd = new SetSymbolContainerCommand()
                     {
                        public boolean changePerformer()
                        {
                           return false;
                        }
                     };
                     if(target == null)
                     {
                        cmd.setContainer((ISymbolContainer) defaultPoolsMap.get(container), null);                                                
                     }
                     else
                     {
                        cmd.setContainer(target, null);                                                
                     }                     
                     cmd.setSymbol(node);
                     return cmd;
                  }
               });
               moveSymbolsCmd.add(new SetValueCmd(node,
                     CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_XPos(),
                     new Long(node.getXPos() + xPos)));
               moveSymbolsCmd.add(new SetValueCmd(node,
                     CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_YPos(),
                     new Long(node.getYPos() + yPos)));
               command.add(moveSymbolsCmd);
            }
         }
      }
      return new Dimension(x, y);
   }

   private void moveConnectionsToDefaultPool(final ISymbolContainer container,
         CompoundCommand command)
   {
      for (Iterator i = container.getConnections().valueListIterator(); i.hasNext();)
      {
         final IConnectionSymbol connection = (IConnectionSymbol) i.next();
         if (null != connection.eContainmentFeature())
         {
            command.add(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  SetSymbolContainerCommand cmd = new SetSymbolContainerCommand();
                  cmd.setContainer((ISymbolContainer) defaultPoolsMap.get(container), null);
                  cmd.setSymbol(connection);
                  return cmd;
               }
            });
         }
      }
   }

   // create lane to collect the homeless symbols, and move those symbols to this lane
   private Command createLaneCmd(final ISwimlaneSymbol container)
   {
      CompoundCommand compoundCommand = new CompoundCommand();
      CreationFactory factory = NodeCreationFactory.getLaneFactory();
      final IDiagramCommand command = (IDiagramCommand) factory.getNewObject();
      command.setParent(container);
      command.setLocation(new Rectangle(0, 0, -1, -1));
      compoundCommand.add((Command) command);  
      compoundCommand.add(new DelegatingCommand()
      {
         public Command createDelegate()
         {
            LaneSymbol lane = (LaneSymbol) ((CreateSymbolCommand) command).getModelElement();
            CompoundCommand command = new CompoundCommand();
            Dimension dimension = moveSymbolsToContainer(command, (ISymbolContainer) container, lane, 0, 0);
            lane.setWidth(dimension.width);
            lane.setHeight(dimension.height);
            /*
            lane.setParentLane(null);
            lane.setParentPool(null);            
            */
            return command;
         }
      });
      return compoundCommand;
   }   
   
   private Command createDefaultPoolCmd(ModelType model, final DiagramType diagram)
   {
      CreateSymbolCommand command = new CreateSymbolCommand(IDiagramCommand.DIAGRAM,
            null, CarnotWorkflowModelPackage.eINSTANCE.getPoolSymbol())
      {
         protected IModelElement createModelElement()
         {
            PoolSymbol defaultPool = DiagramUtil.createDefaultPool(null);
            defaultPoolsMap.put(diagram, defaultPool);            
            return defaultPool;
         }
      };
      command.setParent(diagram);
      command.setLocation(new Rectangle(0, 0, -1, -1));
      return command;
   }

   private boolean checkDefaultPool(DiagramType diagram)
   {
      PoolSymbol defaultPool = DiagramUtil.getDefaultPool(diagram);
      if(defaultPool != null)
      {
         defaultPoolsMap.put(diagram, defaultPool);
         return true;
      }      
      return false;
   }
}