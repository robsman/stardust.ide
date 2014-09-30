package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;

/**
 * @author Simon Nikles
 *
 */
public class InteractiveApplicationRefFeatureDescriptor extends FeatureDescriptor<StardustUserTaskType> {

	public InteractiveApplicationRefFeatureDescriptor(
			ExtendedPropertiesAdapter<StardustUserTaskType> owner,
			StardustUserTaskType object, EStructuralFeature feature) {
		super(owner, object, feature);
	}

	@Override
	public String getChoiceString(Object value) {
		if (!(value instanceof Interface)) return "";
		Interface intf = (Interface)value;
		return intf.getName();
	}

	@Override
	public Hashtable<String, Object> getChoiceOfValues() {
		Hashtable<String,Object> choices = new Hashtable<String, Object>();
		Definitions definitions = ModelUtil.getDefinitions(object);
		List<Interface> interfaces = ModelUtil.getAllRootElements(definitions, Interface.class);
	
		for (Interface iface : interfaces) {
			List<Object> values = ModelDecorator.getAllExtensionAttributeValues(iface, SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustInterface());
			StardustApplicationType sdApp = null;
			if (null != values && values.size() > 0) {
				sdApp = ((StardustInterfaceType)values.get(0)).getStardustApplication();
				if (null != sdApp) choices.put(sdApp.getName(), sdApp.getId());	
			}
			
		}

		return choices;
	}

}
