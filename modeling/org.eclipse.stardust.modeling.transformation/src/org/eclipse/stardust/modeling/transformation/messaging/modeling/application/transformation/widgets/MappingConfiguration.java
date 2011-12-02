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


import java.util.Map;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;



public class MappingConfiguration {
	Map <String,String> indexMap;
	private boolean overwrite;
	private boolean append;
	private boolean deepCopy;
	private AccessPointType sourceType;
	private AccessPointType targetType;	

	public MappingConfiguration(MessageTransformationController controller,	AccessPointType st, AccessPointType tt) {
		this.sourceType = st;
		this.targetType = tt;
		if (controller.isList(st) && controller.isList(tt)) {
			deepCopy = true;
		}
		if (!controller.isList(st) && controller.isList(tt)) {
			append = true;
			overwrite = true;
		}
	}

	public void setIndexMap(Map<String, String> indexMap) {
		this.indexMap = indexMap;
	}

	public Map<String, String> getIndexMap() {
		return indexMap;
	}
	
	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public boolean isDeepCopy() {
		return deepCopy;
	}

	public void setDeepCopy(boolean deepCopy) {
		this.deepCopy = deepCopy;
	}	
	
	public AccessPointType getSourceType() {
		return sourceType;
	}

	public AccessPointType getTargetType() {
		return targetType;
	}
	
	
}
