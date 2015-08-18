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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.IStructuredSelection;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.CurrentVersion;
import org.eclipse.stardust.common.config.Version;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetMapValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;

import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;

public class UpgradeModelAction extends SelectionAction
{
   private static final String BASE_INFINITY_NAMESPACE = "http://www.infinity.com/bpm/model/"; //$NON-NLS-1$

   private static final String PATH_SEPARATOR = "/"; //$NON-NLS-1$

   private static final String TNS_PREFIX = "tns"; //$NON-NLS-1$

   private static final String STANDARD_CARNOT_WORKSPACE = "http://www.carnot.ag/workflowmodel/3.1/struct"; //$NON-NLS-1$

   private static final String STARTING_USER = "STARTING_USER"; //$NON-NLS-1$

   private static final String LAST_ACTIVITY_PERFORMER = "LAST_ACTIVITY_PERFORMER"; //$NON-NLS-1$

   private final WorkflowModelEditor editor;

   private HashMap schemas2namespace = new HashMap();

   public UpgradeModelAction(WorkflowModelEditor editor)
   {
      super(editor);
      this.editor = editor;
      setId(DiagramActionConstants.MODEL_UPGRADE);
      setText(Diagram_Messages.LB_UpgradeModel);
   }

   protected boolean calculateEnabled()
   {
      Object obj = getSelection();
      if (obj instanceof IStructuredSelection)
      {
         obj = ((IStructuredSelection) obj).getFirstElement();
         if (obj instanceof ModelTreeEditPart)
         {
            return createUpdateModelCmd().canExecute();
         }
      }
      return false;
   }

   public void run()
   {
      execute(createUpdateModelCmd());
   }

   public Command createUpdateModelCmd()
   {
      CompoundCommand command = new CompoundCommand();
      ModelType model = editor.getWorkflowModel();
      if (model == null)
      {
         return command.unwrap();
      }

      String vendor = model.getVendor();
      if(vendor != null && !vendor.equals(XMLConstants.VENDOR_NAME))
      {
         command.add(new SetValueCmd(editor.getWorkflowModel(),
               CarnotWorkflowModelPackage.eINSTANCE.getModelType_Vendor(),
               XMLConstants.VENDOR_NAME));
      }

      String carnotVersion = model.getCarnotVersion();
      if (carnotVersion == null || CurrentVersion.getVersion().compareTo(new Version(carnotVersion)) > 0)
      {
         command.add(new SetValueCmd(editor.getWorkflowModel(),
               CarnotWorkflowModelPackage.eINSTANCE.getModelType_CarnotVersion(),
               CurrentVersion.getVersionName()));

         createUpdatePredefinedDataCmd(command, (DataType) ModelUtils
               .findIdentifiableElement(editor.getWorkflowModel().getData(),
                     LAST_ACTIVITY_PERFORMER));
         createUpdatePredefinedDataCmd(command, (DataType) ModelUtils
               .findIdentifiableElement(editor.getWorkflowModel().getData(),
                     STARTING_USER));
         createUpdateControllingAttributes(command, editor.getWorkflowModel());
      }
      createMissingDataCmd(command);
      createMissingDataTypes(command);
      createMissingApplicationContextTypes(command);
      createModifiedValidatorsCmds(command);
      createChangeStructuredDataCmd(command);
      createModifiedDataMappingCmd(command);
      createModifiedProjectPlanningParametersCmd(command);

      return command.unwrap();
   }

   private void createModifiedProjectPlanningParametersCmd(CompoundCommand command)
   {
      ModelType model = editor.getWorkflowModel();
      for (Iterator<AttributeType> i = model.getAttribute().iterator(); i.hasNext();)
      {
         AttributeType attribute = i.next();
         if (attribute.getName().indexOf("project-planning") > -1) //$NON-NLS-1$
         {
            String value = attribute.getValue();
            if (value != null && value.startsWith("ag.carnot.workflow.model.carnot")) //$NON-NLS-1$
            {
               value = value.replaceAll("ag.carnot.workflow.model.carnot", //$NON-NLS-1$
                     "org.eclipse.stardust.model.xpdl.carnot"); //$NON-NLS-1$
               command
                     .add(new SetValueCmd(attribute, CarnotWorkflowModelPackage.eINSTANCE
                           .getAttributeType_Value(), value));
            }
            if (value != null && value.startsWith("ag.carnot.modeling.project")) //$NON-NLS-1$
            {
               value = value.replaceAll("ag.carnot.modeling.project", //$NON-NLS-1$
                     "org.eclipse.stardust.modeling.project"); //$NON-NLS-1$
               command
                     .add(new SetValueCmd(attribute, CarnotWorkflowModelPackage.eINSTANCE
                           .getAttributeType_Value(), value));
            }

         }
      }
   }

