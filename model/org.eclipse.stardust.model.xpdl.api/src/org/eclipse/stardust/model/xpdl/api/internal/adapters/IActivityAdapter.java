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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.engine.api.model.IActivity;
import org.eclipse.stardust.engine.api.model.IApplication;
import org.eclipse.stardust.engine.api.model.IApplicationContext;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IDataMapping;
import org.eclipse.stardust.engine.api.model.IEventHandler;
import org.eclipse.stardust.engine.api.model.IModelParticipant;
import org.eclipse.stardust.engine.api.model.IProcessDefinition;
import org.eclipse.stardust.engine.api.model.ITransition;
import org.eclipse.stardust.engine.api.model.ImplementationType;
import org.eclipse.stardust.engine.api.model.LoopType;
import org.eclipse.stardust.engine.api.model.SubProcessModeKey;
import org.eclipse.stardust.model.spi.AccessPoint;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;


public class IActivityAdapter extends AbstractIdentifiableModelElementAdapter
      implements IActivity
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ActivityType) ? new IActivityAdapter(
               (ActivityType) adaptee) : null;
      }
   };

   protected ActivityType aAdapter;

   protected EventHandlerOwnerAdapter ehoAdapter;

   public IActivityAdapter(ActivityType delegate)
   {
      super(delegate);

      this.aAdapter = delegate;

      this.ehoAdapter = new EventHandlerOwnerAdapter(delegate);
   }

   /**
    * @category EventHandlerOwner
    */
   public Iterator getAllEventHandlers()
   {
      return ehoAdapter.getAllEventHandlers();
   }

   /**
    * @category EventHandlerOwner
    */
   public IEventHandler findHandlerById(String id)
   {
      return ehoAdapter.findHandlerById(id);
   }

   public Iterator getAllInTransitions()
   {
       List result = new LinkedList(); 
       
       for (Iterator i = aAdapter.getInTransitions().iterator(); i.hasNext(); ) {
           TransitionType transitionType = (TransitionType)i.next();
           result.add((ITransition) ModelApiPlugin.getAdapterRegistry().getAdapter(
                   transitionType, ITransitionAdapter.FACTORY));
       }
       
       return result.iterator();   
   }

   public Iterator getAllOutTransitions()
   {
       List result = new LinkedList(); 
       
       for (Iterator i = aAdapter.getOutTransitions().iterator(); i.hasNext(); ) {
           TransitionType transitionType = (TransitionType)i.next();
           result.add((ITransition) ModelApiPlugin.getAdapterRegistry().getAdapter(
                   transitionType, ITransitionAdapter.FACTORY));
       }
       
       return result.iterator(); 
   }

   public Iterator getAllDataMappings()
   {
       // TODO implement
      throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$   
   }

   public Iterator getAllInDataMappings()
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public Iterator getAllOutDataMappings()
   {
       List result = new LinkedList(); 
       
       for (Iterator i = aAdapter.getDataMapping().iterator(); i.hasNext(); ) {
           DataMappingType dataMappingType = (DataMappingType)i.next();
           if (dataMappingType.getDirection().equals(DirectionType.OUT_LITERAL) ||
                   dataMappingType.getDirection().equals(DirectionType.INOUT_LITERAL)) {
               result.add((IDataMapping) ModelApiPlugin.getAdapterRegistry().getAdapter(
                   dataMappingType, IDataMappingAdapter.FACTORY));
           }
       }
       
       return result.iterator(); 
   }

   public Iterator findDataMappings(IData data, Direction direction)
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public IDataMapping findDataMappingById(String id, Direction direction, String context)
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public Iterator findExceptionHandlers(IData data)
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public AccessPoint getAccessPoint(String context, String id)
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public Iterator getAllContexts()
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public IApplicationContext getContext(String id)
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public boolean hasEventHandlers(String type)
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public Iterator getAllEventHandlers(String type)
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public boolean getAllowsAbortByPerformer()
   {
       return aAdapter.isAllowsAbortByPerformer();
   }

   public IApplication getApplication()
   {
       return (IApplication) ModelApiPlugin.getAdapterRegistry().getAdapter(
               aAdapter.getApplication(), IApplicationAdapter.FACTORY);
   }

   public Set getApplicationOutDataMappingAccessPoints()
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
  }

   public IProcessDefinition getImplementationProcessDefinition()
   {
       return (IProcessDefinition) ModelApiPlugin.getAdapterRegistry().getAdapter(
               aAdapter.getImplementationProcess(), IProcessDefinitionAdapter.FACTORY);
   }

   public ImplementationType getImplementationType()
   {
       if (aAdapter.getImplementation().equals(ActivityImplementationType.ROUTE_LITERAL)) {
           return ImplementationType.Route;
       } else if (aAdapter.getImplementation().equals(ActivityImplementationType.APPLICATION_LITERAL)) {
           return ImplementationType.Application;
       } else if (aAdapter.getImplementation().equals(ActivityImplementationType.MANUAL_LITERAL)) {
           return ImplementationType.Manual;
       } else if (aAdapter.getImplementation().equals(ActivityImplementationType.SUBPROCESS_LITERAL)) {
           return ImplementationType.SubProcess;
       } else {
           throw new RuntimeException("Unexpected activity implementation type <"+aAdapter.getImplementation()+">"); //$NON-NLS-1$ //$NON-NLS-2$
       }
   }

   public org.eclipse.stardust.engine.api.model.JoinSplitType getJoinType()
   {
       if (aAdapter.getJoin().equals(JoinSplitType.AND_LITERAL)) {
           return org.eclipse.stardust.engine.api.model.JoinSplitType.And; 
       } else if (aAdapter.getJoin().equals(JoinSplitType.NONE_LITERAL)) {
           return org.eclipse.stardust.engine.api.model.JoinSplitType.None; 
       } else if (aAdapter.getJoin().equals(JoinSplitType.XOR_LITERAL)) {
           return org.eclipse.stardust.engine.api.model.JoinSplitType.Xor; 
       } else {
           throw new RuntimeException("Unexpected split type <"+aAdapter.getJoin()+">"); //$NON-NLS-1$ //$NON-NLS-2$
       }

   }

   public LoopType getLoopType()
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$
   }

   public String getLoopCondition()
   {
       return aAdapter.getLoopCondition();
   }

   public IModelParticipant getPerformer()
   {
       return (IModelParticipant) ModelApiPlugin.getAdapterRegistry().getAdapter(
               aAdapter.getPerformer(), AbstractModelParticipantAdapter.FACTORY);
   }

   public IProcessDefinition getProcessDefinition()
   {
       // TODO implement
       throw new RuntimeException("Not implemented yet");    //$NON-NLS-1$

   }

   public org.eclipse.stardust.engine.api.model.JoinSplitType getSplitType()
   {
       if (aAdapter.getSplit().equals(JoinSplitType.AND_LITERAL)) {
           return org.eclipse.stardust.engine.api.model.JoinSplitType.And; 
       } else if (aAdapter.getSplit().equals(JoinSplitType.NONE_LITERAL)) {
           return org.eclipse.stardust.engine.api.model.JoinSplitType.None; 
       } else if (aAdapter.getSplit().equals(JoinSplitType.XOR_LITERAL)) {
           return org.eclipse.stardust.engine.api.model.JoinSplitType.Xor; 
       } else {
           throw new RuntimeException("Unexpected split type <"+aAdapter.getSplit()+">"); //$NON-NLS-1$ //$NON-NLS-2$
       }
   }

   public SubProcessModeKey getSubProcessMode()
   {
      if (aAdapter.getSubProcessMode().equals(SubProcessModeType.ASYNC_SEPARATE_LITERAL)) {
          return SubProcessModeKey.ASYNC_SEPARATE;
      } else if (aAdapter.getSubProcessMode().equals(SubProcessModeType.SYNC_SEPARATE_LITERAL)) {
          return SubProcessModeKey.SYNC_SEPARATE;
      } else if (aAdapter.getSubProcessMode().equals(SubProcessModeType.SYNC_SHARED_LITERAL)) {
          return SubProcessModeKey.SYNC_SHARED;
      } else {
          throw new RuntimeException("Unexpected SubProcessModeKey <"+aAdapter.getSubProcessMode()+">"); //$NON-NLS-1$ //$NON-NLS-2$
      } 
   }

   public boolean isHibernateOnCreation()
   {
      return aAdapter.isHibernateOnCreation();
   }

   public boolean isInteractive()
   {
       if(getImplementationType().equals(ImplementationType.Manual)) {
           return true;
       } else if (getImplementationType().equals(ImplementationType.Application)) {
           return getApplication().isInteractive();
       }
       return false;
   }

public void addToDataMappings(IDataMapping arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public IDataMapping createDataMapping(String arg0, IData arg1, Direction arg2) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
}

public IDataMapping createDataMapping(String arg0, IData arg1, Direction arg2, String arg3, int arg4) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
}

public void removeAllDataMappings() {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void removeFromDataMappings(IDataMapping arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setAllowsAbortByPerformer(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setApplication(IApplication arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setHibernateOnCreation(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setImplementationProcessDefinition(IProcessDefinition arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setImplementationType(ImplementationType arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setJoinType(org.eclipse.stardust.engine.api.model.JoinSplitType arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setLoopCondition(String arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setLoopType(LoopType arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setPerformer(IModelParticipant arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setSplitType(org.eclipse.stardust.engine.api.model.JoinSplitType arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}

public void setSubProcessMode(SubProcessModeKey arg0) {
    // TODO implement this method!
    throw new RuntimeException("NIY"); //$NON-NLS-1$
    
}
}
