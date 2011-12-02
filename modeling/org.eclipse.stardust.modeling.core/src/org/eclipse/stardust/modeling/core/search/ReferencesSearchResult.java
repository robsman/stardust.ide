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
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.ISearchResultListener;
import org.eclipse.search.ui.SearchResultEvent;

// searchResultClass defined in searchResultViewPages extension
public class ReferencesSearchResult implements ISearchResult
{
   private ReferenceSearchQuery query;

   private Map matchedElements;
   private List searchResultListener;
   private String label;

   public void setLabel(String label)
   {
      this.label = label;
   }

   public ReferencesSearchResult(ReferenceSearchQuery query)
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
      return label;
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

   public void setMatchedElements(Map matchedElements)
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

   public Map getMatchedElements()
   {
      return matchedElements;
   }
}