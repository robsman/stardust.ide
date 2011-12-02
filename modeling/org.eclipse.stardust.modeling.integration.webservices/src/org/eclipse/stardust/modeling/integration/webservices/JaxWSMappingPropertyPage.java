/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.integration.webservices;

import javax.wsdl.Part;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;


/**
 * @author fherinean
 * @version $Revision$
 */
public class JaxWSMappingPropertyPage extends AbstractModelElementPropertyPage
{
   private Label partName;

   private Label typeName;

   private TypeSelectionComposite classBrowser;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      Part part = getPart();
      partName.setText(part.getName());
      typeName.setText(JaxWSResource.getType(part).toString());
      classBrowser.setTypeText(getSynchronizer().getMapping(part).trim());
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

      FormBuilder.createLabel(composite, Diagram_Messages.LB_ClassName);
      classBrowser = new TypeSelectionComposite(composite, Diagram_Messages.WSMappingPropertyPage_Class);
      classBrowser.getText().addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            Part part = getPart();
            getSynchronizer().setMapping(part, classBrowser.getTypeText().trim());
         }
      });
      return composite;
   }

   private Part getPart()
   {
      return (Part) getElement().getAdapter(Part.class);
   }

   private JaxWSOutlineSynchronizer getSynchronizer()
   {
      return (JaxWSOutlineSynchronizer)
         getElement().getAdapter(JaxWSOutlineSynchronizer.class);
   }
}
