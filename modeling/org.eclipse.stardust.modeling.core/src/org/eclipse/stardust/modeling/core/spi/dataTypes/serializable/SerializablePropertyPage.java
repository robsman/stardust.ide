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
package org.eclipse.stardust.modeling.core.spi.dataTypes.serializable;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * @author fherinean
 * @version $Revision$
 */

public class SerializablePropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private static final String BLANK_STRING = " "; //$NON-NLS-1$

   private static final String[] columnProperties = {
      Diagram_Messages.SerializablePropertyPage_ParameterColumnLabel,
      Diagram_Messages.SerializablePropertyPage_TypeColumnLabel
   };

   private LabelWithStatus classLabel;
   private TypeSelectionComposite classBrowser;
   private Label genericInfoLabel;
   private TreeViewer viewer;

   private TypeFinder finder;
   private TypeModel model;
   private HashMap typeCache = new HashMap();
   private String variableType = null;

   private Button autoInitializeCheckBox;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      finder = new TypeFinder(node);
      classBrowser.setTypeFinder(finder);
      classBrowser.setModel((ModelType) node.eContainer());

      String fullClassName = AttributeUtil.getAttributeValue(
            (IExtensibleElement) node, PredefinedConstants.CLASS_NAME_ATT);

      boolean autoInitialize = AttributeUtil.getBooleanValue(
            (IExtensibleElement) node, PredefinedConstants.AUTO_INSTANTIATE_ATT);
      autoInitializeCheckBox.setSelection(autoInitialize);

      if(!StringUtils.isEmpty(fullClassName))
         {
         if (fullClassName.indexOf("${") > -1) //$NON-NLS-1$
         {
            variableType = fullClassName;
         }
         fullClassName = VariableContextHelper.getInstance().getContext(
               (ModelType) this.getModelElement().eContainer())
               .replaceAllVariablesByDefaultValue(fullClassName);

         findType(fullClassName);
         if (model != null)
         {
            classBrowser.setType(model.getType());
         }
         if (variableType != null)
         {
            classBrowser.setTypeText(variableType);
         }
      }

      setViewerInput();

      if (isPredefined(node))
      {
         disableControls();
      }

      getWidgetBindingManager().getValidationBindingManager().bind(
            node, PredefinedConstants.CLASS_NAME_ATT, classLabel);
   }

   private void findType(String fullClassName)
   {
      fullClassName = VariableContextHelper.getInstance().getContext(
            (ModelType) this.getModelElement().eContainer())
            .replaceAllVariablesByDefaultValue(fullClassName);
      model = (TypeModel) typeCache.get(fullClassName);
      if (model == null)
      {
         TypeInfo info = finder.findType(fullClassName);
         if (info != null)
         {
            model = new TypeModel(info);
            typeCache.put(fullClassName, model);
         }
      }
   }

   private void setViewerInput()
   {
      try
      {
         boolean hasParams = model == null ? false : model.hasParameters();
         genericInfoLabel.setText(!hasParams ? "" : MessageFormat.format( //$NON-NLS-1$
               Diagram_Messages.SerializablePropertyPage_ParametersTableLabel, new Object[] {model.getSimpleName()}));
         viewer.setInput(model);
         viewer.getTree().setVisible(hasParams);

         boolean hasDefaultConstructor = model == null ? false : model.hasDefaultConstructor();
         if (hasDefaultConstructor)
         {
            autoInitializeCheckBox.setEnabled(true);
         }
         else
         {
            autoInitializeCheckBox.setSelection(false);
            autoInitializeCheckBox.setEnabled(false);
         }
      }
      catch (JavaModelException e)
      {
         log(Diagram_Messages.EXC_CANNOT_RETRIEVE_TYPE_PARAMETERS, e);
         viewer.setInput(null);
      }
   }

   private void disableControls()
   {
      classBrowser.setEnabled(false);
   }

   private boolean isPredefined(IModelElement element)
   {
      return ((DataType) element).isPredefined();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      String fullClassName = null;
      if (variableType != null)
      {
         fullClassName = variableType;
      }

      if (model != null)
      {
         if (variableType == null)
         {
            fullClassName = model.getFullName();
         }
         AttributeUtil.setAttribute((IExtensibleElement) element,
               PredefinedConstants.CLASS_NAME_ATT, fullClassName);
         if (autoInitializeCheckBox.getSelection())
         {
            AttributeUtil.setBooleanAttribute((IExtensibleElement) element,
                  PredefinedConstants.AUTO_INSTANTIATE_ATT, true);
         }
         else
         {
            AttributeUtil.setAttribute((IExtensibleElement) element,
                  PredefinedConstants.AUTO_INSTANTIATE_ATT, null);
         }
      }
      else
      {
         AttributeUtil.setAttribute((IExtensibleElement) element,
               PredefinedConstants.CLASS_NAME_ATT, fullClassName);
      }
   }

   public Control createBody(Composite parent)
   {
      final Composite composite = FormBuilder.createComposite(parent, 2);
      final String browserTitle = Diagram_Messages.SerializablePropertyPage_Class;

      classLabel = FormBuilder.createLabelWithRightAlignedStatus(
            composite, Diagram_Messages.LB_ClassName);
      classBrowser = new TypeSelectionComposite(composite, browserTitle);

      FormBuilder.createLabel(composite, BLANK_STRING);
      autoInitializeCheckBox = FormBuilder.createCheckBox(composite, Diagram_Messages.SerializablePropertyPage_AutoInstantiateLabel);

      FormBuilder.createLabel(composite, BLANK_STRING, 2); // empty line

      genericInfoLabel = FormBuilder.createLabel(composite, BLANK_STRING, 2);

      classBrowser.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            if (model == null || !classBrowser.getTypeText().equals(model.getTypeName()))
            {
               String typeName = classBrowser.getTypeText();
               if (typeName.indexOf("${") > -1) { //$NON-NLS-1$
                  variableType = typeName;
               } else {
                  variableType = null;
               }
               typeName = VariableContextHelper.getInstance().getContext(
                     (ModelType) getModelElement().eContainer())
                     .replaceAllVariablesByDefaultValue(typeName);
               findType(typeName);
               setViewerInput();
               if (model != null)
               {
                  String fullClassName = model.getFullName();
                  if (variableType != null) {
                     fullClassName = variableType;
                  }
                  AttributeUtil.setAttribute((IExtensibleElement) getModelElement(),
                        PredefinedConstants.CLASS_NAME_ATT, fullClassName);
               }
            }
         }
      });

      Tree table = FormBuilder.createTree(composite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
            columnProperties , new int[] {25, 70}, 2);
      table.setVisible(false);
      viewer = new TreeViewer(table);
      viewer.setContentProvider(new ParameterContentProvider());
      viewer.setLabelProvider(new ParameterLabelProvider());
      viewer.setColumnProperties(columnProperties);
      viewer.setCellModifier(new CellModifier());
      viewer.setCellEditors(new CellEditor[] {null,
            new DialogCellEditor(table)
      {
         protected Object openDialogBox(Control cellEditorWindow)
         {
            IRunnableContext context = new ApplicationWindow(composite.getShell());
            IJavaSearchScope scope = SearchEngine
                  .createJavaSearchScope(new IJavaElement[] {finder.getJavaProject()});
            try
            {
               SelectionDialog dialog = JavaUI.createTypeDialog(composite
                     .getShell(), context, scope,
                     IJavaElementSearchConstants.CONSIDER_ALL_TYPES, false);
               dialog.setTitle(browserTitle);
               if (dialog.open() == Window.OK)
               {
                  IType type = (IType) dialog.getResult()[0];
                  return type.getFullyQualifiedName();
               }
            }
            catch (JavaModelException e)
            {
               log(Diagram_Messages.EXC_CANNOT_SEARCH_AVAILABLE_TYPES, e);
            }
            return null;
         }
      }});

      return composite;
   }

   private class CellModifier implements ICellModifier
   {
      public boolean canModify(Object element, String property)
      {
         return property.equals(columnProperties[1]);
      }

      public Object getValue(Object element, String property)
      {
         return ((ITableLabelProvider) viewer.getLabelProvider()).getColumnText(element, 1);
      }

      public void modify(Object element, String property, Object value)
      {
         Object local = element instanceof TreeItem
            ? ((TreeItem) element).getData() : element;
         if (property.equals(columnProperties[1]))
         {
            ParameterModel model = (ParameterModel) local;
            TypeInfo type = finder.findType((String) value);
            model.setType(type);
            viewer.refresh(model);
         }
      }
   }

   private class ParameterContentProvider extends ArrayContentProvider implements ITreeContentProvider
   {
      public Object[] getElements(Object inputElement)
      {
         TypeModel model = (TypeModel) inputElement;
         try
         {
            return model.getChildren();
         }
         catch (JavaModelException e)
         {
            log(Diagram_Messages.EXC_CANNOT_RETRIEVE_TYPE_PARAMETERS, e);
            return new Object[0];
         }
      }

      public Object[] getChildren(Object parentElement)
      {
         ParameterModel model = (ParameterModel) parentElement;
         try
         {
            return model.getChildren();
         }
         catch (JavaModelException e)
         {
            log(Diagram_Messages.EXC_CANNOT_RETRIEVE_TYPE_PARAMETERS, e);
            return new Object[0];
         }
      }

      public Object getParent(Object element)
      {
         if (element instanceof ParameterModel)
         {
            ParameterModel model = (ParameterModel) element;
            return model.getParent();
         }
         return null;
      }

      public boolean hasChildren(Object element)
      {
         ParameterModel model = (ParameterModel) element;
         try
         {
            return model.hasParameters();
         }
         catch (JavaModelException e)
         {
            log(Diagram_Messages.EXC_CANNOT_RETRIEVE_TYPE_PARAMETERS, e);
            return false;
         }
      }
   }

   private class ParameterLabelProvider extends LabelProvider implements ITableLabelProvider
   {
      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }

      public String getColumnText(Object element, int columnIndex)
      {
         String result = null;
         ParameterModel model = (ParameterModel) element;
         switch (columnIndex)
         {
            case 0 :
               result = model.getParameterName();
               break;
            case 1 :
               result = model.getTypeName();
               break;
         }
         return result == null ? "" : result; //$NON-NLS-1$
      }
   }

   private class TypeModel
   {
      private TypeInfo type;
      private ParameterModel[] parameters;

      public TypeModel(TypeInfo type)
      {
         this.type = type;
      }

      public boolean hasDefaultConstructor()
      {
         try
         {
            for (MethodInfo ctor : type.getConstructors())
            {
               if (ctor.getParameterCount() == 0)
               {
                  return true;
               }
            }
         }
         catch (JavaModelException e)
         {
            log(Diagram_Messages.SerializablePropertyPage_CannotFetchConstructorsErrorMessage, e);
         }
         return false;
      }

      public TypeInfo getType()
      {
         return type;
      }

      public String getSimpleName()
      {
         return type.getType().getElementName();
      }

      public String getFullName()
      {
         updateTypes(type, parameters);
         return type.getFullName();
      }

      public String getTypeName()
      {
         return type.getType().getFullyQualifiedName();
      }

      public boolean hasParameters() throws JavaModelException
      {
         return SerializablePropertyPage.this.hasParameters(type);
      }

      public ParameterModel[] getChildren() throws JavaModelException
      {
         if (parameters == null)
         {
            parameters = getParameters(type);
         }
         return parameters;
      }
   }

   private class ParameterModel
   {
      private Object parent;
      private TypeInfo owner;
      private ITypeParameter param;
      private TypeInfo type;
      private ParameterModel[] parameters;

      public ParameterModel(Object parent, TypeInfo owner, ITypeParameter param)
      {
         this.parent = parent;
         this.owner = owner;
         this.param = param;
      }

      public void setType(TypeInfo type)
      {
         String otherTypeName = SerializablePropertyPage.getTypeName(type);
         if (!getTypeName().equals(otherTypeName))
         {
            this.type = type;
            owner.setParameterType(param.getElementName(),
                  type == null ? null : otherTypeName);
            parameters = null;
         }
      }

      public String getTypeName()
      {
         return SerializablePropertyPage.getTypeName(type);
      }

      public Object getParent()
      {
         return parent;
      }

      public String getParameterName()
      {
         return param.getElementName();
      }

      public boolean hasParameters() throws JavaModelException
      {
         return SerializablePropertyPage.this.hasParameters(type);
      }

      public String getFullName()
      {
         updateTypes(type, parameters);
         return type.getFullName();
      }

      public ParameterModel[] getChildren() throws JavaModelException
      {
         if (parameters == null)
         {
            parameters = getParameters(type);
         }
         return parameters;
      }

      public String getElementName()
      {
         return param.getElementName();
      }
   }

   public void log(String message, JavaModelException e)
   {
      DiagramPlugin.log(new Status(IStatus.WARNING, CarnotConstants.DIAGRAM_PLUGIN_ID,
            0, message, e));
   }

   public void updateTypes(TypeInfo type, ParameterModel[] parameters)
   {
      if (parameters != null)
      {
         for (int i = 0; i < parameters.length; i++)
         {
            ParameterModel param = parameters[i];
            if(param.type != null)
            {
               type.setParameterType(param.getElementName(), param.getFullName());
            }
         }
      }
   }

   private static String getTypeName(TypeInfo type)
   {
      return type == null ? "" : type.getType().getFullyQualifiedName(); //$NON-NLS-1$
   }

   public boolean hasParameters(TypeInfo type) throws JavaModelException
   {
      if (type != null)
      {
         ITypeParameter[] parameters = type.getType().getTypeParameters();
         return parameters.length > 0;
      }
      return false;
   }

   private ParameterModel[] getParameters(TypeInfo type) throws JavaModelException
   {
      if (type != null)
      {
         ITypeParameter[] typeParameters = type.getType().getTypeParameters();
         if (typeParameters.length > 0)
         {
            ParameterModel[] parameters = new ParameterModel[typeParameters.length];
            for (int i = 0; i < typeParameters.length; i++)
            {
               parameters[i] = new ParameterModel(this, type, typeParameters[i]);
               String fullClassName = type.getParameterType(typeParameters[i].getElementName());
               if (fullClassName != null)
               {
                  parameters[i].setType(finder.findType(fullClassName));
               }
            }
            return parameters;
         }
      }
      return new ParameterModel[0];
   }
}