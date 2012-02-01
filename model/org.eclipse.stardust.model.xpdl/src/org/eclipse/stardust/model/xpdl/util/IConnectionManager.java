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
package org.eclipse.stardust.model.xpdl.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;


public interface IConnectionManager
{
   public static final String BY_REFERENCE = "importByReference"; //$NON-NLS-1$
   public static final String CONNECTION_MANAGER_CREATED = "ConnectionManager.CREATED"; //$NON-NLS-1$
   public static final String CONNECTION_MANAGER = "ConnectionManager"; //$NON-NLS-1$
   public static final String SCHEME = "cnx"; //$NON-NLS-1$
   public static final String CONNECTION_SCOPE = "carnot:connection:"; //$NON-NLS-1$
   public static final String URI_ATTRIBUTE_NAME = CONNECTION_SCOPE + "uri"; //$NON-NLS-1$

   EObject find(String uri);
   EObject find(URI uri);
   IConnection findConnection(String uri);
   IConnection findConnection(URI uri);
   ModelType find(ExternalPackage pkg);
}
