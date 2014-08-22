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

public class CamelProducerSpringBeanAccessPointProvider
      extends PlainJavaAccessPointProvider
{
   @SuppressWarnings({"unchecked", "rawtypes"})
   public List createIntrinsicAccessPoint(IModelElement element)
   {
      if (element instanceof IExtensibleElement)
      {
         String producerMethodName = AttributeUtil.getAttributeValue(
               (IExtensibleElement) element, CamelConstants.PRODUCER_METHOD_NAME_ATT);
         if (producerMethodName != null)
         {
            // old behavior
            List result = new ArrayList();
            result = getIntrinsicAccessPoints((IExtensibleElement) element,
                  GenericProducer.class.getName(), producerMethodName, false, null,
                  DirectionType.IN_LITERAL, null, true);
            result.addAll(getIntrinsicAccessPoints((IExtensibleElement) element,
                  GenericProducer.class.getName(), producerMethodName, false, null,
                  DirectionType.OUT_LITERAL, null, false));
            return result;
         }
         List result = new ArrayList();
         return result;
      }
      return null;
   }
}