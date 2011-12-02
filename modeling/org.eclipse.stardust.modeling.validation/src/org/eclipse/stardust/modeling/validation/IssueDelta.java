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
package org.eclipse.stardust.modeling.validation;

import java.util.Collections;
import java.util.List;

import ag.carnot.base.CollectionUtils;

public class IssueDelta
{
   public static final IssueDelta[] ISSUE_DELTA_ARRAY = new IssueDelta[0];

   private final Object target;
   
   private List<Issue> addedIssues;
   
   private List<Issue> modifiedIssues;
   
   private List<Issue> removedIssues;
   
   public IssueDelta(Object target)
   {
     this.target = target; 
   }

   public Object getTarget()
   {
      return target;
   }
   
   public List<Issue> getAddedIssues()
   {
      return nullSafeGet(addedIssues);
   }
   
   public void issueAdded(Issue issue)
   {
      addedIssues = addElement(addedIssues, issue);
   }

   public void issuesAdded(Issue[] issues)
   {
      addedIssues = addElements(addedIssues, issues);
   }

   public List<Issue> getModifiedIssues()
   {
      return nullSafeGet(modifiedIssues);
   }

   public void issueModified(Issue issue)
   {
      modifiedIssues = addElement(modifiedIssues, issue);
   }

   public void issuesModified(Issue[] issues)
   {
      modifiedIssues = addElements(modifiedIssues, issues);
   }

   public List<Issue> getRemovedIssues()
   {
      return nullSafeGet(removedIssues);
   }

   public void issueRemoved(Issue issue)
   {
      removedIssues = addElement(removedIssues, issue);
   }

   public void issuesRemoved(Issue[] issues)
   {
      removedIssues = addElements(removedIssues, issues);
   }

   private static List<Issue> nullSafeGet(List<Issue> list)
   {
      return list == null ? Collections.<Issue>emptyList() : list;
   }
   
   private static List<Issue> addElement(List<Issue> list, Issue element)
   {
      if (list == null)
      {
         list = CollectionUtils.newList();
      }
      list.add(element);
      return list;
   }

   private static List<Issue> addElements(List<Issue> list, Issue[] elements)
   {
      if (list == null)
      {
         list = CollectionUtils.newList();
      }
      for (int i = 0; i < elements.length; i++)
      {
         list.add(elements[i]);
      }
      return list;
   }
}
