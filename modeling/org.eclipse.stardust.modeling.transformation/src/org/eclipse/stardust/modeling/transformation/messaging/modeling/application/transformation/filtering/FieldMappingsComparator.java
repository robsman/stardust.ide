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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering;

import java.util.Comparator;

import org.eclipse.stardust.engine.extensions.transformation.model.mapping.FieldMapping;

public class FieldMappingsComparator implements Comparator<FieldMapping> {

	public int compare(FieldMapping fm1, FieldMapping fm2) {	
		String fp1 = fm1.getFieldPath();
		String fp2 = fm2.getFieldPath();
		String[] sg1 = fp1.split("/"); //$NON-NLS-1$
		String[] sg2 = fp2.split("/");		 //$NON-NLS-1$
		if (fp1.startsWith(fp2) && (sg1.length > sg2.length)) {
			return 1;
		}
		if (fp2.startsWith(fp1) && (sg2.length > sg1.length)) {
			return -1;
		}
		return 0;
	}

}
