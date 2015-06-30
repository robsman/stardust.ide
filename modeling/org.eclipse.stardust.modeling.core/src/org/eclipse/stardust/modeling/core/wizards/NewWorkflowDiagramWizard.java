/*******************************************************************************
 * Copyright (c) 2011, 2013 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.CurrentVersion;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelVariable;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ScriptType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.projectnature.classpath.BpmClasspathUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the provided
 * container. If the container resource (a folder or a project) is selected in the
 * workspace when the wizard is opened, it will accept it as the target container. The
 * wizard creates one file with the extension "cwm". If a sample multi-page editor (also
 * available as a template) is registered for the same extension, it will be able to open
 * it.
 */

public class NewWorkflowDiagramWizard extends Wizard implements INewWizard
{
   private ISelection selection;
   private IPreferenceStore pStore;

   /**
    * Constructor for NewWorkflowDiagramWizard.
    */
   public NewWorkflowDiagramWizard()
   {
      super();
      setWindowTitle(Diagram_Messages.TITLE_NewCarnotWorkflowModel);
      setNeedsProgressMonitor(true);
   }

   /**
    * Adding the page to the wizard.
    */
   public void addPages()
   {
      addPage(new NewWorkflowDiagramWizardPage(selection));
   }

   /**
    * This method is called when 'Finish' button is pressed in the wizard. We will create
    * an operation and run it using wizard as execution context.
    */
   public boolean performFinish()
   {
      final boolean[] result = new boolean[] {false};
      IRunnableWithProgress op = new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException
         {
            try
            {
               IFile file = getResourceContainer(monitor);
               if (file.exists())
               {
                  if (!MessageDialog.openQuestion(getShell(), file.getName(),
                        Diagram_Messages.MSG_AnotherFileAlreadyExists))
                  {
                     return;
                  }
               }
               URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
                     .toOSString(), false);
               WorkflowModelManager manager = new WorkflowModelManager();
               manager.createModel(fileURI);
               monitor.worked(1);
               createContent(manager.getModel());
               monitor.worked(1);
               manager.save(fileURI);
               monitor.worked(1);
               openEditor(monitor, file);
               result[0] = true;
            }
            catch (CoreException e)
            {
               throw new InvocationTargetException(e);
            }
            catch (IOException e)
            {
               throw new InvocationTargetException(e);
            }
            finally
            {
               monitor.done();
            }
         }
      };
      try
      {
         getContainer().run(false, false, op);
      }
      catch (InterruptedException e)
      {
      }
      catch (InvocationTargetException e)
      {
         Throwable realException = e.getTargetException();
         MessageDialog.openError(getShell(), Diagram_Messages.ERR_Error, realException
               .getMessage());
      }
      return result[0];
   }

   private void createContent(ModelType model)
   {
      NewWorkflowDiagramWizardPage props = (NewWorkflowDiagramWizardPage) getStartingPage();
      setModelAttributes(model, props.getModelId(), props.getModelName(), props
            .getModelDescription(), props.getModelAuthor());
      pStore = PlatformUI.getPreferenceStore();

      createDefaultDiagrams(model);
      createDefaultTypes(model);
      createDefaultData(model);
      createDefaultPerformers(model);
      createDefaultProcess(model);
      createDefaultCriticalityAttributes(model);
   }

   private void createDefaultProcess(ModelType model)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      ProcessDefinitionType process = factory.createProcessDefinitionType();
      IdFactory idFactory = new IdFactory(
            "ProcessDefinition", Diagram_Messages.BASENAME_ProcessDefinition); //$NON-NLS-1$
      idFactory.computeNames(model.getProcessDefinition());
      process.setId(idFactory.getId());
      process.setName(idFactory.getName());
      model.getProcessDefinition().add(process);
      DiagramType diagram = factory.createDiagramType();
      diagram.setName(Diagram_Messages.DIAGRAM_NAME_Default);

      String modelingDirection;
      // in case the PreferenceStore is not initialized
      if(pStore.contains(BpmProjectNature.PREFERENCE_MODELING_DIRECTION))
      {
         modelingDirection = pStore.getString(BpmProjectNature.PREFERENCE_MODELING_DIRECTION);
      }
      else
      {
         modelingDirection = BpmProjectNature.DEFAULT_PREFERENCE_MODELING_DIRECTION;
      }
      diagram.setOrientation(OrientationType.VERTICAL_LITERAL.toString().equals(modelingDirection)
            ? OrientationType.VERTICAL_LITERAL
            : OrientationType.HORIZONTAL_LITERAL);

      DiagramModeType defaultMode;
      boolean classicMode;
      if(pStore.contains(BpmProjectNature.PREFERENCE_CLASSIC_MODE))
      {
         classicMode = pStore.getBoolean(BpmProjectNature.PREFERENCE_CLASSIC_MODE);
      }
      else
      {
         classicMode = BpmProjectNature.DEFAULT_PREFERENCE_CLASSIC_MODE;
      }
      if (classicMode)
      {
         defaultMode = DiagramModeType.MODE_400_LITERAL;
      }
      else
      {
         defaultMode = DiagramModeType.MODE_450_LITERAL;
      }
      diagram.setMode(defaultMode);

      process.getDiagram().add(diagram);
      DiagramUtil.createDefaultPool(diagram);
   }

   private void createDefaultPerformers(ModelType model)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      RoleType administrator = factory.createRoleType();
      administrator.setId("Administrator"); //$NON-NLS-1$
      administrator.setName(Diagram_Messages.BASENAME_Administrator);
      administrator.setDescription(ModelUtils
            .createDescription(Diagram_Messages.DESC_InChargeAdministrationActivities));
      model.getRole().add(administrator);
   }

   private void createDefaultData(ModelType model)
   {
      DataTypeType primitiveDataType = (DataTypeType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(),
            defaultDataTypes[0]);
      DataTypeType serializableDataType = (DataTypeType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(),
            defaultDataTypes[1]);
      DataTypeType entityBeanDataType = (DataTypeType) ModelUtils.findIdentifiableElement(model,
            CarnotWorkflowModelPackage.eINSTANCE.getModelType_DataType(),
            defaultDataTypes[2]);

      DataType lastActivityPerformer = createData(model, entityBeanDataType,
            "LAST_ACTIVITY_PERFORMER", Diagram_Messages.NAME_LastActivityPerformer, //$NON-NLS-1$
            Diagram_Messages.DESC_LastActivityPerformer);
      createAttribute(lastActivityPerformer, PredefinedConstants.BROWSABLE_ATT,
            "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(lastActivityPerformer, PredefinedConstants.HOME_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserHome"); //$NON-NLS-1$
      createAttribute(lastActivityPerformer, PredefinedConstants.IS_LOCAL_ATT,
            "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(lastActivityPerformer, PredefinedConstants.JNDI_PATH_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.User"); //$NON-NLS-1$
      createAttribute(lastActivityPerformer, PredefinedConstants.PRIMARY_KEY_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserPK"); //$NON-NLS-1$
      createAttribute(lastActivityPerformer, PredefinedConstants.REMOTE_INTERFACE_ATT,
            null, "org.eclipse.stardust.engine.core.runtime.beans.IUser"); //$NON-NLS-1$

      DataType startingUser = createData(model, entityBeanDataType,
            "STARTING_USER", Diagram_Messages.NAME_StartingUser, //$NON-NLS-1$
            Diagram_Messages.DESC_StartingUser);
      createAttribute(startingUser, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(startingUser, PredefinedConstants.HOME_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserHome"); //$NON-NLS-1$
      createAttribute(startingUser, PredefinedConstants.IS_LOCAL_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(startingUser, PredefinedConstants.JNDI_PATH_ATT, null,
            "ag.carnot.workflow.runtime.User"); //$NON-NLS-1$
      createAttribute(startingUser, PredefinedConstants.PRIMARY_KEY_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserPK"); //$NON-NLS-1$
      createAttribute(startingUser, PredefinedConstants.REMOTE_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.core.runtime.beans.IUser"); //$NON-NLS-1$

      DataType currentUser = createData(model, entityBeanDataType,
            PredefinedConstants.CURRENT_USER, Diagram_Messages.NAME_CurrentUser,
            Diagram_Messages.DESC_CurrentUser);
      createAttribute(currentUser, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(currentUser, PredefinedConstants.HOME_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserHome"); //$NON-NLS-1$
      createAttribute(currentUser, PredefinedConstants.IS_LOCAL_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(currentUser, PredefinedConstants.JNDI_PATH_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.User"); //$NON-NLS-1$
      createAttribute(currentUser, PredefinedConstants.PRIMARY_KEY_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.UserPK"); //$NON-NLS-1$
      createAttribute(currentUser, PredefinedConstants.REMOTE_INTERFACE_ATT, null,
            "org.eclipse.stardust.engine.core.runtime.beans.IUser"); //$NON-NLS-1$

      DataType processId = createData(model, primitiveDataType, PredefinedConstants.PROCESS_ID,
            Diagram_Messages.NAME_ProcessOID, Diagram_Messages.DESC_ProcessOID);
      createAttribute(processId, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(processId, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", "long"); //$NON-NLS-1$ //$NON-NLS-2$

      DataType processPriority = createData(model, primitiveDataType,
            PredefinedConstants.PROCESS_PRIORITY, Diagram_Messages.NAME_ProcessPriority,
            Diagram_Messages.DESC_ProcessPriority);
      createAttribute(processPriority, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", "int"); //$NON-NLS-1$ //$NON-NLS-2$

      DataType rootProcessId = createData(model, primitiveDataType,
            PredefinedConstants.ROOT_PROCESS_ID, Diagram_Messages.NAME_RootProcessOID,
            Diagram_Messages.DESC_RootProcessOID);
      createAttribute(rootProcessId, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(rootProcessId, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", "long"); //$NON-NLS-1$ //$NON-NLS-2$

      DataType currentDate = createData(model, primitiveDataType,
            PredefinedConstants.CURRENT_DATE, Diagram_Messages.NAME_CurrentDate,
            Diagram_Messages.DESC_CurrentDate);
      createAttribute(currentDate, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(currentDate, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", "Calendar"); //$NON-NLS-1$ //$NON-NLS-2$

      DataType businessDate = createData(model, primitiveDataType,
            PredefinedConstants.BUSINESS_DATE, Diagram_Messages.NAME_BusinessDate,
            Diagram_Messages.DESC_BusinessDate);
      createAttribute(businessDate, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", "Timestamp"); //$NON-NLS-1$ //$NON-NLS-2$

      DataType currentLocale = createData(model, primitiveDataType,
            PredefinedConstants.CURRENT_LOCALE, Diagram_Messages.NAME_CurrentLocale,
            Diagram_Messages.DESC_CurrentLocale);
      createAttribute(currentLocale, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(currentLocale, PredefinedConstants.TYPE_ATT,
            "ag.carnot.workflow.spi.providers.data.java.Type", "String"); //$NON-NLS-1$ //$NON-NLS-2$

      DataType currentModel = createData(model, serializableDataType,
            PredefinedConstants.CURRENT_MODEL, Diagram_Messages.NAME_CurrentModel,
            Diagram_Messages.DESC_CurrentModel);
      createAttribute(currentModel, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      createAttribute(currentModel, PredefinedConstants.CLASS_NAME_ATT, null,
            "org.eclipse.stardust.engine.api.runtime.DeployedModelDescription"); //$NON-NLS-1$
   }

   private void createDefaultCriticalityAttributes(ModelType model)
   {
      VariableContextHelper.getInstance().createContext(model);
      VariableContext context = VariableContextHelper.getInstance().getContext(model);
      context.initializeVariables(model);

      // TDefault
      ModelVariable modelVariable = new ModelVariable("${TDefault}", "86400", //$NON-NLS-1$ //$NON-NLS-2$
            Diagram_Messages.CRITICALITY_TARGET_EXECUTION_TIME);
      context.createAttributeSet(modelVariable, 0);

      // CLow
      modelVariable = new ModelVariable("${CLow}", "0", //$NON-NLS-1$ //$NON-NLS-2$
            Diagram_Messages.CRITICALITY_INITIAL_CRITICALITY_LOW);
      context.createAttributeSet(modelVariable, 1);

      // CMed
      modelVariable = new ModelVariable("${CMed}", "0.33", //$NON-NLS-1$ //$NON-NLS-2$
            Diagram_Messages.CRITICALITY_INITIAL_CRITICALITY_MEDIUM);
      context.createAttributeSet(modelVariable, 2);

      // CHigh
      modelVariable = new ModelVariable("${CHigh}", "0.66", //$NON-NLS-1$ //$NON-NLS-2$
            Diagram_Messages.CRITICALITY_INITIAL_CRITICALITY_HIGH);
      context.createAttributeSet(modelVariable, 3);

      // MLow
      modelVariable = new ModelVariable("${MLow}", "10", //$NON-NLS-1$ //$NON-NLS-2$
            Diagram_Messages.CRITICALITY_MULTIPLE_TARGET_EXECUTION_LOW);
      context.createAttributeSet(modelVariable, 4);

      // MMed
      modelVariable = new ModelVariable("${MMed}", "10", //$NON-NLS-1$ //$NON-NLS-2$
            Diagram_Messages.CRITICALITY_MULTIPLE_TARGET_EXECUTION_MEDIUM);
      context.createAttributeSet(modelVariable, 5);

      // MHigh
      modelVariable = new ModelVariable("${MHigh}", "10", //$NON-NLS-1$ //$NON-NLS-2$
            Diagram_Messages.CRITICALITY_MULTIPLE_TARGET_EXECUTION_HIGH);
      context.createAttributeSet(modelVariable, 6);

      //Default criticality formula
      String formula =
         "if(activityInstance.getActivity().getTargetExecutionTime() == 0)\n" +  //$NON-NLS-1$
         "{\n" + //$NON-NLS-1$
         "  T = ${TDefault};\n" +  //$NON-NLS-1$
         "}\n" +  //$NON-NLS-1$
         "else\n" +  //$NON-NLS-1$
         "{\n" + //$NON-NLS-1$
         "  T = activityInstance.getActivity().getTargetExecutionTime();\n" + //$NON-NLS-1$
         "}\n" + //$NON-NLS-1$
         "if(PROCESS_PRIORITY == -1)\n" + //$NON-NLS-1$
         "{\n"+ //$NON-NLS-1$
         "  Cp = ${CLow};\n" +  //$NON-NLS-1$
         "  Mp = ${MLow};\n" + //$NON-NLS-1$
         "}\n" + //$NON-NLS-1$
         "if(PROCESS_PRIORITY == 0)\n" + //$NON-NLS-1$
         "{\n" +  //$NON-NLS-1$
         "   Cp = ${CMed};\n" + //$NON-NLS-1$
         "   Mp = ${MMed};\n" + //$NON-NLS-1$
         "}\n" + //$NON-NLS-1$
         "if(PROCESS_PRIORITY == 1)\n" + //$NON-NLS-1$
         "{\n" + //$NON-NLS-1$
         "   Cp = ${CHigh};\n" + //$NON-NLS-1$
         "   Mp = ${MHigh};\n" + //$NON-NLS-1$
         "}\n" + //$NON-NLS-1$
         "t = activityInstance.getAge() / 1000;\n\n" + //$NON-NLS-1$
         "Cp + (1- Cp) * t/(Mp * T);\n"; //$NON-NLS-1$
      AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:criticalityFormula", "String", formula); //$NON-NLS-1$ //$NON-NLS-2$
   }

   private void createAttribute(DataType data, String name, String type, String value)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      AttributeType attribute = factory.createAttributeType();
      attribute.setName(name);
      if (type != null)
      {
         attribute.setType(type);
      }
      attribute.setValue(value);
      data.getAttribute().add(attribute);
   }

   private DataType createData(ModelType model, DataTypeType type, String id,
         String name, String description)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      DataType data = factory.createDataType();
      data.setId(id);
      data.setName(name);
      data.setType(type);
      data.setDescription(ModelUtils.createDescription(description));
      data.setPredefined(true);
      model.getData().add(data);
      return data;
   }

   private static final String[] defaultDataTypes = {
         PredefinedConstants.PRIMITIVE_DATA, PredefinedConstants.SERIALIZABLE_DATA,
         PredefinedConstants.ENTITY_BEAN_DATA, PredefinedConstants.PLAIN_XML_DATA,
         DmsConstants.DATA_TYPE_DMS_DOCUMENT,
         DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST,
         DmsConstants.DATA_TYPE_DMS_FOLDER,
         DmsConstants.DATA_TYPE_DMS_FOLDER_LIST,
         StructuredDataConstants.STRUCTURED_DATA};

   private static final String[] defaultApplicationTypes = {
         PredefinedConstants.SESSIONBEAN_APPLICATION,
         PredefinedConstants.PLAINJAVA_APPLICATION, PredefinedConstants.JMS_APPLICATION,
         PredefinedConstants.WS_APPLICATION
   };

   private static final String[] defaultContextTypes = {
         PredefinedConstants.DEFAULT_CONTEXT, PredefinedConstants.ENGINE_CONTEXT,
         PredefinedConstants.APPLICATION_CONTEXT, PredefinedConstants.JFC_CONTEXT,
         PredefinedConstants.JSP_CONTEXT, PredefinedConstants.PROCESSINTERFACE_CONTEXT,
         PredefinedConstants.EXTERNALWEBAPP_CONTEXT};

   private static final String[] defaultTriggerTypes = {
         PredefinedConstants.MANUAL_TRIGGER, PredefinedConstants.JMS_TRIGGER,
         PredefinedConstants.MAIL_TRIGGER, PredefinedConstants.TIMER_TRIGGER};

   private static final String[] defaultConditionTypes = {
         PredefinedConstants.TIMER_CONDITION, PredefinedConstants.EXCEPTION_CONDITION,
         PredefinedConstants.ACTIVITY_STATECHANGE_CONDITION,
         PredefinedConstants.PROCESS_STATECHANGE_CONDITION,
         PredefinedConstants.ACTIVITY_ON_ASSIGNMENT_CONDITION,
   // removed due to #4244 PredefinedConstants.OBSERVER_EVENT_CONDITION
   };

   private static final String[] defaultActionTypes = {
         PredefinedConstants.TRIGGER_ACTION, PredefinedConstants.MAIL_ACTION,
         PredefinedConstants.ABORT_PROCESS_ACTION,
         PredefinedConstants.COMPLETE_ACTIVITY_ACTION,
         PredefinedConstants.ACTIVATE_ACTIVITY_ACTION,
         PredefinedConstants.DELEGATE_ACTIVITY_ACTION,
         PredefinedConstants.SCHEDULE_ACTIVITY_ACTION,
         PredefinedConstants.EXCLUDE_USER_ACTION, PredefinedConstants.SET_DATA_ACTION};

   private void createDefaultTypes(ModelType model)
   {
      model.setTypeDeclarations(XpdlFactory.eINSTANCE.createTypeDeclarationsType());

      addMetaTypes(model, defaultDataTypes,
            CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getDataTypeType(),
            new EStructuralFeature[] {});

      addMetaTypes(model, defaultApplicationTypes,
            CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationTypeType(),
            new EStructuralFeature[] {CarnotWorkflowModelPackage.eINSTANCE
                  .getApplicationTypeType_Synchronous()});
      addMetaTypes(model, defaultContextTypes,
            CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getApplicationContextTypeType_HasMappingId(),
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getApplicationContextTypeType_HasApplicationPath()});
      addMetaTypes(model, defaultTriggerTypes,
            CarnotConstants.TRIGGER_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getTriggerTypeType(),
            new EStructuralFeature[] {CarnotWorkflowModelPackage.eINSTANCE
                  .getTriggerTypeType_PullTrigger()});
      addMetaTypes(model, defaultConditionTypes,
            CarnotConstants.CONDITION_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getEventConditionTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getEventConditionTypeType_Implementation(),
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getEventConditionTypeType_ActivityCondition(),
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getEventConditionTypeType_ProcessCondition()});
      addMetaTypes(model, defaultActionTypes,
            CarnotConstants.ACTION_TYPES_EXTENSION_POINT_ID,
            CarnotWorkflowModelPackage.eINSTANCE.getEventActionTypeType(),
            new EStructuralFeature[] {
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getEventActionTypeType_ActivityAction(),
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getEventActionTypeType_ProcessAction(),
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getEventActionTypeType_SupportedConditionTypes(),
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getEventActionTypeType_UnsupportedContexts()});
   }

   private void addMetaTypes(ModelType model, String[] ids, String extensionPointId,
         EClass type, EStructuralFeature[] features)
   {
      Map<String, IConfigurationElement> extensions = SpiExtensionRegistry.instance().getExtensions(extensionPointId);
      for (int i = 0; i < ids.length; i++)
      {
         IConfigurationElement config = extensions.get(ids[i]);
         CreateMetaTypeCommand command = new CreateMetaTypeCommand(config, type, features);
         command.setParent(model);
         command.execute();
      }
   }

   private void createDefaultDiagrams(ModelType model)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      DiagramType diagram = factory.createDiagramType();

      diagram.setName(Diagram_Messages.NAME_DefaultDiagram);
      String modelingDirection;
      // in case the PreferenceStore is not initialized
      if(pStore.contains(BpmProjectNature.PREFERENCE_MODELING_DIRECTION))
      {
         modelingDirection = pStore.getString(BpmProjectNature.PREFERENCE_MODELING_DIRECTION);
      }
      else
      {
         modelingDirection = BpmProjectNature.DEFAULT_PREFERENCE_MODELING_DIRECTION;
      }
      diagram.setOrientation(OrientationType.VERTICAL_LITERAL.toString().equals(modelingDirection)
            ? OrientationType.VERTICAL_LITERAL
            : OrientationType.HORIZONTAL_LITERAL);

      DiagramModeType defaultMode;
      boolean classicMode;
      if(pStore.contains(BpmProjectNature.PREFERENCE_CLASSIC_MODE))
      {
         classicMode = pStore.getBoolean(BpmProjectNature.PREFERENCE_CLASSIC_MODE);
      }
      else
      {
         classicMode = BpmProjectNature.DEFAULT_PREFERENCE_CLASSIC_MODE;
      }
      if (classicMode)
      {
         defaultMode = DiagramModeType.MODE_400_LITERAL;
      }
      else
      {
         defaultMode = DiagramModeType.MODE_450_LITERAL;
      }
      diagram.setMode(defaultMode);
      model.getDiagram().add(diagram);
   }

   private ModelType setModelAttributes(ModelType model, String id, String name,
         String description, String author)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;

      model.setId(id);
      model.setName(name);
      if (description.length() > 0)
      {
         model.setDescription(ModelUtils.createDescription(description));
      }
      model.setOid(0);
      model.setAuthor(author);
      model.setCreated(new Date().toString());
      model.setCarnotVersion(CurrentVersion.getVersionName());
      model.setVendor(XMLConstants.VENDOR_NAME);

      model.setModelOID(0);

      ScriptType script = XpdlFactory.eINSTANCE.createScriptType();
      script.setType("text/ecmascript"); //$NON-NLS-1$
      model.setScript(script);

      AttributeType attrIsReleased = factory.createAttributeType();
      attrIsReleased.setName(PredefinedConstants.IS_RELEASED_ATT);
      attrIsReleased.setType(Reflect.getAbbreviatedName(Boolean.TYPE));
      attrIsReleased.setValue(Reflect.convertObjectToString(Boolean.FALSE));
      model.getAttribute().add(attrIsReleased);

      AttributeType attrRevision = factory.createAttributeType();
      attrRevision.setName(PredefinedConstants.REVISION_ATT);
      attrRevision.setType(Reflect.getAbbreviatedName(Integer.TYPE));
      attrRevision.setValue(Reflect.convertObjectToString(new Integer(0)));
      model.getAttribute().add(attrRevision);

      AttributeType attrVersion = factory.createAttributeType();
      attrVersion.setName(PredefinedConstants.VERSION_ATT);
      attrVersion.setValue(Reflect.convertObjectToString("1")); //$NON-NLS-1$
      model.getAttribute().add(attrVersion);

      return model;
   }

   private void openEditor(IProgressMonitor monitor, final IFile file)
   {
      monitor.setTaskName(Diagram_Messages.TASK_OpeningFileForEditing);
      getShell().getDisplay().asyncExec(new Runnable()
      {
         public void run()
         {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage();
            try
            {
               IDE.openEditor(page, file, true);
            }
            catch (PartInitException e)
            {
            }
         }
      });
      monitor.worked(2);
   }

   private IFile getResourceContainer(IProgressMonitor monitor) throws CoreException
   {
      NewWorkflowDiagramWizardPage page = (NewWorkflowDiagramWizardPage) getStartingPage();

      // create a sample file
      monitor.beginTask(Diagram_Messages.TASK_Creating + page.getFileName(), 6);
      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
      IResource resource = null;
      if (!StringUtils.isEmpty(page.getContainerName()))
      {
         resource = root.findMember(new Path(page.getContainerName()));
      }
      else
      {
         IProject project = createNewProject(root);
         resource = root.findMember(new Path(project.getName()));
      }
// this check is already done in NewWorkflowDiagramWizardPage
/*
      if (!resource.exists() || !(resource instanceof IContainer))
      {
         IStatus status = new Status(IStatus.ERROR, CarnotConstants.DIAGRAM_PLUGIN_ID,
               IStatus.OK, Diagram_Messages.STATUS_P1_Container + page.getContainerName()
                     + Diagram_Messages.STATUS_P2_doesNotExist, null);
         throw new CoreException(status);
      }
      else
*/
      if (!resource.isAccessible())
      {
         IStatus status = new Status(IStatus.ERROR, CarnotConstants.DIAGRAM_PLUGIN_ID,
               IStatus.OK, MessageFormat.format(Diagram_Messages.MSG_ProjectNotOpen,
                     new Object[] {resource.getName()}), null);
         throw new CoreException(status);
      }
      IFile file = ((IContainer) resource).getFile(new Path(page.getFileName()));
      monitor.worked(1);
      return file;
   }

   private IProject createNewProject(IWorkspaceRoot root) throws CoreException,
         JavaModelException
   {
      IProject project = root.getProject("carnot-bpm"); //$NON-NLS-1$
      if (!project.exists())
      {
         project.create(null);
         project.open(null);
         IJavaProject javaProject = JavaCore.create(project);

         IProjectDescription description = project.getDescription();
         description.setNatureIds(new String[] {JavaCore.NATURE_ID});
         project.setDescription(description, null);

         javaProject.setRawClasspath(new IClasspathEntry[] {JavaRuntime
               .getDefaultJREContainerEntry()}, null);
         BpmProjectNature.enableBpmNature(project);
         BpmClasspathUtils.addBpmCoreLibsContainer(project);
      }
      return project;
   }

   /**
    * We will accept the selection in the workbench to see if we can initialize from it.
    *
    * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
    */
   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.selection = selection;
   }
}