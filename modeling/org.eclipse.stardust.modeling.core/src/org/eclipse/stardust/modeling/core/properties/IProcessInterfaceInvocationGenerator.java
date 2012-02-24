package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.swt.widgets.Composite;

public interface IProcessInterfaceInvocationGenerator
{
   void setComponentVisibility(String externalInvocationType);
   
   void handleValidationStatusFromParent(IQuickValidationStatus status);
   
   Composite createExposeComposite(Composite wsdlComposite, IProcessDefinitionTypeProvider processProvider);
   
   interface IProcessDefinitionTypeProvider
   {
      ProcessDefinitionType getProcessDefinitionType();
   }
}
