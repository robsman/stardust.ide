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
package org.eclipse.stardust.modeling.diagramexport.servlet;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

import ag.carnot.base.StringUtils;

public class DiagramLocator
{
   private static DiagramType findDiagramByName(List diagrams, String diagramName)
   {
      DiagramType diagram = null;

      if (StringUtils.isEmpty(diagramName) && (null != diagrams && !diagrams.isEmpty()))
      {
         diagram = (DiagramType) diagrams.get(0);
      }
      else
      {
         diagram = DiagramUtil.findDiagramByName(diagrams, diagramName);
      }

      return diagram;
   }
   
   public static DiagramType findDiagram(ModelType model, String processDefinitionId,
         String diagramName)
   {
      if (StringUtils.isEmpty(processDefinitionId))
      {
         return findDiagramByName(model.getDiagram(), diagramName);
      }
      else
      {
         ProcessDefinitionType procDef = (ProcessDefinitionType) ModelUtils
               .findIdentifiableElement(model.getProcessDefinition(), processDefinitionId);

         if (null != procDef)
         {
            return findDiagramByName(procDef.getDiagram(), diagramName);
         }
      }

      return null;
   }
}
