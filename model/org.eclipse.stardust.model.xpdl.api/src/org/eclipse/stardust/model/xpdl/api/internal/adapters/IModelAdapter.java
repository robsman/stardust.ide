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
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.eclipse.stardust.common.FilteringIterator;
import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.common.SplicingIterator;
import org.eclipse.stardust.common.config.Version;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.api.model.CardinalityKey;
import org.eclipse.stardust.engine.api.model.EventType;
import org.eclipse.stardust.engine.api.model.IApplication;
import org.eclipse.stardust.engine.api.model.IApplicationContextType;
import org.eclipse.stardust.engine.api.model.IApplicationType;
import org.eclipse.stardust.engine.api.model.IConditionalPerformer;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IDataType;
import org.eclipse.stardust.engine.api.model.IEventActionType;
import org.eclipse.stardust.engine.api.model.IEventConditionType;
import org.eclipse.stardust.engine.api.model.IExternalPackage;
import org.eclipse.stardust.engine.api.model.ILinkType;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.api.model.IModelParticipant;
import org.eclipse.stardust.engine.api.model.IModeler;
import org.eclipse.stardust.engine.api.model.IOrganization;
import org.eclipse.stardust.engine.api.model.IProcessDefinition;
import org.eclipse.stardust.engine.api.model.IRole;
import org.eclipse.stardust.engine.api.model.ITriggerType;
import org.eclipse.stardust.engine.api.model.ITypeDeclaration;
import org.eclipse.stardust.engine.api.model.IView;
import org.eclipse.stardust.engine.api.model.IXpdlType;
import org.eclipse.stardust.engine.api.model.Scripting;
import org.eclipse.stardust.engine.core.model.utils.Differences;
import org.eclipse.stardust.engine.core.model.utils.ModelElement;
import org.eclipse.stardust.engine.core.model.utils.ModelElementList;
import org.eclipse.stardust.engine.core.model.utils.ModelListener;
import org.eclipse.stardust.engine.core.model.utils.RootElement;
import org.eclipse.stardust.engine.core.preferences.configurationvariables.IConfigurationVariableDefinition;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.diagram.ArrowKey;
import org.eclipse.stardust.model.diagram.ColorKey;
import org.eclipse.stardust.model.diagram.Diagram;
import org.eclipse.stardust.model.diagram.LineKey;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelParticipantUtils;


