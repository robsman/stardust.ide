package org.eclipse.stardust.model.bpmn2.extension;

import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.stardust.model.bpmn2.extension.utils.Bpmn2ExtensionUtils;
import org.eclipse.stardust.model.bpmn2.extension.utils.JsonMarshaller;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectOrStoreExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelParticipantExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustScriptTaskExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskExt;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;

import com.google.gson.JsonObject;

public class ExtensionHelper2 {

    private static final String ATT_APPLICATION_ACCESS_POINT = "applicationAccessPointRef";
    private static final String ATT_PARAM_MAPPING_OID = "parameterMappingOid";

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

	public <T> T getCoreExtension(Class<T> cls, BaseElement element) {
		return jsonIo.gson().fromJson(getCoreExtension(element), cls);
	}

	public StardustInterfaceExt getApplicationExtension(RootElement element) {
		JsonObject json = Bpmn2ExtensionUtils.getExtensionAsJson(element, "StardustInterface");

		return jsonIo.gson().fromJson(json, StardustInterfaceExt.class);
	}

	public StardustUserTaskExt getUserTaskExtension(UserTask task) {
		JsonObject json = Bpmn2ExtensionUtils.getExtensionAsJson(task, "core");

		return jsonIo.gson().fromJson(json, StardustUserTaskExt.class);
	}

	public StardustUserTaskExt getGlobalUserTaskExtension(GlobalUserTask task) {
		JsonObject json = Bpmn2ExtensionUtils.getExtensionAsJson(task, "core");

		return jsonIo.gson().fromJson(json, StardustUserTaskExt.class);
	}

    public String getAssignmentAccessPointRef(Expression assignment) {
        return Bpmn2ExtensionUtils.getExtensionAttribute(assignment, ATT_APPLICATION_ACCESS_POINT);
    }

    public long getAssignmentParameterMappingOid(Assignment assignment) {
    	return Long.parseLong(Bpmn2ExtensionUtils.getExtensionAttribute(assignment, ATT_PARAM_MAPPING_OID));
    }

	public StardustDataObjectOrStoreExt getDataExtension(RootElement element) {
		JsonObject json = Bpmn2ExtensionUtils.getExtensionAsJson(element, "StardustDataObjectOrStore");

		return jsonIo.gson().fromJson(json, StardustDataObjectOrStoreExt.class);
	}

	public StardustModelParticipantExt getModelParticipantExtension(Resource resource) {
		return jsonIo.gson().fromJson(getCoreExtension(resource), StardustModelParticipantExt.class);
	}

	public StardustDataObjectOrStoreExt getDataStoreExtension(DataStore store) {
		return jsonIo.gson().fromJson(getCoreExtension(store), StardustDataObjectOrStoreExt.class);
	}

	private JsonObject getCoreExtension(BaseElement element) {
		return Bpmn2ExtensionUtils.getExtensionAsJson(element, "core");
	}

	public StardustScriptTaskExt getScriptTaskExtension(ScriptTask task) {
		JsonObject json = Bpmn2ExtensionUtils.getExtensionAsJson(task, "core");
		return jsonIo.gson().fromJson(json, StardustScriptTaskExt.class);
	}

	public XSDSchema getEmbeddedSchemaExtension(ItemDefinition itemdef) {
		String featureName = XSDPackage.Literals.XSD_CONCRETE_COMPONENT__SCHEMA.getName();
		String namespace = XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001;

		for (ExtensionAttributeValue extensionAttributeValue : itemdef.getExtensionValues()) {
			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry e : extensionElements) {
				EStructuralFeature feature = e.getEStructuralFeature();
				if (null != feature
						&& namespace.equals(ExtendedMetaData.INSTANCE.getNamespace(feature))
						&& featureName.equals(feature.getName())) {
					if (e.getValue() instanceof XSDSchema) {
						return (XSDSchema)e.getValue();
					}
				}

			}
		}
		return null;
	}

}
