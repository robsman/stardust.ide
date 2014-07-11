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

import org.eclipse.stardust.common.StringKey;

/**
 * @author mgille
 */
public final class JoinSplitType extends StringKey
{
   private static final long serialVersionUID = 2L;

   public static final JoinSplitType None = new JoinSplitType("None", "None"); //$NON-NLS-1$ //$NON-NLS-2$
   public static final JoinSplitType Xor = new JoinSplitType("XOR", "XOR"); //$NON-NLS-1$ //$NON-NLS-2$
   public static final JoinSplitType And = new JoinSplitType("AND", "And"); //$NON-NLS-1$ //$NON-NLS-2$
   public static final JoinSplitType Or = new JoinSplitType("OR", "Or"); //$NON-NLS-1$ //$NON-NLS-2$

   public static JoinSplitType getKey(String id)
   {
      return (JoinSplitType) getKey(JoinSplitType.class, id);
   }

   private JoinSplitType(String id, String name)
   {
      super(id, name);
   }
}
