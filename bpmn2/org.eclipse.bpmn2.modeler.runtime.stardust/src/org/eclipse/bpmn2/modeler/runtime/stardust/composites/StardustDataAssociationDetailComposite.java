package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.ListCompositeColumnProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.DataAssociationPropertiesAdapter;
import org.eclipse.bpmn2.modeler.ui.property.tasks.DataAssociationDetailComposite;
import org.eclipse.bpmn2.modeler.ui.property.tasks.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * @author Simon Nikles, based on code fragments in {@link DataAssociationDetailComposite}
 * @author Bob
 *
 */
public class StardustDataAssociationDetailComposite extends DataAssociationDetailComposite {

	protected Group assignmentGroup;

	public StardustDataAssociationDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustDataAssociationDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void cleanBindings() {
		super.cleanBindings();
	}

	@Override
	public void createBindings(EObject be) {

		EObject container = ModelUtil.getContainer(be);
		if (!(container instanceof InputOutputSpecification)
		&& !(container instanceof Event)) {
			super.createBindings(be);
			return;
		}

		association = null;
		if (be instanceof DataInput) {
			isInput = true;
			parameterName = ((DataInput)be).getName();
		}
		else if (be instanceof DataOutput) {
			isInput = false;
			parameterName = ((DataOutput)be).getName();
		} else {
			super.createBindings(be);
			return;
		}

		GridData gridData;
		fromGroup = new Group(this, SWT.NONE);
		fromGroup.setText(Messages.DataAssociationDetailComposite_From_Title);
		fromGroup.setLayout(new GridLayout(3,false));
		gridData = new GridData(SWT.FILL,SWT.TOP,true,false,3,1);
		fromGroup.setLayoutData(gridData);
		if (!showFromGroup) {
			fromGroup.setVisible(false);
			gridData.exclude = true;
		}

		toGroup = new Group(this, SWT.NONE);
		toGroup.setText(Messages.DataAssociationDetailComposite_To_Title);
		toGroup.setLayout(new GridLayout(3,false));
		gridData = new GridData(SWT.FILL,SWT.TOP,true,false,3,1);
		toGroup.setLayoutData(gridData);
		if (!showToGroup) {
			toGroup.setVisible(false);
			gridData.exclude = true;
		}

		assignmentGroup = new Group(this, SWT.NONE);
		assignmentGroup.setLayout(new GridLayout(3,false));
		gridData = new GridData(SWT.FILL,SWT.TOP,true,false,3,1);
		assignmentGroup.setLayoutData(gridData);

		final Group group = isInput ? toGroup : fromGroup;

		Activity activity = null;
		Event event = null;
		List<? extends DataAssociation> associations = null;

		if (container instanceof InputOutputSpecification) {
			EObject containerContainer = ModelUtil.getContainer(container);
			if (containerContainer instanceof Activity) {
				activity = (Activity)containerContainer;
				if (isInput)
					associations = activity.getDataInputAssociations();
				else
					associations = activity.getDataOutputAssociations();
			}
			else {
				super.createBindings(be);
				return;
			}
			DataInputOutputDetailComposite details = createDataInputOutputDetailComposite(be, group,SWT.NONE);
			details.setBusinessObject(be);
		}
		if (container instanceof Event) {
			event = (Event)container;
			if (isInput && container instanceof ThrowEvent)
				associations = ((ThrowEvent)container).getDataInputAssociation();
			else if (!isInput && container instanceof CatchEvent) {
				associations = ((CatchEvent)container).getDataOutputAssociation();
			} else {
				super.createBindings(be);
				return;
			}
			DataInputOutputDetailComposite details = createDataInputOutputDetailComposite(be, group,SWT.NONE);
			details.setBusinessObject(be);
		}

		if (associations!=null) {
			for (DataAssociation a : associations) {
				if (isInput) {
					if (a.getTargetRef() == be) {
						association = a;
						break;
					}
				}
				else
				{
					for (ItemAwareElement e : a.getSourceRef()) {
						if (e == be) {
							association = a;
							break;
						}
					}
					if (association!=null)
						break;
				}
			}
			if (association==null && (activity!=null || event!=null)) {
				// create a new DataAssociation
				if (isInput) {
					association = createModelObject(DataInputAssociation.class);
					association.setTargetRef((ItemAwareElement) be);
					InsertionAdapter.add(null != activity ? activity : event, PACKAGE.getActivity_DataInputAssociations(), association);
				}
				else {
					association = createModelObject(DataOutputAssociation.class);
					association.getSourceRef().add((ItemAwareElement) be);
					InsertionAdapter.add(null != activity ? activity : event, PACKAGE.getActivity_DataOutputAssociations(), association);
				}
			}
		}

		showPropertyWidgets();
		showAssignmentsWidgets();

	}


