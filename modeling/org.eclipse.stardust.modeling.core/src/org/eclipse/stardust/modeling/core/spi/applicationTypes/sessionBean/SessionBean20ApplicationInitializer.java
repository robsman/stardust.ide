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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.sessionBean;

import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.JavaApplicationInitializer;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SessionBean20ApplicationInitializer extends JavaApplicationInitializer
{
   public SessionBean20ApplicationInitializer()
   {
      super(CarnotConstants.CLASS_NAME_ATT, CarnotConstants.REMOTE_INTERFACE_ATT);
   }
}
