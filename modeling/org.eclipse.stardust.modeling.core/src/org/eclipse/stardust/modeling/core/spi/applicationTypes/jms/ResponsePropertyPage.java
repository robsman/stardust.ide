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
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IMessageAcceptor;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.spi.providers.applications.jms.DefaultMessageAcceptor;
import ag.carnot.workflow.spi.providers.applications.jms.JMSLocation;
import ag.carnot.workflow.spi.providers.applications.jms.MessageType;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ResponsePropertyPage extends AbstractModelElementPropertyPage
{
   private static final String MESSAGE_ACCEPTOR = PredefinedConstants.MESSAGE_ACCEPTOR_PROPERTY;

   private static final String MESSAGE_TYPE = PredefinedConstants.RESPONSE_MESSAGE_TYPE_PROPERTY;

   private static final String[] types = {
         MessageType.MAP.getId(), MessageType.OBJECT.getId(), MessageType.TEXT.getId(),
         MessageType.STREAM.getId()};

   private static final String[] classNames = {
         null, Object.class.getName(), String.class.getName(), null};

   private ComboViewer messageTypeCombo;

   private ComboViewer acceptorCombo;

   private Button[] buttons;

   private ISelectionChangedListener acceptorListener = new ISelectionChangedListener()
   {
      public void selectionChanged(SelectionChangedEvent event)
      {
         acceptorChanged(true);
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
      acceptorCombo.removeSelectionChangedListener(acceptorListener);
      messageTypeCombo.removeSelectionChangedListener(messageListener);

      ApplicationType application = (ApplicationType) element;

      Collection acceptors = getAcceptors();

      String acceptorClass = AttributeUtil.getAttributeValue(application,
            MESSAGE_ACCEPTOR);
      acceptorCombo.setInput(acceptors);
      IMessageAcceptor acceptor = findAcceptor(acceptors, acceptorClass);
      acceptorCombo.setSelection(acceptor == null ? null : new StructuredSelection(
            acceptor));

      acceptorChanged(false);

      String messageType = AttributeUtil.getAttributeValue(application, MESSAGE_TYPE);
      messageTypeCombo.setSelection(new StructuredSelection((null == messageType)
            ? types[0]
            : messageType));

      acceptorCombo.addSelectionChangedListener(acceptorListener);
      messageTypeCombo.addSelectionChangedListener(messageListener);
   }

   private IMessageAcceptor findAcceptor(Collection acceptors, String acceptorClass)
   {
      for (Iterator iterator = acceptors.iterator(); iterator.hasNext();)
      {
         IMessageAcceptor msgAcceptor = (IMessageAcceptor) iterator.next();
         if (msgAcceptor.getRuntimeClassName().equals(acceptorClass))
         {
            return msgAcceptor;
         }
      }
      return (IMessageAcceptor) (acceptorClass == null && acceptors.size() == 1
            ? acceptors.iterator().next()
            : null);
   }

   private Collection getAcceptors()
   {
      // todo: see comment at RequestPropertyPage
      Map acceptors = new TreeMap();
      Map extensions = SpiExtensionRegistry.instance().getExtensions(
            CarnotConstants.MESSAGE_ACCEPTORS_EXTENSION_POINT_ID);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         try
         {
            IMessageAcceptor acceptor = (IMessageAcceptor) config
                  .createExecutableExtension("acceptorClass"); //$NON-NLS-1$
            acceptors.put(acceptor.getName(), acceptor);
         }
         catch (CoreException e)
         {
            // e.printStackTrace();
         }
      }

      IMessageAcceptor defaultAcceptor = new IMessageAcceptor()
      {
         public String getName()
         {
            return Diagram_Messages.NAME_ACCEPTOR_Default;
         }

         public Collection getMessageTypes()
         {
            return Arrays.asList(types);
         }

         public String getRuntimeClassName()
         {
            return DefaultMessageAcceptor.class.getName();
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
      acceptors.put(defaultAcceptor.getName(), defaultAcceptor);
      return acceptors.values();
   }

   private Object getAccessPoint(String classname)
   {
      AccessPointType ap = AccessPointUtil
            .createIntrinsicAccessPoint(
                  "content", Diagram_Messages.BASENAME_Content, classname, DirectionType.OUT_LITERAL, true, null, //$NON-NLS-1$
                  ModelUtils.getDataType(getApplication(),
                        CarnotConstants.SERIALIZABLE_DATA_ID));
      ap.setElementOid(ModelUtils.getElementOid(ap, ModelUtils
            .findContainingModel(getApplication())));
      AttributeUtil.setAttribute(ap, CarnotConstants.JMS_LOCATION_ATT, JMSLocation.class
            .getName(), JMSLocation.BODY.getId());
      return ap;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ApplicationType application = (ApplicationType) element;

      IMessageAcceptor acceptor = getSelectedAcceptor();
      AttributeUtil.setAttribute(application, MESSAGE_ACCEPTOR, acceptor == null
            ? null
            : acceptor.getRuntimeClassName());

      AttributeUtil.setAttribute(application, MESSAGE_TYPE, MessageType.class.getName(),
            getSelectedMessageType());
   }

   private IMessageAcceptor getSelectedAcceptor()
   {
      IStructuredSelection selection = (IStructuredSelection) acceptorCombo
            .getSelection();
      IMessageAcceptor acceptor = null;
      if (selection != null && !selection.isEmpty())
      {
         acceptor = (IMessageAcceptor) selection.getFirstElement();
      }
      return acceptor;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_MessageAcceptor);
      acceptorCombo = new ComboViewer(FormBuilder.createCombo(composite));
      acceptorCombo.setSorter(new ViewerSorter());
      acceptorCombo.setContentProvider(new ArrayContentProvider());
      acceptorCombo.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            return ((IMessageAcceptor) element).getName();
         }
      });

      FormBuilder.createLabel(composite, Diagram_Messages.LB_MessageType);
      messageTypeCombo = new ComboViewer(FormBuilder.createCombo(composite));
      messageTypeCombo.setSorter(new ViewerSorter());
      messageTypeCombo.setContentProvider(new ArrayContentProvider());
      return composite;
   }

   private void messageTypeChanged()
   {
      ApplicationType application = getApplication();

      List aps = application.getAccessPoint();

      for (int i = aps.size() - 1; i >= 0; i--)
      {
         AccessPointType ap = (AccessPointType) aps.get(i);
         if (ap.getDirection().equals(DirectionType.OUT_LITERAL))
         {
            aps.remove(ap);
         }
      }

      IMessageAcceptor acceptor = getSelectedAcceptor();
      if (acceptor != null)
      {
         String messageType = getSelectedMessageType();
         aps.addAll(acceptor.getPredefinedAccessPoints(messageType));
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

   private void acceptorChanged(boolean propagate)
   {
      messageTypeCombo.setInput(null);

      IMessageAcceptor provider = getSelectedAcceptor();
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

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         if (manager != null)
         {
            manager.updateButtons(JmsPropertyPage.RESPONSE, buttons);
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
