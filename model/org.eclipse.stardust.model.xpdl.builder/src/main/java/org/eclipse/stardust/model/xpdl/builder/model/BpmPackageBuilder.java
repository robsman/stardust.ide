/*******************************************************************************
 * Copyright (c) 2012, 2013 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.model;

import java.util.Date;
import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.config.CurrentVersion;
import org.eclipse.stardust.common.config.ExtensionProviderUtils;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.model.xpdl.builder.BpmModelDef;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractIdentifiableElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.defaults.DefaultElementsInitializer;
import org.eclipse.stardust.model.xpdl.builder.defaults.DefaultTypesInitializer;
import org.eclipse.stardust.model.xpdl.builder.spi.ModelInitializer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.ModelOidUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;

public class BpmPackageBuilder extends AbstractIdentifiableElementBuilder<ModelType, BpmPackageBuilder>
{
   private BpmModelDef definition;

   public BpmPackageBuilder()
   {
      super(doCreate());

      withName("New Workflow Model");
   }

   private static ModelType doCreate()
   {
      ModelType model = F_CWM.createModelType();      
      model.setOid(0);
      
      long maxUsedOid = ModelUtils.getMaxUsedOid(model);      
      ModelOidUtil.register(model, maxUsedOid, null);
      
      return model;
   }

   @Override
   protected String deriveDefaultElementId()
   {
      return "NewWorkflowModel";
   }

   public static BpmPackageBuilder newModel()
   {
      return new BpmPackageBuilder();
   }

   @Override
   protected ModelType finalizeElement()
   {
      super.finalizeElement();

      element.setVendor(XMLConstants.VENDOR_NAME);
      element.setCarnotVersion(CurrentVersion.getVersionName());

      element.setScript(XpdlFactory.eINSTANCE.createScriptType());
      element.getScript().setType("text/ecmascript");
      element.setTypeDeclarations(XpdlFactory.eINSTANCE.createTypeDeclarationsType());

      element.setCreated(new Date().toString());

      element.setAuthor(System.getProperty("user.name"));

      element.setModelOID(0);

      // add default elements
      List<ModelInitializer> modelInitializers = CollectionUtils.newArrayList();
      modelInitializers.add(new DefaultTypesInitializer());
      modelInitializers.add(new DefaultElementsInitializer());
      // allow for third party extensions
      modelInitializers.addAll(ExtensionProviderUtils.getExtensionProviders(ModelInitializer.class));

      for (ModelInitializer initializer : modelInitializers)
      {
         initializer.initializeModel(element);
      }

      if (null != definition)
      {
         definition.build(element);
      }

      return element;
   }

   public BpmPackageBuilder definedAs(BpmModelDef definition)
   {
      // TODO finalize and add elements
      this.definition = definition;

      return this;
   }
}