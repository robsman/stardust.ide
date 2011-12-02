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

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;


public abstract class AbstractModelElementAction extends AbstractEditorPageAction
{
   private final Class elementType;

   public AbstractModelElementAction(DiagramEditorPage editor, Class elementType)
   {
      super(editor);

      this.elementType = elementType;
   }

   public AbstractModelElementAction(DiagramEditorPage editor, int style,
         Class elementType)
   {
      super(editor, style);

      this.elementType = elementType;
   }

   protected boolean calculateEnabled()
   {
      return null != getSelectedElementPart();
   }

   protected GraphicalEditPart getSelectedElementPart()
   {
      GraphicalEditPart result = null;

      if (1 == getSelectedObjects().size())
      {
         Object selection = getSelectedObjects().get(0);
         if (selection instanceof GraphicalEditPart)
         {
            GraphicalEditPart part = (GraphicalEditPart) selection;
            if (elementType.isInstance(part.getModel()))
            {
               result = part;
            }
         }
      }
      return result;
   }

   protected Object getSelectedElement()
   {
      Object result = null;

      GraphicalEditPart selectedPart = getSelectedElementPart();
      if (null != selectedPart)
      {
         result = selectedPart.getModel();
      }
      return result;
   }
}