	private void showAssignmentsWidgets() {
		Group group = assignmentGroup;
		if (assignmentsComposite==null) {
			assignmentsComposite = toolkit.createComposite(group, SWT.NONE);
			assignmentsComposite.setLayout(new GridLayout(1,false));
			assignmentsComposite.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,true,3,1));
		} else {
			assignmentsComposite.setVisible(true);
			((GridData)assignmentsComposite.getLayoutData()).exclude = false;
		}

		if (assignmentsTable!=null)
			assignmentsTable.dispose();
		assignmentsTable = new StardustAssignmentListComposite(assignmentsComposite);
		assignmentsTable.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false,1,1));
		assignmentsTable.bindList(association, association.eClass().getEStructuralFeature("assignment")); //$NON-NLS-1$
		assignmentsTable.setTitle(Messages.DataAssociationDetailComposite_Assignments_Title);

		assignmentsWidgetsShowing = true;
	}

	private void showPropertyWidgets() {
		final Group group = !isInput ? toGroup : fromGroup;

		if (propertyComposite==null) {
			propertyComposite = toolkit.createComposite(group, SWT.NONE);
			GridLayout layout = new GridLayout(3,false);
			layout.verticalSpacing = 0;
			layout.marginHeight = 0;
			propertyComposite.setLayout(layout);
			propertyComposite.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,true,3,1));
		}
		else {
			propertyComposite.setVisible(true);
			((GridData)propertyComposite.getLayoutData()).exclude = false;
		}

		if (propertyDetailsComposite==null) {
			propertyDetailsComposite = new DefaultDetailComposite(propertyComposite,SWT.NONE) {

				@Override
				public Composite getAttributesParent() {
					return this;
				}

				@Override
				public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
					if (propertiesProvider == null) {
						propertiesProvider = new AbstractPropertiesProvider(object) {
							@Override
							public String[] getProperties() {
								String[] properties = null;
								if (isInput) {
									properties = new String[] {
											"sourceRef" //$NON-NLS-1$
									};
								} else {
									properties = new String[] {
											"targetRef" //$NON-NLS-1$
									};
								}
								return properties;
							}
						};
					}
					return propertiesProvider;
				}

				@Override
				protected void bindReference(Composite parent, EObject object, EReference reference) {
					@SuppressWarnings("rawtypes")
					ExtendedPropertiesAdapter adapter = ExtendedPropertiesAdapter.adapt(object);
					adapter.setProperty(
							DataAssociationPropertiesAdapter.UI_SHOW_ITEMS_IN_SCOPE,
							new Boolean(showItemsInScope));
					String displayName = ExtendedPropertiesProvider.getLabel(object, reference);
					ObjectEditor editor = new ComboObjectEditor(this,object,reference);
					editor.createControl(parent,displayName);
				}

				protected boolean isEmpty() {
					return false;
				}
			};
			propertyDetailsComposite.setBusinessObject(association);
			propertyDetailsComposite.setTitle(Messages.DataAssociationDetailComposite_DataItems_Title);
		}
		propertyWidgetsShowing = true;
	}

	@Override
	protected DataInputOutputDetailComposite createDataInputOutputDetailComposite(EObject be, Composite parent, int style) {
	    return new DataInputOutputDetailComposite(parent, style) {
	    	// TODO Refactor as soon as we don't override DataAssociationDetailComposite anymore or ItemAwareElementDetailComposite doesn't hide the propertiesProvider anymore
	    	private AbstractPropertiesProvider propertiesProvider = null;

	    	@Override
	    	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
	    		if (null == propertiesProvider) { // ItemAwareElementDetailComposite hides this
	    			propertiesProvider = new AbstractPropertiesProvider(object) {
	    				String[] properties = new String[] {
	    						"id", //$NON-NLS-1$
	    						"name", //$NON-NLS-1$
	    						"itemSubjectRef", //$NON-NLS-1$
	    				};
	    				@Override
	    				public String[] getProperties() {
	    					return properties;
	    				}
	    			};
	    		}
	    		return propertiesProvider;
	    	}
	    };
	}

	/**
	 * Invert column order
	 * @author Simon Nikles
	 *
	 */
	public class StardustAssignmentListComposite extends AssignmentListComposite {

		public StardustAssignmentListComposite(Composite parent) {
			super(parent);
		}

		@Override
		public ListCompositeColumnProvider getColumnProvider(EObject object, EStructuralFeature feature) {
			columnProvider = new ListCompositeColumnProvider(this);
			columnProvider.add(new AssignmentsTableColumn(object,PACKAGE.getAssignment_From())).setHeaderText(Messages.DataAssociationDetailComposite_From_Title);;
			columnProvider.add(new AssignmentsTableColumn(object,PACKAGE.getAssignment_To())).setHeaderText(Messages.DataAssociationDetailComposite_To_Title);;
			return columnProvider;
		}
	}
}