/*******************************************************************************
 * Copyright (c) 2014 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    apalumbo (ITpearls AG) - Stardust Runtime Extension
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.itemaware;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.ItemKind;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ImportUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ItemDefinitionPropertiesAdapter;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

public class StardustItemDefinitionRefFeatureDescriptor<T extends BaseElement>
      extends FeatureDescriptor<T>
{

   protected ImportUtil importer = new ImportUtil();
   
   public StardustItemDefinitionRefFeatureDescriptor(ExtendedPropertiesAdapter<T> owner,
         T object, EStructuralFeature feature)
   {
      super(owner, object, feature);
      Assert.isTrue(RootElement.class.isAssignableFrom(feature.getEType()
            .getInstanceClass()));
   }

   @Override
   public String getLabel()
   {
      return ItemDefinitionPropertiesAdapter.getLabel();
   }

   @Override
   public String getTextValue()
   {
      ItemDefinition itemDefinition = (ItemDefinition) object.eGet(feature);
      return ItemDefinitionPropertiesAdapter.getDisplayName(itemDefinition);
   }

   @Override
   public EObject createFeature(Resource resource, EClass eClass)
   {
      ItemDefinition itemDefinition = ItemDefinitionPropertiesAdapter
            .createItemDefinition(object.eResource());
      return itemDefinition;
   }

   @Override
   public Object getValue()
   {
      ItemDefinition itemDefinition = (ItemDefinition) object.eGet(feature);
      return ItemDefinitionPropertiesAdapter.getStructureRef(itemDefinition);
   }

   @Override
   protected void internalSet(T object, EStructuralFeature feature, Object value,
         int index)
   {
      Definitions definitions = ModelUtil.getDefinitions(object);
      if (value instanceof String)
      {
         value = importer.createItemDefinition(definitions, null, (String) value,
               ItemKind.INFORMATION);
      }
      if (value == null || value instanceof ItemDefinition)
      {
         ItemDefinition itemDefinition = (ItemDefinition) value;
         super.internalSet(object, feature, itemDefinition, index);
      }
   }
}
