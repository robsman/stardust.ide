package org.eclipse.bpmn2.modeler.runtime.stardust.composites.camel.accesspoint;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.camel.AccessPointChangeListener;
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
public class CamelPrimitiveTypeAccessPointSubComposite extends DefaultDetailComposite implements DatatypeChangeListener, AccessPointChangeListener {

	private AccessPointChangeListener listener;
	
	public CamelPrimitiveTypeAccessPointSubComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public CamelPrimitiveTypeAccessPointSubComposite(Composite parent, int style, AccessPointChangeListener listener) {
		super(parent, style);
		this.listener = listener;
	}

	@Override
	public void createBindings(EObject be) {
		StardustAccessPointType apType = (StardustAccessPointType)be;
		Composite parent = this.getAttributesParent();
		
		AttributeType at = PropertyAdapterCommons.findAttributeType(apType, "carnot:engine:type");
		ObjectEditor editor = new MappedStardustDatatypeDropdown(this, at, CamelAcessPointDataTypes.PRIMITIVE_TYPE, this);
		editor.createControl(parent, "Type");

		//	sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:type", "", "org.eclipse.stardust.engine.core.pojo.data.Type"));
		setTitle(CamelAcessPointDataTypes.PRIMITIVE_TYPE.getDisplayName());
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
