package ag.carnot.bpm.modeling.repository;

import org.eclipse.osgi.util.NLS;

public class Repository_Messages extends NLS {
	private static final String BUNDLE_NAME = "ag.carnot.bpm.modeling.repository.repository-messages"; //$NON-NLS-1$
	public static String BUT_REPLACE_EXISTING_ELEMENT_WITH_NEW_ONE;
	public static String BUT_REUSE_ELEMENT_IN_THE_MD;
	public static String BUT_USE_OPTION_FOR_ALL_DUPLICATE_ELEMENTS;
	public static String DIA_ERROR;
	public static String DIA_ERROR_OPENING_CONNECTION;
	public static String DIA_LOGIN;
	public static String LBL_CONTAINER;
	public static String LBL_FEATURE;
	public static String LBL_ID;
	public static String LBL_NAME;
	public static String LBL_NAME_KLEIN_GESCHRIEBEN;
	public static String LBL_NULL;
	public static String LBL_REQUIRES_THE_ELEMENTS_LISTED_BELOW;
	public static String MSG_FORMAT_CONNECTION_NULL_DOES_NOT_EXIST;
	public static String MSG_FORMAT_CONNECTION_NULL_IS_CLOSED;
	public static String MSG_FORMAT_NO_HANDLER_FOUND_FOR_CONNECTION_TYPE_NULL;
	public static String TXT_CONFLICT;
	public static String TXT_ERROR;
	public static String TXT_INVALID_CONTAINING_FEATURE;
	public static String EXC_IS_NOT_VALID_CLASSIFIER;
	public static String TXT_NAME;
	public static String TXT_REQUIRED_ELEMENTS;
	public static String TXT_TYPELESS;
	public static String TXT_VALUE;
	public static String LBL_ANOTHER_TYPE_WITH_ID_ALREADY_EXISTS_IN_MODEL;
	public static String TXT_INVISIBLE;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME,
				Repository_Messages.class);
	}

	private Repository_Messages() {
	}
}
