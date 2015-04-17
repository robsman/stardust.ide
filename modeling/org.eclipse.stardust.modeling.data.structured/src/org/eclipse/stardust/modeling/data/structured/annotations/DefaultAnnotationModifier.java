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
package org.eclipse.stardust.modeling.data.structured.annotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.modeling.core.Verifier;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.xsd.XSDFeature;

public class DefaultAnnotationModifier implements IAnnotationModifier
{
   private static boolean notify;

   private List<IAnnotationChangedListener> listeners = new ArrayList<IAnnotationChangedListener>();

   public static final IAnnotationModifier INSTANCE = new DefaultAnnotationModifier();

   // implementation of IAnnotationModifier

   public boolean exists(IAnnotation annotation)
   {
      return annotation.exists();
   }

   public boolean canModify(IAnnotation annotation)
   {
      if (annotation instanceof ElementAnnotation)
      {
         return "false".equals(((ElementAnnotation) annotation).getConfigurationAttribute("empty")); //$NON-NLS-1$ //$NON-NLS-2$
      }
      return annotation instanceof AttributeAnnotation;
   }

   public Object getValue(IAnnotation annotation)
   {
      return getValue(annotation, annotation.getElement());
   }

   public Object getValue(IAnnotation annotation, XSDFeature element)
   {
      String rawValue = getRawValue(annotation, element);
      if (DefaultAnnotationModifier.isSelectionBased(annotation))
      {
         if (rawValue != null)
         {
            List<Object> items = DefaultAnnotationModifier.getAnnotationAllowedValues(annotation);
            for (int i = 0; i < items.size(); i++)
            {
               EnumerationItem item = (EnumerationItem) items.get(i);
               if (rawValue.equals(item.getValue()))
               {
                  return item;
               }
            }
         }
         return null;
      }
      return rawValue == null ? "" : rawValue; //$NON-NLS-1$
   }

   public void setValue(IAnnotation annotation, XSDFeature element, Object value)
   {
      if (value != null)
      {
         annotation.setRawValue(element, value.toString());
      }
      else
      {
         delete(annotation);
      }
   }

   private String getRawValue(IAnnotation annotation, XSDFeature element)
   {
      return annotation.getRawValue(element);
   }

   public void setValue(IAnnotation annotation, Object value)
   {
      Object oldValue = null;
      if (DefaultAnnotationModifier.isSelectionBased(annotation))
      {
         EnumerationItem item = (EnumerationItem) DefaultAnnotationModifier.getAnnotationValue(annotation);
         if (item != null)
         {
            oldValue = item;
         }
         item = (EnumerationItem) value;
         annotation.setRawValue(item.getValue());
      }
      else
      {
         if (DefaultAnnotationModifier.getAnnotationValue(annotation) != null)
         {
            oldValue = (String)DefaultAnnotationModifier.getAnnotationValue(annotation);
         }
         annotation.setRawValue((String) value);
      }
      fireAnnotationChanged(annotation, oldValue, value);
   }

   public boolean delete(IAnnotation annotation)
   {
      fireAnnotationChanged(annotation, DefaultAnnotationModifier.getAnnotationValue(annotation), null);

      if (annotation instanceof ElementAnnotation)
      {
         return ((ElementAnnotation) annotation).delete();
      }
      if (annotation instanceof AttributeAnnotation)
      {
         return ((AttributeAnnotation) annotation).delete();
      }
      return false;
   }
   public List<Object> getAllowedValues(IAnnotation annotation)
   {
      IConfigurationElement[] enumerations =
         annotation.getConfiguration().getChildren("enumeration"); //$NON-NLS-1$
      if (enumerations.length == 0)
      {
         return Collections.emptyList();
      }
      ArrayList<Object> labels = new ArrayList<Object>(enumerations.length);
      boolean isBoolean = "boolean".equals(DefaultAnnotationModifier.getType(annotation)); //$NON-NLS-1$
      boolean hasTrue = false;
      boolean hasFalse = false;
      for (int i = 0; i < enumerations.length; i++)
      {
         String name = enumerations[i].getAttribute("name"); //$NON-NLS-1$
         String value = enumerations[i].getAttribute("value"); //$NON-NLS-1$
         if (isBoolean && "true".equals(value)) //$NON-NLS-1$
         {
            hasTrue = true;
         }
         if (isBoolean && "false".equals(value)) //$NON-NLS-1$
         {
            hasFalse = true;
         }
         labels.add(new EnumerationItem(name, value));
      }
      if (isBoolean)
      {
         if (!hasTrue)
         {
            labels.add(new EnumerationItem(Structured_Messages.LBL_TRUE));
         }
         if (!hasFalse)
         {
            labels.add(new EnumerationItem(Structured_Messages.LBL_FALSE));
         }
      }
      return labels;
   }

