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
package org.eclipse.stardust.modeling.core.compare;

import java.util.ResourceBundle;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.contentmergeviewer.ContentMergeViewer;
import org.eclipse.compare.internal.ICompareContextIds;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.compare.structuremergeviewer.IDiffElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

public class ModelElementMergeViewer extends ContentMergeViewer
      implements ISelectionChangedListener
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.core.compare.ModelElementMergeViewerResources"; //$NON-NLS-1$

//   private ComparableModelElementNode fAncestorNode;

//   private ComparableModelElementNode fLeftNode;

//   private ComparableModelElementNode fRightNode;

   private TreeViewer fLeft;

   private TreeViewer fRight;

   private TreeViewer fAncestor;

   private ICompareInput cInput;

//   private ICompareInput aInput;

//   private DiffTreeViewer structureViewer;

//   private CompareConfiguration configuration;

   public ModelElementMergeViewer(Composite parent, int styles,
         CompareConfiguration configuration, ICompareInput cInput, Viewer structureViewer)
   {

      super(styles, ResourceBundle.getBundle(BUNDLE_NAME), configuration);

//      this.configuration = configuration;

      PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
            ICompareContextIds.IMAGE_COMPARE_VIEW);

      buildControl(parent);

      String title = Utilities.getString(getResourceBundle(), "subtitle2"); //$NON-NLS-1$
      getControl().setData(CompareUI.COMPARE_VIEWER_TITLE, title);

      this.cInput = cInput;
//      this.structureViewer = (DiffTreeViewer) structureViewer;
   }

   protected void updateContent(Object ancestor, Object left, Object right)
   {
//      fAncestorNode = (ComparableModelElementNode) ancestor;
      setIntrinsicViewerInput(fAncestor, ancestor);

//      fLeftNode = (ComparableModelElementNode) left;
      setIntrinsicViewerInput(fLeft, left);

//      fRightNode = (ComparableModelElementNode) right;
      setIntrinsicViewerInput(fRight, right);
   }

   /*
    * We can't modify the contents of either side we just return null.
    */
   protected byte[] getContents(boolean left)
   {
      return null;
   }

   public void createControls(Composite composite)
   {
      fAncestor = new TreeViewer(composite, SWT.NONE);
      fAncestor.setContentProvider(new DiffViewerContentProvider());
      fAncestor.setLabelProvider(new DiffViewerLabelProvider());
      fAncestor.setSorter(new ModelViewerSorter());

      fLeft = new TreeViewer(composite, SWT.NONE);
      fLeft.setContentProvider(new DiffViewerContentProvider());
      fLeft.setLabelProvider(new DiffViewerLabelProvider());
      fLeft.setSorter(new ModelViewerSorter());

      fRight = new TreeViewer(composite, SWT.NONE);
      fRight.setContentProvider(new DiffViewerContentProvider());
      fRight.setLabelProvider(new DiffViewerLabelProvider());
      fRight.setSorter(new ModelViewerSorter());

   }

   private void setIntrinsicViewerInput(TreeViewer viewer, Object input)
   {
      if (viewer != null)
      {
         viewer.setInput(input);
      }
   }

   protected void handleResizeAncestor(int x, int y, int width, int height)
   {
      // not implemented yet
   }

   protected void handleResizeLeftRight(int x, int y, int leftWidth, int centerWidth,
         int rightWidth, int height)
   {
      fLeft.getControl().setBounds(x, y, leftWidth, height);
      fRight.getControl().setBounds(x + leftWidth, y, rightWidth, height);
   }

   protected void copy(boolean leftToRight)
   {
      // not implemented
   }

   // --------------------------------------------------------------------------------

   class DiffViewerLabelProvider extends LabelProvider
   {

      public String getText(Object element)
      {
         if (element instanceof ComparableModelElementNode)
            return ((ComparableModelElementNode) element).getName();

         return "DEFAULT"; //$NON-NLS-1$
      }

      public Image getImage(Object element)
      {
         Image image = null;

         if (element instanceof ComparableModelElementNode)
         {
            ComparableModelElementNode input = (ComparableModelElementNode) element;

            Object child = null;

            if (cInput != null && input != null)
            {
               child = ((DiffNode) cInput).findChild(input.getName());
            }

            if (child != null)
            {
               IDiffElement diffElement = (IDiffElement) child;

               int kind = diffElement.getKind();

               switch (kind & Differencer.DIRECTION_MASK)
               {
               case Differencer.LEFT:
                  kind = (kind & ~Differencer.LEFT) | Differencer.RIGHT;
                  break;
               case Differencer.RIGHT:
                  kind = (kind & ~Differencer.RIGHT) | Differencer.LEFT;
                  break;
               }

               image = getCompareConfiguration().getImage(input.getImage(), kind);
            }
            else
            {
               image = input.getImage();
            }

            return image;
         }
         return null;
      }
   }

   class DiffViewerContentProvider implements ITreeContentProvider
   {
      private Object leftRoot = null;

      private Object rightRoot = null;

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
         if (oldInput != newInput)
         {
            leftRoot = null;
            rightRoot = null;
         }
      }

      public boolean isDeleted(Object element)
      {
         return false;
      }

      public void dispose()
      {}

      public Object getParent(Object element)
      {
         ComparableModelElementNode parentNode = ((ComparableModelElementNode) element).getParent();
         return parentNode;
      }

      public final boolean hasChildren(Object element)
      {
         return ((ComparableModelElementNode) element).hasChildren();
      }

      public final Object[] getChildren(Object element)
      {
         // if (leftRoot == null && (element == fLeftNode))
         // {
         // leftRoot = fLeftNode;
         // return new Object[] {element};
         // }
         //
         // if (rightRoot == null && (element == fRightNode))
         // {
         // rightRoot = fRightNode;
         // return new Object[] {element};
         // }
         //
         Object[] children = ((ComparableModelElementNode) element).getChildren();
         return (children != null && children.length > 0)
               ? children
               : new Object[] {element};

      }

      public Object[] getElements(Object element)
      {
         return getChildren(element);
      }
   }

   public void selectionChanged(SelectionChangedEvent event)
   {
      ISelection selection = event.getSelection();
      if (selection != null)
         if (selection instanceof IStructuredSelection)
         {
            IStructuredSelection ss = (IStructuredSelection) selection;
            if (ss.size() == 1)
               this.setInput(ss.getFirstElement());
         }
   }

}
