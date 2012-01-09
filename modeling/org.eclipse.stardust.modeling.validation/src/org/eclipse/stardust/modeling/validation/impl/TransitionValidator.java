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
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.error.PublicException;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.compatibility.el.EvaluationError;
import org.eclipse.stardust.engine.core.compatibility.el.Interpreter;
import org.eclipse.stardust.engine.core.compatibility.el.SymbolTable;
import org.eclipse.stardust.engine.core.compatibility.el.SyntaxError;
import org.eclipse.stardust.engine.core.pojo.data.PrimitiveAccessPathEvaluator;
import org.eclipse.stardust.engine.core.spi.extensions.model.AccessPoint;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.model.xpdl.xpdl2.ScriptType;
import org.eclipse.stardust.modeling.javascript.editor.EditorUtils;
import org.eclipse.stardust.modeling.javascript.editor.JavaScriptValidator;
import org.eclipse.stardust.modeling.javascript.editor.controller.JavaScriptEditorController;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.wst.jsdt.core.IJavaScriptProject;
import org.eclipse.wst.jsdt.core.JavaScriptCore;
import org.eclipse.wst.jsdt.core.compiler.IProblem;


public class TransitionValidator implements IModelElementValidator
{
   private static final String CARNOT_EL = "carnotEL: "; //$NON-NLS-1$
   private static final String ECMA_LANGUAGE = "text/ecmascript"; //$NON-NLS-1$

