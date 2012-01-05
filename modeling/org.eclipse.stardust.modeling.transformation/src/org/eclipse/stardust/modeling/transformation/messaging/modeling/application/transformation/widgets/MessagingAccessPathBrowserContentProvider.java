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

package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.spi.IAccessPathEditor;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.ui.AccessPathBrowserContentProvider;
import org.eclipse.stardust.modeling.integration.dms.data.DmsTypeUtils;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.MessageTransformationUtils;
import org.eclipse.stardust.modeling.validation.util.FieldInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;




public class MessagingAccessPathBrowserContentProvider extends AccessPathBrowserContentProvider
{
   private DirectionType direction;
   private Map childrenCache = new HashMap();
   private Map parentCache = new HashMap();
   private Object root;
   private MessageTransformationUtils mtaUtils = new MessageTransformationUtils();


   public Object[] getChildren(Object parentElement, boolean caching, boolean filterMethods)
   {      
      Object[] children = null;;
      if (caching) {
         children = (Object[]) childrenCache.get(parentElement);         
      } 
      if (children == null || children.length == 0)
      {
         children = new Object[0];
         ITypedElement accessPoint = (ITypedElement) parentElement;
         IMetaType type = accessPoint.getMetaType();
         if (type instanceof DataTypeType)
         {            
            DataTypeType dataType = (DataTypeType) ((AccessPointType)root).getMetaType();
           
               IAccessPathEditor editor = AccessPointUtil.getSPIAccessPathEditor(dataType);
               List aps = editor.getAccessPoints(null,
                  (IExtensibleElement) accessPoint, direction);;
               if (AccessPointUtil.isIn(direction))
               {
                  List inAPs = editor.getAccessPoints(null,
                     (IExtensibleElement) accessPoint, DirectionType.OUT_LITERAL);
                  for (int i = 0; i < inAPs.size(); i++)
                  {
                     IExtensibleElement element = (IExtensibleElement) inAPs.get(i);
                     if (AttributeUtil.getBooleanValue(element, CarnotConstants.BROWSABLE_ATT)
                           && !aps.contains(element))
                     {
                        //aps.add(element);
                     }
                  }
           
               if (aps != null)
               {                  
            	   if (dataType.getId().equalsIgnoreCase("serializable") && filterMethods) { //$NON-NLS-1$
            		   try {
            			   List<AccessPointType> jbAps = new ArrayList<AccessPointType>();
                		   TypeFinder typeFinder = new TypeFinder(dataType);
                		   String className = AttributeUtil.getAttributeValue((IExtensibleElement) parentElement, CarnotConstants.CLASS_NAME_ATT);
                		   if (className.endsWith("[]")) { //$NON-NLS-1$
                			   AccessPointType parentApt = (AccessPointType) parentCache.get(parentElement);
                			   if (parentApt != null) {
                    			   String parentClassName = AttributeUtil.getAttributeValue((IExtensibleElement) parentApt, CarnotConstants.CLASS_NAME_ATT);
                    			   String fullClassName = getFullQualifiedName(dataType, className, parentClassName); 
                    		       if (fullClassName != null) {
                    		    	   fullClassName = fullClassName.replace("[]", ""); //$NON-NLS-1$ //$NON-NLS-2$
                    		    	   AccessPointType newApt = DmsTypeUtils.createSerializableAccessPointType("TestName", fullClassName, direction, dataType);                		    	    //$NON-NLS-1$
                    		    	   aps = Arrays.asList(getChildren(newApt, caching, false));
                    		    	   className = fullClassName;
                    		       }                			                   				   
                			   }
                		   }
                		   if (className.startsWith("java.util.List") && (className.indexOf(">") > -1)) { //$NON-NLS-1$ //$NON-NLS-2$
                			   className = className.replace("java.util.List", ""); //$NON-NLS-1$ //$NON-NLS-2$
                			   className = className.replaceAll("<", ""); //$NON-NLS-1$ //$NON-NLS-2$
                			   className = className.replaceAll(">", ""); //$NON-NLS-1$ //$NON-NLS-2$
                			   for (Iterator<AccessPointType> i = aps.iterator(); i.hasNext();) {
                				   AccessPointType annotatedType = i.next();
                				   if (annotatedType.getId().equalsIgnoreCase("add(" + className + ")")) { //$NON-NLS-1$ //$NON-NLS-2$
                					   aps = Arrays.asList(getChildren(annotatedType, caching, false));
                				   }
                			   }
                		   }
                		   TypeInfo typeInfo = typeFinder.findType(className);
                		   List<FieldInfo> fields = typeInfo.getFields();
                		   for (Iterator<FieldInfo> f = fields.iterator(); f.hasNext();) {
                			   FieldInfo fieldInfo = f.next();
                			   AccessPointType jbAp = getJBStyleAccessPoint(fieldInfo, aps);
                			   if (jbAp != null) {
                				   jbAps.add(jbAp);
                			   }                			   
                		   }                		   
                    	   children = jbAps.toArray();            			   
            		   } catch (Throwable t) {
            			   System.out.println("Problems with " + AttributeUtil.getAttributeValue((IExtensibleElement) parentElement, CarnotConstants.CLASS_NAME_ATT)); //$NON-NLS-1$
            			   t.printStackTrace();
            		   }
            	   } else {
                	   children = aps.toArray();            		   
            	   }
               }              
            }
         }         
         childrenCache.put(parentElement, children);
         for (int i = 0; i < children.length; i++)
         {
            Object child = children[i];
            parentCache.put(child, parentElement);
         }
      }
      return children;
   }

