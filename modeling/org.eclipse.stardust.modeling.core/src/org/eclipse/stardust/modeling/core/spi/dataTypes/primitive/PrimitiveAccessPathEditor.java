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
package org.eclipse.stardust.modeling.core.spi.dataTypes.primitive;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.dataTypes.serializable.SerializableAccessPathEditor;

import ag.carnot.reflect.Reflect;

/**
 * @author fherinean
 * @version $Revision$
 */
public class PrimitiveAccessPathEditor extends SerializableAccessPathEditor
{
   protected String getClassName(IExtensibleElement data)
   {
      Class type = Reflect.getClassFromAbbreviatedName(AttributeUtil.getAttributeValue(
            data, CarnotConstants.TYPE_ATT));
      return (null != type) ? type.getName() : null;
   }
}
