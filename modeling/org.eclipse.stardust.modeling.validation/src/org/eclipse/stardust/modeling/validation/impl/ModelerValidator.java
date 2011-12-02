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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelerType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class ModelerValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      ModelerType modeler = (ModelerType) element;

      if (findDuplicateId(modeler))
      {
         result.add(Issue.error(modeler, Validation_Messages.ERR_MODELER_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      // TODO: check id to fit in maximum length

      // TODO: Check: All associated Organizations must be part of the model

      // for (Iterator i = getAllOrganizations(); i.hasNext();)
      // {
      // IOrganization organization = (IOrganization) i.next();
      //
      // if (((IModel) getModel()).findParticipant(organization.getId()) == null)
      // {
      // inconsistencies.add(new Inconsistency("The associated organization '"
      // + organization.getId()
      // + "' set for participant '"
      // + getId() + "' doesn't exist in the model.",
      // getElementOID(), Inconsistency.ERROR));
      // }
      // }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean findDuplicateId(ModelerType modeler)
   {
      for (Iterator iter = ModelUtils.findContainingModel(modeler).getModeler()
            .iterator(); iter.hasNext();)
      {
         ModelerType otherModeler = (ModelerType) iter.next();

         if ((otherModeler.getId()).equals(modeler.getId())
               && (!modeler.equals(otherModeler)))
         {
            return true;
         }
      }
      return false;
   }

}
