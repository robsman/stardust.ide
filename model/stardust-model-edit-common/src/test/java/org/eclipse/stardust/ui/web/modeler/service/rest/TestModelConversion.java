package org.eclipse.stardust.ui.web.modeler.service.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.eclipse.stardust.engine.api.runtime.Document;
import org.eclipse.stardust.engine.api.runtime.DocumentManagementService;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.ui.web.modeler.marshaling.JsonMarshaller;
import org.eclipse.stardust.ui.web.modeler.model.conversion.BeanInvocationExecutor;
import org.eclipse.stardust.ui.web.modeler.model.conversion.RequestExecutor;
import org.eclipse.stardust.ui.web.modeler.service.DefaultModelManagementStrategy;
import org.eclipse.stardust.ui.web.modeler.service.ModelService;
import org.eclipse.stardust.ui.web.modeler.service.ModelerSessionController;
import org.eclipse.stardust.ui.web.modeler.utils.test.MockModelRepositoryManager;
import org.eclipse.stardust.ui.web.modeler.utils.test.MockServiceFactoryLocator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"../../web-modeler-test-context.xml"})
public class TestModelConversion
{
   @Resource
   MockServiceFactoryLocator mockServiceFactoryLocator;

   @Resource
   MockModelRepositoryManager mockModelRepositoryManager;

   @Resource
   JsonMarshaller jsonIo;

   @Resource
   ModelService modelService;

   @Resource
   private ModelerSessionController restController;

   @Before
   public void initServiceFactory() throws IOException
   {
      mockModelRepositoryManager.loadModelsFromPackage(getClass().getPackage());
   }

   @Test
   public void testToXpdlModelConversion() throws Exception
   {
      // model ID of "Statement P1" BPMN2 test model
      final String srcModelId = "35258023-f7c1-4c12-a0ce-19de63b1d733";

      assertThat(modelService, is(not(nullValue())));

      assertThat(restController, is(not(nullValue())));

      RequestExecutor requestExecutor = new BeanInvocationExecutor(jsonIo, modelService,
            restController);

      JsonObject cmdJson = new JsonObject();

      cmdJson.addProperty("commandId", "model.clone");
      cmdJson.addProperty("modelId", srcModelId);
      cmdJson.add("changeDescriptions", new JsonArray());
      ((JsonArray) cmdJson.get("changeDescriptions")).add(new JsonObject());


      JsonObject changeJson = new JsonObject();
      changeJson.addProperty("targetFormat", "xpdl");
      ((JsonObject) ((JsonArray) cmdJson.get("changeDescriptions")).get(0)).add(
            "changes", changeJson);

      JsonObject cloneModelResult = requestExecutor.applyChange(cmdJson);

      String newModelId = cloneModelResult.getAsJsonObject("changes")
            .getAsJsonArray("added").get(0).getAsJsonObject()
            .get(ModelerConstants.ID_PROPERTY).getAsString();

      assertThat(newModelId, is(notNullValue()));

      DocumentManagementService dmsService = mockServiceFactoryLocator.get()
            .getDocumentManagementService();
      Mockito.when(
            dmsService.createDocument(Mockito.eq(DefaultModelManagementStrategy.MODELS_DIR),
                  Mockito.<Document> any(), Mockito.<byte[]> any(), Mockito.anyString()))
            .thenAnswer(new Answer<Document>()
            {
               @Override
               public Document answer(InvocationOnMock invocation) throws Throwable
               {
                  // trace the generated model
                  System.out.println(new String((byte[]) invocation.getArguments()[2]));
                  return null;
               }
            });

      modelService.saveModel(newModelId);
   }

   @Test
   public void testFromXpdlModelConversion() throws Exception
   {
      // model ID of "ModelConversionReferenceModel" XPDL test model
      final String srcModelId = "ModelConversionReferenceModel";

      assertThat(modelService, is(not(nullValue())));

      assertThat(restController, is(not(nullValue())));

      RequestExecutor requestExecutor = new BeanInvocationExecutor(jsonIo, modelService,
            restController);

      JsonObject cmdJson = new JsonObject();

      cmdJson.addProperty("commandId", "model.clone");
      cmdJson.addProperty("modelId", srcModelId);
      cmdJson.add("changeDescriptions", new JsonArray());
      ((JsonArray) cmdJson.get("changeDescriptions")).add(new JsonObject());


      JsonObject changeJson = new JsonObject();
      changeJson.addProperty("targetFormat", "bpmn2");
      ((JsonObject) ((JsonArray) cmdJson.get("changeDescriptions")).get(0)).add(
            "changes", changeJson);

      JsonObject cloneModelResult = requestExecutor.applyChange(cmdJson);

      String newModelId = cloneModelResult.getAsJsonObject("changes")
            .getAsJsonArray("added").get(0).getAsJsonObject()
            .get(ModelerConstants.ID_PROPERTY).getAsString();

      assertThat(newModelId, is(notNullValue()));

      DocumentManagementService dmsService = mockServiceFactoryLocator.get()
            .getDocumentManagementService();
      Mockito.when(
            dmsService.createDocument(Mockito.eq(DefaultModelManagementStrategy.MODELS_DIR),
                  Mockito.<Document> any(), Mockito.<byte[]> any(), Mockito.anyString()))
            .thenAnswer(new Answer<Document>()
            {
               @Override
               public Document answer(InvocationOnMock invocation) throws Throwable
               {
                  // trace the generated model
                  System.out.println(new String((byte[]) invocation.getArguments()[2]));
                  return null;
               }
            });

      modelService.saveModel(newModelId);
   }

}
