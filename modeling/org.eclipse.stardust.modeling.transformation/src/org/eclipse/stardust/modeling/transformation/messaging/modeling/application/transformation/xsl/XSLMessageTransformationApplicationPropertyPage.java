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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.xsl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IApplicationPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.Constants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;



/**
 * The implementation deals with <code>Rule</code> and <code>RuleModel</code>
 * objects and persists the resource URIs of those.
 * 
 * @author Marc Gille
 */
public class XSLMessageTransformationApplicationPropertyPage extends
		AbstractModelElementPropertyPage implements IApplicationPropertyPage
{
	ApplicationType application;
	Text xslCodeText;

	public XSLMessageTransformationApplicationPropertyPage()
	{
		super();
	}

	/**
	 * 
	 */
	public void loadFieldsFromElement(IModelElementNodeSymbol symbol,
			IModelElement element)
	{
		String xslCode = AttributeUtil.getAttributeValue(
				(IExtensibleElement) element, Constants.XSL_CODE);

		if (xslCode != null)
		{
			xslCodeText.setText(xslCode);
		}
	}

	/**
	 * 
	 */
	public void loadElementFromFields(IModelElementNodeSymbol symbol,
			IModelElement element)
	{
		AttributeUtil.setAttribute((IExtensibleElement) element,
				Constants.XSL_CODE, xslCodeText.getText());
	}

	/**
	 * 
	 */
	public Control createBody(final Composite parent)
	{
		Composite composite = FormBuilder.createComposite(parent, 1);

		xslCodeText = FormBuilder.createLabeledTextArea(composite, Modeling_Messages.LBL_XSL_CODE)
				.getText();
		FormBuilder.createButton(composite, Modeling_Messages.BUT_BW,
				new SelectionListener()
				{
					public void widgetSelected(SelectionEvent e)
					{
						FileDialog fd = new FileDialog(parent.getShell(),
								SWT.SAVE);

						fd.setText(Modeling_Messages.TXT_SAVE);
						fd.setFilterPath("C:/"); //$NON-NLS-1$

						String[] filterExt = {
							"*.xsl" //$NON-NLS-1$
						};
						fd.setFilterExtensions(filterExt);

						String filePath = fd.open();
						if (filePath != null) {
	                        try
	                        {                                                       
	                            File file = new File(filePath);
	                            InputStream is = new FileInputStream(file);
	                            long length = file.length();
	                            byte[] bytes = new byte[(int) length];

	                            // Read in the bytes
	                            
	                            int offset = 0;
	                            int numRead = 0;

	                            while (offset < bytes.length
	                                    && (numRead = is.read(bytes, offset,
	                                            bytes.length - offset)) >= 0)
	                            {
	                                offset += numRead;
	                            }

	                            // Ensure all the bytes have been read in

								if (offset < bytes.length) {
									throw new IOException(
											MessageFormat
													.format(Modeling_Messages.EXC_COULD_NOT_COMPLETELY_READ_FILE,
															new Object[] { file
																	.getName() }));
								}

	                            xslCodeText.setText(new String(bytes));

	                            is.close();
	                        }
	                        catch (IOException i)
	                        {
	                        }						   
						}

					}

					public void widgetDefaultSelected(SelectionEvent e)
					{
					}
				});

		return composite;
	}
}