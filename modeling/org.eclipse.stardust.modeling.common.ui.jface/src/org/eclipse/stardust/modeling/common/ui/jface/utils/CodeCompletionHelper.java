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
package org.eclipse.stardust.modeling.common.ui.jface.utils;

import java.util.HashMap;
import java.util.Map;

public class CodeCompletionHelper {

	private static CodeCompletionHelper instance = null;
	private Map<String,Object> typeMap = new HashMap<String,Object>();
	private Map<String,Object> externalTypeMap = new HashMap<String,Object>();
	
	public CodeCompletionHelper() {
		super();		
	}
	
    public static synchronized CodeCompletionHelper getInstance() {
        if (instance == null) {
            instance = new CodeCompletionHelper();
        }
        return instance;
    }
    
	public synchronized Map<String, Object> getTypeMap() {
		return typeMap;
	}
	
	public synchronized Map<String, Object> getExternalTypeMap() {
		return externalTypeMap;
	}
	
	public synchronized void clear() {
		typeMap.clear();
		externalTypeMap.clear();
	}

}
