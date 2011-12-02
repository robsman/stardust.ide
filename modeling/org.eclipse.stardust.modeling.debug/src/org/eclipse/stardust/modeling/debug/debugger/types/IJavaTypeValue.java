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
package org.eclipse.stardust.modeling.debug.debugger.types;

import org.eclipse.debug.core.model.IVariable;

/**
 * @author sborn
 * @version $Revision$
 */
public interface IJavaTypeValue
{
   String getVariableName();
   String getRefTypeName();
   void setWritebackVariable(IVariable variable);
   IVariable getWritebackVariable();
}
