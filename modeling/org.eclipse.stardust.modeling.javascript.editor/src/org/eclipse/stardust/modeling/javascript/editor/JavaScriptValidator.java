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
package org.eclipse.stardust.modeling.javascript.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.wst.jsdt.core.IBuffer;
import org.eclipse.wst.jsdt.core.IJavaScriptProject;
import org.eclipse.wst.jsdt.core.JavaScriptModelException;
import org.eclipse.wst.jsdt.core.compiler.IProblem;
import org.eclipse.wst.jsdt.internal.compiler.CompilationResult;
import org.eclipse.wst.jsdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.wst.jsdt.internal.compiler.ICompilerRequestor;
import org.eclipse.wst.jsdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.wst.jsdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.wst.jsdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.wst.jsdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.wst.jsdt.internal.core.BasicCompilationUnit;
import org.eclipse.wst.jsdt.internal.core.CancelableNameEnvironment;
import org.eclipse.wst.jsdt.internal.core.JavaProject;

public class JavaScriptValidator
{
   private IJavaScriptProject project;
   private ICompilationUnit compilationUnit;
   private CancelableNameEnvironment environment;
   private IErrorHandlingPolicy policy;
   private CompilerOptions compilerOptions;
   private ICompilerRequestor requestor;
   private CancelableJSProblemFactory problemFactory;
   private JavaScriptProblemFinder problemFinder;
   
   
   public JavaScriptValidator(IJavaScriptProject project) {
      this.project = (JavaProject)project;
      try
      {
         environment = new CancelableNameEnvironment((JavaProject) project, null, null, null);
      }
      catch (JavaScriptModelException e)
      {
         e.printStackTrace();
      }
      policy = DefaultErrorHandlingPolicies.proceedWithAllProblems();
      compilerOptions = getCompilerOptions(project.getOptions(true), false, false);
      
      requestor = new ICompilerRequestor() {
         public void acceptResult(CompilationResult compilationResult) {
        }
      };
      
      problemFactory = new CancelableJSProblemFactory(null);
      
      
      problemFinder = new JavaScriptProblemFinder(
            environment,
            policy,
            compilerOptions,
            requestor,
            problemFactory);            
   }
      
   
    

   synchronized public IProblem[] validate(String code) {
      IBuffer buffer;
      try
      {        
         BasicCompilationUnit bcu = new BasicCompilationUnit(code.toCharArray(), this.getPackageName("com.test.validation.class"), "");         
         CompilationUnitDeclaration result = problemFinder.resolve(bcu, true, true, true);
         IProblem[] problems = result.compilationResult.getErrors();         
         //rp: Workaround
         //This is a workaround to filter problems which are not really problems but which occur due to JDT problems during type resolution (see CRNT-10286)
         //In the mentioned ticket it was possible to fix the problem due to the fact that it resided in a class which we are capabple of influence its behaviour.
         if (problems != null && problems.length > 0) {
            problems =  filterProblems(problems);            
         }
         return problems;
      }
      catch (Throwable e)
      {
         e.printStackTrace();
      }
      return null;          
   }
   
   private IProblem[] filterProblems(IProblem[] problems)
   {      
      List filteredProblems = new ArrayList();      
      for (int i = 0; i < problems.length; i++) 
      {
         IProblem problem = problems[i];
         if (!(problem.getMessage().indexOf("The return type is incompatible") > -1)) 
         {
            filteredProblems.add(problem);
         }         
      }
      int j = 0;
      IProblem[] realProblems = new IProblem[filteredProblems.size()];
      for (Iterator i = filteredProblems.iterator(); i.hasNext();) 
      {
         realProblems[j++] = (IProblem) i.next();
      }
      return realProblems;
   }



   private CompilerOptions getCompilerOptions(Map settings, boolean creatingAST, boolean statementsRecovery) {
      CompilerOptions compilerOptions = new CompilerOptions(settings);
      compilerOptions.performMethodsFullRecovery = statementsRecovery;
      compilerOptions.performStatementsRecovery = statementsRecovery;
      compilerOptions.parseLiteralExpressionsAsConstants = !creatingAST;
      compilerOptions.storeAnnotations = creatingAST; 
      return compilerOptions;
  }
   
   public char[][] getPackageName(String className) {
      StringTokenizer izer = new StringTokenizer(className, ".");
      char[][] result = new char[izer.countTokens() - 1][];
      for (int i = 0; i < result.length; i++) {
         String tok = izer.nextToken();
         result[i] = tok.toCharArray();
      }                
      return result;           
   }

}
