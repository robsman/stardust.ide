/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
/**
 * @author Gregor Gisler
 *
 */
public class CompositeUtil {

	public List<IMethod> getAllMethodsAndConstructors(IType type, boolean constructors) {
		List<IMethod> methods = new ArrayList<IMethod>();
		if (type!=null) {
            try {
				for (IMethod method : type.getMethods()) {
					if (method.getElementName().contains("<")) { //$NON-NLS-1$
						continue;
					}
					if ((method.getFlags() & Flags.AccPublic) == 0) {
						continue;
					}
					if (constructors && method.isConstructor()) {
						// Create List of Constructors
						methods.add(method);
					}
					else if (!constructors && !method.isConstructor()) {
						// Create List of Operations
						methods.add(method);
					}
					else continue;
				}
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return methods;
	}

}
