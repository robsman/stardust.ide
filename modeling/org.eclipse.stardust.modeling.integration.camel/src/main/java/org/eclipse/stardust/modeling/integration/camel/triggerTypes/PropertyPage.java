package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.*;
import org.eclipse.gef.EditPart;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.impl.TriggerTypeImpl;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.triggerTypes.ParameterMappingTablePage;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;

import org.eclipse.swt.widgets.*;

public class PropertyPage extends AbstractModelElementPropertyPage
{

   public static final String PARAMETER_MAPPING_TABLE_ID = ParameterMappingTablePage.PARAMETER_MAPPING_TABLE_ID;
   private static final String HEADERS_ACCESS_POINT_ID = "headers"; //$NON-NLS-1$

   private Text camelContextId;
   private Text routeExtension;
   private Text additionalBeans;
//   private Endpoint camelEndpoint;
   // extended attribute key
   private static final String CAMEL_TRIGGER_USERNAME = "carnot:engine:camel::username"; //$NON-NLS-1$
   private static final String CAMEL_TRIGGER_PASSWORD = "carnot:engine:camel::password"; //$NON-NLS-1$
   private static final String CAMEL_TRIGGER_EVENTCLASS = "eventClass"; //$NON-NLS-1$
   // extended attribute value
   private static final String CAMEL_TRIGGER_USERNAME_CV = "${camelTriggerUsername}"; //$NON-NLS-1$
   private static final String CAMEL_TRIGGER_PASSWORD_CV = "${camelTriggerPassword:Password}"; //$NON-NLS-1$
   private static final String CAMEL_TRIGGER_EVENTCLASS_CV = "message"; //$NON-NLS-1$

   private TriggerTypeImpl getApplication()
   {
      return (TriggerTypeImpl) getModelElement();
   }

   /**
    * (fh) This method is invoked only once when the dialog is created. You can use
    * getAdapter() to get the adapter object or getModelElement() to get the model element
    * if it is required for body construction.
    */
   @Override
   public Control createBody(Composite parent)
   {

      Composite composite = FormBuilder.createComposite(parent, 1);

      // TODO i18n
      // Diagram_Messages.GROUP_EndpointUriSetting, 2
      Group endpointUriSettingGroup = FormBuilder.createGroup(composite, Camel_Messages.label_EndpointSettings, 2, 2);
      endpointUriSettingGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(2));
      FormBuilder.createLabel(endpointUriSettingGroup, Camel_Messages.label_CamelContextId);
      this.camelContextId = FormBuilder.createText(endpointUriSettingGroup);

      // TODO i18n
      // Diagram_Messages.GROUP_RouteSetting, 2
      Group routeSettingGroup = FormBuilder.createGroup(composite, Camel_Messages.label_Route, 2);
      this.routeExtension = FormBuilder.createTextArea(routeSettingGroup, 200);

      Group additionalBeanSettingGroup = FormBuilder.createGroup(composite, Camel_Messages.label_AdditionalBean, 2);
      this.additionalBeans = FormBuilder.createTextArea(additionalBeanSettingGroup, 200);

      return composite;
   }

   /**
    * (fh) This method is invoked to store values from the page controls into the model
    * element. It has the behavior of "commit" and is invoked either when the user presses
    * the "Apply" button or closes the dialog with "Ok".
    */
   @Override
   public void loadElementFromFields(IModelElementNodeSymbol elementNodeSymbol, IModelElement modelElement)
   {

      String camelContextContent = this.camelContextId.getText();
      AttributeUtil.setAttribute((IExtensibleElement) modelElement, CAMEL_CONTEXT_ID_ATT, camelContextContent);

      String endpointTypeClass = AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
            ENDPOINT_TYPE_CLASS_ATT);

      if (!StringUtils.isEmpty(endpointTypeClass))
      {
         AttributeUtil.setAttribute((IExtensibleElement) modelElement, ENDPOINT_TYPE_CLASS_ATT, endpointTypeClass);
//         endpointTypeClass = mapTypeToEndpointClass("Generic Endpoint");
      }
      else
      {

      }

      // ISB-57: allow the user to override an existing definition using empty string
      String routeExtContent = this.routeExtension.getText();
      // if (!StringUtils.isEmpty(routeExtContent))
      AttributeUtil.setAttribute((IExtensibleElement) modelElement, ROUTE_EXT_ATT, routeExtContent);

      String additionalBeanDefinitionContent = this.additionalBeans.getText();
      // if (!StringUtils.isEmpty(routeExtContent))
      AttributeUtil.setAttribute((IExtensibleElement) modelElement, ADDITIONAL_SPRING_BEANS_DEF_ATT,
            additionalBeanDefinitionContent);

      AttributeUtil.setAttribute((IExtensibleElement) modelElement, CAMEL_TRIGGER_USERNAME, CAMEL_TRIGGER_USERNAME_CV);
      AttributeUtil.setAttribute((IExtensibleElement) modelElement, CAMEL_TRIGGER_PASSWORD, CAMEL_TRIGGER_PASSWORD_CV);
      // <carnot:Attribute Name="eventClass" Value="message"/>
      AttributeUtil.setAttribute((IExtensibleElement) modelElement, CAMEL_TRIGGER_EVENTCLASS,
            CAMEL_TRIGGER_EVENTCLASS_CV);

      // (fh) We don't want to clear all access points, since the OUT access point
      // is created in CamelParameterMappingPage. Maybe only the IN ones.

      for (AccessPointType apType : ((TriggerType) modelElement).getAccessPoint())
      {
         if (apType.getId().equals(HEADERS_ACCESS_POINT_ID))
         {
            ((TriggerType) modelElement).getAccessPoint().remove(apType);
            break;
         }
      }
   }

   /**
    * (fh) This method is invoked to load the page components with values from the model
    * element. It has the behavior of "begin" and is invoked either when the page is
    * displayed for the first time or when the user presses the "Restore Defaults" button.
    * This method should not attempt in any way to modify the model element, only the page
    * controls.
    */
   @Override
   public void loadFieldsFromElement(IModelElementNodeSymbol elementNodeSymbol, IModelElement modelElement)
   {

      String camelContextIdContent = AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
            CAMEL_CONTEXT_ID_ATT);
      if (!StringUtils.isEmpty(camelContextIdContent))
      {
         this.camelContextId.setText(camelContextIdContent);
      }
      else
      {
         this.camelContextId.setText(DEFAULT_CAMEL_CONTEXT_ID);

      }

      String routeExtContent = AttributeUtil.getAttributeValue((IExtensibleElement) modelElement, ROUTE_EXT_ATT);

      if (!StringUtils.isEmpty(routeExtContent))
         this.routeExtension.setText(routeExtContent);

      String additionalBeanDefContent = AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,ADDITIONAL_SPRING_BEANS_DEF_ATT);

      if (!StringUtils.isEmpty(additionalBeanDefContent))
         this.additionalBeans.setText(additionalBeanDefContent);


      for (AccessPointType apType : ((TriggerType) modelElement).getAccessPoint())
      {
         if (apType.getId().equals(HEADERS_ACCESS_POINT_ID))
         {
            ((TriggerType) modelElement).getAccessPoint().remove(apType);
            break;
         }
      }
   }

   private TriggerType getTrigger()
   {
      Object element = getElement();
      if (element instanceof EditPart)
      {
         element = ((EditPart) element).getModel();
      }
      if (element instanceof IModelElementNodeSymbol)
      {
         element = ((IModelElementNodeSymbol) element).getModelElement();
      }
      return element instanceof TriggerType ? (TriggerType) element : null;
   }
}
