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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.filtering;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;



public abstract class AbstractMessageFilter extends ViewerFilter {

	protected MessageTransformationController controller;
	protected boolean active = false;

	public AbstractMessageFilter(MessageTransformationController controller) {
		super();
		this.controller = controller;				
	}
	
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		 try {
	         if (isActive()) {
	            AccessPointType messageType = (AccessPointType)element;
	            Object[] children = new Object[]{};           
	            if (controller.isSourceField(messageType)) {
	               if (controller.getSourceAPB().hasChildren(messageType)) {
	                   children = controller.getSourceAPB().getChildren(messageType, true);	                  
	               }	               
	            } else {
	               if (controller.getTargetAPB().hasChildren(messageType)) {
	                   children = controller.getTargetAPB().getChildren(messageType, true);	                  
	               }	               
	            }                        
	            if (children.length > 0) {
	                Object[] filteredChildren = filter(viewer, messageType, children);
	                if (filteredChildren.length > 0 || matches(messageType)) {	                    
	                    return true;
	                } else {
	                    return controller.isRoot(messageType);
	                }
	            } else {	                	               
	                return matches(messageType);    
	            }                                                                                                                                               
	        }   
	        return true;		    
		 } catch (Throwable t) {
		    t.printStackTrace();
		    return false;
		 }
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	protected boolean matches(AccessPointType messageType) {
		return true;
	}


		
}
