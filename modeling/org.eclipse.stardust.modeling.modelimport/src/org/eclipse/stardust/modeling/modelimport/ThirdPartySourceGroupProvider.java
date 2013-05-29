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
package org.eclipse.stardust.modeling.modelimport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ThirdPartySourceGroupProvider
{
   private Composite addtionalOptionsComposite;

   public Control createAdditionalOptionsGroup(Composite optionsGroup, boolean enabled)
   {
      addtionalOptionsComposite = new Composite(optionsGroup, SWT.NONE);
      {
         GridLayout layout = new GridLayout();
         layout.numColumns = 4;
         layout.verticalSpacing = 12;
         addtionalOptionsComposite.setLayout(layout);

         GridData data = new GridData();
         data.verticalAlignment = GridData.FILL;
         data.grabExcessVerticalSpace = true;
         data.horizontalAlignment = GridData.FILL;
         data.horizontalSpan = 2;
         addtionalOptionsComposite.setLayoutData(data);
      }

      return addtionalOptionsComposite;
   }  
}