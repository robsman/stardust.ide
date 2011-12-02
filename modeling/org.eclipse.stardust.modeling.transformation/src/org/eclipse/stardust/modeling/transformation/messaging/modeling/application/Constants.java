/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application;

import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;

public interface Constants
{
	public static final String BEAN_NAME = "messageTransformationEngineBean";  //$NON-NLS-1$
	public static final String SCOPE = "messageTransformation:";  //$NON-NLS-1$
	public static final String FIELD_MAPPING = SCOPE + "FieldMapping"; //$NON-NLS-1$
	public static final String SOURCE_TYPE = SCOPE + "SourceType"; //$NON-NLS-1$
	public static final String TARGET_TYPE = SCOPE + "TargetType"; //$NON-NLS-1$
	public static final String MESSAGE_FORMAT = SCOPE + "MessageFormat"; //$NON-NLS-1$
	public static final String TEST_CONFIGURATION = SCOPE + "TestConfiguration"; //$NON-NLS-1$
	public static final String FORMAT_MODEL_FILE_PATH = SCOPE + "FormatModelFilePath"; //$NON-NLS-1$
	public static final String XSL_CODE = SCOPE + "XSLCode"; //$NON-NLS-1$
	public static final String TRANSFORMATION_PROPERTY = SCOPE + "TransformationProperty"; //$NON-NLS-1$
}
