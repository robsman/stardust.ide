/*******************************************************************************
 * Copyright (c) 2011, 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
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
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
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
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class OrganizationRuntimeBindingPropertyPage
      extends AbstractModelElementPropertyPage
{
   private ComboViewer dataText;
   private AccessPathBrowserComposite dataPathBrowser;
   private LabelWithStatus dataLabel;
   private LabelWithStatus dataPathLabel;
   private Group dataGroup;
   private Button departmentsCheckBox;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager binding = getWidgetBindingManager();

      ModelType model = ModelUtils.findContainingModel(element);

      boolean hasDepartments = AttributeUtil.getBooleanValue((IExtensibleElement) element,
            PredefinedConstants.BINDING_ATT);
      departmentsCheckBox.setSelection(hasDepartments);

      binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(model, model.getData()),
            new Data2DataPathWidgetAdapter2(dataText, dataPathBrowser,
                  DirectionType.IN_LITERAL));

      binding.bind(new LabeledViewer(dataText, dataLabel),
            (IExtensibleElement) element, PredefinedConstants.BINDING_DATA_ID_ATT, model.getData());
      binding.bind(new LabeledText(dataPathBrowser.getMethodText(), dataPathLabel),
            (IExtensibleElement) element, PredefinedConstants.BINDING_DATA_PATH_ATT);

      dataGroup.setVisible(hasDepartments);

      validate(null);
   }

   private void validate(Object selection)
   {
      String dataPath = null;
      DataType dataType = null;

      if(selection != null)
      {
         if(selection instanceof String)
         {
            dataPath = (String) selection;
         }
         else if(selection instanceof DataType)
         {
            dataType = (DataType) selection;
         }
      }

      boolean isValid = true;

      IModelElement modelElement = (IModelElement) getModelElement();

      if(AttributeUtil.getBooleanValue((IExtensibleElement) modelElement, PredefinedConstants.BINDING_ATT))
      {
         if(!ScopeUtils.isValidScopeData((OrganizationType) modelElement, dataPath, dataType))
         {
            isValid = false;
         }
      }

      if(isValid)
      {
         setErrorMessage(null);
         setValid(true);
      }
      else
      {
         setErrorMessage(Validation_Messages.ERR_ORGANIZATION_InvalidScopeData);
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
      departmentsCheckBox = FormBuilder.createCheckBox(composite, Diagram_Messages.BOX_ORGANIZATION_SUPPORTS_DEPARTMENT, 2);
      departmentsCheckBox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
            // ignore
         }

         public void widgetSelected(SelectionEvent e)
         {
            boolean selection = departmentsCheckBox.getSelection();
            dataGroup.setVisible(selection);
            AttributeUtil.setBooleanAttribute((IExtensibleElement) getModelElement(),
                  PredefinedConstants.BINDING_ATT, selection);
            if (selection)
            {
               ModelType modelType = (ModelType) getModelElement().eContainer();
               AttributeUtil.setReference((IExtensibleElement) getModelElement(),
                     PredefinedConstants.BINDING_DATA_ID_ATT,
                     (EObject) modelType.getData().get(0));
            }
            else
            {
               AttributeUtil.setReference((IExtensibleElement) getModelElement(),
                     PredefinedConstants.BINDING_DATA_ID_ATT, null);
            }
            if (selection)
            {
               ModelType modelType = (ModelType) getModelElement().eContainer();
               AttributeUtil.setReference((IExtensibleElement) getModelElement(),
                     PredefinedConstants.BINDING_DATA_ID_ATT,
                     (EObject) modelType.getData().get(0));
            }
            else
            {
               AttributeUtil.setReference((IExtensibleElement) getModelElement(),
                     PredefinedConstants.BINDING_DATA_ID_ATT, null);
            }
            validate(null);
         }
      });
      FormBuilder.createLabel(composite, " ", 2); //$NON-NLS-1$
      dataGroup = FormBuilder.createGroup(composite, Diagram_Messages.LB_Department_Oid, 2, 2);
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
            if (event.getSelection() instanceof StructuredSelection)
            {
               Object element = ((StructuredSelection) event.getSelection()).getFirstElement();
               if (element != null)
               {
                  AttributeType attributeType = AttributeUtil.getAttribute(
                        (IExtensibleElement) getModelElement(),
                        PredefinedConstants.BINDING_DATA_ID_ATT);
                  if ( !AttributeUtil.isReference(attributeType))
                  {
                     AttributeUtil.setReference((IExtensibleElement) getModelElement(),
                           PredefinedConstants.BINDING_DATA_ID_ATT, (EObject) element);
                  }
               }
               validate(element);
            }
         }
      });

      dataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
            Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), dataGroup,
            Diagram_Messages.LB_DataPath);
      dataPathBrowser.getMethodText().addModifyListener(new ModifyListener() {
         public void modifyText(ModifyEvent e)
         {
            if(e.getSource() instanceof Text)
            {
               Text text = (Text) e.getSource();
               if (!StringUtils.isEmpty(text.getText()))
               {
                  validate(text.getText());
               }
            }
         }
      });
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }
}