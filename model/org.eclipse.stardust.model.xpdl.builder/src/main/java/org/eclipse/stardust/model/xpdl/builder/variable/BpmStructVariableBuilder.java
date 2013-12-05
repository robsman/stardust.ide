/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.variable;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.WebModelerConnectionManager;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.modeling.repository.common.descriptors.ReplaceEObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;

public class BpmStructVariableBuilder
      extends AbstractModelElementBuilder<DataType, BpmStructVariableBuilder>
{
   ModelType typeDeclarationModel;
   
   public void setData(DataType data)
   {
      this.element = data;
   }
   
   public ModelType getTypeDeclarationModel()
   {
      return typeDeclarationModel;
   }

   public void setTypeDeclarationModel(ModelType typeDeclarationModel)
   {
      this.typeDeclarationModel = typeDeclarationModel;
   }

   public BpmStructVariableBuilder(ModelType model)
   {
      super(F_CWM.createDataType());

      forModel(model);

      DataTypeType structMetaType = ModelUtils.findIdentifiableElement(
            model.getDataType(), PredefinedConstants.STRUCTURED_DATA);
      if (null != structMetaType)
      {
         element.setType(structMetaType);
      }

      AttributeUtil.setAttribute(element, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR); //$NON-NLS-1$
      AttributeUtil.setBooleanAttribute(element, "carnot:engine:data:bidirectional", true); //$NON-NLS-1$      
   }

   @Override
   protected DataType finalizeElement()
   {
      super.finalizeElement();

      model.getData().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Data";
   }

   public static BpmStructVariableBuilder newStructVariable(ModelType model)
   {
      return new BpmStructVariableBuilder(model);
   }

   public BpmStructVariableBuilder ofType(String declId)
   {
      if(StringUtils.isEmpty(declId))
      {
         return this;
         //generateId();
         //declId = getGeneratedID();
      }
      
      if(getTypeDeclarationModel().getId().equals(model.getId()))
      {      
         AttributeUtil.setAttribute(element, StructuredDataConstants.TYPE_DECLARATION_ATT,
               declId);
      }
      else
      {
         TypeDeclarationType typeDeclaration = getTypeDeclaration(typeDeclarationModel, declId);

         String fileConnectionId = WebModelerConnectionManager.createFileConnection(model, typeDeclarationModel);
         
         String bundleId = CarnotConstants.DIAGRAM_PLUGIN_ID;         
         URI uri = URI.createURI("cnx://" + fileConnectionId + "/");
         
         ReplaceEObjectDescriptor descriptor = new ReplaceEObjectDescriptor(MergeUtils.createQualifiedUri(uri, typeDeclaration, true), element, 
               typeDeclaration.getId(), typeDeclaration.getName(), typeDeclaration.getDescription(),
               bundleId, null);
         
         
         AttributeUtil.setAttribute(element, IConnectionManager.URI_ATTRIBUTE_NAME, descriptor.getURI().toString());
         ExternalReferenceType reference = XpdlFactory.eINSTANCE.createExternalReferenceType();
         if (typeDeclarationModel != null)
         {
            reference.setLocation(ImportUtils.getPackageRef(descriptor, model, typeDeclarationModel).getId());
         }
         reference.setXref(declId);
         element.setExternalReference(reference);                           
      }
      
      return this;
   }
   
   /**
   *
   * @param modelId
   * @param appId
   * @return
   */
  private TypeDeclarationType getTypeDeclaration(ModelType model, String declId) {
     TypeDeclarationsType typeDeclarations = model.getTypeDeclarations();
     if(typeDeclarations != null)
     {
        List<TypeDeclarationType> decls = typeDeclarations.getTypeDeclaration();        
        for (TypeDeclarationType d : decls) {
           if (d.getId().equals(declId)) {
              return d;
           }
        }
     }

     return null;
  }
}