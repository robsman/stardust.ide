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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import java.util.List;

import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class MessageTypeComparer implements IElementComparer {



public boolean equals(Object a, Object b)
{
   if (a instanceof List && b instanceof List) {
      return true;
   } else {
	   if (((a instanceof List) && !(b instanceof List)) || (((b instanceof List) && !(a instanceof List)))) {
		   return false;
	   }
   }
   AccessPointType typeA = null;
   AccessPointType typeB = null;
   if (a instanceof AccessPointType) {
     typeA = (AccessPointType)a; 
   }
   if (b instanceof AccessPointType) {
     typeB = (AccessPointType)b; 
   }
   if (typeA == null || typeB == null) {
      return false;
   }
   String xPathA = AttributeUtil.getAttributeValue((IExtensibleElement) typeA, "FullXPath"); //$NON-NLS-1$
   String xPathB = AttributeUtil.getAttributeValue((IExtensibleElement) typeB, "FullXPath"); //$NON-NLS-1$
   if (xPathA == null || xPathB == null) {
	   return false;
   }
   return xPathA.equalsIgnoreCase(xPathB);               
}

public int hashCode(Object element)
{
   if (element instanceof AccessPointType) {
	   AccessPointType apt = (AccessPointType)element;
	   final int PRIME = 31;
	   int result = 1;
	   result = PRIME * result + ((apt.getName() == null) ? 0 : apt.getName().hashCode());
	   return result;
   }
   return element.hashCode();
}
}
