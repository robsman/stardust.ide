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
package org.eclipse.stardust.modeling.validation.impl.spi.actionTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.workflow.model.PredefinedConstants;

public class TriggerActionValidator implements IModelElementValidator
{
   private static final String MESSAGES = Validation_Messages.MSG_NoProcessSelected;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      String processId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.TRIGGER_ACTION_PROCESS_ATT);
      if (StringUtils.isEmpty(processId))
      {
         result.add(Issue.warning(element, MESSAGES,
               PredefinedConstants.TRIGGER_ACTION_PROCESS_ATT));
      }
      else
      {
         ProcessDefinitionType process = null;
         for (Iterator iter = ModelUtils.findContainingModel(element)
               .getProcessDefinition().iterator(); iter.hasNext();)
         {
            ProcessDefinitionType proc = (ProcessDefinitionType) iter.next();
            if (proc.getId().equals(processId))
            {
               process = proc;
            }
         }
         if (process == null)
         {
            result.add(Issue.warning(element, Validation_Messages.MSG_InvalidProcess,
                  PredefinedConstants.TRIGGER_ACTION_PROCESS_ATT));
         }
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

}
