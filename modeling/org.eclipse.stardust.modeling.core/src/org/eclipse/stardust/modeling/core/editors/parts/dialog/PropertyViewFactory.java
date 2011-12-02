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
package org.eclipse.stardust.modeling.core.editors.parts.dialog;

import org.eclipse.gef.EditPart;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;


public class PropertyViewFactory
{
   private static PropertyViewFactory _instance;

   private PropertyViewFactory()
   {}

   public static synchronized PropertyViewFactory instance()
   {
      if (_instance == null)
      {
         _instance = new PropertyViewFactory();
      }
      return _instance;
   }

   public IPropertyView createPropertyView(Object selection)
   {
      IPropertyView propertyView = null;

      if (selection instanceof EditPart
         && ((EditPart) selection).getModel() instanceof ActivitySymbolType)
      {
         propertyView = new ActivityTypePropertyView();
      }
      return propertyView;
   }

}
