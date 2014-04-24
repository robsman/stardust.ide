package org.eclipse.stardust.modeling.common.platform.utils;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.stardust.model.xpdl.spi.IResourceResolver;

public class WorkspaceResourceResolver implements IResourceResolver
{

   @Override
   public String resolveToLocalUri(String uri, EObject context)
   {
      String localUri = null;
      try
      {
         IProject project = WorkspaceUtils.getProjectFromEObject(context);
         if (project != null)
         {
            localUri = getFileUrl(project, uri);
         }
         else
         {
            IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
            for (int i = 0; i < projects.length; i++)
            {
               IProject proj = projects[i];
               String fileUri = getFileUrl(proj, uri);
               if (!isEmpty(fileUri))
               {
                  localUri = fileUri;
                  break;
               }
            }
         }
      }
      catch (Throwable t)
      {
         // ignore
      }

      return localUri;
   }

   private String getFileUrl(IProject project, String url)
   {
      String fileUri = null;
      try
      {
         if (project.hasNature(JavaCore.NATURE_ID))
         {
            IJavaProject javaProject = JavaCore.create(project);
            IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
            for (int i = 0; i < roots.length; i++)
            {
               IResource resource = roots[i].getCorrespondingResource();
               if (resource instanceof IFolder)
               {
                  IFolder folder = (IFolder) resource;
                  IFile file = folder.getFile(url);
                  if (file.exists())
                  {
                     fileUri = file.toString().substring(1); // strip type identifier
                     break;
                  }
               }
            }
         }
      }
      catch (CoreException e)
      {
         // TODO: handle
         e.printStackTrace();
      }
      return fileUri;
   }
}
