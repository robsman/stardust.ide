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
package org.eclipse.stardust.modeling.integration.dms.application;

import java.util.LinkedHashMap;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsOperation;
import org.eclipse.stardust.engine.extensions.dms.data.VfsOperationAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.BindingManager;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.StringKeyAdapter;
import org.eclipse.stardust.modeling.integration.dms.DMS_Messages;
import org.eclipse.stardust.modeling.integration.dms.data.DmsTypeUtils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author rsauer
 * @version $Revision$
 */
public class VfsOperationCoreProperties extends AbstractModelElementPropertyPage
{
   private Composite dmsContainer;

   private Button [] rbDms;

   private LabeledCombo cbOperation;

   private ComboViewer cbvOperation;

   private LabeledText txtDmsId;

   private Button chkRuntimeDefinedFolder;

   private Button chkRuntimeDefinedVersioning;

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      this.cbOperation = FormBuilder.createLabeledCombo(composite, DMS_Messages.VfsOperationCoreProperties_LB_Operation);
      this.cbvOperation = new ComboViewer(cbOperation.getCombo());

      cbvOperation.setContentProvider(new ArrayContentProvider());

      final LinkedHashMap<DmsOperation, String> operations = new LinkedHashMap<DmsOperation, String>();

      operations.put(DmsOperation.OP_ADD_DOCUMENT, DMS_Messages.VfsOperationCoreProperties_OP_AddDocument);
      operations.put(DmsOperation.OP_CREATE_FOLDER, DMS_Messages.VfsOperationCoreProperties_OP_CreateFolder);

      operations.put(DmsOperation.OP_GET_DOCUMENT, DMS_Messages.VfsOperationCoreProperties_OP_GetDocument);
      operations.put(DmsOperation.OP_GET_DOCUMENTS, DMS_Messages.VfsOperationCoreProperties_OP_GetDocuments);
      operations.put(DmsOperation.OP_GET_FOLDER, DMS_Messages.VfsOperationCoreProperties_OP_GetFolder);
      operations.put(DmsOperation.OP_GET_FOLDERS, DMS_Messages.VfsOperationCoreProperties_OP_GetFolders);

      operations.put(DmsOperation.OP_FIND_DOCUMENTS, DMS_Messages.VfsOperationCoreProperties_OP_FindDocuments);
      operations.put(DmsOperation.OP_FIND_FOLDERS, DMS_Messages.VfsOperationCoreProperties_OP_FindFolders);

      // currently, no lock/unlock is implemented
//      operations.put(DmsOperation.OP_LOCK_DOCUMENT, Messages.VfsOperationCoreProperties_OP_LockDocument);
//      operations.put(DmsOperation.OP_UNLOCK_DOCUMENT, Messages.VfsOperationCoreProperties_OP_UnlockDocument);
//      operations.put(DmsOperation.OP_LOCK_FOLDER, Messages.VfsOperationCoreProperties_OP_LockFolder);
//      operations.put(DmsOperation.OP_UNLOCK_FOLDER, Messages.VfsOperationCoreProperties_OP_UnlockFolder);

      operations.put(DmsOperation.OP_VERSION_DOCUMENT, DMS_Messages.VfsOperationCoreProperties_OP_VersionDocument);

      operations.put(DmsOperation.OP_UPDATE_DOCUMENT, DMS_Messages.VfsOperationCoreProperties_OP_UpdateDocument);
      operations.put(DmsOperation.OP_UPDATE_FOLDER, DMS_Messages.VfsOperationCoreProperties_OP_UpdateFolder);

      operations.put(DmsOperation.OP_REMOVE_DOCUMENT, DMS_Messages.VfsOperationCoreProperties_OP_RemoveDocument);
      operations.put(DmsOperation.OP_REMOVE_FOLDER, DMS_Messages.VfsOperationCoreProperties_OP_RemoveFolder);

