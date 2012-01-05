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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.ModeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.tree.IdentifiableModelElementTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.spi.providers.data.java.Type;

public class FormalParameterPropertyPage extends AbstractModelElementPropertyPage
{

   private XpdlFactory xpdlFactory = XpdlFactory.eINSTANCE;

   private LabeledText idText;

   private LabeledText nameText;

   private LabeledViewer directionCombo;

   private Button descriptorButton;

   private LabeledViewer dataCombo;

   private LabelWithStatus dataLabel;

   private LabelWithStatus dataPathLabel;

   private AccessPathBrowserComposite dataPathBrowser;

   private Button[] buttons;

   private Button autoIdButton;

   private boolean isEditable = true;

   private EList<DataType> datas;

   private Map<String, TypeType> typeMapping;

   private static final Type[] TYPES = {
         Type.Calendar, Type.String, Type.Timestamp, Type.Boolean, Type.Byte, Type.Char,
         Type.Double, Type.Float, Type.Integer, Type.Long, Type.Short};

   List<Type> primitiveTypes = Arrays.asList(TYPES);

   List<Object> dataTypes = new ArrayList<Object>();

   private FormalParameterType parameterType;

   private ProcessDefinitionType providingProcess;

   private boolean readOnly;

   private LabelWithStatus dataTypeLabel;

   private LabeledViewer dataTypeCombo;

   private DataFilter dataFilter;

   private ProcessDefinitionType implementingProcess;

   private List typeFilters = new ArrayList<ViewerFilter>();

   private SelectionListener autoIdListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {}

