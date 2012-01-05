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

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.types.DataField;

public class CWMVariable extends CWMDebugElement implements IVariable
{
   private DataField dataField = null;
   private CWMStackFrame frame;
   private boolean hasChanged = false;
   
   public CWMVariable(CWMStackFrame frame, DataField dataField)
   {
      super((CWMDebugTarget) frame.getDebugTarget());
      this.frame = frame;
      
      Assert.isNotNull(dataField, Debug_Messages.ASSERT_ValueCarrierMayNotBeNull);
      this.dataField = dataField;
   }
   
   public DataField getDataField()
   {
      return dataField;
   }

   public IValue getValue() throws DebugException
   {
      return dataField.getWritebackVariable().getValue();
      //return new CWMValue(this, dataField);
   }

   public String getName() throws DebugException
   {
      return dataField.getName();
   }

   public String getReferenceTypeName() throws DebugException
   {
      return dataField.getTypeName();
   }

   public boolean hasValueChanged() throws DebugException
   {
      return hasChanged;
   }

   public void setValue(String expression) throws DebugException
   {
      if (verifyValue(expression))
      {
         dataField.setValue(expression);
         hasChanged = true;
         fireChangeEvent(DebugEvent.CONTENT);
         
         /* TODO: mark thread that resume only will reload data an suspend again
         CWMThread thread = (CWMThread)getStackFrame().getThread();
         if (null != thread)
         {
            IVariable variable = thread.getReloadValuesVariable();
            if (null != variable)
            {
               variable.setValue(Boolean.TRUE.toString());
               thread.resume();
            }
         }
         */
      }
   }

   public void setValue(IValue value) throws DebugException
   {
      if (verifyValue(value))
      {
         setValue(value.getValueString());
      }
   }

   public boolean supportsValueModification()
   {
      return dataField.supportsValueModification();
   }

   public boolean verifyValue(String expression) throws DebugException
   {
      // TODO Auto-generated method stub
      return true; // default: false;
   }

   public boolean verifyValue(IValue value) throws DebugException
   {
      // TODO Auto-generated method stub
      return true; // default: false;
   }
   
   /**
    * Returns the stack frame owning this variable.
    * 
    * @return the stack frame owning this variable
    */
   protected CWMStackFrame getStackFrame()
   {
      return frame;
   }
}
