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
package org.eclipse.stardust.modeling.common.ui.jface.widgets;

import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.CarnotUiPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


public class LabelWithStatus extends Composite
{
   private final Label label;

   private final Label icon;

   private IQuickValidationStatus status;

   public LabelWithStatus(Composite parent, String text)
   {
      this(parent, text, SWT.NONE);
   }
   
   public LabelWithStatus(Composite parent, String text, int style)
   {
      // apply style (i.e. border)
      super(parent, SWT.NULL);

      this.label = new Label(this, SWT.NULL);
      label.setText(text);
      this.icon = new Label(this, SWT.NULL);

      final boolean alignRight = 0 != (style & SWT.TRAIL);
      
      GridLayout layout = new GridLayout(2, false);
      layout.marginHeight = 0;
      layout.marginWidth = 0;
      layout.horizontalSpacing = 2;
      setLayout(layout);

      label.setLayoutData(new GridData(SWT.LEAD, SWT.CENTER, alignRight, false));
      
      GridData iconLayout = new GridData(alignRight ? SWT.TRAIL : SWT.LEAD, SWT.CENTER,
            false, false);
      iconLayout.widthHint = 8;
      iconLayout.heightHint = 8;
      icon.setLayoutData(iconLayout);

      setValidationStatus(IQuickValidationStatus.OK);
   }
   
   public String getText()
   {
      checkWidget();
      return label.getText();
   }
   
   public void setText(String text)
   {
      checkWidget();
      label.setText(text);
   }

   public IQuickValidationStatus getValidationStatus()
   {
      return status;
   }

   public void setValidationStatus(IQuickValidationStatus status)
   {
      this.status = (null != status) ? status : IQuickValidationStatus.OK;

      // TODO refresh icon
      Image img;
      if (status.hasErrors())
      {
         img = CarnotUiPlugin.getDefault().getImageManager().getImage(
               CarnotUiPlugin.PATH_OVR_ERRORS);
      }
      else if (status.hasWarnings())
      {
         img = CarnotUiPlugin.getDefault().getImageManager().getImage(
               CarnotUiPlugin.PATH_OVR_WARNINGS);
      }
      else
      {
         // TODO infos
         img = null;
      }

      if ( !icon.isDisposed() && (img != icon.getImage()))
      {
         icon.setImage(img);
         icon.redraw();
      }
   }

   public Label getLabel()
   {
      return label;
   }

   public void dispose()
   {
      if ( !icon.isDisposed())
      {
         icon.dispose();
      }
      if ( !label.isDisposed())
      {
         label.dispose();
      }

      super.dispose();
   }

   public boolean setFocus()
   {
      checkWidget();
      return label.setFocus();
   }

   public void setToolTipText(String string)
   {
      super.setToolTipText(string);
      if ( !label.isDisposed())
      {
         label.setToolTipText(string);
      }
      if ( !icon.isDisposed())
      {
         icon.setToolTipText(string);
      }
   }
}