   // static helpers

   public static boolean annotationExists(IAnnotation annotation)
   {
      IAnnotationModifier modifier = getModifier(annotation);
      return modifier == null ? INSTANCE.exists(annotation) : modifier.exists(annotation);
   }

   public static boolean canModifyAnnotation(IAnnotation annotation)
   {
      IAnnotationModifier modifier = getModifier(annotation);
      return modifier == null ? INSTANCE.canModify(annotation) : modifier.canModify(annotation);
   }

   public static void setAnnotationValue(IAnnotation annotation, Object object)
   {
      IAnnotationModifier modifier = getModifier(annotation);
      (modifier == null ? INSTANCE : modifier).setValue(annotation, object);
   }

   public static Object getAnnotationValue(IAnnotation annotation)
   {
      IAnnotationModifier modifier = getModifier(annotation);
      return modifier == null ? INSTANCE.getValue(annotation) : modifier.getValue(annotation);
   }

   public static Object getAnnotationValue(IAnnotation annotation, XSDFeature element)
   {
      IAnnotationModifier modifier = getModifier(annotation);
      return modifier == null ? INSTANCE.getValue(annotation, element) : modifier.getValue(annotation, element);
   }

   public static List<Object> getAnnotationAllowedValues(IAnnotation annotation)
   {
      IAnnotationModifier modifier = getModifier(annotation);
      return modifier == null ? INSTANCE.getAllowedValues(annotation) : modifier.getAllowedValues(annotation);
   }

   public static boolean deleteAnnotation(IAnnotation annotation)
   {
      IAnnotationModifier modifier = getModifier(annotation);
      return modifier == null ? INSTANCE.delete(annotation) : modifier.delete(annotation);
   }

   public static boolean isSelectionBased(IAnnotation annotation)
   {
      return !DefaultAnnotationModifier.getAnnotationAllowedValues(annotation).isEmpty();
   }

   public static IAnnotationModifier getModifier(IAnnotation annotation)
   {
      IAnnotation root = getRootAnnotation(annotation);
      IConfigurationElement config = root.getConfiguration();
      if (config.getAttribute("modifier") != null) //$NON-NLS-1$
      {
         try
         {
            return (IAnnotationModifier) config.createExecutableExtension("modifier"); //$NON-NLS-1$
         }
         catch (CoreException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return null;
   }

   public static IAnnotation getRootAnnotation(IAnnotation annotation)
   {
      while (annotation.getParent() != null)
      {
         annotation = annotation.getParent();
      }
      return annotation;
   }

   public static Verifier getVerifierFor(IAnnotation annotation)
   {
      String type = getType(annotation);
      if ("long".equals(type)) //$NON-NLS-1$
      {
         return VerifierFactory.longVerifier;
      }
      if ("double".equals(type)) //$NON-NLS-1$
      {
         return VerifierFactory.doubleVerifier;
      }
      return null;
   }

   public static String getType(IAnnotation annotation)
   {
      return annotation.getConfiguration().getAttribute("type"); //$NON-NLS-1$
   }

   public void addAnnotationChangedListener(IAnnotationChangedListener listener)
   {
      listeners.add(listener);
      startNotifying();
   }

   public void removeAnnotationChangedListener(IAnnotationChangedListener listener)
   {
      listeners.remove(listener);
   }

   public void fireAnnotationChanged(IAnnotation annotation, Object oldValue, Object newValue)
   {
      if (notify)
      {
         for (Iterator<IAnnotationChangedListener> i = listeners.iterator(); i.hasNext();)
         {
            IAnnotationChangedListener listener = i.next();
            listener.annotationChanged(annotation, oldValue, newValue);
         }
      }
   }

   public static void stopNotifying()
   {
      notify = false;
   }

   public static void startNotifying()
   {
      notify = true;
   }
}
