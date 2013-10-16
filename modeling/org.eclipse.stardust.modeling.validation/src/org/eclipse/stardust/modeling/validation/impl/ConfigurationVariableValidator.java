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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelVariable;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContext;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.validation.IModelValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class ConfigurationVariableValidator implements IModelValidator
{
   private static final Issue[] ISSUE_ARRAY = new Issue[0];
   private ArrayList<EObject> unique;

   public Issue[] validate(ModelType model) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();      
      VariableContext context = VariableContextHelper.getInstance().getContext(model);
      if (context != null)
      {
         for (Iterator<ModelVariable> i = context.getVariables().iterator(); i.hasNext();)
         {
            ModelVariable modelVariable = i.next();
            if (!modelVariable.isRemoved())
            {
               List<EObject> list = context.getVariableReferences().get(
                     modelVariable.getName());
               // Invalid variables
               if (!context.isValidName(modelVariable.getName())
                     || !context.isValidType(modelVariable.getName()))
               {
                  result.add(Issue.warning(model, MessageFormat.format(
                        Validation_Messages.MODEL_ConfigurationVariable_Invalid,
                        new Object[] {modelVariable.getName()}),
                        PredefinedConstants.TARGET_PARTICIPANT_ATT));
               }
               // Unused variables
               if (list == null || list.isEmpty())
               {
                  result.add(Issue.warning(model, MessageFormat.format(
                        Validation_Messages.MODEL_ConfigurationVariable_NeverUsed,
                        new Object[] {modelVariable.getName()}),
                        PredefinedConstants.TARGET_PARTICIPANT_ATT));
               }
               // No default value
               if (modelVariable.getDefaultValue() == null
                     || modelVariable.getDefaultValue() == "") //$NON-NLS-1$
               {
                  result.add(Issue.warning(model, MessageFormat.format(
                        Validation_Messages.MODEL_ConfigurationVariable_NoDefaultValue,
                        new Object[] {modelVariable.getName()}),
                        PredefinedConstants.TARGET_PARTICIPANT_ATT));
               }
               // Not allowed variables
               if (list != null)
               {
                  unique = new ArrayList<EObject>();
                  for (Iterator<EObject> j = list.iterator(); j.hasNext();)
                  {
                     List<Issue> issues = isAllowed(j.next());
                     if (!issues.isEmpty())
                     {
                        result.addAll(issues);
                     }
                  }
               }
            }

         }
      }
      return (Issue[]) result.toArray(ISSUE_ARRAY);
   }
      
   private TypeDeclarationType getTypeDeclaration(EObject o)
   {
      if (o instanceof TypeDeclarationType)
      {
         return (TypeDeclarationType) o;
      }
      else
      {
         if (o.eContainer() != null)
         {
            return getTypeDeclaration(o.eContainer());
         }
      }
      return null;
   }
   
   private List<Issue> isAllowed(EObject o)
   {
      List<Issue> result = new ArrayList<Issue>();
      if (o instanceof IIdentifiableElement)
      {
         IIdentifiableElement dataType = (IIdentifiableElement) o;
         if (dataType.getName().indexOf("${") > -1) //$NON-NLS-1$
         {
            result.add(Issue.error(dataType,
                  Validation_Messages.MODEL_ConfigurationVariable_NAME,
                  PredefinedConstants.TARGET_PARTICIPANT_ATT));
         }
         if (dataType.getId().indexOf("${") > -1) //$NON-NLS-1$
         {
            result.add(Issue.error(dataType,
                  Validation_Messages.MODEL_ConfigurationVariable_ID,
                  PredefinedConstants.TARGET_PARTICIPANT_ATT));
         }
      }
      if (o instanceof AttributeType)
      {
         String owner = ""; //$NON-NLS-1$
         AttributeType attribute = (AttributeType) o;
         EObject parent = attribute.eContainer();
         if (parent != null)
         {
            if (parent instanceof IIdentifiableElement)
            {
               owner = ((IIdentifiableElement) parent).getName();
            }
         }
         String name = attribute.getName();
         for (int k = 0; k < attributes.length; k++)
         {
            if (attributes[k].equalsIgnoreCase(name))
            {
               result.add(Issue.warning(attribute, MessageFormat.format(
                     Validation_Messages.MODEL_ConfigurationVariable_NotAllowed,
                     new Object[] {attribute.getName(), owner}),
                     PredefinedConstants.TARGET_PARTICIPANT_ATT));
            }
         }
      }
      if (o instanceof DescriptionType)
      {
         result.add(Issue.error(o,
               Validation_Messages.MODEL_ConfigurationVariable_Description,
               PredefinedConstants.TARGET_PARTICIPANT_ATT));
      }
      TypeDeclarationType typeDecl = getTypeDeclaration(o);
      if (typeDecl != null)
      {
         if (!unique.contains(typeDecl))
         {
            result.add(Issue.error(typeDecl,
                  Validation_Messages.MODEL_ConfigurationVariable_TypeDec,
                  PredefinedConstants.TARGET_PARTICIPANT_ATT));
            unique.add(typeDecl);
         }
      }    
      return result;
   }

   public String getText(String name, Object element)
   {
      EObject eObject = (EObject) element;
      EStructuralFeature attr = eObject.eClass().getEStructuralFeature(name);
      Object value = attr == null ? null : eObject.eGet(attr);
      return value == null ? "" : value.toString(); //$NON-NLS-1$
   }

   public String getText(Object element)
   {
      if (element instanceof EObject)
      {
         String text = getText("name", element);//$NON-NLS-1$
         if (text.length() == 0)
         {

            text = getText("id", element);//$NON-NLS-1$
         }
         if (text.length() == 0 && element instanceof ITypedElement)
         {
            text = getText(((ITypedElement) element).getMetaType());
         }
         if (text.length() != 0)
         {
            return text;
         }
      }
      return element.toString();
   }

   public String getElementName(Object element)
   {

      EObject object = (EObject) element;
      if (object.eContainer() instanceof ModelType)
      {
         return getText(((EObject) element));
      }
      return getText(((EObject) element).eContainer());

   }

   private String[] attributes = new String[] {
         "carnot:engine:remoteInterface", "carnot:engine:spring::beanId"}; //$NON-NLS-1$ //$NON-NLS-2$
}