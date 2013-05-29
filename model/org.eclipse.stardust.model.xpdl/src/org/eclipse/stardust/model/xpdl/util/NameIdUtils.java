/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

public class NameIdUtils
{
   /**
    * @return
    */
   public static String createIdFromName(Object container, EObject element)
   {
      return createIdFromName(container, element, null);
   }
   
   /**
    * @return
    */
   public static String createIdFromName(Object container, EObject element, String base)
   {
      if(container == null)
      {
         container = findContainer(element);
      }
      
      if(base == null)
      {
         if(element instanceof IIdentifiableElement)
         {            
            base = ((IIdentifiableElement) element).getName();
         }
         else if(element instanceof TypeDeclarationType)
         {
            base = ((TypeDeclarationType) element).getName();         
         }
      }
         
      if(StringUtils.isEmpty(base))
      {
         return "";
      }

      IdFactory factory = null;
      if(element instanceof IIdentifiableElement)
      {            
         factory = new IdFactory(base, base);
      }
      else if(element instanceof TypeDeclarationType)
      {
         factory = new IdFactory(base);
      }

      List list = null;      
      if(element instanceof RoleType
            || element instanceof OrganizationType
            || element instanceof ConditionalPerformerType)
      {
         ModelType containingModel = ModelUtils.findContainingModel(element);
         list = new BasicEList<IModelParticipant>();
         list.addAll(containingModel.getRole());
         list.addAll(containingModel.getOrganization());
         list.addAll(containingModel.getConditionalPerformer());
      }      
      else if(container instanceof EObject
            && !(element instanceof AccessPointType)
            && !(element instanceof ModelType))
      {
         list = (List) computeIdNames(element.eClass(), (EObject) container);
      }
      else if(container instanceof EList)
      {
         list = (List) container;
      }
      if(list != null)
      {
         factory.computeNames(list, false);
      }

      return factory.getId();
   }

   private static EObject findContainer(EObject element)
   {
      return element.eContainer();
   }

   public static String createIdFromName(String name)
   {
      if(StringUtils.isEmpty(name))
      {
         return "";
      }

      IdFactory factory = new IdFactory(name, name);
      return factory.getId();
   }

   private static List computeIdNames(EClass eClass, EObject container)
   {
      return (List) container.eGet(getContainingFeature(eClass, container));
   }

   private static EStructuralFeature getContainingFeature(EClass eClass, EObject container)
   {
      List containingFeatureList = container.eClass().getEAllStructuralFeatures();
      return findContainmentFeature(containingFeatureList, eClass);
   }

   private static EStructuralFeature findContainmentFeature(List containingFeatures,
         EClass eClass)
   {
      EStructuralFeature result = null;

      for (Iterator i = containingFeatures.iterator(); i.hasNext();)
      {
         EStructuralFeature feature = (EStructuralFeature) i.next();
         if ((!feature.isTransient()
            || feature.getEContainingClass().equals(CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer())
            || feature.getEContainingClass().equals(XpdlPackage.eINSTANCE.getTypeDeclarationsType()))
            && feature.getEType().equals(eClass))
         {
            if (null != result)
            {
               return null;
            }
            result = feature;
         }
      }

      return result;
   }

   public static String createIdFromName(String name, List<EObject> ids)
   {
      if(StringUtils.isEmpty(name))
      {
         return "";
      }

      IdFactory factory = new IdFactory(name, name);
      if(!ids.isEmpty())
      {
         factory.computeNames(ids);
      }

      return factory.getId();
   }
}