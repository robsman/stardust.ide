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
package org.eclipse.stardust.model.bpmn2.transform.xpdl;

import org.eclipse.stardust.model.bpmn2.transform.Dialect;
import org.eclipse.stardust.model.bpmn2.transform.Transformator;

/**
 * @author Simon Nikles
 *
 */
public class DialectStardustXPDL implements Dialect {

    public static final String DIALECT_STARDUSTXPDL = "stardust_xpdl";

    public Transformator getTransformator() {
        return new Bpmn2StardustXPDL();
    }

    public String getDialectName() {
        return DIALECT_STARDUSTXPDL;
    };

}
