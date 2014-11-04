/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data;

import javax.xml.namespace.QName;

import org.eclipse.stardust.engine.core.pojo.data.Type;

/**
 * @author Simon Nikles
 *
 */
public enum XSDType2Stardust {

	BYTE("byte", Type.Byte, Type.Byte.getId()),
	DATE("date", Type.Calendar, Type.Calendar.getId()),
	DATE_TIME("dateTime", Type.Timestamp, Type.Timestamp.getId()),
	DOUBLE("double", Type.Double, Type.Double.getId()),
	FLOAT("float", Type.Float, Type.Float.getId()),
	INTEGER("integer", Type.Integer, Type.Integer.getId()),
	INT("int", Type.Integer, Type.Integer.getId()),
	LONG("long", Type.Long, Type.Long.getId()),
	SHORT("short", Type.Short, Type.Short.getId()),
	STRING("string", Type.String, Type.String.getId()),
	BOOLEAN("boolean", Type.Boolean, Type.Boolean.getId());
	
	public static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";

	private String xsdName;
	private Type type;
	private String primitive;

	private XSDType2Stardust(String name, Type type, String primitive) {
		this.xsdName = name;
		this.type = type;
		this.primitive = primitive;
	}

	public static XSDType2Stardust byXsdName(String name) {
		for (XSDType2Stardust type : values()) {
			if (name.equals(type.xsdName)) return type;
		}
		return null;
	}

	public static XSDType2Stardust byType(Type sdType) {
		for (XSDType2Stardust xst : values()) {
			if (sdType.equals(xst)) return xst;
		}
		return null;
	}

	public static XSDType2Stardust byTypeName(String sdType) {
		for (XSDType2Stardust xst : values()) {
			if (sdType.equals(xst.primitive)) return xst;
		}
		return null;
	}

	public String getName() {
		return xsdName;
	}

	public Type getType() {
		return type;
	}

	public String getPrimitive() {
		return primitive;
	}

	public QName getXSDQname() {
		return new QName(XML_SCHEMA_URI, xsdName);
	}

//	public String convertXsdValueToStardust(String xsdValue) {
//		String stardustValue = null;
//		switch(this) {
//		case DATE:
//			try {
//				GregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(xsdValue).toGregorianCalendar();
//				return Reflect.convertObjectToString(calendar);
//			} catch (DatatypeConfigurationException e) {}
//			break;
//		case DATE_TIME:
//			try {
//				GregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(xsdValue).toGregorianCalendar();
//				Date dateTime = calendar.getTime();
//				return Reflect.convertObjectToString(dateTime);
//			} catch (DatatypeConfigurationException e) {}
//			break;
//		default:
//			stardustValue = xsdValue;
//		}
//		return stardustValue;
//	}


}

