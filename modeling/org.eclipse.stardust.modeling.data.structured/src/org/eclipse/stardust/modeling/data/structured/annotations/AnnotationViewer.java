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
package org.eclipse.stardust.modeling.data.structured.annotations;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotBooleanEditor;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;

import ag.carnot.base.CollectionUtils;

public class AnnotationViewer
{
   private static final String[] registryColumns = {
      Structured_Messages.COL_PROPERTY,
      Structured_Messages.COL_VALUE,
      Structured_Messages.COL_EDIT_IN_STRUCTURE,
      Structured_Messages.COL_CHANGE_ALL
   };
   
   private TreeViewer viewer;
   private boolean changeAllMode;
   
   public boolean isChangeAllMode() {
	return changeAllMode;
}

   public AnnotationCellEditor getAnnotationEditor() {
		return annotationEditor;
	}
   
private AnnotationCellEditor annotationEditor;


private AnnotationContentProvider annotationContentProvider;
   
   public AnnotationContentProvider getAnnotationContentProvider() {
	return annotationContentProvider;
}

private Map<IAnnotation, Boolean> changeAllValues = CollectionUtils.newMap();
   private Map<IAnnotation, AnnotationCellEditor> editorsMap = CollectionUtils.newMap();

   private CarnotBooleanEditor changeAll;

   private CarnotBooleanEditor editInStructure;

   public Control createControl(Composite composite, final TreeViewer observed)
   {
      final Tree tree = FormBuilder.createTree(composite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER);
      tree.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData(2));
      tree.setHeaderVisible(true);
      
      viewer = new TreeViewer(tree);
      for (int i = 0; i < registryColumns.length; i++)
      {
         TreeColumn column = new TreeColumn(tree, SWT.LEFT);
         column.setText(registryColumns[i]);
         column.setWidth(200);
      }
      viewer.setUseHashlookup(true);
      
      annotationContentProvider = new AnnotationContentProvider();
      viewer.setContentProvider(annotationContentProvider);
      viewer.setLabelProvider(new AnnotationLabelProvider());
      
      viewer.setColumnProperties(registryColumns);

