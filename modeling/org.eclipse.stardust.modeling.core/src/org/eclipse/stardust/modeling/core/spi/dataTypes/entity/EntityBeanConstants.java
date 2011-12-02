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
package org.eclipse.stardust.modeling.core.spi.dataTypes.entity;

import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;

public final class EntityBeanConstants
{
   public static final String VERSION_2_X = "entity20"; //$NON-NLS-1$
   public static final String VERSION_3_X = "entity30"; //$NON-NLS-1$
   public static final String VERSION_ATT = CarnotConstants.ENGINE_SCOPE + "ejbVersion"; //$NON-NLS-1$

   private EntityBeanConstants() {};
}
