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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;


public class EventHandlersPropertyPage extends AbstractModelElementPropertyPage
   implements IButtonManager
{
   public static final String EVENT_HANDLING_ID = "_cwm_eventhandling_"; //$NON-NLS-1$
   
   public static final int ADD_EVENT_ACTION_BUTTON = IButtonManager.BUTTON_COUNT;
   public static final int ADD_BIND_ACTION_BUTTON = ADD_EVENT_ACTION_BUTTON + 1;
   public static final int ADD_UNBIND_ACTION_BUTTON = ADD_BIND_ACTION_BUTTON + 1;
   
   private final String conditionTypeId;
   private final ConfigurationElement config;
   
   private final IButtonManager buttonController;

   private Button[] buttons;
   private Object selection;
   private TreeViewer viewer;

   public EventHandlersPropertyPage(ConfigurationElement config,
         IButtonManager buttonController)
   {
      this.config = config;
      this.buttonController = buttonController;
      
      this.conditionTypeId = config.getAttribute(SpiConstants.ID);
   }

   public IButtonManager getButtonController()
   {
      return buttonController;
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      viewer.setInput(element);
      updateButtons(null, buttons);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public void contributeVerticalButtons(Composite parent)
   {
      buttons = getButtonController().createButtons(parent);
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         Object item = getSelectedItem();
         updateButtons(null != item ? item : config, buttons);
      }
      super.setVisible(visible);
   }

   public Object getSelection()
   {
      return selection == null ? getSelectedItem() : selection;
   }

   public void updateButtons(Object selection, Button[] buttons)
   {
      this.selection = selection;

      getButtonController().updateButtons(selection, buttons);
   }

   public Button[] createButtons(Composite parent)
   {
      return getButtonController().createButtons(parent);
   }

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
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

      Tree tree = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      GridData treeGridData = new GridData();
      treeGridData.grabExcessHorizontalSpace = true;
      treeGridData.grabExcessVerticalSpace = true;
      treeGridData.horizontalAlignment = SWT.FILL;
      treeGridData.verticalAlignment = SWT.FILL;
      tree.setLayoutData(treeGridData);
//      tree.setLinesVisible(true);
      tree.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            updateButtons(getSelectedItem(), buttons);
         }
      });

      tree.addMouseListener(new MouseAdapter()
      {
         public void mouseDoubleClick(MouseEvent e)
         {
            Object selection = getSelectedItem();
            if ((selection instanceof EventHandlerType)
                  || ActionTypeUtil.isAction(selection))
            {
               selectPageForObject(selection);
            }
         }
      });
      
      viewer = new TreeViewer(tree);
      TableUtil.createColumns(tree, new String[] {
            Diagram_Messages.COL_NAME_Id,
            Diagram_Messages.COL_NAME_Name,
            Diagram_Messages.COL_NAME_Description});
      TableUtil.setInitialColumnSizes(tree, new int[] {35, 35, 30});

      TableLabelProvider provider = new EObjectLabelProvider(getEditor())
      {
         public String getText(String name, Object element)
         {
            if (name.equals("computed")) //$NON-NLS-1$
            {
               if (element instanceof EventHandlerType)
               {
                  EventHandlerType handler = (EventHandlerType) element;
                  StringBuffer sb = new StringBuffer();
                  boolean added = false;
                  added = addDescription(sb, handler.isAutoBind(), Diagram_Messages.DESC_autobound, added);
                  added = addDescription(sb, handler.isLogHandler(), Diagram_Messages.DESC_logHandler, added);
                  added = addDescription(sb, handler.isConsumeOnMatch(), Diagram_Messages.DESC_consumedOnMatch, added);
                  return sb.toString();
               }
            }
            return super.getText(name, element);
         }

         private boolean addDescription(StringBuffer sb, boolean test, String name,
               boolean added)
         {
            if (added)
            {
               sb.append(", "); //$NON-NLS-1$
            }
            if (test)
            {
               sb.append(name);
            }
            return test | added;
         }
      };

      TableUtil.setLabelProvider(viewer, provider,
            new String[] {"id", "name", "computed"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      
      viewer.setContentProvider(new EventHandlingTableContentProvider(
            conditionTypeId));

      return composite;
   }
}