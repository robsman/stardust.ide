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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.engine.api.runtime.DmsUtils;
import org.eclipse.stardust.engine.api.runtime.Document;
import org.eclipse.stardust.engine.api.runtime.DocumentInfo;
import org.eclipse.stardust.engine.api.runtime.DocumentManagementService;
import org.eclipse.stardust.engine.api.runtime.ServiceFactory;
import org.eclipse.stardust.engine.api.runtime.ServiceFactoryLocator;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
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

	/**
	 *
	 */
	public void saveModel(ModelType model) {
			String modelContent = new String(XpdlModelIoUtils.saveModel(model));
			Document modelDocument;

				modelDocument = getDocumentManagementService().getDocument(
						MODELS_DIR + model.getName() + ".xpdl");

			if (null == modelDocument) {
				DocumentInfo docInfo = DmsUtils.createDocumentInfo(model.getName()
						+ ".xpdl");

				docInfo.setOwner(getServiceFactory().getWorkflowService().getUser()
						.getAccount());
				docInfo.setContentType("text/xhtml");

				modelDocument = getDocumentManagementService().createDocument(
						MODELS_DIR, docInfo, modelContent.getBytes(), null);

				// Create initial version

				getDocumentManagementService().versionDocument(
						modelDocument.getId(), null);
			} else {
				getDocumentManagementService().updateDocument(modelDocument,
						modelContent.getBytes(), null, false, null, false);
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

	/**
	 *
	 * @param modelDocument
	 * @return
	 */
	private byte[] readModelContext(Document modelDocument) {
		return getDocumentManagementService().retrieveDocumentContent(
				modelDocument.getId());
	}
   @Override
   public String getModelFileName(ModelType model)
   {
      // TODO Auto-generated method stub
      return null;
   }
   @Override
   public String getModelFilePath(ModelType model)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
