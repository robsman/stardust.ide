/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.modeling.common.ui;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.util.IPreferenceStore;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.ui.PlatformUI;

/**
 * @author fherinean
 * @version $Revision: 48953 $
 */
public class IdFactory extends org.eclipse.stardust.model.xpdl.util.IdFactory
{
   public IdFactory(String baseId, String baseName,
         IIdentifiableModelElement referingElement)
   {
      super(baseId, baseName, referingElement);
      setPreferenceStore(PreferenceStore.getInstance());
   }

   public IdFactory(String baseId, String baseName, String uniquenessKey)
   {
      super(baseId, baseName, uniquenessKey);
      setPreferenceStore(PreferenceStore.getInstance());
   }

   public IdFactory(String baseId, String baseName)
   {
      this(baseId, baseName,
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
      setPreferenceStore(PreferenceStore.getInstance());
   }

   public IdFactory(String baseId, String baseName, EClass targetType,
         EStructuralFeature eFtrId, EStructuralFeature eFtrName)
   {
      super(baseId, baseName, targetType, eFtrId, eFtrName);
      setPreferenceStore(PreferenceStore.getInstance());
   }
   
   static class PreferenceStore implements IPreferenceStore
   {
      private static PreferenceStore instance = null;

      public static PreferenceStore getInstance()
      {
         if(instance == null)
         {
            instance = new PreferenceStore();
         }
         return instance;
      }
      
      public boolean getAutoId()
      {
         return PlatformUI.getPreferenceStore().getBoolean(
               BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION);
      }      
   }   
}