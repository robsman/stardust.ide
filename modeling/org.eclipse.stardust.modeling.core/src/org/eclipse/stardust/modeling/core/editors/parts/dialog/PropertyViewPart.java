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
package org.eclipse.stardust.modeling.core.editors.parts.dialog;

import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class PropertyViewPart extends ViewPart implements ISelectionListener
{
   private IPropertyView propertyView;

   private AbstractEditPart selectedElement;

   private Composite parent;

   private CommandStackEventListener cseListener;

   public PropertyViewPart()
   {
      super();
   }

   public Object getAdapter(Class adapter)
   {
      return super.getAdapter(adapter);
   }

   public void dispose()
   {
      super.dispose();
   }

   public void createPartControl(Composite parent)
   {
      this.parent = parent;
      this.getViewSite().getPage().addSelectionListener(this);
   }

   public void setFocus()
   {
      this.parent.setFocus();
   }

   public void selectionChanged(IWorkbenchPart part, ISelection selection)
   {
      if (selection instanceof IStructuredSelection)
      {
         Object element = ((IStructuredSelection) selection).getFirstElement();

         if (element instanceof AbstractEditPart)
         {
            if (selectedElement == null
                  || (selectedElement != null && element != null && !selectedElement
                        .equals(element)))
            {
               if (propertyView != null)
               {
                  propertyView.dispose();
               }

               propertyView = PropertyViewFactory.instance().createPropertyView(element);

               if (propertyView != null)
               {
                  propertyView.createPartControl(parent);
               }

               if (cseListener != null)
               {
                  selectedElement.getViewer().getEditDomain().getCommandStack()
                        .removeCommandStackEventListener(cseListener);
               }

               selectedElement = (AbstractEditPart) element;

               if (propertyView != null)
               {
                  cseListener = propertyView.createCommandStackListener();
               }

               selectedElement.getViewer().getEditDomain().getCommandStack()
                     .addCommandStackEventListener(cseListener);
            }

            if (propertyView != null)
            {
               propertyView.selectionChanged(selectedElement);
            }
         }
      }
   }
}