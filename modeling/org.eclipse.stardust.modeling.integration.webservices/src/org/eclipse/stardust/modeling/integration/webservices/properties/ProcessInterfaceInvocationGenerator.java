/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.integration.webservices.properties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.error.InternalException;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.engine.core.model.xpdl.XpdlUtils;
import org.eclipse.stardust.engine.core.runtime.utils.XmlUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.IProcessInterfaceInvocationGenerator;
import org.eclipse.stardust.modeling.core.properties.ProcessInterfacePropertyPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;

import org.eclipse.stardust.engine.ws.processinterface.WSDLGenerator;

public class ProcessInterfaceInvocationGenerator
      implements IProcessInterfaceInvocationGenerator
{
   private Text textArea;

   private Button generateButton;

   public Composite createExposeComposite(Composite wsdlComposite, IProcessDefinitionTypeProvider processProvider)
   {
      textArea = FormBuilder.createTextArea(wsdlComposite, 3);
      textArea.setVisible(false);
      generateButton = new Button(wsdlComposite, SWT.None);
      generateButton.setText(Diagram_Messages.BUT_TXT_GENERATE_WSDL);
      generateButton.setVisible(false); 
      generateButton.addSelectionListener(new WsdlButtonSelectionListener(processProvider, textArea));
      return null;
   }
   
   public void setComponentVisibility(String externalInvocationType)
   {
      if(externalInvocationType == null || 
            externalInvocationType.equalsIgnoreCase(ProcessInterfacePropertyPage.REST_INVOCATION_TYPE))
      {
         textArea.setVisible(false);
         generateButton.setVisible(false);
      }
      else if (externalInvocationType.equalsIgnoreCase(ProcessInterfacePropertyPage.BOTH_INVOCATION_TYPES) ||
            externalInvocationType.equalsIgnoreCase(ProcessInterfacePropertyPage.SOAP_INVOCATION_TYPE))
      {
         textArea.setVisible(true);
         generateButton.setVisible(true);
      }
   }

   public void handleValidationStatusFromParent(IQuickValidationStatus status)
   {
      if(status != null && generateButton != null)
      {
         generateButton.setEnabled(!(status == IQuickValidationStatus.ERRORS));
      }
      
   }

   private final static class WsdlButtonSelectionListener implements SelectionListener
   {
      private final IProcessDefinitionTypeProvider processProvider;
      private final Text textArea;
      
      WsdlButtonSelectionListener(IProcessDefinitionTypeProvider processProvider, Text targetTextArea)
      {
         this.processProvider = processProvider;
         this.textArea = targetTextArea;
      }
      
      public void widgetDefaultSelected(SelectionEvent e)
      {}

      public void widgetSelected(SelectionEvent e)
      {            
         String result = ""; //$NON-NLS-1$
         ModelType model = (ModelType) processProvider.getProcessDefinitionType().eContainer();
         Map<String, Object> options = CollectionUtils.newMap();
         options.put(XMLResource.OPTION_ENCODING, XMLConstants.ENCODING_ISO_8859_1);
         Document domCwm = ((XMLResource) model.eResource()).save(null, options, null);
         if (null != domCwm)
         {
            Source xsltSource = null;
            try
            {
               final URL xsltURL = XpdlUtils.getCarnot2XpdlStylesheet();
               if (xsltURL == null)
               {
                  throw new InternalException(Diagram_Messages.EXC_UNABLE_TO_FIND_XPDL_EXPORT_STYLESHEET);
               }
               xsltSource = new StreamSource(xsltURL.openStream());
            }
            catch (IOException ex)
            {
               ex.printStackTrace();
            }               
            try
            {                
               ByteArrayOutputStream bos = new ByteArrayOutputStream();
               StreamResult target = new StreamResult(bos);
               TransformerFactory transformerFactory = XmlUtils.newTransformerFactory();
               Transformer xpdlTrans = transformerFactory.newTransformer(xsltSource);
               XmlUtils.transform(new DOMSource(domCwm), xpdlTrans, target, null, 3,
                     XpdlUtils.UTF8_ENCODING);
               WSDLGenerator wsdlgen = new WSDLGenerator(bos.toByteArray());
               result = new String(wsdlgen.generateFormatted());
            }
            catch (Throwable em)
            {
               em.printStackTrace();
            }
         }            
         textArea.setText(result);
      }
   }
}
