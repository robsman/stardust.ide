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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.util.IConnection;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

public class ExternalPackagePropertyPage extends AbstractModelElementPropertyPage
{
   protected LabeledText txtId;

   protected LabeledText txtName;

   private ComboViewer fileConnectionCombo;

   private LabeledText modelIDText;

   private ExternalPackage externalPackage;

   private Connection selectedConnection;

   public boolean performOk()
   {
      return true;
   }

   public String getTitle()
   {
      return Diagram_Messages.TXT_EXTERNAL_MD_REFERENCES_PROPERTY_PAGE;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      FormBuilder.createLabel(composite, Diagram_Messages.LBL_FILE_CONNECTION);
      fileConnectionCombo = new ComboViewer(FormBuilder.createCombo(composite));
      fileConnectionCombo.setLabelProvider(new ConnectionLabelProvider());
      modelIDText = FormBuilder.createLabeledText(composite, Diagram_Messages.LBL_MD_ID);

      ModelType modelType = (ModelType) getEditor().getModel();
      externalPackage = (ExternalPackage) ((EditPart) this.getElement()).getModel();
      final ConnectionManager cm = (ConnectionManager) modelType.getConnectionManager();
      ModelType referencedModel = cm.find(externalPackage);
      if (this.getElement() instanceof EditPart)
      {
         ExtendedAttributeType attribute = ExtendedAttributeUtil.getAttribute(
               externalPackage.getExtendedAttributes(), "carnot:connection:uri"); //$NON-NLS-1$
         String connectionURI = attribute.getValue();
         IConnection packageConnection = cm.findConnection(connectionURI);
         selectedConnection = null;
         for (Iterator<Connection> i = cm.getConnections(); i.hasNext();)
         {
            Connection connection = i.next();
            fileConnectionCombo.add(connection);
            if (packageConnection.getId().equalsIgnoreCase(connection.getId()))
            {
               selectedConnection = connection;
            }
         }
         ISelection selection = new StructuredSelection(selectedConnection);
         fileConnectionCombo.setSelection(selection);
         modelIDText.getText().setText(referencedModel.getId());
         modelIDText.getText().setEnabled(false);
         fileConnectionCombo.addSelectionChangedListener(new ISelectionChangedListener()
         {

            private boolean ignoreEvent;

            public void selectionChanged(SelectionChangedEvent event)
            {
               if (!ignoreEvent)
               {
                  MessageBox messageBox = new MessageBox(Display.getDefault()
                        .getActiveShell(), SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
                  messageBox.setText(Diagram_Messages.TXT_WR);
                  messageBox
                        .setMessage(Diagram_Messages.MSG_THIS_OPERATION_MAY_RESULT_IN_DANGLING_REFE);
                  if (messageBox.open() == SWT.OK)
                  {
                     if (event.getSelection() instanceof IStructuredSelection)
                     {
                        IStructuredSelection structuredSelection = (IStructuredSelection) event
                              .getSelection();
                        Connection connection = (Connection) structuredSelection
                              .getFirstElement();
                        String newURI = "cnx://" + connection.getId() + "/"; //$NON-NLS-1$ //$NON-NLS-2$
                        ExtendedAttributeType attribute = ExtendedAttributeUtil
                              .getAttribute(externalPackage.getExtendedAttributes(),
                                    "carnot:connection:uri"); //$NON-NLS-1$
                        String oldURI = attribute.getValue();
                        ExtendedAttributeUtil.setAttribute(externalPackage
                              .getExtendedAttributes(), "carnot:connection:uri", newURI); //$NON-NLS-1$
                        EObject o = cm.find(newURI);
                        ModelType referencedModel = (ModelType) Reflect.getFieldValue(o,
                              "eObject"); //$NON-NLS-1$
                        externalPackage.setHref(referencedModel.getId());
                        ModelType modelType = (ModelType) getEditor().getModel();
                        substituteURI(modelType, oldURI, newURI);
                        modelIDText.getText().setText(externalPackage.getHref());
                     }
                  }
                  else
                  {
                     ignoreEvent = true;
                     ISelection selection = new StructuredSelection(selectedConnection);
                     fileConnectionCombo.setSelection(selection);
                     ignoreEvent = false;
                  }
               }

            }

         });
      }
      return composite;
   }


   public void createControl(Composite parent){
      super.createControl(parent);
   }

   private void substituteURI(EObject modelElement, String oldURI, String newURI)
   {
      if (modelElement.eContainer() != null)
      {
         if (modelElement instanceof AttributeType)
         {
            AttributeType attribute = (AttributeType) modelElement;
            if (attribute.getValue().startsWith(oldURI))
            {
               String value = attribute.getValue();
               value = value.substring(oldURI.length());
               attribute.setValue(newURI + value);
            }
         }
         if (modelElement instanceof ExtendedAttributeType)
         {
            ExtendedAttributeType attribute = (ExtendedAttributeType) modelElement;
            if (attribute.getValue().startsWith(oldURI))
            {
               String value = attribute.getValue();
               value = value.substring(oldURI.length());
               attribute.setValue(newURI + value);
            }
         }
      }
      for (Iterator<EObject> i = modelElement.eContents().iterator(); i.hasNext();)
      {
         substituteURI(i.next(), oldURI, newURI);
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {

   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {

   }

   class ConnectionLabelProvider extends LabelProvider
   {

      public String getText(Object element)
      {
         Connection c = (Connection) element;
         return c.getName();
      }

   }

   @Override
   public boolean isValid()
   {
      return super.isValid();
   }
}