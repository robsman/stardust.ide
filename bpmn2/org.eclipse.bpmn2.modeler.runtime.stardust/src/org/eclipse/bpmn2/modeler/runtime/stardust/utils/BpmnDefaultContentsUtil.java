package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.NamespaceUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo;
import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo.DataInfo;
import org.eclipse.xsd.util.XSDConstants;

public class BpmnDefaultContentsUtil {

	public static final String ADMIN_RESOURCE_ID = "Administrator";
	
	private static final String INT_KEY = "integer";
	private static final String STRING_KEY = "string";
	private static final String DATE_KEY = "date";
	
	public static void addDefaultContents(Definitions definitions) {
		addData(definitions);
		createAdministratorPerformer(definitions);
	}	
	
	private static void addData(Definitions definitions) {

		Map<String, ItemDefinition> typeMap = new HashMap<String, ItemDefinition>();
		
		ItemDefinition integerDef = findDefinitionForType(definitions, XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, INT_KEY);
		ItemDefinition stringDef = findDefinitionForType(definitions, XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, STRING_KEY);
		ItemDefinition dateDef = findDefinitionForType(definitions, XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, DATE_KEY);

		if (null == integerDef) integerDef = createType(definitions, XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, INT_KEY);
		if (null == stringDef) integerDef = createType(definitions, XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, STRING_KEY);
		if (null == dateDef) integerDef = createType(definitions, XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, DATE_KEY);

		typeMap.put(INT_KEY, integerDef);
		typeMap.put(STRING_KEY, stringDef);
		typeMap.put(DATE_KEY, dateDef);
		
		for (String cls : PredefinedDataInfo.getTypeClasses()) {
			if (null != findDefinitionForType(definitions, null, cls)) continue;
			ItemDefinition type = createType(definitions, cls);
			ExtensionHelper.getInstance().setAnyAttribute(type, ExtensionHelper2.STARDUST_SYNTHETIC_ITEMDEF, "true");
			typeMap.put(cls, type);	
		}

		List<Process> allProcesses = ModelUtil.getAllObjectsOfType(definitions.eResource(), Process.class);
		List<DataInfo> predefinedData = new PredefinedDataInfo().getPredefinedData();		
		for (Process process : allProcesses) {
			for (DataInfo info : predefinedData) {
				createProperty(process, info, typeMap);
			}
		}
	}
	
	private static void createProperty(Process process, DataInfo info, Map<String, ItemDefinition> typeMap) {
		if (null != findPropertyByStardustPropertyId(process, info.id)) return;
		Property prop = Bpmn2Factory.eINSTANCE.createProperty();
		//prop.setId(info.id);
		ExtensionHelper.getInstance().setAnyAttribute(prop, ExtensionHelper2.STARDUST_SYNTHETIC_PROPERTY, "true");
		ExtensionHelper.getInstance().setAnyAttribute(prop, ExtensionHelper2.STARDUST_PROPERTY_ID, info.id);
		prop.setName(info.name);
		prop.setItemSubjectRef(typeMap.get(info.type));
		process.getProperties().add(prop);
		ModelUtil.setID(prop);
	}

	private static Property findPropertyByStardustPropertyId(Process process, String id) {
		for (Property prop : process.getProperties()) {
			if (ExtensionHelper2.INSTANCE.hasStardustPropertyId(prop, id)) return prop;
			//if (id.equals(prop.getId())) return prop;
		}
		return null;
	}

	private static void createAdministratorPerformer(Definitions definitions) {
		if (null != findAdminUserResource(definitions)) return;
		Resource admin = Bpmn2Factory.eINSTANCE.createResource();
		admin.setId(ADMIN_RESOURCE_ID);
		admin.setName(ADMIN_RESOURCE_ID);
		definitions.getRootElements().add(admin);
		ModelUtil.setID(admin);
	}

	private static RootElement findAdminUserResource(Definitions definitions) {
		for (RootElement root : definitions.getRootElements()) {
			if (root instanceof Resource) {
				if (ADMIN_RESOURCE_ID.equals(((Resource)root).getId())) return root;
			}
		}
		return null;
	}

	private static ItemDefinition createType(Definitions definitions, String ns, String name) {
		ItemDefinition itemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
		NamespaceUtil.addNamespace(definitions.eResource(), ns);
		String prefix = NamespaceUtil.getPrefixForNamespace(definitions.eResource(), ns);
		EObject wrapper = ModelUtil.createStringWrapper(prefix+":"+name);
		itemDef.setStructureRef(wrapper);
		definitions.getRootElements().add(itemDef);
		ModelUtil.setID(itemDef);
		return itemDef;
	}

	private static ItemDefinition createType(Definitions definitions, String name) {
		ItemDefinition itemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
		EObject wrapper = ModelUtil.createStringWrapper(name);
		itemDef.setStructureRef(wrapper);
		definitions.getRootElements().add(itemDef);
		ModelUtil.setID(itemDef);
		return itemDef;
	}

    private static ItemDefinition findDefinitionForType(Definitions definitions, String ns, String typeName) {
    	for (RootElement root : definitions.getRootElements()) {
    		if (root instanceof ItemDefinition) {
    			ItemDefinition def = (ItemDefinition)root;
    			Object structureRef = def.getStructureRef();
    			if (null != structureRef) {
    				if (structureRef.toString().indexOf(":") > -1) {
    					String[] split = structureRef.toString().split(":");
    					String namespaceForPrefix = NamespaceUtil.getNamespaceForPrefix(definitions.eResource(), split[0]);
    					String name = split.length > 1 ? split[1] : "";
    					if (null != ns && ns.equals(namespaceForPrefix) && typeName.equals(name)) return def;
    				} else if (ExtensionHelper2.INSTANCE.isSynthetic(def)) { // synthetic stardust-class types
    					if (structureRef.toString().equals(typeName)) return def;
    				}
    			}
    		}
    	}
    	return null;
    }


}