   private AccessPointType getJBStyleAccessPoint(FieldInfo fieldInfo, List aps) {
	   for (Iterator<AccessPointType> i = aps.iterator(); i.hasNext();) {
		   AccessPointType apt = i.next();
		   String name= apt.getName();
		   if (apt.getName().startsWith("get")) {			    //$NON-NLS-1$
			   name = name.replace("get",""); //$NON-NLS-1$ //$NON-NLS-2$
			   name = name.replace("()",""); //$NON-NLS-1$ //$NON-NLS-2$
			   if (fieldInfo.getFieldName().equalsIgnoreCase(name)) {
				   apt.setId(name);
				   apt.setName(name);
				   return apt;
			   }
		   }
	   }
	return null;
}

public Object getParent(Object element)
   {
      return parentCache.get(element);
   }

   public boolean hasChildren(Object element)
   {
      if ( !(element instanceof AccessPointType))
      {
         return false;
      }  
      if (mtaUtils.isPrimitive((AccessPointType)element)) 
      {
         return false;        
      }
      return getChildren(element).length > 0; 
   }

   public Object[] getElements(Object inputElement)
   {
      Object[] children = (Object[]) childrenCache.get(inputElement);
      if (children == null)
      {
         if (inputElement instanceof ITypedElement && inputElement instanceof IExtensibleElement)
         {
            ITypedElement accessPoint = (ITypedElement) inputElement;
            children = getChildren(accessPoint);
         }
         else
         {
            children = new Object[0];
            childrenCache.put(inputElement, children);
         }
      }
      return children;
   }

   public void dispose()
   {
      childrenCache.clear();
      parentCache.clear();
      childrenCache = null;
      parentCache = null;
      root = null;
      direction = null;
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      childrenCache.clear();
      parentCache.clear();
      root = newInput;
   }

   public ISelection parseSelection(String selectedMethod)
   {
      Object selection = null;
      if (root instanceof ITypedElement && root instanceof IExtensibleElement)
      {
         selection = parseSelection((ITypedElement) root, selectedMethod);
      }
      return selection == null ? null : new StructuredSelection(selection);
   }

   private AccessPointType parseSelection(ITypedElement accessPoint, String selectedMethod)
   {
      IMetaType type = accessPoint.getMetaType();
      if (type instanceof DataTypeType)
      {
         DataTypeType dataType = (DataTypeType) accessPoint.getMetaType();
         IAccessPathEditor editor = AccessPointUtil.getSPIAccessPathEditor(dataType);
         String[] splitted = editor.splitAccessPath(selectedMethod);
         if (exists(splitted[0]))
         {
            Object[] children = getChildren(accessPoint);
            for (int i = 0; i < children.length; i++)
            {
               AccessPointType child = (AccessPointType) children[i];
               if (splitted[0].equals(child.getId()))
               {
                  return exists(splitted[1]) ? parseSelection(child, splitted[1]) : child;
               }
            }
         }
      }
      return null;
   }

   private boolean exists(String string)
   {
      return string != null && string.length() > 0;
   }
   
   
   
   private String rootTypeName;
   
   public MessagingAccessPathBrowserContentProvider(DirectionType direction)
   {
      super(direction);
   }

   public MessagingAccessPathBrowserContentProvider(DirectionType direction, String rootTypeName)
   {
      super(direction);
      this.rootTypeName = rootTypeName;
      
   }


   public String getRootTypeName()
   {
      return rootTypeName;
   }

   @Override
   public Object[] getChildren(Object parentElement)
   {
     return getChildren(parentElement, false, true);
   }
   
   private String getFullQualifiedName(DataTypeType dataType, String type, String className) {
	      IJavaProject jp = JavaCore.create(ModelUtils.getProjectFromEObject(dataType));
	      MTAClassLoader cl = new MTAClassLoader(jp);
	      Class clazz;
		try {
			clazz = cl.loadClass(className);
			if (clazz == null) {
				return null;
			}
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (type.equalsIgnoreCase(field.getType().getSimpleName())) {
					return field.getType().getCanonicalName();
				}
			}
		} catch (Throwable t) {
			return null;
			//t.printStackTrace();
		} 
		return null;
	}
}
