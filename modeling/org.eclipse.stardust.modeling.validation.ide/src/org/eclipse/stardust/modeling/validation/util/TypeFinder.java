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

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.Money;
import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import org.osgi.framework.Bundle;

/**
 * @author fherinean
 * @version $Revision$
 */
public class TypeFinder
{
   private static final Class<?>[] PRIMITIVE_TYPES = new Class[] {
         Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE,
         Float.TYPE, Double.TYPE};

   private SearchJob finder;

   private IJavaSearchScope scope;

   private IJavaProject project;

   private static final String PLUGIN_ID = "org.eclipse.stardust.modeling.common.platform"; //$NON-NLS-1$

   private EObject modelElement;

   private MethodFilter filter;

   public TypeFinder(IJavaProject project)
   {
      this.project = project;
      scope = project == null ? SearchEngine.createWorkspaceScope() : SearchEngine
            .createJavaSearchScope(new IJavaElement[] {project});
   }

   public TypeFinder(IProject project)
   {
      this(JavaCore.create(project));
   }

   public TypeFinder(IResource resource)
   {
      this(resource == null ? null : resource.getProject());
   }

   public TypeFinder(EObject object)
   {
      this(getProjectFromEObject(object));
      this.modelElement = object;
   }

   public TypeFinder(IType iType)
   {
      this(iType == null ? null : iType.getJavaProject());
   }

   public static IProject getProjectFromEObject(EObject eObject)
   {
      if (eObject != null)
      {
         return WorkspaceValidationUtils.getProjectFromEObject(eObject);
      }
      return null;
   }

   public void findTypes(String prefix, TypeFinderListener listener)
   {
      stop();
      if (prefix.length() != 0)
      {
         int ix = prefix.lastIndexOf('.');
         String packageName = ix < 0 ? null : prefix.substring(0, ix);
         String typeName = ix < 0 ? prefix : prefix.substring(ix + 1);
         finder = new SearchJob(packageName, typeName, scope,
               SearchPattern.R_PREFIX_MATCH, listener);
      }
      schedule(listener);
   }

   private void stop()
   {
      if (finder != null)
      {
         finder.stop();
      }
      finder = null;
   }

   private void schedule(TypeFinderListener listener)
   {
      if (finder != null)
      {
         finder.schedule();
      }
      else
      {
         listener.startSearch();
         listener.endSearch();
      }
   }

   IType findExactType(String proto)
   {
      Class<?> primitiveWrapper = getPrimitiveWrapper(proto);
      if (null != primitiveWrapper)
      {
         proto = primitiveWrapper.getName();
      }

      if (project != null)
      {
         try
         {
            return project.findType(proto);
         }
         catch (JavaModelException e)
         {
            // e.printStackTrace();
         }
      }
      final IType[] result = new IType[] {null};
      if (proto.length() != 0 && proto.charAt(proto.length() - 1) != '.')
      {
         int ix = proto.lastIndexOf('.');
         SearchJob finder = new SearchJob(ix < 0 ? null : proto.substring(0, ix), ix < 0
               ? proto
               : proto.substring(ix + 1), scope, SearchPattern.R_EXACT_MATCH,
               new TypeFinderListener()
               {
                  public void typeFound(IType type)
                  {
                     result[0] = type;
                  }

                  public void startSearch()
                  {}

                  public void endSearch()
                  {}
               });
         finder.run(null);
      }
      return result[0];
   }

   public boolean implementsInterface(String className, String interfaceName)
   {
      if (interfaceName.equals(className))
      {
         return true;
      }

      IType type = findExactType(className);
      return implementsInterface(type, interfaceName);
   }

   // todo: make deep search
   public boolean implementsInterface(IType type, String interfaceName)
   {
      if (null == type)
      {
         return false;
      }

      if (interfaceName.equals(type.getFullyQualifiedName()))
      {
         return true;
      }

      try
      {
         String[] signatures = type.getSuperInterfaceTypeSignatures();
         for (int i = 0; i < signatures.length; i++)
         {
            String superName = resolveName(type, signatures[i]);
            if (interfaceName.equals(superName))
            {
               return true;
            }
         }
      }
      catch (Exception e)
      {
         // e.printStackTrace();
      }
      return false;
   }

   public boolean extendsClass(String actualClassName, String className)
   {
      if (className.equals(actualClassName))
      {
         return true;
      }

      IType type = findExactType(actualClassName);
      return (null != type) ? extendsClass(type, className) : false;
   }

