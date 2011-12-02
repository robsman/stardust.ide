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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateMetaTypeCommand extends CreateModelElementCommand
{
   private ConfigurationElement config;
   private EStructuralFeature[] features;

   public CreateMetaTypeCommand(IConfigurationElement config, EClass eClass,
         EStructuralFeature[] features)
   {
      this(config == null ? null : new ConfigurationElement(config), eClass, features);
   }

   public CreateMetaTypeCommand(ConfigurationElement config, EClass eClass,
         EStructuralFeature[] features)
   {
      super(MODEL, null, eClass);

      this.config = config;
      this.features = features;
   }

   protected IModelElement createModelElement()
   {
      IMetaType metaType = null;
      if (config != null)
      {
         String id = config.getAttribute(SpiConstants.ID);
         if (null == ModelUtils.findIdentifiableElement(getModel(), getContainingFeature(),
               id))
         {
            metaType = (IMetaType) super.createModelElement();
   
            metaType.setId(config.getAttribute(SpiConstants.ID));
            metaType.setName(config.getAttribute(SpiConstants.NAME));
   
            // TODO description?
   
            metaType.setIsPredefined(true);
            for (int i = 0; i < features.length; i++)
            {
               EFactory dataFactory = features[i].getEType()
                     .getEPackage()
                     .getEFactoryInstance();
               String attribute = config.getAttribute(features[i].getName());
               if (attribute != null)
               {
                  metaType.eSet(features[i], dataFactory.createFromString(
                        (EDataType) features[i].getEType(), attribute));
               }
            }
            
            List<AttributeType> attributes = metaType.getAttribute();
            ConfigurationElement[] elements = config.getChildren(SpiConstants.ATTR_ELEMENT);
            CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
            for (int i = 0; i < elements.length; i++)
            {
               AttributeType attr = factory.createAttributeType();
   
               attr.setName(elements[i].getAttribute(SpiConstants.ATTR_NAME));
               attr.setType(elements[i].getAttribute(SpiConstants.ATTR_TYPE));
               attr.setValue(elements[i].getAttribute(SpiConstants.ATTR_VALUE));
   
               attributes.add(attr);
            }
         }
      }
      return metaType;
   }
}
