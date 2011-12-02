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
package org.eclipse.stardust.modeling.modelimport;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.eclipse.ui.wizards.datatransfer.IImportStructureProvider;

/**
 * @author fherinean
 * @version $Revision$
 */
public class RepositoryStructureProvider implements IImportStructureProvider
{
   public static IImportStructureProvider INSTANCE = new RepositoryStructureProvider();

   public List getChildren(Object element)
   {
      return Collections.EMPTY_LIST;
   }

   public InputStream getContents(Object element)
   {
      ModelNode modelnode = (ModelNode) element;
      File file = modelnode.getStore().getFile(modelnode);
      try
      {
         return file.toURL().openStream();
      }
      catch (java.io.IOException e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public String getFullPath(Object element)
   {
      if (element instanceof File)
      {
         return ((File) element).getAbsolutePath();
      }
      if (element instanceof ModelNode)
      {
         ModelNode modelnode = (ModelNode) element;
         return modelnode.getStore().getFullName(modelnode);
      }
      return null;
   }

   public String getLabel(Object element)
   {
      if (element instanceof ModelNode)
      {
         ModelNode modelnode = (ModelNode) element;
         return modelnode.getId() + "_" + modelnode.getFullVersion() + ".cwm"; //$NON-NLS-1$ //$NON-NLS-2$
      }
      return null;
   }

   public boolean isFolder(Object element)
   {
      return false;
   }
}
