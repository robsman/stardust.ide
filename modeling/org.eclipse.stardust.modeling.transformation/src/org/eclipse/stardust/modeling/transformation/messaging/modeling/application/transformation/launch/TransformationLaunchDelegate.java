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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.launch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.transformation.messaging.format.FormatManager;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.Constants;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.launch.ProcessingLauncherConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.w3c.dom.Document;

import ag.carnot.base.CollectionUtils;
import ag.carnot.bpm.rt.data.structured.IXPathMap;
import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;
import ag.carnot.bpm.rt.data.structured.StructuredDataConverter;
import ag.carnot.utils.xml.XmlUtils;

import com.infinity.bpm.messaging.format.IMessageFormat;
import com.infinity.bpm.messaging.format.ParsingException;
import com.infinity.bpm.messaging.javascript.JScriptManager3;
import com.infinity.bpm.messaging.model.MappingModelUtil;
import com.infinity.bpm.messaging.model.mapping.FieldMapping;
import com.infinity.bpm.messaging.model.mapping.TransformationProperty;

public class TransformationLaunchDelegate extends LaunchConfigurationDelegate
{
   private Document schemaDocument;
   private ModelType model;
   private JScriptManager3 jsManager;
   private ApplicationType applicationType;
   private TransformationProperty trafoProp;
   private List fieldMappings;
   private boolean containsPrimitive;

   public void launch(ILaunchConfiguration configuration, String mode, final ILaunch launch,
         IProgressMonitor monitor) throws CoreException
   {
      MessageConsole myConsole = new MessageConsole(Modeling_Messages.CSL_OP_CSL, null);
      ConsolePlugin.getDefault().getConsoleManager().addConsoles(
            new IConsole[] {myConsole});
      final MessageConsoleStream msgStream = myConsole.newMessageStream();

      // Retrieving the ProcessingLaunchconfigs to be executed
      final ILaunchManager launchManager = org.eclipse.debug.core.DebugPlugin.getDefault()
            .getLaunchManager();
      ILaunchConfiguration[] launchConfigurations = launchManager.getLaunchConfigurations();
      List launchers = configuration.getAttribute(
            TransformationLauncherConstants.INPUT_MESSAGE_CONFIGURATION, new ArrayList());
      Map processingLauncherMap = new HashMap();
      for (int i = 0; i < launchConfigurations.length; i++ )
      {
         ILaunchConfiguration launchConfiguration = launchConfigurations[i];
         for (Iterator j = launchers.iterator(); j.hasNext();)
         {
            String launcher = (String) j.next();
            String[] launchertupel = launcher.split(","); //$NON-NLS-1$
            if (launchConfiguration.getName().equalsIgnoreCase(launchertupel[1]))
            {
               processingLauncherMap.put(launchertupel[0], launchConfiguration);
            }
         }
      }

      // Loading Model
      final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
            configuration.getAttribute(TransformationLauncherConstants.PROJECT_NAME, "")); //$NON-NLS-1$
      model = null;
      String modelUri = configuration.getAttribute(
            TransformationLauncherConstants.PROCESS_MODEL_FILE_URI, ""); //$NON-NLS-1$
      try
      {
         model = loadModel(modelUri);
      }
      catch (Exception e)
      {
         throw new RuntimeException(Modeling_Messages.EXC_MD_NOT_LOD, e);
      }

