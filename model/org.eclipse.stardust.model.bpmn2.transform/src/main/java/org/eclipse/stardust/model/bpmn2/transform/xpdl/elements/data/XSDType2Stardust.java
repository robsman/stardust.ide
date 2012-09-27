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
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;

/**
 * @author Simon Nikles
 *
 */
public enum XSDType2Stardust {

	BYTE("byte", Type.Byte, null),
	DATE("date", Type.Calendar, ModelerConstants.DATE_PRIMITIVE_DATA_TYPE),
	DATE_TIME("dateTime", Type.Calendar, ModelerConstants.DATE_PRIMITIVE_DATA_TYPE),
	TIME("time", Type.Timestamp, null),
	DECIMAL("decimal", Type.Double, ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE),
	DOUBLE("double", Type.Double, ModelerConstants.DOUBLE_PRIMITIVE_DATA_TYPE),
	FLOAT("float", Type.Float, null),
	INTEGER("integer", Type.Integer, ModelerConstants.INTEGER_PRIMITIVE_DATA_TYPE),
	INT("int", Type.Integer, ModelerConstants.DECIMAL_PRIMITIVE_DATA_TYPE),
	LONG("long", Type.Long, null),
	SHORT("short", Type.Short, null),
	STRING("string", Type.String, ModelerConstants.STRING_PRIMITIVE_DATA_TYPE),
	ANY_URI("anyURI", null, null),
	BASE_64("base64Binary", null, null),
	BOOLEAN("boolean", null, null),
	DURATION("duration", null, null);

	private static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";

	private String name;
	private Type type;
	private String primitive;

	private XSDType2Stardust(String name, Type type, String primitive) {
		this.name = name;
		this.type = type;
		this.primitive = primitive;
	}

	public static XSDType2Stardust byXsdName(String name) {
		for (XSDType2Stardust type : values()) {
			if (name.equals(type.name)) return type;
		}
		return null;
	}

	public static XSDType2Stardust byType(Type sdType) {
		for (XSDType2Stardust xst : values()) {
			if (sdType.equals(xst)) return xst;
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public String getPrimitive() {
		return primitive;
	}

	public QName getXSDQname() {
		return new QName(XML_SCHEMA_URI, name);
	}

}

