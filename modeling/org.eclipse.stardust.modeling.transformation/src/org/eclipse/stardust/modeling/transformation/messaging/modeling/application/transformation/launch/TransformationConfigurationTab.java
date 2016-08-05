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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.debug.CwmFileSelectionDialog;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;
import org.eclipse.wst.jsdt.ui.JavaScriptElementLabelProvider;



public class TransformationConfigurationTab extends AbstractLaunchConfigurationTab
      implements Listener
{
   private final static String MESSAGE_ID = "MessageId"; //$NON-NLS-1$
   private final static String MESSAGE_TYPE = "MessageType"; //$NON-NLS-1$
   private final static String CONFIGURATION_ID = "ConfigurationID"; //$NON-NLS-1$

   private final static String[] COLUMN_PROPERTIES = new String[] { MESSAGE_ID,
         MESSAGE_TYPE, CONFIGURATION_ID };

   private Text projectNameText;
   private Text processModelText;
   private ComboViewer transformationApplicationViewer;
   private Button browseProjectsButton;
   private Button browseProcessModelsButton;
   private TableViewer tableViewer;
   private ModelType model;
   private String[] configurationIds;
   private List configurationList;
   private List launcherIDList = new ArrayList();
   private String modelUri;

   public TransformationConfigurationTab()
   {
      ILaunchManager lm = DebugPlugin.getDefault().getLaunchManager();
      ILaunchConfigurationType ct = lm
            .getLaunchConfigurationType("org.eclipse.stardust.modeling.transformation.application.launch.testType"); //$NON-NLS-1$
      try
      {
         ILaunchConfiguration[] cfgs = lm.getLaunchConfigurations(ct);
         configurationIds = new String[cfgs.length + 1];

         configurationIds[0] = ""; //$NON-NLS-1$

         for (int n = 0; n < cfgs.length; ++n)
         {
            configurationIds[n + 1] = cfgs[n].getName();
         }
      }
      catch (CoreException e)
      {
         throw new RuntimeException(Modeling_Messages.EXC_CANNOT_RETRIEVE_LAUNCH_CFG, e);
      }
   }

   public void createControl(Composite parent)
   {
      // Create widgets

      Composite composite = FormBuilder.createComposite(parent, 1);

      setControl(composite);

      final Group group = new Group(composite, SWT.NONE);

      group.setText(Modeling_Messages.TXT_TRANSF_SEL);

      Label projectNameLabel = new Label(group, 0);

      projectNameLabel.setText(Modeling_Messages.TXT_PROJECT_NAME);

      projectNameText = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);

      browseProjectsButton = new Button(group, 0);

      browseProjectsButton.setText(Modeling_Messages.TXT_BW_DREI_PUNKT);
      browseProjectsButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            browseProjects();
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {
         }
      });

      Label processModelLabel = new Label(group, 0);

      processModelLabel.setText(Modeling_Messages.TXT_PRC_MD);

      processModelText = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);

      browseProcessModelsButton = new Button(group, 0);

      browseProcessModelsButton.setText(Modeling_Messages.TXT_BW_DREI_PUNKT);
      browseProcessModelsButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            browseModelFiles();
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {
         }
      });

      Label messageTransformationApplicationLabel = new Label(group, 0);

      messageTransformationApplicationLabel
            .setText(Modeling_Messages.TXT_MSG_TRANSF_APP);

      transformationApplicationViewer = new ComboViewer(group);

      transformationApplicationViewer.setContentProvider(new ArrayContentProvider());
      transformationApplicationViewer.setLabelProvider(new LabelProvider()
      {
         public Image getImage(Object element)
         {
            return null;
         }

         public String getText(Object element)
         {
            if (element instanceof ApplicationType)
            {
               return ((ApplicationType) element).getName();
            }

            return null;
         }
      });

      transformationApplicationViewer.getCombo().addListener(SWT.Selection, this);

      final Group inputMessagesGroup = new Group(composite, SWT.NONE);

      inputMessagesGroup.setText(Modeling_Messages.TXT_IP_MSG);

      Table table = new Table(inputMessagesGroup, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
            | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);

      table.setHeaderVisible(true);

      TableColumn column = new TableColumn(table, SWT.LEFT, 0);

      column.setText(Modeling_Messages.TXT_IP_MSG_ID);
      column.setWidth(150);

      column = new TableColumn(table, SWT.LEFT, 1);

      column.setText(Modeling_Messages.TXT_IP_MSG_TYPE);
      column.setWidth(150);

      column = new TableColumn(table, SWT.LEFT, 2);

      column.setText(Modeling_Messages.TXT_LAUNCH_CFG_ID);
      column.setWidth(200);

      tableViewer = new TableViewer(table);

      tableViewer.setUseHashlookup(true);
      tableViewer.setColumnProperties(COLUMN_PROPERTIES);
      tableViewer.setLabelProvider(new InputMessageConfigurationLabelProvider());
      tableViewer.setContentProvider(new ArrayContentProvider());

      // Create the cell editors

      CellEditor[] editors = new CellEditor[3];

      editors[0] = new TextCellEditor(table);
      editors[1] = new TextCellEditor(table);
      editors[2] = new ComboBoxCellEditor(table, configurationIds, SWT.READ_ONLY);

      tableViewer.setCellEditors(editors);
      tableViewer.setCellModifier(new InputMessageConfigurationCellModifier());

      // Layout widgets

      FormLayout layout = new FormLayout();

      layout.marginHeight = 10;
      layout.marginWidth = 10;

      group.setLayout(layout);

      FormData formData = new FormData();

      formData.left = new FormAttachment(0, 0);
      formData.top = new FormAttachment(0, 0);

      projectNameLabel.setLayoutData(formData);

      formData = new FormData();

      formData.left = new FormAttachment(messageTransformationApplicationLabel, 5,
            SWT.RIGHT);
      formData.top = new FormAttachment(projectNameLabel, 0, SWT.TOP);
      formData.width = 300;

      projectNameText.setLayoutData(formData);

      formData = new FormData();

      formData.left = new FormAttachment(projectNameText, 5, SWT.RIGHT);
      formData.top = new FormAttachment(projectNameText, 0, SWT.TOP);
      

      browseProjectsButton.setLayoutData(formData);

      formData = new FormData();

      formData.left = new FormAttachment(0, 0);
      formData.top = new FormAttachment(projectNameText, 5, SWT.BOTTOM);
      

      processModelLabel.setLayoutData(formData);

      formData = new FormData();

      formData.left = new FormAttachment(messageTransformationApplicationLabel, 5,
            SWT.RIGHT);
      formData.top = new FormAttachment(processModelLabel, 0, SWT.TOP);
      formData.width = 300;

      processModelText.setLayoutData(formData);

      formData = new FormData();

      formData.left = new FormAttachment(processModelText, 5, SWT.RIGHT);
      formData.top = new FormAttachment(processModelText, 0, SWT.TOP);

      browseProcessModelsButton.setLayoutData(formData);

      formData = new FormData();

      formData.left = new FormAttachment(0, 0);
      formData.top = new FormAttachment(processModelText, 5, SWT.BOTTOM);

      messageTransformationApplicationLabel.setLayoutData(formData);

      formData = new FormData();

      formData.left = new FormAttachment(messageTransformationApplicationLabel, 5,
            SWT.RIGHT);
      formData.top = new FormAttachment(messageTransformationApplicationLabel, 0, SWT.TOP);
      formData.width = 300;

      transformationApplicationViewer.getControl().setLayoutData(formData);

      layout = new FormLayout();

      layout.marginHeight = 10;
      layout.marginWidth = 10;

      inputMessagesGroup.setLayout(layout);

      formData = new FormData();

      formData.left = new FormAttachment(0, 0);
      formData.top = new FormAttachment(0, 0);

      tableViewer.getControl().setLayoutData(formData);
   }
   public String getName()
   {
      return Modeling_Messages.LBL_MESSAGE_TRANSFORMATION_TEST_CONFIGURATION;
   }

   public void initializeFrom(ILaunchConfiguration configuration)
   {
      try
      {         
         launcherIDList = configuration.getAttribute(
               TransformationLauncherConstants.INPUT_MESSAGE_CONFIGURATION,
               new ArrayList());
         List toBeRemoved = new ArrayList();
         for (Iterator i = launcherIDList.iterator(); i.hasNext();) {            
            String launcherID = (String) i.next(); 
            boolean exists = false;
            for (int j = 0; j < configurationIds.length; j++) {
               String id = configurationIds[j];
               String launcherString = launcherID.substring(launcherID.indexOf(",") + 1); //$NON-NLS-1$
               if (id.equalsIgnoreCase(launcherString)) {
                  exists = true;
               }              
            }
            if (!exists) {
               toBeRemoved.add(launcherID);
            }
         }
         launcherIDList.removeAll(toBeRemoved);
         
         String projectName = configuration.getAttribute(
               TransformationLauncherConstants.PROJECT_NAME, ""); //$NON-NLS-1$
         projectNameText.setText(projectName);
         URI uri = URI.createURI(configuration.getAttribute(
               TransformationLauncherConstants.PROCESS_MODEL_FILE_URI, "")); //$NON-NLS-1$
         //modelUri = configuration.getAttribute(ProcessingLauncherConstants.MODEL_URI, ""); //$NON-NLS-1$
         modelUri = uri.toString();
         String modelPathRelativeToProject = uri.path();
         int idx = modelPathRelativeToProject.lastIndexOf(projectName);
         if (idx != -1
               && idx + projectName.length() + 1 < modelPathRelativeToProject.length())
         {
            modelPathRelativeToProject = modelPathRelativeToProject.substring(idx
                  + projectName.length() + 1);
         }        
         if (projectNameText.getText().length() != 0 && modelPathRelativeToProject.indexOf("xpdl") > 0) //$NON-NLS-1$
         {
            processModelText.setText(modelPathRelativeToProject);
            updateProcessModel();
            String applicationId = configuration.getAttribute(
                  TransformationLauncherConstants.APPLICATION_ID, ""); //$NON-NLS-1$
            if (applicationId != null && applicationId.length() != 0)
            {
               ApplicationType application = ModelUtils.findElementById(model.getApplication(), applicationId);
               if (application != null)
               {
                  transformationApplicationViewer.setSelection(new StructuredSelection(
                        application));
                  updateInputMessagesTable();
               }
            }
         }
      }
      catch (CoreException e)
      {
         throw new RuntimeException(
               Modeling_Messages.EXC_CANNOT_RD_MSG_TRANSF_LAUNCHER_CFG, e);
      }
   }

   public void performApply(ILaunchConfigurationWorkingCopy configuration)
   {
      configuration.setAttribute(TransformationLauncherConstants.PROJECT_NAME,
            projectNameText.getText());
      String modelUri = URI.createPlatformResourceURI(
            projectNameText.getText() + "/" + processModelText.getText(), false) //$NON-NLS-1$
            .toString();
      configuration.setAttribute(TransformationLauncherConstants.PROCESS_MODEL_FILE_URI,
            modelUri);
      configuration.setAttribute(
            TransformationLauncherConstants.INPUT_MESSAGE_CONFIGURATION, launcherIDList);
      if (transformationApplicationViewer.getSelection() != null
            && ((IStructuredSelection) transformationApplicationViewer.getSelection())
                  .getFirstElement() != null)
      {
         configuration.setAttribute(TransformationLauncherConstants.APPLICATION_ID,
               ((ApplicationType) ((IStructuredSelection) transformationApplicationViewer
                     .getSelection()).getFirstElement()).getId());
      }
   }

   public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
   {
   }

   public boolean isValid(ILaunchConfiguration launchConfig)
   {
      if (StringUtils.isEmpty(projectNameText.getText())) {
         this.setErrorMessage(Modeling_Messages.MSG_NO_PROJECT_SEL);
         return false;
      }
      if (StringUtils.isEmpty(processModelText.getText())) {
         this.setErrorMessage(Modeling_Messages.MSG_NO_PROJECT_MD_SEL);
         return false;
      }
      if (StringUtils.isEmpty(transformationApplicationViewer.getCombo().getText())) {
         this.setErrorMessage(Modeling_Messages.MSG_TRANSF_APP_SEL_MISSING);
         return false;
      }
      if (configurationList.isEmpty()) {
         this.setErrorMessage(Modeling_Messages.MSG_PRC_LAUNCHER_SEL_MISSING);
         return false;
      }
      for (Iterator i = configurationList.iterator(); i.hasNext();) {
         InputMessageConfiguration conf = (InputMessageConfiguration) i.next(); 
         if (StringUtils.isEmpty(conf.getConfigurationId())) {
            this.setErrorMessage(Modeling_Messages.MSG_PRC_LAUNCHER_SEL_MISSING);
            return false;
         }
      }
      setErrorMessage(null);
      return true;
   }

   public void handleEvent(Event event)
   {
      if (event.widget == transformationApplicationViewer.getCombo())
      {
         updateInputMessagesTable();
         updateLaunchConfigurationDialog();
      }
   }

   private void browseProjects()
   {
      IJavaProject[] projects;

      try
      {
         projects = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot())
               .getJavaProjects();
      }
      catch (JavaModelException e)
      {
         projects = new IJavaProject[0];
      }

      ILabelProvider labelProvider = new JavaScriptElementLabelProvider(
            JavaScriptElementLabelProvider.SHOW_DEFAULT);
      ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(),
            labelProvider);
      dialog.setTitle(Modeling_Messages.DIA_MSG_TRANS_RUN_CFG);
      dialog.setElements(projects);

      String projectName = projectNameText.getText();

      if ( !StringUtils.isEmpty(projectName))
      {
         IJavaProject javaProject = JavaCore.create(ResourcesPlugin.getWorkspace()
               .getRoot().getProject(projectName));
         if (javaProject != null)
         {
            dialog.setInitialSelections(new Object[] { javaProject });
         }
      }

      if (dialog.open() == Window.OK)
      {
         IJavaProject javaProject = (IJavaProject) dialog.getFirstResult();

         if (null != javaProject)
         {
            updateLaunchConfigurationDialog();
            projectNameText.setText(javaProject.getElementName());
            processModelText.setText(""); //$NON-NLS-1$
            transformationApplicationViewer.setInput(null);
            tableViewer.setInput(null);
         }
         else
         {
            projectNameText.setText(""); //$NON-NLS-1$
         }
      }
      updateLaunchConfigurationDialog();
   }

   private void browseModelFiles()
   {
      String projectName = projectNameText.getText();
      if (StringUtils.isEmpty(projectName)) {
         MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
               SWT.ICON_WARNING | SWT.CANCEL);
         messageBox.setText(Modeling_Messages.TXT_WR_LEER);
         messageBox.setMessage(Modeling_Messages.MSG_FIRST_NEED_SELECT_PROJECT);
         messageBox.open();
      } else {
         ResourceListSelectionDialog dialog = new CwmFileSelectionDialog(getShell(),
               ResourcesPlugin.getWorkspace().getRoot().getProject(projectName),
               IResource.FILE);
         if (dialog.open() == Window.OK)
         {
            Object[] modelFiles = dialog.getResult();
            IFile modelFile = (IFile) modelFiles[0];
            
            modelUri = URI.createPlatformResourceURI(
                  modelFile.getFullPath().toString(), true).toString();   
            String fullPath = modelFile.getFullPath().toString();

            processModelText.setText(fullPath.substring(projectName.length() + 2));
            updateLaunchConfigurationDialog();
            updateProcessModel();       
         }         
      }         
   }
   
   /*private void browseModelFiles()
   {
       String projectName = projectText.getText();
       if (StringUtils.isEmpty(projectName)) {
          MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
                SWT.ICON_WARNING | SWT.CANCEL);
          messageBox.setText(Modeling_Messages.TXT_WR_LEER);
          messageBox.setMessage(Modeling_Messages.MSG_FIRST_NEED_SELECTED_PROJECT);
          messageBox.open();
       } else {
          ResourceListSelectionDialog dialog = new CwmFileSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot()
                .getProject(projectName), IResource.FILE);

          if (dialog.open() == Window.OK)
          {
             Object[] files = dialog.getResult();
             IFile modelFile = (IFile) files[0];
             modelUri = URI.createPlatformResourceURI(
                   modelFile.getFullPath().toString(), true).toString();
    
             String fullPath = modelFile.getFullPath().toString();
             modelText.setText(fullPath.substring(projectName.length() + 2));
             updateLaunchConfigurationDialog();
             //updateProcessModel();
             updateStructruredData();
             //updateLaunchConfigurationDialog();
          }
          
       }
   }*/

   private void updateProcessModel()
   {
      String projectName = projectNameText.getText().trim();
      String modelFile = processModelText.getText().trim();

      if (projectName.length() != 0 && modelFile.length() != 0)
      {
         try
         {
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
                  projectName);
            if (null == project)
            {
               throw new RuntimeException(Modeling_Messages.EXC_PROJECT_NOT_SET);
            }
            //modelFile = modelFile.replaceAll("%20", " ");
            IFile file = project.getFile(new Path(modelFile));
            if ( !file.exists())
            {
               throw new RuntimeException(Modeling_Messages.EXC_FILE_DOES_NOT_EXIST);
            }
            //modelUri=URI.createFileURI(file.getFullPath().toString());
            model = loadModel(modelUri);
            updateMessageTransformationApplications();
         }
         catch (Exception ex)
         {
            throw new RuntimeException(ex);
         }
      }
   }

   private void updateMessageTransformationApplications()
   {
      List list = new ArrayList();
      for (int i=0; i<model.getApplication().size(); i++)
      {
         ApplicationType application = (ApplicationType)model.getApplication().get(i);
         // select non-interactive apps only (interactive return null as type (!) 
         if ( !application.isInteractive())
         {
            String appType = application.getType().getId();
            if (appType.equalsIgnoreCase("messageTransformationBean")) { //$NON-NLS-1$
               list.add(application);
            }
         }
      }
      transformationApplicationViewer.setInput(list);
   }

   private void updateInputMessagesTable()
   {
      if (transformationApplicationViewer.getSelection() != null)
      {
         ApplicationType application = (ApplicationType) ((IStructuredSelection) transformationApplicationViewer
               .getSelection()).getFirstElement();
         if (application != null)
         {
            configurationList = new ArrayList();

            for (Iterator i = application.getAccessPoint().iterator(); i.hasNext(); )
            {
               AccessPointType accessPoint = (AccessPointType)i.next();
               if (accessPoint.getDirection().getName().equals(DirectionType.IN_LITERAL.getName())) 
               {
                  String declaredTypeId = AttributeUtil.getAttribute(accessPoint, StructuredDataConstants.TYPE_DECLARATION_ATT).getValue();
                  String configName = "";                                     //$NON-NLS-1$
                  for (Iterator l = launcherIDList.iterator(); l.hasNext();)
                  {                     
                     String[] tuple = l.next().toString().split(","); //$NON-NLS-1$
                     if (accessPoint.getId().equalsIgnoreCase(tuple[0]))
                     {
                        if (tuple.length > 1) {
                           configName = tuple[1];                           
                        }
                     }
                  }
                  configurationList.add(new InputMessageConfiguration(declaredTypeId, accessPoint.getId(), configName));
               }
            }
            tableViewer.setInput(configurationList);
         }
      }
   }

   private int getConfigurationIndex(String value)
   {
      if (value == null || value.length() == 0)
      {
         return 0;
      }

      for (int n = 0; n < configurationIds.length; ++n)
      {
         if (configurationIds[n].equals(value))
         {
            return n;
         }
      }

      throw new IllegalArgumentException(MessageFormat.format(Modeling_Messages.EXC_CFG_DOES_NOT_EXIST, new Object[]{value}));
   }

   public ModelType loadModel(String modelUri) throws Exception
   {
         WorkflowModelManager modelManager = new WorkflowModelManager();
         modelManager.load(URI.createURI(modelUri));
         ModelType model = modelManager.getModel();
         return model;
   }
   
   private static class InputMessageConfigurationLabelProvider extends LabelProvider
         implements ITableLabelProvider
   {
      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }

      public String getColumnText(Object element, int columnIndex)
      {
         InputMessageConfiguration dataMapping = (InputMessageConfiguration) element;

         if (columnIndex == 0)
         {
            return dataMapping.getInputMessageId();
         }
         else if (columnIndex == 1)
         {
            return dataMapping.getInputMessageType();
         }
         else if (columnIndex == 2)
         {
            return dataMapping.getConfigurationId();
         }
         else
         {
            throw new IllegalArgumentException(Modeling_Messages.EXC_COLUM_INDEX_EXCEEDS);
         }
      }

      public String getText(Object element)
      {
         return getColumnText(element, 0);
      }

      public Image getImage(Object element)
      {
         return getColumnImage(element, 0);
      }
   }

   class InputMessageConfiguration
   {
      private String inputMessageId;

      private String inputMessageType;

      private String configurationId;

      public InputMessageConfiguration(String inputMessageID, String inputMessageType,
            String configurationID)
      {
         super();

         this.inputMessageId = inputMessageID;
         this.inputMessageType = inputMessageType;
         this.configurationId = configurationID;
      }

      public String getInputMessageId()
      {
         return inputMessageId;
      }

      public String getInputMessageType()
      {
         return inputMessageType;
      }

      public String getConfigurationId()
      {
         return configurationId;
      }

      public void setInputMessageId(String inputMessageID)
      {
         this.inputMessageId = inputMessageID;
      }

      public void setInputMessageType(String inputMessageType)
      {
         this.inputMessageType = inputMessageType;
      }

      public void setConfigurationId(String configurationID)
      {
         this.configurationId = configurationID;
      }

      public String toString()
      {
         return this.inputMessageType + "," + this.configurationId; //$NON-NLS-1$
      }
   }

   class InputMessageConfigurationCellModifier implements ICellModifier
   {
      public boolean canModify(Object element, String property)
      {
         if (property.equals(CONFIGURATION_ID))
         {
            return true;
         }

         return false;
      }

      public Object getValue(Object element, String property)
      {
        InputMessageConfiguration configuration = (InputMessageConfiguration) element;
        if (property.equals(MESSAGE_ID))
        {
           return configuration.getInputMessageId();
        }
        else if (property.equals(MESSAGE_TYPE))
        {
           return configuration.getInputMessageType();
        }
        else
        {
           return new Integer(getConfigurationIndex(configuration.getConfigurationId()));
        }
      }

      public void modify(Object element, String property, Object value)
      {
         TableItem item = (TableItem) element;
         InputMessageConfiguration configuration = (InputMessageConfiguration) item
               .getData();
         if (property.equals(CONFIGURATION_ID))
         {
            configuration.setConfigurationId(configurationIds[((Integer) value)
                  .intValue()]);
         }
         launcherIDList = new ArrayList();
         for (Iterator i = configurationList.iterator(); i.hasNext();)
         {
            launcherIDList.add(i.next().toString());
         }
         tableViewer.refresh(true);
         updateLaunchConfigurationDialog();
      }
   }

}
