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

import java.util.List;
import java.util.Map;

import org.eclipse.stardust.model.xpdl.builder.common.EObjectUUIDMapper;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

/**
 *
 * @author Marc.Gille
 *
 */
public interface ModelManagementStrategy {

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

	/*
	 *
	 */
	void saveModel(ModelType model);

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
}
