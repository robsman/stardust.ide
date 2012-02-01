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
package org.eclipse.stardust.modeling.templates.adapters;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.stardust.modeling.templates.Templates_Messages;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType;


public class TreeEditPartAdapterFactory implements IAdapterFactory
{
   public Object getAdapter(Object adaptableObject, Class adapterType)
   {
      if (adapterType.equals(TreeEditPart.class))
      {
         if (adaptableObject instanceof TemplateLibraryType)
         {
            return new AbstractTreeEditPart(adaptableObject)
            {
               protected void createEditPolicies()
               {
                  super.createEditPolicies();
                  installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
               }

               protected List getModelChildren()
               {
                  return Collections.singletonList(((TemplateLibraryType) getModel()).getModel());
               }

               protected String getText()
               {
                  TemplateLibraryType library = (TemplateLibraryType) getModel();
                  String text = library.getName();
                  if (text == null)
                  {
                     text = library.getId();
                  }
                  if (text == null)
                  {
                     text = Templates_Messages.TXT_TEMPLATE_LIBRARY;
                  }
                  return text;
               }
            };
         }
      }
      return null;
   }

   public Class[] getAdapterList()
   {
      return new Class[] {TreeEditPart.class};
   }
}
