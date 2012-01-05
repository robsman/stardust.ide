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
package org.eclipse.stardust.modeling.validation.util;

import java.util.Arrays;
import java.util.StringTokenizer;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.TypeNameRequestor;

/**
 * @author fherinean
 * @version $Revision$
 */
public class TypeRequestor extends TypeNameRequestor
{
   private static final char SEPARATOR = '/';
   private static final char EXTENSION_SEPARATOR = '.';

   private boolean stopped;

   private char[] typeName;
   private char[][] enclosing;
   private IJavaSearchScope scope;
   private TypeFinderListener listener;
   private int pattern;

   public TypeRequestor(String typeName, IJavaSearchScope scope, int pattern,
                        TypeFinderListener listener)
   {
	  this.pattern = pattern;
	  if (typeName.length() > 0)
	  {
         StringTokenizer st = new StringTokenizer(typeName, "$"); //$NON-NLS-1$
         int c = st.countTokens();
         if (c > 1)
         {
            enclosing = new char[c - 1][];
            for (int i = 0; i < c - 1; i++)
            {
               enclosing[i] = st.nextToken().toCharArray();
            }
         }
         this.typeName = st.nextToken().toCharArray();
	  }
	  else
	  {
         this.typeName = typeName.toCharArray();
	  }
      this.scope = scope;
      this.listener = listener;
   }

   public synchronized void acceptType(int modifiers, char[] packageName,
         char[] simpleTypeName, char[][] enclosingTypeNames, String path)
   {
      if (!stopped)
      {
         if (matches(modifiers, simpleTypeName, enclosingTypeNames))
         {
            StringBuffer qualifiedName = new StringBuffer();
            for (int i = 0; i < enclosingTypeNames.length; i++)
            {
               qualifiedName.append(enclosingTypeNames[i]).append('.');
            }
            qualifiedName.append(simpleTypeName);
            IType result = null;
            IJavaModel model = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
            int index = path.indexOf(IJavaSearchScope.JAR_FILE_ENTRY_SEPARATOR);
            try
            {
               if (index != -1)
               {
                  result = getJarIType(model, packageName, qualifiedName.toString(), path, index);
               }
               else
               {
                  result = getFileIType(model, packageName, qualifiedName.toString(), path);
               }
            }
            catch (JavaModelException e)
            {
            }
            if (result != null)
            {
               listener.typeFound(result);
            }
         }
      }
   }

   private boolean matches(int modifiers, char[] simpleTypeName, char[][] enclosingTypeNames)
   {
      switch (pattern)
      {
         case SearchPattern.R_EXACT_MATCH:
            return Arrays.equals(typeName, simpleTypeName) && isPublic(modifiers) && isEnclosed(enclosingTypeNames);
         case SearchPattern.R_PREFIX_MATCH:
            if (typeName.length == 0)
            {
               return true;
            }
            if (simpleTypeName.length < typeName.length)
            {
               return false;
            }
            for (int i = 0; i < typeName.length; i++)
            {
               if (typeName[i] != simpleTypeName[i])
               {
                  return false;
               }
        	   }
            return true;
      }
      return false;
   }

   private IType getFileIType(IJavaModel model, char[] packageName, String qualifiedName, String path) throws JavaModelException
   {
      IJavaProject project = null;
      IJavaProject[] projects = model.getJavaProjects();
      for (int i = 0; i < projects.length; i++)
      {
         if (path.startsWith(projects[i].getElementName(), 1))
         {
            if (project == null || projects[i].getElementName().length() > project.getElementName().length())
            {
               project = projects[i];
            }
         }
      }

      if (project != null)
      {
         return project.findType(String.valueOf(packageName), qualifiedName);
      }
      return null;
   }

   private IType getJarIType(IJavaModel model, char[] packageName, String qualifiedName, String path, int index) throws JavaModelException
   {
      String jar = path.substring(0, index);
      String rest = path.substring(index + 1);
      index = rest.lastIndexOf(SEPARATOR);
      if (index != -1)
      {
         rest = rest.substring(index + 1);
      }
      index = rest.lastIndexOf(EXTENSION_SEPARATOR);
      if (index != -1)
      {
         String file = rest.substring(0, index);
         String extension = rest.substring(index + 1);

         IPath[] enclosedPaths = scope.enclosingProjectsAndJars();
         for (int i = 0; i < enclosedPaths.length; i++)
         {
            IPath curr = enclosedPaths[i];
            if (curr.segmentCount() == 1)
            {
               IJavaProject project = model.getJavaProject(curr.segment(0));
               IPackageFragmentRoot root = project.getPackageFragmentRoot(jar);
               if (root.exists())
               {
                  IPackageFragment fragment = root.getPackageFragment(String.valueOf(packageName));
                  if (fragment.exists())
                  {
                     if ("class".equals(extension)) //$NON-NLS-1$
                     {
                        IClassFile classFile = fragment.getClassFile(file + ".class"); //$NON-NLS-1$
                        if (classFile.exists())
                        {
                           return classFile.getType();
                        }

                     }
                     else if ("java".equals(extension)) //$NON-NLS-1$
                     {
                        ICompilationUnit unit = fragment.getCompilationUnit(file + ".java"); //$NON-NLS-1$
                        IType[] types = unit.getAllTypes();
                        for (int j = 0; j < types.length; j++)
                        {
                           if (qualifiedName.equals(types[j].getTypeQualifiedName('.')))
                           {
                              return types[j];
                           }
                        }
                     }
                  }
                  break;
               }
            }
         }
      }
      return null;
   }

   private boolean isEnclosed(char[][] enclosingTypeNames)
   {
      if (enclosing == null)
      {
         return enclosingTypeNames.length == 0;
      }
      if (enclosing.length != enclosingTypeNames.length)
      {
         return false;
      }
      for (int i = 0; i < enclosing.length; i++)
      {
         if (!Arrays.equals(enclosing[i], enclosingTypeNames[i]))
         {
            return false;
         }
      }
      return true;
   }

   private boolean isPublic(int modifiers)
   {
      return Flags.isInterface(modifiers) || Flags.isPublic(modifiers);
   }

   public synchronized void stop()
   {
      stopped = true;
   }

   public char[] getTypeName()
   {
      return typeName;
   }

   public TypeFinderListener getListener()
   {
      return listener;
   }

   public IJavaSearchScope getScope()
   {
      return scope;
   }

   public int getPattern()
   {
      return pattern;
   }
}
