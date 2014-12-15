/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ITpearls AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.eventaction.AbstractEventActionBuilder;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class BpmSetDataEventActionBuilder
      extends AbstractEventActionBuilder<EventActionType, BpmSetDataEventActionBuilder>
{
   public BpmSetDataEventActionBuilder(EventHandlerType handler)
   {
      super(handler, F_CWM.createEventActionType());
   }

   @Override
   protected EventActionType finalizeElement()
   {
	  forActionType(PredefinedConstants.SET_DATA_ACTION);
      return super.finalizeElement();
   }

   public static BpmSetDataEventActionBuilder newSetDataAction(EventHandlerType handler)
   {
      return new BpmSetDataEventActionBuilder(handler);
   }

   public BpmSetDataEventActionBuilder settingData(DataType data) {
	   AttributeUtil.setAttribute(element, PredefinedConstants.SET_DATA_ACTION_DATA_ID_ATT, data.getId());
	   return this;
   }

   public BpmSetDataEventActionBuilder settingDataPath(String path) {
	   AttributeUtil.setAttribute(element, PredefinedConstants.SET_DATA_ACTION_DATA_PATH_ATT, path);
	   return this;
   }

   public BpmSetDataEventActionBuilder fromAccessPoint(String accessPoint) {
	   AttributeUtil.setAttribute(element, PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_NAME_ATT, accessPoint);
	   return this;
   }

   public BpmSetDataEventActionBuilder fromAccessPath(String path) {
	   AttributeUtil.setAttribute(element, PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_PATH_ATT, path);
	   return this;
   }

}
