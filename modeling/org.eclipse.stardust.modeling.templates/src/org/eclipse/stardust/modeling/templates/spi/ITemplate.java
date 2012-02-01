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
package org.eclipse.stardust.modeling.templates.spi;

import org.eclipse.gef.EditPart;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;


public interface ITemplate
{  
   String getId();

   String getName();
   
   void setName(String name);

   String getDescription();
   
   ITemplateFactory getParentFactory();
   
   void applyTemplate(WorkflowModelEditor editor, ModelType targetModel, DiagramType targetDiagram, EditPart editPart, int xHint, int yHint);
}
