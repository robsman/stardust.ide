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
package org.eclipse.stardust.modeling.data.structured;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XpdlSwitch;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.util.XSDSwitch;

public class StructContentProvider implements ITreeContentProvider
{
   private static EObject[] EMPTY_ARRAY = new EObject[0];
   
   private boolean showDirectContentOnly;

   private XsdContentProvider xsdProvider;

   private XpdlContentProvider xpdlProvider;

   public StructContentProvider(boolean showDirectContentOnly)
   {
      this.showDirectContentOnly = showDirectContentOnly;
      xsdProvider = new XsdContentProvider();
      xpdlProvider = new XpdlContentProvider();
   }

   /* Editing support */
   private Map<XSDComponent, XSDComponent> newElements = CollectionUtils.newMap();

   public void setNewElement(XSDSimpleTypeDefinition parent, XSDConstrainingFacet newElement)
   {
      newElements.put(parent, newElement);
   }

   public void setNewElement(XSDModelGroup parent, XSDElementDeclaration newElement)
   {
      newElements.put(parent, newElement);
   }

   public void removeNewElement(XSDModelGroup parent)
   {
      newElements.remove(parent);
   }

   public XSDModelGroup getEditingParent(XSDElementDeclaration element)
   {
      for (Iterator<Map.Entry<XSDComponent, XSDComponent>> itr = newElements.entrySet().iterator(); itr.hasNext();)
      {
         Map.Entry<XSDComponent, XSDComponent> entry = itr.next();
         if (element.equals(entry.getValue()))
         {
            return (XSDModelGroup) entry.getKey();
         }
      }
      return null;
   }

   public Object getParent(Object element)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean hasChildren(Object element)
   {
      return getChildren(element).length > 0;
   }

   public Object[] getElements(Object inputElement)
   {
      // special cases for multi schema content
      if (inputElement instanceof Object[])
      {
         return (Object[]) inputElement;
      }
      if (inputElement instanceof Collection)
      {
         return ((Collection<?>) inputElement).toArray();
      }
      // standard case, the input is a schema or a schema element
      return getChildren(inputElement);
   }

   public void dispose()
   {
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
   }

   public Object[] getChildren(Object parent)
   {
      if (parent instanceof EObject)
      {
         return (Object[]) xpdlProvider.doSwitch((EObject) parent);
      }
      return EMPTY_ARRAY;
   }

   private class XpdlContentProvider extends XpdlSwitch<Object>
   {
      public Object caseTypeDeclarationsType(TypeDeclarationsType declarations)
      {
         // content of TypeDeclarations is the TypeDeclaration list.
         return declarations.getTypeDeclaration().toArray();
      }

      public Object caseTypeDeclarationType(TypeDeclarationType declaration)
      {
         // content of TypeDeclaration is the content of the actual type.
         XSDNamedComponent decl = TypeDeclarationUtils.findElementOrTypeDeclaration(declaration);
         if (decl != null)
         {
            return xsdProvider.doSwitch(decl);
         }
         // TODO: non schema types ?
         return EMPTY_ARRAY;
      }

      public Object defaultCase(EObject object)
      {
         return xsdProvider.doSwitch(object);
      }
   }
   
   private class XsdContentProvider extends XSDSwitch<EObject[]>
   {
      public EObject[] caseXSDSchema(XSDSchema schema)
      {
    	 String targetNamespace = schema.getTargetNamespace();
    	 if (targetNamespace == null)
    	 {
    		targetNamespace = ""; //$NON-NLS-1$
    	 }
         List<XSDElementDeclaration> elements = schema.getElementDeclarations();
         List<XSDTypeDefinition> types = schema.getTypeDefinitions();
         List<XSDNamedComponent> all = CollectionUtils.newList(elements.size() + types.size());
         addElements(all, targetNamespace, elements);
         addElements(all, targetNamespace, types);
         return (EObject[]) all.toArray(new EObject[all.size()]);
      }

      private void addElements(List<XSDNamedComponent> targetList, String targetNamespace, List<? extends XSDNamedComponent> sourceList)
      {
         for (XSDNamedComponent element : sourceList)
         {
       	    String namespace = element.getTargetNamespace();
            if (targetNamespace.equals(namespace == null ? "" : namespace)) //$NON-NLS-1$
            {
               targetList.add(element);
            }
         }
      }

