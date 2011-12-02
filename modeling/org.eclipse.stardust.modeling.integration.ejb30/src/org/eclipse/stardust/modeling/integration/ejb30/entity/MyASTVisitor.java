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
package org.eclipse.stardust.modeling.integration.ejb30.entity;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;


public class MyASTVisitor extends ASTVisitor
{
   private TypeInfo type;
   private ASTNode node;
   private TypeDeclaration result;
   private List<Annotation> annotations = new ArrayList<Annotation>();

   public MyASTVisitor(TypeInfo type)
   {
      this.type = type;
   }

   public MyASTVisitor(TypeInfo type, ASTNode node)
   {
      this.type = type;
      this.node = node;
   }

   private TypeDeclaration getTypeDeclaration()
   {
      return result;
   }

   private List<Annotation> getAnnotations()
   {
      return annotations;
   }

   @Override
   public boolean visit(TypeDeclaration node)
   {
      String nodeName = node.getName().getFullyQualifiedName();
      if (type != null && type.isSameType(nodeName))
      {
         result = node;
      }
      return super.visit(node);
   }

   @Override
   public boolean visit(MarkerAnnotation node)
   {
      if (node.getParent().equals(this.node))
      {
         annotations.add(node);
      }
      return super.visit(node);
   }

   @Override
   public boolean visit(NormalAnnotation node)
   {
      if (node.getParent().equals(this.node))
      {
         annotations.add(node);
      }
      return super.visit(node);
   }

   @Override
   public boolean visit(SingleMemberAnnotation node)
   {
      if (node.getParent().equals(this.node))
      {
         annotations.add(node);
      }
      return super.visit(node);
   }

   public static TypeDeclaration findTypeDeclaration(TypeInfo type)
   {
      ASTParser parser = ASTParser.newParser(AST.JLS3);
      parser.setSource(type.getType().getCompilationUnit());
      ASTNode node = parser.createAST(null);
      MyASTVisitor visitor = new MyASTVisitor(type);
      node.accept(visitor);
      return visitor.getTypeDeclaration();
   }

   public static List<Annotation> getAnnotations(TypeInfo type, ASTNode node)
   {
      MyASTVisitor visitor = new MyASTVisitor(type, node);
      node.accept(visitor);
      return visitor.getAnnotations();
   }

   public static Annotation getAnnotation(TypeInfo type, ASTNode node,
         Class<?> targetClass)
   {
      List<Annotation> annotations = getAnnotations(type, node);
      for (Annotation annotation : annotations)
      {
         String annotationType = annotation.getTypeName().toString();
         try
         {
            if (targetClass.getName().equals(type.resolve(annotationType)))
            {
               return annotation;
            }
         }
         catch (JavaModelException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return null;
   }
}
