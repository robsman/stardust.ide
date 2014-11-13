/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer;

import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.ResourceParameterBinding;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustResourceParameterBindingDetailComposite extends DefaultDetailComposite {

	public StardustResourceParameterBindingDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustResourceParameterBindingDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(final EObject be) {
		//super.createBindings(be);
		// do nothing - show nothing
		if (null == be && be instanceof ResourceParameterBinding) {
			ResourceParameterBinding binding = (ResourceParameterBinding)be;
			binding = createMissingExpression(binding);
			Expression paramBind = binding.getExpression();
			StardustResourceParameterBindingExpressionDetailComposite detail = new StardustResourceParameterBindingExpressionDetailComposite(this, SWT.NONE);
			detail.setBusinessObject(paramBind);
		}
	}

	private ResourceParameterBinding createMissingExpression(final ResourceParameterBinding binding) {
		Expression exp = binding.getExpression();
		if (null == exp) {
			RecordingCommand command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					Expression exp = createModelObject(FormalExpression.class);
					binding.setExpression(exp);
				}
			};
			editingDomain.getCommandStack().execute(command);
		}
		return binding;
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"parameterRef", //$NON-NLS-1$
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}
}
