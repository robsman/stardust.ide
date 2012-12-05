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
package org.eclipse.stardust.modeling.common.ui;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
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
      setAutoId(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION));            
   }

   public IdFactory(String baseId, String baseName, String uniquenessKey)
   {
      super(baseId, baseName, uniquenessKey);
      setAutoId(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION));            
   }

   public IdFactory(String baseId, String baseName)
   {
      this(baseId, baseName,
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
      setAutoId(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION));            
   }

   public IdFactory(String baseId, String baseName, EClass targetType,
         EStructuralFeature eFtrId, EStructuralFeature eFtrName)
   {
      super(baseId, baseName, targetType, eFtrId, eFtrName);
      setAutoId(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION));      
   }
}