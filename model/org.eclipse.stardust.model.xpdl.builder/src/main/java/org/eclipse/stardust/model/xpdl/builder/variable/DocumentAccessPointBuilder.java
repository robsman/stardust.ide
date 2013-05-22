/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.variable;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class DocumentAccessPointBuilder
      extends AbstractModelElementBuilder<AccessPointType, DocumentAccessPointBuilder>
{
   private IAccessPointOwner owner;

   public DocumentAccessPointBuilder()
   {
      super(F_CWM.createAccessPointType());
   }

   public DocumentAccessPointBuilder(IAccessPointOwner anOwner)
   {
      super(F_CWM.createAccessPointType());
      this.owner = anOwner;
      forModel(ModelUtils.findContainingModel(anOwner));      
      DataTypeType dataTypeType = new ModelBuilderFacade().findDataType(model,
            PredefinedConstants.DOCUMENT_DATA);
      
      if(dataTypeType != null)
      {
         element.setType(dataTypeType);
      }
   }


   private void createDefaultAccessPoint(IAccessPointOwner owner2)
   {
      owner.getAccessPoint().add(element);
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "DocumentAccessPoint";
   }

   public static DocumentAccessPointBuilder newAccessPoint(IAccessPointOwner anOwner)
   {
      return new DocumentAccessPointBuilder(anOwner);
   }

   @Override
   protected AccessPointType finalizeElement()
   {
      super.finalizeElement();
      createDefaultAccessPoint(owner);
      
      return element;
   }
   public DocumentAccessPointBuilder withDirection(String direction)
   {
      DirectionType directionType;
      if (direction.equals(DirectionType.IN_LITERAL.getName()))
      {
         directionType = DirectionType.IN_LITERAL;
      }
      else
      {
         directionType = DirectionType.OUT_LITERAL;
      }
      element.setDirection(directionType);
      return self();
   }
}