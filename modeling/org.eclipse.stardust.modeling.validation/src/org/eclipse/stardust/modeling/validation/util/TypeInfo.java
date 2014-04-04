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
package org.eclipse.stardust.modeling.validation.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.*;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class TypeInfo
{
   private static final String EXTENDS = " extends "; //$NON-NLS-1$
   private IType type;
   private Map<String, String> parameterMapping = CollectionUtils.newMap();
   private TypeFinder finder;

   public TypeInfo(TypeFinder finder, IType type, String parameterString)
      throws JavaModelException
   {
      this.finder = finder;
      this.type = type;
      setParameterString(type, parameterString);
   }

   private void setParameterString(IType type, String parameterString)
         throws JavaModelException
   {
      if (parameterString != null)
      {
         List<String> parameters = split(strip(parameterString.trim()));
         ITypeParameter[] types = type.getTypeParameters();
         for (int i = 0; i < types.length && i < parameters.size(); i++)
         {
            parameterMapping.put(types[i].getElementName(), parameters.get(i));
         }
      }
   }

   public List<MethodInfo> getConstructors() throws JavaModelException
   {
      Map<String, MethodInfo> constructorInfos = CollectionUtils.newMap();
      fetchMethods(constructorInfos, true);
      if (constructorInfos.isEmpty())
      {
         // no explicit constructor, infer default one
         MethodInfo defc = new MethodInfo(true, type.getElementName(),
               StringUtils.EMPTY_STRING_ARRAY, StringUtils.EMPTY_STRING_ARRAY, StringUtils.EMPTY_STRING_ARRAY,
               "V", "void", true);
         constructorInfos.put(defc.getEncoded(), defc);
      }
      return CollectionUtils.newList(constructorInfos.values());
   }

   public List<FieldInfo> getFields() throws JavaModelException
   {
      List<FieldInfo> fieldInfos = CollectionUtils.newList();
      fetchFields(CollectionUtils.<String>newSet(), fieldInfos, CollectionUtils.<String>newSet());
      return fieldInfos;
   }

   private void fetchFields(Set<String> names, List<FieldInfo> fieldInfos, Set<String> visitedFields) throws JavaModelException
   {
      fetchFields(names, fieldInfos, false);
      if (!type.isInterface())
      {
         fetchFields(names, fieldInfos, visitedFields, type.getSuperclassTypeSignature());
      }
      String[] interfaces = type.getSuperInterfaceTypeSignatures();
      for (int i = 0; i < interfaces.length; i++)
      {
         fetchFields(names, fieldInfos, visitedFields, interfaces[i]);
      }
   }

   private void fetchFields(Set<String> names, List<FieldInfo> fieldInfos, Set<String> visitedFields,
         String typeSignature) throws JavaModelException
   {
      String superType = typeSignature == null
            ? "java.lang.Object" : resolveSignature(typeSignature); //$NON-NLS-1$
      if (!visitedFields.contains(superType))
      {
         visitedFields.add(superType);
         TypeInfo descriptor = finder.findType(superType);
         if (descriptor == null)
         {
            System.err.println(MessageFormat.format(
                  Validation_Messages.CSL_ERR_UNABLE_TO_RESOLVE_CL,
                  new Object[] { superType }));
         }
         else
         {
            descriptor.fetchFields(names, fieldInfos, visitedFields);
         }
      }
   }

   private void fetchFields(Set<String> names, List<FieldInfo> fieldInfos, boolean constructors)
      throws JavaModelException
   {
      IField[] fields = type.getFields();
      for (int i = 0; i < fields.length; i++)
      {
         IField field = fields[i];
         field.getFlags();
         String fieldName = field.getElementName();
         String fieldType = field.getTypeSignature();
         String parameterType = resolveSignature(fieldType);
         FieldInfo info = new FieldInfo(fieldName, parameterType, field.getFlags());
         if (!names.contains(fieldName))
         {
            names.add(fieldName);
            fieldInfos.add(info);
         }
      }
   }

   public List<MethodInfo> getMethods() throws JavaModelException
   {
      Map<String, MethodInfo> methodInfos = CollectionUtils.newMap();
      fetchMethods(methodInfos, CollectionUtils.<String>newSet());
      return CollectionUtils.newList(methodInfos.values());
   }

   private void fetchMethods(Map<String, MethodInfo> methodInfos, Set<String> visitedTypeNames) throws JavaModelException
   {
      fetchMethods(methodInfos, false);
      if (!type.isInterface())
      {
         fetchMethods(methodInfos, visitedTypeNames, type.getSuperclassTypeSignature());
      }
      String[] interfaces = type.getSuperInterfaceTypeSignatures();
      for (int i = 0; i < interfaces.length; i++)
      {
         fetchMethods(methodInfos, visitedTypeNames, interfaces[i]);
      }
   }

   private void fetchMethods(Map<String, MethodInfo> methodInfos, Set<String> visitedTypeNames,
         String typeSignature) throws JavaModelException
   {
      String superType = typeSignature == null
            ? "java.lang.Object" : resolveSignature(typeSignature); //$NON-NLS-1$
      if (!visitedTypeNames.contains(superType))
      {
         visitedTypeNames.add(superType);
         TypeInfo descriptor = finder.findType(superType);
         if (descriptor == null)
         {
            System.err.println(Validation_Messages.CSL_ERR_UNABLE_TO_RESOLVE_CL + superType + "'.");  //$NON-NLS-1$
         }
         else
         {
            descriptor.fetchMethods(methodInfos, visitedTypeNames);
         }
      }
   }

   private void fetchMethods(Map<String, MethodInfo> methodInfos, boolean constructors)
      throws JavaModelException
   {
      for (IMethod method : type.getMethods())
      {
         if (constructors == method.isConstructor()
               && !(method.getElementType() == IMethod.INITIALIZER)
               && !"<clinit>".equals(method.getElementName()))
         {
            String methodName = method.getElementName();

            String[] parameterNames = getParameterNames(method);

            String[] parameterSignatures = method.getParameterTypes();
            String[] parameterTypes = new String[parameterSignatures.length];
            for (int j = 0; j < parameterSignatures.length; j++)
            {
               parameterTypes[j] = resolveSignature(parameterSignatures[j]);
            }
            String returnSignature = method.getReturnType();
            String returnType = resolveSignature(returnSignature);
            boolean accessible = Flags.isPublic(method.getFlags()) || type.isInterface();
            MethodInfo info = new MethodInfo(constructors, methodName,
                  parameterSignatures, parameterTypes, parameterNames,
                  returnSignature, returnType, accessible);
            String key = info.getEncoded();
            if (!methodInfos.containsKey(key))
            {
               methodInfos.put(key, info);
            }
         }
      }
   }

   private String[] getParameterNames(IMethod method) throws JavaModelException
   {
      String[] parameterNames = method.getParameterNames();
      IAnnotation parameterNamesAnnotation = findParameterNames(method);
      if (parameterNamesAnnotation != null)
      {
         IAnnotation[] names = getParameterNamesValues(parameterNamesAnnotation);
         if (names != null)
         {
            for (int c = 0, i = 0; i < names.length && i < parameterNames.length; i++)
            {
               IAnnotation nameAnnotation = names[i];
               if (matchAnnotation("ParameterName", nameAnnotation.getElementName()))
               {
                  parameterNames[c++] = getParameterName(nameAnnotation);
               }
            }
         }
      }
      ILocalVariable[] params = method.getParameters();
      for (int i = 0; i < params.length && i < parameterNames.length; i++)
      {
         IAnnotation[] annotations = params[i].getAnnotations();
         if (annotations != null)
         {
            for (IAnnotation annotation : annotations)
            {
               if (matchAnnotation("ParameterName", annotation.getElementName()))
               {
                  parameterNames[i] = getParameterName(annotation);
               }
            }
         }
      }
      return parameterNames;
   }

   private String getParameterName(IAnnotation nameAnnotation) throws JavaModelException
   {
      IMemberValuePair[] values = nameAnnotation.getMemberValuePairs();
      for (IMemberValuePair pair : values)
      {
         if ("value".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_STRING) //$NON-NLS-1$
         {
            Object value = pair.getValue();
            if (!value.getClass().isArray())
            {
               return (String) value;
            }
         }
      }
      return null;
   }

   private IAnnotation[] getParameterNamesValues(IAnnotation parameterNamesAnnotation) throws JavaModelException
   {
      IMemberValuePair[] values = parameterNamesAnnotation.getMemberValuePairs();
      for (IMemberValuePair pair : values)
      {
         if ("value".equals(pair.getMemberName()) && pair.getValueKind() == IMemberValuePair.K_ANNOTATION) //$NON-NLS-1$
         {
            Object value = pair.getValue();
            if (value.getClass().isArray())
            {
               Object[] array = (Object[]) value;
               IAnnotation[] annotations = new IAnnotation[array.length];
               System.arraycopy(array, 0, annotations, 0, array.length);
               return annotations;
            }
         }
      }
      return null;
   }

   private IAnnotation findParameterNames(IMethod method) throws JavaModelException
   {
      for (IAnnotation annotation : method.getAnnotations())
      {
         if (matchAnnotation("ParameterNames", annotation.getElementName()))
         {
            return annotation;
         }
      }
      return null;
   }

   private boolean matchAnnotation(String clazz, String elementName)
   {
      int ix = elementName.lastIndexOf('.');
      return clazz.equals(ix < 0 ? elementName : elementName.substring(ix + 1));
   }

   private String resolveSignature(String signature) throws JavaModelException
   {
      String value = Signature.toString(signature);
      return resolve(value);
   }

   public String resolve(String value) throws JavaModelException
   {
      String parameterString = null;
      int ix = value.indexOf('<');
      if (ix > 0)
      {
         parameterString = value.substring(ix);
         value = value.substring(0, ix);
      }
      return resolveSimpleType(value) + resolveParameters(parameterString);
   }

   private String resolveParameters(String parameterString) throws JavaModelException
   {
      if (parameterString == null)
      {
         return ""; //$NON-NLS-1$
      }
      List<String> parameters = split(strip(parameterString.trim()));
      StringBuffer builder = new StringBuffer();
      builder.append('<');
      for (int i = 0; i < parameters.size(); i++)
      {
         if (i > 0)
         {
            builder.append(',');
         }
         builder.append(resolve((String) parameters.get(i)));
      }
      builder.append('>');
      return builder.toString();
   }

   /**
    * Resolves a simple type reference in the context of this type.
    *
    * @param value simple type name
    * @return fully qualified type name
    * @throws JavaModelException
    */
   @SuppressWarnings("restriction")
   private String resolveSimpleType(String value) throws JavaModelException
   {
      String[][] resolved = null;
      //(rp) Workaround for CRNT-13058
      //In any case the resolveType-method returns null for "BinaryTypes" (e.g. Date, String...).
      //But in Eclipse 3.4 this operation is much more time consuming because it uses the same mechanism as
      //for the resolution of "SourceTypes" (e.g. CreateSupportData.java) which uses an instance of a SelectionEngine to
      //perform a lookup. See "NamedMember.resolveType(String typeName, WorkingCopyOwner owner)".
      //This decreases the operation speed of the modeler. So workaround here:
      if (!(type instanceof org.eclipse.jdt.internal.core.BinaryType))
      {
         resolved = type.resolveType(value);
      }
      //(rp) End of Workaround
      if (resolved == null)
      {
         String defaultValue = null;
         int ix = value.indexOf(EXTENDS);
         if (ix > 0)
         {
            defaultValue = value.substring(ix + EXTENDS.length());
            value = value.substring(0, ix);
         }
         String match = (String) parameterMapping.get(value);
         if (match != null)
         {
            value = match;
         }
         else if (defaultValue != null)
         {
            value = defaultValue;
         }
      }
      else if (resolved.length == 1)
      {
         value = resolved[0][0] + '.' + resolved[0][1].replace('.', '$');
      }
      return value;
   }

   private List<String> split(String typeList)
   {
      List<String> parameters = CollectionUtils.newList();
      if (typeList.length() > 0)
      {
         int start = 0;
         int pcount = 0;
         for (int i = 0; i < typeList.length(); i++)
         {
            char c = typeList.charAt(i);
            switch (c)
            {
            case '<':
               pcount++;
               break;
            case '>':
               pcount--;
               break;
            case ',':
               if (pcount == 0)
               {
                  parameters.add(typeList.substring(start, i).trim());
                  start = i + 1;
               }
               break;
            }
         }
         if (start < typeList.length())
         {
            parameters.add(typeList.substring(start).trim());
         }
      }
      return parameters;
   }

   private String strip(String parameters)
   {
      // strip leading and trailing markers
      int start = parameters.indexOf('<');
      start = start < 0 ? 0 : start + 1;
      int end = parameters.lastIndexOf('>');
      if (end < 0)
      {
         end = parameters.length();
      }
      return parameters.substring(start, end).trim();
   }

   public IType getType()
   {
      return type;
   }

   public String getParameterType(String elementName)
   {
      return (String) parameterMapping.get(elementName);
   }

   public String getFullName()
   {
      try
      {
         // TODO: (fh) weird conversion, should we try to construct parameters directly ?
         String parameterizedName = type.getFullyQualifiedParameterizedName();
         String fullName = type.getFullyQualifiedName('.');
         parameterizedName = type.getFullyQualifiedName() + parameterizedName.substring(fullName.length());
         return resolve(parameterizedName);
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }
      return ""; //$NON-NLS-1$
   }

   public void setParameterType(String elementName, String value)
   {
      if (value == null || value.trim().length() == 0)
      {
         parameterMapping.remove(elementName);
      }
      else
      {
         parameterMapping.put(elementName, value.trim());
      }
   }

   public boolean implementsInterface(String name)
   {
      ArrayList<TypeInfo> interfaces = new ArrayList<TypeInfo>();

      if(getFullName().equals(name))
      {
         return true;
      }

      try
      {
         findInterfaces(interfaces, type);
         for(TypeInfo type : interfaces)
         {
            if(type.getFullName().equals(name))
            {
               return true;
            }
         }

         ITypeHierarchy typeHierarchy = type.newSupertypeHierarchy(null);
         IType superType = typeHierarchy.getSuperclass(type);
         TypeInfo superTypeInfo = new TypeInfo(finder, superType, null);

         return superTypeInfo.implementsInterface(name);
      }
      catch (Exception exception)
      {
         // ignore
      }

      return false;
   }

   private void findInterfaces(ArrayList<TypeInfo> list, IType thisType) throws JavaModelException
   {
      String[] interfaces = thisType.getSuperInterfaceTypeSignatures();
      for (int i = 0; i < interfaces.length; i++)
      {
         try
         {
            String superType = resolveSignature(interfaces[i]);
            TypeInfo theType = finder.findType(superType);
            if(!list.contains(theType) && theType != null)
            {
               list.add(theType);
            }
            getInterfaces(finder.findExactType(superType), list);
         }
         catch (Exception exception)
         {
            // ignore
         }
      }
   }

   public List<TypeInfo> getInterfaces()
   {
      ArrayList<TypeInfo> list = new ArrayList<TypeInfo>();
      try
      {
         findInterfaces(list, type);
      }
      catch (Exception exception)
      {
         // ignore
      }
      return list;
   }

   private void getInterfaces(IType baseType, List<TypeInfo> list)
   {
      if(baseType == null)
      {
         return;
      }

      try
      {
         String[] interfaces = baseType.getSuperInterfaceTypeSignatures();
         for (int i = 0; i < interfaces.length; i++)
         {
            try
            {
               String superType = resolveSignature(interfaces[i]);
               TypeInfo theType = finder.findType(superType);
               if(!list.contains(theType))
               {
                  list.add(theType);
                  getInterfaces(finder.findExactType(superType), list);
               }
            }
            catch (Exception exception)
            {
               // ignore
            }
         }
      }
      catch (Exception exception)
      {
         // ignore
      }
   }

   public boolean isInterface()
   {
      try
      {
         return getType().isInterface();
      }
      catch (JavaModelException e)
      {
         return false;
      }
   }

   public boolean isSameType(String nodeName)
   {
      try
      {
         String resolvedName = resolve(nodeName);
         return getFullName().equals(resolvedName);
      }
      catch (JavaModelException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return false;
   }
}