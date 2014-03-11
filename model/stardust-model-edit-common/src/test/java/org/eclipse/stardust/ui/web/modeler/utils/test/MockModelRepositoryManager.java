package org.eclipse.stardust.ui.web.modeler.utils.test;

import static com.google.common.io.ByteStreams.toByteArray;
import static com.google.common.io.Closeables.closeQuietly;
import static org.eclipse.stardust.common.CollectionUtils.newArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import org.eclipse.stardust.engine.api.runtime.Document;
import org.eclipse.stardust.engine.api.runtime.DocumentManagementService;
import org.eclipse.stardust.engine.api.runtime.Folder;
import org.eclipse.stardust.ui.web.modeler.service.DefaultModelManagementStrategy;

@Qualifier("default")
@Scope("session")
public class MockModelRepositoryManager
{
   @javax.annotation.Resource
   private ApplicationContext springContext;

   @javax.annotation.Resource
   private MockServiceFactoryLocator mockServiceFactoryLocator;

   public void loadModelsFromPackage(Package packageReference) throws IOException
   {
      loadModelsFromPackage(packageReference.getName());
   }

   public void loadModelsFromPackage(String packageName) throws IOException
   {
      String resoucePattern = "classpath*:"
            + packageName.replace('.', '/') + "/*";

      defineMockModelRepository(mockServiceFactoryLocator.get()
            .getDocumentManagementService(), springContext, resoucePattern);
   }

   public static void defineMockModelRepository(DocumentManagementService dmsService,
         ResourcePatternResolver resolver, String resoucePattern) throws IOException
   {
      List<Document> modelDocs = newArrayList();
      for (final Resource res : resolver.getResources(resoucePattern))
      {
         if (res.getFilename().endsWith(".class"))
         {
            // skip classes
            continue;
         }

         String fileName = res.getFilename();

         Document modelDoc = Mockito.mock(Document.class);
         Mockito.when(modelDoc.getId()).thenReturn(res.getURI().toString());
         Mockito.when(modelDoc.getName()).thenReturn(fileName);
         Mockito.when(modelDoc.getSize()).thenReturn(res.contentLength());
         Mockito.when(modelDoc.getDateLastModified()).thenReturn(new Date(res.lastModified()));
         Mockito.when(modelDoc.getPath()).thenReturn(res.getURI().toString());

         modelDocs.add(modelDoc);

         Mockito.when(dmsService.retrieveDocumentContent(modelDoc.getId())).thenAnswer(
               new Answer<byte[]>()
               {
                  @Override
                  public byte[] answer(InvocationOnMock invocation) throws Throwable
                  {
                     InputStream isModel = res.getInputStream();
                     try
                     {
                        return toByteArray(isModel);
                     }
                     finally
                     {
                        closeQuietly(isModel);
                     }
                  }
               });
      }

      Folder modelsFolder = Mockito.mock(Folder.class);
      Mockito.when(modelsFolder.getDocuments()).thenReturn(modelDocs);

      Mockito.when(dmsService.getFolder(DefaultModelManagementStrategy.MODELS_DIR))
            .thenReturn(modelsFolder);
   }
}
