package org.eclipse.stardust.modeling.core.spi.applicationTypes.camel;

import static org.eclipse.stardust.modeling.integration.camel.CamelConstants.*;

//import org.eclipse.stardust.modeling.integration.camel.CamelConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
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
 * Camel Application type property page; 2 fields are provided,
 *  - correlationPattern, used to select how the wating activity will be found
 *  - consumer route: used to provide the xml definition of the consumer route
 *  
 * @author
 * 
 */
public class CamelConsumerPropertyPage extends AbstractModelElementPropertyPage
{
   private Text consumerRoute;
   private IExtensibleElement extensibleElement;
   private ComboViewer correlationPatternViewer;

   private ApplicationType getApplication()
   {
      return (ApplicationType) getModelElement();
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
      getAttributeValue(CONSUMER_ROUTE_ATT, consumerRoute);
      getCorrelationPatternValue();
   }

   private void getCorrelationPatternValue()
   {
      String value = AttributeUtil.getAttributeValue(extensibleElement, CORRELATION_PATTERN_EXT_ATT);
      if (value == null)
         value = CorrelationKey.PROCESS;

      if (value.equalsIgnoreCase(CorrelationKey.PROCESS))
      {
         correlationPatternViewer.setSelection(new StructuredSelection(Camel_Messages.label_process));
      }
      if (value.equalsIgnoreCase(CorrelationKey.DATA))
      {
         correlationPatternViewer.setSelection(new StructuredSelection(Camel_Messages.label_data));
      }
      // if(value.equalsIgnoreCase(CorrelationKey.ACTIVITY)){
      // correlationPatternViewer.setSelection(new
      // StructuredSelection(Camel_Messages.label_activity));
      // }
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
      AttributeUtil.setAttribute(extensibleElement, INVOCATION_PATTERN_EXT_ATT, RECEIVE);
   }

   /**
    * (fh) This method is invoked only once when the dialog is created. You can use
    * getAdapter() to get the adapter object or getModelElement() to get the model element
    * if it is required for body construction.
    */
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      FormBuilder.createLabel(composite, Camel_Messages.label_correlation_Pattern);
      correlationPatternViewer = new ComboViewer(FormBuilder.createCombo(composite));
      correlationPatternViewer.setContentProvider(ArrayContentProvider.getInstance());
      correlationPatternViewer.add(Camel_Messages.label_process);
      correlationPatternViewer.add(Camel_Messages.label_data);
      // correlationPatternViewer.add(Camel_Messages.label_activity);
      correlationPatternViewer.setSelection(new StructuredSelection(Camel_Messages.label_process));
      correlationPatternViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         @Override
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            String correlationKey = CorrelationKey.PROCESS;
            ApplicationType application = getApplication();
            if (selection == null || selection.getFirstElement() == null)
               correlationKey = CorrelationKey.PROCESS;
            else
            {
               if (((String) selection.getFirstElement()).equalsIgnoreCase(Camel_Messages.label_process))
               {
                  correlationKey = CorrelationKey.PROCESS;

               }
               // if (((String) selection.getFirstElement())
               // .equalsIgnoreCase(Camel_Messages.label_activity))
               // {
               // correlationKey = CorrelationKey.ACTIVITY;
               // }
               if (((String) selection.getFirstElement()).equalsIgnoreCase(Camel_Messages.label_data))
               {
                  correlationKey = CorrelationKey.DATA;
               }
            }
            AttributeUtil.setAttribute(application, CORRELATION_PATTERN_EXT_ATT, correlationKey);
         }
      });

      FormBuilder.createLabel(composite, Camel_Messages.label_Route);
      consumerRoute = FormBuilder.createTextArea(composite, 2);
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