package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class AbstractElement2Stardust {

    protected final Logger logger = Logger.getLogger(this.getClass());
    protected final CarnotModelQuery query;
    protected final ModelType carnotModel;
    protected final List<String> failures;

    public AbstractElement2Stardust(ModelType carnotModel, List<String> failures) {
        this.carnotModel = carnotModel;
        this.query = new CarnotModelQuery(carnotModel);
        this.failures = failures;
    }

    protected ProcessDefinitionType getProcessOrReportFailure(FlowElement element, FlowElementsContainer container) {
    	ProcessDefinitionType process = query.findProcessDefinition(container.getId());
    	if (process == null) {
    		failures.add(Bpmn2StardustXPDL.FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + " for element " + element.getId() + ", " + element.getName() + ")");
    	}
    	return process;
    }

}