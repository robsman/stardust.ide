package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultListComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.ListCompositeColumnProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.ListCompositeContentProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.TableColumn;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.camel.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.camel.accesspoint.AccessPointTypeDetailComposite;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bob
 *
 */
public class AccessPointListComposite extends DefaultListComposite implements AccessPointChangeListener {

	private boolean isInput;
	private AccessPointChangeListener listener;
	
	/**
	 * @param parent
	 * @param isInput
	 */
	public AccessPointListComposite(Composite parent, boolean isInput, AccessPointChangeListener listener) {
		super(parent, DEFAULT_STYLE);
		this.isInput = isInput;
		this.listener = listener;
	}
	
	@Override
	protected int createColumnProvider(EObject object, EStructuralFeature feature) {
		columnProvider = new ListCompositeColumnProvider(this);
		TableColumn tc;
		
		// Create the table columns:
		// the first column is the "name" feature of the StardustAccessPoint  
		tc = columnProvider.add(object, SdbpmnPackage.eINSTANCE.getStardustAccessPointType(), CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
		tc.setEditable(false); // don't allow editing within the table
		
		// the second column is the value of an AttributeType contained in
		// the StardustAccessPoint object's "attribute" list.
		tc = new TableColumn(this, object, "") {

			@Override
			public String getHeaderText() {
				// returns the column header text
				return "Carnot Engine Type";
			}
			
			@Override
			public String getText(Object element) {
				// returns the "value" feature of an AttributeType object in a list contained by StardustAccessPoint
				// in this case, it's the AttributeType whose "name" is equal to "carnot:engine:type"
				StardustAccessPointType ap = (StardustAccessPointType) element;
				AttributeType at = PropertyAdapterCommons.findAttributeType(ap, "carnot:engine:type");
				if (at!=null) {
					return at.getValue();
				}
				return "";
			}
		};
		columnProvider.add(tc);
		tc.setEditable(false); // don't allow editing within the table
		
		return 2;
	}
	
	@Override
	public ListCompositeContentProvider getContentProvider(EObject object, EStructuralFeature feature, EList<EObject>list) {
		if (contentProvider==null) {
			// Create a content provider for the table.
			// A StardustApplicationType may contain any number of StardustAccessPointType objects,
			// each of which has either an "IN" or "OUT" DirectionType.
			// Collect only those StardustAccessPointType objects in the list that have
			// the correct DirectionType.
			contentProvider = new ListCompositeContentProvider(this, object, feature, list) {

				@Override
				public Object[] getElements(Object inputElement) {
					if (inputElement instanceof List<?>) {
						List<StardustAccessPointType> list = new ArrayList<StardustAccessPointType>();
						for (StardustAccessPointType ap : (List<StardustAccessPointType>)inputElement) {
							if (isInput) {
								// we are configured for displaying only INPUT Access Points:
								// only add  Access Points that have an "IN" DirectionType.
								if (ap.getDirection()==DirectionType.IN_LITERAL)
									list.add(ap);
							}
							else {
								// same for "OUT" Access Points
								if (ap.getDirection()==DirectionType.OUT_LITERAL)
									list.add(ap);
							}
						}
						return list.toArray();
					}
					return super.getElements(inputElement);
				}
			};
		}
		return contentProvider;
	}

	@Override
	protected EObject addListItem(EObject object, EStructuralFeature feature) {
		// Add a new Access Point to the list
		StardustAccessPointType param = null;
		param = SdbpmnFactory.eINSTANCE.createStardustAccessPointType();
		// make sure it has the correct DirectionType
		param.setDirection(isInput ? DirectionType.IN_LITERAL : DirectionType.OUT_LITERAL);
		List<StardustAccessPointType> list = (List<StardustAccessPointType>)object.eGet(feature);
		list.add(param);
		ModelUtil.setID(param);
		param.setName( ModelUtil.toCanonicalString(param.getId()) );
		
		accessPointsChanged();
		
		return param;
	}

	@Override
	protected Object deleteListItem(EObject object, EStructuralFeature feature, int index) {
		Object deleteListItem = super.deleteListItem(object, feature, index);
		accessPointsChanged();
		return deleteListItem;
	}
	
	@Override
	protected Object removeListItem(EObject object, EStructuralFeature feature, int index) {
		// Determine the actual list item index by counting only the Access Points that have
		// the correct DirectionType ("IN" or "OUT") 
		int actualIndex = 0;
		for (StardustAccessPointType ap : (List<StardustAccessPointType>)object.eGet(feature)) {
			if (isInput) {
				if (ap.getDirection()==DirectionType.IN_LITERAL) {
					if (--index < 0)
						break;
				}
			}
			else {
				if (ap.getDirection()==DirectionType.OUT_LITERAL) {
					if (--index < 0)
						break;
				}
			}
			++actualIndex;
		}
		
		Object removeListItem = super.removeListItem(object, feature, actualIndex);
		accessPointsChanged();
		return removeListItem;
	}

	@Override
	public AbstractDetailComposite createDetailComposite(Class eClass, Composite parent, int style) {
		AbstractDetailComposite composite = new AccessPointTypeDetailComposite(parent, (AccessPointChangeListener)this);
		return composite;
	}

	@Override
	public void accessPointsChanged() {
		if (null != listener) listener.accessPointsChanged();
	}
}