package org.eclipse.stardust.modeling.integration.camel;

import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.DEFAULT_CAMEL_CONTEXT_ID;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.extensions.camel.CamelConstants;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.modeling.core.spi.applicationTypes.plainJava.JavaApplicationInitializer;

public class CamelProducerSpringBeanApplicationInitializer extends JavaApplicationInitializer
{
   public CamelProducerSpringBeanApplicationInitializer()
   {
      super(PredefinedConstants.REMOTE_INTERFACE_ATT, PredefinedConstants.CLASS_NAME_ATT);
   }

   @SuppressWarnings("rawtypes")
   @Override
   public List initialize(ApplicationType data, List attributes)
   {
      List result = null;
      for (int i = 0; i < attributes.size(); i++)
      {
         AttributeType attribute = (AttributeType) attributes.get(i);
         if (sourceName == null)
         {
            sourceName = ""; //$NON-NLS-1$
         }
         if (targetName == null)
         {
            targetName = ""; //$NON-NLS-1$
         }
         if (sourceName.equals(attribute.getName()) || targetName.equals(attribute.getName()))
         {
            String className = attribute.getValue();
            if (className != null && className.length() > 0)
               result = addAttribute(result, targetName, className);
         }
         if (CamelConstants.CAMEL_CONTEXT_ID_ATT.equals(attribute.getName()))
         {
            String camelContextId = attribute.getValue();
            if (camelContextId != null && camelContextId.length() > 0)
               result = addAttribute(result, CamelConstants.CAMEL_CONTEXT_ID_ATT, camelContextId);
         }

      }
      result = addAttribute(result, CamelConstants.CAMEL_CONTEXT_ID_ATT, DEFAULT_CAMEL_CONTEXT_ID);

      return result;
   }

   @SuppressWarnings({"rawtypes", "unchecked"})
   private List addAttribute(List result, String name, String value)
   {
      if (result == null)
         result = new ArrayList();
      AttributeType attribute = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
      attribute.setName(name);
      attribute.setValue(value);
      result.add(attribute);
      return result;
   }

   private String sourceName;
   private String targetName;
}
