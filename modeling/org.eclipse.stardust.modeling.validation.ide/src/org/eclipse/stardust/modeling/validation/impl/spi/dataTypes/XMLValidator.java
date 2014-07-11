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
package org.eclipse.stardust.modeling.validation.impl.spi.dataTypes;

import java.util.Collections;
import java.util.List;

import org.eclipse.stardust.engine.extensions.xml.data.XPathEvaluator;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.modeling.validation.*;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLValidator implements IModelElementValidator, IBridgeObjectProvider
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      return Issue.ISSUE_ARRAY;
   }

   public BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath,
         DirectionType direction)
   {
      Class<?> clazz;
      String className;
      if (DirectionType.OUT_LITERAL.equals(direction))
      {
         Object pseudoResult = new XPathEvaluator().evaluate(Collections.EMPTY_MAP,
               "<emptyXml />", accessPath); //$NON-NLS-1$
         clazz = (null != pseudoResult) ? pseudoResult.getClass() : List.class;
         className = clazz.getName();
      }
      else
      {
         className = String.class.getName();
      }

      TypeFinder finder = new TypeFinder(accessPoint);
      return new XMLBridgeObject(finder.findType(className), direction);
   }

   private class XMLBridgeObject extends BridgeObject
   {
      public XMLBridgeObject(TypeInfo typeInfo, DirectionType direction)
      {
         super(typeInfo.getType(), direction);
      }

      public boolean acceptAssignmentFrom(BridgeObject rhs)
      {
         // direction must be in or inout or null
         if (getDirection() == DirectionType.OUT_LITERAL)
         {
            return false;
         }
         // rhs direction must be out, inout or null
         if (rhs.getDirection() == DirectionType.IN_LITERAL)
         {
            return false;
         }
         if (TypeFinder.isAssignable(getEndClass(), rhs.getEndClass()))
         {
            return true;
         }
         TypeFinder finder = new TypeFinder(getEndClass());
         TypeInfo elemType = finder.findType(Element.class.getName());
         if (TypeFinder.isAssignable(elemType.getType(), rhs.getEndClass()))
         {
            return true;
         }
         TypeInfo docType = finder.findType(Document.class.getName());
         return TypeFinder.isAssignable(docType.getType(), rhs.getEndClass());
      }
   }
}
