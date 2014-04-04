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
package org.eclipse.stardust.ui.web.modeler.bpmn2.utils;

import javax.xml.namespace.QName;

import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;

/**
 * @author Simon Nikles
 *
 */
public enum XSDType2StardustMapping {

	DATE("date", ModelerConstants.CALENDAR_PRIMITIVE_DATA_TYPE),
	DATE_TIME("dateTime", ModelerConstants.CALENDAR_PRIMITIVE_DATA_TYPE),
	TIME("time", ModelerConstants.CALENDAR_PRIMITIVE_DATA_TYPE),
	DECIMAL("decimal", ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE),
	DOUBLE("double", ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE),
	FLOAT("float", ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE),
	INTEGER("integer", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE),
	INT("int", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE),
	LONG("long", ModelerConstants.LONG_PRIMITIVE_DATA_TYPE),
	SHORT("short", ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE),
	STRING("string", ModelerConstants.STRING_PRIMITIVE_DATA_TYPE),
	BOOLEAN("boolean", ModelerConstants.BOOLEAN_PRIMITIVE_DATA_TYPE);

	private static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";

	private String xsdName;
	private String type;

	private XSDType2StardustMapping(String xmlName, String type) {
		this.xsdName = xmlName;
		this.type = type;
	}

	public static XSDType2StardustMapping byXsdName(String name) {
		for (XSDType2StardustMapping type : values()) {
			if (name.equals(type.xsdName)) return type;
		}
		return null;
	}

	public String getXsdName() {
		return xsdName;
	}

	public String getType() {
		return type;
	}

	public QName getXSDQname() {
		return new QName(XML_SCHEMA_URI, xsdName);
	}

}

