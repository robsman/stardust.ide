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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


public class CleanupModelSearchQuery implements ISearchQuery
{
   private final CleanupModelSearchResult result;
   private Map matchedElements = new HashMap();

   // creates clean search result (CleanupModelSearchResult as defined in plugin.xml)
   public CleanupModelSearchQuery()
   {
      result = new CleanupModelSearchResult(this);
   }

   public IStatus run(IProgressMonitor monitor) throws OperationCanceledException
   {
      // sets empty collection
      result.setMatchedElements(matchedElements);
      return Status.OK_STATUS;
   }

   public boolean canRerun()
   {
      return false;
   }

   public boolean canRunInBackground()
   {
      return true;
   }

   public String getLabel()
   {
      return Diagram_Messages.LB_UnusedModelElements;
   }

   public ISearchResult getSearchResult()
   {
      return result;
   }
}