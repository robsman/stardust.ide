package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.ItemKind;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

public enum StardustApplicationConfigurationGenerator {

	INSTANCE;
	
	public void generateAccessPointInfos(EObject object, Method method, Constructor<?> constructor) {
		generateAccessPointInfos(object, new Method[]{method}, new Constructor<?>[]{constructor});
	}
	
	public void generateAccessPointInfos(EObject object, Method[] methods, Constructor<?>[] constructors) {
		if ((null == methods || methods.length <= 0) && (null == constructors || constructors.length <= 0)) return;
		
		Definitions definitions = ModelUtil.getDefinitions(object.eResource());
		ItemDefinition inputItemDef = createItemDef(object);
		ItemDefinition outputItemDef = createItemDef(object);

		try {
			IntrinsicJavaAccessPointInfo.addInputAccessPointItemDefinitionSchema(inputItemDef, methods, constructors);
			IntrinsicJavaAccessPointInfo.addOutputAccessPointItemDefinitionSchema(outputItemDef, methods, constructors);
			
			insertStructureReferences(inputItemDef, outputItemDef);
			
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | MalformedURLException
				| CoreException e) {
			e.printStackTrace();
		}
		definitions.getRootElements().add(inputItemDef);
		definitions.getRootElements().add(outputItemDef);
		
		if (object instanceof StardustInterfaceType) {
			StardustInterfaceType interf = (StardustInterfaceType) object;
			// Fill in values for ImplementationRef in the implementRef Property
			populateBPMN2Values(interf, outputItemDef, inputItemDef);
		} 
	}
	
	public void generateAccessPointInfos(EObject object, IMethod...methodAndConstructor) {
		if (null == methodAndConstructor || methodAndConstructor.length <= 0) return;
		Definitions definitions = ModelUtil.getDefinitions(object.eResource());
		ItemDefinition inputItemDef = createItemDef(object);
		ItemDefinition outputItemDef = createItemDef(object);

		try {
			IntrinsicJavaAccessPointInfo.addInputAccessPointItemDefinitionSchema(inputItemDef, methodAndConstructor);
			IntrinsicJavaAccessPointInfo.addOutputAccessPointItemDefinitionSchema(outputItemDef, methodAndConstructor);
			
			insertStructureReferences(inputItemDef, outputItemDef);
			
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | MalformedURLException
				| CoreException e) {
			e.printStackTrace();
		}
		definitions.getRootElements().add(inputItemDef);
		definitions.getRootElements().add(outputItemDef);
		
		if (object instanceof StardustInterfaceType) {
			StardustInterfaceType interf = (StardustInterfaceType) object;
			// Fill in values for ImplementationRef in the implementRef Property
			populateBPMN2Values(interf, outputItemDef, inputItemDef);
		} 
	}
	
	private void insertStructureReferences(ItemDefinition inputItemDef, ItemDefinition outputItemDef) {
		if (null != inputItemDef.getStructureRef()) {
			DynamicEObjectImpl ref = (DynamicEObjectImpl)inputItemDef.getStructureRef();
			URI uri = ref.eProxyURI();
			EObject wrapper = ModelUtil.createStringWrapper(uri.toString());
			inputItemDef.setStructureRef(wrapper);
		}
		if (null != outputItemDef.getStructureRef()) {
			DynamicEObjectImpl ref = (DynamicEObjectImpl)outputItemDef.getStructureRef();
			URI uri = ref.eProxyURI();
			EObject wrapper = ModelUtil.createStringWrapper(uri.toString());
			outputItemDef.setStructureRef(wrapper);
		}	
	}

	private ItemDefinition createItemDef(EObject object) {
		ItemDefinition itemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
		itemDef.setItemKind(ItemKind.INFORMATION);
		String generateID = ModelUtil.generateID(itemDef, object.eResource(), object.eClass().getName());
		itemDef.setId(generateID);
		return itemDef;
	}

	private void populateBPMN2Values(StardustInterfaceType sdInterface, ItemDefinition outputItemDef, ItemDefinition inputItemDef) {
		Interface interf = (Interface) sdInterface.eContainer().eContainer();
		interf.setImplementationRef(sdInterface); //sdInterface.getStardustApplication());
		Definitions definitions = ModelUtil.getDefinitions(interf);
		if (null != interf.getOperations()) {
			// Create new Operation object and add it to the interface
			interf.getOperations().clear();
			Operation op = Bpmn2Factory.eINSTANCE.createOperation();
			String opId = ModelUtil.setID(op);
			//TODO find a better name for the operation
			op.setName(opId);
			op.setImplementationRef(interf.getImplementationRef());
			// Create inMsg and populate it, add it to the operation
			Message inMsg = Bpmn2Factory.eINSTANCE.createMessage();
			definitions.getRootElements().add(inMsg);
			String inMsgId = ModelUtil.setID(inMsg); 
			inMsg.setName(inMsgId);
			inMsg.setItemRef(inputItemDef);	
			op.setInMessageRef(inMsg);
			// Create inMsg and populate it, add it to the operation				
			Message outMsg = Bpmn2Factory.eINSTANCE.createMessage();				
			definitions.getRootElements().add(outMsg);	
			String outMsgId = ModelUtil.setID(outMsg);
			outMsg.setName(outMsgId);
			outMsg.setItemRef(outputItemDef);	
			op.setOutMessageRef(outMsg);
			// Add newly created operation to the Interface
			interf.getOperations().add(op);
		}

	}	
	
}
