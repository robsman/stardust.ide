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
package org.eclipse.stardust.modeling.validation;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;


public class BridgeObject
{
   private IType endClass;

   private final DirectionType direction;
   
   private final String label;

   public BridgeObject(IType endClass, DirectionType direction)
   {
      this.endClass = endClass;
      this.direction = direction;
      label = endClass == null ? null : endClass.getFullyQualifiedName();
   }

   public BridgeObject(IType endClass, DirectionType direction, String label)
   {
      this.endClass = endClass;
      this.direction = direction;
      this.label = label;
   }

   public String toString()
   {
      return label;
   }

   public IType getEndClass()
   {
      return endClass;
   }

   public DirectionType getDirection()
   {
      return direction;
   }

   public boolean acceptAssignmentFrom(BridgeObject rhs)
   {
      // direction must be in or inout or null
      if (direction == DirectionType.OUT_LITERAL)
      {
         return false;
      }
      // rhs direction must be out, inout or null
      if (rhs.direction == DirectionType.IN_LITERAL)
      {
         return false;
      }

      return TypeFinder.isAssignable(getEndClass(), rhs.getEndClass());
   }
   
   public static BridgeObject getBridge(ITypedElement ap, String path,
         DirectionType direction) throws ValidationException
   {
      return getBridge(ap, path, direction, null);
   }

   public static BridgeObject getBridge(ITypedElement ap, String path,
         DirectionType direction, AccessPathEvaluationContext context)
         throws ValidationException
   {
      BridgeObject result;

      IBridgeObjectProvider bridgeProvider = ValidatorRegistry
            .getBridgeObjectProvider(ap);
      if (null != bridgeProvider)
      {
         result = bridgeProvider.getBridgeObject(ap, path, direction);
      }
      else
      {
         result = new BridgeObject(JavaDataTypeUtils
               .getTypeFromCurrentProject(Object.class.getName()), direction);
      }
      return result;
   }
   
   public static void checkMapping(ITypedElement left, String leftPath,
         ITypedElement right, String rightPath) throws ValidationException
   {
      checkMapping(left, leftPath, right, rightPath, null);
   }

   public static void checkMapping(ITypedElement left, String leftPath,
         ITypedElement right, String rightPath, ActivityType activity)
         throws ValidationException
   {
      BridgeObject leftBridge = getBridge(left, leftPath, DirectionType.IN_LITERAL,
            new AccessPathEvaluationContext(right, rightPath, activity));
      BridgeObject rightBridge = getBridge(right, rightPath, DirectionType.OUT_LITERAL,
            new AccessPathEvaluationContext(left, leftPath, activity));

      // (fh) special case of VisualRules application activities
      if (StructDataMappingUtils.isVizRulesApplication(activity)
            && ((isAssignable(rightBridge.getEndClass(), Map.class) //
                  && isAssignable(leftBridge.getEndClass(), Serializable.class)) // 
               || (isAssignable(rightBridge.getEndClass(), Serializable.class)//  
                  && isAssignable(leftBridge.getEndClass(), Map.class))))
      {
         return;
      }

      if ( !leftBridge.acceptAssignmentFrom(rightBridge))
      {
         throw new ValidationException(MessageFormat.format(
               Validation_Messages.BridgeObject_assignmentNotCompatible, new Object[] {
                     leftBridge, rightBridge }), rightPath == null
               || rightPath.length() == 0 ? (Object) right : rightPath);
      }
   }

   private static boolean isAssignable(IType type, Class<?> clazz)
   {
      if (null == type || null == clazz)
      {
         return false;
      }
   
      try
      {
         ITypeHierarchy typeHierarchy = type.newSupertypeHierarchy(null);
         IType clazzType = JavaDataTypeUtils.getTypeFromCurrentProject(clazz.getName());            
         return typeHierarchy.contains(clazzType);
      }
      catch (JavaModelException e)
      {
         return false;
      }
   }
}
