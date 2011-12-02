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
package org.eclipse.stardust.modeling.integration.spring;

import ag.carnot.workflow.model.PredefinedConstants;

public interface SpringConstants
{
   final String SCOPE_SPRING_RT = PredefinedConstants.ENGINE_SCOPE + "spring::"; //$NON-NLS-1$

   final String ATTR_BEAN_ID = SCOPE_SPRING_RT + "beanId"; //$NON-NLS-1$

   final String PRF_APPLICATION_CONTEXT_PATH = "applicationContextPath"; //$NON-NLS-1$
}
