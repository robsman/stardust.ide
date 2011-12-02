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

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;

final class ValidationStatusOk implements IValidationStatus
{
   public boolean hasIssues()
   {
      return false;
   }

   public boolean hasInfos()
   {
      return false;
   }

   public List getInfos()
   {
      return Collections.EMPTY_LIST;
   }

   public boolean hasWarnings()
   {
      return false;
   }

   public List getWarnings()
   {
      return Collections.EMPTY_LIST;
   }

   public boolean hasErrors()
   {
      return false;
   }

   public boolean hasErrors(EStructuralFeature eFtr)
   {
      return false;
   }

   public List getErrors()
   {
      return Collections.EMPTY_LIST;
   }

   public List getErrors(EStructuralFeature eFtr)
   {
      return Collections.EMPTY_LIST;
   }

   public Set getChildrenWithInfos()
   {
      return Collections.EMPTY_SET;
   }

   public Set getChildrenWithWarnings()
   {
      return Collections.EMPTY_SET;
   }

   public Set getChildrenWithErrors()
   {
      return Collections.EMPTY_SET;
   }
}