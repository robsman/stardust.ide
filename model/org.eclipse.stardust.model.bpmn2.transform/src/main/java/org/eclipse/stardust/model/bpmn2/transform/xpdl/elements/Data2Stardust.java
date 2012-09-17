package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements;

import java.util.List;

import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.util.ImportHelper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.builder.variable.BpmStructVariableBuilder;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;

public class Data2Stardust extends AbstractElement2Stardust {

    public Data2Stardust(ModelType carnotModel, List<String> failures) {
        super(carnotModel, failures);
    }

    public void addItemDefinition(ItemDefinition itemdef) {
        EObject structRef = (EObject)itemdef.getStructureRef();
        URI uri = ((InternalEObject)structRef).eProxyURI();
        String uriFragment = uri.fragment();

        String defId = itemdef.getId();

        Import imprt = ImportHelper.findImportForLocation((Definitions)itemdef.eContainer(), uri);

        ExternalReferenceType extReference;
        extReference = XpdlFactory.eINSTANCE.createExternalReferenceType();
        if (imprt != null) {
            extReference.setLocation(imprt.getLocation());
            extReference.setNamespace(imprt.getNamespace());
            extReference.setXref("{"+imprt.getNamespace()+"}"+uriFragment);
        }

        TypeDeclarationType declaration = XpdlFactory.eINSTANCE.createTypeDeclarationType();
        declaration.setExternalReference(extReference);
        declaration.setId(defId);
        declaration.setName(getName(itemdef, uriFragment));

        carnotModel.getTypeDeclarations().getTypeDeclaration().add(declaration);
    }

    public void addDataObject(DataObject dataObject) {
        if (dataObject == null) return;
        addStructuredVariable(dataObject, getName(dataObject));
    }

    public DataType addDataInputVariable(DataInput data) {
        if (data == null) return null;
        return addStructuredVariable(data, getName(data));
    }

    public DataType addDataOutputVariable(DataOutput data) {
        if (data == null) return null;
        return addStructuredVariable(data, getName(data));
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
        return getNonEmpty(dataObject.getName(), dataObject.getId(), dataObject);
    }

    private String getName(DataInput data) {
        return getNonEmpty(data.getName(), data.getId(), data);
    }

    private String getName(DataOutput data) {
        return getNonEmpty(data.getName(), data.getId(), data);
    }

    private String getName(ItemDefinition itemdef, String uriFragment) {
        return getNonEmpty(uriFragment, itemdef.getId(), itemdef);
    }

    private String getNonEmpty(String name, String id, Object data) {
        if (name != null && !name.isEmpty()) {
            return name;
        }
        if (id != null && !id.isEmpty()) {
            return id;
        }
        return data.toString();
    }

}
