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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering.XPathFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;



public class IndexConfigurationDialog extends Dialog implements ModifyListener {
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
  private List<AccessPointType> inputModel = new ArrayList<AccessPointType>();
  private List<AccessPointType> outputModel = new ArrayList<AccessPointType>();
  public static final String[] PROPS = { Modeling_Messages.COL_NAME, Modeling_Messages.COL_INDEX}; 

  
 
  
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
private Group sourceComposite;
private Group targetComposite;
private Tree sourceMessageTree;
private MTATreeViewer sourceMessageTreeViewer;
private Tree targetMessageTree;
private MTATreeViewer targetMessageTreeViewer;
private Map<String,String> indexMap = new HashMap<String,String>();
private XPathFilter sourceXPathViewerFilter;
private XPathFilter targetXPathViewerFilter;
private AccessPointType source;
private AccessPointType target;
private boolean showInvolvedOnly = true;
private MappingConfiguration config;
private Button appendButton;
private Button overwriteButton;
private boolean canceled;
private MultipleAccessPathBrowserContentProvider sourceProvider;
private MultipleAccessPathBrowserContentProvider targetProvider;


@Override
protected void cancelPressed() {
    canceled = true;
	super.cancelPressed();
}

public boolean isCanceled() {
	return canceled;
}

public IndexConfigurationDialog(Shell parentShell, MessageTransformationController controller, MappingConfiguration config) {
    super(parentShell);    
    setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.BORDER| SWT.APPLICATION_MODAL | SWT.RESIZE);
    this.controller = controller;
    inputModel.addAll(controller.getSourceMessageTypes());
    outputModel.addAll(controller.getTargetMessageTypes());
	this.source = config.getSourceType();
	this.target = config.getTargetType();
	this.config = config;
  }

