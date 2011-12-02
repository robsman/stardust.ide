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

import java.util.Collections;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.PlainJavaAccessPointProvider;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SpringBeanAccessPointProvider extends PlainJavaAccessPointProvider
{
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      List result = Collections.EMPTY_LIST;

      if (element instanceof IExtensibleElement)
      {
         String className = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               CarnotConstants.CLASS_NAME_ATT);
         String methodName = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT);

         result = getIntrinsicAccessPoints((IExtensibleElement) element, className,
               methodName, null, null, null, false);
      }
      return result;
   }
}
