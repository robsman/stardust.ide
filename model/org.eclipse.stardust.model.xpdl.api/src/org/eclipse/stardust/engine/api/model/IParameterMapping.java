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
package org.eclipse.stardust.engine.api.model;

import org.eclipse.stardust.engine.core.model.utils.ModelElement;


/**
 *
 * @author ubirkemeyer
 * @version $Revision$
 */
public interface IParameterMapping extends ModelElement
{
   // @todo (france, ub):  return reference
   IData getData();

   // @todo (france, ub):  return reference
   String getParameterId();

   String getParameterPath();

   ITrigger getTrigger();
}