      cbvOperation.setInput(operations.keySet().toArray(
            new DmsOperation[0]));
      cbvOperation.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            if (element instanceof DmsOperation)
            {
               String operationLabel = (String) operations.get(element);
               if ( !StringUtils.isEmpty(operationLabel))
               {
                  return operationLabel;
               }
            }

            return super.getText(element);
         }
      });

      this.rbDms = new Button[3];
      dmsContainer = FormBuilder.createComposite(composite, 3, 2);
      this.rbDms[0] = FormBuilder.createRadioButton(dmsContainer, DMS_Messages.VfsOperationCoreProperties_LB_UseDefaultDms, 3);
      this.rbDms[1] = FormBuilder.createRadioButton(dmsContainer, DMS_Messages.VfsOperationCoreProperties_LB_RuntimeDmsId, 3);
      this.rbDms[2] = FormBuilder.createRadioButton(dmsContainer, DMS_Messages.VfsOperationCoreProperties_LB_UseDmsId, 1);
      this.txtDmsId = FormBuilder.createLabeledText(dmsContainer, ""); //$NON-NLS-1$

      rbDms[0].addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setDmsIdSource(DmsConstants.DMS_ID_SOURCE_DEFAULT);
            txtDmsId.getText().setEnabled(rbDms[2].getSelection());
            ApplicationType application = (ApplicationType) getModelElement();
            AttributeUtil.setAttribute(application, DmsConstants.PRP_OPERATION_DMS_ID, null);
            AccessPointType accessPointType = AccessPointUtil.findAccessPoint(application.getAccessPoint(),
                  VfsOperationAccessPointProvider.AP_ID_DMS_ID, DirectionType.IN_LITERAL);
            if(accessPointType != null)
            {
               application.getAccessPoint().remove(accessPointType);
            }
         }
      });
      rbDms[1].addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setDmsIdSource(DmsConstants.DMS_ID_SOURCE_RUNTIME);
            txtDmsId.getText().setEnabled(rbDms[2].getSelection());
            ApplicationType application = (ApplicationType) getModelElement();
            AttributeUtil.setAttribute(application, DmsConstants.PRP_OPERATION_DMS_ID, null);
            AccessPointType accessPointType = AccessPointUtil.findAccessPoint(application.getAccessPoint(),
                  VfsOperationAccessPointProvider.AP_ID_DMS_ID, DirectionType.IN_LITERAL);
            if(accessPointType == null)
            {
               accessPointType = DmsTypeUtils.createPrimitiveAccessPointType(
                     VfsOperationAccessPointProvider.AP_ID_DMS_ID, String.class,
                     DirectionType.IN_LITERAL, application);
               application.getAccessPoint().add(accessPointType);
            }
         }
      });
      rbDms[2].addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setDmsIdSource(DmsConstants.DMS_ID_SOURCE_MODEL);
            txtDmsId.getText().setEnabled(rbDms[2].getSelection());
            ApplicationType application = (ApplicationType) getModelElement();
            AccessPointType accessPointType = AccessPointUtil.findAccessPoint(application.getAccessPoint(),
                  VfsOperationAccessPointProvider.AP_ID_DMS_ID, DirectionType.IN_LITERAL);
            if(accessPointType != null)
            {
               application.getAccessPoint().remove(accessPointType);
            }
         }
      });

