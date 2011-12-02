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
package org.eclipse.stardust.modeling.project.propertypages;

import org.eclipse.stardust.modeling.project.effort.EffortParameterScope;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * @author fherinean
 */
public class ElementPlanningAspectPropertyPage
      extends AbstractProjectPlanningAspectPropertyPage
{
   public Control createBody(Composite parent)
   {
      setScope(findScope());
      return createParameterComposite(parent);
   }

   private EffortParameterScope findScope()
   {
      for (EffortParameterScope scope : getEffortParameters().SCOPE_LIST)
      {
         if (scope.isApplicable(getModelElement()))
         {
            return scope;
         }
      }
      return null;
   }
}
