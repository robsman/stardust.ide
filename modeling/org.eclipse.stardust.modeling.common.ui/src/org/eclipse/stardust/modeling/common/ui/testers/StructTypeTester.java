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
package org.eclipse.stardust.modeling.common.ui.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.StructuredTypeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkspaceManager;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;

public class StructTypeTester extends PropertyTester
{
   private static String COMPLEX = "complex"; //$NON-NLS-1$
   private static String SIMPLE = "simple"; //$NON-NLS-1$

   public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
   {
      if ("structType".equals(property)) //$NON-NLS-1$
      {
         if(receiver instanceof DataType)
         {
            String structType = COMPLEX;
            IMetaType metaType = ((ITypedElement) receiver).getMetaType();
            if(metaType == null && ((EObject) receiver).eIsProxy())
            {
               EObject resolvedElement = WorkspaceManager.getInstance().findElement((EObject) receiver);
               if(resolvedElement != null && resolvedElement instanceof ITypedElement)
               {
                  metaType = ((ITypedElement) resolvedElement).getMetaType();                        
               }                     
            }
            String metaTypeId = metaType.getId();

            if(metaTypeId.equals(PredefinedConstants.STRUCTURED_DATA))
            {
               TypeDeclarationType decl = StructuredTypeUtils.getTypeDeclaration((DataType) receiver);
               if(decl != null && TypeDeclarationUtils.isEnumeration(decl, false))
               {
                  structType = SIMPLE;
               }
            }

            return expectedValue.equals(structType);
         }
      }
      return false;
   }
}