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
package org.eclipse.stardust.modeling.common.ui.jface.widgets;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class SelectionPopup
{
   private SelectionListener listener;
   private Menu menu;
   protected Object popupSelection = null;
   private IContentProvider contentProvider = null;
   private ILabelProvider labelProvider = null;
   private Object input = null;

   public SelectionPopup(Shell parent) 
   {
      menu = new Menu(parent);
      listener = new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
            // nothing               
         }
         public void widgetSelected(SelectionEvent e)
         {
            // return the selected item  
            MenuItem item = (MenuItem) e.widget;
            popupSelection  = item.getData();             
         }                  
      };         
   }
   
   public void setLocation(int x, int y)
   {
      menu.setLocation(x, y);
   }
   
   /*
   * provide content from input - to iterate over input
   */
   public void setContentProvider(IContentProvider cp)
   {
      contentProvider = cp;
   }

   // provide icon and label
   public void setLabelProvider(ILabelProvider lp)
   {
      labelProvider = lp;
   }
   
   public void setInput(Object i)
   {
      input = i;
   }
   
   // displays the popup and returns the selected item or null if ESC pressed
   public Object open()
   {
      if(input != null && contentProvider instanceof IStructuredContentProvider)
      {
         Object[] elements = ((IStructuredContentProvider) contentProvider).getElements(input);
         for (int i=0; i < elements.length; i++)
         {
            MenuItem mi = new MenuItem(menu, SWT.NONE);            
            Object entry = (Object) elements[i];
            Image img = labelProvider.getImage(entry);
            String txt = labelProvider.getText(entry);
            mi.setText(txt == null ? String.valueOf(entry) : txt);
            if(img != null)
            {
               mi.setImage(img);
            }
            mi.setData(entry);
            mi.addSelectionListener(listener);         
         }
         menu.setVisible(true);
         // wait for input 
         while (!menu.isDisposed() && menu.isVisible()) {
           if (!Display.getCurrent().readAndDispatch())
             Display.getCurrent().sleep();
         }   
         // set by listener or null
         return popupSelection;      
      }     
      return null;
   }
}