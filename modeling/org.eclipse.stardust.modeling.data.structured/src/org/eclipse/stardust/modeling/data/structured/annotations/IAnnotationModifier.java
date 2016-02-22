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

import java.util.List;

import org.eclipse.xsd.XSDFeature;

public interface IAnnotationModifier
{
   boolean exists(IAnnotation annotation);

   boolean canModify(IAnnotation annotation);

   boolean delete(IAnnotation annotation);

   Object getValue(IAnnotation annotation);

   Object getValue(IAnnotation annotation, XSDFeature element);

   void setValue(IAnnotation annotation, XSDFeature element, Object value);

   List<Object> getAllowedValues(IAnnotation annotation);

   void setValue(IAnnotation annotation, Object value);

   void addAnnotationChangedListener(IAnnotationChangedListener annotation);

   void removeAnnotationChangedListener(IAnnotationChangedListener annotation);
}
