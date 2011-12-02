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
package org.eclipse.stardust.modeling.core.spi.triggerTypes.mail;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.ModelElementAdaptable;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.spi.triggerTypes.ParameterMappingTablePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;


public class MailPropertyPage extends AbstractModelElementPropertyPage
{
   private static final String REMOVE = Diagram_Messages.REMOVE;

   private static final String SEEN = Diagram_Messages.SEEN; 

   private static final String LEAVE = Diagram_Messages.LEAVE; 

   private static final String NEW_MAILS = Diagram_Messages.NEW_MAILS; 

   private static final String NOT_SEEN = Diagram_Messages.NOT_SEEN; 

   private static final String EXISTING = Diagram_Messages.EXISTING; 

   private static final String IMAP = "IMAP4"; //$NON-NLS-1$

   private static final String POP = "POP3"; //$NON-NLS-1$

   private static final String HOST = CarnotConstants.ENGINE_SCOPE + "host"; //$NON-NLS-1$

   private static final String MAIL_TRIGGER_PROVIDER = CarnotConstants.WORKFLOW_SPI_PROVIDER
         + "triggers.mail."; //$NON-NLS-1$

   private static final String FLAGS = CarnotConstants.ENGINE_SCOPE + "mailFlags"; //$NON-NLS-1$

   private static final String FLAGS_TYPE = MAIL_TRIGGER_PROVIDER
         + "MailTriggerMailFlags"; //$NON-NLS-1$

   private static final String SENDER_PREDICATE = CarnotConstants.ENGINE_SCOPE
         + "mailSenderPredicate"; //$NON-NLS-1$

   private static final String SUBJECT_PREDICATE = CarnotConstants.ENGINE_SCOPE
         + "mailSubjectPredicate"; //$NON-NLS-1$

   private static final String SELECTOR_PREDICATE = CarnotConstants.ENGINE_SCOPE
         + "selectorPredicate"; //$NON-NLS-1$

   private static final String MAILBOX_ACTION = CarnotConstants.ENGINE_SCOPE
         + "mailboxAction"; //$NON-NLS-1$

   private static final String MAILBOX_ACTION_TYPE = MAIL_TRIGGER_PROVIDER
         + "MailTriggerMailboxAction"; //$NON-NLS-1$

   private static final String PASSWORD = CarnotConstants.ENGINE_SCOPE + "password"; //$NON-NLS-1$

   private static final String PROTOCOL = CarnotConstants.ENGINE_SCOPE + "protocol"; //$NON-NLS-1$

   private static final String PROTOCOL_TYPE = MAIL_TRIGGER_PROVIDER + "MailProtocol"; //$NON-NLS-1$

   private static final String MAIL_CLASS_ATT = CarnotConstants.WORKFLOW_RUNTIME + "Mail"; //$NON-NLS-1$

   private static final String USER = CarnotConstants.ENGINE_SCOPE + "user"; //$NON-NLS-1$

   public static final String PARAMETER_MAPPING_TABLE_ID =
      ParameterMappingTablePage.PARAMETER_MAPPING_TABLE_ID;

   private static final String PARAMETER_MAPPING_TABLE_LABEL = Diagram_Messages.PARAMETER_MAPPING_TABLE_LABEL; 

   private Combo protocolCombo;

   private Text hostText;

   private Text usrText;

   private Text passwdText;

   private Combo flagsCombo;

   private Text senderPredicateText;

   private Text subjectPredicateText;

   private Text selectorPredicateText;

   private Combo mailboxActionCombo;

   private IExtensibleElement extensibleElement;

