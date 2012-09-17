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
package org.eclipse.stardust.model.bpmn2.transform.util;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.emf.common.util.URI;
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

    public static ResourceRole resolveRoleProxy(ResourceRole role, FlowElementsContainer container) {
        URI proxyURI = ((InternalEObject) role).eProxyURI();
        if (container.eResource() != null) {
            org.eclipse.emf.ecore.resource.Resource eRes = container.eResource();
            role = (ResourceRole)eRes.getEObject(proxyURI.fragment());
        }
        return role;
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
