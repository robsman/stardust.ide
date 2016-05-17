/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.integration.dms.data;

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.modeling.data.structured.validation.StructBridgeObject;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.util.PathEntry;

public class DocumentFolderBridgeObject extends StructBridgeObject
{
   public DocumentFolderBridgeObject(IType accessPointType, DirectionType direction,
         PathEntry entry)
   {
      super(accessPointType, direction, entry);
   }

   @Override
   public boolean acceptAssignmentFrom(BridgeObject rhs)
   {
      actualTypeName = null;
      return isList || super.acceptAssignmentFrom(rhs);
   }
}
