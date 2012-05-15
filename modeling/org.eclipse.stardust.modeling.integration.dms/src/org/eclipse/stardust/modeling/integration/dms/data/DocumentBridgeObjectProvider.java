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

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.modeling.data.structured.validation.StructBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.util.PathEntry;

public class DocumentBridgeObjectProvider extends StructBridgeObjectProvider
{

   @Override
   public BridgeObject getBridgeObject(ITypedElement ap, String accessPath,
         DirectionType direction) throws ValidationException
   {
      BridgeObject sat = super.getBridgeObject(ap, accessPath, direction);
      PathEntry entry = new PathEntry(ap, direction);
      if (ap instanceof AccessPointType)
      {
         if (ap.getMetaType().getId().startsWith("dmsFolder")) { //$NON-NLS-1$
            return new DocumentFolderBridgeObject(sat.getEndClass(), direction, entry);
         }
      }
      return super.getBridgeObject(ap, accessPath, direction);
   }

}