   private void createModifiedDataMappingCmd(CompoundCommand command)
   {
      ModelType model = editor.getWorkflowModel();
      for (ProcessDefinitionType processDefinition : model.getProcessDefinition())
      {
         for (ActivityType activity : processDefinition.getActivity())
         {
            for (DataMappingType dataMapping : activity.getDataMapping())
            {
               if (StringUtils.isEmpty(dataMapping.getName()))
               {
                  dataMapping.setName(dataMapping.getId());
               }
            }
         }
      }
   }

   private void createChangeStructuredDataCmd(CompoundCommand command)
   {
      ModelType model = editor.getWorkflowModel();
      TypeDeclarationsType declarations = model.getTypeDeclarations();
      if (declarations == null)
      {
         declarations = XpdlFactory.eINSTANCE.createTypeDeclarationsType();
         command.add(new SetValueCmd(model,
               CarnotWorkflowModelPackage.eINSTANCE.getModelType_TypeDeclarations(),
               declarations));

         // TODO: change the cwm CDATA string to TypeDeclarations
         // TODO: change the cwm struct data objects
      }

      List list = declarations.getTypeDeclaration();
      // upgrade needed if all declarations are schema types and all schema types
      // have schemas in standard carnot namespace.
      boolean needUpgrade = true;
      for (int i = 0; i < list.size(); i++)
      {
         TypeDeclarationType decl = (TypeDeclarationType) list.get(i);
         XpdlTypeType type = decl.getDataType();
         if (type instanceof SchemaTypeType)
         {
            XSDSchema schema = ((SchemaTypeType) type).getSchema();
            if (schema == null)
            {
            	String error = Diagram_Messages.ERR_NULL_SCHEMA_FOR_DECL_NULL;
            	System.err.println(MessageFormat.format(error, new Object[]{decl.getId()}));
            }
            else
            {
               String targetNamespace = schema.getTargetNamespace();
               if (targetNamespace != null && !targetNamespace.equals(STANDARD_CARNOT_WORKSPACE))
               {
                  needUpgrade = false;
                  break;
               }
            }
         }
         else
         {
            needUpgrade = false;
            break;
         }
      }
      if (needUpgrade)
      {
         // we must patch all namespaces before resolving types
         // otherwise undesired namespace declarations will be added with prefix Qnnn
         for (int i = 0; i < list.size(); i++)
         {
            TypeDeclarationType decl = (TypeDeclarationType) list.get(i);
            XSDSchema schema = decl.getSchemaType().getSchema();
            if (schema != null)
            {
               patchSchemaNamespaces(command, decl, schema);
            }
         }
         for (int i = 0; i < list.size(); i++)
         {
            TypeDeclarationType decl = (TypeDeclarationType) list.get(i);
            XSDSchema schema = decl.getSchemaType().getSchema();
            if (schema != null)
            {
               resolveTypes(command, decl, schema);
            }
         }
      }
   }

   private void patchSchemaNamespaces(CompoundCommand command, TypeDeclarationType declaration,
         XSDSchema schema)
   {
      ModelType model = (ModelType) declaration.eContainer().eContainer();
      command.add(new SetMapValueCmd(schema.getQNamePrefixToNamespaceMap(),
            XSDPackage.eNS_PREFIX, XMLResource.XML_SCHEMA_URI));
      schema.setSchemaForSchemaQNamePrefix(XSDPackage.eNS_PREFIX);
      command.add(new SetMapValueCmd(schema.getQNamePrefixToNamespaceMap(),
            TNS_PREFIX, null));
      String targetNamespace = TypeDeclarationUtils.computeTargetNamespace(model, declaration.getId());
      String prefix = TypeDeclarationUtils.computePrefix(declaration.getId(),
            schema.getQNamePrefixToNamespaceMap().keySet());
      command.add(new SetMapValueCmd(schema.getQNamePrefixToNamespaceMap(),
            prefix, targetNamespace));
      command.add(new SetValueCmd(schema,
            XSDPackage.eINSTANCE.getXSDSchema_TargetNamespace(),
            targetNamespace));
      schemas2namespace.put(schema, targetNamespace);
   }

