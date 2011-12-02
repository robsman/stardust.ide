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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.timer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;

import ag.carnot.workflow.model.PredefinedConstants;

public class TimerAccessPointProvider implements IAccessPointProvider
{
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      DataTypeType serializable = ModelUtils.getDataType(element, CarnotConstants.SERIALIZABLE_DATA_ID);
      List accessPointList = new ArrayList();
      AccessPointType ap = AccessPointUtil.createAccessPoint(
            PredefinedConstants.TARGET_TIMESTAMP_ATT, Diagram_Messages.NAME_ACCESSPOINT_TimeStamp, DirectionType.OUT_LITERAL, 
            serializable);
      AttributeUtil.setAttribute(ap, PredefinedConstants.CLASS_NAME_ATT, Long.class
            .getName());
      AttributeUtil.setBooleanAttribute(ap, PredefinedConstants.EVENT_ACCESS_POINT,
            Boolean.TRUE.booleanValue());
      accessPointList.add(ap);
      return accessPointList;
   }
}
