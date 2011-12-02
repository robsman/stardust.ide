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

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;

public class StructDataMappingUtils
{
   private static final String VISUAL_RULES_APP_TYPE_ID = "visualRulesEngineBean"; //$NON-NLS-1$

   // TODO: Move this information to VisualRules specific packages? 
   public static boolean isVizRulesApplication(ActivityType activity)
   {
      if (null != activity
            && ActivityImplementationType.APPLICATION_LITERAL == activity
                  .getImplementation()
            && !activity.getApplication().isInteractive()
            && null != activity.getApplication().getType()
            && VISUAL_RULES_APP_TYPE_ID.equals(activity.getApplication().getType()
                  .getId()))
      {
         return true;
      }

      return false;
   }

   private StructDataMappingUtils()
   {
   }

}
