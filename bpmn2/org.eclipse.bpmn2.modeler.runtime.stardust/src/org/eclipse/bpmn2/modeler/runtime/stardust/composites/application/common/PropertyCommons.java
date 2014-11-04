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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.common;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;

/**
 * Reusable Property information for 'common' model element properties.
 * @author Simon Nikles
 *
 */
public class PropertyCommons {

	public static enum Visibility {

		PUBLIC("Public"),
		PRIVATE("Private");

		public static String NAME = PredefinedConstants.MODELELEMENT_VISIBILITY;

		private String key;
		private Visibility(String key) {
			this.key = key;
		}

		public static String[] getOptionKeys() {
			return new String[]{PUBLIC.key, PRIVATE.key};
		}

	}

}
