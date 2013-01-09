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
package org.eclipse.stardust.model.xpdl.builder.utils;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.ParametersFacade;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.KernelTweakingProperties;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
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
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotWorkflowModelResourceImpl;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.xsd.XSDSchema;

public class XpdlModelUtils
{
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
         for (Iterator i = element.eContents().iterator(); i.hasNext();)
         {
            Object content = i.next();
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

      for (TreeIterator i = model.eAllContents(); i.hasNext();)
      {
         EObject obj = (EObject) i.next();
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

      List activities = process.getActivity();
      for (int i = 0; i < activities.size(); ++i)
      {
         ActivityType activity = (ActivityType) activities.get(i);
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
         description = StringUtils.replace(description, "\r\n", "\n");
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

      Collection cdataParts = (Collection) featureMap.get(
            XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA(), false);
      if ((null != cdataParts) && !cdataParts.isEmpty())
      {
         switch (cdataParts.size())
         {
         case 0:
            result = null;
            break;
         case 1:
            result = cdataParts.iterator().next().toString();
            break;
         default:
            StringBuffer sb = new StringBuffer();
         for (Iterator i = cdataParts.iterator(); i.hasNext();)
         {
            sb.append(i.next().toString());
         }
         result = sb.toString();
         }
      }
      else
      {
         Collection textParts = (Collection) featureMap.get(
               XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), false);
         if ((null != textParts) && !textParts.isEmpty())
         {
            switch (textParts.size())
            {
            case 0:
               result = null;
               break;
            case 1:
               result = textParts.iterator().next().toString();
               break;
            default:
               StringBuffer sb = new StringBuffer();
               for (Iterator i = textParts.iterator(); i.hasNext();)
               {
                  sb.append(i.next().toString());
               }
               result = sb.toString();
            }
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
      EObject result = null;

      if (null != parent)
      {
         Object domain = parent.eGet(feature);
         if (domain instanceof List)
         {
            result = findElementById((List<EObject>) domain, id);
         }
      }
      return result;
   }

   public static <T extends IIdentifiableElement> T findIdentifiableElement(List<? extends T> domain, String id)
   {
      T result = findElementById(domain, id);
      return result instanceof IIdentifiableElement ? result : null;
   }

   public static EObject findElementById(List domain, IIdentifiableElement object)
   {
      String id = object.getId();

      EObject result = null;
      for (int i = 0; i < domain.size(); i++)
      {
         Object candidate = domain.get(i);
         String candidateId = candidate instanceof IIdentifiableElement
            ? ((IIdentifiableElement) candidate).getId()
            : candidate instanceof TypeDeclarationType
               ? ((TypeDeclarationType) candidate).getId()
               : null;
         if(object instanceof AccessPointType)
         {
            DirectionType direction = ((AccessPointType) object).getDirection();
            if(candidate instanceof AccessPointType)
            {
               DirectionType direction_ = ((AccessPointType) candidate).getDirection();
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

   public static List findMetaTypeInstances(List domain, String metaTypeId)
   {
      List result = ((null != domain) && !domain.isEmpty())
            ? new ArrayList(domain.size())
            : Collections.EMPTY_LIST;

      for (Iterator i = domain.iterator(); i.hasNext();)
      {
         IModelElement element = (IModelElement) i.next();
         if (element instanceof ITypedElement)
         {
            IMetaType metaType = ((ITypedElement) element).getMetaType();
            if ((null != metaType) && CompareHelper.areEqual(metaTypeId, metaType.getId()))
            {
               result.add(element);
            }
         }
      }

      return result;
   }

   public static void addSymbols(Set set, ISymbolContainer container, EReference ref,
         EStructuralFeature feat, String refId)
   {
      if (container == null)
      {
         return;
      }
      List subContainers =
         container instanceof DiagramType ? ((DiagramType) container).getPoolSymbols() :
         container instanceof PoolSymbol ? ((PoolSymbol) container).getLanes() :
         container instanceof LaneSymbol ? ((LaneSymbol) container).getChildLanes() :
         Collections.EMPTY_LIST;
      for (Iterator i = subContainers.iterator(); i.hasNext();)
      {
         addSymbols(set, (ISymbolContainer) i.next(), ref, feat, refId);
      }
      List list = (List) container.eGet(ref);
      for (Iterator i = /*container.getNodes().list(ref)*/list.iterator(); i.hasNext();)
      {
         INodeSymbol symbol = (INodeSymbol) i.next();
         if (refId == null || refId.equals(getStringValue(symbol.eGet(feat))))
         {
            set.add(symbol);
         }
      }
   }

   public static void addSymbols(Set set, ISymbolContainer container, EReference ref,
         EStructuralFeature feat, IModelElement element)
   {
      if (null != container)
      {
         List subContainers = container instanceof DiagramType
               ? ((DiagramType) container).getPoolSymbols()
               : container instanceof PoolSymbol
                     ? ((PoolSymbol) container).getLanes()
                     : container instanceof LaneSymbol
                           ? ((LaneSymbol) container).getChildLanes()
                           : Collections.EMPTY_LIST;
         for (Iterator i = subContainers.iterator(); i.hasNext();)
         {
            addSymbols(set, (ISymbolContainer) i.next(), ref, feat, element);
         }
         List list = (List) container.eGet(ref);
         for (Iterator i = /*container.getNodes().list(ref)*/list.iterator(); i.hasNext();)
         {
            INodeSymbol symbol = (INodeSymbol) i.next();
            if (element == symbol.eGet(feat))
            {
               set.add(symbol);
            }
         }
      }
   }

   private static String getStringValue(Object object)
   {
      return object == null ? null : object.toString();
   }

   public static DataTypeType getDataType(IModelElement element, String typeId)
   {
      return (DataTypeType) findIdentifiableElement(
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

   private XpdlModelUtils()
   {
      // utility class
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

   public static IIdentifiableModelElement getIdentifiableModelProxy(
         final EObject model, Class theClass)
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

   public static void resolve(ModelType model, IExtensibleElement extensible)
   {
      // resolve internal type declarations
      if (extensible == model)
      {
         TypeDeclarationsType declarations = model.getTypeDeclarations();
         if (declarations != null)
         {
            List types = declarations.getTypeDeclaration();
            for (int i = 0; i < types.size(); i++)
            {
               TypeDeclarationType decl = (TypeDeclarationType) types.get(i);
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
//      IConfigurationElement config = SpiExtensionRegistry.getConfiguration(extensible);
//      if (config != null)
//      {
//         IConfigurationElement[] refs = config.getChildren("reference");
//         for (int k = 0; k < refs.length; k++)
//         {
//            AttributeType attribute = AttributeUtil.getAttribute(extensible,
//                  refs[k].getAttribute("attributeName"));
//            if (attribute != null)
//            {
//               String scopeList = refs[k].getAttribute("scope");
//               setReference(attribute, model, scopeList);
//            }
//         }
//      }

      // resolve permissions
      // TODO: make permissions a first class element
      IAttributeCategory category = AttributeUtil.createAttributeCategory(extensible, "authorization");
      List attributes = category.getAttributes();
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         setReference(attribute, model, "role+organization");
      }

      List contents = extensible.eContents();
      for (int i = 0; i < contents.size(); i++)
      {
         Object item = contents.get(i);
         if (item instanceof IExtensibleElement)
         {
            resolve(model, (IExtensibleElement) item);
         }
      }
   }

   private static void setReference(AttributeType attribute, ModelType model,
         String scopeList)
   {
      String id = attribute.getValue();
      StringTokenizer st = new StringTokenizer(scopeList, "+");
      while (st.hasMoreTokens())
      {
         String scope = st.nextToken();
         if ("struct".equals(scope))
         {
            // special case of a reference to a structured type
            TypeDeclarationsType declarations = model.getTypeDeclarations();
            if (declarations != null)
            {
               TypeDeclarationType decl = (TypeDeclarationType) findElementById(
                     declarations.getTypeDeclaration(), id);
               if (decl != null)
               {
                  AttributeUtil.setReference(attribute, decl);
               }
            }
         }
         else
         {
            EStructuralFeature feature = model.eClass().getEStructuralFeature(scope);
            IIdentifiableElement element = findIdentifiableElement(model, feature, id);
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
      if (ParametersFacade.instance().getBoolean(
            KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, false))
      {
         modelString = XpdlUtils.convertXpdl2Carnot(modelString,
               XpdlUtils.UTF8_ENCODING);
      }

      String modelXmlEncoding = getXmlEncoding(modelString);

      CarnotWorkflowModelResourceImpl resource = new CarnotWorkflowModelResourceImpl(
            URI.createURI("http://only/a/dummy/URI"));
      ResourceSetImpl resourceSet = new ResourceSetImpl();
      resourceSet.getResources().add(resource);

      Map options = new HashMap();
      options.put("RECORD_UNKNOWN_FEATURE", Boolean.TRUE);
      try
      {
         resource.load(new ByteArrayInputStream(modelString
               .getBytes(modelXmlEncoding)), options);
      }
      catch (Exception e)
      {
         throw new RuntimeException(e);
      }

      EList l = resource.getContents();
      for (Iterator i = l.iterator(); i.hasNext();)
      {
         Object o = i.next();
         if (o instanceof DocumentRoot)
         {
            ModelType model = ((DocumentRoot) o).getModel();
            resolve(model, model);
            return model;
         }
      }

      throw new RuntimeException("Could not load the model (DocumentRoot not found).");
   }

   private static String getXmlEncoding(String text)
   {
      String pattern = "encoding=\"";
      int offset = text.indexOf(pattern) + pattern.length();
      int pos = text.indexOf("\"", offset);
      return text.substring(offset, pos);
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
}