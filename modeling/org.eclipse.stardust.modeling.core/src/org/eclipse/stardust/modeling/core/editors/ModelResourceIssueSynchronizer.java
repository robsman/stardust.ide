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
package org.eclipse.stardust.modeling.core.editors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.IssueDelta;
import org.eclipse.stardust.modeling.validation.ValidationPlugin;


public class ModelResourceIssueSynchronizer implements IResourceDeltaVisitor
{
   private final Map/* <Long, Issue> */markerMapping = new HashMap();

   private final ValidationIssueManager issueManager;

   public ModelResourceIssueSynchronizer(ValidationIssueManager issueManager)
   {
      this.issueManager = issueManager;
   }

   public boolean visit(IResourceDelta delta) throws CoreException
   {
      IMarkerDelta[] markerDeltas = delta.getMarkerDeltas();
      if ((null != markerDeltas) && (0 < markerDeltas.length))
      {
         Map issueDeltas = new HashMap();

         for (int i = 0; i < markerDeltas.length; i++ )
         {
            IMarkerDelta markerDelta = markerDeltas[i];
            if (ValidationPlugin.VALIDATION_MARKER_ID.equals(markerDelta.getType()))
            {
               if (IResourceDelta.ADDED == markerDelta.getKind())
               {
                  ValidationPlugin validationPlugin = ValidationPlugin.getDefault();
                  final Issue issue = validationPlugin != null ? validationPlugin
                        .getValidationService().resolveMapping(markerDelta.getResource(),
                              markerDelta.getId()) : null;
                  if (null != issue)
                  {
                     markerMapping.put(new Long(markerDelta.getId()), issue);

                     EObject element = issue.getModelElement();
                     if (null != element)
                     {
                        IssueDelta iDelta = (IssueDelta) issueDeltas.get(element);
                        if (null == iDelta)
                        {
                           iDelta = new IssueDelta(element);
                           issueDeltas.put(element, iDelta);
                        }
                        iDelta.issueAdded(issue);
                     }
                  }
               }
               else if (IResourceDelta.CHANGED == markerDelta.getKind())
               {
                  final Issue issue = (Issue) markerMapping.get(new Long(
                        markerDelta.getId()));
                  if (null != issue)
                  {
                     EObject element = issue.getModelElement();
                     if (null != element)
                     {
                        IssueDelta iDelta = (IssueDelta) issueDeltas.get(element);
                        if (null == iDelta)
                        {
                           iDelta = new IssueDelta(element);
                           issueDeltas.put(element, iDelta);
                        }
                        iDelta.issueModified(issue);
                     }
                  }
               }
               else if (IResourceDelta.REMOVED == markerDelta.getKind())
               {
                  final Issue issue = (Issue) markerMapping.get(new Long(
                        markerDelta.getId()));
                  if (null != issue)
                  {
                     EObject element = issue.getModelElement();
                     if (null != element)
                     {
                        IssueDelta iDelta = (IssueDelta) issueDeltas.get(element);
                        if (null == iDelta)
                        {
                           iDelta = new IssueDelta(element);
                           issueDeltas.put(element, iDelta);
                        }
                        iDelta.issueRemoved(issue);
                     }

                     markerMapping.remove(new Long(markerDelta.getId()));
                  }
               }
            }
         }

         issueManager.handleIssueUpdate((IssueDelta[]) issueDeltas.values().toArray(
               IssueDelta.ISSUE_DELTA_ARRAY));
      }

      return false;
   }
}