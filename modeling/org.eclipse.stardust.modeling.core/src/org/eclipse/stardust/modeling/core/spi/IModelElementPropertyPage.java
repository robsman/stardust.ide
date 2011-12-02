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
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * @author fherinean
 * @version $Revision$
 */
public interface IModelElementPropertyPage
{
   void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element);

   void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element);

   Control createBody(Composite parent);

   void contributeButtons(Composite parent);

   void contributeVerticalButtons(Composite parent);

   IAdaptable getElement();

   void setElement(IAdaptable element);

   void setContainer(IPreferencePageContainer container);
   
   void dispose();
   
   void setDelegateContainer(AbstractModelElementPropertyPage page);
}