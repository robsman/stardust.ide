package org.eclipse.stardust.modeling.integration.camel;

import org.eclipse.osgi.util.NLS;

public class Camel_Messages extends NLS
{
   private static final String BUNDLE_NAME = "org.eclipse.stardust.modeling.integration.camel.camel-messages"; //$NON-NLS-1$

   public static String label_additionalSpringBeanDef;

   public static String label_CamelContextId;

   

   public static String label_Route;

   
   public static String label_Endpoint_Pattern;
   
   public static String label_Invocation_Pattern_Send;

   public static String label_Invocation_Pattern_SendReceive;
   
   public static String label_Invocation_Pattern_Receive;
   
   public static String label_Invocation_Type;
   
   public static String label_Invocation_Type_Sync;
   
   public static String label_Invocation_Type_Async;

   public static String label_Endpoint_Type;

   public static String label_EndpointSettings;

   public static String label_AdditionalBean;
   
   public static String label_process;
   
   public static String label_data;
   
   public static String label_activity;
   
   public static String label_correlation_Pattern;
   

   // public static String label_
   static
   {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Camel_Messages.class);
   }

   private Camel_Messages()
   {}
}
