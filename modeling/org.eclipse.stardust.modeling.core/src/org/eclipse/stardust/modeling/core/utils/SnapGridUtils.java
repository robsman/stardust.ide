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
package org.eclipse.stardust.modeling.core.utils;

import java.util.ArrayList;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


public class SnapGridUtils
{
   // get SnapToHelper
   public static SnapToHelper getSnapToHelper(AbstractGraphicalEditPart part)
   {
      EditPart diagramEP = part;
      if(diagramEP instanceof DiagramEditPart)
      {
         return (SnapToHelper) diagramEP.getAdapter(SnapToHelper.class);
      }      
      while(!(diagramEP instanceof DiagramEditPart))
      {         
         diagramEP = diagramEP.getParent();
         if(diagramEP == null)
         {
            // check if editor is WorkflowModelEditor (otherwise class cast exception may occur)
            IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if(editor instanceof WorkflowModelEditor)
            {
               DiagramEditorPage diagramEditorPage = (DiagramEditorPage) ((WorkflowModelEditor) editor).getCurrentPage();      
               diagramEP = diagramEditorPage.findEditPart(diagramEditorPage.getDiagram());                        
            }
            else
            {
               return null;
            }
         }
      }  
      // diagramEP must be asked - container for all other symbols that may snap2grid
      SnapToHelper helper = (SnapToHelper) diagramEP.getAdapter(SnapToHelper.class);
      // if helper is only SnapToGeometry then Snap2Grid is disabled
      if(helper == null || helper instanceof SnapToGeometry)
      {
         return null;
      }
      return helper;
   }
   
   public static int getRoundSnapValue(int oldValue, AbstractGraphicalEditPart host)
   {      
      SnapToHelper snapToHelper = getSnapToHelper(host);
      if (snapToHelper == null)
      {
         return oldValue;
      }
      int newValue;
      
      newValue = (int) Math.round(
            new Double((double) oldValue/(SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE)).doubleValue());
      newValue *= SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
      
      return newValue;
   }
   
   // new Dimension, to have the next size 2N that all edges match to grid points 
   // needed on symbol creation
   public static Dimension getSnapDimension(Dimension oldDimension, AbstractGraphicalEditPart host, int n, boolean useRound)
   {
      SnapToHelper snapToHelper = getSnapToHelper(host);
      if (snapToHelper == null)
      {
         return oldDimension;
      }
      int width;
      int height;      
      if(!useRound)
      {
         width = (int) Math.ceil(
               new Double((double) oldDimension.width/(SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n)).doubleValue());
         width *= SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n;
         height = (int) Math.ceil(
               new Double((double) oldDimension.height/(SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n)).doubleValue());
         height *= SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n;                  
      }
      else
      {
         width = (int) Math.round(
               new Double((double) oldDimension.width/(SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n)).doubleValue());
         width *= SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n;
         height = (int) Math.round(
               new Double((double) oldDimension.height/(SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n)).doubleValue());
         height *= SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE * n;         
      }      
      return new Dimension(width, height);
   }
   
   // get SnapLocation 
   public static Point getSnapLocation(AbstractGraphicalEditPart host, AbstractNodeSymbolEditPart part, 
         PrecisionRectangle rect, Dimension size, Point location)
   {
      if(rect == null)
      {
         rect = new PrecisionRectangle(GenericUtils.getSymbolRectangle(part));                        
      }     
      if(size == null)
      {
         size = rect.getSize();                        
      }     
      if(location == null)
      {
         location = rect.getLocation().getCopy();
      }
      
      // get current rectangle
      SnapToHelper snapToHelper = getSnapToHelper(host);
      if (snapToHelper == null)
      {
         return location;
      }      
      Rectangle newBounds = new Rectangle(location, size);
      IFigure figure = part.getFigure();
      // should have the same values as the symbol (when creating a symbol)      
      Point oldLocation = newBounds.getLocation().getCopy();
      // must be set to actual coordinates
      rect = new PrecisionRectangle(newBounds);      
      // if symbol lies in container
      figure.translateToAbsolute(rect);
      PrecisionRectangle baseRect = rect.getPreciseCopy();
      PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);
      ChangeBoundsRequest fakeRequest = new ChangeBoundsRequest();
      fakeRequest.setLocation(oldLocation);
      Point moveDelta = new Point(0, 0);
      fakeRequest.setMoveDelta(moveDelta);
      fakeRequest.setType(RequestConstants.REQ_RESIZE);
      fakeRequest.setEditParts(new ArrayList());
      snapToHelper.snapPoint(fakeRequest, PositionConstants.HORIZONTAL
            | PositionConstants.VERTICAL, new PrecisionRectangle[] {baseRect},
            preciseDelta);
      Point newPoint = preciseDelta.getTranslated(oldLocation);
      return newPoint;
   }
   
   // get new rectangle, needed by lanes
   public static Rectangle getSnapRectangle(EditPart hostEP, EditPart part, Dimension currentDimension, Point currentLocation)
   {
      Rectangle rectangle = new Rectangle(currentLocation, currentDimension);
      if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) hostEP) != null)
      {
         // find next size (will not be smaller)
         Dimension snapDimension = SnapGridUtils.getSnapDimension(currentDimension, (AbstractGraphicalEditPart) hostEP, 1, false);            
         Point snapLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) hostEP, (AbstractNodeSymbolEditPart) part, new PrecisionRectangle(rectangle), null, currentLocation);
         if(snapLocation.x <= 0 || snapLocation.y <= 0)
         {
            if(snapLocation.x <= 0)
            {
               currentLocation.x += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;
            }
            if(snapLocation.y <= 0)
            {
               currentLocation.y += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;               
            }
            snapLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) hostEP, (AbstractNodeSymbolEditPart) part, new PrecisionRectangle(rectangle), null, currentLocation);            
         }
         rectangle = new Rectangle(snapLocation, snapDimension);
      }            
      return rectangle;      
   }   

   // get the nearest value to match a multiple of default grid size
   public static int getNextSnapSize(int value)
   {
      int nextValue;
      if(SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE < value)
      {
         int factor = (int) Math.round(new Double((double) value/SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE).doubleValue());
         nextValue = factor * SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
      }
      else
      {
         nextValue = SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;            
      }
      return nextValue;
   }   
}