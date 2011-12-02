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

import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.ButtonBorder;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Toggle;
import org.eclipse.gef.internal.ui.palette.editparts.GroupEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.SliderPaletteEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class DynamicToolEditPart extends ToolEntryEditPart
{
	static final Border TOOLBAR_ITEM_BORDER = new ButtonBorder(
		    ButtonBorder.SCHEMES.TOOLBAR);
	
   public DynamicToolEditPart(PaletteEntry paletteEntry)
   {
      super(paletteEntry);
   }

   public IFigure createFigure()
   {
      IFigure figure = super.createFigure();
      if (figure instanceof Toggle)
      {
         Toggle button = (Toggle) figure;
         // Workaround: override default action listener
         try
         {
            Method mthGetListeners = Figure.class.getDeclaredMethod("getListeners", //$NON-NLS-1$
                  new Class[] {Class.class});
            mthGetListeners.setAccessible(true);
            Iterator listeners = (Iterator) mthGetListeners.invoke(button,
                  new Object[] {ActionListener.class});
            if (null != listeners)
            {
               for (Iterator i = listeners; i.hasNext();)
               {
                  button.removeActionListener((ActionListener) i.next());
               }
            }
         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

         button.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               if (getParent() instanceof PaletteFlyoutEditPart)
               {
                  ((PaletteFlyoutEditPart) getParent()).openMenu();
                  getViewer().getEditDomain().loadDefaultTool();
               }
               else
               {
                  ((PaletteViewer) getViewer()).setActiveTool(getToolEntry());
               }
            }
         });
      }

      return figure;
   }

   private ToolEntry getToolEntry()
   {
      return (ToolEntry) getPaletteEntry();
   }
}
