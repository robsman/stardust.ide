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
package org.eclipse.stardust.modeling.integration.mail.application;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.swt.ComplexAttributeUtils;
import org.eclipse.stardust.modeling.common.ui.swt.ObjectTable;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.stardust.modeling.integration.mail.MailConstants;
import org.eclipse.stardust.modeling.integration.mail.Mail_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

/**
 * 
 * @author mgille
 */
public class MailPropertyPage extends AbstractModelElementPropertyPage
			implements IApplicationPropertyPage
{
	private TabItem htmlItem;
	private LabeledText mailServerText;
	private LabeledText urlPrefixText;
	private Button htmlButton;
	private Button createProcessHistoryLinkButton;
	private LabeledText defaultFromText;
	private LabeledText defaultToText;
	private LabeledText defaultSubjectText;
	private LabeledText defaultCC;
	private LabeledText defaultBCC;
	private LabeledCombo defaultPriority;
	private LabeledText plainTextTemplateText;
	private LabeledText htmlHeaderText;
	private LabeledText htmlTemplateText;
	private LabeledText htmlFooterText;
	private ObjectTable outputValues;
    private Button subjectUniqueIdentified; 

//	private Button mailResponseButton;

	/**
	 * 
	 */
	public void loadFieldsFromElement(IModelElementNodeSymbol symbol,
				IModelElement element)
	{
		ApplicationType app = (ApplicationType) element;

		this.mailServerText.getText().setText(
            nullSafe(app, MailConstants.DEFAULT_MAIL_SERVER));

      this.urlPrefixText.getText().setText(nullSafe(app, MailConstants.URL_PREFIX));

      this.defaultCC.getText().setText(nullSafe(app, MailConstants.DEFAULT_MAIL_CC));
      this.defaultBCC.getText().setText(nullSafe(app, MailConstants.DEFAULT_MAIL_BCC));

      this.defaultPriority.getCombo().setText(
            nullSafe(app, MailConstants.DEFAULT_MAIL_PRIORITY));

      this.defaultSubjectText.getText().setText(
            nullSafe(app, MailConstants.DEFAULT_MAIL_SUBJECT));
      if (AttributeUtil.getAttributeValue((IExtensibleElement) element,
            MailConstants.SUBJECT_INCLUDE_UNIQUE_IDENTIFIED) == null)
      {
         subjectUniqueIdentified.setSelection(true);
      }
      else
      {
         this.subjectUniqueIdentified.setSelection(AttributeUtil.getBooleanValue(app,
               MailConstants.SUBJECT_INCLUDE_UNIQUE_IDENTIFIED));
      }

      this.plainTextTemplateText.getText().setText(
            nullSafe(app, MailConstants.PLAIN_TEXT_TEMPLATE));

      this.htmlHeaderText.getText().setText(nullSafe(app, MailConstants.HTML_HEADER));
      this.htmlTemplateText.getText().setText(nullSafe(app, MailConstants.HTML_TEMPLATE));
      this.htmlFooterText.getText().setText(nullSafe(app, MailConstants.HTML_FOOTER));

      this.htmlButton.setSelection(AttributeUtil.getBooleanValue(app,
            MailConstants.USE_HTML));
		
		if (htmlButton.getSelection())
		{
			htmlHeaderText.getText().setEnabled(true);
			htmlTemplateText.getText().setEnabled(true);
			htmlFooterText.getText().setEnabled(true);
		}
		else
		{
			htmlHeaderText.getText().setEnabled(false);
			htmlTemplateText.getText().setEnabled(false);
			htmlFooterText.getText().setEnabled(false);
		}
					
		this.createProcessHistoryLinkButton.setSelection(AttributeUtil.getBooleanValue(
            app, MailConstants.CREATE_PROCESS_HISTORY_LINK));
        
      WidgetBindingManager wBndMgr = getWidgetBindingManager();
      wBndMgr.bind(defaultToText, app, MailConstants.DEFAULT_MAIL_TO);
      wBndMgr.bind(defaultFromText, app, MailConstants.DEFAULT_MAIL_FROM);
      wBndMgr.bind(mailServerText, app, MailConstants.DEFAULT_MAIL_SERVER);
      
      
        
//		this.mailResponseButton.setSelection(AttributeUtil.getBooleanValue(app,
//            MailConstants.MAIL_RESPONSE));

		ComplexAttributeUtils.setValuesInObjectTable((IExtensibleElement) element,
            MailConstants.OUTPUT_VALUES, outputValues);
	}

	/**
	 * 
	 */
	public void loadElementFromFields(IModelElementNodeSymbol symbol,
				IModelElement element)
	{
		ApplicationType app = (ApplicationType) element;

		AttributeUtil.setAttribute(app, MailConstants.DEFAULT_MAIL_SERVER,
            mailServerText.getText().getText());
      AttributeUtil.setAttribute(app, MailConstants.URL_PREFIX, urlPrefixText.getText()
            .getText());
      AttributeUtil.setAttribute(app, MailConstants.DEFAULT_MAIL_SUBJECT,
            defaultSubjectText.getText().getText());
      AttributeUtil.setBooleanAttribute(app,
            MailConstants.SUBJECT_INCLUDE_UNIQUE_IDENTIFIED, subjectUniqueIdentified
                  .getSelection());
      AttributeUtil.setAttribute(app, MailConstants.DEFAULT_MAIL_CC, defaultCC.getText()
            .getText());
      AttributeUtil.setAttribute(app, MailConstants.DEFAULT_MAIL_BCC,
            defaultBCC.getText().getText());
      AttributeUtil.setAttribute(app, MailConstants.DEFAULT_MAIL_PRIORITY,
            defaultPriority.getCombo().getText());
		
		
		
		if (htmlButton.getSelection())
		{
			AttributeUtil.setBooleanAttribute(app, MailConstants.USE_HTML, true);						
			AttributeUtil.setAttribute(app, MailConstants.HTML_HEADER, htmlHeaderText
						.getText().getText());			
			AttributeUtil.setAttribute(app, MailConstants.HTML_TEMPLATE, htmlTemplateText
						.getText().getText());			
			AttributeUtil.setAttribute(app, MailConstants.HTML_FOOTER, htmlFooterText
						.getText().getText());			
		}
		else
		{
			AttributeUtil.setBooleanAttribute(app, MailConstants.USE_HTML, false);						
		}
		
		AttributeUtil.setAttribute(app, MailConstants.PLAIN_TEXT_TEMPLATE, plainTextTemplateText
					.getText().getText());
		AttributeUtil.setBooleanAttribute(app, MailConstants.CREATE_PROCESS_HISTORY_LINK, createProcessHistoryLinkButton.getSelection());
//		AttributeUtil.setBooleanAttribute(app, MailConstants.MAIL_RESPONSE, mailResponseButton.getSelection());
		ComplexAttributeUtils.getValuesFromObjectTable((IExtensibleElement) element,
					MailConstants.OUTPUT_VALUES, outputValues);
	}

	/**
	 * 
	 */
	public Control createBody(final Composite parent)
	{		
		Font courier9pt =new Font(parent.getDisplay(), "Courier", 9, SWT.NORMAL); //$NON-NLS-1$
		
		Composite mainComposite = new Composite(parent, SWT.NONE);
		
		mainComposite.setLayout(new FormLayout());
		
		Composite composite = FormBuilder.createComposite(mainComposite, 1);		
		FormData data = new FormData();

		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(0, 5);

		composite.setLayoutData(data);

		this.mailServerText = FormBuilder.createLabeledText(composite,
					Mail_Messages.MAIL_SERVER_LABEL);
		this.urlPrefixText = FormBuilder.createLabeledText(composite,
					Mail_Messages.URL_PREFIX_LABEL);
		this.htmlButton = FormBuilder.createCheckBox(composite, Mail_Messages.BOX_HTML);
		this.createProcessHistoryLinkButton = FormBuilder.createCheckBox(composite, Mail_Messages.CREATE_PROCESS_HISTORY_LINK_LABEL);
		
		TabFolder tabFolder = new TabFolder(mainComposite, SWT.NONE);

		data = new FormData();

		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(composite, 5, SWT.BOTTOM);
		data.bottom = new FormAttachment(100, 5);

		tabFolder.setLayoutData(data);

		// Default settings tab
		
		TabItem item = new TabItem(tabFolder,SWT.NONE);

		item.setText(Mail_Messages.TXT_DEFAULT_SETTINGS);

		composite = FormBuilder.createComposite(tabFolder, 1);

		item.setControl(composite);
		
		String[] priorities = {Mail_Messages.PRIORITY_HIGHEST_VALUE,
							   Mail_Messages.PRIORITY_HIGH_VALUE,
							   Mail_Messages.PRIORITY_NORMAL_VALUE,
							   Mail_Messages.PRIORITY_LOW_VALUE,
							   Mail_Messages.PRIORITY_LOWEST_VALUE};
		
		this.defaultFromText = FormBuilder.createLabeledTextLeftAlignedStatus(composite,
					Mail_Messages.DEFAULT_FROM_LABEL);
		this.defaultToText = FormBuilder.createLabeledTextLeftAlignedStatus(composite,
					Mail_Messages.DEFAULT_TO_LABEL);
		this.defaultCC = FormBuilder.createLabeledText(composite, 
					Mail_Messages.DEFAULT_CC_LABEL);
		this.defaultBCC = FormBuilder.createLabeledText(composite,
					Mail_Messages.DEFAULT_BCC_LABEL);
		this.defaultPriority = FormBuilder.createLabeledCombo(composite,
					Mail_Messages.DEFAULT_PRIORITY_LABEL);		
		this.defaultPriority.getCombo().setItems(priorities);
		this.defaultPriority.getCombo().setText(Mail_Messages.COMBO_BOX_NORMAL);
		this.defaultSubjectText = FormBuilder.createLabeledText(composite,
					Mail_Messages.DEFAULT_SUBJECT_LABEL);
        this.subjectUniqueIdentified = FormBuilder.createCheckBox(composite,
            Mail_Messages.SUBJECT_INCLUDE_UNIQUE_IDENTIFIED);

		FormBuilder.createButton(composite, Mail_Messages.BUT_TEST, new SelectionListener()
		{
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}

			public void widgetSelected(SelectionEvent e)
			{
				testDefaultMailSettings();
			}
		});

		// HTML Template
		
		item = new TabItem(tabFolder,SWT.NONE);
		this.htmlItem = item;

		htmlItem.setText(Mail_Messages.TXT_HTML_TEMPLATE);

		htmlButton.addSelectionListener(new SelectionListener()
		{
			public void widgetDefaultSelected(SelectionEvent e)
			{
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e)
			{
				if (htmlButton.getSelection())
				{
					htmlHeaderText.getText().setEnabled(true);
					htmlTemplateText.getText().setEnabled(true);
					htmlFooterText.getText().setEnabled(true);
				}
				else
				{
					htmlHeaderText.getText().setEnabled(false);
					htmlTemplateText.getText().setEnabled(false);
					htmlFooterText.getText().setEnabled(false);
				}
			}			
		});
		
		composite = FormBuilder.createComposite(tabFolder, 1);

		item.setControl(composite);

		this.htmlHeaderText = FormBuilder.createLabeledTextArea(composite,
				Mail_Messages.HTML_HEADER_LABEL);
		
		htmlHeaderText.getText().setFont(courier9pt);

		this.htmlTemplateText = FormBuilder.createLabeledTextArea(composite,
					Mail_Messages.HTML_TEMPLATE_LABEL);

		htmlTemplateText.getText().setFont(courier9pt);

		this.htmlFooterText = FormBuilder.createLabeledTextArea(composite,
					Mail_Messages.HTML_FOOTER_LABEL);

		htmlFooterText.getText().setFont(courier9pt);

		// Plain Text Template
		
		item = new TabItem(tabFolder,SWT.NONE);

		item.setText(Mail_Messages.TExT_PLAIN_TEXT_TEMPLATE);

		composite = FormBuilder.createComposite(tabFolder, 1);

		item.setControl(composite);
		
		FormBuilder.createButton(composite, Mail_Messages.BUT_TEST, new SelectionListener()
		{
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}

			public void widgetSelected(SelectionEvent e)
			{
				testHTMLText();
			}
		});
		
		this.plainTextTemplateText = FormBuilder.createLabeledTextArea(composite,
					Mail_Messages.PLAIN_TEXT_TEMPLATE_LABEL);
		
		plainTextTemplateText.getText().setFont(courier9pt);
		
		// Output
		
		item = new TabItem(tabFolder,SWT.NONE);

		item.setText(Mail_Messages.TXT_OUTPUT_VALUES);

		composite = FormBuilder.createComposite(tabFolder, 1);

		item.setControl(composite);

