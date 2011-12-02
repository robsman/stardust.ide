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
package org.eclipse.stardust.modeling.templates.defaulttemplate;

import java.util.Map;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;



public class TemplateParameterWizard extends Wizard
{
   private TemplateParameterWizardPage newTemplateParameterPage;

   public TemplateParameterWizard(ModelType model, TemplateType template, Map mapping)
   {
      newTemplateParameterPage = new TemplateParameterWizardPage(model, template, mapping, null);
      this.setWindowTitle("Apply \"" + template.getName() + "\"");
   }

   public void addPages()
   {
      this.addPage(newTemplateParameterPage);
   }

   public boolean performFinish()
   {
      return newTemplateParameterPage.finish(null);
   }
   
   public boolean isKilled()
   {
      return false;
   }  
}
