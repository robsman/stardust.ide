package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MappedStardustDatatypeDropdown;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MappedStardustDatatypeDropdown.DatatypeChangeListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StructuredTypeAccessPointSubComposite extends AbstractDetailComposite implements DatatypeChangeListener, AccessPointChangeListener {

	private AccessPointChangeListener listener;
	
	public StructuredTypeAccessPointSubComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StructuredTypeAccessPointSubComposite(Composite parent, int style, AccessPointChangeListener listener) {
		super(parent, style);
		this.listener = listener;
	}

	@Override
	public void createBindings(EObject be) {
		StardustAccessPointType apType = (StardustAccessPointType)be;
		Composite parent = this.getAttributesParent();
		System.out
				.println("CamelStructuredTypeAccessPointSubComposite.createBindings()");

		AttributeType at = PropertyAdapterCommons.findAttributeType(apType, "carnot:engine:dataType");
		ObjectEditor editor = new MappedStardustDatatypeDropdown(this, at, AcessPointDataTypes.STRUCT_TYPE, this);
		editor.createControl(parent, "Datastructure");

//			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:dataType", "", null));
//			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:path:separator", "/", null));
//			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:data:bidirectional", "true", "boolean"));		
//			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));

		setTitle(AcessPointDataTypes.STRUCT_TYPE.getDisplayName());
	}

	@Override
	public void comboChanged() {
		accessPointsChanged();
	}

	@Override
	public void accessPointsChanged() {
		if (null != listener) listener.accessPointsChanged();
	}

}
