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
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultListener;
import org.eclipse.search.ui.ISearchResultPage;
import org.eclipse.search.ui.ISearchResultViewPart;
import org.eclipse.search.ui.SearchResultEvent;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.tree.WorkflowModelTreeEditPartFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;


public class SearchResultPage extends Page
      implements ISearchResultPage, ISearchResultListener
{
   private String id;

   private Object uiState;

   private WorkflowModelSearchResult searchResult;

   private ISearchResultViewPart part;

   private Composite composite;

   private TreeViewer viewer;

   private String label = ""; //$NON-NLS-1$

   public Object getUIState()
   {
      return uiState;
   }

   public void setInput(ISearchResult search, Object uiState)
   {
      this.uiState = uiState;
      this.searchResult = (WorkflowModelSearchResult) search;
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
      final WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      composite = FormBuilder.createComposite(parent, 1);
      composite.setLayout(new FillLayout());
      viewer = new TreeViewer(FormBuilder.createTree(composite, SWT.NONE));            
      viewer.setContentProvider(new ResultTreeContentProvider());
      viewer.setLabelProvider(new ResultLabelProvider(editor));
      viewer.setSorter(new ViewerSorter());
      viewer.addSelectionChangedListener(new ISelectionChangedListener()            
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            Object obj = ((IStructuredSelection) event.getSelection()).getFirstElement();
            obj = obj instanceof DescriptionType ? ((EObject) obj).eContainer() : obj;
            if (obj instanceof IModelElementNodeSymbol)
            {
               editor.selectSymbol((IModelElementNodeSymbol) obj);
            }
            else if (obj instanceof IModelElement)
            {
               Object part = new WorkflowModelTreeEditPartFactory(editor).createEditPart(
                     null, obj);
               if (!(part instanceof TreeEditPart))
               {
                  obj = ((IModelElement) obj).eContainer();
                  part = new WorkflowModelTreeEditPartFactory(editor).createEditPart(
                        null, obj);
               }
               editor.selectInOutline((IModelElement) obj);
            }
         }
      });
      Tree tree = (Tree) viewer.getControl();
      tree.setLayout(new FillLayout());
      tree.setLayoutData(null);
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
   {}

   public void searchResultChanged(SearchResultEvent e)
   {
      final Set matchedElements = searchResult.getMatchedElements();
      viewer.getControl().getDisplay().asyncExec(new Runnable()
      {
         public void run()
         {
            viewer.setInput(matchedElements);
            label = MessageFormat.format(Diagram_Messages.LB_Result, new Object[] {
                  ((WorkflowModelSearchQuery) searchResult.getQuery()).getSearchText(),
                  String.valueOf(matchedElements.size()),
                  matchedElements.size() == 1
                        ? Diagram_Messages.LB_Reference
                        : Diagram_Messages.LB_References});
            part.updateLabel();
         }
      });
   }
}
