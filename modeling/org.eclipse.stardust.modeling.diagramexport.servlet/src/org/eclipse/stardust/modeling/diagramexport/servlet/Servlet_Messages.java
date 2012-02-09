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
package org.eclipse.stardust.modeling.diagramexport.servlet;

import org.eclipse.osgi.util.NLS;

public class Servlet_Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.diagramexport.servlet.servlet-messages"; //$NON-NLS-1$
	public static String DiagramServlet_EXC_EXACT_ONE_OF_THESE_PARAMETERS_IS_MANDATORY_FOR_DIAGRAM_RETRIVAL_NULL_ONE_TWO;
	public static String EXC_BIGGER_THAN_0;
	public static String EXC_COULD_NOT_FIND_MODEL_FOR_OID;
	public static String EXC_COULD_NOT_FIND_PROCESS_INSTANCE_FOR_OID;
	public static String EXC_FAILED_RENDERING_DIAGRAM;
	public static String EXC_MODEL_NULL_CANNOT_BE_LOADED;
	public static String EXC_PATH_NULL_FOR_MODELSOURCE_DOES_NOT_EXIST;
	public static String EXC_THE_PARAMETER_NULL_MUST_BE_NUMERIC_VALUE;
	public static String EXC_THE_PARAMETER_NULL_MUST_NOT_BE_EMPTY;
	public static String LOG_EXECUTING_RENDERJOB_FOR_DIAGRAM;
	public static String LOG_SHUTTING_DOWN;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Servlet_Messages.class);
	}

	private Servlet_Messages() {
	}
}
