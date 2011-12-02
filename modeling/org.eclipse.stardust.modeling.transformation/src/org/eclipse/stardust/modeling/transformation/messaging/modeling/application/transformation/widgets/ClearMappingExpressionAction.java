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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import org.eclipse.jface.action.Action;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.IMessageTransformationApplicationView;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;


public class ClearMappingExpressionAction extends Action
{
   private MessageTransformationController controller;
   private IMessageTransformationApplicationView view;

   public ClearMappingExpressionAction(IMessageTransformationApplicationView view,
         MessageTransformationController controller)
   {
      this.controller = controller;
      this.view = view;
   }

   public void run()
   {
      super.run();
      controller.performMappingExpressionRemovement();
      view.refreshDocument();      
   }

   public String getText()
   {
      return Modeling_Messages.MSG_CLEAR_MAPPING_EXPRESSION;
   }

   @Override
   public boolean isEnabled()
   {
      return controller.isClearMappingExpressionAvailable();
   }

}
