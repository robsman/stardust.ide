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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.engine.core.model.beans.QNameUtil;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.util.TypeDeclarationUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.ui.IWorkbenchPart;

public class ReloadSchemaAction extends SelectionAction
{
   public ReloadSchemaAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.RELOAD_SCHEMA);
      setText((Diagram_Messages.LB_RELOAD_SCHEMA));
   }

   protected boolean calculateEnabled()
   {
      return getKeyForSchemaCache() != null;
   }

   private TypeDeclarationType getTypeDeclaration()
   {
      if(getSelectedObjects() != null
            && getSelectedObjects().size() == 1)
      {
         Object selection = getSelectedObjects().get(0);
         if (selection instanceof EditPart)
         {
            Object model = ((EditPart) selection).getModel();
            if (model instanceof TypeDeclarationType)
            {
               return (TypeDeclarationType) model;
            }
         }
      }

      return null;
   }

   private String getKeyForSchemaCache()
   {
      TypeDeclarationType typeDeclaration = getTypeDeclaration();
      if(typeDeclaration != null)
      {
         XpdlTypeType type = typeDeclaration.getDataType();
         if (type instanceof ExternalReferenceType)
         {
            String location = ((ExternalReferenceType) type).getLocation();
            if (!location.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
            {
               if(location.toLowerCase().startsWith("http://")) //$NON-NLS-1$
               {
                  String namespaceURI = QNameUtil.parseNamespaceURI(((ExternalReferenceType) type).getXref());
                  String key = '{' + namespaceURI + '}' + location;
                  return key;
               }
            }
         }
      }

      return null;
   }

   public void run()
   {
      String keyForSchemaCache = getKeyForSchemaCache();
      TypeDeclarationUtils.clearSchemaCache(keyForSchemaCache);
   }
}