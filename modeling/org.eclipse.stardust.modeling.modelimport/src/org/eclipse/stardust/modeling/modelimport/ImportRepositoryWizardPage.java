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
package org.eclipse.stardust.modeling.modelimport;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardResourceImportPage;
import org.eclipse.ui.internal.ide.DialogUtil;
import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ImportRepositoryWizardPage extends WizardResourceImportPage
   implements Listener
{
   // widgets
   protected ResourceListAndTreeGroup selectionGroup;

   protected Combo sourceNameField;

   protected Button overwriteExistingResourcesCheckbox;

   protected Button createContainerStructureButton;

//   protected Button createOnlySelectedButton;

   protected Button sourceBrowseButton;

   protected Button selectAllButton;

   protected Button deselectAllButton;

   //A boolean to indicate if the user has typed anything
   private boolean entryChanged = false;

   // dialog store id constants
   private final static String STORE_SOURCE_NAMES_ID = "ImportRepositoryWizardPage.STORE_SOURCE_NAMES_ID";//$NON-NLS-1$

   private final static String STORE_OVERWRITE_EXISTING_RESOURCES_ID = "ImportRepositoryWizardPage.STORE_OVERWRITE_EXISTING_RESOURCES_ID";//$NON-NLS-1$

   private final static String STORE_CREATE_CONTAINER_STRUCTURE_ID = "ImportRepositoryWizardPage.STORE_CREATE_CONTAINER_STRUCTURE_ID";//$NON-NLS-1$

   private static final String SELECT_ALL_TITLE = ImportMessages.STR_SelectAll;

   private static final String DESELECT_ALL_TITLE = ImportMessages.STR_DeselectAll;

   private static final String SELECT_SOURCE_TITLE = ImportMessages.STR_ImportFromRepository;

   private static final String SELECT_SOURCE_MESSAGE = ImportMessages.MSG_SelectImportDir;

   protected static final String SOURCE_EMPTY_MESSAGE = ImportMessages.MSG_SrcNotEmpty;

   protected ImportRepositoryWizardPage(String name, IWorkbench aWorkbench,
                                        IStructuredSelection selection)
   {
      super(name, selection);
   }

   protected Button createButton(Composite parent, int id, String label,
                                 boolean defaultButton)
   {
      // increment the number of columns in the button bar
      ((GridLayout) parent.getLayout()).numColumns += 1;

      Button button = new Button(parent, SWT.PUSH);
      button.setFont(parent.getFont());

      GridData buttonData = new GridData(GridData.FILL_HORIZONTAL);
      button.setLayoutData(buttonData);

      button.setData(new Integer(id));
      button.setText(label);

      if (defaultButton)
      {
         Shell shell = parent.getShell();
         if (shell != null)
         {
            shell.setDefaultButton(button);
         }
         button.setFocus();
      }
      return button;
   }

   /**
    * Creates the buttons for selecting specific types or selecting all or none of the
    * elements.
    *
    * @param parent the parent control
    */
   // todo: do we need it ?
   protected final void createButtonsGroup(Composite parent)
   {
      // top level group
      Composite buttonComposite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.numColumns = 2;
      layout.makeColumnsEqualWidth = true;
      buttonComposite.setLayout(layout);
      buttonComposite.setFont(parent.getFont());
      GridData buttonData = new GridData(GridData.VERTICAL_ALIGN_FILL
         | GridData.HORIZONTAL_ALIGN_FILL);
      buttonData.horizontalSpan = 2;
      buttonComposite.setLayoutData(buttonData);

      selectAllButton = createButton(buttonComposite,
         IDialogConstants.SELECT_ALL_ID, SELECT_ALL_TITLE, false);

      SelectionListener listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setAllSelections(true);
         }
      };
      selectAllButton.addSelectionListener(listener);
      setButtonLayoutData(selectAllButton);

      deselectAllButton = createButton(buttonComposite,
         IDialogConstants.DESELECT_ALL_ID, DESELECT_ALL_TITLE, false);

      listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setAllSelections(false);
         }
      };
      deselectAllButton.addSelectionListener(listener);
      setButtonLayoutData(deselectAllButton);

   }

   /* (non-Javadoc)
    * Method declared on IDialogPage.
    */
   public void createControl(Composite parent)
   {
      super.createControl(parent);
      validateSourceGroup();
      //TODO: set help
//        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),
//                IDataTransferHelpContextIds.FILE_SYSTEM_IMPORT_WIZARD_PAGE);
   }

   /**
    *	Create the import options specification widgets.
    */
   protected void createOptionsGroupButtons(Group optionsGroup)
   {

      // overwrite... checkbox
      overwriteExistingResourcesCheckbox = new Button(optionsGroup, SWT.CHECK);
      overwriteExistingResourcesCheckbox.setFont(optionsGroup.getFont());
      overwriteExistingResourcesCheckbox.setText(ImportMessages.BTN_OverwriteModels);

      // create containers radio
      createContainerStructureButton = new Button(optionsGroup, SWT.CHECK);
      createContainerStructureButton.setFont(optionsGroup.getFont());
      createContainerStructureButton.setText(ImportMessages.BTN_CreateDirStructure);
      createContainerStructureButton.setSelection(false);

      // create selection only radio
//      createOnlySelectedButton = new Button(optionsGroup, SWT.RADIO);
//      createOnlySelectedButton.setFont(optionsGroup.getFont());
//      createOnlySelectedButton.setText("Import selected models only");
//      createOnlySelectedButton.setSelection(true);
   }

   /**
    *	Create the group for creating the root directory
    */
   protected void createRootDirectoryGroup(Composite parent)
   {
      Composite sourceContainerGroup = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.numColumns = 3;
      sourceContainerGroup.setLayout(layout);
      sourceContainerGroup.setFont(parent.getFont());
      sourceContainerGroup.setLayoutData(new GridData(
         GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

      Label groupLabel = new Label(sourceContainerGroup, SWT.NONE);
      groupLabel.setText(getSourceLabel());
      groupLabel.setFont(parent.getFont());

      // source name entry field
      sourceNameField = new Combo(sourceContainerGroup, SWT.BORDER);
      GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
         | GridData.GRAB_HORIZONTAL);
      data.widthHint = SIZING_TEXT_FIELD_WIDTH;
      sourceNameField.setLayoutData(data);
      sourceNameField.setFont(parent.getFont());

      sourceNameField.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateFromSourceField();
         }
      });

      sourceNameField.addKeyListener(new KeyListener()
      {
         /*
          * @see KeyListener.keyPressed
          */
         public void keyPressed(KeyEvent e)
         {
            //If there has been a key pressed then mark as dirty
            entryChanged = true;
         }

         /*
          * @see KeyListener.keyReleased
          */
         public void keyReleased(KeyEvent e)
         {
         }
      });

      sourceNameField.addFocusListener(new FocusListener()
      {
         /*
          * @see FocusListener.focusGained(FocusEvent)
          */
         public void focusGained(FocusEvent e)
         {
            //Do nothing when getting focus
         }

         /*
          * @see FocusListener.focusLost(FocusEvent)
          */
         public void focusLost(FocusEvent e)
         {
            //Clear the flag to prevent constant update
            if (entryChanged)
            {
               entryChanged = false;
               updateFromSourceField();
            }

         }
      });

      // source browse button
      sourceBrowseButton = new Button(sourceContainerGroup, SWT.PUSH);
      sourceBrowseButton.setText(ImportMessages.BTN_Browse);
      sourceBrowseButton.addListener(SWT.Selection, this);
      sourceBrowseButton.setLayoutData(new GridData(
         GridData.HORIZONTAL_ALIGN_FILL));
      sourceBrowseButton.setFont(parent.getFont());
      setButtonLayoutData(sourceBrowseButton);
   }

   /**
    * Update the receiver from the source name field.
    */

   private void updateFromSourceField()
   {

      setSourceName(sourceNameField.getText());
      //Update enablements when this is selected
      updateWidgetEnablements();
   }

   /**
    * Creates and returns a <code>FileSystemElement</code> if the specified
    * file system object merits one.  The criteria for this are:
    * Also create the children.
    */
   protected FileSystemStore createRootElement(
      File fileSystemObject, IImportStructureProvider provider)
   {
      FileSystemStore store = new FileSystemStore(fileSystemObject, provider);
      try
      {
         store.loadRepository();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (ParserConfigurationException e)
      {
         e.printStackTrace();
      }
      return store;
   }

   /**
    * Returns this page's list of currently-specified resources to be
    * imported. This is the primary resource selection facility accessor for
    * subclasses.
    *
    * @return a list of resources currently selected
    * for export (element type: <code>IResource</code>)
    */
   protected java.util.List getSelectedResources()
   {
      return this.selectionGroup.getAllCheckedListItems();
   }

   /**
    * Set all of the selections in the selection group to value
    * @param value boolean
    */
   protected void setAllSelections(boolean value)
   {
      selectionGroup.setAllSelections(value);
   }

   protected void createFileSelectionGroup(Composite parent)
   {
      this.selectionGroup = new ResourceListAndTreeGroup(parent,
         null, SWT.NONE, DialogUtil.inRegularFontMode(parent));

      ICheckStateListener listener = new ICheckStateListener()
      {
         public void checkStateChanged(CheckStateChangedEvent event)
         {
            updateWidgetEnablements();
         }
      };

/*      WorkbenchViewerSorter sorter = new WorkbenchViewerSorter();
      this.selectionGroup.setTreeSorter(sorter);
      this.selectionGroup.setListSorter(sorter);*/
      this.selectionGroup.addCheckStateListener(listener);

   }

   /**
    *	Create the import source specification widgets
    */
   protected void createSourceGroup(Composite parent)
   {
      createRootDirectoryGroup(parent);
      createFileSelectionGroup(parent);
      createButtonsGroup(parent);
   }

   /**
    * Enable or disable the button group.
    */
   protected void enableButtonGroup(boolean enable)
   {
      selectAllButton.setEnabled(enable);
      deselectAllButton.setEnabled(enable);
   }

   /**
    *	Answer a boolean indicating whether the specified source currently exists
    *	and is valid
    */
   protected boolean ensureSourceIsValid()
   {
      if (new File(getSourceDirectoryName()).isDirectory())
         return true;

      displayErrorDialog(ImportMessages.MSG_InvalidSrc);
      sourceNameField.setFocus();
      return false;
   }

   /**
    *	Execute the passed import operation.  Answer a boolean indicating success.
    */
   protected boolean executeImportOperation(ImportOperation op)
   {
      initializeOperation(op);

      try
      {
         getContainer().run(true, true, op);
      }
      catch (InterruptedException e)
      {
         return false;
      }
      catch (InvocationTargetException e)
      {
         displayErrorDialog(e.getTargetException());
         return false;
      }

      IStatus status = op.getStatus();
      if (!status.isOK())
      {
         ErrorDialog
            .openError(getContainer().getShell(), ImportMessages.MSG_ImportProblems,
               null, // no special message
               status);
         return false;
      }

      return true;
   }

   /**
    *	The Finish button was pressed.  Try to do the required work now and answer
    *	a boolean indicating success.  If false is returned then the wizard will
    *	not close.
    *
    * @return boolean
    */
   public boolean finish()
   {
      if (!ensureSourceIsValid())
         return false;

      saveWidgetValues();

      Iterator resourcesEnum = getSelectedResources().iterator();
      List fileSystemObjects = new ArrayList();
      while (resourcesEnum.hasNext())
      {
         addRecursive(fileSystemObjects, (ModelNode) resourcesEnum.next());
      }

      if (fileSystemObjects.size() > 0)
         return importResources(fileSystemObjects);

      MessageDialog.openInformation(getContainer().getShell(),
         ImportMessages.TITLE_Info,
         ImportMessages.MSG_NoFilesSelected);

      return false;
   }

   private void addRecursive(List list, ModelNode node)
   {
      list.add(node);
      for (Iterator i = node.publicVersions(); i.hasNext();)
      {
         addRecursive(list, (ModelNode) i.next());
      }
   }

   /**
    * Returns a content provider for <code>FileSystemElement</code>s that returns
    * only files as children.
    */
   protected ITreeContentProvider getFileProvider()
   {
	  // never used
      return null;
   }

   /**
    *	Answer the root FileSystemElement that represents the contents of
    *	the currently-specified source.  If this FileSystemElement is not
    *	currently defined then create and return it.
    */
   protected FileSystemStore getRepositoryTree()
   {
      File sourceDirectory = getSourceDirectory();
      if (sourceDirectory == null)
         return null;

      return selectFiles(sourceDirectory,
         RepositoryStructureProvider.INSTANCE);
   }

   /**
    * Returns a content provider for <code>FileSystemElement</code>s that returns
    * only folders as children.
    */
   protected ITreeContentProvider getFolderProvider()
   {
	  // never used
	  return null;
   }

   /**
    * Returns a File object representing the currently-named source directory iff
    * it exists as a valid directory, or <code>null</code> otherwise.
    */
   protected File getSourceDirectory()
   {
      return getSourceDirectory(this.sourceNameField.getText());
   }

   /**
    * Returns a File object representing the currently-named source directory iff
    * it exists as a valid directory, or <code>null</code> otherwise.
    *
    * @param path a String not yet formatted for java.io.File compatability
    */
   private File getSourceDirectory(String path)
   {
      File sourceDirectory = new File(getSourceDirectoryName(path));
      if (!sourceDirectory.exists() || !sourceDirectory.isDirectory())
      {
         return null;
      }

      return sourceDirectory;
   }

   /**
    *	Answer the directory name specified as being the import source.
    *	Note that if it ends with a separator then the separator is first
    *	removed so that java treats it as a proper directory
    */
   private String getSourceDirectoryName()
   {
      return getSourceDirectoryName(this.sourceNameField.getText());
   }

   /**
    *	Answer the directory name specified as being the import source.
    *	Note that if it ends with a separator then the separator is first
    *	removed so that java treats it as a proper directory
    */
   private String getSourceDirectoryName(String sourceName)
   {
      IPath result = new Path(sourceName.trim());

      if (result.getDevice() != null && result.segmentCount() == 0) // something like "c:"
         result = result.addTrailingSeparator();
      else
         result = result.removeTrailingSeparator();

      return result.toOSString();
   }

   /**
    *	Answer the string to display as the label for the source specification field
    */
   protected String getSourceLabel()
   {
      return ImportMessages.LB_FromDir;
   }

   /**
    *	Handle all events and enablements for widgets in this dialog
    *
    * @param event Event
    */
   public void handleEvent(Event event)
   {
      if (event.widget == sourceBrowseButton)
         handleSourceBrowseButtonPressed();

      super.handleEvent(event);
   }

   /**
    *	Open an appropriate source browser so that the user can specify a source
    *	to import from
    */
   protected void handleSourceBrowseButtonPressed()
   {
      String currentSource = this.sourceNameField.getText();
      DirectoryDialog dialog = new DirectoryDialog(
         sourceNameField.getShell(), SWT.OPEN);
      dialog.setText(SELECT_SOURCE_TITLE);
      dialog.setMessage(SELECT_SOURCE_MESSAGE);
      dialog.setFilterPath(getSourceDirectoryName(currentSource));

      String selectedDirectory = dialog.open();
      if (selectedDirectory != null)
      {
         //Just quit if the directory is not valid
         if ((getSourceDirectory(selectedDirectory) == null)
            || selectedDirectory.equals(currentSource))
            return;
         //If it is valid then proceed to populate
         setErrorMessage(null);
         setSourceName(selectedDirectory);
         selectionGroup.setFocus();
      }
   }

   /**
    *  Import the resources with extensions as specified by the user
    */
   protected boolean importResources(List fileSystemObjects)
   {
      ImportOperation operation = new ImportOperation(getContainerFullPath(),
         getSourceDirectory(), RepositoryStructureProvider.INSTANCE,
         this, fileSystemObjects);

      operation.setContext(getShell());
      return executeImportOperation(operation);
   }

   /**
    * Initializes the specified operation appropriately.
    */
   protected void initializeOperation(ImportOperation op)
   {
      op.setCreateContainerStructure(createContainerStructureButton
         .getSelection());
      op.setOverwriteResources(overwriteExistingResourcesCheckbox
         .getSelection());
   }

   /**
    *	Repopulate the view based on the currently entered directory.
    */
   protected void resetSelection()
   {
      FileSystemStore currentRoot = getRepositoryTree();
      this.selectionGroup.setRoot(currentRoot);
   }

   /**
    *	Use the dialog store to restore widget values to the values that they held
    *	last time this wizard was used to completion
    */
   protected void restoreWidgetValues()
   {
      IDialogSettings settings = getDialogSettings();
      if (settings != null)
      {
         String[] sourceNames = settings.getArray(STORE_SOURCE_NAMES_ID);
         if (sourceNames == null)
            return; // ie.- no values stored, so stop

         // set filenames history
         for (int i = 0; i < sourceNames.length; i++)
            sourceNameField.add(sourceNames[i]);

         // radio buttons and checkboxes
         overwriteExistingResourcesCheckbox.setSelection(settings
            .getBoolean(STORE_OVERWRITE_EXISTING_RESOURCES_ID));

         boolean createStructure = settings
            .getBoolean(STORE_CREATE_CONTAINER_STRUCTURE_ID);
         createContainerStructureButton.setSelection(createStructure);
//         createOnlySelectedButton.setSelection(!createStructure);
      }
   }

   /**
    * 	Since Finish was pressed, write widget values to the dialog store so that they
    *	will persist into the next invocation of this wizard page
    */
   protected void saveWidgetValues()
   {
      IDialogSettings settings = getDialogSettings();
      if (settings != null)
      {
         // update source names history
         String[] sourceNames = settings.getArray(STORE_SOURCE_NAMES_ID);
         if (sourceNames == null)
            sourceNames = new String[0];

         sourceNames = addToHistory(sourceNames, getSourceDirectoryName());
         settings.put(STORE_SOURCE_NAMES_ID, sourceNames);

         // radio buttons and checkboxes
         settings.put(STORE_OVERWRITE_EXISTING_RESOURCES_ID,
            overwriteExistingResourcesCheckbox.getSelection());
         settings.put(STORE_CREATE_CONTAINER_STRUCTURE_ID,
            createContainerStructureButton.getSelection());
      }
   }

   /**
    * Invokes a file selection operation using the specified file system and
    * structure provider.  If the user specifies files to be imported then
    * this selection is cached for later retrieval and is returned.
    */
   protected FileSystemStore selectFiles(
      final File rootFileSystemObject,
      final IImportStructureProvider structureProvider)
   {
      final FileSystemStore[] results = new FileSystemStore[1];

      BusyIndicator.showWhile(getShell().getDisplay(), new Runnable()
      {
         public void run()
         {
            //Create the root element from the supplied file system object
            results[0] = createRootElement(rootFileSystemObject,
               structureProvider);
         }
      });

      return results[0];
   }

   /**
    * Sets the source name of the import to be the supplied path.
    * Adds the name of the path to the list of items in the
    * source combo and selects it.
    *
    * @param path the path to be added
    */
   protected void setSourceName(String path)
   {

      if (path.length() > 0)
      {

         String[] currentItems = this.sourceNameField.getItems();
         int selectionIndex = -1;
         for (int i = 0; i < currentItems.length; i++)
         {
            if (currentItems[i].equals(path))
               selectionIndex = i;
         }
         if (selectionIndex < 0)
         {
            int oldLength = currentItems.length;
            String[] newItems = new String[oldLength + 1];
            System.arraycopy(currentItems, 0, newItems, 0, oldLength);
            newItems[oldLength] = path;
            this.sourceNameField.setItems(newItems);
            selectionIndex = oldLength;
         }
         this.sourceNameField.select(selectionIndex);

         resetSelection();
      }
   }

   /* (non-Javadoc)
    * Method declared on IDialogPage. Set the selection up when it becomes visible.
    */
   public void setVisible(boolean visible)
   {
      super.setVisible(visible);
      resetSelection();
      if (visible)
         this.sourceNameField.setFocus();
   }

   /**
    *	Answer a boolean indicating whether self's source specification
    *	widgets currently all contain valid values.
    */
   protected boolean validateSourceGroup()
   {
      File sourceDirectory = getSourceDirectory();
      if (sourceDirectory == null)
      {
         setMessage(SOURCE_EMPTY_MESSAGE);
         enableButtonGroup(false);
         return false;
      }

      if (sourceConflictsWithDestination(new Path(sourceDirectory.getPath())))
      {
         setErrorMessage(getSourceConflictMessage()); 
         enableButtonGroup(false);
         return false;
      }

      enableButtonGroup(true);
      return true;
   }

   /**
    * Returns whether the source location conflicts
    * with the destination resource. This will occur if
    * the source is already under the destination.
    *
    * @param sourcePath the path to check
    * @return <code>true</code> if there is a conflict, <code>false</code> if not
    */
   protected boolean sourceConflictsWithDestination(IPath sourcePath)
   {
      IContainer container = getSpecifiedContainer();
      if (container == null)
         return false;

      IPath destinationLocation = getSpecifiedContainer().getLocation();
      if (destinationLocation != null)
      {
         return destinationLocation.isPrefixOf(sourcePath);
      }
      // null destination location is handled in
      // WizardResourceImportPage
      return false;
   }

   public boolean performFinish()
   {
      return finish();
   }
}
