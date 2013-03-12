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
package org.eclipse.stardust.modeling.core.spi.triggerTypes.jms;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageAcceptor;
import org.eclipse.stardust.engine.extensions.jms.app.JMSLocation;
import org.eclipse.stardust.engine.extensions.jms.app.MessageType;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IMessageAcceptor;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.DefaultOutlineProvider;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.properties.ModelElementAdaptable;
import org.eclipse.stardust.modeling.core.properties.ModelElementsOutlineSynchronizer;
import org.eclipse.stardust.modeling.core.properties.ModelElementsTableContentProvider;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.spi.triggerTypes.ParameterMappingTablePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

public class JmsPropertyPage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   public static final String PARAMETER_MAPPING_TABLE_ID = ParameterMappingTablePage.PARAMETER_MAPPING_TABLE_ID;

   private static final String PARAMETER_MAPPING_TABLE_LABEL = Diagram_Messages.PARAMETER_MAPPING_TABLE_LABEL;

   private static final String MESSAGE_ACCEPTOR = PredefinedConstants.MESSAGE_ACCEPTOR_PROPERTY;

   private static final String MESSAGE_TYPE_NAME_ATT = PredefinedConstants.MESSAGE_TYPE_ATT;

   private static final String MESSAGE_TYPE_ATT = MessageType.class.getName();

   private static final String[] types = {
         MessageType.MAP.getId(), MessageType.OBJECT.getId(), MessageType.TEXT.getId(),
         MessageType.STREAM.getId()};

   private static final String[] classNames = {
         null, Object.class.getName(), String.class.getName(), null};

   private static final int[] elementFeatureIds = {
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__ID,
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__NAME,
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__DATA,
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__DATA_PATH};

   private static final String[] labelProperties = {"name", "value"}; //$NON-NLS-1$ //$NON-NLS-2$

   private static final String JMS_LOCATION_ATT = PredefinedConstants.JMS_LOCATION_PROPERTY;

   private Button[] buttons;

   private Object selection;

   private EObjectLabelProvider labelProvider;

   private TableViewer viewer;

   private ModelElementsOutlineSynchronizer outlineSynchronizer;

   private ComboViewer messageTypeCombo;

   private ComboViewer acceptorCombo;

   public void dispose()
   {
      outlineSynchronizer.dispose();
      super.dispose();
   }

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      Collection acceptors = getAcceptors();
      acceptorCombo.setInput(acceptors);

      String acceptorClass = AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, MESSAGE_ACCEPTOR);
      IMessageAcceptor acceptor = findAcceptor(acceptors, acceptorClass);
      acceptorCombo.setSelection(acceptor == null ? null : new StructuredSelection(
            acceptor));

      String messageType = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            MESSAGE_TYPE_NAME_ATT);
      messageTypeCombo.setSelection(new StructuredSelection((null == messageType)
            ? types[0]
            : messageType));

      resetContent();
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

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

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

   private Collection getAcceptors()
   {
      // todo: see comment at RequestPropertyPage
      Map acceptors = new TreeMap();

      Map extensions = SpiExtensionRegistry.instance().getExtensions(
            CarnotConstants.TRIGGER_MESSAGE_ACCEPTORS_EXTENSION_POINT_ID);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         try
         {
            IMessageAcceptor acceptor = (IMessageAcceptor) config
                  .createExecutableExtension(SpiConstants.MSG_ACCEPTOR_ACCEPTOR_CLASS);
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
      AccessPointType ap = AccessPointUtil.createIntrinsicAccessPoint("content", //$NON-NLS-1$
            Diagram_Messages.NAME_ACCESSPOINT_Content, classname, DirectionType.OUT_LITERAL,
            true, null, ModelUtils.getDataType(getTrigger(),
                  CarnotConstants.SERIALIZABLE_DATA_ID));
      AttributeUtil.setAttribute(ap, CarnotConstants.JMS_LOCATION_ATT, JMSLocation.class
            .getName(), JMSLocation.BODY.getId());
      return ap;
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

      acceptorCombo.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            acceptorChanged();
         }
      });

      messageTypeCombo.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            messageTypeChanged();
         }
      });

      Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData(2));
      FormBuilder.applyDefaultTextControlWidth(table);
      table.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateButtons(getSelectedItem(), buttons);
         }
      });
      table.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if (selection instanceof AccessPointType)
            {
               selectPageForObject(selection);
            }
         }
      });

      viewer = new TableViewer(table);
      TableUtil.createColumns(table, new String[] {
    		  Diagram_Messages.COL_NAME_Name, Diagram_Messages.COL_NAME_Location});
      TableUtil.setInitialColumnSizes(table, new int[] {70, 29});
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public String getText(String name, Object element)
         {
            if (name.equals("value") && element instanceof AccessPointType) //$NON-NLS-1$
            {
               return AttributeUtil.getAttributeValue((AccessPointType) element,
                     CarnotConstants.JMS_LOCATION_ATT);
            }
            return super.getText(name, element);
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider, labelProperties);
      ModelElementsTableContentProvider provider = new ModelElementsTableContentProvider(
            CarnotWorkflowModelPackage.eINSTANCE.getIAccessPointOwner_AccessPoint(),
            elementFeatureIds, labelProperties);
      provider
            .setLabelChangingAttributes(new String[] {CarnotConstants.JMS_LOCATION_ATT});
      viewer.setContentProvider(provider);

      outlineSynchronizer = new ModelElementsOutlineSynchronizer(
            new DefaultOutlineProvider(this, CarnotWorkflowModelPackage.eINSTANCE
                  .getIAccessPointOwner_AccessPoint(),
                  CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
                  CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name(),
                  getParentNodeId(), JMSTriggerAccessPointPropertyPage.class.getName())
            {
               protected List retrievePagesFor(ModelElementAdaptable adaptable)
               {
                  // HACK rsauer: injecting param property pages although not listed as
                  // extension

                  IModelElement accessPoint = (IModelElement) adaptable.getAdapter(IModelElement.class);
                  if (accessPoint instanceof AccessPointType)
                  {
                     CarnotPreferenceNode node = new CarnotPreferenceNode(
                           createPageConfiguration(accessPoint), adaptable,
                           SpiConstants.PROPERTY_PAGE_CLASS, 0);

                     return Collections.singletonList(node);
                  }
                  else
                  {
                     return super.retrievePagesFor(adaptable);
                  }
               }
            });
      
      addModelElementsOutlineSynchronizer(outlineSynchronizer);

      addParameterMappingTablePage();

      return composite;
   }

   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(parent);
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
      this.selection = selection;

      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i].isDisposed())
         {
            return;
         }
      }
      buttons[ADD_BUTTON].setEnabled(true);
      buttons[DELETE_BUTTON].setEnabled(selection instanceof AccessPointType);
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[DELETE_BUTTON + 1];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAdd(buttons);
               }
            });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Delete,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDelete(buttons);
               }
            });

      return buttons;
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
      }
      super.setVisible(visible);
   }

   public Object getSelection()
   {
      return selection == null ? getSelectedItem() : selection;
   }

   private void performDelete(Button[] buttons)
   {
      AccessPointType ap = (AccessPointType) getSelection();
      if (ap != null)
      {
         TriggerType triggerType = getTrigger();
         triggerType.getAccessPoint().remove(ap);
         updateButtons(null, buttons);
         selectPage(getParentNodeId());
      }
   }

   private void performAdd(Button[] buttons)
   {
      TriggerType triggerType = getTrigger();
      IMessageAcceptor acceptor = getSelectedAcceptor();
      String messageType = getSelectedMessageType();
      acceptor.getPredefinedAccessPoints(messageType);
      java.util.List points = triggerType.getAccessPoint();
      IdFactory factory = new IdFactory("out", Diagram_Messages.BASENAME_OutAccessPoint); //$NON-NLS-1$
      factory.computeNames(points);
      AccessPointType ap = AccessPointUtil.createIntrinsicAccessPoint(factory.getId(),
            factory.getName(), null, DirectionType.OUT_LITERAL, true, null, ModelUtils
                  .getDataType(getTrigger(), CarnotConstants.SERIALIZABLE_DATA_ID));

      triggerType.getAccessPoint().add(ap);
      if (preselect)
      {
         selectPageForObject(ap);
      }
   }

   private void addParameterMappingTablePage()
   {
      CarnotPreferenceNode node = new CarnotPreferenceNode(ConfigurationElement
            .createPageConfiguration(PARAMETER_MAPPING_TABLE_ID,
                  PARAMETER_MAPPING_TABLE_LABEL,
                  /* template.getAttribute("icon") */null,
                  ParameterMappingTablePage.class.getName()), getElement(),
            CarnotPreferenceNode.INSERTION_ORDER);

      addNodeTo(null, node, null);
      refreshTree();
   }

   private void acceptorChanged()
   {
      TriggerType trigger = getTrigger();

      String currentMessageType = getSelectedMessageType();
      if (StringUtils.isEmpty(currentMessageType))
      {
         currentMessageType = AttributeUtil.getAttributeValue(trigger,
               MESSAGE_TYPE_NAME_ATT);
      }

      IMessageAcceptor acceptor = getSelectedAcceptor();
      AttributeUtil.setAttribute(trigger, MESSAGE_ACCEPTOR, (null == acceptor)
            ? null
            : acceptor.getRuntimeClassName());

      IMessageAcceptor provider = getSelectedAcceptor();
      if (provider != null)
      {
         Collection messageTypes = provider.getMessageTypes();
         messageTypeCombo.setInput(messageTypes);

         if (!messageTypes.isEmpty())
         {
            messageTypeCombo.setSelection(new StructuredSelection((messageTypes
                  .contains(currentMessageType)) ? currentMessageType : messageTypes
                  .iterator().next()));
         }
      }
      else
      {
         messageTypeCombo.setInput(null);
      }

      messageTypeChanged();
   }

   private void messageTypeChanged()
   {
      TriggerType trigger = getTrigger();

      String configuredMessageType = AttributeUtil.getAttributeValue(trigger,
            MESSAGE_TYPE_NAME_ATT);

      String currentMessageType = getSelectedMessageType();

      if (!CompareHelper.areEqual(configuredMessageType, currentMessageType))
      {
         AttributeUtil.setAttribute(trigger, MESSAGE_TYPE_NAME_ATT, MESSAGE_TYPE_ATT,
               currentMessageType);

         List aps = trigger.getAccessPoint();

         for (int i = aps.size() - 1; i >= 0; i--)
         {
            AccessPointType ap = (AccessPointType) aps.get(i);
            String locationValue = AttributeUtil.getAttributeValue(ap, JMS_LOCATION_ATT);
            if (JMSLocation.BODY.getId().equals(locationValue))
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

         resetContent();
      }
   }

   private TriggerType getTrigger()
   {
      Object element = getElement();
      if (element instanceof EditPart)
      {
         element = ((EditPart) element).getModel();
      }
      if (element instanceof IModelElementNodeSymbol)
      {
         element = ((IModelElementNodeSymbol) element).getModelElement();
      }
      return element instanceof TriggerType ? (TriggerType) element : null;
   }

   private void resetContent()
   {
      TriggerType element = getTrigger();
      viewer.setInput(element);
      outlineSynchronizer.init(element);
      updateButtons(null, buttons);
      expandTree();
   }

   private String getParentNodeId()
   {
      return getTrigger().getType().getId();
   }
}