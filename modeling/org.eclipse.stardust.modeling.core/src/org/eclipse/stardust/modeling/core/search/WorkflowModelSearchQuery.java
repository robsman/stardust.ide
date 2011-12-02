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
package org.eclipse.stardust.modeling.core.search;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class WorkflowModelSearchQuery implements ISearchQuery
{

   private final String searchText;

   private final ModelType model;

   private final WorkflowModelSearchResult result;

   private Set matchedElements = new HashSet();

   public WorkflowModelSearchQuery(String searchText, ModelType model)
   {
      this.searchText = searchText.toLowerCase();
      this.model = model;
      result = new WorkflowModelSearchResult(this);
   }

   public IStatus run(IProgressMonitor monitor) throws OperationCanceledException
   {
      String searchString;
      String[] searchStrings = searchText.split(" "); //$NON-NLS-1$
      if (searchStrings.length > 1)
      {
         for (int i = 0; i < searchStrings.length; i++)
         {
            search(searchStrings[i]);
         }
      }
      else
      {
         searchString = searchText;
         search(searchString);
      }

      result.setMatchedElements(matchedElements);

      return Status.OK_STATUS;
   }

   private void search(String searchString)
   {
      EObject element;
      for (Iterator iter = model.eAllContents(); iter.hasNext();)
      {
         element = (EObject) iter.next();
         if ((element instanceof IIdentifiableModelElement)
               && (!(element instanceof IMetaType)))
         {
            IIdentifiableModelElement identifiableElement = ((IIdentifiableModelElement) element);
            if (identifiableElement.getName() != null && identifiableElement.getName().toLowerCase().indexOf(searchString) >= 0)
            {
               matchedElements.add(identifiableElement);
            }
            String description = ModelUtils.getDescriptionText(identifiableElement.getDescription());
            if (description != null
                  && description.toLowerCase().indexOf(searchString) >= 0)
            {
               matchedElements.add(identifiableElement);
               matchedElements.add(identifiableElement.getDescription());
            }
         }
      }
   }

   public String getLabel()
   {
      return Diagram_Messages.LB_SearchQuery;
   }

   public boolean canRerun()
   {
      return false;
   }

   public boolean canRunInBackground()
   {
      return true;
   }

   public ISearchResult getSearchResult()
   {
      return result;
   }

   public String getSearchText()
   {
      return searchText;
   }
}