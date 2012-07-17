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
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.junit.Before;
import org.junit.Test;


public class CrossModelSupportModelBuilderTest
{
   

   @Before
   public void initCrossModeling()
   {      
      ModelType providerModel = newBpmModel().withName("ProviderModel").build();
      ModelType consumerModel = newBpmModel().withName("ConsumerModel").build();
      ModelManagementStrategy strategy = new InMemoryModelManagementStrategy();
      ModelManagementHelper.getInstance().setModelManagementStrategy(strategy);
      strategy.loadModels().add(consumerModel);
      strategy.loadModels().add(providerModel);
      MBFacade.createRole(providerModel, "Adminitrator", "Administrator");
      MBFacade.createRole(consumerModel, "Adminitrator", "Administrator");
      MBFacade.createPrimitiveData(providerModel, "ProvidedPrimitive", "ProvidedPrimitive", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
      MBFacade.createTypeDeclaration(providerModel, "ProvidedComposite", "ProvidedComposite");
      ProcessDefinitionType providedProcess = MBFacade.createProcess(providerModel, "ProvidedProcess", "ProvidedProcess");
      ProcessDefinitionType consumerProcess = MBFacade.createProcess(consumerModel, "ConsumerProcess", "ConsumerProcess");
      MBFacade.createStructuredData(consumerModel, "ProviderModel", "ProvidedComposite1", "ProvidedComposite1", "ProvidedComposite");
      long maxOid = XpdlModelUtils.getMaxUsedOid(consumerModel);
      MBFacade.createActivity("ConsumerModel", consumerProcess, "Subprocess", null, "ProvidedProcess1", "ProvidedProcess1", null, "ProviderModel:ProvidedProcess", maxOid);
      byte[] modelContent = XpdlModelIoUtils.saveModel(consumerModel);
      System.out.println(new String(modelContent));
   }

   @Test
   public void verifyStringVariable()
   {
      //Todo: Verify things here and in other verify methods
   }

}