//      FormBuilder.createHorizontalSeparator(dmsContainer, 3);

      this.chkRuntimeDefinedFolder = FormBuilder.createCheckBox(dmsContainer,
            DMS_Messages.VfsOperationCoreProperties_LB_RuntimeTargetFolder, 3);

      this.chkRuntimeDefinedVersioning = FormBuilder.createCheckBox(dmsContainer,
            DMS_Messages.VfsOperationCoreProperties_LB_RuntimeVersioning, 3);

      // view, hide elements depending on selection
      this.cbvOperation.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            ISelection sel = event.getSelection();
            if(sel.isEmpty())
            {
               dmsContainer.setVisible(false);
            }
            else if(sel instanceof IStructuredSelection)
            {
               boolean targetFolder = false;
               boolean versioning = false;

               dmsContainer.setVisible(true);
               // hide and remove values (if any)
               if(((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_CREATE_FOLDER)
                     || ((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_ADD_DOCUMENT))
               {
                  targetFolder = true;
               }

               if(((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_ADD_DOCUMENT)
                     || ((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_UPDATE_DOCUMENT))
               {
                  versioning = true;
               }

               if(((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_CREATE_FOLDER)
                     || ((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_REMOVE_FOLDER)
                     || ((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_ADD_DOCUMENT)
                     || ((IStructuredSelection) sel).getFirstElement().equals(DmsOperation.OP_REMOVE_DOCUMENT))
               {
                  dmsContainer.setVisible(false);
                  ApplicationType application = (ApplicationType) getModelElement();
                  AccessPointType accessPointType = AccessPointUtil.findAccessPoint(application.getAccessPoint(),
                        VfsOperationAccessPointProvider.AP_ID_DMS_ID, DirectionType.IN_LITERAL);
                  if(accessPointType != null)
                  {
                     application.getAccessPoint().remove(accessPointType);
                  }
               }

               // view/hide, uncheck
               if(targetFolder)
               {
                  chkRuntimeDefinedFolder.setVisible(true);
               }
               else
               {
                  chkRuntimeDefinedFolder.setSelection(false);
                  chkRuntimeDefinedFolder.setVisible(false);
               }
               if(versioning)
               {
                  chkRuntimeDefinedVersioning.setVisible(true);
               }
               else
               {
                  chkRuntimeDefinedVersioning.setSelection(false);
                  chkRuntimeDefinedVersioning.setVisible(false);
               }
            }
         }
      });

      return composite;
   }

   protected void setDmsIdSource(String dmsIdSource)
   {
      ApplicationType app = (ApplicationType) getModelElement();
      AttributeUtil.setAttribute(app, DmsConstants.PRP_DMS_ID_SOURCE, dmsIdSource);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
//      if ( !rbDms[2].getSelection())
//      {
//         txtDmsId.getText().setText("");
//      }
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (element instanceof ApplicationType)
      {
         ApplicationType application = (ApplicationType) element;

         getWidgetBindingManager().bind(cbOperation,
               BindingManager.createWidgetAdapter(cbvOperation), application,
               DmsConstants.PRP_OPERATION_NAME,
               new StringKeyAdapter(DmsOperation.class));

         getWidgetBindingManager().bind(txtDmsId, application,
               DmsConstants.PRP_OPERATION_DMS_ID);

         String dmsIdSource = AttributeUtil.getAttributeValue(application, DmsConstants.PRP_DMS_ID_SOURCE);
         if (DmsConstants.DMS_ID_SOURCE_DEFAULT.equals(dmsIdSource))
         {
            this.rbDms[0].setSelection(true);
         }
         else if (DmsConstants.DMS_ID_SOURCE_RUNTIME.equals(dmsIdSource))
         {
            this.rbDms[1].setSelection(true);
         }
         else if (DmsConstants.DMS_ID_SOURCE_MODEL.equals(dmsIdSource))
         {
            this.rbDms[2].setSelection(true);
         }
         else
         {
            // default is 'default'
            this.rbDms[0].setSelection(true);
         }
         txtDmsId.getText().setEnabled(rbDms[2].getSelection());

         getWidgetBindingManager().bind(chkRuntimeDefinedFolder, application,
               DmsConstants.PRP_RUNTIME_DEFINED_TARGET_FOLDER);

         getWidgetBindingManager().bind(chkRuntimeDefinedVersioning, application,
               DmsConstants.PRP_RUNTIME_DEFINED_VERSIONING);

      }
   }

}
