/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
/**
 *
 */
package org.eclipse.stardust.model.xpdl.builder.compatibility.iam;

import org.eclipse.stardust.common.error.PublicException;

/**
 * @author Ammar.Hassan
 *
 */
public class DuplicateIdException extends PublicException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param arg0
	 */
	public DuplicateIdException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public DuplicateIdException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DuplicateIdException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
