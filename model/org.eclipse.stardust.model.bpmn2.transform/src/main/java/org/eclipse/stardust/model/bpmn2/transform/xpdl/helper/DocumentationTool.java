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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.helper;

import java.util.List;

import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.Expression;

/**
 * @author Simon Nikles
 *
 */
public class DocumentationTool {

	public static String getDescriptionFromDocumentation(List<Documentation> documentation) {
		String description = "";
		for (Documentation doc : documentation) {
			description = description.concat(doc.getText());
		}
		return description;
	}

	public static String getInformalExpressionValue(Expression expression) {
		String description = "";
		if (expression != null) {
			List<Documentation> documentation = expression.getDocumentation();			
			if (documentation != null) {
				for (Documentation doc : documentation) {
					description = description.concat(doc.getText());
				}
			}
		}
		return description;
	}
}
