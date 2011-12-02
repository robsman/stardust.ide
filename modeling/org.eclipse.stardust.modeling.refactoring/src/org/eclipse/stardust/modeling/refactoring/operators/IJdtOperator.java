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
package org.eclipse.stardust.modeling.refactoring.operators;

import java.util.List;

import org.eclipse.ltk.core.refactoring.participants.RefactoringArguments;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.core.resources.IFile;


/**
 * @author fherinean
 * @version $Revision$
 */
public interface IJdtOperator
{
   List getRefactoringChanges(ModelType model, Object element, RefactoringArguments arguments);
   List getQueryMatches(IFile file, ModelType model, Object element);
}
