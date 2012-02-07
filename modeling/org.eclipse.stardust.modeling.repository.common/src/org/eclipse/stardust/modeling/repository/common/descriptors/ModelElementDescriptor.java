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
package org.eclipse.stardust.modeling.repository.common.descriptors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class ModelElementDescriptor extends EObjectDescriptor
{
   public static final boolean DEBUG = true;

   private URI rootURI;
   
   private boolean qualifyUri;

   public ModelElementDescriptor(URI uri, IIdentifiableModelElement identifiable,
         String iconBundleId, String iconPath, boolean qualifyUri)
   {
      super(MergeUtils.createQualifiedUri(uri, identifiable, qualifyUri), identifiable,
            identifiable.getId(), identifiable.getName(), 
            ModelUtils.getDescriptionText(identifiable.getDescription()), iconBundleId, iconPath);
      this.qualifyUri = qualifyUri;
      rootURI = uri;
   }

   public IIdentifiableModelElement getIdentifiable()
   {
      return (IIdentifiableModelElement) eObject;
   }

   public URI getRootURI()
   {
      return rootURI;
   }

   public boolean isQualifyUri()
   {
      return qualifyUri;
   }
}