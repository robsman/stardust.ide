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
package org.eclipse.stardust.modeling.debug.model.ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import ag.carnot.bpm.rt.data.structured.IXPathMap;
import ag.carnot.bpm.rt.data.structured.StructuredDataXPathUtils;
import ag.carnot.bpm.rt.data.structured.TypedXPath;
import ag.carnot.workflow.runtime.beans.BigData;

/**
 * Tree representation of structured data
 */
public class StructuredTree extends TreeViewer
{

   public StructuredTree (Composite parent, StructuredValue rootValue, final IXPathMap xPathMap, final boolean suportsValueModification)
   {
      super(parent, SWT.BORDER | SWT.FULL_SELECTION);
      
      this.setLabelProvider(new StructuredTreeLabelProvider());
      this.setContentProvider(new StructuredTreeContentProvider(xPathMap));
      this.setInput(new StructuredTreeModel(rootValue));
      
      this.getTree().addMouseListener(new MouseAdapter(){
         public void mouseDown(MouseEvent e)
         {
            if (e.button != 3)
            {
               // ignore everything except right mouse button
               return;
            }
            
            if (getTree().getSelection().length != 1 || !suportsValueModification) {
               // ignore if no correct selection is made
               return;
            }
            
            Menu popupMenu = new Menu(getTree());
            
            final StructuredValue selectedValue = (StructuredValue) getTree().getSelection()[0].getData();
            
            // add add commands for every child definition element, that is of type list
            for (java.util.Iterator i = xPathMap.getAllXPaths().iterator(); i.hasNext(); )
            {
               final TypedXPath typedXPath = (TypedXPath)i.next();
               if (StructuredDataXPathUtils.getParentXPath(typedXPath.getXPath()).equals(selectedValue.getXPath().getXPath()))
               {
                  // typedXPath is child XPath of selectedValue's XPath
                  if (typedXPath.isList())
                  {
                     MenuItem actionItem = new MenuItem(popupMenu, SWT.PUSH);
                     final String elementName = StructuredDataXPathUtils.getLastXPathPart(typedXPath.getXPath());
                     actionItem.setText("Add to \""+elementName+"\"");
                     actionItem.addListener(SWT.Selection, new Listener()
                     {
                        public void handleEvent(Event e)
                        {
                           // map for complex types
                           Map selectedMap = (Map)selectedValue.getData();
                           List list = (List)selectedMap.get(elementName);
                           if (list == null) 
                           {
                              list = new LinkedList();
                              selectedMap.put(elementName, list);
                           }
                           if (typedXPath.getType() == BigData.NULL)
                           {
                              Object initialValue = StructuredDataXPathUtils.createInitialValue(xPathMap, typedXPath.getXPath(), true);
                              if (typedXPath.isList())
                              {
                                 // add only the first element of the list 
                                 initialValue = ((List)initialValue).get(0);
                              }
                              list.add(initialValue);
                           }
                           else
                           {
                              list.add(null);
                           }
                           // refresh the tree
                           //update(selectedValue, null);
                           refresh();
                        }
                     });
                  }
               }
            }
            
            
            // add delete command if this is an element of a list
            if (selectedValue.getXPath().isList())
            {
               MenuItem actionItem = new MenuItem(popupMenu, SWT.PUSH);
               actionItem.setText("Delete");
               actionItem.addListener(SWT.Selection, new Listener()
               {
                  public void handleEvent(Event e)
                  {
                     String elementName = StructuredDataXPathUtils.getLastXPathPart(selectedValue.getXPath().getXPath());
                     Map parentMap = (Map) selectedValue.getParent().getData();
                     List list = (List)parentMap.get(elementName);
                     list.remove(selectedValue.getData());
                     // refresh the tree
                     //update(selectedValue.getParent(), null);
                     refresh();
                  }
               });
            }

            popupMenu.setVisible(true);
         }
      });
      
   }
   
}
