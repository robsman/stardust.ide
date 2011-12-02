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
package org.eclipse.stardust.modeling.core.decoration;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;


/**
 * @author rsauer
 * @version $Revision$
 */
public interface IDecorationProvider
{
   String getId();

   IFigure createDecoration(INodeSymbol node);

   Locator createDecorationLocator(INodeSymbol node, IFigure nodeFigure,
         IFigure decorationFigure);

   IFigure createDecoration(IConnectionSymbol connection);

   ConnectionLocator createDecorationLocator(IConnectionSymbol connection,
         Connection connectionFigure, IFigure decorationFigure);
   
   void decorationRemoved(INodeSymbol node, IFigure figure);

   void decorationRemoved(IConnectionSymbol connection, IFigure figure);
}
