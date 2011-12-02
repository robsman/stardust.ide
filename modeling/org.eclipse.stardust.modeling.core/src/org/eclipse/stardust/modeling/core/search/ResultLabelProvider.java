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
package org.eclipse.stardust.modeling.core.search;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.swt.graphics.Image;


public class ResultLabelProvider implements ILabelProvider
{
   private WorkflowModelEditor editor;

   public ResultLabelProvider(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public Image getImage(Object element)
   {
      String icon = null;
      if (element instanceof EObject)
      {
         icon = editor.getIconFactory().getIconFor((EObject) element);
      }
      return icon == null ? null : DiagramPlugin.getImage(icon);
   }

   public String getText(Object element)
   {
      String name = ""; //$NON-NLS-1$
      if (element instanceof IIdentifiableModelElement)
      {
         name = ((IIdentifiableModelElement) element).getName();
      }
      else if (element instanceof DescriptionType)
      {
         name = (String) ((DescriptionType) element).getMixed().getValue(0);
         name = name.length() > 100 ? name.substring(0, 100) : name;
      }
      else if (element instanceof DiagramType)
      {
         name = ((DiagramType) element).getName();
      }
      else if (element instanceof INodeSymbol && element instanceof IModelElementNodeSymbol)
      {
         name = ((IModelElementNodeSymbol) element).getModelElement().getName();
      }
      else if (element instanceof DataMappingType)
      {
         name = ((DataMappingType) element).getId();
      }
      else if (element instanceof ParameterMappingType)
      {
         name = ((ParameterMappingType) element).getData().getId();
      }
      return name;
   }

   public void addListener(ILabelProviderListener listener)
   {}

   public void dispose()
   {}

   public boolean isLabelProperty(Object element, String property)
   {
      return false;
   }

   public void removeListener(ILabelProviderListener listener)
   {}
}
