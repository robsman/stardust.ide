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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.ui.PlatformUI;

/**
 * @author fherinean
 * @version $Revision: 48953 $
 */
public class IdFactory
{
   private final EClass eTypeTargetType;
   private final EStructuralFeature eFtrId;
   private final EStructuralFeature eFtrName;
   
   private String baseId;
   private String baseName;
   private String id;
   private String name;
   private IIdentifiableModelElement referingElement;

   private String uniquenessKey;
   
   private Integer sharedID = null;

   public void setSharedID(Integer sharedID)
   {
      this.sharedID = sharedID;
   }

   public IdFactory(String baseId, String baseName,
         IIdentifiableModelElement referingElement)
   {
      this(baseId, baseName);
      setReferingElement(referingElement);

   }

   public IdFactory(String baseId, String baseName, String uniquenessKey)
   {
      this(baseId, baseName);

      this.uniquenessKey = uniquenessKey;
   }

   public IdFactory(String baseId, String baseName)
   {
      this(baseId, baseName,
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
   }

   public IdFactory(String baseId, String baseName, EClass targetType,
         EStructuralFeature eFtrId, EStructuralFeature eFtrName)
   {
      this.eTypeTargetType = targetType;
      this.eFtrId = eFtrId;
      this.eFtrName = eFtrName;
      
      this.baseId = baseId;
      this.baseName = baseName;
   }

   public String getId()
   {
      String useId = id == null ? baseId : id;
      if(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION))
      {
         String name_ = getName();
         if(name_ != null)
         {
            useId = ModelUtils.computeId(name_);
         }
      }
      return useId;
   }

   public String getName()
   {
      return name == null ? baseName : name;
   }

   public IIdentifiableModelElement getReferingElement()
   {
      return referingElement;
   }

   public void setReferingElement(IIdentifiableModelElement referingElement)
   {
      this.referingElement = referingElement;

      this.uniquenessKey = (null != referingElement)
            ? Long.toString(referingElement.getElementOid())
            : null;
   }

   public void computeNames(List<? extends EObject> list)
   {
      computeNames(list, true);
   }
   
   public void computeNames(List<? extends EObject> list, boolean forceSuffix)
   {
      boolean foundBaseId = false;
      String searchId = baseId + "_"; //$NON-NLS-1$
      String searchName = baseName != null ? baseName + " " : null; //$NON-NLS-1$
      int counter = 1;
            
      if(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION) && searchName != null)
      {
         searchId = ModelUtils.computeId(searchName);         
      }      
      
      for (EObject o : list)
      {
         if (eTypeTargetType.isInstance(o))
         {
            String existingId = (String) o.eGet(eFtrId);
            foundBaseId |= ((null != existingId) && existingId.equals(baseId));
            if (existingId != null && existingId.startsWith(searchId))
            {
               try
               {
                  String sn = existingId.substring(searchId.length()).trim();
                  int number = Integer.parseInt(sn);
                  if (number >= counter)
                  {
                     counter = number + 1;
                  }
               }
               catch (NumberFormatException nfe)
               {
                  // ignore
               }
            }
            if (null != eFtrName)
            {
               String existingName = (String) o.eGet(eFtrName);
               if (existingName != null && existingName.startsWith(searchName))
               {
                  try
                  {
                     String sn = existingName.substring(searchName.length()).trim();
                     int number = Integer.parseInt(sn);
                     if (number >= counter)
                     {
                        counter = number + 1;
                     }
                  }
                  catch (NumberFormatException nfe)
                  {
                     // ignore
                  }
               }
            }            
         }
      }
      if (sharedID != null)
      {
         id = searchId + sharedID.intValue();
         name = searchName + sharedID.intValue();         
      }
      else if (!foundBaseId && !forceSuffix)
      {
         id = baseId;
      }
      else
      {
         id = searchId + counter;
         if(baseName != null)
         {
            name = searchName + counter;
         }
      }
   }

   // carefull, it compares only the base id, base name and refering id
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (!(o instanceof IdFactory)) return false;

      final IdFactory idFactory = (IdFactory) o;

      if (!baseId.equals(idFactory.baseId)) return false;
      if (!baseName.equals(idFactory.baseName)) return false;
      if (uniquenessKey != null ? !uniquenessKey.equals(idFactory.uniquenessKey) : idFactory.uniquenessKey != null) return false;

      return true;
   }

   public int hashCode()
   {
      int result;
      result = baseId.hashCode();
      result = 29 * result + baseName.hashCode();
      result = 29 * result + (uniquenessKey != null ? uniquenessKey.hashCode() : 0);
      return result;
   }
}