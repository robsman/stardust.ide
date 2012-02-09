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
package org.eclipse.stardust.modeling.core.properties;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.core.Diagram_Messages;

public class ActivityControllingPropertyPage extends AbstractControllingPropertyPage
{
   private static final String NOT_INCLUDE_LB = Diagram_Messages.ActivityControllingPropertyPage_NotInclude_LB;
   private static final String INCLUDE_LB = Diagram_Messages.ActivityControllingPropertyPage_IncludeLB;
   private static final Map rpCalculationByKeys;
   private static final Map rpCalculationByValues;
   
   static
   {
      Map byValues = new HashMap();
      byValues.put(Boolean.TRUE, INCLUDE_LB);
      byValues.put(Boolean.FALSE, NOT_INCLUDE_LB);
      rpCalculationByKeys = Collections.unmodifiableMap(byValues);
      Map byKeys = new HashMap();
      byKeys.put(INCLUDE_LB, Boolean.TRUE);
      byKeys.put(NOT_INCLUDE_LB, Boolean.FALSE);
      rpCalculationByValues = Collections.unmodifiableMap(byKeys);
   }
   
   private ControllingAttribute[] controllingAttributes =
   {
      new ControllingAttribute(Diagram_Messages.LB_Measure, "measure", //$NON-NLS-1$
              null, ""), //$NON-NLS-1$
      new ControllingAttribute(Diagram_Messages.LB_TargetMeasureQuantity, "targetMeasureQuantity", //$NON-NLS-1$
              "long", ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_Difficulty, "difficulty", //$NON-NLS-1$
              "int", ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetProcessingTime, "targetProcessingTime", //$NON-NLS-1$
              PERIOD, ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetExecutionTime, "targetExecutionTime", //$NON-NLS-1$
              PERIOD, ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetIdleTime, "targetIdleTime", //$NON-NLS-1$
              PERIOD, ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetWaitingTime, "targetWaitingTime", //$NON-NLS-1$
              PERIOD, ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetQueueDepth, "targetQueueDepth", //$NON-NLS-1$
              "int", ""), //$NON-NLS-1$ //$NON-NLS-2$
      new ControllingAttribute(Diagram_Messages.LB_TargetCostPerExecution, "targetCostPerExecution", //$NON-NLS-1$
              Type.Money.getId(), Diagram_Messages.MEASURE_UNIT_EUR),
      new ControllingAttribute(Diagram_Messages.LB_TargetCostPerSecond, "targetCostPerSecond", //$NON-NLS-1$
              Type.Money.getId(), Diagram_Messages.MEASURE_UNIT_EUR),
      new ControllingAttribute(Diagram_Messages.ActivityControllingPropertyPage_ResourcePerformanceCalculation_LB, "includeTime",  //$NON-NLS-1$
            Type.Boolean.getId(), "", rpCalculationByKeys, rpCalculationByValues ) //$NON-NLS-1$
   };

   public ControllingAttribute[] getControllingAttributes()
   {
      return controllingAttributes;
   }
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      String value = AttributeUtil.getAttributeValue((IExtensibleElement) element, PWH
            + "includeTime"); //$NON-NLS-1$
      if (value == null)
      {
         AttributeUtil.setBooleanAttribute((IExtensibleElement) element, PWH
               + "includeTime", ActivityUtil.isInteractive((ActivityType) element)); //$NON-NLS-1$
      }
      super.loadFieldsFromElement(symbol, element);
   }
}