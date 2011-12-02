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
package org.eclipse.stardust.modeling.core.highlighting;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * @author sborn
 * @version $Revision$
 */
public class HighlightState extends AbstractEnumerator
{
   public static final int DEFAULT = 0;
   public static final int SELECTED = 1;
   public static final int ACTIVE = 2;
   public static final int BROKEN = 3;
   public static final int DONE = 4;
   
   public static final HighlightState DEFAULT_LITERAL = new HighlightState(DEFAULT, "default"); //$NON-NLS-1$
   public static final HighlightState SELECTED_LITERAL = new HighlightState(SELECTED, "selected"); //$NON-NLS-1$
   public static final HighlightState ACTIVE_LITERAL = new HighlightState(ACTIVE, "active"); //$NON-NLS-1$
   public static final HighlightState BROKEN_LITERAL = new HighlightState(BROKEN, "broken"); //$NON-NLS-1$
   public static final HighlightState DONE_LITERAL = new HighlightState(DONE, "done"); //$NON-NLS-1$
   
   private HighlightState(int value, String name)
   {
      super(value, name);
   }
}
