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
package org.eclipse.stardust.model.xpdl.xpdl2.util;

import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.XSDMapping;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;

public class XSDElementCheckForType
{
   public static boolean needsType;
   public static XSDTypeDefinition checkDefinition;
   public static String oldDefName;

   public static boolean needsType(XSDComplexTypeDefinition complexType, XSDTypeDefinition oldDef)
   {  
      needsType = false;
      checkDefinition = oldDef;
      if(checkDefinition instanceof XSDSimpleTypeDefinition)
      {
         String javaTypeName = XSDMapping.getJavaTypeForXSDType(checkDefinition.getName());
         if(!StringUtils.isEmpty(javaTypeName))
         {
            return true;
         }
      }
      if(complexType != null)
      {
         visit(complexType);
      }
      return needsType;
   }
   
   public static boolean needsType(TypeDeclarationType declaration, XSDTypeDefinition oldDef)
   {  
      XSDComplexTypeDefinition complexType = TypeDeclarationUtils.getComplexType(declaration);
      return needsType(complexType, oldDef);
   }
   
   public static void visit(XSDComplexTypeDefinition complexType)
   {
      XSDComplexTypeContent content = complexType.getContent();
      if (content instanceof XSDParticle)
      {
         visit((XSDParticle) content);
         XSDParticleContent particleContent = ((XSDParticle) content).getContent();
         if (particleContent instanceof XSDModelGroup)
         {
             List<XSDParticle> xsdParticles = ((XSDModelGroup) particleContent).getContents();
             for (XSDParticle xsdParticle : xsdParticles)
             {
                XSDParticleContent xsdParticleContent = xsdParticle.getContent();
                if (xsdParticleContent instanceof XSDElementDeclaration)
                {
                   XSDTypeDefinition typeDefinition = ((XSDElementDeclaration) xsdParticleContent).getTypeDefinition();
                   if (checkTypeDefinition(typeDefinition))
                   {
                      return;
                   }
                }
             }
         }         
      }  
   }

   private static boolean checkTypeDefinition(XSDTypeDefinition typeDefinition)
   {      
      if (typeDefinition instanceof XSDComplexTypeDefinition)
      {
         String name = ((XSDComplexTypeDefinition) typeDefinition).getName();
         if (checkDefinition instanceof XSDComplexTypeDefinition 
               && name != null 
               && name.equals(checkDefinition.getName()))
         {
            needsType = true;
            return true;
         }
      }         
      else if (typeDefinition instanceof XSDSimpleTypeDefinition)
      {
         String name = ((XSDSimpleTypeDefinition) typeDefinition).getName();
         if (checkDefinition instanceof XSDSimpleTypeDefinition 
               && name != null 
               && name.equals(checkDefinition.getName()))
         {
            needsType = true;
            return true;
         }
      }
      return false;
   }   
   
   public static void visit(XSDParticle particle)
   {
      XSDParticleContent particleContent = particle.getContent();
      if (particleContent instanceof XSDModelGroupDefinition)
      {
      }
      else if(particleContent instanceof XSDTerm)
      {
         visit((XSDTerm) particleContent);
      }      
   }
   
   public static void visit(XSDTerm term)
   {
      if (term instanceof XSDElementDeclaration)
      {
         visit((XSDElementDeclaration) term);
      }
      else if (term instanceof XSDModelGroup)
      {
         visit((XSDModelGroup) term);
      }      
   }
      
   public static void visit(XSDModelGroup group)
   {
      List<XSDParticle> xsdParticles = ((XSDModelGroup) group).getContents();
      for (XSDParticle xsdParticle : xsdParticles)
      {
         XSDParticleContent particleContent = xsdParticle.getContent();
         if (particleContent instanceof XSDElementDeclaration)
         {
            XSDTypeDefinition typeDefinition = ((XSDElementDeclaration) particleContent).getTypeDefinition();
            if (checkTypeDefinition(typeDefinition))
            {
               return;
            }
         }
         visit((XSDParticle) xsdParticle);
         if (needsType)
         {
            return;
         }
      }
   }     
   
   public static void visit(XSDElementDeclaration element)
   {
      XSDTypeDefinition type = ((XSDElementDeclaration) element).getAnonymousTypeDefinition();
      if (type instanceof XSDComplexTypeDefinition)
      {
         visit((XSDComplexTypeDefinition) type);
      }   
      else if(type == null)
      {
         XSDTypeDefinition typeDefinition = element.getTypeDefinition();
         if(checkTypeDefinition(typeDefinition))
         {
            return;
         }
      }      
   }    
}