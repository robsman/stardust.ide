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
package org.eclipse.stardust.modeling.refactoring.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IType;
import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.AttributeValueChange;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ConditionOperator implements IJdtOperator
{
   private String typeId;
   private String[] classNames;

   public ConditionOperator(String type, String[] attributes)
   {
      typeId = type;
      classNames = attributes;
   }

   public List getRefactoringChanges(ModelType model, Object element, RefactoringArguments arguments)
   {
      if (!(element instanceof IType))
      {
         return Collections.EMPTY_LIST;
      }

      String originalClassName = ((IType) element).getFullyQualifiedName();
      String newClassName = OperatorsRegistry.getNewClassName((IType) element, arguments);
      if (newClassName.equals(originalClassName))
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List conditionTypes = model.getEventConditionType();
      for (int i = 0; i < conditionTypes.size(); i++)
      {
         EventConditionTypeType type = (EventConditionTypeType) conditionTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List eventHandlers = type.getEventHandlers();
            for (int j = 0; j < eventHandlers.size(); j++)
            {
               EventHandlerType handler = (EventHandlerType) eventHandlers.get(j);
               for (int k = 0; k < classNames.length; k++)
               {
                  AttributeType attribute = AttributeUtil.getAttribute(handler, classNames[k]);
                  if (attribute != null)
                  {
                     String className = attribute.getValue();
                     if (originalClassName.equals(className))
                     {
                        result.add(new AttributeValueChange(attribute, newClassName));
                     }
                  }
               }
            }
            break;
         }
      }
      return result;
   }

   public List getQueryMatches(IFile file, ModelType model, Object element)
   {
      if (!(element instanceof IType))
      {
         return Collections.EMPTY_LIST;
      }

      String className = ((IType) element).getFullyQualifiedName();

      List result = new ArrayList();
      List conditionTypes = model.getEventConditionType();
      for (int i = 0; i < conditionTypes.size(); i++)
      {
         EventConditionTypeType type = (EventConditionTypeType) conditionTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List eventHandlers = type.getEventHandlers();
            for (int j = 0; j < eventHandlers.size(); j++)
            {
               EventHandlerType handler = (EventHandlerType) eventHandlers.get(j);
               for (int k = 0; k < classNames.length; k++)
               {
                  AttributeType attribute = AttributeUtil.getAttribute(handler, classNames[k]);
                  if (attribute != null)
                  {
                     if (className.equals(attribute.getValue()))
                     {
                        result.add(new EObjectMatch(file, attribute, 0, className.length()));
                     }
                  }
               }
            }
            break;
         }
      }
      return result;
   }
}
