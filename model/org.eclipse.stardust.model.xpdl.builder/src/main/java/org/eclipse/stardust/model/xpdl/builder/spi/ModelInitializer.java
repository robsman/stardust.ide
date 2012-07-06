package org.eclipse.stardust.model.xpdl.builder.spi;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public interface ModelInitializer
{
   void initializeModel(ModelType model);
}
