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
package org.eclipse.stardust.modeling.data.structured.annotations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamedComponent;


public class AnnotationContentProvider implements ITreeContentProvider
{
   private static final String APPLICATION_INFO_EXTENSION_ID = 
         "org.eclipse.stardust.modeling.data.structured.applicationInfo"; //$NON-NLS-1$
   private static final String IPP_CATEGORY_ID = APPLICATION_INFO_EXTENSION_ID.substring(0, 
         APPLICATION_INFO_EXTENSION_ID.lastIndexOf('.')); //$NON-NLS-1$
   
   private TypeDeclarationType declaration;
   private boolean isInternalSchema;
   private XSDNamedComponent root;
   private ArrayList<CategoryAnnotation> elements;
   private boolean filter;

   public void setDeclaration(TypeDeclarationType declaration)
   {
      this.declaration = declaration;
      isInternalSchema = TypeDeclarationUtils.isInternalSchema(declaration);
      root = TypeDeclarationUtils.findElementOrTypeDeclaration(declaration);
   }

   public Object[] getChildren(Object element)
   {
      IAnnotation annotation = (IAnnotation) element;
      List<IAnnotation> children = annotation.getChildren();
      return children.toArray();
   }

   public Object getParent(Object element)
   {
      IAnnotation annotation = (IAnnotation) element;
      return annotation.getParent();
   }

   public boolean hasChildren(Object element)
   {
      IAnnotation annotation = (IAnnotation) element;
      return !annotation.getChildren().isEmpty();
   }

   public Object[] getElements(Object inputElement)
   {
      ArrayList<CategoryAnnotation> result = new ArrayList<CategoryAnnotation>(elements.size());
      for (int i = 0; i < elements.size(); i++)
      {
         CategoryAnnotation category = elements.get(i);
         if (!filter || !IPP_CATEGORY_ID.equals(category.getId()))
         {
            result.add(category);
         }
      }
      return result.toArray();
   }

   public void dispose()
   {
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
   }

   public void setCurrentElement(XSDElementDeclaration decl)
   {
      filter = decl != null && decl.getType() instanceof XSDComplexTypeDefinition;
      if (elements == null)
      {
         String extensionId = APPLICATION_INFO_EXTENSION_ID; //$NON-NLS-1$
         IConfigurationElement[] configs = Platform.getExtensionRegistry().getConfigurationElementsFor(extensionId);
         elements = new ArrayList<CategoryAnnotation>();
         for (int i = 0; i < configs.length; i++)
         {
            String name = configs[i].getName();
            if ("category".equals(name)) //$NON-NLS-1$
            {
               elements.add(new CategoryAnnotation(this, decl, configs[i]));
            }
         }
      }
      else
      {
         for (CategoryAnnotation annotation : elements)
         {
            annotation.setElementDeclaration(decl);
         }
      }
   }

   public void removeIPPAnnotations(XSDElementDeclaration decl)
   {
      CategoryAnnotation ippCategory = getIPPCategory(decl);
      ElementAnnotation annotation = getStorageAnnotation(ippCategory);
      if (annotation != null && annotation.exists())
      {
         DefaultAnnotationModifier.deleteAnnotation(annotation);
      }
   }

   private ElementAnnotation getStorageAnnotation(CategoryAnnotation category)
   {
      List<IAnnotation> children = category.getChildren();
      for (int i = 0; i < children.size(); i++)
      {
         ElementAnnotation annotation = (ElementAnnotation) children.get(i);
         if ("storage".equals(annotation.getConfigurationAttribute("name"))) //$NON-NLS-1$ //$NON-NLS-2$
         {
            return annotation;
         }
      }
      return null;
   }

   private CategoryAnnotation getIPPCategory(XSDElementDeclaration decl)
   {
      String extensionId = APPLICATION_INFO_EXTENSION_ID; //$NON-NLS-1$
      IConfigurationElement[] configs = Platform.getExtensionRegistry().getConfigurationElementsFor(extensionId);
      for (int i = 0; i < configs.length; i++)
      {
         String name = configs[i].getName();
         if ("category".equals(name)) //$NON-NLS-1$
         {
            if (IPP_CATEGORY_ID.equals(configs[i].getAttribute("id"))) //$NON-NLS-1$
            {
               return new CategoryAnnotation(this, decl, configs[i]);
            }
         }
      }
      return null;
   }

   public TypeDeclarationType getDeclaration()
   {
      return declaration;
   }

   public boolean isInternalSchema()
   {
      return isInternalSchema;
   }

   public XSDNamedComponent getRoot()
   {
      return root;
   }
}