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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.xpdl.builder.common.EObjectUUIDMapper;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

/**
 *
 * @author Marc.Gille
 *
 */
public interface ModelManagementStrategy {

   enum ModelUploadStatus
   {
      NEW_MODEL_CREATED, MODEL_ALREADY_EXISTS, NEW_MODEL_VERSION_CREATED
   };

    /**
     * @param params
     */
    void initialize(Map<String, Object> params);

	/**
    *
    * @return
    */
    ModelType loadModel(String id);

    EObjectUUIDMapper uuidMapper();

	/**
	 *
	 * @param id
	 * @return
	 */
	ModelType attachModel(String id);

   ModelType attachModel(String id, String name, EObject model);

	/**
	 *
	 * @return
	 */
	Map<String, ModelType>  getModels();

	/**
	 *
	 * @param reload
	 * @return
	 */
	Map<String, ModelType>  getModels(boolean reload);

   /**
    * Returns a stable, system-wide unique ID for the given model.
    * <p>
    * When being used in multiple sessions of the same deployment, but with the logically
    * same model, this should provide an identical ID.
    *
    * @return a unique ID (there format of this ID does not matter to callers)
    */
   String getUniqueModelId(EObject model);

	/*
	 *
	 */
	void saveModel(ModelType model);

   /*
   *
   */
  void saveModel(ModelType model, String oldModelId);
		
	/**
	 *
	 * @param model
	 */
	void deleteModel(ModelType model);

	/**
	 *
	 */
	void versionizeModel(ModelType model);

	/**
	 * @param model
	 * @return
	 */
	String getModelFileName(ModelType model);

   /**
    * @param model
    * @return
    */
   String getModelFilePath(ModelType model);


   /**
    * @param fileName
    * @param fileContent
    * @param createNewVersion
    * @return
    */
   ModelUploadStatus uploadModelFile(String fileName, byte[] fileContent,
         boolean createNewVersion);
}