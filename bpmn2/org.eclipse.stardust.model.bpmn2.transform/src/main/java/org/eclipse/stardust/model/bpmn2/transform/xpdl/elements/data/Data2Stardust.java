/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data;

import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.util.ImportHelper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.User;
import org.eclipse.stardust.engine.api.runtime.UserHome;
import org.eclipse.stardust.engine.api.runtime.UserPK;
import org.eclipse.stardust.engine.core.runtime.beans.IUser;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.reader.ModelInfo;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataStoreType;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmStructVariableBuilder;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.xsd.XSDSchema;

public class Data2Stardust extends AbstractElement2Stardust {

	private static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";

    public Data2Stardust(ModelType carnotModel, List<String> failures) {
        super(carnotModel, failures);
    }

    public void addItemDefinition(ItemDefinition itemdef) {

    	if (ExtensionHelper2.INSTANCE.isSynthetic(itemdef)) return;

    	Definitions defs = ModelInfo.getDefinitions(itemdef);
    	TypeDeclarationType declaration = XpdlFactory.eINSTANCE.createTypeDeclarationType();
    	XSDSchema embedded = ExtensionHelper2.INSTANCE.getEmbeddedSchemaExtension(itemdef);

    	String defId = itemdef.getId();

    	URI uri = getDataStructureURI(itemdef, defs);
    	if (null == uri) return; // ignore type-definition if no uri is available // uri = URI.createURI("");
    	String uriFragment = uri.fragment();

    	if (isXsdType(uri)) {
        	// no need to declare primitive types (if they were all mapped...)
        	return;
        } else if (isSerializable(itemdef)) {
        	return;
        } else if (null != embedded) {
        	SchemaTypeType schemaType = XpdlFactory.eINSTANCE.createSchemaTypeType();
            schemaType.setSchema(embedded);
            declaration.setSchemaType(schemaType);
        } else {
	        URI baseUri = null;
	        if (null != uri) {
	        	baseUri = uri.trimFragment();
	        }

	        Import imprt = ImportHelper.findImportForLocation((Definitions)itemdef.eContainer(), baseUri);
	        ExternalReferenceType extReference;
	        extReference = XpdlFactory.eINSTANCE.createExternalReferenceType();
	        if (imprt != null) {
	            extReference.setLocation(imprt.getLocation());
	            extReference.setNamespace(imprt.getNamespace());
	            extReference.setXref("{"+imprt.getNamespace()+"}"+uriFragment);
	            declaration.setExternalReference(extReference);
	        }
        }
		declaration.setId(defId);
		declaration.setName(getName(itemdef, uriFragment));
        carnotModel.getTypeDeclarations().getTypeDeclaration().add(declaration);
    }


//    public void addItemDefinition(ItemDefinition itemdef) {
//        if (itemdef.getStructureRef() == null) {
//        	logger.info("No structure reference for item definition " + itemdef);
//        	return;
//        }
//
//        Definitions defs = ModelInfo.getDefinitions(itemdef);
//        URI uri = getDataStructureURI(itemdef, defs);
//        if (isXsdType(uri)) {
//        	// no need to declare primitive types (if they were all mapped...)
//        	return;
//        }
//
//        URI baseUri = uri.trimFragment();
//        String uriFragment = uri.fragment();
//        String defId = itemdef.getId();
//
//        Import imprt = ImportHelper.findImportForLocation((Definitions)itemdef.eContainer(), baseUri);
//
//        ExternalReferenceType extReference;
//        extReference = XpdlFactory.eINSTANCE.createExternalReferenceType();
//        if (imprt != null) {
//            extReference.setLocation(imprt.getLocation());
//            extReference.setNamespace(imprt.getNamespace());
//            extReference.setXref("{"+imprt.getNamespace()+"}"+uriFragment);
//        }
//
//        TypeDeclarationType declaration = XpdlFactory.eINSTANCE.createTypeDeclarationType();
//        declaration.setExternalReference(extReference);
//        declaration.setId(defId);
//        declaration.setName(getName(itemdef, uriFragment));
//
//        carnotModel.getTypeDeclarations().getTypeDeclaration().add(declaration);
//    }

    public void addDataObject(DataObject dataObject) {
        if (dataObject == null) return;
        String name = getName(dataObject);
        addVariable(dataObject, name);
    }

	public void addDataStore(DataStore dataStore) {
        if (dataStore == null) return;
        String name = getName(dataStore);
        addVariable(dataStore, name);
	}

	public DataType addDataInputVariable(DataInput data) {
        if (data == null) return null;
        return addVariable(data, getName(data));
    }