      public void widgetSelected(SelectionEvent e)
      {
         boolean selection = ((Button) e.widget).getSelection();
         if (selection)
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
      ISelection sel = directionCombo.getViewer().getSelection();
      if (sel.isEmpty())
      {
         directionCombo.getViewer().setSelection(
               new StructuredSelection(DirectionType.IN_LITERAL));
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

   private LabeledViewer categoryCombo;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      implementingProcess = getProcess();
      nameText.getText().removeModifyListener(listener);
      typeMapping = CollectionUtils.newMap();
      typeMapping.put(Type.String.getId(), TypeType.STRING_LITERAL);
      typeMapping.put(Type.Integer.getId(), TypeType.INTEGER_LITERAL);
      typeMapping.put(Type.Boolean.getId(), TypeType.BOOLEAN_LITERAL);
      typeMapping.put(Type.Calendar.getId(), TypeType.DATETIME_LITERAL);
      typeMapping.put(Type.Timestamp.getId(), TypeType.DATETIME_LITERAL);
      typeMapping.put(Type.Long.getId(), TypeType.INTEGER_LITERAL);
      typeMapping.put(Type.Double.getId(), TypeType.FLOAT_LITERAL);
      typeMapping.put(Type.Short.getId(), TypeType.INTEGER_LITERAL);
      typeMapping.put(Type.Byte.getId(), TypeType.INTEGER_LITERAL);

      WidgetBindingManager binding = getWidgetBindingManager();
      parameterType = (FormalParameterType) element;
      nameText.getText().setText(parameterType.getName());
      idText.getText().setText(parameterType.getId());
      providingProcess = (ProcessDefinitionType) parameterType.eContainer().eContainer();

      readOnly = !providingProcess.equals(implementingProcess);

      ModelType referencedModelType = ModelUtils.findContainingModel(providingProcess);
      ModelType propertyModelType = ModelUtils.findContainingModel(implementingProcess);

      datas = propertyModelType.getData();

      for (Iterator<TypeDeclarationType> i = referencedModelType.getTypeDeclarations()
            .getTypeDeclaration().iterator(); i.hasNext();)
      {
         TypeDeclarationType typeDec = i.next();
         dataTypes.add(typeDec);
      }
      dataTypes.add(StructuredTypeUtils.getResourceTypeDeclaration());
      dataTypes.addAll(primitiveTypes);
      DataType selectedData = getSelectedData();
      Object selectedType = getInterfaceType();
      dataFilter.setFilterType(selectedType);
      dataFilter.setReferencedModel(referencedModelType);

      categoryCombo.getViewer().setInput(typeFilters);

      String selectedCategory = parameterType.getDataType().getCarnotType();
      if (selectedCategory == null)
      {
         if (parameterType.getDataType().getBasicType() != null)
         {
            selectedCategory = "primitive"; //$NON-NLS-1$
         }
         if (parameterType.getDataType().getDeclaredType() != null)
         {
            selectedCategory = "struct"; //$NON-NLS-1$
         }
      }
      ViewerFilter selectedFilter = getSelectedFilter(selectedCategory);

      if (selectedFilter != null)
      {
         categoryCombo.getViewer().setSelection(new StructuredSelection(selectedFilter));
         dataFilter.setCategoryFilter(selectedFilter);
         dataTypeCombo.getViewer().setFilters(new ViewerFilter[] {selectedFilter});
         dataTypeCombo.getViewer().setInput(dataTypes);
      }

      dataCombo.getViewer().setInput(datas);

      if (selectedType != null)
      {
         dataTypeCombo.getViewer().setSelection(new StructuredSelection(selectedType));
      }
      if (selectedData != null)
      {
         dataCombo.getViewer().setSelection(new StructuredSelection(selectedData));
      }

      ModeType mode = parameterType.getMode();
      if (mode != null)
      {
         if (mode.equals(ModeType.IN))
         {
            ((Combo) directionCombo.getViewer().getControl()).select(0);
         }
         if (mode.equals(ModeType.OUT))
         {
            ((Combo) directionCombo.getViewer().getControl()).select(1);
         }
         if (mode.equals(ModeType.INOUT))
         {
            ((Combo) directionCombo.getViewer().getControl()).select(2);
         }
      }
      nameText.getText().removeModifyListener(listener);
      binding.bind(idText, parameterType, XpdlPackage.eINSTANCE
            .getFormalParameterType_Id());
      binding.bind(nameText, parameterType, XpdlPackage.eINSTANCE
            .getFormalParameterType_Name());
      idText.getText().setEditable(false);
      nameText.getText().addModifyListener(listener);
      enableControls();
      validatePage();
   }

   private ViewerFilter getSelectedFilter(String category)
   {
      for (Iterator<TypeFilter> i = typeFilters.iterator(); i.hasNext();)
      {
         TypeFilter filter = i.next();
         if (filter.getName().equals(category))
         {
            return filter;
         }
      }
      return null;
   }

   protected void updateApplyButton()
   {
      super.updateApplyButton();
      validatePage();
   }

   private Object getType(DataType selectedData)
   {
      if (selectedData == null)
      {
         return null;
      }
      for (Iterator<Object> i = dataTypes.iterator(); i.hasNext();)
      {
         Object o = i.next();
         String typeName = AttributeUtil.getAttributeValue(selectedData.getAttribute(),
               PredefinedConstants.TYPE_ATT);
         if (typeName == null)
         {
            typeName = AttributeUtil.getAttributeValue(selectedData.getAttribute(),
                  "carnot:engine:dataType"); //$NON-NLS-1$
         }
         if (typeName == null && selectedData.getType().getId().startsWith("dms")) //$NON-NLS-1$
         {
            typeName = AttributeUtil.getAttributeValue(selectedData.getAttribute(),
                  "carnot:engine:dms:resourceMetadataSchema"); //$NON-NLS-1$
            if (typeName == null)
            {
               typeName = "ResourceProperty"; //$NON-NLS-1$
            }
         }
         if (o instanceof Type)
         {
            if (((Type) o).getId().equalsIgnoreCase(typeName))
            {
               return o;
            }
         }
         if (o instanceof TypeDeclarationType)
         {
            if (((TypeDeclarationType) o).getId().equalsIgnoreCase(typeName))
            {
               return o;
            }
         }
      }
      return null;
   }

   private void enableControls()
   {
      autoIdButton.setEnabled(!readOnly && enablePage);
      idText.getText().setEnabled(!readOnly && enablePage);
      nameText.getText().setEnabled(!readOnly && enablePage);
      directionCombo.getViewer().getControl().setEnabled(!readOnly && enablePage);
      dataTypeCombo.getViewer().getControl().setEnabled(!readOnly && enablePage);
      categoryCombo.getViewer().getControl().setEnabled(!readOnly && enablePage);
      for (int i = 0; i < buttons.length; i++)
      {
         ((Control) buttons[i]).setEnabled(!readOnly && enablePage);
      }
   }

   private ProcessDefinitionType getProcess()
   {
      ProcessDefinitionType process = null;
      ModelElementPropertyDialog dialog = (ModelElementPropertyDialog) this
            .getContainer();
      if (dialog.getElement() instanceof IdentifiableModelElementTreeEditPart)
      {
         process = (ProcessDefinitionType) Reflect.getFieldValue(dialog.getElement(),
               "model"); //$NON-NLS-1$
      }
      else
      {
         process = (ProcessDefinitionType) getModelElement(dialog.getElement());
      }
      return process;
   }

   private DataType getSelectedData()
   {
      FormalParameterMappingsType mappingsType = implementingProcess
            .getFormalParameterMappings();
      if (mappingsType.getMapping().isEmpty())
      {
         return null;
      }
      mappingsType.getMappedData((FormalParameterType) parameterType);
      DataType dt = implementingProcess.getFormalParameterMappings().getMappedData(
            (FormalParameterType) parameterType);
      return dt;
   }

   private Object getInterfaceType()
   {
      FormalParameterMappingsType mappingsType = providingProcess
            .getFormalParameterMappings();
      if (mappingsType.getMapping().isEmpty())
      {
         return null;
      }
      mappingsType.getMappedData((FormalParameterType) parameterType);
      DataType dt = providingProcess.getFormalParameterMappings().getMappedData(
            (FormalParameterType) parameterType);
      return this.getType(dt);
   }

   public boolean isEditable()
   {
      return isEditable;
   }

   private void setEditable(boolean enabled_)
   {

   }

   private void updateDescriptorState()
   {

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
            for (int i = 0; i < buttons.length; i++)
            {
               ((Control) buttons[i]).setEnabled(!readOnly);
            }

         }
      }
      super.setVisible(visible);
      validatePage();
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      if (manager != null)
      {
         buttons = manager.createButtons(parent);
         for (int i = 0; i < buttons.length; i++)
         {
            ((Control) buttons[i]).setEnabled(!readOnly);
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      typeFilters = getTypeFilters();
      implementingProcess = getProcess();
      providingProcess = (ProcessDefinitionType) ((FormalParameterType) this
            .getModelElement()).eContainer().eContainer();
      readOnly = !providingProcess.equals(implementingProcess);

      Composite composite = FormBuilder.createComposite(parent, 2);

      nameText = FormBuilder.createLabeledTextLeftAlignedStatus(composite,
    		  Diagram_Messages.LB_Name);
      nameText.getText().setEnabled(enablePage);
      nameText.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            validatePage();
         }
      });

      idText = FormBuilder.createLabeledTextLeftAlignedStatus(composite,
    		  Diagram_Messages.LB_ID);
      idText.getText().setEditable(true);
      idText.getText().setEnabled(enablePage);
      idText.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            validatePage();
         }
      });

      autoIdButton = FormBuilder.createCheckBox(composite,
            Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION);
      autoIdButton.setSelection(autoIdButtonValue);
      autoIdButton.setEnabled(enablePage);
      if (autoIdButtonValue)
      {
         idText.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);

      // FormBuilder.createLabel(composite, Properties_Messages.LB_Direction);
      List<DirectionType> directions = new ArrayList<DirectionType>();
      directions.add(DirectionType.IN_LITERAL);
      directions.add(DirectionType.OUT_LITERAL);
      directions.add(DirectionType.INOUT_LITERAL);

      directionCombo = FormBuilder.createComboViewer(composite,
            Diagram_Messages.LB_Direction, directions);

      directionCombo.getViewer().getControl().setEnabled(enablePage);

      ((Combo) directionCombo.getViewer().getControl())
            .addSelectionListener(new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  StructuredSelection selection = (StructuredSelection) directionCombo
                        .getViewer().getSelection();
                  if (selection.getFirstElement().equals(DirectionType.IN_LITERAL))
                  {
                     parameterType.setMode(ModeType.IN);
                  }
                  if (selection.getFirstElement().equals(DirectionType.OUT_LITERAL))
                  {
                     parameterType.setMode(ModeType.OUT);
                  }
                  if (selection.getFirstElement().equals(DirectionType.INOUT_LITERAL))
                  {
                     parameterType.setMode(ModeType.INOUT);
                  }
                  validatePage();
               }
            });

      categoryCombo = FormBuilder.createComboViewer(composite, "Category", //$NON-NLS-1$
            new ArrayList<Object>());
      categoryCombo.getViewer().getControl().setEnabled(enablePage);
      categoryCombo.getViewer().setContentProvider(new ArrayContentProvider());
      ((Combo) categoryCombo.getViewer().getControl())
            .addSelectionListener(new SelectionAdapter()
            {
               public void widgetDefaultSelected(SelectionEvent e)
               {
                  super.widgetDefaultSelected(e);
               }

               public void widgetSelected(SelectionEvent e)
               {
                  IStructuredSelection selection = (IStructuredSelection) categoryCombo
                        .getViewer().getSelection();
                  ViewerFilter filter = (ViewerFilter) selection.getFirstElement();

                  dataTypeCombo.getViewer().setFilters(new ViewerFilter[] {filter});
                  dataFilter.setCategoryFilter(filter);
                  dataTypeCombo.getViewer().setInput(dataTypes);
                  validatePage();
               }
            });

      dataTypeCombo = FormBuilder.createComboViewer(composite, "Data Type", //$NON-NLS-1$
            new ArrayList<Object>());
      dataTypeCombo.getViewer().getControl().setEnabled(enablePage);
      dataTypeCombo.getViewer().setSorter(new ViewerSorter());
      dataTypeCombo.getViewer().setContentProvider(new ArrayContentProvider());
      dataTypeCombo.getViewer().setFilters(new ViewerFilter[] {new EmptyFilter("")}); //$NON-NLS-1$
      dataTypeCombo.getViewer().setLabelProvider(new EObjectLabelProvider(getEditor()));
      ((Combo) dataTypeCombo.getViewer().getControl())
            .addSelectionListener(new SelectionAdapter()
            {

               public void widgetDefaultSelected(SelectionEvent e)
               {
                  super.widgetDefaultSelected(e);
               }

               public void widgetSelected(SelectionEvent e)
               {
                  Object dataType = ((StructuredSelection) dataTypeCombo.getViewer()
                        .getSelection()).getFirstElement();
                  dataFilter.setFilterType(dataType);
                  dataCombo.getViewer().setInput(datas);
                  dataCombo.getViewer().refresh(true);
                  validatePage();
               }

            });

      dataCombo = FormBuilder.createComboViewer(composite, Diagram_Messages.LB_Data,
            new ArrayList<Object>());
      dataCombo.getViewer().getControl().setEnabled(enablePage);
      dataCombo.getViewer().setSorter(new ViewerSorter());
      dataFilter = new DataFilter();
      dataCombo.getViewer().setFilters(new ViewerFilter[] {dataFilter});
      dataCombo.getViewer().setContentProvider(new ArrayContentProvider());
      dataCombo.getViewer().setLabelProvider(new EObjectLabelProvider(getEditor()));
      ((Combo) dataCombo.getViewer().getControl())
            .addSelectionListener(new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  DataType data = (DataType) ((StructuredSelection) dataCombo.getViewer()
                        .getSelection()).getFirstElement();
                  DataTypeType dataType = xpdlFactory.createDataTypeType();
                  String typeId = data.getType().getId();

                  if (PredefinedConstants.PRIMITIVE_DATA.equals(typeId))
                  {
                     BasicTypeType basicType = xpdlFactory.createBasicTypeType();
                     String primitiveType = AttributeUtil.getAttributeValue(data,
                           PredefinedConstants.TYPE_ATT);
                     TypeType tt = typeMapping.get(primitiveType);
                     if (tt != null)
                     {
                        basicType.setType(tt);
                     }
                     dataType.setBasicType(basicType);
                  }
                  else if (PredefinedConstants.STRUCTURED_DATA.equals(typeId))
                  {
                     DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
                     if (data.getExternalReference() != null)
                     {
                        ExternalReferenceType extRef = data.getExternalReference();
                        declaredType.setId(extRef.getXref());
                     }
                     else
                     {
                        declaredType.setId(AttributeUtil.getAttributeValue(data,
                              StructuredDataConstants.TYPE_DECLARATION_ATT));
                     }
                     dataType.setDeclaredType(declaredType);
                  }
                  else if ("dmsDocument".equals(typeId) //$NON-NLS-1$
                        || "dmsDocumentList".equals(typeId)) //$NON-NLS-1$
                  {
                     DeclaredTypeType declaredType = xpdlFactory.createDeclaredTypeType();
                     String declTypeId = AttributeUtil.getAttributeValue(data,
                           "carnot:engine:dms:resourceMetadataSchema"); //$NON-NLS-1$
                     if (declTypeId == null)
                     {
                        declTypeId = "ResourceProperty"; //$NON-NLS-1$
                     }
                     declaredType.setId(declTypeId);
                     dataType.setDeclaredType(declaredType);
                  }

                  parameterType.setDataType(dataType);
                  dataType.setCarnotType(typeId);

                  if (!implementingProcess.equals(providingProcess))
                  {
                     FormalParameterType implementingType = implementingProcess
                           .getFormalParameters().getFormalParameter(
                                 parameterType.getId());
                     if (implementingType == null)
                     {
                        implementingType = ModelUtils
                              .cloneFormalParameterType(parameterType, null);
                        implementingProcess.getFormalParameters().addFormalParameter(
                              implementingType);
                     }
                     else
                     {
                        if (ModelUtils
                              .haveDifferentTypes(parameterType, implementingType))
                        {
                           implementingType.setDataType((DataTypeType) EcoreUtil
                                 .copy(parameterType.getDataType()));
                        }
                     }
                  }
                  implementingProcess.getFormalParameterMappings().setMappedData(
                        parameterType, data);
                  validatePage();
               }
            });
      return composite;
   }

   private List<ViewerFilter> getTypeFilters()
   {
      List<ViewerFilter> result = new ArrayList<ViewerFilter>();
      result.add(new PrimitiveTypesFilter("primitive")); //$NON-NLS-1$
      result.add(new StructuredTypesFilter("struct")); //$NON-NLS-1$
      result.add(new DocumentTypesFilter("dmsDocument")); //$NON-NLS-1$
      result.add(new DocumentListTypesFilter("dmsDocumentList")); //$NON-NLS-1$
      return result;
   }

   private void validatePage()
   {
      boolean enable = true;

      if (directionCombo.getViewer().getSelection().isEmpty())
      {
         directionCombo.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         directionCombo.getLabel().setToolTipText(Diagram_Messages.LBL_PLEASE_PROVIDE_A_DIRECTION);
         enable = false;
      }
      else
      {
         directionCombo.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      if (categoryCombo.getViewer().getSelection().isEmpty())
      {
         categoryCombo.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         categoryCombo.getLabel().setToolTipText(Diagram_Messages.LBL_PLEASE_PROVIDE_A_CATEGORY);
         enable = false;
      }
      else
      {
         categoryCombo.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      if (dataTypeCombo.getViewer().getSelection().isEmpty())
      {
         dataTypeCombo.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         dataTypeCombo.getLabel().setToolTipText(Diagram_Messages.LBL_TXT_PLEASE_PROVIDE_A_DATATYPE);
         enable = false;
      }
      else
      {
         dataTypeCombo.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      if (dataCombo.getViewer().getSelection().isEmpty())
      {
         dataCombo.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         dataCombo.getLabel().setToolTipText(Diagram_Messages.LBL_TXT_PLEASE_PROVIDE_A_DATA);
         enable = false;
      }
      else
      {
         dataCombo.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      if (StringUtils.isEmpty(nameText.getText().getText()))
      {
         enable = false;
         nameText.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         nameText.getLabel().setToolTipText(Diagram_Messages.LBL_TXT_PLEASE_PROVIDE_A_NAME);
      }
      else
      {
         nameText.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      if (StringUtils.isEmpty(idText.getText().getText()))
      {
         enable = false;
         idText.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         idText.getLabel().setToolTipText(Diagram_Messages.LBL_TXT_PLEASE_PROVIDE_A_ID);
      }
      else
      {
         idText.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      }
      if (!readOnly)
      {
         enablePage(enable);
      }
   }

   private void enablePage(boolean enabled)
   {
      setValid(enabled);
      getApplyButton().setEnabled(enabled);
      buttons[0].setEnabled(enabled);
      buttons[2].setEnabled(enabled);
      buttons[3].setEnabled(enabled);
      getApplyButton().setEnabled(enabled);
   }

   // Typefilters

   public class TypeFilter extends ViewerFilter
   {
      private String name;

      public TypeFilter(String name)
      {
         super();
         this.name = name;
      }

      public String getName()
      {
         return name;
      }

      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         return false;
      }

   }

   public class StructuredTypesFilter extends TypeFilter
   {

      public StructuredTypesFilter(String name)
      {
         super(name);
      }

      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         if (element instanceof TypeDeclarationType)
         {
            TypeDeclarationType decl = (TypeDeclarationType) element;
            return (!decl.getName().equals("<default>")); //$NON-NLS-1$
         }
         return false;
      }

      public String toString()
      {
         return Diagram_Messages.TXT_STRUCTURED_TYPE;
      }

   }

   public class PrimitiveTypesFilter extends TypeFilter
   {

      public PrimitiveTypesFilter(String name)
      {
         super(name);
      }

      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         return element instanceof Type;
      }

      public String toString()
      {
         return Diagram_Messages.TXT_PRIMITIVE_DATA;
      }

   }

   public class DocumentTypesFilter extends TypeFilter
   {

      public DocumentTypesFilter(String name)
      {
         super(name);
      }

      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         return element instanceof TypeDeclarationType;
      }

      public String toString()
      {
         return Diagram_Messages.TXT_DOCUMENT;
      }

   }

   public class DocumentListTypesFilter extends TypeFilter
   {

      public DocumentListTypesFilter(String name)
      {
         super(name);
      }

      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         return element instanceof TypeDeclarationType;
      }

      public String toString()
      {
         return Diagram_Messages.TXT_DOCUMENT_LIST;
      }

   }

   public class EmptyFilter extends TypeFilter
   {

      public EmptyFilter(String name)
      {
         super(name);
      }

      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
         return false;
      }

   }

}