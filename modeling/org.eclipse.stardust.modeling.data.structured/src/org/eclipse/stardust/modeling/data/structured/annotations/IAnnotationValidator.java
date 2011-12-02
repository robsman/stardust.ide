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
package org.eclipse.stardust.modeling.data.structured.annotations;

import org.eclipse.core.runtime.IStatus;

/**
 * Must be implemented by custom annotation validators.
 * 
 * @author herinean
 * @version $Revision$
 */
public interface IAnnotationValidator
{
   /**
    * Invoked to validate an annotation.
    * 
    * @param annotation the IAnnotation that must be validated.
    * @return an IStatus that represents the result of the validation.
    *       An IStatus with the severity OK means that the annotation is valid.
    */
   IStatus validate(IAnnotation annotation);
}
