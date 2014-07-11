package org.eclipse.stardust.modeling.validation;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.stardust.common.CollectionUtils;

public class ValidationMarkerService
{
   private static AtomicReference<ValidationMarkerService> service = new AtomicReference<ValidationMarkerService>();

   private final Map<IResource, Map<Long, Issue>> markerMappingRegistry = CollectionUtils.newMap();

   public static ValidationMarkerService getInstance()
   {
      if (null == service.get())
      {
         service.compareAndSet(null, new ValidationMarkerService());
      }

      return service.get();
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

   private ValidationMarkerService()
   {
      // utility class
   }
}
