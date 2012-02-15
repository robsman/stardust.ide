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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.engine.core.model.utils.Hook;
import org.eclipse.stardust.engine.core.model.utils.ModelElement;
import org.eclipse.stardust.engine.core.model.utils.RootElement;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class AbstractModelElementAdapter extends AbstractAttributeHolder
      implements ModelElement
{
   protected final IIdentifiableElement ieDelegate;
   protected final IModelElement meDelegate;
   protected final IIdentifiableModelElement imeDelegate;
   protected RootElement model = null;
   
   public AbstractModelElementAdapter(EObject delegate)
   {
      super((delegate instanceof IExtensibleElement)
            ? (IExtensibleElement) delegate
            : null);
      
      this.ieDelegate = (delegate instanceof IIdentifiableElement)
            ? (IIdentifiableElement) delegate
            : null;
      this.meDelegate = (delegate instanceof IModelElement)
            ? (IModelElement) delegate
            : null;
      this.imeDelegate = (delegate instanceof IIdentifiableModelElement)
            ? (IIdentifiableModelElement) delegate
            : null;
   }

   public int getElementOID()
   {
      return (int) meDelegate.getElementOid();
   }

   public String getDescription()
   {
      return (null != imeDelegate)
            ? ModelUtils.getDescriptionText(imeDelegate.getDescription())
            : null;
   }

   public RootElement getModel()
   {
      if(this.model == null)
      {
         ModelType mt = ModelUtils.findContainingModel(meDelegate); 
         this.model = new IModelAdapter(mt);
      }
      
      return this.model; 
   }
   
   public long getOID()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public ModelElement getParent()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean isPredefined()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean isTransient()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public <T> T getRuntimeAttribute(String name)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Object setRuntimeAttribute(String name, Object value)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void setDescription(String description)
   {
      // TODO Auto-generated method stub
      
   }

   public void delete()
   {
      // TODO Auto-generated method stub
      
   }

   public void addReference(Hook reference)
   {
      // TODO Auto-generated method stub
      
   }

   public void setParent(ModelElement parent)
   {
      // TODO Auto-generated method stub
      
   }

   public void removeReference(Hook reference)
   {
      // TODO Auto-generated method stub
      
   }

   public void register(int oid)
   {
      // TODO Auto-generated method stub
      
   }

   public void setElementOID(int elementOID)
   {
      // TODO Auto-generated method stub
      
   }

   public void setPredefined(boolean predefined)
   {
      // TODO Auto-generated method stub
      
   }

   public String getUniqueId()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
