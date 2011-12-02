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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import org.eclipse.emf.ecore.EObject;

/**
 * @author fherinean
 * @version $Revision$
 */
public interface IContainedElementCommand extends Cloneable
{
   int PARENT = 0;
   int PROCESS = 1;
   int MODEL = 2;

   void setParent(EObject parent);
   IContainedElementCommand duplicate();
}
