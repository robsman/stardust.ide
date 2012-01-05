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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationPlugin;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class ApplicationValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      ApplicationType application = (ApplicationType) element;

      if (findDuplicateId(application))
      {
         result.add(Issue.error(application,
               Validation_Messages.ERR_APPLICATION_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      ValidationService vs = ValidationPlugin.getDefault().getValidationService();
      result.addAll(Arrays.asList(vs.validateModelElements(application.getContext())));
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean findDuplicateId(ApplicationType application)
   {
      ModelType model = ModelUtils.findContainingModel(application);
      if (null != model)
      {
         for (Iterator iter = model.getApplication().iterator(); iter.hasNext();)
         {
            ApplicationType otherApplication = (ApplicationType) iter.next();

            if ((otherApplication.getId()).equals(application.getId())
                  && (!application.equals(otherApplication)))
            {
               return true;
            }
         }
      }
      return false;
   }
}
