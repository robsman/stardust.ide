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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowFigure;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.SWT;

public class TextSymbolFigure extends RectangleFigure implements EditableFigure
{
   private TextFlow textArea;
   
   public TextSymbolFigure()
   {
      FlowFigure content = new FlowPage();
      this.textArea = new TextFlow();
      content.add(textArea);
      
      content.setBorder(new MarginBorder(3));
      
      setLayoutManager(new ToolbarLayout());
      add(content);
      setOutline(false);

      setPreferredSize(new Dimension(40, 20));
   }
   
   public void setText(String text)
   {
      textArea.setText(text == null ? "" : text); //$NON-NLS-1$
   }

   public String getText()
   {
      return textArea.getText();
   }

   public Rectangle getEditingBounds()
   {
      return getBounds();
   }

   public int getEditingStyle()
   {
      return SWT.MULTI;
   }
}