   private void resolveTypes(CompoundCommand command, TypeDeclarationType declaration,
         XSDSchema schema)
   {
      List elements = schema.getElementDeclarations();
      for (int i = 0; i < elements.size(); i++)
      {
         XSDElementDeclaration element = (XSDElementDeclaration) elements.get(i);
         // we need to be sure that the element is defined in this schema and not in an imported one
         if (CompareHelper.areEqual(schema, element.getSchema()))
         {
            patchElement(command, declaration, element);
         }
      }
      List types = schema.getTypeDefinitions();
      for (int i = 0; i < types.size(); i++)
      {
         XSDTypeDefinition type = (XSDTypeDefinition) types.get(i);
         // we need to be sure that the type is defined in this schema and not in an imported one
         if (type instanceof XSDComplexTypeDefinition && CompareHelper.areEqual(schema, type.getSchema()))
         {
            patchType(command, declaration, (XSDComplexTypeDefinition) type);
         }
      }
   }

   private void patchType(CompoundCommand command, TypeDeclarationType declaration, XSDComplexTypeDefinition complexType)
   {
      XSDComplexTypeContent content = complexType.getContent();
      if (content instanceof XSDParticle)
      {
         patchParticle(command, declaration, (XSDParticle) content);
      }
   }

   private void patchParticle(CompoundCommand command, TypeDeclarationType declaration, XSDParticle particle)
   {
      XSDTerm term = particle.getTerm();
      if (term != null)
      {
         patchTerm(command, declaration, term);
      }
   }

   private void patchTerm(CompoundCommand command, TypeDeclarationType declaration, XSDTerm term)
   {
      if (term instanceof XSDElementDeclaration)
      {
         patchElement(command, declaration, (XSDElementDeclaration) term);
      }
      // TODO: use instanceof XSDModelGroup once we switch to java 5
      else if ("XSDModelGroup".equals(term.eClass().getName())) //$NON-NLS-1$
      {
         // must use ecore reflection and not cast to XSDModelGroup to avoid java 5 dependencies
         EStructuralFeature feature = term.eClass().getEStructuralFeature("contents"); //$NON-NLS-1$
         List particles = (List) term.eGet(feature);
         for (int i = 0; i < particles.size(); i++)
         {
            patchParticle(command, declaration, (XSDParticle) particles.get(i));
         }
      }
   }

   private void patchElement(CompoundCommand command, TypeDeclarationType declaration, XSDElementDeclaration element)
   {
      XSDTypeDefinition type = element.getAnonymousTypeDefinition();
      if (type instanceof XSDComplexTypeDefinition)
      {
         patchType(command, declaration, (XSDComplexTypeDefinition) type);
      }
      else if (type == null)
      {
         type = element.getType();
         if (type != null && type.getSchema() == null)
         {
            // unresolved type
            type = resolve(declaration, element, type);
            if (type != null && type.getSchema() != null)
            {
               updateImports(command, element.getSchema(), type.getSchema());
               command.add(new SetValueCmd(element,
                     XSDPackage.eINSTANCE.getXSDElementDeclaration_TypeDefinition(), type));
            }
         }
      }
   }

   private XSDTypeDefinition resolve(TypeDeclarationType declaration,
         XSDElementDeclaration element, XSDTypeDefinition type)
   {
      String name = type.getName();
      String targetNamespace = type.getTargetNamespace();
      if (name != null)
      {
         if (XMLResource.XML_SCHEMA_URI.equals(targetNamespace))
         {
            XSDSchema schema = element.getSchema();
            XSDTypeDefinition def = findTypeDefinition(schema, name);
            if (def != null)
            {
               return def;
            }
         }
         if (STANDARD_CARNOT_WORKSPACE.equals(targetNamespace) // declared in the standard carnot workspace
            || TNS_PREFIX.equals(targetNamespace) // xmlns:tns namespace declaration may be missing
            || XMLResource.XML_SCHEMA_URI.equals(targetNamespace)) // declared in no namespace meaning it inherits the schema namespace
         {
            TypeDeclarationType decl = ((TypeDeclarationsType) declaration.eContainer()).getTypeDeclaration(name);
            return findTypeDefinition(decl);
         }
      }
      return null;
   }

   private static XSDTypeDefinition findTypeDefinition(TypeDeclarationType declaration)
   {
      if (declaration != null)
      {
         XpdlTypeType type = declaration.getDataType();
         if (type instanceof SchemaTypeType)
         {
            XSDSchema schema = ((SchemaTypeType) type).getSchema();
            String name = declaration.getId();
            return findTypeDefinition(schema, name);
         }
      }
      return null;
   }

