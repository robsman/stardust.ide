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
package org.eclipse.stardust.model.bpmn2.transform.carnot;

import org.eclipse.stardust.model.bpmn2.transform.Dialect;
import org.eclipse.stardust.model.bpmn2.transform.Transformator;

/**
 * @author Simon Nikles
 *
 */
public class DialectCarnotXPDL implements Dialect {

	public static final String DIALECT_CARNOT = "carnot_xpdl";
		
	public Transformator getTransformator() {
		return new Bpmn2CarnotXPDL();
	}

	public String getDialectName() {
		return DIALECT_CARNOT;
	};

}
