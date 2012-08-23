/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice.cxf;

import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.eclipse.core.runtime.IPath;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice.IClassGenerator;

public class ClassGenerator implements IClassGenerator
{
   @Override
   public void generateClasses(IPath location, String wsdlLocation) throws Throwable
   {
      try
      {
      WSDLToJava.main(new String[] {
            "-d", //$NON-NLS-1$
            location.toString(),
            "-keep", //$NON-NLS-1$
            "-verbose", //$NON-NLS-1$
            wsdlLocation
         });
      }
      catch (Throwable t)
      {
         t.printStackTrace();
      }
   }
}
