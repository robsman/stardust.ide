/*******************************************************************************
 * Copyright (c) 2011, 2012 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Assignment;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.HumanPerformer;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.modeler.core.IBpmn2RuntimeExtension;
import org.eclipse.bpmn2.modeler.core.LifecycleEvent;
import org.eclipse.bpmn2.modeler.core.LifecycleEvent.EventType;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.PropertiesCompositeFactory;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustActivityInputDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustActivityOutputDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustCommonEventDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustDataAssignmentDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustDataAssociationDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustInterfaceDefinitionDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustTaskDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustTimerEventDefinitionDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.StardustDataObjectDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.StardustDataStoreDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.type.StardustItemDefinitionDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer.StardustResourceDetailsComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer.StardustResourceParameterBindingDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer.StardustResourceRoleDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer.conditional.ConditionalPerformerDetailsComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.task.StardustUserTaskDetailComposite;
import org.eclipse.bpmn2.modeler.ui.DefaultBpmn2RuntimeExtension.RootElementParser;
import org.eclipse.bpmn2.modeler.ui.wizards.FileService;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.ui.IEditorInput;
import org.xml.sax.InputSource;

/**
 * @author Bob Brodt
 * @param <ExtensionsPackageImpl>
 *
 */

public class StardustRuntimeExtension<ExtensionsPackageImpl> implements IBpmn2RuntimeExtension {

	public static final String RUNTIME_ID = "org.eclipse.bpmn2.modeler.runtime.stardust";

	private static final String targetNamespace = "http://org.eclipse.bpmn2.modeler.runtime.stardust";

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.IBpmn2RuntimeExtension#getTargetNamespace(org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType)
	 */
	@Override
	public String getTargetNamespace(Bpmn2DiagramType diagramType) {
		return targetNamespace;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.IBpmn2RuntimeExtension#isContentForRuntime(org.eclipse.bpmn2.modeler.core.IFile)
	 */
	@Override
	public boolean isContentForRuntime(IEditorInput input) {
		InputSource source = new InputSource( FileService.getInputContents(input) );
		RootElementParser parser = new RootElementParser(targetNamespace);
		parser.parse(source);
		return parser.getResult();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.IBpmn2RuntimeExtension#notify(org.eclipse.bpmn2.modeler.core.LifecycleEvent)
	 */
	@Override
	public void notify(LifecycleEvent event) {
		if (event.eventType.equals(EventType.EDITOR_INITIALIZED)) {

			/*
			 * Register our own Detail Composite class (Property Sheet
			 * composite) to handle Timer Event Definitions. This is necessary
			 * because Event Definitions are not first-class citizens of the
			 * tabbed property sheet hierarchy. The only way these property
			 * sheets can be instantiated are as detail sections of a List section
			 * {@see AbstractListComposite#showDetails(boolean)}
			 * Except in the case when an Event Definition object is selected
			 * from the Outline view.
			 * TODO: this needs to be unified so we have one-stop shopping for
			 * these detail composites.
			 */
			PropertiesCompositeFactory.register(TimerEventDefinition.class,
					StardustTimerEventDefinitionDetailComposite.class);
			PropertiesCompositeFactory.register(Interface.class, StardustInterfaceDefinitionDetailComposite.class);

			PropertiesCompositeFactory.register(Resource.class, StardustResourceDetailsComposite.class);
			PropertiesCompositeFactory.register(ConditionalPerformerType.class, ConditionalPerformerDetailsComposite.class);

			PropertiesCompositeFactory.register(Performer.class, StardustResourceRoleDetailComposite.class);
			PropertiesCompositeFactory.register(HumanPerformer.class, StardustResourceRoleDetailComposite.class);
			PropertiesCompositeFactory.register(PotentialOwner.class, StardustResourceRoleDetailComposite.class);

			PropertiesCompositeFactory.register(Assignment.class, StardustDataAssignmentDetailComposite.class);

			PropertiesCompositeFactory.register(ResourceParameterBinding.class, StardustResourceParameterBindingDetailComposite.class);


	        PropertiesCompositeFactory.register(DataInput.class, StardustDataAssociationDetailComposite.class);
	        PropertiesCompositeFactory.register(DataOutput.class, StardustDataAssociationDetailComposite.class);
	        PropertiesCompositeFactory.register(Activity.class, StardustActivityInputDetailComposite.class);
	        PropertiesCompositeFactory.register(Activity.class, StardustActivityOutputDetailComposite.class);
	        PropertiesCompositeFactory.register(Task.class, StardustTaskDetailComposite.class);
	        PropertiesCompositeFactory.register(UserTask.class, StardustUserTaskDetailComposite.class);

	        PropertiesCompositeFactory.register(DataObject.class, StardustDataObjectDetailComposite.class);
	        PropertiesCompositeFactory.register(DataObjectReference.class, StardustDataObjectDetailComposite.class);

	        PropertiesCompositeFactory.register(DataStore.class, StardustDataStoreDetailComposite.class);
	        PropertiesCompositeFactory.register(DataStoreReference.class, StardustDataStoreDetailComposite.class);

	        PropertiesCompositeFactory.register(ItemDefinition.class, StardustItemDefinitionDetailComposite.class);

	        PropertiesCompositeFactory.register(Event.class, StardustCommonEventDetailComposite.class);

		}
	}
}
