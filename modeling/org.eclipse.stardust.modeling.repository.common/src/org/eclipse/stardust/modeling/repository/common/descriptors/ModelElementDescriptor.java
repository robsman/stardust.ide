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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.LinkAttribute;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ImportCancelledException;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.ClosureDisplayDialog;
import org.eclipse.stardust.modeling.repository.common.util.CreateClosures;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;


public class ModelElementDescriptor extends EObjectDescriptor
{
   public static final boolean DEBUG = true;

   private URI rootUri;
   
   private boolean qualifyUri;

   public ModelElementDescriptor(URI uri, IIdentifiableModelElement identifiable,
         String iconBundleId, String iconPath, boolean qualifyUri)
   {
      super(MergeUtils.createQualifiedUri(uri, identifiable, qualifyUri), identifiable,
            identifiable.getId(), identifiable.getName(), 
            ModelUtils.getDescriptionText(identifiable.getDescription()), iconBundleId, iconPath);
      this.qualifyUri = qualifyUri;
      rootUri = uri;
   }

   public IIdentifiableModelElement getIdentifiable()
   {
      return (IIdentifiableModelElement) eObject;
   }

   public void importElements(IconFactory iconFactory, ModelType targetModel, boolean asLink)
   {
      // compute all objects that are referenced by the source object
      CreateClosures createClosures = new CreateClosures();
      List<EObject> closure = createClosures.computeClosure(eObject, targetModel);
      if (closure.size() > 1)
      {
         // all other than eObject are referenced
         if (!ClosureDisplayDialog.acceptClosure(null, iconFactory, eObject, closure))
         {
            throw new ImportCancelledException();
         }
      }      
      
      Map<EObject, EObject> map = MergeUtils.createClosureMap(closure, targetModel);
      Map<EObject, MergeAction> reuseReplace = ImportUtils.reuseReplaceMap(map, iconFactory);
      // CANCEL pressed
      if (reuseReplace == null)
      {
         throw new ImportCancelledException();
      }
      
      LinkAttribute linkAttribute = new LinkAttribute(rootUri, asLink, qualifyUri, IConnectionManager.URI_ATTRIBUTE_NAME);
      
      MergeUtils.importElements(eObject, targetModel, closure, map, reuseReplace, linkAttribute);
   }
}