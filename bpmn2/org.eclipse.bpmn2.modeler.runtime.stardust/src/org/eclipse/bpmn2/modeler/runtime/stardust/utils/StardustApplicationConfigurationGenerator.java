package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.ItemKind;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.accesspoint.AccessPointInfoProvider;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.accesspoint.IntrinsicJavaAccessPointInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.stardust.model.bpmn2.extension.AccessPointSchemaWrapper.Direction;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * @author Simon Nikles
 *
 */
public enum StardustApplicationConfigurationGenerator {

	INSTANCE;

	public void generateAccessPointInfos(StardustContextType appCtx) {
		Definitions definitions = ModelUtil.getDefinitions(appCtx.eResource());
		ItemDefinition inputItemDef = findOrCreateItemDef(appCtx, Direction.IN);
		ItemDefinition outputItemDef = findOrCreateItemDef(appCtx, Direction.OUT);
		inputItemDef.getExtensionValues().clear();
		outputItemDef.getExtensionValues().clear();
		try {
			AccessPointInfoProvider.addInputAccessPointItemDefinitionSchema(appCtx, inputItemDef);
			AccessPointInfoProvider.addOutputAccessPointItemDefinitionSchema(appCtx, outputItemDef);
			insertStructureReferences(inputItemDef, outputItemDef);
		} catch(Exception e) {
			e.printStackTrace();
		}
		definitions.getRootElements().add(inputItemDef);
		definitions.getRootElements().add(outputItemDef);
		updateBPMN2Values((StardustInterfaceType)appCtx.eContainer().eContainer(), outputItemDef, inputItemDef);
	}

	public void generateAccessPointInfos(StardustApplicationType appType) {
		Definitions definitions = ModelUtil.getDefinitions(appType.eResource());
		ItemDefinition inputItemDef = findOrCreateItemDef(appType, Direction.IN);
		ItemDefinition outputItemDef = findOrCreateItemDef(appType, Direction.OUT);
		inputItemDef.getExtensionValues().clear();
		outputItemDef.getExtensionValues().clear();
		try {
			AccessPointInfoProvider.addInputAccessPointItemDefinitionSchema(appType, inputItemDef);
			AccessPointInfoProvider.addOutputAccessPointItemDefinitionSchema(appType, outputItemDef);
			insertStructureReferences(inputItemDef, outputItemDef);
		} catch(Exception e) {
			e.printStackTrace();
		}
		definitions.getRootElements().add(inputItemDef);
		definitions.getRootElements().add(outputItemDef);
		updateBPMN2Values((StardustInterfaceType)appType.eContainer(), outputItemDef, inputItemDef);
	}

	public void generateAccessPointInfos(EObject object, Method method, Constructor<?> constructor) {
		Constructor<?>[] constructors = null != constructor ? new Constructor<?>[]{constructor} : new Constructor<?>[]{};
		Method[] methods = null != method ? new Method[]{method} : new Method[]{};
		generateAccessPointInfos(object, methods, constructors);
	}
	
