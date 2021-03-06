package org.eclipse.stardust.modeling.integration.camel.ui;

import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.*;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
//import org.eclipse.stardust.modeling.integration.camel.CamelConstants;
//import org.eclipse.jface.viewers.ArrayContentProvider;
//import org.eclipse.jface.viewers.ComboViewer;
//import org.eclipse.jface.viewers.ISelectionChangedListener;
//import org.eclipse.jface.viewers.IStructuredSelection;
//import org.eclipse.jface.viewers.SelectionChangedEvent;
//import org.eclipse.jface.viewers.StructuredSelection;
//
//import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * Camel Application type property page; 2 fields are provided, - correlationPattern, used
 * to select how the wating activity will be found - consumer route: used to provide the
 * xml definition of the consumer route
 * 
 * @author
 * 
 */
public class CamelConsumerPropertyPage extends AbstractModelElementPropertyPage
{
   private Text consumerRoute;
   private IExtensibleElement extensibleElement;
   private Button transactedRoute;
   private Button autoStartupRoute;
   // private ComboViewer correlationPatternViewer;

//   private ApplicationType getApplication()
//   {
//      return (ApplicationType) getModelElement();
//   }

   /**
    * (fh) This method is invoked to load the page components with values from the model
    * element. It has the behavior of "begin" and is invoked either when the page is
    * displayed for the first time or when the user presses the "Restore Defaults" button.
    * This method should not attempt in any way to modify the model element, only the page
    * controls.
    */
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      extensibleElement = (IExtensibleElement) element;
      getAttributeValue(CONSUMER_ROUTE_ATT, consumerRoute);
      if(AttributeUtil.getAttributeValue(extensibleElement, TRANSACTED_ROUTE_EXT_ATT) == null)
         AttributeUtil.setAttribute(extensibleElement, TRANSACTED_ROUTE_EXT_ATT, Boolean.TRUE.toString());
      getCheckBoxValue(TRANSACTED_ROUTE_EXT_ATT,transactedRoute);
      
      if(AttributeUtil.getAttributeValue(extensibleElement, AUTO_STARTUP_ROUTE_EXT_ATT) == null)
          AttributeUtil.setBooleanAttribute(extensibleElement, AUTO_STARTUP_ROUTE_EXT_ATT, Boolean.TRUE);
      getCheckBoxValue(AUTO_STARTUP_ROUTE_EXT_ATT,autoStartupRoute);
   }

   /**
    * (fh) This method is invoked to store values from the page controls into the model
    * element. It has the behavior of "commit" and is invoked either when the user presses
    * the "Apply" button or closes the dialog with "Ok".
    */
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      extensibleElement = (IExtensibleElement) element;
      setAttributeValue(CONSUMER_ROUTE_ATT, null, consumerRoute);
      setAttributeValue(TRANSACTED_ROUTE_EXT_ATT, null, transactedRoute);
      setBooleanAttributeValue(AUTO_STARTUP_ROUTE_EXT_ATT, null, autoStartupRoute);
      AttributeUtil.setAttribute(extensibleElement,  PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_RESPONSIBILITY, "application");
   }

   /**
    * (fh) This method is invoked only once when the dialog is created. You can use
    * getAdapter() to get the adapter object or getModelElement() to get the model element
    * if it is required for body construction.
    */
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      
      transactedRoute = FormBuilder.createCheckBox(composite,
            Camel_Messages.label_Transacted_Route);
      transactedRoute.setSelection(true);
      autoStartupRoute = FormBuilder.createCheckBox(composite,
              Camel_Messages.label_AutoStartup_Route);
      autoStartupRoute.setSelection(true);
      
      FormBuilder.createLabel(composite, Camel_Messages.label_Route);
      FormBuilder.createLabel(composite, "");
      consumerRoute = FormBuilder.createTextArea(composite, 2);
      return composite;
   }

   private void setAttributeValue(String attrName, String attrType, Object control)
   {
      if (control instanceof Text)
      {
         AttributeUtil.setAttribute(extensibleElement, attrName, ((Text) control).getText());
      }
      if (control instanceof Button)
      {
         Boolean isSelected = ((Button) control).getSelection();
         AttributeUtil.setAttribute(extensibleElement, attrName, isSelected.toString());
      }
   }

   private void setBooleanAttributeValue(String attrName, String attrType, Object control)
   {
      if (control instanceof Button)
      {
         Boolean isSelected = ((Button) control).getSelection();
         AttributeUtil.setBooleanAttribute(extensibleElement, attrName, isSelected);
      }
   }

   private void getAttributeValue(String attrName, Text control)
   {
      String value;
      if ((value = AttributeUtil.getAttributeValue(extensibleElement, attrName)) != null)
      {
         control.setText(value);
      }
   }
   
   private void getCheckBoxValue(String attrName, Button control)
   {
      String value;
      if ((value = AttributeUtil.getAttributeValue(extensibleElement, attrName)) != null)
      {
         control.setSelection(new Boolean(value));
      }
   }
}