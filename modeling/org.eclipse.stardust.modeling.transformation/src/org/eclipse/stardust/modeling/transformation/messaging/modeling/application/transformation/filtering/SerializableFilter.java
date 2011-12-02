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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;


public class SerializableFilter extends ViewerFilter 
{

   public boolean select(Viewer viewer, Object parentElement, Object element)
   {
      if (element instanceof StructAccessPointType) {
         return false;
      }
      AccessPointType apt = (AccessPointType)element;
      if (apt.getType().getId().equalsIgnoreCase("serializable")) { //$NON-NLS-1$
         return true;
      }
      return false;

   }

   public String toString()
   {
      return "Serializable Data"; //$NON-NLS-1$
   }

}
