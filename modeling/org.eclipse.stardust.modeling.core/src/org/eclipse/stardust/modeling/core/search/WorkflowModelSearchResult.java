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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultListener;
import org.eclipse.search.ui.SearchResultEvent;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class WorkflowModelSearchResult implements ISearchResult
{
   private WorkflowModelSearchQuery query;

   private Set matchedElements;

   private List searchResultListener;

   public WorkflowModelSearchResult(WorkflowModelSearchQuery query)
   {
      this.query = query;
      searchResultListener = new ArrayList();
   }

   public void addListener(ISearchResultListener l)
   {
      searchResultListener.add(l);
   }

   public void removeListener(ISearchResultListener l)
   {
      searchResultListener.remove(l);
   }

   public String getLabel()
   {
      return Diagram_Messages.LB_Search;
   }

   public String getTooltip()
   {
      return null;
   }

   public ImageDescriptor getImageDescriptor()
   {
      return null;
   }

   public ISearchQuery getQuery()
   {
      return query;
   }

   public void setMatchedElements(Set matchedElements)
   {
      this.matchedElements = matchedElements;
      fireChange(null);
   }

   private void fireChange(SearchResultEvent e)
   {
      for (Iterator iter = searchResultListener.iterator(); iter.hasNext();)
      {
         ((ISearchResultListener) iter.next()).searchResultChanged(e);
      }
   }

   public Set getMatchedElements()
   {
      return matchedElements;
   }
}
