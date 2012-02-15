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
package org.eclipse.stardust.model.diagram;

import org.eclipse.stardust.engine.core.model.utils.ModelElement;


public interface Symbol extends ModelElement
{
   /**
    *
    */
   int getBottom();

   /**
    *
    */
   int getHeight();

   /**
     *
     */
   int getLeft();

   /**
     *
     */
   int getRight();

   /**
    *
    */
   int getTop();

   /**
    *
    */
   java.util.Iterator getAllConnections();

   /**
    *
    */
   int getX();

   /**
     *
     */
   int getY();

   /**
     *
     */
   int getWidth();
}
