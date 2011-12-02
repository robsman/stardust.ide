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
package org.eclipse.stardust.modeling.core.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPropertyPage;


public class DataTypeHintPage extends AbstractModelElementPropertyPage
      implements IWorkbenchPropertyPage
{
   private Button textButton;

   private Button numericButton;

   private Button complexButton;

   private List buttons = new ArrayList();

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager binding = getWidgetBindingManager();

      for (Iterator iter = buttons.iterator(); iter.hasNext();)
      {
         Button btn = (Button) iter.next();
         binding.getModelBindingManager()
               .bind(
                     WidgetBindingManager.createModelAdapter(
                           (IExtensibleElement) element,
                           CarnotConstants.DATA_TYPE_HINT_ATT, false),
                     getSwtButtonAdapter(btn));
      }

   }

   private SwtWidgetAdapter getSwtButtonAdapter(Button button)
   {
      return new SwtWidgetAdapter(button)
      {
         private SelectionListener listener;
 
         public void bind(IBindingMediator manager)
         {
            super.bind(manager);

            final Button button = (Button) getWidget();

            button.addSelectionListener(new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  String value = null;
                  if (textButton.getSelection())
                  {
                     value = CarnotConstants.TEXT_HINT;
                  }
                  else if (numericButton.getSelection())
                  {
                     value = CarnotConstants.NUMERIC_HINT;
                  }
                  else if (complexButton.getSelection())
                  {
                     value = CarnotConstants.COMPLEX_HINT;
                  }
                  updateModel(value);
               }
            });
         }
        
         public void unbind()
         {
            if (null != listener)
            {
               if (!getWidget().isDisposed())
               {
                  ((Button) getWidget()).removeSelectionListener(listener);
               }
               listener = null;
            }

            super.unbind();
         }
        
         public void updateControl(Object value)
         {
            value = value != null ? value : CarnotConstants.TEXT_HINT;
            textButton.setSelection(CarnotConstants.TEXT_HINT.equals(value));
            numericButton.setSelection(CarnotConstants.NUMERIC_HINT.equals(value));
            complexButton.setSelection(CarnotConstants.COMPLEX_HINT.equals(value));
         }
      };
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);
      Group group = FormBuilder.createGroup(composite,
            Diagram_Messages.DataTypeHintPage_GroupLabel, 1);
      textButton = FormBuilder.createRadioButton(group,
            Diagram_Messages.DataTypeHintPage_TextLabel);
      numericButton = FormBuilder.createRadioButton(group,
            Diagram_Messages.DataTypeHintPage_NumericLabel);
      complexButton = FormBuilder.createRadioButton(group,
            Diagram_Messages.DataTypeHintPage_ComplexLabel);
      buttons.add(textButton);
      buttons.add(numericButton);
      buttons.add(complexButton);
      return composite;
   }

}