public class IModelAdapter extends AbstractModelAdapter implements IModel
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ModelType)
               ? new IModelAdapter((ModelType) adaptee)
               : null;
      }
   };

   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   protected final ModelType mDelegate;

   public IModelAdapter(ModelType delegate)
   {
      super(delegate);

      this.mDelegate = delegate;
   }

   /**
    * @category RootElement
    */
   public int getModelOID()
   {
      return mDelegate.getModelOID();
   }

   /**
    * @category RootElement
    */
   public ModelElement lookupElement(int elementOID)
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category SymbolTable
    */
   public Object lookupSymbol(String name)
   {
      // TODO taken from ModelBean, weird
      if (null == findData(name))
      {
         throw new PublicException(API_Messages.MSG_InvalidSym + " '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
      }

      return null;
   }

   /**
    * @category SymbolTable
    */
   public IData lookupSymbolType(String name)
   {
      IData data = findData(name);

      if (null == data)
      {
         throw new PublicException(API_Messages.MSG_InvalidSym + " '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
      }

      return data;
   }

   /**
    * @category IModel
    */
   public int getApplicationsCount()
   {
      return mDelegate.getApplication().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllApplications()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(mDelegate, PKG_CWM.getModelType_Application(),
                  IApplicationAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public IApplication findApplication(String id)
   {
      return (IApplication) ModelApiPlugin.getAdapterRegistry().getAdapter(mDelegate,
            PKG_CWM.getModelType_Application(), id, IApplicationAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public int getDataCount()
   {
      return mDelegate.getData().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllData()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate, PKG_CWM.getModelType_Data(), IDataAdapter.FACTORY)
            .iterator();
   }

   /**
    * @category IModel
    */
   public IData findData(String id)
   {
      return (IData) ModelApiPlugin.getAdapterRegistry().getAdapter(mDelegate,
            PKG_CWM.getModelType_Data(), id, IDataAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public int getModelersCount()
   {
      return mDelegate.getModeler().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllModelers()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(mDelegate, PKG_CWM.getModelType_Modeler(),
                  IModelerAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public int getOrganizationsCount()
   {
      return mDelegate.getOrganization().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllOrganizations()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(mDelegate, PKG_CWM.getModelType_Organization(),
                  IOrganizationAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public int getRolesCount()
   {
      return mDelegate.getRole().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllRoles()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate, PKG_CWM.getModelType_Role(), IRoleAdapter.FACTORY)
            .iterator();
   }

   /**
    * @category IModel
    */
   public int getConditionalPerformersCount()
   {
      return mDelegate.getConditionalPerformer().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllConditionalPerformers()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(mDelegate, PKG_CWM.getModelType_ConditionalPerformer(),
                  IConditionalPerformerAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public Iterator getAllParticipants()
   {
      return new SplicingIterator(new SplicingIterator(getAllOrganizations(),
            getAllRoles()), new SplicingIterator(getAllConditionalPerformers(),
            getAllModelers()));
   }

   /**
    * @category IModel
    */
   public Iterator getAllTopLevelParticipants()
   {
      return new FilteringIterator(getAllParticipants(), new Predicate()
      {
         public boolean accept(Object o)
         {
            return !((IModelParticipant) o).getAllOrganizations().hasNext();
         }
      });
   }

   /**
    * @category IModel
    */
   public Iterator getAllWorkflowParticipants()
   {
      return new SplicingIterator(getAllOrganizations(), getAllRoles());
   }

   /**
    * @category IModel
    */
   public IModelParticipant findParticipant(String id)
   {
      IModelParticipant result = null;

      Object participant = ModelParticipantUtils.findById(mDelegate, id);
      if (null != participant)
      {
         result = (IModelParticipant) ModelApiPlugin.getAdapterRegistry().getAdapter(
               participant, AbstractModelParticipantAdapter.FACTORY);
      }
      else
      {
         result = (IModelParticipant) ModelApiPlugin.getAdapterRegistry().getAdapter(
               mDelegate.getModeler(), id, IModelerAdapter.FACTORY);
      }

      return result;
   }

   /**
    * @category IModel
    */
   public int getProcessDefinitionsCount()
   {
      return mDelegate.getProcessDefinition().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllProcessDefinitions()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(mDelegate, PKG_CWM.getModelType_ProcessDefinition(),
                  IProcessDefinitionAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public IProcessDefinition findProcessDefinition(String id)
   {
      return (IProcessDefinition) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate, PKG_CWM.getModelType_ProcessDefinition(), id,
            IProcessDefinitionAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public int getDiagramsCount()
   {
      return mDelegate.getDiagram().size();
   }

   /**
    * @category IModel
    */
   public Iterator getAllDiagrams()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate.getDiagram(), DiagramAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public Diagram findDiagram(String id)
   {
      return (Diagram) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getDiagram(), id, DiagramAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllViews()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate.getView(), IViewAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public Iterator getAllApplicationContextTypes()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(mDelegate.getApplicationContextType(),
                  IApplicationContextTypeAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public IApplicationContextType findApplicationContextType(String id)
   {
      return (IApplicationContextType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getApplicationContextType(), id,
            IApplicationContextTypeAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllApplicationTypes()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate.getApplicationType(), IApplicationTypeAdapter.FACTORY)
            .iterator();
   }

   /**
    * @category IModel
    */
   public IApplicationType findApplicationType(String id)
   {
      return (IApplicationType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getApplicationType(), id, IApplicationTypeAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllDataTypes()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate.getDataType(), IDataTypeAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public IDataType findDataType(String id)
   {
      return (IDataType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getDataType(), id, IDataTypeAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllEventActionTypes()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate.getEventActionType(), IEventActionTypeAdapter.FACTORY)
            .iterator();
   }

   /**
    * @category IModel
    */
   public IEventActionType findEventActionType(String id)
   {
      return (IEventActionType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getEventActionType(), id, IEventActionTypeAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllEventConditionTypes()
   {
      return ModelApiPlugin
            .getAdapterRegistry()
            .getAdapters(mDelegate.getEventConditionType(),
                  IEventConditionTypeAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public IEventConditionType findEventConditionType(String id)
   {
      return (IEventConditionType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getEventConditionType(), id, IEventConditionTypeAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllLinkTypes()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate.getLinkType(), ILinkTypeAdapter.FACTORY).iterator();
   }

   /**
    * @category IModel
    */
   public ILinkType findLinkType(String id)
   {
      return (ILinkType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getLinkType(), id, ILinkTypeAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllTriggerTypes()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(mDelegate.getTriggerType(), ITriggerTypeAdapter.FACTORY)
            .iterator();
   }

   /**
    * @category IModel
    */
   public ITriggerType findTriggerType(String id)
   {
      return (ITriggerType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            mDelegate.getTriggerType(), id, ITriggerTypeAdapter.FACTORY);
   }

   /**
    * @category IModel
    */
   public Iterator getAllLinkTypesForType(Class type)
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   /**
    * @category IModel
    */
   public Version getCarnotVersion()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public IModeler authentify(String id, String password)
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void addToProcessDefinitions(IProcessDefinition arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public Vector checkConsistency()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IApplication createApplication(String arg0, String arg1, String arg2, int arg3)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IApplicationContextType createApplicationContextType(String arg0, String arg1,
         boolean arg2, boolean arg3, boolean arg4, int arg5)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IApplicationType createApplicationType(String arg0, String arg1, boolean arg2,
         boolean arg3, int arg4)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IConditionalPerformer createConditionalPerformer(String arg0, String arg1,
         String arg2, IData arg3, int arg4)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IData createData(String arg0, IDataType arg1, String arg2, String arg3,
         boolean arg4, int arg5, Map arg6)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IDataType createDataType(String arg0, String arg1, boolean arg2, int arg3)
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

   public IEventActionType createEventActionType(String arg0, String arg1, boolean arg2,
         boolean arg3, boolean arg4, int arg5)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IEventConditionType createEventConditionType(String arg0, String arg1,
         boolean arg2, EventType arg3, boolean arg4, boolean arg5, int arg6)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public ILinkType createLinkType(String arg0, Class arg1, Class arg2, String arg3,
         String arg4, CardinalityKey arg5, CardinalityKey arg6, ArrowKey arg7,
         ArrowKey arg8, ColorKey arg9, LineKey arg10, boolean arg11, boolean arg12,
         int arg13)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IModeler createModeler(String arg0, String arg1, String arg2, String arg3,
         int arg4)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IOrganization createOrganization(String arg0, String arg1, String arg2, int arg3)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IProcessDefinition createProcessDefinition(String arg0, String arg1, String arg2)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IProcessDefinition createProcessDefinition(String arg0, String arg1,
         String arg2, boolean arg3, int arg4)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IRole createRole(String arg0, String arg1, String arg2, int arg3)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public ITriggerType createTriggerType(String arg0, String arg1, boolean arg2,
         boolean arg3, int arg4)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public IView createView(String arg0, String arg1, int arg2)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultApplicationId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultConditionalPerformerId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultDataId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultDiagramId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultModelerId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultOrganizationId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultProcessDefinitionId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultRoleId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public String getDefaultViewId()
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   public void removeFromApplicationContextTypes(IApplicationContextType arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromApplicationTypes(IApplicationType arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromApplications(IApplication arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromData(IData arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromDataTypes(IDataType arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromDiagrams(Diagram arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromLinkTypes(ILinkType arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromParticipants(IModelParticipant arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void removeFromProcessDefinitions(IProcessDefinition arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void setCarnotVersion(String arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void setDescription(String arg0)
   {
      // TODO implement this method!
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);

   }

   public void fireModelElementsLinked(ModelElement first, ModelElement second)
   {
      // TODO Auto-generated method stub

   }

   public void fireModelElementsUnlinked(ModelElement first, ModelElement second)
   {
      // TODO Auto-generated method stub

   }

   public void fireModelElementCreated(ModelElement element, ModelElement parent)
   {
      // TODO Auto-generated method stub

   }

   public void fireModelElementDeleted(ModelElement element, ModelElement parent)
   {
      // TODO Auto-generated method stub

   }

   public void deregister(ModelElement element)
   {
      // TODO Auto-generated method stub

   }

   public void fireModelElementChanged(ModelElement element)
   {
      // TODO Auto-generated method stub

   }

   public int createElementOID()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public void register(ModelElement element)
   {
      // TODO Auto-generated method stub

   }

   public void addToModelListeners(ModelListener listener)
   {
      // TODO Auto-generated method stub

   }

   public void removeFromModelListeners(ModelListener listener)
   {
      // TODO Auto-generated method stub

   }

   public Iterator getAllModelListeners()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void setModelOID(int oid)
   {
      // TODO Auto-generated method stub

   }

   public RootElement deepCopy()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void mergeDifferences(Differences diff)
   {
      // TODO Auto-generated method stub

   }

   public void setLoading(boolean loading)
   {
      // TODO Auto-generated method stub

   }

   public int createTransientElementOID()
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public Set<IConfigurationVariableDefinition> getConfigurationVariableDefinitions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Set<String> getConfigurationVariableReferences()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ITypeDeclaration createTypeDeclaration(String id, String name,
         String description, Map attributes, IXpdlType xpslType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Scripting getScripting()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void setScripting(Scripting scripting)
   {
      // TODO Auto-generated method stub

   }

   public List<IExternalPackage> getExternalPackages()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IExternalPackage findExternalPackage(String id)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getTypeDeclarations()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ITypeDeclaration findTypeDeclaration(String id)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getApplications()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getData()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList getParticipants()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public ModelElementList<IProcessDefinition> getProcessDefinitions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IProcessDefinition getImplementingProcess(QName processId)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Set<QName> getImplementedInterfaces()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