   private static XSDTypeDefinition findTypeDefinition(XSDSchema schema, String name)
   {
      if (schema != null)
      {
         List types = schema.getTypeDefinitions();
         for (int i = 0; i < types.size(); i++)
         {
            XSDTypeDefinition def = (XSDTypeDefinition) types.get(i);
            // we need to be sure that the type is defined in this schema and not in an imported one
            if (name.equals(def.getName()) && CompareHelper.areEqual(schema, def.getSchema()))
            {
               return def;
            }
         }
      }
      return null;
   }

   private void updateImports(CompoundCommand command, XSDSchema schema, XSDSchema schema2Import)
   {
      if (schema == schema2Import)
      {
         return;
      }
      List contents = schema.getContents();
      for (int i = 0; i < contents.size(); i++)
      {
         XSDSchemaContent item = (XSDSchemaContent) contents.get(i);
         if (item instanceof XSDImport)
         {
            XSDImport imported = (XSDImport) item;
            if (schema2Import == imported.getResolvedSchema())
            {
               // schema already imported
               return;
            }
         }
      }
      // add new import declaration
      TypeDeclarationType decl = (TypeDeclarationType) schema2Import.eContainer().eContainer();
      String prefix = TypeDeclarationUtils.computePrefix(decl.getId(), schema.getQNamePrefixToNamespaceMap().keySet());
      command.add(new SetMapValueCmd(schema.getQNamePrefixToNamespaceMap(),
            prefix, schemas2namespace.get(schema2Import)));
      XSDImport xsdImport = XSDFactory.eINSTANCE.createXSDImport();
      xsdImport.setNamespace((String) schemas2namespace.get(schema2Import));
      xsdImport.setSchemaLocation(schema2Import.getSchemaLocation());
      command.add(new SetValueCmd(schema,
            XSDPackage.eINSTANCE.getXSDSchema_Contents(), 0, xsdImport));
   }

   private void createUpdateControllingAttributes(CompoundCommand command,
         ModelType model)
   {
      List processes = model.getProcessDefinition();
      for (int i = 0; i < processes.size(); i++)
      {
         createUpdateControllingAttributes(command, (ProcessDefinitionType) processes.get(i));
      }
   }

   private void createUpdateControllingAttributes(CompoundCommand command,
         ProcessDefinitionType process)
   {
      List activities = process.getActivity();
      for (int i = 0; i < activities.size(); i++)
      {
         createUpdateControllingAttributes(command, (ActivityType) activities.get(i));
      }
   }

   private void createUpdateControllingAttributes(CompoundCommand command,
         ActivityType activity)
   {
      createUpdateControllingAttribute(command, activity, "carnot:pwh:targetProcessingTime"); //$NON-NLS-1$
      createUpdateControllingAttribute(command, activity, "carnot:pwh:targetExecutionTime"); //$NON-NLS-1$
      createUpdateControllingAttribute(command, activity, "carnot:pwh:targetIdleTime"); //$NON-NLS-1$
      createUpdateControllingAttribute(command, activity, "carnot:pwh:targetWaitingTime"); //$NON-NLS-1$
   }

   private void createUpdateControllingAttribute(CompoundCommand command,
         IExtensibleElement element, String name)
   {
      AttributeType attribute = AttributeUtil.getAttribute(element, name);
      if (attribute != null)
      {
         String value = attribute.getValue();
         try
         {
            long minutes = Long.parseLong(value);
            long hours = 0;
            long days = 0;
            if (minutes > 59)
            {
               hours = minutes / 60;
               minutes = minutes - 60 * hours;
               if (hours > 23)
               {
                  days = hours / 24;
                  hours = hours - 24 * days;
               }
            }
            StringBuffer period = new StringBuffer();
            period.append("000:00:"); //$NON-NLS-1$
            appendNumber(period, days, 3);
            period.append(':');
            appendNumber(period, hours, 2);
            period.append(':');
            appendNumber(period, minutes, 2);
            period.append(":00"); //$NON-NLS-1$
            command.add(new SetValueCmd(attribute,
                  CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
                  period.toString()));
         }
         catch (NumberFormatException nfe)
         {
            // nothing to do
         }
      }
   }

   private void appendNumber(StringBuffer buffer, long value, int count)
   {
      if (value > 999)
      {
         // TODO: exception ? warning ?
         value = 999;
      }
      if (count > 2 && value < 100)
      {
         buffer.append('0');
      }
      if (count > 1 && value < 10)
      {
         buffer.append('0');
      }
      buffer.append(value);
   }

