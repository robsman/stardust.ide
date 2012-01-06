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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationModelingPlugin;
import org.eclipse.swt.graphics.Image;

public class ExternalClassesLabelProvider extends LabelProvider implements ITableLabelProvider {

	private static final Image errorImage = MessageTransformationModelingPlugin.getDefault()
    .getImageDescriptor("icons/error_tsk.gif").createImage(); //$NON-NLS-1$
	
	public Image getColumnImage(Object element, int columnIndex) {
		if (element instanceof AccessPointType) {
			AccessPointType externalClassAP = (AccessPointType)element;
			if (columnIndex == 0 && externalClassAP.getElementOid() == -99) {
				return errorImage;
			}
		}
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {		
		if (element instanceof AccessPointType) {
			AccessPointType externalClassAP = (AccessPointType)element;
			if (columnIndex == 0) {				
				return externalClassAP.getName();
			}
			if (columnIndex == 1) {
				return AttributeUtil.getAttributeValue(externalClassAP, PredefinedConstants.CLASS_NAME_ATT);
			}
		}
		return ""; //$NON-NLS-1$
	}
	
    public String getText(Object element)
    {
       return getColumnText(element, 0);
    }

    public Image getImage(Object element)
    {
       return getColumnImage(element, 0);
    }

}
