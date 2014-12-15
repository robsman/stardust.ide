package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons.createAttributeType;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.ProcessAttributes.ATTACHMENTS_UNIQUE_PER_ROOT;

import java.util.List;

import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.BpmnDefaultContentsUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType;
import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo;
import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo.DataInfo;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;

/**
 * @author Simon Nikles
 *
 */
public class StardustProcessExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustProcessType> {

	public StardustProcessExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustProcessType sdProcess) {
		super(adapterFactory, sdProcess);

		EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getStardustProcessType_SupportsProcessAttachments();

		setFeatureDescriptor(feature,
				new FeatureDescriptor<StardustProcessType>(this, sdProcess, feature) {
			@Override
			protected void internalSet(StardustProcessType sdProcess, EStructuralFeature feature, Object value, int index) {
				if (null == value) value = Boolean.FALSE;
				Object oldValue = object.eGet(feature);
				super.internalSet(object, feature, value, index);
				try {
					Boolean supportsAttachments = (Boolean)value;
					if (supportsAttachments) {
						ensureAttachmentVariableExists(sdProcess);
					} else {
						removeAttachmentVariable(sdProcess);
					}
				} catch (Exception e) {}
			}
		});
	}

	private void removeAttachmentVariable(StardustProcessType sdProcess) {
		Process bpmnProcess = (Process)sdProcess.eContainer().eContainer();
		DataInfo processAttachmentDataInfo = PredefinedDataInfo.getProcessAttachmentDataInfo();
		List<Property> properties = bpmnProcess.getProperties();
		Property toRemove = null;
		for (Property prop : properties) {
			String propId = ExtensionHelper2.INSTANCE.getStardustPropertyId(prop);
			if (null != propId && propId.equals(processAttachmentDataInfo.id)) {
				toRemove = prop;
			}
		}
		if (null != toRemove) {
			bpmnProcess.getProperties().remove(toRemove);
		}
	}

	private void ensureAttachmentVariableExists(StardustProcessType sdProcess) {
		Process bpmnProcess = (Process)sdProcess.eContainer().eContainer();
		DataInfo processAttachmentDataInfo = PredefinedDataInfo.getProcessAttachmentDataInfo();
		List<Property> properties = bpmnProcess.getProperties();
		Property existingVar = null;
		for (Property prop : properties) {
			String propId = ExtensionHelper2.INSTANCE.getStardustPropertyId(prop);
			if (null != propId && propId.equals(processAttachmentDataInfo.id)) {
				existingVar = prop;
				break;
			}
		}
		if (null == existingVar) {
			BpmnDefaultContentsUtil.addProcessAttachmentVariable(bpmnProcess, processAttachmentDataInfo);
		}
	}

}
