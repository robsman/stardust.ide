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
package org.eclipse.stardust.model.xpdl.carnot.util;

public interface ElementIdRefs
{
   public static final String ANNOTATION_ID = "http://www.carnot.ag/workflow/model/ElementIdRef"; //$NON-NLS-1$

   public static final String ATTR_SCOPE = "scope"; //$NON-NLS-1$

   public static final String ATTR_REF_TYPE = "reftype"; //$NON-NLS-1$

   public static final String SCOPE_MODEL = "model"; //$NON-NLS-1$

   public static final String SCOPE_PROCESS = "process"; //$NON-NLS-1$

   public static final String SCOPE_POOL = "pool"; //$NON-NLS-1$

   public static final String REF_TYPE_ID = "id"; //$NON-NLS-1$

   public static final String REF_TYPE_OID = "oid"; //$NON-NLS-1$
}
