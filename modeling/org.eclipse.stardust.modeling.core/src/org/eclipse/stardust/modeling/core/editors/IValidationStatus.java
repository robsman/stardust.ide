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
package org.eclipse.stardust.modeling.core.editors;

import java.util.List;
import java.util.Set;

import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;


public interface IValidationStatus extends IQuickValidationStatus
{
   final IValidationStatus OK = new ValidationStatusOk();

   List getInfos();

   List getWarnings();

   List getErrors();

   Set getChildrenWithInfos();

   Set getChildrenWithWarnings();

   Set getChildrenWithErrors();
}
