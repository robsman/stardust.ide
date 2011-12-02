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
package org.eclipse.stardust.modeling.audittrail.ui;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author rsauer
 * @version $Revision$
 */
public class DbLocationLabelProvider extends LabelProvider
{
   public String getText(Object element)
   {
      if (AuditTrailUtils.NO_AUDIT_TRAIL_DB.equals(element))
      {
         return ""; //$NON-NLS-1$
      }
      else if (element instanceof IFolder)
      {
         return ((IFolder) element).getFullPath().lastSegment();
      }
      else
      {
         return super.getText(element);
      }
   }
}