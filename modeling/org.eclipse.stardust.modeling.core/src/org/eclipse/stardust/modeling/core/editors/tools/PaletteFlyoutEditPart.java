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

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.ChangeEvent;
import org.eclipse.draw2d.ChangeListener;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.internal.ui.palette.editparts.IPaletteStackEditPart;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteListener;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerPreferences;
import org.eclipse.gef.ui.palette.editparts.PaletteEditPart;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;

/**
 * The EditPart for a PaletteStack.
 * 
 * @author Whitney Sorenson
 * @since 3.0
 */
public class PaletteFlyoutEditPart extends PaletteEditPart implements IPaletteStackEditPart
{
   private final DynamicToolEntry myEntry;

   private static final Dimension EMPTY_DIMENSION = new Dimension(0, 0);

   private ChangeListener clickableListener = new ChangeListener()
   {
      public void handleStateChanged(ChangeEvent event)
      {
         if (null != activeFigure)
         {
            if (event.getPropertyName().equals(ButtonModel.MOUSEOVER_PROPERTY))
            {
               getMyEntryFigure().getModel().setMouseOver(
                     activeFigure.getModel().isMouseOver());
            }
            else if (event.getPropertyName().equals(ButtonModel.SELECTED_PROPERTY))
            {
               getMyEntryFigure().getModel().setSelected(
                     activeFigure.getModel().isSelected());
               getMyEntryFigure().getModel().setMouseOver(
                     activeFigure.getModel().isMouseOver());
               getMyEntryFigure().getModel().setPressed(
                     activeFigure.getModel().isPressed());
            }
            else if (event.getPropertyName().equals(ButtonModel.PRESSED_PROPERTY))
            {
               getMyEntryFigure().getModel().setPressed(
                     activeFigure.getModel().isPressed());
            }
         }
      }
   };

   // listen to see if active tool is changed in palette
   private PaletteListener paletteListener = new PaletteListener()
   {
      public void activeToolChanged(PaletteViewer palette, ToolEntry tool)
      {
         if (getToolContainer().getChildren().contains(tool))
         {
            if ( !getToolContainer().getActiveEntry().equals(tool))
               getToolContainer().setActiveEntry(tool);
         }
      }
   };

   private Clickable activeFigure;

   private Figure contentsFigure;

   private Menu menu;

   /**
    * Creates a new PaletteStackEditPart with the given PaletteStack as its model.
    * 
    * @param model
    *           the PaletteStack to associate with this EditPart.
    */
   public PaletteFlyoutEditPart(PaletteFlyout model)
   {
      super(model);

      this.myEntry = new DynamicCreationToolEntry(model.getLabel(),
            model.getDescription(), model.getSmallIcon(), model.getLargeIcon());
   }

   /**
    * @see org.eclipse.gef.EditPart#activate()
    */
   public void activate()
   {
      // in case the model is out of sync
      checkActiveEntrySync();
      ((PaletteViewer) getViewer()).addPaletteListener(paletteListener);
      super.activate();
   }

   /**
    * Called when the active entry has changed.
    * 
    * @param oldValue
    *           the old model value (can be null)
    * @param newValue
    *           the new model value (can be null)
    */
   private void activeEntryChanged(Object oldValue, Object newValue)
   {
      GraphicalEditPart part = null;
      Clickable clickable = null;

      if (newValue != null)
      {
         part = (GraphicalEditPart) getViewer().getEditPartRegistry().get(newValue);
         clickable = (Clickable) part.getFigure();
         // clickable.setVisible(true);
         activeFigure = clickable;

         clickable.addChangeListener(clickableListener);
      }

      if (oldValue != null)
      {
         part = (GraphicalEditPart) getViewer().getEditPartRegistry().get(oldValue);
         // if part is null, its no longer a child.
         if (part != null)
         {
            clickable = (Clickable) part.getFigure();
            clickable.setVisible(false);
            clickable.removeChangeListener(clickableListener);
         }
      }

      getMyEntryFigure().setVisible(true);
   }

   private void checkActiveEntrySync()
   {
      if (activeFigure == null)
         activeEntryChanged(null, getToolContainer().getActiveEntry());
   }

   /**
    * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
    */
   public IFigure createFigure()
   {
      Figure figure = new Figure()
      {
         public Dimension getPreferredSize(int wHint, int hHint)
         {
            if (PaletteFlyoutEditPart.this.getChildren().isEmpty())
               return EMPTY_DIMENSION;
            return super.getPreferredSize(wHint, hHint);
         }

         public void paintBorder(Graphics graphics)
         {
            int layoutMode = getPreferenceSource().getLayoutSetting();
            if (layoutMode == PaletteViewerPreferences.LAYOUT_LIST
                  || layoutMode == PaletteViewerPreferences.LAYOUT_DETAILS)
               ;// return;

            Rectangle rect = getBounds().getCopy();

            graphics.translate(getLocation());
            graphics.setBackgroundColor(ColorConstants.listForeground);

            // fill the corner arrow
            int[] points = new int[6];

            points[0] = rect.width;
            points[1] = rect.height - 5;
            points[2] = rect.width;
            points[3] = rect.height;
            points[4] = rect.width - 5;
            points[5] = rect.height;

            graphics.fillPolygon(points);

            graphics.translate(getLocation().getNegated());
         }
      };
      figure.setLayoutManager(new BorderLayout());

      contentsFigure = new Figure();
      
      
      
      StackLayout stackLayout = new StackLayout();
      // make it so the stack layout does not allow the invisible figures to contribute
      // to its bounds
      stackLayout.setObserveVisibility(true);
      contentsFigure.setLayoutManager(stackLayout);

      figure.add(contentsFigure, BorderLayout.CENTER);      
      return figure;
   }

