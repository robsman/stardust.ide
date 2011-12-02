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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.EventHandlingUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPropertyPageContributor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.ui.IWorkbenchPropertyPage;


/**
 * @author rsauer
 * @version $Revision$
 */
public class EventHandlingOutlineSynchronizer extends AbstractEventHandlingStructureSynchronizer
{
   private final AbstractModelElementPropertyPage rootPage;

   private EObjectLabelProvider defaultProvider;

   private Map spiTypes;

   private Map modelTypes;

   // todo: (fh) refactor based on the model of the EventHandlingTableContentProvider
   public EventHandlingOutlineSynchronizer(AbstractModelElementPropertyPage rootPage,
         IEventHandlerOwner target)
   {
      super(target);
      
      this.rootPage = rootPage;
      defaultProvider = new EObjectLabelProvider(rootPage.getEditor());
   }

   public AbstractModelElementPropertyPage getRootPage()
   {
      return rootPage;
   }

   public void init()
   {
      if (null != getEventHandlerOwner())
      {
         ModelType model = ModelUtils.findContainingModel(getEventHandlerOwner());
         this.modelTypes = (null != model)
               ? collectModelTypes(model)
               : Collections.EMPTY_MAP;
         this.spiTypes = collectSpiTypes();
      }
      
      super.init();
   }

   public void updateOutline(Object hint)
   {
      super.updateOutline(hint);
      
      getRootPage().refreshTree();
   }
   
   protected void synchronizeConditionTypes()
   {
      CarnotPreferenceNode parentNode = (CarnotPreferenceNode) rootPage.getPreferenceManager().find(
            EventHandlersPropertyPage.EVENT_HANDLING_ID);

      HashSet existingIds = new HashSet();
      IPreferenceNode[] subNodes = parentNode.getSubNodes();
      
      // delete nodes without a spi correspondent, although should not be necessary
      for (int i = 0; i < subNodes.length; i++)
      {
         if (spiTypes.containsKey(subNodes[i].getId()))
         {
            existingIds.add(subNodes[i].getId());
         }
         else
         {
            rootPage.removePreferenceNode(parentNode, (CarnotPreferenceNode) subNodes[i]);
         }
      }

      // add and / or synchronize nodes
      for (Iterator i = spiTypes.values().iterator(); i.hasNext();)
      {
         IConfigurationElement conditionTypeConfig = (IConfigurationElement) i.next();
         String id = conditionTypeConfig.getAttribute(SpiConstants.ID);
         if (!existingIds.contains(id))
         {
            addConditionTypeNode(conditionTypeConfig);
         }
         synchronizeEventHandlers(id);
      }

      // TODO (fh) we should show condition types that are defined in the model but does
      // not have a spi extension
/*      for (Iterator iterator = modelTypes.keySet().iterator(); iterator.hasNext();)
      {
         String id = (String) iterator.next();
         if ( !spiTypes.containsKey(id))
         {
            EventConditionTypeType condition = (EventConditionTypeType) modelTypes.get(id);
            PreferenceNode node = addConditionTypeNode(condition);
            tableManager.setGrayed(condition, true);
         }
      }*/
   }
   
   protected void synchronizeEventHandlers(String conditionTypeId)
   {
      CarnotPreferenceNode parentNode = (CarnotPreferenceNode) 
         rootPage.getPreferenceManager().find(getConditionTypeNodeId(conditionTypeId));
      
      IPreferenceNode[] subNodes = parentNode.getSubNodes();
      
      // map all nodes
      HashMap existingNodes = new HashMap();
      for (int i = 0; i < subNodes.length; i++)
      {
         existingNodes.put(subNodes[i].getId(), subNodes[i]);
      }

      List handlers = ModelUtils.findMetaTypeInstances(
            getEventHandlerOwner().getEventHandler(), conditionTypeId);
      
      // add and / or synchronize event handlers
      for (int i = 0; i < handlers.size(); i++)
      {
         EventHandlerType handler = (EventHandlerType) handlers.get(i);
         String id = handler.getId();
         CarnotPreferenceNode node = (CarnotPreferenceNode) existingNodes.get(id);
         if (node == null)
         {
            node = addHandlerNode(handler);
         }
         else
         {
            existingNodes.remove(id);
         }
         node.setSortOrder(i);
         synchronizeEventActions(handler);
      }
      
      // remove nodes that does not have a coresponding event handler
      for (Iterator i = existingNodes.values().iterator(); i.hasNext();)
      {
         rootPage.removePreferenceNode(parentNode, (CarnotPreferenceNode) i.next());
      }
   }

