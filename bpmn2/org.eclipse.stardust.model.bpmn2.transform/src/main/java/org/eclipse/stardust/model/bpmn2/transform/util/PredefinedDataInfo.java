package org.eclipse.stardust.model.bpmn2.transform.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants;

/**
 * @author Simon Nikles
 *
 */
public class PredefinedDataInfo {

	public static final String VAR_START_EVENT_ID = "StartEventId";
	public static final String LBL_START_EVENT_ID = "Start Event Id";

	public static List<String> getTypeClasses() {
		return Arrays.asList(
				"org.eclipse.stardust.engine.core.runtime.beans.IUser",
				"org.eclipse.stardust.engine.api.runtime.DeployedModelDescription",
				"org.eclipse.stardust.engine.api.runtime.Document",
				"org.eclipse.stardust.engine.api.runtime.Folder",
				"java.util.List");
	}

	public List<DataInfo> getPredefinedData() {
		List<DataInfo> dataList = new ArrayList<PredefinedDataInfo.DataInfo>();
		dataList.add(new DataInfo(PredefinedConstants.PROCESS_ID, "Process OID", "integer"));
		dataList.add(new DataInfo(PredefinedConstants.ROOT_PROCESS_ID, "Root Process OID", "integer"));
		dataList.add(new DataInfo(PredefinedConstants.PROCESS_PRIORITY, "Process Priority", "integer"));
		dataList.add(new DataInfo(PredefinedConstants.CURRENT_LOCALE, "Current Locale", "string"));
		dataList.add(new DataInfo(PredefinedConstants.CURRENT_DATE, "Current Date", "date"));
		dataList.add(new DataInfo(PredefinedConstants.STARTING_USER, "Starting User", "org.eclipse.stardust.engine.core.runtime.beans.IUser"));
		dataList.add(new DataInfo(PredefinedConstants.CURRENT_USER, "Current User", "org.eclipse.stardust.engine.core.runtime.beans.IUser"));
		dataList.add(new DataInfo(PredefinedConstants.LAST_ACTIVITY_PERFORMER, "Last activity performer", "org.eclipse.stardust.engine.core.runtime.beans.IUser"));
		dataList.add(new DataInfo(PredefinedConstants.CURRENT_MODEL, "Current Model", "org.eclipse.stardust.engine.api.runtime.DeployedModelDescription"));
		dataList.add(new DataInfo(VAR_START_EVENT_ID, LBL_START_EVENT_ID, "string"));

		return dataList;
	}

	public static DataInfo getProcessAttachmentDataInfo() {
		return new DataInfo(DmsConstants.DATA_ID_ATTACHMENTS, "Process Attachments", "java.util.List");
	}

	public static class DataInfo {
		public String type;
		public String name;
		public String id;
		public DataInfo(String id, String name, String type) {
			this.id = id;
			this.name = name;
			this.type = type;
		}
	}
}
