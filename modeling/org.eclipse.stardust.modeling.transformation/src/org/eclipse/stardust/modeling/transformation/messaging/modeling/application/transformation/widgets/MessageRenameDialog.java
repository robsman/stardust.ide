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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationModelingPlugin;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.PrimitivesFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.StructuredTypesFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


public class MessageRenameDialog extends Dialog implements ModifyListener
{
   private WorkflowModelEditor wfme;   
   private MessageTransformationController controller;
   
   private List allMessageTypes = new ArrayList();
   private List typeFilters = new ArrayList();
   private AccessPointType messageType;
      
   private static Text messageNameText;
   private Combo messageCombo;   
   private ComboViewer messageComboViewer;

   private Combo dataTypeCombo;
   private ComboViewer dataTypeComboViewer;
   
   private Label errorLabel;
   private boolean isError = false;

   private Composite structPrimComposite;
   protected ViewerFilter selectedFilter;
   
   private boolean isSource;
   private static String messageName;
   
   public MessageRenameDialog(Shell parentShell,
         MessageTransformationController controller, boolean isSource)
   {
      super(parentShell);
      this.controller = controller;
      this.isSource = isSource;
      allMessageTypes.addAll(controller.getSourceMessageTypes());
      allMessageTypes.addAll(controller.getTargetMessageTypes());
      typeFilters.add(new StructuredTypesFilter());
      typeFilters.add(new PrimitivesFilter());
   }

