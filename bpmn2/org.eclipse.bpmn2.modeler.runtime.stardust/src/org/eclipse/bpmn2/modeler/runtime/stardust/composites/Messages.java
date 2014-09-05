package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.bpmn2.modeler.runtime.stardust.composites.messages";

	public static String StardustDataAssignmentDetailComposite_From_Title;
	public static String StardustDataAssignmentDetailComposite_To_Title;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}

}
