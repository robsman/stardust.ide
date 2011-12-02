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
package org.eclipse.stardust.modeling.integration.webservices;

import org.eclipse.osgi.util.NLS;

public class Webservices_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.integration.webservices.webservices-messages"; //$NON-NLS-1$

   private Webservices_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Webservices_Messages.class);
   }
   
   public static String UddiRegistryBrowserWizard_WindowTitle;
   public static String UddiRegistryBrowserWizard_BindingPageName;
   public static String UddiRegistryBrowserWizard_BindingPageDescription;
   public static String UddiRegistryBrowserWizard_BindingPageTypeColumnLabel;
   public static String UddiRegistryBrowserWizard_BindingPageAccessPointColumnLabel;
   public static String UddiRegistryBrowserWizard_ServicePageName;
   public static String UddiRegistryBrowserWizard_ServicePageDescription;
   public static String UddiRegistryBrowserWizard_ServicePageNameColumnLabel;
   public static String UddiRegistryBrowserWizard_ServicePageDescriptionColumnLabel;
   public static String UddiRegistryBrowserWizard_BusinessPageName;
   public static String UddiRegistryBrowserWizard_BusinessPageDescription;
   public static String UddiRegistryBrowserWizard_BusinessPageHint;
   public static String UddiRegistryBrowserWizard_BusinessPageDefaultFilter;
   public static String UddiRegistryBrowserWizard_SearchButtonLabel;
   public static String UddiRegistryBrowserWizard_BusinessPageNameColumnLabel;
   public static String UddiRegistryBrowserWizard_BusinessPageDescriptionColumnLabel;
   public static String WebServiceResource_ErrorMessage;
   public static String WebServicePropertyPage_WsdlUrlLabel; 
   public static String WebServicePropertyPage_PortLabel;
   public static String WebServicePropertyPage_StyleLabel;
   public static String WebServicePropertyPage_UseLabel;
   public static String WebServicePropertyPage_ProtocolLabel;
   public static String WebServicePropertyPage_UddiRegistryGroupLabel;
   public static String WebServicePropertyPage_InquiryUrlLabel;
   public static String WebServicePropertyPage_BrowseButtonLabel;
   public static String WebServicePropertyPage_ImplementationLabel;
   public static String WebServicePropertyPage_UddiBrowseErrorMessage;
   public static String WebServicePropertyPage_GenericResourceLabel;
   public static String WebServicePropertyPage_InfinitySpecificLabel;
   public static String WebServicePropertyPage_HttpBasicAuthorizationLabel;
   public static String WebServicePropertyPage_UsernamePasswordLabel;
   public static String WebServicePropertyPage_UsernamePasswordDigestLabel;
   public static String WebServicePropertyPage_XWSSConfigurationLabel;
   public static String WebServicePropertyPage_AuthenticationAccessPointName;
   public static String WebServicePropertyPage_IncludeAddressingCheckBoxLabel;
   public static String WebServicePropertyPage_RequiredCheckBoxLabel;
   public static String WebServicePropertyPage_BindingKeyLabel; 
   public static String WebServicePropertyPage_WsSecurityLabel;
   public static String WebServicePropertyPage_LoadButtonLabel;
   public static String WebServicePropertyPage_OperationLabel;
   public static String WebServicePropertyPage_MechanismLabel;
   public static String WebServicePropertyPage_AccessPointLabel;
   public static String WebServicePropertyPage_LoadErrorMessage;
   public static String WebServicePropertyPage_AddressingLabel;
   public static String WebServicePropertyPage_AuthenticationLabel;
   public static String WebServicePropertyPage_ErrorDialogTitle;
   public static String WebServicePropertyPage_BindingLabel;
   public static String WebServicePropertyPage_EndpointAddressAccessPointName;
   public static String WebServicePropertyPage_WsAddressingEndpointReferenceAccessPointName;
   public static String WebServicePropertyPage_ServiceLabel;
   public static String WebServicePropertyPage_VariantLabel;
   public static String WebServicePropertyPage_UnknownPartValidationError;
   public static String WebServicePropertyPage_TypeNotFoundForInputPart;
   public static String WebServicePropertyPage_TypeNotFoundForOutputPart;
}
