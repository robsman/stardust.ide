package org.eclipse.stardust.model.bpmn2.extension;

import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.UserTask;

import com.google.gson.JsonObject;

import org.eclipse.stardust.model.bpmn2.extension.utils.Bpmn2ExtensionUtils;
import org.eclipse.stardust.model.bpmn2.extension.utils.JsonMarshaller;
import org.eclipse.stardust.model.bpmn2.sdbpmn2.StardustInterfaceExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn2.StardustUserTaskExt;

public class ExtensionHelper2 {

    private static final String ATT_APPLICATION_ACCESS_POINT = "applicationAccessPointRef";

    private static ExtensionHelper2 instance = null;

	private JsonMarshaller jsonIo = new JsonMarshaller();

	private ExtensionHelper2() {
	}

	public static ExtensionHelper2 getInstance() {
		if (instance == null) {
			instance = new ExtensionHelper2();
		}
		return instance;
	}

	public StardustInterfaceExt getApplicationExtension(RootElement element) {
		JsonObject json = Bpmn2ExtensionUtils.getExtensionAsJson(element, "StardustInterface");

		return jsonIo.gson().fromJson(json, StardustInterfaceExt.class);
	}

	public StardustUserTaskExt getUserTaskExtension(UserTask task) {
		JsonObject json = Bpmn2ExtensionUtils.getExtensionAsJson(task, "core");

		return jsonIo.gson().fromJson(json, StardustUserTaskExt.class);
	}

    public String getAssignmentAccessPointRef(Expression assignment) {
        return Bpmn2ExtensionUtils.getExtensionAttribute(assignment, ATT_APPLICATION_ACCESS_POINT);
    }

}
