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
package org.eclipse.stardust.modeling.refactoring.query.matches;

import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.search.ui.text.Match;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * @author fherinean
 * @version $Revision$
 */
public class EObjectMatch extends Match
{
   private IFile file;

   public EObjectMatch(IFile file, EObject eObject, EStructuralFeature feature, int offset, int length)
   {
      super(eObject/*new EObjectResourceWrapper(file, eObject, feature)*/, offset, length);
      this.file = file;
   }

   public EObjectMatch(IFile file, AttributeType attribute, int offset, int length)
   {
      super(attribute/*new EObjectResourceWrapper(file, attribute)*/, offset, length);
      this.file = file;
   }
   
   public IFile getFile()
   {
      return file;
   }

   public static void addMethodMatch(List result, IFile file,
         AttributeType attribute, String methodName)
   {
      String oldValue = attribute.getValue();
      if (oldValue != null)
      {
         int ix = oldValue.lastIndexOf('(');
         StringTokenizer st = new StringTokenizer(ix >= 0 ?
            oldValue.substring(0, ix) : oldValue, "."); //$NON-NLS-1$
         while (st.hasMoreTokens())
         {
            String token = trim(st.nextToken(), false);
            if (methodName.equals(token))
            {
               result.add(new EObjectMatch(file, attribute, 0, ix < 0 ? oldValue.length() : ix));
               break;
            }
         }

      }
   }

   public static void addMethodMatch(List result, IFile file,
         EObject eObject, EStructuralFeature feature, String methodName)
   {
      String oldValue = (String) eObject.eGet(feature);
      if (oldValue != null)
      {
         int ix = oldValue.lastIndexOf('(');
         StringTokenizer st = new StringTokenizer(ix >= 0 ?
            oldValue.substring(0, ix) : oldValue, "."); //$NON-NLS-1$
         while (st.hasMoreTokens())
         {
            String token = trim(st.nextToken(), false);
            if (methodName.equals(token))
            {
               result.add(new EObjectMatch(file, eObject, feature, 0,
                  ix < 0 ? oldValue.length() : ix));
               break;
            }
         }

      }
   }

   public static void addParamsMatch(List result, IFile file,
         AttributeType attribute, String className)
   {
      String oldValue = attribute.getValue();
      if (oldValue != null && oldValue.endsWith(")")) //$NON-NLS-1$
      {
         int ix = oldValue.lastIndexOf('(');
         if (ix++ > 0)
         {
            StringTokenizer st = new StringTokenizer(
               oldValue.substring(ix, oldValue.length() - 1), ","); //$NON-NLS-1$
            while (st.hasMoreTokens())
            {
               String token = trim(st.nextToken(), true);
               if (className.equals(token))
               {
                  result.add(new EObjectMatch(file, attribute, ix, oldValue.length() - 1));
                  break;
               }
            }
         }
      }
   }

   public static void addParamsMatch(List result, IFile file,
         EObject eObject, EStructuralFeature feature, String className)
   {
      String oldValue = (String) eObject.eGet(feature);
      if (oldValue != null && oldValue.endsWith(")")) //$NON-NLS-1$
      {
         int ix = oldValue.lastIndexOf('(');
         if (ix++ > 0)
         {
            StringTokenizer st = new StringTokenizer(
               oldValue.substring(ix, oldValue.length() - 1), ","); //$NON-NLS-1$
            while (st.hasMoreTokens())
            {
               String token = trim(st.nextToken(), true);
               if (className.equals(token))
               {
                  result.add(new EObjectMatch(file, eObject, feature, ix,
                     oldValue.length() - 1));
                  break;
               }
            }
         }
      }
   }

   private static String trim(String token, boolean paramSubstitution)
   {
      if (!paramSubstitution)
      {
         int ix = token.indexOf('(');
         if (ix >= 0)
         {
            token = token.substring(0, ix);
         }
      }
      return token.trim();
   }
}
