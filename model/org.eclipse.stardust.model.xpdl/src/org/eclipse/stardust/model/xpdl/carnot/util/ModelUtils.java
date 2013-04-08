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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.xsd.XSDSchema;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.dto.AuditTrailPersistence;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.core.upgrade.jobs.m30.ApplicationContextType;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IAttributeCategory;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.IdRef;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.Model_Messages;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataInitializer;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;

public class ModelUtils
{
   private static XpdlFactory xpdlFactory = XpdlFactory.eINSTANCE;

   public static final class EObjectInvocationHandler implements InvocationHandler
   {
      private final EObject model;

      public EObjectInvocationHandler(EObject model)
      {
         this.model = model;
      }

      public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable
      {
         return doInvoke(proxy, model, method, args);
      }

      public EObject getModel()
      {
         return model;
      }
   }

   public static final Comparator<Object> IDENTIFIABLE_COMPARATOR = new Comparator<Object>()
   {
      public int compare(Object lhs, Object rhs)
      {
         IIdentifiableElement lhsIie = lhs instanceof IIdentifiableElement
               ? (IIdentifiableElement) lhs : null;
         IIdentifiableElement rhsIie = rhs instanceof IIdentifiableElement
               ? (IIdentifiableElement) rhs : null;

         String lhsKey = lhsIie == null ? null : StringUtils.isEmpty(lhsIie.getName())
               ? lhsIie.getId() : lhsIie.getName();
         String rhsKey = rhsIie == null ? null : StringUtils.isEmpty(rhsIie.getName())
               ? rhsIie.getId() : rhsIie.getName();

         if (lhsKey != null)
         {
            return rhsKey == null ? 1 : lhsKey.compareToIgnoreCase(rhsKey);
         }
         return -1;
      }
   };

   public static ModelType findContainingModel(EObject element)
   {
      if (element instanceof ModelType)
      {
         return (ModelType) element;
      }
      if (element != null)
      {
         while (null != element.eContainer())
         {
            element = element.eContainer();
            if (element instanceof ModelType)
            {
               return (ModelType) element;
            }
         }
         for (EObject content : element.eContents())
         {
            if (content instanceof ModelType)
            {
               return (ModelType) content;
            }
         }
      }
      return null;
   }

   public static DiagramType findContainingDiagram(IGraphicalObject graphicalObject)
   {
      EObject element = graphicalObject;
      DiagramType diagram = (element instanceof DiagramType)
            ? (DiagramType) element
            : null;

      while (null == diagram && null != element.eContainer())
      {
         element = element.eContainer();
         if (element instanceof DiagramType)
         {
            diagram = (DiagramType) element;
         }
      }

      return diagram;
   }

   public static ProcessDefinitionType findContainingProcess(EObject element)
   {
      ProcessDefinitionType process = (element instanceof ProcessDefinitionType)
            ? (ProcessDefinitionType) element
            : null;

      while ((null == process) && (null != element.eContainer()))
      {
         element = element.eContainer();
         if (element instanceof ProcessDefinitionType)
         {
            process = (ProcessDefinitionType) element;
         }
      }

      return process;
   }

   public static PoolSymbol findContainingPool(EObject element)
   {
      PoolSymbol pool = (element instanceof PoolSymbol)
            ? (PoolSymbol) element
            : null;

      while ((null == pool) && (null != element.eContainer()))
      {
         element = element.eContainer();
         if (element instanceof PoolSymbol)
         {
            pool = (PoolSymbol) element;
         }
      }

      return pool;
   }

   public static ApplicationType findContainingApplication(EObject element)
   {
      ApplicationType application = (element instanceof ApplicationType)
            ? (ApplicationType) element
            : null;

      while ((null == application) && (null != element.eContainer()))
      {
         element = element.eContainer();
         if (element instanceof ApplicationType)
         {
            application = (ApplicationType) element;
         }
      }

      return application;
   }

   public static ActivityType findContainingActivity(EObject element)
   {
      ActivityType activity = (element instanceof ActivityType)
            ? (ActivityType) element
            : null;
      while ((null == activity) && (null != element.eContainer()))
      {
         element = element.eContainer();
         if (element instanceof ActivityType)
         {
            activity = (ActivityType) element;
         }
      }
      return activity;
   }

   public static EventHandlerType findContainingEventHandlerType(EObject element)
   {
      EventHandlerType handler = (element instanceof EventHandlerType)
            ? (EventHandlerType) element
            : null;

      while ((null == handler) && (null != element.eContainer()))
      {
         element = element.eContainer();
         if (element instanceof EventHandlerType)
         {
            handler = (EventHandlerType) element;
         }
      }

      return handler;
   }

   public static TriggerType findContainingTriggerType(EObject element)
   {
      TriggerType triggerType = (element instanceof TriggerType)
            ? (TriggerType) element
            : null;

      while ((null == triggerType) && (null != element.eContainer()))
      {
         element = element.eContainer();
         if (element instanceof TriggerType)
         {
            triggerType = (TriggerType) element;
         }
      }

      return triggerType;
   }

   public static long getElementOid(IModelElement element, ModelType model)
   {
      final long elementOid;
      if (element.isSetElementOid())
      {
         elementOid = element.getElementOid();
      }
      else
      {
         elementOid = getMaxUsedOid(model) + 1;
      }
      return elementOid;
   }

   public static long getMaxUsedOid(ModelType model)
   {
      long maxOid = 0;

      if (model.isSetOid())
      {
         maxOid = model.getOid();
      }

      for (TreeIterator<EObject> i = model.eAllContents(); i.hasNext();)
      {
         EObject obj = i.next();
         if (obj instanceof IModelElement && ((IModelElement) obj).isSetElementOid())
         {
            maxOid = Math.max(maxOid, ((IModelElement) obj).getElementOid());
         }
      }
      return maxOid;
   }

