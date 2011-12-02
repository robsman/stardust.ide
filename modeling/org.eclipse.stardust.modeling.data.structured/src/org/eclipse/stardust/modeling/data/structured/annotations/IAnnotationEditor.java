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
package org.eclipse.stardust.modeling.data.structured.annotations;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

/**
 * Must be implemented by custom annotation editors.
 * 
 * @author herinean
 * @version $Revision$
 */
public interface IAnnotationEditor
{
   /**
    * Invoked to create the actual editing control.
    * 
    * @param cellEditor the AnnotationCellEditor that controls the editing.
    * @param annotation the IAnnotation that it is being edited.
    * @param itemBounds the bounds of the item being edited in the tree. 
    * @return the control used for editing.
    */
   Control createControl(AnnotationCellEditor cellEditor, IAnnotation annotation, Rectangle itemBounds);
}
