package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms;

import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.accesspoint.jms.JmsAppAccesspointsExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class JmsApplicationAccessPointListComposite extends AccessPointListComposite {

	public JmsApplicationAccessPointListComposite(Composite parent, boolean isInput, AccessPointChangeListener listener) {
		super(parent, isInput, listener);
	}

	@Override
	public AbstractDetailComposite createDetailComposite(@SuppressWarnings("rawtypes") Class eClass, Composite parent, int style) {
		AbstractDetailComposite composite = new JmsApplicationAccessPointTypeDetailComposite(parent, (AccessPointChangeListener)this);
		return composite;
	}

	@Override
	protected EObject addListItem(EObject object, EStructuralFeature feature) {
		StardustAccessPointType accessPoint = null;
		accessPoint = SdbpmnFactory.eINSTANCE.createStardustAccessPointType();
		// make sure it has the correct DirectionType
		accessPoint.setDirection(isInput ? DirectionType.IN_LITERAL : DirectionType.OUT_LITERAL);
		List<StardustAccessPointType> list = (List<StardustAccessPointType>)object.eGet(feature);
		list.add(accessPoint);
		ModelUtil.setID(accessPoint);
		accessPoint.setName( ModelUtil.toCanonicalString(accessPoint.getId()) );

		JmsAppAccesspointsExtendedPropertiesAdapter.INSTANCE.createJmsSerializableTypeModel(accessPoint);

		accessPointsChanged();

		return accessPoint;
	}

}
