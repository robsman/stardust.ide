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
package org.eclipse.stardust.modeling.core.properties;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.spi.SpiPropertyPage;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ConditionPropertyPage extends SpiPropertyPage
{
   private static final String CONSUME = Diagram_Messages.CONSUME;

   private static final String LOG = Diagram_Messages.LOG;

   private static final String AUTO_BIND = Diagram_Messages.AUTO_BIND;

   protected LabeledText txtId;
   protected LabeledText txtName;

   private Button autoBindCheckButton;

   private Button logHandlerCheckButton;

   private Button consumeOnMatchCheckButton;

   private Button[] buttons;
   
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if (GenericUtils.getAutoIdValue())
         {
            String computedId = NameIdUtils.createIdFromName(null, getModelElement());            
            txtId.getText().setText(computedId);
         }
      }
   };

   public ConditionPropertyPage(ConfigurationElement config) throws CoreException
   {
      super(config);
   }

   public Control createBody(Composite content)
   {
      Composite composite = FormBuilder.createComposite(content, 2);

      this.txtName = FormBuilder
            .createLabeledText(composite, Diagram_Messages.LB_Name);
      this.txtId = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);

      boolean autoIdButtonValue = GenericUtils.getAutoIdValue();
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }

      Composite checkBoxComposite = FormBuilder.createComposite(composite, 3);
      ((GridLayout) checkBoxComposite.getLayout()).marginHeight = 15;
      ((GridLayout) checkBoxComposite.getLayout()).marginWidth = 0;
      GridData gdCheckBoxComposite = new GridData();
      gdCheckBoxComposite.horizontalSpan = 2;
      checkBoxComposite.setLayoutData(gdCheckBoxComposite);
      autoBindCheckButton = FormBuilder.createCheckBox(checkBoxComposite, AUTO_BIND);
      logHandlerCheckButton = FormBuilder.createCheckBox(checkBoxComposite, LOG);
      consumeOnMatchCheckButton = FormBuilder.createCheckBox(checkBoxComposite, CONSUME);

      super.createBody(composite);

      return composite;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);

      txtName.getText().removeModifyListener(listener);

      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      Map extensions = registry
            .getExtensions(CarnotConstants.CONDITION_TYPES_EXTENSION_POINT_ID);
      IConfigurationElement extension = (IConfigurationElement) extensions
            .get(((EventHandlerType) element).getType().getId());
      if (extension.getAttribute(SpiConstants.EH_IMPLEMENTATION).equalsIgnoreCase("pull")) //$NON-NLS-1$
      {
         consumeOnMatchCheckButton.setEnabled(false);
      }
      else
      {
         autoBindCheckButton.setEnabled(false);
      }

      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.bind(txtId, element, PKG_CWM.getIIdentifiableElement_Id());
      wBndMgr.bind(txtName, element, PKG_CWM.getIIdentifiableElement_Name());

      wBndMgr.getModelBindingManager().bind(element,
            PKG_CWM.getEventHandlerType_AutoBind(), autoBindCheckButton);
      wBndMgr.getModelBindingManager().bind(element,
            PKG_CWM.getEventHandlerType_ConsumeOnMatch(), consumeOnMatchCheckButton);
      wBndMgr.getModelBindingManager().bind(element,
            PKG_CWM.getEventHandlerType_LogHandler(), logHandlerCheckButton);

      wBndMgr.getModelBindingManager().updateWidgets(element);
      txtName.getText().addModifyListener(listener);
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         manager.updateButtons(getModelElement(), buttons);
      }
      super.setVisible(visible);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      buttons = manager.createButtons(parent);
   }
}