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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.SelectionPopup;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.GenericLinkConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.RefersToConnectionEditPart;
import org.eclipse.swt.graphics.Image;


public class CheckDeleteConnections
{
   /**
    * is the one selected object some kind of connection
    * then check if there are more connections between the symbols
    * (show a selectionDialog if so and change the selectedObjects or CANCEL)
    * if there is only one connection, no dialog is necessary
    * @param selectedObjects - Referenz to selectedObjects
    * @param editor - the Editor
    * @param deleteSymbol - called by DeleteSymbolAction 
    */
   public static boolean checkConnections(List selectedObjects, final WorkflowModelEditor editor, boolean deleteSymbol)
   {
      // the selected connection
      Object selection = selectedObjects.get(0);
      
      List sourceElementConnections = null;
      List targetElementConnections = null;      
      List matchedConnections = new ArrayList();
      
      if(selection instanceof AbstractConnectionSymbolEditPart)
      {
         // get sourceModel and targetModel of current Connection
         EditPart source = ((AbstractConnectionSymbolEditPart) selection).getSource();
         if(source instanceof AbstractModelElementNodeSymbolEditPart)
         {
            sourceElementConnections = new ArrayList(((AbstractModelElementNodeSymbolEditPart) source).getSourceConnections());
            // collect all
            sourceElementConnections.addAll(((AbstractModelElementNodeSymbolEditPart) source).getTargetConnections());
         }
         // target
         EditPart target = ((AbstractConnectionSymbolEditPart) selection).getTarget();
         if(target instanceof AbstractModelElementNodeSymbolEditPart)
         {
            targetElementConnections = new ArrayList(((AbstractModelElementNodeSymbolEditPart) target).getSourceConnections());
            targetElementConnections.addAll(((AbstractModelElementNodeSymbolEditPart) target).getTargetConnections());
         }
         // collect the connections between the 2 modelelements 
         if(targetElementConnections != null && sourceElementConnections != null)
         {
            AbstractConnectionSymbolEditPart connection;
            for (int i = 0; i < sourceElementConnections.size(); ++i) 
            {
               connection = (AbstractConnectionSymbolEditPart) sourceElementConnections.get(i);
               if(targetElementConnections.contains(connection) && (deleteSymbol 
                     || (!deleteSymbol && (
                     // in case of this only symbols can be deleted 
                     // (should be not in the list when delete all is called) 
                     !(connection instanceof GenericLinkConnectionEditPart) &&
                     !(connection instanceof RefersToConnectionEditPart)))))                  
               {
                  matchedConnections.add(connection);
               }
            }
            // if there is only one connection (or less), do nothing (no dialog necessary)
            if(matchedConnections.size() <= 1)
            {
               return true;
            }
            // Show Popup Dialog
            SelectionPopup popup = new SelectionPopup(editor.getSite().getShell());
            popup.setContentProvider(new ArrayContentProvider());
            popup.setLabelProvider(new LabelProvider()
            {
               // object is an EditPart
               public Image getImage(Object element)
               {
                  EObject model = (EObject) ((AbstractEditPart) element).getModel();
                  // to call IconFactory
                  EClass meta = model.eClass();
                  return DiagramPlugin.getImage(editor.getIconFactory().getIconFor(meta));
               }
               // object is an EditPart
               public String getText(Object element)
               {
                  EObject model = (EObject) ((AbstractEditPart) element).getModel();
                  if(model instanceof GenericLinkConnectionType)
                  {
                     // the exact name of the link connection (can be more than one)
                     return ((GenericLinkConnectionType) model).getLinkType().getName().toString();
                  }
                  else if(model instanceof TransitionConnectionType)
                  {
                     // the exact name of the transition connection (can be more than one)
                     return ((TransitionConnectionType) model).getTransition().getName().toString();
                  }                  
                  EClass meta = model.eClass();                  
                  return meta.getName();
               }
            });
            popup.setInput(matchedConnections);
            Object selectedConnection = popup.open();
            // ESC is pressed 
            if(selectedConnection == null)
            {
               return false;
            } 
            // overwrite with selection
            selectedObjects.set(0, selectedConnection);
         }
      }
      return true;
   }
}