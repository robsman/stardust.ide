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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SearchJob extends Job
{
   private char[] packageName;
   private TypeRequestor requestor;

   public SearchJob(String packageName, String typeName, IJavaSearchScope scope,
                    int pattern, TypeFinderListener listener)
   {
      super(Validation_Messages.LB_SearchingTypes);
      this.packageName = packageName == null ? null : packageName.toCharArray();
      requestor = new TypeRequestor(typeName, scope, pattern, listener);
      setUser(true);
   }

   protected IStatus run(IProgressMonitor parent)
   {
      try
      {
         requestor.getListener().startSearch();
         SearchEngine engine = new SearchEngine();
         engine.searchAllTypeNames(
            packageName,
            requestor.getTypeName(),
            requestor.getPattern(),
            IJavaSearchConstants.CLASS_AND_INTERFACE,
            requestor.getScope(),
            requestor,
            IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH, null);
      }
      catch (JavaModelException e)
      {
      }
      finally
      {
         requestor.getListener().endSearch();
      }
      return Status.OK_STATUS;
   }

   public void stop()
   {
      requestor.stop();
      cancel();
   }
}
