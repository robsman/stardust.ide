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
package org.eclipse.stardust.modeling.integration.dms.data;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.BindingManager;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledWidget;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.ReferencedModelSorter;
import org.eclipse.stardust.modeling.core.utils.ExtensibleElementAdapter;
import org.eclipse.stardust.modeling.core.utils.ExtensibleElementValueAdapter;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.data.structured.StructContentProvider;
import org.eclipse.stardust.modeling.data.structured.StructLabelProvider;
import org.eclipse.stardust.modeling.integration.dms.DMS_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * @author fherinean
 * @version $Revision$
 */

public class DmsResourcePropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private static final int[] widths = {100, 100, 100};
   private static final TypeDeclarationType DEFAULT_METADATA_SCHEMA =
      DmsTypeUtils.getResourceTypeDeclaration();

   private ListViewer declaredTypesViewer;
   private LabeledWidget wrapper;
   private DataType dataType;
   protected TypeDeclarationType selectedType;
   private Button groupingCheckbox;
   private ReferencedModelSorter refSorter = new ReferencedModelSorter();
   private StructLabelProvider typesViewerLabelProvider = new StructLabelProvider();

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      final ModelType model = ModelUtils.findContainingModel(node);
      dataType = ((DataType) node);
      final List<TypeDeclarationType> types = CollectionUtils.newList();
      types.add(DEFAULT_METADATA_SCHEMA);
      types.addAll(TypeDeclarationUtils.filterTypeDeclarations(this
            .collectTypeDeclarations(model), TypeDeclarationUtils.COMPLEX_TYPE));

      declaredTypesViewer.setSorter(refSorter);
      declaredTypesViewer.setInput(types);
      typesViewerLabelProvider.setModel(model);

      wBndMgr.bind(wrapper, BindingManager.createWidgetAdapter(declaredTypesViewer),
            (IExtensibleElement) node, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT,
            new ExtensibleElementValueAdapter()
            {
               public Object fromModel(ExtensibleElementAdapter binding, Object value)
               {
                  Object typeDec = null;
                  String uri = AttributeUtil.getAttributeValue(dataType,
                        IConnectionManager.URI_ATTRIBUTE_NAME);
                  if (value instanceof String)
                  {
                     if (selectedType != null) {
                        return selectedType;
                     }
                     if (uri != null)
                     {
                        Object desc = model.getConnectionManager().find(uri);
                        typeDec = Reflect.getFieldValue(desc, "eObject"); //$NON-NLS-1$
                     }
                     else
                     {
                        typeDec = ModelUtils.getTypeDeclaration(model, (String) value);
                     }

                     return typeDec;
                  }
                  else if (value == null || DEFAULT_METADATA_SCHEMA.getId().equals(value))
                  {
                     return DEFAULT_METADATA_SCHEMA;
                  }

                  return super.fromModel(binding, value);
               }

               public Object toModel(ExtensibleElementAdapter binding, Object value)
               {
                  if (DEFAULT_METADATA_SCHEMA == value)
                  {
                     return null;
                  }
                  else if (value instanceof TypeDeclarationType)
                  {
                     return ((TypeDeclarationType) value).getId();
                  }
                  return super.toModel(binding, value);
               }
            });
      validate();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      LabelWithStatus typesLabel = FormBuilder.createLabelWithLeftAlignedStatus(composite,
            org.eclipse.stardust.modeling.data.structured.Structured_Messages.DataStructPropertyPage_DeclaredTypesLabel);
      FormBuilder.createLabel(composite,
            org.eclipse.stardust.modeling.data.structured.Structured_Messages.DataStructPropertyPage_DetailsLabel);

      org.eclipse.swt.widgets.List declaredTypesList = FormBuilder.createList(composite);
      declaredTypesList.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      declaredTypesViewer = new ListViewer(declaredTypesList);
      declaredTypesViewer.setContentProvider(new ArrayContentProvider());
      declaredTypesViewer.setLabelProvider(typesViewerLabelProvider);
      /*declaredTypesViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            TypeDeclarationType type = (TypeDeclarationType) element;
            String name = type.getName();
            return name == null ? type.getId() : name;
         }
      });*/
      wrapper = new LabeledWidget(declaredTypesList, typesLabel);

      final Tree tree = FormBuilder.createTree(composite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER);
      tree.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      tree.setHeaderVisible(true);
      final TreeViewer viewer = new TreeViewer(tree);
      for (int i = 0; i < StructLabelProvider.COMPLEX_TYPE_COLUMNS.length; i++)
      {
         TreeColumn column = new TreeColumn(tree, SWT.LEFT);
         column.setText(StructLabelProvider.COMPLEX_TYPE_COLUMNS[i]);
         column.setWidth(widths[i]);
      }
      viewer.setUseHashlookup(true);
      viewer.setContentProvider(new StructContentProvider(false));
      viewer.setLabelProvider(new StructLabelProvider());
      viewer.setColumnProperties(StructLabelProvider.COMPLEX_TYPE_COLUMNS);

      declaredTypesViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (!selection.isEmpty())
            {
               selectedType = (TypeDeclarationType) selection.getFirstElement();
               viewer.setInput(selectedType);

               if (TypeDeclarationUtils.getType(selectedType) == TypeDeclarationUtils.COMPLEX_TYPE)
               {
                  updateTableColumns(tree, StructLabelProvider.COMPLEX_TYPE_COLUMNS[0], true);
               }
               else if (TypeDeclarationUtils.getType(selectedType) == TypeDeclarationUtils.SIMPLE_TYPE)
               {
                  updateTableColumns(tree, StructLabelProvider.SIMPLE_TYPE_COLUMNS[0], false);
               }
               setMessage(null);
               setValid(true);
            }
         }

         private void updateTableColumns(final Tree tree, String name, boolean visible)
         {
            TreeColumn first = tree.getColumn(0);
            first.setText(name);
            for (int i = 1; i < StructLabelProvider.COMPLEX_TYPE_COLUMNS.length; i++)
            {
               TreeColumn column = tree.getColumn(i);
               if (visible)
               {
                  column.setWidth(widths[i]);
               }
               else
               {
                  widths[i] = column.getWidth();
                  column.setWidth(0);
               }
            }
         }
      });

      groupingCheckbox = FormBuilder.createCheckBox(composite, DMS_Messages.DMS_PropertyPage_ShowTypes);
      groupingCheckbox.addSelectionListener(new SelectionListener(){

        public void widgetDefaultSelected(SelectionEvent e) {

        }

        public void widgetSelected(SelectionEvent e) {
            refSorter.setGrouped(groupingCheckbox.getSelection());
            typesViewerLabelProvider.setShowGroupInfo(groupingCheckbox.getSelection());
            declaredTypesViewer.refresh(true);
        }

      });

      return composite;
   }

   private void validate()
   {
      boolean isValid = true;
      IModelElement node = (IModelElement) getModelElement();
      String structuredDataId = AttributeUtil.getAttributeValue(
            (IExtensibleElement) node, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
      if (AttributeUtil
            .getAttributeValue(dataType, IConnectionManager.URI_ATTRIBUTE_NAME) == null)
      {
         if (!StringUtils.isEmpty(structuredDataId))
         {
            ModelType model = ModelUtils.findContainingModel(node);
            TypeDeclarationsType declarations = model.getTypeDeclarations();
            TypeDeclarationType declaration = declarations
                  .getTypeDeclaration(structuredDataId);
            if (declaration == null)
            {
               isValid = false;
            }
         }
      }
      else
      {
         isValid = true;
      }
      if (!isValid)
      {
         String message = DMS_Messages.DataValidator_InvalidType + structuredDataId;
         setMessage(message, ERROR);
         setValid(false);
      }
      else
      {
         setMessage(null);
         setValid(true);
      }
   }

   public void apply()
   {
      ExternalReferenceType ref = dataType.getExternalReference();
      ModelType externalModel = ModelUtils.findContainingModel(selectedType);
      ModelType model = ModelUtils.findContainingModel(dataType);
      if (externalModel != null && !externalModel.equals(model))
      {
         if (AttributeUtil.getAttributeValue(dataType,
               IConnectionManager.URI_ATTRIBUTE_NAME) == null)
         {
            ref = XpdlFactory.eINSTANCE.createExternalReferenceType();
            dataType.setExternalReference(ref);
         }
         ref.setLocation(externalModel.getId());
         ref.setXref(selectedType.getId());
         AttributeUtil.setReference(dataType,
               StructuredDataConstants.TYPE_DECLARATION_ATT, null);
         String uri = ExtendedAttributeUtil.getAttributeValue(selectedType,
               IConnectionManager.URI_ATTRIBUTE_NAME);
         AttributeUtil.setAttribute(dataType, IConnectionManager.URI_ATTRIBUTE_NAME, uri);
      }
      else
      {
         dataType.setExternalReference(null);
         AttributeUtil
               .setAttribute(dataType, IConnectionManager.URI_ATTRIBUTE_NAME, null);
      }
      if (selectedType.getId().equals(
            StructuredTypeUtils.getResourceTypeDeclaration().getId()))
      {
         AttributeUtil.setAttribute(dataType, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT,
               null);
      }
      else
      {
         AttributeUtil.setAttribute(dataType, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT,
               selectedType.getId());
         AttributeUtil.setReference(dataType,
               DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, selectedType);
      }
      super.apply();
   }


   private List<TypeDeclarationType> collectTypeDeclarations(ModelType model)
   {
      List<TypeDeclarationType> declarations = CollectionUtils.newList();
      TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
      if (typeDeclarations != null)
      {
         declarations.addAll(typeDeclarations.getTypeDeclaration());
      }
      ExternalPackages packages = model.getExternalPackages();
      if (packages != null)
      {
         for (ExternalPackage pkg : packages.getExternalPackage())
         {
            String uri = ExtendedAttributeUtil.getAttributeValue(pkg, IConnectionManager.URI_ATTRIBUTE_NAME);
            if (!StringUtils.isEmpty(uri))
            {
               IConnectionManager manager = model.getConnectionManager();
               if (manager != null)
               {
                  EObject externalModel = manager.find(uri);
                  if (externalModel instanceof IObjectReference)
                  {
                     externalModel = ((IObjectReference) externalModel).getEObject();
                  }
                  if (externalModel instanceof ModelType)
                  {
                     TypeDeclarationsType externalDeclarations = ((ModelType) externalModel).getTypeDeclarations();
                     if (externalDeclarations != null)
                     {
                        declarations.addAll(externalDeclarations.getTypeDeclaration());
                     }
                  }
               }
            }
         }
      }
      return declarations;
   }
}