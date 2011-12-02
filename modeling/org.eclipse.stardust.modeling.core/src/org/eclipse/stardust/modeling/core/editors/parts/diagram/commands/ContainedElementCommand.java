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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class ContainedElementCommand extends Command implements IContainedElementCommand
{
   private int parentLevel;
   private EObject parent;

   public ContainedElementCommand(int parentLevel)
   {
      this.parentLevel = parentLevel;
   }

   public EObject getContainer()
   {
      switch (parentLevel)
      {
         case MODEL:
            return getModel();
         case PROCESS:
            return getProcess();
         default:
            return getParent();
      }
   }

   public void setParent(EObject parent)
   {
      this.parent = parent;
   }

   public IContainedElementCommand duplicate()
   {
      try
      {
         return (IContainedElementCommand) clone();
      }
      catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
         return this;
      }
   }

   public void dispose()
   {
      parent = null;
   }

   public EObject getParent()
   {
      return parent;
   }

   public ProcessDefinitionType getProcess()
   {
      if(getParent() == null)
      {
         return null;
      }
      return ModelUtils.findContainingProcess(getParent());
   }

   public ModelType getModel()
   {
      if(getParent() == null)
      {
         return null;
      }
      return ModelUtils.findContainingModel(getParent());
   }

   public int getParentLevel()
   {
      return parentLevel;
   }
}