   public Issue[] validate(IModelElement element) throws ValidationException
   {      
      ModelType model = ModelUtils.findContainingModel(element);
      List result = new ArrayList();
      TransitionType transition = (TransitionType) element;
      XmlTextNode expression = transition.getExpression();
      String expressionValue = null;
      String condition = transition.getCondition();
      
      boolean needsValidation = true;
      
      // validation depends on language
      ScriptType script = model.getScript();
      String language = script.getType();
      // JavaScript, ECMA
      if(!StringUtils.isEmpty(language) && language.equals(ECMA_LANGUAGE))
      {
         if(condition.equals("OTHERWISE")) //$NON-NLS-1$
         {
            needsValidation = false;
         }
         else if(condition.equals("CONDITION")) //$NON-NLS-1$
         {
            expressionValue = ModelUtils.getCDataString(expression.getMixed());
            if(expressionValue.equals("true") || expressionValue.equals("false")) //$NON-NLS-1$//$NON-NLS-2$
            {
               needsValidation = false;
            }            
         }
         if(needsValidation)         
         {
            if (expressionValue.startsWith(CARNOT_EL))
            {
               result.add(Issue.error(transition, Validation_Messages.MSG_TransitionCondUpdate,
                     CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Expression()));            
            }
            else
            {
               IProject project = ModelUtils.getProjectFromEObject(element);
               IJavaScriptProject javaProject = JavaScriptCore.create(project);
               try
               {
                  EditorUtils.addJSSupport(project, model);
               }
               catch (CoreException e)
               {
               }               
               
               JavaScriptValidator javaScriptValidator = new JavaScriptValidator(javaProject);
               JavaScriptEditorController controller = new JavaScriptEditorController();
               controller.intializeModel(model);
               String toCheck = controller.getJScript();
               toCheck += "\n" + expressionValue; //$NON-NLS-1$
               toCheck = VariableContextHelper.getInstance().getContext(model)
                     .replaceAllVariablesByDefaultValue(toCheck);
               IProblem[] test = javaScriptValidator.validate(toCheck); 
               if(test.length > 0)
               {    
                  String message = test[0].getMessage();
                  result.add(Issue.warning(transition, Validation_Messages.MSG_InvalidJavaScriptTransitionCondition));                  
               }                              
            }
         }
      }
      // CarnotEL
      else
      {
         if (null != expression)
         {
            condition = ModelUtils.getCDataString(expression.getMixed());
         }
         else
         {
            condition = transition.getCondition();
         }
         if (condition == null || condition.trim().length() == 0)
         {
            result.add(Issue.error(transition, MessageFormat.format(
                  Validation_Messages.MSG_EmptyTransitionCond, null),
                  CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Condition()));
            needsValidation = false;
         }         
         if(needsValidation)
         {
            try
            {
               Interpreter.evaluate(condition, new SymbolTableAdapter(model));               
            }
            catch (SyntaxError e)
            {
               result.add(Issue.error(transition, MessageFormat.format(
                     Validation_Messages.MSG_SyntaxInvalidTransitionCond,
                     new Object[] {e.getMessage()}),
                     CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Condition()));
            }
            catch (EvaluationError e)
            {
               result.add(Issue.error(transition, MessageFormat.format(
                     Validation_Messages.MSG_SemanticInvalidTransitionCond,
                     new Object[] {e.getMessage()}),
                     CarnotWorkflowModelPackage.eINSTANCE.getTransitionType_Condition()));
            }
         }         
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   public static class SymbolTableAdapter implements SymbolTable
   {
      private final ModelType model;

      public SymbolTableAdapter(ModelType model)
      {
         this.model = model;
      }

      public Object lookupSymbol(String name)
      {
         AccessPoint data = lookupSymbolType(name);
         if (null == data)
         {
            throw new PublicException(MessageFormat.format(Validation_Messages.MSG_InvalidSymbol,
                  new Object[] {name}));
         }
         
         Object result = null;
         if (null != data.getType())
         {
            if (null == data.getType().getId())
            {
               Object typeHint = data.getAttribute("carnot:engine:typeHint"); //$NON-NLS-1$
               if ("text".equals(typeHint)) //$NON-NLS-1$
               {
                  result = ""; //$NON-NLS-1$
               }
               else if ("numeric".equals(typeHint)) //$NON-NLS-1$
               {
                  result = new Double(0.0);
               }
               else if ("complex".equals(typeHint)) //$NON-NLS-1$
               {
                  result = new java.io.Serializable()
                  {
                     private static final long serialVersionUID = 1L;
                  };
               }
            }
            else if (PredefinedConstants.PRIMITIVE_DATA.equals(data.getType().getId()))
            {
               PrimitiveAccessPathEvaluator pojoEvaluator = new PrimitiveAccessPathEvaluator();
               result = pojoEvaluator.createDefaultValue(data.getAllAttributes());
               if (null == result)
               {
                  result = pojoEvaluator.createInitialValue(data.getAllAttributes());
               }
            }
         }

         return result;
      }

      public AccessPoint lookupSymbolType(String name)
      {
         DataType data = null;
         if (PredefinedConstants.ACTIVITY_INSTANCE_ACCESSPOINT.equals(name))
         {
            DataType activityInstance = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
            activityInstance.setId(PredefinedConstants.ACTIVITY_INSTANCE_ACCESSPOINT);
            activityInstance.setName("Activity Instance"); //$NON-NLS-1$
            activityInstance.setPredefined(true);
            DataTypeType serializableDataType = (DataTypeType) ModelUtils.findElementById(model.getDataType(), PredefinedConstants.SERIALIZABLE_DATA);
            activityInstance.setType(serializableDataType);
            AttributeUtil.setAttribute(activityInstance, PredefinedConstants.BROWSABLE_ATT, "true"); //$NON-NLS-1$
            AttributeUtil.setAttribute(activityInstance, PredefinedConstants.CLASS_NAME_ATT, "ag.carnot.workflow.runtime.ActivityInstance"); //$NON-NLS-1$
            data = activityInstance;
         }
         else
         {
            data = (DataType) ModelUtils.findIdentifiableElement(model.getData(), name);
         }

         if (null == data)
         {
            throw new PublicException(MessageFormat.format(Validation_Messages.MSG_InvalidSymbol, 
                  new Object[] {name}));
         }

         return new ElValidationIDataAdapter(data);
      }
   }   
}