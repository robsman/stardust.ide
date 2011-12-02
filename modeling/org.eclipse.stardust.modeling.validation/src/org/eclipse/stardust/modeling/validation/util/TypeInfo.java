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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.core.BinaryType;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


public class TypeInfo
{
   private static final String EXTENDS = " extends "; //$NON-NLS-1$
   private IType type;
   private Map parameterMapping = new HashMap();
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
         List parameters = split(strip(parameterString.trim()));
         ITypeParameter[] types = type.getTypeParameters();
         for (int i = 0; i < types.length && i < parameters.size(); i++)
         {
            parameterMapping.put(types[i].getElementName(), parameters.get(i));
         }
      }
   }
   
   public List getConstructors() throws JavaModelException
   {
      Map methods = new HashMap();
      fetchMethods(methods, true);
      List result = new ArrayList();
      result.addAll(methods.values());
      return result;
   }
   
   public List getFields() throws JavaModelException
   {
      Map fields = new HashMap();
      Set visitedFields = new HashSet();
      fetchFields(fields, visitedFields);
      List result = new ArrayList();
      result.addAll(fields.values());
      return result;
   }

   private void fetchFields(Map list, Set visitedFields) throws JavaModelException
   {
      fetchFields(list, false);
      if (!type.isInterface())
      {
         fetchFields(list, visitedFields, type.getSuperclassTypeSignature());
      }
      String[] interfaces = type.getSuperInterfaceTypeSignatures();
      for (int i = 0; i < interfaces.length; i++)
      {
         fetchFields(list, visitedFields, interfaces[i]);
      }
   }

   private void fetchFields(Map list, Set visitedFields,
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
            descriptor.fetchFields(list, visitedFields);
         }
      }
   }

   private void fetchFields(Map list, boolean constructors)
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
         if (!list.containsKey(fieldName))
         {
            list.put(fieldName, info);
         }
      }
   }   
   
   public List getMethods() throws JavaModelException
   {
      Map methods = new HashMap();
      Set visitedTypeNames = new HashSet();
      fetchMethods(methods, visitedTypeNames);
      List result = new ArrayList();
      result.addAll(methods.values());
      return result;
   }

   private void fetchMethods(Map list, Set visitedTypeNames) throws JavaModelException
   {
      fetchMethods(list, false);
      if (!type.isInterface())
      {
         fetchMethods(list, visitedTypeNames, type.getSuperclassTypeSignature());
      }
      String[] interfaces = type.getSuperInterfaceTypeSignatures();
      for (int i = 0; i < interfaces.length; i++)
      {
         fetchMethods(list, visitedTypeNames, interfaces[i]);
      }
   }

   private void fetchMethods(Map list, Set visitedTypeNames,
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
            System.err.println(Validation_Messages.CSL_ERR_UNABLE_TO_RESOLVE_CL + superType + "'."); //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$
         }
         else
         {
            descriptor.fetchMethods(list, visitedTypeNames);
         }
      }
   }

   private void fetchMethods(Map list, boolean constructors)
      throws JavaModelException
   {
      IMethod[] methods = type.getMethods();
      for (int i = 0; i < methods.length; i++)
      {
         IMethod mtd = methods[i];
         if (constructors == mtd.isConstructor()
               && !(mtd.getElementType() == IMethod.INITIALIZER))
         {
            String methodName = mtd.getElementName();
            String[] parameterSignatures = mtd.getParameterTypes();
            String[] parameterTypes = new String[parameterSignatures.length];
            for (int j = 0; j < parameterSignatures.length; j++)
            {
               parameterTypes[j] = resolveSignature(parameterSignatures[j]);
            }
            String returnSignature = mtd.getReturnType();
            String returnType = resolveSignature(returnSignature);
            boolean accessible = Flags.isPublic(mtd.getFlags());
            MethodInfo info = new MethodInfo(constructors,
                  methodName, parameterSignatures, parameterTypes,
                  returnSignature, returnType, accessible);
            String key = info.getEncoded();
            if (!list.containsKey(key))
            {
               list.put(key, info);
            }
         }
      }
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
      List parameters = split(strip(parameterString.trim()));
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
   private String resolveSimpleType(String value) throws JavaModelException
   {      
	  String[][] resolved = null;
	  //(rp) Workaround for CRNT-13058 
	  //In any case the resolveType-method returns null for "BinaryTypes" (e.g. Date, String...). 
	  //But in Eclipse 3.4 this operation is much more time consuming because it uses the same mechanism as
	  //for the resolution of "SourceTypes" (e.g. CreateSupportData.java) which uses an instance of a SelectionEngine to
	  //perform a lookup. See "NamedMember.resolveType(String typeName, WorkingCopyOwner owner)".
	  //This decreases the operation speed of the modeler. So workaround here:
	  if (!(type instanceof BinaryType)) 
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
         value = resolved[0][0] + '.' + resolved[0][1];
      }
      return value;
   }

   private List split(String typeList)
   {
      List parameters = new ArrayList();
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