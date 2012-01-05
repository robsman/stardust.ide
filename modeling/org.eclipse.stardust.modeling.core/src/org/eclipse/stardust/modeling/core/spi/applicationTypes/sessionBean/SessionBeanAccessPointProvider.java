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

import java.util.List;

import org.eclipse.stardust.engine.extensions.ejb.SessionBeanConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPointProvider;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SessionBeanAccessPointProvider implements IAccessPointProvider
{
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      IAccessPointProvider delegate = getDelegate(
            ((IExtensibleElement) element).getAttribute());
      return delegate.createIntrinsicAccessPoint(element);
   }

   private IAccessPointProvider getDelegate(List attributes)
   {
      String style = getVersionAttribute(attributes);
      if (SessionBeanConstants.VERSION_3_X.equals(style))
      {
         return new SessionBean30AccessPointProvider();
      }
      else
      {
         return new SessionBean20AccessPointProvider();
      }
   }

   private String getVersionAttribute(List attributes)
   {
      String style = SessionBeanConstants.VERSION_3_X;
      if (!attributes.isEmpty())
      {
         style = AttributeUtil.getAttributeValue(attributes, SessionBeanConstants.VERSION_ATT);
         if (style == null)
         {
            // old style app
            style = SessionBeanConstants.VERSION_2_X;
         }
      }
      return style;
   }
}
