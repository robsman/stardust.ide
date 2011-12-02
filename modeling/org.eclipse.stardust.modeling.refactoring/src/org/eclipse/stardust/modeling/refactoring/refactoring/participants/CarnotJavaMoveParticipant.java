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
package org.eclipse.stardust.modeling.refactoring.refactoring.participants;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.IType;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.MoveParticipant;
import org.eclipse.stardust.modeling.refactoring.refactoring.Refactoring_Messages;
import org.eclipse.stardust.modeling.refactoring.refactoring.changes.ModelChange;


/**
 * @author sborn
 * @version $Revision$
 */
public class CarnotJavaMoveParticipant extends MoveParticipant
{
   private Object element;

   protected boolean initialize(Object element)
   {
      this.element = element;
      return element instanceof IType;
   }

   public String getName()
   {
      return Refactoring_Messages.MSG_MoveParticipant;
   }

   public RefactoringStatus checkConditions(IProgressMonitor pm,
         CheckConditionsContext context) throws OperationCanceledException
   {
      return new RefactoringStatus();
   }

   public Change createChange(IProgressMonitor pm) throws CoreException,
         OperationCanceledException
   {
      return ModelChange.createChange(pm, getName(), element, getArguments());
   }
}
