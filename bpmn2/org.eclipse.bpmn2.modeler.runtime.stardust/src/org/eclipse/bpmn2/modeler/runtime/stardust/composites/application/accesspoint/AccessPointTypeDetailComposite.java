package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.IntObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class AccessPointTypeDetailComposite extends DefaultDetailComposite implements AccessPointChangeListener {

	private AccessPointChangeListener listener;
	
	public AccessPointTypeDetailComposite(Composite parent, AccessPointChangeListener listener) {
		super(parent, SWT.NONE);
		this.listener = listener;
	}
	
	protected Hashtable<String, Object> getDatatypeComboChoice() {
		Hashtable<String, Object> choices = new Hashtable<String, Object>();
		for (AcessPointDataTypes type : AcessPointDataTypes.values()) {
			choices.put(type.getDisplayName(), type.getKey());
		}
		return choices;
	}
	
	@Override
	public void createBindings(EObject be) {
		final StardustAccessPointType accessPoint = (StardustAccessPointType) be;
		Composite parent = getAttributesParent();
		ObjectEditor editor;

		editor = new IntObjectEditor(this, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id());
		editor.createControl(parent, "Element ID");
		editor.setEditable(false);
		
		editor = new TextObjectEditor(this, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
		editor.createControl(parent, "Name");
		editor.setEditable(true);
		
		ComboObjectEditor objectEditor= new ComboObjectEditor(this, accessPoint, SdbpmnPackage.eINSTANCE.getStardustAccessPointType_TypeRef()) {

			@Override
			protected boolean setValue(final Object newValue) {
				final Object oldValue = getValue();
				if (super.setValue(newValue)) {
					if (oldValue != newValue) {
						setBusinessObject(accessPoint);
						accessPointsChanged();
					}
					return true;
				}
				return false;
			}
			
			@Override
			protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature){
				if (choices==null) {
					choices = getDatatypeComboChoice();
				}
				return choices;
			}
		
		};

		objectEditor.createControl(this, "AccessPoint Data Type");

		AbstractDetailComposite subComposite = null;
		String apTypeStr = accessPoint.getTypeRef();
		AcessPointDataTypes typeCategory = AcessPointDataTypes.forKey(apTypeStr);
		if (null == typeCategory) return;
		
		switch(typeCategory) {
		case PRIMITIVE_TYPE:
			subComposite = new PrimitiveTypeAccessPointSubComposite(getAttributesParent(), SWT.NONE, this);
			break;
		case SERIALIZABLE_TYPE:
			subComposite = new SerializableTypeAccessPointSubComposite(getAttributesParent(), SWT.NONE, this);
			break;
		case STRUCT_TYPE:
			subComposite = new StructuredTypeAccessPointSubComposite(getAttributesParent(), SWT.NONE, this);
			break;
		default:
			break;
		}
		
		// rebuild the service-specific details section
		if (subComposite != null)
			subComposite.setBusinessObject(accessPoint);
	}

	@Override
	public void accessPointsChanged() {
		if (null != listener) listener.accessPointsChanged();
	}
}