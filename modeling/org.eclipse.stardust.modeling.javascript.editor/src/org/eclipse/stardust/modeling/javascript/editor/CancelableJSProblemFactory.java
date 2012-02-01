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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.wst.jsdt.core.compiler.CategorizedProblem;
import org.eclipse.wst.jsdt.internal.compiler.problem.AbortCompilation;
import org.eclipse.wst.jsdt.internal.compiler.problem.DefaultProblem;
import org.eclipse.wst.jsdt.internal.compiler.problem.DefaultProblemFactory;
import org.eclipse.wst.jsdt.internal.compiler.problem.ProblemSeverities;

public class CancelableJSProblemFactory extends DefaultProblemFactory {
   public IProgressMonitor monitor;

   public CancelableJSProblemFactory(IProgressMonitor monitor) {
       super();
       this.monitor = monitor;
   }

   public CategorizedProblem createProblem(char[] originatingFileName, int problemId, String[] problemArguments, String[] messageArguments, int severity, int startPosition, int endPosition, int lineNumber, int columnNumber) {
      if (this.monitor != null && this.monitor.isCanceled()) 
          throw new AbortCompilation(true/*silent*/, new OperationCanceledException());
        
         String message = this.getLocalizedMessage(problemId, messageArguments);
         severity = checkSeverity(severity, message);
         return new DefaultProblem(
              originatingFileName, 
              this.getLocalizedMessage(problemId, messageArguments),
              problemId, 
              problemArguments, 
              severity, 
              startPosition, 
              endPosition, 
              lineNumber,
              columnNumber);
  }

   private int checkSeverity(int severity, String message)
   {
      // warning, but not this special warning
      if ((severity == 32 || severity == 0)
            && message.indexOf("cannot be resolved or is not a field") == -1 //$NON-NLS-1$
            && message.indexOf("Type mismatch: cannot convert from") == -1) //$NON-NLS-1$
      {
         return ProblemSeverities.Warning;
      } 
      if (message.indexOf("Duplicate") > -1) { //$NON-NLS-1$
         return ProblemSeverities.Warning;
      }
      return ProblemSeverities.Error;
   }
}