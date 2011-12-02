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
package org.eclipse.stardust.modeling.core.editors.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.swt.graphics.Image;


/**
 * @author fherinean
 * @version $Revision$
 */
public class EObjectLabelProvider
   extends LabelProvider
   implements TableLabelProvider
{
   protected WorkflowModelEditor editor;

   public EObjectLabelProvider(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public String getText(Object element)
   {
      if (element instanceof EObject)
      {
         // try to get the "name" feature value
         String text = getText("name", element); //$NON-NLS-1$
         if (text.length() == 0)
         {
            // if no name found or set, try to get the "id" value
            text = getText("id", element); //$NON-NLS-1$
         }
         if (text.length() == 0 && element instanceof ITypedElement)
         {
            // if no name found or set, try to get the "type" name
            text = getText(((ITypedElement) element).getMetaType());
         }
         if (text.length() != 0)
         {
            return text;
         }
      }
      // fall back to default implementation
      return super.getText(element);
   }

   public boolean isLabelProperty(Object element, String property)
   {
      return super.isLabelProperty(element, property);
   }

   public String getText(String name, Object element)
   {
      EObject eObject = (EObject) element;
      EStructuralFeature attr = eObject.eClass().getEStructuralFeature(name);
      Object value = attr == null ? null : eObject.eGet(attr);
      return value == null ? "" : value.toString(); //$NON-NLS-1$
   }

   public String getText(int index, Object element)
   {
      EObject eObject = (EObject) element;
      EStructuralFeature attr = eObject.eClass().getEStructuralFeature(index);
      Object value = attr == null ? null : eObject.eGet(attr);
      return value == null ? "" : value.toString(); //$NON-NLS-1$
   }

   public boolean isNamed()
   {
      return true;
   }

   public boolean accept(Object element)
   {
      return element instanceof EObject;
   }

   public Image getImage(Object element)
   {
      String icon = editor.getIconFactory().getIconFor((EObject) element);
      return icon == null ? null : DiagramPlugin.getImage(icon);
   }
}