      // Executing processing launchers
      Map inputMessage = null;
      final Map inputMessagesMap = new HashMap();
      for (Iterator i = processingLauncherMap.keySet().iterator(); i.hasNext();)
      {
         String messageID = (String) i.next();
         ILaunchConfiguration processingLaunchConfiguration = (ILaunchConfiguration) processingLauncherMap.get(messageID);
         try
         {
            FileInputStream fis = new FileInputStream(
                  processingLaunchConfiguration.getAttribute(
                        ProcessingLauncherConstants.SOURCE_FILE_PATH, (String) null));
            String sourceFormat = processingLaunchConfiguration.getAttribute(
                  ProcessingLauncherConstants.SOURCE_FORMAT, (String) null);
            String declaredTypeId = processingLaunchConfiguration.getAttribute(
                  ProcessingLauncherConstants.LEGO_DATA, (String) null);
            TypeDeclarationType typeDeclaration = model.getTypeDeclarations().getTypeDeclaration(declaredTypeId);
            schemaDocument = typeDeclaration.getSchema().getDocument();
            if (schemaDocument != null)
            {
               inputMessage = performMessageParsing(fis, sourceFormat, schemaDocument,
                     declaredTypeId, processingLaunchConfiguration);
            }
            inputMessagesMap.put(messageID, inputMessage);
         }
         catch (FileNotFoundException e)
         {
            throw new RuntimeException(Modeling_Messages.EXC_IMPO_FILE_NOT_FOUND, e);
         }
         catch (ParsingException e)
         {
            throw new RuntimeException(Modeling_Messages.EXC_PB_PR_FILE_CONTENT, e);
         }
         catch (RuntimeException e)
         {
             throw e;
         }
         catch (Throwable e)
         {
            throw new RuntimeException(Modeling_Messages.EXC_PB_PR_FILE_CONTENT, e);
         }
      }

      // Executing the message transformations
      final String applicationID = configuration.getAttribute(
            TransformationLauncherConstants.APPLICATION_ID, ""); //$NON-NLS-1$
      final Map/* <String, Object> */outputMessages = CollectionUtils.newMap();

      //checkForPrimitives(model, applicationID, outputMessages);

      // Execution has to be done in its own thread in order to prevent pending
      // "startup launch message" on the botton of the workbench.
      Thread thread = new Thread(new Runnable()
      {
         public void run()
         {
            try
            {
               initializeModel(model, applicationID, outputMessages);

               if (containsPrimitive) {
            	   msgStream.println(Modeling_Messages.MSG_WR_CURR_MSG_TRANS_LAUNCHERS_NOT_SUPPORTED_USAGE_PRIMI_DATA);
            	   msgStream.println(Modeling_Messages.MSG_MAPPING_CONTAIN_PRIMI_MSG_IGN);
            	   containsPrimitive = false;
               }

               jsManager.initializeContext(inputMessagesMap, outputMessages, new ArrayList());
               initOnDebug(launch, project, getApplicationType());

               for (int i = 0; i < fieldMappings.size(); i++)
               {
                  FieldMapping fieldMapping = (FieldMapping) fieldMappings.get(i);

                  if ( !initBeforeNextScript(fieldMapping.getFieldPath()))
                  {
                     msgStream.println(Modeling_Messages.MSG_SCRIPT_DEB_TERM);
                     break;
                  }

                  String type;
                  if (fieldMapping.isAdvancedMapping())
                  {
                     type = "advanced"; //$NON-NLS-1$
                  }
                  else
                  {
                     type = "basic"; //$NON-NLS-1$

                  }
                  msgStream.println("Field '"+fieldMapping.getFieldPath()+"', "+type+" expression: " + fieldMapping.getMappingExpression()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

                  Object result = jsManager.executeMapping(fieldMapping.getMappingExpression());
                  if (fieldMapping.isAdvancedMapping())
                  {
                     // In case of advanced mappings, no explicit result is available, since the
                     // javascript contains statements and not expressions!
                     // Here we just output the contents of the field after transformation.
                     // Indeed, the statements can also change other fields!
                     result = jsManager.executeMapping(fieldMapping.getFieldPath().replace('/', '.'));
                  }

                  String stringifiedResult = null == result ? "<null>" : result //$NON-NLS-1$
                        .toString();
                  msgStream.println(Modeling_Messages.MSG_RESULT + stringifiedResult);
                  msgStream.println("---"); //$NON-NLS-1$
               }
            }
            catch (CoreException e)
            {
               e.printStackTrace();
            }
            finally
            {
               try
               {
                  launch.terminate();
                  launchManager.removeLaunch(launch);
               }
               catch (DebugException e)
               {
                  e.printStackTrace();
               }
            }
         }
      });

      thread.start();
   }

