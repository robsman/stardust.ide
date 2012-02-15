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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import java.util.Vector;

import org.eclipse.stardust.engine.api.model.IActivity;
import org.eclipse.stardust.engine.api.model.IProcessDefinition;
import org.eclipse.stardust.engine.api.model.ITransition;
import org.eclipse.stardust.engine.core.model.utils.ModelElement;
import org.eclipse.stardust.engine.core.runtime.beans.IProcessInstance;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class ITransitionAdapter extends AbstractIdentifiableModelElementAdapter
      implements ITransition
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof TransitionType) ? new ITransitionAdapter(
               (TransitionType) adaptee) : null;
      }
   };

   protected final TransitionType tDelegate;

   protected final AbstractConnectionAdapter cAdapter;

   public ITransitionAdapter(TransitionType delegate)
   {
      super(delegate);

      this.tDelegate = delegate;

      // TODO
      this.cAdapter = new AbstractConnectionAdapter(null);
   }

   /**
    * @category Connection
    */
   public ModelElement getFirst()
   {
      return cAdapter.getFirst();
   }

   /**
    * @category Connection
    */
   public ModelElement getSecond()
   {
      return cAdapter.getSecond();
   }

   /**
    * @category ITransition
    */
   public String getCondition()
   {
      XmlTextNode expression = tDelegate.getExpression();
      return (null != expression)
            ? ModelUtils.getCDataString(expression.getMixed())
            : tDelegate.getCondition();
   }

   /**
    * @category ITransition
    */
   public boolean getForkOnTraversal()
   {
      return tDelegate.isForkOnTraversal();
   }

   /**
    * @category ITransition
    */
   public IProcessDefinition getProcessDefinition()
   {
      return (IProcessDefinition) ModelApiPlugin.getAdapterRegistry()
            .getAdapter(ModelUtils.findContainingProcess(tDelegate),
                  IProcessDefinitionAdapter.FACTORY);
   }

   /**
    * @category ITransition
    */
   public IActivity getFromActivity()
   {
      return (IActivity) ModelApiPlugin.getAdapterRegistry().getAdapter(
            tDelegate.getFrom(), IActivityAdapter.FACTORY);
   }

   /**
    * @category ITransition
    */
   public IActivity getToActivity()
   {
      return (IActivity) ModelApiPlugin.getAdapterRegistry().getAdapter(
            tDelegate.getTo(), IActivityAdapter.FACTORY);
   }

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public boolean isEnabled(IProcessInstance arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public boolean isOtherwiseEnabled(IProcessInstance arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public void setCondition(String arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setForkOnTraversal(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
