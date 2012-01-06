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
package org.eclipse.stardust.modeling.core.spi.applicationTypes.jms;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageProvider;
import org.eclipse.stardust.engine.extensions.jms.app.JMSLocation;
import org.eclipse.stardust.engine.extensions.jms.app.MessageType;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IMessageProvider;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author fherinean
 * @version $Revision$
 */
public class RequestPropertyPage extends AbstractModelElementPropertyPage
{
   private static final String[] types = {
         MessageType.MAP.getId(), MessageType.OBJECT.getId(), MessageType.TEXT.getId(),
         MessageType.STREAM.getId()};

   private static final String[] classNames = {
         null, Object.class.getName(), String.class.getName(), null};

   private LabeledText factoryJndiText;

   private LabeledText queueJndiText;

   private LabeledCombo lbcMsgProvider;

   private ComboViewer providerCombo;

   private LabeledCombo lbcMsgType;

   private ComboViewer messageTypeCombo;

   private Button includeHeaders;

   private Button[] buttons;

   private ISelectionChangedListener providerListener = new ISelectionChangedListener()
   {
      public void selectionChanged(SelectionChangedEvent event)
      {
         providerChanged(true);
      }
   };

   private ISelectionChangedListener messageListener = new ISelectionChangedListener()
   {
      public void selectionChanged(SelectionChangedEvent event)
      {
         messageTypeChanged();
      }
   };

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      providerCombo.removeSelectionChangedListener(providerListener);
      messageTypeCombo.removeSelectionChangedListener(messageListener);

      WidgetBindingManager binding = getWidgetBindingManager();

      ApplicationType application = (ApplicationType) element;

      binding.bind(factoryJndiText, application,
            PredefinedConstants.QUEUE_CONNECTION_FACTORY_NAME_PROPERTY);
      binding.bind(queueJndiText, application, PredefinedConstants.QUEUE_NAME_PROPERTY);

      binding.getValidationBindingManager().bind(application,
            PredefinedConstants.MESSAGE_PROVIDER_PROPERTY, lbcMsgProvider.getLabel());
      binding.getValidationBindingManager().bind(application,
            PredefinedConstants.MESSAGE_TYPE_ATT, lbcMsgType.getLabel());

      Collection providers = getProviders();

      String providerClass = AttributeUtil.getAttributeValue(application,
            PredefinedConstants.MESSAGE_PROVIDER_PROPERTY);
      providerCombo.setInput(providers);
      IMessageProvider provider = findProvider(providers, providerClass);
      providerCombo.setSelection(provider == null ? null : new StructuredSelection(
            provider));

      providerChanged(false);

      String messageType = AttributeUtil.getAttributeValue(application,
            PredefinedConstants.REQUEST_MESSAGE_TYPE_PROPERTY);
      messageTypeCombo.setSelection(new StructuredSelection((null == messageType)
            ? types[0]
            : messageType));

      AttributeType attribute = AttributeUtil.getAttribute(application,
            PredefinedConstants.INCLUDE_OID_HEADERS_PROPERTY);
      includeHeaders.setSelection(attribute != null
            && AttributeUtil.getBooleanValue(attribute));

