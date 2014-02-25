package org.eclipse.stardust.ui.web.modeler.bpmn2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.JsonObject;

import org.eclipse.stardust.ui.web.modeler.bpmn2.compatibility.AdonisImporter;
import org.eclipse.stardust.ui.web.modeler.marshaling.JsonMarshaller;
import org.eclipse.stardust.ui.web.modeler.model.di.LaneSymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.PoolSymbolJto;
import org.eclipse.stardust.ui.web.modeler.model.di.ProcessDiagramJto;
import org.eclipse.stardust.ui.web.modeler.spi.ModelPersistenceHandler.ModelDescriptor;

public class AdonisImportFixesTest
{
   private final Bpmn2PersistenceHandler persistenceHandler = new Bpmn2PersistenceHandler();

   private Definitions model;

   @Before
   public void loadMoel() throws IOException
   {
      FileInputStream fis = new FileInputStream("c:/tmp/CoBa/Dokumenteneinreichung und pruefung.bpmn");
      try
      {
         ModelDescriptor<Definitions> modelDescriptor = persistenceHandler.loadModel("Dokumenteneinreichung und pruefung.bpmn",
               fis);
         this.model = modelDescriptor.model;
      }
      finally
      {
         fis.close();
      }
   }

   @Test
   @Ignore
   public void testGeometryFixes()
   {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      persistenceHandler.saveModel(model, baos);

      System.out.println(new String(baos.toByteArray()));
   }

   @Test
   @Ignore
   public void testAnchorPointInference()
   {
      Bpmn2Binding binding = new Bpmn2Binding(null);
      Bpmn2ModelMarshaller marshaller = (Bpmn2ModelMarshaller) binding.getMarshaller();

      JsonObject diagramJson = marshaller.toProcessDiagramJson(model, "P_14783");

      ProcessDiagramJto diagramJto = new JsonMarshaller().gson().fromJson(diagramJson, ProcessDiagramJto.class);
      for (PoolSymbolJto poolJto : diagramJto.poolSymbols.values())
      {
         for (LaneSymbolJto laneJto : poolJto.laneSymbols)
         {
         }
      }
   }
}
