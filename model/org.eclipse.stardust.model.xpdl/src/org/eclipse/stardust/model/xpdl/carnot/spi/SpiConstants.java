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
package org.eclipse.stardust.model.xpdl.carnot.spi;

/**
 * @author rsauer
 * @version $Revision$
 */
public interface SpiConstants
{
   String ID = "id"; //$NON-NLS-1$
   String NAME = "name"; //$NON-NLS-1$
   String ICON = "icon"; //$NON-NLS-1$
   String PROPERTY_PAGE_CLASS = "propertyPageClass"; //$NON-NLS-1$
   String OBJECT_CLASS = "objectClass"; //$NON-NLS-1$
   String CATEGORY = "category"; //$NON-NLS-1$

   String DATA_ACCESS_PATH_EDITOR_CLASS = "accessPathEditorClass"; //$NON-NLS-1$
   String DATA_INITIALIZER_CLASS = "initializerClass"; //$NON-NLS-1$

   String APP_IS_SYNCHRONOUS = "synchronous"; //$NON-NLS-1$
   String APP_ACCESS_POINT_PROVIDER = "accessPointProvider"; //$NON-NLS-1$
   String APP_INITIALIZER_CLASS = "initializerClass"; //$NON-NLS-1$

   String APP_CTXT_HAS_MAPPING_ID = "hasMappingId"; //$NON-NLS-1$
   String APP_CTXT_HAS_APPLICATION_PATH = "hasApplicationPath"; //$NON-NLS-1$
   String APP_CTXT_ACCESS_POINT_PROVIDER = "accessPointProvider"; //$NON-NLS-1$

   String TR_IS_PULL_TRIGGER = "pullTrigger"; //$NON-NLS-1$

   String EH_IMPLEMENTATION = "implementation"; //$NON-NLS-1$
   String EH_IS_PROCESS_CONDITION = "processCondition"; //$NON-NLS-1$
   String EH_IS_ACTIVITY_CONDITION = "activityCondition"; //$NON-NLS-1$
   String EH_RUNTIME_PROPERTY_PAGE_CLASS = "runtimePropertyPageClass"; //$NON-NLS-1$
   String EH_IS_AUTOMATICALLY_BOUND = "automaticBinding"; //$NON-NLS-1$
   String EH_IS_LOGGED = "logHandler"; //$NON-NLS-1$
   String EH_IS_CONSUMED_ON_MATCH = "consumeOnMatch"; //$NON-NLS-1$
   String EH_ACCESS_POINT_PROVIDER = "accessPointProvider"; //$NON-NLS-1$

   String EA_IS_PROCESS_ACTION = "processAction"; //$NON-NLS-1$
   String EA_IS_ACTIVITY_ACTION = "activityAction"; //$NON-NLS-1$
   String EA_RUNTIME_PROPERTY_PAGE_CLASS = "runtimePropertyPageClass"; //$NON-NLS-1$
   String EA_SUPPORTED_CONDITION_TYPES = "supportedConditionTypes"; //$NON-NLS-1$
   String EA_UNSUPPORTED_CONTEXTS = "unsupportedContexts"; //$NON-NLS-1$

   String MSG_PROVIDER_PROVIDER_CLASS = "providerClass"; //$NON-NLS-1$

   String MSG_ACCEPTOR_ACCEPTOR_CLASS = "acceptorClass"; //$NON-NLS-1$

   String ATTR_ELEMENT = "attribute"; //$NON-NLS-1$
   String ATTR_NAME = "name"; //$NON-NLS-1$
   String ATTR_VALUE = "value"; //$NON-NLS-1$
   String ATTR_TYPE = "type"; //$NON-NLS-1$
}