   public boolean extendsClass(IType type, String className)
   {
      if (className.equals(type.getFullyQualifiedName()))
      {
         return true;
      }

      try
      {
         String superName = resolveName(type, type.getSuperclassTypeSignature());
         if (className.equals(superName))
         {
            return true;
         }
         if ("java.lang.Object".equals(superName)) //$NON-NLS-1$
         {
            return false;
         }
         return extendsClass(superName, className);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return false;
   }

   public static String resolveName(IType type, String signature)
         throws JavaModelException
   {
      String typeName = null;
      int arrayCount = Signature.getArrayCount(signature);
      // todo: this does not take into account generics !!!
      if (signature.charAt(arrayCount) == Signature.C_UNRESOLVED)
      {
         String[][] resolved = type.resolveType(Signature.toString(signature));
         if (resolved.length == 0)
         {
            warn(Validation_Messages.MSG_UnableToResolve + Signature.toString(signature));
         }
         else if (resolved.length > 1)
         {
            warn(Validation_Messages.MSG_AmbiguousDeclaration
                  + Signature.toString(signature));
         }
         else
         {
            typeName = Signature.toQualifiedName(resolved[0]);
         }
      }
      else
      {
         typeName = Signature.toString(signature);
      }
      return typeName;
   }

   public static void warn(String message)
   {
      log(new Status(IStatus.WARNING, PLUGIN_ID, 0, message, null));
   }

   public static void log(IStatus status)
   {
      try
      {
         Bundle bundle = Platform.getBundle(PLUGIN_ID);
         Platform.getLog(bundle).log(status);
      }
      catch (Exception ex)
      {
      }
   }

   @SuppressWarnings("deprecation")
   public static String getClassFromAbbreviatedName(String className)
   {
      Class<?> resolvedClass = null;

      if (StringUtils.isEmpty(className))
      {
         resolvedClass = String.class;
      }
      else if (Type.String.getId().equals(className))
      {
         resolvedClass = String.class;
      }
      else if (Type.Boolean.getId().equals(className))
      {
         resolvedClass = Boolean.class;
      }
      else if (Type.Char.getId().equals(className))
      {
         resolvedClass = Character.class;
      }
      else if (Type.Byte.getId().equals(className))
      {
         resolvedClass = Byte.class;
      }
      else if (Type.Short.getId().equals(className))
      {
         resolvedClass = Short.class;
      }
      else if (Type.Integer.getId().equals(className))
      {
         resolvedClass = Integer.class;
      }
      else if (Type.Long.getId().equals(className))
      {
         resolvedClass = Long.class;
      }
      else if (Type.Float.getId().equals(className))
      {
         resolvedClass = Float.class;
      }
      else if (Type.Double.getId().equals(className))
      {
         resolvedClass = Double.class;
      }
      else if (Type.Money.getId().equals(className))
      {
         resolvedClass = Money.class;
      }
      else if (Type.Calendar.getId().equals(className))
      {
         resolvedClass = Calendar.class;
      }
      else if (Type.Timestamp.getId().equals(className))
      {
         resolvedClass = Date.class;
      }
      else if ("Period".equals(className)) //$NON-NLS-1$
      {
         resolvedClass = Period.class;
      }
      else if ("decimal".equals(className)) //$NON-NLS-1$
      {
         resolvedClass = BigDecimal.class;
      }
      return resolvedClass == null ? null : resolvedClass.getName();
   }

   public static boolean isAssignable(IType left, IType right)
   {
      if (right == null || left == null)
      {
         // todo: (fh) it should never happen, check with primitive types and arrays
         return false;
      }
      try
      {
         ITypeHierarchy hierarchy = right.newSupertypeHierarchy(null);
         return hierarchy.contains(left);
      }
      catch (JavaModelException e)
      {
         return false;
      }
   }

   public MethodInfo getConstructor(TypeInfo type, String ctorName)
   {
      String compactCtorName = StringUtils.replace(ctorName, ", ", ","); //$NON-NLS-1$ //$NON-NLS-2$

      for (MethodInfo candidate : getConstructors(type))
      {
         if (compactCtorName.equals(candidate.getEncoded()))
         {
            return candidate;
         }
      }
      return null;
   }

   public MethodInfo getMethod(TypeInfo type, String methodName)
   {
      String compactMethodName = StringUtils.replace(methodName, ", ", ","); //$NON-NLS-1$ //$NON-NLS-2$

      for (MethodInfo candidate : getMethods(type))
      {
         if (compactMethodName.equals(StringUtils.replace(candidate.getEncoded(), ", ", //$NON-NLS-1$
               ","))) //$NON-NLS-1$
         {
            return candidate;
         }
      }
      return null;
   }

   public IJavaProject getJavaProject()
   {
      return project;
   }

   public static Class<?> getPrimitiveWrapper(String primitiveType)
   {
      Class<?> result = null;

      if (primitiveType.endsWith("[]")) //$NON-NLS-1$
      {
         return Array.class;
      }
      else
         for (int i = 0; i < PRIMITIVE_TYPES.length; i++)
         {
            if (PRIMITIVE_TYPES[i].getName().equals(primitiveType))
            {
               result = Reflect.getWrapperClassFromPrimitiveClassName(PRIMITIVE_TYPES[i]);
               break;
            }
         }

      return result;
   }

   public TypeInfo findType(String fullClassName)
   {
      String parameters = null;
      String className = fullClassName;
      int ix = fullClassName.indexOf('<');
      if (ix > 0)
      {
         parameters = fullClassName.substring(ix);
         className = fullClassName.substring(0, ix);
      }
      // fix inner classes
      className = className.replace('$', '.');
      IType type = findExactType(className);
      TypeInfo result = null;
      if (type != null)
      {
         try
         {
            result = new TypeInfo(this, type, parameters);
         }
         catch (JavaModelException e)
         {
            log(e.getJavaModelStatus());
         }
      }
      return result;
   }

   public List<MethodInfo> getMethods(TypeInfo type)
   {
      try
      {
         return filter(type.getMethods());
      }
      catch (JavaModelException e)
      {
         log(e.getJavaModelStatus());
      }
      return Collections.emptyList();
   }

   public List<MethodInfo> getConstructors(TypeInfo type)
   {
      try
      {
         return filter(type.getConstructors());
      }
      catch (JavaModelException e)
      {
         log(e.getJavaModelStatus());
      }
      return Collections.emptyList();
   }

   private List<MethodInfo> filter(List<MethodInfo> list)
   {
      if (filter == null)
      {
         return list;
      }
      List<MethodInfo> result = CollectionUtils.newList(list.size());
      for (MethodInfo info : list)
      {
         if (filter.accept(info))
         {
            result.add(info);
         }
      }
      return result;
   }

   public EObject getModelElement()
   {
      return modelElement;
   }

   public void setMethodFilter(MethodFilter filter)
   {
      this.filter = filter;
   }
}