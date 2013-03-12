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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.UnbindActionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.EventHandlingUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.utils.MetaTypeModelingUtils;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


public class EventHandlingButtonController implements IButtonManager
{
   public static final int ADD_EVENT_ACTION_BUTTON = IButtonManager.BUTTON_COUNT;

   public static final int ADD_BIND_ACTION_BUTTON = ADD_EVENT_ACTION_BUTTON + 1;

   public static final int ADD_UNBIND_ACTION_BUTTON = ADD_BIND_ACTION_BUTTON + 1;

   private Object selection;

   private final AbstractModelElementPropertyPage rootPage;
   
   public EventHandlingButtonController(AbstractModelElementPropertyPage rootPage)
   {
      this.rootPage = rootPage;
   }

   public Object getSelection()
   {
      // TODO by default return the rootPage's IConfigurationElement
      return selection == null ? null : selection;
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
      for (int i = 0; i < buttons.length; i++)
      {
         if (buttons[i].isDisposed())
         {
            return;
         }
      }
      
      this.selection = selection;
      
      buttons[ADD_BUTTON].setEnabled((selection instanceof ConfigurationElement)
            || (selection instanceof EventHandlerType)
            || (selection instanceof AbstractEventAction));
      
      buttons[DELETE_BUTTON].setEnabled((null != selection)
            && !(selection instanceof EventConditionTypeType)
            && !(selection instanceof ConfigurationElement));

      EventHandlerType handler = getEventHandlerFromSelection(selection);
      if (null != handler)
      {
         EventConditionTypeType type = handler.getType();

         boolean process = handler.eContainer() instanceof ProcessDefinitionType;
         boolean activity = handler.eContainer() instanceof ActivityType;

         buttons[ADD_EVENT_ACTION_BUTTON].setEnabled( !ActionTypeUtil.getSupportedActionTypes(
               type, process, activity, "event").isEmpty()); //$NON-NLS-1$

         buttons[ADD_BIND_ACTION_BUTTON].setEnabled( !ActionTypeUtil.getSupportedActionTypes(
               type, process, activity, "bind").isEmpty()); //$NON-NLS-1$

         buttons[ADD_UNBIND_ACTION_BUTTON].setEnabled( !ActionTypeUtil.getSupportedActionTypes(
               type, process, activity, "unbind").isEmpty()); //$NON-NLS-1$
      }
      else
      {
         buttons[ADD_EVENT_ACTION_BUTTON].setEnabled(false);
         buttons[ADD_BIND_ACTION_BUTTON].setEnabled(false);
         buttons[ADD_UNBIND_ACTION_BUTTON].setEnabled(false);
      }
      buttons[UP_BUTTON].setEnabled(isMoveUpAllowed(selection));
      buttons[DOWN_BUTTON].setEnabled(isMoveDownAllowed(selection));
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[ADD_UNBIND_ACTION_BUTTON + 1];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAdd(buttons);
               }
            });

      buttons[ADD_EVENT_ACTION_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_AddEventAction,//
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAddEventAction(buttons);
               }
            });

      buttons[ADD_BIND_ACTION_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_AddBindAction,//
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAddBindAction(buttons);
               }
            });

      buttons[ADD_UNBIND_ACTION_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_AddUnbindAction,//
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAddUnbindAction(buttons);
               }
            });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_Delete,//
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDelete(buttons);
               }
            });

      buttons[UP_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_MoveUp,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performUp(buttons);
               }
            });

      buttons[DOWN_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_MoveDown,//
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDown(buttons);
               }
            });
      
      return buttons;
   }

   private void performAdd(Button[] buttons)
   {
      IConfigurationElement config = getConfigurationFromSelection();
      if (config != null)
      {
         EventHandlerType handler = createEventHandler(config);
         updateButtons(handler, buttons);
         if (AbstractModelElementPropertyPage.preselect)
         {
            rootPage.selectPageForObject(handler);
         }
      }
   }

   private void performDelete(Button[] buttons)
   {
      Object selection = getSelection();
      if (selection instanceof EventHandlerType)
      {
         EventHandlerType handler = (EventHandlerType) selection;
         getEventHandlers().remove(handler);

         updateButtons(null, buttons);
         rootPage.selectPage(EventHandlersPropertyPage.EVENT_HANDLING_ID);
      }
      else if (ActionTypeUtil.isAction(selection))
      {
         removeAction((IIdentifiableModelElement) selection);
         updateButtons(null, buttons);
         rootPage.selectPage(EventHandlersPropertyPage.EVENT_HANDLING_ID);
      }
   }

   protected void performAddEventAction(Button[] buttons)
   {
      EventHandlerType eventHandlerType = getEventHandlerFromSelection(getSelection());
      Object action = addAction(buttons, Diagram_Messages.ACT_EventAction,
            Diagram_Messages.ACT_DESC_EventAction, eventHandlerType.getEventAction(),
            CarnotWorkflowModelFactory.eINSTANCE.createEventActionType(),
            eventHandlerType);
      if (AbstractModelElementPropertyPage.preselect)
      {
         rootPage.selectPageForObject(action);
      }
   }

   protected void performAddBindAction(Button[] buttons)
   {
      EventHandlerType eventHandlerType = getEventHandlerFromSelection(getSelection());
      Object action = addAction(buttons, Diagram_Messages.ACT_BindAction,
            Diagram_Messages.ACT_DESC_BindAction, eventHandlerType.getBindAction(),
            CarnotWorkflowModelFactory.eINSTANCE.createBindActionType(), eventHandlerType);
      if (AbstractModelElementPropertyPage.preselect)
      {
         rootPage.selectPageForObject(action);
      }
   }

   protected void performAddUnbindAction(Button[] buttons)
   {
      EventHandlerType eventHandlerType = getEventHandlerFromSelection(getSelection());
      Object action = addAction(buttons, Diagram_Messages.ACT_UnbindAction,
            Diagram_Messages.ACT_DESC_UnbindAction,
            eventHandlerType.getUnbindAction(),
            CarnotWorkflowModelFactory.eINSTANCE.createUnbindActionType(),
            eventHandlerType);
      if (AbstractModelElementPropertyPage.preselect)
      {
         rootPage.selectPageForObject(action);
      }
   }

   private void performUp(Button[] buttons)
   {
      Object element = getSelection();
      List list = getContainmentList(element);
      if (list != null)
      {
         moveUp(list, element);
         updateButtons(element, buttons);
         updateButtons(element, buttons);
      }
   }
   
   private void performDown(Button[] buttons)
   {
      Object element = getSelection();
      List list = getContainmentList(element);
      if (list != null)
      {
         moveDown(list, element);
         updateButtons(element, buttons);
         updateButtons(element, buttons);
      }
   }

   private void moveUp(List list, Object element)
   {
      int index = list.indexOf(element);

      if (list instanceof EList)
      {
         ((EList) list).move(index -1, element);
      }
      else
      {
         list.remove(element);
         list.add(index - 1, element);
      }
   }
   
   private void moveDown(List list, Object element)
   {
      int index = list.indexOf(element);

      if (list instanceof EList)
      {
         ((EList) list).move(index + 1, element);
      }
      else
      {
         list.remove(element);
         list.add(index + 1, element);
      }
   }

   private List getContainmentList(Object element)
   {
      List list = null;
      if (element instanceof EventHandlerType)
      {
         list = getEventHandlers();
      }
      else if (element instanceof EventActionType)
      {
         list = ModelUtils.findContainingEventHandlerType((EObject) element)
               .getEventAction();
      }
      else if (element instanceof BindActionType)
      {
         list = ModelUtils.findContainingEventHandlerType((EObject) element)
               .getBindAction();
      }
      else if (element instanceof UnbindActionType)
      {
         list = ModelUtils.findContainingEventHandlerType((EObject) element)
               .getUnbindAction();
      }
      return list;
   }

   private List getEventHandlers()
   {
      IModelElement model = (IModelElement) rootPage.getModelElement();
      if (model instanceof EventHandlerType)
      {
         return Collections.singletonList(model);
      }
      else if (model instanceof IEventHandlerOwner)
      {
         return ((IEventHandlerOwner) model).getEventHandler();
      }
      return null;
   }

   private Object addAction(Button[] buttons, String id, String name, List list,
         IIdentifiableModelElement actionType, EventHandlerType eventHandlerType)
   {
      IdFactory idFactory = new IdFactory(id, name);
      idFactory.computeNames(list);

      actionType.setId(idFactory.getId());
      actionType.setName(idFactory.getName());

      ActionTypePropertyPage.setActionType(ModelUtils.findContainingModel(eventHandlerType),
    		actionType, ActionTypeUtil.findFirstActionType(eventHandlerType,
    		ActionTypeUtil.getContext(actionType))); //$NON-NLS-1$

      list.add(actionType);

      updateButtons(actionType, buttons);

      return actionType;
   }

   private EventHandlerType getEventHandlerFromSelection(Object selection)
   {
      return (selection instanceof EObject)
            ? ModelUtils.findContainingEventHandlerType((EObject) selection)
            : null;
   }

   private EventHandlerType createEventHandler(IConfigurationElement config)
   {
      ModelType model = ModelUtils.findContainingModel((IModelElement) rootPage.getElement()
            .getAdapter(IModelElement.class));

      EventConditionTypeType conditionType = EventHandlingUtils.findConfiguredEventConditionType(
            model, config.getAttribute(SpiConstants.ID), true, true);

      if (null == conditionType)
      {
         // add type declaration if necessary
         CreateMetaTypeCommand cmdAddConditionType = MetaTypeModelingUtils.getCreateEventConditionTypeCmd(config);
         cmdAddConditionType.setParent(model);
         cmdAddConditionType.execute();

         conditionType = EventHandlingUtils.findConfiguredEventConditionType(model,
               config.getAttribute(SpiConstants.ID), true, true);
      }
      
      IdFactory idFactory = new IdFactory(conditionType.getId(), conditionType.getName());
      idFactory.computeNames(getEventHandlers());

      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      EventHandlerType handler = factory.createEventHandlerType();
      handler.setId(idFactory.getId());
      handler.setName(idFactory.getName());
      handler.setType(conditionType);

      getEventHandlers().add(handler);

      return handler;
   }

   private void removeAction(IIdentifiableModelElement action)
   {
      List list = getContainmentList(action);
      if (null != list)
      {
         list.remove(action);
      }
   }

   private boolean isMoveUpAllowed(Object element)
   {
      // TODO
      List list = getContainmentList(element);
      return list == null ? false : list.indexOf(element) > 0;
   }
   
   private boolean isMoveDownAllowed(Object element)
   {
      // TODO
      List list = getContainmentList(element);
      return list == null ? false : list.indexOf(element) < list.size() - 1;
   }

   private IConfigurationElement getConfigurationFromSelection()
   {
      Object selection = getSelection();

      IConfigurationElement config = null;
      if (ActionTypeUtil.isAction(selection))
      {
         selection = ((AbstractEventAction) selection).eContainer();
      }
      if (selection instanceof EventHandlerType)
      {
         config = (IConfigurationElement) EventHandlingUtils.findInstalledEventConditionTypes(
               rootPage.getModelElement() instanceof ActivityType,
               rootPage.getModelElement() instanceof ProcessDefinitionType)
               .get(((EventHandlerType) selection).getType().getId());
      }
      else if (selection instanceof ConfigurationElement)
      {
         config = (IConfigurationElement) EventHandlingUtils.findInstalledEventConditionTypes(
               rootPage.getModelElement() instanceof ActivityType,
               rootPage.getModelElement() instanceof ProcessDefinitionType)
               .get(((ConfigurationElement) selection).getAttribute(SpiConstants.ID));
      }
      return config;
   }
}