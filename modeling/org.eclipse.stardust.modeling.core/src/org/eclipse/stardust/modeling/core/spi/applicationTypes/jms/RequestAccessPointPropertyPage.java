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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.jms;

import org.eclipse.stardust.engine.extensions.jms.app.JMSLocation;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import ag.carnot.workflow.model.PredefinedConstants;

/**
 * @author fherinean
 * @version $Revision$
 */
public class RequestAccessPointPropertyPage extends AbstractModelElementPropertyPage
{
   private static final String[][] LOCATION = {
         {JMSLocation.HEADER.getId(), "Header"}, {JMSLocation.BODY.getId(), "Body"} //$NON-NLS-1$ //$NON-NLS-2$
   };

   private LabeledCombo locationCombo;

   private LabeledText idText;
   private LabeledText nameText;

   private LabelWithStatus lblClassName;

   private TypeSelectionComposite classNameBrowser;

   private LabeledText valueText;

   private Button autoIdButton;
   private Button[] buttons;

   
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
            idText.getText().setEditable(false);
            String computedId = ModelUtils.computeId(nameText.getText().getText());
            idText.getText().setText(computedId);            
         }
         else
         {
            idText.getText().setEditable(true);            
         }         
      }
   };            
   
   private ModifyListener idSyncListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         Text text = (Text) e.widget;
         String name = text.getText();
         if (autoIdButton.getSelection())
         {
            idText.getText().setText(ModelUtils.computeId(name));
         }
      }
   };
   
   
   
   
   protected boolean includeDefaultValue()
   {
      return true;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      nameText.getText().removeModifyListener(idSyncListener);

      WidgetBindingManager binding = getWidgetBindingManager();

      AccessPointType ap = (AccessPointType) element;

      binding.bind(idText, ap, CarnotWorkflowModelPackage.eINSTANCE
            .getIIdentifiableElement_Id());
      binding.bind(nameText, ap, CarnotWorkflowModelPackage.eINSTANCE
            .getIIdentifiableElement_Name());

      binding.getValidationBindingManager().bind(ap,
            PredefinedConstants.JMS_LOCATION_PROPERTY, locationCombo.getLabel());

      String location = AttributeUtil.getAttributeValue(ap,
            PredefinedConstants.JMS_LOCATION_PROPERTY);
      locationCombo.getCombo().select(-1);
      for (int i = 0; i < LOCATION.length; i++)
      {
         if (LOCATION[i][0].equals(location))
         {
            locationCombo.getCombo().select(i);
            break;
         }
      }

      binding.bind(new LabeledText(classNameBrowser.getText(), lblClassName), ap,
            PredefinedConstants.CLASS_NAME_ATT);

      if (includeDefaultValue())
      {
         binding.bind(valueText, ap, PredefinedConstants.DEFAULT_VALUE_ATT);
      }

      nameText.getText().addModifyListener(idSyncListener);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AccessPointType ap = (AccessPointType) element;
      int locationIndex = locationCombo.getCombo().getSelectionIndex();
      if (locationIndex < 0)
      {
         AttributeUtil.setAttribute(ap, PredefinedConstants.JMS_LOCATION_PROPERTY, null);
      }
      else
      {
         AttributeUtil.setAttribute(ap, PredefinedConstants.JMS_LOCATION_PROPERTY,
               JMSLocation.class.getName(), LOCATION[locationIndex][0]);
      }
      GenericUtils.setAutoIdValue(getModelElement(), autoIdButton.getSelection());                        
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      this.locationCombo = FormBuilder.createLabeledCombo(composite,
            Diagram_Messages.LB_Location);
      for (int i = 0; i < LOCATION.length; i++)
      {
         locationCombo.getCombo().add(LOCATION[i][1]);
      }

      this.nameText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_Name);
      this.idText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_Id);

      autoIdButton = FormBuilder.createCheckBox(composite,
            Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue(getModelElement());
      autoIdButton.setSelection(autoIdButtonValue);
      if(autoIdButtonValue)
      {
         idText.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);
      
      this.lblClassName = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.LB_SPI_Type);
      this.classNameBrowser = new TypeSelectionComposite(composite, Diagram_Messages.LB_SPI_Type);
      if (includeDefaultValue())
      {
         this.valueText = FormBuilder.createLabeledText(composite,
               Diagram_Messages.LB_DefaultValue);
      }

      return composite;
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         if (manager != null)
         {
            manager.updateButtons(getModelElement(), buttons);
         }
      }
      super.setVisible(visible);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      if (manager != null)
      {
         buttons = manager.createButtons(parent);
      }
   }
}