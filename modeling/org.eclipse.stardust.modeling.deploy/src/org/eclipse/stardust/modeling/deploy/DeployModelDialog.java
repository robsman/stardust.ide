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
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.eclipse.stardust.common.error.ValidationException;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.api.model.Inconsistency;
import org.eclipse.stardust.engine.api.query.DeployedModelQuery;
import org.eclipse.stardust.engine.api.runtime.Daemon;
import org.eclipse.stardust.engine.api.runtime.DeploymentElement;
import org.eclipse.stardust.engine.api.runtime.DeploymentException;
import org.eclipse.stardust.engine.api.runtime.DeploymentOptions;
import org.eclipse.stardust.engine.api.runtime.Models;
import org.eclipse.stardust.engine.api.runtime.ServiceFactory;
import org.eclipse.stardust.engine.cli.common.DeploymentCallback;
import org.eclipse.stardust.engine.cli.common.DeploymentUtils;
import org.eclipse.stardust.engine.core.compatibility.gui.AbstractDialog;
import org.eclipse.stardust.engine.core.compatibility.gui.ValidationExceptionDialog;

public class DeployModelDialog extends AbstractDialog implements ActionListener
{
   private static final long serialVersionUID = 1L;

   private static final String OVERWRITE_CARD = "overwrite"; //$NON-NLS-1$
   private static final String DEPLOY_CARD = "deploy"; //$NON-NLS-1$

   private ServiceFactory sf;
   private JRadioButton overwriteButton;
   private JRadioButton deployButton;
   private CardLayout card;
   private JPanel listPanel;
   private DeployedModelsView deployView;
   private DeployedModelsView overwriteView;
   private Models data;
   
   private boolean wasDeployed = false;
   private List<String> modelFiles;

   public DeployModelDialog(JFrame parent, ServiceFactory service, List<String> modelFiles, List<IModel> models)
   {
      super(parent);
      this.sf = service;
      this.modelFiles = modelFiles;

      data = sf.getQueryService().getModels(DeployedModelQuery.findAll());

      if (!data.isEmpty() && models.size() == 1)
      {
         overwriteView.setData(data, models, true);
         overwriteButton.setEnabled(true);
      }
      else
      {
         overwriteButton.setEnabled(false);
      }

      deployView.setData(data, models, false);

      deployButton.setSelected(true);
      card.show(listPanel, DEPLOY_CARD);
   }

   protected JComponent createContent()
   {
      overwriteButton = new JRadioButton(new AbstractAction(Deploy_Messages.getString("LB_Overwrite")) //$NON-NLS-1$
      {
         private static final long serialVersionUID = 1L;

         public void actionPerformed(ActionEvent e)
         {
            card.show(listPanel, OVERWRITE_CARD);
         }
      });
      deployButton = new JRadioButton(new AbstractAction(Deploy_Messages.getString("LB_DeployVersion")) //$NON-NLS-1$
      {
         private static final long serialVersionUID = 1L;

         public void actionPerformed(ActionEvent e)
         {
            card.show(listPanel, DEPLOY_CARD);
         }
      });

      ButtonGroup group = new ButtonGroup();
      group.add(overwriteButton);
      group.add(deployButton);
      JPanel mainPanel = new JPanel(new BorderLayout());
      Box options = new Box(BoxLayout.X_AXIS);

      options.add(new JLabel(Deploy_Messages.getString("LB_Action"))); //$NON-NLS-1$
      options.add(deployButton);
      options.add(overwriteButton);
      mainPanel.add(options, BorderLayout.NORTH);
      card = new CardLayout();
      listPanel = new JPanel(card);
      overwriteView = new DeployedModelsView();
      listPanel.add(overwriteView, OVERWRITE_CARD);
      deployView = new DeployedModelsView();
      listPanel.add(deployView, DEPLOY_CARD);
      mainPanel.add(listPanel);
      return mainPanel;
   }

   public void validateSettings() throws ValidationException
   {
      if (overwriteButton.isSelected() && overwriteView.getSelectedModelOID() == -1)
      {
         throw new ValidationException(Deploy_Messages.getString("MSG_NoModelToOverwrite"), false); //$NON-NLS-1$
      }
   }

   public void onOK()
   {
      try
      {
         if (overwriteButton.isSelected())
         {
            overwrite();
         }
         else
         {
            deploy();
         }
      }
      catch (DeploymentException e)
      {
      }
   }

   private void deploy()
   {
      MyDeploymentCallback callback = new MyDeploymentCallback();
      DeploymentOptions options = new DeploymentOptions();
      options.setValidFrom(deployView.getValidFrom());
      options.setComment(deployView.getComment());
      List<DeploymentElement> units = null;
      try
      {
         units = RuntimeUtil.createDeploymentElements(modelFiles);
      }
      catch (IOException ex)
      {
         callback.reportErrors(Collections.singletonList(new Inconsistency(ex.getMessage(), Inconsistency.ERROR)));
         return;
      }
      DeploymentUtils.deployFromFiles(sf, callback, units, options);      
      wasDeployed = true;
   }

   private void overwrite()
   {
      MyDeploymentCallback callback = new MyDeploymentCallback();
      DeploymentOptions options = new DeploymentOptions();
      options.setComment(deployView.getComment());
      DeploymentElement unit = null;
      try
      {
         unit = RuntimeUtil.createDeploymentElement(modelFiles.get(0));
      }
      catch (IOException ex)
      {
         callback.reportErrors(Collections.singletonList(new Inconsistency(ex.getMessage(), Inconsistency.ERROR)));
         return;
      }
      DeploymentUtils.overwriteFromFile(sf, callback, unit, overwriteView.getSelectedModelOID(), options);
      wasDeployed = true;
   }

   private boolean ignoreWarnings(List<Inconsistency> warnings)
   {
      boolean isDaemonRunning = false;
      ValidationException e = new ValidationException(Deploy_Messages
            .getString("MSG_ContinueDeploying"), warnings, true); //$NON-NLS-1$
      List<Daemon> daemons = sf.getAdministrationService().getAllDaemons(true);
      for (Daemon daemon : daemons)
      {
         if (daemon.isRunning())
         {
            isDaemonRunning = true;
            JOptionPane.showMessageDialog(this,
                        Deploy_Messages.getString("MSG_DeploymentNotPossible"), //$NON-NLS-1$
                        Deploy_Messages.getString("TITLE_Warning"), //$NON-NLS-1$
                        JOptionPane.WARNING_MESSAGE);
         }
      }
      return isDaemonRunning ? false : ValidationExceptionDialog.showDialog(this, e,
            false);
   }

   private void showErrors(List<Inconsistency> errors)
   {
      ValidationException e = new ValidationException(
            Deploy_Messages.getString("MSG_NoErrors"), errors, false); //$NON-NLS-1$
      ValidationExceptionDialog.showDialog(this, e);
   }

   private class MyDeploymentCallback implements DeploymentCallback
   {
      public void reportErrors(List<Inconsistency> errors)
      {
         showErrors(errors);
      }

      public boolean reportWarnings(List<Inconsistency> warnings)
      {
         return ignoreWarnings(warnings);
      }
   }

   public static boolean showDialog(ServiceFactory service, List<String> modelFiles, List<IModel> models, JFrame parent)
   {
      DeployModelDialog instance = new DeployModelDialog(parent, service, modelFiles, models);
      showDialog(Deploy_Messages.getString("DIALOG_Model"), instance, parent); //$NON-NLS-1$
      return instance.wasDeployed;
   }
}