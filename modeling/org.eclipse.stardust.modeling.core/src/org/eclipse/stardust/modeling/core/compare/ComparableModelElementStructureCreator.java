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
package org.eclipse.stardust.modeling.core.compare;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.compare.structuremergeviewer.IStructureCreator;
import org.eclipse.core.runtime.CoreException;

public class ComparableModelElementStructureCreator implements IStructureCreator
{

   public static final String DEFAULT_NAME = "NAME"; // TODO //$NON-NLS-1$

   public String getName()
   {
      return DEFAULT_NAME;
   }

   public IStructureComparator getStructure(Object input)
   {
      return (IStructureComparator) input;
   }

   public IStructureComparator locate(Object path, Object input)
   {
      return null;
   }

   public String getContents(Object node, boolean ignoreWhitespace)
   {
      if (node instanceof IStreamContentAccessor)
      {
         try
         {
            InputStream inputStream = ((IStreamContentAccessor) node).getContents();

            byte[] buffer = new byte[4096];
            OutputStream outputStream = new ByteArrayOutputStream();

            while (true)
            {
               int read = inputStream.read(buffer);

               if (read == -1)
               {
                  break;
               }
               outputStream.write(buffer, 0, read);
            }

            outputStream.close();
            inputStream.close();

            return outputStream.toString();

         }
         catch (CoreException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }
      return null;
   }

   public void save(IStructureComparator node, Object input)
   {
      if (node instanceof IEditableContent)
      {
         ((IEditableContent) node).setContent(((ComparableModelElementNode) input)
               .getContent());
      }
   }
}
