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
import java.util.BitSet;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationModelingPlugin;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.PrimitivesFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.SerializableFilter;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.StructuredTypesFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


public class MessageAdditionDialog extends Dialog implements ModifyListener {
  private static final int RESET_ID = IDialogConstants.NO_TO_ALL_ID + 1;
  private Text messageNameText;
  private String tagName;
  private ComboViewer messageComboViewer;
  private MessageTransformationController controller;
  private AccessPointType messageType;
  private String messageName;
  private List messageTypes;
  private String preset;
  private List<AccessPointType> allMessageTypes = new ArrayList<AccessPointType>();
  private List<ViewerFilter> typeFilters = new ArrayList<ViewerFilter>();
 
  
private Combo messageCombo;
private Label errorLabel;
private WorkflowModelEditor wfme;
private Composite mainComposite;
private boolean isError = false;
private Combo dataTypeCombo;
private ComboViewer dataTypeComboViewer;
private Label structLabel;
private TypeSelectionComposite classBrowser;
private StackLayout stack;
private Composite structPrimComposite;
private Composite classBrowseComposite;
private Group messageComposite;
protected ViewerFilter selectedFilter;
private DirectionType directionType;


  public MessageAdditionDialog(Shell parentShell, MessageTransformationController controller, List messageTypes, String preset, DirectionType directionType) {
    super(parentShell);
    this.controller = controller;
    this.messageTypes = messageTypes;
    this.directionType = directionType;
    this.preset = preset;
	allMessageTypes.addAll(controller.getSourceMessageTypes());
	allMessageTypes.addAll(controller.getTargetMessageTypes());
	typeFilters.add(new StructuredTypesFilter());
	typeFilters.add(new PrimitivesFilter());
	if (!controller.isSimpleMode() || controller.isWithSerializable()) {
		typeFilters.add(new SerializableFilter());	
	}	
  }

  protected Control createDialogArea(Composite parent) {
     mainComposite = parent;
     if ((null != PlatformUI.getWorkbench())
           && (null != PlatformUI.getWorkbench().getActiveWorkbenchWindow())
           && (null != PlatformUI.getWorkbench()
                 .getActiveWorkbenchWindow()
                 .getActivePage()))
     {
        IEditorPart currentEditor = PlatformUI.getWorkbench()
              .getActiveWorkbenchWindow()
              .getActivePage()
              .getActiveEditor();
        if (currentEditor instanceof WorkflowModelEditor)
        {
           wfme = (WorkflowModelEditor) currentEditor;
        }
     } 
     
     
    Composite comp = (Composite) super.createDialogArea(parent);

    GridLayout layout = (GridLayout) comp.getLayout();
    layout.numColumns = 3;

    Label tagNameLabel = new Label(comp, SWT.LEFT);
    tagNameLabel.setText(MessageFormat.format(Modeling_Messages.TXT_NAME, new Object[]{controller.getNameString()}));
    if (controller.isSimpleMode()) {
    	
    } else {
        tagNameLabel.setText(Modeling_Messages.TXT_MSG_NAME);    	
    }
    messageNameText = new Text(comp, SWT.SINGLE | SWT.BORDER);
    messageNameText.addModifyListener(this);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    messageNameText.setLayoutData(data);
    errorLabel = new Label(comp, SWT.NONE);
    errorLabel.setImage(MessageTransformationModelingPlugin.getDefault().getImageDescriptor("icons/error.gif").createImage()); //$NON-NLS-1$
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
	

        
    messageComposite = new Group(comp, SWT.NONE);
    stack = new StackLayout();
    messageComposite.setLayout(stack);
    GridData messageCompData = new GridData();
    messageCompData.grabExcessHorizontalSpace = true;
    messageCompData.grabExcessVerticalSpace = true;
    messageCompData.horizontalAlignment = SWT.FILL;
    messageCompData.verticalAlignment = SWT.FILL;
    messageCompData.horizontalSpan = 2;
    messageComposite.setLayout(stack);    
    messageComposite.setLayoutData(messageCompData);
    
    structPrimComposite = new Composite(messageComposite, SWT.NONE);
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
       
    //structLabel = new Label(structPrimComposite, SWT.LEFT);
    messageCombo = new Combo(structPrimComposite, SWT.NO);
    messageCombo.setLayoutData(comboData);
    messageComboViewer = new ComboViewer(messageCombo);
    messageComboViewer.setContentProvider(new ArrayContentProvider());
    messageComboViewer.setLabelProvider(new EObjectLabelProvider(wfme));
    
    classBrowseComposite = new Composite(messageComposite, SWT.NONE);
    GridLayout classBrowseLayout = new GridLayout();
    classBrowseLayout.numColumns = 1;
    GridData classBrowseData = new GridData();
    classBrowseData.grabExcessHorizontalSpace = true;
    classBrowseData.grabExcessVerticalSpace = true;
    classBrowseData.verticalAlignment = SWT.FILL;
    classBrowseData.horizontalAlignment = SWT.FILL;
    classBrowseComposite.setLayout(classBrowseLayout);
    classBrowseComposite.setLayoutData(classBrowseData);
    classBrowser = new TypeSelectionComposite(classBrowseComposite, Modeling_Messages.PlainJavaPropertyPage_LB_Plain_Java);
    classBrowser.getText().addModifyListener(new ModifyListener(){

		public void modifyText(ModifyEvent e) {
			serializableTypeModified(e);			
		}
    	
    });
    stack.topControl = classBrowseComposite;
    
    
    parent.getShell().setMinimumSize(300, 150);
    parent.getShell().setText(Modeling_Messages.TXT_ADD + controller.getNameString());
    return comp;
  }
  
