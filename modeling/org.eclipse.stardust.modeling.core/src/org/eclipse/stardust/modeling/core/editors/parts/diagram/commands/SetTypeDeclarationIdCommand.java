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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.XSDTypeDefinition;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;

/**
 * @author grotjahn
 * @version $Revision: 17465 $
 */
public class SetTypeDeclarationIdCommand extends Command
{
   private TypeDeclarationType declaration;
   private ModelType model;
   
   private String newId;
   private String previousId;
   private boolean duplicateId = false;

   private Command command;
   
   public SetTypeDeclarationIdCommand(TypeDeclarationType decl, String id)
   {      
      declaration = decl;
      model = (ModelType) declaration.eContainer().eContainer();
            
      previousId = declaration.getId();
      newId = id;
      
      ChangeRecorder targetRecorder = new ChangeRecorder();
      targetRecorder.beginRecording(Collections.singleton(declaration.eContainer()));      
      
      if(!newId.equals(previousId))
      {
         checkDuplicates();
         changeId();
         if(!duplicateId)
         {
            fixOtherDeclarations();
         }
      }    

      final ChangeDescription change = targetRecorder.endRecording();
      targetRecorder.dispose();
      command = new Command()
      {
         public void execute()
         {
         }

         public void undo()
         {
            change.applyAndReverse();
         }

         public void redo()
         {
            change.applyAndReverse();
         }
      };
   }

   public void redo()
   {
      command.redo();
   }

   public void undo()
   {
      command.undo();
   }
   
   private void checkDuplicates()
   {
      for(TypeDeclarationType decl : model.getTypeDeclarations().getTypeDeclaration())
      {
         if(!decl.equals(declaration))
         {
            if(decl.getId().equals(newId))
            {
               duplicateId = true;
               break;
            }            
         }
      }      
   }   
   
   private void changeId()
   {
      List<XSDSchemaDirective> savedDirectives = new ArrayList<XSDSchemaDirective>();
      savedDirectives.addAll(declaration.getSchema().getReferencingDirectives());
      String oldId = declaration.getId();
      declaration.setId(newId);
      
      if (TypeDeclarationUtils.isInternalSchema(declaration))
      {
         XSDSchema xsdSchema = declaration.getSchema();
         String oldTargetNamespace = xsdSchema.getTargetNamespace();
         
         xsdSchema.setTargetNamespace(TypeDeclarationUtils.computeTargetNamespace(ModelUtils.findContainingModel(declaration), newId));         
         
         String prefix = TypeDeclarationUtils.computePrefix(declaration.getId(), xsdSchema.getQNamePrefixToNamespaceMap().keySet());
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
            TypeDeclarationUtils.updateImports(xsdSchema, oldTargetNamespace, oldId, newId);
         }
         xsdSchema.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX + newId);
         xsdSchema.eSet(XSDPackage.eINSTANCE.getXSDSchema_ReferencingDirectives(), savedDirectives);  
         XSDNamedComponent component = TypeDeclarationUtils.findElementOrTypeDeclaration(declaration, previousId);
         if (component != null)
         {
            component.setName(newId);
         }
         if (component instanceof XSDElementDeclaration)
         {
            XSDElementDeclaration element = (XSDElementDeclaration) component;
            if (!element.isElementDeclarationReference() && element.getAnonymousTypeDefinition() == null)
            {
               XSDTypeDefinition type = element.getTypeDefinition();
               if (type != null && type.getSchema() == xsdSchema)
               {
                  type.setName(newId);
               }
            }
         }         
      }
      
      Set<XSDElementDeclaration> elements = new HashSet<XSDElementDeclaration>();
      XSDTypeDefinition definition = TypeDeclarationUtils.getTypeDefinition(model.getTypeDeclarations(), newId);
      for(TypeDeclarationType decl : ((TypeDeclarationsType) declaration.eContainer()).getTypeDeclaration()) {
         TypeDeclarationUtils.findElementsForType(decl, elements, oldId);
      }
      for (Iterator<XSDElementDeclaration> i = elements.iterator(); i.hasNext();) {
         XSDElementDeclaration elementDecl = i.next();
         elementDecl.setTypeDefinition(definition);
      }
      declaration.getSchema().eSet(XSDPackage.eINSTANCE.getXSDSchema_ReferencingDirectives(), savedDirectives);  
   }   

   private void fixOtherDeclarations()
   {
      for(TypeDeclarationType decl : model.getTypeDeclarations().getTypeDeclaration())
      {
         if(!decl.equals(declaration))
         {
            XpdlTypeType type = decl.getDataType();
            if(TypeDeclarationUtils.fixImport(decl, newId, previousId) && type instanceof SchemaTypeType)
            {
               TypeDeclarationUtils.updateTypeDefinition(decl, newId, previousId);            
            }
         }
      }      
   }   
}