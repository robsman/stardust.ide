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
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.*;


public class PoolValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      PoolSymbol pool = (PoolSymbol) element;

      if (findDuplicateId(pool))
      {
         result.add(Issue.error(pool, MessageFormat.format(
               Validation_Messages.MSG_DuplicateIdUsed, new Object[] {pool.getId()}),
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      ValidationService vs = ValidationPlugin.getDefault().getValidationService();

      result.addAll(Arrays.asList(vs.validateModelElements(pool.getLanes())));

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean findDuplicateId(PoolSymbol pool)
   {
      for (Iterator i = ModelUtils.findContainingDiagram(pool)
            .getPoolSymbols()
            .iterator(); i.hasNext();)
      {
         PoolSymbol otherPool = (PoolSymbol) i.next();
         if ( !pool.equals(otherPool) && (null != otherPool.getId())
               && (otherPool.getId().equals(pool.getId())))
         {
            return true;
         }
      }
      return false;
   }
}
