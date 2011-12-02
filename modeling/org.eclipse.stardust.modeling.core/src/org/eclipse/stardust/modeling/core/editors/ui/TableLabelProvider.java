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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.swt.graphics.Image;

/**
 * @author fherinean
 * @version $Revision$
 */
public interface TableLabelProvider
{
   String getText(String name, Object element);

   String getText(int index, Object element);

   boolean isNamed();

   boolean accept(Object element);

   Image getImage(Object element);
}
