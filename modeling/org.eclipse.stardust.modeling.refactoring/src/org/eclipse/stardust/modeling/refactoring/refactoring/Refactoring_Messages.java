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
package org.eclipse.stardust.modeling.refactoring.refactoring;

import org.eclipse.osgi.util.NLS;

public class Refactoring_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.refactoring.refactoring.refactoring-messages"; //$NON-NLS-1$

   private Refactoring_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Refactoring_Messages.class);
   }

   public static String MSG_UnableToSave;

   public static String MSG_ParsingModels;

   public static String MSG_RenameParticipant;

   public static String MSG_MoveParticipant;
}