   public static ActivityType findRootActivity(ProcessDefinitionType process)
   {
      ActivityType result = null;

      for (ActivityType activity : process.getActivity())
      {
         if (activity.getInTransitions().isEmpty())
         {
            if (null == result)
            {
               result = activity;
            }
            else
            {
               // no unique root acivity
               result = null;
               break;
            }
         }
      }

      return result;
   }

   public static DataType findData(IModelElement context, String dataId)
   {
      DataType result = null;

      ModelType model = findContainingModel(context);
      if (null != model)
      {
         result = (DataType) findIdentifiableElement(model.getData(), dataId);
      }

      return result;
   }

   public static DescriptionType createDescription(String description)
   {
      if (description == null)
      {
         return null;
      }
      DescriptionType descriptionType =
         CarnotWorkflowModelFactory.eINSTANCE.createDescriptionType();
      setCDataString(descriptionType.getMixed(), description);
      return descriptionType;
   }

   public static void setCDataString(FeatureMap mixed, String description)
   {
      setCDataString(mixed, description, false);
   }

   public static void setCDataString(FeatureMap mixed, String description,
         boolean normalizeCrLf)
   {
      if (normalizeCrLf)
      {
         description = StringUtils.replace(description, "\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
      }

      // clean up any pending previous non-CDATA content (i.e. from XPDL load)
      if (mixed.isSet(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text()))
      {
         mixed.unset(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text());
      }
      mixed.set(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA(), Collections
            .singleton(description));
   }

   public static String getDescriptionText(DescriptionType desc)
   {
      if (desc == null)
      {
         return null;
      }
      return getCDataString(desc.getMixed());
   }

   public static String getCDataString(FeatureMap featureMap)
   {
      String result = null;

      Collection<?> parts = (Collection<?>) featureMap.get(
            XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA(), false);
      if (parts == null || parts.isEmpty())
      {
         parts = (Collection<?>) featureMap.get(
               XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), false);
      }
      if (parts != null && !parts.isEmpty())
      {
         if (parts.size() == 1)
         {
            result = parts.iterator().next().toString();
         }
         else
         {
            StringBuffer sb = new StringBuffer();
            for (Object o : parts)
            {
               sb.append(o.toString());
            }
            result = sb.toString();
         }
      }
      return result;
   }

   public static IIdentifiableElement findIdentifiableElement(EObject parent,
         EStructuralFeature feature, String id)
   {
      EObject result = findElementById(parent, feature, id);
      return result instanceof IIdentifiableElement ? (IIdentifiableElement) result : null;
   }

   public static EObject findElementById(EObject parent, EStructuralFeature feature, String id)
   {
      if (parent != null)
      {
         Object value = parent.eGet(feature);
         if (value instanceof List)
         {
            @SuppressWarnings("unchecked")
            List<EObject> domain = (List<EObject>) value;
            return findElementById(domain, id);
         }
      }
      return null;
   }

   public static <T extends IIdentifiableElement> T findIdentifiableElement(List<? extends T> domain, String id)
   {
      T result = findElementById(domain, id);
      return result instanceof IIdentifiableElement ? result : null;
   }

   public static EObject findElementById(List<?> domain, IIdentifiableElement object)
   {
      String id = object.getId();

      EObject result = null;
      for (Object candidate : domain)
      {
         String candidateId = candidate instanceof IIdentifiableElement
            ? ((IIdentifiableElement) candidate).getId()
            : candidate instanceof TypeDeclarationType
               ? ((TypeDeclarationType) candidate).getId()
               : null;

         if(object instanceof DataMappingType)
         {
            DirectionType direction = ((DataMappingType) object).getDirection();
            if(candidate instanceof DataMappingType)
            {
               DirectionType direction_ = ((DataMappingType) candidate).getDirection();
               if(!direction.equals(direction_))
               {
                  continue;
               }
            }
            else
            {
               continue;
            }
         }

         if(object instanceof AccessPointType)
         {
            DirectionType direction = ((AccessPointType) object).getDirection();
            if (candidate instanceof AccessPointType)
            {
               DirectionType candidateDirection = ((AccessPointType) candidate).getDirection();
               if (!direction.equals(candidateDirection))
               {
                  continue;
               }
            }
            else
            {
               continue;
            }
         }

         if (CompareHelper.areEqual(id, candidateId))
         {
            result = (EObject) candidate;
            break;
         }
      }
      return result;
   }

   public static <T> T findElementById(List<? extends T> domain, String id)
   {
      T result = null;
      for (int i = 0; i < domain.size(); i++)
      {
         T candidate = domain.get(i);
         String candidateId = candidate instanceof IIdentifiableElement
            ? ((IIdentifiableElement) candidate).getId()
            : candidate instanceof TypeDeclarationType
               ? ((TypeDeclarationType) candidate).getId()
               : null;
         if (CompareHelper.areEqual(id, candidateId))
         {
            result = candidate;
            break;
         }
      }
      return result;
   }

   public static List<ITypedElement> findMetaTypeInstances(List<? extends IModelElement> domain, String metaTypeId)
   {
      if (domain == null || domain.isEmpty())
      {
         return Collections.emptyList();
      }

      List<ITypedElement> result = CollectionUtils.newList(domain.size());
      for (IModelElement element : domain)
      {
         if (element instanceof ITypedElement)
         {
            IMetaType metaType = ((ITypedElement) element).getMetaType();
            if (metaType != null && CompareHelper.areEqual(metaTypeId, metaType.getId()))
            {
               result.add((ITypedElement) element);
            }
         }
      }
      return result;
   }

