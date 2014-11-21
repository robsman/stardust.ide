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
package org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.labels.Labels;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;

/**
 * Reusable Property information for 'common' model element properties.
 * @author Simon Nikles
 *
 */
public class CommonAttributes {

	public static enum Visibility {

		PUBLIC("Public"),
		PRIVATE("Private");

		public static String NAME = PredefinedConstants.MODELELEMENT_VISIBILITY;
		public static String LABEL = Labels.common_Visibility;

		private String key;
		private Visibility(String key) {
			this.key = key;
		}

		public static String[] getOptionKeys() {
			return new String[]{PUBLIC.key, PRIVATE.key};
		}

		public static Map<String, String> getChoices() {
			Map<String, String> choices = new HashMap<String, String>(2);
			choices.put(PUBLIC.key, PUBLIC.key);
			choices.put(PRIVATE.key, PRIVATE.key);
			return choices;
		}

		public String getKey() {
			return key;
		}

	}

}
