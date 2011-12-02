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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.modeling.core.Verifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;


public class AnnotationCellEditor implements MouseMoveListener
{
   private TreeViewer viewer;
   
   private TreeEditor editor;

   private int column;

   private IAnnotation annotation;

   public AnnotationCellEditor(TreeViewer viewer, int column, IAnnotation annotation)
   {
      this.viewer = viewer;
      this.column = column;
      this.annotation = annotation;
      editor = new TreeEditor(viewer.getTree());
      editor.grabHorizontal = true;
      editor.grabVertical = true;
      viewer.getTree().addMouseMoveListener(this);
   }

   public void dispose()
   {
      stopEditing();
      viewer.getTree().removeMouseMoveListener(this);
      if (editor != null)
      {
         editor.dispose();
      }
   }

   public void mouseMove(MouseEvent e)
   {
      Tree tree = getTree();
      TreeItem item = tree.getItem(new Point(e.x, e.y));
      if (item != null && item.getData() instanceof ConfigurationItem)
      {
         ConfigurationItem config = (ConfigurationItem) item.getData();
         IStatus status = config.getValidationStatus();
         tree.setToolTipText(status.isOK() ? null : status.getMessage());
      }
      else
      {
         tree.setToolTipText(null);
      }
   }

   void update(final TreeItem item)
   {
      stopEditing();
      if (item == null)
      {
         return;
      }

      Object data = item.getData();
      final IAnnotation annotation = this.annotation == null ? (IAnnotation) data : this.annotation;
      if (this.annotation != null)
      {
         if (data instanceof XSDElementDeclaration)
         {
            XSDElementDeclaration decl = (XSDElementDeclaration) data;
            if (decl.getName().equalsIgnoreCase("<new>")) { //$NON-NLS-1$
            	return;
            }
            if (decl.getType() instanceof XSDComplexTypeDefinition)
            {
               return;
            }
         }
         else
         {
            return;
         }
      }
      Control control = null;
      if (DefaultAnnotationModifier.canModifyAnnotation(annotation))
      {
         IAnnotationEditor editor = null;
         if (annotation instanceof ConfigurationItem)
         {
            editor = ((ConfigurationItem) annotation).getEditor();
         }
         if (editor != null)
         {
            control = editor.createControl(this, annotation, item.getBounds());
         }
         else if (DefaultAnnotationModifier.isSelectionBased(annotation))
         {
            final CCombo combo = new CCombo(getTree(), SWT.READ_ONLY);
            ComboViewer viewer = new ComboViewer(combo);
            viewer.setContentProvider(new ArrayContentProvider());
            viewer.setInput(DefaultAnnotationModifier.getAnnotationAllowedValues(annotation));
            Object selection = DefaultAnnotationModifier.getAnnotationValue(annotation);
            viewer.setSelection(selection == null ? StructuredSelection.EMPTY : new StructuredSelection(selection), true);
            viewer.addSelectionChangedListener(new ISelectionChangedListener()
            {
               public void selectionChanged(SelectionChangedEvent event)
               {
                  ISelection selection = event.getSelection();
                  DefaultAnnotationModifier.setAnnotationValue(annotation, selection instanceof IStructuredSelection
                        && !((IStructuredSelection) selection).isEmpty()
                        ? ((IStructuredSelection) selection).getFirstElement() : null);
                  updateViewer(annotation);
               }
            });
            combo.setBounds(item.getBounds());
            control = combo;
         }
         else
         {
            final Text text = new Text(getTree(), SWT.NONE);
            Verifier verifier = DefaultAnnotationModifier.getVerifierFor(annotation);
            if (verifier != null)
            {
               text.addVerifyListener(verifier);
            }
            text.setText((String) DefaultAnnotationModifier.getAnnotationValue(annotation));
            text.addModifyListener(new ModifyListener()
            {
               public void modifyText(ModifyEvent e)
               {
                  DefaultAnnotationModifier.setAnnotationValue(annotation, text.getText());
                  updateViewer(annotation);
               }
            });
            text.selectAll();
            text.setBounds(item.getBounds());
            control = text;
         }
      }
      if (control != null)
      {
         control.setFocus();
         editor.setEditor(control, item, column);
      }
   }

   public Tree getTree()
   {
      return viewer.getTree();
   }

   public void updateViewer(IAnnotation annotation)
   {
      while (annotation != null)
      {
         viewer.update(annotation, (String[]) viewer.getColumnProperties());
         annotation = annotation.getParent();
      }
   }

   void stopEditing()
   {
      Control oldEditor = editor.getEditor();
      if (oldEditor != null && !oldEditor.isDisposed())
      {
         oldEditor.dispose();
      }
   }
}
