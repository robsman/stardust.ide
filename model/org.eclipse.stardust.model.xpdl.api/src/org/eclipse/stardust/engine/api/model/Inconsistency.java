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

import java.io.Serializable;

/**
 * The <code>Inconsistency</code> class provides information about a model inconsistency.
 * Inconsistencies are of two types: errors and warnings. When an error inconsistency is
 * issued, the model is unable to work (models with errors cannot be deployed). A warning
 * inconsistency implies that the specific workflow operation may fail.
 */
public class Inconsistency implements Serializable
{
   /**
    * Specifies a warning inconsistency.
    */
   public static final int WARNING = 0;
   /**
    * Specifies an error inconsistency.
    */
   public static final int ERROR = 1;

   private int sourceElementOID;
   private int severity;
   private String message;

   /**
    * Constructs an Inconsistency for a specific model element.
    *
    * @param message          inconsistency message.
    * @param sourceElementOID the OID of the inconsistent model element.
    * @param severity         the severity: WARNING or ERROR.
    */
   public Inconsistency(String message, int sourceElementOID, int severity)
   {
      this.message = message;
      this.sourceElementOID = sourceElementOID;
      this.severity = severity;
   }

   /**
    * Constructs an Inconsistency.
    *
    * @param synopsis inconsistency message.
    * @param severity the severity: WARNING or ERROR.
    */
   public Inconsistency(String synopsis, int severity)
   {
      this(synopsis, 0, severity);
   }

   /**
    * Returns the element OID of the inconsistent model element (activity, role etc.).
    *
    * @return the element OID if the source.
    */
   public int getSourceElementOID()
   {
      return sourceElementOID;
   }

   /**
    * Sets the element OID of the inconsistent model element.
    *
    * @param sourceElementOID the element OID of the source.
    */
   public void setSourceElementOID(int sourceElementOID)
   {
      this.sourceElementOID = sourceElementOID;
   }

   /**
    * Gets the severity of the inconsistency.
    *
    * @return the severity.
    */
   public int getSeverity()
   {
      return severity;
   }

   /**
    * Gets the message contained in the inconsistency.
    *
    * @return the inconsistency message.
    */
   public String getMessage()
   {
      return message;
   }

   public String toString()
   {
      return Long.toString(sourceElementOID) + " : " + message; //$NON-NLS-1$
   }
}
