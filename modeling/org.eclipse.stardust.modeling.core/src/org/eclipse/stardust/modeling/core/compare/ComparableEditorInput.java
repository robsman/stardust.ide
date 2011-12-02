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

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.internal.CompareDialog;
import org.eclipse.compare.internal.NullViewer;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;


public class ComparableEditorInput extends CompareEditorInput
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.core.compare.ModelElementMergeViewerResources"; //$NON-NLS-1$

   private ModelDiffViewer modelDiffViewer;

   private DiffNode input;

   private boolean threeWay = false;

   private ListenerList listenerList = new ListenerList();

   public ComparableEditorInput(CompareConfiguration configuration)
   {
      super(configuration);

      ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
      this.setTitle(bundle.getString("title")); //$NON-NLS-1$
   }

   /**
    * @see CompareEditorInput#prepareInput(org.eclipse.core.runtime.IProgressMonitor)
    */
   protected Object prepareInput(IProgressMonitor monitor)
         throws InvocationTargetException, InterruptedException
   {
      Differencer d = new Differencer();
      Object difference = d.findDifferences(threeWay, monitor, null, input.getAncestor(),
            input.getLeft(), input.getRight());
      return difference;
   }

   /**
    * Initial input of models to compare
    * @param input
    */
   public void setInput(DiffNode input)
   {
      this.input = input;

      if (this.input.getAncestor() != null)
      {
         threeWay = true;
      }

      this.initializeCompareConfiguration();
   }

   public Viewer createDiffViewer(Composite parent)
   {
      this.modelDiffViewer = new ModelDiffViewer(parent, getCompareConfiguration());
      this.modelDiffViewer
            .setStructureCreator(new ComparableModelElementStructureCreator());
      return this.modelDiffViewer;
   }

   public Viewer findContentViewer(Viewer oldViewer, ICompareInput input, Composite parent)
   {
      Viewer newViewer = null;

      boolean isNewViewer = false;

      if (oldViewer instanceof NullViewer)
      {
         newViewer = new ModelElementMergeViewer(parent, SWT.NONE,
               getCompareConfiguration(), input, modelDiffViewer);

         // structure pane should update its content on every selection change
         this.modelDiffViewer
               .addSelectionChangedListener((ISelectionChangedListener) newViewer);
      }
      else
      {
         newViewer = oldViewer;
         isNewViewer = true;
      }

      return newViewer;
   }

   void initializeCompareConfiguration()
   {
      if (this.input.getAncestor() != null)
      {
         // retrieving the model node
         Object ancestorNode = ((ComparableModelElementNode) this.input.getAncestor())
               .getChildren()[0];

         String ancestorLabel = ((ComparableModelElementNode) ancestorNode).getName();
         EObject ancestorElement = ((ComparableModelElementNode) ancestorNode)
               .getEObject();
         getCompareConfiguration().setAncestorLabel(ancestorLabel);

         if (ancestorElement != null)
         {
            getCompareConfiguration().setAncestorImage(
                  DiagramPlugin.getImage(IconFactory.getDefault().getIconFor(ancestorElement)));
         }
      }

      CopyUtil.reset();

      EObject leftElement = null;
      if (this.input.getLeft() != null)
      {
         // retrieving the model node
         Object leftNode = ((ComparableModelElementNode) this.input.getLeft())
               .getChildren()[0];

         String leftLabel = ((ComparableModelElementNode) leftNode).getName();
         leftElement = ((ComparableModelElementNode) leftNode).getEObject();
         getCompareConfiguration().setLeftLabel(leftLabel);

         if (leftElement != null)
         {
            getCompareConfiguration().setLeftImage(
                  DiagramPlugin.getImage(IconFactory.getDefault().getIconFor(leftElement)));
         }
      }

      if (this.input.getRight() != null)
      {
         // retrieving the model node
         Object rightNode = ((ComparableModelElementNode) this.input.getRight())
               .getChildren()[0];

         String rightLabel = ((ComparableModelElementNode) rightNode).getName();
         EObject rightElement = ((ComparableModelElementNode) rightNode).getEObject();
         getCompareConfiguration().setRightLabel(rightLabel);

         if (rightElement != null)
         {
            getCompareConfiguration().setRightImage(
                  DiagramPlugin.getImage(IconFactory.getDefault().getIconFor(rightElement)));
            
            if (leftElement instanceof ModelType && rightElement instanceof ModelType)
            {
               ModelElementOidRegistration.replaceDuplicateOids(
                     (ModelType) rightElement, (ModelType) leftElement);
            }
         }
      }
   }

   public void addPropertyChangeListener(IPropertyChangeListener listener)
   {
      super.addPropertyChangeListener(listener);
      if (listener instanceof CompareDialog)
      {
         listenerList.add(listener);
      }
   }
}
