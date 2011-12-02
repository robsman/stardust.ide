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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SpiPropertyPage extends AbstractModelElementPropertyPage
{
   private IModelElementPropertyPage delegate;

   public SpiPropertyPage(IModelElementPropertyPage delegate)
   {
      this.delegate = delegate;
      delegate.setDelegateContainer(this);
   }

   protected void performDefaults()
   {
      super.performDefaults();
   }

   public boolean performCancel()
   {
      if(delegate instanceof AbstractModelElementPropertyPage)
      {
         ((AbstractModelElementPropertyPage) delegate).performCancel();
      }
      return super.performCancel();
   }

   public SpiPropertyPage(ConfigurationElement config) throws CoreException
   {
      delegate = (IModelElementPropertyPage) config.createExecutableExtension(SpiConstants.PROPERTY_PAGE_CLASS);
      delegate.setDelegateContainer(this);
   }

   public void dispose()
   {
      if (null != delegate)
      {
         delegate.dispose();
      }
      super.dispose();
   }

   public WidgetBindingManager getWidgetBindingManager()
   {
      return delegate instanceof AbstractModelElementPropertyPage
         ? ((AbstractModelElementPropertyPage) delegate).getWidgetBindingManager()
         : super.getWidgetBindingManager();
   }

   public void loadFieldsFromElement(
         IModelElementNodeSymbol symbol, IModelElement element)
   {
      delegate.loadFieldsFromElement(symbol, element);
   }

   public void loadElementFromFields(
         IModelElementNodeSymbol symbol, IModelElement element)
   {
      delegate.loadElementFromFields(symbol, element);
   }

   public Control createBody(Composite content)
   {
      return delegate.createBody(content);
   }

   public void contributeButtons(Composite parent)
   {
      delegate.contributeButtons(parent);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      delegate.contributeVerticalButtons(parent);
   }

   public IAdaptable getElement()
   {
      return delegate.getElement();
   }

   public void setElement(IAdaptable element)
   {
      delegate.setElement(element);
   }

   public void setContainer(IPreferencePageContainer container)
   {
      super.setContainer(container);
      delegate.setContainer(container);
   }

   public IModelElementPropertyPage getDelegate()
   {
      return delegate;
   }

   public void noDefaultAndApplyButton()
   {
      super.noDefaultAndApplyButton();
   }
}