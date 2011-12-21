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

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.spi.dataTypes.primitive.PrimitiveAccessPathEditor;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.util.Path;
import org.eclipse.stardust.modeling.validation.util.PathEntry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import ag.carnot.base.StringUtils;
import ag.carnot.bpm.rt.data.structured.IXPathMap;
import ag.carnot.bpm.rt.data.structured.StructuredDataXPathUtils;
import ag.carnot.bpm.rt.data.structured.TypedXPath;
import ag.carnot.bpm.rt.data.structured.spi.StructDataTransformerKey;
import ag.carnot.reflect.Reflect;

public class AccessPathWizard extends Dialog
{
   private static final int CUT_ID = IDialogConstants.CLIENT_ID + 1;
   
   private String title;

   private Path path;
   private PathEntry selectedPath;
   private PathEntry selectedPath_;
   private ITypedElement ap;
   private String transformerKey = null;

   private StyledText pathViewer;
   private Label typeViewer;
   
   private TableViewer childrenViewer;
   
   private Group detailsArea;
   private Composite xomDomArea;
   private Button allRadio;
   private Button indexedRadio;
   private Button collectionAPIRadio;
   private Button domRadio;
   private LabeledText indexText;   
   private boolean isLego;
   private boolean cutPressed;   
   private IXPathMap xPathMap;   
   private DirectionType direction;

