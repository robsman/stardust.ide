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
package org.eclipse.stardust.modeling.repository.common;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;


public class ExternalPackageResolver implements Adapter
{
   ModelType model;

   public ExternalPackageResolver(ModelType model)
   {
      this.model = model;
      addListenerRecursive(model.getExternalPackages());
   }

   public Notifier getTarget()
   {
      return null;
   }

   public boolean isAdapterForType(Object type)
   {
      return false;
   }

   public void notifyChanged(Notification notification)
   {
      Object notifier = notification.getNotifier();
      Object feature = notification.getFeature();
      if (notifier instanceof ModelType)
      {
         if (CarnotWorkflowModelPackage.eINSTANCE.getModelType_ExternalPackages().equals(
               feature))
         {
            ExternalPackages newPkgs = (ExternalPackages) notification.getNewValue();
            addListenerRecursive(newPkgs);
         }
      }
      if (notifier instanceof Connection && model != null)
      {
         if (feature instanceof EAttribute)
         {
            EAttribute attribute = (EAttribute) feature;
            if (attribute.getName().equalsIgnoreCase("id")) //$NON-NLS-1$
            {
               for (Iterator<ExternalPackage> i = model.getExternalPackages()
                     .getExternalPackage().iterator(); i.hasNext();)
               {
                  ExternalPackage externalPackage = i.next();
                  String uri = ExtendedAttributeUtil.getAttributeValue(externalPackage
                        .getExtendedAttributes(), IConnectionManager.URI_ATTRIBUTE_NAME);
                  if (uri.equalsIgnoreCase("cnx://" + notification.getOldStringValue() //$NON-NLS-1$
                        + "/")) //$NON-NLS-1$
                  {
                     ExtendedAttributeUtil.setAttribute(externalPackage
                           .getExtendedAttributes(),
                           IConnectionManager.URI_ATTRIBUTE_NAME, "cnx://" //$NON-NLS-1$
                                 + notification.getNewStringValue() + "/"); //$NON-NLS-1$
                  }
               }
            }
         }

      }

   }

   private void addListenerRecursive(ExternalPackages newPkgs)
   {
      if (newPkgs != null && model != null)
      {
         newPkgs.eAdapters().add(this);
         /*
          * for (Iterator<ExternalPackage> i = newPkgs.getExternalPackage().iterator();
          * i.hasNext();) { ExternalPackage externalPackage = i.next(); String uri =
          * ExtendedAttributeUtil
          * .getAttributeValue(externalPackage.getExtendedAttributes(),
          * IConnectionManager.URI_ATTRIBUTE_NAME); model.getConnectionManager().
          * EObjectDescriptor object = (EObjectDescriptor)
          * model.getConnectionManager().find(uri); object.eAdapters().add(this); }
          */
      }
   }

   public void setTarget(Notifier newTarget)
   {}
}
