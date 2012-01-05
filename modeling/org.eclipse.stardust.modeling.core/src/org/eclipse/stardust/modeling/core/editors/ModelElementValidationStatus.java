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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.modeling.validation.Issue;


final class ModelElementValidationStatus implements IEObjectValidationStatus
{
   private Map /*<EStructuralFeature, FeatureStatusView>*/ statusPerFeature;
   
   private List infos;
   private Map/*<List<Issue>>*/ eFtrInfos;

   private List warnings;
   private Map/*<List<Issue>>*/ eFtrWarnings;

   private List errors;
   private Map/*<List<Issue>>*/ eFtrErrors;

   private Set affectedParents;

   private Set childrenWithInfos;

   private Set childrenWithWarnings;

   private Set childrenWithErrors;

   public boolean hasIssues()
   {
      return hasErrors() || hasWarnings() || hasInfos();
   }

   boolean hasChildrenWithIssues()
   {
      return !getChildrenWithErrors().isEmpty() || !getChildrenWithWarnings().isEmpty()
            || !getChildrenWithInfos().isEmpty();
   }

   public boolean hasInfos()
   {
      return !getInfos().isEmpty();
   }

   public List getInfos()
   {
      return nullSafeGetList(infos);
   }

   public boolean hasWarnings()
   {
      return !getWarnings().isEmpty();
   }

   public List getWarnings()
   {
      return nullSafeGetList(warnings);
   }

   public boolean hasErrors()
   {
      return !getErrors().isEmpty();
   }

   public List getErrors()
   {
      return nullSafeGetList(errors);
   }

   public Set getChildrenWithInfos()
   {
      return nullSafeGetSet(childrenWithInfos);
   }

   public Set getChildrenWithWarnings()
   {
      return nullSafeGetSet(childrenWithWarnings);
   }

   public Set getChildrenWithErrors()
   {
      return nullSafeGetSet(childrenWithErrors);
   }

   public IValidationStatus getFeatureStatus(Object feature)
   {
      IValidationStatus status = (null != statusPerFeature)
            ? (IValidationStatus) statusPerFeature.get(feature)
            : null;
      if (null == status)
      {
         status = new FeatureStatusView(feature);

         if (null == statusPerFeature)
         {
            statusPerFeature = new HashMap();
         }
         statusPerFeature.put(feature, status);
      }      
      return status;
   }

   void addIssue(Issue issue)
   {
      if (issue.isInfo())
      {
         this.infos = addIssue(infos, issue);
         this.eFtrInfos = addIssue(eFtrInfos, issue.getFeature(), issue);
      }
      else if (issue.isWarning())
      {
         this.warnings = addIssue(warnings, issue);
         this.eFtrWarnings = addIssue(eFtrWarnings, issue.getFeature(), issue);
      }
      else if (issue.isError())
      {
         this.errors = addIssue(errors, issue);
         this.eFtrErrors = addIssue(eFtrErrors, issue.getFeature(), issue);
      }
   }

   void removeIssue(Issue issue)
   {
      if (issue.isInfo())
      {
         this.infos = removeIssue(infos, issue);
         this.eFtrInfos = removeIssue(eFtrInfos, issue.getFeature(), issue);
      }
      else if (issue.isWarning())
      {
         this.warnings = removeIssue(warnings, issue);
         this.eFtrWarnings = removeIssue(eFtrWarnings, issue.getFeature(), issue);
      }
      else if (issue.isError())
      {
         this.errors = removeIssue(errors, issue);
         this.eFtrErrors = removeIssue(eFtrErrors, issue.getFeature(), issue);
      }
   }

   Set getAffectedParents()
   {
      return nullSafeGetSet(affectedParents);
   }

   void addAffectedParent(Object parent)
   {
      if (null == this.affectedParents)
      {
         this.affectedParents = new HashSet();
      }
      this.affectedParents.add(parent);
   }

   void removeAffectedParent(Object parent)
   {
      if (null != this.affectedParents)
      {
         this.affectedParents.remove(parent);
      }
   }

