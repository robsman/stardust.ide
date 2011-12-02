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
package org.eclipse.stardust.modeling.validation.impl.spi.applicationTypes;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationConstants;
import org.eclipse.stardust.modeling.validation.ValidationException;


public class SessionBeanValidator implements IModelElementValidator
{
   // (fh) we cannot reference SessionBeanConstants from here
   public static final String VERSION_2_X = "sessionBean20"; //$NON-NLS-1$
   public static final String VERSION_3_X = "sessionBean30"; //$NON-NLS-1$
   public static final String VERSION_ATT = CarnotConstants.ENGINE_SCOPE + "ejbVersion"; //$NON-NLS-1$

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      IModelElementValidator delegate = getDelegate(element);
      return delegate.validate(element);
   }

   private IModelElementValidator getDelegate(IModelElement element)
   {
      String style = getVersionAttribute(((IExtensibleElement) element).getAttribute());
      try
      {
         return (IModelElementValidator) SpiExtensionRegistry.createExecutableExtension(
               ValidationConstants.ELEMENT_VALIDATOR_EXTENSION_POINT, "class", //$NON-NLS-1$
               "org.eclipse.stardust.modeling.validation." + style + "Application", //$NON-NLS-1$ //$NON-NLS-2$
               "filter", "metaType", style); //$NON-NLS-1$ //$NON-NLS-2$
      }
      catch (Exception ex)
      {
         // default to EJB 2.0 ?
         // radio3x.setEnabled(false);
         // TODO: return a dummy 3.0 page
      }
      return null;
   }

   private String getVersionAttribute(List attributes)
   {
      String style = VERSION_3_X;
      if (!attributes.isEmpty())
      {
         style = AttributeUtil.getAttributeValue(attributes, VERSION_ATT);
         if (style == null)
         {
            // old style app
            style = VERSION_2_X;
         }
      }
      return style;
   }
}