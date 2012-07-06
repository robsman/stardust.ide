package org.eclipse.stardust.model.xpdl.builder;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ADMINISTRATOR_ROLE;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.DEFAULT_CONTEXT;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newInDataMapping;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualTrigger;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOtherwiseTransition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOutDataMapping;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newPrimitiveVariable;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDiagram;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newTransition;
import static org.eclipse.stardust.model.xpdl.builder.diagram.BpmDiagramBuilder.newActivitySymbol;
import static org.eclipse.stardust.model.xpdl.builder.diagram.BpmDiagramBuilder.newJoinGatewaySymbol;
import static org.eclipse.stardust.model.xpdl.builder.diagram.BpmDiagramBuilder.newSplitGatewaySymbol;
import static org.eclipse.stardust.model.xpdl.builder.diagram.BpmDiagramBuilder.newTransitionConnection;
import static org.junit.Assert.assertSame;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.builder.activity.BpmRouteActivityBuilder;
import org.eclipse.stardust.model.xpdl.builder.diagram.BpmDiagramBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.junit.Before;
import org.junit.Test;



public class ModelContainingGatewaysTest
{

   private ModelType gatewaysModel;

   @Before
   public void initGatewaysModel()
   {
      this.gatewaysModel = newBpmModel().withName("Gateways Model").build();

      DataType aString = newPrimitiveVariable(gatewaysModel).withId("aString")
            .ofType(Type.String)
            .build();

      ProcessDefinitionType testProcess = newProcessDefinition(gatewaysModel).withIdAndName(
            "XOR_GATEWAY_PROCESS", "XOR Gateway Process").build();

      newManualTrigger(testProcess).accessibleTo(ADMINISTRATOR_ROLE).build();

      ActivityType step1 = newManualActivity(testProcess).withId("Step_1")
            .havingDefaultPerformer(ADMINISTRATOR_ROLE)
            .build();
      ActivityType gateway = BpmRouteActivityBuilder.newRouteActivity(testProcess)
            .withId("xorGateway")
            .usingControlFlow(JoinSplitType.XOR_LITERAL, JoinSplitType.XOR_LITERAL)
            .build();
      ActivityType step2a = newManualActivity(testProcess).withId("Step_2a")
            .havingDefaultPerformer(ADMINISTRATOR_ROLE)
            .build();
      ActivityType step2b = newManualActivity(testProcess).withId("Step_2b")
            .havingDefaultPerformer(ADMINISTRATOR_ROLE)
            .build();

      TransitionType ts1g = newTransition(testProcess).betweenActivities(step1, gateway)
            .build();
      TransitionType tg2a = newTransition(testProcess).betweenActivities(gateway, step2a)
            .onCondition("aString == 'a';")
            .build();
      TransitionType tg2b = newOtherwiseTransition(testProcess).betweenActivities(gateway, step2b)
            .build();

      newOutDataMapping(step1).inContext(DEFAULT_CONTEXT)
            .toVariable(aString)
            .build();
      newInDataMapping(step2a).inContext(DEFAULT_CONTEXT)
            .fromVariable(aString)
            .build();

      DiagramType diagram = newProcessDiagram(testProcess)
            .withName("Default Diagram")
            .build();
      PoolSymbol canvas = BpmDiagramBuilder.newDefaultPool(diagram).build();

      ActivitySymbolType symStep1 = newActivitySymbol(canvas).forActivity(step1)
            .atPosition(205, 147)
            .build();

      ActivitySymbolType symGatewayActivity = newActivitySymbol(canvas).forActivity(gateway)
            .atPosition(286, 233).withSize(20, 20)
            .build();
      GatewaySymbol symGatewayJoin = newJoinGatewaySymbol(canvas).forActivitySymbol(symGatewayActivity)
            .atPosition(275, 222).withSize(42, 42)
            .build();
      GatewaySymbol symGatewaySplit = newSplitGatewaySymbol(canvas).forActivitySymbol(symGatewayActivity)
            .atPosition(275, 222).withSize(42, 42)
            .build();

      ActivitySymbolType symStep2a = newActivitySymbol(canvas).forActivity(step2a)
            .atPosition(75, 307)
            .build();
      ActivitySymbolType symStep2b = newActivitySymbol(canvas).forActivity(step2b)
            .atPosition(330, 307)
            .build();

      newTransitionConnection(canvas).forTransition(ts1g).fromBottomOf(symStep1).to(symGatewayJoin)
            .build();
      newTransitionConnection(canvas).forTransition(tg2a)
            .fromLeftOf(symGatewaySplit).toTopOf(symStep2a)
            .build();
      newTransitionConnection(canvas).forTransition(tg2b)
            .fromRightOf(symGatewaySplit).toTopOf(symStep2b)
            .build();

      BpmModelBuilder.assignMissingElementOids(gatewaysModel);

      byte[] modelXml = XpdlModelIoUtils.saveModel(gatewaysModel);
      System.out.println(new String(modelXml));
   }

   @Test
   public void verifyStringVariable()
   {
      DataType aString = XpdlModelUtils.findElementById(gatewaysModel.getData(), "aString");

      assertNotNull(aString);
      assertTrue(aString.isSetElementOid());
   }

   @Test
   public void verifyXorGateway()
   {
      ProcessDefinitionType xorGatewayProcess = XpdlModelUtils.findElementById(
            gatewaysModel.getProcessDefinition(), "XOR_GATEWAY_PROCESS");

      assertNotNull(xorGatewayProcess);

      ActivityType xorGateway = XpdlModelUtils.findElementById(
            xorGatewayProcess.getActivity(), "xorGateway");
      assertNotNull(xorGateway);

      assertSame(JoinSplitType.XOR_LITERAL, xorGateway.getJoin());
      assertSame(JoinSplitType.XOR_LITERAL, xorGateway.getSplit());
   }
}
