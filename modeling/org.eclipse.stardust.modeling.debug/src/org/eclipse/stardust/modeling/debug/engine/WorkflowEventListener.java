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
package org.eclipse.stardust.modeling.debug.engine;

import org.eclipse.stardust.engine.core.runtime.beans.IActivityInstance;
import org.eclipse.stardust.engine.core.runtime.beans.IWorkflowEventListener;
import org.eclipse.stardust.engine.core.runtime.beans.TransitionTokenBean;

/**
 * This interface is adapted from 
 * {@link ag.carnot.workflow.tools.defdesk.WorkflowEventListener}.
 * 
 * @author sborn
 * @version $Revision$
 */
public interface WorkflowEventListener extends IWorkflowEventListener
{
   public void performedTransition(TransitionTokenBean transitionToken);

   public void appendedToWorklist(IActivityInstance activityInstance);
}
