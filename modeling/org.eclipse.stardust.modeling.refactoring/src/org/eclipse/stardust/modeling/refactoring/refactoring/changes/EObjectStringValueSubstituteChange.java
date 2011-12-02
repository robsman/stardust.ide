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
package org.eclipse.stardust.modeling.refactoring.refactoring.changes;

import java.util.StringTokenizer;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;

import ag.carnot.base.StringUtils;

/**
 * @author fherinean
 * @version $Revision$
 */
public class EObjectStringValueSubstituteChange extends Change
{
   private static EObjectLabelProvider labelProvider = new EObjectLabelProvider(null);
   
   private String what;
   private String with;
   private EObject eObject;
   private EStructuralFeature feature;
   private String name;
   private boolean paramSubstitution;

   public EObjectStringValueSubstituteChange(String name, EObject eObject,
         EStructuralFeature feature, boolean paramSubstitution,
         String what, String with)
   {
      this.name = name;
      this.eObject = eObject;
      this.feature = feature;
      this.paramSubstitution = paramSubstitution;
      this.what = what;
      this.with = with;
   }

   public Change perform(IProgressMonitor pm) throws CoreException
   {
      eObject.eSet(feature, substitute((String) eObject.eGet(feature)));
      return new EObjectStringValueSubstituteChange(name, eObject, feature,
         paramSubstitution, with, what);
   }

   // duplicate code with AttributeSubstituteChange
   private String substitute(String oldValue)
   {
      int fromIndex = paramSubstitution ? oldValue.lastIndexOf('(') : 0;
      int toIndex = paramSubstitution ? oldValue.length() - 1 : oldValue.lastIndexOf('(');
      String separator = paramSubstitution ? "," : "."; //$NON-NLS-1$ //$NON-NLS-2$
      String prefix = oldValue.substring(0, fromIndex);
      String postfix = oldValue.substring(toIndex);
      String original = oldValue.substring(fromIndex, toIndex);
      StringBuffer sb = new StringBuffer();
      sb.append(prefix);
      boolean needSeparator = false;
      StringTokenizer st = new StringTokenizer(original, separator);
      while (st.hasMoreTokens())
      {
         if (needSeparator)
         {
            sb.append(separator);
         }
         else
         {
            needSeparator = true;
         }
         String token = st.nextToken();
         if (trim(token, paramSubstitution).equals(what))
         {
            sb.append(StringUtils.replace(token, what, with));
         }
         else
         {
            sb.append(token);
         }
      }
      sb.append(postfix);
      return sb.toString();
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

   public static void addParamsSubstitution(List result, String name, EObject eObject,
         EStructuralFeature feature, String originalClassName, String newClassName)
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
               if (originalClassName.equals(token))
               {
                  result.add(new EObjectStringValueSubstituteChange(name, eObject,
                     feature, true, originalClassName, newClassName));
                  break;
               }
            }
         }
      }
   }

   public static void addMethodSubstitution(List result, String name, EObject eObject,
         EStructuralFeature feature, String originalMethodName, String newMethodName)
   {
      String oldValue = (String) eObject.eGet(feature);
      if (oldValue != null)
      {
         int ix = oldValue.lastIndexOf('(');
         StringTokenizer st = new StringTokenizer(ix >= 0 ?
            oldValue.substring(0, oldValue.lastIndexOf('(')) : oldValue, "."); //$NON-NLS-1$
         while (st.hasMoreTokens())
         {
            String token = trim(st.nextToken(), false);
            if (originalMethodName.equals(token))
            {
               result.add(new EObjectStringValueSubstituteChange(name, eObject,
                  feature, false, originalMethodName, newMethodName));
               break;
            }
         }

      }
   }

   public String getName()
   {
      return eObject instanceof AttributeType ? "attribute: " + name //$NON-NLS-1$
         : labelProvider.getText(eObject) + ": " + name; //$NON-NLS-1$
   }

   public void initializeValidationData(IProgressMonitor pm)
   {
   }

   public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException, OperationCanceledException
   {
      return new RefactoringStatus();
   }

   public Object getModifiedElement()
   {
      return eObject;
   }
}
