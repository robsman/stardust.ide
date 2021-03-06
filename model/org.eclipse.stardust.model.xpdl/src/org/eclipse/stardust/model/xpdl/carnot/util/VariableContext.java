/*******************************************************************************
 * Copyright (c) 2011, 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.internal.RegEx;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.preferences.configurationvariables.ConfigurationVariableScope;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;

public class VariableContext
{
   private List<ModelVariable> variables = new ArrayList<ModelVariable>();

   private Map<String, List<EObject>> variableReferences = new HashMap<String, List<EObject>>();

   private Map<String, Map<EObject, String>> elementReferences = new HashMap<String, Map<EObject, String>>();

   private Pattern pattern = Pattern.compile("(\\$\\{[^{}]+\\})"); //$NON-NLS-1$

   private ModelType model;

   private boolean criticalityFormulaChanged;

   public VariableContext()
   {
      super();
   }

   private List<AttributeType> getConfigurationVariableElements(ModelType model)
   {
      List<AttributeType> elements = new ArrayList<AttributeType>();
      List<AttributeType> allAttributes = model.getAttribute();
      for(AttributeType at: allAttributes)
      {
         if(at.getName().startsWith("ipp:variables")) //$NON-NLS-1$
         {
            elements.add(at);
         }
      }
      //sort ascending by index to prevent lookup problems
      Collections.sort(elements, new ConfigurationVariableIndexComparator());
      return elements;
   }

   public void initializeVariables(ModelType model)
   {
      try
      {
         this.model = model;
         variableReferences.clear();
         variables.clear();
         int lastIndex = -1;

         List<AttributeType> configVariableElements
            = getConfigurationVariableElements(model);
         for(AttributeType configVariableElement: configVariableElements)
         {
            int variableIndex = getIndex(configVariableElement.getName());
            ModelVariable modelVariable
               = createModelVariable(configVariableElement);
            try
            {
               if (lastIndex != variableIndex)
               {
                  parseVariables(model, modelVariable.getName());
               }
            }
            catch (Throwable t)
            {
               t.printStackTrace();
            }
            lastIndex = variableIndex;
         }
      }
      catch (Throwable t)
      {
         // In case that the initialization fails the model should still be loadable
      }
   }
   
   
   public void removeVariable(ModelVariable variable, String newValue)
   {
      replaceVariable(variable, newValue);
      variable.setRemoved(true);
      saveVariables();
      variables.remove(variable);
   }

   public void saveVariables()
   {
      int j = 0;
      for (Iterator<ModelVariable> i = getVariables().iterator(); i.hasNext();)
      {
         ModelVariable modelVariable = i.next();
         removeAttributeSet(modelVariable, j);
         j++;
      }
      j = 0;
      for (Iterator<ModelVariable> i = getVariables().iterator(); i.hasNext();)
      {
         ModelVariable modelVariable = i.next();
         createAttributeSet(modelVariable, j);
         if (!modelVariable.isRemoved())
         {
            j++;
         }
      }
   }

   public void createAttributeSet(ModelVariable modelVariable, int j)
   {
      if (!modelVariable.isRemoved())
      {
         String saveName = modelVariable.getName().substring(2, modelVariable.getName().length() - 1);
         AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:variables[" + j //$NON-NLS-1$
               + "]:name", "String", saveName); //$NON-NLS-1$ //$NON-NLS-2$

         AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:variables[" + j //$NON-NLS-1$
               + "]:defaultValue", "String", modelVariable.getDefaultValue()); //$NON-NLS-1$ //$NON-NLS-2$

         AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:variables[" + j //$NON-NLS-1$
               + "]:description", "String", modelVariable.getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
      }
   }

   private void removeAttributeSet(ModelVariable modelVariable, int j)
   {
      AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:variables[" + j //$NON-NLS-1$
            + "]:name", "String", null); //$NON-NLS-1$ //$NON-NLS-2$
      AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:variables[" + j //$NON-NLS-1$
            + "]:defaultValue", "String", null); //$NON-NLS-1$ //$NON-NLS-2$
      AttributeUtil.setAttribute((IExtensibleElement) model, "ipp:variables[" + j //$NON-NLS-1$
            + "]:description", "String", null); //$NON-NLS-1$ //$NON-NLS-2$
   }

   private ModelVariable createModelVariable(AttributeType attribute)
   {
      ModelVariable modelVariable;
      int index = getIndex(attribute.getName());
      if (index + 1 > variables.size())
      {
         modelVariable = new ModelVariable("", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         variables.add(modelVariable);
      }
      modelVariable = variables.get(index);
      String feature = attribute.getName()
            .substring(attribute.getName().indexOf("]") + 2); //$NON-NLS-1$
      if (feature.equals("name")) //$NON-NLS-1$
      {
         modelVariable.setName("${" + attribute.getValue() + "}"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (feature.equals("defaultValue")) //$NON-NLS-1$
      {
         modelVariable.setDefaultValue(attribute.getValue());
      }
      if (feature.equals("description")) //$NON-NLS-1$
      {
         modelVariable.setDescription(attribute.getValue());
      }
      return modelVariable;
   }

   public static int getIndex(String name)
   {
      int startIndex = name.indexOf("["); //$NON-NLS-1$
      int endIndex = name.indexOf("]"); //$NON-NLS-1$
      String index = name.substring(startIndex + 1, endIndex);
      return Integer.parseInt(index);
   }

   private class ConfigurationVariableIndexComparator implements Comparator<AttributeType>
   {

      public int compare(AttributeType o1, AttributeType o2)
      {
         Integer index1 = getIndex(o1.getName());
         Integer index2 = getIndex(o2.getName());

         return index1.compareTo(index2);
      }

   }

   private void parseVariables(EObject modelElement, String ref)
   {
      int idx = modelElement.toString().indexOf(ref);
      if (modelElement.eContainer() != null && idx > -1)
      {
         if (idx > 0 && modelElement.toString().charAt(idx - 1) != '\\')
         {
            List<EObject> refList = variableReferences.get(ref);
            if (refList == null)
            {
               refList = new ArrayList<EObject>();
               variableReferences.put(ref, refList);
            }
            refList.add(modelElement);
            if (modelElement.eContainer() instanceof IModelElement)
            {
               IModelElement parent = (IModelElement) modelElement.eContainer();
               Map<EObject, String> elementMap = elementReferences.get(String
                     .valueOf(parent.getElementOid()));
               if (elementMap == null)
               {
                  elementMap = new HashMap<EObject, String>();
                  elementReferences.put(String.valueOf(parent.getElementOid()),
                        elementMap);
               }
               elementMap.put(modelElement, ref);
            }
         }
      }
      for (Iterator<EObject> i = modelElement.eContents().iterator(); i.hasNext();)
      {
         parseVariables(i.next(), ref);
      }
   }

   public void cleanupReferences()
   {
      List<ModelVariable> invalidVariables = new ArrayList<ModelVariable>();
      for (Iterator<ModelVariable> i = variables.iterator(); i.hasNext();)
      {
         ModelVariable variable = i.next();
         List<EObject> invalidReferences = new ArrayList<EObject>();
         List<EObject> refList = variableReferences.get(variable.getName());
         if (refList != null)
         {
            for (Iterator<EObject> j = refList.iterator(); j.hasNext();)
            {
               EObject modelElement = j.next();
               if (modelElement.eContainer().toString().indexOf("ChangeDescriptionImpl") > -1) //$NON-NLS-1$
               {
                  invalidReferences.add(modelElement);
               }
            }
            if (invalidReferences.size() > 0
                  && (invalidReferences.size() == refList.size()))
            {
               invalidVariables.add(variable);
               variableReferences.remove(variable.getName());
            }
            else
            {
               refList.removeAll(invalidReferences);
            }
         }
      }
      variables.removeAll(invalidVariables);
   }
   
   public void refreshVariables(EObject modelElement)
   {
      Matcher matcher = pattern.matcher(modelElement.toString());
      if (modelElement.eContainer() != null)
      {
         while (matcher.find())
         {
            if ((matcher.start() == 0)
                  || ((matcher.start() > 0) && (modelElement.toString().charAt(
                        matcher.start() - 1) != '\\')))
            {
               String ref = modelElement.toString().substring(matcher.start(),
                     matcher.end());
               ref = ref.trim();

               ModelVariable modelVariable = null;
               List<EObject> refList = null;

               if ( !variableExists(ref))
               {
                  modelVariable = new ModelVariable(ref, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
                  variables.add(modelVariable);
               }
               else
               {
                  modelVariable = getVariable(ref);
                  refList = getReferences(modelVariable);
               }

               if (refList == null)
               {
                  refList = new ArrayList<EObject>();
                  variableReferences.put(ref, refList);
               }

               if ( !containedReference(modelElement, refList))
               {
                  refList.add(modelElement);
               }
            }
         }
      }
      for (Iterator<EObject> i = modelElement.eContents().iterator(); i.hasNext();)
      {
         refreshVariables(i.next());
      }
   }

   private boolean variableExists(String ref)
   {
      for (Iterator<ModelVariable> i = variables.iterator(); i.hasNext();)
      {
         ModelVariable variable = i.next();
         if(variableEquals(variable.getName(), ref))
         {
            return true;
         }
      }
      return false;
   }

   private ModelVariable getVariable(String ref)
   {
      for (Iterator<ModelVariable> i = variables.iterator(); i.hasNext();)
      {
         ModelVariable variable = i.next();
         if (variableEquals(variable.getName(), ref))
         {
            return variable;
         }
      }
      return null;
   }

   private boolean containedReference(EObject modelElement, List<EObject> refList)
   {
      for (Iterator<EObject> i = refList.iterator(); i.hasNext();)
      {
         EObject o = i.next();
         if (o.toString().equals(modelElement.toString()))
         {
            return true;
         }
      }
      return false;
   }

   public List<ModelVariable> getVariables()
   {
      return variables;
   }

   public List<EObject> getReferences(ModelVariable modelVariable)
   {

      String typelessName = "${" + modelVariable.getStrippedName().split(":")[0] + "}";
      List<EObject> references = variableReferences.get(modelVariable.getName());
      if (references == null || references.isEmpty())
      {
         references = variableReferences.get(typelessName);
      }
      return references;
   }

   public boolean hasVariable(IModelElement modelElement)
   {
      Object o = elementReferences.get(String.valueOf(modelElement.getElementOid()));
      return (o != null);
   }

   public void replaceVariablesWithDefaultValue(IModelElement modelElement)
   {
      Map<EObject, String> elementMap = elementReferences.get(String.valueOf(modelElement
            .getElementOid()));
      replaceAllVariablesWithDefaultValues(elementMap, (EObject) modelElement);
   }

   private void replaceAllVariablesWithDefaultValues(Map<EObject, String> elementMap,
         EObject modelElement)
   {
      if (!(modelElement instanceof IModelElement))
      {
         for (Iterator<Entry<EObject, String>> i = elementMap.entrySet().iterator(); i
               .hasNext();)
         {
            Entry<EObject, String> entry = i.next();
            ModelVariable modelVariable = getModelVariableByName(entry.getValue());
            if (modelElement instanceof AttributeType
                  && entry.getKey() instanceof AttributeType)
            {
               AttributeType modelAttribute = (AttributeType) modelElement;
               AttributeType refAttribute = (AttributeType) entry.getKey();
               if (modelAttribute.getName().equals(refAttribute.getName()))
               {
                  this.replaceVariable(modelAttribute, modelVariable, modelVariable
                        .getDefaultValue());
               }
            }
         }
      }
      for (Iterator<EObject> i = modelElement.eContents().iterator(); i.hasNext();)
      {
         replaceAllVariablesWithDefaultValues(elementMap, i.next());
      }
   }

   public ModelVariable getModelVariableByName(String value)
   {
      for (Iterator<ModelVariable> i = variables.iterator(); i.hasNext();)
      {
         ModelVariable modelVariable = i.next();
         if (modelVariable.getName().equals(value))
         {
            return modelVariable;
         }
      }
      return null;
   }

   public String replaceAllVariablesByDefaultValue(String aString)
   {
      String result = aString;
      if (aString == null || StringUtils.isEmpty(result))
      {
         return result;
      }
      for (Iterator<ModelVariable> i = variables.iterator(); i.hasNext();)
      {
         ModelVariable modelVariable = i.next();
         result = replace(modelVariable, modelVariable.getDefaultValue(), result);
      }
      //Additionally deal with escaped variables
      result = result.replaceAll("\\\\\\$\\{", "\\$\\{"); //$NON-NLS-1$ //$NON-NLS-2$
      return result;
   }

   public void replaceVariable(Object o, ModelVariable modelVariable, String newValue)
   {
      if (o instanceof AttributeType)
      {
         AttributeType attribute = (AttributeType) o;
         String value = (String) attribute.getValue();
         value = replace(modelVariable, newValue, value);
         attribute.setValue(value);
         // CRNT-22739
         if (attribute.getName().equalsIgnoreCase("ipp:criticalityFormula")) //$NON-NLS-1$
         {
            setCriticalityFormulaChanged(true);
         }
      }
      else
      {
         if (o instanceof XmlTextNode)
         {
            XmlTextNode textNode = (XmlTextNode) o;
            String value = ModelUtils.getCDataString(textNode.getMixed());
            value = replace(modelVariable, newValue, value);
            ModelUtils.setCDataString(textNode.getMixed(), value);
         }
         else
         {
            if (o instanceof DescriptionType)
            {
               DescriptionType description = (DescriptionType) o;
               String value = ModelUtils.getCDataString(description.getMixed());
               value = replace(modelVariable, newValue, value);
               ModelUtils.setCDataString(description.getMixed(), value);
            }
            else
            {
               if (o instanceof IIdentifiableElement)
               {
                  // ID
                  IIdentifiableElement identifiable = (IIdentifiableElement) o;
                  String value = identifiable.getId();
                  value = replace(modelVariable, newValue, value);
                  identifiable.setId(value);
                  // NAME
                  value = identifiable.getName();
                  value = replace(modelVariable, newValue, value);
                  identifiable.setName(value);
               }
            }
         }
      }
   }

   public void replaceVariable(ModelVariable modelVariable, String newValue)
   {
      List<EObject> refList = getReferences(modelVariable);
      if (refList != null)
      {
         for (Iterator<EObject> k = refList.iterator(); k.hasNext();)
         {
            Object o = k.next();
            replaceVariable(o, modelVariable, newValue);
         }
      }
      if (!newValue.equals(modelVariable.getName()))
      {
         variableReferences.put(newValue, refList);
         variableReferences.remove(modelVariable.getName());
      }
   }

   public String replace(ModelVariable modelVariable, String newValue, String value)
   {
      String literal = modelVariable.getName();
      value = replaceLiteral(literal, value, newValue);
      if (modelVariable.getStrippedName().endsWith(":String"))
      {
         String typelessName = modelVariable.getStrippedName().split(":")[0];
         literal = "${" + typelessName + "}";
         value = replaceLiteral(literal, value, newValue);
      }
      return value;
   }

   private String replaceLiteral(String literal, String value, String newValue)
   {
      String tobeReplaced = "";
      String replacement = "";
      if (!newValue.startsWith("${")) //$NON-NLS-1$
      {
         tobeReplaced = literal.substring(2, literal.length() - 1);
         replacement = newValue;
         if (replacement.indexOf("$") > -1) //$NON-NLS-1$
         {
            replacement = replacement.replace("$", "\\$"); //$NON-NLS-1$ //$NON-NLS-2$
         }
         List<String> list1 = new ArrayList<String>();
         while (value.indexOf("${" + tobeReplaced + "}") > -1) //$NON-NLS-1$ //$NON-NLS-2$
         {
            int idx = value.indexOf("${" + tobeReplaced + "}"); //$NON-NLS-1$ //$NON-NLS-2$
            if (idx == 0 || (idx > 0 && value.charAt(idx - 1) != '\\'))
            {
               value = value.replaceFirst("(\\$\\{" + tobeReplaced + "\\})", replacement); //$NON-NLS-1$ //$NON-NLS-2$
            }
            else
            {
               list1.add("\\$\\{" + tobeReplaced + "\\}"); //$NON-NLS-1$ //$NON-NLS-2$
               value = value.replaceFirst("(\\$\\{" + tobeReplaced + "\\})", "*0*0*0*0*"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
         }
         for (Iterator<String> i = list1.iterator(); i.hasNext();)
         {
            String string = i.next();
            value = value.replaceFirst("(\\*0\\*0\\*0\\*0\\*)", string); //$NON-NLS-1$
         }
      }
      else
      {
         tobeReplaced = literal.substring(2, literal.length() - 1);
         replacement = newValue.substring(2, newValue.length() - 1);
         if (replacement.indexOf("$") > -1) //$NON-NLS-1$
         {
            replacement = replacement.replace("$", "\\$"); //$NON-NLS-1$ //$NON-NLS-2$
         }
         tobeReplaced = RegEx.REUtil.quoteMeta(tobeReplaced);
         value = value.replaceAll("(\\$\\{" + tobeReplaced + "\\})", "\\$\\{" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
               + replacement + "\\}"); //$NON-NLS-1$
      }
      return value;
   }

   public boolean isSecurityContext(String name)
   {
      if (name.startsWith("${")) //$NON-NLS-1$
      {
         name = name.substring(2, name.length() - 1);
      }
      String type = VariableContextHelper.getType(name);
      if(type.equals(ConfigurationVariableScope.Password.name()))
      {
         return true;
      }

      return false;
   }

   public boolean isValidType(String name)
   {
      if (name.startsWith("${")) //$NON-NLS-1$
      {
         name = name.substring(2, name.length() - 1);
      }
      String type = VariableContextHelper.getType(name);
      ConfigurationVariableScope[] scopes = ConfigurationVariableScope.values();
      for(ConfigurationVariableScope scope : scopes)
      {
         if(scope.name().equals(type))
         {
            return true;
         }
      }

      return false;
   }

   public boolean isValidName(String name)
   {
      if (name == null)
      {
         return false;
      }
      if (name.startsWith("${")) //$NON-NLS-1$
      {
         name = name.substring(2, name.length() - 1);
      }
      if (StringUtils.isEmpty(name))
      {
         return false;
      }

      String[] parts = name.split(":");
      if(parts.length > 2)
      {
         return false;
      }

      name = VariableContextHelper.getName(name);
      if (!StringUtils.isValidIdentifier(name))
      {
         return false;
      }
      return true;
   }

   public boolean isCriticalityFormulaChanged()
   {
      return criticalityFormulaChanged;
   }

   public void setCriticalityFormulaChanged(boolean criticalityFormulaChanged)
   {
      this.criticalityFormulaChanged = criticalityFormulaChanged;
   }

   private boolean variableEquals(String left, String right)
   {
      if (left.startsWith("${")) //$NON-NLS-1$
      {
         left = left.substring(2, left.length() - 1);
      }
      if (right.startsWith("${")) //$NON-NLS-1$
      {
         right = right.substring(2, right.length() - 1);
      }
      String leftType = VariableContextHelper.getType(left);
      String rightType = VariableContextHelper.getType(right);
      if(!leftType.equals(rightType))
      {
         return false;
      }

      String leftName = VariableContextHelper.getName(left);
      String rightName = VariableContextHelper.getName(right);
      if(leftName.equalsIgnoreCase(rightName))
      {
         return true;
      }

      return false;
   }
}