   private void createMissingDataCmd(CompoundCommand command)
   {
      ModelType model = editor.getWorkflowModel();
      DataType data = (DataType) ModelUtils.findIdentifiableElement(model.getData(),
            PredefinedConstants.PROCESS_PRIORITY);
      if (data == null)
      {
         DataTypeType primitiveDataType = (DataTypeType) ModelUtils.findIdentifiableElement(
               model.getDataType(), PredefinedConstants.PRIMITIVE_DATA);
         if (null != primitiveDataType)
         {
            CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
            data = factory.createDataType();
            data.setId(PredefinedConstants.PROCESS_PRIORITY);
            data.setName("Process Priority"); //$NON-NLS-1$
            data.setDescription(ModelUtils.createDescription("Priority assigned to the current process.")); //$NON-NLS-1$
            data.setPredefined(true);
   //         AttributeUtil.setAttribute(data, PredefinedConstants.BROWSABLE_ATT, "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
            AttributeUtil.setAttribute(data, PredefinedConstants.TYPE_ATT,
                  "ag.carnot.workflow.spi.providers.data.java.Type", "int"); //$NON-NLS-1$ //$NON-NLS-2$
            command.add(new SetValueCmd(editor.getWorkflowModel(),
                  CarnotWorkflowModelPackage.eINSTANCE.getModelType_Data(),
                  data));
            command.add(new SetValueCmd(data,
                  CarnotWorkflowModelPackage.eINSTANCE.getDataType_Type(),
                  primitiveDataType));
         }
      }
   }

   private void createMissingApplicationContextTypes(CompoundCommand command)
   {
      String[] defaultContextTypes = {
         PredefinedConstants.DEFAULT_CONTEXT, PredefinedConstants.ENGINE_CONTEXT,
         PredefinedConstants.APPLICATION_CONTEXT, PredefinedConstants.JFC_CONTEXT,
         PredefinedConstants.JSP_CONTEXT, PredefinedConstants.PROCESSINTERFACE_CONTEXT,
         PredefinedConstants.EXTERNALWEBAPP_CONTEXT};

      ModelType model = editor.getWorkflowModel();
      EList<ApplicationContextTypeType> applicationContextTypes = model.getApplicationContextType();
      Map<String, IConfigurationElement> dataTypesConfig = SpiExtensionRegistry.instance().getExtensions(CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID);

      for(String contextTypeKey : defaultContextTypes)
      {
         ApplicationContextTypeType contextType = ModelUtils.findIdentifiableElement(applicationContextTypes, contextTypeKey); //$NON-NLS-1$
         if(contextType == null)
         {
            IConfigurationElement contextConfig = dataTypesConfig.get(contextTypeKey);
            CreateMetaTypeCommand createContextTypeCommand = new CreateMetaTypeCommand(
                  contextConfig, CarnotWorkflowModelPackage.eINSTANCE.getApplicationContextTypeType(),
                     new EStructuralFeature[] {
                           CarnotWorkflowModelPackage.eINSTANCE
                                 .getApplicationContextTypeType_HasMappingId(),
                           CarnotWorkflowModelPackage.eINSTANCE
                                 .getApplicationContextTypeType_HasApplicationPath()});
            createContextTypeCommand.setParent(model);
            command.add(createContextTypeCommand);
         }
      }
   }

   private void createMissingDataTypes(CompoundCommand command)
   {
      String[] dmsDataTypeKeys = {
            DmsConstants.DATA_TYPE_DMS_DOCUMENT,
            DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST,
            DmsConstants.DATA_TYPE_DMS_FOLDER,
            DmsConstants.DATA_TYPE_DMS_FOLDER_LIST,
      };

      Map<String, IConfigurationElement> dataTypesConfig
         = SpiExtensionRegistry.instance().getExtensions(CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID);
      ModelType model = editor.getWorkflowModel();

      EList<DataTypeType> modelDataTypes = model.getDataType();
      for(String dmsDataTypeKey: dmsDataTypeKeys)
      {
         DataTypeType dmsDataType = ModelUtils.findIdentifiableElement(modelDataTypes, dmsDataTypeKey); //$NON-NLS-1$
         if(dmsDataType == null)
         {
            IConfigurationElement dataConfig = dataTypesConfig.get(dmsDataTypeKey);
            CreateMetaTypeCommand createDataTypeCommand = new CreateMetaTypeCommand(
                  dataConfig, CarnotWorkflowModelPackage.eINSTANCE.getDataTypeType(),
                  new EStructuralFeature[] {});
            createDataTypeCommand.setParent(model);
            command.add(createDataTypeCommand);
         }
      }
   }

