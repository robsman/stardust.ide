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
package org.eclipse.stardust.modeling.audittrail;

import org.eclipse.core.resources.IFolder;

/**
 * @author rsauer
 * @version $Revision$
 */
public interface IAuditTrailDbListener
{
   int CREATED = 0;
   int RESET = 1;
   int DELETED = 2;
   
   void onAuditTrailDbChanged(IFolder dbFolder, int change);
}
