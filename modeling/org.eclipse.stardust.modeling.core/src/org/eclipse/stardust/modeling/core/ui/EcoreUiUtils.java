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
package org.eclipse.stardust.modeling.core.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

public class EcoreUiUtils
{
   public static List getEnumItems(EEnum enumType)
   {
      List result = new ArrayList();
      for (Iterator i = enumType.getELiterals().iterator(); i.hasNext();)
      {
         EEnumLiteral literal = (EEnumLiteral) i.next();
         result.add(literal.getInstance());
      }
      return result;
   }
}