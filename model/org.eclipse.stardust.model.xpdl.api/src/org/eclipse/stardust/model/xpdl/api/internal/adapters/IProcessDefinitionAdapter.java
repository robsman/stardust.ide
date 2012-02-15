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
import java.util.List;
import java.util.Vector;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.engine.api.model.IActivity;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IDataPath;
import org.eclipse.stardust.engine.api.model.IEventConditionType;
import org.eclipse.stardust.engine.api.model.IEventHandler;
import org.eclipse.stardust.engine.api.model.IFormalParameter;
import org.eclipse.stardust.engine.api.model.IProcessDefinition;
import org.eclipse.stardust.engine.api.model.IReference;
import org.eclipse.stardust.engine.api.model.ITransition;
import org.eclipse.stardust.engine.api.model.ITrigger;
import org.eclipse.stardust.engine.api.model.ITriggerType;
import org.eclipse.stardust.engine.api.model.Inconsistency;
import org.eclipse.stardust.engine.core.model.utils.Hook;
import org.eclipse.stardust.engine.core.model.utils.ModelElement;
import org.eclipse.stardust.engine.core.model.utils.ModelElementList;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.diagram.Diagram;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class IProcessDefinitionAdapter extends AbstractIdentifiableModelElementAdapter
      implements IProcessDefinition
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ProcessDefinitionType)
               ? new IProcessDefinitionAdapter((ProcessDefinitionType) adaptee)
               : null;
      }
   };

   protected final ProcessDefinitionType pdDelegate;

   protected final EventHandlerOwnerAdapter ehoAdapter;

   public IProcessDefinitionAdapter(ProcessDefinitionType target)
   {
      super(target);

      this.pdDelegate = target;

      this.ehoAdapter = new EventHandlerOwnerAdapter(target);
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllActivities()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(pdDelegate.getActivity(), IActivityAdapter.FACTORY).iterator();
   }

   /**
    * @category IProcessDefinition
    */
   public IActivity findActivity(String id)
   {
      return (IActivity) ModelApiPlugin.getAdapterRegistry().getAdapter(
            pdDelegate.getActivity(), id, IActivityAdapter.FACTORY);
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllTransitions()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(pdDelegate.getTransition(), ITransitionAdapter.FACTORY)
            .iterator();
   }

   /**
    * @category IProcessDefinition
    */
   public ITransition findTransition(String id)
   {
      return (ITransition) ModelApiPlugin.getAdapterRegistry().getAdapter(
            pdDelegate.getTransition(), id, ITransitionAdapter.FACTORY);
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllTriggers()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(pdDelegate.getTrigger(), ITriggerAdapter.FACTORY).iterator();
   }

   /**
    * @category IProcessDefinition
    */
   public ITrigger findTrigger(String id)
   {
      return (ITrigger) ModelApiPlugin.getAdapterRegistry().getAdapter(
            pdDelegate.getTrigger(), id, ITriggerAdapter.FACTORY);
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllDataPaths()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(pdDelegate.getDataPath(), IDataPathAdapter.FACTORY).iterator();
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllInDataPaths()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(pdDelegate.getDataPath(), Predicates.IN_DATA_PATH,
                  IDataPathAdapter.FACTORY).iterator();
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllOutDataPaths()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(pdDelegate.getDataPath(), Predicates.OUT_DATA_PATH,
                  IDataPathAdapter.FACTORY).iterator();
   }

   /**
    * @category IProcessDefinition
    */
   public IDataPath findDataPath(String id, Direction direction)
   {
      return (IDataPath) ModelApiPlugin.getAdapterRegistry().getAdapter(
            pdDelegate.getDataPath(),
            id,
            AccessPointUtil.isIn(ConversionUtils.convert(direction))
                  ? Predicates.IN_DATA_PATH
                  : Predicates.OUT_DATA_PATH, IDataPathAdapter.FACTORY);
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllDescriptors()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(pdDelegate.getDataPath(), Predicates.IS_DESCRIPTOR,
                  IDataPathAdapter.FACTORY).iterator();
   }

   /**
    * @category IProcessDefinition
    */
   public IDataPath findDescriptor(String id)
   {
      IDataPath result = findDataPath(id, Direction.IN);
      return result.isDescriptor() ? result : null;
   }

   /**
    * @category EventHandlerOwner
    */
   public Iterator getAllEventHandlers()
   {
      return ehoAdapter.getAllEventHandlers();
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllEventHandlers(String type)
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category IProcessDefinition
    */
   public boolean hasEventHandlers(String type)
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category EventHandlerOwner
    */
   public IEventHandler findHandlerById(String id)
   {
      return ehoAdapter.findHandlerById(id);
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllDiagrams()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllImplementingActivities()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category IProcessDefinition
    */
   public IActivity getRootActivity()
   {
      return (IActivity) ModelApiPlugin.getAdapterRegistry().getAdapter(
            ModelUtils.findRootActivity(pdDelegate), IActivityAdapter.FACTORY);
   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllPossibleSubprocesses()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllSubProcesses()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category IProcessDefinition
    */
   public Iterator getAllSuperProcesses()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public boolean isConsistent()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void addToActivities(IActivity arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void addToDataPaths(IDataPath arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void addToSubProcesses(IProcessDefinition arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void addToTransitions(ITransition arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void addToTriggers(ITrigger arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public Vector checkConsistency()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public void checkConsistency(Vector arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public IActivity createActivity(String arg0, String arg1, String arg2, int arg3)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IDataPath createDataPath(String arg0, String arg1, IData arg2, String arg3,
         Direction arg4, int arg5)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public Diagram createDiagram(String arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public Diagram createDiagram(String arg0, int arg1)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public ITransition createTransition(String arg0, String arg1, String arg2,
         IActivity arg3, IActivity arg4)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public ITransition createTransition(String arg0, String arg1, String arg2,
         IActivity arg3, IActivity arg4, int arg5)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public ITrigger createTrigger(String arg0, String arg1, ITriggerType arg2, int arg3)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public Iterator getAllInstances()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultActivityId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultTransitionId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public void removeFromActivities(IActivity arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromDataPaths(IDataPath arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromDiagrams(Diagram arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromSubProcesses(IProcessDefinition arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromSuperProcesses(IProcessDefinition arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromTransitions(ITransition arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromTriggers(ITrigger arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void setDescription(String description)
   {
      // TODO Auto-generated method stub

   }

   public void delete()
   {
      // TODO Auto-generated method stub

   }

   public void addReference(Hook reference)
   {
      // TODO Auto-generated method stub

   }

   public void setParent(ModelElement parent)
   {
      // TODO Auto-generated method stub

   }

   public void removeReference(Hook reference)
   {
      // TODO Auto-generated method stub

   }

   public void register(int oid)
   {
      // TODO Auto-generated method stub

   }

   public void setElementOID(int elementOID)
   {
      // TODO Auto-generated method stub

   }

   public void setPredefined(boolean predefined)
   {
      // TODO Auto-generated method stub

   }

   public String getUniqueId()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public <T> T getRuntimeAttribute(String name)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Object setRuntimeAttribute(String name, Object value)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getEventHandlers()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void removeFromEventHandlers(IEventHandler handler)
   {
      // TODO Auto-generated method stub

   }

   public void addToEventHandlers(IEventHandler handler)
   {
      // TODO Auto-generated method stub

   }

   public IEventHandler createEventHandler(String id, String name, String description,
         IEventConditionType type, int elementOID)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void checkConsistency(List<Inconsistency> inconsistencies)
   {
      // TODO Auto-generated method stub

   }

   public ModelElementList<IActivity> getActivities()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getTransitions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getTriggers()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getDataPaths()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public int getDefaultPriority()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public void setDefaultPriority(int priority)
   {
      // TODO Auto-generated method stub

   }

   public List<IFormalParameter> getFormalParameters()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IFormalParameter findFormalParameter(String id)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean getDeclaresInterface()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public IData getMappedData(String parameterId)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getMappedDataId(String parameterId)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IReference getExternalReference()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
