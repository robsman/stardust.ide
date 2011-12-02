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

import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class LaneValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      LaneSymbol lane = (LaneSymbol) element;

      if (findDuplicateId(lane))
      {
         result.add(Issue.error(lane, MessageFormat.format(
               Validation_Messages.MSG_DuplicateIdUsed, new Object[] {lane.getId()}),
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      if (null != lane.getParticipant())
      {
         for (Iterator i = lane.getActivitySymbol().iterator(); i.hasNext();)
         {
            ActivitySymbolType activitySymbol = (ActivitySymbolType) i.next();
            ActivityType activity = activitySymbol.getActivity();
            if ((null != activity) && ActivityUtil.isInteractive(activity)
                  && (null != activity.getPerformer())
                  && (lane.getParticipant() != activity.getPerformer()))
            {
               result.add(Issue.warning(activity,
                     MessageFormat.format(
                           Validation_Messages.MSG_OverriddenLaneParticipant,
                           new Object[] {
                                 lane.getParticipant().getId(),
                                 activity.getPerformer().getId()}),
                     ValidationService.PKG_CWM.getActivityType_Performer()));
            }
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean findDuplicateId(LaneSymbol lane)
   {
      PoolSymbol pool = lane.getParentPool();

      for (Iterator i = pool.getLanes().iterator(); i.hasNext();)
      {
         LaneSymbol otherLane = (LaneSymbol) i.next();
         if ( !lane.equals(otherLane) && (null != otherLane.getId())
               && (otherLane.getId().equals(lane.getId())))
         {
            return true;
         }
      }
      return false;
   }
}
