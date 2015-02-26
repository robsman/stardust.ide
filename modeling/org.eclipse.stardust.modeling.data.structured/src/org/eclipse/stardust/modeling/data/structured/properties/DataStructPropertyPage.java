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
package org.eclipse.stardust.modeling.data.structured.properties;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.ReferencedModelSorter;
import org.eclipse.stardust.modeling.data.structured.StructContentProvider;
import org.eclipse.stardust.modeling.data.structured.StructLabelProvider;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;

public class DataStructPropertyPage extends AbstractModelElementPropertyPage
   implements IDataPropertyPage
{
   private static final int[] widths = {100, 100, 100};

   private Composite body;
   private StackLayout stackLayout;
   
   private TableViewer typesViewer;
   private TreeViewer detailsViewer;
   private ReferencedModelSorter refSorter = new ReferencedModelSorter();
   private StructLabelProvider typesViewerLabelProvider = new StructLabelProvider();

   private Button groupingCheckbox;

   private Composite typesComposite;

   private Composite typesDetailsComposite;

   private SashForm sashForm;

   private LabelWithStatus typesLabel;

   private Button volatileCheckBox;   

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      volatileCheckBox = FormBuilder.createCheckBox(composite, Structured_Messages.LBL_Volatile_Data, 1);
      volatileCheckBox.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            DataType data = (DataType) getModelElement();
            boolean selection = ((Button) e.widget).getSelection();
            if(selection)
            {
               AttributeUtil.setBooleanAttribute(data, PredefinedConstants.VOLATILE_DATA, true);
            }
            else
            {
               AttributeUtil.setAttribute(data, PredefinedConstants.VOLATILE_DATA, null);               
            }
         }
      });
      
      body = FormBuilder.createComposite(composite, 1);
      stackLayout = new StackLayout();
      body.setLayout(stackLayout);
      
      sashForm = new SashForm(body, SWT.HORIZONTAL);
      typesComposite = FormBuilder.createComposite(sashForm,1 );
      typesDetailsComposite = FormBuilder.createComposite(sashForm, 1);
      sashForm.setWeights(new int[] { 1, 2});
      
      typesLabel = FormBuilder.createLabelWithLeftAlignedStatus(typesComposite,
            Structured_Messages.DataStructPropertyPage_DeclaredTypesLabel); 
                  
      FormBuilder.createLabel(typesDetailsComposite,
            Structured_Messages.DataStructPropertyPage_DetailsLabel);            
      
      Table declaredTypesTable = new Table(typesComposite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER);
      declaredTypesTable.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      typesViewer = new TableViewer(declaredTypesTable);
      typesViewer.setContentProvider(new StructContentProvider(true));
      typesViewer.setSorter(refSorter);
      typesViewer.setLabelProvider(typesViewerLabelProvider);

      final Tree tree = FormBuilder.createTree(typesDetailsComposite, SWT.SINGLE | SWT.FULL_SELECTION
            | SWT.BORDER);
      tree.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
      tree.setHeaderVisible(true);
      detailsViewer = new TreeViewer(tree);
      for (int i = 0; i < StructLabelProvider.COMPLEX_TYPE_COLUMNS.length; i++)
      {
         TreeColumn column = new TreeColumn(tree, SWT.LEFT);
         column.setText(StructLabelProvider.COMPLEX_TYPE_COLUMNS[i]);
         if(i == 0)
         {
            column.setWidth(200);
         }
         
      }
      detailsViewer.setUseHashlookup(true);
      detailsViewer.setContentProvider(new StructContentProvider(false));
      detailsViewer.setLabelProvider(new StructLabelProvider());
      detailsViewer.setColumnProperties(StructLabelProvider.COMPLEX_TYPE_COLUMNS);
      
      typesViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (!selection.isEmpty())
            {
               TypeDeclarationType type = (TypeDeclarationType) selection.getFirstElement();
               setType((DataType) getModelElement(), type);
               detailsViewer.setInput(type);
               if (!detailsViewer.getTree().isEnabled())
               {
            	   detailsViewer.expandAll();
               }               
               if (TypeDeclarationUtils.getType(type) == TypeDeclarationUtils.COMPLEX_TYPE)
               {
                  updateTableColumns(tree, StructLabelProvider.COMPLEX_TYPE_COLUMNS[0], true);
               }
               else if (TypeDeclarationUtils.getType(type) == TypeDeclarationUtils.SIMPLE_TYPE)                  
               {
                  updateTableColumns(tree, StructLabelProvider.SIMPLE_TYPE_COLUMNS[0], false);
               }               
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
      
      Composite buttonComposite = FormBuilder.createComposite(composite, 1);
      GridData gd = new GridData();
      gd.grabExcessHorizontalSpace = false;
      gd.grabExcessVerticalSpace = false;
      gd.horizontalAlignment = SWT.FILL;
      gd.verticalAlignment = SWT.LEFT;
      gd.verticalIndent = 0;
      buttonComposite.setLayoutData(gd);
      
      groupingCheckbox = FormBuilder.createCheckBox(buttonComposite, Diagram_Messages.LB_GroupModelElements);
      groupingCheckbox.addSelectionListener(new SelectionListener(){

		public void widgetDefaultSelected(SelectionEvent e) {
			
		}

		public void widgetSelected(SelectionEvent e) {
			refSorter.setGrouped(groupingCheckbox.getSelection());
			typesViewerLabelProvider.setShowGroupInfo(groupingCheckbox.getSelection());
			typesViewer.refresh(true);
		}
			    	  
      });
      
      return composite;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      DataType data = (DataType) element;
      IStructuredSelection selection = (IStructuredSelection) typesViewer.getSelection();
      if (selection.isEmpty())
      {
         data.setExternalReference(null);
         AttributeUtil.setReference(data, StructuredDataConstants.TYPE_DECLARATION_ATT, null);
         AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, null);
      }
      else
      {
         TypeDeclarationType type = (TypeDeclarationType) selection.getFirstElement();
         setType(data, type);
      }
   }

   private void setType(DataType data, TypeDeclarationType type)
   {
      ModelType typeModel = ModelUtils.findContainingModel(type);
      if (typeModel == ModelUtils.findContainingModel(data))
      {
         data.setExternalReference(null);
         AttributeUtil.setReference(data, StructuredDataConstants.TYPE_DECLARATION_ATT, type);
         AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, null);
      }
      else
      {
         ExternalReferenceType ref = data.getExternalReference();
         if (ref == null)
         {
            ref = XpdlFactory.eINSTANCE.createExternalReferenceType();
            data.setExternalReference(ref);
         }
         ref.setLocation(typeModel.getId());
         ref.setXref(type.getId());
         AttributeUtil.setReference(data, StructuredDataConstants.TYPE_DECLARATION_ATT, null);
         String uri = ExtendedAttributeUtil.getAttributeValue(type, IConnectionManager.URI_ATTRIBUTE_NAME);
         AttributeUtil.setAttribute(data, IConnectionManager.URI_ATTRIBUTE_NAME, uri);
      }
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      DataType data = (DataType) element;
      setDeclaredType(data);
      getWidgetBindingManager().getValidationBindingManager().bind(data, StructuredDataConstants.TYPE_DECLARATION_ATT, typesLabel);
   }

   private void setDeclaredType(DataType data)
   {
      ModelType model = ModelUtils.findContainingModel(data);
      refSorter.setModel(model);
      typesViewerLabelProvider.setModel(model);
      typesViewer.setInput(collectTypeDeclarations(model));
      TypeDeclarationType type = StructuredTypeUtils.getTypeDeclaration(data);
      typesViewer.setSelection(type == null ? StructuredSelection.EMPTY : new StructuredSelection(type));
      stackLayout.topControl = sashForm;
      volatileCheckBox.setSelection(AttributeUtil.getBooleanValue(data, PredefinedConstants.VOLATILE_DATA));      
      body.layout();
   }

   private List<TypeDeclarationType> collectTypeDeclarations(ModelType model)
   {
      List<TypeDeclarationType> declarations = CollectionUtils.newList();
      TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
      if (typeDeclarations != null)
      {
         for(TypeDeclarationType declaration : typeDeclarations.getTypeDeclaration())
         {
            if(!TypeDeclarationUtils.isEnumeration(declaration, false))
            {         
               declarations.add(declaration);
            }
         }
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
                     TypeDeclarationsType externalDeclarations = ((ModelType) externalModel)
                           .getTypeDeclarations();
                     if (externalDeclarations != null)
                     {
                        for (Iterator<TypeDeclarationType> i = externalDeclarations
                              .getTypeDeclaration().iterator(); i.hasNext();)
                        {
                           TypeDeclarationType declaration = i.next();
                           ExtendedAttributeType visibility = ExtendedAttributeUtil
                                 .getAttribute(declaration.getExtendedAttributes(),
                                       PredefinedConstants.MODELELEMENT_VISIBILITY);
                           if (visibility == null
                                 || visibility.getValue().equalsIgnoreCase("Public")) //$NON-NLS-1$
                           {
                              if(!TypeDeclarationUtils.isEnumeration(declaration, false))
                              {
                                 declarations.add(declaration);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return declarations;
   }
}