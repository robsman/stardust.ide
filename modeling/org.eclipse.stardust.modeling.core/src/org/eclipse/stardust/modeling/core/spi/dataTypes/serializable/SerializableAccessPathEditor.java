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
package org.eclipse.stardust.modeling.core.spi.dataTypes.serializable;

import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.PlainJavaAccessPointProvider;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SerializableAccessPathEditor implements IAccessPathEditor
{
   public List getAccessPoints(String hint, IExtensibleElement data, DirectionType direction)
   {
      return PlainJavaAccessPointProvider.getIntrinsicAccessPoints(data,
         getClassName(data), null, null, direction, hint, false);
   }

   protected String getClassName(IExtensibleElement element)
   {
      return AttributeUtil.getAttributeValue(element, CarnotConstants.CLASS_NAME_ATT);
   }

   public String[] splitAccessPath(String accessPath)
   {
      return PlainJavaAccessPointProvider.splitAccessPath(accessPath);
   }

   public boolean supportsBrowsing()
   {
      return true;
   }
}
