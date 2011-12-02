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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.onAssignment;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;


public class OnAssignmentAccessPointProvider implements IAccessPointProvider
{
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      List accessPointList = new ArrayList();

/*      DataTypeType serializable = ModelUtils.getDataType(element, CarnotConstants.SERIALIZABLE_DATA_ID);
      AccessPointType previousUserAp = AccessPointUtil.createAccessPoint(
            PredefinedConstants.SOURCE_USER_ATT, Spi_Messages.NAME_ACCESSPOINT_PreviousUser, DirectionType.OUT_LITERAL, 
            serializable);
      AttributeUtil.setAttribute(previousUserAp, PredefinedConstants.CLASS_NAME_ATT,
            Long.class.getName());
      AttributeUtil.setBooleanAttribute(previousUserAp,
            PredefinedConstants.EVENT_ACCESS_POINT, Boolean.TRUE.booleanValue());
      accessPointList.add(previousUserAp);

      AccessPointType newUserAp = AccessPointUtil.createAccessPoint(
            PredefinedConstants.TARGET_USER_ATT, Spi_Messages.NAME_ACCESSPOINT_NewUser, DirectionType.OUT_LITERAL, 
            serializable);
      AttributeUtil.setAttribute(newUserAp, PredefinedConstants.CLASS_NAME_ATT, Long.class
            .getName());
      AttributeUtil.setBooleanAttribute(newUserAp, PredefinedConstants.EVENT_ACCESS_POINT,
            Boolean.TRUE.booleanValue());
      accessPointList.add(newUserAp);*/

      return accessPointList;
   }

}
