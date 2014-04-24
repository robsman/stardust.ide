package org.eclipse.stardust.modeling.common.platform.utils;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class WorkspaceUtils
{
   public static String resolveAbsolutePath(String projectName, String resourcePath)
   {
      IWorkspaceRoot wspRoot = ResourcesPlugin.getWorkspace().getRoot();

      if (null != wspRoot && null != projectName)
      {
         IProject wspProject = wspRoot.getProject(projectName);
         if (null != wspProject && null != resourcePath)
         {
            IResource resource = wspProject.findMember(resourcePath);

            if (null != resource)
            {
               return resource.getLocation().toFile().getAbsolutePath();
            }
         }
      }

      return null;
   }

   public static String getLocation(ModelType model)
   {
      String projectName = null;
      String modelFilePath = null;
      Resource eResource = model.eResource();
      if (eResource != null)
      {
         URI eUri = eResource.getURI();
         if (!eUri.isPlatform())
         {
            return eUri.toFileString();
         }
         URI projectUri = eUri.trimSegments(eUri.segmentCount() - 2);
         URI modelUri = eUri.deresolve(projectUri);
         IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
            eUri.segment(1));
         IProject project = null;
         if (resource instanceof IProject)
         {
            project = (IProject) resource;
         }
         else if (resource != null)
         {
            project = resource.getProject();
         }
         if (project != null)
         {
            projectName = project.getName();
            modelFilePath = modelUri.toString();
            if (modelFilePath.startsWith(projectName + "/")) //$NON-NLS-1$
            {
               modelFilePath = modelFilePath.substring(projectName.length() + 1);
            }
         }
      }
      if (modelFilePath == null || projectName == null)
      {
         return null;
      }

      return resolveAbsolutePath(projectName, modelFilePath);
   }

   // TODO: duplicate method VersionRepository, need to put it in a common place
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
               IResource resource = ResourcesPlugin.getWorkspace().getRoot()
                     .findMember(eUri.segment(1));
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

   private WorkspaceUtils()
   {
      // utility class
   }
}
