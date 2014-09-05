package org.eclipse.bpmn2.modeler.runtime.stardust.dialogs;

import org.eclipse.osgi.util.NLS;

/**
 * @author Simon Nikles
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.bpmn2.modeler.runtime.stardust.dialogs.messages"; 
	
	public static String StardustDataMappingDialog_Title;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}

}
