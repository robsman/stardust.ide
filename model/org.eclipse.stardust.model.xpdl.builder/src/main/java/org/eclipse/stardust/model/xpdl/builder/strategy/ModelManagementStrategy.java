/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
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
	public List<ModelType> loadModels();
	
	/**
    * 
    * @return
    */
    public ModelType loadModel(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ModelType attachModel(String id);

	/**
	 * 
	 * @return
	 */
	public Map<String, ModelType>  getModels();
	
	/**
	 * 
	 * @param reload
	 * @return
	 */
	public Map<String, ModelType>  getModels(boolean reload);
	
	/*
	 * 
	 */
	public void saveModel(ModelType model);
	
	/**
	 * 
	 * @param model
	 */
	public void deleteModel(ModelType model);
	
	/**
	 * 
	 */
	public void versionizeModel(ModelType model);
}
