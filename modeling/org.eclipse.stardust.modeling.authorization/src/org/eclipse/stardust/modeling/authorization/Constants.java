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
package org.eclipse.stardust.modeling.authorization;

public final class Constants
{
   public static final String PLUGIN_ID = "org.eclipse.stardust.modeling.authorization"; //$NON-NLS-1$

   public static final String EXTENSION_POINT_ID = "modelElementPermission"; //$NON-NLS-1$

   public static final String SCOPE = "authorization"; //$NON-NLS-1$

   public static final char SCOPE_SEPARATOR = ':'; //$NON-NLS-1$

   public static final String ID_ATTRIBUTE = "id"; //$NON-NLS-1$

   public static final String NAME_ATTRIBUTE = "name"; //$NON-NLS-1$

   public static final String SCOPE_ATTRIBUTE = "scope"; //$NON-NLS-1$
    
   public static final String DEFAULT_PARTICIPANT_ATTRIBUTE = "defaultParticipant"; //$NON-NLS-1$

   public static final String FIXED_PARTICIPANT_ATTRIBUTE = "fixed"; //$NON-NLS-1$

   public static final String ALL_PARTICIPANT = "All"; //$NON-NLS-1$
   
   public static final String OWNER_PARTICIPANT = "Owner"; //$NON-NLS-1$

   private Constants() {}; // prevents instantiation
}
