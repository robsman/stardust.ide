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
package org.eclipse.stardust.modeling.debug;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.stardust.modeling.debug.model.CWMStackFrame;


public class CWMSourceLookupParticipant extends AbstractSourceLookupParticipant
{
   public String getSourceName(Object object) throws CoreException
   {
      if (object instanceof CWMStackFrame)
      {
         return ((CWMStackFrame)object).getSourceName();
      }
      
      return null;
   }
}
