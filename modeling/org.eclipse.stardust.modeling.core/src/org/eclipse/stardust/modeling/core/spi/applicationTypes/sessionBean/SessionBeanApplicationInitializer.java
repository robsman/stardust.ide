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
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationInitializer;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SessionBeanApplicationInitializer implements IApplicationInitializer
{
   public List initialize(ApplicationType data, List attributes)
   {
      IApplicationInitializer delegate = getDelegate(attributes);
      return delegate.initialize(data, attributes);
   }

   private IApplicationInitializer getDelegate(List attributes)
   {
      String style = getSetVersionAttribute(attributes);
      if (SessionBeanConstants.VERSION_3_X.equals(style))
      {
         return new SessionBean30ApplicationInitializer();
      }
      else
      {
         return new SessionBean20ApplicationInitializer();
      }
   }

   private String getSetVersionAttribute(List attributes)
   {
      String style = SessionBeanConstants.VERSION_3_X;
      if (attributes.isEmpty())
      {
         // new created apps are 3.0
         AttributeUtil.setAttribute(attributes, SessionBeanConstants.VERSION_ATT, style);
      }
      else
      {
         style = AttributeUtil.getAttributeValue(attributes, SessionBeanConstants.VERSION_ATT);
         if (style == null)
         {
            // old style app
            style = SessionBeanConstants.VERSION_2_X;
            AttributeUtil.setAttribute(attributes, SessionBeanConstants.VERSION_ATT, style);
         }
      }
      return style;
   }
}
