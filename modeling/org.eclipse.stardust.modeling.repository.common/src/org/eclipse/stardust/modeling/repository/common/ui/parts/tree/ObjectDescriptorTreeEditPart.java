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
package org.eclipse.stardust.modeling.repository.common.ui.parts.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.swt.graphics.Image;


public class ObjectDescriptorTreeEditPart extends LazyLoadingTreeEditPart
{
   public ObjectDescriptorTreeEditPart(IObjectDescriptor model)
   {
      super(model);
   }

   protected Image doGetImage()
   {
      return ((IObjectDescriptor) getModel()).getIcon();
   }
   
   protected String doGetText()
   {               
      return ((IObjectDescriptor) getModel()).getLabel();
   }   
   
   protected List doGetModelChildren()
   {
      IObjectDescriptor model = (IObjectDescriptor) getModel();
      return model.hasChildren()
         ? Arrays.asList(((IObjectDescriptor) getModel()).getChildren())
         : Collections.EMPTY_LIST;
   }

   protected boolean isLazyLoading()
   {
      IObjectDescriptor model = (IObjectDescriptor) getModel();
      return model.isLazyLoading();
   }    
}
