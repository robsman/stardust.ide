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

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.integration.dms.data.DmsTypeUtils;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringKey;

import com.infinity.bpm.rt.integration.data.dms.DmsConstants;
import com.infinity.bpm.rt.integration.data.dms.DmsOperation;
import com.infinity.bpm.rt.integration.data.dms.VfsOperationAccessPointProvider;

/**
 * @author rsauer
 * @version $Revision$
 */
public class VfsOperationModelingAccessPointProvider implements IAccessPointProvider
{
   public List<AccessPointType> createIntrinsicAccessPoint(IModelElement element)
   {
      List<AccessPointType> result;
      
      if (element instanceof ApplicationType)
      {
         final ApplicationType dmsOperation = (ApplicationType) element;
         
         final ModelType model = ModelUtils.findContainingModel(dmsOperation);
         
         final boolean runtimeDefinedTargetFolder = AttributeUtil.getBooleanValue(dmsOperation,
               DmsConstants.PRP_RUNTIME_DEFINED_TARGET_FOLDER);
         final boolean runtimeDefinedVersioning = AttributeUtil.getBooleanValue(dmsOperation,
               DmsConstants.PRP_RUNTIME_DEFINED_VERSIONING);
         
         result = CollectionUtils.newList();

         final String dmsIdSource = AttributeUtil.getAttributeValue(dmsOperation,
               DmsConstants.PRP_DMS_ID_SOURCE); 
         
         if (DmsConstants.DMS_ID_SOURCE_RUNTIME.equals(dmsIdSource))
         {
            // add the DMS selector (DMS ID) if configured so
            DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_DMS_ID, String.class,
                  DirectionType.IN_LITERAL, element);
         }
         
         final DmsOperation operation = (DmsOperation) StringKey.getKey(DmsOperation.class,
               AttributeUtil.getAttributeValue(dmsOperation,
                     DmsConstants.PRP_OPERATION_NAME));
         if (DmsOperation.OP_CREATE_FOLDER == operation)
         {
            if (runtimeDefinedTargetFolder)
            {
               // FolderInfo to identify parent folder
               result.add(DmsTypeUtils.newDmsFolderAccessPoint(model,
                     VfsOperationAccessPointProvider.AP_ID_TARGET_FOLDER,
                     DirectionType.IN_LITERAL));
            }

            // the created folder
            result.add(DmsTypeUtils.newDmsFolderAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_FOLDER, DirectionType.INOUT_LITERAL));

         }
         else if (DmsOperation.OP_FIND_FOLDERS == operation)
         {
            // FolderInfo defining search criteria
            result.add(DmsTypeUtils.newDmsFolderInfoAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_FOLDER_INFO,
                  DirectionType.IN_LITERAL));

