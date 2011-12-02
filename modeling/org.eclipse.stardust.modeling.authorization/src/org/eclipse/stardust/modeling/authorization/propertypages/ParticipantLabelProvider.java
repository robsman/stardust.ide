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
package org.eclipse.stardust.modeling.authorization.propertypages;

import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.modeling.authorization.AuthorizationAspectPlugin;
import org.eclipse.stardust.modeling.authorization.Authorization_Messages;
import org.eclipse.stardust.modeling.authorization.Constants;
import org.eclipse.stardust.modeling.authorization.Permission;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;


public class ParticipantLabelProvider extends LabelProvider
   implements IFontProvider//, IColorProvider
{
   private Font defaultFont;
   private Font otherFont;
   private Permission permission;

   public ParticipantLabelProvider(Font defaultFont)
   {
      this.defaultFont = defaultFont;
      if (defaultFont != null)
      {
         FontData fontData = defaultFont.getFontData()[0];
         fontData.setStyle(fontData.getStyle() ^ SWT.ITALIC);
         this.otherFont = new Font(defaultFont.getDevice(), fontData);
      }
   }

   public String getText(Object element)
   {
      boolean isDefault = false;
      String label = null;
      if (element == Constants.ALL_PARTICIPANT)
      {
         label = Authorization_Messages.LBL_ALL;
         isDefault = permission.isDefaultAll();
      }
      else if (element == Constants.OWNER_PARTICIPANT)
      {
         label = Authorization_Messages.LBL_OWNER;
         isDefault = permission.isDefaultOwner();
      }
      else if (element instanceof IModelParticipant)
      {
         IModelParticipant participant = (IModelParticipant) element;
         label = participant.getName();
         if (label == null)
         {
            label = participant.getId();
         }
         isDefault = permission.isDefault(participant);
      }
      else if (element != null)
      {
         label = element.toString();
      }
      if (label != null && isDefault)
      {
         label += Authorization_Messages.LBL_DEFAULT;
      }
      return label == null ? "" : label; //$NON-NLS-1$
   }

   public Image getImage(Object element)
   {
      if (element == Constants.ALL_PARTICIPANT || element == Constants.OWNER_PARTICIPANT || element instanceof RoleType)
      {
         return AuthorizationAspectPlugin.getDefault().getImage("icons/role.gif"); //$NON-NLS-1$
      }
      else if (element instanceof OrganizationType)
      {
         return AuthorizationAspectPlugin.getDefault().getImage("icons/organization.gif"); //$NON-NLS-1$
      }
      return null;
   }

   public Font getFont(Object element)
   {
      if ((element == Constants.ALL_PARTICIPANT || element == Constants.OWNER_PARTICIPANT) && otherFont != null)
      {
         return otherFont;
      }
      return defaultFont;
   }

   @Override
   public void dispose()
   {
      otherFont.dispose();
      defaultFont = null;
      otherFont = null;
      super.dispose();
   }
/*
   public Color getBackground(Object element)
   {
      return null;
   }

   public Color getForeground(Object element)
   {
      if (element instanceof RoleType
            && PredefinedConstants.ADMINISTRATOR_ROLE.equals(((RoleType) element).getId()))
      {
         return ColorConstants.gray;
      }
      return null;
   }
*/

   public void setPermission(Permission permission)
   {
      this.permission = permission;
   }
}
