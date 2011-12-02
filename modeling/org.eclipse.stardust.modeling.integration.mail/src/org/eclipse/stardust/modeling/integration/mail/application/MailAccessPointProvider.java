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
package org.eclipse.stardust.modeling.integration.mail.application;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.integration.mail.MailConstants;

import ag.carnot.base.CollectionUtils;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.spi.utils.JavaAccessPointType;

/**
 * 
 * @author mgille
 */
public class MailAccessPointProvider implements IAccessPointProvider
{
	public List<AccessPointType> createIntrinsicAccessPoint(IModelElement element)
	{
		final List<AccessPointType> accessPoints = CollectionUtils.newList();

		if (element != null && element instanceof IExtensibleElement)
		{
			String plainTextTemplate;
			if  (AttributeUtil.getAttributeValue((IExtensibleElement) element, MailConstants.PLAIN_TEXT_TEMPLATE) != null) {
				plainTextTemplate = AttributeUtil.getAttributeValue(
						(IExtensibleElement) element,
							MailConstants.PLAIN_TEXT_TEMPLATE);
			} else {
				plainTextTemplate = ""; //$NON-NLS-1$
			}
			boolean mailResponse = AttributeUtil.getBooleanValue(
						(IExtensibleElement) element, MailConstants.MAIL_RESPONSE);			
			MessageFormat plainTextTemplateFormat = new MessageFormat(
							plainTextTemplate);			

			
			String htmlTextTemplate;
			if (AttributeUtil.getAttributeValue((IExtensibleElement) element, MailConstants.HTML_TEMPLATE) != null) {
				htmlTextTemplate = AttributeUtil.getAttributeValue(
						(IExtensibleElement) element,
							MailConstants.HTML_TEMPLATE);
			} else {
				htmlTextTemplate = ""; //$NON-NLS-1$
			}
			MessageFormat htmlTextTemplateFormat = new MessageFormat (
							htmlTextTemplate);
			
			
			// DataType type

			DataTypeType dataTypePrimitive = ModelUtils.getDataType(
						(IModelElement) element, CarnotConstants.PRIMITIVE_DATA_ID);
			DataTypeType dataTypeSerializable = ModelUtils
						.getDataType((IModelElement) element,
									CarnotConstants.SERIALIZABLE_DATA_ID);		
			
			// IN Data Mapping
			
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
						PredefinedConstants.MAIL_SERVER, PredefinedConstants.MAIL_SERVER + ": " + String.class.getName(), //$NON-NLS-1$
						String.class.getName(), DirectionType.IN_LITERAL, false,
						new String[]
						{ JavaAccessPointType.PARAMETER.getId(),
									JavaAccessPointType.class.getName() },
						dataTypePrimitive));
			
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                        PredefinedConstants.FROM_ADDRESS, PredefinedConstants.FROM_ADDRESS + ": " + String.class.getName(), //$NON-NLS-1$
						String.class.getName(), DirectionType.IN_LITERAL, false,
						new String[]
						{ JavaAccessPointType.PARAMETER.getId(),
									JavaAccessPointType.class.getName() },
						dataTypePrimitive));
			
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                        PredefinedConstants.TO_ADDRESS, PredefinedConstants.TO_ADDRESS + ": " + String.class.getName(), //$NON-NLS-1$
						String.class.getName(), DirectionType.IN_LITERAL, false,
						new String[]
						{ JavaAccessPointType.PARAMETER.getId(),
									JavaAccessPointType.class.getName() },
						dataTypePrimitive));
			
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                        PredefinedConstants.CC_ADDRESS, PredefinedConstants.CC_ADDRESS + ": " + String.class.getName(), //$NON-NLS-1$
						String.class.getName(), DirectionType.IN_LITERAL, false,
						new String[]
						{ JavaAccessPointType.PARAMETER.getId(),
									JavaAccessPointType.class.getName() },
						dataTypePrimitive));
			
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                        PredefinedConstants.BCC_ADDRESS, PredefinedConstants.BCC_ADDRESS + ": " + String.class.getName(), //$NON-NLS-1$
						String.class.getName(), DirectionType.IN_LITERAL, false,
						new String[]
						{ JavaAccessPointType.PARAMETER.getId(),
									JavaAccessPointType.class.getName() },
						dataTypePrimitive));
			
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                        PredefinedConstants.MAIL_PRIORITY, PredefinedConstants.MAIL_PRIORITY + ": " + String.class.getName(), //$NON-NLS-1$
						String.class.getName(), DirectionType.IN_LITERAL, false,
						new String[]
						{ JavaAccessPointType.PARAMETER.getId(),
									JavaAccessPointType.class.getName() },
						dataTypePrimitive));			
									
			accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(PredefinedConstants.SUBJECT,
                        PredefinedConstants.SUBJECT + ": " + String.class.getName(), String.class //$NON-NLS-1$
									.getName(), DirectionType.IN_LITERAL, false,
						new String[]
						{ JavaAccessPointType.PARAMETER.getId(),
									JavaAccessPointType.class.getName() },
						dataTypePrimitive));
			
         accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
               PredefinedConstants.ATTACHMENTS, PredefinedConstants.ATTACHMENTS + ": " //$NON-NLS-1$
                     + java.util.List.class.getName(), java.util.List.class.getName(),
               DirectionType.IN_LITERAL, false, new String[] {
                     JavaAccessPointType.PARAMETER.getId(),
                     JavaAccessPointType.class.getName() }, dataTypePrimitive));
			
			int plaincount = plainTextTemplateFormat.getFormatsByArgumentIndex().length;			
			int htmlcount = htmlTextTemplateFormat.getFormatsByArgumentIndex().length;
			
			int count = 0;
			
			
			if (plaincount > htmlcount) {
				count = plaincount;
			} else {
				count = htmlcount;
			}
			
			for (int i = 0; i < count; i++)
			{
				accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                            PredefinedConstants.TEMPLATE_VARIABLE + i, PredefinedConstants.TEMPLATE_VARIABLE + i
										+ ": " + java.lang.String.class.getName(), //$NON-NLS-1$
							java.lang.String.class.getName(),
							DirectionType.IN_LITERAL, false, new String[]
							{ JavaAccessPointType.PARAMETER.getId(),
										JavaAccessPointType.class.getName() },
							dataTypePrimitive));
			}
			
			if (mailResponse)
			{
				accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                            PredefinedConstants.RESPONSE_MAIL, PredefinedConstants.RESPONSE_MAIL + ": ag.carnot.mail.Mail", //$NON-NLS-1$
							"ag.carnot.mail.Mail", DirectionType.OUT_LITERAL, true, //$NON-NLS-1$
							new String[]
							{ JavaAccessPointType.RETURN_VALUE.getId(),
										JavaAccessPointType.class.getName() },
							dataTypeSerializable));
			}
			else
			{
				accessPoints.add(AccessPointUtil.createIntrinsicAccessPoint(
                            PredefinedConstants.RETURN_VALUE,
                            PredefinedConstants.RETURN_VALUE + ": " + String.class.getName(), String.class //$NON-NLS-1$
										.getName(), DirectionType.OUT_LITERAL, true,
							new String[]
							{ JavaAccessPointType.RETURN_VALUE.getId(),
										JavaAccessPointType.class.getName() },
							dataTypePrimitive));
			}			

		}

		return accessPoints;
	}
}