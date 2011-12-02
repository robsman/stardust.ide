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
package org.eclipse.stardust.modeling.core.editors;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;


public class DynamicConnectionFactory implements CreationFactory
{
   private DynamicConnectionCommand cmd;
   private WorkflowModelEditor editor;
   private String targetAnchorType;
   private String sourceAnchorType;
   
   public DynamicConnectionFactory(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public Command getStartCommand(INodeSymbol sourceSymbol)
   {
      ModelServer server = editor.getModelServer();
      if (server != null && server.requireLock(sourceSymbol))
      {
         return null;
      }
      
      cmd = new DynamicConnectionCommand(editor);
      cmd.setSourceSymbol(sourceSymbol);
      cmd.setSourceAnchorType(sourceAnchorType);
      cmd.setTargetAnchorType(targetAnchorType);
      return cmd.canExecute() ? cmd : null;
   }

   public Command getCompleteCommand(INodeSymbol targetSymbol)
   {
      cmd.setTargetSymbol(targetSymbol);
      return cmd;
   }

   public Object getNewObject()
   {
      return this;
   }

   public Object getObjectType()
   {
      return CarnotWorkflowModelPackage.eINSTANCE.getIConnectionSymbol();
   }

   public void setTargetAnchorType(String anchorType)
   {
      this.targetAnchorType = anchorType;
      if (cmd != null)
      {
         cmd.setTargetAnchorType(anchorType);
      }
   }

   public void setSourceAnchorType(String anchorType)
   {
      this.sourceAnchorType = anchorType;
      if (cmd != null)
      {
         cmd.setSourceAnchorType(anchorType);
      }
   }
}