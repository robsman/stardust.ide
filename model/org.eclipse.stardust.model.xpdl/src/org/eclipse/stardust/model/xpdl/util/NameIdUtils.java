/*
 * $Id$
 * (C) 2000 - 2012 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.util;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
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
      if (container == null)
      {
         container = findContainer(element);
      }

      if (base == null)
      {
         if (element instanceof Code)
         {
            base = ((Code) element).getName();
         }
         if (element instanceof IIdentifiableElement)
         {
            base = ((IIdentifiableElement) element).getName();
         }
         if (element instanceof FormalParameterType)
         {
            base = ((FormalParameterType) element).getName();
         }
         else if (element instanceof TypeDeclarationType)
         {
            base = ((TypeDeclarationType) element).getName();
         }
      }

      if (StringUtils.isEmpty(base))
      {
         return ""; //$NON-NLS-1$
      }

      IdFactory factory = null;
      if (element instanceof Code)
      {
         factory = new IdFactory(base, base,
                     CarnotWorkflowModelPackage.eINSTANCE.getCode(),
                     CarnotWorkflowModelPackage.eINSTANCE.getCode_Code(),
                     CarnotWorkflowModelPackage.eINSTANCE.getCode_Name());
      }
      else if (element instanceof FormalParameterType)
      {
         factory = new IdFactory(base, base,
               XpdlPackage.eINSTANCE.getFormalParameterType(),
               XpdlPackage.eINSTANCE.getFormalParameterType_Id(),
               XpdlPackage.eINSTANCE.getFormalParameterType_Name());
      }
      else if (element instanceof IIdentifiableElement)
      {
         factory = new IdFactory(base, base);
      }
      else if (element instanceof TypeDeclarationType)
      {
         factory = new IdFactory(base);
      }

      List list = null;
      if (element instanceof RoleType
            || element instanceof OrganizationType
            || element instanceof ConditionalPerformerType)
      {
         ModelType containingModel = ModelUtils.findContainingModel(element);
         list = new BasicEList<IModelParticipant>();
         list.addAll(containingModel.getRole());
         list.addAll(containingModel.getOrganization());
         list.addAll(containingModel.getConditionalPerformer());
      }
      else if (element instanceof DataMappingType
            && container instanceof ActivityType)
      {
         list = new BasicEList<DataMappingType>();
         List<DataMappingType> datamappings = new BasicEList<DataMappingType>();
         datamappings.addAll(((ActivityType) container).getDataMapping());
         DirectionType direction = ((DataMappingType) element).getDirection();

         for(DataMappingType dataMapping : datamappings)
         {
            if(direction.equals(DirectionType.INOUT_LITERAL))
            {
               list.add(dataMapping);
            }
            else if(dataMapping.getDirection().equals(direction))
            {
               list.add(dataMapping);
            }
         }         
      }
      else if (container instanceof EObject
            && !(element instanceof AccessPointType)
            && !(element instanceof ModelType))
      {
         list = computeIdNames(element.eClass(), (EObject) container);
      }
      else if (container instanceof List)
      {
         list = (List) container;
      }
      if (list != null)
      {
         List clone = CollectionUtils.newList(list);
         clone.remove(element);
         factory.computeNames(clone, false);
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
         return ""; //$NON-NLS-1$
      }

      IdFactory factory = new IdFactory(name, name);
      return factory.getId();
   }

   private static List<?> computeIdNames(EClass eClass, EObject container)
   {
      return (List<?>) container.eGet(getContainingFeature(eClass, container));
   }

   private static EStructuralFeature getContainingFeature(EClass eClass, EObject container)
   {
      return findContainmentFeature(container.eClass().getEAllStructuralFeatures(), eClass);
   }

   private static EStructuralFeature findContainmentFeature(List<EStructuralFeature> containingFeatures,
         EClass eClass)
   {
      EStructuralFeature result = null;

      for (EStructuralFeature feature : containingFeatures)
      {
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
         return ""; //$NON-NLS-1$
      }

      IdFactory factory = new IdFactory(name, name);
      if(!ids.isEmpty())
      {
         factory.computeNames(ids);
      }

      return factory.getId();
   }
}