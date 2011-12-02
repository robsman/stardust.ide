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

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.ui.actions.PrintAction;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.IWorkbenchPart;


public class DiagramPrintAction extends PrintAction
{

   public DiagramPrintAction(IWorkbenchPart part)
   {
      super(part);
      // TODO Auto-generated constructor stub
   }

   public void run()
   {
      GraphicalViewer viewer;
      viewer = (GraphicalViewer) getWorkbenchPart().getAdapter(GraphicalViewer.class);

      LayerManager lm = (LayerManager) viewer.getEditPartRegistry().get(LayerManager.ID);
      IFigure f = lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
      Rectangle oldBounds = f.getBounds();

      Rectangle printingBounds = getPrintingBounds(((WorkflowModelEditor) getWorkbenchPart())
            .getActiveDiagram());

      PrintDialog dialog = new PrintDialog(viewer.getControl().getShell(), SWT.NULL);
      PrinterData data = dialog.open();

      if (data != null)
      {
         DiagramPrintGraphicalViewerOperation op = new DiagramPrintGraphicalViewerOperation(
               new Printer(data), viewer, printingBounds);
         op.run(getWorkbenchPart().getTitle());
      }

      f.setBounds(oldBounds);
   }

   private Rectangle getPrintingBounds(DiagramType activeDiagram)
   {
      long x = -1;
      long y = -1;
      long width = -1;
      long height = -1;
      for (Iterator iter = activeDiagram.getNodes().valueListIterator(); iter.hasNext();)
      {
         INodeSymbol symbol = (INodeSymbol) iter.next();
         if (x == -1 || symbol.getXPos() < x)
         {
            x = symbol.getXPos();
         }
         if (y == -1 || symbol.getYPos() < y)
         {
            y = symbol.getYPos();
         }
         if (width == -1 || symbol.getWidth() + symbol.getXPos() > width)
         {
            width = symbol.getWidth() + symbol.getXPos();
         }
         if (height == -1 || symbol.getHeight() + symbol.getYPos() > height)
         {
            height = symbol.getHeight() + symbol.getYPos();
         }
      }

      x = x - 20;
      y = y - 20;
      width = width + 20;
      height = height + 20;

      return new Rectangle(new Point(x, y), new Dimension(new Long(width).intValue(),
            new Long(height).intValue()));
   }

}
