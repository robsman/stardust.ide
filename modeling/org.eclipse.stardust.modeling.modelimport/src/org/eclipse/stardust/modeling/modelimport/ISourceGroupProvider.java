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

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Interface for all third party model import plugins.
 * 
 * @author amueller
 * @version $Revision$
 */
public interface ISourceGroupProvider
{
   /**
    * Creates the UI to define the information to get the third party model.
    * 
    * @param parent
    * @param wizardPage
    * @return Control
    */
   public Control createSourceGroup(Composite parent, IImportModelWizardPage wizardPage);

   /**
    * @param optionsGroup
    */
   public Control createAdditionalOptionsGroup(Composite optionsGroup, boolean enabled);

   /**
    * Checks if all entries are done.
    * 
    * @return boolean
    */
   public boolean isComplete();

   /**
    * Gets the external resource.
    * 
    * @return Resource or null if no resource is selected.
    */
   public Resource getExternalResource();

   /**
    * Gets ths internal resource.
    * 
    * @param containerFullPath
    * @return Resource path or null if no resource is selected.
    */
   public IPath getResourcePath(IPath containerFullPath);

   /**
    * @param parent
    * @param page
    * @return Control or null if no advancedExpandableControl is created.
    */
   public Control createAdvancedExpandableControl(Composite parent,
         IImportModelWizardPage wizardPage);
}
