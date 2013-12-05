/*******************************************************************************
 * Copyright (c) 2011, 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.stardust.model.xpdl.builder.strategy;

import static org.eclipse.stardust.common.CollectionUtils.newArrayList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.engine.api.runtime.*;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerModelManager;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

/**
 *
 * @author Marc.Gille
 *
 */
public class InMemoryModelManagementStrategy extends
		AbstractModelManagementStrategy {

	private static final String MODELS_DIR = "/process-models/";

	private ServiceFactory serviceFactory;
	private DocumentManagementService documentManagementService;
	private List<ModelType> models = new ArrayList<ModelType>();

   public void registerModel(ModelType model)
   {
      models.add(model);
   }

	public Map<String, ModelType> getModels()
	{
	    return getModels(true);
	}
	/**
	 *
	 */
	public List<ModelDescriptor> loadModels()
	{
	   List<ModelDescriptor> result = newArrayList();
	   for (ModelType model : models)
	   {
	      result.add(new ModelDescriptor(model.getId(), null, model, model));
	   }
		return result;
	}

    /**
     *
     */
    public ModelType loadModel(String id) {
       for (Iterator<ModelType> i = models.iterator(); i.hasNext();) {
          ModelType model = i.next();
          if (model.getId().equalsIgnoreCase(id)) {
             return model;
          }
       }
       return null;
    }

	/**
	 *
	 */
	public ModelType attachModel(String id) {
	    ModelType model = this.loadModel(id);
	    models.add(model);
		return model;
	}

   public void saveModel(ModelType model)
   {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      WebModelerModelManager manager = new WebModelerModelManager();
      manager.setModel(model);
      try
      {
         manager.save(URI.createURI(getModelFileName(model)), baos);
      }
      catch (IOException ex)
      {
         throw new InternalException("Unable to save model '" + model.getId() + "'", ex);
      }
      byte[] content = baos.toByteArray();

      DocumentManagementService service = getDocumentManagementService();
      Document document = service.getDocument(MODELS_DIR + model.getName() + ".xpdl");
      if (null == document)
      {
         DocumentInfo info = DmsUtils.createDocumentInfo(model.getName() + ".xpdl");
         info.setOwner(getServiceFactory().getWorkflowService().getUser().getAccount());
         info.setContentType("text/xhtml");
         document = service.createDocument(MODELS_DIR, info, content, null);
         // Create initial version
         service.versionDocument(document.getId(), null, null);
      }
      else
      {
         service.updateDocument(document, content, null, false, null, null, false);
      }
   }

	/**
	 *
	 * @param model
	 */
	public void deleteModel(ModelType model) {
		Document modelDocument = getDocumentManagementService().getDocument(
				MODELS_DIR + model.getName() + ".xpdl");

		if (modelDocument != null)
		{
			getDocumentManagementService().removeDocument(modelDocument.getId());
		}

		getModels().remove(model.getId());
	}

	/**
	 *
	 */
	public void versionizeModel(ModelType model) {
	}

	/**
	 *
	 * @return
	 */
	private DocumentManagementService getDocumentManagementService() {
		if (documentManagementService == null) {
			documentManagementService = getServiceFactory()
					.getDocumentManagementService();
		}

		return documentManagementService;
	}

	private ServiceFactory getServiceFactory() {
		// TODO Replace

		if (serviceFactory == null) {
			serviceFactory = ServiceFactoryLocator.get("motu", "motu");
		}

		return serviceFactory;
	}

	@Override
   public String getModelFileName(ModelType model)
   {
      return model.getId() + '.' + XpdlUtils.EXT_XPDL;
   }
   @Override
   public String getModelFilePath(ModelType model)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ModelUploadStatus uploadModelFile(String fileName, byte[] fileContent,
         boolean createNewVersion)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
