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
package org.eclipse.stardust.modeling.model.i18n.properties;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.model.i18n.I18N_Messages;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class I18NBundlesPropertyPage extends AbstractModelElementPropertyPage
{
   private static String MESSAGE = I18N_Messages.I18NBundlesPropertyPage_Bundle_Basename_Label;
   
   private PropertiesList propertiesList;
   private PropertyValuesEditor valuesEditor;

   private ScopedPropertyModel model = null;

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      propertiesList = new PropertiesList();
      propertiesList.createBody(composite, 1);
      ((GridData) propertiesList.getViewer().getTable().getLayoutData()).widthHint =
         FormBuilder.getTextSize(parent, FormBuilder.DEFAULT_TEXT_FIELD_CHARS + 
               MESSAGE.length() + 1);

      valuesEditor = new PropertyValuesEditor();
      valuesEditor.createBody(composite, 1);
      
      propertiesList.getViewer().addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            StructuredSelection selection = (StructuredSelection) event.getSelection();
            String selectedProperty = selection.isEmpty() ? null : (String) selection.getFirstElement();
            valuesEditor.setPropertyName(selectedProperty);
         }
      });
      
      return composite;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      model.getPropertyModel().save();
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (model == null)
      {
         model = new ScopedPropertyModel(element);
         model.register();
         propertiesList.setModel(model);
         valuesEditor.setModel(model);
      }
      propertiesList.select(PropertyModel.NAME);

      model.getPropertyModel().addPropertyModelListener(new IPropertyModelListener()
      {
         public void localesChanged()
         {
            checkLocales();
         }
      });
      checkLocales();
   }

   private void checkLocales()
   {
      if (!model.getPropertyModel().hasLocales())
      {
         setMessage(I18N_Messages.I18NBundlesPropertyPage_NoLanguageBundles, INFORMATION);
      }
      else
      {
         setMessage(null);
      }
   }

   public void dispose()
   {
      if (model != null)
      {
         model.unregister();
         model.dispose();
      }
      super.dispose();
   }

   public ScopedPropertyModel getScopedPropertyModel()
   {
      return model;
   }

   public void apply()
   {
      super.apply();
      super.addDependentCommand(model.getPropertyModel().getCommand());
   }
}
