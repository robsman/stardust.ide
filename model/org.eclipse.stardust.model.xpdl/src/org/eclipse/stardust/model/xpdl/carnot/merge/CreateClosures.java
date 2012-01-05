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
package org.eclipse.stardust.model.xpdl.carnot.merge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaContent;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;
import ag.carnot.workflow.model.PredefinedConstants;

public class CreateClosures
{
   public List<EObject> computeClosure(EObject eObject, ModelType targetModel)
   {
      List<EObject> closure = new ArrayList<EObject>();
      Set<EObject> visited = new HashSet<EObject>(); // used to prevent infinity recursion while traversing connected graphs
      visit(closure, visited, eObject, null, targetModel);
      return closure;
   }
   
   private boolean isTopLevelElement(EObject element)
   {
      return element.eContainer() == null || element.eContainer() instanceof ModelType
         || element instanceof TypeDeclarationType;
   }

   protected boolean alreadyLinked(EObject element, ModelType model)
   {
      return false;
   }
   
   private void visit(List<EObject> closure, Set<EObject> visited, EObject element, EReference reference, ModelType targetModel)
   {
      if (element == null)
      {
         // nothing to visit here
         return;
      }
      if (element.eContainer() == null)
      {
         // element was already linked with reuse option
         return;
      }
      if (!visited.contains(element) && !(element instanceof ModelType))
      {
         visited.add(element);
         if ((element instanceof IIdentifiableModelElement || element instanceof TypeDeclarationType)
               && isTopLevelElement(element)
               && !alreadyLinked(element, targetModel))
         {
            closure.add(element);
         }
         
         if (element instanceof TypeDeclarationType)
         {
            // special case of imports in internal schemas:
            // - we no longer follow children and references by reflection;
            // - if SchemaType, analyze the imports and if internal visit the type;
            // - if ExternalReference, analize location and if internal visit the type;
            XpdlTypeType type = ((TypeDeclarationType) element).getDataType();
            if (type instanceof ExternalReferenceType)
            {
               visitTypeReference(closure, visited, (TypeDeclarationsType) element.eContainer(),
                     ((ExternalReferenceType) type).getLocation(), targetModel);
            }
            else if (type instanceof SchemaTypeType)
            {
               XSDSchema schema = ((SchemaTypeType) type).getSchema();
               if (schema != null)
               {
                  for (XSDSchemaContent item : schema.getContents())
                  {
                     if (item instanceof XSDImport)
                     {
                        visitTypeReference(closure, visited, (TypeDeclarationsType) element.eContainer(),
                              ((XSDImport) item).getSchemaLocation(), targetModel);
                     }
                  }
               }
            }
         }
         else // regular model elements
         {
            // special case of struct data objects, to be revised with #CRNT-9244
            if (element instanceof DataType)
            {
               DataType data = (DataType) element;
               if (data.getType() != null && PredefinedConstants.STRUCTURED_DATA.equals(data.getType().getId()))
               {
                  String typeId = AttributeUtil.getAttributeValue(data, StructuredDataConstants.TYPE_DECLARATION_ATT);
                  TypeDeclarationType decl = ModelUtils.findContainingModel(element)
                        .getTypeDeclarations().getTypeDeclaration(typeId);
                  if (decl != null)
                  {
                     visit(closure, visited, decl, null, targetModel);
                  }
               }
            }
            
            if(element instanceof ProcessDefinitionType)
            {
               FormalParameterMappingsType formalParameterMappings = ((ProcessDefinitionType) element).getFormalParameterMappings();
               FormalParametersType referencedParametersType = ((ProcessDefinitionType) element).getFormalParameters();
               if(referencedParametersType != null && formalParameterMappings != null)
               {      
                  for (Iterator<FormalParameterType> i = referencedParametersType.getFormalParameter().iterator(); i.hasNext();) {
                     FormalParameterType referencedParameterType = i.next();
                     DataType mappedData = formalParameterMappings.getMappedData(referencedParameterType);         
                     if(mappedData != null)
                     {
                        visit(closure, visited, mappedData, null, targetModel);                        
                     }
                  }
               }               
            }
            
            for (EObject child : element.eContents())
            {
               // special case of attributes that references other identifiables
               // TODO: #CRNT-9244
               visit(closure, visited, child, null, targetModel);
            }
   
            // process references
            EList<EObject> crossReferences = element.eCrossReferences();
            EContentsEList.FeatureIterator<EObject> references =
               (EContentsEList.FeatureIterator<EObject>) crossReferences.iterator();
            while (references.hasNext())
            {
               EObject target = references.next();
               EStructuralFeature ref = references.feature();
               if (!ref.isTransient()) // traverse only direct references, i.e. data from a data mapping and not reverse
               {
                  visit(closure, visited, target, reference, targetModel);
               }
            }
         }
      }
   }

   private void visitTypeReference(List<EObject> closure, Set<EObject> visited, TypeDeclarationsType declarations, String location,
         ModelType targetModel)
   {
      TypeDeclarationType decl = TypeDeclarationUtils.findTypeDeclarationByLocation(declarations, location);
      visit(closure, visited, decl, null, targetModel);
   }  
}