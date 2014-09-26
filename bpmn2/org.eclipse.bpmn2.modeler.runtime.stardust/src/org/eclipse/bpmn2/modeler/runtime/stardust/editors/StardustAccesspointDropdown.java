package org.eclipse.bpmn2.modeler.runtime.stardust.editors;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;

/**
 * @author Simon Nikles
 *
 */
public class StardustAccesspointDropdown extends ComboObjectEditor {

	private StardustApplicationType accesspointOwner;
	private DirectionType direction; 
	
	public interface AccesspointSelectionListener {
		public void comboChanged();
	}
	
	public StardustAccesspointDropdown(AbstractDetailComposite parent, AttributeType object, StardustApplicationType accesspointOwner, DirectionType direction) {
		super(parent, object, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value());
		this.accesspointOwner = accesspointOwner;
		this.direction = direction;
	}
	
	protected boolean canSetNull() {
		return true;
	}

	@Override
	protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature){
		if (null != choices) return choices;
		Hashtable<String, Object> apMap = new Hashtable<String, Object>();
		for (StardustAccessPointType ap : accesspointOwner.getAccessPoint1()) {
			if (null != direction && direction.equals(ap.getDirection())) {
				apMap.put(ap.getName(), ap.getId());
			}
		}
		choices = apMap;
		return choices;
	}

	public void forceReload() {
		choices = null;
	}

}
