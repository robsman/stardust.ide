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
package org.eclipse.stardust.modeling.common.ui.properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.projectnature.classpath.BpmClasspathUtils;
import org.eclipse.stardust.modeling.common.ui.UI_Messages;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;


/**
 * @author rsauer
 * @version $Revision$
 */
public class BpmNaturePropertyPage extends PropertyPage implements IWorkbenchPropertyPage
{
   private Button chkEnableBpmSupport;

/*
   private Button chkEnableSpringSupport;
*/
   protected Control createContents(Composite parent)
   {
      Composite panel = FormBuilder.createComposite(parent, 1);

      chkEnableBpmSupport = FormBuilder.createCheckBox(panel,
            UI_Messages.STR_EnableCarnotBpm, 2);

/*
      chkEnableSpringSupport = FormBuilder.createCheckBox(panel,
            "Enable Infinity BPM Spring support", 2);
*/
      IAdaptable element = getElement();

      IProject project = (IProject) element.getAdapter(IProject.class);
      if (null != project)
      {
         boolean checked = false;
         try
         {
            checked = project.isOpen() && project.hasNature(BpmProjectNature.NATURE_ID);
         }
         catch (CoreException e)
         {
            // TODO trace
         }
         
         chkEnableBpmSupport.setSelection(checked);
/*         
         chkEnableSpringSupport.setSelection(checked
               && BpmClasspathUtils.hasBpmSpringLibsContainer(project));
*/
         
         try
         {
            chkEnableBpmSupport.setEnabled( !project.hasNature(BpmProjectNature.FACETED_PROJECT_NATURE));
/*
            chkEnableSpringSupport.setEnabled( !project.hasNature(BpmProjectNature.FACETED_PROJECT_NATURE));
*/
         }
         catch (CoreException e)
         {
            // ignore
         }
      }

      return panel;
   }

   public boolean performOk()
   {
      IProject project = (IProject) getElement().getAdapter(IProject.class);
      if (null != project)
      {
         boolean isFacetedProject = false;
         try
         {
            isFacetedProject = project.hasNature(BpmProjectNature.FACETED_PROJECT_NATURE);
         }
         catch (CoreException e)
         {
            // ignore
         }
         
/*
         boolean enableSpringSupport = chkEnableSpringSupport.getSelection();
*/         
         if (chkEnableBpmSupport.getSelection())
         {
            BpmProjectNature.enableBpmNature(project);
            if ( !isFacetedProject)
            {
               BpmClasspathUtils.addBpmCoreLibsContainer(project);
            }
         }
         else
         {
            if ( !isFacetedProject)
            {
               BpmClasspathUtils.removeBpmCoreLibsContainer(project);
            }
            BpmProjectNature.disableBpmNature(project);
/*            
            enableSpringSupport = false;
*/
         }
/*
         if (false && enableSpringSupport && !isFacetedProject)
         {
            BpmClasspathUtils.addBpmSpringLibsContainer(project);
         }
         else
         {
            BpmClasspathUtils.removeBpmSpringLibsContainer(project);
         }
*/
      }

      return super.performOk();
   }
}
