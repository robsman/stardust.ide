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
import org.eclipse.stardust.model.xpdl.xpdl2.util.XsdIconProvider;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XsdTextProvider;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.parts.IconFactory;
import org.eclipse.stardust.modeling.data.structured.annotations.DefaultAnnotationModifier;
import org.eclipse.stardust.modeling.data.structured.annotations.IAnnotation;
import org.eclipse.stardust.modeling.data.structured.properties.DefaultValueModifier;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.xsd.*;

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
   
   public static final String[] DEFAULT_CARDINALITY_LABELS =
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
      xsdTextProvider.CARDINALITY_LABELS = DEFAULT_CARDINALITY_LABELS;
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
      xsdTextProvider.setColumn(columnIndex); 
      
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
      
      if (columnIndex == 1 && element instanceof XSDEnumerationFacet || columnIndex == 3 && element instanceof XSDComponent)
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
         String xpdlProviderText = (String) xpdlTextProvider.doSwitch((EObject) element);
         String xpdli18nText = getTextForProviderText(xpdlProviderText);
         if (xpdli18nText == null)
         {
            return xpdlProviderText;
         }
         return xpdli18nText;
      }
      return getDefaultLabel(element);
   }

   private String getTextForProviderText(String xpdlProviderText)
   {
      if (xpdlProviderText.equalsIgnoreCase("<sequence>")) { //$NON-NLS-1$
         return Structured_Messages.LBL_Sequence;
      }
      // Further types
      // ...
      return null;
   }

   private String getDefaultLabel(Object element)
   {
      switch (column)
      {
         case 0: return element.toString();
      }
      return ""; //$NON-NLS-1$
   }

   private class XpdlIconProvider extends XpdlSwitch<String>
   {
      public String caseTypeDeclarationType(TypeDeclarationType typeDeclaration)
      {
         return IconFactory.getDefault().getIconFor(typeDeclaration);
      }

      public String defaultCase(EObject object)
      {
         return xsdIconProvider.doSwitch(object).getQualifiedName();
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
}