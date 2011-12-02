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
package org.eclipse.stardust.modeling.templates.basic.factories;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.ImportCancelledException;
import org.eclipse.stardust.modeling.templates.basic.ui.ExclusiveChoiceTemplateWizard;
import org.eclipse.stardust.modeling.templates.defaulttemplate.TemplateHelper;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.stardust.modeling.templates.spi.ITemplateFactory;
import org.eclipse.swt.widgets.Display;



public class ExclusiveChoiceTemplateAdapter implements ITemplate
   
{
   ITemplateFactory parentTemplateFactory;   
   
   public ExclusiveChoiceTemplateAdapter(ITemplateFactory javaFactory)
   {
      this.parentTemplateFactory = javaFactory;
   }

   public String getDescription()
   {
      String description = null;
      try
      {
         description = TemplateHelper.readResourceToString("/html/exclusivechoice.html", parentTemplateFactory);
      }
      catch (Throwable e)
      {
         description = null;
      }
      if (description != null) {
         return description;
      } 
      return "<h1><b>Exclusive Choice with N-Threads</b></h1>" + 
      "<p><b>Description</b><p>" +
      "This pattern consists of a start activity which spawns via <i>XOR</i> to N threads." +
      "<p><b>Parameter</b></p>" +
      "<ul>" +
      "    <li>Number of activities</li>" +
      "    <li>Type of activity (Default: Application Activity)</li>" +   
      "</ul>" +
      "<p><b>Example</b></p>" +
      "<img src=\"/images/exclusivechoice.JPG\"\\>";   
      }
   

     

   public String getId()
   {
      return "Exclusive Choice";
   }

   public String getName()
   {
      return "Exclusive Choice";
   }

   public String getCategory()
   {
      return null;
   }

   public void applyTemplate(WorkflowModelEditor editor, ModelType targetModel,
         DiagramType targetDiagram, EditPart editPart, int xHint, int yHint)
   {
      String description = this.getDescription();
      final ExclusiveChoiceTemplateWizard wizard = new ExclusiveChoiceTemplateWizard(editor, targetModel,
               targetDiagram, editPart, xHint, yHint);
      WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard) {
         protected void cancelPressed()
         {
            // TODO Auto-generated method stub
            super.cancelPressed();
            setReturnCode(Window.CANCEL);
         }

         public int open()
         {            
            return super.open();
         }

         protected void finishPressed()
         {
            super.finishPressed();       
            ExclusiveChoiceTemplateWizard w = (ExclusiveChoiceTemplateWizard)wizard;
            if (w.isKilled()) {
               this.setReturnCode(Window.CANCEL);
            }
         }
      };
      if (dialog.open() == Window.CANCEL) {
         throw new ImportCancelledException();
      }               
   }

   public ITemplateFactory getParentFactory()
   {
      return parentTemplateFactory;
   }
   
   
   

}
