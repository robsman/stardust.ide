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
package org.eclipse.stardust.modeling.common.platform.validation.impl;

import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;

public class FixedQuickValidationStatus implements IQuickValidationStatus
{
   private final boolean hasInfos;

   private final boolean hasWarnings;

   private final boolean hasErrors;

   public FixedQuickValidationStatus(boolean hasInfos, boolean hasWarnings,
         boolean hasErrors)
   {
      this.hasInfos = hasInfos;
      this.hasWarnings = hasWarnings;
      this.hasErrors = hasErrors;
   }

   public boolean hasIssues()
   {
      return hasInfos() || hasWarnings() || hasErrors();
   }

   public boolean hasInfos()
   {
      return hasInfos;
   }

   public boolean hasWarnings()
   {
      return hasWarnings;
   }

   public boolean hasErrors()
   {
      return hasErrors;
   }
}
