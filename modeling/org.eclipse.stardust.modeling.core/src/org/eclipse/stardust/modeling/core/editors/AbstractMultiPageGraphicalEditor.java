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

import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.actions.StackAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.AlignmentSnapToGridAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.CleanupModelAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.CopySymbolAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.CreateSubprocessFromSelectionAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.CutAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DeleteAllAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DiagramPrintAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DistributeAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.GroupSymbolsAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.PasteSymbolAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SetDefaultSizeAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ShowInDiagramAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ShowInOutlineAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ShrinkToFitAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SnapToGridAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.UngroupSymbolsAction;
import org.eclipse.stardust.modeling.core.editors.parts.properties.UndoablePropSheetEntry;
import org.eclipse.stardust.modeling.core.utils.FileEditorInputTracker;

public abstract class AbstractMultiPageGraphicalEditor extends MultiPageEditorPart
      implements IAdaptable
{
   private boolean isDirty = false;

   private ActionRegistry actionRegistry;

   private CommandStack sharedCommandStack;

   private EditDomain editDomain;

   // private CommandStackListener commandStackListener;

   // private MultiPageCommandStackListener multiPageCommandStackListener;

   private ISelectionListener selectionListener;

   private SelectionSynchronizer synchronizer;

   private DelegatingZoomManager delegatingZoomManager;

   protected KeyHandler sharedKeyHandler;

   private List<String> editorActionIDs = CollectionUtils.newList();

   private List<String> editPartActionIDs = CollectionUtils.newList();

   private List<String> stackActionIDs = CollectionUtils.newList();

   private FileEditorInputTracker editorInputTracker;

   private PropertySheetPage propertySheetPage;

   protected AbstractMultiPageGraphicalEditorOutlinePage outlinePage;

   public AbstractMultiPageGraphicalEditor()
   {
      editDomain = new DefaultEditDomain(this);
      editDomain.setCommandStack(getSharedCommandStack());
   }

   public EditDomain getEditDomain()
   {
      return editDomain;
   }

   public abstract Object getModel();

   protected abstract WorkflowModelOutlinePage createOutlinePage();

   public Object getAdapter(@SuppressWarnings("rawtypes") Class type)
   {
      if (type == IPropertySheetPage.class)
      {
         return getPropertySheetPage();
      }
      else if (type == CommandStack.class)
      {
         return getSharedCommandStack();
      }
      else if (type == ActionRegistry.class)
      {
         return getActionRegistry();
      }
      else if (type == ZoomManager.class)
      {
         return getDelegatingZoomManager();
      }
      else if (type == GraphicalViewer.class)
      {
         return (getCurrentPage() instanceof AbstractGraphicalEditorPage)
               ? ((AbstractGraphicalEditorPage) getCurrentPage()).getGraphicalViewer()
               : null;
      }
      return super.getAdapter(type);
   }

   public IEditorPart getCurrentPage()
   {
      if (-1 == getActivePage())
      {
         return null;
      }

      return getEditor(getActivePage());
   }

   protected void firePropertyChange(int propertyId)
   {
      super.firePropertyChange(propertyId);
      updateActions(editorActionIDs);
   }

   protected void createActions()
   {
      addStackAction(new UndoAction(this));
      addStackAction(new RedoAction(this));

      addEditPartAction(new CleanupModelAction(this));

      addEditPartAction(new ShrinkToFitAction(this));
      addEditPartAction(new DeleteAllAction(this));
      addEditPartAction(new DirectEditAction((IWorkbenchPart) this));

      addEditPartAction(new AlignmentSnapToGridAction(this, new AlignmentAction(
            (IWorkbenchPart) this, PositionConstants.LEFT), PositionConstants.LEFT));
      addEditPartAction(new AlignmentSnapToGridAction(this, new AlignmentAction(
            (IWorkbenchPart) this, PositionConstants.RIGHT), PositionConstants.RIGHT));
      addEditPartAction(new AlignmentSnapToGridAction(this, new AlignmentAction(
            (IWorkbenchPart) this, PositionConstants.TOP), PositionConstants.TOP));
      addEditPartAction(new AlignmentSnapToGridAction(this, new AlignmentAction(
            (IWorkbenchPart) this, PositionConstants.BOTTOM), PositionConstants.BOTTOM));
      addEditPartAction(new AlignmentSnapToGridAction(this, new AlignmentAction(
            (IWorkbenchPart) this, PositionConstants.CENTER), PositionConstants.CENTER));
      addEditPartAction(new AlignmentSnapToGridAction(this, new AlignmentAction(
            (IWorkbenchPart) this, PositionConstants.MIDDLE), PositionConstants.MIDDLE));

      addEditPartAction(new DistributeAction(this, PositionConstants.HORIZONTAL));
      addEditPartAction(new DistributeAction(this, PositionConstants.VERTICAL));

      addEditPartAction(new SnapToGridAction(this));

      addEditPartAction(new SetDefaultSizeAction(this));

      addEditPartAction(new GroupSymbolsAction(this));
      addEditPartAction(new UngroupSymbolsAction(this));

      addEditorAction(new SaveAction(this));
      addAction(new DiagramPrintAction(this));

      addEditPartAction(new CutAction(this));
      addEditPartAction(new CopySymbolAction(this));
      addEditPartAction(new PasteSymbolAction(this));
      addEditPartAction(new CreateSubprocessFromSelectionAction(this));
      addEditPartAction(new ShowInDiagramAction(this));
      addEditPartAction(new ShowInOutlineAction(this));

      IAction zoomIn = new ZoomInAction(getDelegatingZoomManager());
      IAction zoomOut = new ZoomOutAction(getDelegatingZoomManager());
      addAction(zoomIn);
      addAction(zoomOut);
      getSite().getKeyBindingService().registerAction(zoomIn);
      getSite().getKeyBindingService().registerAction(zoomOut);
   }

   protected ActionRegistry getActionRegistry()
   {
      if (null == actionRegistry)
      {
         actionRegistry = new ActionRegistry();
      }
      return actionRegistry;
   }

   protected CommandStack getSharedCommandStack()
   {
      if (null == sharedCommandStack)
      {
         sharedCommandStack = new CommandStack();
         sharedCommandStack.addCommandStackListener(new CommandStackListener()
         {
            public void commandStackChanged(EventObject event)
            {
               setDirty(sharedCommandStack.isDirty());
               updateActions(stackActionIDs);
            }
         });
      }
      return sharedCommandStack;
   }

   /*
    * private CommandStackListener getCommandStackListener() { if (null ==
    * commandStackListener) { this.commandStackListener = new CommandStackListener() {
    * public void commandStackChanged(EventObject event) { updateActions(stackActionIDs); } }; }
    * return commandStackListener; }
    */

   /*
    * protected MultiPageCommandStackListener getMultiPageCommandStackListener() { if
    * (null == multiPageCommandStackListener) { multiPageCommandStackListener = new
    * MultiPageCommandStackListener(); } return multiPageCommandStackListener; }
    */

   protected ISelectionListener getSelectionListener()
   {
      if (null == selectionListener)
      {
         this.selectionListener = new ISelectionListener()
         {
            public void selectionChanged(IWorkbenchPart part, ISelection selection)
            {
               updateActions(editPartActionIDs);
               /*
               IAction deleteAction = getActionRegistry().getAction(
                     ActionFactory.DELETE.getId());
               deleteAction.setEnabled(false);
               */
            }
         };
      }
      return selectionListener;
   }

   protected abstract boolean canDelete(ISelection selection);

   protected SelectionSynchronizer getSelectionSynchronizer()
   {
      if (null == synchronizer)
      {
         synchronizer = new SelectionSynchronizer();
      }
      return synchronizer;
   }

   protected DelegatingZoomManager getDelegatingZoomManager()
   {
      if (null == delegatingZoomManager)
      {
         delegatingZoomManager = new DelegatingZoomManager();
         if ((getCurrentPage() instanceof AbstractGraphicalEditorPage)
               && (null != ((AbstractGraphicalEditorPage) getCurrentPage())
                     .getGraphicalViewer()))
         {
            delegatingZoomManager
                  .setCurrentZoomManager(getZoomManager(((AbstractGraphicalEditorPage) getCurrentPage())
                        .getGraphicalViewer()));
         }
      }

      return delegatingZoomManager;
   }

   protected ZoomManager getZoomManager(GraphicalViewer viewer)
   {
      // get zoom manager from root edit part
      RootEditPart rootEditPart = (null != viewer) ? viewer.getRootEditPart() : null;
      ZoomManager zoomManager = null;
      if (rootEditPart instanceof ScalableFreeformRootEditPart)
      {
         zoomManager = ((ScalableFreeformRootEditPart) rootEditPart).getZoomManager();
      }
      else if (rootEditPart instanceof ScalableRootEditPart)
      {
         zoomManager = ((ScalableRootEditPart) rootEditPart).getZoomManager();
      }
      return zoomManager;
   }

   public boolean isDirty()
   {
      return isDirty;
   }

   protected void setDirty(boolean dirty)
   {
      if (isDirty != dirty)
      {
         this.isDirty = dirty;
         firePropertyChange(IEditorPart.PROP_DIRTY);
      }
   }

   public boolean isSaveAsAllowed()
   {
      return true;
   }

   protected void addAction(IAction action)
   {
      getActionRegistry().registerAction(action);
   }

   protected void addEditorAction(EditorPartAction action)
   {
      getActionRegistry().registerAction(action);
      editorActionIDs.add(action.getId());
   }

   protected void addEditPartAction(SelectionAction action)
   {
      getActionRegistry().registerAction(action);
      editPartActionIDs.add(action.getId());
   }

   protected void addStackAction(StackAction action)
   {
      getActionRegistry().registerAction(action);
      stackActionIDs.add(action.getId());
   }

   private void updateActions(List<String> actionIds)
   {
      for (String id : actionIds)
      {
         IAction action = getActionRegistry().getAction(id);
         if (null != action && action instanceof UpdateAction)
         {
            ((UpdateAction) action).update();
         }
      }
   }

   protected FileEditorInputTracker getEditorInputTracker()
   {
      if (null == editorInputTracker)
      {
         editorInputTracker = new FileEditorInputTracker(this);
         editorInputTracker.addChangeVisitor(new EditorCloseTracker());
      }
      return editorInputTracker;
   }

   protected void closeEditor(final boolean save)
   {
      getSite().getShell().getDisplay().syncExec(new Runnable()
      {
         public void run()
         {
            getSite().getPage().closeEditor(AbstractMultiPageGraphicalEditor.this, save);
         }
      });
   }

   protected PropertySheetPage getPropertySheetPage()
   {
      if (null == propertySheetPage)
      {
         propertySheetPage = new PropertySheetPage();
         propertySheetPage.setRootEntry(new UndoablePropSheetEntry(
               getSharedCommandStack()));
      }
      return propertySheetPage;
   }

   protected void setInput(IEditorInput input)
   {
      IEditorInput editorInput = getEditorInput();
      if (editorInput instanceof FileEditorInput)
      {
         IFile file = ((FileEditorInput) editorInput).getFile();
         if (null != editorInputTracker)
         {
            file.getWorkspace().removeResourceChangeListener(getEditorInputTracker());
         }
      }

      super.setInput(input);

      editorInput = getEditorInput();
      if (editorInput != null)
      {
         if (editorInput instanceof FileEditorInput)
         {
            IFile file = ((FileEditorInput) editorInput).getFile();
            file.getWorkspace().addResourceChangeListener(getEditorInputTracker());
         }
         setPartName(editorInput.getName());
      }
   }

   public void dispose()
   {
      // unbind multi page command stack listener
      // getMultiPageCommandStackListener().dispose();

      // remove delegating CommandStackListener
      // getDelegatingCommandStack().removeCommandStackListener(
      // getDelegatingCommandStackListener());

      // remove selection listener
      getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(
            getSelectionListener());

      // disposy the ActionRegistry (will unbind all actions)
      getActionRegistry().dispose();
      //actionRegistry = null;
      editorInputTracker = null;
      if (outlinePage != null)
      {
         outlinePage.dispose();
         outlinePage = null;
      }
      // important: always call super implementation of unbind
      super.dispose();
   }

   protected KeyHandler getSharedKeyHandler()
   {
      if (null == sharedKeyHandler)
      {
         sharedKeyHandler = new KeyHandler();

         // configure common keys for all viewers
         sharedKeyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry()
               .getAction(DiagramActionConstants.FORWARD_DELETE));
         sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry()
               .getAction(GEFActionConstants.DIRECT_EDIT));
         sharedKeyHandler.put(KeyStroke.getPressed(
               'c', 'c', 0), getActionRegistry()
               .getAction(DiagramActionConstants.CONNECT));

         // copy, cut, paste added
         sharedKeyHandler.put(KeyStroke.getPressed('c', SWT.CTRL, 0), getActionRegistry()
               .getAction(ActionFactory.COPY.getId()));
         sharedKeyHandler.put(KeyStroke.getPressed('x', SWT.CTRL, 0), getActionRegistry()
               .getAction(ActionFactory.CUT.getId()));
         sharedKeyHandler.put(KeyStroke.getPressed('v', SWT.CTRL, 0), getActionRegistry()
               .getAction(ActionFactory.PASTE.getId()));
      }
      return sharedKeyHandler;
   }

   protected AbstractMultiPageGraphicalEditorOutlinePage getOutlinePage()
   {
      if (null == outlinePage)
      {
         this.outlinePage = createOutlinePage();
      }
      return outlinePage;
   }

   protected void resetOutlinePage()
   {
      this.outlinePage = null;
   }

   /*
    * class MultiPageCommandStackListener implements CommandStackListener { private List
    * commandStacks = new ArrayList(2);
    *
    * public void addCommandStack(CommandStack commandStack) {
    * commandStacks.add(commandStack); commandStack.addCommandStackListener(this); }
    *
    * public void commandStackChanged(EventObject event) { if (((CommandStack)
    * event.getSource()).isDirty()) { setDirty(true); } else { boolean oneIsDirty = false;
    * for (Iterator i = commandStacks.iterator(); i.hasNext();) { CommandStack stack =
    * (CommandStack) i.next(); if (stack.isDirty()) { oneIsDirty = true; break; } }
    * setDirty(oneIsDirty); } }
    *
    * public void dispose() { for (Iterator i = commandStacks.iterator(); i.hasNext();) {
    * ((CommandStack) i.next()).removeCommandStackListener(this); } commandStacks.clear(); }
    *
    * public void markSaveLocations() { for (Iterator i = commandStacks.iterator();
    * i.hasNext();) { ((CommandStack) i.next()).markSaveLocation(); } } }
    */

   public class EditorCloseTracker implements IResourceDeltaVisitor
   {
      public boolean visit(IResourceDelta delta)
      {
         if (delta.getKind() == IResourceDelta.REMOVED)
         {
            if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0)
            {
               // if the file was deleted
               // NOTE: The case where an open, unsaved file is deleted is being handled
               // by the PartListener added to the Workbench in the initialize() method.
               if (!isDirty())
               {
                  closeEditor(false);
               }
            }
            else
            {
               // else if it was moved or renamed
               final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(
                     delta.getMovedToPath());
               Display display = getSite().getShell().getDisplay();
               display.asyncExec(new Runnable()
               {
                  public void run()
                  {
                     setInput(new FileEditorInput(newFile));
                  }
               });
            }
         }
         return false;
      }
   }
}