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
package org.eclipse.stardust.modeling.core.editors.parts.diagram;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.modeling.core.editors.IDiagramEditorConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ReloadConnectionsAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.ReloadConnectionCommandFactory;
import org.eclipse.stardust.modeling.core.editors.tools.AutoexposeMarqueeDragTracker;
import org.eclipse.stardust.modeling.core.editors.tools.DiagramViewportAutoexposeHelper;


/**
 * @author rsauer
 * @version $Revision$
 */
public class DiagramRootEditPart extends ScalableFreeformRootEditPart
{

   protected GridLayer createGridLayer()
   {
      return new DiagramGridLayer();
   }

   protected LayeredPane createPrintableLayers()
   {
      LayeredPane layeredPane = super.createPrintableLayers();

      FreeformLayer decorationLayer = new FreeformLayer();
      decorationLayer.setLayoutManager(new DelegatingLayout());

      layeredPane.addLayerAfter(decorationLayer,
            IDiagramEditorConstants.DECORATION_LAYER, LayerConstants.CONNECTION_LAYER);

      return layeredPane;
   }

   public Object getAdapter(Class adapter)
   {
      if (adapter == AutoexposeHelper.class)
      {
         return new DiagramViewportAutoexposeHelper(this);
      }
      if (adapter == IModelElement.class)
      {
         return getContents().getModel();
      }
      return super.getAdapter(adapter);
   }

   public DragTracker getDragTracker(Request request)
   {
      final EditPart editPart = this;
      return new AutoexposeMarqueeDragTracker()
      {
         protected boolean handleButtonDown(int button)
         {
            getViewer().select(editPart);
            return super.handleButtonDown(button);
         }
      };
   }

   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy()
      {
         public boolean understandsRequest(Request req)
         {
            if (ReloadConnectionsAction.REQ_RELOAD_CONNECTIONS.equals(req.getType()))
            {
               return true;
            }
            return super.understandsRequest(req);
         }
      });
   }

   public Command getCommand(Request req)
   {
      if (ReloadConnectionsAction.REQ_RELOAD_CONNECTIONS.equals(req.getType()))
      {
         return getReloadConnectionsCommand();
      }
      return super.getCommand(req);
   }

   private Command getReloadConnectionsCommand()
   {
      Command command = UnexecutableCommand.INSTANCE;
      DiagramType diagram = (DiagramType) (getContents().getModel() instanceof DiagramType
            ? getContents().getModel()
            : null);
      if (diagram != null)
      {
         List symbols = getAllSymbols(diagram);
         command = ReloadConnectionCommandFactory.INSTANCE
               .createReloadConnectionCmd(symbols);
      }
      return command;
   }

   private List getAllSymbols(DiagramType diagram)
   {
      List allSymbols = new ArrayList();
      for (Iterator iter = diagram.getNodes().valueListIterator(); iter.hasNext();)
      {
         INodeSymbol symbol = (INodeSymbol) iter.next();
         allSymbols.add(symbol);
      }
      for (Iterator iter = diagram.getPoolSymbols().iterator(); iter.hasNext();)
      {
         PoolSymbol pool = (PoolSymbol) iter.next();
         for (Iterator iterator = pool.getNodes().valueListIterator(); iterator.hasNext();)
         {
            INodeSymbol symbol = (INodeSymbol) iterator.next();
            allSymbols.add(symbol);
         }
         for (Iterator iterator = pool.getLanes().iterator(); iterator.hasNext();)
         {
            LaneSymbol lane = (LaneSymbol) iterator.next();
            for (Iterator iter2 = lane.getNodes().valueListIterator(); iter2.hasNext();)
            {
               INodeSymbol symbol = (INodeSymbol) iter2.next();
               allSymbols.add(symbol);
            }
         }
      }
      return allSymbols;
   }
}