            // expression
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_EXPRESSION, String.class,
                  DirectionType.IN_LITERAL, element));
            
            // search result (documents found)
            result.add(DmsTypeUtils.newDmsFolderListAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_FOLDER_LIST, DirectionType.OUT_LITERAL));

         }
         else if (DmsOperation.OP_REMOVE_FOLDER == operation)
         {
            // the folder to be deleted
            result.add(DmsTypeUtils.newDmsFolderAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_FOLDER, DirectionType.IN_LITERAL));

            // recursive
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_RECURSIVE, Boolean.class,
                  DirectionType.IN_LITERAL, element));
         }
         else if (DmsOperation.OP_ADD_DOCUMENT == operation)
         {
            if (runtimeDefinedTargetFolder)
            {
               // FolderInfo to identify parent folder
               result.add(DmsTypeUtils.newDmsFolderInfoAccessPoint(model,
                     VfsOperationAccessPointProvider.AP_ID_TARGET_FOLDER,
                     DirectionType.IN_LITERAL));
            }

            if (runtimeDefinedVersioning)
            {
               // optional versioning criteria 
               result.add(DmsTypeUtils.newVersioningAccessPoint(model,
                     VfsOperationAccessPointProvider.AP_ID_VERSIONING,
                     DirectionType.IN_LITERAL));
            }
            
            // the added file
            result.add(DmsTypeUtils.newDmsDocumentAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT,
                  DirectionType.INOUT_LITERAL));
         }
         else if (DmsOperation.OP_FIND_DOCUMENTS == operation)
         {
            // FileInfo defining search criteria
            result.add(DmsTypeUtils.newDmsDocumentAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT_INFO,
                  DirectionType.IN_LITERAL));

            // expression
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_EXPRESSION, String.class,
                  DirectionType.IN_LITERAL, element));
            
            // search result (documents found)
            result.add(DmsTypeUtils.newDmsDocumentListAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT_LIST,
                  DirectionType.OUT_LITERAL));
         }
         else if (DmsOperation.OP_UPDATE_DOCUMENT == operation)
         {
            // the document to be updated
            result.add(DmsTypeUtils.newDmsDocumentAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT,
                  DirectionType.INOUT_LITERAL));
            
            if (runtimeDefinedVersioning)
            {
               // optional versioning criteria 
               result.add(DmsTypeUtils.newVersioningAccessPoint(model,
                     VfsOperationAccessPointProvider.AP_ID_VERSIONING,
                     DirectionType.IN_LITERAL));
            }

         }
         else if (DmsOperation.OP_UPDATE_FOLDER == operation)
         {
            // the folder to be updated
            result.add(DmsTypeUtils.newDmsFolderAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_FOLDER,
                  DirectionType.INOUT_LITERAL));
         }
         else if (DmsOperation.OP_REMOVE_DOCUMENT == operation)
         {
            // the affected file
            result.add(DmsTypeUtils.newDmsDocumentAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT,
                  DirectionType.IN_LITERAL));
         }
         else if (DmsOperation.OP_GET_DOCUMENT == operation)
         {
            // document ID
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT_ID, String.class,
                  DirectionType.IN_LITERAL, element));

            // document to be returned
            result.add(DmsTypeUtils.newDmsDocumentAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT,
                  DirectionType.OUT_LITERAL));
         }
         else if (DmsOperation.OP_GET_FOLDER == operation)
         {
            // folder ID
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_FOLDER_ID, String.class,
                  DirectionType.IN_LITERAL, element));

            // folder to be returned
            result.add(DmsTypeUtils.newDmsFolderAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_FOLDER,
                  DirectionType.OUT_LITERAL));
         }
         else if (DmsOperation.OP_GET_DOCUMENTS == operation)
         {
            // document IDs
            result.add(DmsTypeUtils.createSerializableAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT_IDS, List.class,
                  DirectionType.IN_LITERAL, element));

            // documents to be returned
            result.add(DmsTypeUtils.newDmsDocumentListAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT_LIST,
                  DirectionType.OUT_LITERAL));
         }
         else if (DmsOperation.OP_GET_FOLDERS == operation)
         {
            // folder IDs
            result.add(DmsTypeUtils.createSerializableAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_FOLDER_IDS, List.class,
                  DirectionType.IN_LITERAL, element));

            // folders to be returned
            result.add(DmsTypeUtils.newDmsFolderListAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_FOLDER_LIST,
                  DirectionType.OUT_LITERAL));
         }
         else if (DmsOperation.OP_LOCK_FOLDER == operation 
               || DmsOperation.OP_UNLOCK_FOLDER == operation)
         {
            // folder ID
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_FOLDER_ID, String.class,
                  DirectionType.IN_LITERAL, element));
         }
         else if (DmsOperation.OP_LOCK_DOCUMENT == operation
               || DmsOperation.OP_UNLOCK_DOCUMENT == operation)
         {
            // document ID
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT_ID, String.class,
                  DirectionType.IN_LITERAL, element));
         }
         else if (DmsOperation.OP_VERSION_DOCUMENT == operation)
         {
            // revision name
            result.add(DmsTypeUtils.createPrimitiveAccessPointType(
                  VfsOperationAccessPointProvider.AP_ID_VERSION_LABEL, String.class,
                  DirectionType.IN_LITERAL, element));
            
            // the document to version
            result.add(DmsTypeUtils.newDmsDocumentAccessPoint(model,
                  VfsOperationAccessPointProvider.AP_ID_DOCUMENT,
                  DirectionType.INOUT_LITERAL));
         }

      }
      else
      {
         result = null;
      }
      
      return result;
   }

}
