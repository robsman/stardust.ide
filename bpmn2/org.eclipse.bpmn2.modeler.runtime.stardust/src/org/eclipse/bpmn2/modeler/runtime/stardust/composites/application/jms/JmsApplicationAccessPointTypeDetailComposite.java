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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointTypeDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class JmsApplicationAccessPointTypeDetailComposite extends AccessPointTypeDetailComposite {

	public JmsApplicationAccessPointTypeDetailComposite(Composite parent, AccessPointChangeListener listener) {
		super(parent, listener);
	}

	protected Hashtable<String, Object> getDatatypeComboChoice() {
		Hashtable<String, Object> choices = new Hashtable<String, Object>();
		choices.put(AcessPointDataTypes.SERIALIZABLE_TYPE.getDisplayName(), AcessPointDataTypes.SERIALIZABLE_TYPE.getKey());
		return choices;
	}

}