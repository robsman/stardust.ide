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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.ui.PlatformUI;


/**
 * @author rsauer
 * @version $Revision$
 */
public class SnapCenterToGrid extends SnapToGrid implements IPropertyChangeListener
{
   // constants to show what must be changed
   public static final int CHANGE_BOTH = 1;
   public static final int CHANGE_DIMENSION = 2;   
   /**
    * The default grid size if the viewer does not specify a size.
    * 
    * @see #PROPERTY_GRID_SPACING
    */
   public static int CARNOT_DEFAULT_GRID_SIZE = 
                              Integer.parseInt(PlatformUI.getPreferenceStore()
                                    .getString(BpmProjectNature.PREFERENCE_SNAP_GRID_PIXEL));
   /**
    * factor for visible Grid
    */
   public static int CARNOT_VISIBLE_GRID_FACTOR = 
                              Integer.parseInt(PlatformUI.getPreferenceStore()
                                    .getString(BpmProjectNature.PREFERENCE_VISIBLE_GRID_FACTOR));
   
   public SnapCenterToGrid(GraphicalEditPart container)
   {
      super(container);
      // register to preference store
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      store.addPropertyChangeListener(this);
      
      setSpacing();
   }
   
   public void setSpacing()
   {
      Dimension spacing = (Dimension) container.getViewer().getProperty(
            PROPERTY_GRID_SPACING);
      if (null == spacing)
      {
         if (DEFAULT_GRID_SIZE == gridX)
         {
            gridX = CARNOT_DEFAULT_GRID_SIZE;
         }
         if (DEFAULT_GRID_SIZE == gridY)
         {
            gridY = CARNOT_DEFAULT_GRID_SIZE;
         }
      }      
   }   

   /**
    * @see SnapToHelper#snapRectangle(Request, int, PrecisionRectangle,
    *      PrecisionRectangle)
    */
   public int snapRectangle(Request request, int snapLocations, PrecisionRectangle rect,
         PrecisionRectangle result)
   {
      rect = rect.getPreciseCopy();
      makeRelative(container.getContentPane(), rect);
      PrecisionRectangle correction = new PrecisionRectangle();
      makeRelative(container.getContentPane(), correction);
      
      if (gridX > 0 && (snapLocations & EAST) != 0)
      {
         snapHorizontal(rect, correction, new Integer(CHANGE_DIMENSION));
         snapLocations &= ~EAST;
      }

      if ((snapLocations & WEST) != 0 && gridX > 0)
      {
         snapHorizontal(rect, correction, new Integer(CHANGE_BOTH));
         snapLocations &= ~WEST;
      }

      if ((snapLocations & HORIZONTAL) != 0 && gridX > 0)
      {
         snapHorizontal(rect, correction, null);
         snapLocations &= ~HORIZONTAL;
      }

      if ((snapLocations & SOUTH) != 0 && gridY > 0)
      {
         snapVertical(rect, correction, new Integer(CHANGE_DIMENSION));
         snapLocations &= ~SOUTH;
      }

      if ((snapLocations & NORTH) != 0 && gridY > 0)
      {
         snapVertical(rect, correction, new Integer(CHANGE_BOTH));
         snapLocations &= ~NORTH;
      }

      if ((snapLocations & VERTICAL) != 0 && gridY > 0)
      {
         snapVertical(rect, correction, null);
         snapLocations &= ~VERTICAL;
      }
      
      correction.updateInts();
      makeAbsolute(container.getContentPane(), correction);      
      result.preciseX += correction.preciseX;
      result.preciseY += correction.preciseY;
      result.preciseWidth += correction.preciseWidth;
      result.preciseHeight += correction.preciseHeight;
      result.updateInts();
      
      return snapLocations;
   }

   private void snapVertical(PrecisionRectangle rect, PrecisionRectangle correction, Integer change)
   {
      double topCorrection = 0;
      double heightCorrection = 0;
      if(change == null)
      {
         topCorrection = Math.IEEEremainder(rect.preciseY - origin.y, gridY);
      }      
      else if(change.intValue() == CHANGE_BOTH)
      {
         topCorrection = Math.IEEEremainder(rect.preciseY - origin.y, gridY);
         heightCorrection = Math.IEEEremainder(rect.preciseHeight - origin.y, gridY);         
      }
      else if(change.intValue() == CHANGE_DIMENSION)
      {
         heightCorrection = Math.IEEEremainder(rect.preciseHeight - origin.y, gridY);
      }
      correction.preciseHeight -= heightCorrection;         
      correction.preciseY -= topCorrection;         
   }

   private void snapHorizontal(PrecisionRectangle rect, PrecisionRectangle correction, Integer change)
   {
      double leftCorrection = 0;
      double widthCorrection = 0;
      if(change == null)
      {
         leftCorrection = Math.IEEEremainder(rect.preciseX - origin.x, gridX);
      }      
      else if(change.intValue() == CHANGE_BOTH)
      {
         leftCorrection = Math.IEEEremainder(rect.preciseX - origin.x, gridX);
         widthCorrection = Math.IEEEremainder(rect.preciseWidth - origin.x, gridX);         
      }
      else if(change.intValue() == CHANGE_DIMENSION)
      {
         widthCorrection = Math.IEEEremainder(rect.preciseWidth - origin.x, gridX);
      }
      correction.preciseWidth -= widthCorrection;         
      correction.preciseX -= leftCorrection;         
   }

   public void propertyChange(PropertyChangeEvent event)
   {
      boolean hasChanged = false;
      if(event.getProperty().equals(BpmProjectNature.PREFERENCE_SNAP_GRID_PIXEL))
      {
         CARNOT_DEFAULT_GRID_SIZE = Integer.parseInt(((String) event.getNewValue()));
         hasChanged = true;
      }
      if(event.getProperty().equals(BpmProjectNature.PREFERENCE_VISIBLE_GRID_FACTOR))
      {
         CARNOT_VISIBLE_GRID_FACTOR = Integer.parseInt(((String) event.getNewValue()));         
         hasChanged = true;
      }
      if(hasChanged)
      {
         setSpacing();
      }
  }
}