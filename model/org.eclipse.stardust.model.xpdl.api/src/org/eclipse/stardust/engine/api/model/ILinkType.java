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

import org.eclipse.stardust.engine.core.model.utils.IdentifiableElement;
import org.eclipse.stardust.model.diagram.ArrowKey;
import org.eclipse.stardust.model.diagram.ColorKey;
import org.eclipse.stardust.model.diagram.LineKey;


/**
 * Parameterizes a generic links.
 */
public interface ILinkType extends IdentifiableElement
{
   /**
    *
    */
   public String getFirstRole();

   /**
    *
    */
   public String getSecondRole();

   /**
    *
    */
   public Class getFirstClass();

   /**
    *
    */
   public Class getSecondClass();

   /**
    *
    */
   public CardinalityKey getFirstCardinality();

   /**
    *
    */
   public CardinalityKey getSecondCardinality();

   /**
    * Retrieves the class of the other "end" of the link type.
    */
   public Class getOtherClass(String role);

   /**
    * Retrieves the role of the other "end" of the link type.
    */
   public String getOtherRole(String role);

   // Attributes for the visualisation
   public ArrowKey getFirstArrowType();

   public ArrowKey getSecondArrowType();

   public boolean getShowLinkTypeName();

   public boolean getShowRoleNames();

   public LineKey getLineType();

   public ColorKey getLineColor();

}
