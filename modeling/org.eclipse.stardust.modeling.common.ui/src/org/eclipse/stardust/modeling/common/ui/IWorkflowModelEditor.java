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
package org.eclipse.stardust.modeling.common.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;


public interface IWorkflowModelEditor extends IEditorPart
{
   void updateEditor(IWorkbenchPage page);

   ModelType getWorkflowModel();

   void updateModel(IEditorInput editorInput) throws PartInitException;
   
   boolean requireLock(EObject eObject);
}
