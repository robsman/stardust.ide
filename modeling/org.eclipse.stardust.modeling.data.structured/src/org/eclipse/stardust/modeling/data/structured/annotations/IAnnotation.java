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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.xsd.XSDElementDeclaration;

public interface IAnnotation
{
   String getName();

   List<IAnnotation> getChildren();

   IAnnotation getParent();

   boolean exists();
   
   String getRawValue();
   
   String getRawValue(XSDElementDeclaration element);

   void setRawValue(String value);

   void setRawValue(XSDElementDeclaration elementDeclaration, String value);

   IConfigurationElement getConfiguration();

   XSDElementDeclaration getElement();
}
