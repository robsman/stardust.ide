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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.renderer;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets.MappingConfiguration;


public interface IMappingRenderer {
	public AccessPointType getType();
	public String renderGetterCode(boolean ignoreArrays, boolean variablesAsIndices, MappingConfiguration config);
	public String renderSetterCode(String getterCode, boolean ignoreArrays, boolean variablesAsIndices, MappingConfiguration config);
	public String renderListMappingCode(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, String inset, int depth, MappingConfiguration config);
	public String renderAssignmentCode(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, String inset, int depth, String result, MappingConfiguration config);
	public String renderAdditionCode(IMappingRenderer sourceMapper, IMappingRenderer targetMapper, MappingConfiguration config);
	public String getTypeString();
}
