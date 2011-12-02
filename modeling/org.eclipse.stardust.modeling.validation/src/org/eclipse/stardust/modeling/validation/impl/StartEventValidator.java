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
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class StartEventValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      
      StartEventSymbol startEvent = (StartEventSymbol) element;

      ProcessDefinitionType process = ModelUtils.findContainingProcess(element);
      ActivityType startActivity = null;
      List activitySymbols = null;
      boolean otherStartActivities = false;

      // search start activity
      for (Iterator iter = process.getActivity().iterator(); iter.hasNext();)
      {
         ActivityType activity = (ActivityType) iter.next();
         if (!(!activity.getInTransitions().isEmpty()))
         {
            if (startActivity == null)
            {
               startActivity = activity;
            }
            else if (!otherStartActivities)
            {
               otherStartActivities = true;
            }
         }
      }

      // only one start activity
      if (startActivity != null && !otherStartActivities)
      {
         activitySymbols = startActivity.getActivitySymbols();
      }

      if(activitySymbols != null
            && !activitySymbols.isEmpty())
      {
         EList outTransitions = startEvent.getOutTransitions();
         if(!outTransitions.isEmpty())
         {
            TransitionConnectionType transition = (TransitionConnectionType) outTransitions.get(0);
            IFlowObjectSymbol symbol = transition.getTargetActivitySymbol();
            if(symbol instanceof ActivitySymbolType)
            {
               ActivityType activity = ((ActivitySymbolType) symbol).getActivity();
               // check if start activity
               if(!activitySymbols.contains(symbol))
               {
                  result.add(Issue.warning(startEvent, MessageFormat.format(
                        Validation_Messages.MSG_StartEventSymbolWrongConnected, 
                        new Object[] {activity.getId(), startActivity.getId()})));                  
               }
            }            
         }
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}