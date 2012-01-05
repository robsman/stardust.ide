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

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.HTMLFactory;
import javax.swing.text.html.ImageView;

import org.eclipse.stardust.modeling.templates.spi.ITemplate;


public class ExtendedViewFactory extends HTMLFactory implements ViewFactory
{
   private ITemplate template;

   public ExtendedViewFactory(ITemplate template)
   {
      this.template = template;
   }

   public View create(Element element)
   {
      HTML.Tag kind = (HTML.Tag) (element.getAttributes().getAttribute(javax.swing.text.StyleConstants.NameAttribute));
      if (kind.toString().equalsIgnoreCase("img")) {
         ImageView imageView = new ExtendedImageView(template, element);        
         return imageView;
      }
      return super.create(element);
   }



}
