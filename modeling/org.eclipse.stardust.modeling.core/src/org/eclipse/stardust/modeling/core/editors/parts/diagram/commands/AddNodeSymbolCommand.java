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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class AddNodeSymbolCommand extends Command
{
   private ISymbolContainer diagram;
   private INodeSymbol nodeSymbol;

   private IModelElement nodeModel;
   private EObject modelContainer;
   private EStructuralFeature modelContainmentFeature;

   private EStructuralFeature symbolContainmentFeature;

   private Long modelElementOidBackup;
   private Long symbolElementOidBackup;

   public void setDiagram(ISymbolContainer targetLane)
   {
      this.diagram = targetLane;
   }

   public void setNodeSymbol(INodeSymbol flowObject, EStructuralFeature containmentFeature)
   {
      this.nodeSymbol = flowObject;
      this.symbolContainmentFeature = containmentFeature;
   }

   public void setNodeModel(IModelElement nodeModel, EObject container,
         EStructuralFeature containmentFeature)
   {
      this.nodeModel = nodeModel;
      this.modelContainer = container;
      this.modelContainmentFeature = containmentFeature;
   }

   public void execute()
   {
      if (null != nodeModel)
      {
         this.modelElementOidBackup = nodeModel.isSetElementOid() ? new Long(
               nodeModel.getElementOid()) : null;
      }
      this.symbolElementOidBackup = nodeSymbol.isSetElementOid() ? new Long(
            nodeSymbol.getElementOid()) : null;

      redo();
   }

   public void redo()
   {
      if (null != nodeSymbol)
      {
         if (null != nodeModel)
         {
            if ((null == modelContainer) || (null == modelContainmentFeature))
            {
               throw new RuntimeException(Diagram_Messages.EX_MissingNodeModelContainer + nodeModel);
            }

            EList modelContainment = (EList) modelContainer.eGet(modelContainmentFeature);
            modelContainment.add(nodeModel);
         }

         if ((null == diagram) || (null == symbolContainmentFeature))
         {
            throw new RuntimeException(Diagram_Messages.EX_MissingNodeSymbolContainer + nodeSymbol);
         }

         EList symbolContainment = (EList) diagram.eGet(symbolContainmentFeature);
         symbolContainment.add(nodeSymbol);
      }
   }

   public void undo()
   {
      if (null != nodeSymbol)
      {
         if ((null == diagram) || (null == symbolContainmentFeature))
         {
            throw new RuntimeException(Diagram_Messages.EX_MissingNodeSymbolContainer + nodeSymbol);
         }

         EList symbolContainment = (EList) diagram.eGet(symbolContainmentFeature);
         symbolContainment.remove(nodeSymbol);
         if (null != symbolElementOidBackup)
         {
            nodeSymbol.setElementOid(symbolElementOidBackup.longValue());
         }
         else
         {
            nodeSymbol.unsetElementOid();
         }

         if (null != nodeModel)
         {
            if ((null == modelContainer) || (null == modelContainmentFeature))
            {
               throw new RuntimeException(Diagram_Messages.EX_MissingNodeModelContainer + nodeModel);
            }

            EList modelContainment = (EList) modelContainer.eGet(modelContainmentFeature);
            modelContainment.remove(nodeModel);
            if (null != modelElementOidBackup)
            {
               nodeModel.setElementOid(modelElementOidBackup.longValue());
            }
            else
            {
               nodeModel.unsetElementOid();
            }
         }
      }
   }
}