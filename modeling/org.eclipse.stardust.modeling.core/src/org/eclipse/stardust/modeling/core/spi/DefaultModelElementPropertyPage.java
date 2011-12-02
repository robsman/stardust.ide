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
package org.eclipse.stardust.modeling.core.spi;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IPropertyPage;
import org.eclipse.swt.widgets.Composite;


/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class DefaultModelElementPropertyPage
      implements IModelElementPropertyPage, IPropertyPage
{
   private IAdaptable element;
   private IPreferencePageContainer container;

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public void contributeButtons(Composite parent)
   {}

   public void contributeVerticalButtons(Composite parent)
   {}

   public IAdaptable getElement()
   {
      return element;
   }

   public void setElement(IAdaptable element)
   {
      this.element = element;
   }

   public IPreferencePageContainer getContainer()
   {
      return container;
   }

   public void setContainer(IPreferencePageContainer container)
   {
      this.container = container;
   }

   public void apply()
   {
   }

   public void elementChanged()
   {
   }

   public void dispose()
   {
   }
}
