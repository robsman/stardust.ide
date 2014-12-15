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

import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.NamespaceUtil;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.ItemDefinitionPropertiesAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.eclipse.xsd.util.XSDConstants;

/**
 * @author Simon Nikles
 *
 */
public class FilteredStardustDatatypeDropdown extends ComboObjectEditor {

	public enum Filter {
		PRIMITIVE,
		STRUCTURED,
		CLASS,
		CLASS_OR_STRUCTURED;
	}

	private Filter filter;
	private List<String> allowedClassNames;
	private boolean excludeSynthetic;

	public FilteredStardustDatatypeDropdown(AbstractDetailComposite parent, EObject object, EStructuralFeature feature, Filter filter, List<String> allowedClassNames, boolean excludeSynthetic) {
		super(parent, object, feature);
		if (null == NamespaceUtil.getPrefixForNamespace(parent.getBusinessObject().eResource(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001)) {
			NamespaceUtil.addNamespace(parent.getBusinessObject().eResource(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
		}
		this.filter = filter;
		this.allowedClassNames = allowedClassNames;
		this.excludeSynthetic = excludeSynthetic;
	}

	@Override
	protected Hashtable<String,Object> getChoiceOfValues(EObject object, EStructuralFeature feature){
		if (null != choices) return choices;
		Hashtable<String, Object> filteredMap = new Hashtable<String, Object>();
		Hashtable<String, Object> initialMap = ItemDefinitionPropertiesAdapter.getChoiceOfValues(parent.getBusinessObject());
		for (Entry<String, Object> entry : initialMap.entrySet()) {
			if (entry.getValue() instanceof ItemDefinition) {
				ItemDefinition itemDef = (ItemDefinition)entry.getValue();
				// ignore synthetic declaration, if itemDef is in allowedClassList
				boolean ignoreSynthetic = false;
				switch(filter) {
				case PRIMITIVE:
					if (excludeSynthetic && isSynthetic(itemDef)) break;
					if (isPrimitive(itemDef)) filteredMap.put(entry.getKey(), entry.getValue());
					break;
				case STRUCTURED:
					if (excludeSynthetic && isSynthetic(itemDef)) break;
					if (isStructured(itemDef)) filteredMap.put(entry.getKey(), entry.getValue());
					break;
				case CLASS:
					if (null != allowedClassNames) {
						if (!allowedClassNames.contains(itemDef.getStructureRef().toString())) break;
						ignoreSynthetic = true;
					}
					if (!ignoreSynthetic && excludeSynthetic && isSynthetic(itemDef)) break;
					if (isSerializable(itemDef)) filteredMap.put(entry.getKey(), entry.getValue());
					break;
				case CLASS_OR_STRUCTURED:
					boolean isSerializable = isSerializable(itemDef);
					if (isSerializable) {
						if (null != allowedClassNames) {
							if (!allowedClassNames.contains(itemDef.getStructureRef().toString())) break;
							ignoreSynthetic = true;
						}
					}
					if (!ignoreSynthetic && excludeSynthetic && isSynthetic(itemDef)) break;
					if (isSerializable) filteredMap.put(entry.getKey(), entry.getValue());
					else if (isStructured(itemDef)) filteredMap.put(entry.getKey(), entry.getValue());
					break;
				default:
					filteredMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		choices = filteredMap;
		return choices;
	}

	private boolean isSynthetic(ItemDefinition itemDef) {
		return ExtensionHelper2.INSTANCE.isSynthetic(itemDef);
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

}
