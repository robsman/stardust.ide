/*******************************************************************************
 * Copyright (c) 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Florin.Herinean (SunGard CSA LLC) - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.stardust.model.xpdl.xpdl2.util;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.stardust.model.xpdl.xpdl2.*;

public class XpdlUtil
{
   public static LoopStandardType getOrCreateLoopStandard(LoopType loop)
   {
      if (loop == null)
      {
         loop = XpdlFactory.eINSTANCE.createLoopType();
      }
      loop.setLoopType(LoopTypeType.STANDARD);
      LoopStandardType loopStandard = loop.getLoopStandard();
      if (loopStandard == null)
      {
         loopStandard = XpdlFactory.eINSTANCE.createLoopStandardType();
         loop.setLoopStandard(loopStandard);
         loop.setLoopMultiInstance(null);
      }
      return loopStandard;
   }

   public static LoopMultiInstanceType getOrCreateLoopMulti(LoopType loop)
   {
      if (loop == null)
      {
         loop = XpdlFactory.eINSTANCE.createLoopType();
      }
      loop.setLoopType(LoopTypeType.MULTI_INSTANCE);
      LoopMultiInstanceType loopMulti = loop.getLoopMultiInstance();
      if (loopMulti == null)
      {
         loopMulti = XpdlFactory.eINSTANCE.createLoopMultiInstanceType();
         loop.setLoopMultiInstance(loopMulti);
         loop.setLoopStandard(null);
      }
      return loopMulti;
   }

   public static String getText(FeatureMap featureMap, boolean preferCDATA)
   {
      EStructuralFeature feature = preferCDATA
            ? XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA()
            : XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text();

      Collection<?> parts = (Collection<?>) featureMap.get(feature, false);
      if (parts == null || parts.isEmpty())
      {
         EStructuralFeature fallbackFeature = preferCDATA
               ? XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text()
               : XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA();
         parts = (Collection<?>) featureMap.get(fallbackFeature, false);
      }
      String result = null;
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

   public static void setText(FeatureMap mixed, String text, boolean preferCDATA)
   {
      EStructuralFeature excludedFeature = preferCDATA
            ? XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text()
            : XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA();
      if (mixed.isSet(excludedFeature))
      {
         mixed.unset(excludedFeature);
      }

      EStructuralFeature feature = preferCDATA
            ? XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA()
            : XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text();
      if (text == null)
      {
         mixed.unset(feature);
      }
      else
      {
         mixed.set(feature, Collections.singleton(text));
      }
   }

   public static String getLoopStandardCondition(LoopType loop)
   {
      if (loop != null && loop.getLoopType() == LoopTypeType.STANDARD)
      {
         LoopStandardType loopStandard = loop.getLoopStandard();
         if (loopStandard != null)
         {
            return getLoopStandardCondition(loopStandard);
         }
      }
      return null;
   }

   public static String getLoopStandardCondition(LoopStandardType loopStandard)
   {
      ExpressionType expression = loopStandard.getLoopCondition();
      if (expression == null)
      {
         return null;
      }
      return getText(expression.getMixed(), false);
   }

   public static void setLoopStandardCondition(LoopStandardType loopStandard, String condition)
   {
      ExpressionType expression = loopStandard.getLoopCondition();
      if (expression == null)
      {
         expression = XpdlFactory.eINSTANCE.createExpressionType();
         loopStandard.setLoopCondition(expression);
      }
      setText(expression.getMixed(), condition, false);
   }

}
