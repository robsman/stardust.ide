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
package org.eclipse.stardust.modeling.core.wizards;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.ui.DiagramSelectionDialog;
import org.eclipse.stardust.modeling.core.ui.ModelElementSelectionDialog;
import org.eclipse.stardust.modeling.core.utils.ImageFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class ExportCarnotModelDiagramWizardPage extends ExportDiagramResourceWizardPage
{
   private static final String PROC_DEF_LABEL = Diagram_Messages.WIZARD_LB_Process;

   private static final String DIAGRAM_LABEL = Diagram_Messages.WIZARD_LB_Diagram;

   private static final String FILE_LABEL = Diagram_Messages.WIZARD_LB_ToFile;

   private static final String CONTAINER_BROWSE_BUTTON_LABEL = Diagram_Messages.WIZARD_LB_Browse;

   private Text fileNameField;

   private Text procDefNameField;

   private ProcessDefinitionType processDefinition;

   private Text diagramNameField;

   private List diagrams = new ArrayList();

   private Button pngRadioButton;

   private Button jpgRadioButton;

   private Button gifRadioButton;

   private List modelDiagrams = new ArrayList();

   private Text modelDiagramNameField;
   
   private WorkflowModelEditor editor;

   public ExportCarnotModelDiagramWizardPage(String pageName,
         IStructuredSelection selection, IEditorPart editorPart)
   {
      super(pageName, selection);
      editor = (WorkflowModelEditor) (editorPart instanceof WorkflowModelEditor
            ? editorPart
            : null);
   }

   protected void createDestinationGroup(Composite parent)
   {
      Composite containerGroup = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.numColumns = 3;
      containerGroup.setLayout(layout);
      containerGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
            | GridData.GRAB_HORIZONTAL));
      containerGroup.setFont(parent.getFont());

      FormBuilder.createLabel(containerGroup, Diagram_Messages.LB_ModelDiagram);
      modelDiagramNameField = FormBuilder.createText(containerGroup);
      modelDiagramNameField.setEditable(false);
      FormBuilder.createButton(containerGroup, CONTAINER_BROWSE_BUTTON_LABEL,
            new SelectionAdapter()
            {

               public void widgetSelected(SelectionEvent e)
               {
                  browseModelDiagrams();
               }

               private void browseModelDiagrams()
               {
                  List selectedResources = getSelectedResources();

                  if (selectedResources.isEmpty())
                  {
                     return;
                  }

                  IFile modelFile = (IFile) selectedResources.get(0);

                  DiagramSelectionDialog dialog = new DiagramSelectionDialog(getShell(),
                        modelFile, null, false, editor);
                  dialog.setTitle(Diagram_Messages.TITLE_ModelDialog);
                  dialog.setMessage(MessageFormat.format(
                        Diagram_Messages.WIZARD_MSG_SelectDiagram,
                        new String[] {modelFile.toString()}));

                  // TODO: single select
                  if (dialog.open() == Window.OK)
                  {
                     modelDiagrams = new ArrayList(Arrays.asList(dialog.getResult()));
                     StringBuffer selectedDiagrams = new StringBuffer();
                     int i = 0;
                     for (Iterator iter = modelDiagrams.iterator(); iter.hasNext();)
                     {
                        i++;
                        selectedDiagrams
                              .append((((DiagramType) iter.next()).getName() + (modelDiagrams
                                    .size() != i ? ", " : ""))); //$NON-NLS-1$ //$NON-NLS-2$
                     }
                     modelDiagramNameField.setText(selectedDiagrams.toString());
                     updateDestinationField();
                  }
               }

            });

      // ProcessDefinition
      Label label = new Label(containerGroup, SWT.None);
      label.setText(PROC_DEF_LABEL);
      label.setFont(parent.getFont());

      procDefNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
      GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
            | GridData.GRAB_HORIZONTAL);
      data.widthHint = SIZING_TEXT_FIELD_WIDTH;
      procDefNameField.setLayoutData(data);
      procDefNameField.setFont(parent.getFont());
      procDefNameField.setEditable(false);
      procDefNameField.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent event)
         {
            setCompletionState();
         }
      });

      Button procDefBrowseButton = new Button(containerGroup, SWT.PUSH);
      procDefBrowseButton.setText(CONTAINER_BROWSE_BUTTON_LABEL);
      procDefBrowseButton.setFont(parent.getFont());
      procDefBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
      procDefBrowseButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent event)
         {
            browseProcessDefinitions();
         }

         public void widgetDefaultSelected(SelectionEvent event)
         {
            browseProcessDefinitions();
         }
      });

      // Diagram
      label = new Label(containerGroup, SWT.None);
      label.setText(DIAGRAM_LABEL);
      label.setFont(parent.getFont());

      diagramNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
      data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
      data.widthHint = SIZING_TEXT_FIELD_WIDTH;
      diagramNameField.setLayoutData(data);
      diagramNameField.setFont(parent.getFont());
      diagramNameField.setEditable(false);
      diagramNameField.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent event)
         {
            setCompletionState();
         }
      });

      Button diagramBrowseButton = new Button(containerGroup, SWT.PUSH);
      diagramBrowseButton.setText(CONTAINER_BROWSE_BUTTON_LABEL);
      diagramBrowseButton.setFont(parent.getFont());
      diagramBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
      diagramBrowseButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent event)
         {
            browseDiagrams();
         }

         public void widgetDefaultSelected(SelectionEvent event)
         {
            browseDiagrams();
         }
      });

      // Filename
      label = new Label(containerGroup, SWT.None);
      label.setText(FILE_LABEL);
      label.setFont(parent.getFont());

      fileNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
      data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
      data.widthHint = SIZING_TEXT_FIELD_WIDTH;
      fileNameField.setLayoutData(data);
      fileNameField.setFont(parent.getFont());
      fileNameField.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent event)
         {
            setCompletionState();
         }
      });

      Button containerBrowseButton = new Button(containerGroup, SWT.PUSH);
      containerBrowseButton.setText(CONTAINER_BROWSE_BUTTON_LABEL);
      containerBrowseButton.setFont(parent.getFont());
      containerBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
      containerBrowseButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent event)
         {
            browseForFile();
         }

         public void widgetDefaultSelected(SelectionEvent event)
         {
            browseForFile();
         }
      });

      setPageComplete(false); // TODO

      setTitle(Diagram_Messages.WIZARD_LB_Select);
      setDescription(Diagram_Messages.WIZARD_DESCRIPTION_Export); // TODO

      setControl(containerGroup);

      ImageDescriptor imageDescrip = WorkbenchImages
            .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_WIZ);
      setImageDescriptor(imageDescrip);
   }

   private void browseForFile()
   {
      if (getDiagram().size() <= 1)
      {
         FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);

         String[] filterExtensions;
         if (true == pngRadioButton.getSelection())
         {
            filterExtensions = new String[] {"*.png"}; //$NON-NLS-1$
         }
         else if (true == jpgRadioButton.getSelection())
         {
            filterExtensions = new String[] {"*.jpg"}; //$NON-NLS-1$
         }
         else
         {
            filterExtensions = new String[] {"*.gif"}; //$NON-NLS-1$
         }

         dialog.setFilterExtensions(filterExtensions);

         String directory = dialog.open();
         if (directory != null && new File(directory).exists())
         {
            if (!MessageDialog.openQuestion(getShell(),
                  Diagram_Messages.Title_FileExists,
                  Diagram_Messages.MSG_AnotherFileAlreadyExists))
            {
               directory = null;
            }
         }

         fileNameField.setText(directory != null ? directory : ""); //$NON-NLS-1$
      }
      else
      {
         DirectoryDialog dialog = new DirectoryDialog(getShell());
         String directory = dialog.open();
         fileNameField.setText(directory != null ? directory : ""); //$NON-NLS-1$
      }
   }

   /**
    * Open a process definition chooser
    */
   private void browseProcessDefinitions()
   {
      List selectedResources = getSelectedResources();

      if (selectedResources.isEmpty())
      {
         return;
      }

      IFile modelFile = (IFile) selectedResources.get(0);

      ModelElementSelectionDialog dialog = new ModelElementSelectionDialog(getShell(),
            modelFile, new Class[] {ProcessDefinitionType.class}, editor);
      dialog.setTitle(Diagram_Messages.TITLE_ModelDialog);
      dialog.setMessage(MessageFormat.format(Diagram_Messages.MSG_SelectProcess,
            new String[] {modelFile.toString()}));

      // TODO: single select
      if (dialog.open() == Window.OK)
      {
         Object[] modelElements = dialog.getResult();
         processDefinition = (ProcessDefinitionType) modelElements[0];
         procDefNameField.setText(processDefinition.getName());

         diagrams = null;
         diagramNameField.setText(""); //$NON-NLS-1$

      }
   }

   /**
    * Open a diagram chooser
    */
   private void browseDiagrams()
   {
      List selectedResources = getSelectedResources();

      if (selectedResources.isEmpty())
      {
         return;
      }

      IFile modelFile = (IFile) selectedResources.get(0);

      DiagramSelectionDialog dialog = new DiagramSelectionDialog(getShell(), modelFile,
            processDefinition, true, editor);
      dialog.setTitle(Diagram_Messages.TITLE_ModelDialog);
      dialog.setMessage(MessageFormat.format(Diagram_Messages.WIZARD_MSG_SelectDiagram,
            new String[] {modelFile.toString()}));

      // TODO: single select
      if (dialog.open() == Window.OK)
      {
         diagrams = new ArrayList(Arrays.asList(dialog.getResult()));
         StringBuffer selectedDiagrams = new StringBuffer();
         int i = 0;
         for (Iterator iter = diagrams.iterator(); iter.hasNext();)
         {
            i++;
            selectedDiagrams.append((((DiagramType) iter.next()).getName() + (diagrams
                  .size() != i ? ", " : ""))); //$NON-NLS-1$ //$NON-NLS-2$
         }
         diagramNameField.setText(selectedDiagrams.toString());
         updateDestinationField();
      }
   }

   private void updateDestinationField()
   {
      String fileName = fileNameField.getText();
      if (!StringUtils.isEmpty(fileName))
      {
         if (diagrams.size() == 1)
         {
            fileNameField.setText(!new File(fileName).isDirectory()
                  ? fileName
                  : createFileName((DiagramType) diagrams.get(0), fileName)
                        .getAbsolutePath());
         }
         if (diagrams.size() > 1)
         {
            fileNameField.setText(new File(fileName).isDirectory() ? fileName : new File(
                  fileName).getParent());
         }
      }
   }

   protected void createOptionsGroupButtons(Group optionsGroup)
   {
      optionsGroup.setText(Diagram_Messages.WIZARD_LB_FileFormat);

      pngRadioButton = new Button(optionsGroup, SWT.RADIO);
      pngRadioButton.setText("PNG"); //$NON-NLS-1$
      pngRadioButton.setSelection(true);
      pngRadioButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateFileExtension("PNG"); //$NON-NLS-1$
         }
      });

      jpgRadioButton = new Button(optionsGroup, SWT.RADIO);
      jpgRadioButton.setText("JPEG"); //$NON-NLS-1$
      jpgRadioButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateFileExtension("JPEG"); //$NON-NLS-1$
         }
      });

      gifRadioButton = new Button(optionsGroup, SWT.RADIO);
      gifRadioButton.setText("GIF"); //$NON-NLS-1$
      gifRadioButton.setEnabled(false);
      gifRadioButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateFileExtension("GIF"); //$NON-NLS-1$
         }
      });
   }

   public void handleEvent(Event event)
   {
   // TODO Auto-generated method stub

   }

   public List getDiagram()
   {
      List allDiagrams = new ArrayList();
      if (modelDiagrams != null)
      {
         allDiagrams.addAll(modelDiagrams);
      }
      if (diagrams != null)
      {
         allDiagrams.addAll(diagrams);
      }
      return allDiagrams;
   }

   public String getFileName()
   {
      return fileNameField.getText();
   }

   public String getImageFormat()
   {
      if (pngRadioButton.getSelection())
      {
         return ImageFormat.PNG;
      }
      else if (jpgRadioButton.getSelection())
      {
         return ImageFormat.JPEG;
      }
      else
      {
         return ImageFormat.GIF;
      }
   }

   protected List getTypesToExport()
   {
      ArrayList list = new ArrayList();
      list.add("cwm"); //$NON-NLS-1$
      return list;
   }

   private void setCompletionState()
   {
      boolean complete = false;

      if (((null != diagrams && diagrams.size() > 0) || (null != modelDiagrams && modelDiagrams
            .size() > 0))
            && !StringUtils.isEmpty(fileNameField.getText()))
      {
         complete = true;
      }

      setPageComplete(complete);
   }

   private File createFileName(DiagramType diagram, String baseDir)
   {
      StringBuffer buffer = new StringBuffer();
      if (null != baseDir)
      {
         buffer.append(baseDir).append(File.separatorChar);
      }

      ModelType model;
      EObject rawContainer = diagram.eContainer();
      if (rawContainer instanceof ModelType)
      {
         model = (ModelType) rawContainer;
         buffer.append(model.getId()).append("_"); //$NON-NLS-1$
      }
      else if (rawContainer instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) rawContainer;
         model = (ModelType) (process).eContainer();
         buffer.append(model.getId()).append("_"); //$NON-NLS-1$
         buffer.append(process.getId()).append("_"); //$NON-NLS-1$
      }
      else
      {
         return null;
      }

      buffer.append(diagram.getName());
      buffer.append("." + getImageFormat()); //$NON-NLS-1$

      return new File(buffer.toString());
   }

   private void updateFileExtension(String extension)
   {
      String fileName = fileNameField.getText();
      if (!StringUtils.isEmpty(fileName))
      {
         if (!new File(fileName).isDirectory() && !fileName.endsWith(extension))
         {
            fileName = fileName.substring(0, fileName.lastIndexOf(".") + 1) //$NON-NLS-1$
                  + (extension.toLowerCase());
            fileNameField.setText(fileName);
         }
      }
   }

   protected void updateWidgetEnablements()
   {
      modelDiagramNameField.setText(""); //$NON-NLS-1$
      procDefNameField.setText(""); //$NON-NLS-1$
      diagramNameField.setText(""); //$NON-NLS-1$
      fileNameField.setText(""); //$NON-NLS-1$
      if (diagrams != null)
      {
         diagrams.clear();
      }
      if (modelDiagrams != null)
      {
         modelDiagrams.clear();
      }
   }
}
