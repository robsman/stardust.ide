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
package org.eclipse.stardust.modeling.common.projectnature.classpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * @author rsauer
 * @version $Revision$
 */
public class BpmToolLibrariesClasspathContainerInitializer
extends ClasspathContainerInitializer
{
   public void initialize(IPath containerPath, IJavaProject project) throws CoreException
   {
      JavaCore.setClasspathContainer(
            containerPath,//
            new IJavaProject[] {project},
            new IClasspathContainer[] {new BpmToolLibrariesClasspathContainer()},//
            null);
   }
}
