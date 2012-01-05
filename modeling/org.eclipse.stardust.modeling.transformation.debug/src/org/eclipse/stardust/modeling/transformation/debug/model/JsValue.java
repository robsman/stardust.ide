/*******************************************************************************
 * Copyright (c) 2007, 2011 Actuate Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Actuate Corporation  - initial API and implementation
 *    SunGard CSA LLC - Adapted to Eclipse Debug Framework
 *******************************************************************************/
package org.eclipse.stardust.modeling.transformation.debug.model;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

import com.infinity.bpm.thirdparty.org.mozilla.javascript.Context;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.ContextAction;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.NativeJavaConstructor;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.NativeJavaMethod;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.NativeJavaObject;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.NativeJavaPackage;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.Scriptable;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.ScriptableObject;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.Undefined;
import com.infinity.bpm.thirdparty.org.mozilla.javascript.debug.DebuggableObject;

public class JsValue extends JsDebugElement implements IValue
{
   private JsVariable variable;
   private Object rawValue; // TODO
   private String reservedValueType = null; // TODO
   private boolean isPrimitive;

   public JsValue(JsVariable variable, Object rawValue)
   {
      super((JsDebugTarget) variable.getDebugTarget());
      this.variable = variable;
      this.rawValue = rawValue;
   }

   public JsValue(JsVariable variable, Object rawValue, boolean isPrimitive)
   {
      super((JsDebugTarget) variable.getDebugTarget());
      this.variable = variable;
      this.rawValue = rawValue;
      this.isPrimitive = isPrimitive;
   }

   public Object getRawValue()
   {
      return rawValue;
   }

   public String getReferenceTypeName() throws DebugException
   {
      return variable.getReferenceTypeName();
   }

   public String getValueString() throws DebugException
   {
      return null == rawValue ? "" :rawValue.toString(); // TODO
   }

   public boolean isAllocated() throws DebugException
   {
      return true;
   }

   public IVariable[] getVariables()
   {
      return (IVariable[]) Context.call(new ContextAction()
      {
         public Object run(Context arg0)
         {
            try
            {
               return getVariablesActionImpl();
            }
            catch (Exception e)
            {
               e.printStackTrace();
               return JsVariable.NO_VARS;
            }
         }
      });
   }

   public boolean hasVariables() throws DebugException
   {
      return getVariables().length > 0;
   }

   public static boolean isValidJsValue(Object val)
   {
      return (val != Scriptable.NOT_FOUND //
            && !(val instanceof Undefined) //
            && !(val instanceof NativeJavaMethod) //
            && !(val instanceof NativeJavaConstructor) //
      && !(val instanceof NativeJavaPackage));

   }

   private IVariable[] getVariablesActionImpl() throws DebugException
   {
      if (reservedValueType != null)
      {
         return JsVariable.NO_VARS;
      }

      Object valObj = rawValue;

      if (rawValue instanceof NativeJavaObject)
      {
         valObj = ((NativeJavaObject) rawValue).unwrap();
      }

      if (valObj == null || valObj.getClass().isPrimitive() || isPrimitive)
      {
         return JsVariable.NO_VARS;
      }

      List children = new ArrayList();

      if (valObj.getClass().isArray())
      {
         int len = Array.getLength(valObj);

         boolean primitive = valObj.getClass().getComponentType().isPrimitive();

         for (int i = 0; i < len; i++)
         {
            Object aobj = Array.get(valObj, i);

            if (isValidJsValue(aobj))
            {
               children.add(new JsVariable(variable.getFrame(), variable, "["
                     + children.size() + "]", aobj, primitive));
            }
         }
      }
      else if (valObj instanceof Scriptable)
      {
         Object[] ids;

         if (valObj instanceof DebuggableObject)
         {
            ids = ((DebuggableObject) valObj).getAllIds();
         }
         else
         {
            ids = ((Scriptable) valObj).getIds();
         }

         if (ids == null || ids.length == 0)
         {
            return JsVariable.NO_VARS;
         }

         for (int i = 0; i < ids.length; i++)
         {
            if (ids[i] instanceof String)
            {
               Object val = ScriptableObject.getProperty((Scriptable) valObj,
                     (String) ids[i]);

               if (val instanceof NativeJavaObject)
               {
                  val = ((NativeJavaObject) val).unwrap();
               }

               if (isValidJsValue(val))
               {
                  children.add(new JsVariable(variable.getFrame(), variable, (String) ids[i],
                        val));
               }
            }
         }
      }
      else
      {
         // refelct native java objects
         reflectMembers(valObj, children);
      }

      if (children.size() == 0)
      {
         return JsVariable.NO_VARS;
      }

      return (IVariable[]) children.toArray(new IVariable[children.size()]);
   }

   private void reflectMembers(Object obj, List children)
   {
      HashMap names = new HashMap();

      Class clazz = obj.getClass();
      Field field = null;

      try
      {
         while (clazz != null)
         {
            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++)
            {
               field = fields[i];
               field.setAccessible(true);

               if (Modifier.isStatic(field.getModifiers())
                     || names.containsKey(field.getName()))
               {
                  continue;
               }

               // special fix for Java 5 LinkedHashMap.Entry hashCode()
               // implementation error, which is fixed in 6 though.
               if (obj instanceof LinkedHashMap && "header".equals(field.getName())) //$NON-NLS-1$
               {
                  continue;
               }

               JsVariable jsVar = new JsVariable(variable.getFrame(), variable, field.getName(),
                     field.get(obj), field.getType().isPrimitive());

               jsVar.setReferenceTypeName(convertArrayTypeName(field.getType(), field.getType()
                     .isPrimitive()));

               children.add(jsVar);

               names.put(field.getName(), null);
            }

            clazz = clazz.getSuperclass();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private static String convertArrayTypeName(Class clz, boolean explicitPrimitive)
   {
      if (clz.isArray())
      {
         return convertPrimativeTypeName(clz.getComponentType(), explicitPrimitive)
               + "[]"; //$NON-NLS-1$
      }
      else
      {
         return convertPrimativeTypeName(clz, explicitPrimitive);
      }
   }

   private static String convertPrimativeTypeName(Class clz, boolean explictPrimitive)
   {
      if (clz.isPrimitive() || explictPrimitive)
      {
         if (Boolean.class.equals(clz) || Boolean.TYPE.equals(clz))
         {
            return "boolean"; //$NON-NLS-1$
         }

         if (Character.class.equals(clz) || Character.TYPE.equals(clz))
         {
            return "char"; //$NON-NLS-1$
         }

         if (Byte.class.equals(clz) || Byte.TYPE.equals(clz))
         {
            return "byte"; //$NON-NLS-1$
         }

         if (Short.class.equals(clz) || Short.TYPE.equals(clz))
         {
            return "short"; //$NON-NLS-1$
         }

         if (Integer.class.equals(clz) || Integer.TYPE.equals(clz))
         {
            return "int"; //$NON-NLS-1$
         }

         if (Long.class.equals(clz) || Long.TYPE.equals(clz))
         {
            return "long"; //$NON-NLS-1$
         }

         if (Float.class.equals(clz) || Float.TYPE.equals(clz))
         {
            return "float"; //$NON-NLS-1$
         }

         if (Double.class.equals(clz) || Double.TYPE.equals(clz))
         {
            return "double"; //$NON-NLS-1$
         }

         if (Void.class.equals(clz) || Void.TYPE.equals(clz))
         {
            return "void"; //$NON-NLS-1$
         }
      }

      return clz.getName();
   }
}
