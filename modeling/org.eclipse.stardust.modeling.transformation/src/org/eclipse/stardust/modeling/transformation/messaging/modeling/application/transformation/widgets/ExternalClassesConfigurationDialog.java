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
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;



public class ExternalClassesConfigurationDialog extends Dialog implements ModifyListener {
  private static final int RESET_ID = IDialogConstants.NO_TO_ALL_ID + 1;
  private Text messageNameText;
  private String tagName;
  private ComboViewer messageComboViewer;
  private MessageTransformationController controller;
  private AccessPointType messageType;
  private String messageName;
  private List messageTypes;
  private String preset;
  private List allMessageTypes = new ArrayList();
  private List typeFilters = new ArrayList();
  private List<AccessPointType> externalClasses = new ArrayList<AccessPointType>();
 
  
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
private TableViewer tableViewer;
private boolean showMissing;


  public ExternalClassesConfigurationDialog(Shell parentShell, MessageTransformationController controller, List messageTypes, String preset, DirectionType directionType) {
    super(parentShell);    
    setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.BORDER| SWT.APPLICATION_MODAL | SWT.RESIZE);
    this.controller = controller;
    this.messageTypes = messageTypes;
    this.directionType = directionType;
    this.preset = preset;
	allMessageTypes.addAll(controller.getSourceMessageTypes());
	allMessageTypes.addAll(controller.getTargetMessageTypes());
	typeFilters.add(new StructuredTypesFilter());
	typeFilters.add(new PrimitivesFilter());
	if (!controller.isSimpleMode()) {
		typeFilters.add(new SerializableFilter());	
	}
	//Save externalClasses
	for (Iterator<AccessPointType> i = controller.getExternalClassTypes().iterator(); i.hasNext();) {
		AccessPointType apt = i.next();
		externalClasses.add(apt);
		if (apt.getElementOid() == -99) {
			showMissing = true;
		}
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
    
    Group tableComposite = new Group(comp, SWT.NONE);
    tableComposite.setText(Modeling_Messages.TXT_EXTERNAL_CL);
    GridLayout tableCompLayout = new GridLayout();    
    tableComposite.setLayout(tableCompLayout);
    GridData tableCompGridData = new GridData();
    tableCompGridData.grabExcessHorizontalSpace = true;
    tableCompGridData.grabExcessVerticalSpace = true;
    tableCompGridData.horizontalAlignment = SWT.FILL;
    tableCompGridData.verticalAlignment = SWT.FILL; 
    tableCompGridData.horizontalSpan = 3;
    tableComposite.setLayoutData(tableCompGridData);
    
    Table table = new Table(tableComposite, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
            | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);    

    table.setHeaderVisible(true);
    
    GridData tableData = new GridData();
    tableData.grabExcessHorizontalSpace = true;
    tableData.grabExcessVerticalSpace = true;
    tableData.horizontalAlignment = SWT.FILL;
    tableData.verticalAlignment = SWT.FILL;
    table.setLayoutData(tableData);
    table.setLinesVisible(true);
    
    TableColumn column = new TableColumn(table, SWT.LEFT, 0);
    column.setText(Modeling_Messages.TXT_INSTANCE_NAME);
    column.setWidth(150);
    column = new TableColumn(table, SWT.LEFT, 1);
    column.setText(Modeling_Messages.TXT_CL_NAME);
    column.setWidth(400);
    
    tableViewer = new TableViewer(table);

    tableViewer.setUseHashlookup(true);
    tableViewer.setLabelProvider(new ExternalClassesLabelProvider());
    tableViewer.setContentProvider(new ArrayContentProvider());
    tableViewer.setInput(controller.getExternalClassTypes());
    
    if (showMissing) {
        errorLabel = new Label(comp, SWT.NONE);
        errorLabel.setImage(MessageTransformationModelingPlugin.getDefault().getImageDescriptor("icons/error.gif").createImage());         //$NON-NLS-1$
        errorLabel.setVisible(true);
        errorLabel = new Label(comp, SWT.NONE);
        errorLabel.setText(Modeling_Messages.TXT_LEAST_ONE_EXTERNAL_CL_IS_MISSING_DEL_CORRE_ENT_OR_ADD_CLASSPATH);
        errorLabel.setVisible(true);
    }
    
    Composite buttonComposite = new Composite(tableComposite, SWT.NONE);
    RowLayout buttonCompLayout = new RowLayout();
    buttonComposite.setLayout(buttonCompLayout);
    RowData buttonCompRowData = new RowData();
    buttonCompRowData.height = 23;
    buttonCompRowData.width = 70;
    
    Button addButton = new Button(buttonComposite, SWT.NONE);
    addButton.setLayoutData(buttonCompRowData);
    
    addButton.setText(Modeling_Messages.TXT_ADD_DREI_PUNKT);
    
    addButton.addSelectionListener(new SelectionAdapter(){
        public void widgetSelected(SelectionEvent e)
        {
        	ExternalClassAdditionDialog dialog = new ExternalClassAdditionDialog(Display
                    .getCurrent().getActiveShell(), controller, controller
                    .getExternalClassTypes(), "", DirectionType.IN_LITERAL); //$NON-NLS-1$
            if (dialog.open() == IDialogConstants.OK_ID)
            {            	            	
            	AccessPointType ap = controller.createExternalClass(dialog.getMessageType(), dialog.getMessageName(), false);
            	ap.setElementOid(1);
            	externalClasses.add(ap);
            	tableViewer.setInput(externalClasses);
            } 
        }
    });
          
    Button removeButton = new Button(buttonComposite, SWT.NONE);
    removeButton.setLayoutData(buttonCompRowData);
    removeButton.setText(Modeling_Messages.TXT_REMOVE);
    
    removeButton.addSelectionListener(new SelectionAdapter(){
        public void widgetSelected(SelectionEvent e)
        {
        	IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        	if (!selection.isEmpty()) {
        		AccessPointType externalClassAP = (AccessPointType) selection.getFirstElement();
        		externalClasses.remove(externalClassAP);
        		externalClassAP.setElementOid(0);
        		tableViewer.setInput(externalClasses);
        	}
        }
    });  
    
    parent.getShell().setMinimumSize(600, 300);
    parent.getShell().setText(Modeling_Messages.TXT_CFG_EXT_CL);
    return comp;
  }

private void initCombos()
{   
   messageComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

		public void selectionChanged(SelectionChangedEvent event) {			
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			if (!selection.isEmpty()) {
	            messageType = (AccessPointType) selection.getFirstElement();
	            String text = messageType.getId();
	            int n = 1;
	            while ( isAccessPointIdDefined(text + n) )
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
   
    dataTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event)
      {
         IStructuredSelection selection = (IStructuredSelection) event.getSelection();
         selectedFilter = (ViewerFilter) selection.getFirstElement();
         messageComboViewer.setFilters(new ViewerFilter[]{selectedFilter});
         if (selectedFilter instanceof SerializableFilter) 
         {
        	 stack.topControl = classBrowseComposite;          	         	            
         } else {
        	 stack.topControl = structPrimComposite;      	 
         }
         messageComposite.setText(Modeling_Messages.TXT_SEL + selectedFilter.toString());
    	 messageComposite.layout();
      }
       
    });
          
