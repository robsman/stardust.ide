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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.core.spi.dataTypes.struct.StructAccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;

import com.infinity.bpm.messaging.model.mapping.FieldMapping;
import com.infinity.bpm.messaging.model.mapping.MappingFactory;



public class MultipleAccessPathBrowserContentProvider extends MessagingAccessPathBrowserContentProvider
{
   Map fieldMappings;
   Map fieldPathsToAccessPoints;
   MessageTransformationController controller;
   Map accessPointCache = new HashMap();
   private MessagingAccessPathBrowserContentProvider apc;
  
   public MultipleAccessPathBrowserContentProvider(DirectionType direction, MessageTransformationController controller)
   {
      super(direction);
      this.controller = controller;      
      this.fieldMappings = controller.getFieldMappings();
      this.fieldPathsToAccessPoints = controller.getFieldPathsToAccessPoints();
   }

   public Object[] getChildren(Object parentElement, boolean caching)
   {
      if (parentElement instanceof List) {
         List list = (List)parentElement;
         return list.toArray();
      } else {
    	 String rootElement = AttributeUtil.getAttributeValue((IExtensibleElement) parentElement, "RootElement");  //$NON-NLS-1$
    	 setApc((MessagingAccessPathBrowserContentProvider) accessPointCache.get(rootElement)); 
         Object[] children = getApc().getChildren(parentElement, caching, true);
         for (int i = 0; i < children.length; i++) {
            AccessPointType element = (AccessPointType)children[i];
            setupAP(parentElement, element, false);               
         }
         return children;     
      }
   }
   
   //rainer:Fix for CRNT-17926
   public Object[] getChildrenWithoutSetup(Object parentElement, boolean caching)
   {
      if (parentElement instanceof List) {
         List list = (List)parentElement;
         return list.toArray();
      } else {
         String rootElement = AttributeUtil.getAttributeValue((IExtensibleElement) parentElement, "RootElement");  //$NON-NLS-1$
         setApc((MessagingAccessPathBrowserContentProvider) accessPointCache.get(rootElement)); 
         Object[] children = getApc().getChildren(parentElement, caching, true);
         return children;     
      }
   }

   @Override
   public Object[] getElements(Object inputElement)
   {
     if (inputElement instanceof List) {
         List list = (List)inputElement;
         for (Iterator i = list.iterator(); i.hasNext();) {
            AccessPointType type = (AccessPointType)i.next();
            setupAP(null, type, true);
         }
         return list.toArray();
     }
     StructAccessPointType type = (StructAccessPointType)inputElement;
     setupAP(null, type, false);
     Object[] elements = getApc().getElements(inputElement);
     for (int i = 0; i < elements.length; i++) {
        StructAccessPointType element = (StructAccessPointType)elements[i];        
        setupAP(inputElement, element, false);
     }
     return elements;
   }

   @Override
   public boolean hasChildren(Object element)
   {
      if (element instanceof List) {
         List list = (List)element;
         return list.isEmpty();
      } else {    	  
         if (getApc(element) != null) {
            return getApc(element).hasChildren(element);            
         } 
         return false;
      }
   }

   private MessagingAccessPathBrowserContentProvider getApc(Object element) {
	   String rootTypeName = AttributeUtil.getAttributeValue((IExtensibleElement) element, "RootElement").toString(); //$NON-NLS-1$
	   return (MessagingAccessPathBrowserContentProvider) this.accessPointCache.get(rootTypeName);
	
}

private void setupAP(Object parentElement, AccessPointType type, boolean isRootType)
   {
      String fullXPath = ""; //$NON-NLS-1$
	  //Move this up !?
	  if (isRootType) {
    	  setApc((MessagingAccessPathBrowserContentProvider) accessPointCache.get(type.getId()));
      }	
      /*if (!controller.isSimpleMode() && AttributeUtil.getAttribute(type, "RootElement") == null) {
         AttributeUtil.setAttribute(type, "RootElement", getApc().getRootTypeName());
      }*/
      if (AttributeUtil.getAttribute(type, "RootElement") == null) { //$NON-NLS-1$
      		AttributeUtil.setAttribute(type, "RootElement", getApc().getRootTypeName()); //$NON-NLS-1$
   	  }

      String realXPath = null;
      if (type instanceof StructAccessPointType) {
         StructAccessPointType sapt = (StructAccessPointType)type;
         realXPath = sapt.getXPath().toString();
         fullXPath = getApc().getRootTypeName() + "/" + realXPath; //$NON-NLS-1$
      } else {
    	 if (controller.isSerializable(type)) {
    		 realXPath = ""; //$NON-NLS-1$
    		 if (parentElement != null) {
    			 fullXPath = AttributeUtil.getAttributeValue((IExtensibleElement) parentElement, "FullXPath"); //$NON-NLS-1$
    			 fullXPath = fullXPath + "/" + type.getId();    			 	 //$NON-NLS-1$
    		 } else {
    			fullXPath = type.getId(); 
    		 }
    	 } else {
             realXPath = ""; //$NON-NLS-1$
             fullXPath = getApc().getRootTypeName() + "/" + realXPath; //$NON-NLS-1$
    	 }
      }            
      fullXPath = fullXPath.replaceAll("@", ""); //$NON-NLS-1$ //$NON-NLS-2$
      if (!controller.isSimpleMode() && AttributeUtil.getAttribute(type, "FullXPath") == null) { //$NON-NLS-1$
         AttributeUtil.setAttribute(type, "FullXPath", fullXPath); //$NON-NLS-1$
      }      
      FieldMapping fieldMapping = MappingFactory.eINSTANCE.createFieldMapping();
      fieldMapping.setFieldPath(fullXPath);
      fieldMapping.setMappingExpression(""); //$NON-NLS-1$
      fieldMapping.setAdvancedMapping(false);
      fieldPathsToAccessPoints.put(fullXPath, type);
      if (fieldMappings.get(fullXPath) == null) {
         fieldMappings.put(fullXPath, fieldMapping);   
      }
   }


   @Override
   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      if (newInput == null) {
         return;
      }
      List list = (List)newInput;
      for (Iterator i = list.iterator(); i.hasNext();) {
         AccessPointType ap = (AccessPointType)i.next();
         setApc(new MessagingAccessPathBrowserContentProvider(DirectionType.INOUT_LITERAL, ap.getId()));
         accessPointCache.put(ap.getId(), getApc());
         getApc().inputChanged(viewer, oldInput, ap);
      }
   }


   @Override
   public void dispose()
   {
      try {
         super.dispose();
      } catch (Throwable t) {
         
      }
   }

   @Override
   public Object[] getChildren(Object parentElement)
   {
      return getChildren(parentElement, false);
   }

void setApc(MessagingAccessPathBrowserContentProvider apc) {
	this.apc = apc;
}

MessagingAccessPathBrowserContentProvider getApc() {	
	return apc;
}

public AccessPointType getChild(AccessPointType parent, String name) {
	Object[] children = this.getChildren(parent);
	for (int i = 0; i < children.length; i++) {
		AccessPointType apt = (AccessPointType) children[i];
		if (apt.getName().equalsIgnoreCase(name)) {
			return apt;
		}
	}
	return null;
}

}
