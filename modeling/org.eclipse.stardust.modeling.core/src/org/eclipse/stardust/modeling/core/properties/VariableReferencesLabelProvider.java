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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.swt.graphics.Image;


public class VariableReferencesLabelProvider extends LabelProvider
      implements ITableLabelProvider
{
   EObjectLabelProvider labelProvider;

   public VariableReferencesLabelProvider(WorkflowModelEditor editor)
   {
      labelProvider = new EObjectLabelProvider(editor);
   }

   public Image getColumnImage(Object element, int columnIndex)
   {
      if (columnIndex == 0)
      {
         return labelProvider.getImage(((EObject) element).eContainer());
      }
      return null;
   }

   public String getColumnText(Object element, int columnIndex)
   {
      if (columnIndex == 0)
      {
         EObject object = (EObject)element;
         if (object.eContainer() instanceof ModelType) {
            return labelProvider.getText(((EObject) element));
         }
         return labelProvider.getText(((EObject) element).eContainer());
      }
      if (columnIndex == 1)
      {
         if (element instanceof XmlTextNode)
         {
            if (((XmlTextNode) element).eContainer() instanceof TransitionType)
            {
               return Diagram_Messages.COL_TXT_TRANSITION_CONDITION;
            }
            return Diagram_Messages.COL_TXT_XML_TEXT_NODE;
         }
         if (element instanceof DescriptionType)
         {
            return Diagram_Messages.COL_TXT_DESCRIPTION;
         }
         if (element instanceof AttributeType)
         {
            AttributeType attribute = (AttributeType)element;
            return attribute.getName();
         }
         return labelProvider.getText(element);
      }
      if (columnIndex == 2)
      {
         if (element instanceof XmlTextNode)
         {
            return ModelUtils.getCDataString(((XmlTextNode) element).getMixed());
         }
         if (element instanceof DescriptionType)
         {
            return ModelUtils.getCDataString(((DescriptionType) element).getMixed());
         }
         if (element instanceof AttributeType)
         {
            AttributeType attribute = (AttributeType)element;
            return attribute.getValue();
         }
         return labelProvider.getText(element);
      }
      return ""; //$NON-NLS-1$
   }

}
