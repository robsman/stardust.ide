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
package org.eclipse.stardust.modeling.integration.spring.application;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.BindingManager;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.integration.spring.SpringConstants;
import org.eclipse.stardust.modeling.integration.spring.SpringModelingPlugin;
import org.eclipse.stardust.modeling.integration.spring.Spring_Messages;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanBrowser extends Dialog
{
   private BindingManager bndMgr = new BindingManager();

   private String appContextFile;

   private BeanDefinitionRegistry beanRegistry;

   private ListViewer vwBeans;

   private String beanId;

   private String beanType;

   public BeanBrowser(Shell parentShell)
   {
      super(parentShell);
   }

   public String getBeanId()
   {
      return beanId;
   }

   public String getBeanType()
   {
      return beanType;
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite dlgArea = (Composite) super.createDialogArea(parent);

      GridLayout layout = (GridLayout) dlgArea.getLayout();
      layout.numColumns = 3;
      layout.makeColumnsEqualWidth = false;

      LabeledText txtAppContextFile = FormBuilder.createLabeledText(dlgArea,
            Spring_Messages.LB_AppCtxFile);
      bndMgr.bind(new DirectModelAdapter()
      {
         public Object getModel()
         {
            return appContextFile;
         }

         public void updateModel(Object value)
         {
            appContextFile = (String) value;
         }
      }, BindingManager.createWidgetAdapter(txtAppContextFile.getText()));

      FormBuilder.createButton(dlgArea, Spring_Messages.LB_Reload, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            reloadApplicationContext();
         }

      });

      FormBuilder.createLabel(dlgArea, Spring_Messages.LB_DecBeans);
      this.vwBeans = new ListViewer(FormBuilder.createList(dlgArea, 2));
      vwBeans.setContentProvider(new ArrayContentProvider());
      ((GridData) vwBeans.getControl().getLayoutData()).heightHint = 100;

      String storedPath = SpringModelingPlugin.instance()
            .getPluginPreferences()
            .getString(SpringConstants.PRF_APPLICATION_CONTEXT_PATH);
      if ( !StringUtils.isEmpty(storedPath))
      {
         txtAppContextFile.getText().setText(storedPath);
         reloadApplicationContext();
      }

      return dlgArea;
   }

   protected void okPressed()
   {
      if (null != beanRegistry)
      {
         ISelection selBeanId = vwBeans.getSelection();
         if ( !selBeanId.isEmpty() && (selBeanId instanceof StructuredSelection))
         {
            this.beanId = (String) ((StructuredSelection) selBeanId).getFirstElement();

            BeanDefinition beanDef = beanRegistry.getBeanDefinition(beanId);
            if (beanDef instanceof AbstractBeanDefinition)
            {
               this.beanType = ((AbstractBeanDefinition) beanDef).getBeanClassName();
            }
            else
            {
               this.beanType = null;
            }
         }
      }
      else
      {
         this.beanId = null;
         this.beanType = null;
      }

      SpringModelingPlugin.instance().getPluginPreferences().setValue(
            SpringConstants.PRF_APPLICATION_CONTEXT_PATH, appContextFile);

      super.okPressed();
   }

   protected void cancelPressed()
   {
      super.cancelPressed();

      this.beanId = null;
      this.beanType = null;
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);

      shell.setText(Spring_Messages.LB_BrowseBeans);
   }

   private void reloadApplicationContext()
   {
      IResource appContextResource = ResourcesPlugin.getWorkspace().getRoot().findMember(
            appContextFile);

      if ((null != appContextResource) && appContextResource.exists()
            && (appContextResource instanceof IFile)
            && (null != appContextResource.getLocation()))
      {
         try
         {
            String appContextPath = appContextResource.getLocation().toOSString();
            // escaping leading windows drive spec
            if ( !appContextPath.startsWith("/")) //$NON-NLS-1$
            {
               appContextPath = "/" + appContextPath; //$NON-NLS-1$
            }
            this.beanRegistry = loadBeans(appContextPath);
            String[] beanNames = beanRegistry.getBeanDefinitionNames();

            vwBeans.setInput(beanNames);
         }
         catch (BeansException e)
         {
            MessageDialog.openError(getParentShell(), Spring_Messages.MSG_FailedLoadingBeanDef,
                  e.getMessage());
         }
      }
   }

   private BeanDefinitionRegistry loadBeans(String appContextPath)
   {
      BeanDefinitionRegistry beanFactory = new DefaultListableBeanFactory();
      XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
            beanFactory);

      // Configure the bean definition reader with this context's
      // resource loading environment.
      beanDefinitionReader.setResourceLoader(new BeanBrowserResourceLoader());
      beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(
            beanDefinitionReader.getResourceLoader()));
      // don't resolve bean class names
      beanDefinitionReader.setBeanClassLoader(null);

      beanDefinitionReader.loadBeanDefinitions(appContextPath);
      
      return beanFactory;
   }
}