      public EObject[] caseXSDElementDeclaration(XSDElementDeclaration element)
      {
         if (!showDirectContentOnly && element.isElementDeclarationReference())
         {
            element = element.getResolvedElementDeclaration();
         }
         XSDTypeDefinition type = element.getAnonymousTypeDefinition();
         if (type == null && !showDirectContentOnly)
         {
            type = element.getTypeDefinition();
         }
         return type == null ? new EObject[0] : doSwitch(type);
      }

      public EObject[] caseXSDComplexTypeDefinition(XSDComplexTypeDefinition type)
      {
         EObject[] content = EMPTY_ARRAY;
         if (type.getContent() != null)
         {
            content = doSwitch(type.getContent());
         }
         List<XSDComponent> result = CollectionUtils.newList();
         XSDWildcard wildcard = type.getAttributeWildcardContent();
         List<XSDAttributeGroupContent> attributes = type.getAttributeContents();
         addAttributes(result, attributes);
         if (wildcard != null)
         {
            result.add(wildcard);
         }
         EObject[] resultArray = new EObject[result.size() + content.length];
         result.toArray(resultArray);
         System.arraycopy(content, 0, resultArray, result.size(), content.length);
         return resultArray;
      }

      private void addAttributes(List<XSDComponent> result, List<XSDAttributeGroupContent> attributes)
      {
         // TODO: (fh) passing resolved declaration blocks type modification for unresolved elements, because eContainer is null.
         for (XSDAttributeGroupContent attribute : attributes)
         {
            if (attribute instanceof XSDAttributeUse)
            {
               XSDAttributeDeclaration attr = ((XSDAttributeUse) attribute).getContent(); //getAttributeDeclaration());
               if (attr.isAttributeDeclarationReference())
               {
                  attr = attr.getResolvedAttributeDeclaration();
               }
               result.add(attr);
            }
            else if (attribute instanceof XSDAttributeGroupDefinition)
            {
               XSDAttributeGroupDefinition group = (XSDAttributeGroupDefinition) attribute;
               if (group.isAttributeGroupDefinitionReference())
               {
                  group = group.getResolvedAttributeGroupDefinition();
               }
               addAttributes(result, group.getContents());
            }
         }
      }

      public EObject[] caseXSDSimpleTypeDefinition(XSDSimpleTypeDefinition type)
      {
         // TODO:
         List<? extends XSDConstrainingFacet> elements = type.getEnumerationFacets();
         if (elements.isEmpty())
         {
            elements = type.getPatternFacets();
         }
         XSDConstrainingFacet newElement = (XSDConstrainingFacet) newElements.get(type);
         int size = elements.size();
         if (newElement != null)
         {
            size++;
         }
         EObject[] result = new EObject[size];
         for (int i = 0; i < elements.size(); i++)
         {
            result[i] = elements.get(i);
         }
         if (newElement != null)
         {
            result[result.length - 1] = newElement;
         }
         return result;
      }

      public EObject[] caseXSDModelGroup(XSDModelGroup model)
      {
         List<Object> result = CollectionUtils.newList();
         List<XSDParticle> particles = model.getContents();
         addModelGroupContent(result, particles);
         XSDTerm newElement = (XSDTerm) newElements.get(model);
         if (newElement != null)
         {
            result.add(newElement);
         }
         return result.toArray(new EObject[result.size()]);
      }

      private void addModelGroupContent(List<Object> result, List<XSDParticle> particles)
      {
         for (XSDParticle particle : particles)
         {
            XSDParticleContent content = particle.getContent();
            if (content instanceof XSDModelGroupDefinition)
            {
               XSDModelGroupDefinition group = (XSDModelGroupDefinition) content;
               if (group.isModelGroupDefinitionReference())
               {
                  group = group.getResolvedModelGroupDefinition();
               }
               EObject[] children = caseXSDModelGroup(group.getModelGroup());
               for (int j = 0; j < children.length; j++)
               {
                  result.add(children[j]);
               }
            }
            else
            {
               result.add(content);
            }
         }
      }

      public EObject[] caseXSDParticle(XSDParticle particle)
      {
         XSDParticleContent content = particle.getContent();
         if (content instanceof XSDModelGroupDefinition)
         {
            XSDModelGroupDefinition group = (XSDModelGroupDefinition) content;
            if (group.isModelGroupDefinitionReference())
            {
               group = group.getResolvedModelGroupDefinition();
            }
            return caseXSDModelGroup(group.getModelGroup());
         }
         return new EObject[] {particle.getContent()};
      }

      public EObject[] defaultCase(EObject object)
      {
         return EMPTY_ARRAY;
      }
   }
}