public Map<String,String> getIndexMap() {
	return indexMap;
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
    tableComposite.setText(Modeling_Messages.TXT_INDEX_CFG);
    GridLayout tableCompLayout = new GridLayout();
    tableCompLayout.numColumns = 2;
    tableComposite.setLayout(tableCompLayout);
    GridData tableCompGridData = new GridData();
    tableCompGridData.grabExcessHorizontalSpace = true;
    tableCompGridData.grabExcessVerticalSpace = true;
    tableCompGridData.horizontalAlignment = SWT.FILL;
    tableCompGridData.verticalAlignment = SWT.FILL; 
    tableCompGridData.horizontalSpan = 3;
    tableComposite.setLayoutData(tableCompGridData);

    GridLayout fillLayout = new GridLayout();
    
    
    sourceComposite = new Group(tableComposite, SWT.NONE);        
    GridData messageData = new GridData();
    messageData.grabExcessHorizontalSpace = true;
    messageData.grabExcessVerticalSpace = true;
    messageData.horizontalAlignment = SWT.FILL;
    messageData.verticalAlignment = SWT.FILL;
    sourceComposite.setLayoutData(messageData);
    sourceComposite.setText(MessageFormat.format(Modeling_Messages.TXT_SR, new Object[]{controller.getNameString()}));
    sourceComposite.setLayout(fillLayout);
      
    targetComposite = new Group(tableComposite, SWT.NONE);         
    targetComposite.setLayout(fillLayout);
    targetComposite.setLayoutData(messageData);
    targetComposite.setText(MessageFormat.format(Modeling_Messages.TXT_TARGET, new Object[]{controller.getNameString()}));
    GridData treeData = new GridData();
    treeData.grabExcessHorizontalSpace = true;
    treeData.grabExcessVerticalSpace = true;
    treeData.horizontalAlignment = SWT.FILL;
    treeData.verticalAlignment = SWT.FILL;
    treeData.horizontalSpan = 2;
    
    Button showAllButton = new Button(tableComposite, SWT.CHECK);
    showAllButton.setText(Modeling_Messages.TXT_SH_AFFECTED_TREE_PATH_ONLY);
    showAllButton.addSelectionListener(new SelectionListener() {

		public void widgetDefaultSelected(SelectionEvent e) {
		}

		public void widgetSelected(SelectionEvent e) {
			showInvolvedOnly = !showInvolvedOnly;
			sourceXPathViewerFilter.setActive(showInvolvedOnly);
			targetXPathViewerFilter.setActive(showInvolvedOnly);
			refreshModel();
		}
    	
    });
    
    sourceMessageTree = new Tree(sourceComposite, SWT.MULTI | SWT.FULL_SELECTION
            | SWT.BORDER);
    GridData treeGridData = new GridData();
    treeGridData.grabExcessVerticalSpace = true;
    treeGridData.verticalAlignment = SWT.FILL;
    sourceMessageTree.setLayoutData(treeGridData);

    sourceMessageTree.setHeaderVisible(true);
    sourceMessageTreeViewer = new MTATreeViewer(sourceMessageTree);
      
    String[] sourceColumns = {Modeling_Messages.COL_NAME, Modeling_Messages.COL_INDEX}; 
    String[] targetColumns = {Modeling_Messages.COL_NAME, Modeling_Messages.COL_INDEX};
                  
    for (int i = 0; i < sourceColumns.length; i++)
    {
       TreeColumn column = new TreeColumn(sourceMessageTree, SWT.LEFT);
       column.setText(sourceColumns[i]);
       if(i == 0)
       {
          column.setWidth(180);
       }
       else
       {
          column.setWidth(100);               
       }
    }
    
    CellEditor[] editors = new CellEditor[2];
    editors[0] = null;
    editors[1] = new TextCellEditor(sourceMessageTreeViewer.getTree());
    
    sourceMessageTreeViewer.setColumnProperties(PROPS);
    sourceMessageTreeViewer.setCellModifier(new IndexCellModifier(sourceMessageTreeViewer));
    sourceMessageTreeViewer.setCellEditors(editors);

    sourceMessageTreeViewer.setUseHashlookup(true);
    sourceMessageTreeViewer.getTree().setLayoutData(treeData);  
    targetMessageTree = new Tree(targetComposite, SWT.FULL_SELECTION | SWT.BORDER);
    targetMessageTree.setLayoutData(treeGridData);
    targetMessageTree.setHeaderVisible(true);
    targetMessageTreeViewer = new MTATreeViewer(targetMessageTree);
    for (int i = 0; i < targetColumns.length; i++)
    {
       TreeColumn column = new TreeColumn(targetMessageTree, SWT.LEFT);
       column.setText(targetColumns[i]);
       if(i == 0)
       {
          column.setWidth(180);               
       }
       else
       {
          column.setWidth(100);               
       }
    }
    targetMessageTreeViewer.setUseHashlookup(true);
    targetMessageTreeViewer.setColumnProperties(targetColumns);
    targetMessageTreeViewer.getTree().setLayoutData(treeData);
    
    editors = new CellEditor[2];
    editors[0] = null;
    editors[1] = new TextCellEditor(targetMessageTreeViewer.getTree());
    
    targetMessageTreeViewer.setColumnProperties(PROPS);
    targetMessageTreeViewer.setCellModifier(new IndexCellModifier(targetMessageTreeViewer));
    targetMessageTreeViewer.setCellEditors(editors);
    
    sourceXPathViewerFilter = new XPathFilter(controller, controller.getXPathFor(source));
    sourceMessageTreeViewer.addFilter(sourceXPathViewerFilter);
    sourceXPathViewerFilter.setActive(true);
    
    targetXPathViewerFilter = new XPathFilter(controller, controller.getXPathFor(target));
    targetMessageTreeViewer.addFilter(targetXPathViewerFilter);
    targetXPathViewerFilter.setActive(true);
    
    showAllButton.setSelection(true);

    
    /*Composite radioComposite2 = new Composite(comp, SWT.NONE);    
    GridLayout radioCompLayout = new GridLayout();
    radioComposite2.setLayout(radioCompLayout);
    GridData radioCompGridData = new GridData();
    radioCompGridData.grabExcessHorizontalSpace = true;
    radioCompGridData.grabExcessVerticalSpace = true; 
    radioCompGridData.horizontalAlignment = SWT.FILL;
    radioCompGridData.verticalAlignment = SWT.FILL;
    radioComposite2.setLayoutData(radioCompGridData);*/
    
    Group radioComposite = new Group(comp, SWT.NONE);
    radioComposite.setText(Modeling_Messages.TXT_MAPPING_STRATEGY);
    GridLayout radioCompLayout = new GridLayout();
    radioComposite.setLayout(radioCompLayout);
    GridData radioCompGridData = new GridData();
    radioCompGridData.grabExcessHorizontalSpace = true;
    radioCompGridData.grabExcessVerticalSpace = true; 
    radioCompGridData.horizontalAlignment = SWT.FILL;
    radioCompGridData.verticalAlignment = SWT.FILL;
    radioComposite.setLayoutData(radioCompGridData);

     
    //radioCompGridData.heightHint = 0;
    //radioCompGridData.horizontalIndent = 0;
    //radioCompGridData.verticalIndent = 0;
    //radioCompGridData.widthHint = 60;

    
    appendButton = new Button(radioComposite, SWT.RADIO);
    appendButton.setText(Modeling_Messages.TXT_APPEND_ELE);
    appendButton.setEnabled(config.isAppend() && config.isOverwrite());
    appendButton.addSelectionListener(new SelectionListener(){

		public void widgetDefaultSelected(SelectionEvent e) {						
		}

		public void widgetSelected(SelectionEvent e) {	
			config.setAppend(appendButton.getSelection());
			config.setOverwrite(overwriteButton.getSelection());			
		}
    	
    });

    overwriteButton = new Button(radioComposite, SWT.RADIO);
    overwriteButton.setText(Modeling_Messages.TXT_EXISTING_ELE);
    overwriteButton.setEnabled(config.isAppend() && config.isOverwrite());
    radioComposite.setEnabled(config.isAppend() && config.isOverwrite());
    
   
    overwriteButton.addSelectionListener(new SelectionListener(){

		public void widgetDefaultSelected(SelectionEvent e) {						
		}

		public void widgetSelected(SelectionEvent e) {				
			config.setAppend(appendButton.getSelection());
			config.setOverwrite(overwriteButton.getSelection());			
		}
    	
    });
    
    appendButton.setSelection(false);
    overwriteButton.setSelection(true);
    
    config.setAppend(false);
    config.setOverwrite(true);
    
    this.refreshModel();

    parent.getShell().setMinimumSize(600, 300);
    return comp;
  }


