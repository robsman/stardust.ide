package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MappedStardustDatatypeDropdown;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.MappedStardustDatatypeDropdown.DatatypeChangeListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class SerializableTypeAccessPointSubComposite extends DefaultDetailComposite implements DatatypeChangeListener, AccessPointChangeListener {
	
	private AccessPointChangeListener listener;
	
	public SerializableTypeAccessPointSubComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public SerializableTypeAccessPointSubComposite(Composite parent, int style, AccessPointChangeListener listener) {
		super(parent, SWT.NONE);
		this.listener = listener;
	}

	@Override
	public void createBindings(EObject be) {
		StardustAccessPointType apType = (StardustAccessPointType)be;
		Composite parent = this.getAttributesParent();
		System.out
				.println("CamelSerializableTypeAccessPointSubComposite.createBindings()");
		
		AttributeType at = PropertyAdapterCommons.findAttributeType(apType, "carnot:engine:className");
		ObjectEditor editor = new MappedStardustDatatypeDropdown(this, at, AcessPointDataTypes.SERIALIZABLE_TYPE, this);
		editor.createControl(parent, "Serializable");
		
//		private void createSerializableTypeModel(StardustAccessPointType sdAccessPoint) {
//			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:className", "", null));
//			sdAccessPoint.getAttribute().add(PropertyAdapterCommons.createAttributeType("RootElement", ""+sdAccessPoint.getId(), null));
//		}
//

		setTitle(AcessPointDataTypes.SERIALIZABLE_TYPE.getDisplayName());
		attributesSection.setExpanded(true);
		attributesSection.setTouchEnabled(false);
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
