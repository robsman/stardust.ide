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
package org.eclipse.stardust.modeling.core.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.dnd.ModelElementTransferDragSourceListener;
import org.eclipse.stardust.modeling.core.editors.parts.tree.OutlineTreeEditor;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;


public abstract class AbstractMultiPageGraphicalEditorOutlinePage
      extends ContentOutlinePage implements IAdaptable
{
   protected static final int ID_OUTLINE = 0;

   protected static final int ID_OVERVIEW = 1;

   private final WorkflowModelEditor editor;

   protected PageBook pageBook;

   protected Control outline;

   protected LightweightSystem overviewLws;

   protected Canvas overview;

   protected Thumbnail thumbnail;

   protected IAction showOutlineAction;

   protected IAction showOverviewAction;

   protected DisposeListener disposeListener;

   private ActionRegistry actionRegistry;

   private ISelectionListener selectionListener;

   private OutlineTreeEditor outlineTreeEditor;

   AbstractMultiPageGraphicalEditorOutlinePage(WorkflowModelEditor editor,
         EditPartViewer viewer)
   {
      super(viewer);
      this.editor = editor;
   }

   protected abstract EditPartFactory createOutlineEditPartFactory();

   protected abstract void contributeToolBarActions(IToolBarManager tbm);

   public void setOutlineContents(Object contents)
   {
      if (getViewer().getEditPartFactory() != null)
      {
         getViewer().setContents(contents);
      }
      Tree tree = (Tree) getViewer().getControl();
      if (tree != null && tree.getItemCount() > 0)
      {
         tree.getItem(0).setExpanded(true);
      }
   }

   public void editorPageChanged()
   {
      initializeOverview();
   }

   public void init(IPageSite pageSite)
   {
      pageSite.setSelectionProvider(getViewer());

      super.init(pageSite);

      getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(
            getSelectionListener());

      createActions();

      ActionRegistry registry = getActionRegistry();
      IActionBars bars = pageSite.getActionBars();
      String id = ActionFactory.UNDO.getId();
      bars.setGlobalActionHandler(id, registry.getAction(id));
      id = ActionFactory.REDO.getId();
      bars.setGlobalActionHandler(id, registry.getAction(id));
      id = ActionFactory.DELETE.getId();
      bars.setGlobalActionHandler(id, registry.getAction(id));
      id = ActionFactory.PRINT.getId();
      bars.setGlobalActionHandler(id, registry.getAction(id));
      id = ActionFactory.COPY.getId();
      bars.setGlobalActionHandler(id, registry.getAction(id));
      id = ActionFactory.PASTE.getId();
      bars.setGlobalActionHandler(id, registry.getAction(id));
      id = ActionFactory.CUT.getId();
      bars.setGlobalActionHandler(id, registry.getAction(id));
      // id = IncrementDecrementAction.INCREMENT;
      // bars.setGlobalActionHandler(id, registry.getAction(id));
      // id = IncrementDecrementAction.DECREMENT;
      // bars.setGlobalActionHandler(id, registry.getAction(id));
      bars.updateActionBars();
   }

   protected abstract void createActions();

   protected abstract boolean canDelete(ISelection selection);

   protected ISelectionListener getSelectionListener()
   {
      if (null == selectionListener)
      {
         this.selectionListener = new ISelectionListener()
         {
            public void selectionChanged(IWorkbenchPart part, ISelection selection)
            {
               // updateActions(editPartActionIDs);
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

   public void createControl(Composite parent)
   {
      this.pageBook = new PageBook(parent, SWT.NONE);
      this.outline = getViewer().createControl(pageBook);
      this.overview = new Canvas(pageBook, SWT.NONE);

      pageBook.showPage(outline);

      configureOutlineViewer();
      hookOutlineViewer();
      initializeOutlineViewer();
      addOutlineTreeEditor(parent);
   }

   private void addOutlineTreeEditor(Composite parent)
   {
      Tree tree = (Tree) getViewer().getControl();
      outlineTreeEditor = new OutlineTreeEditor(tree, getViewer());
   }

   public OutlineTreeEditor getOutlineTreeEditor()
   {
      return outlineTreeEditor;
   }

   public Control getControl()
   {
      return pageBook;
   }

   public void dispose()
   {
      editor.getSelectionSynchronizer().removeViewer(getViewer());

      if (disposeListener != null && getCanvas() != null && !getCanvas().isDisposed())
      {
         getCanvas().removeDisposeListener(disposeListener);
      }

      editor.resetOutlinePage();

      super.dispose();
   }

   public Object getAdapter(Class type)
   {
      Object adapter;
      if (type == ZoomManager.class
            && (editor.getCurrentPage() instanceof AbstractGraphicalEditorPage))
      {
         adapter = ((AbstractGraphicalEditorPage) editor.getCurrentPage())
               .getGraphicalViewer().getProperty(ZoomManager.class.toString());
      }
      else
      {
         adapter = null;
      }
      return adapter;
   }

   protected void showPage(int id)
   {
      if (id == ID_OUTLINE)
      {
         showOutlineAction.setChecked(true);
         showOverviewAction.setChecked(false);
         pageBook.showPage(outline);
         if (thumbnail != null)
         {
            thumbnail.setVisible(false);
         }
      }
      else if (id == ID_OVERVIEW)
      {
         if (thumbnail == null)
         {
            initializeOverview();
         }
         else
         {
            updateOverview();
         }
         showOutlineAction.setChecked(false);
         showOverviewAction.setChecked(true);
         pageBook.showPage(overview);
         if (thumbnail != null)
         {
            thumbnail.setVisible(true);
         }
      }
   }

   protected void initializeOutlineViewer()
   {
      EObject model = (EObject) editor.getModel();
      if (model != null && editor.checkUpgradeModel())
      {
         setOutlineContents(model.eContainer());
      }
      else
      {
         setOutlineContents(null);
      }
   }

   protected void hookOutlineViewer()
   {
      editor.getSelectionSynchronizer().addViewer(getViewer());
   }

   protected void initializeOverview()
   {
      if (null == overviewLws)
      {
         overviewLws = new LightweightSystem(overview);
      }

      updateOverview();
   }

   protected void updateOverview()
   {
      RootEditPart rep = (editor.getCurrentPage() instanceof AbstractGraphicalEditorPage)
            ? ((AbstractGraphicalEditorPage) editor.getCurrentPage())
                  .getGraphicalViewer().getRootEditPart()
            : null;
      if (rep instanceof ScalableFreeformRootEditPart)
      {
         ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) rep;
         final Thumbnail thumbnail = new ScrollableThumbnail((Viewport) root.getFigure());
         thumbnail.setBorder(new MarginBorder(3));
         thumbnail.setSource(root.getLayer(LayerConstants.PRINTABLE_LAYERS));
         overviewLws.setContents(thumbnail);
         this.disposeListener = new DisposeListener()
         {
            public void widgetDisposed(DisposeEvent e)
            {
               thumbnail.deactivate();
               if (AbstractMultiPageGraphicalEditorOutlinePage.this.thumbnail == thumbnail)
               {
                  AbstractMultiPageGraphicalEditorOutlinePage.this.thumbnail = null;
               }
            }
         };
         getCanvas().addDisposeListener(disposeListener);
         AbstractMultiPageGraphicalEditorOutlinePage.this.thumbnail = thumbnail;
      }
      else
      {
         overviewLws.setContents(new Figure());
      }
   }

   protected FigureCanvas getCanvas()
   {
      FigureCanvas canvas = null;

      AbstractGraphicalEditorPage currentPage = (editor.getCurrentPage() instanceof AbstractGraphicalEditorPage)
            ? (AbstractGraphicalEditorPage) editor.getCurrentPage()
            : null;
      if (null != currentPage)
      {
         GraphicalViewer graphicalViewer = currentPage.getGraphicalViewer();
         if (null != graphicalViewer)
         {
            return (FigureCanvas) graphicalViewer.getControl();
         }
      }

      return canvas;
   }

   protected void configureOutlineViewer()
   {
      getViewer().setEditDomain(editor.getEditDomain());
      getViewer().setEditPartFactory(createOutlineEditPartFactory());

      // ContextMenuProvider provider = new LogicContextMenuProvider(getViewer(),
      // editor.getActionRegistry());
      // getViewer().setContextMenu(provider);
      // getSite().registerContextMenu("org.eclipse.gef.examples.logic.outline.contextmenu",
      // //$NON-NLS-1$
      // provider, getSite().getSelectionProvider());

      getViewer().setKeyHandler(editor.getSharedKeyHandler());

      getViewer().addDragSourceListener(
            new ModelElementTransferDragSourceListener(getViewer()));

      IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
      this.showOutlineAction = new Action()
      {
         public void run()
         {
            showPage(ID_OUTLINE);
         }
      };
      showOutlineAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor("icons/outline.gif")); //$NON-NLS-1$
      showOutlineAction.setToolTipText(Diagram_Messages.ToolTip_Outline);
      tbm.add(showOutlineAction);

      this.showOverviewAction = new Action()
      {
         public void run()
         {
            showPage(ID_OVERVIEW);
         }
      };
      showOverviewAction.setImageDescriptor(DiagramPlugin
            .getImageDescriptor("icons/overview.gif")); //$NON-NLS-1$
      showOverviewAction.setToolTipText(Diagram_Messages.ToolTip_Graphical);
      tbm.add(showOverviewAction);

      contributeToolBarActions(tbm);

      ContextMenuProvider provider = new WorkflowModelEditorContextMenuProvider(
            getViewer(), getActionRegistry(), editor);
      getViewer().setContextMenu(provider);
      getSite().registerContextMenu(
            "org.eclipse.stardust.modeling.core.outline.contextmenu", //$NON-NLS-1$
            provider, getViewer());

      outline.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            EditPart selection = getSelectedPart();
            if (selection != null)
            {
               IAction action = null;
               // selection.getModel is ConnectionImpl
               Object model = selection.getModel();
               if(model instanceof DiagramType)
               {
                  action = getActionRegistry().getAction(DiagramActionConstants.DIAGRAM_OPEN);
               }
               else if(model instanceof org.eclipse.stardust.modeling.repository.common.Connection)
               {
                  action = getActionRegistry().getAction(ObjectRepositoryActivator.CONNECTION_RESPOSITORY_SELECTION);
               }
               else
               {
                  action = getActionRegistry().getAction(ActionFactory.PROPERTIES.getId());
               }
               if (action.isEnabled())
               {
                  action.run();
               }
            }
         }

         public void mouseUp(MouseEvent e)
         {
            EditPart selection = getSelectedPart();
            IAction action = (selection != null)
                  && (selection.getModel() instanceof DiagramType) ? getActionRegistry()
                  .getAction(DiagramActionConstants.DIAGRAM_OPEN) : null;
            if (action != null && action.isEnabled())
            {
               action.run();
            }
            IWorkbenchPage activePage = PlatformUI.getWorkbench()
                  .getActiveWorkbenchWindow().getActivePage();
            if (activePage.getActivePart() instanceof ISearchResultViewPart)
            {
               activePage.activate(activePage
                     .findView("org.eclipse.ui.views.ContentOutline")); //$NON-NLS-1$
            }
         }
      });

      showPage(ID_OUTLINE);
   }

   private EditPart getSelectedPart()
   {
      ISelection selection = getSelection();
      return selection instanceof IStructuredSelection
            && ((IStructuredSelection) selection).size() == 1
            ? (EditPart) ((IStructuredSelection) selection).getFirstElement()
            : null;
   }

   protected ActionRegistry getActionRegistry()
   {
      if (null == actionRegistry)
      {
         actionRegistry = new DelegatingActionRegistry(getEditor().getActionRegistry());
      }
      return actionRegistry;
   }

   public AbstractMultiPageGraphicalEditor getEditor()
   {
      return editor;
   }
}