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
package org.eclipse.stardust.modeling.common.platform.validation;

import org.eclipse.stardust.modeling.common.platform.validation.impl.FixedQuickValidationStatus;

public interface IQuickValidationStatus
{
   final IQuickValidationStatus OK = new FixedQuickValidationStatus(false, false, false);

   final IQuickValidationStatus INFOS = new FixedQuickValidationStatus(true, false, false);

   final IQuickValidationStatus WARNINGS = new FixedQuickValidationStatus(false, true,
         false);

   final IQuickValidationStatus ERRORS = new FixedQuickValidationStatus(false, false,
         true);
   
   boolean hasIssues();

   boolean hasInfos();

   boolean hasWarnings();

   boolean hasErrors();
}
