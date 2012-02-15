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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.engine.api.model.CardinalityKey;
import org.eclipse.stardust.engine.api.model.ILinkType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.diagram.ArrowKey;
import org.eclipse.stardust.model.diagram.ColorKey;
import org.eclipse.stardust.model.diagram.LineKey;
import org.eclipse.stardust.model.xpdl.carnot.LinkTypeType;


public class ILinkTypeAdapter extends AbstractIdentifiableModelElementAdapter
      implements ILinkType
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof LinkTypeType) ? new ILinkTypeAdapter(
               (LinkTypeType) adaptee) : null;
      }
   };

   protected final LinkTypeType ltDelegate;

   public ILinkTypeAdapter(LinkTypeType target)
   {
      super(target);

      this.ltDelegate = target;
   }

   public String getFirstRole()
   {
      return ltDelegate.getSourceRole();
   }
   
   public Class getFirstClass()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }
   
   public CardinalityKey getFirstCardinality()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public String getSecondRole()
   {
      return ltDelegate.getTargetRole();
   }
   
   public Class getSecondClass()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }
   
   public CardinalityKey getSecondCardinality()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public Class getOtherClass(String role)
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public String getOtherRole(String role)
   {
      return CompareHelper.areEqual(role, getFirstRole())
            ? getSecondRole()
            : CompareHelper.areEqual(role, getSecondRole()) ? getFirstRole() : null;
   }

   public ColorKey getLineColor()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public LineKey getLineType()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public ArrowKey getFirstArrowType()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public ArrowKey getSecondArrowType()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public boolean getShowLinkTypeName()
   {
      return ltDelegate.isShowLinkTypeName();
   }

   public boolean getShowRoleNames()
   {
      return ltDelegate.isShowRoleNames();
   }

public void setFirstArrowType(ArrowKey arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setFirstCardinality(CardinalityKey arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setFirstClass(Class arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setFirstRole(String arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setLineColor(ColorKey arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setLineType(LineKey arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setSecondArrowType(ArrowKey arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setSecondCardinality(CardinalityKey arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setSecondClass(Class arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setSecondRole(String arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setShowLinkTypeName(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setShowRoleNames(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
