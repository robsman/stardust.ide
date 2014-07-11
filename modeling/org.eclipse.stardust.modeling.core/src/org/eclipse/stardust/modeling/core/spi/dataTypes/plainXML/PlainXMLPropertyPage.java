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
package org.eclipse.stardust.modeling.core.spi.dataTypes.plainXML;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.platform.utils.WorkspaceUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.validation.util.ProjectClassLoader;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @author fherinean
 * @version $Revision$
 */
public class PlainXMLPropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private static final String[][] SCHEMA_TYPES = {
         {PredefinedConstants.PLAINXML_SCHEMA_TYPE_NONE, "None"}, //$NON-NLS-1$
         {PredefinedConstants.PLAINXML_SCHEMA_TYPE_XSD, "XSD"}, //$NON-NLS-1$
         {PredefinedConstants.PLAINXML_SCHEMA_TYPE_WSDL, "WSDL (XSD Envelope)"}, //$NON-NLS-1$
         {PredefinedConstants.PLAINXML_SCHEMA_TYPE_DTD, "DTD"} //$NON-NLS-1$
   };

   private Combo schemaTypeCombo;

   private Text typeDeclarationURLText;

   private Text elementNameText;

   private Button elementNameButton;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      DataType dataType = (DataType) element;

      schemaTypeCombo.select(0);
      String schemaType = AttributeUtil.getAttributeValue(dataType,
            PredefinedConstants.PLAINXML_SCHEMA_TYPE_ATT);
      for (int i = 0; i < SCHEMA_TYPES.length; i++)
      {
         if (SCHEMA_TYPES[i][0].equals(schemaType))
         {
            schemaTypeCombo.select(i);
         }
      }
      validationChanged();

      String schemaUrl = AttributeUtil.getAttributeValue(dataType,
            PredefinedConstants.PLAINXML_SCHEMA_URL_ATT);
      typeDeclarationURLText.setText(schemaUrl == null ? "" : schemaUrl); //$NON-NLS-1$

      String elementName = AttributeUtil.getAttributeValue(dataType,
            PredefinedConstants.PLAINXML_TYPE_ID_ATT);
      elementNameText.setText(elementName == null ? "" : elementName); //$NON-NLS-1$

      if (isPredefined(element))
      {
         disableControls();
      }
   }

   private void disableControls()
   {
      typeDeclarationURLText.setEditable(false);
      elementNameText.setEditable(false);
      elementNameButton.setEnabled(false);
   }

   private boolean isPredefined(IModelElement element)
   {
      return ((DataType) element).isPredefined();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      DataType dataType = (DataType) element;
      dataType.getAttribute().clear();

      AttributeUtil.setAttribute(dataType, PredefinedConstants.BROWSABLE_ATT,
            "boolean", "true"); //$NON-NLS-1$ //$NON-NLS-2$
      int selection = schemaTypeCombo.getSelectionIndex();
      if (selection < 0)
      {
         selection = 0;
      }
      AttributeUtil.setAttribute(dataType, PredefinedConstants.PLAINXML_SCHEMA_TYPE_ATT,
            SCHEMA_TYPES[selection][0]);

      if (selection > 0)
      {
         AttributeUtil.setAttribute(dataType, PredefinedConstants.PLAINXML_SCHEMA_URL_ATT,
               typeDeclarationURLText.getText());
         AttributeUtil.setAttribute(dataType, PredefinedConstants.PLAINXML_TYPE_ID_ATT,
               elementNameText.getText());
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 3);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_SchemaType);

      schemaTypeCombo = FormBuilder.createCombo(composite, 2);
      for (int i = 0; i < SCHEMA_TYPES.length; i++)
      {
         schemaTypeCombo.add(SCHEMA_TYPES[i][1]);
      }
      schemaTypeCombo.select(0);
      schemaTypeCombo.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            validationChanged();
         }
      });

      FormBuilder.createLabel(composite, Diagram_Messages.LB_TypeDeclarationURL);

      typeDeclarationURLText = FormBuilder.createText(composite, 2);
      FormBuilder.createLabel(composite, Diagram_Messages.LB_ElementName);

      elementNameText = FormBuilder.createText(composite);

      elementNameButton = FormBuilder
            .createButton(composite, Diagram_Messages.B_Browse, null);
      elementNameButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            selectElement();
         }
      });

      return composite;
   }

   private void validationChanged()
   {
      int selection = schemaTypeCombo.getSelectionIndex();
      typeDeclarationURLText.setEnabled(selection > 0);
      elementNameText.setEnabled(selection > 0);
      elementNameButton.setEnabled(selection > 0);
   }

   private void selectElement()
   {
      String typeDeclUri = typeDeclarationURLText.getText().trim();
      if (typeDeclUri.length() > 0)
      {
         ElementListSelectionDialog dialog = new ElementListSelectionDialog(null,
               new LabelProvider());
         dialog.setMultipleSelection(false);
         Object[] declaredTypes = getDeclaredTypes();
         if(declaredTypes.length != 0)
         {
            dialog.setElements(declaredTypes);
            dialog.setInitialSelections(new Object[] {elementNameText.getText()});
            int resultx = dialog.open();
            if (resultx == Window.OK)
            {
               Object[] result = dialog.getResult();
               if (result.length == 1)
               {
                  elementNameText.setText(result[0] == null ? "" : result[0].toString()); //$NON-NLS-1$
               }
            }            
         }
         else
         {
            dialog.close();
         }
      }
   }

   private Object[] getDeclaredTypes()
   {
      List types = Collections.EMPTY_LIST;

      // need to override context class loader so we can find the resource from the
      // project classpath
      ClassLoader cclBackup = Thread.currentThread().getContextClassLoader();
      try
      {
         IProject project = WorkspaceUtils.getProjectFromEObject(getModelElement());
         String resource = typeDeclarationURLText.getText().trim();
         Thread.currentThread().setContextClassLoader(new ProjectClassLoader(
               XmlUtils.class.getClassLoader(), project, resource.startsWith("/") //$NON-NLS-1$
               ? resource.substring(1) : resource));
         String resolvedUri = XmlUtils.resolveResourceUri(resource);
         switch (schemaTypeCombo.getSelectionIndex())
         {
         case 1: // xsd
            types = SchemaUtils.getXSDSchemaElements(resolvedUri);
            break;
         case 2: // wsdl
            types = SchemaUtils.getWSDLSchemaElements(resolvedUri);
            break;
         case 3: // dtd
            types = SchemaUtils.getDTDSchemaElements(resolvedUri);
            break;
         }
      }
      catch (Exception e)
      {
         ErrorDialog.openError(null, Diagram_Messages.ERR_Error,
               Diagram_Messages.ERR_CannotRetrieveSchemaElement, new Status(Status.WARNING,
                     CarnotConstants.DIAGRAM_PLUGIN_ID, 1, e.getMessage(), e));
      }
      finally
      {
         // restoring previous context class loader
         Thread.currentThread().setContextClassLoader(cclBackup);
      }
      return types.toArray();
   }
}
