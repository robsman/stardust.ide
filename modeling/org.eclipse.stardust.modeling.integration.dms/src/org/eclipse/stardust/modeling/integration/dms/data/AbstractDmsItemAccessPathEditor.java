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
package org.eclipse.stardust.modeling.integration.dms.data;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPathEditor;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;


/**
 * @author fherinean
 * @version $Revision$
 */
public abstract class AbstractDmsItemAccessPathEditor implements IAccessPathEditor
{
   protected abstract StructAccessPointType getAccessPointDefinition(ModelType model, IExtensibleElement data);
   
   public List<AccessPointType> getAccessPoints(String hint, IExtensibleElement data,
         DirectionType direction)
   {
      ModelType model = DmsTypeUtils.findModelFromContext((ITypedElement) data);

      StructAccessPointType dmsDocumentAccessPoint = (data instanceof StructAccessPointType)
            ? (StructAccessPointType) data
            : getAccessPointDefinition(model, data);
      
      return DmsTypeUtils.getDmsStructuredDataEditor(model, data) //
            .getAccessPoints(hint, dmsDocumentAccessPoint, direction);
   }

   public String[] splitAccessPath(String accessPath)
   {
      return new StructAccessPathEditor().splitAccessPath(accessPath);
   }

   public boolean supportsBrowsing()
   {
      return true;
   }
}