   private static final int SELECTION_CHANGED_FLAG = 0;
   private static final int MANUAL_SET_NAME_FLAG = 1;

   private void initCombos()
   {
      final BitSet flags = new BitSet(2);

      messageNameText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent event)
         {
            if (!flags.get(SELECTION_CHANGED_FLAG))
            {
               if (flags.get(MANUAL_SET_NAME_FLAG))
               {
                  if (messageNameText.getText().isEmpty())
                  {
                     flags.clear(MANUAL_SET_NAME_FLAG);
                  }
               }
               else
               {
                  flags.set(MANUAL_SET_NAME_FLAG);
               }
            }
         }
      });

      messageComboViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (!selection.isEmpty())
            {
               if (!flags.get(MANUAL_SET_NAME_FLAG))
               {
                  flags.set(SELECTION_CHANGED_FLAG);
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
                  flags.clear(SELECTION_CHANGED_FLAG);
               }
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
            if (selectedFilter instanceof SerializableFilter)
            {
               stack.topControl = classBrowseComposite;
            }
            else
            {
               stack.topControl = structPrimComposite;
            }
            messageComposite.setText(Modeling_Messages.TXT_SEL + selectedFilter.toString());
            messageComposite.layout();
         }
      });

      messageComboViewer.setInput(controller.getAvailableMessageTypes());
      messageComboViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            if (event.getSelection() instanceof IStructuredSelection)
            {
               IStructuredSelection selection = (IStructuredSelection) event.getSelection();
               messageType = (AccessPointType) selection.getFirstElement();
            }
         }
      });

      // Filters
      dataTypeComboViewer.setInput(typeFilters);
      List<ViewerFilter> filterSelection = new ArrayList<ViewerFilter>();
      filterSelection.add(typeFilters.get(0));
      dataTypeComboViewer.setSelection(new StructuredSelection(filterSelection));

      // MessageTypes
      List<AccessPointType> selection = new ArrayList<AccessPointType>();
      selection.add(controller.getAvailableMessageTypes().get(0));
      messageNameText.setSelection(0, messageNameText.getText().length());

   }

  protected void buttonEnablement() {
	String text = messageNameText.getText(); 
	this.errorLabel.setVisible(isError);
	if (getButton(IDialogConstants.OK_ID) != null) {
		if (selectedFilter instanceof SerializableFilter) {
			getButton(IDialogConstants.OK_ID).setEnabled(!isError && !text.equalsIgnoreCase("") && text.indexOf(" ") == -1);			 //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			getButton(IDialogConstants.OK_ID).setEnabled(!isError && !text.equalsIgnoreCase("") && text.indexOf(" ") == -1 && messageCombo.getSelectionIndex() != -1); //$NON-NLS-1$ //$NON-NLS-2$
		}				
	}		
}


protected void createButtonsForButtonBar(Composite parent) {
    super.createButtonsForButtonBar(parent);
    getButton(IDialogConstants.OK_ID).setEnabled(false);
    initCombos();
    this.buttonEnablement();
}

  protected void buttonPressed(int buttonId) {
    if (buttonId == IDialogConstants.OK_ID) {
		messageName = messageNameText.getText();
	}
	super.buttonPressed(buttonId);		
		
  }

public void modifyText(ModifyEvent arg0) {	
	String text = messageNameText.getText();
	if (!controller.isSimpleMode()) {
		if (getMessageTypeByName(text) != null) {
			isError = true;
			errorLabel.setToolTipText(MessageFormat.format(Modeling_Messages.TXT_DOES_ALREADY_EXIST, new Object[]{text}));	
		} else {
			if (!StringUtils.isValidIdentifier(text)) {
				isError = true;
				errorLabel.setToolTipText(MessageFormat.format(Modeling_Messages.TXT_NOT_VALID_NAME, new Object[]{text}));			
			} else {
				isError = false;
				errorLabel.setToolTipText(null);				
			}
		}		
	} else {
		if (!StringUtils.isValidIdentifier(text)) {
			isError = true;
			errorLabel.setToolTipText(MessageFormat.format(Modeling_Messages.TXT_NOT_VALID_NAME, new Object[]{text}));	
		} else {
			isError = false;
			errorLabel.setToolTipText(null);				
		}		
	}
	buttonEnablement();	
}

   private void serializableTypeModified(ModifyEvent e)
   {
      String messageName = classBrowser.getType().getType().getElementName();
      String text = messageName;
      int n = 1;
      while (isAccessPointIdDefined(text + n))
      {
         n++;
      }
      text = text + n;
      messageNameText.setText(text);
      this.messageType = controller.getMtaUtils().createSerializableAccessPoint(classBrowser.getType(),
            text, directionType);
   }

   public AccessPointType getMessageType()
   {
      return messageType;
   }

   public String getMessageName()
   {
      return messageName;
   }

   private boolean isAccessPointIdDefined(String id)
   {
      for (AccessPointType messageType : allMessageTypes)
      {
         if (messageType.getId().equalsIgnoreCase(id))
         {
            return true;
         }
      }
      return false;
   }

   private AccessPointType getMessageTypeByName(String name)
   {
      for (AccessPointType messageType : allMessageTypes)
      {
         if (messageType.getId().equalsIgnoreCase(name))
         {
            return messageType;
         }
      }
      return null;
   }
}
