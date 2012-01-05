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

import org.eclipse.stardust.engine.core.struct.IXPathMap;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.debug.debugger.UiAccessor;
import org.eclipse.ui.IEditorPart;

public class DebuggerUtils
{

   public static IXPathMap getXPathMapOfCurrentModel(String declaredTypeAdapterId)
   {
      IEditorPart editPart = UiAccessor.getActiveEditPart();
      WorkflowModelEditor editor = (WorkflowModelEditor) editPart;
      ModelType model = editor.getWorkflowModel();

      return StructuredTypeUtils.getXPathMap(model, declaredTypeAdapterId);
   }

}
