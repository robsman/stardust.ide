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
package org.eclipse.stardust.modeling.integration.webservices;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;

public class JaxWSASTVisitor extends ASTVisitor
{
   private PackageDeclaration result;
   private List<Annotation> annotations = new ArrayList<Annotation>();

   @Override
   public boolean visit(PackageDeclaration node)
   {
      result = node;
      return super.visit(node);
   }

   @Override
   public boolean visit(MarkerAnnotation node)
   {
      annotations.add(node);
      return super.visit(node);
   }

   @Override
   public boolean visit(NormalAnnotation node)
   {
      annotations.add(node);
      return super.visit(node);
   }

   @Override
   public boolean visit(SingleMemberAnnotation node)
   {
      annotations.add(node);
      return super.visit(node);
   }

   public static Annotation getAnnotation(ICompilationUnit source,
         Class<?> targetClass)
   {
      ASTParser parser = ASTParser.newParser(AST.JLS3);
      parser.setSource(source);
      ASTNode node = parser.createAST(null);
      JaxWSASTVisitor visitor = new JaxWSASTVisitor();
      node.accept(visitor);
      if (visitor.result != null)
      {
         visitor.result.accept(visitor);
         for (Annotation annotation : visitor.annotations)
         {
            String annotationType = annotation.getTypeName().toString();
            if (targetClass.getName().equals(annotationType))
            {
               return annotation;
            }
         }
      }
      return null;
   }
}
