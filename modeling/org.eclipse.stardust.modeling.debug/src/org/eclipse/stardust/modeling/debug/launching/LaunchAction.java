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
package org.eclipse.stardust.modeling.debug.launching;

import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.core.actions.EditDomainAwareAction;
import org.eclipse.stardust.modeling.debug.Debug_Messages;


public class LaunchAction extends EditDomainAwareAction implements IAction
{
   private LaunchShortcut launcher = new LaunchShortcut();

   public LaunchAction()
   {
      setId("org.eclipse.stardust.modeling.debug"); //$NON-NLS-1$
      setText(Debug_Messages.LaunchAction_Name);
      setToolTipText(Debug_Messages.LaunchAction_ToolTip);
   }

   public boolean isEnabled()
   {
      if (super.isEnabled() && getSelectedModelElements().size() == 1)
      {
         EObject target = (EObject) getSelectedModelElements().get(0);
         return isInSameModel(target) && isLaunchable(target);
      }
      return false;
   }

   private boolean isInSameModel(EObject target)
   {
      return getModel() == getEditor().getWorkflowModel();
   }

   private boolean isLaunchable(EObject target)
   {
      return isProcessDefinition(target) || isProcessDiagram(target);
   }

   private boolean isProcessDefinition(EObject target)
   {
      return target instanceof ProcessDefinitionType;
   }

   private boolean isProcessDiagram(EObject target)
   {
      return target instanceof DiagramType && target.eContainer() instanceof ProcessDefinitionType;
   }

   public void run()
   {
      Object target = getSelectedModelElements().get(0);
      if (target instanceof ProcessDefinitionType)
      {
         launcher.launch((ProcessDefinitionType) target, ILaunchManager.DEBUG_MODE);
      }
      else if (target instanceof DiagramType)
      {
         launcher.launch((DiagramType) target, ILaunchManager.DEBUG_MODE);
      }
   }
}
