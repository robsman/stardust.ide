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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.tree.IdentifiableModelElementTreeEditPart;

import ag.carnot.workflow.model.PredefinedConstants;

public class UpgradeDataAction extends SelectionAction
{
   private Command cmd;

   public UpgradeDataAction(WorkflowModelEditor editor)
   {
      super(editor);
      setId(DiagramActionConstants.DATA_UPGRADE);
      setText(Diagram_Messages.LB_UpgradeData);
   }

   private static final String STARTING_USER = "STARTING_USER"; //$NON-NLS-1$

   private static final String LAST_ACTIVITY_PERFORMER = "LAST_ACTIVITY_PERFORMER"; //$NON-NLS-1$

   protected boolean calculateEnabled()
   {
      Object obj = getSelection();
      if (obj instanceof IStructuredSelection)
      {
         obj = ((IStructuredSelection) obj).getFirstElement();
         if (obj instanceof IdentifiableModelElementTreeEditPart)
         {
            Object modelElement = ((AbstractTreeEditPart) obj).getModel();
            if (modelElement instanceof DataType
                  && (LAST_ACTIVITY_PERFORMER.equals(((DataType) modelElement).getId()) || (STARTING_USER
                        .equals(((DataType) modelElement).getId()))))
            {
               cmd = createUpdatePredefinedDataCmd((DataType) modelElement);
               return cmd.canExecute();
            }
         }
      }
      return false;
   }

   public void run()
   {
      execute(cmd);
   }

   private Command createUpdatePredefinedDataCmd(DataType data)
   {
      CompoundCommand command = new CompoundCommand();
      if (data != null)
      {
         Map map = new HashMap();
         map.put(PredefinedConstants.HOME_INTERFACE_ATT,
               "ag.carnot.workflow.runtime.UserHome"); //$NON-NLS-1$
         map.put(PredefinedConstants.REMOTE_INTERFACE_ATT,
               "ag.carnot.workflow.runtime.beans.IUser"); //$NON-NLS-1$
         map.put(PredefinedConstants.PRIMARY_KEY_ATT, "ag.carnot.workflow.runtime.UserPK"); //$NON-NLS-1$
         map.put(PredefinedConstants.JNDI_PATH_ATT, "ag.carnot.workflow.runtime.User"); //$NON-NLS-1$

         for (Iterator iter = map.keySet().iterator(); iter.hasNext();)
         {
            String attName = (String) iter.next();
            String attValue = (String) map.get(attName);

            AttributeType attribute = AttributeUtil.getAttribute(data, attName);
            if (attribute != null && !attribute.getValue().equals(attValue))
            {
               command.add(new SetValueCmd(attribute,
                     CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
                     attValue));
            }
         }
         if (!data.isPredefined())
         {
            command.add(new SetValueCmd(data, CarnotWorkflowModelPackage.eINSTANCE
                  .getDataType_Predefined(), true));
         }
      }
      return command;
   }
}
