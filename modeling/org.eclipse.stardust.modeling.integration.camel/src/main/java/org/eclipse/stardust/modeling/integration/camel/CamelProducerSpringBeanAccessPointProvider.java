package org.eclipse.stardust.modeling.integration.camel;

import java.util.ArrayList;
import java.util.List;
//import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.extensions.camel.CamelConstants;
import org.eclipse.stardust.engine.extensions.camel.GenericProducer;
//import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
//import org.eclipse.stardust.model.xpdl.carnot.DataTypeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
//import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
//import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
//import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.PlainJavaAccessPointProvider;

public class CamelProducerSpringBeanAccessPointProvider extends PlainJavaAccessPointProvider
{
   @SuppressWarnings({"unchecked", "rawtypes"})
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      // Collections.EMPTY_LIST;
      if (element instanceof IExtensibleElement)
      {
         String producerMethodName = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               CamelConstants.PRODUCER_METHOD_NAME_ATT);
         if (producerMethodName != null)
         {
            // old behavior
            List result = new ArrayList();
            result = getIntrinsicAccessPoints((IExtensibleElement) element, GenericProducer.class.getName(),
                  producerMethodName, false, null, DirectionType.IN_LITERAL, null, true);
            result.addAll(getIntrinsicAccessPoints((IExtensibleElement) element, GenericProducer.class.getName(),
                  producerMethodName, false, null, DirectionType.OUT_LITERAL, null, false));
            return result;
         }
//         else
//         {
//            String invocationType = AttributeUtil.getAttributeValue((IExtensibleElement) element,
//                  CamelConstants.INVOCATION_TYPE_EXT_ATT);
//            String invocationPattern = AttributeUtil.getAttributeValue((IExtensibleElement) element,
//                  CamelConstants.INVOCATION_PATTERN_EXT_ATT);
//
//            if (invocationPattern.equalsIgnoreCase("send"))
//            {
//               List result = new ArrayList();
//               result = getIntrinsicAccessPoints((IExtensibleElement) element, GenericProducer.class.getName(),
//                     GenericProducer.SEND_METHOD_WITH_HEADER, false, null, DirectionType.IN_LITERAL, null, true);
//               return result;
//            }
//            // producerMethodName = GenericProducer.SEND_METHOD_WITH_HEADER;
//            else if (invocationPattern.equalsIgnoreCase("sendReceive")
//                  && invocationType.equalsIgnoreCase("synchronous"))
//            {
//               List result = new ArrayList();
//               AccessPointType bodyAccessPoint = AccessPointUtil.createAccessPoint("body", "Body",
//                     DirectionType.IN_LITERAL, ModelUtils.getDataType(element, CarnotConstants.PRIMITIVE_DATA_ID));
//               AccessPointType headersAccessPoint = AccessPointUtil.createAccessPoint("headers", "Headers",
//                     DirectionType.IN_LITERAL, ModelUtils.getDataType(element, CarnotConstants.PRIMITIVE_DATA_ID));
//               AccessPointType outAccessPoint = AccessPointUtil.createAccessPoint("returnValue", "returnValue",
//                     DirectionType.OUT_LITERAL, ModelUtils.getDataType(element, CarnotConstants.PRIMITIVE_DATA_ID));
//               result.add(bodyAccessPoint);
//               result.add(headersAccessPoint);
//               result.add(outAccessPoint);
//               return result;
//            }
//
//         }
         List result = new ArrayList();
         return result;
      }
      return null;

   }

//   public static AccessPointType createPrimitiveAccessPointType(String id, String name, Class< ? > clazz,
//         DirectionType direction, IModelElement element)
//   {
//      DataTypeType serializable = ModelUtils.getDataType(element, CarnotConstants.PRIMITIVE_DATA_ID);
//      AccessPointType ap = AccessPointUtil.createAccessPoint(id, name, direction, serializable);
//      AttributeUtil.setAttribute(ap, PredefinedConstants.TYPE_ATT, clazz.getName());
//      return ap;
//   }
}