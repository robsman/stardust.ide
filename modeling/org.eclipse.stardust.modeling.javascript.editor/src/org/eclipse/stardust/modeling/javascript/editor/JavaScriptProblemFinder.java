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

import org.eclipse.wst.jsdt.internal.compiler.ICompilerRequestor;
import org.eclipse.wst.jsdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.wst.jsdt.internal.compiler.IProblemFactory;
import org.eclipse.wst.jsdt.internal.compiler.env.INameEnvironment;
import org.eclipse.wst.jsdt.internal.compiler.impl.CompilerOptions;

public class JavaScriptProblemFinder extends org.eclipse.wst.jsdt.internal.core.CompilationUnitProblemFinder {

   public JavaScriptProblemFinder(INameEnvironment environment,
         IErrorHandlingPolicy policy, CompilerOptions compilerOptions,
         ICompilerRequestor requestor, IProblemFactory problemFactory)
   {
      super(environment, policy, compilerOptions, requestor, problemFactory);

   }
   
   
}
   
