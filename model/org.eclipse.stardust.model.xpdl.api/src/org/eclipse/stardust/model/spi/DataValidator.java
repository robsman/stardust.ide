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
package org.eclipse.stardust.model.spi;

import java.util.List;
import java.util.Map;

import org.eclipse.stardust.common.Direction;

/**
 * Provides static validation of {@link org.eclipse.stardust.model.xpdl.DataMapping}
 * configurations by tentatively evaluation of {@link AccessPoint} dereferences during
 * modeling time.
 */
public interface DataValidator
{
   /**
    * Performs static Data validation. An implementation is expected to
    * inspect the given attributes and indicate any
    * problems with an appropriate {@link org.eclipse.stardust.model.xpdl.Inconsistency}.
    *
    * @param attributes Implementation specific data attributes.
    *
    * @return The list of found {@link org.eclipse.stardust.model.xpdl.Inconsistency} instances.
    *
    * @see org.eclipse.stardust.model.spi.gui.DataPropertiesPanel#getAttributes
    */
   List validate(Map attributes);

   /**
    * Creates an implementation specific bridge object for the given access point and
    * path.
    * 
    * @param point
    *           the implementation specific access point.
    * @param path
    *           the implementation specific access path.
    * @param direction
    *           the data flow direction, either {@link Direction#In}if a LHS bridge is
    *           requested or {@link Direction#Out}if a RHS bridge is requested
    * 
    * @return the corresponding bridge object.
    */
   BridgeObject getBridgeObject(AccessPoint point, String path, Direction direction);
}
