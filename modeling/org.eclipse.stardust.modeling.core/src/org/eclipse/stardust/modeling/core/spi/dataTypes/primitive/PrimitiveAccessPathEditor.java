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

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.dataTypes.serializable.SerializableAccessPathEditor;

/**
 * @author fherinean
 * @version $Revision$
 */
public class PrimitiveAccessPathEditor extends SerializableAccessPathEditor
{
   protected String getClassName(IExtensibleElement data)
   {
      String className = AttributeUtil.getAttributeValue(
            data, CarnotConstants.TYPE_ATT);
      if(StringUtils.isEmpty(className))
      {
         return null;
      }
      
      Class type = Reflect.getClassFromAbbreviatedName(className);
      return (null != type) ? type.getName() : null;
   }
}