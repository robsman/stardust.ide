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

import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;


/**
 * 
 */
public class DmsFolderListAccessPathEditor extends AbstractDmsItemAccessPathEditor
      implements IAccessPathEditor
{
   
   protected StructAccessPointType getAccessPointDefinition(ModelType model, IExtensibleElement data)
   {
      return DmsTypeUtils.newDmsFolderListAccessPoint(model, null, data, DirectionType.INOUT_LITERAL);
   }

}
