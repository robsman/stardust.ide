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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.xsl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.engine.core.pojo.utils.JavaAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

/**
 * 
 * @author Marc Gille
 */
public class XSLMessageTransformationAccessPointProvider implements IAccessPointProvider
{
	public List createIntrinsicAccessPoint(IModelElement element)
	{
		ArrayList accessPoints = new ArrayList();
		
		DataTypeType dataTypeSerializable = ModelUtils.getDataType(
				(IModelElement) element, CarnotConstants.SERIALIZABLE_DATA_ID);
		DataTypeType dataTypePrimitive = ModelUtils.getDataType(
				(IModelElement) element, CarnotConstants.PRIMITIVE_DATA_ID);
		
		if (element != null && element instanceof IExtensibleElement)
		{
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
					"InputMessage", "InputMessage: java.util.Map", //$NON-NLS-1$ //$NON-NLS-2$
					"java.util.Map", //$NON-NLS-1$
					DirectionType.IN_LITERAL, false, new String[]
					{ JavaAccessPointType.PARAMETER.getId(),
								JavaAccessPointType.class.getName() },
					dataTypeSerializable));
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
					"OutputMessage", "OutputMessage: java.util.Map", //$NON-NLS-1$ //$NON-NLS-2$
					"java.util.Map", //$NON-NLS-1$
					DirectionType.OUT_LITERAL, true, new String[]
					{ JavaAccessPointType.RETURN_VALUE.getId(),
								JavaAccessPointType.class.getName() },
					dataTypeSerializable));
		}

		return accessPoints;
	}
}
