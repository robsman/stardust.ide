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
package org.eclipse.stardust.modeling.core.marker;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.FixInvalidIdsAction;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.ui.IMarkerResolution;


/**
 * @author fherinean
 * @version $Revision: 13774 $
 */
public class IdentifiableResolutionGenerator implements IResolutionGenerator
{
   public boolean hasResolutions(WorkflowModelEditor editor, Issue issue)
   {
      if (issue.getModelElement() instanceof IIdentifiableElement)
      {
         if(CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id().equals(issue.getFeature()))
         {
            IModelElement element = (IModelElement) issue.getModelElement();
            String id = ((IIdentifiableElement) element).getId();
            
            if (id == null || id.length() == 0)
            {
               return true;
            }
            else if (hasBadCharacters(id))
            {
               return true;
            }
            else if (!Character.isJavaIdentifierStart(id.charAt(0)))
            {
               return true;
            }
         }
      }
      return false;
   }

   public void addResolutions(List<IMarkerResolution> list, WorkflowModelEditor editor, final Issue issue)
   {
      String id = null;
      IModelElement element = null;
      if (issue.getFeature().equals(CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id()))
      {
         element = (IModelElement) issue.getModelElement();
         id = ((IIdentifiableElement) element).getId();
      }
      if (element != null)
      {
         if (id == null || id.length() == 0)
         {
            list.add(new MarkerResolution(getAction(editor, element, "Create automatic ID"))); //$NON-NLS-1$
         }
         else if (hasBadCharacters(id))
         {
            list.add(new MarkerResolution(getAction(editor, element, "Convert to valid ID"))); //$NON-NLS-1$
         }
         else if (!Character.isJavaIdentifierStart(id.charAt(0)))
         {
            list.add(new MarkerResolution(getAction(editor, element, "Convert to valid ID"))); //$NON-NLS-1$
         }
      }
   }

   private boolean hasBadCharacters(String id)
   {
      for (int i = 0; i < id.length(); i++)
      {
         if (!Character.isJavaIdentifierPart(id.charAt(i)))
         {
            return true;
         }
      }
      return false;
   }

   private IAction getAction(final WorkflowModelEditor editor, final IModelElement element, String label)
   {
      return new Action(label)
      {
         public void run()
         {
            FixInvalidIdsAction.run(editor, element);
         }
      };
   }
}