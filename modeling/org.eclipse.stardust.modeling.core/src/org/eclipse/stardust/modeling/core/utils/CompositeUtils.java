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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

public class CompositeUtils
{
   public static void enableComposite(Composite composite, boolean enable)
   {      
      Control[] children = composite.getChildren();
      for (int i = 0; i < children.length; i++)
      {
         Object child = children[i];  
         if (child instanceof Control)
            {
            if (enableControl((Control) child))
            {
               ((Control) child).setEnabled(true);     
            }
            else
            {
               ((Control) child).setEnabled(enable);
            }
            if(child instanceof Composite)
            {
               enableComposite((Composite) child, enable);               
            }            
         } 
      }      
   }    

   public static void enableContentComposite(Composite composite, boolean enablePage)
   {
      if(composite != null && !composite.isDisposed())
      {
         composite.setEnabled(true);
         enableComposite(composite, enablePage);
      }      
   }
   
   private synchronized static boolean enableControl(Control child)
   {
      if(child instanceof Table && ((((Table) child).getStyle() & SWT.CHECK) != 0)
            || child instanceof Table && ((((Table) child).getStyle() & SWT.RADIO) != 0)
            || child instanceof Table && ((((Table) child).getStyle() & SWT.PASSWORD) != 0))
      {
         //return false;
      }      
      if (child.getClass().getName().equals("org.eclipse.swt.widgets.Composite") //$NON-NLS-1$
            || child instanceof Table
            || child instanceof Tree
            || child instanceof SashForm
            || child instanceof TabFolder)
      {
         return true;
      }      
      
      return false;
   }
}