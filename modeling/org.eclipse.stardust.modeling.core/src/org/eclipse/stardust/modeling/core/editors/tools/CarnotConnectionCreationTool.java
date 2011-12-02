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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;


public class CarnotConnectionCreationTool extends ConnectionCreationTool
{
   public CarnotConnectionCreationTool()
   {
      super();

      // by default only apply once per cycle
      setUnloadWhenFinished(true);
   }

   protected void executeCurrentCommand()
   {
      try
      {
         super.executeCurrentCommand();
      }
      catch (CommandCanceledException cce)
      {
         setCurrentCommand(null);
      }
      // TODO: (fh) for any other exception display an error dialog
   }

   public CarnotConnectionCreationTool(CreationFactory factory)
   {
      super(factory);

      // by default only apply once per cycle
      setUnloadWhenFinished(true);
   }

   protected boolean handleMove()
   {

      if (super.handleMove() && isInState(STATE_CONNECTION_STARTED))
      {
         scrollDiagramPage();
      }

      return true;
   }

   protected boolean handleHover()
   {

      if (super.handleHover() && isInState(STATE_CONNECTION_STARTED))
      {
         scrollDiagramPage();
      }

      return true;
   }

   private void scrollDiagramPage()
   {
      if (getCurrentViewer() instanceof ScrollingGraphicalViewer)
      {
         ViewportScrollHelper scrollHelper = new ViewportScrollHelper(
               (GraphicalEditPart) getCurrentViewer().getRootEditPart(),
               (ScrollingGraphicalViewer) getCurrentViewer());
         scrollHelper.updateViewport(getLocation());

      }
   }

   protected void handleFinished()
   {
      if (unloadWhenFinished() && !getCurrentInput().isControlKeyDown())
      {
         getDomain().loadDefaultTool();
      }
      else
      {
         reactivate();
      }
   }

   public void startConnection(AbstractNodeSymbolEditPart part)
   {
      setViewer(part.getViewer());
      // compute the center of the selected figure in the graphical viewer top level figure coordinates
      IFigure figure = part.getFigure();
      Point start = figure.getBounds().getCenter();
      // the location obtained are already in parent coordinates
      figure = figure.getParent();
      while (figure.getParent() != null)
      {
         figure.translateToParent(start);
         figure = figure.getParent();
      }

      // simulate a click in the middle of the selected figure
      getCurrentInput().getMouseLocation().setLocation(start);
      setStartLocation(start);
      handleButtonDown(1);
   }
}
