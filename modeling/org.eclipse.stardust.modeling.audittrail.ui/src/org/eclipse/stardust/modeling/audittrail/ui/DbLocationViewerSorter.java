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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @author rsauer
 * @version $Revision$
 */
public class DbLocationViewerSorter extends ViewerSorter
{
   public int compare(Viewer viewer, Object e1, Object e2)
   {
      if (AuditTrailUtils.NO_AUDIT_TRAIL_DB.equals(e1))
      {
         return -1;
      }
      if (AuditTrailUtils.NO_AUDIT_TRAIL_DB.equals(e2))
      {
         return 1;
      }
      else
      {
         return super.compare(viewer, e1, e2);
      }
   }
}