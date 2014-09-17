/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013, 2014 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultListComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.ListCompositeColumnProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.ListCompositeContentProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.TableColumn;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.IntObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ObjectEditor;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.StardustInterfaceExtendedPropertiesAdapter.ApplicationTypes;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeBooleanEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeComboEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.editors.AttributeTypeTextEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.property.StardustInterfaceDefinitionPropertySection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

class CamelDetailComposite extends DefaultDetailComposite {

	//Consumer, Prducer (send and send/receive)
	private ApplicationTypes camelTypes;
	
	public enum CamelAcessPointDataTypes {
		TYPE("carnot:engine:type", "primitive", "Primitive Data"),
		DATATYPE("carnot:engine:dataType", "struct", "Structured Data"),
		CLASSNAME("carnot:engine:className", "serializable", "Serializable");
		
		private String key;
		private String type;
		public String displayName;

		private CamelAcessPointDataTypes(String key, String type, String displayName) {
			this.setKey(key);
			this.displayName = displayName;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public String getType() {
			return type;
		}		

		public static CamelAcessPointDataTypes forKey(String key) {
			if (null == key) return null;
			for (CamelAcessPointDataTypes t : values()) {
				if (key.equals(t.key)) return t;
			}
			return null;
		}	
	}
	
	public CamelDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public CamelDetailComposite(Composite parent, int style, ApplicationTypes camelType) {
		super(parent, style);
		camelTypes = camelType;
	}

