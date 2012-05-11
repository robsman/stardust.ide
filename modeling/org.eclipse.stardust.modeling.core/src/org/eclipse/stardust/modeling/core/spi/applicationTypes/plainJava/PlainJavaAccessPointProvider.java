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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.constants.PlainJavaConstants;
import org.eclipse.stardust.engine.core.pojo.utils.JavaAccessPointType;
import org.eclipse.stardust.engine.core.pojo.utils.JavaApplicationTypeHelper;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

/**
 * @author fherinean
 * @version $Revision$
 */
public class PlainJavaAccessPointProvider implements IAccessPointProvider
{

   /**
    * Returns all intrinsic access points which are computed appropriate to the elements
    * class, method and constructor attribute values.
    * 
    * @param element
    *           The application element.
    * @return All calculated {@link AccessPointType}s. An empty list if no access points
    *         could be calculated.
    */
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      List result = Collections.EMPTY_LIST;

      if (element instanceof IExtensibleElement)
      {
         String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               CarnotConstants.CLASS_NAME_ATT);
         className = VariableContextHelper.getInstance().getContext(element)
               .replaceAllVariablesByDefaultValue(className);
         String methodName = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT);
         methodName = VariableContextHelper.getInstance().getContext(element)
               .replaceAllVariablesByDefaultValue(methodName);
         String constructorName = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, CarnotConstants.CONSTRUCTOR_NAME_ATT);
         constructorName = VariableContextHelper.getInstance().getContext(element)
               .replaceAllVariablesByDefaultValue(constructorName);
         
         result = getIntrinsicAccessPoints((IExtensibleElement) element, className,
               methodName, constructorName, null, null, false);
      }
      return result;
   }

   /**
    * Returns all intrinsic access points which are computed appropriate to the
    * application element, class name, method name, constructor name and data flow
    * direction.
    * 
    * @param element
    *           The application element.
    * @param className
    *           The class name of the application.
    * @param methodName
    *           The method name of the application.
    * @param constructorName
    *           The constructor name of the application.
    * @param direction
    *           The data flow direction of the access points.
    * @param hint
    * @param paramsOnly
    *           Only the parameter of the method are considered.
    * @return All calculated {@link AccessPointType}s. An empty list if no access points
    *         could be calculated.
    */
   public static List getIntrinsicAccessPoints(IExtensibleElement element,
         String className, String methodName, String constructorName,
         DirectionType direction, String hint, boolean paramsOnly)
   {
      return getIntrinsicAccessPoints(element, className, methodName, false,
            constructorName, direction, hint, paramsOnly);
   }

   /**
    * Computes all intrinsic access points appropriate to the application element, class
    * name, method name, constructor name and data flow direction and returns them.
    * 
    * @param element
    *           The application element.
    * @param className
    *           The class name of the application.
    * @param methodName
    *           The method name of the application.
    * @param usedForObjectCreation
    *           The methodName is used for object creation (e.g. create() for EJB).
    * @param constructorName
    *           The constructor name of the application.
    * @param direction
    *           The data flow direction of the access points.
    * @param hint
    * @param paramsOnly
    *           Only the parameter of the method are considered.
    * @return All calculated {@link AccessPointType}s. An empty list if no access points
    *         could be calculated.
    */
   public static List getIntrinsicAccessPoints(IExtensibleElement element,
         String fullClassName, String methodName, boolean usedForObjectCreation,
         String constructorName, DirectionType direction, String hint, boolean paramsOnly)
   {
      final ArrayList accessPoints = new ArrayList();

      if (element != null)
      {
         // if the element is not browsable, there are no access points.
         IModelElement ref = element instanceof ITypedElement ? ((ITypedElement) element)
               .getMetaType() : (IModelElement) element;
         DataTypeType dataType = ModelUtils.getDataType(ref,
               CarnotConstants.SERIALIZABLE_DATA_ID);
         AttributeType browsable = AttributeUtil.getAttribute(element,
               CarnotConstants.BROWSABLE_ATT);
         if (browsable == null || AttributeUtil.getBooleanValue(browsable)
               || AccessPointUtil.isOut(direction))
         {
            if (fullClassName != null)
            {
               // get the exact class type from the model elements project
               TypeFinder finder = new TypeFinder(ref);
               TypeInfo type = finder.findType(fullClassName);
               if (type != null)
               {
                  // get all methods from the type and add parameter access points and
                  // possibly method access points
                  String fragmentName = getFragmentNameFilter(hint);
                  List methods = finder.getMethods(type, fragmentName);
                  for (int i = 0; i < methods.size(); i++)
                  {
                     MethodInfo method = (MethodInfo) methods.get(i);
                     
                     if (methodName != null)
                     {
                        String compactMethodName = StringUtils
                           .replace(methodName, ", ", ","); //$NON-NLS-1$ //$NON-NLS-2$
                        if (method.getEncoded().equals(compactMethodName))
                        {
                           method.setUsedForObjectCreation(usedForObjectCreation);
                           addParameterAccessPoints(method, accessPoints, direction,
                                 dataType, paramsOnly);
                           if (paramsOnly)
                           {
                              return accessPoints;
                           }
                        }
                     }

                     if (!paramsOnly)
                     {
                        addMethodAccessPoint(method, accessPoints, direction, dataType);
                     }
                  }
                  // if there is a method hint, no use to add constructor parameters
                  if (hint == null && constructorName != null)
                  {
                     String compactCtorName = StringUtils
                        .replace(constructorName, ", ", ","); //$NON-NLS-1$ //$NON-NLS-2$

                     // get all constructors from type and add parameter access points
                     List constructors = finder.getConstructors(type);
                     for (int i = 0; i < constructors.size(); i++)
                     {
                        MethodInfo method = (MethodInfo) constructors.get(i);
                        if (method.getEncoded().equals(compactCtorName)
                              && method.isAccessible())
                        {
                           addParameterAccessPoints(method, accessPoints, direction,
                                 dataType, paramsOnly);
                        }
                     }
                  }
               }
            }
         }
      }
      return accessPoints;
   }

   public static String getFragmentNameFilter(String hint)
   {
      if (hint == null)
      {
         hint = ""; //$NON-NLS-1$
      }
      else
      {
         int ix = hint.indexOf('(');
         if (ix >= 0)
         {
            hint = hint.substring(0, ix);
         }
      }
      return hint;
   }

   /**
    * Creates new access points in consideration of data flow direction and of parameter
    * count and return value of the method and add them to the access points list.
    * 
    * @param method
    *           The signature of the application's method.
    * @param accessPoints
    *           All computed access points.
    * @param direction
    *           The data flow direction of the access point to create.
    * @param dataType
    *           The type of the access point to create.
    * @param paramsOnly
    *           Only the parameter of the method are considered.
    */
   public static void addParameterAccessPoints(MethodInfo method,
         final ArrayList accessPoints, DirectionType direction, DataTypeType dataType,
         boolean paramsOnly)
   {
      String prefix = method.isUsedForObjectCreation()
            ? PlainJavaConstants.CONSTRUCTOR_PARAMETER_PREFIX
            : PlainJavaConstants.METHOD_PARAMETER_PREFIX;

      if (AccessPointUtil.isIn(direction))
      {
         for (int i = 0; i < method.getParameterCount(); i++)
         {
            String humanName = method.getParameterName(i);
            String paramName = humanName.toLowerCase().charAt(0) + prefix + (i + 1);
            AccessPointType accessPoint = AccessPointUtil.createIntrinsicAccessPoint(
                  paramName,//
                  paramName + " : " + humanName, method.getParameterType(i), //$NON-NLS-1$
                  DirectionType.IN_LITERAL,//
                  false,//
                  new String[] {
                        JavaAccessPointType.PARAMETER.getId(),
                        JavaAccessPointType.class.getName()},//
                  dataType);
            accessPoints.add(accessPoint);
         }
      }
      if (!paramsOnly && method.hasReturn() && AccessPointUtil.isOut(direction))
      {
         AccessPointType accessPoint = AccessPointUtil.createIntrinsicAccessPoint(
               JavaApplicationTypeHelper.RETURN_VALUE_ACCESS_POINT_NAME,//
               JavaApplicationTypeHelper.RETURN_VALUE_ACCESS_POINT_NAME
                     + " : " + method.getReturnName(), method.getReturnType(), //$NON-NLS-1$
               DirectionType.OUT_LITERAL,//
               false,//
               new String[] {
                     JavaAccessPointType.RETURN_VALUE.getId(),
                     JavaAccessPointType.class.getName()},//
               dataType);
         accessPoints.add(accessPoint);
      }
   }

   /**
    * Creates a new access point in consideration of the data flow direction and method
    * signature and add it to the access points list.
    * 
    * @param method
    *           The signature of the application's method.
    * @param accessPoints
    *           All computed access points.
    * @param direction
    *           The data flow direction of the access point to create.
    * @param dataType
    *           The type of the access point to create.
    */
   public static void addMethodAccessPoint(MethodInfo method,
         final ArrayList accessPoints, DirectionType direction, DataTypeType dataType)
   {
      if (method.getParameterCount() == 0)
      {
         if (method.hasReturn() && AccessPointUtil.isOut(direction)
               && method.isAccessible())
         {
            AccessPointType accessPoint = AccessPointUtil.createIntrinsicAccessPoint(
                  method.getEncoded(),//
                  method.getLabel(),//
                  method.getReturnType(),//
                  DirectionType.OUT_LITERAL,//
                  !method.isPrimitiveReturn() && !method.isArrayReturn(),//
                  new String[] {
                        JavaAccessPointType.METHOD.getId(),
                        JavaAccessPointType.class.getName()},//
                  dataType);
            accessPoints.add(accessPoint);
         }
      }
      else if (method.getParameterCount() == 1 && AccessPointUtil.isIn(direction)
            && method.isAccessible())
      {
         AccessPointType accessPoint = AccessPointUtil.createIntrinsicAccessPoint(method
               .getEncoded(),//
               method.getLabel(),//
               method.getParameterType(0),//
               DirectionType.IN_LITERAL,//
               false,//
               new String[] {
                     JavaAccessPointType.METHOD.getId(),
                     JavaAccessPointType.class.getName()},//
               dataType);
         accessPoints.add(accessPoint);
      }
   }

   /**
    * Splits the given access path at the first occurrence of '.' in the path.
    * 
    * @param accessPath
    * @return The splitted access path in an array with two elements. The unmodified
    *         access path in the first array element if access path couldn't be splitted
    *         up.
    */
   public static String[] splitAccessPath(String accessPath)
   {
      if (accessPath != null && accessPath.length() > 0)
      {
         int pCount = 0;
         for (int i = 0; i < accessPath.length(); i++)
         {
            if (accessPath.charAt(i) == '.' && pCount == 0)
            {
               return new String[] {
                     accessPath.substring(0, i), accessPath.substring(i + 1)};
            }
            if (accessPath.charAt(i) == '(')
            {
               pCount++;
            }
            if (accessPath.charAt(i) == ')')
            {
               pCount--;
            }
         }
      }
      return new String[] {accessPath, null};
   }
}
