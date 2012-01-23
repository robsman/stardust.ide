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

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class DataPathPropertyPage extends AbstractModelElementPropertyPage
{
   private LabeledText idText;
   private LabeledText nameText;

   private ComboViewer directionCombo;

   private Button noDescriptorButton;
   private Button simpleDescriptorButton;
   private Button keyDescriptorButton;

   private ComboViewer dataText;

   private LabelWithStatus dataLabel;

   private LabelWithStatus dataPathLabel;

   private AccessPathBrowserComposite dataPathBrowser;

   private Button[] buttons;
   private Button autoIdButton;
   private boolean isEditable = true;

   
   private SelectionListener autoIdListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {
      }

      public void widgetSelected(SelectionEvent e)
      {
         boolean selection = ((Button) e.widget).getSelection();
         if(selection)
         {
            idText.getText().setEditable(false);
            String computedId = ModelUtils.computeId(nameText.getText().getText());
            idText.getText().setText(computedId);            
         }
         else
         {
            idText.getText().setEditable(true);            
         }         
      }
   };         
   
   protected void performDefaults()
   {
      super.performDefaults();
      ISelection sel = directionCombo.getSelection();
      if (sel.isEmpty())
      {
         directionCombo.setSelection(new StructuredSelection(DirectionType.IN_LITERAL));
      }
   }   
   
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         Text text = (Text) e.widget;
         String name = text.getText();
         if (autoIdButton.getSelection())
         {
            idText.getText().setText(ModelUtils.computeId(name));
         }
      }
   };

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager binding = getWidgetBindingManager();

      final DataPathType dataPath = (DataPathType) element;
      final ModelType model = ModelUtils.findContainingModel(element);

      nameText.getText().removeModifyListener(listener);

      binding.bind(idText, dataPath, CarnotWorkflowModelPackage.eINSTANCE
            .getIIdentifiableElement_Id());

      binding.bind(nameText, dataPath, CarnotWorkflowModelPackage.eINSTANCE
            .getIIdentifiableElement_Name());

      binding.getModelBindingManager().bind(dataPath,
            CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_Direction(),
            directionCombo);

      /*binding.getModelBindingManager().bind(dataPath,
            CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_Descriptor(),
            descriptorButton);

      binding.getModelBindingManager().bind(dataPath,
            CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_Key(),
            keyButton);*/

      updateDescriptorState();

      binding.getValidationBindingManager().bind(dataPath,
            CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_Data(), dataLabel);

      binding.getValidationBindingManager().bind(dataPath,
            CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath(),
            dataPathLabel);

      binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(model, model.getData()),
            new Data2DataPathWidgetAdapter2(dataText, dataPathBrowser, dataPath
                  .getDirection()));
      dataPathBrowser
            .setDirectionProvider(new AccessPathBrowserComposite.IDirectionProvider()
            {
               public DirectionType getDirection()
               {
                  return ModelUtils.getDualDirection(dataPath.getDirection());
               }
            });

      binding.getModelBindingManager().bind(dataPath,
            CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_Data(), dataText);

      binding.getModelBindingManager().bind(dataPath,
            CarnotWorkflowModelPackage.eINSTANCE.getDataPathType_DataPath(),
            dataPathBrowser.getMethodText());
      nameText.getText().addModifyListener(listener);
      
      isEditable = !DmsConstants.PATH_ID_ATTACHMENTS.equals(dataPath.getId());
      
      setEditable(isEditable);
   }

   public boolean isEditable()
   {
      return isEditable;
   }
   
   private void setEditable(boolean enabled_)
   {
      boolean enabled = enabled_ && enablePage;
      
      idText.getText().setEnabled(enabled);
      nameText.getText().setEnabled(enabled);
      directionCombo.getCombo().setEnabled(enabled);

      updateDescriptorState();
      
      dataText.getCombo().setEnabled(enabled);
      if(StringUtils.isEmpty(dataText.getCombo().getText()))
      {
         dataPathBrowser.setEnabled(false);
      }
      else
      {
         dataPathBrowser.setEnabled(enabled);
         DataType data = ((DataPathType) getModelElement()).getData();
         IAccessPathEditor editor = AccessPointUtil
               .getSPIAccessPathEditor(data.getType());
         dataPathBrowser.getBrowseButton().setEnabled(
               editor != null && editor.supportsBrowsing() && enabled);
      }
      autoIdButton.setEnabled(enabled);
      if (buttons != null && buttons.length >= IButtonManager.DELETE_BUTTON)
      {
         buttons[IButtonManager.DELETE_BUTTON].setEnabled(enabled);
      }
   }

   private void updateDescriptorState()
   {
      StructuredSelection selection = (StructuredSelection) directionCombo.getSelection();

      boolean isInPath = DirectionType.IN_LITERAL.equals(selection.getFirstElement());

      DataPathType dataPath = (DataPathType) getModelElement();
      if (!isInPath && dataPath.isDescriptor())
      {
         dataPath.setDescriptor(false);
         if (dataPath.isKey())
         {
            dataPath.unsetKey();
            keyDescriptorButton.setSelection(false);
         }
         else
         {
            simpleDescriptorButton.setSelection(false);
         }
      }
      
      if (dataPath.isKey())
      {
         keyDescriptorButton.setSelection(true);
         simpleDescriptorButton.setSelection(false);
         noDescriptorButton.setSelection(false);
      }
      else if (dataPath.isDescriptor())
      {
         keyDescriptorButton.setSelection(false);
         simpleDescriptorButton.setSelection(true);
         noDescriptorButton.setSelection(false);
      }
      else
      {
         keyDescriptorButton.setSelection(false);
         simpleDescriptorButton.setSelection(false);
         noDescriptorButton.setSelection(true);
      }

      boolean enabled = !selection.isEmpty() && isInPath && enablePage;
      noDescriptorButton.setEnabled(enabled);
      simpleDescriptorButton.setEnabled(enabled);
      keyDescriptorButton.setEnabled(enabled);
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         if (manager != null)
         {
            manager.updateButtons(getModelElement(), buttons);
         }
      }
      super.setVisible(visible);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      if (manager != null)
      {
         buttons = manager.createButtons(parent);
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      GenericUtils.setAutoIdValue(getModelElement(), autoIdButton.getSelection());            
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      nameText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_Name);
      idText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);

      autoIdButton = FormBuilder.createCheckBox(composite,
            Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue(getModelElement());
      autoIdButton.setSelection(autoIdButtonValue);
      if(autoIdButtonValue)
      {
         idText.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);      

      FormBuilder.createLabel(composite, Diagram_Messages.LB_Direction);
      directionCombo = new ComboViewer(FormBuilder.createCombo(composite));
      directionCombo.add(DirectionType.IN_LITERAL);
      directionCombo.add(DirectionType.OUT_LITERAL);
      directionCombo.getCombo().addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateDescriptorState();
         }
      });

      FormBuilder.createLabel(composite, ""/*"Descriptor:"*/); //$NON-NLS-1$
      Composite group = FormBuilder.createComposite(composite, 3);
      group.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData());
      noDescriptorButton = FormBuilder.createRadioButton(group, Diagram_Messages.LB_NoDescriptor);
      simpleDescriptorButton = FormBuilder.createRadioButton(group, Diagram_Messages.LB_Descriptor);
      keyDescriptorButton = FormBuilder.createRadioButton(group, Diagram_Messages.LB_KeyDescriptor);
      
      SelectionListener l = new SelectionAdapter()
      {
         @Override
         public void widgetDefaultSelected(SelectionEvent e)
         {
            updateKeyButton();
         }

         @Override
         public void widgetSelected(SelectionEvent e)
         {
            updateKeyButton();
         }

         private void updateKeyButton()
         {
            if (keyDescriptorButton.getSelection())
            {
               ((DataPathType) getModelElement()).setDescriptor(true);
               ((DataPathType) getModelElement()).setKey(true);
            }
            else if (simpleDescriptorButton.getSelection())
            {
               ((DataPathType) getModelElement()).setDescriptor(true);
               ((DataPathType) getModelElement()).unsetKey();
            }
            else
            {
               ((DataPathType) getModelElement()).setDescriptor(false);
               ((DataPathType) getModelElement()).unsetKey();
            }
         }
      };
      noDescriptorButton.addSelectionListener(l);
      simpleDescriptorButton.addSelectionListener(l);
      keyDescriptorButton.addSelectionListener(l);

      dataLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.LB_Data);
      dataText = new ComboViewer(FormBuilder.createCombo(composite));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));

      dataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), composite,
            Diagram_Messages.LB_DataPath);

      return composite;
   }
}