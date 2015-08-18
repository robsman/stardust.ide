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
package org.eclipse.stardust.modeling.core.spi.dataTypes.entity;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;

public class EntityBeanPropertyPage extends AbstractModelElementPropertyPage
{
   private Map delegates = new HashMap();
   private Map bodies = new HashMap();
   private Button radio3x;
   private Button radio2x;
   private StackLayout stackLayout;
   private Composite bodyStack;
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AbstractModelElementPropertyPage delegate = getCreateDelegate();
      delegate.loadFieldsFromElement(symbol, element);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AbstractModelElementPropertyPage delegate = getCreateDelegate();
      delegate.loadElementFromFields(symbol, element);
   }

   public Object getAdapter(Class adapter)
   {
      AbstractModelElementPropertyPage delegate = getCreateDelegate();
      return delegate.getAdapter(adapter);
   }

   public Control createBody(final Composite parent)
   {
      Composite bodyComposite = FormBuilder.createComposite(parent, 1);
            
      Group versionGroup = FormBuilder.createGroup(bodyComposite, "Version", 2); //$NON-NLS-1$
      versionGroup.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData());
      radio3x = FormBuilder.createRadioButton(versionGroup, Diagram_Messages.BUT_EJB_DREI_X);
      radio3x.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e)
            {
            }

            public void widgetSelected(SelectionEvent e)
            {
               if (radio3x.getSelection())
               {
                  setEJBVersion(EntityBeanConstants.VERSION_3_X);
               }
            }
      });
      radio2x = FormBuilder.createRadioButton(versionGroup, Diagram_Messages.BUT_EJB_ZWEI_X);
      radio2x.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
   
            public void widgetSelected(SelectionEvent e)
            {
               if (radio2x.getSelection())
               {
                  setEJBVersion(EntityBeanConstants.VERSION_2_X);
               }
            }
      });
      String style = getSetVersionAttribute();
      radio3x.setSelection(EntityBeanConstants.VERSION_3_X.equals(style));
      radio2x.setSelection(EntityBeanConstants.VERSION_2_X.equals(style));
      bodyStack = FormBuilder.createComposite(bodyComposite, 1);
      stackLayout = new StackLayout();
      bodyStack.setLayout(stackLayout);
      setCreateBody();
      
      IModelElement modelElement = (IModelElement) getModelElement();
      if(modelElement != null 
            && modelElement instanceof DataType
            && ((DataType) modelElement).isPredefined())
      {
         radio2x.setEnabled(false);
         radio3x.setEnabled(false);
      }      
      return bodyComposite;
   }

   private void setCreateBody()
   {
      String style = getSetVersionAttribute();
      Control body = (Control) bodies.get(style);
      if (body == null)
      {
         AbstractModelElementPropertyPage delegate = getCreateDelegate();
         body = delegate.createBody(bodyStack);
         bodies.put(style, body);
      }
      stackLayout.topControl = body;
      bodyStack.layout();
   }

   protected void setEJBVersion(String version)
   {
      DataType data = (DataType) getModelElement();
      AttributeUtil.setAttribute(data, EntityBeanConstants.VERSION_ATT, version);
      // TODO: move / clear attributes
      boolean mustInitialize = !delegates.containsKey(version);
      setCreateBody();
      if (mustInitialize)
      {
         AbstractModelElementPropertyPage delegate = getCreateDelegate();
         IModelElementNodeSymbol symbol = getModelElementNodeSymbol();
         IModelElement element = getModelElementFromSymbol(symbol);
         delegate.loadFieldsFromElement(symbol, element);
      }
   }

   private AbstractModelElementPropertyPage getCreateDelegate()
   {
      String style = getSetVersionAttribute();
      AbstractModelElementPropertyPage delegate = 
         (AbstractModelElementPropertyPage) delegates.get(style);
      if (delegate == null)
      {
         try
         {
            delegate = (AbstractModelElementPropertyPage) SpiExtensionRegistry.createPropertyPage(
               "_cwm_spi_data_", style); //$NON-NLS-1$
            delegate.setContainer(getContainer());
            delegate.setElement(getElement());
         }
         catch (Exception ex)
         {
            // default to EJB 2.0 ?
            // radio3x.setEnabled(false);
            // TODO: return a dummy 3.0 page
         }
         /*if (delegate == null)
         {
            delegate = new SessionBean20PropertyPage();
         }*/
         delegates.put(style, delegate);
      }
      return delegate;
   }

   private String getSetVersionAttribute()
   {
      String style = EntityBeanConstants.VERSION_3_X;
      DataType data = (DataType) getModelElement();
      if (data.getAttribute().isEmpty())
      {
         // new created apps are 3.0
         AttributeUtil.setAttribute(data, EntityBeanConstants.VERSION_ATT, style);
      }
      else
      {
         style = AttributeUtil.getAttributeValue(data, EntityBeanConstants.VERSION_ATT);
         if (style == null)
         {
            // old style app
            style = EntityBeanConstants.VERSION_2_X;
            AttributeUtil.setAttribute(data, EntityBeanConstants.VERSION_ATT, style);
         }
      }
      return style;
   }
}
