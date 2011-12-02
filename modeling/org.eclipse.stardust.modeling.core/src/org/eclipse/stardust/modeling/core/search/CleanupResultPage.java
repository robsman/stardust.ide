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
package org.eclipse.stardust.modeling.core.search;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultListener;
import org.eclipse.search.ui.ISearchResultPage;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.search.ui.SearchResultEvent;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdaptee;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.parts.tree.IdentifiableModelElementTreeEditPart;
import org.eclipse.stardust.modeling.core.search.actions.DeleteSelectedAction;
import org.eclipse.stardust.modeling.core.search.tree.EditorSelectionChangedListener;
import org.eclipse.stardust.modeling.core.search.tree.ResultViewModelTreeEditPart;
import org.eclipse.stardust.modeling.core.search.tree.ResultViewTreeEditPartFactory;
import org.eclipse.stardust.modeling.core.search.tree.ResultViewTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;


public class CleanupResultPage extends Page
      implements ISearchResultPage, ISearchResultListener, ISelectionProvider, NotificationAdaptee
{
   private String id;
   private String label = ""; //$NON-NLS-1$
   
   private Object uiState;
   
   private Composite composite;
   private Tree tree;
   private ResultViewTreeViewer viewer;
   
   private ActionRegistry actionRegistry;
   
   private Collection selectionChangedListeners = new ArrayList();
   private EditorSelectionChangedListener selectionChangedListener;
   private ISelection pageSelection;
   
   private CleanupModelSearchResult searchResult;
   private ISearchResultViewPart part;

   private ModelType model;
   
   private SelectionAction action;
   
   private NotificationAdapter  notificationAdapter;
   private Set checkedItems;
   
   public Object getUIState()
   {
      return uiState;
   }

   protected ActionRegistry getActionRegistry()
   {
      if (null == actionRegistry)
      {
         actionRegistry = new ActionRegistry();
      }
      return actionRegistry;
   }   
   
   public void setInput(ISearchResult search, Object uiState)
   {
      this.uiState = uiState;
      this.searchResult = (CleanupModelSearchResult) search;
      if (searchResult != null)
      {
         searchResult.addListener(this);
      }
   }

   public void setViewPart(ISearchResultViewPart part)
   {
      this.part = part;
   }

   public void restoreState(IMemento memento)
   {}

   public void saveState(IMemento memento)
   {}

   public void setID(String id)
   {
      this.id = id;
   }

   public String getID()
   {
      return id;
   }

   public String getLabel()
   {
      return label;
   }

   public IPageSite getSite()
   {
      return super.getSite();
   }

   public void init(IPageSite site)
   {
      super.init(site);
   }

   public void createControl(Composite parent)
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      composite = FormBuilder.createComposite(parent, 1);
      composite.setLayout(new FillLayout());
      
      tree = FormBuilder.createTree(composite, SWT.CHECK);
      viewer = new ResultViewTreeViewer();
      viewer.setControl(tree);
      
      selectionChangedListener = new EditorSelectionChangedListener(this);
      initViewer(editor);
      
      viewer.addSelectionChangedListener(selectionChangedListener);      
      getSite().setSelectionProvider(viewer);
      viewer.getControl().setVisible(false);   
      tree.setLayout(new FillLayout());
      tree.setLayoutData(null);
      tree.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            if (e.detail == SWT.CHECK)
            {
               TreeItem item = (TreeItem) e.item;
               setState(item, true);
            }
         }
      });      
   }

   private void initViewer(WorkflowModelEditor editor)
   {
      action = new DeleteSelectedAction(editor);      
      getSite().getActionBars().getToolBarManager().removeAll();
      getSite().getActionBars().getToolBarManager().add(action);
      
      ResultViewTreeEditPartFactory factory = new ResultViewTreeEditPartFactory(editor);      
      viewer.setEditDomain(editor.getEditDomain());
      viewer.setEditPartFactory(factory);
            
      selectionChangedListener.setEditor(editor);
   }

   public void dispose()
   {
      if(model != null)
      {
         model.eAdapters().remove(notificationAdapter);
      }                  
      super.dispose();
   }

   public Control getControl()
   {
      return composite;
   }

   public void setFocus()
   {}

   public void searchResultChanged(SearchResultEvent e)
   {
      final Map matchedElements = searchResult.getMatchedElements();
      viewer.getControl().getDisplay().asyncExec(new Runnable()
      {
         public void run()
         {
            WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
                  .getActiveWorkbenchWindow().getActivePage().getActiveEditor();            
            initViewer(editor);            
            viewer.setMatchedElements(matchedElements);
            notificationAdapter = new NotificationAdapter(CleanupResultPage.this);
            
            // if user changed to other model, we need to set the model again
            if(model != null)
            {
               model.eAdapters().remove(notificationAdapter);
            }            
            model = (ModelType) editor.getModel();            
            model.eAdapters().add(notificationAdapter);
            
            viewer.setContents(model, Diagram_Messages.LB_UnusedModelElements, true);
            if (tree != null && tree.getItemCount() > 0)
            {
               tree.getItem(0).setExpanded(true);
            }
            
            Integer numberMatchedElements = new Integer(viewer.getResultViewFilter().getNumberMatchedElements());
            
            label = MessageFormat.format(Diagram_Messages.LB_Result, new Object[] {
                  ((CleanupModelSearchQuery) searchResult.getQuery()).getLabel(),
                  numberMatchedElements, 
                  numberMatchedElements.intValue() == 1
                        ? Diagram_Messages.LB_Item 
                        : Diagram_Messages.LB_Items});
            part.updateLabel();
         }
      });
   }

   public void addSelectionChangedListener(ISelectionChangedListener listener)
   {
      selectionChangedListeners.add(listener);      
   }

   public ISelection getSelection()
   {
      return pageSelection;
   }

   public void removeSelectionChangedListener(ISelectionChangedListener listener)
   {
      selectionChangedListeners.remove(listener);      
   }

   public void setSelection(ISelection selection)
   {
      if(selection == null)
      {
         return;
      }      
      
      pageSelection = selection;
      for (Iterator listeners = selectionChangedListeners.iterator(); listeners.hasNext(); )
      {
         ISelectionChangedListener listener = (ISelectionChangedListener)listeners.next();
         listener.selectionChanged(new SelectionChangedEvent(this, selection));
      }      
   }  
   
   // must also set all items (editparts) checked for delete
   private void setState(TreeItem item, boolean setChecked)
   {
      boolean checked = item.getChecked();      
      if(checkedItems == null)
      {
         checkedItems = new HashSet();
      }      
      setItem(item, checked, setChecked);
      ((DeleteSelectedAction) action).setSelectedElements(checkedItems);
      action.update();   
      getSite().getActionBars().getToolBarManager().update(true);
   }
   
   private void setItem(TreeItem item, boolean checked, boolean setChecked)
   {
      Object data = item.getData();        
      if(data instanceof ResultViewModelTreeEditPart)
      {
         ResultViewModelTreeEditPart editPartItem = (ResultViewModelTreeEditPart) data;
         TreeItem editPartTreeItem = (TreeItem) editPartItem.getWidget();
         if(setChecked)
         {
            editPartTreeItem.setChecked(checked);
         }         
         TreeItem[] itemsArray = item.getItems();
         List items = Arrays.asList(itemsArray);         
         for (Iterator i = items.iterator(); i.hasNext();)
         {
            TreeItem childItem = (TreeItem) i.next();   
            setItem(childItem, checked, setChecked);
         }         
      }
      else if(data instanceof ChildCategoryNode)
      {
         /*
         List children = ((ChildCategoryNode) data).getChildren();
         if(children.isEmpty())
         {
            item.setChecked(false);
            return;
         }
         */
         ChildCategoryNode editPartItem = (ChildCategoryNode) data;
         TreeItem editPartTreeItem = (TreeItem) editPartItem.getWidget();
         if(setChecked)
         {
            editPartTreeItem.setChecked(checked);
         }
         TreeItem[] itemsArray = item.getItems();
         List items = Arrays.asList(itemsArray);         
         for (Iterator i = items.iterator(); i.hasNext();)
         {
            TreeItem childItem = (TreeItem) i.next();   
            setItem(childItem, checked, setChecked);
         }         
      }
      else if(data instanceof AbstractEObjectTreeEditPart
            || data instanceof IdentifiableModelElementTreeEditPart)
      {
         EObject model = (EObject) ((AbstractEObjectTreeEditPart) data).getModel();
         AbstractEObjectTreeEditPart editPartItem = (AbstractEObjectTreeEditPart) data;
         TreeItem editPartTreeItem = (TreeItem) editPartItem.getWidget();
         if(setChecked)
         {
            editPartTreeItem.setChecked(checked);
         }
         boolean isChecked = item.getChecked();      
         if(!(model instanceof TypeDeclarationsType))
         {
            if(isChecked)
            {
               checkedItems.add(model);
            }
            else
            {
               checkedItems.remove(model);               
            }            
         }
         TreeItem[] itemsArray = item.getItems();
         List items = Arrays.asList(itemsArray);         
         for (Iterator i = items.iterator(); i.hasNext();)
         {
            TreeItem childItem = (TreeItem) i.next();               
            setItem(childItem, checked, setChecked);
         }         
      } 
      else if(data instanceof DiagramEditPart)
      {
         // later
         // EObject model = (EObject) ((DiagramEditPart) data).getModel();
      }      
      else if(data instanceof AbstractModelElementNodeSymbolEditPart)
      {
         // later
         // EObject model = (EObject) ((AbstractModelElementNodeSymbolEditPart) data).getModel();
      }      
   }

   public Object getModel()
   {
      // nothing
      return null;
   }

   public void handleNotification(Notification notification)
   {
      RootEditPart root = viewer.getRootEditPart();
      ResultViewModelTreeEditPart modelTreeEditPart = (ResultViewModelTreeEditPart) ((AbstractEObjectTreeEditPart) root.getChildren().get(0)).getChildren().get(0);
      TreeItem editPartTreeItem = (TreeItem) modelTreeEditPart.getWidget();
      if(editPartTreeItem != null)
      {
         checkedItems = new HashSet();
         setItem(editPartTreeItem, false, false);
         ((DeleteSelectedAction) action).setSelectedElements(checkedItems);
         action.update();            
         getSite().getActionBars().getToolBarManager().update(true);
      }
   }
}