    public DataType addDataOutputVariable(DataOutput data) {
        if (data == null) return null;
        return addVariable(data, getName(data));
    }

    protected DataType addVariable(ItemAwareElement bpmnData, String name) {
        if (refersToPrimitiveType(bpmnData)) {
        	return addPrimitiveVariable(bpmnData, name);
        } else if (refersToSerializableType(bpmnData)) {
        	return addSerializableVariable(bpmnData, name);
        }
        else {
       		return addStructuredVariable(bpmnData, name);
        }
	}

	protected DataType addVariable(DataStore bpmnData, String name) {
    	StardustDataStoreType extension = ExtensionHelper.getInstance().getDataStoreExtension((DataStore)bpmnData);
    	DataTypeEnum type = null;
    	if (null != extension) {
    		type = DataTypeEnum.forKey(extension.getType());
    		DataType data = null;
    		if (null != type) {
    			switch (type) {
    			case DOCUMENT:
    				data = addDocumentVariable(bpmnData, name, extension);
    				break;
    			case DOCUMENT_FOLDER:
    				data = addDocumentFolderVariable(bpmnData, name, extension);
    				break;
    			case DOCUMENT_FOLDER_LIST:
    				data = addDocumentFolderListVariable(bpmnData, name, extension);
    				break;
    			case DOCUMENT_LIST:
    				data = addDocumentListVariable(bpmnData, name, extension);
    				break;
    			case ENTITY_BEAN:
    			default:
    				break;
    			}
    			if (null != data && null != extension.getStardustAttributes()) {
    				data.getAttribute().addAll(extension.getStardustAttributes().getAttributeType());
    			}
    			carnotModel.getData().add(data);
    			return data;
    		}
    	}
    	return addStructuredVariable(bpmnData, name);
    }

    private DataType addDocumentVariable(DataStore bpmnData, String name, StardustDataStoreType extension) {
    	DataType data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
    	data.setId(bpmnData.getId());
    	data.setName(name);
    	DataTypeType dmsType = ModelUtils.findIdentifiableElement(carnotModel.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT);
    	data.setType(dmsType);
    	String typeId = getNonSyntheticTypeId(bpmnData);
    	if (null != typeId) AttributeUtil.setAttribute(data, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, typeId);
        AttributeUtil.setBooleanAttribute(data, "carnot:engine:data:bidirectional", true);
        AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT, "org.eclipse.stardust.engine.api.runtime.Document");

