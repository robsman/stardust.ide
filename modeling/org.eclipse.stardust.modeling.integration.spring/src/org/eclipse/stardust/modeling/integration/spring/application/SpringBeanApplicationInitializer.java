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
package org.eclipse.stardust.modeling.integration.spring.application;

import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.JavaApplicationInitializer;


public class SpringBeanApplicationInitializer extends JavaApplicationInitializer
{
   public SpringBeanApplicationInitializer()
   {
      super(CarnotConstants.REMOTE_INTERFACE_ATT, CarnotConstants.CLASS_NAME_ATT);
   }
}
