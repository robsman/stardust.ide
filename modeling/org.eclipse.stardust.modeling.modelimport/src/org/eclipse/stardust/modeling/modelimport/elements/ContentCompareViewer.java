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
package org.eclipse.stardust.modeling.modelimport.elements;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.contentmergeviewer.ContentMergeViewer;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class ContentCompareViewer extends ContentMergeViewer
{
   private static LabelProvider lp = new LabelProvider()
   {
      public Image getImage(Object element)
      {
         return ((ITypedElement) element).getImage();
      }

      public String getText(Object element)
      {
         return ((ITypedElement) element).getName();
      }
   };
   
   private static ITreeContentProvider cp = new ITreeContentProvider()
   {
      public Object[] getChildren(Object parentElement)
      {
         return ((IStructureComparator) parentElement).getChildren();
      }

      public Object getParent(Object element)
      {
         if (element instanceof AttributeComparator)
         {
            return ((AttributeComparator) element).getParent();
         }
         if (element instanceof StructureComparator)
         {
            return ((StructureComparator) element).getParent();
         }
         return null;
      }

      public boolean hasChildren(Object element)
      {
         return ((IStructureComparator) element).getChildren().length > 0;
      }

      public Object[] getElements(Object inputElement)
      {
         return ((IStructureComparator) inputElement).getChildren();
      }

      public void dispose()
      {
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
      }
   };

   private TreeViewer left;
   private TreeViewer right;
   private TreeViewer ancestor;

   public ContentCompareViewer(Composite parent, CompareConfiguration config)
   {
      super(SWT.NONE, null, config);
      buildControl(parent);
   }

   protected void copy(boolean leftToRight)
   {
      // empty, since no merge is performed here
   }

   protected void createControls(Composite composite)
   {
      // ancestor should never be used
      ancestor = new TreeViewer(composite, SWT.NONE);
      
      left = new TreeViewer(composite, SWT.None);
      left.setContentProvider(cp);
      left.setLabelProvider(lp);
      left.setSorter(new ModelElementsSorter());

      right = new TreeViewer(composite, SWT.None);
      right.setContentProvider(cp);
      right.setLabelProvider(lp);
      right.setSorter(new ModelElementsSorter());
   }

   protected byte[] getContents(boolean left)
   {
      return null;
   }

   protected void handleResizeAncestor(int x, int y, int width, int height)
   {
      ancestor.getControl().setVisible(false);
   }

   protected void handleResizeLeftRight(int x, int y, int leftWidth, int centerWidth,
         int rightWidth, int height)
   {
      leftWidth -= centerWidth / 2;
      left.getControl().setBounds(x, y, leftWidth - centerWidth, height);
      right.getControl().setBounds(x + leftWidth + centerWidth, y, rightWidth, height);
   }

   protected void updateContent(Object ancestor, Object leftInput, Object rightInput)
   {
      setInput(left, leftInput);
      setInput(right, rightInput);
      setTitle(lp.getText(leftInput == null ? rightInput : leftInput));
   }

   private void setTitle(String title)
   {
      getControl().setData(CompareUI.COMPARE_VIEWER_TITLE, title);
   }

   private void setInput(TreeViewer viewer, Object input)
   {
      viewer.setInput(input);
      if (input != null && input instanceof RootComparator)
      {
         viewer.expandToLevel(2);
      }
   }
}
