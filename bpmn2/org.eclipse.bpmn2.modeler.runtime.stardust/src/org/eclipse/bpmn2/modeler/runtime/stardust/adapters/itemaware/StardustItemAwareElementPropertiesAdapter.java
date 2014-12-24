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

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ItemAwareElementPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;

public class StardustItemAwareElementPropertiesAdapter<T extends ItemAwareElement> extends ItemAwareElementPropertiesAdapter<T>
{

   public StardustItemAwareElementPropertiesAdapter(AdapterFactory adapterFactory, T object) 
   {
      super(adapterFactory, object);

      EStructuralFeature feature = Bpmn2Package.eINSTANCE.getItemAwareElement_ItemSubjectRef();
      setFeatureDescriptor(feature,
         new StardustItemDefinitionRefFeatureDescriptor<T>(this, object, feature)
      );

   }
   
}