protected void okPressed() {
	config.setAppend(appendButton.getSelection());
	config.setOverwrite(overwriteButton.getSelection());
	super.okPressed();
}

class IndexCellModifier implements ICellModifier {
	  private Viewer viewer;

	  public IndexCellModifier(Viewer viewer) {
	    this.viewer = viewer;
	  }

	  public boolean canModify(Object element, String property) {		 
		 AccessPointType apt = (AccessPointType)element;		 
	     return controller.isList(apt);
	  }

	  public Object getValue(Object element, String property) {
		AccessPointType apt = (AccessPointType) element;
		String xPath = controller.getXPathFor(apt);
		String value = indexMap.get(xPath);	
		if (value == null) {
			value = "0"; //$NON-NLS-1$
			indexMap.put(xPath, value);
		}
    	return value;	     
	  }

	  public void modify(Object element, String property, Object value) {
		AccessPointType apt = (AccessPointType) ((TreeItem)element).getData();
		String xPath = controller.getXPathFor(apt);
		indexMap.put(xPath, value.toString());	
	    viewer.refresh();
	  }
	}

  protected void buttonEnablement() {
	if (getButton(IDialogConstants.OK_ID) != null) {
		getButton(IDialogConstants.OK_ID).setEnabled(true);
	}

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

public void refreshModel()
{
   sourceProvider = new MultipleAccessPathBrowserContentProvider(
         DirectionType.INOUT_LITERAL, controller);
   sourceMessageTreeViewer.setContentProvider(sourceProvider);
   sourceMessageTreeViewer.setLabelProvider(new DelegatingIndexingLabelProvider(controller, indexMap, controller.getXPathFor(source)));
   sourceMessageTreeViewer.setComparer(new MessageTypeComparer());
   sourceMessageTreeViewer.setInput(inputModel);
   controller.setSourceAPB(sourceProvider);

   targetProvider = new MultipleAccessPathBrowserContentProvider(DirectionType.INOUT_LITERAL, controller);
   targetMessageTreeViewer.setContentProvider(targetProvider);
   targetMessageTreeViewer.setLabelProvider(new DelegatingIndexingLabelProvider(controller, indexMap, controller.getXPathFor(target)));
   targetMessageTreeViewer.setComparer(new MessageTypeComparer());   
   targetMessageTreeViewer.setInput(outputModel);
   controller.setTargetAPB(targetProvider);

   sourceMessageTreeViewer.refreshVisibleItems();
   targetMessageTreeViewer.refreshVisibleItems();
}





}