	@Override
	public void createBindings(EObject be) {
		Composite parent = this.getAttributesParent();

		if (camelTypes.equals(ApplicationTypes.CAMELCONSUMER)) {
			setTitle("Camel Consumer Service Configuration (receive)");			
		} else if (camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			setTitle("Camel Producer Service Configuration (send)");			
		} else {
			setTitle("Camel Producer Service Configuration (send/receive)");
		}
		
		StardustInterfaceType sdInterface = (StardustInterfaceType) be;

		ObjectEditor editor = null;
		StardustApplicationType sdApplication;
		sdApplication = sdInterface.getStardustApplication();
		bindAttribute(sdApplication, "name");
		bindAttribute(sdApplication, "id");
		bindAttribute(sdApplication, "elementOid");

		AttributeType at;
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::camelContextId");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Context Id");
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:visibility");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "Public", "Private" });
		editor.createControl(parent, "Visibility");

		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::invocationType");
		editor = new AttributeTypeComboEditor(this, at, new String[] { "Synchronous", "Asynchronous" });
		editor.createControl(parent, "Invocation Type");

		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "synchronous:retry:enable");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Enable Retry");
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "synchronous:retry:number");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Number of Retries");
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "synchronous:retry:time");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Time between Retries (seconds)");

		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::supportMultipleAccessPoints");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Multiple Access Points");
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::transactedRoute");
		editor = new AttributeTypeBooleanEditor(this, at);
		editor.createControl(parent, "Transacted Route");
		
		if (camelTypes.equals(ApplicationTypes.CAMELCONSUMER) || camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE)) {
			at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::consumerRoute");
			editor = new AttributeTypeTextEditor(this, at);
			editor.createControl(parent, "Camel Consumer Route");
		}
			
		if (camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SENDRECEIVE) || camelTypes.equals(ApplicationTypes.CAMELPRODUCER_SEND)) {
			at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::processContextHeaders");
			editor = new AttributeTypeBooleanEditor(this, at);
			editor.createControl(parent, "Include Process Context Headers in Producer Route ");			
				
			at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::routeEntries");
			editor = new AttributeTypeTextEditor(this, at);
			editor.createControl(parent, "Camel Producer Route");
		}
		
		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::inBodyAccessPoint");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Body Input Access Point");

		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::outBodyAccessPoint");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Body Output Access Point");

		at = StardustInterfaceDefinitionPropertySection.findAttributeType(sdApplication, "carnot:engine:camel::additionalSpringBeanDefinitions");
		editor = new AttributeTypeTextEditor(this, at);
		editor.createControl(parent, "Additional Spring Bean Definitions");

		// AccesPointsSection for Input and Output AccessPoint definitions
		Composite accessPointsSection = this.createSectionComposite(this, "Access Points");
		
		// create two lists, one for Input and one for Output Access Points
		AccessPointListComposite inputParams = new AccessPointListComposite(accessPointsSection, true);
		inputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
		inputParams.setTitle("Inputs");

		AccessPointListComposite outputParams = new AccessPointListComposite(accessPointsSection, false);
		outputParams.bindList(sdApplication, SdbpmnPackage.eINSTANCE.getStardustApplicationType_AccessPoint1());
		outputParams.setTitle("Outputs");
	}
	
	private class AccessPointListComposite extends DefaultListComposite {

		boolean isInput;
		/**
		 * @param parent
		 * @param isInput
		 */
		public AccessPointListComposite(Composite parent, boolean isInput) {
			super(parent, DEFAULT_STYLE);
			this.isInput = isInput;
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
					AttributeType at = StardustInterfaceDefinitionPropertySection.findAttributeType(ap, "carnot:engine:type");
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
			// generate an ID for it
			ModelUtil.setID(param);
			// and a name based on the ID string
			param.setName( ModelUtil.toCanonicalString(param.getId()) );
			
			// also create two AttributeType objects
			AttributeType at;
			at = StardustInterfaceExtendedPropertiesAdapter.createAttributeType("carnot:engine:type", "", null);
			param.getAttribute().add(at);
			
			at = StardustInterfaceExtendedPropertiesAdapter.createAttributeType("RootElement", "", "");
			param.getAttribute().add(at);
			
			// Remove ItemDefinition and write new ItemDefinition with newly added ListItem (Camel AccessPoint)
//			StardustInterfaceType sdIntType = (StardustInterfaceType) businessObject;
//			StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdIntType);
//			StardustApplicationConfigurationGenerator.INSTANCE.generateAccessPointInfos((StardustInterfaceType) businessObject, method, constr );
//			StardustApplicationConfigurationGenerator.INSTANCE.
			return param;
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
			return super.removeListItem(object, feature, actualIndex);
		}

		@Override
		public AbstractDetailComposite createDetailComposite(Class eClass, Composite parent, int style) {
			AbstractDetailComposite composite = new AccessPointTypeDetailComposite(parent);
			return composite;
		}
	}
	
	private class AccessPointTypeDetailComposite extends DefaultDetailComposite {
		
		// Yuck :-p
		String attributeTypes[] = new String[] {
				"carnot:engine:type",
				"RootElement",
		};
		
		public AccessPointTypeDetailComposite(Composite parent) {
			super(parent, SWT.NONE);
		}
		
		@Override
		public void createBindings(EObject be) {
			final StardustAccessPointType accessPoint = (StardustAccessPointType) be;
			Composite parent = getAttributesParent();
			ObjectEditor editor;

			// Create editors/viewers for two values in the Access Point
			editor = new IntObjectEditor(this, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid());
			editor.createControl(parent, "Element ID");
			editor.setEditable(false);
			
			editor = new TextObjectEditor(this, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
			editor.createControl(parent, "Name");
			editor.setEditable(true);
			
			//Present a Combo Box to 
			ComboObjectEditor objectEditor= new ComboObjectEditor(this, accessPoint, CarnotWorkflowModelPackage.eINSTANCE.getAccessPointType_Type()) {

				@Override
				protected boolean setValue(final Object newValue) {
					final Object oldValue = getValue();
//					if (super.setValue(newValue)) {
//						// "newValue" should be a String or null
//						if (oldValue != newValue) {
//							// This will force a rebuild of the entire Property
//							// Sheet tab.
//							setBusinessObject(accessPoint);
//							// Set additional attributes
//							
//						}
//						return true;
//					}
					return false;
				}
				
				@Override
				protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature){
					if (choices==null) {
						choices = new Hashtable<String, Object>();
						for (CamelAcessPointDataTypes type : CamelAcessPointDataTypes.values()) {
							choices.put(type.getDisplayName(), type.getKey());
						}
					}
					return choices;
				}
			
			};
			
			
			
			objectEditor.createControl(this, "AccessPoint Data Type");
			
			// Create editors for the two AttributeType objects contained by the Access Point.
			for (String name : attributeTypes) {
				AttributeType at = StardustInterfaceDefinitionPropertySection.findAttributeType(accessPoint, name);
				if (at!=null) {
					System.out.println("AttributeType: " + at.getName());
					if ("RootElement".equals(at.getName())) {
						editor = new AttributeTypeBooleanEditor(this, at);
					}
					else {
						editor = new AttributeTypeTextEditor(this, at);
					}
					editor.createControl(parent, ModelUtil.toCanonicalString(name));
				}
			}
		}
	}
}