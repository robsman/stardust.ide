package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

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

}
