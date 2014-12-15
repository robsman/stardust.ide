/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.editors;

import java.net.URI;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.NamespaceUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ItemDefinitionPropertiesAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.eclipse.xsd.util.XSDConstants;

/**
 * @author Simon Nikles
 *
 */
public class MappedStardustDatatypeDropdown extends ComboObjectEditor {

	private AcessPointDataTypes datatypeCategory;
	private DatatypeChangeListener listener;

	public interface DatatypeChangeListener {
		public void comboChanged();
	}

	public MappedStardustDatatypeDropdown(AbstractDetailComposite parent, AttributeType object, AcessPointDataTypes datatypeCategory, DatatypeChangeListener listener) {
		super(parent, object, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value());
		if (null == NamespaceUtil.getPrefixForNamespace(parent.getBusinessObject().eResource(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001)) {
			NamespaceUtil.addNamespace(parent.getBusinessObject().eResource(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
		}
		this.listener = listener;
		this.datatypeCategory = datatypeCategory;
	}

	@Override
	protected boolean setValue(final Object newValue) {
		final Object oldValue = getValue();

		if (null != newValue && !(newValue instanceof ItemDefinition)) return super.setValue(newValue);

		ItemDefinition newItemDef = (ItemDefinition)newValue;


		if (AcessPointDataTypes.PRIMITIVE_TYPE.equals(datatypeCategory)) {
			String newValueName = null != newItemDef ? newItemDef.getStructureRef().toString() : null;
			if (null != newValueName) {
				int delimPos = newValueName.indexOf(":");
				if (delimPos > -1 && delimPos < newValueName.length() -1) newValueName = newValueName.substring(delimPos+1);
			}
			XSDType2Stardust newStardustValue = XSDType2Stardust.byXsdName(newValueName);
			String newSdStr = null != newStardustValue ? newStardustValue.getPrimitive() : null;
			if (super.setValue(newSdStr)) {
				if (oldValue != newValueName) {
					if (null != listener) listener.comboChanged();
				}
				return true;
			}
		} else if (AcessPointDataTypes.SERIALIZABLE_TYPE.equals(datatypeCategory)) {
			final String newValueId = null != newItemDef && null != newItemDef.getStructureRef() ? newItemDef.getStructureRef().toString() : null;
			if (super.setValue(newValueId)) {
				if (oldValue != newValueId) {
					if (null != listener) listener.comboChanged();
				}
				return true;
			}
		} else {
			final String newValueId = null != newItemDef ? newItemDef.getId() : null;
			if (super.setValue(newValueId)) {
				if (oldValue != newValueId) {
					if (null != listener) listener.comboChanged();
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getValue() {
		Object stardustValue = null;
		try {
			stardustValue = super.getValue();
		} catch (Exception e) {}
		if (null == stardustValue) return null;
		if (AcessPointDataTypes.PRIMITIVE_TYPE.equals(datatypeCategory)) {
			XSDType2Stardust newStardustValue = XSDType2Stardust.byTypeName(stardustValue.toString());
			String newSdStr = null != newStardustValue ? newStardustValue.getName() : null;
			if (null == newSdStr) return null;
			return getChoiceOfValues(object, CarnotWorkflowModelPackage.eINSTANCE.getAttributeType_Value()).get(addXsdPrefix(newSdStr));
		} else if (AcessPointDataTypes.SERIALIZABLE_TYPE.equals(datatypeCategory)) {
			return getItemDefinitionByRefString(object, stardustValue.toString());
		} else {
			return getItemDefinitionById(object, stardustValue.toString());
		}
	}

	private Object addXsdPrefix(String newSdStr) {
		return NamespaceUtil.getPrefixForNamespace(parent.getBusinessObject().eResource(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001) + ":" + newSdStr;
	}

	@Override
	protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature){
		if (null != choices) return choices;
		Hashtable<String, Object> filteredMap = new Hashtable<String, Object>();
		Hashtable<String, Object> initialMap = ItemDefinitionPropertiesAdapter.getChoiceOfValues(parent.getBusinessObject());
		for (Entry<String, Object> entry : initialMap.entrySet()) {
			if (entry.getValue() instanceof ItemDefinition) {
				ItemDefinition itemDef = (ItemDefinition)entry.getValue();

				switch(datatypeCategory) {
				case PRIMITIVE_TYPE:
					if (isPrimitive(itemDef)) filteredMap.put(entry.getKey(), entry.getValue());
					break;
				case SERIALIZABLE_TYPE:
					if (isSerializable(itemDef)) filteredMap.put(entry.getKey(), entry.getValue());
					break;
				case STRUCT_TYPE:
					if (isStructured(itemDef)) filteredMap.put(entry.getKey(), entry.getValue());
					break;
				default:
					filteredMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		choices = filteredMap;
		return choices;
	}

	private boolean isStructured(ItemDefinition itemDef) {
		URI uri = null;
		if (null == itemDef || null == itemDef.getStructureRef()) return false;
		if (!isPrimitive(itemDef)) {
			try {
				uri = new URI(itemDef.getStructureRef().toString());
			} catch (Exception e) {
				return false;
			}
			return null != uri && uri.isAbsolute();
		}
		System.out.println("MappedStardustDatatypeDropdown.isStructured(?) " +  uri);
		return false;
	}

	private boolean isSerializable(ItemDefinition itemDef) {
		URI uri = null;
		if (null == itemDef || null == itemDef.getStructureRef()) return false;
		if (!isPrimitive(itemDef)) {
			try {
				uri = new URI(itemDef.getStructureRef().toString());
			} catch (Exception e) {
				return false;
			}
			return null == uri || !uri.isAbsolute();
		}
		return false;
	}

	/**
	 * Considers (only) XSD defined types as primitive types.
	 *
	 * @param itemDef
	 * @return
	 */
	private boolean isPrimitive(ItemDefinition itemDef) {
		if (null == itemDef || null == itemDef.getStructureRef()) return false;
		String ref = itemDef.getStructureRef().toString();
		if (0 > ref.indexOf(":")) return false;
		String[] parts = ref.split(":");
		if (2 != parts.length) return false;
		String namespace = NamespaceUtil.getNamespaceForPrefix(getObject().eResource(), parts[0]);
		if (!XSDType2Stardust.XML_SCHEMA_URI.equals(namespace)) return false;
		XSDSimpleTypeDefinition simpleTypeDefinition = XSDSchemaImpl.getSchemaForSchema(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001).resolveSimpleTypeDefinition(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001,parts[1]);
		return null != simpleTypeDefinition;
	}

	private static ItemDefinition getItemDefinitionById(EObject resourceProvider, String id) {
		if (null == id) return null;
		Definitions definitions = ModelUtil.getDefinitions(resourceProvider.eResource());
		if (null == definitions) return null;
		List<ItemDefinition> itemDefs = ModelUtil.getAllRootElements(definitions, ItemDefinition.class);
		for (ItemDefinition def : itemDefs) {
			if (null != def.getId() && def.getId().equals(id)) return def;
		}
		return null;
	}

	private static ItemDefinition getItemDefinitionByRefString(EObject resourceProvider, String refString) {
		if (null == refString) return null;
		Definitions definitions = ModelUtil.getDefinitions(resourceProvider.eResource());
		if (null == definitions) return null;
		List<ItemDefinition> itemDefs = ModelUtil.getAllRootElements(definitions, ItemDefinition.class);
		for (ItemDefinition def : itemDefs) {
			if (null != def.getStructureRef() && def.getStructureRef().toString().equals(refString)) return def;
		}
		return null;
	}

}
