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
package org.eclipse.stardust.modeling.debug.debugger.types;

import java.text.MessageFormat;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.highlighting.IHighlightable;

import ag.carnot.workflow.model.IModel;
import ag.carnot.workflow.model.ITransition;
import ag.carnot.workflow.runtime.beans.TransitionTokenBean;

public class TransitionTokenDigest extends AbstractJavaTypeValue implements IHighlightable
{
   private long oid;
   private boolean isConsumed;
   private String procDefId;
   private String transitionId;
   
   public TransitionTokenDigest(IVariable variable)
   {
      super(variable);
      
      try
      {
         IVariable[] subVariables = getAssociatedVariable().getValue().getVariables();

         oid = DebugVariableUtils.extractAsLong("oid", subVariables); //$NON-NLS-1$
         isConsumed = DebugVariableUtils.extractAsBoolean("isConsumed", subVariables); //$NON-NLS-1$
         procDefId = DebugVariableUtils.extractAsString("procDefId", subVariables); //$NON-NLS-1$
         transitionId = DebugVariableUtils.extractAsString("transitionId", subVariables); //$NON-NLS-1$
      }
      catch (DebugException e)
      {
         throw new InternalException(Constants.EMPTY, e);
      }
   }
   
   public TransitionTokenDigest(TransitionTokenBean transitionToken)
   {
      super(null);
      
      Assert.isNotNull(transitionToken);
      
      oid = transitionToken.getOID(); 
      isConsumed = Boolean.valueOf(transitionToken.isConsumed()).booleanValue();

      ITransition transition = transitionToken.getTransition();
      IModel model = transition == null ? null : (IModel) transition.getModel();
      transitionId = transition == null ? null : "{" + model.getId() + '}' + transition.getId();
      procDefId = transition == null ? null : "{" + model.getId() + '}' + transition.getProcessDefinition().getId();
   }
   
   /**
    * @return Returns the isConsumed.
    */
   public boolean isConsumed()
   {
      return isConsumed;
   }
   
   /**
    * @return Returns the procDefId.
    */
   protected String getProcDefId()
   {
      return procDefId;
   }

   /**
    * @return Returns the transitionId.
    */
   public String getTransitionId()
   {
      return transitionId;
   }

   /**
    * @return Returns the transTokenOid.
    */
   public long getOid()
   {
      return oid;
   }

   public String getProcessDefinitionChildId()
   {
      return getTransitionId();
   }

   public String getProcessDefinitionId()
   {
      return getProcDefId();
   }
   
   public String toString()
   {
      return MessageFormat.format(Debug_Messages.MSG_TransitionTokenDigest_ToString,
            new Object[] { new Long(getOid()), getTransitionId() });
   }
   
   public boolean equals(Object other)
   {
      boolean isEqual;

      if (this == other)
      {
         isEqual = true;
      }
      else if (!(other instanceof IHighlightable))
      {
         isEqual = false;
      }
      else
      {
         final IHighlightable tmp = (IHighlightable) other;

         isEqual = new Highlightable(getProcessDefinitionId(), getProcessDefinitionChildId()).equals(
               new Highlightable(tmp.getProcessDefinitionId(), tmp.getProcessDefinitionChildId()));
      }

      return isEqual;
   }
   
   public int hashCode()
   {
      return new Highlightable(getProcessDefinitionId(), getProcessDefinitionChildId()).hashCode();
   }
}
