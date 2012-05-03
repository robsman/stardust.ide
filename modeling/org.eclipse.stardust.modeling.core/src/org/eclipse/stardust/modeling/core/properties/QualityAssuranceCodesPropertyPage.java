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
package org.eclipse.stardust.modeling.core.properties;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;

public class QualityAssuranceCodesPropertyPage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   public static final String CODES_ID = "_cwm_quality_control_codes_"; //$NON-NLS-1$

   private TreeViewer viewer;
   private TableLabelProvider labelProvider;
   private Button[] buttons;
   private Object selection;
   private ModelElementsOutlineSynchronizer outlineSynchronizer;
   private ModelType model;   

   public QualityAssuranceCodesPropertyPage()
   {
   }
   
   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(parent);
   }

   public void dispose()
   {
      if(outlineSynchronizer != null)
      {
         outlineSynchronizer.dispose();
      }
      super.dispose();
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
      }
      super.setVisible(visible);
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
      this.selection = selection;
      
      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i].isDisposed())
         {
            return;
         }
      }
      boolean isValid = selection instanceof Code;
      buttons[ADD_BUTTON].setEnabled(true);
      buttons[DELETE_BUTTON].setEnabled(isValid);
      buttons[UP_BUTTON].setEnabled(canMoveUp((Code) selection));
      buttons[DOWN_BUTTON].setEnabled(canMoveDown((Code) selection));
   }

      private boolean canMoveUp(Code selection)
      {
         ModelType model = getModel();                                 
         EList<Code> codes = model.getQualityControl().getCode();
         return codes.indexOf(selection) > 0;
      }

      private boolean canMoveDown(Code selection)
      {
         ModelType model = getModel();                                 
         EList<Code> codes = model.getQualityControl().getCode();
         int index = codes.indexOf(selection);
         return index >= 0 && index < codes.size() - 1;
      }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[4];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAdd(buttons);
               }
            });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_Delete, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDelete(buttons);
               }
            });
      buttons[UP_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_MoveUp, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performUp(buttons);
         }
      });

      buttons[DOWN_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_MoveDown, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performDown(buttons);
         }
      });
      
      return buttons;
   }

   public Object getSelection()
   {
      Object object = selection == null ? getSelectedItem() : selection;
      return object;
   }

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      if (selection instanceof Proxy)
      {
         Proxy proxy = (Proxy) selection;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         
         Object value = Reflect.getFieldValue(ih, "val$element"); //$NON-NLS-1$
         if (value != null)
         {
            selection = value;
         }
      }
      
      return selection;
   }

   private void performDelete(Button[] buttons)
   {            
      Code code = (Code) getSelection();
      
      if (code != null)
      {
         ModelType model = getModel();         
         model.getQualityControl().getCode().remove(code);
         updateButtons(null, buttons);
         selectPage(CODES_ID);
      }
   }

   private void performAdd(Button[] buttons)
   {
      Code code = CarnotWorkflowModelFactory.eINSTANCE.createCode();
      
      IdFactory factory = new IdFactory("code", "code", //$NON-NLS-1$ //$NON-NLS-2$
            CarnotWorkflowModelPackage.eINSTANCE.getCode(),
            CarnotWorkflowModelPackage.eINSTANCE.getCode_Code(), 
            CarnotWorkflowModelPackage.eINSTANCE.getCode_Code());
      factory.computeNames(getModel().getQualityControl().getCode());
      
      ModelType model = getModel();
      QualityControlType qualityControl = model.getQualityControl();
      if(qualityControl == null)
      {
         model.setQualityControl(CarnotWorkflowModelFactory.eINSTANCE.createQualityControlType());
      }
      qualityControl = model.getQualityControl();
      EList<Code> codes = qualityControl.getCode();
      code.setCode(factory.getId());
      code.setName(factory.getName());
      
      codes.add(code);
      
      if (preselect)
      {
         selectPageForObject(code);
      }
   }
   
   private void performUp(Button[] buttons)
   {
      Code code = (Code) getSelection();
      ModelType model = getModel();
                              
      EList<Code> codes = model.getQualityControl().getCode();
      int index = codes.indexOf(code);
      if (index > 0)
      {
         codes.move(index - 1, index);
         updateButtons(code, buttons);
      }
   }

   private void performDown(Button[] buttons)
   {
      Code code = (Code) getSelection();
      ModelType model = getModel();
                              
      EList<Code> codes = model.getQualityControl().getCode();
      int index = codes.indexOf(code);
      if (index >= 0 && index < codes.size() - 1)
      {
         codes.move(index + 1, index);
         updateButtons(code, buttons);
      }
   }

   private ModelType getModel()
   {
      if (model == null)
      {
         Object element = getElement();
         if (element instanceof EditPart)
         {
            Object modelElement = ((EditPart) element).getModel();
            if (modelElement instanceof ModelType)
            {
               model = (ModelType) modelElement;
            }
         }
      }
      return model;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      ModelType model = getModel();
      QualityControlType qualityControl = model.getQualityControl();
      if(qualityControl == null)
      {
         model.setQualityControl(CarnotWorkflowModelFactory.eINSTANCE.createQualityControlType());
      }
      qualityControl = model.getQualityControl();
      
      viewer.setInput(qualityControl);
      viewer.expandAll();
      outlineSynchronizer.init(qualityControl);
      updateButtons(null, buttons);
      expandTree();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      Tree tree = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(tree);

      tree.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateButtons(getSelectedItem(), buttons);
         }
      });

      tree.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if (selection instanceof Code)
            {
               selectPageForObject(selection);
            }
         }
      });

      viewer = new TreeViewer(tree);
      TableUtil.createColumns(tree, new String[] {
            Diagram_Messages.QUALITY_CONTROL_CODE, Diagram_Messages.LB_Name});
      
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public Image getImage(Object element)
         {
            return null; 
         }

         public String getText(String name, Object element)
         {
            String value = null;
            if(element instanceof Code)
            {
               Code code = (Code) element;
               if (name.equals("name")) //$NON-NLS-1$
               {
                  value = code.getName();
                  if(StringUtils.isEmpty(value))
                  {
                     value = code.getCode();
                  }                  
               }
               else if (name.equals("code")) //$NON-NLS-1$
               {
                  value = code.getCode();
               }
            }
            return super.getText(name, element);
         }
      };
      
      TableUtil.setLabelProvider(viewer, labelProvider, new String[] {"code", "name"}); //$NON-NLS-1$ //$NON-NLS-2$
      viewer.setContentProvider(new QualityAssuranceCodesTreeContentProvider());

      OutlineProvider op = new DefaultOutlineProvider(this,
            CarnotWorkflowModelPackage.eINSTANCE.getQualityControlType_Code(),
            CarnotWorkflowModelPackage.eINSTANCE.getCode_Code(),
            CarnotWorkflowModelPackage.eINSTANCE.getCode_Name(),
            CODES_ID, QualityAssuranceCodePropertyPage.class.getName())
      {
         public ConfigurationElement createPageConfiguration(IModelElement element)
         {
            Code code = (Code) element;
            
            return ConfigurationElement.createPageConfiguration(code.getCode(),
                  code.getName(), 
                  getEditor().getIconFactory().getIconFor(code),
                  QualityAssuranceCodePropertyPage.class.getName());
         }

         @Override
         public IAdaptable getAdaptable()
         {
            IAdaptable original =  page.getElement();
            return new ModelElementAdaptable(
                  new Class[] {IButtonManager.class, IModelElementNodeSymbol.class},
                  new Object[] {page},
                  original);
         }

         public void addNodeTo(String parentNodeId, final CarnotPreferenceNode node)
         {            
            page.addNodeTo(parentNodeId, node, new EObjectLabelProvider(getEditor())
            {
               public String getText(String name, Object element)
               {
                  Code code = (Code) element;
                  return code.getName(); 
               }
               
               public Image getImage(Object element)
               {
                  return DiagramPlugin.getImage("/icons/full/obj16/context.gif"); //$NON-NLS-1$
               }
            });
         }
      };

      outlineSynchronizer = new ModelElementsOutlineSynchronizer(op);
      addModelElementsOutlineSynchronizer(outlineSynchronizer);
      
      return composite;
   }

   public Point computeSize()
   {
      TableUtil.setInitialColumnSizesDirect(viewer.getTree(), new int[] {35, 65});
      return super.computeSize();
   }
}