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
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.engine.extensions.dms.data.emfxsd.DmsSchemaProvider;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.util.IObjectReference;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.modeling.data.structured.validation.StructBridgeObject;
import org.eclipse.stardust.modeling.data.structured.validation.StructBridgeObjectProvider;
import org.eclipse.stardust.modeling.integration.dms.DMS_Messages;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
import org.eclipse.stardust.modeling.validation.util.Path;
import org.eclipse.stardust.modeling.validation.util.PathEntry;

/**
 * @author rsauer
 * @version $Revision$
 */
public abstract class AbstractDmsItemValidator
      implements IModelElementValidator, IBridgeObjectProvider
{
   protected abstract Class<?> getInterfaceType();
   
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      // TODO (ab) validate path
      List<Issue> issues = CollectionUtils.newList();

      String structuredDataId = AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
      if (!StringUtils.isEmpty(structuredDataId))
      {
         ModelType model = ModelUtils.findContainingModel(element);
         TypeDeclarationsType declarations = model.getTypeDeclarations();
         TypeDeclarationType declaration = declarations
               .getTypeDeclaration(structuredDataId);
         if (declaration == null)
         {
            String uri = AttributeUtil.getAttributeValue((DataType) element,
                  IConnectionManager.URI_ATTRIBUTE_NAME);
            if (uri != null)
            {
               if (!StringUtils.isEmpty(structuredDataId))
               {
                  IConnectionManager manager = model.getConnectionManager();
                  if (manager != null)
                  {
                     EObject externalModel = manager.find(uri);
                     if (externalModel != null
                           && externalModel instanceof IObjectReference)
                     {
                        declaration = (TypeDeclarationType) externalModel;
                     }
                  }
               }
            }
            if (declaration == null)
            {
               String message = DMS_Messages.DataValidator_InvalidType + structuredDataId;
               issues.add(new Issue(Issue.ERROR, element, message,
                     StructuredDataConstants.TYPE_DECLARATION_ATT));
            }
         }
      }
      return issues.toArray(new Issue[issues.size()]);
   }

   public BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath,
         DirectionType direction) throws ValidationException
   {
      if ( StringUtils.isEmpty(accessPath))
      {
         BridgeObject javaBridge = JavaDataTypeUtils.getBridgeObject(
               getInterfaceType().getName(), null, direction);
         return new DmsDocumentBridgeDescriptor(javaBridge.getEndClass(),
               javaBridge.getDirection());
      }
      else
      {
         PathEntry entry = new PathEntry(accessPoint, direction);
         Path path = new Path(entry);
         path.setMethod(accessPath);
         entry = path.getSelection();
         String[] typeNames = StructBridgeObject.computeActualTypeName(entry);
         if (entry.isSingle() && DmsSchemaProvider.DOCUMENT_COMPLEX_TYPE_NAME.equals(typeNames[0]))
         {
            return new DmsDocumentValidator().getBridgeObject(DmsTypeUtils.newDmsDocumentAccessPoint(
                  DmsTypeUtils.findModelFromContext(accessPoint), direction), null,
                  direction);
         }
         else if (entry.isSingle() && DmsSchemaProvider.FOLDER_COMPLEX_TYPE_NAME.equals(typeNames[0]))
         {
            return new DmsFolderValidator().getBridgeObject(DmsTypeUtils.newDmsFolderAccessPoint(
                  DmsTypeUtils.findModelFromContext(accessPoint), direction), null,
                  direction);
         }
         if (entry.isSingle() && DmsSchemaProvider.DOCUMENT_LIST_COMPLEX_TYPE_NAME.equals(typeNames[0]))
         {
            return new DmsDocumentListValidator().getBridgeObject(DmsTypeUtils.newDmsDocumentListAccessPoint(
                  DmsTypeUtils.findModelFromContext(accessPoint), null, direction), null,
                  direction);
         }
         else if (entry.isSingle() && DmsSchemaProvider.FOLDER_LIST_COMPLEX_TYPE_NAME.equals(typeNames[0]))
         {
            return new DmsFolderListValidator().getBridgeObject(DmsTypeUtils.newDmsFolderListAccessPoint(
                  DmsTypeUtils.findModelFromContext(accessPoint), null, direction), null,
                  direction);
         }
         else
         {
            return new StructBridgeObjectProvider().getBridgeObject(accessPoint,
                  accessPath, direction);
         }
      }
   }
}