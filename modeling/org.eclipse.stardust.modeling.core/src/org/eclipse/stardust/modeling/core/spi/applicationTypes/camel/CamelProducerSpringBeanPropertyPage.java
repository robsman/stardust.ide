package org.eclipse.stardust.modeling.core.spi.applicationTypes.camel;
import static org.eclipse.stardust.modeling.integration.camel.CamelConstants.*;
//import org.eclipse.stardust.engine.extensions.camel.CamelConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * Camel Application type property page; 3 fields are provided camelContextId,
 * routeDefinition and additional spring beans definition
 * 
 * @author 
 * 
 */
public class CamelProducerSpringBeanPropertyPage extends AbstractModelElementPropertyPage
{
   private Text routeEntry;
   private IExtensibleElement extensibleElement;

   public void dispose()
   {
      super.dispose();
   }

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
      getAttributeValue(PRODUCER_ROUTE_ATT, routeEntry);
   }

   /**
    * (fh) This method is invoked to store values from the page controls into the model
    * element. It has the behavior of "commit" and is invoked either when the user presses
    * the "Apply" button or closes the dialog with "Ok".
    */
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      extensibleElement = (IExtensibleElement) element;
      setAttributeValue(PRODUCER_ROUTE_ATT, null, routeEntry);
   }

   /**
    * (fh) This method is invoked only once when the dialog is created. You can use
    * getAdapter() to get the adapter object or getModelElement() to get the model element
    * if it is required for body construction.
    */
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      FormBuilder.createLabel(composite, Camel_Messages.label_Route);
      routeEntry = FormBuilder.createTextArea(composite, 2);

      return composite;
   }

   private void setAttributeValue(String attrName, String attrType, Text control)
   {
      if (control instanceof Text)
      {
         AttributeUtil.setAttribute(extensibleElement, attrName, control.getText());
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
}