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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.stardust.modeling.common.projectnature.ModelingCoreActivator;


/**
 * @author rsauer
 * @version $Revision$
 */
public class CarnotHomeLocationClasspathContainer implements IClasspathContainer
{
   public static final Path PATH_CARNOT_HOME_LOCATION_CP = new Path(
         ModelingCoreActivator.ID_CARNOT_HOME_LOCATION_CP);

   public IClasspathEntry[] getClasspathEntries()
   {
      return BpmClasspathUtils.CLASSPATH_ENTRY_ARRAY;
   }

   public int getKind()
   {
      return IClasspathContainer.K_APPLICATION;
   }

   public String getDescription()
   {
      return "CARNOT_HOME Libraries"; //$NON-NLS-1$
   }

   public IPath getPath()
   {
      return PATH_CARNOT_HOME_LOCATION_CP;
   }
}
