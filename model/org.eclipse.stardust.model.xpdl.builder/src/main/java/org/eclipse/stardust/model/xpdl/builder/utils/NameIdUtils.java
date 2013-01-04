/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.builder.utils;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IdFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

public class NameIdUtils
{
   /**
    * TODO Replace by Eclipse modeler logic
    * @param activity
    *
    * @param name
    * @return
    */
   public static String createIdFromName(Object container, IIdentifiableElement element)
   {
      if(element instanceof ModelType && !StringUtils.isEmpty(element.getId()))
      {
         return element.getId();
      }

      if(container == null)
      {
         container = findContainer(element);
      }

      String base = element.getName();
      if(StringUtils.isEmpty(base))
      {
         return "";
      }

      IdFactory factory = new IdFactory(base, base);

      List list = null;
      if(container instanceof EObject
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
         factory.computeNames(list);
      }

      return factory.getId();
   }

   private static EObject findContainer(IIdentifiableElement element)
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