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
package org.eclipse.stardust.modeling.core.propertyTesters;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


public class EditPartPropertyTester extends PropertyTester
{
   /**
    * Executes the property test determined by the parameter <code>property</code>.
    * 
    * @param receiver
    *           the receiver of the property test
    * @param property
    *           the property to test
    * @param args
    *           additional arguments to evaluate the property. If no arguments are
    *           specified in the <code>test</code> expression an array of length 0 is
    *           passed
    * @param expectedValue
    *           the expected value of the property. The value is either of type
    *           <code>java.lang.String</code> or a boxed base type. If no value was
    *           specified in the <code>test</code> expressions then <code>null</code>
    *           is passed
    * 
    * @return returns <code>true<code> if the property is equal to the expected value; 
    *  otherwise <code>false</code> is returned
    */
   public boolean test(Object receiver, String property, Object[] args,
         Object expectedValue)
   {
      if ("modelElement".equals(property)) //$NON-NLS-1$
      {
         return testModelElement(receiver, args, expectedValue);
      }
      return false;
   }

   private boolean testModelElement(Object receiver, Object[] args, Object expectedValue)
   {
      IModelElement element = null;
      if (receiver instanceof IEditorInput)
      {
         IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
         if (window != null)
         {
            IWorkbenchPage page = window.getActivePage();
            if (page != null)
            {
               IEditorPart editor = page.getActiveEditor();
               if (receiver.equals(editor.getEditorInput()) && editor instanceof WorkflowModelEditor)
               {
                  WorkflowModelEditor wme = (WorkflowModelEditor) editor;
                  element = wme.getActiveDiagram();
               }
            }
         }
      }
      else if (receiver instanceof IAdaptable)
      {
         IAdaptable adaptable = (IAdaptable) receiver;
         element = (IModelElement) adaptable.getAdapter(IModelElement.class);
      }
      if (element != null)
      {
         if (expectedValue instanceof String)
         {
            boolean checkParent = false;
            for (int i = 0; i < args.length; i++)
            {
               if ("checkParent".equals(args[i])) //$NON-NLS-1$
               {
                  checkParent = true;
                  break;
               }
            }
            try
            {
               Class expectedClass = Class.forName((String) expectedValue);
               return isInstance(element, expectedClass, checkParent);
            }
            catch (Exception e)
            {
               // todo: some message? or simply ignore?
            }
         }
      }
      return false;
   }

   private boolean isInstance(IModelElement element, Class expectedClass, boolean checkParent)
   {
      boolean result = expectedClass.isInstance(element);
      if (!result && checkParent && element.eContainer() instanceof IModelElement)
      {
         result = isInstance((IModelElement) element.eContainer(), expectedClass, checkParent);
      }
      return result;
   }
}
