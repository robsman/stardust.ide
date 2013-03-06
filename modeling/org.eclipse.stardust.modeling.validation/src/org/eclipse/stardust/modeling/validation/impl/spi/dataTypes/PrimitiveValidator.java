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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IBridgeObjectProvider;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;
 

public class PrimitiveValidator implements IModelElementValidator, IBridgeObjectProvider
{
   private static final String MESSAGES = Validation_Messages.MSG_PRIMITIVE_UnspecifiedType;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, CarnotConstants.TYPE_ATT)))
      {
         result.add(Issue.warning(element, MESSAGES));
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   public BridgeObject getBridgeObject(ITypedElement accessPoint, String accessPath, DirectionType direction) throws ValidationException
   {
      return JavaDataTypeUtils.getBridgeObject(accessPoint, accessPath, direction);
   }
}
