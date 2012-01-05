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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ScopeUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import ag.carnot.workflow.model.PredefinedConstants;

public class SubprocessRuntimeBindingPropertyPage
      extends AbstractModelElementPropertyPage
{
   private ComboViewer dataText;
   private AccessPathBrowserComposite dataPathBrowser;
   private LabelWithStatus dataLabel;
   private LabelWithStatus dataPathLabel;
   private Group dataGroup;
   private Button dynamicBindingCheckBox;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager binding = getWidgetBindingManager();

      ModelType model = ModelUtils.findContainingModel(element);

      dataText.setInput(model.getData());
      binding.bind(new LabeledViewer(dataText, dataLabel),
            (IExtensibleElement) element, PredefinedConstants.BINDING_DATA_ID_ATT, model.getData());
      binding.bind(new LabeledText(dataPathBrowser.getMethodText(), dataPathLabel),
            (IExtensibleElement) element, PredefinedConstants.BINDING_DATA_PATH_ATT);
      binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(model, model.getData()),
            new Data2DataPathWidgetAdapter2(dataText, dataPathBrowser,
                  DirectionType.IN_LITERAL));
      
      boolean hasDepartments = AttributeUtil.getBooleanValue((IExtensibleElement) element,
            PredefinedConstants.BINDING_ATT);
      dynamicBindingCheckBox.setSelection(hasDepartments);
      dataGroup.setVisible(hasDepartments);
      
      validate(null);
   }

   private void validate(Object selection)
   {      
      String dataPath = null;
      DataType dataType = null;
      
      if (selection != null)
      {
         if (selection instanceof String)
         {
            dataPath = (String) selection;
         }
         else if (selection instanceof DataType)
         {
            dataType = (DataType) selection;
         }
      }
      
      boolean isValid = true;
      
      IModelElement modelElement = (IModelElement) getModelElement();
      
      if (AttributeUtil.getBooleanValue((IExtensibleElement) modelElement, PredefinedConstants.BINDING_ATT))
      {
         if (!ScopeUtils.isValidScopeData((IExtensibleElement) modelElement, dataPath, dataType))
         {            
            isValid = false;
         }
      }

      if (isValid)
      {
         setErrorMessage(null);
         setValid(true);
      }
      else
      {
         setErrorMessage(Diagram_Messages.ERROR_MSG_SELECTED_DATA + "/" +Diagram_Messages.ERROR_MSG_DATA_PATH_MUST_RESOLVE_TO_A_STRING_VALUE); //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$
         setValid(false);
      }            
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      createDataGroup(composite);
      return composite;
   }

   private void createDataGroup(Composite composite)
   {
      dynamicBindingCheckBox = FormBuilder.createCheckBox(composite, Diagram_Messages.BOX_SUBPROCESS_IS_RESOLVED_AT_RUNTIME, 2);
      dynamicBindingCheckBox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
            // ignore
         }

         public void widgetSelected(SelectionEvent e)
         {
            boolean selection = dynamicBindingCheckBox.getSelection();
            dataGroup.setVisible(selection);
            AttributeUtil.setBooleanAttribute((IExtensibleElement) getModelElement(),
                  PredefinedConstants.BINDING_ATT, selection);
            
            validate(null);
         }
      });
      FormBuilder.createLabel(composite, " ", 2); //$NON-NLS-1$
      dataGroup = FormBuilder.createGroup(composite, Diagram_Messages.LBL_IMP_MD_ID, 2, 2);
      GridData gridDataGroup = new GridData(SWT.FILL, SWT.NONE, false, false);
      gridDataGroup.horizontalSpan = 2;
      dataGroup.setLayoutData(gridDataGroup);

      dataLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
            Diagram_Messages.LB_Data);
      dataText = new ComboViewer(FormBuilder.createCombo(dataGroup));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));

      dataText.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            if(event.getSelection() instanceof StructuredSelection)
            {
               Object element = ((StructuredSelection) event.getSelection()).getFirstElement();
               validate(element);               
            }
         }         
      });
      
      dataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
            Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), dataGroup,
            Diagram_Messages.LB_DataPath);
      dataPathBrowser.getMethodText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            if (e.getSource() instanceof Text)
            {
               Text text = (Text) e.getSource();
               validate(text.getText());
            }            
         }
      });
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }
}