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
package org.eclipse.stardust.modeling.core.marker;

import java.util.List;

import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.ui.IMarkerResolution;

/**
 * @author fherinean
 * @version $Revision$
 */
public interface IResolutionGenerator
{
   boolean hasResolutions(WorkflowModelEditor editor, Issue issue);
   void addResolutions(List<IMarkerResolution> list, WorkflowModelEditor editor, Issue issue);
}
