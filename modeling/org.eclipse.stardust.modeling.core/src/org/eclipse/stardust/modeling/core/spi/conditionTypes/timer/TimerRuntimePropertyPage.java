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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.timer;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IConditionPropertyPage;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.DefaultModelElementPropertyPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class TimerRuntimePropertyPage extends DefaultModelElementPropertyPage
      implements IConditionPropertyPage
{
   public void loadFieldsFromElement(
         IModelElementNodeSymbol symbol, IModelElement node)
   {
   }

   public void loadElementFromFields(
         IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(Composite parent)
   {
      return null;
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }   
}