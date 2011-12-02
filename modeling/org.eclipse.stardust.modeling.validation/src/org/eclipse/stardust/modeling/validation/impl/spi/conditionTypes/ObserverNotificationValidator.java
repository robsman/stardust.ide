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
package org.eclipse.stardust.modeling.validation.impl.spi.conditionTypes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;


public class ObserverNotificationValidator implements IModelElementValidator
{

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

}