   private void createModifiedValidatorsCmds(CompoundCommand command)
   {
      ModelType model = editor.getWorkflowModel();

      EventActionTypeType eventAction = (EventActionTypeType) ModelUtils
            .findIdentifiableElement(model.getEventActionType(),
                  PredefinedConstants.TRIGGER_ACTION);
      createModifiedValidatorCmd(command, eventAction,
            PredefinedConstants.VALIDATOR_CLASS_ATT,
            "org.eclipse.stardust.engine.core.extensions.actions.trigger.TriggerActionValidator"); //$NON-NLS-1$

      eventAction = (EventActionTypeType) ModelUtils.findIdentifiableElement(model
            .getEventActionType(), PredefinedConstants.MAIL_ACTION);
      createModifiedValidatorCmd(command, eventAction,
            PredefinedConstants.VALIDATOR_CLASS_ATT,
            "org.eclipse.stardust.engine.extensions.mail.action.sendmail.MailActionValidator"); //$NON-NLS-1$

      ApplicationContextTypeType appContext = (ApplicationContextTypeType) ModelUtils
            .findIdentifiableElement(model.getApplicationContextType(),
                  PredefinedConstants.JFC_CONTEXT);
      createModifiedValidatorCmd(command, appContext,
            PredefinedConstants.VALIDATOR_CLASS_ATT,
            "org.eclipse.stardust.engine.core.extensions.interactive.contexts.jfc.JFCValidator"); //$NON-NLS-1$

      appContext = (ApplicationContextTypeType) ModelUtils.findIdentifiableElement(model
            .getApplicationContextType(), PredefinedConstants.JSP_CONTEXT);
      createModifiedValidatorCmd(command, appContext,
            PredefinedConstants.VALIDATOR_CLASS_ATT,
            "org.eclipse.stardust.engine.extensions.web.jsp.contexts.JSPValidator"); //$NON-NLS-1$
   }

   private void createModifiedValidatorCmd(CompoundCommand command,
         IExtensibleElement eventAction, String validatorAttrName,
         String validatorClassName)
   {
      if (eventAction == null)
      {
         // nothing to do
         return;
      }
      AttributeType validatorAttr = AttributeUtil.getAttribute(eventAction,
            validatorAttrName);
      if (null == validatorAttr)
      {
         validatorAttr = AttributeUtil.createAttribute(validatorAttrName);
         validatorAttr.setValue(validatorClassName);
         command.add(new SetValueCmd(eventAction, CarnotWorkflowModelPackage.eINSTANCE
               .getIExtensibleElement_Attribute(), validatorAttr));
      }
      else if ( !validatorAttr.getAttributeValue().equals(validatorClassName))
      {
         command.add(new SetValueCmd(validatorAttr, CarnotWorkflowModelPackage.eINSTANCE
               .getAttributeType_Value(), validatorClassName));
      }

   }

   private void createUpdatePredefinedDataCmd(CompoundCommand command, DataType data)
   {
      if (data != null)
      {
         Map map = new HashMap();
         map.put(PredefinedConstants.HOME_INTERFACE_ATT,
               "ag.carnot.workflow.runtime.UserHome"); //$NON-NLS-1$
         map.put(PredefinedConstants.REMOTE_INTERFACE_ATT,
               "ag.carnot.workflow.runtime.beans.IUser"); //$NON-NLS-1$
         map.put(PredefinedConstants.PRIMARY_KEY_ATT, "ag.carnot.workflow.runtime.UserPK"); //$NON-NLS-1$
         map.put(PredefinedConstants.JNDI_PATH_ATT, "ag.carnot.workflow.runtime.User"); //$NON-NLS-1$

         for (Iterator iter = map.keySet().iterator(); iter.hasNext();)
         {
            String attName = (String) iter.next();
            String attValue = (String) map.get(attName);

            AttributeType attribute = AttributeUtil.getAttribute(data, attName);
            if (!attribute.getValue().equals(attValue))
            {
               command.add(new SetValueCmd(attribute,
                     CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
                     attValue));
            }
         }
         if (!data.isPredefined())
         {
            command.add(new SetValueCmd(data, CarnotWorkflowModelPackage.eINSTANCE
                  .getDataType_Predefined(), true));
         }
      }
   }
}