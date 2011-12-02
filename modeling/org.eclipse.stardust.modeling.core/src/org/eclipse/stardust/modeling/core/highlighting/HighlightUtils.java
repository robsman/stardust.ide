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
package org.eclipse.stardust.modeling.core.highlighting;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.IHighliteableGraphicalObject;


public class HighlightUtils
{
   public static class GraphicalObjectHighlighter implements Runnable
   {
      private final IHighliteableGraphicalObject graphicalObject;
      private final HighlightState state;
      private final IColorFactory colorFactory;
      private RuntimeException exception;
      
      public GraphicalObjectHighlighter(IHighliteableGraphicalObject graphicalObject,
            HighlightState state)
      {
         this(graphicalObject, state, null);
      }
   
      public GraphicalObjectHighlighter(IHighliteableGraphicalObject graphicalObject,
            HighlightState state, IColorFactory colorFactory)
      {
         super();
         this.graphicalObject = graphicalObject;
         this.state = state;
         
         if (null == colorFactory)
         {
            this.colorFactory = new ColorFactory(ColorConstants.black);
         }
         else
         {
            this.colorFactory = colorFactory;
         }
      }
   
      public void run()
      {
         try
         {
            switch (state.getValue())
            {
               case HighlightState.DEFAULT:
                  graphicalObject.resetHighliteBorderColor();
                  break;

               case HighlightState.BROKEN:
                  graphicalObject.setHighliteBorderColor(colorFactory.createColor(state));
                  break;

               case HighlightState.ACTIVE:
                  graphicalObject.setHighliteBorderColor(colorFactory.createColor(state));
                  break;

               case HighlightState.SELECTED:
                  graphicalObject.setHighliteBorderColor(colorFactory.createColor(state));
                  break;

               case HighlightState.DONE:
                  graphicalObject.setHighliteBorderColor(colorFactory.createColor(state));
                  break;

               default:
                  break;
            }
         }
         catch (RuntimeException e)
         {
            exception = e;
         }
      }
      
      public RuntimeException getException()
      {
         return exception;
      }
   }
}
