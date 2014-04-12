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
package org.eclipse.stardust.test.model.transformation.bpmn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.annotation.Resource;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.TransitionUtil;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.ui.web.modeler.common.ModelRepository;
import org.eclipse.stardust.ui.web.modeler.edit.model.ModelConversionService;
import org.eclipse.stardust.ui.web.modeler.utils.test.MockModelRepositoryManager;
import org.eclipse.stardust.ui.web.modeler.utils.test.MockServiceFactoryLocator;
import org.eclipse.stardust.ui.web.modeler.xpdl.XpdlPersistenceHandler;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Simon Nikles
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"../../../../ui/web/modeler/web-modeler-test-context.xml"})
public abstract class Bpmn2StardustTestSuite {

    //public static final String TEST_BPMN_MODEL_DIR = "models/bpmn/";
    public static final String TEST_ID_START_EVENT = "TestModelStartEventId";
    public static final String TEST_ID_START_EVENT_TIMER_DATE = "TestModelTimerStartEventTime";
    public static final String TEST_ID_START_EVENT_TIMER_CYCLE_STOP = "TestModelStartEventCycleStop";

    public static final String TEST_ID_TASK_A = "TestModelTaskA";
    public static final String TEST_NAME_TASK_A = "a";
    public static final String TEST_ID_TASK_B = "TestModelTaskB";
    public static final String TEST_NAME_TASK_B = "b";
    public static final String TEST_ID_TASK_C = "TestModelTaskC";
    public static final String TEST_ID_TASK_D = "TestModelTaskD";
    public static final String TEST_ID_TASK_E = "TestModelTaskE";
    public static final String TEST_ID_SEQUENCE_A_TO_B = "TestModelSequenceAtoB";
    public static final String TEST_ID_CONDITIONAL_SEQUENCE = "TestModelConditionalSequenceFlow1";
    public static final String TEST_ID_DEFAULT_SEQUENCE = "TestModelDefaultSequenceFlow";

    public static final String TEST_ID_XOR_MIXED_GATEWAY = "TestModelXORMixedGateway";
    public static final String TEST_ID_SEQUENCE_B2GATE = "SeqB2Gate";
    public static final String TEST_ID_SEQUENCE_C2GATE = "SeqC2Gate";
    public static final String TEST_ID_SEQUENCE_GATE2D = "SeqGate2D";
    public static final String TEST_ID_SEQUENCE_GATE2E = "SeqGate2E";

    public static final String TEST_ID_SUBPROCESS = "TestModelSubProcess";
    public static final String TEST_ID_MAIN_PROCESS = "TestModelMainProcess";
    public static final String TEST_ID_PARTNER_ENTITY_ORG_A = "TestOrganisationA";
    public static final String TEST_ID_RESOURCE_ROLE_A = "TestPerformerRoleA";

    @Resource
    MockServiceFactoryLocator mockServiceFactoryLocator;

    @Resource
    MockModelRepositoryManager mockModelRepositoryManager;

    @Resource
    private ModelConversionService modelConversionService;

    @Resource
    private XpdlPersistenceHandler persistenceHandler;

    @Resource
    private ModelRepository modelRepository;

    private final String modelsPackage;

    protected Bpmn2StardustTestSuite() {
       this("models/bpmn");
    }

    protected Bpmn2StardustTestSuite(String modelsPackage) {
      this.modelsPackage = modelsPackage;
    }

    @Before
    public void initModelRepository() throws IOException {
        mockModelRepositoryManager.loadModelsFromPackage(modelsPackage);
    }


    public ModelType transformModel(Definitions definitions) {
        try {
            EObject xpdlModel = modelConversionService.convertModel(definitions, "xpdl");
            debugToFile((ModelType)xpdlModel);
            return (ModelType)xpdlModel;
        } catch (RuntimeException rte) {
            throw rte;
        } catch (Exception e) {
            throw new RuntimeException("Failed transforming model.", e);
        }
    }

    private void debugToFile(ModelType xpdlModel) {
        try {
        	String path = TestSequences2Stardust.class.getClassLoader().getResource("").getPath();
        	FileOutputStream target = new FileOutputStream(new File(path+"/"+xpdlModel.getName()+".xpdl"));
        	persistenceHandler.saveModel(xpdlModel, target);
//        	System.out.println(path);
//            target.write(XpdlModelIoUtils.saveModel(xpdlModel));
//            target.flush();
            target.close();
        } catch (FileNotFoundException e) {
        	throw new RuntimeException("Failed write model output.", e);
        } catch (IOException e) {
        	throw new RuntimeException("Failed write model output.", e);
        }

	}

	public Definitions loadBpmnModel(String bpmnFile) {
        Definitions definitions = null;
        for (EObject model : modelRepository.getAllModels()) {
            if (bpmnFile.equals(modelRepository.getModelFileName(model))) {
                definitions = (Definitions) model;
                break;
            }
        }

        return definitions;
    }

    public Definitions loadBpmnModelWithXsd(String bpmnFile) throws FileNotFoundException, IOException {
       Definitions definitions = null;
       for (EObject model : modelRepository.getAllModels()) {
           if (bpmnFile.equals(modelRepository.getModelFileName(model))) {
               definitions = (Definitions) model;
               break;
           }
       }

       return definitions;
    }

    public static String getResourceFilePath(String relativePath) {
        URL fileUri = Bpmn2StardustTestSuite.class.getClassLoader().getResource(relativePath);
        return fileUri.getPath();
    }


    public static boolean transitionExistsBetween(ActivityType source, ActivityType target) {
    	for (TransitionType trans : source.getOutTransitions()) {
    		if (trans.getTo() == target) return true;
    	}
    	return false;
    }

    public static String transitionConditionBetween(ActivityType source, ActivityType target) {
    	String condition = "";
    	for (TransitionType trans : source.getOutTransitions()) {
    		if (trans.getTo() == target) {
    			condition = TransitionUtil.getTransitionExpression(trans);
    			break;
    		}
    	}
    	return condition;
    }

    public static boolean otherwiseConditionBetween(ActivityType source, ActivityType target) {
    	for (TransitionType trans : source.getOutTransitions()) {
    		if (trans.getTo() == target) {
    			return TransitionUtil.hasOtherwiseCondition(trans);
    		}
    	}
    	return false;
    }

    public static boolean noneIsNull(IModelElement ... elements) {
    	for (IModelElement element : elements) {
    		if (null == element) {
    			return false;
    		}
    	}
    	return true;
    }

    public static EventActionType getFirstEventActionOfType(EventHandlerType handler, EventActionTypeType actionType) {
    	for (EventActionType eventAction : handler.getEventAction()) {
    		if (eventAction.getType().equals(actionType)) {
    			return eventAction;
    		}
    	}
    	return null;
    }

    public static boolean eventHasActionType(EventHandlerType handler, EventActionTypeType actionType) {
    	for (EventActionType eventAction : handler.getEventAction()) {
    		if (eventAction.getType().equals(actionType)) {
    			return true;
    		}
    	}
    	return false;
    }

}
