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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.TextSymbolFigure;


public class TextSymbolEditPart extends AbstractNodeSymbolEditPart
{
   public TextSymbolEditPart(WorkflowModelEditor editor, TextSymbolType model)
   {
      super(editor, model);
   }

   protected EStructuralFeature getDirectEditFeature()
   {
      return CarnotWorkflowModelPackage.eINSTANCE.getTextSymbolType_Text();
   }

   protected IFigure createFigure()
   {
      IFigure figure = new TextSymbolFigure();
      INodeSymbol symbol = (INodeSymbol) getModel();
      figure.setBounds(new Rectangle(new Long(symbol.getXPos()).intValue(), 
            new Long(symbol.getYPos()).intValue(), 
            figure.getPreferredSize().width, 
            figure.getPreferredSize().height));
      
      return figure;
   }
   
   protected void refreshVisuals()
   {
      super.refreshVisuals();
      
      ((TextSymbolFigure) getFigure()).setText(getCastedModel().getText());
   }

   private TextSymbolType getCastedModel()
   {
      return (TextSymbolType) getModel();
   }
}