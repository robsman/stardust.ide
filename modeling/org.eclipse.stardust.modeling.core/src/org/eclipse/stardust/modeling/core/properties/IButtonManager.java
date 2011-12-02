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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author fherinean
 * @version $Revision$
 */
public interface IButtonManager
{
   static final int ADD_BUTTON = 0;
   static final int DELETE_BUTTON = ADD_BUTTON + 1;
   static final int UP_BUTTON = DELETE_BUTTON + 1;
   static final int DOWN_BUTTON = UP_BUTTON + 1;

   static final int BUTTON_COUNT = DOWN_BUTTON + 1;

   void updateButtons(Object selection, Button[] buttons);

   Button[] createButtons(Composite parent);

   Object getSelection();
}
