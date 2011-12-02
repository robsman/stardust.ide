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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateMetaTypeCommand;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.ConfigurationElementLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableManager;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.stardust.modeling.core.spi.SpiPropertyPage;
import org.eclipse.stardust.modeling.core.utils.MetaTypeModelingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPropertyPage;

import ag.carnot.base.CompareHelper;

public class ApplicationInteractivePropertyPage extends AbstractModelElementPropertyPage
{
   public static final String INTERACTIVE_ID = "_cwm_interactive_"; //$NON-NLS-1$
   public static final String INTERACTIVE_LABEL = Diagram_Messages.INTERACTIVE_LABEL;

   private HashMap newContexts = new HashMap();
   private TableManager tableManager;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      resetContent();

      ApplicationType application = (ApplicationType) node;
      ModelType model = ModelUtils.findContainingModel(application);
      Map types = collectModelContextTypes(model);
      Map items = collectSpiContextTypes();

      for (Iterator iterator = items.keySet().iterator(); iterator.hasNext();)
      {
         tableManager.addElement(items.get(iterator.next()));
      }

      for (Iterator iterator = application.getContext().iterator(); iterator.hasNext();)
      {
         ContextType contextType = (ContextType) iterator.next();
         ApplicationContextTypeType type = contextType.getType();
         if(type != null)
         {
            ConfigurationElement config = getCreate(type.getId(), items, types);
            boolean hasPage = items.containsKey(type.getId());
            tableManager.setGrayed(config, !hasPage);
            tableManager.setChecked(config, true);
            if (hasPage)
            {
               updateContexts(true, type.getId());
            }
            
         }
      }
   }

   private Map collectSpiContextTypes()
   {
      Map items = new TreeMap();
      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      Map extensions = registry.getExtensions(CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID);
      for (Iterator i = extensions.keySet().iterator(); i.hasNext();)
      {
         String id = (String) i.next();
         if (!ActivityUtil.isImplicitContext(id))
         {
            IConfigurationElement element = (IConfigurationElement) extensions.get(id);
            items.put(id, new ConfigurationElement(element));
         }
      }
      return items;
   }

   private Map collectModelContextTypes(ModelType model)
   {
      Map types = new HashMap();
      for (Iterator iterator = model.getApplicationContextType().iterator(); iterator.hasNext();)
      {
         ApplicationContextTypeType type = (ApplicationContextTypeType) iterator.next();
         types.put(type.getId(), type);
      }
      return types;
   }

   private void resetContent()
   {
      removePreferenceNodes(INTERACTIVE_ID);
      tableManager.removeAll();
   }

   private ConfigurationElement getCreate(String id, Map items, Map types)
   {
      ConfigurationElement config = (ConfigurationElement) items.get(id);
      if (config == null)
      {
         ApplicationContextTypeType type = (ApplicationContextTypeType) types.get(id);
         config = ConfigurationElement.createPageConfiguration(id, type.getName(), "", ""); //$NON-NLS-1$ //$NON-NLS-2$
         tableManager.addElement(config);
      }
      return config;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol,
         IModelElement element)
   {
      ApplicationType application = (ApplicationType) element;
      ModelType model = ModelUtils.findContainingModel(application);
      Map types = collectModelContextTypes(model);

      java.util.List contexts = application.getContext();
      for (int i = 0; i < tableManager.getRowCount(); i++)
      {
         ConfigurationElement item = (ConfigurationElement) tableManager.getElementAt(new int[] {i});
         String type = item.getAttribute(SpiConstants.ID);
         if (tableManager.getChecked(i))
         {
            ContextType ctx = (ContextType) newContexts.get(type);
            if (ctx != null)
            {
               ctx.setElementOid(ModelUtils.getElementOid(ctx, ModelUtils.findContainingModel(application)));
               contexts.add(ctx);
               if (!types.containsKey(type))
               {
                  addContextType(model, item);
               }
            }
         }
         else
         {
            for (int j = 0; j < contexts.size(); j++)
            {
               ContextType ctx = (ContextType) contexts.get(j);
               if (ctx.getType() != null && CompareHelper.areEqual(ctx.getType().getId(), type))
               {
                  contexts.remove(j);
                  break;
               }
            }
         }
      }
   }

   private void addContextType(ModelType model, ConfigurationElement config)
   {
      Map extensions = SpiExtensionRegistry.instance().getExtensions(
            CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID);
      IConfigurationElement cfgContext = (IConfigurationElement) extensions.get(config.getAttribute(SpiConstants.ID));
      CreateMetaTypeCommand cmd = MetaTypeModelingUtils.getCreateContextTypeCmd(cfgContext);
      cmd.setParent(model);
      cmd.execute();
   }

   public Control createBody(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      GridLayout compositeLayout = new GridLayout(1, false);
      composite.setLayout(compositeLayout);

      GridData gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      composite.setLayoutData(gridData);

      // TODO: use FormBuilder to create the table
      Table table = new Table(composite, SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            TableItem item = (TableItem) e.item;
            if (item != null && !item.getGrayed())
            {
               updateContexts(item.getChecked(), item.getText());
            }
         }
      });

      GridData tableGridData = new GridData();
      tableGridData.grabExcessHorizontalSpace = true;
      tableGridData.grabExcessVerticalSpace = true;
      tableGridData.horizontalAlignment = SWT.FILL;
      tableGridData.verticalAlignment = SWT.FILL;
      table.setLayoutData(tableGridData);

      tableManager = new TableManager(table);
      tableManager.setColumnSizes(new int[] {25, 75});
      tableManager.setColumnNames(new String[] {Diagram_Messages.COL_NAME_Context, Diagram_Messages.COL_NAME_Name});
      tableManager.setAttributeNames(new String[] {"id", "name"}); //$NON-NLS-1$ //$NON-NLS-2$
      tableManager.addLabelProvider(ConfigurationElementLabelProvider.instance());
      tableManager.setDefaultIcon("icons/full/obj16/application.gif"); //$NON-NLS-1$

      return composite;
   }

   private void updateContexts(boolean checked, String id)
   {
      ContextType ctx = null;
      ApplicationType application = (ApplicationType) getModelElement();
      java.util.List contexts = application.getContext();
      for (int i = 0; i < contexts.size(); i++)
      {
         ContextType contextType = (ContextType) contexts.get(i);
         if(contextType.getType() != null)
         {
            if (CompareHelper.areEqual(contextType.getType().getId(), id))
            {
               ctx = contextType;
               break;
            }            
         }
      }
      if (ctx == null)
      {
         ctx = (ContextType) newContexts.get(id);
         if (ctx == null)
         {
            ctx = CarnotWorkflowModelFactory.eINSTANCE.createContextType();
            ModelType model = (ModelType) application.eContainer();
            ApplicationContextTypeType contextType = getApplicationContextType(model, id);
            if (null == contextType)
            {
               // inject meta type into model
               Map items = collectSpiContextTypes();
               ConfigurationElement cfgContext = (ConfigurationElement) items.get(id);
               addContextType(model, cfgContext);

               contextType = getApplicationContextType(model, id);
            }
            ctx.setType(contextType);
            newContexts.put(id, ctx);
         }
      }

      String prefid = composePageId(INTERACTIVE_ID, id);
      if (checked)
      {
         if (getNode(prefid) == null)
         {
            SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
            Map extensions = registry.getExtensions(
                  CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID);
            IConfigurationElement template = (IConfigurationElement) extensions.get(id);
            final ContextType context = ctx;
            final ConfigurationElement config = new ConfigurationElement(template);
            CarnotPreferenceNode node = new CarnotPreferenceNode(config,
                  new ModelElementAdaptable(IModelElement.class, context, 
                        EditPart.class, getElement()))
            {
               public void createPage()
               {
                  try
                  {
                     IWorkbenchPropertyPage page = new SpiPropertyPage(config);
                     internalSetPage(page);
                  }
                  catch (CoreException e)
                  {
                     ErrorDialog.openError(getShell(), Diagram_Messages.ERROR_DIA_EXC, e.getMessage(),
                           e.getStatus()); //$NON-NLS-1$
                  }
               }
            };
            addNodeTo(INTERACTIVE_ID, node, null);
         }
      }
      else
      {
      removePreferenceNodes(prefid, true);
      }
      refreshTree();
   }

   private static ApplicationContextTypeType getApplicationContextType(ModelType model, String id)
   {
      return (ApplicationContextTypeType) ModelUtils.findIdentifiableElement(model,
         CarnotWorkflowModelPackage.eINSTANCE.getModelType_ApplicationContextType(), id);
   }
}