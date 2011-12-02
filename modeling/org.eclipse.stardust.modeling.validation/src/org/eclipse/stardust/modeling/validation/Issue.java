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
package org.eclipse.stardust.modeling.validation;

import java.io.Serializable;

import org.eclipse.emf.ecore.EObject;

public class Issue implements Serializable
{
   public static final Issue[] ISSUE_ARRAY = new Issue[0];

   /**
    * Specifies an info inconsistency.
    */
   public static final int INFO = 0;

   /**
    * Specifies a warning inconsistency.
    */
   public static final int WARNING = 1;

   /**
    * Specifies an error inconsistency.
    */
   public static final int ERROR = 2;

   private final int severity;

   private EObject modelElement;

   private Object feature;

   private String message;

   public static Issue info(EObject element, String message)
   {
      return new Issue(INFO, element, message);
   }

   public static Issue info(EObject element, String message,
         Object feature)
   {
      return new Issue(INFO, element, message, feature);
   }

   public static Issue warning(EObject element, String message)
   {
      return new Issue(WARNING, element, message);
   }

   public static Issue warning(EObject element, String message,
         Object feature)
   {
      return new Issue(WARNING, element, message, feature);
   }

   public static Issue error(EObject element, String message)
   {
      return new Issue(ERROR, element, message);
   }

   public static Issue error(EObject element, String message,
         Object feature)
   {
      return new Issue(ERROR, element, message, feature);
   }

   public Issue(int severity, EObject element, String message)
   {
      this(severity, element, message, null);
   }

   public Issue(int severity, EObject element, String message,
         Object feature)
   {
      this.severity = severity;
      this.modelElement = element;
      this.message = message;
      this.feature = feature;
   }

   public String getMessage()
   {
      return message;
   }

   public boolean isInfo()
   {
      return INFO == getSeverity();
   }

   public boolean isWarning()
   {
      return WARNING == getSeverity();
   }

   public boolean isError()
   {
      return ERROR == getSeverity();
   }

   public int getSeverity()
   {
      return severity;
   }

   public EObject getModelElement()
   {
      return modelElement;
   }

   public Object getFeature()
   {
      return feature;
   }
}