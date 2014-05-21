package org.eclipse.stardust.modeling.validation.util;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.stardust.model.xpdl.carnot.ContextType;

public class WorkspaceValidationUtils
{
   // TODO: duplicate method WorkspaceUtils, need to put it in a common place
   public static IProject getProjectFromEObject(EObject eObject)
   {
      if (eObject instanceof ContextType)
      {
         ContextType contextType = (ContextType) eObject;
         if (contextType.getType() != null)
         {
            eObject = contextType.getType().eContainer();
         }
      }
      if (eObject != null)
      {
         Resource eResource = eObject.eResource();
         if (eResource != null)
         {
            URI eUri = eResource.getURI();

            if (eUri.isFile())
            {
               String fileString = eUri.toFileString();
               java.net.URI netModelUri = new File(fileString).toURI();
               IContainer[] containers = ResourcesPlugin.getWorkspace().getRoot()
                     .findContainersForLocationURI(netModelUri);
               if (containers != null && containers.length > 0)
               {
                  IContainer container = containers[0];
                  return container.getProject();
               }
            }

            if (eUri.segmentCount() > 1)
            {
               IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
                  eUri.segment(1));
               if (resource instanceof IProject)
               {
                  return (IProject) resource;
               }
               else if (resource != null)
               {
                  return resource.getProject();
               }
            }
         }
      }
      return null;
   }

   private WorkspaceValidationUtils()
   {
      // utility class
   }
}
