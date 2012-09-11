/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.strategy;

import static org.eclipse.stardust.common.CollectionUtils.newHashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.xpdl.builder.common.EObjectUUIDMapper;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public abstract class AbstractModelManagementStrategy implements ModelManagementStrategy {

	private Map<String, ModelType> xpdlModels = newHashMap();

   private Map<String, EObject> models = newHashMap();

   private final EObjectUUIDMapper eObjectUUIDMapper = new EObjectUUIDMapper();

	/**
	 *
	 */
	public Map<String, ModelType> getModels()
	{
		return getModels(false);
	}

	public EObject getOriginalModel(String modelId)
	{
	   return models.get(modelId);
	}

   /**
	 *
	 */
   public Map<String, ModelType> getModels(boolean reload)
   {
      if (reload)
      {
         xpdlModels.clear();
         for (ModelDescriptor modelDescriptor : loadModels())
         {
            xpdlModels.put(modelDescriptor.id, modelDescriptor.xpdlModel);
            // register original representation (in order to smoothly transition to BPMN2)
            models.put(modelDescriptor.id, modelDescriptor.model);
         }
      }

      return xpdlModels;
   }

	public EObjectUUIDMapper uuidMapper()
	{
	   return eObjectUUIDMapper;
	}

   /**
    * Maps the model and it's elements to a UUID which will remain constant through out the session.
    * It can be used to identify elements uniquely on client and server side.
    *
    * This method needs to be called whenever a model is loaded.
    */
   protected void loadEObjectUUIDMap(ModelType model)
   {
      // Load if not already loaded.
      if (null == eObjectUUIDMapper.getUUID(model))
      {
         eObjectUUIDMapper.map(model);
         for (Iterator<EObject> i = model.eAllContents(); i.hasNext();)
         {
            eObjectUUIDMapper.map(i.next());
         }
      }
   }

	/**
	 *
	 */
   // TODO refactor to a proper API, this probably requires moving model management
   // strategy out of builder
	protected abstract List<ModelDescriptor> loadModels();

    /**
     *
     */
    public abstract ModelType loadModel(String id);

	/**
	 *
	 */
	public abstract ModelType attachModel(String id);

	/**
	 *
	 */
	public abstract void saveModel(ModelType model);

	/**
	 *
	 */
	public abstract void deleteModel(ModelType model);

	/**
	 *
	 */
	public abstract void versionizeModel(ModelType model);

	protected static class ModelDescriptor
	{
      public final String id;
	   public final String fileName;
	   public final EObject model;
	   public final ModelType xpdlModel;

      public ModelDescriptor(String id, String fileName, EObject model,
            ModelType xpdlModel)
      {
         this.id = id;
         this.fileName = fileName;
         this.model = model;
         this.xpdlModel = xpdlModel;
      }
	}
}
