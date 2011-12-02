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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class EventHandlingPropertyPage extends AbstractModelElementPropertyPage
{
   public static final int ACTIVITY = 0;
   public static final int PROCESS = 1;

   public static final int ADD_EVENT_ACTION_BUTTON = IButtonManager.BUTTON_COUNT;
   public static final int ADD_BIND_ACTION_BUTTON = ADD_EVENT_ACTION_BUTTON + 1;
   public static final int ADD_UNBIND_ACTION_BUTTON = ADD_BIND_ACTION_BUTTON + 1;

   private EventHandlingOutlineSynchronizer outlineSync;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (null == outlineSync)
      {
         this.outlineSync = new EventHandlingOutlineSynchronizer(this,
               (IEventHandlerOwner) getModelElement());
      }
      outlineSync.init();
   }
   
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public void dispose()
   {
      if (null != outlineSync)
      {
         outlineSync.dispose();
         this.outlineSync = null;
      }
      
      super.dispose();
   }

   public Control createBody(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout compositeLayout = new GridLayout(1, false);
      composite.setLayout(compositeLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      return composite;
   }
}