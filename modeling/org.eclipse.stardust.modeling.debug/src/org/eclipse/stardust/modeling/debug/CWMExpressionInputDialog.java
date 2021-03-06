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

import org.eclipse.jdt.debug.core.IJavaVariable;
import org.eclipse.jdt.internal.debug.ui.actions.ExpressionInputDialog;
import org.eclipse.swt.widgets.Shell;

public class CWMExpressionInputDialog extends ExpressionInputDialog
{
   public CWMExpressionInputDialog(Shell parentShell, IJavaVariable variable)
   {
      super(parentShell, variable);
   }
}
