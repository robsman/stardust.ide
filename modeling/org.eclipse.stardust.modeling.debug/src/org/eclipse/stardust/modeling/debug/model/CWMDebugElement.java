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
package org.eclipse.stardust.modeling.debug.model;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.stardust.modeling.debug.Constants;


public abstract class CWMDebugElement extends PlatformObject implements IDebugElement 
{
   protected CWMDebugTarget target;
   
   /**
    * Fires a debug event
    * 
    * @param event the event to be fired
    */
   protected void fireEvent(DebugEvent event)
   {
      DebugPlugin.getDefault().fireDebugEventSet(new DebugEvent[] { event });
   }

   /**
    * Fires a <code>CREATE</code> event for this element.
    */
   protected void fireCreationEvent()
   {
      fireEvent(new DebugEvent(this, DebugEvent.CREATE));
   }

   /**
    * Fires a <code>TERMINATE</code> event for this element.
    */
   protected void fireTerminateEvent()
   {
      fireEvent(new DebugEvent(this, DebugEvent.TERMINATE));
   }

   public CWMDebugElement(CWMDebugTarget target)
   {
      this.target = target;
   }
   
   public String getSourceName()
   {
      return ((CWMDebugTarget) getDebugTarget()).getSourceName();
   }
   
   public void setSourceName(String sourceName)
   {
      ((CWMDebugTarget) getDebugTarget()).setSourceName(sourceName);
   }
   
   public String getModelIdentifier()
   {
      return Constants.ID_CWM_DEBUG_MODEL;
   }

   public IDebugTarget getDebugTarget()
   {
      return target;
   }

   public ILaunch getLaunch()
   {
      return getDebugTarget().getLaunch();
   }

   public Object getAdapter(Class adapter)
   {
      if (adapter == IDebugElement.class)
      {
         return this;
      }
      
      return super.getAdapter(adapter);
   }

   /**
    * Fires a <code>RESUME</code> event for this element with
    * the given detail.
    * 
    * @param detail event detail code
    */
   public void fireResumeEvent(int detail)
   {
      fireEvent(new DebugEvent(this, DebugEvent.RESUME, detail));
   }

   /**
    * Fires a <code>SUSPEND</code> event for this element with
    * the given detail.
    * 
    * @param detail event detail code
    */
   public void fireSuspendEvent(int detail)
   {
      fireEvent(new DebugEvent(this, DebugEvent.SUSPEND, detail));
   }

   /**
    * Fires a <code>CHANGE</code> event for this element with
    * the given detail.
    * 
    * @param detail event detail code
    */
   public void fireChangeEvent(int detail)
   {
      fireEvent(new DebugEvent(this, DebugEvent.SUSPEND, detail));
   }}
