package org.eclipse.stardust.ui.web.modeler.bpmn2;

import static org.eclipse.stardust.common.CollectionUtils.isEmpty;
import static org.eclipse.stardust.common.CollectionUtils.newArrayList;
import static org.eclipse.stardust.common.CollectionUtils.newHashMap;
import static org.eclipse.stardust.common.CollectionUtils.newHashSet;
import static org.eclipse.stardust.common.StringUtils.isEmpty;
import static org.eclipse.stardust.ui.web.modeler.bpmn2.Bpmn2Utils.findContainingModel;
import static org.eclipse.stardust.ui.web.modeler.bpmn2.Bpmn2Utils.findParticipatingProcesses;
import static org.eclipse.stardust.ui.web.modeler.bpmn2.Bpmn2Utils.getModelUuid;
import static org.eclipse.stardust.ui.web.modeler.bpmn2.utils.Bpmn2ExtensionUtils.getExtensionElement;
import static org.eclipse.stardust.ui.web.modeler.bpmn2.utils.ElementRefUtils.encodeReference;
import static org.eclipse.stardust.ui.web.modeler.marshaling.GsonUtils.extractAsString;
import static org.eclipse.stardust.ui.web.modeler.marshaling.GsonUtils.hasNotJsonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.XMLConstants;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.dc.Point;
import org.eclipse.dd.di.Diagram;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.dd.di.Edge;
import org.eclipse.dd.di.Shape;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.ui.web.modeler.bpmn2.utils.Bpmn2DataflowUtil;
import org.eclipse.stardust.ui.web.modeler.bpmn2.utils.Bpmn2DatatypeUtil;
import org.eclipse.stardust.ui.web.modeler.bpmn2.utils.Bpmn2ExtensionUtils;
import org.eclipse.stardust.ui.web.modeler.bpmn2.utils.Bpmn2ProxyResolver;
import org.eclipse.stardust.ui.web.modeler.bpmn2.utils.ModelInfo;
import org.eclipse.stardust.ui.web.modeler.bpmn2.utils.XSDType2StardustMapping;
import org.eclipse.stardust.ui.web.modeler.integration.ExternalXmlSchemaManager;
import org.eclipse.stardust.ui.web.modeler.marshaling.JsonMarshaller;
import org.eclipse.stardust.ui.web.modeler.marshaling.ModelMarshaller;
import org.eclipse.stardust.ui.web.modeler.model.ActivityJto;
import org.eclipse.stardust.ui.web.modeler.model.ApplicationJto;
import org.eclipse.stardust.ui.web.modeler.model.DataFlowJto;
import org.eclipse.stardust.ui.web.modeler.model.DataJto;
import org.eclipse.stardust.ui.web.modeler.model.DataMappingJto;
import org.eclipse.stardust.ui.web.modeler.model.EventJto;
import org.eclipse.stardust.ui.web.modeler.model.GatewayJto;
import org.eclipse.stardust.ui.web.modeler.model.ModelElementJto;
import org.eclipse.stardust.ui.web.modeler.model.ModelJto;
import org.eclipse.stardust.ui.web.modeler.model.ModelParticipantJto;
import org.eclipse.stardust.ui.web.modeler.model.PrimitiveDataJto;
import org.eclipse.stardust.ui.web.modeler.model.ProcessDefinitionJto;
import org.eclipse.stardust.ui.web.modeler.model.TransitionJto;
import org.eclipse.stardust.ui.web.modeler.model.TypeDeclarationJto;
import org.eclipse.stardust.ui.web.modeler.model.di.ActivitySymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.ConnectionSymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.DataSymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.EventSymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.GatewaySymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.LaneSymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.PoolSymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.ProcessDiagramJto;
import org.eclipse.stardust.ui.web.modeler.model.di.ShapeJto;
import org.eclipse.stardust.ui.web.modeler.service.XsdSchemaUtils;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Bpmn2ModelMarshaller implements ModelMarshaller
{
   private static final Logger trace = LogManager.getLogger(Bpmn2ModelMarshaller.class);

   private final Bpmn2Binding bpmn2Binding;

   private JsonMarshaller jsonIo = new JsonMarshaller();

   // TODO wire to Spring bean
   private final ExternalXmlSchemaManager externalXmlSchemaManager = new ExternalXmlSchemaManager();

   public Bpmn2ModelMarshaller(Bpmn2Binding bpmn2Binding)
   {
      this.bpmn2Binding = bpmn2Binding;
   }

   @Override
   public JsonElement toJson(EObject element)
   {
      trace.info("Converting to JSON: " + element);

      if (element instanceof Definitions)
      {
         return toModelJson(element);
      }
      else if (element instanceof ItemDefinition)
      {
         return jsonIo.gson().toJsonTree(toJto((ItemDefinition) element));
      }
      else if (element instanceof Interface)
      {
         return jsonIo.gson().toJsonTree(toJto((Interface) element));
      }
      else if (element instanceof Process)
      {
         return jsonIo.gson().toJsonTree(toJto((Process) element));
      }
      else if (element instanceof BPMNDiagram)
      {
         return jsonIo.gson().toJsonTree(toProcessDiagramJto((BPMNDiagram) element, null));
      }
      else if (element instanceof DataStore)
      {
         return jsonIo.gson().toJsonTree(toJto((DataStore) element));
      }
      else if (element instanceof DataObject)
      {
         return jsonIo.gson().toJsonTree(toJto((DataObject) element));
      }
      else if (element instanceof Resource)
      {
         return jsonIo.gson().toJsonTree(toJto((Resource) element));
      }
      else if (element instanceof Event)
      {
         return jsonIo.gson().toJsonTree(toJto((Event) element));
      }
      else if (element instanceof Activity)
      {
         return jsonIo.gson().toJsonTree(toJto((Activity) element));
      }
      else if (element instanceof Gateway)
      {
         return jsonIo.gson().toJsonTree(toJto((Gateway) element));
      }
      else if (element instanceof SequenceFlow)
      {
         return jsonIo.gson().toJsonTree(toJto((SequenceFlow) element));
      }
      else if (element instanceof BPMNShape)
      {
         BPMNShape shape = (BPMNShape) element;

         if (shape.getBpmnElement() instanceof DataObject)
         {
            DataSymbolJto symbolJto = newShapeJto(shape, new DataSymbolJto());
            symbolJto.modelElement = toJto((DataObject) shape.getBpmnElement());

            return jsonIo.gson().toJsonTree(symbolJto);
         }
         else if (shape.getBpmnElement() instanceof DataStoreReference)
         {
            DataSymbolJto symbolJto = newShapeJto(shape, new DataSymbolJto());
            symbolJto.modelElement = toJto(((DataStoreReference) shape.getBpmnElement()).getDataStoreRef());

            return jsonIo.gson().toJsonTree(symbolJto);
         }
         else if (shape.getBpmnElement() instanceof Event)
         {
            EventSymbolJto symbolJto = newShapeJto(shape, new EventSymbolJto());
            symbolJto.modelElement = toJto((Event) shape.getBpmnElement());

            return jsonIo.gson().toJsonTree(symbolJto);
         }
         else if (shape.getBpmnElement() instanceof Activity)
         {
            ActivitySymbolJto symbolJto = newShapeJto(shape, new ActivitySymbolJto());
            symbolJto.modelElement = toJto((Activity) shape.getBpmnElement());

            return jsonIo.gson().toJsonTree(symbolJto);
         }
         else if (shape.getBpmnElement() instanceof Gateway)
         {
            GatewaySymbolJto symbolJto = newShapeJto(shape, new GatewaySymbolJto());
            symbolJto.modelElement = toJto((Gateway) shape.getBpmnElement());

            return jsonIo.gson().toJsonTree(symbolJto);
         }
         else if (shape.getBpmnElement() instanceof Participant)
         {
            PoolSymbolJto symbolJto = newShapeJto(shape, new PoolSymbolJto());
            symbolJto.orientation = shape.isIsHorizontal()
                  ? ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL
                  : ModelerConstants.DIAGRAM_FLOW_ORIENTATION_VERTICAL;

            return jsonIo.gson().toJsonTree(symbolJto);
         }
         else if (shape.getBpmnElement() instanceof Lane)
         {
            LaneSymbolJto symbolJto = newShapeJto(shape, new LaneSymbolJto());
            symbolJto.orientation = shape.isIsHorizontal()
                  ? ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL
                  : ModelerConstants.DIAGRAM_FLOW_ORIENTATION_VERTICAL;

            return jsonIo.gson().toJsonTree(symbolJto);
         }
         else
         {
            trace.debug("Unsupported shape: " + shape.getBpmnElement());
         }
      }
      else if (element instanceof BPMNEdge)
      {
         BPMNEdge edge = (BPMNEdge) element;

         if (edge.getBpmnElement() instanceof SequenceFlow)
         {
            ConnectionSymbolJto symbolJto = newEdgeJto(edge, new ConnectionSymbolJto());
            SequenceFlow flow = (SequenceFlow) edge.getBpmnElement();
            symbolJto.modelElement = toJto(flow);
            symbolJto.fromModelElementType = encodeNodeKind(flow.getSourceRef());
            symbolJto.toModelElementType = encodeNodeKind(flow.getTargetRef());

            return jsonIo.gson().toJsonTree(symbolJto);
         }
      }

      throw new IllegalArgumentException("Not yet implemented: " + element);
   }

   @Override
   public JsonObject toModelJson(EObject model)
   {
      assert model instanceof Definitions;

      Definitions bpmn2Model = (Definitions) model;

      String modelUuid = Bpmn2Utils.getModelUuid(bpmn2Model);

      ModelJto modelJto = new ModelJto();
      modelJto.uuid = modelUuid;
      modelJto.id = modelUuid;
      modelJto.name = bpmn2Model.getName();
      if (isEmpty(modelJto.name))
      {
         modelJto.name = bpmn2Binding.getModelFileName(bpmn2Model);
         if ( !isEmpty(modelJto.name) && modelJto.name.endsWith(".bpmn"))
         {
            modelJto.name = modelJto.name.substring(0,
                  modelJto.name.length() - ".bpmn".length());
         }
      }

      // TODO processes etc.
      for (RootElement root : bpmn2Model.getRootElements())
      {
         if (root instanceof ItemDefinition)
         {
            modelJto.typeDeclarations.put(root.getId(), toJto((ItemDefinition) root));
         } else if (root instanceof Interface) {
        	 modelJto.applications.put(root.getId(), toJto((Interface)root));
         }
         else if (root instanceof Participant)
         {
            modelJto.participants.put(root.getId(), toJto((Participant) root));
         }
         else if (root instanceof Resource)
         {
            ModelParticipantJto jto = toJto((Resource) root);
            if (isEmpty(jto.parentUUID))
            {
               modelJto.participants.put(root.getId(), jto);
            }
         }
         else if (root instanceof DataStore)
         {
            modelJto.dataItems.put(root.getId(), toJto((DataStore) root));
         }
         else if (root instanceof Process)
         {
            modelJto.processes.put(root.getId(), toJto((Process) root));

            for (FlowElement flowElement : ((Process) root).getFlowElements())
            {
               if (flowElement instanceof DataObject)
               {
                  modelJto.dataItems.put(flowElement.getId(), toJto((DataObject) flowElement));
               }
            }

            // expose process properties as global data (see BPMN2 -> WS-BPEL mapping)
            for (Property property : ((Process) root).getProperties())
            {
               modelJto.dataItems.put(property.getId(), toJto((Process) root, property));
            }
         }
      }

      return jsonIo.gson().toJsonTree(modelJto).getAsJsonObject();
   }

   @Override
   public JsonObject toProcessDiagramJson(EObject model, String processId)
   {
      assert model instanceof Definitions;

      BPMNDiagram primaryDiagram = null;
      BPMNDiagram fallbackDiagram = null;

      for (RootElement root : ((Definitions) model).getRootElements())
      {
         if ((root instanceof Process) && processId.equals(((Process) root).getId()))
         {
            Process process = (Process) root;

            for (Diagram diagram : ((Definitions) model).getDiagrams())
            {
               if (diagram.getRootElement() instanceof BPMNPlane)
               {
                  BPMNPlane plane = (BPMNPlane) diagram.getRootElement();

                  if (process == plane.getBpmnElement())
                  {
                     primaryDiagram = (BPMNDiagram) diagram;
                     break;
                  }
                  else if (plane.getBpmnElement() instanceof Collaboration)
                  {
                     List<Process> participatingProcesses = findParticipatingProcesses((Collaboration) plane.getBpmnElement());
                     if (participatingProcesses.contains(process))
                     {
                        if (1 == participatingProcesses.size())
                        {
                           primaryDiagram = (BPMNDiagram) diagram;
                           break;
                        }
                        else if (null == fallbackDiagram)
                        {
                           fallbackDiagram = (BPMNDiagram) diagram;
                        }
                     }
                  }

               }
            }

            ProcessDiagramJto jto = null;
            if (null != primaryDiagram)
            {
               jto = toProcessDiagramJto(primaryDiagram, process);
            }
            else if (null != fallbackDiagram)
            {
               jto = toProcessDiagramJto(fallbackDiagram, process);
            }
            if (null != jto)
            {
               return jsonIo.gson().toJsonTree(jto).getAsJsonObject();
            }
         }
      }
      return new JsonObject();
   }

   @Override
   public JsonArray retrieveConfigurationVariables(EObject model)
   {
      // TODO support config variables
      trace.warn("Configuration variable support is not yet implemented for BPMN2.");
      return new JsonArray();
   }

   @Override
   public String retrieveEmbeddedMarkup(EObject model, String applicationId)
   {
      // TODO support embedded markup
      trace.warn("Embedded amrkup support is not yet implemented for BPMN2.");
      return null;
   }

   public ProcessDiagramJto toProcessDiagramJto(BPMNDiagram diagram, Process process)
   {
      BPMNPlane plane = (BPMNPlane) diagram.getRootElement();

      if (null == process)
      {
         if (plane.getBpmnElement() instanceof Process)
         {
            process = (Process) plane.getBpmnElement();
         }
         else if (plane.getBpmnElement() instanceof Collaboration)
         {
            List<Process> participatingProcesses = findParticipatingProcesses((Collaboration) plane.getBpmnElement());
            if (1 != participatingProcesses.size())
            {
               throw new IllegalArgumentException("Unsupported diagram configuration: "
                     + diagram);
            }
            else
            {
               process = participatingProcesses.get(0);
            }
         }
         else
         {
            throw new IllegalArgumentException("Unsupported diagram configuration: "
                  + diagram);
         }
      }

      Definitions model = findContainingModel(diagram);
      if (null == model)
      {
         throw new IllegalArgumentException("Must not pass a detached diagram: "
               + diagram);
      }

      // find pool/lane shapes
      BPMNShape processPoolShape = null;
      List<BPMNShape> otherPoolShapes = newArrayList();
      List<BPMNShape> laneShapes = newArrayList();
      for (DiagramElement element : plane.getPlaneElement())
      {
         if (element instanceof BPMNShape)
         {
            BPMNShape shape = (BPMNShape) element;
            if (shape.getBpmnElement() instanceof Participant)
            {
               Participant participant = (Participant) shape.getBpmnElement();
               if (process == participant.getProcessRef())
               {
                  // TODO found the pool symbol
                  processPoolShape = shape;
               }
               else
               {
                  otherPoolShapes.add(shape);
               }
            }
            else if (shape.getBpmnElement() instanceof Lane)
            {
               // TODO found a lane symbol
               laneShapes.add(shape);
            }
         }
      }

      ProcessDiagramJto jto = new ProcessDiagramJto();
      jto.uuid = plane.getId();
      jto.id = plane.getId();
      jto.name = nameOrId(diagram.getName(), plane.getId());

      PoolSymbolJto mainPoolJto;
      LaneSymbolJto defaultLane = null;
      boolean horizontalLanes = false;
      boolean verticalLanes = false;
      if ((null == processPoolShape) && !otherPoolShapes.isEmpty())
      {
         // TODO quick hack to show LEO diagrams
         //processPoolShape = otherPoolShapes.remove(0);
    	  // TODO - SOLUTION THAT ALSO HANDLES INTERNAL PROCESS (NO POOL) COMBINED WITH BLACKBOX POOL
      }
      if (null != processPoolShape)
      {
         mainPoolJto = newShapeJto(processPoolShape, new PoolSymbolJto());
         mainPoolJto.processId = process.getId();
         if (processPoolShape.isIsHorizontal())
         {
            mainPoolJto.orientation = ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL;
         }

         if (processPoolShape.getBpmnElement() instanceof Participant)
         {
            Participant poolParticipant = (Participant) processPoolShape.getBpmnElement();
         }

         for (BPMNShape laneShape : laneShapes)
         {
            LaneSymbolJto laneJto = newShapeJto(laneShape, new LaneSymbolJto());
            if (null == defaultLane)
            {
               defaultLane = laneJto;
            }
            if (laneShape.isIsHorizontal())
            {
               horizontalLanes = true;
               laneJto.orientation = ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL;
            }
            else
            {
               verticalLanes = true;
            }

            if (laneShape.getBpmnElement() instanceof Lane)
            {
               laneJto.name = ((Lane) laneShape.getBpmnElement()).getName();
            }

            mainPoolJto.laneSymbols.add(laneJto);
         }
      }
      else
      {
         mainPoolJto = new PoolSymbolJto();
         mainPoolJto.processId = process.getId();
// TODO FIND A SOLUTION THAT HANDLES ALSO 'INTERNAL' PROCESS WITH BLACKBOXES
//         if ( !otherPoolShapes.isEmpty())
//         {
//            float left = otherPoolShapes.get(0).getBounds().getX();
//            float top = otherPoolShapes.get(0).getBounds().getY();
//            float right = left + otherPoolShapes.get(0).getBounds().getWidth();
//            float bottom = top + otherPoolShapes.get(0).getBounds().getHeight();
//
//            for (BPMNShape bpmnShape : otherPoolShapes)
//            {
//               left = Math.min(left, bpmnShape.getBounds().getX());
//               top = Math.min(top, bpmnShape.getBounds().getY());
//               right = Math.max(right, bpmnShape.getBounds().getX() + bpmnShape.getBounds().getWidth());
//               bottom = Math.max(bottom, bpmnShape.getBounds().getX() + bpmnShape.getBounds().getHeight());
//            }
//
//            mainPoolJto.x = Math.round(left);
//            mainPoolJto.y = Math.round(bottom + 1);
//            mainPoolJto.width = Math.round(right - left);
//            mainPoolJto.height = 600;
//         }
//         else
         {
            // TODO dynamically determine pool/lane dimensions
             mainPoolJto.x = 0;
             mainPoolJto.y = 0;
             mainPoolJto.width = 1000;
             mainPoolJto.height = 600;
             float boundX = 0;
             float boundY = 0;
             float boundW = 0;
             float boundH = 0;
             for (DiagramElement symbol : plane.getPlaneElement())
             {
                if (symbol instanceof BPMNShape) {
             	   Bounds bounds = ((BPMNShape)symbol).getBounds();
             	   boundX = Math.min(bounds.getX(), boundX);
             	   boundY = Math.min(bounds.getY(), boundY);

             	   boundH = Math.max(boundH, (bounds.getY() - boundY)+bounds.getHeight());
             	   boundW = Math.max(boundW, (bounds.getX() - boundX)+bounds.getWidth());
                }
             }
             mainPoolJto.x = (int)boundX;
             mainPoolJto.y = (int)boundY;
             mainPoolJto.width = (int)boundW + 20;
             mainPoolJto.height = (int)boundH + 40;
         }
      }

      // ensure pool shape fully encloses lanes and has proper orientation
      int right = mainPoolJto.x + mainPoolJto.width;
      int bottom = mainPoolJto.y + mainPoolJto.height;
      for (LaneSymbolJto laneSymbol : mainPoolJto.laneSymbols)
      {
         mainPoolJto.x = Math.min(mainPoolJto.x, laneSymbol.x);
         mainPoolJto.y = Math.min(mainPoolJto.y, laneSymbol.y);
         right = Math.max(right, laneSymbol.x + laneSymbol.width);
         bottom = Math.max(bottom, laneSymbol.y + laneSymbol.height);
      }
      mainPoolJto.width = right - mainPoolJto.x;
      mainPoolJto.height = bottom - mainPoolJto.y;

      if (horizontalLanes && !verticalLanes)
      {
         mainPoolJto.orientation = ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL;
      }

      if (null == defaultLane)
      {
         defaultLane = new LaneSymbolJto();
         defaultLane.x = mainPoolJto.x + 10;
         defaultLane.y = mainPoolJto.y + 10;
         defaultLane.width = mainPoolJto.width - 20;
         defaultLane.height = mainPoolJto.height - 20;

         defaultLane.orientation = mainPoolJto.orientation;

         mainPoolJto.laneSymbols.add(defaultLane);
      }

      // required to properly connect connections to node symbols
      Map<FlowNode, BPMNShape> nodeSymbolPerElement = newHashMap();
      Map<ItemAwareElement, BPMNShape> symbolPerItemAwareElement = newHashMap();

      // process edges in pass after modes
      List<BPMNEdge> edges = newArrayList();
      for (DiagramElement symbol : plane.getPlaneElement())
      {
         if (symbol instanceof BPMNShape)
         {
            // find related lane
            LaneSymbolJto laneJto = null;
            for (LaneSymbolJto lane : mainPoolJto.laneSymbols)
            {
               if (isWithinBounds(((BPMNShape) symbol).getBounds(), lane))
               {
                  laneJto = lane;
                  break;
               }
            }
            if (null == laneJto)
            {
               continue;
            }

            BPMNShape shape = (BPMNShape) symbol;

            if (shape.getBpmnElement() instanceof FlowNode)
            {
               if (shape.getBpmnElement() instanceof Activity)
               {
                  ActivitySymbolJto symbolJto = newShapeJto(shape,
                        new ActivitySymbolJto());

                  symbolJto.modelElement = toJto((Activity) shape.getBpmnElement());

                  laneJto.activitySymbols.put(symbolJto.modelElement.id, symbolJto);
                  nodeSymbolPerElement.put((FlowNode) shape.getBpmnElement(), shape);

                  List<DataInput> dataInputs = null;
                  List<DataOutput> dataOutputs = null;
                  List<ItemAwareElement> dataIO = new ArrayList<ItemAwareElement>();
                  if (null != shape.getBpmnElement()) {
                	  InputOutputSpecification ioSpecification = ((Activity) shape.getBpmnElement()).getIoSpecification();
                	  if (null != ioSpecification) {
	                	  dataInputs = ioSpecification.getDataInputs();
	                	  dataOutputs = ioSpecification.getDataOutputs();
                	  }
                	  if (null != dataInputs) dataIO.addAll(dataInputs);
                	  if (null != dataOutputs) dataIO.addAll(dataOutputs);
                	  for (ItemAwareElement itemAware :  dataIO) {
                		  symbolPerItemAwareElement.put(itemAware, shape);
                	  }
                  }
               }
               else if (shape.getBpmnElement() instanceof Gateway)
               {
                  GatewaySymbolJto symbolJto = newShapeJto(shape, new GatewaySymbolJto());

                  symbolJto.modelElement = toJto((Gateway) shape.getBpmnElement());

                  laneJto.gatewaySymbols.put(symbolJto.modelElement.id, symbolJto);
                  nodeSymbolPerElement.put((FlowNode) shape.getBpmnElement(), shape);
               }
               else if (shape.getBpmnElement() instanceof Event)
               {
                  if ((shape.getBpmnElement() instanceof StartEvent)
                        || (shape.getBpmnElement() instanceof EndEvent))
                  {
                     EventSymbolJto symbolJto = newShapeJto(shape, new EventSymbolJto());

                     symbolJto.modelElement = toJto((Event) shape.getBpmnElement());

                     laneJto.eventSymbols.put(symbolJto.modelElement.id, symbolJto);
                     nodeSymbolPerElement.put((FlowNode) shape.getBpmnElement(), shape);
                  }
                  else if (shape.getBpmnElement() instanceof BoundaryEvent)
                  {
                     EventSymbolJto symbolJto = newShapeJto(shape, new EventSymbolJto());

                     symbolJto.modelElement = toJto((Event) shape.getBpmnElement());

                     laneJto.eventSymbols.put(symbolJto.modelElement.id, symbolJto);
                     nodeSymbolPerElement.put((FlowNode) shape.getBpmnElement(), shape);
                  }
               }
            }
            else if (((shape.getBpmnElement() instanceof DataObject)
                  || (shape.getBpmnElement() instanceof DataObjectReference)))
            {
               DataObject dataObject = null;
               if (shape.getBpmnElement() instanceof DataObjectReference) {
            	   dataObject = ((DataObjectReference) shape.getBpmnElement()).getDataObjectRef();
            	   symbolPerItemAwareElement.put((ItemAwareElement)shape.getBpmnElement(), shape);
               } else {
            	   dataObject = (DataObject) shape.getBpmnElement();
               }

               DataSymbolJto symbolJto = newShapeJto(shape, new DataSymbolJto());
               symbolJto.dataFullId = getFullId(dataObject);

               laneJto.dataSymbols.put(dataObject.getId(), symbolJto);
               symbolPerItemAwareElement.put((ItemAwareElement)dataObject, shape);
            }
            else if (((shape.getBpmnElement() instanceof DataStore)
                  || (shape.getBpmnElement() instanceof DataStoreReference)))
            {
               DataStore dataStore = (shape.getBpmnElement() instanceof DataStoreReference)
                     ? ((DataStoreReference) shape.getBpmnElement()).getDataStoreRef()
                     : (DataStore) shape.getBpmnElement();

               DataSymbolJto symbolJto = newShapeJto(shape, new DataSymbolJto());
               symbolJto.dataFullId = getFullId(dataStore);

               laneJto.dataSymbols.put(dataStore.getId(), symbolJto);
            }
            else
            {
               trace.debug("Unsupported shape: " + shape.getBpmnElement());
            }
         }
         else if (symbol instanceof BPMNEdge)
         {
            edges.add((BPMNEdge) symbol);
         }
      }

      for (BPMNEdge edge : edges)
      {
         if (edge.getBpmnElement() instanceof SequenceFlow)
         {
            ConnectionSymbolJto symbolJto = new ConnectionSymbolJto();
            SequenceFlow sFlow = (SequenceFlow) edge.getBpmnElement();

            BPMNShape sourceNode = nodeSymbolPerElement.get(sFlow.getSourceRef());
            BPMNShape targetNode = nodeSymbolPerElement.get(sFlow.getTargetRef());
            if ((null == sourceNode) || (null == targetNode))
            {
               // quick exist to cater for currently unsupported node types
               continue;
            }

            symbolJto.type = ModelerConstants.CONTROL_FLOW_CONNECTION_LITERAL;
            symbolJto.modelElement = toJto(sFlow);

            symbolJto.fromModelElementOid = bpmn2Binding.findOid((Definitions) model,
                  sourceNode);
            symbolJto.fromModelElementType = encodeNodeKind(sFlow.getSourceRef());
            symbolJto.toModelElementOid = bpmn2Binding.findOid((Definitions) model,
                  targetNode);
            symbolJto.toModelElementType = encodeNodeKind(sFlow.getTargetRef());

            if ( !isEmpty(edge.getWaypoint()) && (2 <= edge.getWaypoint().size()))
            {
               // use original coordinates to avoid having to adjust waypoints as well
               // (see determineShapeBounds)
               symbolJto.fromAnchorPointOrientation = determineAnchorPoint(sourceNode,
                     edge.getWaypoint().get(0), edge.getWaypoint().get(1));
               symbolJto.toAnchorPointOrientation = determineAnchorPoint(targetNode,
                     edge.getWaypoint().get(edge.getWaypoint().size() - 1),
                     edge.getWaypoint().get(edge.getWaypoint().size() - 2));
            }

            jto.connections.put(symbolJto.modelElement.id, symbolJto);

         } else if (edge.getBpmnElement() instanceof DataAssociation) {
             ConnectionSymbolJto symbolJto = new ConnectionSymbolJto();
             DataAssociation dataFlow = (DataAssociation) edge.getBpmnElement();
             if (dataFlow.eIsProxy()) {
            	 Bpmn2ProxyResolver.resolveResourceProxy(dataFlow, ModelInfo.getDefinitions(dataFlow));
             }
             BPMNShape sourceNode = null; //symbolPerItemAwareElement.get(dataFlow.getSourceRef());
             BPMNShape targetNode = null; //symbolPerItemAwareElement.get(dataFlow.getTargetRef());
             if (dataFlow.getSourceRef() != null && dataFlow.getSourceRef().size() > 0) {
            	 sourceNode = symbolPerItemAwareElement.get(dataFlow.getSourceRef().get(0));
            	 if (null != sourceNode) symbolJto.fromModelElementType = encodeNodeKind(dataFlow.getSourceRef().get(0), sourceNode.getBpmnElement());
             }
             targetNode = symbolPerItemAwareElement.get(dataFlow.getTargetRef());
             if (null != targetNode) symbolJto.toModelElementType = encodeNodeKind(dataFlow.getTargetRef(), targetNode.getBpmnElement());

             if ((null == sourceNode) || (null == targetNode))
             {
                // quick exist to cater for currently unsupported node types
                continue;
             }

             symbolJto.type = ModelerConstants.DATA_FLOW_CONNECTION_LITERAL;
             DataFlowJto flowJto = toJto(dataFlow);
             symbolJto.modelElement = flowJto;

             symbolJto.fromModelElementOid = bpmn2Binding.findOid((Definitions) model, sourceNode);
             symbolJto.toModelElementOid = bpmn2Binding.findOid((Definitions) model, targetNode);


             if ( !isEmpty(edge.getWaypoint()) && (2 <= edge.getWaypoint().size()))
             {
                // use original coordinates to avoid having to adjust waypoints as well
                // (see determineShapeBounds)
                symbolJto.fromAnchorPointOrientation = determineAnchorPoint(sourceNode,
                      edge.getWaypoint().get(0), edge.getWaypoint().get(1));
                symbolJto.toAnchorPointOrientation = determineAnchorPoint(targetNode,
                      edge.getWaypoint().get(edge.getWaypoint().size() - 1),
                      edge.getWaypoint().get(edge.getWaypoint().size() - 2));
             }

             jto.connections.put(symbolJto.modelElement.id, symbolJto);
         }
      }

//      Map<String, ConnectionSymbolJto> mergeConnections = mergeConnections(jto.connections);
//      jto.connections.clear();
//      jto.connections.putAll(mergeConnections); // one flow-symbol for all flows between the same pair of activity & data

      jto.poolSymbols.put(mainPoolJto.id, mainPoolJto);

      for (BPMNShape poolShape : otherPoolShapes)
      {
         PoolSymbolJto poolJto = newShapeJto(poolShape, new PoolSymbolJto());

         if (poolShape.getBpmnElement() instanceof Participant)
         {
            Participant poolParticipant = (Participant) poolShape.getBpmnElement();
            poolJto.id = poolParticipant.getId();
            poolJto.name = poolParticipant.getName();

            if (null != poolParticipant.getProcessRef())
            {
               poolJto.processId = poolParticipant.getProcessRef().getId();
            }

            // TODO anything else?
         }

         poolJto.orientation = mainPoolJto.orientation;
         // grow pool to be at least as large as the main pool
         if (ModelerConstants.DIAGRAM_FLOW_ORIENTATION_HORIZONTAL.equals(poolJto.orientation))
         {
            if (poolJto.x > mainPoolJto.x)
            {
               poolJto.width += (poolJto.x - mainPoolJto.x);
               poolJto.x = mainPoolJto.x;
            }
            if ((poolJto.x + poolJto.width) < (mainPoolJto.x + mainPoolJto.width))
            {
               poolJto.width = (mainPoolJto.x + mainPoolJto.width) - poolJto.x;
            }
         }
         else if (ModelerConstants.DIAGRAM_FLOW_ORIENTATION_VERTICAL.equals(poolJto.orientation))
         {
            if (poolJto.y > mainPoolJto.y)
            {
               poolJto.height += (poolJto.y - mainPoolJto.y);
               poolJto.y = mainPoolJto.y;
            }
            if ((poolJto.y + poolJto.height) < (mainPoolJto.y + mainPoolJto.height))
            {
               poolJto.height = (mainPoolJto.y + mainPoolJto.height) - poolJto.y;
            }
         }

         jto.poolSymbols.put(poolJto.id, poolJto);
      }

      return jto;
   }

   private Map<String, ConnectionSymbolJto> mergeConnections(Map<String, ConnectionSymbolJto> connections) {
	   Map<String, List<ConnectionSymbolJto>> connectionsBySourceAndTarget = new HashMap<String, List<ConnectionSymbolJto>>();
	   Map<String, ConnectionSymbolJto> mergedConnections = new HashMap<String, ConnectionSymbolJto>();
	   for (ConnectionSymbolJto connection : connections.values()) {
		   if (ModelerConstants.DATA_FLOW_CONNECTION_LITERAL.equals(connection.type)) {
			   String sourceOid = connection.fromModelElementOid.toString();
			   String targetOid = connection.toModelElementOid.toString();
			   String key = ModelerConstants.DATA.equals(connection.fromModelElementType)
					   	  ? sourceOid+"-"+targetOid
					   	  : targetOid+"-"+sourceOid;
			   if (!connectionsBySourceAndTarget.containsKey(sourceOid)) connectionsBySourceAndTarget.put(key, new ArrayList<ConnectionSymbolJto>());
			   connectionsBySourceAndTarget.get(key).add(connection);
		   }
		   else mergedConnections.put(connection.modelElement.id, connection);
	   }
	   for (List<ConnectionSymbolJto> bunch : connectionsBySourceAndTarget.values()) {
		   if (bunch.size() > 1) {
			   ConnectionSymbolJto merged = mergeConnectionDataFlows(bunch);
			   mergedConnections.put(merged.modelElement.id, merged);
		   } else if (bunch.size() == 1) {
			   ConnectionSymbolJto connectionSymbolJto = bunch.get(0);
			   mergedConnections.put(connectionSymbolJto.modelElement.id, connectionSymbolJto);
		   }
	   }
	   return mergedConnections;
   }

   private ConnectionSymbolJto mergeConnectionDataFlows(Collection<ConnectionSymbolJto> bunch) {
	   ConnectionSymbolJto merged = new ConnectionSymbolJto();
	   for (ConnectionSymbolJto jto : bunch) {
		   merged.type = jto.type;
		   if (null == merged.oid) merged.oid = jto.oid;
		   merged.fromModelElementOid = jto.fromModelElementOid;
		   merged.toModelElementOid = jto.toModelElementOid;
		   merged.fromModelElementType = jto.fromModelElementType;
		   merged.toModelElementType = jto.toModelElementType;
		   if (ModelerConstants.UNDEFINED_ORIENTATION_KEY == merged.fromAnchorPointOrientation) merged.fromAnchorPointOrientation = jto.fromAnchorPointOrientation;
		   if (ModelerConstants.UNDEFINED_ORIENTATION_KEY == merged.toAnchorPointOrientation) merged.toAnchorPointOrientation = jto.toAnchorPointOrientation;
		   merged.modelElement = mergeDataFlowMappings(bunch);
	   }
	   return merged;
   }


   private ModelElementJto mergeDataFlowMappings(Collection<ConnectionSymbolJto> bunch) {
	   DataFlowJto merged = new DataFlowJto();
	   List<Entry<String, JsonElement>> attributeEntries = new ArrayList<Map.Entry<String,JsonElement>>();
	   for (ConnectionSymbolJto jto : bunch) {
		   if (jto.modelElement instanceof DataFlowJto) {
			   DataFlowJto dataFlow = (DataFlowJto)jto.modelElement;
			   attributeEntries.addAll(dataFlow.attributes.entrySet());
			   merged.comments.addAll(dataFlow.comments);
			   merged.description = merged.description.concat(";" + dataFlow.description);
			   merged.id = merged.id.concat("_" + dataFlow.id);
			   merged.modelId = dataFlow.modelId;
			   merged.modelUUID = dataFlow.modelUUID;
			   merged.type = dataFlow.type;
			   merged.uuid = dataFlow.uuid;
			   if (null != dataFlow.inputDataMapping) merged.inputDataMapping = dataFlow.inputDataMapping;
			   if (null != dataFlow.outputDataMapping) merged.outputDataMapping = dataFlow.outputDataMapping;
			   //merged.inputDataMapping.putAll(dataFlow.inputDataMapping);
			   //merged.outputDataMapping.putAll(dataFlow.outputDataMapping);
		   }
	   }
	   //if (merged.inputDataMapping.isEmpty()) merged.inputDataMapping = null;
	   //if (merged.outputDataMapping.isEmpty()) merged.outputDataMapping = null;
	   for (Entry<String, JsonElement> entry : attributeEntries) {
		   merged.attributes.add(entry.getKey(), entry.getValue());
	   }
	   return merged;
   	}

   private DataFlowJto toJto(DataAssociation dataFlow) {
	   DataFlowJto dataFlowJson = (DataFlowJto)newModelElementJto(dataFlow, new DataFlowJto());
       dataFlowJson.type = ModelerConstants.DATA_FLOW_LITERAL;
       addDataMappingsFromAssociation(dataFlowJson, dataFlow);
       return dataFlowJson;
   }

   private void addDataMappingsFromAssociation(DataFlowJto dataFlowJson,
		DataAssociation dataFlow) {

	   String direction = null;

	   List<ItemAwareElement> sourceRefs = dataFlow.getSourceRef();
	   if (null == sourceRefs || sourceRefs.size() == 0) {
		   trace.warn("DataAssociation doesn't have a source: " + dataFlow );
		   return;
	   }
	   if (sourceRefs.size() > 1) {
		   trace.warn("Multiple sources for DataAssociations not supported: " + dataFlow );
		   return;
	   }

	   Activity activity = null;
	   ItemAwareElement source = sourceRefs.get(0);
	   ItemAwareElement target = dataFlow.getTargetRef();

       if (source instanceof DataObjectReference) {
    	   source = ((DataObjectReference)source).getDataObjectRef();
    	   direction = ModelerConstants.INPUT_DATA_MAPPING_PROPERTY;
       }
       if (source instanceof DataStoreReference) {
    	   source = ((DataStoreReference)source).getDataStoreRef();
    	   if (source.eIsProxy()) {
    		   source = Bpmn2ProxyResolver.resolveDataStoreProxy((DataStore)source, ModelInfo.getDefinitions(source));
    		   direction = ModelerConstants.INPUT_DATA_MAPPING_PROPERTY;
    	   }
       }
       if (source instanceof DataOutput) {
    	   if (((DataOutput)source).eContainer() instanceof InputOutputSpecification) {
    		   if (((DataOutput)source).eContainer().eContainer() instanceof Activity) {
    			   activity = (Activity)((DataOutput)source).eContainer().eContainer();
    		   }
    	   }
       }

       if (target instanceof DataObjectReference) {
    	   target = ((DataObjectReference)target).getDataObjectRef();
    	   direction = ModelerConstants.OUTPUT_DATA_MAPPING_PROPERTY;
       }
       if (target instanceof DataStoreReference) {
    	   target = ((DataStoreReference)target).getDataStoreRef();
    	   if (target.eIsProxy()) {
    		   target = Bpmn2ProxyResolver.resolveDataStoreProxy((DataStore)target, ModelInfo.getDefinitions(target));
    		   direction = ModelerConstants.OUTPUT_DATA_MAPPING_PROPERTY;
    	   }
       }
       if (target instanceof DataInput) {
    	   if (((DataInput)target).eContainer() instanceof InputOutputSpecification) {
    		   if (((DataInput)target).eContainer().eContainer() instanceof Activity) {
    			   activity = (Activity)((DataInput)target).eContainer().eContainer();
    		   }
    	   }

       }
       if (null == activity) return;

       if (Bpmn2DataflowUtil.hasAssignment(dataFlow)) {
           addDataMappingsFromAssignments(direction, dataFlowJson, dataFlow, source, target);
       }
       else {
           //addDataPathFromTransformationExpression(jto, assocIn, dataInput, associationSource);
    	   addDataMappingWithoutAssignment(direction, dataFlowJson, dataFlow, source, target);
       }

   }

   private void addDataMappingsFromAssignments(String direction,
		   DataFlowJto dataFlowJson, DataAssociation dataFlow,
		   ItemAwareElement source, ItemAwareElement target) {

	   String dataUuid = getModelUuid(findContainingModel(source)) + ":"
			   + source.getId();

	   for (Assignment assign : dataFlow.getAssignment()) {
		   Expression fromExpression = assign.getFrom();
		   String assingmentId = assign.getId();
		   Expression toExpression = assign.getTo();

		   String dataPath = ModelerConstants.INPUT_DATA_MAPPING_PROPERTY.equals(direction)
				   		   ? getExpressionValue(fromExpression)
				   		   : getExpressionValue(toExpression);

		   // TODO
		   //String applicationAccessPoint = ExtensionHelper2.getInstance().getAssignmentAccessPointRef(toExpression);
		   //String applicationAccessPath = getExpressionValue(toExpression);

		   String mappingId = dataFlow.getId() + "_" + assingmentId;

		   DataMappingJto assocJto = newModelElementJto(dataFlow, new DataMappingJto());
		   DataMappingJto mapping = populateInDataMapping(assocJto, dataUuid, dataPath, null, null, null);
		   mapping.id = mappingId;
		   if (ModelerConstants.INPUT_DATA_MAPPING_PROPERTY.equals(direction)) {
			   dataFlowJson.inputDataMapping = mapping; // put(mappingId, mapping);
			   //dataFlowJson.outputDataMapping = null; // xpdl ModelElementUnmarshaller checks for jsonNull
		   } else {
			   dataFlowJson.outputDataMapping = mapping; //put(mappingId, mapping);
			   //dataFlowJson.inputDataMapping = null; // xpdl ModelElementUnmarshaller checks for jsonNull
		   }
	   }

	}

   private String getExpressionValue(Expression expr) {
	   if (null == expr) return "";
	   if (expr instanceof FormalExpression) {
		   return ((FormalExpression)expr).getBody();
	   } else {
		   String informal = "";
		   List<Documentation> documentation = expr.getDocumentation();
		   if (null != documentation) {
			   for (Documentation doc : documentation) {
				   informal = informal.concat(doc.getText());
			   }
		   }
		   return informal;
	   }
   }

	private void addDataMappingWithoutAssignment(String direction,
			DataFlowJto dataFlowJson, DataAssociation dataFlow,
			ItemAwareElement source, ItemAwareElement target) {

	       String dataUuid = ModelerConstants.INPUT_DATA_MAPPING_PROPERTY.equals(direction)
	    		   ? getModelUuid(findContainingModel(source)) + ":" + source.getId()
	    		   : getModelUuid(findContainingModel(target)) + ":" + target.getId();

	       String expr = "";
	       if (dataFlow.getTransformation() != null) {
	           if (dataFlow.getTransformation().getBody() != null && !dataFlow.getTransformation().getBody().isEmpty()) {
	               expr = dataFlow.getTransformation().getBody();
	           } else if (dataFlow.getTransformation().getMixed() != null )  {
	                expr = ModelUtils.getCDataString(dataFlow.getTransformation().getMixed());
	           }
	       }
	       DataMappingJto assocJto = newModelElementJto(dataFlow, new DataMappingJto());
	       DataMappingJto mapping = populateInDataMapping(assocJto, dataUuid, expr, null, null, null);
//	       jto.inDataFlows.put(assocJto.id, assocJto);
		   if (ModelerConstants.INPUT_DATA_MAPPING_PROPERTY.equals(direction)) {
			   dataFlowJson.inputDataMapping = mapping; //.put(mapping.id, mapping);
		   } else {
			   dataFlowJson.outputDataMapping = mapping; //.put(mapping.id, mapping);
		   }


	}

	private static boolean isWithinBounds(Bounds symbolBounds, ShapeJto bounds)
   {
      return (bounds.x <= symbolBounds.getX())
            && ((bounds.x + bounds.width) >= symbolBounds.getX() + symbolBounds.getWidth())
            && (bounds.y <= symbolBounds.getY())
            && ((bounds.y + bounds.height) >= symbolBounds.getY() + symbolBounds.getHeight());
   }

   public TypeDeclarationJto toJto(ItemDefinition itemDefinition)
   {
      TypeDeclarationJto jto = newModelElementJto(itemDefinition,
            new TypeDeclarationJto());

      // TODO
      String schemaLocation = null;
      String typeId = null;
      if (itemDefinition.getStructureRef() instanceof InternalEObject)
      {
         URI proxyURI = ((InternalEObject) itemDefinition.getStructureRef()).eProxyURI();
         if ((null != proxyURI) && proxyURI.hasFragment())
         {
            schemaLocation = proxyURI.trimFragment().toString();
            typeId = proxyURI.fragment();

            if ( !isEmpty(schemaLocation) && schemaLocation.startsWith("istream:///"))
            {
               schemaLocation = schemaLocation.substring("istream:///".length());
            }
         }
      }

      Import importSpec = itemDefinition.getImport();
      if ((null == importSpec) && !isEmpty(schemaLocation))
      {
         for (Import candidate : Bpmn2Utils.findContainingModel(itemDefinition)
               .getImports())
         {
            if (schemaLocation.equals(candidate.getLocation()))
            {
               importSpec = candidate;
               break;
            }
         }
      }

      if ((null != importSpec)
            && (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001.equals(importSpec.getImportType())))
      {
         XSDSchema importedSchema = externalXmlSchemaManager.resolveSchemaFromUri(importSpec.getLocation());
         if ((null != importedSchema) && !isEmpty(typeId))
         {
            for (XSDTypeDefinition typeDefinition : importedSchema.getTypeDefinitions())
            {
               if (typeId.equals(typeDefinition.getName()))
               {
                  jto.typeDeclaration.schema = XsdSchemaUtils.toSchemaJson(importedSchema);
                  jto.typeDeclaration.type.classifier = "ExternalReference";
                  jto.typeDeclaration.type.location = importSpec.getLocation();
                  jto.typeDeclaration.type.namespace = importSpec.getNamespace();
                  jto.typeDeclaration.type.xref = typeDefinition.getQName(importedSchema);
               }
            }
         }
      }
      else
      {
         EObject xsdSchema = getExtensionElement(itemDefinition, "schema", XMLConstants.W3C_XML_SCHEMA_NS_URI);
         if ((xsdSchema instanceof XSDSchema) && !isEmpty(typeId))
         {
            XSDSchema embeddedSchema = (XSDSchema) xsdSchema;
            jto.typeDeclaration.schema = XsdSchemaUtils.toSchemaJson(embeddedSchema);
            jto.typeDeclaration.type.classifier = "SchemaType";
         }
      }

      return jto;
   }

   public ModelParticipantJto toJto(Resource resource)
   {
      ModelParticipantJto jto = newModelElementJto(resource, new ModelParticipantJto());

      loadDescription(resource, jto);
      loadExtensions(resource, jto);

      JsonObject extJson = Bpmn2ExtensionUtils.getExtensionAsJson(resource, "core");
      if (extJson.has(ModelerConstants.PARTICIPANT_TYPE_PROPERTY))
      {
         jto.type = extJson.get(ModelerConstants.PARTICIPANT_TYPE_PROPERTY).getAsString();
         if ( !isEmpty(jto.participantType))
         {
            // TODO review
            jto.participantType = jto.type;
         }
      }

      if (isEmpty(jto.type))
      {
         jto.type = ModelerConstants.ROLE_PARTICIPANT_TYPE_KEY;
      }

      if (extJson.has(ModelerConstants.PARENT_UUID_PROPERTY))
      {
         jto.parentUUID = extJson.get(ModelerConstants.PARENT_UUID_PROPERTY).getAsString();

         EObject parentResource = bpmn2Binding.findElementByUuid(
               findContainingModel(resource), jto.parentUUID);
         if (parentResource instanceof Resource)
         {
            JsonObject parentExtJson = Bpmn2ExtensionUtils.getExtensionAsJson(
                  (Resource) parentResource, "core");
            if (parentExtJson.has(ModelerConstants.TEAM_LEAD_FULL_ID_PROPERTY)
                  && extractAsString(parentExtJson,
                        ModelerConstants.TEAM_LEAD_FULL_ID_PROPERTY).endsWith(jto.id))
            {
               jto.type = ModelerConstants.TEAM_LEADER_TYPE_KEY;
            }
         }
      }

      if (extJson.has(ModelerConstants.TEAM_LEAD_FULL_ID_PROPERTY))
      {
         jto.teamLeadFullId = extJson.get(ModelerConstants.TEAM_LEAD_FULL_ID_PROPERTY).getAsString();
      }

      Definitions model = findContainingModel(resource);
      if (null != model)
      {
         for (RootElement rootElement : model.getRootElements())
         {
            if (rootElement instanceof Resource)
            {
               JsonObject extJson2 = Bpmn2ExtensionUtils.getExtensionAsJson(rootElement, "core");
               if (extJson2.has(ModelerConstants.PARENT_UUID_PROPERTY)
                     && jto.uuid.equals(extJson2.get(ModelerConstants.PARENT_UUID_PROPERTY).getAsString()))
               {
                  ModelParticipantJto childParticipant = toJto((Resource) rootElement);
                  jto.childParticipants.add(childParticipant);
               }
            }
         }
      }

      return jto;
   }

   public ModelParticipantJto toJto(Participant participant)
   {
      ModelParticipantJto jto = newModelElementJto(participant, new ModelParticipantJto());

      jto.type = ModelerConstants.ROLE_PARTICIPANT_TYPE_KEY;

      return jto;
   }

   public DataJto toJto(Process process, Property property)
   {
      DataJto jto;
      if ((null == property.getItemSubjectRef())
            || (null == property.getItemSubjectRef().getStructureRef()))
      {
         // undefined data
         jto = newModelElementJto(property, new PrimitiveDataJto());
         ((PrimitiveDataJto) jto).primitiveDataType = ModelerConstants.STRING_PRIMITIVE_DATA_TYPE;
      }
      // TODO struct
      else
      {
         jto = newModelElementJto(property, new DataJto());
      }

      // prefix naming to cater for "global data" projection
      jto.id = process.getId() + "_" + jto.id;
      jto.name = nameOrId(process.getName(), process.getId()) + " - " + jto.name;

      return jto;
   }

   public ApplicationJto toJto(Interface application)
   {
      ApplicationJto jto = newModelElementJto(application, new ApplicationJto());

      //loadExtensions(application, jto);
      loadApplicationExtensions(application, jto);

      // TODO resolve app type from model element
      jto.applicationType = ModelerConstants.WEB_SERVICE_APPLICATION_TYPE_ID;

      return jto;
   }

   public ProcessDefinitionJto toJto(Process process)
   {
      ProcessDefinitionJto jto = newModelElementJto(process, new ProcessDefinitionJto());

      loadDescription(process, jto);
      loadExtensions(process, jto);

      for (FlowElement flowElement : process.getFlowElements())
      {
         // TODO
         if (flowElement instanceof Activity)
         {
            jto.activities.put(flowElement.getId(), toJto((Activity) flowElement));
            jto.dataFlows.putAll(toJtoMap(((Activity)flowElement).getDataInputAssociations()));
            jto.dataFlows.putAll(toJtoMap(((Activity)flowElement).getDataOutputAssociations()));
         }
         else if (flowElement instanceof Gateway)
         {
            jto.gateways.put(flowElement.getId(), toJto((Gateway) flowElement));
         }
         else if (flowElement instanceof Event)
         {
            jto.events.put(flowElement.getId(), toJto((Event) flowElement));
         }
         else if (flowElement instanceof SequenceFlow)
         {
            jto.controlFlows.put(flowElement.getId(), toJto((SequenceFlow) flowElement));
         }
      }

      return jto;
   }



   private Map<String, DataFlowJto> toJtoMap(
		List<? extends DataAssociation> dataAssociations) {

	   Map<String, DataFlowJto> result = new HashMap<String, DataFlowJto>();
	   for (DataAssociation assoc : dataAssociations) {
		   DataFlowJto jto = toJto(assoc);
		   result.put(jto.id, jto);
	   }
	   return result;
   }

   public DataJto toJto(DataStore variable)
   {
      DataJto jto = newModelElementJto(variable, new DataJto());

      toJto(variable, jto);

      return jto;
   }

   public DataJto toJto(DataObject variable)
   {
      DataJto jto = newModelElementJto(variable, new DataJto());

      toJto(variable, jto);

      return jto;
   }

   private DataJto toJto(ItemAwareElement variable, DataJto jto)
   {
      loadDescription(variable, jto);
      loadExtensions(variable, jto);

      boolean isBpmnXmlPrimitiveType = Bpmn2DatatypeUtil.refersToPrimitiveType(variable);

      if (null != variable.getItemSubjectRef() && !isBpmnXmlPrimitiveType)
      {
         // TODO
         jto.dataType = ModelerConstants.STRUCTURED_DATA_TYPE_KEY;
         jto.structuredDataTypeFullId = getModelUuid(findContainingModel(variable)) + ":"
               + variable.getItemSubjectRef().getId();
      }
      else
      {
         JsonObject extJson = Bpmn2ExtensionUtils.getExtensionAsJson(variable, "core");
         if (!extJson.has(ModelerConstants.DATA_TYPE_PROPERTY) && isBpmnXmlPrimitiveType) {
        	// non instrumented external bpmn - initialize with xml datatype mapping
          	URI uri = Bpmn2DatatypeUtil.getDataStructureURI(variable);
            String typeName = uri.fragment();
        	XSDType2StardustMapping byXsdName = XSDType2StardustMapping.byXsdName(typeName);
        	if (null != byXsdName && null != byXsdName.getType()) {
        		extJson.addProperty(ModelerConstants.DATA_TYPE_PROPERTY, ModelerConstants.PRIMITIVE_DATA_TYPE_KEY);
        		extJson.addProperty(ModelerConstants.PRIMITIVE_DATA_TYPE_PROPERTY, byXsdName.getType());
        	}
         }
         if (extJson.has(ModelerConstants.DATA_TYPE_PROPERTY))
         {
            jto.dataType = extJson.get(ModelerConstants.DATA_TYPE_PROPERTY).getAsString();
            if ( !isEmpty(jto.dataType))
            {
               if (ModelerConstants.PRIMITIVE_DATA_TYPE_KEY.equals(jto.dataType))
               {
                  jto.primitiveDataType = extractAsString(extJson,
                        ModelerConstants.PRIMITIVE_DATA_TYPE_PROPERTY);
               }
               else if (ModelerConstants.STRUCTURED_DATA_TYPE_KEY.equals(jto.dataType))
               {
                  jto.structuredDataTypeFullId = "";
               }
            }
         }
      }
      return jto;
   }

   public ActivityJto toJto(Activity activity)
   {
	  final String refProperty = "interactiveApplicationRef";

      ActivityJto jto = newModelElementJto(activity, new ActivityJto());

      loadDescription(activity, jto);
      loadExtensions(activity, jto);

      Interface applicationReference = null;

      String dataApContext = "default";

//      if (activity instanceof NoneTask)
//      {
//         jto.activityType = ModelerConstants.TASK_ACTIVITY;
//         jto.taskType = ModelerConstants.NONE_TASK_KEY;
//      }
//      else
      if (activity instanceof ManualTask)
      {
         jto.activityType = ModelerConstants.TASK_ACTIVITY;
         jto.taskType = ModelerConstants.MANUAL_TASK_KEY;
      }
      else if (activity instanceof UserTask)
      {
         jto.activityType = ModelerConstants.TASK_ACTIVITY;
         jto.taskType = ModelerConstants.USER_TASK_KEY;
         // TODO: ManualTask vs UserTask should not be the distinction criteria for automatically generated gui vs interactive application ui
		JsonObject coreJson = Bpmn2ExtensionUtils.getExtensionAsJson(activity, "core");
		if (hasNotJsonNull(coreJson, refProperty)) {
			String refId = coreJson.get(refProperty).getAsString();
			applicationReference = ModelInfo.findInterfaceById(ModelInfo.getDefinitions(activity), refId);
		}
      }
      else if (activity instanceof ServiceTask)
      {
         jto.activityType = ModelerConstants.TASK_ACTIVITY;
         jto.taskType = ModelerConstants.SERVICE_TASK_KEY;
         applicationReference = getInterfaceForOperation(ModelInfo.getDefinitions(activity), ((ServiceTask)activity).getOperationRef());
      }
      else if (activity instanceof ScriptTask)
      {
         jto.activityType = ModelerConstants.TASK_ACTIVITY;
         jto.taskType = ModelerConstants.SCRIPT_TASK_KEY;
         JsonObject coreJson = Bpmn2ExtensionUtils.getExtensionAsJson(activity, "core");
         if (hasNotJsonNull(coreJson, refProperty)) {
        	 String refId = coreJson.get("refProperty").getAsString();
        	 applicationReference = ModelInfo.findInterfaceById(ModelInfo.getDefinitions(activity), refId);
         }
      }
      else if (activity instanceof SendTask)
      {
         jto.activityType = ModelerConstants.TASK_ACTIVITY;
         jto.taskType = ModelerConstants.SEND_TASK_KEY;
         applicationReference = getInterfaceForOperation(ModelInfo.getDefinitions(activity), ((SendTask)activity).getOperationRef());
      }
      else if (activity instanceof ReceiveTask)
      {
         jto.activityType = ModelerConstants.TASK_ACTIVITY;
         jto.taskType = ModelerConstants.RECEIVE_TASK_KEY;
         applicationReference = getInterfaceForOperation(ModelInfo.getDefinitions(activity), ((ReceiveTask)activity).getOperationRef());
      }
//      else if (activity instanceof RuleTask)
//      {
//         jto.activityType = ModelerConstants.TASK_ACTIVITY;
//         jto.taskType = ModelerConstants.RULE_TASK_KEY;
//      }
      else if (activity instanceof SubProcess)
      {
         SubProcess subProcess = (SubProcess) activity;

         jto.activityType = ModelerConstants.SUBPROCESS_ACTIVITY;

         // jto.subprocessFullId = subProcess.get;
      }

      if ( !activity.getResources().isEmpty())
      {
         for (ResourceRole resourceRole : activity.getResources())
         {
            if (resourceRole instanceof Performer)
            {
               jto.participantFullId = encodeReference(resourceRole.getResourceRef());
               break;
            }
         }
      }

      if (null != applicationReference) {
    	  jto.applicationFullId = getApplicationFullId(applicationReference, jto);
      }

      //addDataFlows(activity, jto, dataApContext);

      return jto;
   }

   private DataMappingJto populateInDataMapping(DataMappingJto assocJto, String fromVariableId, String dataPath, String accessPointId, String accessPointContext, String accessPointPath) {

	   if (StringUtils.isNotEmpty(fromVariableId))
		   assocJto.dataFullId = fromVariableId; // ModelerConstants.DATA_FULL_ID_PROPERTY

	   if (StringUtils.isNotEmpty(dataPath))
		   assocJto.dataPath = dataPath;  //String s = ModelerConstants.DATA_PATH_PROPERTY;

	   if (StringUtils.isNotEmpty(accessPointId))
		   assocJto.accessPointId = accessPointId; // ModelerConstants.ACCESS_POINT_ID_PROPERTY

	   if (StringUtils.isNotEmpty(accessPointContext))
		   assocJto.accessPointContext = accessPointContext; // ModelerConstants.ACCESS_POINT_CONTEXT_PROPERTY

	   if (StringUtils.isNotEmpty(accessPointPath))
		   assocJto.accessPointPath = accessPointPath;  //ACCESS_POINT_PATH_PROPERTY

	   return assocJto;
   }

   public GatewayJto toJto(Gateway gateway)
   {
      GatewayJto jto = newModelElementJto(gateway, new GatewayJto());

      loadDescription(gateway, jto);
      loadExtensions(gateway, jto);

      // prefix name due to current gateway-workarounds
      jto.name = "gateway" + jto.name;

      // TODO
      if (gateway instanceof ExclusiveGateway)
      {
         jto.gatewayType = ModelerConstants.XOR_GATEWAY_TYPE;
      }
      else if (gateway instanceof ParallelGateway)
      {
         jto.gatewayType = ModelerConstants.AND_GATEWAY_TYPE;
      }

      return jto;
   }

   public EventJto toJto(Event event)
   {
      EventJto jto = newModelElementJto(event, new EventJto());

      loadDescription(event, jto);
      loadExtensions(event, jto);

      if (event instanceof StartEvent)
      {
         StartEvent startEvent = (StartEvent) event;
         jto.eventType = ModelerConstants.START_EVENT;
         jto.eventClass = encodeEventClass(startEvent.getEventDefinitions());
         jto.throwing = false;
         jto.interrupting = startEvent.isIsInterrupting();
      }
      else if (event instanceof IntermediateCatchEvent)
      {
         IntermediateCatchEvent intermediateCatchEvent = (IntermediateCatchEvent) event;
         jto.eventType = ModelerConstants.INTERMEDIATE_EVENT;
         jto.eventClass = encodeEventClass(intermediateCatchEvent.getEventDefinitions());
         jto.throwing = false;
      }
      else if (event instanceof IntermediateThrowEvent)
      {
         IntermediateThrowEvent intermediateThrowEvent = (IntermediateThrowEvent) event;
         jto.eventType = ModelerConstants.INTERMEDIATE_EVENT;
         jto.eventClass = encodeEventClass(intermediateThrowEvent.getEventDefinitions());
         jto.throwing = true;
      }
      else if (event instanceof BoundaryEvent)
      {
         BoundaryEvent boundaryEvent = (BoundaryEvent) event;

         jto.eventType = ModelerConstants.INTERMEDIATE_EVENT;
         jto.eventClass = encodeEventClass(boundaryEvent.getEventDefinitions());

         // TODO Temporary
         if (boundaryEvent.getAttachedToRef() != null)
         {
            jto.bindingActivityUuid = boundaryEvent.getAttachedToRef().getId();
         }

         jto.interrupting = boundaryEvent.isCancelActivity();
         jto.throwing = false;
      }
      else if (event instanceof EndEvent)
      {
         EndEvent endEvent = (EndEvent) event;
         jto.eventType = ModelerConstants.STOP_EVENT;
         jto.eventClass = encodeEventClass(endEvent.getEventDefinitions());
         jto.throwing = true;
      }

      return jto;
   }

   public TransitionJto toJto(SequenceFlow sFlow)
   {
      TransitionJto jto = newModelElementJto(sFlow, new TransitionJto());

      loadDescription(sFlow, jto);
      loadExtensions(sFlow, jto);

      if (sFlow.getConditionExpression() instanceof FormalExpression)
      {
         // TODO otherwise
         jto.conditionExpression = ((FormalExpression) sFlow.getConditionExpression()).getBody();
      }
      else if (null != sFlow.getConditionExpression()) {
    	  String informal = "";
          List<Documentation> documentation = sFlow.getConditionExpression().getDocumentation();
          if (documentation != null) {
              for (Documentation doc : documentation) {
            	  informal = informal.concat(doc.getText());
              }
          }
          jto.conditionExpression = informal;
      } else if (isDefaultSequenceOfSource(sFlow)) {
    	  jto.otherwise = true;
      }
// nikles, 2014-03-17: BPMN supports informal expressions; is it really useful to preset the name value into the condition field?
//      else if ( !isEmpty(sFlow.getName()))
//      {
//         jto.conditionExpression = sFlow.getName();
//      }

      return jto;
   }


   private boolean isDefaultSequenceOfSource(SequenceFlow sFlow) {
	   SequenceFlow defaultSequence = null;
	   FlowNode sourceNode = sFlow.getSourceRef();
		if (null != sourceNode) {
			if (sourceNode instanceof Activity) {
				defaultSequence = ((Activity)sourceNode).getDefault();
			} else if (sourceNode instanceof ExclusiveGateway) {
				defaultSequence = ((ExclusiveGateway)sourceNode).getDefault();
			} else if (sourceNode instanceof InclusiveGateway) {
				defaultSequence = ((InclusiveGateway)sourceNode).getDefault();
			} else if (sourceNode instanceof ComplexGateway) {
				defaultSequence = ((ComplexGateway)sourceNode).getDefault();
			}
		}
		return sFlow == defaultSequence;
   }

   public String getFullId(BaseElement element)
   {
      Definitions model = Bpmn2Utils.findContainingModel(element);
      if (null != model)
      {
         return bpmn2Binding.getModelId(model) + ":" + element.getId();
      }

      return null;
   }

   /**
    *
    * @param <T>
    * @param <J>
    * @param src
    * @param jto
    * @return
    */
   public <T extends BaseElement, J extends ModelElementJto> J newModelElementJto(T src,
         J jto)
   {
      jto.uuid = bpmn2Binding.findUuid(src);
      jto.id = src.getId();

      String name;

      if (src instanceof ItemDefinition)
      {
         name = ((ItemDefinition) src).getId();
      }
      else if (src instanceof Interface)
      {
         name = ((Interface) src).getId();
      }
      else if (src instanceof Resource)
      {
         name = ((Resource) src).getName();
      }
      else if (src instanceof Participant)
      {
         name = ((Participant) src).getName();
      }
      else if (src instanceof DataStore)
      {
         name = ((DataStore) src).getName();
      }
      else if (src instanceof Property)
      {
         name = ((Property) src).getName();
      }
      else if (src instanceof FlowElement)
      {
         name = ((FlowElement) src).getName();
      }
      else if (src instanceof CallableElement)
      {
         name = ((CallableElement) src).getName();
      }
      else if (src instanceof DataAssociation) {
    	  name = ((DataAssociation) src).getId(); // TODO id?
      }
      else
      {
         throw new IllegalArgumentException("Unsupported model element: " + src);
      }

      // fall back to UUID if name is not defined
      jto.name = nameOrId(name, src.getId());

      jto.oid = bpmn2Binding.findOid(src);

      Definitions model = Bpmn2Utils.findContainingModel(src);
      if (null != model)
      {
         jto.modelId = bpmn2Binding.getModelId(model);
         jto.modelUUID = bpmn2Binding.getModelId(model);
      }

      return jto;
   }

   /**
    *
    * @param <T>
    * @param <J>
    * @param shape
    * @param jto
    * @return
    */
   public <T extends Shape, J extends ShapeJto> J newShapeJto(BPMNShape shape, J jto)
   {
      jto.oid = bpmn2Binding.findOid(shape);

      Bounds bounds = shape.getBounds();
      jto.x = (int) bounds.getX();
      jto.y = (int) bounds.getY();
      jto.width = (int) bounds.getWidth();
      jto.height = (int) bounds.getHeight();

      return jto;
   }

   /**
    *
    * @param <T>
    * @param <J>
    * @param edge
    * @param jto
    * @return
    */
   public <T extends Edge, J extends ConnectionSymbolJto> J newEdgeJto(BPMNEdge edge,
         J jto)
   {
      jto.oid = bpmn2Binding.findOid(edge);

      if (null != edge.getSourceElement())
      {
         jto.fromModelElementOid = bpmn2Binding.findOid(edge.getSourceElement());
      }
      if (null != edge.getTargetElement())
      {
         jto.toModelElementOid = bpmn2Binding.findOid(edge.getTargetElement());
      }
      return jto;
   }

   /**
    *
    * @param event
    * @return
    */
   public String encodeEventClass(List<EventDefinition> eventDefinitions)
   {
      Set<String> eventClasses = newHashSet();

      for (EventDefinition eventDefinition : eventDefinitions)
      {
         if (eventDefinition instanceof TimerEventDefinition)
         {
            eventClasses.add(ModelerConstants.TIMER_EVENT_CLASS_KEY);
         }
         else if (eventDefinition instanceof MessageEventDefinition)
         {
            eventClasses.add(ModelerConstants.MESSAGE_EVENT_CLASS_KEY);
         }
         else if (eventDefinition instanceof ErrorEventDefinition)
         {
            eventClasses.add(ModelerConstants.ERROR_EVENT_CLASS_KEY);
         }
         else
         {
            // TODO more event classes ...
         }
      }
      return (1 == eventClasses.size()) ? eventClasses.iterator().next() : null; // TODO
                                                                                 // "complex"
                                                                                 // instead
                                                                                 // of
                                                                                 // null;
   }

   /**
    *
    * @param node
    * @return
    */
   public static String encodeNodeKind(FlowNode node)
   {
      if (node instanceof Activity)
      {
         return ModelerConstants.ACTIVITY_KEY;
      }
      else if (node instanceof Gateway)
      {
         return ModelerConstants.GATEWAY;
      }
      else if (node instanceof Event)
      {
         return ModelerConstants.EVENT_KEY;
      }
      else
      {
         throw new IllegalArgumentException("Unsupported flow node: " + node);
      }
   }

   public static String encodeNodeKind(ItemAwareElement itemAware, BaseElement flowNode)
   {
      if (itemAware instanceof DataInput || itemAware instanceof DataOutput) {
    	  if (!(flowNode instanceof FlowNode)) throw new IllegalArgumentException("Unsupported node type (FlowNode expected): " + flowNode);

    	  return encodeNodeKind((FlowNode)flowNode);
      }
      return ModelerConstants.DATA;
   }

   /**
    *
    * @param fromShape
    * @param point
    * @param point2
    * @return
    */
   private int determineAnchorPoint(BPMNShape fromShape, Point point, Point point2)
   {
      Bounds fromBounds = fromShape.getBounds(); // determineShapeBounds(fromShape);

      double dx = point.getX() - (fromBounds.getX() + fromBounds.getWidth() / 2.0);
      double dy = point.getY() - (fromBounds.getY() + fromBounds.getHeight() / 2.0);

      if ((dx == 0.0) && (dy == 0.0))
      {
         dx = point2.getX() - point.getX();
         dy = point2.getY() - point.getY();

      }

      if (Math.abs(dx) >= Math.abs(dy))
      {
         if (0L == Math.round(Math.abs(dx)))
         {
            return ModelerConstants.UNDEFINED_ORIENTATION_KEY;
         }

         // horizontal
         return (0.0 < dx) ? ModelerConstants.EAST_KEY : ModelerConstants.WEST_KEY;

      }
      else
      {
         // vertical
         return (0.0 < dy) ? ModelerConstants.SOUTH_KEY : ModelerConstants.NORTH_KEY;
      }
   }

   /**
    *
    * @param name
    * @param id
    * @return
    */
   private static String nameOrId(String name, String id)
   {
      return !isEmpty(name) ? name : id;
   }

   /**
    *
    * @param element
    * @param jto
    */
   private void loadDescription(BaseElement element, ModelElementJto jto)
   {
      Documentation description = Bpmn2ExtensionUtils.getDescription(element);

      if (description != null)
      {
         jto.description = description.getText();
      }
   }

   /**
    *
    * @param element
    * @param jto
    */
   private void loadExtensions(BaseElement element, ModelElementJto jto)
   {
      JsonElement attributes = Bpmn2ExtensionUtils.getExtensionAsJson(element, "core")
            .get(ModelerConstants.ATTRIBUTES_PROPERTY);

      if (attributes != null)
      {
         jto.attributes = attributes.getAsJsonObject();
      }
      else
      {
         jto.attributes = new JsonObject();
      }

   }

   private ApplicationJto loadApplicationExtensions(Interface element, ApplicationJto jto)
   {
      JsonObject coreJson = Bpmn2ExtensionUtils.getExtensionAsJson(element, "core");

      if (null != coreJson) {
    	  if (hasNotJsonNull(coreJson, ModelerConstants.APPLICATION_TYPE_PROPERTY))
    		  jto.applicationType = coreJson.get(ModelerConstants.APPLICATION_TYPE_PROPERTY).getAsString();
    	  if (hasNotJsonNull(coreJson, "stardustApplication")) {
    		  try {
	    		  JsonObject app = coreJson.get("stardustApplication").getAsJsonObject(); // TODO NECESSARY?
	    		  jto.oid = hasNotJsonNull(app, ModelerConstants.OID_PROPERTY) ? app.get(ModelerConstants.OID_PROPERTY).getAsLong() : null;
	    		  jto.id = app.get(ModelerConstants.ID_PROPERTY).getAsString();
	    		  jto.name = app.get(ModelerConstants.NAME_PROPERTY).getAsString();
	    		  jto.interactive = app.get(ModelerConstants.INTERACTIVE_PROPERTY).getAsBoolean();
    		  } catch (Exception e) {
    			  // TODO
    		  }
    	  }
    	  String uuid = getModelUuid(findContainingModel(element)) + ":"
			   + element.getId();

    	  jto.uuid = uuid;

    	  // TODO
    	  // contexts = ModelerConstants.CONTEXTS_PROPERTY
    	  // ACCESS_POINTS_PROPERTY
    	  // ...

      }
      return jto;
   }

   private Interface getInterfaceForOperation(Definitions defs, Operation operation) {
	   if (null == defs || null == operation) return null;
	   if (operation.eIsProxy()) operation = Bpmn2ProxyResolver.resolveOperationProxy(operation, defs);
	   return (Interface) operation.eContainer();
   }

   private String getApplicationFullId(Interface application, ActivityJto jto) {
	   if (null != application)
		   return getModelUuid(findContainingModel(application)) + ":"
                + application.getId();
	   return null;
   }

//   private String loadApplicationReference(Activity activity, ActivityJto jto) {
//	   final String refProperty = "interactiveApplicationRef";
//	   Interface bpmnInterface = null;
//	   if (ModelerConstants.USER_TASK_KEY.equals(jto.taskType)) {
//		   JsonObject coreJson = Bpmn2ExtensionUtils.getExtensionAsJson(activity, "core");
//		   if (hasNotJsonNull(coreJson, refProperty)) {
//			   String refId = coreJson.get("refProperty").getAsString();
//			   bpmnInterface = ModelInfo.findInterfaceById(ModelInfo.getDefinitions(activity), refId);
//		   }
//	   } else {
//		   bpmnInterface = ModelInfo.getInterfaceByOperationRef(activity, activity.eContainer());
//	   }
//	   if (null != bpmnInterface)
//		   return getModelUuid(findContainingModel(bpmnInterface)) + ":"
//                + bpmnInterface.getId();
//	   return null;
//   }

   @Override
   public void init()
   {
      // TODO Auto-generated method stub
   }

   @Override
   public void done()
   {
      // TODO Auto-generated method stub
   }
}
