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
package org.eclipse.stardust.modeling.templates.views;

import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;

import org.eclipse.stardust.modeling.templates.spi.ITemplate;


public class ExtendedHTMLEditorKit extends HTMLEditorKit
{
   private ITemplate template;

   public ExtendedHTMLEditorKit(ITemplate template)
   {
      this.template = template;
   }

   public ViewFactory getViewFactory()
   {
      // TODO Auto-generated method stub
      return new ExtendedViewFactory(template);
   }

}