	public void generateAccessPointInfos(EObject object, Method[] methods, Constructor<?>[] constructors) {
		if ((null == methods || methods.length <= 0) && (null == constructors || constructors.length <= 0)) return;
		
		Definitions definitions = ModelUtil.getDefinitions(object.eResource());
		ItemDefinition inputItemDef = createItemDef(object);
		ItemDefinition outputItemDef = createItemDef(object);
		String ownerId = null;
		if (object instanceof StardustInterfaceType && null != ((StardustInterfaceType) object).getStardustApplication()) {
			ownerId = ((StardustInterfaceType) object).getStardustApplication().getId();
		}

		try {
			IntrinsicJavaAccessPointInfo.addInputAccessPointItemDefinitionSchema(ownerId, inputItemDef, methods, constructors);
			IntrinsicJavaAccessPointInfo.addOutputAccessPointItemDefinitionSchema(ownerId, outputItemDef, methods, constructors);
			
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
		String ownerId = null;
		if (object instanceof StardustInterfaceType && null != ((StardustInterfaceType) object).getStardustApplication()) {
			ownerId = ((StardustInterfaceType) object).getStardustApplication().getId();
		}
		
		try {
			IntrinsicJavaAccessPointInfo.addInputAccessPointItemDefinitionSchema(ownerId, inputItemDef, methodAndConstructor);
			IntrinsicJavaAccessPointInfo.addOutputAccessPointItemDefinitionSchema(ownerId, outputItemDef, methodAndConstructor);
			
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
			System.out
					.println("StardustApplicationConfigurationGenerator.insertStructureReferences() " + inputItemDef.getStructureRef());
			DynamicEObjectImpl ref = (DynamicEObjectImpl)inputItemDef.getStructureRef();
			URI uri = ref.eProxyURI();
			final String uriStr = null != uri ? uri.toString() : "";
			final EObject wrapper = ModelUtil.createStringWrapper(uriStr);
			inputItemDef.setStructureRef(wrapper);
		}
		if (null != outputItemDef.getStructureRef()) {
			System.out
			.println("StardustApplicationConfigurationGenerator.insertStructureReferences() " + inputItemDef.getStructureRef());
			DynamicEObjectImpl ref = (DynamicEObjectImpl)outputItemDef.getStructureRef();
			URI uri = ref.eProxyURI();
			final String uriStr = null != uri ? uri.toString() : "";
			final EObject wrapper = ModelUtil.createStringWrapper(uriStr);
			outputItemDef.setStructureRef(wrapper);
		}	
	}

	private ItemDefinition findOrCreateItemDef(StardustApplicationType appType, Direction direction) {
		StardustInterfaceType sdiface = (StardustInterfaceType)appType.eContainer();
		Interface iface = (Interface)sdiface.eContainer().eContainer();
		List<Operation> operations = iface.getOperations();
		Message msg = null;
		for (Operation op: operations) {
			if (Direction.IN.equals(direction)) {
				msg = op.getInMessageRef();
			} else {
				msg = op.getOutMessageRef();
			}
		}
		if (null != msg) {
			if (null != msg.getItemRef()) return msg.getItemRef();
		}
		return createItemDef(appType);
	}

	private ItemDefinition findOrCreateItemDef(StardustContextType appCtx, Direction direction) {
		StardustInterfaceType sdiface = (StardustInterfaceType)appCtx.eContainer().eContainer();
		Interface iface = (Interface)sdiface.eContainer().eContainer();
		List<Operation> operations = iface.getOperations();
		Message msg = null;
		for (Operation op: operations) {
			if (Direction.IN.equals(direction)) {
				msg = op.getInMessageRef();
			} else {
				msg = op.getOutMessageRef();
			}
		}
		if (null != msg) {
			if (null != msg.getItemRef()) return msg.getItemRef();
		}
		return createItemDef(appCtx);
	}

	private ItemDefinition createItemDef(EObject object) {
		ItemDefinition itemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
		itemDef.setItemKind(ItemKind.INFORMATION);
		String generateID = ModelUtil.generateID(itemDef, object.eResource(), ItemDefinition.class.getSimpleName()); // object.eClass().getName());
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

	private void updateBPMN2Values(StardustInterfaceType sdInterface, ItemDefinition outputItemDef, ItemDefinition inputItemDef) {
		Interface interf = (Interface) sdInterface.eContainer().eContainer();
		interf.setImplementationRef(sdInterface); //sdInterface.getStardustApplication());
		Definitions definitions = ModelUtil.getDefinitions(interf);
		if (null != interf.getOperations()) {
			Operation op = null;
			if (interf.getOperations().size() > 0) {
				op = interf.getOperations().get(0); 
			} else {
				op = Bpmn2Factory.eINSTANCE.createOperation();
				ModelUtil.setID(op);
				op.setName(((Interface)sdInterface.eContainer().eContainer()).getName() + "_Operation");
				op.setImplementationRef(interf.getImplementationRef());
				interf.getOperations().add(op);
			}
			
			Message mIn = null;
			if (null != op && null != op.getInMessageRef()) {
				mIn = op.getInMessageRef();
			} else {
				mIn = Bpmn2Factory.eINSTANCE.createMessage();
				definitions.getRootElements().add(mIn);
				ModelUtil.setID(mIn); 
				mIn.setName(op.getName() + "_Input");
				mIn.setItemRef(inputItemDef);	
				op.setInMessageRef(mIn);
			}
			
			Message mOut = null;
			if (null != op && null != op.getOutMessageRef()) {
				mOut = op.getOutMessageRef();
			} else {
				mOut = Bpmn2Factory.eINSTANCE.createMessage();
				definitions.getRootElements().add(mOut);
				ModelUtil.setID(mOut); 
				mOut.setName(op.getName() + "_Output");
				mOut.setItemRef(outputItemDef);	
				op.setOutMessageRef(mOut);
			}
			
		}

	}	

}
