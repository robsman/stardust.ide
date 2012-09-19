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
package org.eclipse.stardust.modeling.common.ui.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class FeatureTester extends PropertyTester
{
   public boolean test(Object value, String property, Object[] args, Object expectedValue)
   {
      if (value instanceof EObject && "feature".equals(property) && args.length > 0) //$NON-NLS-1$
      {
         for (String featureId : args[0].toString().split("\\.")) //$NON-NLS-1$
         {
            if (value == null)
            {
               break;
            }
            EObject eObject = (EObject) value;
            EClass eClass = eObject.eClass();
            EStructuralFeature feature = eClass.getEStructuralFeature(featureId);
            value = eObject.eGet(feature);
         }
         if (expectedValue == null)
         {
            return value == null;
         }
         if (value != null)
         {
            return expectedValue instanceof String ? expectedValue.equals(value.toString()) : expectedValue.equals(value);
         }
      }
      return false;
   }
}
