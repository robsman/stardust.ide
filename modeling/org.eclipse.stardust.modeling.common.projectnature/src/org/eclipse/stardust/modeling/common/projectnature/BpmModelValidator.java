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
package org.eclipse.stardust.modeling.common.projectnature;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author rsauer
 * @version $Revision$
 */
public class BpmModelValidator extends IncrementalProjectBuilder
{

   public BpmModelValidator()
   {
      // TODO Auto-generated constructor stub
   }

   @SuppressWarnings("unchecked")
   protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
         throws CoreException
   {
      // TODO Auto-generated method stub
      return null;
   }

}
