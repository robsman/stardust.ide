package org.eclipse.stardust.modeling.integration.camel;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;

/**
 * This is a duplicate of org.eclipse.stardust.engine.extensions.camel.CamelConstants we
 * duplicated the constant used to avoid to reference ipp-camel in this project.
 * 
 * @author
 * 
 */
public class CamelConstants
{
   public final static String CAMEL_SCOPE = PredefinedConstants.ENGINE_SCOPE + "camel:";
   public final static String CONSUMER_ROUTE_ATT = CAMEL_SCOPE + ":consumerRoute"; //$NON-NLS-1$
   public static final String CORRELATION_PATTERN_EXT_ATT = CAMEL_SCOPE + ":correlationPattern"; //$NON-NLS-1$
   public static final String INVOCATION_PATTERN_EXT_ATT = CAMEL_SCOPE + ":invocationPattern";
   public final static String PRODUCER_ROUTE_ATT = CAMEL_SCOPE + ":routeEntries"; //$NON-NLS-1$
   public static final String SEND = "send";
   public static final String SEND_RECEIVE = "sendReceive";
   public static final String RECEIVE = "receive";

   public static final class CorrelationKey
   {
      public static final String PROCESS = "process";
      public static final String ACTIVITY = "activity";
      public static final String DATA = "data";
   }
}
