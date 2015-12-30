package org.eclipse.stardust.modeling.integration.camel.validator;

import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.INVOCATION_PATTERN_EXT_ATT;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.extensions.camel.CamelConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationTypeImpl;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

public class CamelProducerModelValidator implements IModelElementValidator
{
   private static final transient Logger logger = LogManager.getLogger(CamelProducerModelValidator.class);

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      String invocationPattern = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            INVOCATION_PATTERN_EXT_ATT);

         if (!((ApplicationTypeImpl) element).getAccessPoint().isEmpty())
         {
            for (int i = 0; i < ((ApplicationTypeImpl) element).getAccessPoint().size(); i++)
            {
               AccessPointType accessPoint = ((ApplicationTypeImpl) element).getAccessPoint().get(i);
               if ((accessPoint.getDirection().getLiteral().equalsIgnoreCase(Direction.OUT.getName()) || accessPoint
                     .getDirection().getLiteral().equalsIgnoreCase(Direction.IN_OUT.getId()))
                     && invocationPattern.equals(CamelConstants.InvocationPatterns.SEND))
               {
                  String message = MessageFormat.format(
                        Camel_Messages.issue_Application_Contains_Out_AccessPoint_While_Endpoint_Pattern_Is_Set_To,
                        new Object[] {((ApplicationTypeImpl) element).getName(), invocationPattern});
                  result.add(Issue.error(element, message, CamelConstants.INVOCATION_PATTERN_EXT_ATT));
               }
            }
         }

      String camelContextId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            CamelConstants.CAMEL_CONTEXT_ID_ATT);
      if (StringUtils.isEmpty(camelContextId))
         result.add(Issue.error(element, Camel_Messages.issue_CamelContextID_is_Empty,
               CamelConstants.CAMEL_CONTEXT_ID_ATT));

      if (result.isEmpty())
         logger.debug(Camel_Messages.issue_No_Issues_Found);

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

}
