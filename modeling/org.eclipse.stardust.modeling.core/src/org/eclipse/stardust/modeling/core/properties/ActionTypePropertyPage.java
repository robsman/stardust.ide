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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.UnbindActionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.spi.IModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.SpiPropertyPage;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.MetaTypeModelingUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;


public class ActionTypePropertyPage extends AbstractModelElementPropertyPage
{
   protected LabeledText txtId;
   protected LabeledText txtName;

   private ComboViewer eventActionTypeCombo;

   private Composite spiArea;

   private StackLayout spiLayout;

   private Map spiPages = new HashMap();

   private Map spiControls = new HashMap();

   private IIdentifiableModelElement eventActionType;

   private Button[] buttons;
   private Button autoIdButton;

   
   private SelectionListener autoIdListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {
      }

      public void widgetSelected(SelectionEvent e)
      {
         boolean selection = ((Button) e.widget).getSelection();
         if(selection)
         {
            txtId.getText().setEditable(false);
            String computedId = NameIdUtils.createIdFromName(null, getModelElement());
            txtId.getText().setText(computedId);            
         }
         else
         {
            txtId.getText().setEditable(true);            
         }         
      }
   };         
   
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         Text text = (Text) e.widget;
         String name = text.getText();
         if (autoIdButton.getSelection())
         {
            String computedId = NameIdUtils.createIdFromName(null, getModelElement(), name);            
            txtId.getText().setText(computedId);
         }
      }
   };

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      eventActionType = (IIdentifiableModelElement) element;

      txtName.getText().removeModifyListener(listener);

      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.bind(txtId, element, PKG_CWM.getIIdentifiableElement_Id());
      wBndMgr.bind(txtName, element, PKG_CWM.getIIdentifiableElement_Name());

      ((Combo) eventActionTypeCombo.getControl()).removeAll();

      IConfigurationElement type = addValidProcessActionsToCombo();

      eventActionTypeCombo.setSelection(type == null ? null : new StructuredSelection(
            type));

      wBndMgr.getModelBindingManager().updateWidgets(element);
      txtName.getText().addModifyListener(listener);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      SpiPropertyPage page = (SpiPropertyPage) spiPages.get(ActionTypeUtil
            .getActionTypeConfigurationElement(eventActionType));
      if (page != null)
      {
         page.loadElementFromFields(symbol, element);
      }
      GenericUtils.setAutoIdValue(getModelElement(), autoIdButton.getSelection());                  
   }

   public Control createBody(Composite content)
   {
      Composite composite = FormBuilder.createComposite(content, 2);

      this.txtName = FormBuilder
         .createLabeledText(composite, Diagram_Messages.LB_Name);
      this.txtId = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);

      autoIdButton = FormBuilder.createCheckBox(composite,
            Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue(getModelElement());
      autoIdButton.setSelection(autoIdButtonValue);
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_FORMBUILDER_ActionType);
      Combo combo = FormBuilder.createCombo(composite);
      eventActionTypeCombo = new ComboViewer(combo);
      eventActionTypeCombo.setSorter(new ViewerSorter());
      eventActionTypeCombo.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            IConfigurationElement type = (IConfigurationElement) element;
            return type.getAttribute(SpiConstants.NAME);
         }
      });
      eventActionTypeCombo.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            changeActionType(event.getSelection());
         }
      });

      spiArea = FormBuilder.createComposite(composite, 1, 2);
      spiLayout = new StackLayout();
      spiArea.setLayout(spiLayout);

      return composite;
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

   private IConfigurationElement addValidProcessActionsToCombo()
   {
      IConfigurationElement match = null;
      List supportedActions = ActionTypeUtil.getSupportedActionTypes(
            (EventHandlerType) eventActionType.eContainer(), ActionTypeUtil
                  .getContext(eventActionType));
      for (int i = 0; i < supportedActions.size(); i++)
      {
         IConfigurationElement type = (IConfigurationElement) supportedActions.get(i);
         eventActionTypeCombo.add(type);
         if (type.getAttribute(SpiConstants.ID).equals(
               ActionTypeUtil.getActionType(eventActionType).getId()))
         {
            match = type;
         }
      }
      return match;
   }

   protected void changeActionType(ISelection selection)
   {
      if (selection instanceof IStructuredSelection && !selection.isEmpty())
      {
         final IConfigurationElement type = (IConfigurationElement) ((IStructuredSelection) selection)
               .getFirstElement();
         setActionType(ModelUtils.findContainingModel(eventActionType), eventActionType,
               type);

         try
         {
            SpiPropertyPage page = (SpiPropertyPage) spiPages.get(type);
            if (page == null)
            {
               if (type.getAttribute(SpiConstants.PROPERTY_PAGE_CLASS) == null)
               {
                  page = getEmptyPage();
               }
               else
               {
                  page = new SpiPropertyPage(new ConfigurationElement(type));
               }
               page.noDefaultAndApplyButton();
               page.setContainer(getContainer());
               page.setElement(getElement());
               Composite composite = FormBuilder.createComposite(spiArea, 1);
               composite.setLayout(new FillLayout());
               page.createControl(composite);

               registerValidation(page);

               spiPages.put(type, page);
               spiControls.put(type, composite);
            }
            page.elementChanged();
            spiLayout.topControl = (Control) spiControls.get(type);
            spiArea.layout();
         }
         catch (Exception ex)
         {
            DiagramPlugin.warn(ex.getMessage());
         }
      }
   }

   public static void setActionType(ModelType model, IIdentifiableModelElement element,
         IConfigurationElement config)
   {
      EventActionTypeType type = (EventActionTypeType) ModelUtils
            .findIdentifiableElement(model.getEventActionType(), config
                  .getAttribute(SpiConstants.ID));
      if (type == null)
      {
         CreateMetaTypeCommand cmdAddConditionType = MetaTypeModelingUtils
               .getCreateEventActionTypeCmd(config);
         cmdAddConditionType.setParent(model);
         cmdAddConditionType.execute();
         type = (EventActionTypeType) cmdAddConditionType.getModelElement();
      }
      if (element instanceof EventActionType)
      {
         ((EventActionType) element).setType(type);
      }
      else if (element instanceof BindActionType)
      {
         ((BindActionType) element).setType(type);
      }
      else if (element instanceof UnbindActionType)
      {
         ((UnbindActionType) element).setType(type);
      }
   }

   private SpiPropertyPage getEmptyPage()
   {
      return new SpiPropertyPage(new IModelElementPropertyPage()
      {
         IAdaptable element;

         public void loadFieldsFromElement(IModelElementNodeSymbol symbol,
               IModelElement element)
         {}

         public void loadElementFromFields(IModelElementNodeSymbol symbol,
               IModelElement element)
         {}

         public Control createBody(Composite parent)
         {
            return FormBuilder.createComposite(parent, 1);
         }

         public void contributeButtons(Composite parent)
         {}

         public void contributeVerticalButtons(Composite parent)
         {}

         public IAdaptable getElement()
         {
            return element;
         }

         public void setElement(IAdaptable element)
         {
            this.element = element;
         }

         public void setContainer(IPreferencePageContainer container)
         {}

         public void dispose()
         {}
         
         public void setDelegateContainer(AbstractModelElementPropertyPage page)
         {            
         }   
      });
   }
}