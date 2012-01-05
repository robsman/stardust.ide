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
package org.eclipse.stardust.modeling.transformation.debug;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import org.eclipse.stardust.modeling.transformation.debug.model.JsStackFrame;
import org.eclipse.stardust.modeling.transformation.debug.views.TransformationMappingDebugView;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;


public class SourceLookupParticipant extends AbstractSourceLookupParticipant
{

   public String getSourceName(Object object) throws CoreException
   {
      if (object instanceof JsStackFrame)
      {
         JsStackFrame stackFrame = (JsStackFrame) object;
         
         showDebugView(stackFrame);
      }

      return null;
   }
   
   private void showDebugView(final JsStackFrame stackFrame)
   {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      if (null != workbench)
      {
         workbench.getDisplay().syncExec(new Runnable()
         {
            public void run()
            {
               try
               {
                  final IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow()
                        .getActivePage();
                  activePage.showView(TransformationMappingDebugView.VIEW_ID);
                  TransformationMappingDebugView debugView = (TransformationMappingDebugView) activePage
                        .findView(TransformationMappingDebugView.VIEW_ID);

                  if (null != debugView)
                  {
                     debugView.initWithModelElemet(stackFrame.getApplicationType());
                     debugView.highlightLine(stackFrame);
                     debugView.highlightOutMessageNode(stackFrame.getFieldPath());
                  }
                  //PlatformUI.getWorkbench().showPerspective(<perspective-id>, PlatformUI.getWorkbench().getActiveWorkbenchWindow());
               }
               catch (PartInitException e)
               {
                  // View cannot be shown.
               }
            }
         });
      }
   }
}
