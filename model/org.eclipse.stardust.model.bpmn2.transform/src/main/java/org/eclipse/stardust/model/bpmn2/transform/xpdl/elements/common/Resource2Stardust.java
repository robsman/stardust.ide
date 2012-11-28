package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Resource;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustResourceType;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class Resource2Stardust extends AbstractElement2Stardust {

	public Resource2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addResource(Resource resource) {
		/* In BPMN2, a Resource is assigned to activities (e.g. as ResourceRole 'Performer').
		 * Runtime data used for assignment is defined in a resource assignment expression in BPMN2.
		 * Stardust stores this information (incl. variables) in the reusable participant element.
		 * Therefore, we expect the whole definition (participant) to be in an extension element of the resource,
		 * while ommiting the BPMN2-assignment expressions.
		 * */
		Bpmn2StardustXPDLExtension.addResourceExtension(resource, carnotModel);
	}

	public void setConditionalPerformerData(Definitions defs) {
		// During load and transformation of the bpmn model, the references to data cannot be resolved.
		// Thus, the id of the data element is stored and resolved finally
		List<ConditionalPerformerType> conditionalPerformers = carnotModel.getConditionalPerformer();
		for (int i = 0; i < conditionalPerformers.size(); i++) {
			ConditionalPerformerType performer = conditionalPerformers.get(i);
			if (performer.getData() == null) {
				Resource res = BpmnModelQuery.findResource(defs, performer.getId());
				if (res != null) {
					StardustResourceType sdRes = ExtensionHelper.getInstance().getResourceExtension(res);
					if (sdRes != null) {
						String dataId = sdRes.getDataId();
						if (dataId != null && !dataId.isEmpty()) {
							DataType data = query.findVariable(dataId);
							if (data != null) {
								performer.setData(data);
							}
						}
					}
				}
			}
		}
	}

}
