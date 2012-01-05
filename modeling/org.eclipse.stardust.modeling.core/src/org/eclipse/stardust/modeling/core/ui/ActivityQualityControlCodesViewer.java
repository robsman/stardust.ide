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
package org.eclipse.stardust.modeling.core.ui;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.Code;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotBooleanEditor;
import org.eclipse.stardust.modeling.core.editors.ui.DefaultTableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;


public class ActivityQualityControlCodesViewer
{
   private static final String[] registryColumns = {
      Diagram_Messages.QUALITY_CONTROL_CODE, 
      Diagram_Messages.QUALITY_CONTROL_DESCRIPTION,
      Diagram_Messages.QUALITY_CONTROL_CODE_AVAILABLE};
   
   private TreeViewer viewer;
   private CarnotBooleanEditor editInStructure;
   private Object input;

   private EList<Code> validQualityCodes;
   
   public Control createControl(Composite composite)
   {
      Tree tree = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      
      viewer = new TreeViewer(tree);
      TableUtil.createColumns(tree, registryColumns);
      TableUtil.setInitialColumnSizes(tree, new int[] {30, 60, 10});

      TableLabelProvider labelProvider = new DefaultTableLabelProvider(null)
      {
         public String getText(int index, Object element)
         {
            switch (index)
            {
               case 0: return ((Code) element).getCode();
               case 1: return ((Code) element).getValue();
            }
            
            return ""; //$NON-NLS-1$
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider, registryColumns);
      viewer.setContentProvider(new ITreeContentProvider()
      {         
         public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
         {
         }
         
         public void dispose()
         {
         }
         
         public boolean hasChildren(Object element)
         {
            return false;
         }
         
         public Object getParent(Object element)
         {
            return null;
         }
         
         public Object[] getElements(Object inputElement)
         {
            EList<Code> list = (EList<Code>) inputElement;
            Object[] data = list.toArray(new Object[list.size()]);
            
            return data;
         }
         
         public Object[] getChildren(Object parentElement)
         {
            return null;
         }
      });
      
      editInStructure = new CarnotBooleanEditor(2)
      {
         public boolean canEdit(Object element)
         {
            return true;
         }

         public Object getValue(Object element)
         {
            if(validQualityCodes.contains(element))
            {
               return Boolean.TRUE;               
            }
            return Boolean.FALSE;
         }

         public void setValue(Object element, Object value)
         {
            if(value.equals(Boolean.TRUE))
            {
               validQualityCodes.add((Code) element);
            }
            else
            {
               validQualityCodes.remove((Code) element);               
            }
         }
      };
      editInStructure.setTree(tree);
      
      return tree;
   }
   
   public void setInput(Object input, ActivityType activity)
   {
      validQualityCodes = activity.getValidQualityCodes();
      
      
      this.input = input;
      viewer.setInput(input);
      editInStructure.setTree(viewer.getTree());
      
      editInStructure.refresh();
      viewer.refresh();
      viewer.expandAll();
   }
}