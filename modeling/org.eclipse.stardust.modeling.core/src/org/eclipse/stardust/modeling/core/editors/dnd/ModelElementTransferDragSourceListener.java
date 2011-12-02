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
package org.eclipse.stardust.modeling.core.editors.dnd;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.gef.dnd.AbstractTransferDragSourceListener;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;


public class ModelElementTransferDragSourceListener
      extends AbstractTransferDragSourceListener
{
   IIdentifiableModelElement element = null;

   public ModelElementTransferDragSourceListener(EditPartViewer viewer)
   {
      super(viewer, ModelElementTransfer.getInstance());
   }

   public void dragSetData(DragSourceEvent event)
   {
      event.data = getModelElement();
   }

   public void dragFinished(DragSourceEvent event)
   {
      element = null;
      ModelElementTransfer.getInstance().setObject(null);
   }

   public void dragStart(DragSourceEvent event)
   {
      Object element = getModelElement();
      if (element == null)
         event.doit = false;

      if (element instanceof INodeSymbol
            || ModelElementSymbolCreationFactory.getSymbolEClass(element) == null)
      {
         event.doit = false;
      }
      ModelElementTransfer.getInstance().setObject(element);
   }

   public Transfer getTransfer()
   {
      return super.getTransfer();
   }

   private Object getModelElement()
   {
      Object selection = null;
      EditPart editPart = (EditPart) getViewer().getSelectedEditParts().get(0);
      if (editPart instanceof TreeEditPart)
      {
         selection = editPart.getModel();         
      }
      return (selection instanceof IIdentifiableModelElement
            || selection instanceof IObjectDescriptor
            || selection instanceof TypeDeclarationType)
            ? selection : null;
   }
}
