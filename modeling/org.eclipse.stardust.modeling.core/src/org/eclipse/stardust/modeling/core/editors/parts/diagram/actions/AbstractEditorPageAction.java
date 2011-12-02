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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;


public abstract class AbstractEditorPageAction extends SelectionAction
{
   public AbstractEditorPageAction(DiagramEditorPage editor)
   {
      super(editor);
   }

   public AbstractEditorPageAction(DiagramEditorPage editor, int style)
   {
      super(editor, style);
   }

   protected DiagramEditorPage getEditor()
   {
      return (DiagramEditorPage) getWorkbenchPart();
   }
}
