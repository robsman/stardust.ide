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

import java.text.MessageFormat;
import java.util.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xsd.*;

public class TypeDeclarationPropertyPage extends AbstractModelElementPropertyPage
{
   private static final XpdlPackage PKG_XPDL = XpdlPackage.eINSTANCE;
   private LabeledText txtId;
   private LabeledText txtName;
   private Label namespaceLabel;

   private Button autoNamespaceButton;

   private TypeDeclarationType declaration;
   private String savedId;

   private Button publicCheckBox;
   protected boolean publicType;

   private ModifyListener idListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if (autoNamespaceButton.getSelection())
         {
            if(!StringUtils.isEmpty(declaration.getId()))
            {
               namespaceLabel.setText(TypeDeclarationUtils.computeTargetNamespace(ModelUtils.findContainingModel(declaration), declaration.getId()));
            }
         }
         validateInput();
      }
   };

   private ModifyListener nameListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if (GenericUtils.getAutoIdValue())
         {
            String computedId = NameIdUtils.createIdFromName(null, declaration);
            txtId.getText().setText(computedId);
         }
         validateInput();
      }
   };

   public void elementChanged()
   {
      declaration = (TypeDeclarationType) getElement().getAdapter(EObject.class);

      setupVisibility();

      savedId = declaration.getId();

      txtName.getText().removeModifyListener(nameListener);
      txtId.getText().removeModifyListener(idListener);

      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.bind(txtId, declaration, PKG_XPDL.getTypeDeclarationType_Id());
      wBndMgr.bind(txtName, declaration, PKG_XPDL.getTypeDeclarationType_Name());

      XSDNamedComponent type = null;
      try
      {
         type = TypeDeclarationUtils.findElementOrTypeDeclaration(declaration);
      }
      catch(IllegalArgumentException e)
      {
      }

      String namespace = type == null ? null : type.getTargetNamespace();
      namespaceLabel.setText(namespace == null ? "" : namespace); //$NON-NLS-1$

      if (!TypeDeclarationUtils.isInternalSchema(declaration))
      {
         autoNamespaceButton.setSelection(false);
         autoNamespaceButton.setEnabled(false);
      }

      wBndMgr.getModelBindingManager().updateWidgets(declaration);

      txtName.getText().addModifyListener(nameListener);
      txtId.getText().addModifyListener(idListener);

      txtName.getText().selectAll();
      txtName.getText().setFocus();

      if (ExtendedAttributeUtil.getAttributeValue(declaration, IConnectionManager.URI_ATTRIBUTE_NAME) != null)
      {
         txtId.getText().setEnabled(false);
         txtName.getText().setEnabled(false);
      }

      validateInput();
   }

   public void apply()
   {
      List<XSDSchemaDirective> savedDirectives = new ArrayList<XSDSchemaDirective>();
      savedDirectives.addAll(declaration.getSchema().getReferencingDirectives());
      // boolean duplicate = false;
      String id = declaration.getId();

      /*boolean duplicate = false;
      for (TypeDeclarationType decl : ((TypeDeclarationsType) declaration.eContainer()).getTypeDeclaration())
      {
         if (!decl.equals(declaration))
         {
            if (decl.getId().equals(id))
            {
               duplicate = true;
               break;
            }
         }
      }*/

      if (TypeDeclarationUtils.isInternalSchema(declaration))
      {
         XSDSchema xsdSchema = declaration.getSchema();
         String oldTargetNamespace = xsdSchema.getTargetNamespace();

         if (!namespaceLabel.getText().equals(oldTargetNamespace == null ? "" : oldTargetNamespace)) //$NON-NLS-1$
         {
            xsdSchema.setTargetNamespace(TypeDeclarationUtils.computeTargetNamespace(ModelUtils.findContainingModel(declaration), id));
         }

         String prefix = TypeDeclarationUtils.computePrefix(id, xsdSchema.getQNamePrefixToNamespaceMap().keySet());
         xsdSchema.getQNamePrefixToNamespaceMap().put(prefix, xsdSchema.getTargetNamespace());
         ArrayList<String> toRemove = new ArrayList<String>();
         Map<String, String> prefixes = xsdSchema.getQNamePrefixToNamespaceMap();

         if(oldTargetNamespace != null)
         {
            for (Map.Entry<String, String> entry : prefixes.entrySet())
            {
               if (!prefix.equals(entry.getKey()) && oldTargetNamespace.equals(entry.getValue()))
               {
                  toRemove.add(entry.getKey());
               }
            }
            for (int j = 0; j < toRemove.size(); j++)
            {
               prefixes.remove(toRemove.get(j));
            }
            xsdSchema.eSet(XSDPackage.eINSTANCE.getXSDSchema_ReferencingDirectives(), savedDirectives);
            TypeDeclarationUtils.updateImports(xsdSchema, oldTargetNamespace, savedId, id);
         }
         xsdSchema.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX + id);
         xsdSchema.eSet(XSDPackage.eINSTANCE.getXSDSchema_ReferencingDirectives(), savedDirectives);
         XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(declaration, savedId);
         if (component != null)
         {
            component.setName(id);
         }
         if (component instanceof XSDElementDeclaration)
         {
            XSDElementDeclaration element = (XSDElementDeclaration) component;
            if (!element.isElementDeclarationReference() && element.getAnonymousTypeDefinition() == null)
            {
               XSDTypeDefinition type = element.getTypeDefinition();
               if (type != null && type.getSchema() == xsdSchema)
               {
                  type.setName(id);
               }
            }
         }
      }

      Set<XSDElementDeclaration> elements = new HashSet<XSDElementDeclaration>();
      ModelType model = ModelUtils.findContainingModel(declaration);
      XSDTypeDefinition definition = TypeDeclarationUtils.getTypeDefinition(model.getTypeDeclarations(), id);
      for(TypeDeclarationType decl : ((TypeDeclarationsType) declaration.eContainer()).getTypeDeclaration())
      {
         TypeDeclarationUtils.findElementsForType(decl, elements, savedId);
      }
      for (XSDElementDeclaration elementDecl : elements)
      {
         elementDecl.setTypeDefinition(definition);
      }
      declaration.getSchema().eSet(XSDPackage.eINSTANCE.getXSDSchema_ReferencingDirectives(), savedDirectives);
      savedId = id;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);
      this.txtName = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_Name);
      txtName.setTextLimit(80);

      this.txtId = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);
      txtId.setTextLimit(80);
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue();
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }

      publicCheckBox = FormBuilder.createCheckBox(composite, Diagram_Messages.CHECKBOX_Visibility);
      publicCheckBox.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            publicType = !publicType;
            if (publicType)
            {
               ExtendedAttributeUtil.setAttribute(declaration.getExtendedAttributes(),
                     PredefinedConstants.MODELELEMENT_VISIBILITY, "Public"); //$NON-NLS-1$
            }
            else
            {
               ExtendedAttributeUtil.setAttribute(declaration.getExtendedAttributes(),
                     PredefinedConstants.MODELELEMENT_VISIBILITY, "Private"); //$NON-NLS-1$
            }
         }
      });

      FormBuilder.createLabel(composite, " ", 2); //$NON-NLS-1$
      FormBuilder.createHorizontalSeparator(composite, 2);
      FormBuilder.createLabel(composite, " ", 2); //$NON-NLS-1$

      FormBuilder.createLabel(composite,
            Structured_Messages.SimpleTypePropertyPage_NamespaceLabel);
      namespaceLabel = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      autoNamespaceButton = FormBuilder.createCheckBox(composite,
            Structured_Messages.SYNCHRONIZE_NAMESPACE_AND_ID, 2); //$NON-NLS-1$
      autoNamespaceButton.setSelection(PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION));

      return composite;
   }

   private void validateInput()
   {
      ModelType model = (ModelType) declaration.eContainer().eContainer();

     if (txtName.getText().getText().length() == 0
           || txtId.getText().getText().length() == 0)
     {
        setMessage(Structured_Messages.TypeDeclarationPropertyPage_EmptyValuesMessage, ERROR);
        setValid(false);
        return;
     }

     if(!ModelUtils.isValidId(txtId.getText().getText()))
     {
        setMessage(MessageFormat.format(
              Structured_Messages.ComplexTypePropertyPage_InvalidFieldIdentifierErrorMessage,
              new Object [] {txtId.getText().getText()}), ERROR);
        setValid(false);
        return;
     }


     // check for duplicates
     TypeDeclarationsType declarations = model.getTypeDeclarations();
     List<TypeDeclarationType> allDeclarations = declarations.getTypeDeclaration();
     for (TypeDeclarationType td : allDeclarations)
     {
        if (!td.equals(declaration))
        {
           if (td.getId().equals(txtId.getText().getText()))
           {
              setMessage(MessageFormat.format(
                    Structured_Messages.TypeDeclarationPropertyPage_DuplicateValueMessage,
                    new Object [] {txtId.getText().getText()}), ERROR);
              setValid(false);
              return;
           }
           if (td.getName().equals(txtName.getText().getText()))
           {
              setMessage(MessageFormat.format(
                    Structured_Messages.TypeDeclarationPropertyPage_DuplicateValueMessage,
                    new Object [] {txtName.getText().getText()}), ERROR);
              setValid(false);
              return;
           }
        }
     }
     setMessage(null);
     setValid(true);
   }

   private void setupVisibility()
   {
      ExtendedAttributeType visibility = ExtendedAttributeUtil.getAttribute(declaration
            .getExtendedAttributes(), PredefinedConstants.MODELELEMENT_VISIBILITY);
      if (visibility == null)
      {
         String visibilityDefault = PlatformUI.getPreferenceStore().getString(
               BpmProjectNature.PREFERENCE_MULTIPACKAGEMODELING_VISIBILITY);
         if (visibilityDefault == null || visibilityDefault == "" //$NON-NLS-1$
               || visibilityDefault.equalsIgnoreCase("Public")) //$NON-NLS-1$
         {

            ExtendedAttributeUtil.createAttribute(declaration,
                  PredefinedConstants.MODELELEMENT_VISIBILITY).setValue("Public"); //$NON-NLS-1$
            publicType = true;
         }
      }
      else
      {
         if (visibility.getValue().equalsIgnoreCase("Public")) //$NON-NLS-1$
         {
            publicType = true;
         }
         else
         {
            publicType = false;
         }
      }
      publicCheckBox.setSelection(publicType);
   }
}