   public static void addSymbols(Set<INodeSymbol> set, ISymbolContainer container, EReference ref,
         EStructuralFeature feat, String refId)
   {
      if (container == null)
      {
         return;
      }
      for (ISymbolContainer subContainer : getSubContainers(container))
      {
         addSymbols(set, subContainer, ref, feat, refId);
      }
      @SuppressWarnings("unchecked")
      List<INodeSymbol> list = (List<INodeSymbol>) container.eGet(ref);
      for (INodeSymbol symbol : list)
      {
         if (refId == null || refId.equals(getStringValue(symbol.eGet(feat))))
         {
            set.add(symbol);
         }
      }
   }

   public static void addSymbols(Set<INodeSymbol> set, ISymbolContainer container, EReference ref,
         EStructuralFeature feat, IModelElement element)
   {
      if (container != null)
      {
         for (ISymbolContainer subContainer : getSubContainers(container))
         {
            addSymbols(set, subContainer, ref, feat, element);
         }

         @SuppressWarnings("unchecked")
         List<INodeSymbol> list = (List<INodeSymbol>) container.eGet(ref);
         for (INodeSymbol symbol : list)
         {
            if (element == symbol.eGet(feat))
            {
               set.add(symbol);
            }
         }
      }
   }

   public static List< ? extends ISymbolContainer> getSubContainers(ISymbolContainer container)
   {
      List<? extends ISymbolContainer> subContainers = Collections.emptyList();
      if (container instanceof DiagramType)
      {
         subContainers = ((DiagramType) container).getPoolSymbols();
      }
      else if (container instanceof PoolSymbol)
      {
         subContainers = ((PoolSymbol) container).getLanes();
      }
      else if (container instanceof LaneSymbol)
      {
         subContainers = ((LaneSymbol) container).getChildLanes();
      }
      return subContainers;
   }

   private static String getStringValue(Object object)
   {
      return object == null ? null : object.toString();
   }

   public static org.eclipse.stardust.model.xpdl.carnot.DataTypeType getDataType(IModelElement element, String typeId)
   {
      return (org.eclipse.stardust.model.xpdl.carnot.DataTypeType) findIdentifiableElement(
         findContainingModel(element),
         CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(),
         typeId);
   }

   public static ApplicationTypeType getApplicationType(IModelElement element, String typeId)
   {
      return (ApplicationTypeType) findIdentifiableElement(
         findContainingModel(element),
         CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationType(),
         typeId);
   }

   public static TypeDeclarationType getTypeDeclaration(IExtensibleElement element, String typeId)
   {
      return getTypeDeclaration(findContainingModel(element), typeId);
   }

   public static TypeDeclarationType getTypeDeclaration(IModelElement element, String typeId)
   {
      return getTypeDeclaration(findContainingModel(element), typeId);
   }

   private static TypeDeclarationType getTypeDeclaration(ModelType model, String typeId)
   {
      return (TypeDeclarationType) findElementById(model.getTypeDeclarations().getTypeDeclaration(), typeId);
   }

   public static ApplicationContextTypeType getApplicationContextType(IModelElement element, String typeId)
   {
      return (ApplicationContextTypeType) findIdentifiableElement(
         findContainingModel(element),
         CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationContextType(),
         typeId);
   }

   public static TriggerTypeType getTriggerType(IModelElement element, String typeId)
   {
      return (TriggerTypeType) findIdentifiableElement(
         findContainingModel(element),
         CarnotWorkflowModelPackage.eINSTANCE.getModelType_TriggerType(),
         typeId);
   }

   private ModelUtils()
   {
      // utility class
   }

   public static IDataInitializer getInitializer(org.eclipse.stardust.model.xpdl.carnot.DataTypeType type)
   {
      if (type != null)
      {
         SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
         Map<String, IConfigurationElement> extensions = registry.getExtensions(CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID);
         IConfigurationElement config = extensions.get(type.getId());
         if (config != null)
         {
            try
            {
               return (IDataInitializer) config.createExecutableExtension("initializerClass"); //$NON-NLS-1$
            }
            catch (CoreException e)
            {
               //e.printStackTrace();
            }
            catch (ClassCastException cce)
            {
               // todo
            }
         }
      }
      return null;
   }

   public static DirectionType getDualDirection(DirectionType direction)
   {
      DirectionType result;

      if (DirectionType.IN_LITERAL.equals(direction))
      {
         result = DirectionType.OUT_LITERAL;
      }
      else if (DirectionType.OUT_LITERAL.equals(direction))
      {
         result = DirectionType.IN_LITERAL;
      }
      else
      {
         result = direction;
      }

      return result;
   }

