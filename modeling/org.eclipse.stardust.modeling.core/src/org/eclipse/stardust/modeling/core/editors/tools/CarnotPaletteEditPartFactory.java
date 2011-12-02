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
package org.eclipse.stardust.modeling.core.editors.tools;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;

public class CarnotPaletteEditPartFactory extends PaletteEditPartFactory
{
   public EditPart createEditPart(EditPart parentEditPart, Object model)
   {
      if (model instanceof PaletteFlyout)
      {
         return new PaletteFlyoutEditPart((PaletteFlyout) model);
      }
      else if (model instanceof DynamicToolEntry)
      {
         return new DynamicToolEditPart((DynamicToolEntry)model);
      }
      else
      {
         return super.createEditPart(parentEditPart, model);
      }
   }

}
