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
package org.eclipse.stardust.modeling.repository.file;

import org.eclipse.osgi.util.NLS;

public class File_Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.repository.file.file-messages"; //$NON-NLS-1$
	public static String BOX_IMPORT_BY_REFERENCE;
	public static String BUT_BW;
	public static String BUT_TXT_SEARCH;
	public static String EXC_ALREADY_CLOSED;
	public static String EXC_ALREADY_OPEN;
	public static String EXC_MISSING_FILENAME;
	public static String EXC_UNABLE_TO_LOAD_MD;
	public static String LBL_FIND;
	public static String LBL_ID;
	public static String LBL_TXT;
	public static String LBL_TXT_NAME;
	public static String MSG_CIRCULAR_REFERENCES_BETWEEN_MD_FILES_ARE_NOT_ALLOWED;
	public static String MSG_IS_DIRECTLY_OR_INDIRECTLY_REFERENCED_BY_MD;
	public static String MSG_PLEASE_PROVIDE_A_VALID_FILENAME;
	public static String TXT_CIRCULAR_DEPENDENCY_DETECTED;
	public static String TXT_SEARCH;
	public static String TXT_WR;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, File_Messages.class);
	}

	private File_Messages() {
	}
}
