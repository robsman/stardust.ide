package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.spi.IModelAdapterFactory;


public class ModelAdapterFactory implements IModelAdapterFactory
{

   public IModel adapt(ModelType model)
   {
      return (IModelAdapter) IModelAdapter.FACTORY.createAdapter(model);
   }

}
