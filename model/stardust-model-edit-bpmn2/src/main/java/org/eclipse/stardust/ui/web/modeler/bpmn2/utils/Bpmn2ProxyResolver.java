/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.ui.web.modeler.bpmn2.utils;

import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * @author Simon Nikles
 *
 */
public class Bpmn2ProxyResolver {

    public static Resource resolveResourceProxy(Resource resource, FlowElementsContainer container) {
        URI proxyURI = ((InternalEObject) resource).eProxyURI();
        if (container.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = container.eResource();
            resource = (Resource)eRes.getEObject(proxyURI.fragment());
        }
        return resource;
    }

	public static Resource resolveResourceProxy(Resource resource, Definitions defs) {
        URI proxyURI = ((InternalEObject) resource).eProxyURI();
        if (defs.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = defs.eResource();
            resource = (Resource)eRes.getEObject(proxyURI.fragment());
        }
        return resource;
	}

	public static DataStore resolveDataStoreProxy(DataStore element, Definitions defs) {
        URI proxyURI = ((InternalEObject) element).eProxyURI();
        if (defs.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = defs.eResource();
            element = (DataStore)eRes.getEObject(proxyURI.fragment());
        }
        return element;
	}

    public static ResourceRole resolveRoleProxy(ResourceRole role, FlowElementsContainer container) {
        URI proxyURI = ((InternalEObject) role).eProxyURI();
        if (container.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = container.eResource();
            role = (ResourceRole)eRes.getEObject(proxyURI.fragment());
        }
        return role;
    }

    public static ResourceRole resolveRoleProxy(ResourceRole role, Definitions defs) {
        URI proxyURI = ((InternalEObject) role).eProxyURI();
        if (defs.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = defs.eResource();
            role = (ResourceRole)eRes.getEObject(proxyURI.fragment());
        }
        return role;
    }

//    public static Operation resolveOperationProxy(Operation operation, FlowElementsContainer container) {
//        URI proxyURI = ((InternalEObject) operation).eProxyURI();
//        if (container.eResource() != null) {
//            org.eclipse.emf.ecore.resource.Resource eRes = container.eResource();
//            operation = (Operation)eRes.getEObject(proxyURI.fragment());
//        }
//        return operation;
//    }

    public static Operation resolveOperationProxy(Operation operation, EObject container) {
        URI proxyURI = ((InternalEObject) operation).eProxyURI();
        if (container.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = container.eResource();
            operation = (Operation)eRes.getEObject(proxyURI.fragment());
        }
        return operation;
    }

	public static ItemDefinition resolveItemDefinition(ItemDefinition itemDef, Definitions definitions) {
        URI proxyURI = ((InternalEObject) itemDef).eProxyURI();
        if (definitions.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = definitions.eResource();
            itemDef = (ItemDefinition)eRes.getEObject(proxyURI.fragment());
        }
        return itemDef;
	}

	public static org.eclipse.bpmn2.Error resolveError(org.eclipse.bpmn2.Error error, Definitions definitions) {
		if (!error.eIsProxy()) return error;
        URI proxyURI = ((InternalEObject) error).eProxyURI();
        if (definitions.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = definitions.eResource();
            error = (org.eclipse.bpmn2.Error)eRes.getEObject(proxyURI.fragment());
        }
        return error;
	}

	public static CallableElement resolveCallableElementProxy(CallableElement callable, Definitions definitions) {
		if (!callable.eIsProxy()) return callable;
        URI proxyURI = ((InternalEObject) callable).eProxyURI();
        if (definitions.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = definitions.eResource();
            callable = (CallableElement)eRes.getEObject(proxyURI.fragment());
        }
        return callable;
	}

//	public static AnyType resolveAnyTypeProxy(AnyType type, RootElement container) {
//		URI proxyURI = ((InternalEObject) type).eProxyURI();
//		if (container.eResource() != null) {
//			org.eclipse.emf.ecore.resource.Resource eRes = container.eResource();
//			type = (AnyType)eRes.getEObject(proxyURI.fragment());
//		}
//		return type;
//	}


}
