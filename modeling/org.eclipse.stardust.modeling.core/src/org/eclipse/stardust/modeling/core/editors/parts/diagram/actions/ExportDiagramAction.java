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

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.Pair;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.export.DiagramExporter;
import org.eclipse.stardust.modeling.core.utils.ImageFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;

public class ExportDiagramAction extends SelectionAction
{
   private Set<DiagramType> diagrams;
   private File fileNameProposal;
   private String selectedDirectoryName;

   public ExportDiagramAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.EXPORT_DIAGRAM);
   }

   public void run()
   {
      String format = null;
      int size = diagrams.size();
      if (1 == size)
      {
         if (null == fileNameProposal)
         {
            fileNameProposal = createFileName((DiagramType) diagrams.toArray()[0], null);
            Pair fileAndFormat = browseForFileAndFormat(fileNameProposal
                  .getAbsolutePath());
            String selectedFileName = (String) fileAndFormat.getFirst();
            format = (String) fileAndFormat.getSecond();
            if (null == selectedFileName)
            {
               return;
            }

            fileNameProposal = new File(selectedFileName);
         }
      }
      else if (1 < size)
      {
         if (null == selectedDirectoryName)
         {
            Pair directoryAndFormat = browseForDirectoryAndFormat(null);
            selectedDirectoryName = (String) directoryAndFormat.getFirst();
            format = (String) directoryAndFormat.getSecond();
            if (null == selectedDirectoryName)
            {
               return;
            }
         }
      }

      for (Iterator iter = diagrams.iterator(); iter.hasNext();)
      {
         DiagramType diagram = (DiagramType) iter.next();
         DiagramExporter exporter = new DiagramExporter(diagram, getWorkbenchPart());

         File file = null == fileNameProposal ? createFileName(diagram,
               selectedDirectoryName) : fileNameProposal;
         if (null != file)
         {
            format = StringUtils.isEmpty(format) ? ImageFormat.PNG : format;

            String fileName = file.getName();
            int pointIdx = fileName.lastIndexOf("."); //$NON-NLS-1$
            if (-1 == pointIdx)
            {
               file = new File(file.getAbsolutePath() + "." + format); //$NON-NLS-1$
            }

            if (ImageFormat.PNG.equals(format))
            {
               exporter.dumpDiagramToPNGFile(file);
            }
            else if (ImageFormat.JPEG.equals(format))
            {
               exporter.dumpDiagramToJPEGFile(file);
            }
            else if (ImageFormat.GIF.equals(format))
            {
               exporter.dumpDiagramToGIFFile(file);
            }
         }
      }
   }

   /**
    * Note: This method sets previously set attributes fileNameProposal and
    * selectedDirectoryName to null.
    */
   protected boolean calculateEnabled()
   {
      boolean result = false;
      diagrams = new HashSet();
      fileNameProposal = null;
      selectedDirectoryName = null;

      for (Iterator selectionIter = getSelectedObjects().iterator(); selectionIter
            .hasNext();)
      {
         Object selection = selectionIter.next();
         if (!(selection instanceof EditPart))
         {
            continue;
         }

         Object rawElement = ((EditPart) selection).getModel();
         rawElement = selection instanceof DiagramRootEditPart
               ? ((DiagramRootEditPart) selection).getContents().getModel()
               : rawElement;

         if (rawElement instanceof DiagramType)
         {
            result = true;

            DiagramType diagram = (DiagramType) rawElement;
            diagrams.add(diagram);
         }
         else if (rawElement instanceof ProcessSymbolType)
         {
            result = true;

            ProcessSymbolType symbol = (ProcessSymbolType) rawElement;
            ProcessDefinitionType processDefinition = (ProcessDefinitionType) symbol
                  .getModelElement();
            diagrams.addAll(processDefinition.getDiagram());
         }
         else if (rawElement instanceof ProcessDefinitionType)
         {
            result = true;

            ProcessDefinitionType processDefinition = (ProcessDefinitionType) rawElement;
            diagrams.addAll(processDefinition.getDiagram());
         }
         else if (rawElement instanceof ModelType)
         {
            result = true;

            ModelType model = (ModelType) rawElement;
            diagrams.addAll(model.getDiagram());

            for (Iterator iter = model.getProcessDefinition().iterator(); iter.hasNext();)
            {
               ProcessDefinitionType processDefinition = (ProcessDefinitionType) iter
                     .next();
               diagrams.addAll(processDefinition.getDiagram());
            }
         }
      }

      int size = diagrams.size();
      if (0 == size)
      {
         result = false;
      }
      else if (1 == size)
      {
         setText(Diagram_Messages.DESC_ExportDiagram);
      }
      else
      {
         setText(Diagram_Messages.MENU_ExportAllDiagrams);
      }

      return result;
   }

   public File getFileNameProposal()
   {
      return fileNameProposal;
   }

   public void setFileNameProposal(File fileNameProposal)
   {
      this.fileNameProposal = fileNameProposal;
   }

   public String getSelectedDirectoryName()
   {
      return selectedDirectoryName;
   }

   public void setSelectedDirectoryName(String selectedDirectoryName)
   {
      this.selectedDirectoryName = selectedDirectoryName;
   }

   private Pair browseForDirectoryAndFormat(String proposal)
   {
      DirectorySelectionDialog dialog = new DirectorySelectionDialog(Display.getCurrent()
            .getActiveShell());

      dialog.setFilterPath(proposal);

      dialog.setBlockOnOpen(true);
      int result = dialog.open();
      if (Window.OK == result)
      {
         Pair directoryAndFormat = new Pair(dialog.getFileName(), dialog.getImageFormat());
         return directoryAndFormat;
      }
      else
      {
         return new Pair(null, null);
      }
   }

   private Pair browseForFileAndFormat(String proposal)
   {
      FileSelectionDialog dialog = new FileSelectionDialog(Display.getCurrent()
            .getActiveShell());

      File proposedFile = new File(proposal);

      dialog.setFileName(proposedFile.getName());
      dialog.setFilterExtensions(new String[] {"*.png", "*.jpg", "*.*"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

      dialog.setBlockOnOpen(true);
      int result = dialog.open();
      if (Window.OK == result)
      {
         Pair fileAndFormat = new Pair(dialog.getFileName(), dialog.getImageFormat());
         return fileAndFormat;
      }
      else
      {
         return new Pair(null, null);
      }
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
         ProcessDefinitionType processDefinition = (ProcessDefinitionType) rawContainer;
         model = (ModelType) (processDefinition).eContainer();
         buffer.append(model.getId()).append("_"); //$NON-NLS-1$
         buffer.append(processDefinition.getId()).append("_"); //$NON-NLS-1$
      }
      else
      {
         return null;
      }

      buffer.append(diagram.getName());

      return new File(buffer.toString());
   }

   private class FileSelectionDialog extends org.eclipse.jface.dialogs.Dialog
   {
      private String fileName;

      private String[] filterExtensions;

      private Button pngRadioButton;

      private Button jpgRadioButton;

      private Button gifRadioButton;

      private String imageFormat;

      private Text fileNameField;

      public String[] getFilterExtensions()
      {
         return filterExtensions;
      }

      public void setFilterExtensions(String[] filterExtensions)
      {
         this.filterExtensions = filterExtensions;
      }

      public FileSelectionDialog(Shell shell)
      {
         super(shell);
         imageFormat = ImageFormat.PNG;
      }

      protected void configureShell(Shell shell)
      {
         super.configureShell(shell);

         shell.setText(Diagram_Messages.TITLE_SelectFileAndImageFormat);
      }

      protected Control createDialogArea(Composite parent)
      {
         Composite containerGroup = new Composite(parent, SWT.NONE);
         GridLayout layout = new GridLayout();
         layout.numColumns = 3;
         containerGroup.setLayout(layout);
         containerGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
               | GridData.GRAB_HORIZONTAL));
         containerGroup.setFont(parent.getFont());

         // Filename
         Label label = new Label(containerGroup, SWT.None);
         label.setText(Diagram_Messages.LABEL_SelectFile);
         label.setFont(parent.getFont());

         fileNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
         GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
               | GridData.GRAB_HORIZONTAL);
         data.widthHint = 250;
         fileNameField.setLayoutData(data);
         fileNameField.setFont(parent.getFont());
         fileNameField.addModifyListener(new ModifyListener()
         {
            public void modifyText(ModifyEvent event)
            {}
         });

         Button containerBrowseButton = new Button(containerGroup, SWT.PUSH);
         containerBrowseButton.setText(Diagram_Messages.BUTTON_Browse);
         containerBrowseButton.setFont(parent.getFont());
         containerBrowseButton
               .setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

         final FileDialog dialog = new FileDialog(parent.getShell(), SWT.SAVE);
         dialog.setFileName(fileName);
         dialog.setFilterExtensions(filterExtensions);
         containerBrowseButton.addSelectionListener(new SelectionListener()
         {
            public void widgetSelected(SelectionEvent event)
            {
               dialog.setFilterExtensions(filterExtensions);
               fileName = dialog.open();
               if (fileName != null && new File(fileName).exists())
               {
                  if (!MessageDialog.openQuestion(Display.getCurrent().getActiveShell(),
                        Diagram_Messages.Title_FileExists, Diagram_Messages.MSG_FileExists))
                  {
                     fileName = null;
                  }
               }
               fileNameField.setText((null != fileName) ? fileName : ""); //$NON-NLS-1$
            }

            public void widgetDefaultSelected(SelectionEvent event)
            {
               fileName = dialog.open();
               fileNameField.setText((null != fileName) ? fileName : ""); //$NON-NLS-1$
            }
         });

         Group optionsGroup = new Group(containerGroup, SWT.NONE);
         layout = new GridLayout();
         optionsGroup.setLayout(layout);
         optionsGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
               | GridData.GRAB_HORIZONTAL));
         optionsGroup.setFont(parent.getFont());

         optionsGroup.setText(Diagram_Messages.GROUP_ImageFormat);

         pngRadioButton = new Button(optionsGroup, SWT.RADIO);
         pngRadioButton.setText("PNG"); //$NON-NLS-1$
         pngRadioButton.setSelection(true);
         pngRadioButton.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               imageFormat = ImageFormat.PNG;
               filterExtensions = new String[] {"*.png", "*.jpg", "*.gif", "*.*"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
               updateFileExtension();
            }
         });

         jpgRadioButton = new Button(optionsGroup, SWT.RADIO);
         jpgRadioButton.setText("JPEG"); //$NON-NLS-1$
         jpgRadioButton.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               imageFormat = ImageFormat.JPEG;
               filterExtensions = new String[] {"*.jpg", "*.png", "*.gif", "*.*"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
               updateFileExtension();
            }
         });

         gifRadioButton = new Button(optionsGroup, SWT.RADIO);
         gifRadioButton.setText("GIF"); //$NON-NLS-1$
         gifRadioButton.setEnabled(false);
         gifRadioButton.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               imageFormat = ImageFormat.GIF;
               filterExtensions = new String[] {"*.gif", "*.jpg", "*.png", "*.*"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
               updateFileExtension();
            }
         });

         return super.createDialogArea(parent);
      }

      public String getFileName()
      {
         return fileName;
      }

      public void setFileName(String fileName)
      {
         this.fileName = fileName;
      }

      public String getImageFormat()
      {
         return imageFormat;
      }

      protected void okPressed()
      {
         fileName = fileNameField.getText();
         if (new File(fileName).isAbsolute())
         {
            super.okPressed();
         }
         else
         {
            MessageDialog.openInformation(getShell(), Diagram_Messages.TITLE_InvalidDir,
                  Diagram_Messages.MSG_InvalidDir);
         }
      }

      private void updateFileExtension()
      {
         String fileName = fileNameField.getText();
         if (!StringUtils.isEmpty(fileName))
         {
            if (!new File(fileName).isDirectory() && !fileName.endsWith(imageFormat))
            {
               fileName = fileName.substring(0, fileName.lastIndexOf(".") + 1) //$NON-NLS-1$
                     + (imageFormat.toLowerCase());
               fileNameField.setText(fileName);
            }
         }
      }
   }

   private class DirectorySelectionDialog extends org.eclipse.jface.dialogs.Dialog
   {
      private String fileName;

      private String filterPath;

      private Button pngRadioButton;

      private Button jpgRadioButton;

      private Button gifRadioButton;

      private String imageFormat;

      private Text fileNameField;

      public DirectorySelectionDialog(Shell shell)
      {
         super(shell);
         imageFormat = ImageFormat.PNG;
      }

      protected void configureShell(Shell shell)
      {
         super.configureShell(shell);

         shell.setText(Diagram_Messages.MENU_SelectDirectoryAndImageFormat);
      }

      protected void cancelPressed()
      {
         // TODO Auto-generated method stub
         super.cancelPressed();
      }

      protected Control createDialogArea(Composite parent)
      {
         Composite containerGroup = new Composite(parent, SWT.NONE);
         GridLayout layout = new GridLayout();
         layout.numColumns = 3;
         containerGroup.setLayout(layout);
         containerGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
               | GridData.GRAB_HORIZONTAL));
         containerGroup.setFont(parent.getFont());

         // Filename
         Label label = new Label(containerGroup, SWT.None);
         label.setText(Diagram_Messages.LABEL_SelectDirectory);
         label.setFont(parent.getFont());

         fileNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
         GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
               | GridData.GRAB_HORIZONTAL);
         data.widthHint = 250;
         fileNameField.setLayoutData(data);
         fileNameField.setFont(parent.getFont());
         fileNameField.addModifyListener(new ModifyListener()
         {
            public void modifyText(ModifyEvent event)
            {}
         });

         Button containerBrowseButton = new Button(containerGroup, SWT.PUSH);
         containerBrowseButton.setText(Diagram_Messages.BUTTON_Browse);
         containerBrowseButton.setFont(parent.getFont());
         containerBrowseButton
               .setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

         final DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.SAVE);
         containerBrowseButton.addSelectionListener(new SelectionListener()
         {
            public void widgetSelected(SelectionEvent event)
            {
               fileName = dialog.open();
               fileNameField.setText(fileName);
            }

            public void widgetDefaultSelected(SelectionEvent event)
            {
               fileName = dialog.open();
               fileNameField.setText(fileName);
            }
         });

         Group optionsGroup = new Group(containerGroup, SWT.NONE);
         layout = new GridLayout();
         optionsGroup.setLayout(layout);
         optionsGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
               | GridData.GRAB_HORIZONTAL));
         optionsGroup.setFont(parent.getFont());

         optionsGroup.setText(Diagram_Messages.GROUP_ImageFormat);

         pngRadioButton = new Button(optionsGroup, SWT.RADIO);
         pngRadioButton.setText("PNG"); //$NON-NLS-1$
         pngRadioButton.setSelection(true);
         pngRadioButton.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               imageFormat = ImageFormat.PNG;
            }
         });

         jpgRadioButton = new Button(optionsGroup, SWT.RADIO);
         jpgRadioButton.setText("JPEG"); //$NON-NLS-1$
         jpgRadioButton.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               imageFormat = ImageFormat.JPEG;
            }
         });

         gifRadioButton = new Button(optionsGroup, SWT.RADIO);
         gifRadioButton.setText("GIF"); //$NON-NLS-1$
         gifRadioButton.setEnabled(false);
         gifRadioButton.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {
               imageFormat = ImageFormat.GIF;
            }
         });

         return super.createDialogArea(parent);
      }

      protected void okPressed()
      {
         fileName = fileNameField.getText();
         super.okPressed();
      }

      public String getFileName()
      {
         return fileName;
      }

      public void setFileName(String fileName)
      {
         this.fileName = fileName;
      }

      public String getImageFormat()
      {
         return imageFormat;
      }

      public String getFilterPath()
      {
         return filterPath;
      }

      public void setFilterPath(String filterPath)
      {
         this.filterPath = filterPath;
      }
   }
}