   protected void synchronizeEventActions(EventHandlerType handler)
   {
      CarnotPreferenceNode parentNode = (CarnotPreferenceNode) 
         rootPage.getPreferenceManager().find(getHandlerNodeId(handler));
   
      IPreferenceNode[] subNodes = parentNode.getSubNodes();
      
      // collect existing nodes
      HashMap existingActions = new HashMap();
      int[] index = {0};
      for (int i = 0; i < subNodes.length; i++)
      {
         IModelElement element = (IModelElement) ((CarnotPreferenceNode) subNodes[i])
            .getAdaptable().getAdapter(IModelElement.class);
         if (element instanceof AbstractEventAction)
         {
            existingActions.put(element, subNodes[i]);
         }
         else
         {
            ((CarnotPreferenceNode) subNodes[i]).setSortOrder(index[0]++);
         }
      }

      // add and / or synchronize event actions
      syncActionNodes(handler.getBindAction(), existingActions, index);
      syncActionNodes(handler.getEventAction(), existingActions, index);
      syncActionNodes(handler.getUnbindAction(), existingActions, index);

      // remove nodes that does not have a coresponding event handler
      for (Iterator i = existingActions.values().iterator(); i.hasNext();)
      {
         rootPage.removePreferenceNode(parentNode, (CarnotPreferenceNode) i.next());
      }
   }

   private void syncActionNodes(List actionList, HashMap existingActions, int[] index)
   {
      for (Iterator i = actionList.iterator(); i.hasNext();)
      {
         AbstractEventAction action = (AbstractEventAction) i.next();
         CarnotPreferenceNode node = (CarnotPreferenceNode) existingActions.get(action);
         if (node == null)
         {
            node = addActionNode(action);
         }
         else
         {
            existingActions.remove(action);
         }
         node.setSortOrder(index[0]++);
      }
   }

   private PreferenceNode addConditionTypeNode(IConfigurationElement template)
   {
      final ConfigurationElement config = ConfigurationElement.createPageConfiguration(
            template.getAttribute(SpiConstants.ID),
            template.getAttribute(SpiConstants.NAME),
            SpiExtensionRegistry.encodeExtensionIcon(template),
            EventHandlersPropertyPage.class.toString());

      final EventHandlingButtonController buttonController = new EventHandlingButtonController(
            rootPage);

      CarnotPreferenceNode node = new CarnotPreferenceNode(config,
            new ModelElementAdaptable(new Class[] {IButtonManager.class},
                  new Object[] {buttonController},
                  rootPage.getElement()))
      {
         public void createPage()
         {
            IWorkbenchPropertyPage page = new EventHandlersPropertyPage(
                  config, buttonController);
            internalSetPage(page);
         }
      };
      return rootPage.addNodeTo(EventHandlersPropertyPage.EVENT_HANDLING_ID, node,
            null);
   }

