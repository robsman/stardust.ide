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
package org.eclipse.stardust.modeling.integration.dms;

import org.eclipse.osgi.util.NLS;

public class DMS_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.integration.dms.dms-messages"; //$NON-NLS-1$

   public static String BOX_PROCESS_SUPPORTS_ATTACHMENTS;

public static String DmsResourcePropertyPage_DefaultPropertiesSchema;

   public static String DmsResourcePropertyPage_LB_PropertiesSchema;

   public static String DmsResourcePropertyPage_LB_SharedDocument;

   public static String VfsOperationCoreProperties_LB_Operation;

   public static String VfsOperationCoreProperties_LB_RuntimeDmsId;

   public static String VfsOperationCoreProperties_LB_RuntimeTargetFolder;

   public static String VfsOperationCoreProperties_LB_RuntimeVersioning;

   public static String VfsOperationCoreProperties_LB_UseDefaultDms;

   public static String VfsOperationCoreProperties_LB_UseDmsId;

   public static String VfsOperationCoreProperties_OP_AddDocument;

   public static String VfsOperationCoreProperties_OP_CreateFolder;

   public static String VfsOperationCoreProperties_OP_FindDocuments;

   public static String VfsOperationCoreProperties_OP_FindFolders;

   public static String VfsOperationCoreProperties_OP_GetDocument;

   public static String VfsOperationCoreProperties_OP_GetDocuments;

   public static String VfsOperationCoreProperties_OP_GetFolder;

   public static String VfsOperationCoreProperties_OP_GetFolders;

   public static String VfsOperationCoreProperties_OP_LockDocument;

   public static String VfsOperationCoreProperties_OP_LockFolder;

   public static String VfsOperationCoreProperties_OP_RemoveDocument;

   public static String VfsOperationCoreProperties_OP_RemoveFolder;

   public static String VfsOperationCoreProperties_OP_UnlockDocument;

   public static String VfsOperationCoreProperties_OP_UnlockFolder;

   public static String VfsOperationCoreProperties_OP_UpdateDocument;

   public static String VfsOperationCoreProperties_OP_UpdateFolder;

   public static String VfsOperationCoreProperties_OP_VersionDocument;

   public static String DataValidator_InvalidType;
   
   public static String DMSApplicationValidator_NoAccessPoint;

   public static String DMS_PropertyPage_ShowTypes;   
   
   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, DMS_Messages.class);
   }

   private DMS_Messages()
   {
   }
}