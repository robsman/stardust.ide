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
package org.eclipse.stardust.modeling.validation;

public interface ValidationConstants
{
   String ELEMENT_VALIDATOR_EXTENSION_POINT = "org.eclipse.stardust.modeling.validation.modelElementValidator"; //$NON-NLS-1$

   String MODEL_VALIDATOR_EXTENSION_POINT = "org.eclipse.stardust.modeling.validation.modelValidator"; //$NON-NLS-1$   
   
   String BRIDGE_PROVIDER_EXTENSION_POINT = "org.eclipse.stardust.modeling.validation.bridgeObjectProvider"; //$NON-NLS-1$

   String EP_ATTR_ID = "id"; //$NON-NLS-1$

   String EP_ATTR_CLASS = "class"; //$NON-NLS-1$

   String EP_ATTR_TARGET_TYPE = "targetType"; //$NON-NLS-1$

   String EP_ATTR_META_TYPE_ID = "metaTypeId"; //$NON-NLS-1$

   String EP_ATTR_DATA_TYPE_ID = "dataTypeId"; //$NON-NLS-1$
}