      providerCombo.addSelectionChangedListener(providerListener);
      messageTypeCombo.addSelectionChangedListener(messageListener);
   }

   private IMessageProvider findProvider(Collection providers, String providerClass)
   {
      for (Iterator iterator = providers.iterator(); iterator.hasNext();)
      {
         IMessageProvider msgProvider = (IMessageProvider) iterator.next();
         if (msgProvider.getRuntimeClassName().equals(providerClass))
         {
            return msgProvider;
         }
      }
      return (IMessageProvider) (providerClass == null && providers.size() == 1
            ? providers.iterator().next()
            : null);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ApplicationType application = (ApplicationType) element;

      IMessageProvider provider = getSelectedProvider();
      AttributeUtil.setAttribute(application,
            PredefinedConstants.MESSAGE_PROVIDER_PROPERTY, provider == null
                  ? null
                  : provider.getRuntimeClassName());

      AttributeUtil.setAttribute(application,
            PredefinedConstants.REQUEST_MESSAGE_TYPE_PROPERTY,
            MessageType.class.getName(), getSelectedMessageType());

      AttributeUtil.setAttribute(application,
            PredefinedConstants.INCLUDE_OID_HEADERS_PROPERTY, Boolean.TYPE.getName(),
            includeHeaders.getSelection() ? Boolean.TRUE.toString() : Boolean.FALSE
                  .toString());
   }

   private IMessageProvider getSelectedProvider()
   {
      IStructuredSelection selection = (IStructuredSelection) providerCombo
            .getSelection();
      IMessageProvider provider = null;
      if (selection != null && !selection.isEmpty())
      {
         provider = (IMessageProvider) selection.getFirstElement();
      }
      return provider;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      this.factoryJndiText = FormBuilder.createLabeledText(composite,
            Diagram_Messages.LB_ConnectionFactoryJNDI);
      this.queueJndiText = FormBuilder.createLabeledText(composite,
    		  Diagram_Messages.LB_QueueJNDI);

      this.lbcMsgProvider = FormBuilder.createLabeledCombo(composite,
            Diagram_Messages.LB_SPI_MessageProviderTypeClass);
      providerCombo = new ComboViewer(lbcMsgProvider.getCombo());
      providerCombo.setSorter(new ViewerSorter());
      providerCombo.setContentProvider(new ArrayContentProvider());
      providerCombo.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            return ((IMessageProvider) element).getName();
         }
      });

      this.lbcMsgType = FormBuilder.createLabeledCombo(composite,
    		  Diagram_Messages.LB_MessageType);
      messageTypeCombo = new ComboViewer(lbcMsgType.getCombo());
      messageTypeCombo.setSorter(new ViewerSorter());
      messageTypeCombo.setContentProvider(new ArrayContentProvider());

      FormBuilder.createLabel(composite, Diagram_Messages.LB_IncludeOIDHeaderFields);
      includeHeaders = FormBuilder
            .createCheckBox(composite, JmsPropertyPage.EMPTY_STRING);

      return composite;
   }

   private void messageTypeChanged()
   {
      ApplicationType application = getApplication();

      List aps = application.getAccessPoint();

      for (int i = aps.size() - 1; i >= 0; i--)
      {
         AccessPointType ap = (AccessPointType) aps.get(i);
         if (ap.getDirection().equals(DirectionType.IN_LITERAL))
         {
            aps.remove(ap);
         }
      }

      IMessageProvider provider = getSelectedProvider();
      if (provider != null)
      {
         String messageType = getSelectedMessageType();
         aps.addAll(provider.getPredefinedAccessPoints(messageType));
      }
   }

   private String getSelectedMessageType()
   {
      IStructuredSelection selection = (IStructuredSelection) messageTypeCombo
            .getSelection();
      String messageType = null;
      if (selection != null && !selection.isEmpty())
      {
         messageType = (String) selection.getFirstElement();
      }
      return messageType;
   }

   private ApplicationType getApplication()
   {
      return (ApplicationType) getElement().getAdapter(IModelElement.class);
   }

   private void providerChanged(boolean propagate)
   {
      messageTypeCombo.setInput(null);

      IMessageProvider provider = getSelectedProvider();
      if (provider != null)
      {
         Collection messageTypes = provider.getMessageTypes();
         messageTypeCombo.setInput(messageTypes);
         if (!messageTypes.isEmpty())
         {
            messageTypeCombo.setSelection(new StructuredSelection(messageTypes.iterator()
                  .next()));
         }
      }

      if (propagate)
      {
         messageTypeChanged();
      }
   }

   private Collection getProviders()
   {
      Map providers = new TreeMap();
      Map extensions = SpiExtensionRegistry.instance().getExtensions(
            CarnotConstants.MESSAGE_PROVIDERS_EXTENSION_POINT_ID);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         try
         {
            IMessageProvider provider = (IMessageProvider) config
                  .createExecutableExtension("providerClass"); //$NON-NLS-1$
            providers.put(provider.getName(), provider);
         }
         catch (CoreException e)
         {
            // e.printStackTrace();
         }
      }

      // todo: transform that into a wrapper and put name, message types,
      // runtime class name and access point provider into the SPI schema
      IMessageProvider defaultProvider = new IMessageProvider()
      {
         public String getName()
         {
            return Diagram_Messages.NAME_PROVIDER_DefaultProvider;
         }

         public Collection getMessageTypes()
         {
            return Arrays.asList(types);
         }

         public String getRuntimeClassName()
         {
            return DefaultMessageProvider.class.getName();
         }

         public Collection getPredefinedAccessPoints(String messageType)
         {
            for (int i = 1; i < 3; i++)
            {
               if (types[i].equals(messageType))
               {
                  return Collections.singletonList(getAccessPoint(classNames[i]));
               }
            }
            return Collections.EMPTY_LIST;
         }
      };
      providers.put(defaultProvider.getName(), defaultProvider);
      return providers.values();
   }

   private Object getAccessPoint(String classname)
   {
      AccessPointType ap = AccessPointUtil
            .createIntrinsicAccessPoint(
                  "content", Diagram_Messages.BASENAME_Content, classname, DirectionType.IN_LITERAL, false, null, //$NON-NLS-1$
                  ModelUtils.getDataType(getApplication(),
                        CarnotConstants.SERIALIZABLE_DATA_ID));
      ap.setElementOid(ModelUtils.getElementOid(ap, ModelUtils
            .findContainingModel(getApplication())));
      AttributeUtil.setAttribute(ap, CarnotConstants.JMS_LOCATION_ATT, JMSLocation.class
            .getName(), JMSLocation.BODY.getId());
      return ap;
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         if (manager != null)
         {
            manager.updateButtons(JmsPropertyPage.REQUEST, buttons); //$NON-NLS-1$
         }
      }
      super.setVisible(visible);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      if (manager != null)
      {
         buttons = manager.createButtons(parent);
      }
   }
}
