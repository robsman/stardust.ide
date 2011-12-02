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

import org.eclipse.gef.EditDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;


public class OrientationAction extends Action
{
   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;
   
   private OrientationType orientationType;
   private DiagramType diagram;
   private EditDomain domain;   

   public OrientationAction(OrientationType orientationType, DiagramType diagram, EditDomain domain)
   {
      super(orientationType.getName());
      this.orientationType = orientationType;
      this.diagram = diagram;      
      this.domain = domain;
   }
   public void run()
   {
      domain.getCommandStack().execute(new SetValueCmd(diagram, CWM_PKG
            .getDiagramType_Orientation(), orientationType));
   }

   public OrientationType getOrientationType()
   {
      return orientationType;
   }
}
