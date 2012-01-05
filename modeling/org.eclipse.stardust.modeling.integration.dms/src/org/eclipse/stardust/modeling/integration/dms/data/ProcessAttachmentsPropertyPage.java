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
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.integration.dms.DMS_Messages;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import ag.carnot.workflow.model.PredefinedConstants;

import com.infinity.bpm.rt.integration.data.dms.DmsConstants;

/**
 * @author fherinean
 * @version $Revision$
 */

public class ProcessAttachmentsPropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private Button withAttachments;
   private Button byReference;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (element instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) element;

         DataType attachments = findAttachmentsData(process);

         DataPathType attachmentsIn = findAttachmentsDataPath(process,
               DirectionType.IN_LITERAL);
         DataPathType attachmentsOut = findAttachmentsDataPath(process,
               DirectionType.OUT_LITERAL);

         boolean hasAttachments = (null != attachments) && (null != attachmentsIn)
               && (null != attachmentsOut);
         withAttachments.setSelection(hasAttachments);
         
         WidgetBindingManager wBndMgr = getWidgetBindingManager();
         wBndMgr.bind(byReference, process, DmsConstants.BY_REFERENCE_ATT);
         byReference.setEnabled(hasAttachments);
         if (!hasAttachments)
         {
            byReference.setSelection(false);
         }
      }
      else
      {
         withAttachments.setSelection(false);
         withAttachments.setEnabled(false);
         byReference.setSelection(false);
         byReference.setEnabled(false);
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (element instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType process = (ProcessDefinitionType) element;

         DataType attachments = findAttachmentsData(process);
         
         DataPathType attachmentsIn = findAttachmentsDataPath(process,
               DirectionType.IN_LITERAL);
         DataPathType attachmentsOut = findAttachmentsDataPath(process,
               DirectionType.OUT_LITERAL);

         if (withAttachments.getSelection())
         {
            if (null == attachments)
            {
               attachments = createAttachmentsData(process);
            }
            if (null == attachmentsIn)
            {
               attachmentsIn = createAttachmentsDataPath(process,
                     DirectionType.IN_LITERAL, attachments);
            }
            if (null == attachmentsOut)
            {
               attachmentsOut = createAttachmentsDataPath(process,
                     DirectionType.OUT_LITERAL, attachments);
            }
         }
         else
         {
            if (null != attachmentsIn)
            {
               process.getDataPath().remove(attachmentsIn);
            }
            if (null != attachmentsOut)
            {
               process.getDataPath().remove(attachmentsOut);
            }
            // do not remove data, as it may be used by other processes
         }
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      withAttachments = FormBuilder.createCheckBox(composite,
            DMS_Messages.BOX_PROCESS_SUPPORTS_ATTACHMENTS, 2);
      byReference = FormBuilder.createCheckBox(composite,
            DMS_Messages.DmsResourcePropertyPage_LB_SharedDocument, 2);
      withAttachments.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            byReference.setEnabled(withAttachments.getSelection());
         }
      });

      return composite;
   }

   private DataType createAttachmentsData(ProcessDefinitionType process)
   {
      DataType data = null;

      data = ModelUtils.findData(process, DmsConstants.DATA_ID_ATTACHMENTS);
      if (null != data)
      {
         DataTypeType dataType = data.getType();
         if ((null == dataType)
               || !com.infinity.bpm.rt.integration.data.dms.DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST.equals(dataType.getId()))
         {
            data.setType(findDocumentSetDataType(process));
         }
         
         AttributeType className = AttributeUtil.getAttribute(data,
               PredefinedConstants.CLASS_NAME_ATT);
         if ((null == className)
               || !List.class.getName().equals(className.getValue()))
         {
            AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT,
                  List.class.getName());
         }
         
         AttributeType attrBidirectional = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
         attrBidirectional.setName(CarnotConstants.ENGINE_SCOPE + "data:bidirectional"); //$NON-NLS-1$
         attrBidirectional.setValue(Boolean.TRUE.toString());
         attrBidirectional.setType(Boolean.TYPE.getName());

         
         AttributeType bidirectional = AttributeUtil.getAttribute(data,
               CarnotConstants.ENGINE_SCOPE + "data:bidirectional"); //$NON-NLS-1$
         if ((null == bidirectional)
               || !Boolean.TRUE.toString().equals(className.getValue()))
         {
            AttributeUtil.setAttribute(data, CarnotConstants.ENGINE_SCOPE + "data:bidirectional", //$NON-NLS-1$
                  Boolean.TYPE.getName(), Boolean.TRUE.toString());
         }
      }
      else
      {
         data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
         data.setId(DmsConstants.DATA_ID_ATTACHMENTS);
         data.setName("Process Attachments"); //$NON-NLS-1$
         data.setType(findDocumentSetDataType(process));
         data.setElementOid(ModelUtils.getElementOid(data,
               ModelUtils.findContainingModel(process)));

         ModelUtils.findContainingModel(process).getData().add(data);
         
         AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT,
               List.class.getName());
         AttributeUtil.setAttribute(data, CarnotConstants.ENGINE_SCOPE + "data:bidirectional", //$NON-NLS-1$
               Boolean.TYPE.getName(), Boolean.TRUE.toString());
      }

      return data;
   }

   private DataPathType createAttachmentsDataPath(ProcessDefinitionType process,
         DirectionType direction, DataType attachments)
   {
      DataPathType path = CarnotWorkflowModelFactory.eINSTANCE.createDataPathType();
      path.setId(DmsConstants.PATH_ID_ATTACHMENTS);
      path.setName("Process Attachments"); //$NON-NLS-1$
      path.setDirection(direction);
      path.setElementOid(ModelUtils.getElementOid(path,
            ModelUtils.findContainingModel(process)));
      
      path.setData(attachments);

      process.getDataPath().add(path);

      return path;
   }

   private DataType findAttachmentsData(ProcessDefinitionType process)
   {
      DataType result = null;

      result = ModelUtils.findData(process, DmsConstants.DATA_ID_ATTACHMENTS);
      if (null != result)
      {
         IMetaType dataType = result.getMetaType();
         if ((null == dataType)
               || (!DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST.equals(dataType.getId()) && !"dms-document-set" //$NON-NLS-1$
                     .equals(dataType.getId())))
         {
            result = null;
         }
      }

      return result;
   }

   private DataPathType findAttachmentsDataPath(ProcessDefinitionType process,
         DirectionType direction)
   {
      for (DataPathType dataPath : process.getDataPath())
      {
         if (DmsConstants.PATH_ID_ATTACHMENTS.equals(dataPath.getId()))
         {
            if (direction.equals(dataPath.getDirection()))
            {
               return dataPath;
            }
         }
      }
      return null;
   }

   private DataTypeType findDocumentSetDataType(ProcessDefinitionType process)
   {
      DataTypeType dataType = (DataTypeType) ModelUtils.findIdentifiableElement(
            ((ModelType) process.eContainer()).getDataType(),
            DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST);

      if (null == dataType)
      {
         Map<String, IConfigurationElement> dataExtensions = SpiExtensionRegistry.instance().getExtensions(
               CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID);

         IConfigurationElement config = dataExtensions.get(DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST);
         if (null != config)
         {
            CreateMetaTypeCommand cmdCreateMetaType = new CreateMetaTypeCommand(config,
                  CarnotWorkflowModelPackage.eINSTANCE.getDataTypeType(),
                  new EStructuralFeature[] {});
            cmdCreateMetaType.setParent(process.eContainer());
            cmdCreateMetaType.execute();

            dataType = (DataTypeType) cmdCreateMetaType.getModelElement();
         }
      }
      return dataType;
   }
}
