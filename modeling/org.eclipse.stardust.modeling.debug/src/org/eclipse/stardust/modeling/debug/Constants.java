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
package org.eclipse.stardust.modeling.debug;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public interface Constants
{
   public final static String EMPTY = ""; //$NON-NLS-1$
   
   public static final String ID_CWM_DEBUG_MODEL = "org.eclipse.stardust.modeling.debug"; //$NON-NLS-1$
   public static final String ID_CWM_LAUNCH = "org.eclipse.stardust.modeling.debug.launchConfigurationType"; //$NON-NLS-1$
   
   public static final String ATTR_CWM_MODEL_FILE_PATH = ID_CWM_DEBUG_MODEL
         + ".ATTR_CWM_MODEL_FILE"; //$NON-NLS-1$
   public static final String ATTR_CWM_MODEL_DEPENDENCIES = ID_CWM_DEBUG_MODEL
         + ".ATTR_CWM_MODEL_DEP"; //$NON-NLS-1$
   public static final String ATTR_CWM_PROCESS_DEFINITION_ID = ID_CWM_DEBUG_MODEL
         + ".ATTR_CWM_PROCESS_DEFINITION_ID"; //$NON-NLS-1$
   
   public static final String CMDLINE_ARG_MODEL_FILE = "model_file="; //$NON-NLS-1$
   public static final String CMDLINE_ARG_DEPENDENCY_FILE = "dep_file="; //$NON-NLS-1$
   public static final String CMDLINE_ARG_PROCESS_DEFINITION_ID = "procdef_id="; //$NON-NLS-1$
   public static final String CMDLINE_ARG_VIEW_TYPE = "view="; //$NON-NLS-1$
   
   public static final String THREAD_GROUP_PARAM_PREFIX = "ThreadGroup."; //$NON-NLS-1$
   public static final String THREAD_GROUP_ACTIVITY_THREAD = "Infinity Activity Thread"; //$NON-NLS-1$
   public static final String THREAD_GROUP_ACTIVITY_THREAD_PARAM = THREAD_GROUP_PARAM_PREFIX
         + THREAD_GROUP_ACTIVITY_THREAD;
   public static final String THREAD_GROUP_HELPER_THREAD = "Infinity Helper Thread"; //$NON-NLS-1$
   public static final String THREAD_GROUP_HELPER_THREAD_PARAM = THREAD_GROUP_PARAM_PREFIX
         + THREAD_GROUP_HELPER_THREAD;
   public static final String THREAD_GROUP_ON_COMPLETION_THREAD = "Infinity On Completion Thread"; //$NON-NLS-1$
   public static final String THREAD_GROUP_ON_COMPLETION_THREAD_PARAM = THREAD_GROUP_PARAM_PREFIX
         + THREAD_GROUP_ON_COMPLETION_THREAD;
   public static final String CURRENT_DEBUGGER_PARAM = "CARNOT.Debugger"; //$NON-NLS-1$
   public static final String CURRENT_SERVICE_FACTORY_PARAM = "CARNOT.WorkflowServiceFactory"; //$NON-NLS-1$

   public static final String ID_RUNTIME_CLASSPATH_PROVIDER = "org.eclipse.stardust.modeling.debug.launching.RuntimeClasspathProvider"; //$NON-NLS-1$

   public static final String ID_DEBUG_CORE_LIBS_CP = ID_CWM_DEBUG_MODEL + ".carnotDebugCoreLibraries"; //$NON-NLS-1$;

   public static final IPath PATH_DEBUG_CORE_LIBS_CP = new Path(ID_DEBUG_CORE_LIBS_CP);
}
