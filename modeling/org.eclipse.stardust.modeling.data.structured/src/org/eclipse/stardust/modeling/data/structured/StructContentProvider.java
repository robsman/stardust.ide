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
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XpdlSwitch;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XsdContentProvider;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

public class StructContentProvider implements ITreeContentProvider
{
   private EditableXsdContentProvider xsdProvider;

   private XpdlContentProvider xpdlProvider;

   public StructContentProvider(boolean showDirectContentOnly)
   {
      xsdProvider = new EditableXsdContentProvider(showDirectContentOnly);
      xpdlProvider = new XpdlContentProvider();
   }

   public void setNewElement(XSDSimpleTypeDefinition parent, XSDConstrainingFacet newElement)
   {
      xsdProvider.newElements.put(parent, newElement);
   }

   public void setNewElement(XSDModelGroup parent, XSDElementDeclaration newElement)
   {
      xsdProvider.newElements.put(parent, newElement);
   }

   public void removeNewElement(XSDSimpleTypeDefinition parent)
   {
      xsdProvider.newElements.remove(parent);
   }

   public void removeNewElement(XSDModelGroup parent)
   {
      xsdProvider.newElements.remove(parent);
   }

   public XSDModelGroup getEditingParent(XSDElementDeclaration element)
   {
      for (Iterator<Map.Entry<XSDComponent, XSDComponent>> itr = xsdProvider.newElements.entrySet().iterator(); itr.hasNext();)
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
      return XsdContentProvider.EMPTY_ARRAY;
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
         return XsdContentProvider.EMPTY_ARRAY;
      }

      public Object defaultCase(EObject object)
      {
         return xsdProvider.doSwitch(object);
      }
   }
   
   private static class EditableXsdContentProvider extends XsdContentProvider
   {
      /* Editing support */
      private Map<XSDComponent, XSDComponent> newElements = CollectionUtils.newMap();

      private EditableXsdContentProvider(boolean showDirectContentOnly)
      {
         super(showDirectContentOnly);
      }

      @SuppressWarnings("unchecked")
      protected <T> T getNewItem(XSDComponent parent)
      {
         return (T) newElements.get(parent);
      }
   }
}