   /**
    * @see org.eclipse.gef.EditPart#deactivate()
    */
   public void deactivate()
   {
      ((PaletteViewer) getViewer()).removePaletteListener(paletteListener);
      super.deactivate();
   }

   /**
    * @see org.eclipse.gef.EditPart#eraseTargetFeedback(org.eclipse.gef.Request)
    */
   public void eraseTargetFeedback(Request request)
   {
      Iterator children = getChildren().iterator();

      while (children.hasNext())
      {
         PaletteEditPart part = (PaletteEditPart) children.next();
         part.eraseTargetFeedback(request);
      }
      super.eraseTargetFeedback(request);
   }

   /**
    * @see org.eclipse.gef.GraphicalEditPart#getContentPane()
    */
   public IFigure getContentPane()
   {
      return contentsFigure;
   }

   private PaletteFlyout getToolContainer()
   {
      return (PaletteFlyout) getModel();
   }

   /**
    * Opens the menu to display the choices for the active entry.
    */
   public void openMenu()
   {
      MenuManager menuManager = new MenuManager();

      PaletteViewer paletteViewer = (PaletteViewer) getViewer();


      PaletteEditPart part = null;
      PaletteEntry entry = null;
      for (Iterator children = getChildren().iterator(); children.hasNext();)
      {
         part = (PaletteEditPart) children.next();
         entry = (PaletteEntry) part.getModel();

         if (myEntry != entry)
         {
            menuManager.add(new SetActiveFlyoutToolAction(paletteViewer,
                  entry.getLabel(), entry.getSmallIcon(), (ToolEntry) entry));
         }
      }

      menu = menuManager.createContextMenu(paletteViewer.getControl());

      // make the menu open below the figure
      Rectangle figureBounds = getFigure().getBounds().getCopy();
      getFigure().translateToAbsolute(figureBounds);

      Point menuLocation = paletteViewer.getControl().toDisplay(
            figureBounds.getTopRight().x, figureBounds.getTopRight().y);

      // remove feedback from the arrow Figure and children figures
      if (null != activeFigure)
      {
         activeFigure.getModel().setMouseOver(false);
      }
      eraseTargetFeedback(new Request(RequestConstants.REQ_SELECTION));

      menu.setLocation(menuLocation);
      menu.addMenuListener(new StackMenuListener(menu, getViewer().getControl()
            .getDisplay()));
      menu.setVisible(true);
   }

   /**
    * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
    */
   public void propertyChange(PropertyChangeEvent event)
   {
      if (event.getPropertyName().equals(PaletteFlyout.PROPERTY_ACTIVE_ENTRY))
         activeEntryChanged(event.getOldValue(), event.getNewValue());
      else
         super.propertyChange(event);
   }

   public List getModelChildren()
   {
      List result = new ArrayList(super.getModelChildren());

      result.add(0, myEntry);

      return result;
   }

   /**
    * @see org.eclipse.gef.editparts.AbstractEditPart#refreshChildren()
    */
   protected void refreshChildren()
   {
      super.refreshChildren();

      Iterator children = getChildren().iterator();
      while (children.hasNext())
      {
         PaletteEditPart editPart = (PaletteEditPart) children.next();
         editPart.getFigure().setVisible(myEntry == editPart.getModel());
      }
   }

   /**
    * @see org.eclipse.gef.EditPart#showTargetFeedback(org.eclipse.gef.Request)
    */
   public void showTargetFeedback(Request request)
   {
      // if menu is showing, don't show feedback. this is a fix
      // for the occasion when show is called after forced erase
      if (menu != null && !menu.isDisposed() && menu.isVisible())
         return;

      Iterator children = getChildren().iterator();
      while (children.hasNext())
      {
         PaletteEditPart part = (PaletteEditPart) children.next();
         part.showTargetFeedback(request);
      }

      super.showTargetFeedback(request);
   }

   private Clickable getMyEntryFigure()
   {
      GraphicalEditPart part = (GraphicalEditPart) getViewer().getEditPartRegistry().get(
            myEntry);
      return (Clickable) part.getFigure();
   }

   class StackMenuListener extends MenuAdapter
   {
      private Menu menu;

      private Display d;

      StackMenuListener(Menu menu, Display d)
      {
         this.menu = menu;
         this.d = d;
      }

      /**
       * @see org.eclipse.swt.events.MenuListener#menuHidden(org.eclipse.swt.events.MenuEvent)
       */
      public void menuHidden(MenuEvent e)
      {
         if ((null != activeFigure) && !activeFigure.isSelected())
         {
            clickableListener.handleStateChanged(new ChangeEvent(activeFigure,
                  ButtonModel.SELECTED_PROPERTY));
         }

         d.asyncExec(new Runnable()
         {
            public void run()
            {
               if (menu != null)
               {
                  if ( !menu.isDisposed())
                     menu.dispose();
                  menu = null;
               }
            }
         });
      }
   }

   public PaletteEditPart getActiveEntry()
   {
      return this;
   }
}
