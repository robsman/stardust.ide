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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.PlainJavaAccessPointProvider;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SessionBean20AccessPointProvider implements IAccessPointProvider
{
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      List result = new ArrayList();

      String className = AttributeUtil.getAttributeValue(
         (IExtensibleElement) element, CarnotConstants.REMOTE_INTERFACE_ATT);
      String methodName = AttributeUtil.getAttributeValue(
         (IExtensibleElement) element, CarnotConstants.METHOD_NAME_ATT);
      result.addAll(PlainJavaAccessPointProvider.getIntrinsicAccessPoints(
         (IExtensibleElement) element, className, methodName, null, null, null, false));

      className = AttributeUtil.getAttributeValue(
         (IExtensibleElement) element, CarnotConstants.HOME_INTERFACE_ATT);
      methodName = AttributeUtil.getAttributeValue(
         (IExtensibleElement) element, CarnotConstants.CREATE_METHOD_NAME_ATT);
      result.addAll(PlainJavaAccessPointProvider.getIntrinsicAccessPoints(
         (IExtensibleElement) element, className, methodName, true, null, null, null, true));

      return result;
   }
}
