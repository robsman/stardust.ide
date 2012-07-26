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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public abstract class AbstractModelManagementStrategy implements ModelManagementStrategy {

	private Map<String, ModelType> models = new HashMap<String, ModelType>();

	/**
	 * 
	 */
	public Map<String, ModelType> getModels()
	{
		return getModels(false);
	}

	/**
	 * 
	 */
	public Map<String, ModelType> getModels(boolean reload)
	{
		if (reload)
		{
			for (ModelType model: loadModels())
			{
			models.put(model.getId(), model);
			}
		}
		
		return models;
	}

	/**
	 * 
	 */
	public abstract List<ModelType> loadModels();
	
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
}
