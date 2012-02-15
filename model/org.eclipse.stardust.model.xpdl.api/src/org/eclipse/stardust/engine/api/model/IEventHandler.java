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

import java.util.Iterator;

import org.eclipse.stardust.engine.core.model.utils.IdentifiableElement;


/**
 * @author ubirkemeyer
 * @version $Revision$
 */
public interface IEventHandler extends IdentifiableElement, Typeable, AccessPointOwner
{
   Iterator getAllEventActions();

   Iterator getAllBindActions();

   Iterator getAllUnbindActions();

   boolean isAutoBind();

   boolean isUnbindOnMatch();

   boolean isLogHandler();

   boolean isConsumeOnMatch();

   boolean hasBindActions();

   boolean hasUnbindActions();
}
