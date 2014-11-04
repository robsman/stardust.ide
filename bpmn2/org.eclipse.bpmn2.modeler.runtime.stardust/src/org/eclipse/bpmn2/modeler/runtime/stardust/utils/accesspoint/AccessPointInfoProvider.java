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
package org.eclipse.bpmn2.modeler.runtime.stardust.utils.accesspoint;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.utils.ImportUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.extension.AccessPointSchemaWrapper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;

public class AccessPointInfoProvider {

	public static ItemDefinition addInputAccessPointItemDefinitionSchema(StardustApplicationType app, ItemDefinition itemDef) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		AccessPointSchemaWrapper wrapper = createSchemaWrapper(app);
		itemDef = ExtensionHelper2.INSTANCE.createInputAccessPointItemDefinition(wrapper, itemDef);
		return itemDef;
	}

	public static ItemDefinition addInputAccessPointItemDefinitionSchema(StardustContextType appCtx, ItemDefinition itemDef) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		AccessPointSchemaWrapper wrapper = createSchemaWrapper(appCtx);
		itemDef = ExtensionHelper2.INSTANCE.createInputAccessPointItemDefinition(wrapper, itemDef);
		return itemDef;
	}

	public static ItemDefinition addOutputAccessPointItemDefinitionSchema(StardustContextType appCtx, ItemDefinition itemDef) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		AccessPointSchemaWrapper wrapper = createSchemaWrapper(appCtx);
		itemDef = ExtensionHelper2.INSTANCE.createOutputAccessPointItemDefinition(wrapper, itemDef);
		return itemDef;
	}

	public static ItemDefinition addOutputAccessPointItemDefinitionSchema(StardustTriggerType trigger, ItemDefinition itemDef) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		AccessPointSchemaWrapper wrapper = createSchemaWrapper(trigger);
		itemDef = ExtensionHelper2.INSTANCE.createOutputAccessPointItemDefinition(wrapper, itemDef);
		return itemDef;
	}

	public static ItemDefinition addOutputAccessPointItemDefinitionSchema(StardustApplicationType app, ItemDefinition itemDef) throws ClassNotFoundException, NoSuchMethodException, SecurityException, MalformedURLException, CoreException {
		AccessPointSchemaWrapper wrapper = createSchemaWrapper(app);
		itemDef = ExtensionHelper2.INSTANCE.createOutputAccessPointItemDefinition(wrapper, itemDef);
		return itemDef;
	}

	private static AccessPointSchemaWrapper createSchemaWrapper(StardustApplicationType app) {
		AccessPointSchemaWrapper schemaWrapper = createSchemaWrapper(app.getAccessPoint1());
		schemaWrapper.setOwnerApplicationId(app.getId());
		return schemaWrapper;
	}

	private static AccessPointSchemaWrapper createSchemaWrapper(StardustTriggerType trigger) {
		AccessPointSchemaWrapper schemaWrapper = createSchemaWrapper(trigger.getAccessPoint1());
		schemaWrapper.setOwnerApplicationId(trigger.getId());
		return schemaWrapper;
	}

	private static AccessPointSchemaWrapper createSchemaWrapper(StardustContextType appCtx) {
		EList<StardustAccessPointType> sdApoints = new BasicEList<StardustAccessPointType>();
		if (null == appCtx) return null;
		for (AccessPointType ap : appCtx.getAccessPoint()) {
			if (ap instanceof StardustAccessPointType) {
				sdApoints.add((StardustAccessPointType)ap);
			} else {
				StardustAccessPointType sdap = SdbpmnFactory.eINSTANCE.createStardustAccessPointType();
				sdap.setDirection(ap.getDirection());
				sdap.setDescription(ap.getDescription());
				sdap.setElementOid(ap.getElementOid());
				sdap.setName(ap.getName());
				sdap.setId(ap.getId());
				sdap.setTypeRef(null != ap.getType() ? ap.getType().getId() : "");
				sdApoints.add(sdap);
			}
		}
		AccessPointSchemaWrapper schemaWrapper = createSchemaWrapper(sdApoints);
		schemaWrapper.setOwnerApplicationId(((StardustApplicationType)appCtx.eContainer()).getId());
		return schemaWrapper;
	}

	private static AccessPointSchemaWrapper createSchemaWrapper(EList<StardustAccessPointType> accessPoints) {
		AccessPointSchemaWrapper wrapper = new AccessPointSchemaWrapper();
		for (StardustAccessPointType ap : accessPoints) { //app.getAccessPoint1()) {
			//AccessPoint ap = (AccessPoint) v;
			String id = ap.getId();
			String type = ap.getTypeRef(); // ap.getType().getId();
			String displayName = ap.getName();
			String typeClass = "";
			XSDTypeDefinition typeDef = null;
			if (AcessPointDataTypes.PRIMITIVE_TYPE.getKey().equals(type)) {
				String prop = AcessPointDataTypes.PRIMITIVE_TYPE.getType();
				String typeRef = getStringAttribute(ap, prop); //ap.getTypeRef(); //ap.getStringAttribute(prop);
				XSDType2Stardust byTypeName = XSDType2Stardust.byTypeName(typeRef);
				XSDSimpleTypeDefinition simpleType = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
				if (null == byTypeName) continue;
				QName qname = byTypeName.getXSDQname();
				simpleType.setTargetNamespace(qname.getNamespaceURI());
				simpleType.setName(qname.getLocalPart());
				typeDef = simpleType;
			} else if (AcessPointDataTypes.SERIALIZABLE_TYPE.getKey().equals(type)) {
				String prop = AcessPointDataTypes.SERIALIZABLE_TYPE.getType();
				String typeRef = getStringAttribute(ap, prop); //ap.getStringAttribute(prop);
				XSDSimpleTypeDefinition simpleType = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
				simpleType.setTargetNamespace(ImportUtil.IMPORT_KIND_XML_SCHEMA);
				simpleType.setName("anyType");
				typeDef = simpleType;
				ItemDefinition refItemDef = findItemDefinition(ap, typeRef);
				if (null != refItemDef) {
					if (null != refItemDef.getStructureRef()) {
						typeClass = refItemDef.getStructureRef().toString();
					}
				}

			} else if (AcessPointDataTypes.STRUCT_TYPE.getKey().equals(type)) {
				String prop = AcessPointDataTypes.STRUCT_TYPE.getType();
				String typeRef = getStringAttribute(ap, prop); // ap.getStringAttribute(prop);
				ItemDefinition refItemDef = findItemDefinition(ap, typeRef);
				if (null != refItemDef) {
					if (refItemDef.getStructureRef() instanceof XSDElementDeclaration) {
						XSDElementDeclaration decl = (XSDElementDeclaration)refItemDef.getStructureRef();
						typeDef = decl.getType();
					} else if (refItemDef.getStructureRef() instanceof XSDTypeDefinition) {
						typeDef = (XSDTypeDefinition)refItemDef.getStructureRef();
					} else {
						QName qname = QName.valueOf(refItemDef.getStructureRef().toString());
						XSDSimpleTypeDefinition simpleType = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
						simpleType.setTargetNamespace(qname.getNamespaceURI());
						simpleType.setName(qname.getLocalPart());
						typeDef = simpleType;
					}
				}
			}
			wrapper.addElement(displayName, id, typeDef, id, typeClass, AccessPointSchemaWrapper.Direction.valueOf(ap.getDirection()));
		}
		return wrapper;
	}

	private static String getStringAttribute(StardustAccessPointType ap, String prop) {
		EList<AttributeType> attributes = ap.getAttribute();
		Iterator<AttributeType> iterator = attributes.iterator();
		while (iterator.hasNext()) {
			AttributeType at = iterator.next();
			if (null != at && null != prop && prop.equals(at.getName())) return at.getValue();
		}
		return null;
	}

	private static ItemDefinition findItemDefinition(EObject app, String typeRef) {
		if (null == typeRef) return null;
		Definitions definitions = ModelUtil.getDefinitions(app.eResource());
		if (null == definitions) return null;
		List<ItemDefinition> itemDefs = ModelUtil.getAllRootElements(definitions, ItemDefinition.class);
		for (ItemDefinition def : itemDefs) {
			if (null != def.getId() && def.getId().equals(typeRef)) return def;
		}
		return null;
	}


}