//		this.mailResponseButton = FormBuilder.createCheckBox(composite, MailMessages.MAIL_RESPONSE_LABEL);

		this.outputValues = new ObjectTable(composite, 
						ObjectTable.ADD_ROW | ObjectTable.DELETE_BUTTON | ObjectTable.EDITABLE | ObjectTable.NUMBER_COLUMN | ObjectTable.SHOW_HEADERS,
					SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION,
					OutputValue.class, new String[]
					{ "name" , "value"}, new String[] //$NON-NLS-1$ //$NON-NLS-2$
					{ "Name" , "Value"}, new int[] //$NON-NLS-1$ //$NON-NLS-2$
					{ SWT.LEFT, SWT.LEFT }, new int[]
					{ 200, 200 }, new Image[]
					{ null, null });

		outputValues.setObjects(CollectionUtils.newList());

		return mainComposite;
	}
	
	private void testHTMLText()
	{
	}

	private void testDefaultMailSettings()
	{
		try
		{
			Properties props = new Properties();

			props.put("mail.smtp.host", mailServerText.getText().getText()); //$NON-NLS-1$
			props.put("mail.debug", "true"); //$NON-NLS-1$ //$NON-NLS-2$

			Session session = Session.getInstance(props, null);
			MimeMessage msg = new MimeMessage(session);

			InternetAddress fromAddress = new InternetAddress(defaultFromText.getText().getText());
			msg.setFrom(fromAddress);
			
			msg.addRecipients(Message.RecipientType.TO,
               parseRecipientList(defaultToText.getText().getText()));
			if ( !StringUtils.isEmpty(defaultCC.getText().getText()))
	      {
				msg.addRecipients(Message.RecipientType.CC,
                  parseRecipientList(defaultCC.getText().getText()));
			}
         if ( !StringUtils.isEmpty(defaultBCC.getText().getText()))
         {
				msg.addRecipients(Message.RecipientType.BCC,
                  parseRecipientList(defaultBCC.getText().getText()));
			}
         if ( !StringUtils.isEmpty(defaultPriority.getCombo().getText()))
         {
				//msg.addHeader("X-Priority", defaultPriority.getCombo().getText());
				int prioValue = defaultPriority.getCombo().getSelectionIndex() + 1;
				String prioString = String.valueOf(prioValue);
				msg.addHeader("X-Priority", prioString); //$NON-NLS-1$
			}
			msg.setSubject(defaultSubjectText.getText().getText());
			msg.setText(Mail_Messages.MSG_TXT_TEST_MESSAGE_FOR_INFINITY_MAIL_APPLICATION_TYPE);
			
			// Send the message

			Transport.send(msg);
		}
		catch (AddressException e)
		{
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}		
	}
	
   private InternetAddress[] parseRecipientList(String recipientsSpec)
         throws AddressException
   {
      List<InternetAddress> recipients = CollectionUtils.newList();

      if (!StringUtils.isEmpty(recipientsSpec))
      {
         for (Iterator<String> i = StringUtils.split(recipientsSpec, ';'); i.hasNext();)
         {
            recipients.add(new InternetAddress((String) i.next()));
         }
      }

      return recipients.toArray(new InternetAddress[recipients.size()]);
   }
	
	private static String nullSafe(IExtensibleElement element, String key)
	{
	     String value = AttributeUtil.getAttributeValue(element, key);      

	     return StringUtils.isEmpty(value) ? "" : value; //$NON-NLS-1$
	}
}
