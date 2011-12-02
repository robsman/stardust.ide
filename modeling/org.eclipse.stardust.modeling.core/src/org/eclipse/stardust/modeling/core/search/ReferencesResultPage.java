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
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultListener;
import org.eclipse.search.ui.ISearchResultPage;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.search.ui.SearchResultEvent;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.IdentifiableModelElementTreeEditPart;
import org.eclipse.stardust.modeling.core.search.tree.EditorSelectionChangedListener;
import org.eclipse.stardust.modeling.core.search.tree.ResultViewTreeEditPartFactory;
import org.eclipse.stardust.modeling.core.search.tree.ResultViewTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;


public class ReferencesResultPage extends Page
implements ISearchResultPage, ISearchResultListener, ISelectionProvider
{
   private String id;
   private String label = ""; //$NON-NLS-1$
   
   private Object uiState;
   
   private Composite composite;
   private Tree tree;
   private ResultViewTreeViewer viewer;
   
   private Collection selectionChangedListeners = new ArrayList();
   private EditorSelectionChangedListener selectionChangedListener;
   private ISelection pageSelection;
   
   private ReferencesSearchResult searchResult;
   private ISearchResultViewPart part;

   private WorkflowModelEditor editor;
   
   private ModelType model;

   private MenuManager menuManager;
   
   public Object getUIState()
   {
      return uiState;
   }

   public void setInput(ISearchResult search, Object uiState)
   {
      this.uiState = uiState;
      this.searchResult = (ReferencesSearchResult) search;
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
      
      tree = FormBuilder.createTree(composite, SWT.MULTI);
      viewer = new ResultViewTreeViewer();
      viewer.setControl(tree);

      menuManager = new MenuManager();      
      tree.setMenu(menuManager.createContextMenu(tree));
      
      selectionChangedListener = new EditorSelectionChangedListener(this);
      model = (ModelType) editor.getModel();
      initViewer(editor);
      
      viewer.addSelectionChangedListener(selectionChangedListener);      
      getSite().setSelectionProvider(viewer);
      viewer.getControl().setVisible(false);   
      tree.setLayout(new FillLayout());
      tree.setLayoutData(null);      
   }

   private void initViewer(WorkflowModelEditor editor)
   {
      ResultViewTreeEditPartFactory factory = new ResultViewTreeEditPartFactory(editor);      
      viewer.setEditDomain(editor.getEditDomain());
      viewer.setEditPartFactory(factory);
      
      selectionChangedListener.setEditor(editor);
   }

   public void dispose()
   {
      super.dispose();
   }

   public Control getControl()
   {
      return composite;
   }

   public void setFocus()
   {
   }

   public void searchResultChanged(SearchResultEvent e)
   {
      final Map matchedElements = searchResult.getMatchedElements();
      viewer.getControl().getDisplay().asyncExec(new Runnable()
      {
         public void run()
         {
            getControl().setFocus();
            
            editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
               .getActiveWorkbenchWindow().getActivePage().getActiveEditor();            
            initViewer(editor);     
            model = (ModelType) editor.getModel();                                    
            viewer.setMatchedElements(matchedElements);
            // if user changed to other model, we need to set the model again
            viewer.setContents(model, null, false);
      
            Integer numberMatchedElements = new Integer(viewer.getResultViewFilter().getNumberMatchedElements());
            
            label = MessageFormat.format(Diagram_Messages.LB_Result, new Object[] {
                  ((ReferenceSearchQuery) searchResult.getQuery()).getLabel(),
                  numberMatchedElements, 
                  numberMatchedElements.intValue() == 1                  
                        ? Diagram_Messages.LB_Reference
                        : Diagram_Messages.LB_References});
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
      menuManager.removeAll();
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
      Object object = ((StructuredSelection) selection).getFirstElement();
      if(object instanceof AbstractEObjectTreeEditPart
            || object instanceof IdentifiableModelElementTreeEditPart)
      {
         EObject model = (EObject) ((AbstractEObjectTreeEditPart) object).getModel();
         if(!(model instanceof TypeDeclarationsType))
         {
            editor.selectElement(model);
         }
      } 
      else if(object instanceof DiagramEditPart)
      {
         EObject model = (EObject) ((DiagramEditPart) object).getModel();
         editor.selectElement(model);
      }      
      else if(object instanceof AbstractModelElementNodeSymbolEditPart)
      {
         EObject model = (EObject) ((AbstractModelElementNodeSymbolEditPart) object).getModel();
         editor.selectSymbol((INodeSymbol) model);         
      }
      
      SelectionAction action = (SelectionAction) editor.getActionRegistry().getAction(DiagramActionConstants.SHOW_IN_DIAGRAM);
      if(action.isEnabled())
      {
         menuManager.add(action);      
      }
   }   
}