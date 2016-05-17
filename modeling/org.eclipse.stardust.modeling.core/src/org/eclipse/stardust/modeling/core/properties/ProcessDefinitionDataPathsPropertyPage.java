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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.core.compatibility.extensions.dms.DmsConstants;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;

public class ProcessDefinitionDataPathsPropertyPage
      extends AbstractModelElementPropertyPage implements IButtonManager
{
   public static final String DATA_PATHS_ID = "_cwm_data_paths_"; //$NON-NLS-1$

   private static final int[] elementFeatureIds = {
      CarnotWorkflowModelPackage.DATA_PATH_TYPE__ID,
      CarnotWorkflowModelPackage.DATA_PATH_TYPE__NAME,
      CarnotWorkflowModelPackage.DATA_PATH_TYPE__DATA,
      CarnotWorkflowModelPackage.DATA_PATH_TYPE__DATA_PATH
   };

   private static final String[] labelProperties = {"id"};//$NON-NLS-1$

   private Button[] buttons;
   private Object selection;

   private EObjectLabelProvider labelProvider;
   private TableViewer viewer;
   private ModelElementsOutlineSynchronizer outlineSynchronizer;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      viewer.setInput(element);
      outlineSynchronizer.init(element);
      updateButtons(null, buttons);
      expandTree();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public void dispose()
   {
      outlineSynchronizer.dispose();
      super.dispose();
   }

   private ProcessDefinitionType getProcess()
   {
      return (ProcessDefinitionType) getModelElement();
   }

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
      table.setHeaderVisible(true);
      table.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
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
            if (selection instanceof DataPathType)
            {
               selectPageForObject(selection);
            }
         }
      });

      viewer = new TableViewer(table);
      TableUtil.createColumns(table, new String[] {
            Diagram_Messages.COL_NAME_Id,
            Diagram_Messages.COL_NAME_Name,
            Diagram_Messages.COL_NAME_Description});
      TableUtil.setInitialColumnSizes(table, new int[] {35, 35, 30});
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public String getText(String name, Object element)
         {
            if ("data".equals(name)) //$NON-NLS-1$
            {
               DataPathType dataPath = (DataPathType) element;
               DataType data = dataPath.getData();
               if (data != null)
               {
                  String text = super.getText(Diagram_Messages.TXT_NAME, data);
                  return StringUtils.isEmpty(dataPath.getDataPath()) ?
                        text : text + "." + dataPath.getDataPath(); //$NON-NLS-1$
               }
            }
            return super.getText(name, element);
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider,
            new String[] {"id", "name", "data"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      viewer.setContentProvider(new ModelElementsTableContentProvider(
            CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_DataPath(),
            elementFeatureIds, labelProperties));

      outlineSynchronizer = new ModelElementsOutlineSynchronizer(
            new DefaultOutlineProvider(this,
               CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_DataPath(),
               CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
               CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name(),
               DATA_PATHS_ID,
               DataPathPropertyPage.class.getName()));

      addModelElementsOutlineSynchronizer(outlineSynchronizer);
      return composite;
   }

   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(parent);
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
      }
      super.setVisible(visible);
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
      buttons[DELETE_BUTTON].setEnabled(selection instanceof DataPathType
            && !DmsConstants.PATH_ID_ATTACHMENTS.equals(((DataPathType) selection).getId()));
      buttons[UP_BUTTON].setEnabled(selection instanceof DataPathType &&
         canMoveUp((DataPathType) selection));
      buttons[DOWN_BUTTON].setEnabled(selection instanceof DataPathType &&
         canMoveDown((DataPathType) selection));
   }

   private boolean canMoveUp(DataPathType selection)
   {
      ProcessDefinitionType pd = (ProcessDefinitionType) selection.eContainer();
      return pd.getDataPath().indexOf(selection) > 0;
   }

   private boolean canMoveDown(DataPathType selection)
   {
      ProcessDefinitionType pd = (ProcessDefinitionType) selection.eContainer();
      int index = pd.getDataPath().indexOf(selection);
      return index >= 0 && index < pd.getDataPath().size() - 1;
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[BUTTON_COUNT];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performAdd(buttons);
         }
      });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Delete, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performDelete(buttons);
         }
      });

      buttons[UP_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_MoveUp, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performUp(buttons);
         }
      });

      buttons[DOWN_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_MoveDown, new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            performDown(buttons);
         }
      });

      return buttons;
   }

   public Object getSelection()
   {
      return selection == null ? getSelectedItem() : selection;
   }

   private void performUp(Button[] buttons)
   {
      DataPathType dataPath = (DataPathType) getSelection();
      EList<DataPathType> paths = getProcess().getDataPath();
      int index = paths.indexOf(dataPath);
      if (index > 0)
      {
         paths.move(index - 1, index);
         updateButtons(dataPath, buttons);
      }
   }

   private void performDown(Button[] buttons)
   {
      DataPathType dataPath = (DataPathType) getSelection();
      EList<DataPathType> paths = getProcess().getDataPath();
      int index = paths.indexOf(dataPath);
      if (index >= 0 && index < paths.size() - 1)
      {
         paths.move(index + 1, index);
         updateButtons(dataPath, buttons);
      }
   }

   private void performDelete(Button[] buttons)
   {
      DataPathType dataPath = (DataPathType) getSelection();
      ProcessDefinitionType process = getProcess();
      process.getDataPath().remove(dataPath);
      updateButtons(null, buttons);
      selectPage(ProcessDefinitionDataPathsPropertyPage.DATA_PATHS_ID);
   }

   private void performAdd(Button[] buttons)
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      DataPathType dataPath = factory.createDataPathType();
      ProcessDefinitionType process = getProcess();

      IdFactory idFactory = new IdFactory(Diagram_Messages.ID_DataPath, Diagram_Messages.BASENAME_DataPath);
      idFactory.computeNames(process.getDataPath());

      dataPath.setId(idFactory.getId());
      dataPath.setName(idFactory.getName());
      dataPath.setData(null);
      dataPath.setDescriptor(false);
      dataPath.setDirection(DirectionType.IN_LITERAL);
      dataPath.setDataPath(""); //$NON-NLS-1$
      process.getDataPath().add(dataPath);

      if (preselect)
      {
         selectPageForObject(dataPath);
      }
   }
}