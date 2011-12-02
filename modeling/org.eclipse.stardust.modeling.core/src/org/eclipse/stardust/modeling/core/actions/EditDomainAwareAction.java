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
package org.eclipse.stardust.modeling.core.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;


/**
 * @author rsauer
 * @version $Revision$
 */
public class EditDomainAwareAction extends Action
{
   private EditDomain editDomain;

   private ModelType model;

   // Workaround for fixing CAD converter
   private WorkflowModelEditor editor;

   private List<IModelElement> selectedModelElements = Collections.emptyList();

   private List<IGraphicalObject> selectedSymbols = Collections.emptyList();

   public void setContext(EditDomain editDomain, ModelType model,
         List<IModelElement> selectedModelElements, List<IGraphicalObject> selectedSymbols)
   {
      this.editDomain = editDomain;

      this.model = model;

      this.selectedModelElements = selectedModelElements;
      this.selectedSymbols = selectedSymbols;
   }

   public EditDomain getEditDomain()
   {
      return editDomain;
   }

   public ModelType getModel()
   {
      return model;
   }

   public List<IModelElement> getSelectedModelElements()
   {
      return selectedModelElements;
   }

   public List<IGraphicalObject> getSelectedSymbols()
   {
      return selectedSymbols;
   }

   public WorkflowModelEditor getEditor()
   {
      return editor;
   }

   public void setEditor(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public boolean isEnabled()
   {
      return (null != getEditDomain()) && super.isEnabled();
   }
}
