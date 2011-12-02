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
package org.eclipse.stardust.modeling.refactoring.refactoring.changes;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;


/**
 * @author sborn
 * @version $Revision$
 */
public class AttributeValueChange extends Change
{
   protected AttributeType attribute;
   private String newValue;

   public AttributeValueChange(AttributeType attribute, String value)
   {
      this.attribute = attribute;
      newValue = value;
   }

   public Change perform(IProgressMonitor pm) throws CoreException
   {
      String oldValue = attribute.getValue();
      attribute.setValue(newValue);
      return new AttributeValueChange(attribute, oldValue);
   }

   public String getName()
   {
      return "attribute: " + attribute.getName(); //$NON-NLS-1$
   }

   public void initializeValidationData(IProgressMonitor pm)
   {
   }

   public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException, OperationCanceledException
   {
      return new RefactoringStatus();
   }

   public Object getModifiedElement()
   {
      return attribute;
   }
}
