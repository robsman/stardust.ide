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
package org.eclipse.stardust.modeling.javascript;

import org.eclipse.wst.jsdt.core.infer.IInferenceFile;
import org.eclipse.wst.jsdt.core.infer.InferEngine;
import org.eclipse.wst.jsdt.core.infer.InferrenceProvider;
import org.eclipse.wst.jsdt.core.infer.RefactoringSupport;
import org.eclipse.wst.jsdt.core.infer.ResolutionConfiguration;


/**
 * @author rpielmann
 * @version $Revision$
 */
public class JavaScriptInferenceProvider implements InferrenceProvider
{

   public int applysTo(IInferenceFile scriptFile) {
      return InferrenceProvider.MAYBE_THIS;
  }

  public String getID() {
      return "JavaScriptInferrenceProvider"; //$NON-NLS-1$
  }

  public InferEngine getInferEngine() {
	 InferEngine engine = new JavaScriptInferenceEngine();
	 engine.inferenceProvider=this;
	 return engine;
  }

  public RefactoringSupport getRefactoringSupport() {
      return null;
  }

	public ResolutionConfiguration getResolutionConfiguration() {
		return new ResolutionConfiguration();
	}


}
      
