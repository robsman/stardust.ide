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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CloseDiagramAction extends UpdateDiagramAction
{
   public CloseDiagramAction(WorkflowModelEditor editor)
   {
      super(editor);
      initUI();
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1 && getDiagram() != null
            && ((WorkflowModelEditor) getWorkbenchPart()).hasDiagramPage(getDiagram());
   }

   public void run()
   {
      ((WorkflowModelEditor) getWorkbenchPart()).closeDiagramPage(getDiagram());
   }

   private DiagramType getDiagram()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof DiagramRootEditPart)
      {
         Object element = ((DiagramRootEditPart) selection).getContents().getModel();
         if (element instanceof DiagramType)
         {
            return (DiagramType) element;
         }
      }
      return null;
   }

   protected void initUI()
   {
      super.init();
      setId(DiagramActionConstants.DIAGRAM_CLOSE);
      setText(Diagram_Messages.TXT_CloseDiagram);
   }
}
