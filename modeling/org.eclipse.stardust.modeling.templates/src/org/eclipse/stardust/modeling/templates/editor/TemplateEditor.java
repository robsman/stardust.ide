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
package org.eclipse.stardust.modeling.templates.editor;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;
import org.eclipse.stardust.modeling.templates.emf.template.util.TemplateManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.*;



/**
 * TODO
 */
public class TemplateEditor extends WorkflowModelEditor
      implements IResourceChangeListener
{
   public TemplateEditor()
   {
      ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
   }

   /**
    * Provides a TemplateManager
    */
   protected ExtendedModelManager createModelManager()
   {
      return new TemplateManager();
   }

   /**
    * The <code>MultiPageEditorPart</code> implementation of this
    * <code>IWorkbenchPart</code> method disposes all nested editors. Subclasses may
    * extend.
    */
   public void dispose()
   {
      ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
      super.dispose();
   }

   /**
    * Closes all project files on project close.
    */
   public void resourceChanged(final IResourceChangeEvent event)
   {
      if (event.getType() == IResourceChangeEvent.PRE_CLOSE)
      {
         Display.getDefault().asyncExec(new Runnable()
         {
            public void run()
            {
               IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
               for (int i = 0; i < pages.length; i++)
               {
                  IEditorInput editorInput = getEditorInput();
                  if ((editorInput instanceof IFileEditorInput)
                        && ((IFileEditorInput) editorInput).getFile().getProject().equals(event.getResource()))
                  {
                     IEditorPart editorPart = pages[i].findEditor(editorInput);
                     pages[i].closeEditor(editorPart, true);
                  }
               }
            }
         });
      }
   }
}
