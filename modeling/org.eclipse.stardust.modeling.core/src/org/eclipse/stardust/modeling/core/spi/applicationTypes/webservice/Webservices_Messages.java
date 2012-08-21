/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA 
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import org.eclipse.osgi.util.NLS;

public class Webservices_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice.webservices-messages"; //$NON-NLS-1$

   private Webservices_Messages()
   {}

   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Webservices_Messages.class);
   }
   
   public static String WebServiceResource_ErrorMessage;
   public static String WebServicePropertyPage_WsdlUrlLabel; 
   public static String WebServicePropertyPage_PortLabel;
   public static String WebServicePropertyPage_StyleLabel;
   public static String WebServicePropertyPage_UseLabel;
   public static String WebServicePropertyPage_ProtocolLabel;
   public static String WebServicePropertyPage_ImplementationLabel;
   public static String WebServicePropertyPage_GenericResourceLabel;
   public static String WebServicePropertyPage_InfinitySpecificLabel;
   public static String WebServicePropertyPage_HttpBasicAuthorizationLabel;
   public static String WebServicePropertyPage_UsernamePasswordLabel;
   public static String WebServicePropertyPage_UsernamePasswordDigestLabel;
   public static String WebServicePropertyPage_AuthenticationAccessPointName;
   public static String WebServicePropertyPage_IncludeAddressingCheckBoxLabel;
   public static String WebServicePropertyPage_RequiredCheckBoxLabel;
   public static String WebServicePropertyPage_WsSecurityLabel;
   public static String WebServicePropertyPage_LoadButtonLabel;
   public static String WebServicePropertyPage_OperationLabel;
   public static String WebServicePropertyPage_MechanismLabel;
   public static String WebServicePropertyPage_EndpointLabel;
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
   public static String WebServicePropertyPage_WSDL;   
   public static String WebServicePropertyPage_Generate_Classes;
   public static String WebServicePropertyPage_Retrieving_WSDL;
}
