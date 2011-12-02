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

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.refactoring.operators.IJdtOperator;
import org.eclipse.stardust.modeling.refactoring.operators.OperatorsRegistry;
import org.eclipse.stardust.modeling.refactoring.query.matches.EObjectMatch;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.EObjectStringValueSubstituteChange;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.core.resources.IFile;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DataActionOperator implements IJdtOperator
{
   private String typeId;
   private String[] attributeNames;

   public DataActionOperator(String typeId, String[] attributeNames)
   {
      this.typeId = typeId;
      this.attributeNames = attributeNames;
   }

   public List getRefactoringChanges(ModelType model, Object element, RefactoringArguments arguments)
   {
      String originalMethodName = null;
      String newMethodName = null;
      if (element instanceof IMethod)
      {
         originalMethodName = ((IMethod) element).getElementName();
         newMethodName = OperatorsRegistry.getNewMethodName((IMethod) element, arguments);
         if (newMethodName.equals(originalMethodName))
         {
            return Collections.EMPTY_LIST;
         }
      }
      else
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List actionTypes = model.getEventActionType();
      for (int i = 0; i < actionTypes.size(); i++)
      {
         EventActionTypeType type = (EventActionTypeType) actionTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List actions = type.getActionInstances();
            for (int j = 0; j < actions.size(); j++)
            {
               AbstractEventAction action = (AbstractEventAction) actions.get(j);
               processAction(result, model, action, originalMethodName, newMethodName);
            }
            break;
         }
      }
      return result;
   }

   public List getQueryMatches(IFile file, ModelType model, Object element)
   {
      String methodName = null;
      if (element instanceof IMethod)
      {
         methodName = ((IMethod) element).getElementName();
      }
      else
      {
         return Collections.EMPTY_LIST;
      }

      List result = new ArrayList();
      List actionTypes = model.getEventActionType();
      for (int i = 0; i < actionTypes.size(); i++)
      {
         EventActionTypeType type = (EventActionTypeType) actionTypes.get(i);
         if (typeId.equals(type.getId()))
         {
            List actions = type.getActionInstances();
            for (int j = 0; j < actions.size(); j++)
            {
               AbstractEventAction action = (AbstractEventAction) actions.get(j);
               queryAction(result, file, model, action, methodName);
            }
            break;
         }
      }
      return result;
   }

   private void queryAction(List result, IFile file, ModelType model,
                            AbstractEventAction action, String methodName)
   {
      String dataId = AttributeUtil.getAttributeValue(action, attributeNames[0]);
      DataType data = (DataType) ModelUtils.findIdentifiableElement(model.getData(), dataId);
      if (isJavaType(data))
      {
         AttributeType attribute = AttributeUtil.getAttribute(action, attributeNames[1]);
         if (attribute != null)
         {
            EObjectMatch.addMethodMatch(result, file, attribute, methodName);
         }
      }
   }

   private void processAction(List result, ModelType model, AbstractEventAction action,
                              String originalMethodName, String newMethodName)
   {
      String dataId = AttributeUtil.getAttributeValue(action, attributeNames[0]);
      DataType data = (DataType) ModelUtils.findIdentifiableElement(model.getData(), dataId);
      if (isJavaType(data))
      {
         AttributeType attribute = AttributeUtil.getAttribute(action, attributeNames[1]);
         if (attribute != null)
         {
            EObjectStringValueSubstituteChange.addMethodSubstitution(result,
               attributeNames[1], attribute,
               CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value(),
               originalMethodName, newMethodName);
         }
      }
   }

   private boolean isJavaType(DataType data)
   {
      if (data != null)
      {
         DataTypeType type = data.getType();
         String id = type.getId();
         return CarnotConstants.SERIALIZABLE_DATA_ID.equals(id)
            || CarnotConstants.ENTITY_BEAN_DATA_ID.equals(id)
            || CarnotConstants.PRIMITIVE_DATA_ID.equals(id)
            || CarnotConstants.HIBERNATE_DATA_ID.equals(id);
      }
      return false;
   }
}
