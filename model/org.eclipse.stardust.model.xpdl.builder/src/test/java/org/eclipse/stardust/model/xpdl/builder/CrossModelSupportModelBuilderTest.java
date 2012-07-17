package org.eclipse.stardust.model.xpdl.builder;

import static junit.framework.Assert.assertTrue;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ADMINISTRATOR_ROLE;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.DEFAULT_CONTEXT;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.eclipse.stardust.model.xpdl.builder.BpmActivityDef;
import org.eclipse.stardust.model.xpdl.builder.BpmActivitySequenceDef;
import org.eclipse.stardust.model.xpdl.builder.BpmModelDef;
import org.eclipse.stardust.model.xpdl.builder.BpmProcessDef;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementHelper;
import org.eclipse.stardust.model.xpdl.builder.strategy.ModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.strategy.InMemoryModelManagementStrategy;
import org.eclipse.stardust.model.xpdl.builder.utils.MBFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.junit.Before;
import org.junit.Test;


public class CrossModelSupportModelBuilderTest
{

   private ModelType model;

   @Before
   public void initHelloWorldModel()
   {
      // specify transitions explicitly
      ModelType providerModel = newBpmModel().withName("ProviderModel").build();
      ModelType consumerModel = newBpmModel().withName("ConsumerModel").build();
      ModelManagementStrategy strategy = new InMemoryModelManagementStrategy();
      ModelManagementHelper.getInstance().setModelManagementStrategy(strategy);
      strategy.loadModels().add(consumerModel);
      strategy.loadModels().add(providerModel);
      MBFacade.createPrimitiveData(providerModel, "ProvidedPrimitive", "ProvidedPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      MBFacade.createTypeDeclaration(providerModel, "ProvidedTypeDeclaration", "ProvidedTypeDeclaration");
      MBFacade.createStructuredData(consumerModel, "ProviderModel", "ProvidedComposite1", "ProvidedComposite1", "ProvidedTypeDeclaration");
      byte[] modelContent = XpdlModelIoUtils.saveModel(consumerModel);
      System.out.println(new String(modelContent));
   }

   @Test
   public void verifyStringVariable()
   {
      /*DataType aString = XpdlModelUtils.findElementById(model.getData(), "Name");

      assertThat(aString, not(is(nullValue())));
      assertTrue(aString.isSetElementOid());*/
   }

}
