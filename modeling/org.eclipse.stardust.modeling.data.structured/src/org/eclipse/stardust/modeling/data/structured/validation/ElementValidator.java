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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;

import ag.carnot.base.StringUtils;

public class ElementValidator
{
   private static List<String> messages;   
   
   public static List<String> validateElements(TypeDeclarationType declaration)
   {      
      messages = new ArrayList<String>();
      XSDComplexTypeDefinition complexType = TypeDeclarationUtils.getComplexType(declaration);
      if(complexType != null)
      {
         visit(complexType);
      }
      if(!messages.isEmpty())
      {
         return messages;
      }
      return null;
   }
   
   public static void visit(XSDComplexTypeDefinition complexType)
   {
	  List<String> attributeNames = new ArrayList<String>();
      for (XSDAttributeGroupContent attribute : complexType.getAttributeContents())
      {
         if (attribute instanceof XSDAttributeUse)
         {
            XSDAttributeDeclaration attr = ((XSDAttributeUse) attribute).getContent();
            String name = attr.getName();
            if (name != null)
            {
                if (attributeNames.contains(name))
                {
                    messages.add(MessageFormat.format(
                            Structured_Messages.ComplexTypePropertyPage_DuplicateFieldErrorMessage,
                            new Object [] {name}));                         
                }
                attributeNames.add(name);                        
            }
         }         
      }
      
      List<String> names = new ArrayList<String>(); 
      XSDComplexTypeContent content = complexType.getContent();
      if (content instanceof XSDParticle)
      {
         visit((XSDParticle) content);
         XSDParticleContent particleContent = ((XSDParticle) content).getContent();
         if (particleContent instanceof XSDModelGroup)
         {
             for (XSDParticle xsdParticle : ((XSDModelGroup) particleContent).getContents())
             {
                XSDParticleContent xsdParticleContent = xsdParticle.getContent();
                if (xsdParticleContent instanceof XSDElementDeclaration)
                {
                	String name = ((XSDElementDeclaration) xsdParticleContent).getName();
                    if (name != null)
                    {
                    	if (names.contains(name))
                    	{
                            messages.add(MessageFormat.format(
                            		Structured_Messages.ComplexTypePropertyPage_DuplicateFieldErrorMessage,
                                    new Object [] {name}));                    		
                    	}
                    	names.add(name);                    	
                    }
                }
             }      
         }         
      }  
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
      List<String> names = new ArrayList<String>(); 
      for (XSDParticle xsdParticle : ((XSDModelGroup) group).getContents())
      {
         XSDParticleContent particleContent = xsdParticle.getContent();
         
         // check for element declarations    
         if (particleContent instanceof XSDElementDeclaration)
         {
            String name = ((XSDElementDeclaration) particleContent).getName();
            if (name != null)
            {
                if (names.contains(name))
                {
                    messages.add(MessageFormat.format(
                            Structured_Messages.ComplexTypePropertyPage_DuplicateFieldErrorMessage,
                            new Object [] {name}));                         
                }
                names.add(name);                        
            }
         }
         visit((XSDParticle) xsdParticle);
      }      
   }     
   
   public static void visit(XSDElementDeclaration element)
   {
      XSDTypeDefinition type = ((XSDElementDeclaration) element).getAnonymousTypeDefinition();
      if (type instanceof XSDComplexTypeDefinition)
      {
         visit((XSDComplexTypeDefinition) type);
      }   
      else if (type == null)
      {
         String name = element.getName();
         if (!isValidElementName(name))
         {
            messages.add(MessageFormat.format(
                  Structured_Messages.ComplexTypePropertyPage_InvalidFieldIdentifierErrorMessage,
                  new Object [] {name}));
         }            
      }      
   } 
   
   // validate name
   public static boolean isValidElementName(String name)
   {
      if(StringUtils.isEmpty(name))
      {
         return false;
      }
      char ch = name.charAt(0);      
      if (!Character.isLetter(ch)
            && ch != ':' && ch != '_')
      {
         return false;
      }
      for (int i = 1; i < name.length(); i++)
      {
         ch = name.charAt(i);      
         if (!Character.isLetter(ch)
               && !Character.isDigit(ch)
               && ch != '.' && ch != '-' && ch != '_')
         {
            return false;
         }
      }
      return true;
   }
}