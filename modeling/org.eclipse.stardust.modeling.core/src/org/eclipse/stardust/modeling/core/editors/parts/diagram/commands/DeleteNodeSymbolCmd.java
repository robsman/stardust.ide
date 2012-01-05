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

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;


public class DeleteNodeSymbolCmd extends DeleteGraphicalObjectCmd
{
   private CompoundCommand connectionCommands;

   private List laneContainmentsBackup;

   private IIdentifiableModelElement nodeModelBackup;

   public DeleteNodeSymbolCmd(INodeSymbol symbol)
   {
      super(symbol);
   }

   public void execute()
   {
      createBackup();

      connectionCommands.execute();

      for (Iterator i = laneContainmentsBackup.iterator(); i.hasNext();)
      {
         CommandUtils.undoContainment((CommandUtils.ContainmentState) i.next());
      }

      if (getCastedModel() instanceof IModelElementNodeSymbol)
      {
         ((IModelElementNodeSymbol) getCastedModel()).setModelElement(null);
      }

      super.execute();
   }

   public void redo()
   {
      connectionCommands.redo();

      for (Iterator i = laneContainmentsBackup.iterator(); i.hasNext();)
      {
         CommandUtils.undoContainment((CommandUtils.ContainmentState) i.next());
      }

      if (getCastedModel() instanceof IModelElementNodeSymbol)
      {
         ((IModelElementNodeSymbol) getCastedModel()).setModelElement(null);
      }

      super.redo();
   }

   public void undo()
   {

      if ((getCastedModel() instanceof IModelElementNodeSymbol)
            && (null != nodeModelBackup))
      {
         ((IModelElementNodeSymbol) getCastedModel()).setModelElement(nodeModelBackup);
      }

      super.undo();

      for (Iterator i = laneContainmentsBackup.iterator(); i.hasNext();)
      {
         CommandUtils.redoContainment((CommandUtils.ContainmentState) i.next());
      }
      laneContainmentsBackup.clear();

      connectionCommands.undo();
   }

   private void createBackup()
   {
      connectionCommands = new CompoundCommand();

      Set connectionSet = new HashSet();

      if (!(getCastedModel() instanceof PoolSymbol)
            && (!(getCastedModel() instanceof LaneSymbol)))
      {
         for (Iterator i = getCastedModel().getInConnectionFeatures().iterator(); i
               .hasNext();)
         {
            EReference conFtr = (EReference) i.next();
            Object conValue = getCastedModel().eGet(conFtr);
            if (conValue instanceof EList)
            {
               for (Iterator j = ((EList) conValue).iterator(); j.hasNext();)
               {
                  addConnectionDeleteCmd(connectionSet, (IConnectionSymbol) j.next());
               }
            }
            else if (conValue != null)
            {
               addConnectionDeleteCmd(connectionSet, (IConnectionSymbol) conValue);
            }
         }

         for (Iterator i = getCastedModel().getOutConnectionFeatures().iterator(); i
               .hasNext();)
         {
            EReference conFtr = (EReference) i.next();
            Object conValue = getCastedModel().eGet(conFtr);
            if (conValue instanceof EList)
            {
               for (Iterator j = ((EList) conValue).iterator(); j.hasNext();)
               {
                  addConnectionDeleteCmd(connectionSet, (IConnectionSymbol) j.next());
               }
            }
            else if (conValue != null)
            {
               addConnectionDeleteCmd(connectionSet, (IConnectionSymbol) conValue);
            }
         }
      }

      for (Iterator i = getCastedModel().getReferingToConnections().iterator(); i
            .hasNext();)
      {
         RefersToConnectionType refersToConn = (RefersToConnectionType) i.next();
         addConnectionDeleteCmd(connectionSet, refersToConn);
      }
      for (Iterator i = getCastedModel().getInLinks().iterator(); i.hasNext();)
      {
         GenericLinkConnectionType linkConn = (GenericLinkConnectionType) i.next();
         addConnectionDeleteCmd(connectionSet, linkConn);
      }
      for (Iterator i = getCastedModel().getOutLinks().iterator(); i.hasNext();)
      {
         GenericLinkConnectionType linkConn = (GenericLinkConnectionType) i.next();
         addConnectionDeleteCmd(connectionSet, linkConn);
      }

      laneContainmentsBackup = Collections.EMPTY_LIST;
      /*
       * TODO laneContainmentsBackup = new ArrayList(getCastedModel().getLanes().size());
       * for (Iterator i = getCastedModel().getLanes().iterator(); i.hasNext();) {
       * LaneSymbol lane = (LaneSymbol) i.next();
       * laneContainmentsBackup.add(CommandUtils.backupContainment(getCastedModel(),
       * lane.getLaneComponents())); }
       */

      if (getCastedModel() instanceof IModelElementNodeSymbol)
      {
         this.nodeModelBackup = ((IModelElementNodeSymbol) getCastedModel())
               .getModelElement();
      }
   }

   private INodeSymbol getCastedModel()
   {
      return (INodeSymbol) getTarget();
   }

   private void addConnectionDeleteCmd(Set connectionSet, IConnectionSymbol connection)
   {
      if (!connectionSet.contains(connection))
      {
         connectionCommands.add(new DeleteConnectionSymbolCmd(connection));
         connectionSet.add(connection);
      }
   }
}