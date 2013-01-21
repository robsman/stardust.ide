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

import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

public class DocumentAccessPointBuilder
      extends AbstractModelElementBuilder<AccessPointType, DocumentAccessPointBuilder>
{
   private ModelBuilderFacade facade;

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
      long maxElementOid = XpdlModelUtils.getMaxUsedOid(model);
      element.setElementOid(++maxElementOid);      
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

   public DocumentAccessPointBuilder withType(String dataID)
   {
      DataType dataType = getModelBuilderFacade().findData(model, dataID);
      
      if(dataType != null)
      {
         element.setType(dataType.getType());      
      }
      return self();
   }

   private ModelBuilderFacade getModelBuilderFacade()
   {
      if (facade == null)
      {
         facade = new ModelBuilderFacade();
      }
      return facade;
   }
}