   protected Control createDialogArea(Composite parent)
   {
      if ((null != PlatformUI.getWorkbench())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow())
            && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage()))
      {
         IEditorPart currentEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
               .getActivePage().getActiveEditor();
         if (currentEditor instanceof WorkflowModelEditor)
         {
            wfme = (WorkflowModelEditor) currentEditor;
         }
      }

      Composite comp = (Composite) super.createDialogArea(parent);
      GridLayout layout = (GridLayout) comp.getLayout();
      layout.numColumns = 3;

      Label tagNameLabel = new Label(comp, SWT.LEFT);
      tagNameLabel.setText(MessageFormat.format(Modeling_Messages.TXT_NAME,
            new Object[] {controller.getNameString()}));
      
      messageNameText = new Text(comp, SWT.SINGLE | SWT.BORDER);
      messageNameText.addModifyListener(this);
      GridData data = new GridData(GridData.FILL_HORIZONTAL);
      messageNameText.setLayoutData(data);
      errorLabel = new Label(comp, SWT.NONE);
      errorLabel.setImage(MessageTransformationModelingPlugin.getDefault()
            .getImageDescriptor("icons/error.gif").createImage()); //$NON-NLS-1$
      errorLabel.setVisible(false);

      GridData comboData = new GridData();
      comboData.grabExcessHorizontalSpace = true;
      comboData.horizontalAlignment = SWT.FILL;
      comboData.horizontalSpan = 2;

      Label typeLabel = new Label(comp, SWT.LEFT);
      typeLabel.setText(Modeling_Messages.TXT_DATETYPE);

      dataTypeCombo = new Combo(comp, SWT.NO);
      dataTypeCombo.setLayoutData(comboData);
      dataTypeComboViewer = new ComboViewer(dataTypeCombo);
      dataTypeComboViewer.setContentProvider(new ArrayContentProvider());
      
      structPrimComposite = new Composite(comp, SWT.NONE);      
      GridLayout structPrimLayout = new GridLayout();
      structPrimLayout.numColumns = 2;
      GridData structPrimData = new GridData();
      structPrimData.grabExcessHorizontalSpace = true;
      structPrimData.grabExcessVerticalSpace = true;
      structPrimData.verticalAlignment = SWT.FILL;
      structPrimData.horizontalAlignment = SWT.FILL;
      structPrimData.horizontalSpan = 2;
      structPrimComposite.setLayoutData(structPrimData);
      structPrimComposite.setLayout(structPrimLayout);

      messageCombo = new Combo(structPrimComposite, SWT.NO);
      messageCombo.setLayoutData(comboData);
      messageComboViewer = new ComboViewer(messageCombo);
      messageComboViewer.setContentProvider(new ArrayContentProvider());
      messageComboViewer.setLabelProvider(new EObjectLabelProvider(wfme));
      
      parent.getShell().setMinimumSize(300, 150);
      parent.getShell().setText(Modeling_Messages.TXT_MODIFY + controller.getNameString());
      return comp;
   }

   private void initCombos()
   {
      messageComboViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (!selection.isEmpty())
            {
               messageType = (AccessPointType) selection.getFirstElement();
               String text = messageType.getId();
               int n = 1;
               while (isAccessPointIdDefined(text + n))
               {
                  n++;
               }
               text = text + n;
               messageNameText.setText(text);
               messageNameText.setSelection(0, messageNameText.getText().length());
            }
            buttonEnablement();
         }
      });

      dataTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            selectedFilter = (ViewerFilter) selection.getFirstElement();
            messageComboViewer.setFilters(new ViewerFilter[] {selectedFilter});
         }
      });

      messageComboViewer.setInput(controller.getAvailableMessageTypes());
      messageComboViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            if (event.getSelection() instanceof IStructuredSelection)
            {
               IStructuredSelection selection = (IStructuredSelection) event
                     .getSelection();
               messageType = (AccessPointType) selection.getFirstElement();
            }
         }
      });      
      
      if(isSource)
      {
         messageType = controller.getSelectedSourceField();         
      }
      else
      {
         messageType = controller.getSelectedTargetField();                  
      }         

      int typeSelected = 0;
      if(!(messageType instanceof StructAccessPointType))
      {
         typeSelected = 1;
      }
      messageNameText.setText(messageType.getId());
      
      // Filters
      dataTypeComboViewer.setInput(typeFilters);
      List filterSelection = new ArrayList();
      filterSelection.add(typeFilters.get(typeSelected));
      dataTypeComboViewer.setSelection(new StructuredSelection(filterSelection));

      List<AccessPointType> availableMessageTypes = controller.getAvailableMessageTypes();
      List dataSelection = new ArrayList();      
      for(AccessPointType currentType : availableMessageTypes)
      {
         if(typeSelected == 1)
         {
            AttributeType attribute = AttributeUtil.getAttribute(messageType, CarnotConstants.TYPE_ATT);
            
            AttributeType currentAttribute = AttributeUtil.getAttribute(currentType, CarnotConstants.TYPE_ATT);
            String currentAttributeType = attribute.getType();
            if(currentAttributeType.equals("ag.carnot.workflow.spi.providers.data.java.Type"))
            {
               if(currentAttribute.getValue().equals(attribute.getValue()))
               {
                  dataSelection.add(currentType);      
                  break;                                 
               }
            }
         }
         else
         {
            String messageTypeType = AttributeUtil.getAttribute(messageType, "carnot:engine:dataType").getValue();
            DataTypeType type = currentType.getType();            
            if(type.equals(messageType.getType()) && messageTypeType.equals(currentType.getId()))
            {
               dataSelection.add(currentType);;      
               break;
            }            
         }
      }
      messageComboViewer.setSelection(new StructuredSelection(dataSelection));
      
      messageNameText.setSelection(0, messageNameText.getText().length());
   }

   protected void buttonEnablement()
   {
      String text = messageNameText.getText();
      this.errorLabel.setVisible(isError);
      if (getButton(IDialogConstants.OK_ID) != null)
      {
            getButton(IDialogConstants.OK_ID)
                  .setEnabled(
                        !isError
                              && !text.equalsIgnoreCase("") && text.indexOf(" ") == -1 && messageCombo.getSelectionIndex() != -1); //$NON-NLS-1$ //$NON-NLS-2$
      }
   }

   protected void createButtonsForButtonBar(Composite parent)
   {
      super.createButtonsForButtonBar(parent);
      initCombos();
   }

   protected void buttonPressed(int buttonId) 
   {
      if(buttonId == IDialogConstants.OK_ID) 
      {
         messageName = messageNameText.getText();
      }
      super.buttonPressed(buttonId);              
   }   
   
   public void modifyText(ModifyEvent arg0)
   {
      String text = messageNameText.getText();
      
      if (getMessageTypeByName(text) != null)
      {
         isError = true;
         errorLabel.setToolTipText(MessageFormat.format(
               Modeling_Messages.TXT_DOES_ALREADY_EXIST, new Object[] {text}));
      }
      else
      {
         if (!StringUtils.isValidIdentifier(text))
         {
            isError = true;
            errorLabel.setToolTipText(MessageFormat.format(
                  Modeling_Messages.TXT_NOT_VALID_NAME, new Object[] {text}));
         }
         else
         {
            isError = false;
            errorLabel.setToolTipText(null);
         }
      }
      buttonEnablement();
   }

   public AccessPointType getMessageType()
   {
      return messageType;
   }

   public static String getMessageName()
   {
      return messageName;
   }

   private boolean isAccessPointIdDefined(String id)
   {
      for (Iterator i = allMessageTypes.iterator(); i.hasNext();)
      {
         AccessPointType messageType = (AccessPointType) i.next();
         if (messageType.getId().equalsIgnoreCase(id))
         {
            return true;
         }
      }
      return false;
   }

   private AccessPointType getMessageTypeByName(String name)
   {
      for (Iterator i = allMessageTypes.iterator(); i.hasNext();)
      {
         AccessPointType messageType = (AccessPointType) i.next();
         if (messageType.getId().equalsIgnoreCase(name))
         {
            return messageType;
         }
      }
      return null;
   }
}