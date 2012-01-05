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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TextType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.AnnotationSymbolFigure;


public class AnnotationSymbolEditPart extends AbstractNodeSymbolEditPart
{
   public AnnotationSymbolEditPart(WorkflowModelEditor editor, AnnotationSymbolType model)
   {
      super(editor, model);
   }

   protected EStructuralFeature getDirectEditFeature()
   {
      return CarnotWorkflowModelPackage.eINSTANCE.getAnnotationSymbolType_Text();
   }

   protected IFigure createFigure()
   {
      AnnotationSymbolFigure textFigure = new AnnotationSymbolFigure();
      INodeSymbol symbol = (INodeSymbol) getModel();
      textFigure.setBounds(new Rectangle(new Long(symbol.getXPos()).intValue(), 
            new Long(symbol.getYPos()).intValue(), 
            textFigure.getPreferredSize().width, 
            textFigure.getPreferredSize().height));
            
      return textFigure;
   }

   protected List getModelSourceConnections()
   {
      List result = Collections.EMPTY_LIST;

      if ((null != getCastedModel())
            && !getCastedModel().getReferingFromConnections().isEmpty())
      {
         result = new ArrayList(getCastedModel().getReferingFromConnections().size());

         // Workaround (rsauer): transiently filtering out refers to associations to connections
         for (Iterator i = getCastedModel().getReferingFromConnections().iterator(); i.hasNext();)
         {
            RefersToConnectionType connection = (RefersToConnectionType) i.next();
            if ((null == connection.getTo())
                  || (connection.getTo() instanceof INodeSymbol))
            {
               result.add(connection);
            }
         }
      }
      return result;
   }

   protected List getModelTargetConnections()
   {
      List result = Collections.EMPTY_LIST;

      if ((null != getCastedModel())
            && !getCastedModel().getReferingToConnections().isEmpty())
      {
         result = new ArrayList(getCastedModel().getReferingToConnections().size());

         // Workaround(rsauer): transiently filtering out refers to associations to connections
         for (Iterator i = getCastedModel().getReferingToConnections().iterator(); i.hasNext();)
         {
            RefersToConnectionType connection = (RefersToConnectionType) i.next();
            if ((null == connection.getFrom())
                  || (connection.getFrom() instanceof INodeSymbol))
            {
               result.add(connection);
            }
         }
      }
      return result;
   }

   protected void refreshVisuals()
   {
      super.refreshVisuals();

      final TextType text = getCastedModel().getText();
      ((AnnotationSymbolFigure) getFigure()).setText(text == null
            ? "" : ModelUtils.getCDataString(text.getMixed())); //$NON-NLS-1$
   }

   private AnnotationSymbolType getCastedModel()
   {
      return (AnnotationSymbolType) getModel();
   }
}
