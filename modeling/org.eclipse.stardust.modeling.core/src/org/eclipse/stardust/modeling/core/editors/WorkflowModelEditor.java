/*******************************************************************************
 * Copyright (c) 2011, 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.editors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.*;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.config.CurrentVersion;
import org.eclipse.stardust.common.config.Version;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.*;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.decoration.DecorationUtils;
import org.eclipse.stardust.modeling.core.decoration.IDecorationProvider;
import org.eclipse.stardust.modeling.core.decoration.IDecorationTarget;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdaptee;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.*;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteSymbolCommandFactory;
import org.eclipse.stardust.modeling.core.jobs.ModelValidationJob;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.validation.ValidationMarkerService;
import org.eclipse.stardust.modeling.validation.ValidationPlugin;

public class WorkflowModelEditor extends AbstractMultiPageGraphicalEditor
      implements IWorkflowModelEditor, EditPartRegistry, IGotoMarker, IDecorationTarget
{
   private WorkflowModelManager modelManager;

   private ModelType cwmModel;

   private List<DiagramType> diagrams = new ArrayList<DiagramType>();

   private Map<NotificationAdaptee, NotificationAdapter> adapters = CollectionUtils.newMap();

   private Set<IDiagramChangeListener> diagramChangeListeners = CollectionUtils.newSet();

   private final ValidationIssueManager validationIssueManager = new ValidationIssueManager();

   private final ModelResourceIssueSynchronizer issueSynchronizer = new ModelResourceIssueSynchronizer(
         validationIssueManager);

   private final EditorChangeTracker editorChangeTracker = new EditorChangeTracker();

   public EditorChangeTracker getEditorChangeTracker()
   {
      return editorChangeTracker;
   }

   private WorkflowModelOutlinePage outlinePage;

   private VariableContextHelper variableContextHelper = VariableContextHelper.getInstance();

   public AbstractMultiPageGraphicalEditorOutlinePage getOutlinePage()
   {
      return super.getOutlinePage();
   }

   private boolean changed;

   private boolean initialized;

   private ModelValidationJob validationJob;

   private Map<String, IDecorationProvider> decorations = CollectionUtils.newMap();

   private IconFactory iconFactory;

   protected Boolean upgradeChecked;

   public WorkflowModelEditor()
   {
      PlatformUI.getPreferenceStore().setDefault(
            BpmProjectNature.PREFERENCE_AUTO_VALIDATION, true);
      addPropertyListener(new IPropertyListener()
      {
         public void propertyChanged(Object source, int propId)
         {
            if (propId == PROP_INPUT)
            {
               setPartName(getEditorInput().getName());
            }
         }
      });
      iconFactory = new IconFactory(this);
   }

   public WorkflowModelManager getModelManager()
   {
      return modelManager;
   }

   public void dispose()
   {
      variableContextHelper.removeContext(this.getWorkflowModel());
      
      // remove from cache if no longer referenced
      // WorkspaceManager.getInstance().cleanCache(this.getWorkflowModel());      
      super.dispose();
   }

   public boolean isChanged()
   {
      return changed;
   }

   public IconFactory getIconFactory()
   {
      return iconFactory;
   }

   public ActionRegistry getActionRegistry()
   {
      return super.getActionRegistry();
   }

   public void addDiagramChangeListener(IDiagramChangeListener listener)
   {
      if (null != listener && !diagramChangeListeners.contains(listener))
      {
         diagramChangeListeners.add(listener);
      }
   }

   public void removeDiagramChangeListener(IDiagramChangeListener listener)
   {
      if (null != listener && diagramChangeListeners.contains(listener))
      {
         diagramChangeListeners.remove(listener);
      }
   }

   public void showDiagramPage(final DiagramType diagram) throws PartInitException
   {
      int pageId = diagrams.indexOf(diagram);
      if (pageId < 0)
      {
         pageId = createDiagramPage(diagram);
      }
      setActivePage(pageId);
   }

   public List<DiagramType> getOpenedDiagrams()
   {
      return Collections.unmodifiableList(diagrams);
   }

   protected void createActions()
   {
      super.createActions();

      addEditPartAction(new CopyAction(this));
      addEditPartAction(new PasteAction(this));

      addEditPartAction(new SetDefaultParticipantAction(this));

      addEditPartAction(new ConnectAction(this));
      /*addEditPartAction*/addAction(new ReloadConnectionsAction(this));

      addEditPartAction(new OptimizeDiagramAction(this));
      addEditPartAction(new UpdateDiagramAction(this));
      addEditPartAction(new UpgradeModelAndDiagramAction(this));
      addEditPartAction(new UpgradeDataAction(this));
      addEditPartAction(new OpenDiagramAction(this, OpenDiagramAction.DIAGRAM));
      addEditPartAction(new OpenDiagramAction(this, OpenDiagramAction.DEFAULT_DIAGRAM));
      addEditPartAction(new OpenDiagramAction(this,
            OpenDiagramAction.DEFAULT_SUBPROCESS_DIAGRAM));
      addEditPartAction(new CloseDiagramAction(this));
      addEditPartAction(new ReferencesSearchAction(this));
      addEditPartAction(new SearchAction(this));
      // search over Connection
      // addEditPartAction(new SearchConnectionAction(this));

      ShowPropertiesAction propDlgAction = new ShowPropertiesAction(this, getSite()
            .getSelectionProvider());
      propDlgAction.setId(ActionFactory.PROPERTIES.getId());
      addAction(propDlgAction);

      addEditPartAction(new CreateOrganizationHierarchyAction(this));
      addEditPartAction(new CreateActivityGraphAction(this));

      DeleteSymbolAction deleteSymbolAction = new DeleteSymbolAction(this);
      IAction deleteAction = getActionRegistry().getAction(ActionFactory.DELETE.getId());
      deleteSymbolAction.setImageDescriptor(deleteAction.getImageDescriptor());
      deleteSymbolAction.setDisabledImageDescriptor(deleteAction
            .getDisabledImageDescriptor());
      addEditPartAction(deleteSymbolAction);
      addEditPartAction(new ForwardDeleteAction(this, getActionRegistry()));
      // deploy model
      addEditPartAction(new DeployModelAction(this));
      addEditPartAction(new ImportModelElementsAction(this));
      addEditPartAction(new CreateActivityAction(null,
            DiagramActionConstants.CREATE_GENERIC_ACTIVITY, this));
      addEditPartAction(new CreateActivityAction(
            ActivityImplementationType.ROUTE_LITERAL,
            DiagramActionConstants.CREATE_ROUTE_ACTIVITY, this));
      addEditPartAction(new CreateActivityAction(
            ActivityImplementationType.MANUAL_LITERAL,
            DiagramActionConstants.CREATE_MANUAL_ACTIVITY, this));
      addEditPartAction(new CreateActivityAction(
            ActivityImplementationType.APPLICATION_LITERAL,
            DiagramActionConstants.CREATE_APPLICATION_ACTIVITY, this));
      addEditPartAction(new CreateActivityAction(
            ActivityImplementationType.SUBPROCESS_LITERAL,
            DiagramActionConstants.CREATE_SUBPROCESS_ACTIVITY, this));
      addEditPartAction(new CreateProcessDefinitionAction(this));
      addEditPartAction(new ResetSubprocessAction(this));
      addEditPartAction(new CreateSubprocessAction(this));

      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();

      addEditPartAction(new CreateApplicationAction(null, this));

      Map<String, IConfigurationElement> applicationTypesExtensions = registry
            .getExtensions(CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID);
      for (IConfigurationElement config : applicationTypesExtensions.values())
      {
         addEditPartAction(new CreateApplicationAction(config, this));
      }
      Map<String, IConfigurationElement> contextTypesExtensions = registry
            .getExtensions(CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID);
      for (IConfigurationElement config : contextTypesExtensions.values())
      {
         if (!ActivityUtil.isImplicitContext(config.getAttribute("id"))) //$NON-NLS-1$
         {
            addEditPartAction(new CreateInteractiveApplicationAction(config, this));
         }
      }

      addEditPartAction(new ValidateModelAction(this));
      addEditPartAction(new CreateLinkTypeAction(this));
      addEditPartAction(new CreateDiagramAction(this));
      addEditPartAction(new ConvertGatewayAction(this));

      Map<String, IConfigurationElement> dataExtensions = registry
            .getExtensions(CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID);
      for (IConfigurationElement config : dataExtensions.values())
      {
         addEditPartAction(new CreateDataAction(config, this));
      }
      addEditPartAction(new CreateDataAction(null, this));

      // REPOSITORY CONNECTION STUFF
      // ask the plugin for all extensions
      Map<String, IConfigurationElement> connectionExtensions = registry
            .getExtensions(ObjectRepositoryActivator.PLUGIN_ID,
                  ObjectRepositoryActivator.CONNECTION_EXTENSION_POINT_ID);
      for (IConfigurationElement config : connectionExtensions.values())
      {
         addEditPartAction(new CreateRepositoryConnectionAction(config, this));
      }
      Map<String, IConfigurationElement> searchConnectionExtensions = registry
            .getExtensions(ObjectRepositoryActivator.PLUGIN_ID,
                  ObjectRepositoryActivator.CONNECTION_SEARCH_EXTENSION_POINT_ID);
      for (IConfigurationElement config : searchConnectionExtensions.values())
      {
         addEditPartAction(new SearchConnectionAction(config, this));
      }
      // search over Connection
      // addEditPartAction(new SearchConnectionAction(this));

      addEditPartAction(new ElementSelectionAction(this));

      // 2 new Actions (1 for linking an object from the tree in the outline)
      // addEditPartAction(new LinkConnectionObjectAction(this));
      addEditPartAction(new ImportConnectionObjectAction(this));
      addEditPartAction(new RefreshConnectionObjectAction(this));
      addEditPartAction(new AddExternalReferenceAction(this));
      addEditPartAction(new DeleteExternalReferenceAction(this));

      Map<String, IConfigurationElement> triggerExtensions = registry
            .getExtensions(CarnotConstants.TRIGGER_TYPES_EXTENSION_POINT_ID);
      for (IConfigurationElement config : triggerExtensions.values())
      {
         addEditPartAction(new CreateTriggerAction(config, this));
      }
      // as of 4.0 strategy, modelers have no longer any role
      // and we no longer create them
      // addEditPartAction(new CreateModelerAction(this));
      addEditPartAction(new CreateConditionalPerformerAction(this));
      addEditPartAction(new CreateRoleAction(this));
      addEditPartAction(new CreateOrganizationAction(this));

      addEditPartAction(new ExportDiagramAction(this));
      addEditPartAction(new ReloadSchemaAction(this));
   }

   protected boolean canDelete(ISelection selection)
   {
      return false;
   }

   protected void createPages()
   {
      initializeExtensions();
      try
      {
         ModelType model = getWorkflowModel();
         if (initialized && model != null)
         {
            if (checkUpgradeModel())
            {
               removeUpgradePage();
               List<DiagramType> modelDiagrams = model.getDiagram();
               if (modelDiagrams != null)
               {
                  for (DiagramType diagram : modelDiagrams)
                  {
                     showDiagramPage(diagram);
                  }
               }
               List<ProcessDefinitionType> processDefinitions = model.getProcessDefinition();
               if (processDefinitions != null && !processDefinitions.isEmpty())
               {
                  List<DiagramType> processDiagrams = ((ProcessDefinitionType) processDefinitions.get(0)).getDiagram();
                  if (processDiagrams != null && !processDiagrams.isEmpty())
                  {
                     showDiagramPage((DiagramType) processDiagrams.get(0));
                  }
               }
            }
            else
            {
               showUpgradePage();
            }
         }
      }
      catch (PartInitException e)
      {
         ErrorDialog.openError(getSite().getShell(), Diagram_Messages.ERR_OpenError,
               Diagram_Messages.ERR_duringOpeningTheEditor, e.getStatus());
      }
   }

   private void removeUpgradePage()
   {
      for (int i = getPageCount() - 1; i >= 0; i--)
      {
         IEditorPart page = getEditor(i);
         if (page instanceof UpgradePage)
         {
            removePage(i);
         }
      }
   }

   private void showUpgradePage()
   {
      for (int i = 0; i < getPageCount(); i++)
      {
         removePage(i);
      }
      final UpgradePage page = new UpgradePage(this);
      try
      {
         int pageId = addPage(page, getEditorInput());
         setPageText(pageId, page.getPageName());
         setActivePage(pageId);
      }
      catch (PartInitException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   /**
    *
    * @return false if the model needed upgrade but the user had declined
    */
   boolean checkUpgradeModel()
   {
      if (upgradeChecked == null)
      {
         upgradeChecked = Boolean.TRUE;
         Command upgradeModelAndDiagramCmd = new UpgradeModelAndDiagramAction(this)
               .createUpgradeModelAndDiagramCmd();
         if (upgradeModelAndDiagramCmd.canExecute())
         {
            String message = null;
            Version modelVersion = null;
            Version carnotVersion = CurrentVersion.getVersion();
            try
            {
               modelVersion = new Version(cwmModel.getCarnotVersion());
               if (modelVersion.compareTo(carnotVersion, true) < 0)
               {
                  message = MessageFormat.format(Diagram_Messages.MSG_UpgradeModel,
                        cwmModel.getName(), modelVersion.toString(), carnotVersion.toString());
               }
               else if (modelVersion.compareTo(carnotVersion, true) > 0)
               {
               message = MessageFormat.format(Diagram_Messages.MSG_DIA_MODEL_NULL_WAS_CREATED_USING_A_NEWER_VERSION_OF_THE_MODELER_ONE
                     + "\n" + Diagram_Messages.MSG_DIA_CHANGING_MODEL_IS_NOT_RECOMMENDED, new Object[]{cwmModel.getName(), modelVersion.toString()}); //$NON-NLS-1$

                  MessageDialog dialog = new MessageDialog(getSite().getShell(),
                        Diagram_Messages.LB_UpgradeModel, null, message, MessageDialog.INFORMATION,
                        new String[] {IDialogConstants.OK_LABEL}, 0);
                  dialog.open();
                  upgradeChecked = Boolean.TRUE;
                  return upgradeChecked;
               }
               else
               {
                  message = MessageFormat
                        .format(
                              Diagram_Messages.MSG_DIA_DEPRECATED_MD_FORMAT_DETECTED
                                    + "\n" //$NON-NLS-1$
                                    + Diagram_Messages.MSG_DIA_DO_YOU_WANT_TO_UPDATE_MD_TO_CURRENT_SPECS_NULL,
                              new Object[] {modelVersion.toString()});
               }
            }
            catch (Exception ex)
            {
               message = MessageFormat
                     .format(
                           Diagram_Messages.MSG_DIA_UNKNOWN_VERSION_FOR_MD_NULL
                                 + "\n" //$NON-NLS-1$
                                 + Diagram_Messages.MSG_DO_YOU_WANT_TO_UPDATE_MD_TO_VERSION_ONE,
                           new Object[] {
                                 cwmModel.getName(), carnotVersion.toShortString()});
            }
            MessageDialog dialog = new MessageDialog(getSite().getShell(),
                  Diagram_Messages.LB_UpgradeModel, null, message, MessageDialog.QUESTION,
                  new String[] {IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL}, 0);
            switch (dialog.open())
            {
            case 0: // yes, update first then open
               getEditDomain().getCommandStack().execute(upgradeModelAndDiagramCmd);
               break;
            case 1: // no update, just open model
               upgradeChecked = modelVersion != null && modelVersion.getMajor() == carnotVersion.getMajor();
            }
         }
         FixInvalidIdsAction.run(this);
      }
      return upgradeChecked.booleanValue();
   }

   private int createDiagramPage(final DiagramType diagram) throws PartInitException
   {
      final DiagramEditorPage page = new DiagramEditorPage(this, diagram);
      final int pageId = addPage(page, getEditorInput());

      setPageText(pageId, page.getPageName());

      // add command stacks
      // getMultiPageCommandStackListener().addCommandStack(page.getCommandStack());

      diagrams.add(diagram);

      if (diagram.eContainer() instanceof ProcessDefinitionType)
      {
         final ProcessDefinitionType process = (ProcessDefinitionType) diagram
               .eContainer();
         addRemoveAdapter(diagram, process);
      }
      addRemoveAdapter(diagram, diagram);
      for (IDecorationProvider decoration : decorations.values())
      {
         DecorationUtils.applyDecoration(decoration, page.getGraphicalViewer()
               .getRootEditPart().getChildren());
      }

      fireDiagramPageOpened(page);
      return pageId;
   }

   private void addRemoveAdapter(final Object diagram, final EObject eObject)
   {
      final EObject container = eObject.eContainer();
      NotificationAdaptee adaptee = new NotificationAdaptee()
      {
         public Object getModel()
         {
            return eObject;
         }

         public void handleNotification(Notification notification)
         {
            if (notification.getEventType() == Notification.REMOVE
                  && notification.getOldValue() == eObject)
            {
               container.eAdapters().remove(adapters.get(this));
               adapters.remove(this);
               for (int i = 0; i < getOpenedDiagrams().size(); i++)
               {
                  if (diagrams.get(i) == diagram)
                  {
                     removePage(i);
                     diagrams.remove(i);
                     break;
                  }
               }
            }
         }
      };
      NotificationAdapter adapter = new NotificationAdapter(adaptee);
      adapters.put(adaptee, adapter);
      container.eAdapters().add(adapter);
   }

   public void doSave(IProgressMonitor monitor)
   {
      try
      {
         IFile file = null;
         if(getEditorInput() instanceof IFileEditorInput)
         {
            file = ((IFileEditorInput) getEditorInput()).getFile();
         }
         if (file != null && file.exists()
               || MessageDialog.openConfirm(getSite().getShell(),
                     Diagram_Messages.MSG_CreateFile,
                     Diagram_Messages.MSG_Confirm_P1_TheFile + file.getName()
                           + Diagram_Messages.MSG_Confirm_P2_doesntExist))
         {
            save(file, monitor);
            getSharedCommandStack().markSaveLocation();
         }
      }
      catch (CoreException e)
      {
         ErrorDialog.openError(getSite().getShell(), Diagram_Messages.ERR_DuringSave,
               Diagram_Messages.ERR_WorkflowModelCouldNotBeSaved, e.getStatus());
      }
   }

   public void doSaveAs()
   {
      SaveAsDialog dialog = new SaveAsDialog(getSite().getShell());
      dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
      dialog.open();
      IPath path = dialog.getResult();

      if (null == path)
      {
         return;
      }

      ProgressMonitorDialog progressMonitorDialog = new ProgressMonitorDialog(getSite()
            .getShell());
      IProgressMonitor progressMonitor = progressMonitorDialog.getProgressMonitor();

      try
      {
         progressMonitorDialog.open();
         try
         {
            IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
            save(newFile, progressMonitor);
            getSharedCommandStack().markSaveLocation();

            setInput(new FileEditorInput(newFile));
            firePropertyChange(PROP_INPUT);
         }
         finally
         {
            progressMonitorDialog.close();
         }
      }
      catch (CoreException e)
      {
         ErrorDialog.openError(getSite().getShell(), Diagram_Messages.ERR_DuringSave,
               Diagram_Messages.ERR_CurrentModelCouldNotBeSaved, e.getStatus());
      }
   }

   public Object getAdapter(@SuppressWarnings("rawtypes") Class type)
   {
      if (type == IContentOutlinePage.class)
      {
         return getOutlinePage();
      }
      if (type == ConnectionManager.class)
      {
         return getConnectionManager();
      }
      if (type == WorkflowModelManager.class)
      {
         return modelManager;
      }
      return super.getAdapter(type);
   }

   public DiagramType getActiveDiagram()
   {
      int idx = getActivePage();
      IEditorPart editPart = getEditor(idx);

      if (editPart instanceof DiagramEditorPage)
      {
         return ((DiagramEditorPage) editPart).getDiagram();
      }

      return null;
   }

   public Object getModel()
   {
      return getWorkflowModel();
   }

   public ModelType getWorkflowModel()
   {
      return cwmModel;
   }

   protected FlyoutPreferences getPalettePreferences()
   {
      return WorkflowModelEditorPaletteFactory.createPalettePreferences();
   }

   protected WorkflowModelOutlinePage createOutlinePage()
   {
      outlinePage = new WorkflowModelOutlinePage(this);
      validationIssueManager.addValidationEventListener(outlinePage);
      return outlinePage;
   }

   protected void pageChange(int newPageIndex)
   {
      super.pageChange(newPageIndex);

      // refresh content depending on current page
      currentPageChanged();
   }

   protected void setActivePage(int pageIndex)
   {
      if (getPageCount() > 0 && pageIndex >= 0 && pageIndex < getPageCount())
      {
         super.setActivePage(pageIndex);
      }

      // refresh content depending on current page
      currentPageChanged();
   }

   protected void currentPageChanged()
   {
      // updateVisuals overview page
      if (null != outlinePage)
      {
         outlinePage.editorPageChanged();
         if ((null != getEditorSite())
               && (null != getEditorSite().getSelectionProvider()) //
               && (null != getSite()) && (null != getSite().getSelectionProvider()))
         {
            try
            {
               getEditorSite().getSelectionProvider().setSelection(
                     getSite().getSelectionProvider().getSelection());
            }
            catch (NullPointerException e)
            {
               // Workaround (rsauer): don't know how to prevent refresh during perspective switch
            }
         }
      }

      IEditorPart currentPage = getCurrentPage();
      if (currentPage != null)
      {
         // updateVisuals zoom actions
         getDelegatingZoomManager().setCurrentZoomManager(
               getZoomManager((currentPage instanceof AbstractGraphicalEditorPage)
                     ? ((AbstractGraphicalEditorPage) currentPage)
                           .getGraphicalViewer()
                     : null));
      }

      if (currentPage instanceof DiagramEditorPage)
      {
         fireDiagramPageChanged((DiagramEditorPage) currentPage);
      }
   }

   private void fireDiagramPageChanged(DiagramEditorPage page)
   {
      // inform all listeners about the page change
      for (IDiagramChangeListener listener : diagramChangeListeners)
      {
         listener.diagramPageChanged(page);
      }
   }

   private void fireDiagramPageOpened(DiagramEditorPage page)
   {
      // inform all listeners about the page open
      for (IDiagramChangeListener listener : diagramChangeListeners)
      {
         listener.diagramPageOpened(page);
      }
   }

   private void fireDiagramPageClosed(DiagramEditorPage page)
   {
      // inform all listeners about the page close
      for (IDiagramChangeListener listener : diagramChangeListeners)
      {
         listener.diagramPageClosed(page);
      }
   }

   protected void setInput(IEditorInput input)
   {
      getEditorInputTracker().removeChangeVisitor(issueSynchronizer);
      getEditorInputTracker().removeChangeVisitor(editorChangeTracker);

      getEditorInputTracker().addChangeVisitor(issueSynchronizer);
      getEditorInputTracker().addChangeVisitor(editorChangeTracker);

      super.setInput(input);
   }

   public void init(IEditorSite site, IEditorInput input) throws PartInitException
   {
      updateModel(input);

      super.init(site, input);

      // initialize actions
      createActions();

      updateListeners();
   }

   private void updateListeners()
   {
      // add selection change listener
      getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(
            getSelectionListener());

      getSharedCommandStack().markSaveLocation();

      getSharedCommandStack().addCommandStackEventListener(
            new CommandStackEventListener()
            {
               public void stackChanged(CommandStackEvent event)
               {
                  boolean mustValidate = (0 != (event.getDetail() & (CommandStack.POST_EXECUTE
                        | CommandStack.POST_UNDO | CommandStack.POST_REDO)));

                  if (mustValidate)
                  {
                     validateModel();
                  }
               }
            });

      getSite().getShell().addShellListener(new ShellAdapter()
      {
         public void shellActivated(ShellEvent e)
         {
            forceRefresh();
            if (WorkflowModelEditor.this.getSite().getPage().equals(
                  getSite().getWorkbenchWindow().getActivePage())
                  && CompareHelper.areEqual(WorkflowModelEditor.this.getSite().getPage()
                        .getActiveEditor(), WorkflowModelEditor.this))
            {
               checkChanged();
            }
         }
      });

      getSite().getWorkbenchWindow().getWorkbench().addWindowListener(
            new IWindowListener()
            {
               public void windowActivated(IWorkbenchWindow window)
               {
                  forceRefresh();
                  if (this.equals(getSite().getWorkbenchWindow().getActivePage()))
                  {
                     checkChanged();
                  }
               }

               public void windowDeactivated(IWorkbenchWindow window)
               {}

               public void windowClosed(IWorkbenchWindow window)
               {}

               public void windowOpened(IWorkbenchWindow window)
               {}
            });
   }

   public void updateEditor(IWorkbenchPage workbenchPage)
   {
      if (getSite().getPage().equals(workbenchPage))
      {
         initializeExtensions();
         if (!initialized)
         {
            closePages();
            getOutlinePage().setOutlineContents(null);
         }
         else if (diagrams.isEmpty())
         {
            createPages();
         }
         for (int i = 0; i < getPageCount(); i++)
         {
            if (getEditor(i) instanceof UpgradePage)
            {
               UpgradePage page = (UpgradePage) getEditor(i);
               page.redraw();
            }
            else if (initialized)
            {
               DiagramEditorPage page = (DiagramEditorPage) getEditor(i);
               Map<?, ?> registry = page.getGraphicalViewer().getEditPartRegistry();
               for (Object o : registry.values())
               {
                  ((EditPart) o).refresh();
               }
               WorkflowModelEditorPaletteFactory.updatePalette(page);
            }
         }
         if (initialized && checkUpgradeModel())
         {
            getOutlinePage().initializeOutlineViewer();
         }
         validateModel();
      }
   }

   private void initializeExtensions()
   {
      initialized = true;
   }

   private void forceRefresh()
   {
      IEditorInput editorInput = getEditorInput();
      if (editorInput instanceof FileEditorInput)
      {
         try
         {
            IFile file = ((FileEditorInput) editorInput).getFile();
            file.refreshLocal(IResource.DEPTH_ZERO, null);
         }
         catch (CoreException e)
         {
            // e.printStackTrace();
         }
      }
   }

   public void updateModel(IEditorInput input) throws PartInitException
   {
      try
      {
         // we expect IFileEditorInput here,
         // ClassCassException is catched to force PartInitException
         if (input instanceof IFileEditorInput)
         {
            IFile file = ((IFileEditorInput) input).getFile();
            this.cwmModel = create(file);
         }
         else if (input instanceof IURIEditorInput)
         {
            java.net.URI uri = ((IURIEditorInput) input).getURI();
            this.cwmModel = create(uri);
         }

         // validate network
         if (null == getWorkflowModel())
         {
            throw new PartInitException(
                  Diagram_Messages.EX_SpecifiedInputNotValidModel);
         }
      }
      catch (CoreException e)
      {
         e.printStackTrace();
         throw new PartInitException(e.getStatus());
      }
      catch (ClassCastException e)
      {
         throw new PartInitException(Diagram_Messages.EX_SpecifiedInputNotValidModel, e);
      }
   }

   protected void validateModel()
   {
      if (getWorkflowModel() == null)
      {
         // When dealing with no or an invalid license this can happen
         return;
      }

      VariableContextHelper.getInstance().storeVariables(getWorkflowModel(), true);

      if (!PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_VALIDATION))
      {
         return;
      }

      if (validationJob != null)
      {
         validationJob.cancel();
      }
      if (initialized)
      {
      validationJob = new ModelValidationJob(this, getWorkflowModel(),
            createPerspectiveFilter());
      if (null != validationJob.getModelFile())
      {
         validationJob.setRule(ResourcesPlugin.getWorkspace().getRuleFactory()
               .markerRule(validationJob.getModelFile()));
      }
      else
      {
         // TODO verify: if no file was created, schedule to workspace root?
         validationJob.setRule(ResourcesPlugin.getWorkspace().getRuleFactory()
               .markerRule(ResourcesPlugin.getWorkspace().getRoot()));
      }
      validationJob.schedule();
   }
      else
      {
         IResource modelFile = ((IFileEditorInput) getEditorInput()).getFile();
         try
         {
            modelFile.deleteMarkers(ValidationPlugin.VALIDATION_MARKER_ID, true,
                  IResource.DEPTH_INFINITE);
            ValidationMarkerService.getInstance()
                  .removeMappings(modelFile);
         }
         catch (CoreException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   private Map<String, String> createPerspectiveFilter()
   {
      String perspectiveId = DiagramPlugin.getViewAsPerspectiveId(this);
      return perspectiveId == null
            ? Collections.<String, String>emptyMap()
            : Collections.singletonMap("perspectiveType", perspectiveId); //$NON-NLS-1$
   }

   protected ModelType create(IFile file) throws CoreException
   {
      ModelType model = null;
      // change
      this.modelManager = createModelManager();
      // this.modelManager = new ExtendedModelManager(getSite());

      if (file.exists())
      {
         try
         {
            modelManager.load(URI.createPlatformResourceURI(file.getFullPath().toString(), false));
         }
         catch (Exception e)
         {
            throw new PartInitException(Diagram_Messages.EX_FailedLoadingModel, e);
            // modelManager.createModel(file.getFullPath());
         }

         model = modelManager.getModel();
         if (null == model)
         {
            throw new CoreException(new Status(IStatus.ERROR,
                  CarnotConstants.DIAGRAM_PLUGIN_ID, IStatus.OK,
                  Diagram_Messages.EX_ErrorLoadingNetwork, null));
         }
         fixSymbols(model);
         // do we need this?
         // getConnectionManager(model).resolve(model);
      }
      variableContextHelper.storeVariables(model, false);

      return model;
   }

   protected ExtendedModelManager createModelManager()
   {
      return new ExtendedModelManager();
   }

   protected ModelType create(java.net.URI uri) throws CoreException
   {
      ModelType model = null;
      // change
      this.modelManager = createModelManager();
      // this.modelManager = new ExtendedModelManager(getSite());

      try
      {
         modelManager.load(URI.createURI(uri.toString()));
      }
      catch (Exception e)
      {
         throw new PartInitException(Diagram_Messages.EX_FailedLoadingModel, e);
         // modelManager.createModel(file.getFullPath());
      }

      model = modelManager.getModel();
      if (null == model)
      {
         throw new CoreException(new Status(IStatus.ERROR,
               CarnotConstants.DIAGRAM_PLUGIN_ID, IStatus.OK,
               Diagram_Messages.EX_ErrorLoadingNetwork, null));
      }
      fixSymbols(model);
      // do we need this?
      // getConnectionManager(model).resolve(model);
      return model;
   }

   private void fixSymbols(ModelType model)
   {
      fixSymbols(model.getDiagram());
      List<ProcessDefinitionType> processes = model.getProcessDefinition();
      for (ProcessDefinitionType process : processes)
      {
         fixSymbols(process.getDiagram());
      }
   }

   private static void fixSymbols(List<DiagramType> diagrams)
   {
      for (DiagramType diagram : diagrams)
      {
         List<IModelElementNodeSymbol> toDelete = CollectionUtils.newList();
         Iterator<EObject> contents = diagram.eAllContents();
         while (contents.hasNext())
         {
            EObject object = contents.next();
            if (object instanceof IModelElementNodeSymbol)
            {
               IModelElementNodeSymbol symbol = (IModelElementNodeSymbol) object;
               if (symbol.getModelElement() == null
                     && !(symbol instanceof StartEventSymbol)
                     && !(symbol instanceof EndEventSymbol)
                     && !(symbol instanceof IntermediateEventSymbol)
                     && !(symbol instanceof PublicInterfaceSymbol))
               {
                  toDelete.add(symbol);
               }
            }
         }
         for (IModelElementNodeSymbol symbol : toDelete)
         {
            DeleteSymbolCommandFactory.createDeleteSymbolCommand(symbol).execute();
         }
      }
   }

   private void save(IFile file, IProgressMonitor progressMonitor) throws CoreException
   {
      getEditorInputTracker().removeChangeVisitor(editorChangeTracker);

      if (null == progressMonitor)
      {
         progressMonitor = new NullProgressMonitor();
      }

      progressMonitor.beginTask(Diagram_Messages.TASKNAME_Saving + file, 3);

      if (null == modelManager)
      {
         IStatus status = new Status(IStatus.ERROR, CarnotConstants.DIAGRAM_PLUGIN_ID, 0,
               Diagram_Messages.ERR_NoModelManagerFound, null);
         throw new CoreException(status);
      }

      // save network to file
      try
      {
         // store configuration variables
         try
         {
            variableContextHelper.storeVariables(getWorkflowModel(), true);
         }
         catch (Throwable t)
         {
            t.printStackTrace();
         }

         // Model locked within web modeler --> unlock (CRNT-29022)
         AttributeType attribute = AttributeUtil.getAttribute(getWorkflowModel(),
               PredefinedConstants.READ_ONLY_HASH);
         if (attribute != null)
         {
            getWorkflowModel().getAttribute().remove(attribute);
         }

         modelManager.save(URI.createPlatformResourceURI(file.getFullPath().toString(), false));
         progressMonitor.worked(1);

         // todo: synchronize with save as !!!
         /* do we still need this?
         if (connectionManager != null)
         {
            connectionManager.save();
         }
         */


         progressMonitor.worked(1);

         file.refreshLocal(IResource.DEPTH_ZERO, new SubProgressMonitor(progressMonitor,
               1));
         progressMonitor.done();
      }
      catch (FileNotFoundException e)
      {
         IStatus status = new Status(IStatus.ERROR, CarnotConstants.DIAGRAM_PLUGIN_ID, 0,
               Diagram_Messages.ERR_writingFile, e);
         throw new CoreException(status);
      }
      catch (IOException e)
      {
         IStatus status = new Status(IStatus.ERROR, CarnotConstants.DIAGRAM_PLUGIN_ID, 0,
               Diagram_Messages.ERR_writingFile, e);
         throw new CoreException(status);
      }

      getEditorInputTracker().addChangeVisitor(editorChangeTracker);
   }

   public EditPart findEditPart(Object model)
   {
      Map<?, ?> registry;

      DiagramType diagram = getDiagram(model);
      if (diagram != null && diagrams.contains(diagram))
      {
         int pageNumber = diagrams.indexOf(diagram);
         DiagramEditorPage page = (DiagramEditorPage) getEditor(pageNumber);
         registry = page.getGraphicalViewer().getEditPartRegistry();
      }
      else
      {
         WorkflowModelOutlinePage outline = (WorkflowModelOutlinePage) getOutlinePage();
         registry = outline.getViewer().getEditPartRegistry();
      }

      return (EditPart) registry.get(model);
   }

   private DiagramType getDiagram(Object model)
   {
      if (model instanceof DiagramType)
      {
         return (DiagramType) model;
      }
      if (model instanceof IGraphicalObject)
      {
         return ModelUtils.findContainingDiagram((IGraphicalObject) model);
      }
      return null;
   }

   public boolean hasDiagramPage(DiagramType diagram)
   {
      return diagrams.contains(diagram);
   }

   protected void closePages()
   {
      while (!diagrams.isEmpty())
      {
         DiagramType diagram = (DiagramType) diagrams.get(0);
         closeDiagramPage(diagram);
      }
   }

   public void closeDiagramPage(DiagramType diagram)
   {
      NotificationAdaptee adaptee = findAdaptee(diagram);
      if (adaptee != null)
      {
         if(diagram.eContainer() != null)
         {
            diagram.eContainer().eAdapters().remove(adapters.get(adaptee));
         }
         adapters.remove(adaptee);
      }
      int pageId = diagrams.indexOf(diagram);
      diagrams.remove(diagram);
      if (pageId >= 0)
      {
         // remove all decorations
         DiagramEditorPage page = (DiagramEditorPage) getEditor(pageId);
         for (IDecorationProvider decoration : decorations.values())
         {
            DecorationUtils.removeDecoration(decoration, page.getGraphicalViewer()
                  .getRootEditPart().getChildren());
         }
         removePage(pageId);
         currentPageChanged();
      }
      variableContextHelper.removeContext(getWorkflowModel());
   }

   public void removePage(int pageIndex)
   {
      IEditorPart editor = getEditor(pageIndex);
      super.removePage(pageIndex);
      if (editor instanceof DiagramEditorPage)
      {
         fireDiagramPageClosed((DiagramEditorPage) editor);
      }
   }

   private NotificationAdaptee findAdaptee(DiagramType diagram)
   {
      for (NotificationAdaptee adaptee : adapters.keySet())
      {
         if (adaptee.getModel() == diagram)
         {
            return adaptee;
         }
      }
      return null;
   }

   public boolean isActiveDiagram(DiagramType diagram)
   {
      return getActivePage() >= 0 && getActivePage() == diagrams.indexOf(diagram);
   }

   public void gotoMarker(IMarker marker)
   {
      EObject element = null;
      try
      {
         element = (EObject) marker.getAttribute(ValidationPlugin.MARKER_ELEMENT);
      }
      catch (CoreException e)
      {
         // ignore errors
      }

      if (null != element)
      {
         selectElement(element);
         ShowPropertiesAction showPropertiesAction = new ShowPropertiesAction(this, outlinePage.getSite().getSelectionProvider());
         showPropertiesAction.run();
      }
   }

   public boolean hasDecoration(IDecorationProvider decoration)
   {
      return decorations.containsKey(decoration.getId());
   }

   public void enableDecoration(IDecorationProvider decoration)
   {
      if (!hasDecoration(decoration))
      {
         decorations.put(decoration.getId(), decoration);
         for (int i = 0; i < getPageCount(); i++ )
         {
            DiagramEditorPage page = (DiagramEditorPage) getEditor(i);

            DecorationUtils.applyDecoration(decoration, page.getGraphicalViewer()
                  .getRootEditPart().getChildren());
         }
      }
   }

   public void disableDecoration(IDecorationProvider decoration)
   {
      if (hasDecoration(decoration))
      {
         for (int i = 0; i < getPageCount(); i++ )
         {
            DiagramEditorPage page = (DiagramEditorPage) getEditor(i);

            DecorationUtils.removeDecoration(decoration, page.getGraphicalViewer()
                  .getRootEditPart().getChildren());
         }
         decorations.remove(decoration.getId());
      }
   }

   public void selectElement(EObject element)
   {
      outlinePage.selectElement(element);
      INodeSymbol symbol = getElementSymbol(element);
      if (symbol != null)
      {
         selectSymbol(symbol);
      }
   }

   public void selectSymbols(List<?> symbols, DiagramType diagram)
   {
      if (!isActiveDiagram(diagram))
      {
         try
         {
            showDiagramPage(diagram);
         }
         catch (PartInitException e)
         {
            e.printStackTrace();
         }
      }

      List<EditPart> editParts = CollectionUtils.newList();
      for(Object symbol : symbols)
      {
         EditPart editPart = findEditPart(symbol);
         editParts.add(editPart);
      }
      getEditorSite().getSelectionProvider().setSelection(
            new StructuredSelection(editParts));
   }

   public void selectSymbol(INodeSymbol symbol)
   {
      DiagramType diagram = getDiagram(symbol);
      if (!isActiveDiagram(diagram))
      {
         try
         {
            showDiagramPage(diagram);
         }
         catch (PartInitException e)
         {
            e.printStackTrace();
         }
      }
      EditPart editPart = findEditPart(symbol);
      if (null != editPart)
      {
         getEditorSite().getSelectionProvider().setSelection(
               new StructuredSelection(editPart));
         editPart.getParent().getViewer().reveal(editPart);
      }
   }

   public INodeSymbol getElementSymbol(EObject element)
   {
      List<DiagramType> diagrams = null;
      if (element instanceof ProcessDefinitionType)
      {
         diagrams = ((ProcessDefinitionType) element).getDiagram();
      }
      else if (ModelUtils.findContainingProcess(element) != null)
      {
         diagrams = ModelUtils.findContainingProcess(element).getDiagram();
      }
      else
      {
         return null;
      }
      for (DiagramType diagram : diagrams)
      {
         // TODO (fh) fix in model
         @SuppressWarnings("unchecked")
         List<EStructuralFeature> nodeContainingFeatures = diagram.getNodeContainingFeatures();
         for (EStructuralFeature feature : nodeContainingFeatures)
         {
            @SuppressWarnings("unchecked")
            List<INodeSymbol> nodeSymbolList = (List<INodeSymbol>) diagram.eGet(feature);
            for (INodeSymbol nodeSymbol : nodeSymbolList)
            {
               if (nodeSymbol instanceof IModelElementNodeSymbol)
               {
                  IIdentifiableModelElement nodeSymbolModelElement = ((IModelElementNodeSymbol) nodeSymbol)
                        .getModelElement();

                  if (null != nodeSymbolModelElement
                        && nodeSymbolModelElement.equals(element))
                  {
                     return nodeSymbol;
                  }
               }
            }
         }
      }
      return null;
   }

   public void selectInOutline(EObject element)
   {
      outlinePage.selectElement(element);
   }

   public void setFocus()
   {
      try
      {
         super.setFocus();
      }
      catch(SWTException e)
      {
      }
      forceRefresh();
      checkChanged();
   }

   protected void checkChanged()
   {
      if (null != getCurrentPage())
      {
         if (getCurrentPage() instanceof DiagramEditorPage)
         {
            setPageText(getActivePage(), ((DiagramEditorPage) getCurrentPage())
                  .getPageName());
            ((DiagramEditorPage) getCurrentPage())
                  .updateTitle(((DiagramEditorPage) getCurrentPage()).getEditorInput());
         }
         boolean action = changed;
         changed = false;
         if (action
               && MessageDialog.openQuestion(getSite().getShell(),
                     Diagram_Messages.LB_Changed, Diagram_Messages.MSG_ResourceChanged))
         {
            reloadModel();
         }
      }
   }

   public void reloadModel()
   {
      Display display = getSite().getShell().getDisplay();
      display.syncExec(new Runnable()
      {
         public void run()
         {
            initializeExtensions();
            closePages();
            setInput(getEditorInput());
            try
            {
               updateModel(getEditorInput());
            }
            catch (PartInitException e)
            {
               // TODO: what to do with the exception???
               // e.printStackTrace();
            }
            upgradeChecked = null;
            createPages();
            getOutlinePage().initializeOutlineViewer();
            validateModel();
         }
      });
   }

   public void refreshDiagramPages()
   {
      for (int i = 0; i < getPageCount(); i++)
      {
         DiagramEditorPage diagramPage = (DiagramEditorPage) getEditor(i);
         diagramPage.getGraphicalViewer().setContents(diagramPage.getDiagram());
      }
   }

   public class EditorChangeTracker implements IResourceDeltaVisitor
   {
      public boolean isEnabled = true;

      public void setEnabled(boolean isEnabled)
      {
         this.isEnabled = isEnabled;
      }

      public boolean visit(IResourceDelta delta)
      {
         if(!isEnabled)
         {
            return false;
         }

         if (delta.getKind() == IResourceDelta.CHANGED)
         {
            if ((delta.getFlags() & (IResourceDelta.CONTENT | IResourceDelta.REPLACED | IResourceDelta.SYNC)) != 0)
            {
               if((delta.getFlags() & IResourceDelta.SYNC) != 0)
               {
                  if(delta.getAffectedChildren(IResourceDelta.CHANGED).length > 0)
                  {
                     changed = true;
                  }
               }
               else
               {
                  changed = true;
               }
            }
         }
         return false;
      }
   }

   public ValidationIssueManager getIssueManager()
   {
      return validationIssueManager;
   }

   public ConnectionManager getConnectionManager()
   {
      return modelManager instanceof ExtendedModelManager
            ? ((ExtendedModelManager) modelManager).getConnectionManager() : null;
   }

   public void doUpgradeModel()
   {
      upgradeChecked = Boolean.TRUE;
      Command upgradeModelAndDiagramCmd = new UpgradeModelAndDiagramAction(this)
            .createUpgradeModelAndDiagramCmd();
      if (upgradeModelAndDiagramCmd.canExecute())
      {
         getEditDomain().getCommandStack().execute(upgradeModelAndDiagramCmd);
      }
      createPages();
      getOutlinePage().initializeOutlineViewer();
   }

   public DiagramEditorPage[] getEditors()
   {
      List<DiagramEditorPage> editors = new ArrayList<DiagramEditorPage>();
      for (int i = 0; i < getPageCount(); i++)
      {
         IEditorPart editor = getEditor(i);
         if (editor instanceof DiagramEditorPage)
         {
            editors.add((DiagramEditorPage) editor);
         }
      }
      return editors.toArray(new DiagramEditorPage[editors.size()]);
   }
}