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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractConnectionSymbolFigure;


public class TriggersConnectionEditPart extends AbstractConnectionSymbolEditPart
{
   protected TriggersConnectionEditPart(TriggersConnectionType model)
   {
      super(model);
   }

   protected IFigure createFigure()
   {
      AbstractConnectionSymbolFigure pLine = (AbstractConnectionSymbolFigure) super.createFigure();

      pLine.setDefaultBorderColor(ColorConstants.lightGray);
      pLine.setDefaultFillColor(ColorConstants.lightGray);
      pLine.setSourceDecoration(null);
      pLine.setTargetDecoration(null);

      return pLine;
   }
}