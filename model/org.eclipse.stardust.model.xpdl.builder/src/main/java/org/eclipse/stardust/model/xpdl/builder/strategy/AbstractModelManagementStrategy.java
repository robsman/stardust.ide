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
