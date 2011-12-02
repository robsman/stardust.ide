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

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.PlatformUI;


public class SymbolCollisionUtils
{
   public static boolean isCollision(EditPart targetEP, Point useLocation)
   {            
      for(Iterator iter = ((PoolSymbol) targetEP).getNodes().valueListIterator(); iter.hasNext();)
      {
         EObject element = (EObject) iter.next();
         if(element instanceof INodeSymbol)
         {
            if(isSymbolCollision((INodeSymbol) element, useLocation))
            {
               return true;
            }            
         }               
      }         
      return false;
   }

   public static boolean isSymbolCollision(INodeSymbol symbol, Point useLocation)
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().
         getActiveWorkbenchWindow().getActivePage().getActiveEditor();            
      
      AbstractGraphicalEditPart editPart = (AbstractGraphicalEditPart) editor.findEditPart(symbol);
      IFigure figure = editPart.getFigure();
      Rectangle bounds = figure.getBounds().getCopy();
      if(bounds.intersects(null))
      {
         return true;
      }
      return false;
   }
}