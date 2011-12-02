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
package org.eclipse.stardust.modeling.javascript.editor;

import java.util.ResourceBundle;

import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.TextEditorAction;

public class SwitchToModeAction extends TextEditorAction 
{
   
   private boolean basicMapping = true;
   private String menuText = "Switch To Advanced Mapping Mode";
   
   protected SwitchToModeAction(ResourceBundle bundle, String prefix, ITextEditor editor)
   {
      super(bundle, prefix, editor);
   }

   @Override
   public void update()
   {
      super.update();
   }

   @Override
   public void run()
   {
      basicMapping = !basicMapping;
      if (basicMapping) {
         menuText = "Switch To Advanced Mapping Mode";
      } else {
         menuText = "Switch To Basic Mapping Mode";
      }
      this.setText(menuText);
      super.run();
   }


   public boolean isBasicMapping()
   {
      return basicMapping;
   }

   public void setBasicMapping(boolean basicMapping)
   {
      this.basicMapping = basicMapping;
   }
   
   

}
