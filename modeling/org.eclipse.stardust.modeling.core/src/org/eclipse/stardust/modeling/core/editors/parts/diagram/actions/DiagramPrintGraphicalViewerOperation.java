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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.print.PrintGraphicalViewerOperation;
import org.eclipse.swt.printing.Printer;

public class DiagramPrintGraphicalViewerOperation extends PrintGraphicalViewerOperation
{

   public DiagramPrintGraphicalViewerOperation(Printer p, GraphicalViewer g, Rectangle printingBounds)
   {
      super(p, g);
      LayerManager lm = (LayerManager) getViewer().getEditPartRegistry().get(
            LayerManager.ID);
      IFigure f = lm.getLayer(LayerConstants.PRINTABLE_LAYERS);
      f.setBounds(printingBounds);
      setPrintSource(f);
   }

}
