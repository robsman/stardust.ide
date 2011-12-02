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
package org.eclipse.stardust.modeling.data.structured.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.actions.ISpiAction;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;

public class CreateEnumerationAction extends Action implements ISpiAction
{
   private TypeDeclarationType declaration;

   private IStructuredSelection selection;

   private WorkflowModelEditor editor;

   protected List<?> getSelectedObjects()
   {
      return selection instanceof IStructuredSelection
         ? ((IStructuredSelection) selection).toList()
         : Collections.EMPTY_LIST;
   }

   public void setConfiguration(IConfigurationElement config, WorkflowModelEditor editor,
         IStructuredSelection selection)
   {
      setId(config.getAttribute(SpiConstants.ID));
      setText(Structured_Messages.CreateEnumerationAction_ActionLabel);
      setImageDescriptor(DiagramPlugin.getImageDescriptor(
            "{org.eclipse.xsd.edit}icons/full/obj16/XSDSimpleTypeDefinition.gif")); //$NON-NLS-1$
      this.editor = editor;
      this.selection = selection;
   }
   
   public boolean isEnabled()
   {
      if (getSelectedObjects().size() != 1)
      {
         return false;
      }
      Object selection = getSelectedObjects().get(0);
      return selection instanceof TreeEditPart
            && ((TreeEditPart) selection).getModel() instanceof TypeDeclarationsType;
   }

   private TypeDeclarationsType getTypeDeclarationsType()
   {
      Object selection = getSelectedObjects().get(0);
      return (TypeDeclarationsType)((TreeEditPart) selection).getModel();
   }

   public void run()
   {
      editor.getEditDomain().getCommandStack().execute(createCommand());
   }

   private SetValueCmd createCommand()
   {
      IdFactory idFactory = new IdFactory(
            Structured_Messages.CreateEnumerationAction_BaseId,
            Structured_Messages.CreateEnumerationAction_BaseName,
            XpdlPackage.eINSTANCE.getTypeDeclarationType(),
            XpdlPackage.eINSTANCE.getTypeDeclarationType_Id(),
            XpdlPackage.eINSTANCE.getTypeDeclarationType_Name());  
      declaration = XpdlFactory.eINSTANCE.createTypeDeclarationType();      
      
      TypeDeclarationsType parent = getTypeDeclarationsType();
      idFactory.computeNames(parent.getTypeDeclaration());
      declaration.setId(idFactory.getId());
      declaration.setName(idFactory.getName());
      
      SchemaTypeType schema = XpdlFactory.eINSTANCE.createSchemaTypeType();      
      declaration.setSchemaType(schema);

      XSDSchema xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();
      xsdSchema.getQNamePrefixToNamespaceMap().put(XSDPackage.eNS_PREFIX, XMLResource.XML_SCHEMA_URI);
      xsdSchema.setSchemaForSchemaQNamePrefix(XSDPackage.eNS_PREFIX);
      ModelType model = (ModelType) parent.eContainer();
      xsdSchema.setTargetNamespace("http://www.infinity.com/bpm/model/" + model.getId() + "/" + declaration.getId()); //$NON-NLS-1$ //$NON-NLS-2$
      String prefix = TypeDeclarationUtils.computePrefix(declaration.getId(), xsdSchema.getQNamePrefixToNamespaceMap().keySet());
      xsdSchema.getQNamePrefixToNamespaceMap().put(prefix, xsdSchema.getTargetNamespace());
      xsdSchema.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX + declaration.getId());
      schema.setSchema(xsdSchema);
      
      XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
      xsdSimpleTypeDefinition.setName(declaration.getId());
      XSDSimpleTypeDefinition baseType = xsdSchema.resolveSimpleTypeDefinition(XMLResource.XML_SCHEMA_URI, "string"); //$NON-NLS-1$
      xsdSimpleTypeDefinition.setBaseTypeDefinition(baseType);
      xsdSchema.getContents().add(xsdSimpleTypeDefinition);
      
      XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
      xsdElementDeclaration.setName(declaration.getId());
      xsdElementDeclaration.setTypeDefinition(xsdSimpleTypeDefinition);
      xsdSchema.getContents().add(xsdElementDeclaration);
      
      return new SetValueCmd(parent,
            XpdlPackage.eINSTANCE.getTypeDeclarationsType_TypeDeclaration(), declaration)
      {
         public void redo()
         {
            super.redo();
            CreationUtils.showInOutlineAndEdit(declaration);
         }

         public void undo()
         {
            super.undo();
         }         
      };
   }
}