        return data;
	}

    private DataType addDocumentListVariable(DataStore bpmnData, String name, StardustDataStoreType extension) {
    	DataType data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
    	data.setId(bpmnData.getId());
    	data.setName(name);
    	DataTypeType dmsType = ModelUtils.findIdentifiableElement(carnotModel.getDataType(), DmsConstants.DATA_TYPE_DMS_DOCUMENT_LIST);
    	data.setType(dmsType);
    	String typeId = getNonSyntheticTypeId(bpmnData);
    	if (null != typeId) AttributeUtil.setAttribute(data, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, typeId);
        AttributeUtil.setBooleanAttribute(data, "carnot:engine:data:bidirectional", true);
        AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT, "java.util.List");

        return data;
	}

    private DataType addDocumentFolderVariable(DataStore bpmnData, String name, StardustDataStoreType extension) {
    	DataType data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
    	data.setId(bpmnData.getId());
    	data.setName(name);
    	DataTypeType dmsType = ModelUtils.findIdentifiableElement(carnotModel.getDataType(), DmsConstants.DATA_TYPE_DMS_FOLDER);
    	data.setType(dmsType);
    	String typeId = getNonSyntheticTypeId(bpmnData);
    	if (null != typeId) AttributeUtil.setAttribute(data, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, typeId);
        AttributeUtil.setBooleanAttribute(data, "carnot:engine:data:bidirectional", true);
        AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT, "org.eclipse.stardust.engine.api.runtime.Folder");

        return data;
	}

    private DataType addDocumentFolderListVariable(DataStore bpmnData, String name, StardustDataStoreType extension) {
    	DataType data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
    	data.setId(bpmnData.getId());
    	data.setName(name);
    	DataTypeType dmsType = ModelUtils.findIdentifiableElement(carnotModel.getDataType(), DmsConstants.DATA_TYPE_DMS_FOLDER_LIST);
    	data.setType(dmsType);
    	String typeId = getNonSyntheticTypeId(bpmnData);
    	if (null != typeId) AttributeUtil.setAttribute(data, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT, typeId);
        AttributeUtil.setBooleanAttribute(data, "carnot:engine:data:bidirectional", true);
        AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT, "java.util.List");

        return data;
	}

	private DataType addSerializableVariable(ItemAwareElement bpmnData, String name) {
    	DataType data = CarnotWorkflowModelFactory.eINSTANCE.createDataType();
    	data.setId(bpmnData.getId());
    	data.setName(name);
    	DataTypeType serializableType = ModelUtils.findIdentifiableElement(carnotModel.getDataType(), PredefinedConstants.SERIALIZABLE_DATA);
    	data.setType(serializableType);
    	try {
    		URI dataStructureURI = getDataStructureURI(bpmnData);
    		if (IUser.class.getName().equals(dataStructureURI.toString())) {
    			return addAsUserBeanVariable(data);
    		}
    		String cls = bpmnData.getItemSubjectRef().getStructureRef().toString();
    		AttributeUtil.setAttribute(data, PredefinedConstants.CLASS_NAME_ATT, cls);
    	} catch (Exception e) {
    		failures.add("addSerializableVariable failed setting class attribute for bpmn Data " + data);
    	}
    	carnotModel.getData().add(data);
        return data;
	}

    private DataType addAsUserBeanVariable(DataType data) {
        DataTypeType dataTypeType = (DataTypeType) ModelUtils.findIdentifiableElement(carnotModel.getDataType(), PredefinedConstants.ENTITY_BEAN_DATA);
        data.setType(dataTypeType);

        AttributeUtil.setBooleanAttribute(data, PredefinedConstants.BROWSABLE_ATT, Boolean.TRUE);
        AttributeUtil.setAttribute(data, PredefinedConstants.HOME_INTERFACE_ATT, UserHome.class.getName());
        AttributeUtil.setBooleanAttribute(data, PredefinedConstants.IS_LOCAL_ATT, Boolean.TRUE);
        AttributeUtil.setAttribute(data, PredefinedConstants.JNDI_PATH_ATT, User.class.getName());
        AttributeUtil.setAttribute(data, PredefinedConstants.PRIMARY_KEY_ATT, UserPK.class.getName());
        AttributeUtil.setAttribute(data, PredefinedConstants.REMOTE_INTERFACE_ATT, IUser.class.getName());
        carnotModel.getData().add(data);
        return data;
	}

	private DataType addStructuredVariable(ItemAwareElement data, String name) {
        BpmStructVariableBuilder builder = BpmModelBuilder.newStructVariable(carnotModel);
        builder.setTypeDeclarationModel(carnotModel);
        String typeId = getTypeId(data);

        DataType dataType = //builder.forModel(carnotModel)
                builder.withIdAndName(data.getId(), name)
                        .ofType(typeId)
                        .build();
        dataType.setPredefined(false);

        AttributeUtil.setAttribute(dataType, PredefinedConstants.MODELELEMENT_VISIBILITY, "Public");
        AttributeUtil.setAttribute(dataType, "carnot:engine:path:separator", StructuredDataConstants.ACCESS_PATH_SEGMENT_SEPARATOR);
        AttributeUtil.setBooleanAttribute(dataType, "carnot:engine:data:bidirectional", true);
        return dataType;
    }

    private DataType addPrimitiveVariable(ItemAwareElement data, String name) {

    	URI uri = getDataStructureURI(data);
        if (uri == null) return null;
        String typeName = uri.fragment();

        XSDType2Stardust typeMap = XSDType2Stardust.byXsdName(typeName);
        if (typeMap == null) {
        	failures.add("Unknown primitive Datatype " + uri.toString() + " - No variable created for data element: " + data);
        	return null;
        }

        DataType variable =
        		BpmModelBuilder.newPrimitiveVariable(carnotModel)
        			.withIdAndName(data.getId(), name)
        			.ofType(typeMap.getType())
        			.build();
        variable.setPredefined(false);

        AttributeUtil.setAttribute(variable, PredefinedConstants.MODELELEMENT_VISIBILITY, "Public");
        // TODO carnot:engine:defaultValue
        // TODO authorization:data.readDataValues - __carnot_internal_all_permissions__
    	return variable;
    }

    public static boolean refersToPrimitiveType(ItemAwareElement data) {
        URI typeUri = getDataStructureURI(data);
        if (typeUri == null) return false;
        return isXsdType(typeUri);
    }

    public static boolean refersToPrimitiveType(ItemDefinition data) {
    	Definitions defs = ModelInfo.getDefinitions(data);
        URI typeUri = getDataStructureURI(data, defs);
        if (typeUri == null) return false;
        return isXsdType(typeUri);
    }

    private static boolean refersToSerializableType(ItemAwareElement data) {
    	ItemDefinition itemSubjectRef = data.getItemSubjectRef();
    	if (null == itemSubjectRef) {
    		return false;
    	}
        return isSerializable(itemSubjectRef);
    }

    private static boolean isXsdType(URI typeUri) {
    	if (typeUri == null) return false;
    	URI baseUri = typeUri.trimFragment();
        return baseUri.toString().equals(XML_SCHEMA_URI);
    }

	private static boolean isSerializable(ItemDefinition itemDef) {
		java.net.URI uri = null;
		if (null == itemDef || null == itemDef.getStructureRef()) return false;
		if (!refersToPrimitiveType(itemDef)) {
			try {
				Definitions defs = ModelInfo.getDefinitions(itemDef);
				URI structureURI = getDataStructureURI(itemDef, defs);
				uri = new java.net.URI(structureURI.toString());
			} catch (Exception e) {
				return false;
			}
			return null == uri || !uri.isAbsolute();
		}
		return false;
	}

    private static URI getDataStructureURI(ItemAwareElement data) {
    	if (data == null) return null;
    	ItemDefinition itemDef = data.getItemSubjectRef();
    	Definitions defs = ModelInfo.getDefinitions(data);
        return getDataStructureURI(itemDef, defs);
    }

    private static URI getDataStructureURI(ItemDefinition itemDef, Definitions defs) {
    	if (itemDef == null) return null;
    	if (itemDef.eIsProxy()) {
    		itemDef = Bpmn2ProxyResolver.resolveItemDefinition(itemDef, defs);
    	}
    	//EObject structureRef = (EObject)itemDef.getStructureRef();
    	EObject structureRef = (EObject)itemDef.eGet(Bpmn2Package.Literals.ITEM_DEFINITION__STRUCTURE_REF);
    	if (structureRef == null) return null;

        return ((InternalEObject)structureRef).eProxyURI();
    }

    private String getNonSyntheticTypeId(ItemAwareElement data) {
        String typeId = null;
        ItemDefinition itemDef = null;
        if (data.getItemSubjectRef() != null) {
            if (data.getItemSubjectRef().eIsProxy()) {
            	itemDef = Bpmn2ProxyResolver.resolveItemDefinition(data.getItemSubjectRef(), ModelInfo.getDefinitions(data));
            } else {
            	itemDef = data.getItemSubjectRef();
            }
            if (null == itemDef) return null;
            if (ExtensionHelper2.INSTANCE.isSynthetic(itemDef)) return null;
            return itemDef.getId();
        }
        return typeId;
    }

    private String getTypeId(ItemAwareElement data) {
        String typeId =
                data.getItemSubjectRef() != null
                ? (data.getItemSubjectRef().eIsProxy())
                        ? ((InternalEObject)data.getItemSubjectRef()).eProxyURI().fragment()
                        : data.getItemSubjectRef().getId()
                : "";
        return typeId;
    }

    private String getName(DataObject dataObject) {
    	String name = dataObject.getName();
    	if (dataObject.getName() == null || dataObject.getName().trim().isEmpty()) {
    		name = findDataObjectReferenceName(dataObject);
    	}
        return getNonEmptyName(name, dataObject.getId(), dataObject);
    }

    private String getName(DataStore dataStore) {
    	String name = dataStore.getName();
    	if (dataStore.getName() == null || dataStore.getName().trim().isEmpty()) {
    		name = findDataStoreReferenceName(dataStore);
    	}
        return getNonEmptyName(name, dataStore.getId(), dataStore);
    }

	private String getName(DataInput data) {
        return getNonEmptyName(data.getName(), data.getId(), data);
    }

    private String getName(DataOutput data) {
        return getNonEmptyName(data.getName(), data.getId(), data);
    }

    private String getName(ItemDefinition itemdef, String uriFragment) {
        return getNonEmptyName(uriFragment, itemdef.getId(), itemdef);
    }

    private String findDataObjectReferenceName(DataObject dataObject) {
    	List<DataObjectReference> refs = ModelInfo.getDataObjectReferencesTo(dataObject);
    	for (DataObjectReference ref : refs) {
    		if (ref.getName() != null && !ref.getName().trim().isEmpty()) return ref.getName();
    	}
		return null;
	}

    private String findDataStoreReferenceName(DataStore dataStore) {
    	List<DataStoreReference> refs = ModelInfo.getDataStoreReferencesTo(dataStore);
    	for (DataStoreReference ref : refs) {
    		if (ref.getName() != null && !ref.getName().trim().isEmpty()) return ref.getName();
    	}
		return null;
	}


//    private String getNonEmpty(String name, String id, Object data) {
//        if (name != null && !name.isEmpty()) {
//            return name;
//        }
//        if (id != null && !id.isEmpty()) {
//            return id;
//        }
//        return String.valueOf(data.hashCode());
//    }

}