   void clearAffectedParents()
   {
      if (null != affectedParents)
      {
         affectedParents.clear();
      }
   }

   void updateChildStatus(EObject child, ModelElementValidationStatus childStatus)
   {
      this.childrenWithInfos = updateChildStatus(childrenWithInfos,
            !childStatus.getInfos().isEmpty(), child, childStatus);

      this.childrenWithWarnings = updateChildStatus(childrenWithWarnings,
            !childStatus.getWarnings().isEmpty(), child, childStatus);

      this.childrenWithErrors = updateChildStatus(childrenWithErrors,
            !childStatus.getErrors().isEmpty(), child, childStatus);
   }

   private static List nullSafeGetList(List list)
   {
      return (null == list) ? Collections.EMPTY_LIST : list;
   }

   private static List nullSafeGetList(Map eFtrMap, Object eFtr)
   {
      List list = (null != eFtrMap) ? (List) eFtrMap.get(eFtr) : null;
      return (null == list) ? Collections.EMPTY_LIST : list;
   }

   private static Set nullSafeGetSet(Set set)
   {
      return (null == set) ? Collections.EMPTY_SET : set;
   }

   private static List addIssue(List issues, Issue issue)
   {
      if (null == issues)
      {
         issues = new ArrayList();
      }
      if ( !issues.contains(issue))
      {
         issues.add(issue);
      }
      return issues;
   }

   private static Map addIssue(Map eFtrIssues, Object eFtr, Issue issue)
   {
      if (null == eFtrIssues)
      {
         eFtrIssues = new HashMap();
      }
      List issues = (List) eFtrIssues.get(eFtr);
      if (null == issues)
      {
         issues = new ArrayList();
         eFtrIssues.put(eFtr, issues);
      }
      if ( !issues.contains(issue))
      {
         issues.add(issue);
      }
      return eFtrIssues;
   }

   private static List removeIssue(List issues, Issue issue)
   {
      if (null != issues)
      {
         issues.remove(issue);
      }
      return issues;
   }

   private static Map removeIssue(Map eFtrIssues, Object eFtr, Issue issue)
   {
      if (null != eFtrIssues)
      {
         List issues = (List) eFtrIssues.get(eFtr);
         if (null != issues)
         {
            eFtrIssues.remove(issue);
         }            
      }
      return eFtrIssues;
   }

   private static Set updateChildStatus(Set children, boolean doesQualify,
         EObject child, ModelElementValidationStatus childStatus)
   {
      if (doesQualify)
      {
         if (null == children)
         {
            children = new HashSet();
         }
         children.add(child);
      }
      else
      {
         if (null != children)
         {
            children.remove(child);
         }
      }

      return children;
   }
   
   private final class FeatureStatusView implements IValidationStatus
   {
      private final Object feature;
      
      FeatureStatusView(Object feature)
      {
         this.feature = feature;
      }
      
      public boolean hasIssues()
      {
         return hasErrors() || hasWarnings() || hasInfos();
      }

      public boolean hasInfos()
      {
         return !getInfos().isEmpty();
      }

      public List getInfos()
      {
         return ModelElementValidationStatus.nullSafeGetList(eFtrInfos, feature);
      }

      public boolean hasWarnings()
      {
         return !getWarnings().isEmpty();
      }

      public List getWarnings()
      {
         return ModelElementValidationStatus.nullSafeGetList(eFtrWarnings, feature);
      }

      public boolean hasErrors()
      {
         return !getErrors().isEmpty();
      }

      public List getErrors()
      {
         return ModelElementValidationStatus.nullSafeGetList(eFtrErrors, feature);
      }

      public Set getChildrenWithInfos()
      {
         return ModelElementValidationStatus.this.getChildrenWithInfos();
      }

      public Set getChildrenWithWarnings()
      {
         return ModelElementValidationStatus.this.getChildrenWithWarnings();
      }

      public Set getChildrenWithErrors()
      {
         return ModelElementValidationStatus.this.getChildrenWithErrors();
      }
   }
}