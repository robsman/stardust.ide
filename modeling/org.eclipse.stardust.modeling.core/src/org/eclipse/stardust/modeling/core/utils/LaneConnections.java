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
package org.eclipse.stardust.modeling.core.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;


public class LaneConnections
{
   // all editparts
   private LaneEditPart laneEP;
   private LaneFigure laneFigure;
   private boolean collapsed;
   private List childLaneEditParts = new ArrayList();
   private Set inConnections = new HashSet();
   private Set outConnections = new HashSet();   
   private Set innerConnections = new HashSet();
   
   public LaneConnections(LaneEditPart laneEP, boolean collapsed)
   {
      this.laneEP = laneEP;
      this.laneFigure = laneEP.getLaneFigure();
      this.collapsed = collapsed;
   }

   public void handleConnections()
   {     
      EditPart parentEP = laneEP.getParent();
      while(parentEP != null)
      {
         if(parentEP instanceof LaneEditPart)
         {
            LaneFigure figure = ((LaneEditPart) parentEP).getLaneFigure();
            if(figure.isCollapsed())
            {
               // should not be possible
               collapsed = true;
               break;
            }
         }
         parentEP = parentEP.getParent();
      }

      collectAll(laneEP, true);
      collectInner();
      viewHideConnections();
      
      for(int i = 0; i < childLaneEditParts.size(); i++)
      {
         LaneEditPart childLaneEP = (LaneEditPart) childLaneEditParts.get(i);
         LaneConnections laneConnections = new LaneConnections(childLaneEP, childLaneEP.getLaneFigure().isCollapsed());
         laneConnections.handleConnections();         
      }
   }
   
   public void collectAll(LaneEditPart laneEditPart, boolean collect)
   {
      List childrenEP = laneEditPart.getChildren();
      for (int i = 0; i < childrenEP.size(); i++)
      {
         AbstractNodeSymbolEditPart childEP = (AbstractNodeSymbolEditPart) childrenEP.get(i);
         if(childEP instanceof LaneEditPart)
         {
            if(!collapsed && collect)
            {
               childLaneEditParts.add(childEP);               
            }
            collectAll((LaneEditPart) childEP, false);     
         }
         if(childEP.getSourceConnections().size() > 0)
         {
            outConnections.addAll(childEP.getSourceConnections());               
         }
         if(childEP.getTargetConnections().size() > 0)
         {
            inConnections.addAll(childEP.getTargetConnections());               
         }
      } 
   }

   public void collectInner()
   {
      if(inConnections.size() > 0 && outConnections.size() > 0)
      {
         Iterator it = inConnections.iterator();
         while (it.hasNext()) 
         {         
            AbstractConnectionEditPart connectionEP = (AbstractConnectionEditPart) it.next();            
            if(outConnections.contains(connectionEP) && !innerConnections.contains(connectionEP))
            {
               innerConnections.add(connectionEP);
            }            
         }         
      }
      if(innerConnections.size() > 0)
      {
         Iterator it = innerConnections.iterator();
         while (it.hasNext()) 
         {         
            AbstractConnectionEditPart connectionEP = (AbstractConnectionEditPart) it.next();
            outConnections.remove(connectionEP);
            inConnections.remove(connectionEP);
         }
      }
   }   
   
   public void viewHideConnections()
   {
      if(innerConnections.size() > 0)
      {
         Iterator it = innerConnections.iterator();
         while (it.hasNext()) 
         {         
            AbstractConnectionEditPart connectionEP = (AbstractConnectionEditPart) it.next();
            PolylineConnection connectionFigure = (PolylineConnection) connectionEP.getFigure();
            
            // hide or show connection!
            if(collapsed)
            {
               connectionFigure.setVisible(false);
            }
            else
            {
               connectionFigure.setVisible(true);  
               ConnectionAnchor targetAnchor = (ConnectionAnchor) ((AbstractConnectionSymbolEditPart) connectionEP).getTargetConnectionAnchor();
               if(targetAnchor != null)
               {
                  connectionFigure.setTargetAnchor(targetAnchor);
               }
               ConnectionAnchor sourceAnchor = (ConnectionAnchor) ((AbstractConnectionSymbolEditPart) connectionEP).getSourceConnectionAnchor();
               if(sourceAnchor != null)
               {
                  connectionFigure.setSourceAnchor(sourceAnchor);
               }
            }            
         }
      }
      
      if(inConnections.size() > 0)
      {
         Iterator it = inConnections.iterator();
         while (it.hasNext()) 
         {         
            AbstractConnectionEditPart connectionEP = (AbstractConnectionEditPart) it.next();
            PolylineConnection connectionFigure = (PolylineConnection) connectionEP.getFigure();
            if(collapsed)
            {
               ChopboxAnchor anchor = new ChopboxAnchor(laneFigure);
               connectionFigure.setTargetAnchor(anchor);
            }
            else
            {
               ConnectionAnchor anchor = (ConnectionAnchor) ((AbstractConnectionSymbolEditPart) connectionEP).getTargetConnectionAnchor();
               if(anchor != null)
               {
                  connectionFigure.setTargetAnchor(anchor);
               }
            } 
         }
      }
         
      if(outConnections.size() > 0)
      {
         Iterator it = outConnections.iterator();
         while (it.hasNext()) 
         {         
            AbstractConnectionEditPart connectionEP = (AbstractConnectionEditPart) it.next();
            PolylineConnection connectionFigure = (PolylineConnection) connectionEP.getFigure();            
            
            if(collapsed)
            {
               ChopboxAnchor anchor = new ChopboxAnchor(laneFigure);
               connectionFigure.setSourceAnchor(anchor);
            }
            else
            {
               ConnectionAnchor anchor = (ConnectionAnchor) ((AbstractConnectionSymbolEditPart) connectionEP).getSourceConnectionAnchor();
               if(anchor != null)
               {
                  connectionFigure.setSourceAnchor(anchor);
               }
            } 
         }         
      }
   }
}