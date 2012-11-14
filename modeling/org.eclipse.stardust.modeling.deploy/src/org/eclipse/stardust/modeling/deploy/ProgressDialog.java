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
package org.eclipse.stardust.modeling.deploy;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.eclipse.stardust.common.error.ValidationException;
import org.eclipse.stardust.engine.core.compatibility.gui.AbstractDialog;

public class ProgressDialog extends AbstractDialog implements ActionListener
{
   private static final long serialVersionUID = 1L;

   public static final Runnable ON_CANCEL_EXIT_WITH_MINUS_ONE = new Runnable()
   {
      public void run()
      {
         System.exit(-1);
      }
   };
   
   private JProgressBar progress;
   
   private final Runnable onCancelAction;

   public ProgressDialog(JFrame parent, Runnable onCancelAction)
   {
      super(AbstractDialog.OK_CANCEL_TYPE, parent);
      
      this.onCancelAction = onCancelAction;
      
      setModal(false);
   }

   public static ProgressDialog showDialog(JFrame parent, String title,
         Runnable onCancelAction)
   {
      ProgressDialog progressDialog = new ProgressDialog(parent, onCancelAction);
      showDialog(title, progressDialog, parent);
      
      return progressDialog;
   }

   protected JComponent createContent()
   {
      this.progress = new JProgressBar();
      progress.setIndeterminate(true);
      
      JPanel mainPanel = new JPanel(new BorderLayout());
      mainPanel.add(progress, BorderLayout.CENTER);

      return mainPanel;
   }

   protected JPanel createButtonPanel()
   {
      JPanel buttonPanel = super.createButtonPanel();
      
      if (null != okButton)
      {
         okButton.setEnabled(false);
      }
      
      return buttonPanel;
   }

   public void validateSettings() throws ValidationException
   {
   }

   public void onCancel()
   {
      if (null != onCancelAction)
      {
         onCancelAction.run();
      }
   }
}