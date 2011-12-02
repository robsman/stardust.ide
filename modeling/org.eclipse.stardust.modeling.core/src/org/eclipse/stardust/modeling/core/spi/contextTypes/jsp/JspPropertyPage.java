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
package org.eclipse.stardust.modeling.core.spi.contextTypes.jsp;

import java.net.URL;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IContextPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.DefaultModelElementPropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class JspPropertyPage extends DefaultModelElementPropertyPage
      implements IContextPropertyPage
{
   private static final String WEBEX_SCOPE = "carnot:webex:"; //$NON-NLS-1$
   private static final String HTML_PATH_ATT = WEBEX_SCOPE + "htmlPath"; //$NON-NLS-1$

   private Text urlText = null;
   private Button testButton = null;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ContextType context = (ContextType) element;
      java.util.List attributes = context.getAttribute();

      String url = null;

      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         if (HTML_PATH_ATT.equals(attribute.getName()))
         {
            url = attribute.getValue();
         }
      }

      urlText.setText(url == null ? "" : url.trim()); //$NON-NLS-1$
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol,
                                        IModelElement element)
   {
      ContextType context = (ContextType) element;
      AttributeUtil.setAttribute(context, HTML_PATH_ATT,
              urlText.getText().trim());
   }

   public Control createBody(final Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout compositeLayout = new GridLayout(3, false);
      composite.setLayout(compositeLayout);

      // composite layout data
      GridData compositeGridData = new GridData();
      compositeGridData.horizontalAlignment = GridData.FILL;
      compositeGridData.verticalAlignment = GridData.FILL;
      compositeGridData.grabExcessHorizontalSpace = true;
      composite.setLayoutData(compositeGridData);

      // classLabel label widget
      Label urlLabel = new Label(composite, SWT.NONE);
      urlLabel.setText(Diagram_Messages.LB_JSPURL);

      // class text widget
      urlText = FormBuilder.createText(composite);

      // browse button widget
      testButton = new Button(composite, SWT.NONE);
      testButton.setText(Diagram_Messages.B_Test); 

      // browse button grid data
      GridData testButtonGridData = new GridData(SWT.FILL);
      testButton.setLayoutData(testButtonGridData);
      testButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            String u = urlText.getText();
            try
            {
               URL url = new URL(u);
               url.openStream();
               MessageDialog.openInformation(parent.getShell(), Diagram_Messages.MSG_TestURL, 
                     Diagram_Messages.MSG_SuccessfullyConnectedTo + u);
            }
            catch (Exception x)
            {
               MessageDialog.openWarning(parent.getShell(), Diagram_Messages.MSG_TestURL, 
                     Diagram_Messages.MSG_ConnectionTo + u + Diagram_Messages.MSG_Failed); 
            }
         }
      });

      return composite;
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }   
}