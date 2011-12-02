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
/**
 * 
 */
package org.eclipse.stardust.modeling.transformation.debug.common;



public class SteppingManager
{
   private int levelCounter;
   private SteppingMode requestedMode;

   public SteppingManager()
   {
      super();
      reset();
   }

   public void reset()
   {
      if (requestedTermination())
      {
         return;
      }

      levelCounter = 0;
      requestedMode = SteppingMode.NONE;
   }
   
   public void recordEnter()
   {
      ++levelCounter;
   }
   
   public void recordExit()
   {
      --levelCounter;
   }
   
   public void setMode(SteppingMode mode)
   {
      if (requestedTermination())
      {
         return;
      }

      requestedMode = mode;
   }
   
   public final boolean requestedTermination()
   {
      return SteppingMode.TERMINATE == requestedMode;
   }
   
   public boolean canSuspend()
   {
      switch (requestedMode)
      {
         case BREAK:
         case STEP_INTO:
            return true;
         case STEP_OUT:
            if (levelCounter < 0)
            {
               return true;
            }
            break;
         case STEP_OVER:
            if (levelCounter <= 0)
            {
               return true;
            }
            break;
      }
      return false;
   }
}