   private HashMap comboValueMap;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      extensibleElement = (IExtensibleElement) element;
      getAttributeValue(PROTOCOL, protocolCombo);
      getAttributeValue(HOST, hostText);
      getAttributeValue(USER, usrText);
      getAttributeValue(PASSWORD, passwdText);
      getAttributeValue(FLAGS, flagsCombo);
      getAttributeValue(SENDER_PREDICATE, senderPredicateText);
      getAttributeValue(SUBJECT_PREDICATE, subjectPredicateText);
      getAttributeValue(SELECTOR_PREDICATE, selectorPredicateText);
      getAttributeValue(MAILBOX_ACTION, mailboxActionCombo);
      ((TriggerType) element).getAccessPoint().clear();
      ((TriggerType) element).getAccessPoint().add(createMailAccessPoint());
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      extensibleElement = (IExtensibleElement) element;
      if (extensibleElement != null)
      {
         extensibleElement.getAttribute().clear();
         setAttributeValue(PROTOCOL, PROTOCOL_TYPE, protocolCombo);
         setAttributeValue(HOST, null, hostText);
         setAttributeValue(USER, null, usrText);
         setAttributeValue(PASSWORD, null, passwdText);
         setAttributeValue(FLAGS, FLAGS_TYPE, flagsCombo);
         setAttributeValue(SENDER_PREDICATE, null, senderPredicateText);
         setAttributeValue(SUBJECT_PREDICATE, null, subjectPredicateText);
         setAttributeValue(SELECTOR_PREDICATE, null, selectorPredicateText);
         setAttributeValue(MAILBOX_ACTION, MAILBOX_ACTION_TYPE, mailboxActionCombo);
      }
   }

   private void setAttributeValue(String attrName, String attrType, Control control)
   {
      if (control instanceof Text)
      {
         AttributeUtil.setAttribute(extensibleElement, attrName, ((Text) control)
               .getText());
      }
      else if (control instanceof Combo)
      {
         AttributeUtil.setAttribute(extensibleElement, attrName, attrType,
               (String) comboValueMap.get(((Combo) control).getText()));
      }
   }

   private void getAttributeValue(String attrName, Control control)
   {
      String value;
      if ((value = AttributeUtil.getAttributeValue(extensibleElement, attrName)) != null)
      {
         if (control instanceof Text)
         {
            ((Text) control).setText(value);
         }
         else if (control instanceof Combo)
         {
            ((Combo) control).setText((String) comboValueMap.get(value));
         }
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      Group serverSettingsGroup = FormBuilder
            .createGroup(composite, Diagram_Messages.GROUP_ServerSettings, 2); 

      FormBuilder.createLabel(serverSettingsGroup, Diagram_Messages.LB_Protocol); 

      protocolCombo = FormBuilder.createCombo(serverSettingsGroup);
      protocolCombo.add(POP);
      protocolCombo.add(IMAP);
      protocolCombo.setText(POP);

      FormBuilder.createLabel(serverSettingsGroup, Diagram_Messages.LB_Server); 

      hostText = FormBuilder.createText(serverSettingsGroup);
      FormBuilder.createLabel(serverSettingsGroup, Diagram_Messages.LB_Name); 

      usrText = FormBuilder.createText(serverSettingsGroup);
      FormBuilder.createLabel(serverSettingsGroup, Diagram_Messages.LB_Password); 

      passwdText = new Text(serverSettingsGroup, SWT.BORDER | SWT.PASSWORD);
      GridData passwdTextGridData = new GridData(SWT.FILL);
      passwdTextGridData.grabExcessHorizontalSpace = true;
      passwdTextGridData.horizontalAlignment = SWT.FILL;
      passwdTextGridData.widthHint = FormBuilder.getDefaultTextSize(composite);
      passwdText.setLayoutData(passwdTextGridData);

      Group mailSettingsGroup = FormBuilder.createGroup(composite, Diagram_Messages.GROUP_MailSettings, 2); 

      FormBuilder.createLabel(mailSettingsGroup, Diagram_Messages.LB_CandidateMails); 

      flagsCombo = FormBuilder.createCombo(mailSettingsGroup);
      flagsCombo.add(EXISTING);
      flagsCombo.add(NOT_SEEN);
      flagsCombo.add(NEW_MAILS);
      flagsCombo.setText(EXISTING);
      flagsCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));

      FormBuilder.createLabel(mailSettingsGroup, Diagram_Messages.LB_SenderPredicates); 

      senderPredicateText = FormBuilder.createText(mailSettingsGroup);
      FormBuilder.createLabel(mailSettingsGroup, Diagram_Messages.LB_SubjectPredicate); 

      subjectPredicateText = FormBuilder.createText(mailSettingsGroup);
      FormBuilder.createLabel(mailSettingsGroup, Diagram_Messages.LB_BodyPredicate); 

      selectorPredicateText = FormBuilder.createText(mailSettingsGroup);
      FormBuilder.createLabel(mailSettingsGroup, Diagram_Messages.LB_MailboxAction); 

      mailboxActionCombo = FormBuilder.createCombo(mailSettingsGroup);
      mailboxActionCombo.add(LEAVE);
      mailboxActionCombo.add(SEEN);
      mailboxActionCombo.add(REMOVE);
      mailboxActionCombo.setText(LEAVE);

      initComboValueMap();

      addParameterMappingTablePage();

      return composite;
   }

   private void initComboValueMap()
   {
      comboValueMap = new HashMap();
      comboValueMap.put("pop3", POP); //$NON-NLS-1$
      comboValueMap.put("imap", IMAP); //$NON-NLS-1$
      comboValueMap.put("recent", NEW_MAILS); //$NON-NLS-1$
      comboValueMap.put("notSeen", NOT_SEEN); //$NON-NLS-1$
      comboValueMap.put("any", EXISTING); //$NON-NLS-1$
      comboValueMap.put("remove", REMOVE); //$NON-NLS-1$
      comboValueMap.put("read", SEEN); //$NON-NLS-1$
      comboValueMap.put("leave", LEAVE); //$NON-NLS-1$
      comboValueMap.put(POP, "pop3"); //$NON-NLS-1$
      comboValueMap.put(IMAP, "imap"); //$NON-NLS-1$
      comboValueMap.put(NEW_MAILS, "recent"); //$NON-NLS-1$
      comboValueMap.put(NOT_SEEN, "notSeen"); //$NON-NLS-1$
      comboValueMap.put(EXISTING, "any"); //$NON-NLS-1$
      comboValueMap.put(REMOVE, "remove"); //$NON-NLS-1$
      comboValueMap.put(SEEN, "read"); //$NON-NLS-1$
      comboValueMap.put(LEAVE, "leave"); //$NON-NLS-1$
   }

   private void addParameterMappingTablePage()
   {
      CarnotPreferenceNode node = new CarnotPreferenceNode(ConfigurationElement
            .createPageConfiguration(PARAMETER_MAPPING_TABLE_ID,
                  PARAMETER_MAPPING_TABLE_LABEL,
                  /* template.getAttribute("icon") */
                  null, ParameterMappingTablePage.class.getName()),
            new ModelElementAdaptable(IModelElement.class, getTrigger(), EditPart.class, getElement()
            ), CarnotPreferenceNode.INSERTION_ORDER);

      addNodeTo(null, node, null);
      refreshTree();
   }

   private TriggerType getTrigger()
   {
      Object element = getElement();
      if (element instanceof EditPart)
      {
         element = ((EditPart) element).getModel();
      }
      if (element instanceof IModelElementNodeSymbol)
      {
         element = ((IModelElementNodeSymbol) element).getModelElement();
      }
      return element instanceof TriggerType ? (TriggerType) element : null;
   }

   private AccessPointType createMailAccessPoint()
   {
      AccessPointType ap = AccessPointUtil.createIntrinsicAccessPoint("mail", "mail", //$NON-NLS-1$ //$NON-NLS-2$
            MAIL_CLASS_ATT, DirectionType.OUT_LITERAL, true, null,
            ModelUtils.getDataType(getTrigger(), CarnotConstants.SERIALIZABLE_DATA_ID));
      
      ap.setElementOid(ModelUtils.getElementOid(ap,
            ModelUtils.findContainingModel(getTrigger())));
      
      return ap;
   }
}
