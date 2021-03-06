/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.xpdl2.util;

import org.eclipse.emf.ecore.EObject;
//import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.xsd.*;
import org.eclipse.xsd.util.XSDSwitch;

public class XsdTextProvider extends XSDSwitch<String>
{
   private static final String[] DEFAULT_CARDINALITY_LABELS =
   {
      "required",//Structured_Messages.CardinalityRequiredLabel, //$NON-NLS-1$
      "optional",//Structured_Messages.CardinalityOptionalLabel, //$NON-NLS-1$
      "many",//Structured_Messages.CardinalityManyLabel, //$NON-NLS-1$
      "at least one"//Structured_Messages.CardinalityAtLeastOneLabel //$NON-NLS-1$
   };
   
   private int column;
   
   public String[] CARDINALITY_LABELS = DEFAULT_CARDINALITY_LABELS;

   public void setColumn(int column)
   {
      this.column = column;
   }

   public String caseXSDSchema(XSDSchema schema)
   {
      switch (column)
      {
      case 0: return "schema"; //$NON-NLS-1$
      case 1: return schema.getTargetNamespace();
      }
      return ""; //$NON-NLS-1$
   }

   public String caseXSDElementDeclaration(XSDElementDeclaration element)
   {
      XSDElementDeclaration ref = null;
      if (element.isElementDeclarationReference())
      {
         ref = element.getResolvedElementDeclaration();
      }
      switch (column)
      {
      case 0: return ref == null ? element.getName() : ref.getName();
      case 1:
         if (ref != null)
         {
            XSDTypeDefinition type = ref.getTypeDefinition();
            if (type == ref.getAnonymousTypeDefinition())
            {
               if (type instanceof XSDSimpleTypeDefinition)
               {
                  type = type.getBaseType();
               }
               else if (type instanceof XSDComplexTypeDefinition)
               {
                  return ref.getQName(element);
               }
            }
            return type.getQName(element);
         }
         XSDTypeDefinition type = element.getTypeDefinition();
         if (type != null)
         {
            return type.getQName(element);
         }
         break;
      case 2:
         int cardinalityIndex = getCardinalityIndex(element);
         if (cardinalityIndex >= 0)
         {
            return CARDINALITY_LABELS[cardinalityIndex];
         }
         else if (element.eContainer() instanceof XSDParticle)
         {
            XSDParticle particle = (XSDParticle) element.eContainer();
            int minOccurs = particle.getMinOccurs();
            int maxOccurs = particle.getMaxOccurs();
            if (maxOccurs == XSDParticle.UNBOUNDED)
            {
               return Integer.toString(minOccurs) + "..*";  //$NON-NLS-1$
            }
            else
            {
               return Integer.toString(minOccurs) + ".." + Integer.toString(maxOccurs);  //$NON-NLS-1$
            }
         }
      }
      return ""; //$NON-NLS-1$
   }

   public String caseXSDSimpleTypeDefinition(XSDSimpleTypeDefinition simpleType)
   {
      switch (column)
      {
      case 0:
         String name = simpleType.getName();
         return name == null ? "<value>" : name;
      case 1: return simpleType.getBaseTypeDefinition().getQName(simpleType);
      }
      return ""; //$NON-NLS-1$
   }

   public String caseXSDComplexTypeDefinition(XSDComplexTypeDefinition complexType)
   {
      switch (column)
      {
      case 0: return complexType.getName();
      case 1:
         XSDComplexTypeContent content = complexType.getContent();
         if (content instanceof XSDParticle)
         {
            // TODO:
            return ""; //$NON-NLS-1$
         }
         return complexType.getBaseTypeDefinition().getName();
      }
      return ""; //$NON-NLS-1$
   }

   public String caseXSDConstrainingFacet(XSDConstrainingFacet facet)
   {
      switch (column)
      {
//         case 0: return '<' + facet.getFacetName() + '>';
//         case 1: return facet.getLexicalValue();
      case 0: return facet.getLexicalValue();
      }
      return ""; //$NON-NLS-1$
   }

   public String caseXSDModelGroup(XSDModelGroup model)
   {
      switch (column)
      {
      case 0: return '<' + model.getCompositor().getName() + '>';
      }
      return ""; //$NON-NLS-1$
   }

   public String caseXSDAttributeDeclaration(XSDAttributeDeclaration attribute)
   {
      XSDAttributeDeclaration resolvedAttribute = attribute.getResolvedAttributeDeclaration();
      switch (column)
      {
      case 0:
         return resolvedAttribute.getName();
      case 1:
         XSDSimpleTypeDefinition typeDefinition = resolvedAttribute.getTypeDefinition();
         if (typeDefinition == null)
         {
            return("<unresolved>"); //$NON-NLS-1$
         }
         if (typeDefinition.getName() == null)
         {
            typeDefinition = typeDefinition.getBaseTypeDefinition();
         }
         return typeDefinition.getQName(attribute);
      case 2:
         int cardinalityIndex = getCardinalityIndex(attribute);
         if (cardinalityIndex >= 0)
         {
            return XSDAttributeUseCategory.get(cardinalityIndex).getLiteral();
         }
      }
      return ""; //$NON-NLS-1$
   }

   public String caseXSDWildcard(XSDWildcard wildcard)
   {
      switch (column)
      {
      case 0:
         String label = "any"; //$NON-NLS-1$
         if (((XSDWildcard) wildcard).eContainer() instanceof XSDComplexTypeDefinition)
         {
            label += "Attribute"; //$NON-NLS-1$
         }
         return '<' + label + '>';
      }
      return ""; //$NON-NLS-1$
   }
   
   public String defaultCase(EObject object)
   {
      return column == 0 ? object.toString() : ""; //$NON-NLS-1$
   }

   public static int getCardinalityIndex(XSDConcreteComponent term)
   {
      if (term instanceof XSDTerm && term.eContainer() instanceof XSDParticle)
      {
         XSDParticle particle = (XSDParticle) term.eContainer();
         int minOccurs = particle.getMinOccurs();
         int maxOccurs = particle.getMaxOccurs();
         if (maxOccurs == XSDParticle.UNBOUNDED || maxOccurs > 1)
         {
            if (minOccurs == 0)
            {
               return 2;
            }
            else
            {
               return 3;
            }
         }
         else
         {
            if (minOccurs == 0)
            {
               return 1;
            }
            else
            {
               return 0;
            }
         }
      }
      else if (term instanceof XSDAttributeDeclaration)
      {
         XSDAttributeUse xsdAttributeUse = (XSDAttributeUse) term.eContainer();
         // can be null for unresolved references
         if (xsdAttributeUse != null)
         {
            XSDAttributeUseCategory category = xsdAttributeUse.getUse();
            if (category != null)
            {
               return category.getValue();
            }
         }
      }
      return -1;
   }
}