    messageComboViewer.setInput(controller.getAvailableMessageTypes());
    messageComboViewer.addSelectionChangedListener(new ISelectionChangedListener(){

		public void selectionChanged(SelectionChangedEvent event) {
			if (event.getSelection() instanceof IStructuredSelection)  {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				messageType = (AccessPointType)selection.getFirstElement();
			}			
		}			
    
    });  
    //Filters
    dataTypeComboViewer.setInput(typeFilters); 
    List filterSelection = new ArrayList();
    filterSelection.add(typeFilters.get(0));
    dataTypeComboViewer.setSelection(new StructuredSelection(filterSelection));
    
    //MessageTypes
    List selection = new ArrayList();
    selection.add(controller.getAvailableMessageTypes().get(0)); 
    messageNameText.setSelection(0, messageNameText.getText().length());

}

  protected void buttonEnablement() {
	if (getButton(IDialogConstants.OK_ID) != null) {
		getButton(IDialogConstants.OK_ID).setEnabled(true);
	}

}


protected void createButtonsForButtonBar(Composite parent) {
    super.createButtonsForButtonBar(parent);
    getButton(IDialogConstants.OK_ID).setEnabled(false);
    //initCombos();
    this.buttonEnablement();
}

  protected void buttonPressed(int buttonId) {
    if (buttonId == IDialogConstants.OK_ID) {
		controller.getExternalClassTypes().clear();
		for (Iterator<AccessPointType> i = externalClasses.iterator(); i.hasNext();) {		
			AccessPointType ap = i.next();
			controller.createExternalClass(ap, ap.getName(), true);
		}				
	} else {
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
				errorLabel.setToolTipText(MessageFormat.format(Modeling_Messages.TXT_NOT_VALID_NAME, new Object[]{text}));				 //$NON-NLS-1$
			} else {
				isError = false;
				errorLabel.setToolTipText(null);				
			}
		}		
	} else {
		if (!StringUtils.isValidIdentifier(text)) {
			isError = true;
			errorLabel.setToolTipText(MessageFormat.format(Modeling_Messages.TXT_NOT_VALID_NAME, new Object[]{text}));				 //$NON-NLS-1$
		} else {
			isError = false;
			errorLabel.setToolTipText(null);				
		}		
	}
	buttonEnablement();	
}


public AccessPointType getMessageType() {
	return messageType;
}

public String getMessageName() {
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

private AccessPointType getMessageTypeByName(String name) {
	for (Iterator i = allMessageTypes.iterator(); i.hasNext();) {
	   AccessPointType messageType = (AccessPointType)i.next();		
		if (messageType.getId().equalsIgnoreCase(name)) {
			return messageType;
		}
	}
	return null;
}



}
