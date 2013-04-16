/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *
 * @author Barry.Grotjahn
 *******************************************************************************/

package org.eclipse.stardust.model.xpdl.carnot.spi;

import org.eclipse.core.runtime.IExtensionPoint;

public interface IStardustExtensionRegistry
{

   IExtensionPoint getExtensionPoint(String expandedId);

}