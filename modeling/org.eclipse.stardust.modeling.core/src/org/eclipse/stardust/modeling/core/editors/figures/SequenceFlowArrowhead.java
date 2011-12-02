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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;

/**
 * @author rsauer
 * @version $Revision$
 */
public class SequenceFlowArrowhead extends PolygonDecoration
{
   /**
    *  
    */
   public SequenceFlowArrowhead()
   {
      super();
      
      setScale(12, 4);

      setFill(true);
      setBackgroundColor(ColorConstants.black);

      setOutline(false);
   }
}