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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.Label;
import org.eclipse.stardust.common.CompareHelper;

/**
 * @author rsauer
 * @version $Revision: 16865 $
 */
public class ProcessInterfaceFigure extends AbstractLabeledIconFigure
{
   public static final int EVENT_FLOW_START = 1;

   public static final int EVENT_FLOW_INTERMEDIATE = 2;

   public static final int EVENT_FLOW_END = 3;

   public static final int EVENT_TYPE_NONE = 0;

   public static final int EVENT_TYPE_MESSAGE = 1;

   public static final int EVENT_TYPE_TIMER = 2;

   public static final int EVENT_TYPE_ERROR = 3;

   public static final int EVENT_TYPE_CANCEL = 4;

   public static final int EVENT_TYPE_COMPENSATION = 5;

   public static final int EVENT_TYPE_RULE = 6;

   public static final int EVENT_TYPE_LINK = 7;

   public static final int EVENT_TYPE_MULTIPLE = 8;

   public static final int EVENT_TYPE_TERMINATE = 9;

   private String iconPath;

   private IconFigure typeIndicator;

   private int kind;

   public ProcessInterfaceFigure(int kind, String iconPath)
   {
      super(getIconFromKind(kind, iconPath)); //$NON-NLS-1$
      this.kind = kind;
      getShape().setLayoutManager(new MyBorderLayout());
      setIconPath(iconPath);
      setOutline(false);
   }

   public void setText(String text)
   {
      boolean empty = text == null || text.length() == 0;
      if (empty)
      {
         Label label = getLabel();
         if (label.getParent() != null)
         {
            doRemoveLabel(label);
         }
      }
      else
      {
         Label label = getLabel();
         if (label.getParent() == null)
         {
            label.setParent(this);
            doAddLabel(label);
         }
         super.setText(text);
      }
   }

   public void setIconPath(String iconPath)
   {
      if (!CompareHelper.areEqual(this.iconPath, iconPath))
      {
         if (typeIndicator != null)
         {
            getShape().remove(typeIndicator);
            typeIndicator = null;
         }
         if (iconPath != null)
         {
            super.setIconPath(getIconFromKind(kind, iconPath));
            typeIndicator = new IconFigure(iconPath);
            if(getShape().getLayoutManager() == null)
            {
               getShape().setLayoutManager(new MyBorderLayout());               
            }            
            getShape().add(typeIndicator, MyBorderLayout.CENTER);
         }
         this.iconPath = iconPath;
      }
   }

   private static String getIconFromKind(int kind, String iconPath)
   {
	   return "icons/full/obj16/processInterface_with_border.gif"; //$NON-NLS-1$
   }
}