      annotationEditor = new AnnotationCellEditor(viewer, 1, null)
      {
         @Override
         public void updateViewer(IAnnotation annotation)
         {
            super.updateViewer(annotation);
            propagate(observed, annotation);
            if (editorsMap.containsKey(annotation))
            {
               observed.refresh(true);
            }
         }
      };
      tree.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            TreeItem item = (TreeItem)e.item;
            IAnnotation annotation = (IAnnotation) item.getData();
            AnnotationCellEditor editor = editorsMap.get(annotation);
            if (editor != null)
            {
               editor.stopEditing();
               observed.refresh(true);
            }
            annotationEditor.update(item);
         }
      });
      final Point position = new Point(0, 0);
      final Tree observedTree = (Tree) observed.getControl();
      observedTree.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseDown(MouseEvent e)
         {
            position.x = e.x;
            position.y = e.y;
         }
      });
      observedTree.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            TreeColumn[] columns = observedTree.getColumns();
            int[] order = observedTree.getColumnOrder();
            int x0 = 0;
            int x1 = x0;
            for (int i = 0; i < order.length; i++)
            {
               TreeColumn column = columns[order[i]];
               Object data = column.getData();
               x1 += column.getWidth();
               if (data instanceof IAnnotation)
               {
                  AnnotationCellEditor editor = editorsMap.get(data);
                  if (position.x >= x0 && position.x < x1)
                  {
                     editor.update((TreeItem)e.item);
                  }
                  else
                  {
                     editor.stopEditing();
                  }
               }
               x0 = x1;
            }
         }
      });
      
      annotationContentProvider.setCurrentElement(null);
      viewer.setInput("dummy");
      viewer.expandAll();
      viewer.getControl().setVisible(false);
      
      MenuManager menuManager = createMenuManager(new Action[] {createDeleteAction()});
      tree.setMenu(menuManager.createContextMenu(tree));
      
      changeAll = new CarnotBooleanEditor(3)
      {
         public boolean canEdit(Object element)
         {
            IAnnotation annotation = (IAnnotation) element;
            return DefaultAnnotationModifier.canModifyAnnotation(annotation);
         }

         public Object getValue(Object element)
         {
            IAnnotation annotation = (IAnnotation) element;
            return changeAllValues.containsKey(annotation);
         }

         public void setValue(Object element, Object value)
         {
            IAnnotation annotation = (IAnnotation) element;
            Boolean newValue = (Boolean) value;
            changeAllMode = newValue;
            if (newValue)
            {
               changeAllValues.put(annotation, newValue);               
            }
            else
            {
               changeAllValues.remove(annotation);
            }
         }

         @Override
         public void refresh(TreeEditor editor)
         {
            super.refresh(editor);
            Button button = (Button) editor.getEditor();
            Item item = editor.getItem();
            IAnnotation annotation = (IAnnotation) item.getData();
            button.setVisible(editorsMap.containsKey(annotation));
         }
      };
      changeAll.setTree(tree);
      changeAll.refresh();

      editInStructure = new CarnotBooleanEditor(2)
      {
         public boolean canEdit(Object element)
         {
            IAnnotation annotation = (IAnnotation) element;
            return DefaultAnnotationModifier.canModifyAnnotation(annotation);
         }

         public Object getValue(Object element)
         {
            IAnnotation annotation = (IAnnotation) element;
            return editorsMap.containsKey(annotation) ? Boolean.TRUE : Boolean.FALSE;
         }

         public void setValue(Object element, Object value)
         {
            IAnnotation annotation = (IAnnotation) element;
            if ((Boolean) value)
            {
               TreeColumn column = new TreeColumn(observedTree, SWT.LEFT);
               column.setText(annotation.getName());
               column.setWidth(200);
               column.setData(annotation);
               observedTree.showColumn(column);
               observed.refresh(true);
               int index = observedTree.indexOf(column);
               AnnotationCellEditor editor = new AnnotationCellEditor(observed, index, annotation)
               {
                  @Override
                  public void updateViewer(IAnnotation annotation)
                  {
                     super.updateViewer(annotation);
                     if (propagate(observed, annotation))
                     {
                        observed.refresh(true);
                     }
                     viewer.refresh(annotation, true);
                  }
               };
               editorsMap.put(annotation, editor);
            }
            else
            {
               AnnotationCellEditor editor = editorsMap.remove(annotation);
               if (editor != null)
               {
                  editor.dispose();
               }
               TreeColumn column = findColumn(observedTree, annotation);
               if (column != null)
               {
                  column.dispose();
               }
            }
            changeAll.refresh();
         }
      };
      editInStructure.setTree(tree);
      
      return tree;
   }

   private boolean propagate(TreeViewer observed, IAnnotation annotation)
   {
      if (changeAllValues.containsKey(annotation))
      {
         ITreeContentProvider provider = (ITreeContentProvider) observed.getContentProvider();
         Object[] elements = provider.getElements(observed.getInput());
         changeAll(annotation, provider, elements, annotation.getRawValue());
         return true;
      }
      return false;
   }

   private void changeAll(IAnnotation annotation, ITreeContentProvider provider,
         Object[] elements, String rawValue)
   {
      if (elements != null)
      {
         for (int i = 0; i < elements.length; i++)
         {
            if (elements[i] instanceof XSDElementDeclaration)
            {
               XSDElementDeclaration element = (XSDElementDeclaration) elements[i];
               if (element.eContainer() != null && !(element.getType() instanceof XSDComplexTypeDefinition))
               {
                  annotation.setRawValue(element, rawValue);
               }
            }
            changeAll(annotation, provider, provider.getChildren(elements[i]), rawValue);
         }
      }
   }

   private MenuManager createMenuManager(final Action[] actions)
   {
      MenuManager menuManager = new MenuManager();
      menuManager.addMenuListener(new IMenuListener()
      {
         public void menuAboutToShow(IMenuManager manager)
         {
            boolean enabled = true;
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            if (selection != null && !selection.isEmpty())
            {
               @SuppressWarnings("unchecked")
               Iterator<IAnnotation> annotations = selection.iterator();
               while (annotations.hasNext())
               {
                  IAnnotation annotation = annotations.next();
                  if (!annotation.exists() || !DefaultAnnotationModifier.canModifyAnnotation(annotation))
                  {
                     enabled = false;
                     break;
                  }
               }
            }
            for (int i = 0; i < actions.length; i++)
            {
               actions[i].setEnabled(enabled);
            }
         }
      });
      for (int i = 0; i < actions.length; i++)
      {
         menuManager.add(actions[i]);
      }
      return menuManager;
   }

   private Action createDeleteAction()
   {
      return new Action(Structured_Messages.ACTION_DELETE)
      {
         public void runWithEvent(Event event)
         {
            annotationEditor.stopEditing();
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            if (selection != null && !selection.isEmpty())
            {
               @SuppressWarnings("unchecked")
               Iterator<IAnnotation> annotations = selection.iterator();
               while (annotations.hasNext())
               {
                  IAnnotation annotation = annotations.next();
                  IAnnotation parent = annotation.getParent();
                  DefaultAnnotationModifier.deleteAnnotation(annotation);
                  if (parent == null)
                  {
                     viewer.refresh();
                  }
                  else
                  {
                     viewer.refresh(parent);
                  }
               }
            }
         }
      };
   }

   public void setInput(XSDElementDeclaration element)
   {
      annotationEditor.stopEditing();
      if (element != null && element.getType() instanceof XSDComplexTypeDefinition)
      {
         annotationContentProvider.removeIPPAnnotations(element);
      }
      annotationContentProvider.setCurrentElement(element);
      if (element == null)
      {
         viewer.getControl().setVisible(false);
      }
      else
      {
         viewer.refresh(true);
         viewer.expandAll();
         editInStructure.setTree(viewer.getTree());
         changeAll.setTree(viewer.getTree());
         viewer.getControl().setVisible(true);
      }
   }

   public void createDefaultAnnotations(XSDElementDeclaration xsdElement)
   {
      // we don't create default annotations
   }

   public void setDeclaration(TypeDeclarationType declaration)
   {
      annotationContentProvider.setDeclaration(declaration);
   }

   private TreeColumn findColumn(final Tree tree, IAnnotation annotation)
   {
      TreeColumn[] columns = tree.getColumns();
      for (int i = 0; i < columns.length; i++)
      {
         if (columns[i].getData() == annotation)
         {
            return columns[i];
         }
      }
      return null;
   }
}