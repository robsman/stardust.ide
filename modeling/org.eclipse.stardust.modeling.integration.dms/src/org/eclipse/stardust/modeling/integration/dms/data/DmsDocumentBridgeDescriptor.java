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

import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.modeling.validation.BridgeObject;


/**
 * @author rsauer
 * @version $Revision$
 */
public class DmsDocumentBridgeDescriptor extends BridgeObject
{

   public DmsDocumentBridgeDescriptor(IType endClass, DirectionType direction,
         String label)
   {
      super(endClass, direction, label);
   }

   public DmsDocumentBridgeDescriptor(IType endClass, DirectionType direction)
   {
      super(endClass, direction);
      // TODO Auto-generated constructor stub
   }

   public DirectionType getDirection()
   {
      // TODO Auto-generated method stub
      return super.getDirection();
   }

   public IType getEndClass()
   {
      // TODO Auto-generated method stub
      return super.getEndClass();
   }

   public boolean acceptAssignmentFrom(BridgeObject rhs)
   {
      // TODO Auto-generated method stub
      return super.acceptAssignmentFrom(rhs);
   }
   
}
