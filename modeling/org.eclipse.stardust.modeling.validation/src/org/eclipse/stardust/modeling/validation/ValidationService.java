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

import java.util.*;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.xpdl.carnot.*;

import ag.carnot.base.CollectionUtils;

public class ValidationService
{
   public static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   private final Map<IResource, Map<Long, Issue>> markerMappingRegistry;

   private ThreadLocal<IProgressMonitor> monitor = new ThreadLocal<IProgressMonitor>();

   ValidationService()
   {
      markerMappingRegistry = CollectionUtils.newMap();
   }

   public void createMapping(IMarker marker, Issue issue)
   {
      try
      {
         if (ValidationPlugin.VALIDATION_MARKER_ID.equals(marker.getType()))
         {
            Map<Long, Issue> mapping = markerMappingRegistry.get(marker.getResource());
            if (mapping == null)
            {
               mapping = CollectionUtils.newMap();
               markerMappingRegistry.put(marker.getResource(), mapping);
            }
            mapping.put(marker.getId(), issue);
         }
      }
      catch (CoreException e)
      {
         // TODO: handle exception
      }
   }

   public Issue resolveMapping(IMarker marker)
   {
      Issue issue = null;
      try
      {
         if (ValidationPlugin.VALIDATION_MARKER_ID.equals(marker.getType()))
         {
            issue = resolveMapping(marker.getResource(), marker.getId());
         }
      }
      catch (CoreException e)
      {
         // TODO: handle exception
      }

      return issue;
   }

   public Issue resolveMapping(IResource resource, long markerId)
   {
      Map<Long, Issue> mapping = markerMappingRegistry.get(resource);
      return mapping == null ? null : mapping.get(markerId);
   }

   public void removeMapping(IMarker marker)
   {
      try
      {
         if (ValidationPlugin.VALIDATION_MARKER_ID.equals(marker.getType()))
         {
            removeMapping(marker.getResource(), marker.getId());
         }
      }
      catch (CoreException e)
      {
         // TODO: handle exception
      }
   }

   public void removeMapping(IResource resource, long markerId)
   {
      Map<Long, Issue> mapping = markerMappingRegistry.get(resource);
      if (mapping != null)
      {
         mapping.remove(markerId);
      }
   }

   public void removeMappings(IResource resource)
   {
      Map<Long, Issue> mapping = markerMappingRegistry.get(resource);
      if (mapping != null)
      {
         mapping.clear();
      }
   }

   public Issue[] validateModel(ModelType model)
   {
      List<Issue> result = null;
      if (!isCanceled())
      {
         IModelValidator[] validators = ValidatorRegistry.getModelValidators();
         for (int i = 0; !isCanceled() && i < validators.length; i++)
         {
            try
            {
               Issue[] issues = validators[i].validate(model);
               result = safeAddIssues(result, issues);
            }
            catch (Exception e)
            {
               // TODO: handle exception
            }
         }
      }
      return safeToArray(result);
   }

   public Issue[] validateModelElements(List<?> elements)
   {
      List<Issue> result = null;
      if (!isCanceled())
      {
         for (Object element : elements)
         {
            if (isCanceled())
            {
               break;
            }
            if (element instanceof IModelElement)
            {
               Issue[] issues = validateModelElement((IModelElement) element);
               result = safeAddIssues(result, issues);
            }
            else
            {
               // TODO trace
            }
         }
      }
      return safeToArray(result);
   }

   public Issue[] validateModelElement(IModelElement element)
   {
      List<Issue> result = null;
      if (!isCanceled())
      {
         IModelElementValidator[] validators = ValidatorRegistry.getModelElementValidators(element);
         for (int i = 0; !isCanceled() && i < validators.length; i++)
         {
            try
            {
               Issue[] issues = validators[i].validate(element);
               result = safeAddIssues(result, issues);
            }
            catch (Exception e)
            {
               // TODO: handle exception
               if (e.getCause() instanceof ValidationException
                     && element instanceof DataMappingType)
               {
                  if (null == result)
                  {
                     result = CollectionUtils.newList();
                  }
                  ValidationException cause = (ValidationException) e.getCause();
                  EStructuralFeature feature = getFeature((DataMappingType) element, cause.getSource());
                  result.add(Issue.warning(element, cause.getMessage(), feature));
               }
            }
         }
      }

      return safeToArray(result);
   }

   private Issue[] safeToArray(List<Issue> result)
   {
      return result == null || isCanceled() ? Issue.ISSUE_ARRAY : result.toArray(Issue.ISSUE_ARRAY);
   }

   private List<Issue> safeAddIssues(List<Issue> result, Issue[] issues)
   {
      if (issues != null && issues.length > 0)
      {
         if (result == null)
         {
            result = CollectionUtils.newList();
         }
         for (int j = 0; j < issues.length; j++)
         {
            result.add(issues[j]);
         }
      }
      return result;
   }

   public void setProgressMonitor(IProgressMonitor monitor)
   {
      this.monitor.set(monitor);
   }

   private boolean isCanceled()
   {
      IProgressMonitor monitor = this.monitor.get();
      return monitor == null ? false : monitor.isCanceled();
   }

   private EStructuralFeature getFeature(DataMappingType dataMapping, Object source)
   {
      if (source != null)
      {
         if (source.equals(dataMapping.getData()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_Data();
         }
         if (source.equals(dataMapping.getDataPath()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_DataPath();
         }
         if (source instanceof AccessPointType
               && ((AccessPointType) source).getId().equals(
                     dataMapping.getApplicationAccessPoint()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_ApplicationAccessPoint();
         }
         if (source.equals(dataMapping.getApplicationPath()))
         {
            return ValidationService.PKG_CWM.getDataMappingType_ApplicationPath();
         }
      }
      return null;
   }
}
