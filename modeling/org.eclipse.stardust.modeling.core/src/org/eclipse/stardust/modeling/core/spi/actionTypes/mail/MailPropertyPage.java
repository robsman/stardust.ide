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
package org.eclipse.stardust.modeling.core.spi.actionTypes.mail;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IActionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;


public class MailPropertyPage extends AbstractModelElementPropertyPage
      implements IActionPropertyPage
{
   private static final String DATA_PATH = Diagram_Messages.DATA_PATH;

   private static final String DATA = Diagram_Messages.DATA;

   private static final String EMAIL = Diagram_Messages.EMAIL;

   private static final String RUNTIME_MESSAGE = Diagram_Messages.RUNTIME_MESSAGE;

   private static final String PREDEFINED_MESSAGE = Diagram_Messages.PREDEFINED_MESSAGE;

   private static final String ADDRESS = Diagram_Messages.ADDRESS;

   private static final String USER_PERFORMER = Diagram_Messages.LB_MailAction_UserPerformer;

   private static final String MODEL_PARTICIPANT = Diagram_Messages.MODEL_PARTICIPANT;

   private static final String CONTENT = Diagram_Messages.CONTENT;

   private static final String SEND = Diagram_Messages.SEND;

   private static final String MAIL = "email"; //$NON-NLS-1$

   private static final String MAIL_RECEIVER_TYPE = "ag.carnot.workflow.spi.providers.actions.mail.ReceiverType"; //$NON-NLS-1$

   private static final String RECEIVER_TYPE = "carnot:engine:receiverType"; //$NON-NLS-1$

   private static final String PARTICIPANT = "participant"; //$NON-NLS-1$

   private static final String RECEIVER = "carnot:engine:receiver"; //$NON-NLS-1$

   private static final String MAIL_BODY_TEMPLATE = "carnot:engine:mailBodyTemplate"; //$NON-NLS-1$

   private static final Object PREDEFINED_ADRESS = "email"; //$NON-NLS-1$

   private static final String MAIL_ADDRESS = "carnot:engine:emailAddress"; //$NON-NLS-1$

   private static final String MAIL_TYPE = "mail"; //$NON-NLS-1$

   private static final String MAIL_DATA_PATH = "carnot:engine:mailBodyDataPath"; //$NON-NLS-1$

   private static final String MAIL_DATA = "carnot:engine:mailBodyData"; //$NON-NLS-1$
   
   private static final String MAIL_SUBJECT = "carnot:engine:mailSubject"; //$NON-NLS-1$

   private Table receiverList;

   private Button participantButton;

   private Button predefinedAdressButton;

   private Button userPerformerButton;

   private Button predefinedMessageButton;

   private Button runtimeDefinedMessageButton;

   private Text predefinedMsgContentTextArea;

   private Text emailAddressText;

   private StackLayout receiverCompositeLayout;

   private StackLayout contentCompositeLayout;

   private Composite predefinedMsgComposite;

   private Composite runtimeDefinedMsgComposite;

   private Composite participantComposite;

   private Composite addressComposite;

   private Composite userPerformerComposite;

   private LabeledViewer dataLabel;

   private AccessPathBrowserComposite dataPathBrowser;

   private LabeledText dataPathLabel;

   private TableViewer receiverListViewer;

   private Composite contentComposite;

   private static final String[] attributeList = {
         MAIL_ADDRESS, RECEIVER_TYPE, RECEIVER, MAIL_BODY_TEMPLATE, MAIL_DATA,
         MAIL_DATA_PATH};

   private static final String CURRENT_PERFORMER = "currentPerformer"; //$NON-NLS-1$

   private Text mailSubject;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(MAIL_TYPE))
      {
         receiverList.removeAll();
         String attributeValue = null;

         IModelParticipant receiver = (IModelParticipant) AttributeUtil.getIdentifiable(
               (IExtensibleElement) element, RECEIVER);
         initReceiverList((IExtensibleElement) element, receiver);

         ModelType model = ModelUtils.findContainingModel(element);
         WidgetBindingManager binding = getWidgetBindingManager();

         binding.getModelBindingManager().bind(
               new Data2DataPathModelAdapter2(model, model.getData()),
               new Data2DataPathWidgetAdapter2(dataLabel.getViewer(), dataPathBrowser,
                     DirectionType.IN_LITERAL));

         if ((attributeValue = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, MAIL_ADDRESS)) != null)
         {
            emailAddressText.setText(attributeValue);
         }
         userPerformerButton
               .setEnabled(element.eContainer().eContainer() instanceof ActivityType);
         if ((attributeValue = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, RECEIVER_TYPE)) != null)
         {
            participantButton.setSelection(attributeValue.equals(PARTICIPANT));
            predefinedAdressButton.setSelection(attributeValue.equals(PREDEFINED_ADRESS));
            userPerformerButton.setSelection(attributeValue.equals(CURRENT_PERFORMER));
            if (participantButton.getSelection())
            {
               receiverCompositeLayout.topControl = participantComposite;
            }
            else if (predefinedAdressButton.getSelection())
            {
               receiverCompositeLayout.topControl = addressComposite;
            }
            else if (userPerformerButton.getSelection())
            {
               receiverCompositeLayout.topControl = userPerformerComposite;
            }
         }
         if ((attributeValue = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, MAIL_BODY_TEMPLATE)) != null)
         {
            predefinedMessageButton.setSelection(true);
            runtimeDefinedMessageButton.setSelection(false);
            selectPredefinedMessage();
            predefinedMsgContentTextArea.setText(attributeValue);
         }
         if ((attributeValue = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, MAIL_DATA)) != null)
         {
            runtimeDefinedMessageButton.setSelection(true);
            predefinedMessageButton.setSelection(false);
            selectRuntimeDefinedMessage();
         }
         
         if ((attributeValue = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, MAIL_SUBJECT)) == null)
         {
            attributeValue = Diagram_Messages.DEFAULT_SUBJECT;
         }
         mailSubject.setText(attributeValue);
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (ActionTypeUtil.getActionType(element).getId().equals(MAIL_TYPE))
      {
         AttributeUtil.clearExcept((IExtensibleElement) element, attributeList);
         if (participantButton != null)
         {
            if (participantButton.getSelection())
            {
               IModelParticipant participant = receiverList.getSelection().length == 0
                     ? null
                     : (IModelParticipant) ((IStructuredSelection) receiverListViewer
                           .getSelection()).getFirstElement();
               if (participant != null)
               {
                  AttributeUtil.setReference((IExtensibleElement) element,
                        RECEIVER, participant);
                  AttributeUtil.setAttribute((IExtensibleElement) element,
                        RECEIVER_TYPE, MAIL_RECEIVER_TYPE, PARTICIPANT);
               }
            }
            else if (predefinedAdressButton.getSelection())
            {
               AttributeUtil.setAttribute((IExtensibleElement) element, MAIL_ADDRESS,
                     emailAddressText.getText());
               AttributeUtil.setAttribute((IExtensibleElement) element, RECEIVER_TYPE,
                     MAIL_RECEIVER_TYPE, MAIL);
            }
            else if (userPerformerButton.getSelection())
            {
               AttributeUtil.setAttribute((IExtensibleElement) element, RECEIVER_TYPE,
                     "ag.carnot.workflow.spi.providers.actions.mail.ReceiverType", //$NON-NLS-1$
                     CURRENT_PERFORMER);
            }
            if (predefinedMessageButton.getSelection())
            {
               AttributeUtil.setAttribute((IExtensibleElement) element,
                     MAIL_BODY_TEMPLATE, predefinedMsgContentTextArea.getText());
               AttributeUtil.setAttribute((IExtensibleElement) element, MAIL_DATA, null);
               AttributeUtil.setAttribute((IExtensibleElement) element, MAIL_DATA_PATH,
                     null);
            }
            else if (runtimeDefinedMessageButton.getSelection())
            {
               AttributeUtil.setAttribute((IExtensibleElement) element,
                     MAIL_BODY_TEMPLATE, null);
            }
         }
         AttributeUtil.setAttribute((IExtensibleElement) element, MAIL_SUBJECT,
               mailSubject.getText());
      }
   }

   private void initReceiverList(IExtensibleElement element, IModelParticipant receiver)
   {
      java.util.List participantList = new ArrayList();
      ModelType model = ModelUtils.findContainingModel(element);
      participantList.addAll(model.getConditionalPerformer());
      participantList.addAll(model.getOrganization());
      participantList.addAll(model.getRole());
      receiverListViewer = new TableViewer(receiverList);
      receiverListViewer.setSorter(new ViewerSorter());
      receiverListViewer.setLabelProvider(new LabelProvider()
      {
         public Image getImage(Object element)
         {
            return DiagramPlugin.getImage(getEditor().getIconFactory().getIconFor((EObject) element));
         }

         public String getText(Object element)
         {
            return ((IModelParticipant) element).getName();
         }
      });

      for (Iterator iter = participantList.iterator(); iter.hasNext();)
      {
         IModelParticipant participant = (IModelParticipant) iter.next();
         receiverListViewer.add(participant);
      }
      if (receiver != null)
      {
         receiverListViewer.setSelection(new StructuredSelection(receiver));
      }
      if (receiverListViewer.getSelection().isEmpty() && participantList.size() > 0)
      {
         receiverListViewer.getTable().select(0);
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1, 2);
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      ((GridLayout) composite.getLayout()).makeColumnsEqualWidth = true;      
      Group receiverGroup = FormBuilder.createGroup(composite, SEND, 3);

      participantButton = FormBuilder.createRadioButton(receiverGroup, MODEL_PARTICIPANT);
      participantButton.setSelection(true);
      predefinedAdressButton = FormBuilder.createRadioButton(receiverGroup, ADDRESS);
      userPerformerButton = FormBuilder.createRadioButton(receiverGroup, USER_PERFORMER);

      createReceiverComposite(receiverGroup);
      
      Group subjectGroup = FormBuilder.createGroup(composite, Diagram_Messages.SUBJECT, 1);
      subjectGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData());
      mailSubject = FormBuilder.createText(subjectGroup);

      Group contentGroup = FormBuilder.createGroup(composite, CONTENT, 3);

      predefinedMessageButton = FormBuilder.createRadioButton(contentGroup,
            PREDEFINED_MESSAGE);
      predefinedMessageButton.setSelection(true);
      runtimeDefinedMessageButton = FormBuilder.createRadioButton(contentGroup,
            RUNTIME_MESSAGE);

      createContentComposite(contentGroup);

      return composite;
   }

   private void createContentComposite(Group contentGroup)
   {
      contentComposite = FormBuilder.createComposite(contentGroup, 1, 2);
      contentCompositeLayout = new StackLayout();
      contentComposite.setLayout(contentCompositeLayout);

      predefinedMsgComposite = FormBuilder.createComposite(contentComposite, 1);
      predefinedMsgContentTextArea = FormBuilder
            .createTextArea(predefinedMsgComposite, 2);

      runtimeDefinedMsgComposite = FormBuilder.createComposite(contentComposite, 2);

      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(
            runtimeDefinedMsgComposite, DATA);
      ComboViewer dataText = new ComboViewer(FormBuilder
            .createCombo(runtimeDefinedMsgComposite));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));
      dataLabel = new LabeledViewer(dataText, label);

      label = FormBuilder.createLabelWithRightAlignedStatus(runtimeDefinedMsgComposite,
            DATA_PATH);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), runtimeDefinedMsgComposite,
            DATA_PATH);
      dataPathLabel = new LabeledText(dataPathBrowser.getMethodText(), label);

      contentCompositeLayout.topControl = predefinedMsgComposite;

      predefinedMessageButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            selectPredefinedMessage();
         }
      });
      runtimeDefinedMessageButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            selectRuntimeDefinedMessage();
         }
      });
   }

   private void selectRuntimeDefinedMessage()
   {
      contentCompositeLayout.topControl = runtimeDefinedMsgComposite;
      contentComposite.layout();

      IExtensibleElement element = (IExtensibleElement) getModelElement();
      WidgetBindingManager binding = getWidgetBindingManager();

      binding.bind(dataLabel, element, MAIL_DATA,
            ModelUtils.findContainingModel(element), CarnotWorkflowModelPackage.eINSTANCE
                  .getModelType_Data());
      binding.bind(dataPathLabel, element, MAIL_DATA_PATH);
   }

   private void selectPredefinedMessage()
   {
      contentCompositeLayout.topControl = predefinedMsgComposite;
      contentComposite.layout();

      IExtensibleElement element = (IExtensibleElement) getModelElement();
      WidgetBindingManager binding = getWidgetBindingManager();

      binding.unbind(dataLabel, element, MAIL_DATA);
      binding.unbind(dataPathLabel, element, MAIL_DATA_PATH);
   }

   private void createReceiverComposite(final Group receiverGroup)
   {
      final Composite receiverComposite = FormBuilder
            .createComposite(receiverGroup, 1, 2);
      receiverCompositeLayout = new StackLayout();
      receiverComposite.setLayout(receiverCompositeLayout);
  
      participantComposite = FormBuilder.createComposite(receiverComposite, 2);
      receiverList = new Table(participantComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
      receiverList.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(1));

      addressComposite = FormBuilder.createComposite(receiverComposite, 2);
      FormBuilder.createLabel(addressComposite, EMAIL);
      emailAddressText = FormBuilder.createText(addressComposite);

      userPerformerComposite = FormBuilder.createComposite(receiverComposite, 1);

      receiverCompositeLayout.topControl = participantComposite;

      predefinedAdressButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            receiverCompositeLayout.topControl = addressComposite;
            receiverComposite.layout();
         }
      });
      participantButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            receiverCompositeLayout.topControl = participantComposite;
            receiverComposite.layout();
         }
      });
      userPerformerButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            receiverCompositeLayout.topControl = userPerformerComposite;
            receiverComposite.layout();
         }
      });
   }
}