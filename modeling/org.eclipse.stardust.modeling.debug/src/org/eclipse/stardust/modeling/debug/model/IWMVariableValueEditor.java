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
package org.eclipse.stardust.modeling.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.actions.IVariableValueEditor;
import org.eclipse.jdt.debug.core.IJavaStackFrame;
import org.eclipse.swt.widgets.Shell;

public interface IWMVariableValueEditor extends IVariableValueEditor
{

   /* (non-Javadoc)
    * @see org.eclipse.debug.ui.actions.IVariableValueEditor#editVariable(org.eclipse.debug.core.model.IVariable, org.eclipse.swt.widgets.Shell)
    */
   public boolean editVariable(IVariable variable, Shell shell);

   /* (non-Javadoc)
    * @see org.eclipse.debug.ui.actions.IVariableValueEditor#saveVariable(org.eclipse.debug.core.model.IVariable, java.lang.String, org.eclipse.swt.widgets.Shell)
    */
   public boolean saveVariable(IVariable variable, String expression, Shell shell);

   public IValue evaluate2(IJavaStackFrame frame, String stringValue)
         throws DebugException;

}