   protected void initOnDebug(ILaunch launch, IProject project, ApplicationType applicationType) throws CoreException
   {
   }

   protected boolean initBeforeNextScript(String fieldPath) throws CoreException
   {
      return true;
   }

   public ModelType loadModel(String modelUri) throws Exception
   {
      WorkflowModelManager modelManager = new WorkflowModelManager();
      modelManager.load(URI.createURI(modelUri));
      ModelType model = modelManager.getModel();
      return model;
   }

   public Map performMessageParsing(FileInputStream fis, String sourceFormat,
         Object schemaDocument, String legoTypeName,
         ILaunchConfiguration launchConfiguration) throws ParsingException
   {
      Map outputMessage = null;

      try
      {
         IMessageFormat messageFormat = FormatManager.getMessageFormat(sourceFormat,
               launchConfiguration);
         Document sourceDoc = messageFormat.parse(fis, schemaDocument);

         IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(model, legoTypeName);
         StructuredDataConverter structuredDataConverter = new StructuredDataConverter(
               xPathMap);

         String serializedDoc = XmlUtils.toString(sourceDoc);
         outputMessage = (Map) structuredDataConverter.toCollection(
               serializedDoc, "", true); //$NON-NLS-1$
      }
      catch (Exception e1)
      {

      }
      return outputMessage;
   }

   protected ApplicationType getApplicationType()
   {
      return applicationType;
   }

   protected JScriptManager3 getJsManager()
   {
      return jsManager;
   }

   private void initializeModel(ModelType model, String appId, Map outputMessages)
   {
      applicationType = this.getApplicationByID(model, appId);
      jsManager = new JScriptManager3();

      for (int i=0; i<applicationType.getAccessPoint().size(); i++)
      {
    	 AccessPointType accessPoint = (AccessPointType)applicationType.getAccessPoint().get(i);
         String declaredTypeId = AttributeUtil.getAttributeValue(accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT);
         TypeDeclarationType typeDeclaration = ModelUtils.getTypeDeclaration((IModelElement)applicationType, declaredTypeId);
         if (accessPoint.getType().getId().equalsIgnoreCase("struct")) //$NON-NLS-1$
         {
             IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(model, typeDeclaration.getId());
             if (accessPoint.getDirection().equals(DirectionType.IN_LITERAL))
             {
                jsManager.registerInAccessPointType(accessPoint.getId(), xPathMap.getRootXPath());
             }
             else
             {
                jsManager.registerOutAccessPointType(accessPoint.getId(), xPathMap.getRootXPath());
                outputMessages.put(accessPoint.getId(), CollectionUtils.newMap());
             }
         } else {
        	 containsPrimitive = true;
         }
      }

      String xmlString = AttributeUtil.getAttributeValue(
            (IExtensibleElement) applicationType, Constants.TRANSFORMATION_PROPERTY);

      if (xmlString != null)
      {
         trafoProp = (TransformationProperty) MappingModelUtil
               .transformXML2Ecore(xmlString.getBytes());

         this.fieldMappings = trafoProp.getFieldMappings();
      }
      else
      {
         throw new RuntimeException(Modeling_Messages.EXC_MESSAGE_TRANSF_APP_NOT_CONFI_PROPERLY);
      }
   }

   private ApplicationType getApplicationByID(ModelType modelType, String applicationID)
   {
      List applications = modelType.getApplication();
      for (Iterator i = applications.iterator(); i.hasNext();)
      {
         ApplicationType appType = (ApplicationType) i.next();
         if (appType.getId().equalsIgnoreCase(applicationID))
         {
            return appType;
         }
      }
      return null;
   }
}
