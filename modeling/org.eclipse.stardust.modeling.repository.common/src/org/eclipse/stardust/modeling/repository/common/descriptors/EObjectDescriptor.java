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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.LinkAttribute;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.IImageManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.ImportCancelledException;
import org.eclipse.stardust.modeling.repository.common.ImportableDescriptor;
import org.eclipse.stardust.modeling.repository.common.ui.ImageUtil;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.ClosureDisplayDialog;
import org.eclipse.stardust.modeling.repository.common.util.CreateClosures;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;
import org.eclipse.swt.graphics.Image;

public class EObjectDescriptor extends EObjectImpl implements IObjectDescriptor, IObjectReference, ImportableDescriptor
{
   private static volatile boolean SET_URIS = true;

   protected boolean alwaysReplace = false;
   protected EObject eObject;
   private EClass classifier;
   private String iconBundleId;
   private String iconPath;
   private URI uri;
   private String id;
   private String name;
   private String description = ""; //$NON-NLS-1$

   public EObjectDescriptor(URI uri, EClass classifier, String id, String name,
         String description, String iconBundleId, String iconPath)
   {
      this.uri = uri;
      this.classifier = classifier;
      this.id = id;
      this.name = name;
      this.description = description == null ? "" : description; //$NON-NLS-1$
      this.iconBundleId = iconBundleId;
      this.iconPath = iconPath;
   }

   public EObjectDescriptor(URI uri, EObject eObject, String id, String name,
         String description, String iconBundleId, String iconPath)
   {
      this(uri, eObject.eClass(), id, name, description, iconBundleId, iconPath);
      this.eObject = eObject;
      if (SET_URIS)
      {
         if (this.eObject instanceof Extensible)
         {
            ExtendedAttributeUtil.setAttribute((Extensible) this.eObject, IConnectionManager.URI_ATTRIBUTE_NAME, uri.toString());
         }
         else if (this.eObject instanceof IExtensibleElement)
         {
            AttributeUtil.setAttribute((IExtensibleElement) this.eObject, IConnectionManager.URI_ATTRIBUTE_NAME, uri.toString());
         }
      }
   }

   public static boolean areUrisSet()
   {
      return SET_URIS;
   }

   public static void setURIS(boolean setUris)
   {
      SET_URIS = setUris;
   }

   public IObjectDescriptor[] getChildren()
   {
      return null;
   }

   public Image getIcon()
   {
      if (iconBundleId == null || iconPath == null)
      {
         return null;
      }
      IImageManager im = ImageUtil.getImageManager(iconBundleId);
      return im.getPlainIcon(iconPath);
   }

   public String getLabel()
   {
      return name == null ? id : name;
   }

   public Object getType()
   {
      return classifier;
   }

   public URI getURI()
   {
      return uri;
   }

   public boolean hasChildren()
   {
      return false;
   }

   public String getId()
   {
      return id;
   }

   public String getName()
   {
      return name;
   }

   public String getDescription()
   {
      return description;
   }

   public String getIconBundleId()
   {
      return iconBundleId;
   }

   public String getIconPath()
   {
      return iconPath;
   }

   public boolean isLazyLoading()
   {
      return false;
   }

   @SuppressWarnings("unchecked")
   public <T extends EObject> T getEObject()
   {
      return (T) eObject;
   }

   public void importElements(IconFactory iconFactory, ModelType targetModel, boolean asLink)
   {
      // compute all objects that are referenced by the source object
      List<EObject> closure;
      if (asLink)
      {
         closure = CollectionUtils.newList();
         closure.add(eObject);
      }
      else
      {
         CreateClosures createClosures = new CreateClosures();
         closure = createClosures.computeClosure(eObject, targetModel);
         if (closure.size() > 1)
         {
            if (!ClosureDisplayDialog.acceptClosure(null, iconFactory, eObject, closure))
            {
               throw new ImportCancelledException();
            }
         }
      }

      Map<EObject, MergeAction> reuseReplace = Collections.emptyMap();
      Map<EObject, EObject> map = MergeUtils.createClosureMap(closure, targetModel);
      if (!map.isEmpty())
      {
         if(alwaysReplace)
         {
            reuseReplace = ImportUtils.reuseReplaceMap(map);
         }
         else
         {
            reuseReplace = ImportUtils.reuseReplaceMap(map, iconFactory, asLink);
         }
         // CANCEL pressed
         if (reuseReplace == null)
         {
            throw new ImportCancelledException();
         }

         if(alwaysReplace)
         {
            reuseReplace = null;
         }
      }

      LinkAttribute linkAttribute = new LinkAttribute(getRootURI(), asLink, isQualifyUri(), IConnectionManager.URI_ATTRIBUTE_NAME);
      if (asLink && !(eObject instanceof Extensible))
      {
         ModelType thisModel = ModelUtils.findContainingModel(eObject);
         if (!thisModel.getId().equals(targetModel.getId()))
         {
            ImportUtils.getPackageRef(this, targetModel, thisModel);
         }
      }
      MergeUtils.importElements(eObject, targetModel, closure, map, reuseReplace, linkAttribute);
   }

   public boolean isQualifyUri()
   {
      return true;
   }

   public URI getRootURI()
   {
      return uri.trimSegments(1);
   }

   public EObject resolveElement(EObject eObject)
   {
      LinkAttribute.setLinkInfoAttr(eObject, getURI(), true, IConnectionManager.URI_ATTRIBUTE_NAME);
      if (eObject.eIsProxy())
      {
         MergeUtils.replace(eObject, this.eObject);
         ((InternalEObject) this.eObject).eSetProxyURI(((InternalEObject) eObject).eProxyURI());
         return this.eObject;
      }
      return eObject;
   }
}