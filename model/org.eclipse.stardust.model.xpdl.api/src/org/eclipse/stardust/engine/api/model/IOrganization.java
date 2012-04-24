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
package org.eclipse.stardust.engine.api.model;

import java.util.Iterator;

/**
 * Enterprise organization acting as a workflow participant.
 */
public interface IOrganization extends IModelParticipant
{
   /**
    */
   IModelParticipant findParticipant(String id);

   /**
    * @return An iterator on all participants who are not organizations.
    */
   Iterator getAllParticipants();

   /**
    * @return An iterator on all participants who are (sub)organizations.
    */
   Iterator getSubOrganizations();

   /**
    * Checks, wether this process is a direct or indirect suborganization of the
    * organization <tt>testOrganization</tt>.
    */
   boolean isDirectOrIndirectSubOrganizationOf(IOrganization testOrganization);
}
