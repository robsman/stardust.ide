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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

/**
 *
 * @author Marc.Gille
 *
 */
public class InMemoryModelManagementStrategy extends
		AbstractModelManagementStrategy {

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
	      loadEObjectUUIDMap(model);
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

	@Override
   public String getUniqueModelId(EObject model) {
	   for (ModelType xpdlModel : models) {
         if (model == xpdlModel) {
            return xpdlModel.getId();
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

   }

	/**
	 *
	 * @param model
	 */
	public void deleteModel(ModelType model) {
	}

	/**
	 *
	 */
	public void versionizeModel(ModelType model) {
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