   private CarnotPreferenceNode addHandlerNode(EventHandlerType handler)
   {
      IConfigurationElement conditionTypeConfig = (IConfigurationElement) spiTypes.get(handler.getMetaType()
            .getId());
      if (null == conditionTypeConfig)
      {
         conditionTypeConfig = (IConfigurationElement) modelTypes.get(handler.getMetaType()
               .getId());
      }
      
      final ConfigurationElement config = ConfigurationElement.createPageConfiguration(
            handler.getId(), handler.getName(),
            SpiExtensionRegistry.encodeExtensionIcon(conditionTypeConfig),
            conditionTypeConfig.getAttribute(SpiConstants.PROPERTY_PAGE_CLASS));
      
      IButtonManager buttonController = null;
      
      IPreferencePage page = rootPage.getPage(getConditionTypeNodeId(handler.getType()));
      if (page instanceof EventHandlersPropertyPage)
      {
         buttonController = ((EventHandlersPropertyPage) page).getButtonController();
      }
      
      ModelElementAdaptable adaptable = new ModelElementAdaptable(
         new Class[] {
            IButtonManager.class, IModelElement.class,
            IModelElementNodeSymbol.class},
         new Object[] {
            (null != buttonController) ? buttonController : (Object) rootPage,
            handler},
         rootPage.getElement()
      );
      
      // TODO: bad coding!!! The page should come from the spi declaration
      CarnotPreferenceNode node = new CarnotPreferenceNode(config, adaptable)
      {
         public void createPage()
         {
            try
            {
               IWorkbenchPropertyPage page = new ConditionPropertyPage(config);
               internalSetPage(page);
            }
            catch (CoreException e)
            {
               ErrorDialog.openError(rootPage.getShell(), Diagram_Messages.ERROR_DIA_EXC, e.getMessage(),
                     e.getStatus()); //$NON-NLS-1$
            }
         }
      };
      
      CarnotPropertyPageContributor contributor = CarnotPropertyPageContributor.instance();
      List nodes = contributor.contributePropertyPages(adaptable);
      if (nodes.size() == 1)
      {
         CarnotPreferenceNode root = (CarnotPreferenceNode) nodes.get(0);
         IPreferenceNode[] subnodes = root.getSubNodes();
         for (int i = 0; i < subnodes.length; i++)
         {
            root.remove(subnodes[i]);
            node.add(subnodes[i]);
         }
      }

      return rootPage.addNodeTo(getConditionTypeNodeId(handler.getMetaType().getId()),
            node, defaultProvider);
   }

   private WorkflowModelEditor getEditor()
   {
      return getRootPage().getEditor();
   }

   private CarnotPreferenceNode addActionNode(AbstractEventAction action)
   {
      EventHandlerType handler = ModelUtils.findContainingEventHandlerType(action);

      ConfigurationElement config = ConfigurationElement.createPageConfiguration(
            action.getId(), action.getName(),
            getEditor().getIconFactory().getIconFor(action),
            ActionTypePropertyPage.class.getName());

      IButtonManager buttonController = null;
      
      IPreferencePage page = rootPage.getPage(getConditionTypeNodeId(handler.getType()));
      if (page instanceof EventHandlersPropertyPage)
      {
         buttonController = ((EventHandlersPropertyPage) page).getButtonController();
      }
      
      CarnotPreferenceNode node = new CarnotPreferenceNode(config,
            new ModelElementAdaptable(
               new Class[] {
                  IButtonManager.class, IModelElement.class, IModelElementNodeSymbol.class},
               new Object[] {
                  (null != buttonController) ? buttonController : (Object) rootPage,
                  action},
               rootPage.getElement()
            ), CarnotPreferenceNode.INSERTION_ORDER);
      return rootPage.addNodeTo(getHandlerNodeId(handler), node, defaultProvider);
   }

   private String getConditionTypeNodeId(EventConditionTypeType conditionType)
   {
      return getConditionTypeNodeId(conditionType.getId());
   }

   private String getConditionTypeNodeId(String conditionTypeId)
   {
      return ModelElementPropertyDialog.composePageId(EventHandlersPropertyPage.EVENT_HANDLING_ID,
            conditionTypeId);
   }

   private String getHandlerNodeId(EventHandlerType handler)
   {
      if (null != handler)
      {
         IMetaType conditionType = handler.getMetaType();
         
         return ModelElementPropertyDialog.composePageId(
        	getConditionTypeNodeId(conditionType.getId()), handler.getId());
      }
      else
      {
         return null;
      }
   }

   private Map collectSpiTypes()
   {
      return EventHandlingUtils.findInstalledEventConditionTypes(
            PKG_CWM.getActivityType().isInstance(getEventHandlerOwner()),
            PKG_CWM.getProcessDefinitionType().isInstance(getEventHandlerOwner()));
   }

   private Map collectModelTypes(ModelType model)
   {
      return EventHandlingUtils.findConfiguredEventConditionTypes(model,
            PKG_CWM.getActivityType().isInstance(getEventHandlerOwner()),
            PKG_CWM.getProcessDefinitionType().isInstance(getEventHandlerOwner()));
   }

}
