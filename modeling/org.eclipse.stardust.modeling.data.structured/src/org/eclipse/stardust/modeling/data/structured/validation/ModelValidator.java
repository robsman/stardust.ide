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
package org.eclipse.stardust.modeling.data.structured.validation;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.ExtendedAttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.stardust.modeling.validation.IModelValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;

import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

public class ModelValidator implements IModelValidator
{
   private static final Issue[] ISSUE_ARRAY = new Issue[0];

   private boolean canResolveExternalReference(String location, IProject project)
   {
      try
      {
         new URL(location);
         // if it's a real url do nothing
         return true;
      }
      catch (MalformedURLException e)
      {
         //try to find in project itself and in all required projects
         try
         {
            if (project.hasNature(JavaCore.NATURE_ID))
            {
               IJavaProject javaProject = JavaCore.create(project);
               if (GenericUtils.getFile(javaProject, location, true) != null)
               {
                  return true;
               }
            }
         }
         catch (CoreException e1)
         {
            e1.printStackTrace();
         }
      }

      return false;
   }

   // validate references
   public Issue[] validate(ModelType model) throws ValidationException
   {
      IProject project = ModelUtils.getProjectFromEObject(model);

      List<Issue> result = CollectionUtils.newList();
      TypeDeclarationsType declarations = model.getTypeDeclarations();
      List<TypeDeclarationType> allDeclarations = declarations.getTypeDeclaration();
      for (TypeDeclarationType declaration : allDeclarations)
      {
         Issue duplicateId = checkTypeDeclaration(declaration, allDeclarations);
         if (duplicateId != null)
         {
            result.add(duplicateId);
         }

         XpdlTypeType dataType = declaration.getDataType();
         if (dataType instanceof SchemaTypeType || dataType instanceof ExternalReferenceType)
         {
            if (dataType instanceof ExternalReferenceType)
            {
               String location = ((ExternalReferenceType) dataType).getLocation();
               if (location != null && !location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
               {
                  boolean canResolveExternalReference = canResolveExternalReference(
                        location, project);
                  if (!canResolveExternalReference)
                  {
                     result.add(Issue.error(declaration, MessageFormat.format(
                           "TypeDeclaration ''{0}'': imported file ''{1}'' not found.", //$NON-NLS-1$
                           declaration.getId(), location)));
                  }
               }
            }

            if (dataType instanceof SchemaTypeType)
            {
               XSDSchema schema = declaration.getSchema();
               if (schema != null)
               {
                  schema.validate();
                  List<XSDDiagnostic> diagnostics = schema.getDiagnostics();
                  schema.clearDiagnostics();
                  for (XSDDiagnostic diagnostic : diagnostics)
                  {
                     String message = diagnostic.getMessage();
                     result.add(Issue.error(declaration, message));
                  }

                  List<XSDImport> xsdImports = TypeDeclarationUtils.getImports(schema);
                  if (xsdImports != null)
                  {
                     for (XSDImport xsdImport : xsdImports)
                     {
                        String location = ((XSDImport) xsdImport).getSchemaLocation();
                        if (location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
                        {
                           String typeId = location.substring(StructuredDataConstants.URN_INTERNAL_PREFIX.length());
                           if (declarations.getTypeDeclaration(typeId) == null)
                           {
                              result.add(Issue.error(declaration,
                                    MessageFormat.format("TypeDeclaration ''{0}'': referenced type ''{1}'' not found.", //$NON-NLS-1$
                                          declaration.getId(), typeId)));
                           }
                        }
                     }
                  }

                  checkEnumeration(declaration, result);
               }
            }
         }
      }
      return result.toArray(ISSUE_ARRAY);
   }

   private Issue checkTypeDeclaration(TypeDeclarationType checkDeclaration, List<TypeDeclarationType> allDeclarations)
   {
      for (TypeDeclarationType declaration : allDeclarations)
      {
         if (!declaration.equals(checkDeclaration))
         {
            if (declaration.getId().equals(checkDeclaration.getId()))
            {
               return Issue.error(checkDeclaration, MessageFormat.format("TypeDeclaration ''{0}'' has duplicate Id.", //$NON-NLS-1$
                     checkDeclaration.getId()));
            }
         }
      }
      return null;
   }

   private void checkEnumeration(TypeDeclarationType checkDeclaration, List<Issue> result)
   {
      XSDNamedComponent component = TypeDeclarationUtils.getSimpleType(checkDeclaration);
      if (component instanceof XSDSimpleTypeDefinition)
      {
         if(ExtendedAttributeUtil.getAttribute(checkDeclaration, CarnotConstants.CLASS_NAME_ATT) != null)
         {
            ModelType model = ModelUtils.findContainingModel(checkDeclaration);
            String className = ExtendedAttributeUtil.getAttributeValue(checkDeclaration, CarnotConstants.CLASS_NAME_ATT);

            if(StringUtils.isEmpty(className))
            {
               result.add(Issue.error(checkDeclaration, Structured_Messages.ERROR_MSG_NO_CLASSNAME));
            }
            else
            {
               if(model != null && TypeFinder.getProjectFromEObject(model) != null)
               {
                  TypeFinder finder = new TypeFinder(model);
                  TypeInfo type = finder.findType(className);
                  if (type == null)
                  {
                     result.add(Issue.error(checkDeclaration,
                           MessageFormat.format(Structured_Messages.ERROR_MSG_INVALID_CLASSNAME,
                           className)));
                  }
               }
            }
         }
      }
   }
}