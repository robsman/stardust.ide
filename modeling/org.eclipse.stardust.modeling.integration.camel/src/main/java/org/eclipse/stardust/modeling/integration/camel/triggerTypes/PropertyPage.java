package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import static org.eclipse.stardust.modeling.integration.camel.Constants.*;
import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.*;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.extensions.camel.CamelConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;
import org.eclipse.swt.widgets.*;

public class PropertyPage extends AbstractModelElementPropertyPage
{
   private Text camelContextId;

   private Text routeExtension;

   private Text additionalBeans;

   private Button transactedRoute;
   
   private Button autoStartupRoute;

   private static final String CAMEL_TRIGGER_USERNAME = "carnot:engine:camel::username";

   private static final String CAMEL_TRIGGER_PASSWORD = "carnot:engine:camel::password";

   private static final String CAMEL_TRIGGER_EVENTCLASS = "eventClass";

   // extended attribute value
   private static final String CAMEL_TRIGGER_USERNAME_CV = "${camelTriggerUsername}";

   private static final String CAMEL_TRIGGER_PASSWORD_CV = "${camelTriggerPassword:Password}";

   private static final String CAMEL_TRIGGER_EVENTCLASS_CV = "message";

   /**
    * (fh) This method is invoked only once when the dialog is created. You can use
    * getAdapter() to get the adapter object or getModelElement() to get the model element
    * if it is required for body construction.
    */
   @Override
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      Group endpointUriSettingGroup = FormBuilder.createGroup(composite,
            Camel_Messages.label_EndpointSettings, 2, 2);
      endpointUriSettingGroup.setLayoutData(FormBuilder
            .createDefaultSingleLineWidgetGridData(2));
      FormBuilder.createLabel(endpointUriSettingGroup,
            Camel_Messages.label_CamelContextId);
      this.camelContextId = FormBuilder.createText(endpointUriSettingGroup);
      this.transactedRoute = FormBuilder.createCheckBox(endpointUriSettingGroup,
            Camel_Messages.label_Transacted_Route);
      this.transactedRoute.setSelection(true);
      this.autoStartupRoute = FormBuilder.createCheckBox(endpointUriSettingGroup,
              Camel_Messages.label_AutoStartup_Route);
      this.autoStartupRoute.setSelection(true);
      Group routeSettingGroup = FormBuilder.createGroup(composite,
            Camel_Messages.label_Route, 2);
      this.routeExtension = FormBuilder.createTextArea(routeSettingGroup, 200);
      Group additionalBeanSettingGroup = FormBuilder.createGroup(composite,
            Camel_Messages.label_AdditionalBean, 2);
      this.additionalBeans = FormBuilder.createTextArea(additionalBeanSettingGroup, 200);
      createAccessPointsPage();
      return composite;
   }

   /**
    * (fh) This method is invoked to store values from the page controls into the model
    * element. It has the behavior of "commit" and is invoked either when the user presses
    * the "Apply" button or closes the dialog with "Ok".
    */
   @Override
   public void loadElementFromFields(IModelElementNodeSymbol elementNodeSymbol,
         IModelElement modelElement)
   {
      String endpointTypeClass = AttributeUtil.getAttributeValue(
            (IExtensibleElement) modelElement, ENDPOINT_TYPE_CLASS_ATT);

      if (!StringUtils.isEmpty(endpointTypeClass))
      {
         AttributeUtil.setAttribute((IExtensibleElement) modelElement,
               ENDPOINT_TYPE_CLASS_ATT, endpointTypeClass);
      }
      String camelContextContent = this.camelContextId.getText();
      Boolean isSelected = ((Button) transactedRoute).getSelection();
      Boolean isAutoStartup = ((Button) autoStartupRoute).getSelection();
      String routeExtContent = this.routeExtension.getText();
      String additionalBeanDefinitionContent = this.additionalBeans.getText();

      AttributeUtil.setAttribute((IExtensibleElement) modelElement, CAMEL_CONTEXT_ID_ATT,
            camelContextContent);
      AttributeUtil.setAttribute((IExtensibleElement) modelElement, ROUTE_EXT_ATT,
            routeExtContent);
      AttributeUtil.setAttribute((IExtensibleElement) modelElement,
            ADDITIONAL_SPRING_BEANS_DEF_ATT, additionalBeanDefinitionContent);
      AttributeUtil.setAttribute((IExtensibleElement) modelElement,
            CamelConstants.TRANSACTED_ROUTE_EXT_ATT, isSelected.toString());
      AttributeUtil.setBooleanAttribute((IExtensibleElement) modelElement,
            CamelConstants.AUTO_STARTUP_ROUTE_EXT_ATT, isAutoStartup);

      AttributeUtil.setAttribute((IExtensibleElement) modelElement,
            CAMEL_TRIGGER_USERNAME, CAMEL_TRIGGER_USERNAME_CV);
      AttributeUtil.setAttribute((IExtensibleElement) modelElement,
            CAMEL_TRIGGER_PASSWORD, CAMEL_TRIGGER_PASSWORD_CV);
      AttributeUtil.setAttribute((IExtensibleElement) modelElement,
            CAMEL_TRIGGER_EVENTCLASS, CAMEL_TRIGGER_EVENTCLASS_CV);
   }

   /**
    * (fh) This method is invoked to load the page components with values from the model
    * element. It has the behavior of "begin" and is invoked either when the page is
    * displayed for the first time or when the user presses the "Restore Defaults" button.
    * This method should not attempt in any way to modify the model element, only the page
    * controls.
    */
   @Override
   public void loadFieldsFromElement(IModelElementNodeSymbol elementNodeSymbol,
         IModelElement modelElement)
   {
      String camelContextIdContent = AttributeUtil.getAttributeValue(
            (IExtensibleElement) modelElement, CAMEL_CONTEXT_ID_ATT);
      if (!StringUtils.isEmpty(camelContextIdContent))
      {
         this.camelContextId.setText(camelContextIdContent);
      }
      else
      {
         this.camelContextId.setText(DEFAULT_CAMEL_CONTEXT_ID);
      }

      if (AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
            TRANSACTED_ROUTE_EXT_ATT) == null)
         AttributeUtil.setAttribute((IExtensibleElement) modelElement,
               TRANSACTED_ROUTE_EXT_ATT, Boolean.TRUE.toString());
      getCheckBoxValue((IExtensibleElement) modelElement, TRANSACTED_ROUTE_EXT_ATT,
            transactedRoute);
      
      if (AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
    		  AUTO_STARTUP_ROUTE_EXT_ATT) == null)
           AttributeUtil.setBooleanAttribute((IExtensibleElement) modelElement,
        		   AUTO_STARTUP_ROUTE_EXT_ATT, Boolean.TRUE);
        getCheckBoxValue((IExtensibleElement) modelElement, AUTO_STARTUP_ROUTE_EXT_ATT,
              autoStartupRoute);

      String routeExtContent = AttributeUtil.getAttributeValue(
            (IExtensibleElement) modelElement, ROUTE_EXT_ATT);
      if (!StringUtils.isEmpty(routeExtContent))
         this.routeExtension.setText(routeExtContent);

      String additionalBeanDefContent = AttributeUtil.getAttributeValue(
            (IExtensibleElement) modelElement, ADDITIONAL_SPRING_BEANS_DEF_ATT);
      if (!StringUtils.isEmpty(additionalBeanDefContent))
         this.additionalBeans.setText(additionalBeanDefContent);
   }

   private void getCheckBoxValue(IExtensibleElement modelElement, String attrName,
         Button control)
   {
      String value;
      if ((value = AttributeUtil.getAttributeValue((IExtensibleElement) modelElement,
            attrName)) != null)
      {
         control.setSelection(new Boolean(value));
      }
   }

   private class OutAccessPointDefinitionPageConfigurationElement
         extends ConfigurationElement
   {
      private Map<String, Object> attributes = new HashMap<String, Object>();

      public OutAccessPointDefinitionPageConfigurationElement(String id, String name)
      {
         super(ConfigurationElement.CFG_PAGE);
         attributes.put(SpiConstants.ID, id);
         attributes.put(SpiConstants.NAME, name);
         attributes.put(SpiConstants.ICON, null);
         attributes.put(SpiConstants.PROPERTY_PAGE_CLASS,
               OutAccessPointListTablePage.class.getName());//
      }

      public Object createExecutableExtension(String propertyName) throws CoreException
      {
         return new OutAccessPointListTablePage();
      }

      public String getAttribute(String name) throws InvalidRegistryObjectException
      {
         return (String) attributes.get(name);
      }

      public String[] getAttributeNames()
      {
         return (String[]) attributes.keySet().toArray(new String[attributes.size()]);
      }
   }

   private ConfigurationElement creatAccessPointDefinitionPageConfigurationElement(
         String id, String name)
   {
      return new OutAccessPointDefinitionPageConfigurationElement(id, name);
   }

   private void createAccessPointsPage()
   {
      ConfigurationElement outParamDefiniitionPage = creatAccessPointDefinitionPageConfigurationElement(
            OUT_ACCESS_POINT_LIST_PAGE_ID, Camel_Messages.label_Parameters_Definition);
      CarnotPreferenceNode node = new CarnotPreferenceNode(outParamDefiniitionPage,
            getElement(), 3);
      addNodeTo(null, node, null);
      refreshTree();
   }
}
