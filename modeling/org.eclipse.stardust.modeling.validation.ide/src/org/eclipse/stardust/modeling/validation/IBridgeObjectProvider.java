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
package org.eclipse.stardust.modeling.validation;

import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;

public interface IBridgeObjectProvider
{
   // BridgeObject getBridgeObject(ITypedElement point, DirectionType direction);
   BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath,
         DirectionType direction) throws ValidationException;
}
