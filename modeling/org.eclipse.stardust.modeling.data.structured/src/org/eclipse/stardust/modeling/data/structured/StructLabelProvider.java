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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XpdlSwitch;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.data.structured.annotations.DefaultAnnotationModifier;
import org.eclipse.stardust.modeling.data.structured.annotations.IAnnotation;
import org.eclipse.stardust.modeling.data.structured.properties.DefaultValueModifier;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDPatternFacet;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.util.XSDSwitch;


public class StructLabelProvider extends LabelProvider
   implements ITableLabelProvider
{
   public static final String[] COMPLEX_TYPE_COLUMNS =
   {
      Structured_Messages.NameColumnLabel,
      Structured_Messages.TypeColumnLabel, 
      Structured_Messages.CardinalityColumnLabel,
      // Messages.DefaultValueColumnLabel
   };

   public static final String[] SIMPLE_TYPE_COLUMNS =
   {
      Structured_Messages.ValuesColumnLabel
   };
   
   public static final String[] CARDINALITY_LABELS =
   {
      Structured_Messages.CardinalityRequiredLabel,
      Structured_Messages.CardinalityOptionalLabel,
      Structured_Messages.CardinalityManyLabel,
      Structured_Messages.CardinalityAtLeastOneLabel
   };
   
   private XsdIconProvider xsdIconProvider;
   private XpdlIconProvider xpdlIconProvider;
   private XsdTextProvider xsdTextProvider;
   private XpdlTextProvider xpdlTextProvider;
   
   private int column;
   private Tree tree;
   private boolean showGroupInfo = false;
   private ModelType model;

   public StructLabelProvider()
   {
      xsdIconProvider = new XsdIconProvider();
      xpdlIconProvider = new XpdlIconProvider();
      xsdTextProvider = new XsdTextProvider();
      xpdlTextProvider = new XpdlTextProvider();
   }

   public StructLabelProvider(Tree tree)
   {
      this();
      this.tree = tree;
   }

   public void setShowGroupInfo(boolean showGroupInfo)
   {
      this.showGroupInfo = showGroupInfo;
   }

   public void setModel(ModelType model)
   {
      this.model = model;
   }

   public Image getImage(Object element)
   {
      return getColumnImage(element, 0);
   }

   public String getText(Object element)
   {
      return getColumnText(element, 0);
   }

   public Image getColumnImage(Object element, int columnIndex)
   {
      if (columnIndex == 0 && element instanceof EObject)
      {
         String iconLocation = xpdlIconProvider.doSwitch((EObject) element);
         if (iconLocation != null)
         {
            return DiagramPlugin.getImage(iconLocation);
         }
      }
      return null;
   }

   public String getColumnText(Object element, int columnIndex)
   {
      column = columnIndex;
      
      if (tree != null && element instanceof XSDElementDeclaration)
      {
         TreeColumn treeColumn = tree.getColumn(column);
         if (treeColumn.getData() instanceof IAnnotation)
         {
            IAnnotation annotation = (IAnnotation) treeColumn.getData();
            Object annotationValue = DefaultAnnotationModifier.getAnnotationValue(annotation, (XSDElementDeclaration) element);
            return annotationValue == null ? "" : annotationValue.toString(); //$NON-NLS-1$
         }
      }
      
      if (columnIndex == 3 && element instanceof XSDComponent)
      {
         return DefaultValueModifier.getStringForElement((XSDComponent) element);
      }      
      if (columnIndex == 1 && element instanceof XSDEnumerationFacet)
      {
         return DefaultValueModifier.getStringForElement((XSDComponent) element);
      }  
      
      /*if (element instanceof TypeDeclarationType)
      {
    	 TypeDeclarationType typeDecl = (TypeDeclarationType)element;
    	 ExternalReferenceType extRef = typeDecl.getExternalReference();
    	 if (extRef != null && showGroupInfo)
    	 {    		  
    	    String[] qualifier = extRef.getLocation().split(":");    		  
    	    return qualifier[2] + "/" + (String) xpdlTextProvider.doSwitch((EObject) element);  
    	 }
      }*/
      
      if (element instanceof EObject)
      {
         return (String) xpdlTextProvider.doSwitch((EObject) element);
      }
      return getDefaultLabel(element);
   }

   private String getDefaultLabel(Object element)
   {
      switch (column)
      {
         case 0: return element.toString();
      }
      return ""; //$NON-NLS-1$
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

   private class XpdlIconProvider extends XpdlSwitch<String>
   {
      public String caseTypeDeclarationType(TypeDeclarationType typeDeclaration)
      {
         return IconFactory.getDefault().getIconFor(typeDeclaration);
      }

      public String defaultCase(EObject object)
      {
         return xsdIconProvider.doSwitch(object);
      }
   }

   private class XsdIconProvider extends XSDSwitch<String>
   {
      public String caseXSDSchema(XSDSchema schema)
      {
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDSchema.gif"; //$NON-NLS-1$
      }

      public String caseXSDElementDeclaration(XSDElementDeclaration element)
      {
         return element.isElementDeclarationReference()
               ? "{org.eclipse.xsd.edit}icons/full/obj16/XSDElementUse.gif" //$NON-NLS-1$
               : "{org.eclipse.xsd.edit}icons/full/obj16/XSDElementDeclaration.gif"; //$NON-NLS-1$
      }

      public String caseXSDComplexTypeDefinition(XSDComplexTypeDefinition complexType)
      {
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDComplexTypeDefinition.gif"; //$NON-NLS-1$
      }

      public String caseXSDSimpleTypeDefinition(XSDSimpleTypeDefinition simpleType)
      {
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDSimpleTypeDefinition.gif"; //$NON-NLS-1$
      }

      public String caseXSDModelGroup(XSDModelGroup modelGroup)
      {
         switch (modelGroup.getCompositor().getValue())
         {
         case XSDCompositor.ALL: return "{org.eclipse.xsd.edit}icons/full/obj16/XSDModelGroupAll.gif"; //$NON-NLS-1$
         case XSDCompositor.CHOICE: return "{org.eclipse.xsd.edit}icons/full/obj16/XSDModelGroupChoice.gif"; //$NON-NLS-1$
         case XSDCompositor.SEQUENCE: return "{org.eclipse.xsd.edit}icons/full/obj16/XSDModelGroupSequence.gif"; //$NON-NLS-1$
         }
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDModelGroupUnresolved.gif"; //$NON-NLS-1$
      }

      public String caseXSDEnumerationFacet(XSDEnumerationFacet enumeration)
      {
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDEnumerationFacet.gif"; //$NON-NLS-1$
      }

      public String caseXSDPatternFacet(XSDPatternFacet pattern)
      {
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDPatternFacet.gif"; //$NON-NLS-1$
      }

      public String caseXSDAttributeDeclaration(XSDAttributeDeclaration attribute)
      {
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDAttributeDeclaration.gif"; //$NON-NLS-1$
      }

      public String caseXSDWildcard(XSDWildcard wildcard)
      {
         if (wildcard.eContainer() instanceof XSDComplexTypeDefinition)
         {
            return "{org.eclipse.xsd.edit}icons/full/obj16/XSDWildcardAttribute.gif"; //$NON-NLS-1$
         }
         return "{org.eclipse.xsd.edit}icons/full/obj16/XSDWildcardElement.gif"; //$NON-NLS-1$
      }
   }

   private class XpdlTextProvider extends XpdlSwitch<String>
   {
      public String caseTypeDeclarationType(TypeDeclarationType typeDeclaration)
      {
         switch (column)
         {
         case 0: 
            String name = typeDeclaration.getName();
            if (name == null)
            {
               name = typeDeclaration.getId();
               if (name == null)
               {
                  name = "<TypeDeclaration>"; //$NON-NLS-1$
               }
            }
            if (showGroupInfo)
            {
               ModelType model = ModelUtils.findContainingModel(typeDeclaration);
               if (model != StructLabelProvider.this.model)
               {
                  if (name.equals(StructuredTypeUtils.getResourceTypeDeclaration()
                        .getName()))
                  {
                     return name;
                  }
                  String modelName = model == null ? "null" : model.getName(); //$NON-NLS-1$
                  if (modelName == null)
                  {
                     modelName = model.getId();
                     if (modelName == null)
                     {
                        modelName = "<Model>"; //$NON-NLS-1$
                     }
                  }
                  return modelName + " / " + name; //$NON-NLS-1$
               }
            }
            return name;
         }
         return ""; //$NON-NLS-1$
      }

      public String defaultCase(EObject object)
      {
         return xsdTextProvider.doSwitch(object);
      }
   }

   private class XsdTextProvider extends XSDSwitch<String>
   {
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
               // TODO: (fh) is that correct ?
               return ref.getQName(element);
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
         case 0: return simpleType.getName();
         case 1: return simpleType.getBaseTypeDefinition().getName();
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
         switch (column)
         {
         case 0: return attribute.getName();
         case 1:
            if (attribute.getTypeDefinition() == null)
            {
               return("<unresolved>");
            }
            return attribute.getTypeDefinition().getName();
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
         return getDefaultLabel(object);
      }
   }
}