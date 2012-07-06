package org.eclipse.stardust.model.xpdl.builder.diagram;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;



public class BpmDiagramBuilder extends AbstractElementBuilder<DiagramType, BpmDiagramBuilder>
{
   protected ModelType model;

   protected ProcessDefinitionType process;

   public static BpmDiagramBuilder newProcessDiagram(ProcessDefinitionType process)
   {
      BpmDiagramBuilder builder = new BpmDiagramBuilder();

      builder.forProcess(process);

      return builder;
   }

   public static BpmPoolBuilder newDefaultPool(DiagramType diagram)
   {
      BpmPoolBuilder builder = new BpmPoolBuilder();

      builder.inContainer(diagram).coveringTheWholeDiagram();

      return builder;
   }

   public static BpmDiagramBuilder newModelDiagram(ModelType model)
   {
      BpmDiagramBuilder builder = new BpmDiagramBuilder();

      builder.forModel(model);

      return builder;
   }

   public static BpmActivitySymbolBuilder newActivitySymbol(ISymbolContainer container)
   {
      BpmActivitySymbolBuilder builder = new BpmActivitySymbolBuilder();

      builder.inContainer(container);

      return builder;
   }

   public static BpmGatewaySymbolBuilder newGatewaySymbol(ISymbolContainer container)
   {
      return BpmGatewaySymbolBuilder.newGatewaySymbol(container);
   }

   public static BpmGatewaySymbolBuilder newJoinGatewaySymbol(ISymbolContainer container)
   {
      return BpmGatewaySymbolBuilder.newJoinGatewaySymbol(container);
   }

   public static BpmGatewaySymbolBuilder newSplitGatewaySymbol(ISymbolContainer container)
   {
      return BpmGatewaySymbolBuilder.newSplitGatewaySymbol(container);
   }

   public static BpmTransitionConnectionBuilder newTransitionConnection(ISymbolContainer container)
   {
      BpmTransitionConnectionBuilder builder = new BpmTransitionConnectionBuilder();

      builder.inContainer(container);

      return builder;
   }

   protected BpmDiagramBuilder()
   {
      super(F_CWM.createDiagramType());
   }

   @Override
   protected DiagramType finalizeElement()
   {
      DiagramType element = super.finalizeElement();

      if (null == model)
      {
         throw new NullPointerException("Model must be set.");
      }

      if (null != process)
      {
         process.getDiagram().add(element);
      }
      else
      {
         model.getDiagram().add(element);
      }

      if ( !element.isSetMode())
      {
         element.setMode(DiagramModeType.MODE_450_LITERAL);
      }
      if ( !element.isSetOrientation())
      {
         element.setOrientation(OrientationType.VERTICAL_LITERAL);
      }

      return element;
   }

   public BpmDiagramBuilder inProcess(ProcessDefinitionType process)
   {
      setProcess(process);

      return self();
   }

   public BpmDiagramBuilder forProcess(ProcessDefinitionType process)
   {
      setProcess(process);

      return self();
   }

   public BpmDiagramBuilder withName(String name)
   {
      element.setName(name);

      return self();
   }

   public ProcessDefinitionType process()
   {
      return process;
   }

   protected void setProcess(ProcessDefinitionType process)
   {
      if (null == this.process)
      {
         if (null != process)
         {
            this.process = process;

            ModelType containingModel = XpdlModelUtils.findContainingModel(process);
            if (null != containingModel)
            {
               setModel(containingModel);
            }
         }
      }
      else
      {
         if (this.process != process)
         {
            throw new IllegalArgumentException("Process Definition must only be set once.");
         }
      }
   }

   public BpmDiagramBuilder inModel(ModelType model)
   {
      setModel(model);

      return self();
   }

   public BpmDiagramBuilder forModel(ModelType model)
   {
      return inModel(model);
   }

   public ModelType model()
   {
      return model;
   }

   protected void setModel(ModelType model)
   {
      if (null == this.model)
      {
         if (null != model)
         {
            this.model = model;
         }
      }
      else
      {
         if (this.model != model)
         {
            throw new IllegalArgumentException("Model must only be set once.");
         }
      }
   }
}
