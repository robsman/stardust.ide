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
package org.eclipse.stardust.modeling.common.ui.jface.utils;

import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.swt.widgets.Text;


public class LabeledText extends LabeledWidget
{
   public LabeledText(Text text, LabelWithStatus label)
   {
      super(text, label);
   }
   
   public Text getText()
   {
      return (Text) getWidget();
   }
   
   public void setTextLimit(int limit)
   {
      getText().setTextLimit(limit);
   }
}