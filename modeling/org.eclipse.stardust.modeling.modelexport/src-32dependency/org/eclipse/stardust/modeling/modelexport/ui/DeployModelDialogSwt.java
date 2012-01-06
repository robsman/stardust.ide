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
package org.eclipse.stardust.modeling.modelexport.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.common.config.ParametersFacade;
import org.eclipse.stardust.common.error.ValidationException;
import org.eclipse.stardust.common.utils.xml.XmlUtils;
import org.eclipse.stardust.engine.api.model.IModel;
import org.eclipse.stardust.engine.api.model.Inconsistency;
import org.eclipse.stardust.engine.api.runtime.DeployedModelDescription;
import org.eclipse.stardust.engine.api.runtime.DeploymentElement;
import org.eclipse.stardust.engine.api.runtime.DeploymentException;
import org.eclipse.stardust.engine.api.runtime.DeploymentOptions;
import org.eclipse.stardust.engine.api.runtime.ServiceFactory;
import org.eclipse.stardust.engine.cli.common.DeploymentCallback;
import org.eclipse.stardust.engine.cli.common.DeploymentUtils;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.core.runtime.beans.removethis.KernelTweakingProperties;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.deploy.DeployedModelsView;
import org.eclipse.stardust.modeling.deploy.RuntimeUtil;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class DeployModelDialogSwt extends Dialog
{
   private static final String OVERWRITE_CARD = "overwrite"; //$NON-NLS-1$
   private static final String DEPLOY_CARD = "deploy"; //$NON-NLS-1$
   private static DeployModelDialogSwt instance;

   private ServiceFactory sf;
   private Button overwriteButton;
   private Button deployButton;
   private CardLayout card;
   private JPanel listPanel;
   private DeployedModelsView deployView;
   private DeployedModelsView overwriteView;
   private List<DeployedModelDescription> data;
   
   private boolean wasDeployed = false;
   private IModel model;

   public DeployModelDialogSwt(Shell parent)
   {
      super(parent);
   }

   public static boolean showDialog(ServiceFactory service, IModel model,
         Shell parent)
   {
      if (instance == null)
      {
         instance = new DeployModelDialogSwt(parent);
      }

      instance.setData(service, model);
      instance.open();
      
      return instance.wasDeployed;
   }

   private void setData(ServiceFactory service, IModel model)
   {
      this.sf = service;
      this.model = model;

//      version = null;

      data = new ArrayList<DeployedModelDescription>(sf.getQueryService().getAllModelDescriptions());

      if (!data.isEmpty())
      {
         overwriteView.setData(data, Collections.singletonList(model), true);
         overwriteButton.setEnabled(true);
      }
      else
      {
         overwriteButton.setEnabled(false);
      }

      deployView.setData(data, Collections.singletonList(model), false);

      if (!data.isEmpty())
      {
         overwriteButton.setSelection(true);
         card.show(listPanel, OVERWRITE_CARD);
      }
      else
      {
         deployButton.setSelection(true);
         card.show(listPanel, DEPLOY_CARD);
      }
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite panel = (Composite) super.createDialogArea(parent);

      ((GridLayout) panel.getLayout()).numColumns = 3;
      
      FormBuilder.createLabel(panel, Internal_ExportMessages.getString("LB_Action")); //$NON-NLS-1$
      this.overwriteButton = FormBuilder.createRadioButton(panel,
            Internal_ExportMessages.getString("LB_Overwrite")); //$NON-NLS-1$
      this.deployButton = FormBuilder.createRadioButton(panel,
            Internal_ExportMessages.getString("LB_DeployVersion")); //$NON-NLS-1$

      overwriteButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            card.show(listPanel, OVERWRITE_CARD);
         }
      });
      overwriteButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            card.show(listPanel, DEPLOY_CARD);
         }
      });

      Composite mainPanelContainer = FormBuilder.createComposite(panel, 1, 3);
      
      Frame mainPanel = SWT_AWT.getFrame(mainPanelContainer);
      mainPanel.setLayout(new BorderLayout());
      
      listPanel = new JPanel(card = new CardLayout());
      listPanel.add(overwriteView =
            new DeployedModelsView(), OVERWRITE_CARD);
      listPanel.add(deployView =
            new DeployedModelsView(), DEPLOY_CARD);
      mainPanel.add(listPanel);

      return panel;
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      
      shell.setText(Internal_ExportMessages.getString("DIALOG_Model"));
   }

   public void validateSettings() throws ValidationException
   {
      if (overwriteButton.getSelection() && overwriteView.getSelectedModelOID() == -1)
      {
         throw new ValidationException(Internal_ExportMessages.getString("MSG_NoModelToOverwrite"), false); //$NON-NLS-1$
      }
   }

   public void onOK()
   {
      try
      {
         if (overwriteButton.getSelection())
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
         units = RuntimeUtil.createDeploymentElements(Collections.<String>emptyList());
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
      String modelFile = "";

      String modelXml = XmlUtils.getXMLString(modelFile);
      
      if (!modelFile.endsWith(XpdlUtils.EXT_XPDL))
      {
         if (ParametersFacade.instance().getBoolean(
               KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, true))
         {
            modelXml = XpdlUtils.convertCarnot2Xpdl(modelXml);
         }
      }
      else
      {
         if ( !ParametersFacade.instance().getBoolean(
               KernelTweakingProperties.XPDL_MODEL_DEPLOYMENT, true))
         {
            modelXml = XpdlUtils.convertXpdl2Carnot(modelXml);
         }
      }

      DeploymentUtils.overwriteFromFile(sf, new MyDeploymentCallback(),
            overwriteView.getSelectedModelOID(), modelXml, overwriteView.getValidFrom(),
            null, overwriteView.getComment(), false, false);
      
      wasDeployed = true;
   }

   private boolean ignoreWarnings(List warnings)
   {
      ValidationException e = new ValidationException(
            Internal_ExportMessages.getString("MSG_ContinueDeploying"), warnings, true); //$NON-NLS-1$
      return false; // TODO ValidationExceptionDialog.showDialog(this, e, false);
   }

   private void showErrors(List errors)
   {
      ValidationException e = new ValidationException(
            Internal_ExportMessages.getString("MSG_NoErrors"), errors, false); //$NON-NLS-1$
      // TODO ValidationExceptionDialog.showDialog(this, e);
   }

   private class MyDeploymentCallback implements DeploymentCallback
   {
      public void reportErrors(List errors)
      {
         showErrors(errors);
      }

      public boolean reportWarnings(List warnings)
      {
         return ignoreWarnings(warnings);
      }
   }
}