   public AccessPathWizard(WorkflowModelEditor editor, String title,
			ITypedElement accessPoint, DirectionType direction) {
		super(editor.getSite().getShell());
		ap = accessPoint;
		setShellStyle(getShellStyle() | SWT.RESIZE);

		this.direction = direction;
		
		if (ap.getMetaType().getId().startsWith("struct")) { //$NON-NLS-1$
			try {
				if (ap instanceof DataType) {
					initializeForDataType();
				}
				if (ap instanceof AccessPointType) {
					initializeForAccessPointType(editor, accessPoint);
				}

			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		PathEntry root = new PathEntry(ap, direction);
		path = new Path(root);
		String label = root.getLabel();
		this.title = label == null ? title : title + ' ' + label;
	}

	private void initializeForDataType() {
		DataType dataType = (DataType) ap;
		xPathMap = StructuredTypeUtils.getXPathMap(dataType);
		isLego = true;
	}

	private void initializeForAccessPointType(WorkflowModelEditor editor,
			ITypedElement accessPoint) {
		ModelType modelType = editor.getWorkflowModel();
		AccessPointType apt = (AccessPointType) ap;
		String type = AttributeUtil.getAttributeValue(((AccessPointType) apt)
				.getAttribute(), "carnot:engine:dataType"); //$NON-NLS-1$
		TypeDeclarationType decl = modelType.getTypeDeclarations()
				.getTypeDeclaration(type);
		xPathMap = StructuredTypeUtils.getXPathMap(decl);
		isLego = true;
		ap = new StructAccessPointType(xPathMap.getRootXPath(), xPathMap);
		((AccessPointType) ap)
				.setType((DataTypeType) accessPoint.getMetaType());
		IIdentifiableElement d = (IIdentifiableElement) accessPoint;
		((AccessPointType) ap).setId(d.getId());
		((AccessPointType) ap).setName(d.getName());
	}

   public void setMethod(String method)
   {
      try
      {
        if (isLego && method.matches(StructuredTypeUtils.TRANSFORMATION_PATTERN.pattern()))
        {
           if (StructuredTypeUtils.isValidDomAccessPath((DataType)ap, method))
           {
              if (method.startsWith("DOM")) //$NON-NLS-1$
              {
                 transformerKey = StructDataTransformerKey.DOM;
              }              
           }
           method = method.substring(4);
           method = method.substring(0, method.length() - 1);           
        }  
        path.setMethod(method == null ? "" : method.trim()); //$NON-NLS-1$
      }
      catch (ValidationException ex)
      {
         ex.printStackTrace();
      }
   }

   public String getSelectedMethod()
   {
      if (isLego && transformerKey != null) 
      {
         return transformerKey + "(" + path.getMethod() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      }     
      return path.getMethod();
   }

   protected void buttonPressed(int buttonId)
   {
      if (IDialogConstants.FINISH_ID == buttonId)
      {
         okPressed();
      }
      else if (IDialogConstants.BACK_ID == buttonId)
      {
         backPressed();
      }
      else if (CUT_ID == buttonId)
      {
         cutPressed();
      }
      else if (IDialogConstants.NEXT_ID == buttonId)
      {
         nextPressed();
      }
      else
      {
         super.buttonPressed(buttonId);
      }
   }

   protected void okPressed()
   {
      cutPressed = false;                  
      if (selectedPath != null)
      {
         // "Finish" should append the selected entry to the path
         selectedPath = path.push(selectedPath); 
         updateViewers();
      }
      if (selectedPath_ != null)
      {
         // "Finish" should append the selected entry to the path
         selectedPath = path.push(selectedPath_); 
         updateViewers();
      }
      
      super.okPressed();
   }

   protected void nextPressed()
   {
      cutPressed = false;                  
      if (selectedPath != null)
      {
         selectedPath = path.push(selectedPath);
      }
      updateViewers();
   }

   protected void backPressed()
   {
      cutPressed = false;                  
      selectedPath = (PathEntry) path.pop();
      updateViewers();
   }

   protected void cutPressed()
   {
      cutPressed = true;      
      path.cut();
      updateViewers();
      // remove selection in children viewer, otherwise "Cut"+"Finish" will append 
      // the selected item again and this is not what the user wants if he clicks "Cut"+"Finish"
      childrenViewer.setSelection(StructuredSelection.EMPTY);
   }

   private void updateViewers()
   {
      updateChildren();
      updateDetails();
      updatePathViewer();
      updateButtons();
   }

   protected void createButtonsForButtonBar(Composite parent)
   {
      createButton(parent, IDialogConstants.BACK_ID, IDialogConstants.BACK_LABEL, false);
      createButton(parent, IDialogConstants.NEXT_ID, IDialogConstants.NEXT_LABEL, false);
      createButton(parent, CUT_ID, Diagram_Messages.BUT_CUT, false);
      createButton(parent, IDialogConstants.FINISH_ID, IDialogConstants.FINISH_LABEL, true);
      createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
      
      updateButtons();
   }

   protected Control createDialogArea(Composite parent)
   {
      getShell().setText(title);
      
      Composite composite = (Composite) super.createDialogArea(parent);
      GridLayout layout = (GridLayout) composite.getLayout();
      layout.numColumns = 2;
      
      FormBuilder.createLabel(composite, Diagram_Messages.LB_Path);
      pathViewer = new StyledText(composite, SWT.BORDER);
      pathViewer.setBackground(composite.getBackground());
      pathViewer.setCursor(pathViewer.getDisplay().getSystemCursor(SWT.CURSOR_ARROW));
      pathViewer.getCaret().setVisible(false);
      pathViewer.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(1));
      FormBuilder.applyDefaultTextControlWidth(pathViewer);
      pathViewer.setEditable(false);
      // avoid showing selection
      pathViewer.setSelectionBackground(pathViewer.getBackground());
      pathViewer.setSelectionForeground(pathViewer.getForeground());

      FormBuilder.createLabel(composite, Diagram_Messages.LB_Type);
      typeViewer = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      typeViewer.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(1));
      FormBuilder.applyDefaultTextControlWidth(typeViewer);
      updatePathViewer();
      
      FormBuilder.createHorizontalSeparator(composite, 2);
      
      detailsArea = FormBuilder.createGroup(composite, Diagram_Messages.LB_DetailsFor, 3, 2);
      GridData gd = (GridData) detailsArea.getLayoutData();
      gd.grabExcessVerticalSpace = false;
      if (isLego) 
      {
         xomDomArea = FormBuilder.createComposite(detailsArea, 3, 3);
         GridLayout gridLayout = (GridLayout) xomDomArea.getLayout();
         gridLayout.marginLeft = 0;
         gridLayout.horizontalSpacing = 0;
         gridLayout.marginWidth = 0;
         gridLayout.marginBottom = 0;
         gridLayout.marginTop = 0;
         domRadio = FormBuilder.createRadioButton(xomDomArea, Diagram_Messages.BUT_DOM);
         collectionAPIRadio = FormBuilder.createRadioButton(xomDomArea, Diagram_Messages.BUT_STANDARD_API);
         
         
         domRadio.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}

            public void widgetSelected(SelectionEvent e)
            {                               
               if (((Button)e.getSource()).getSelection())
               {
                  transformerKey = StructDataTransformerKey.DOM;
               } 
               updatePathViewer();
            }
         });
         
         collectionAPIRadio.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e)
            {}
            public void widgetSelected(SelectionEvent e)
            {                               
               if (((Button)e.getSource()).getSelection())
               {
                  transformerKey = null;
               }       
               updatePathViewer();
            }        
         });
      }
      
      indexedRadio = FormBuilder.createRadioButton(detailsArea, Diagram_Messages.LB_Indexed);
      indexText = FormBuilder.createLabeledText(detailsArea, ""); //$NON-NLS-1$
      allRadio = FormBuilder.createRadioButton(detailsArea, Diagram_Messages.LB_All, 3);      
            
      updateDetails();

      Table rawTable = FormBuilder.createTable(composite,
            SWT.BORDER | SWT.FULL_SELECTION, new String[] {Diagram_Messages.COL_Element, Diagram_Messages.COL_Type},
            new int[] {49, 49}, 2);
      gd = (GridData) rawTable.getLayoutData();
      gd.heightHint = 100;
      childrenViewer = new TableViewer(rawTable);
      childrenViewer.setContentProvider(new ArrayContentProvider());
      childrenViewer.setLabelProvider(new TableLabelProvider());
      childrenViewer.setSorter(new ViewerSorter());
      updateChildren();

      // add selection after first children update because buttons are not yet created.
      childrenViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            pathChanged(event.getSelection());
         }
      });
      childrenViewer.addDoubleClickListener(new IDoubleClickListener()
      {
         public void doubleClick(DoubleClickEvent event)
         {
            if (event.getSelection().isEmpty())
            {
               // no action if nothing selected with the double-click
               return;
            }
            // do "next"
            nextPressed();
         }
      });
      allRadio.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            path.setIndex(PathEntry.ALL);
            indexText.getText().setEnabled(false);
            indexText.getText().setText(""); //$NON-NLS-1$
            updateViewers();
         }
      });
      indexedRadio.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            indexText.getText().setEnabled(true);
            if (path.getIndex() == PathEntry.ALL)
            {
               path.setIndex(1);
            }
            indexText.getText().setText(Long.toString(path.getIndex()));
            updateViewers();
         }
      });
      indexText.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String index = indexText.getText().getText().trim();
            if (index.length() > 0)
            {
               try
               {
                  path.setIndex(Long.parseLong(index));
                  indexText.getLabel().setValidationStatus(IQuickValidationStatus.OK);
                  indexText.getLabel().setToolTipText(null);
               }
               catch (Exception ex)
               {
                  indexText.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
                  String msg = MessageFormat.format(Diagram_Messages.MSG_InvalidIndex, new Object[] { index });
                  indexText.getLabel().setToolTipText(msg);
               }
            }
            else
            {
               indexText.getLabel().setValidationStatus(IQuickValidationStatus.OK);
               indexText.getLabel().setToolTipText(null);
            }
            updatePathViewer();
         }
      });
      pathViewer.addMouseListener(new MouseListener()
      {
         public void mouseDoubleClick(MouseEvent e)
         {}

         public void mouseDown(MouseEvent e)
         {
            int offset = pathViewer.getCaretOffset();
            selectPathEntry(offset);
         }

         public void mouseUp(MouseEvent e)
         {}
      });

      return composite;
   }

   private void selectPathEntry(int offset)
   {
      selectedPath = path.selectPathEntry(offset);
      updateViewers();
   }

   private void pathChanged(ISelection selection)
   {
      selectedPath = null;
      if (selection instanceof IStructuredSelection && !selection.isEmpty())
      {
         selectedPath = (PathEntry)
            ((IStructuredSelection) selection).getFirstElement();
         selectedPath_ = null;
         
         if(isLego && selectedPath != null)
         {
            String method = path.getMethod();
            if(!StringUtils.isEmpty(method))
            {
               method = method + "/"; //$NON-NLS-1$
            }
            
            String xPath = StructuredDataXPathUtils.getXPathWithoutIndexes(method + selectedPath.getId());            
            TypedXPath path_ = xPathMap.getXPath(xPath);
            if(path_ != null)
            {
               if(path_.getChildXPaths().isEmpty())
               {
                  selectedPath_ = selectedPath;
                  selectedPath = null;
               }
            }
            else
            {
               selectedPath = null;               
            }
         }
         else if(selectedPath != null && ap instanceof DataType)
         {
            DataTypeType dataType = (DataTypeType) ap.getMetaType();
            IAccessPathEditor editor = AccessPointUtil.getSPIAccessPathEditor(dataType);
            String className = null;
             
            if(editor instanceof PrimitiveAccessPathEditor)
            {
               Class type = Reflect.getClassFromAbbreviatedName(AttributeUtil.getAttributeValue(
                     (IExtensibleElement) ap, CarnotConstants.TYPE_ATT));
               className = (null != type) ? type.getName() : null;            	 
            }             

            if(className != null)
            {
               String id = null;
               String path = selectedPath.getFullId();
               ITypedElement element = ap;
		      
               if (path != null && path.length() == 0)
               {
                  path = null;
               }
		      
               while (element != null && editor != null && path != null)
               {
                  String[] splitted = editor.splitAccessPath(path);
                  id = splitted[0];
                  path = splitted[1];
		         
                  DirectionType segmentDirection = (DirectionType.IN_LITERAL.equals(direction) && !StringUtils.isEmpty(path))
                     ? DirectionType.OUT_LITERAL : direction; 
		         
                  List accessPoints = editor.getAccessPoints(id,
                        (IExtensibleElement) element, segmentDirection);
                  element = null;
                  for (int i = 0; i < accessPoints.size(); i++)
                  {
                     AccessPointType ap = (AccessPointType) accessPoints.get(i);
                     if (ap.getId().equals(id))
                     {
                        element = ap;
                        editor = AccessPointUtil.getSPIAccessPathEditor(ap.getType());
                        break;
                     }
                  }
               }
               if(element == null)
               {
                  selectedPath = null;               		    	  
               }		      
            }
         }
      }
      updateButtons();
   }

   private void updateButtons()
   {
      getButton(IDialogConstants.BACK_ID).setEnabled(!path.isRootSelected());
      getButton(IDialogConstants.NEXT_ID).setEnabled(selectedPath != null);
      getButton(CUT_ID).setEnabled(!path.isLastItem());
      if(isLego)
      {
         getButton(IDialogConstants.FINISH_ID).setEnabled(selectedPath != null || selectedPath_ != null || cutPressed);         
   }
      else
      {
         getButton(IDialogConstants.FINISH_ID).setEnabled(path.matchesDirection());         
      }
   }

   private void updateDetails()
   {
      boolean indexed = path.isIndexed();       
      if (isLego && path.getLabel() != null) 
      {
         String xPathString  = path.getLabel();        
         xPathString = xPathString.substring(xPathString.indexOf("/") + 1);         //$NON-NLS-1$
         TypedXPath xPath = xPathMap.getXPath(xPathString);        
         xomDomArea.setVisible(isLego && xPath != null);
         domRadio.setVisible(isLego && xPath != null);
         collectionAPIRadio.setVisible(isLego && xPath != null);        
         if (xPath != null)
         {
            boolean listOrPrimitive = (StructuredDataXPathUtils.canReturnList(xPathString, xPathMap) || StructuredDataXPathUtils.returnsSinglePrimitive(xPathString, xPathMap));
            if (listOrPrimitive)
            {
               transformerKey = null;
            }
            domRadio.setSelection(transformerKey != null && transformerKey.equalsIgnoreCase(StructDataTransformerKey.DOM) && !listOrPrimitive);
            collectionAPIRadio.setSelection(transformerKey == null || listOrPrimitive);
            domRadio.setEnabled(!listOrPrimitive);
            collectionAPIRadio.setEnabled(true);
         }
         else
         {
            transformerKey = null;
         }
      }      
      allRadio.setVisible(indexed);
      indexedRadio.setVisible(indexed);
      indexText.getText().setVisible(indexed);
      if (indexed)
      {
         if (!path.isIndexed() || path.getIndex() == PathEntry.ALL)
         {
            indexedRadio.setSelection(false);
            allRadio.setSelection(true);
            indexText.getText().setText(""); //$NON-NLS-1$
            indexText.getText().setEnabled(false);
         }
         else
         {
            allRadio.setSelection(false);
            indexedRadio.setSelection(true);
            indexText.getText().setText(Long.toString(path.getIndex()));
            indexText.getText().setEnabled(true);
         }
      } 
      
      String detailsTitle = MessageFormat.format(Diagram_Messages.LB_DetailsFor,
            new Object [] {path.getSelection().getLabel()});
      detailsArea.setText(detailsTitle);
   }

   private void updatePathViewer()
   {
      String label = path.getLabel();
      if (isLego && transformerKey != null) 
      {
         label = transformerKey + "(" + label + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      }      
      pathViewer.setText(label);      
      StyleRange unstyled = new StyleRange();
      unstyled.start = 0;
      unstyled.length = label.length();
      pathViewer.setStyleRange(unstyled);
      StyleRange styled = new StyleRange();
      styled.fontStyle = SWT.BOLD;
      styled.start = path.getSelectionStart();
      styled.length = path.getSelectionLength();
      pathViewer.setStyleRange(styled);
      typeViewer.setText(path.getType());
   }

   private void updateChildren()
   {
      List<PathEntry> children = path.getChildren();
      boolean hasChildren = !children.isEmpty();

      // preserve selection
      PathEntry selection = selectedPath;
      childrenViewer.setInput(children);
      childrenViewer.setSelection(selection == null ? StructuredSelection.EMPTY
            : new StructuredSelection(selection));
      
      childrenViewer.getTable().setEnabled(hasChildren);
      
      if (this.getDialogArea() != null)
      {
         ((Composite)this.getDialogArea()).layout();
      }
   }
   
   private static class TableLabelProvider extends LabelProvider implements ITableLabelProvider
   {
      public Image getColumnImage(Object element, int columnIndex)
      {
         if (columnIndex == 0)
         {
            PathEntry entry = (PathEntry) element;
            String image = entry.getIcon();
            return DiagramPlugin.getImage(image);
         }
         return null;
      }

      public String getColumnText(Object element, int columnIndex)
      {
         PathEntry entry = (PathEntry) element;
         switch (columnIndex)
         {
            case 0: return entry.getLabel();
            case 1: return entry.getTypeName();
         }
         return ""; //$NON-NLS-1$
      }
   }
}