   // TODO: duplicate method VersionRepository, need to put it in a common place
   public static IProject getProjectFromEObject(EObject eObject)
   {
      if (eObject instanceof ContextType)
      {
         ContextType contextType = (ContextType) eObject;
         if (contextType.getType() != null)
         {
            eObject = contextType.getType().eContainer();
         }
      }
      if (eObject != null)
      {
         Resource eResource = eObject.eResource();
         if (eResource != null)
         {
            URI eUri = eResource.getURI();

            if (eUri.isFile())
            {
               String fileString = eUri.toFileString();
               java.net.URI netModelUri = new File(fileString).toURI();
               IContainer[] containers = ResourcesPlugin.getWorkspace().getRoot()
                     .findContainersForLocationURI(netModelUri);
               if (containers != null && containers.length > 0)
               {
                  IContainer container = containers[0];
                  return container.getProject();
               }
            }

            if (eUri.segmentCount() > 1)
            {
               IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
                  eUri.segment(1));
               if (resource instanceof IProject)
               {
                  return (IProject) resource;
               }
               else if (resource != null)
               {
                  return resource.getProject();
               }
            }
         }
      }
      return null;
   }

   public static IIdentifiableModelElement getIdentifiableModelProxy(
         final EObject model, Class<?> theClass)
   {
      return (IIdentifiableModelElement) Proxy.newProxyInstance(
            IModelElement.class.getClassLoader(), new Class[] {
                  IIdentifiableModelElement.class, theClass},
            new EObjectInvocationHandler(model));
   }

   private static Object doInvoke(Object proxy, EObject model, Method method,
         Object[] args) throws Throwable
   {
      if (method.getDeclaringClass().equals(EObject.class))
      {
         if ("eGet".equals(method.getName()) //$NON-NLS-1$
               || "eSet".equals(method.getName()) //$NON-NLS-1$
               || "eIsSet".equals(method.getName()) //$NON-NLS-1$
               || "eUnset".equals(method.getName())) //$NON-NLS-1$
         {
            if ((null != args)
                  && CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid()
                        .equals(args[0]))
            {
               Object[] modifiedArgs = (Object[]) args.clone();
               modifiedArgs[0] = CarnotWorkflowModelPackage.eINSTANCE.getModelType_Oid();
               return method.invoke(model, modifiedArgs);
            }
            else
            {
               return method.invoke(model, args);
            }
         }
         else
         {
            return method.invoke(model, args);
         }
      }

      if (method.getDeclaringClass().equals(IModelElement.class))
      {
         if (method.getName().equals("getElementOid") && model instanceof ModelType) //$NON-NLS-1$
         {
            return new Long(((ModelType) model).getModelOID());
         }
         else if (method.getName().equals("setElementOid") && model instanceof ModelType) //$NON-NLS-1$
         {
            ((ModelType) model).setModelOID(((Long) args[0]).intValue());
         }
         else if (method.getName().equals("unsetElementOid") && model instanceof ModelType) //$NON-NLS-1$
         {
            ((ModelType) model).unsetModelOID();
         }
         else if (method.getName().equals("isSetElementOid") && model instanceof ModelType) //$NON-NLS-1$
         {
            return ((ModelType) model).isSetModelOID() ? Boolean.TRUE : Boolean.FALSE;
         }
         return null;
      }

      if (method.getDeclaringClass().equals(IIdentifiableModelElement.class))
      {
         if (method.getName().equals("getDescription") && model instanceof ModelType) //$NON-NLS-1$
         {
            return ((ModelType) model).getDescription();
         }
         else if (method.getName().equals("setDescription") && model instanceof ModelType) //$NON-NLS-1$
         {
            ((ModelType) model).setDescription((DescriptionType) args[0]);
         }
         return null;
      }

      if (method.getDeclaringClass().equals(Object.class))
      {
         if (method.getName().equals("equals")) //$NON-NLS-1$
         {
            return Boolean.valueOf(model.equals(args[0]) || (proxy == args[0]));
         }
      }
      return method.invoke(model, args);
   }

   public static String computeId(String name)
   {
      if (name == null)
      {
         return ""; //$NON-NLS-1$
      }

      StringBuffer sb = new StringBuffer();
      name = name.trim();

      if (name.equals("")) //$NON-NLS-1$
      {
         return ""; //$NON-NLS-1$
      }

      for (int i = 0; i < name.length(); i++)
      {
         char charAt = name.charAt(i);
         if(i == 0)
         {
            if (!Character.isJavaIdentifierStart(charAt))
            {
               charAt = '_'; //$NON-NLS-1$
            }
         }
         else
         {
            if (!Character.isJavaIdentifierPart(charAt))
            {
               if (!Character.isWhitespace(charAt))
               {
                  charAt = '_'; //$NON-NLS-1$
               }
            }
         }
         if (!Character.isWhitespace(charAt))
         {
            sb.append(charAt);
         }
      }

      return sb.toString();
   }

   public static void resolve(ModelType model, IExtensibleElement extensible)
   {
      // resolve internal type declarations
      if (extensible == model)
      {
         TypeDeclarationsType declarations = model.getTypeDeclarations();
         if (declarations != null)
         {
            List<TypeDeclarationType> types = declarations.getTypeDeclaration();
            for (TypeDeclarationType decl : types)
            {
               XpdlTypeType xpdlType = decl.getDataType();
               if (xpdlType instanceof SchemaTypeType)
               {
                  XSDSchema schema = ((SchemaTypeType) xpdlType).getSchema();
                  if (schema != null)
                  {
                     schema.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX + decl.getId());
                  }
               }
            }
         }
      }

      // resolve declared references
      IConfigurationElement config = SpiExtensionRegistry.getConfiguration(extensible);
      if (config != null)
      {
         IConfigurationElement[] refs = config.getChildren("reference"); //$NON-NLS-1$
         for (IConfigurationElement ref : refs)
         {
            AttributeType attribute = AttributeUtil.getAttribute(extensible,
                  ref.getAttribute("attributeName")); //$NON-NLS-1$
            if (attribute != null)
            {
               String scopeList = ref.getAttribute("scope"); //$NON-NLS-1$
               setReference(attribute, model, scopeList);
            }
         }
      }

      // resolve references for Organizations which are not part of ExtensionRegistry (see CRNT-16871)
      if (extensible instanceof OrganizationType)
      {
         AttributeType attribute = AttributeUtil.getAttribute(extensible,
               PredefinedConstants.BINDING_DATA_ID_ATT);
         if (attribute != null)
         {
            setReference(attribute, model, "data"); //$NON-NLS-1$
         }
      }

      if (extensible instanceof DataType)
      {
         AttributeType attribute = AttributeUtil.getAttribute(extensible,
               DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
         if (attribute != null)
         {
            setReference(attribute, model, "struct"); //$NON-NLS-1$
         }
      }

      // This is for the WebModeler who does not have access to the extension mechanism
      if (config == null && extensible instanceof TriggerType)
      {
         AttributeType attribute = AttributeUtil.getAttribute(extensible,
               PredefinedConstants.MANUAL_TRIGGER_PARTICIPANT_ATT);
         if (attribute != null)
         {
            setReference(attribute, model, "role+organization"); //$NON-NLS-1$
         }
      }
      if (config == null && extensible instanceof DataType)
      {
         AttributeType attribute = AttributeUtil.getAttribute(extensible,
               StructuredDataConstants.TYPE_DECLARATION_ATT);
         if (attribute != null)
         {
            setReference(attribute, model, "struct"); //$NON-NLS-1$
         }
      }

      if (config == null && extensible instanceof AccessPointType)
      {
         AttributeType attribute = AttributeUtil.getAttribute(extensible,
               StructuredDataConstants.TYPE_DECLARATION_ATT);
         if (attribute != null)
         {
            setReference(attribute, model, "struct"); //$NON-NLS-1$
         }
      }

      // resolve permissions
      // TODO: make permissions a first class element
      IAttributeCategory category = AttributeUtil.createAttributeCategory(extensible, "authorization"); //$NON-NLS-1$
      for (AttributeType attribute : category.getAttributes())
      {
         setReference(attribute, model, "role+organization"); //$NON-NLS-1$
      }

      for (Object item : extensible.eContents())
      {
         if (item instanceof IExtensibleElement)
         {
            resolve(model, (IExtensibleElement) item);
         }
      }
   }

   public static void setReference(AttributeType attribute, ModelType model,
         String scopeList)
   {
      String id = attribute.getValue();
      int ix = id.indexOf(':');
      if (ix > 0)
      {
         QName qname = QName.valueOf(id.substring(ix + 1));
         ExternalPackages packages = model.getExternalPackages();
         if (packages != null)
         {
            ExternalPackage pkg = packages.getExternalPackage(qname.getNamespaceURI());
            if (pkg != null)
            {
               ModelType otherModel = getExternalModel(pkg);
               if (otherModel != null)
               {
                  model = otherModel;
                  scopeList = id.substring(0, ix);
                  if ("typeDeclaration".equals(scopeList))
                  {
                     scopeList = "struct";
                  }
                  id = qname.getLocalPart();
               }
            }
         }
      }
      StringTokenizer st = new StringTokenizer(scopeList, "+"); //$NON-NLS-1$
      while (st.hasMoreTokens())
      {
         String scope = st.nextToken();
         if ("struct".equals(scope)) //$NON-NLS-1$
         {
            // special case of a reference to a structured type
            TypeDeclarationsType declarations = model.getTypeDeclarations();
            if (declarations != null)
            {
               TypeDeclarationType decl = (TypeDeclarationType)
                  ModelUtils.findElementById(declarations.getTypeDeclaration(), id);
               if (decl != null)
               {
                  AttributeUtil.setReference(attribute, decl);
               }
            }
         }
         else
         {
            EStructuralFeature feature = model.eClass().getEStructuralFeature(scope);
            IIdentifiableElement element = ModelUtils.findIdentifiableElement(model, feature, id);
            if (element != null)
            {
               AttributeUtil.setReference(attribute, element);
               break;
            }
         }
      }
   }

   public static boolean isValidId(String id)
   {
      if (id == null || id.equals("")) //$NON-NLS-1$
      {
         return false;
      }
      if (!Character.isJavaIdentifierStart(id.charAt(0)))
      {
         return false;
      }
      for (int i = 1; i < id.length(); i++)
      {
         if (!Character.isJavaIdentifierPart(id.charAt(i)))
         {
            return false;
         }
      }
      return true;
   }

   public static ModelType parseModelType(String modelString)
   {
      String modelXmlEncoding = getXmlEncoding(modelString);

      CarnotWorkflowModelResourceImpl resource = new CarnotWorkflowModelResourceImpl(
            URI.createURI("http://only/a/dummy/URI")); //$NON-NLS-1$
      ResourceSetImpl resourceSet = new ResourceSetImpl();
      resourceSet.getResources().add(resource);

      Map<String, Object> options = CollectionUtils.newMap();
      options.put("RECORD_UNKNOWN_FEATURE", Boolean.TRUE); //$NON-NLS-1$
      try
      {
         resource.load(new ByteArrayInputStream(modelString.getBytes(modelXmlEncoding)), options);
      }
      catch (Exception e)
      {
         throw new RuntimeException(e);
      }

      for (Object o : resource.getContents())
      {
         if (o instanceof DocumentRoot)
         {
            ModelType model = ((DocumentRoot) o).getModel();
            resolve(model, model);
            return model;
         }
      }

      throw new RuntimeException(Model_Messages.EXC_COULD_NOT_LOAD_MODEL_DOC_ROOT_NOT_FOUND);
   }

   private static String getXmlEncoding(String text)
   {
      String pattern = "encoding=\""; //$NON-NLS-1$
      int offset = text.indexOf(pattern) + pattern.length();
      int pos = text.indexOf("\"", offset); //$NON-NLS-1$
      return text.substring(offset, pos);
   }

   public static EObject getEObject(IAdaptable adaptable)
   {
      if (adaptable == null)
      {
         return null;
      }

      IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) adaptable.getAdapter(
            IModelElementNodeSymbol.class);
      if (symbol != null)
      {
         IModelElement modelElement = symbol.getModelElement();
         return modelElement == null ? symbol : modelElement;
      }


      IModelElement modelElement = (IModelElement) adaptable.getAdapter(IModelElement.class);
      return modelElement == null ? ((EObject)adaptable.getAdapter(EObject.class)) : modelElement;
   }

   public static IModelElement findElementByOid(List<?> list, long elementOid)
   {
	  for (Object object : list)
	  {
		 if (object instanceof IModelElement)
		 {
			if (((IModelElement) object).getElementOid() == elementOid)
			{
			   return (IModelElement) object;
			}
		 }
	  }
	  return null;
   }

   public static EObject findElementByFeature(List<?> list, Object prototype, String featureName)
   {
	  if (!(prototype instanceof EObject))
	  {
		 return null;
	  }
	  EClass class1 = ((EObject) prototype).eClass();
	  EStructuralFeature feature = class1.getEStructuralFeature(featureName);
	  if(feature == null)
	  {
	     return null;
	  }

	  Object value1 = ((EObject) prototype).eGet(feature);
	  for (Object object : list)
	  {
		 if (object instanceof EObject)
		 {
			EClass class2 = ((EObject) object).eClass();
			if (class2.equals(class1))
			{
			   Object value2 = ((EObject) object).eGet(feature);
			   if (CompareHelper.areEqual(value1, value2))
			   {
				  return (EObject) object;
			   }
			}
		 }
	  }
	  return null;
   }

   public static IModelParticipant findParticipant(String participantId, List<? extends IModelParticipant>... participants)
   {
      for (List<? extends IModelParticipant> list : participants)
      {
         for (IModelParticipant participant : list)
         {
            if (participant.getId().equals(participantId))
            {
               return participant;
            }
         }
      }
      return null;
   }

   public static List<FormalParameterType> findAllFormalParameters(ModelType model)
   {
      List<FormalParameterType> result = new ArrayList<FormalParameterType>();
      List<ProcessDefinitionType> processes = model.getProcessDefinition();
      for (Iterator<ProcessDefinitionType> i = processes.iterator(); i.hasNext();)
      {
         ProcessDefinitionType process = i.next();
         if (process.getFormalParameters().getFormalParameter() != null)
         {
            result.addAll(process.getFormalParameters().getFormalParameter());
         }
      }
      return result;
   }

   public static List<String> getURIsForExternalPackages(ModelType model)
   {
      List<String> result = new ArrayList<String>();
      ExternalPackages externalPackages = model.getExternalPackages();
      if (externalPackages != null)
      {
         for (Iterator<ExternalPackage> i = externalPackages.getExternalPackage()
               .iterator(); i.hasNext();)
         {
            ExternalPackage externalPackage = i.next();
            ExtendedAttributeType attribute = ExtendedAttributeUtil.getAttribute(
                  externalPackage.getExtendedAttributes(), "carnot:connection:uri"); //$NON-NLS-1$
            if (attribute != null && attribute.getValue() != null)
            {
               result.add(attribute.getValue());
            }
         }
      }
      return result;
   }

   public static boolean referenceToAnotherVersionExists(ModelType model, String uri)
   {
      ModelType refModel = getReferencedModelByURI(model, uri);
      List<String> allReferencedURIs = ModelUtils.getURIsForExternalPackages(model);
      for (Iterator<String> i = allReferencedURIs.iterator(); i.hasNext();)
      {
         String aUri = i.next();
         if (!uri.equalsIgnoreCase(aUri))
         {
            Object aObject = model.getConnectionManager().find(aUri);
            if (aObject != null)
            {
               ModelType aModel = (ModelType) Reflect.getFieldValue(aObject, "eObject"); //$NON-NLS-1$
               if (aModel.getId().equalsIgnoreCase(refModel.getId()))
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   public static ModelType getReferencedModelByURI(ModelType model, String uri)
   {
      EObject o = model.getConnectionManager().find(uri);
      ModelType refModel = null;
      if(o != null)
      {
         Object refObject = Reflect.getFieldValue(o, "eObject"); //$NON-NLS-1$
         if (refObject != null && refObject instanceof ModelType)
         {
            refModel = (ModelType) refObject;
         }
      }
      return refModel;
   }

   public static Map<String, TypeType> getTypeMapping()
   {
      Map<String, TypeType> typeMapping;

      typeMapping = CollectionUtils.newMap();
      typeMapping.put(Type.String.getId(), TypeType.STRING_LITERAL);
      typeMapping.put(Type.Integer.getId(), TypeType.INTEGER_LITERAL);
      typeMapping.put(Type.Boolean.getId(), TypeType.BOOLEAN_LITERAL);
      typeMapping.put(Type.Calendar.getId(), TypeType.DATETIME_LITERAL);

      return typeMapping;
   }

   public static DataTypeType createDataType(DataType data)
   {
      Map<String, TypeType> typeMapping = getTypeMapping();

      DataTypeType dataType = xpdlFactory.createDataTypeType();
      String typeId = data.getType().getId();

      if (PredefinedConstants.PRIMITIVE_DATA.equals(typeId))
      {
         BasicTypeType basicType = xpdlFactory.createBasicTypeType();
         String primitiveType = AttributeUtil.getAttributeValue(data,
               PredefinedConstants.TYPE_ATT);
         TypeType tt = typeMapping.get(primitiveType);
         if (tt != null)
         {
            basicType.setType(tt);
         }
         dataType.setBasicType(basicType);
      }
      else if (PredefinedConstants.STRUCTURED_DATA.equals(typeId))
      {
         DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
         declaredType.setId(AttributeUtil.getAttributeValue(data,
               StructuredDataConstants.TYPE_DECLARATION_ATT));
         dataType.setDeclaredType(declaredType);
      }
      return dataType;
   }

   public static FormalParameterType cloneFormalParameterType(
         FormalParameterType referencedParameterType, DataType mappedData)
   {
      FormalParameterType parameterType = XpdlFactory.eINSTANCE
            .createFormalParameterType();
      if(mappedData != null)
      {
         parameterType.setDataType(createDataType(mappedData));
      }
      else
      {
         parameterType.setDataType((DataTypeType) EcoreUtil.copy(referencedParameterType.getDataType()));
      }

      parameterType.setDescription(referencedParameterType.getDescription());
      parameterType.setMode(referencedParameterType.getMode());
      parameterType.setId(referencedParameterType.getId());
      parameterType.setName(referencedParameterType.getName());
      return parameterType;
   }

   public static boolean haveDifferentTypes(FormalParameterType type1,
         FormalParameterType type2)
   {
      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataType1 = type1.getDataType();
      org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType dataType2 = type2.getDataType();
      if (dataType1 != null && dataType2 != null)
      {
         BasicTypeType basicType1 = dataType1.getBasicType();
         BasicTypeType basicType2 = dataType2.getBasicType();
         DeclaredTypeType declaredType1 = dataType1.getDeclaredType();
         DeclaredTypeType declaredType2 = dataType2.getDeclaredType();
         if (declaredType1 != null && declaredType2 != null)
         {
            if (declaredType1.getId().equals(declaredType2.getId()))
            {
               return false;
            }
            else
            {
               return true;
            }
         }
         if (basicType1 != null && declaredType2 != null)
         {
            return true;
         }
         if (basicType2 != null && declaredType1 != null)
         {
            return true;
         }
         if (basicType1 != null && basicType2 != null)
         {
            if (basicType1.getType().getName().equals(basicType2.getType().getName()))
            {
               return false;
            }
         }
      }
      return true;
   }

   public static ModelType getExternalModel(ExternalPackage pack)
   {
      String uri = ExtendedAttributeUtil.getAttributeValue(pack, IConnectionManager.URI_ATTRIBUTE_NAME);
      if (!StringUtils.isEmpty(uri))
      {
         ModelType model = findContainingModel(pack);
         IConnectionManager manager = model.getConnectionManager();
         if (manager != null)
         {
            EObject externalModel = manager.find(uri);
            if (externalModel instanceof IObjectReference)
            {
               externalModel = ((IObjectReference) externalModel).getEObject();
            }
            if (externalModel instanceof ModelType)
            {
               return (ModelType) externalModel;
            }
         }
      }
      return null;
   }

   public static String getLocation(ModelType model)
   {
      String projectName = null;
      String modelFilePath = null;
      Resource eResource = model.eResource();
      if (eResource != null)
      {
         URI eUri = eResource.getURI();
         if (!eUri.isPlatform())
         {
            return eUri.toFileString();
         }
         URI projectUri = eUri.trimSegments(eUri.segmentCount() - 2);
         URI modelUri = eUri.deresolve(projectUri);
         IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
            eUri.segment(1));
         IProject project = null;
         if (resource instanceof IProject)
         {
            project = (IProject) resource;
         }
         else if (resource != null)
         {
            project = resource.getProject();
         }
         if (project != null)
         {
            projectName = project.getName();
            modelFilePath = modelUri.toString();
            if (modelFilePath.startsWith(projectName + "/")) //$NON-NLS-1$
            {
               modelFilePath = modelFilePath.substring(projectName.length() + 1);
            }
         }
      }
      if (modelFilePath == null || projectName == null)
      {
         return null;
      }
      return getFileSystemPath(projectName, modelFilePath);
   }

   public static String getFileSystemPath(String project, String fullPath)
   {
      IWorkspaceRoot wspRoot = ResourcesPlugin.getWorkspace().getRoot();

      if (null != wspRoot && null != project)
      {
         IProject wspProject = wspRoot.getProject(project);
         if (null != wspProject && null != fullPath)
         {
            IResource resource = wspProject.findMember(fullPath);

            if (null != resource)
            {
               return resource.getLocation().toFile().getAbsolutePath();
            }
         }
      }

      return null;
   }

   public static boolean hasCircularDependency(String referencingModelID,
         ModelType referencedModel)
   {
      if (referencingModelID.equalsIgnoreCase(referencedModel.getId()))
      {
         return true;
      }
      ExternalPackages externalPackages = referencedModel.getExternalPackages();
      if (externalPackages != null)
      {
         for (ExternalPackage externalPackage : externalPackages.getExternalPackage())
         {
            if (externalPackage.getHref().equals(referencingModelID))
            {
               return true;
            }
            else
            {
               ModelType m = referencedModel.getConnectionManager().find(externalPackage);
               if (hasCircularDependency(referencingModelID, m))
               {
                  return true;
               }
            }
         }
      }
      return false;
   }

   public static boolean externalPackageExists(ModelType referingModel, ModelType referencedModel)
   {
      ExternalPackages externalPackages = referingModel.getExternalPackages();
      if (externalPackages != null)
      {
         for (ExternalPackage externalPackage : externalPackages.getExternalPackage())
         {
            if (externalPackage.getHref().equals(referencedModel.getId()))
            {
               return true;
            }
         }
      }
      return false;
   }

   public static List<IModelElement> findPackageReferingModelElements(ModelType referingModel, ExternalPackage externalPackage)
   {
      List<IModelElement> result = CollectionUtils.newList();
      for (ProcessDefinitionType process : referingModel.getProcessDefinition())
      {
         IdRef externalRef = process.getExternalRef();
         if (externalRef != null)
         {
            ExternalPackage packageRef = externalRef.getPackageRef();
            if (packageRef != null && packageRef.equals(externalPackage))
            {
               result.add(process);
            }
         }
         for (ActivityType activity : process.getActivity())
         {
            externalRef = activity.getExternalRef();
            if (externalRef != null)
            {
               ExternalPackage packageRef = externalRef.getPackageRef();
               if (packageRef != null && packageRef.equals(externalPackage))
               {
                  result.add(activity);
               }
            }
         }
      }
      return result;
   }

   public static String getActivityImplementationTypeText(
         ActivityImplementationType implementation)
   {
      switch (implementation)
      {
      case MANUAL_LITERAL:
         return Model_Messages.MANUAL_ACTIVITY;

      case ROUTE_LITERAL:
         return Model_Messages.ROUTE_ACTIVITY;

      case APPLICATION_LITERAL:
         return Model_Messages.APPLICATION_ACTIVITY;

      case SUBPROCESS_LITERAL:
         return Model_Messages.SUBPROCESS_ACTIVITY;
      }
      return ""; //$NON-NLS-1$
   }

   public static String getSubprocessModeTypeText(SubProcessModeType modeType)
   {
      switch (modeType)
      {
      case SYNC_SHARED_LITERAL:
         return Model_Messages.SYNC_SHARED;

      case SYNC_SEPARATE_LITERAL:
         return Model_Messages.SYNC_SEPARATE;

      case ASYNC_SEPARATE_LITERAL:
         return Model_Messages.ASYNC_SEPARATE;
      }
      return ""; //$NON-NLS-1$
   }

   public static String getFlowTypeText(String literal)
   {
      if (literal.equals("AND")) //$NON-NLS-1$
      {
         return Model_Messages.JOIN_SPLIT_LOOP_AND;
      }
      if (literal.equals("XOR")) //$NON-NLS-1$
      {
         return Model_Messages.JOIN_SPLIT_LOOP_XOR;
      }
      if (literal.equals("None")) //$NON-NLS-1$
      {
         return Model_Messages.JOIN_SPLIT_LOOP_NONE;
      }
      if (literal.equals("No Loop")) //$NON-NLS-1$
      {
         return Model_Messages.JOIN_SPLIT_LOOP_NOLOOP;
      }
      if (literal.equals("Repeat")) //$NON-NLS-1$
      {
         return Model_Messages.JOIN_SPLIT_LOOP_REPEAT;
      }
      if (literal.equals("While")) //$NON-NLS-1$
      {
         return Model_Messages.JOIN_SPLIT_LOOP_WHILE;
      }
      if (literal.equals("Unknown")) //$NON-NLS-1$
      {
         return Model_Messages.JOIN_SPLIT_LOOP_UNKNOWN;
      }
      return literal;
   }

   public static List<TypeDeclarationType> getAllTypeDeclarations(ModelType modelType)
   {
      List<TypeDeclarationType> dataTypes = CollectionUtils.newList();
      addTypeDeclarations(dataTypes, modelType);

      ExternalPackages packages = modelType.getExternalPackages();
      if (packages != null)
      {
         for (ExternalPackage pkg : packages.getExternalPackage())
         {
            ModelType externalModel = getExternalModel(pkg);
            if (externalModel != null)
            {
               addTypeDeclarations(dataTypes, externalModel);
            }
         }
      }
      return dataTypes;
   }

   private static void addTypeDeclarations(List<TypeDeclarationType> dataTypes, ModelType modelType)
   {
      TypeDeclarationsType typeDeclarations = modelType.getTypeDeclarations();
      if (typeDeclarations != null)
      {
         dataTypes.addAll(typeDeclarations.getTypeDeclaration());
      }
   }

   public static ArrayList<String> getPersistenceOptions(ProcessDefinitionType process)
   {
      ArrayList<String> list = new ArrayList<String>();
      list.add(AuditTrailPersistence.IMMEDIATE.name());

      for (Iterator<ActivityType> i = process.getActivity().iterator(); i.hasNext();)
      {
         ActivityType activity = i.next();
         if (activity.getApplication() != null)
         {
            ApplicationType application = activity.getApplication();
            if (application.isInteractive())
            {
               return list;
            }
            if (application.getType().getId().equals(PredefinedConstants.JMS_APPLICATION))
            {
               String directionType = AttributeUtil.getAttributeValue(
                     (IExtensibleElement) application, "carnot:engine:type"); //$NON-NLS-1$
               if (directionType != null && !directionType.equalsIgnoreCase("out")) //$NON-NLS-1$
               {
                  return list;
               }
            }
         }
      }

      list.add(AuditTrailPersistence.TRANSIENT.name());
      list.add(AuditTrailPersistence.DEFERRED.name());

      return list;
   }

   public static String getPersistenceOptionsText(String value)
   {
      if (value.equals(AuditTrailPersistence.IMMEDIATE.name()))
      {
         return Model_Messages.AUDITTRAIL_PERSISTENCE_IMMEDIATE;
      }
      if (value.equals(AuditTrailPersistence.DEFERRED.name()))
      {
         return Model_Messages.AUDITTRAIL_PERSISTENCE_DEFERRED;
      }
      if (value.equals(AuditTrailPersistence.TRANSIENT.name()))
      {
         return Model_Messages.AUDITTRAIL_PERSISTENCE_TRANSIENT;
      }
      return null;
   }

}