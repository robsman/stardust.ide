/*******************************************************************************
 * Copyright (c) 2011 - 2012 SunGard CSA 
 *******************************************************************************/

package org.eclipse.stardust.modeling.core.spi.applicationTypes.webservice;

import javax.wsdl.Part;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * @author fherinean
 * @version $Revision: 57229 $
 */
public class JaxWSXmlTemplatePropertyPage extends
   AbstractModelElementPropertyPage
{
   private Label partName;

   private Label typeName;

   private Text templateTextArea;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      Part part = getPart();
      partName.setText(part.getName());
      typeName.setText(JaxWSResource.getType(part).toString());
      templateTextArea.setText(getSynchronizer().getTemplate(part));
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_PartName);
      partName = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      FormBuilder.createLabel(composite, Diagram_Messages.LB_XMLName);
      typeName = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$

      FormBuilder.createLabel(composite, Diagram_Messages.LB_Template, 2); 
      templateTextArea = FormBuilder.createTextArea(composite, 2);
      templateTextArea.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String value = templateTextArea.getText();
            getSynchronizer().setTemplate(getPart(), value);
         }
      });
      return composite;
   }

   private JaxWSOutlineSynchronizer getSynchronizer()
   {
      return (JaxWSOutlineSynchronizer)
         getElement().getAdapter(JaxWSOutlineSynchronizer.class);
   }

   private Part getPart()
   {
      return (Part) getElement().getAdapter(Part.class);
   }
}
