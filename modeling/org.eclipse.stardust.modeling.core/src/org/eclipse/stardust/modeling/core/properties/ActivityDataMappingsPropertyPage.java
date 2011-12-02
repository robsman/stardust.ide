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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.editors.ui.TableLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.spi.ConfigurationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPropertyPage;


class ActivityDataMappingsPropertyPage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   public static final String OUT_DATA_MAPPINGS_ID = "_cwm_out_data_mappings_"; //$NON-NLS-1$

   public static final String IN_DATA_MAPPINGS_ID = "_cwm_in_data_mappings_"; //$NON-NLS-1$

   private static final String UNDEFINED = "[undefined]"; //$NON-NLS-1$

   private boolean isIn;

   private TreeViewer viewer;

   private TableLabelProvider labelProvider;

   private Button[] buttons;

   private Object selection;

   private ModelElementsOutlineSynchronizer outlineSynchronizer;

   private ActivityType activity;

   private DataType data;

   public ActivityDataMappingsPropertyPage(boolean in)
   {
      isIn = in;
   }

   protected void performDefaults()
   {
      super.performDefaults();
      selectPage(isIn
            ? ActivityDataMappingsPropertyPage.IN_DATA_MAPPINGS_ID
            : ActivityDataMappingsPropertyPage.OUT_DATA_MAPPINGS_ID);
   }   
   
   public void contributeVerticalButtons(Composite parent)
   {
      buttons = createButtons(parent);
   }

   public void dispose()
   {
      outlineSynchronizer.dispose();
      super.dispose();
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
         updateTable();
      }
      super.setVisible(visible);
   }

   protected void updateTable()
   {
      for (int i = 0; i < viewer.getTree().getItemCount(); i++)
      {
         TreeItem contextItem = viewer.getTree().getItem(i);
         boolean isGrayed = !isValidContext(((ApplicationContextTypeType) contextItem
               .getData()).getId());
         contextItem.setGrayed(isGrayed);
         contextItem.setForeground(isGrayed ? ColorConstants.gray : ColorConstants.black);
         for (int j = 0; j < contextItem.getItemCount(); j++)
         {
            TreeItem dmItem = contextItem.getItem(j);
            dmItem.setGrayed(isGrayed);
            dmItem.setForeground(isGrayed ? ColorConstants.gray : ColorConstants.black);
         }
      }
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
      boolean isValidDM = selection instanceof DataMappingType;// &&
      // !invalidContexts.contains(((DataMappingType) selection).getContext());
      buttons[ADD_BUTTON].setEnabled(selection != null
            && (isValidDM || selection instanceof ApplicationContextTypeType)
            && canAdd(selection));
      buttons[DELETE_BUTTON].setEnabled(isValidDM);
      buttons[UP_BUTTON].setEnabled(isValidDM && canMoveUp((DataMappingType) selection));
      buttons[DOWN_BUTTON].setEnabled(isValidDM
            && canMoveDown((DataMappingType) selection));      
   }

   private boolean canAdd(Object selection)
   {
      String context = selection instanceof ApplicationContextTypeType
            ? ((ApplicationContextTypeType) selection).getId()
            : ((DataMappingType) selection).getContext();
      return isValidContext(context);
   }

   private boolean canMoveDown(DataMappingType type)
   {
      List contexts = ActivityUtil.getContextTypes(getActivity(), isIn
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL);
      for (int i = 0; i < contexts.size(); i++)
      {
         ApplicationContextTypeType context = (ApplicationContextTypeType) contexts
               .get(i);
         if (context.getId().equals(type.getContext()))
         {
            Object activeDialog = ((ModelElementPropertyDialog) this.getContainer())
                  .getSelectedPage();
            if (activeDialog != null)
            {
               if (activeDialog instanceof ActivityDataMappingsPropertyPage
                     && (i < contexts.size() - 1))
               {
                  // there is a valid context below
                  return true;
               }
               else
               {
                  DataMappingsTreeContentProvider provider = (DataMappingsTreeContentProvider) viewer
                        .getContentProvider();
                  List mappings = Arrays.asList(provider.getChildren((Object) context));
                  int index = mappings.indexOf(type);
                  return index >= 0 && index < mappings.size() - 1;
               }
            }
         }
      }
      return false;
   }

   private boolean canMoveUp(DataMappingType type)
   {
      List contexts = ActivityUtil.getContextTypes(getActivity(), isIn
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL);
      for (int i = 0; i < contexts.size(); i++)
      {
         ApplicationContextTypeType context = (ApplicationContextTypeType) contexts
               .get(i);
         if (context.getId().equals(type.getContext()))
         {
            if (i > 0)
            {
               // there is a valid context above
               return true;
            }
            else
            {
               DataMappingsTreeContentProvider provider = (DataMappingsTreeContentProvider) viewer
                     .getContentProvider();
               List mappings = Arrays.asList(provider.getChildren((Object) context));
               int index = mappings.indexOf(type);
               return index > 0;
            }
         }
      }
      return false;
   }

   public Button[] createButtons(Composite parent)
   {
      final Button[] buttons = new Button[BUTTON_COUNT];

      buttons[ADD_BUTTON] = FormBuilder.createButton(parent, Diagram_Messages.B_Add,
            new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performAdd(buttons);
               }
            });

      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_Delete, new SelectionAdapter()
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
            Diagram_Messages.B_MoveDown, new SelectionAdapter()
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

   private Object getSelectedItem()
   {
      IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
      Object selection = sel.getFirstElement();
      return selection;
   }

   private void performDown(Button[] buttons)
   {
      DataMappingType type = (DataMappingType) getSelection();

      List contexts = ActivityUtil.getContextTypes(getActivity(), isIn
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL);
      EList allMappings = getActivity().getDataMapping();
      for (int i = 0; i < contexts.size(); i++)
      {
         ApplicationContextTypeType context = (ApplicationContextTypeType) contexts
               .get(i);
         if (context.getId().equals(type.getContext()))
         {
            // reuse some functionality of the content provider
            DataMappingsTreeContentProvider provider = (DataMappingsTreeContentProvider) viewer
                  .getContentProvider();
            List mappings = Arrays.asList(provider.getChildren((Object) context));
            int index = mappings.indexOf(type);
            if (index < mappings.size() - 1)
            {
               // move down in the same context
               int oldPos = allMappings.indexOf(type);
               DataMappingType theOneAfter = (DataMappingType) mappings.get(index + 1);
               int newPos = allMappings.indexOf(theOneAfter);
               allMappings.move(newPos, oldPos);
            }
            else
            {
               // move to the next context
               ApplicationContextTypeType nextContext = (ApplicationContextTypeType) contexts
                     .get(i + 1);
               type.setContext(nextContext.getId());
               List newMappings = Arrays.asList(provider
                     .getChildren((Object) nextContext));
               if (newMappings.indexOf(type) > 0)
               {
                  int oldPos = allMappings.indexOf(type);
                  DataMappingType theFirstOne = (DataMappingType) newMappings.get(0);
                  int newPos = allMappings.indexOf(theFirstOne);
                  allMappings.move(newPos, oldPos);
               }
            }
            break;
         }
      }

      refreshAccessPoints(type);      
      
      updateButtons(type, buttons);
   }

   private void performUp(Button[] buttons)
   {
      DataMappingType type = (DataMappingType) getSelection();

      List contexts = ActivityUtil.getContextTypes(getActivity(), isIn
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL);
      EList allMappings = getActivity().getDataMapping();
      for (int i = 0; i < contexts.size(); i++)
      {
         ApplicationContextTypeType context = (ApplicationContextTypeType) contexts
               .get(i);
         if (context.getId().equals(type.getContext()))
         {
            // reuse some functionality of the content provider
            DataMappingsTreeContentProvider provider = (DataMappingsTreeContentProvider) viewer
                  .getContentProvider();
            List mappings = Arrays.asList(provider.getChildren((Object) context));
            int index = mappings.indexOf(type);
            if (index > 0)
            {
               // move up in the same context
               int oldPos = allMappings.indexOf(type);
               DataMappingType theOneBefore = (DataMappingType) mappings.get(index - 1);
               int newPos = allMappings.indexOf(theOneBefore);
               allMappings.move(newPos, oldPos);
            }
            else
            {
               // move to the previous context
               ApplicationContextTypeType prevContext = (ApplicationContextTypeType) contexts
                     .get(i - 1);
               type.setContext(prevContext.getId());
               List newMappings = Arrays.asList(provider
                     .getChildren((Object) prevContext));
               if (newMappings.indexOf(type) < newMappings.size() - 1)
               {
                  int oldPos = allMappings.indexOf(type);
                  DataMappingType theLastOne = (DataMappingType) newMappings
                        .get(newMappings.size() - 1);
                  int newPos = allMappings.indexOf(theLastOne) + 1;
                  allMappings.move(newPos, oldPos);
               }
            }
            break;
         }
      }
      
      refreshAccessPoints(type);      

      updateButtons(type, buttons);
   }

   private void refreshAccessPoints(DataMappingType type)
   {
      List<ModelElementsOutlineSynchronizer> modelElementsOutlineElements = getModelElementsOutlineElements();
      for(ModelElementsOutlineSynchronizer element : modelElementsOutlineElements)
      {
         List<IPreferenceNode> nodes = element.getNodes();
         for (IPreferenceNode node : nodes)
         {
            if(node instanceof CarnotPreferenceNode)
            {
               IWorkbenchPropertyPage page = (IWorkbenchPropertyPage) node.getPage();
               if(page != null)
               {
                  if(page instanceof DataMappingPropertyPage
                        && ((DataMappingPropertyPage) page).getModelElement().equals(type))
                  {
                     ((DataMappingPropertyPage) page).refreshAccessPoint();
                  }
               }
            }               
         }         
      }
   }

   private void performDelete(Button[] buttons)
   {
      DataMappingType dataMapping = (DataMappingType) getSelection();
      if (dataMapping != null)
      {
         ActivityType activity = getActivity();
         activity.getDataMapping().remove(dataMapping);

         // check if this is needed
         ActivityUtil.updateConnections(activity);

         updateButtons(null, buttons);

         selectPage(isIn
               ? ActivityDataMappingsPropertyPage.IN_DATA_MAPPINGS_ID
               : ActivityDataMappingsPropertyPage.OUT_DATA_MAPPINGS_ID);
      }
   }

   private void performAdd(Button[] buttons)
   {
      String context = null;
      Object object = getSelection();
      if (object instanceof DataMappingType)
      {
         context = ((DataMappingType) object).getContext();
      }
      else
      {
         context = ((ApplicationContextTypeType) object).getId();
      }
      DataMappingType dataMapping = CarnotWorkflowModelFactory.eINSTANCE
            .createDataMappingType();
      // oid ?
      String baseId = isIn ? "in" : "out"; //$NON-NLS-1$ //$NON-NLS-2$
      String baseName = isIn
            ? Diagram_Messages.BASENAME_InDataMapping
            : Diagram_Messages.BASENAME_OutDataMapping;
      IdFactory factory = new IdFactory(baseId, baseName,
            CarnotWorkflowModelPackage.eINSTANCE.getDataMappingType(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(), null);
      factory.computeNames(getActivity().getDataMapping());

      dataMapping.setId(factory.getId());
      dataMapping.setName(factory.getId());
      dataMapping.setContext(context);
      dataMapping.setDirection(isIn
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL);

      DataType data = getData();
      if (data != null)
      {
         dataMapping.setData(data);
      }

      ActivityType activity = getActivity();

      dataMapping.setElementOid(ModelUtils.getElementOid(dataMapping, ModelUtils
            .findContainingModel(activity)));

      // todo: add in correct place
      activity.getDataMapping().add(dataMapping);

      // check if this is needed
      // ActivityUtil.updateConnections(activity);

      refreshAccessPoints(dataMapping);      
      
      if (preselect)
      {
         selectPageForObject(dataMapping);
      }
   }

   private ActivityType getActivity()
   {
      if (activity == null)
      {
         Object element = getElement();
         if (element instanceof EditPart)
         {
            Object model = ((EditPart) element).getModel();
            if (model instanceof DataMappingConnectionType)
            {
               model = ((DataMappingConnectionType) model).getActivitySymbol();
            }
            if (model instanceof ActivitySymbolType)
            {
               model = ((ActivitySymbolType) model).getActivity();
            }
            if (model instanceof ActivityType)
            {
               activity = (ActivityType) model;
            }
         }
      }
      return activity;
   }

   private DataType getData()
   {
      if (data == null)
      {
         Object element = getElement();
         if (element instanceof EditPart)
         {
            Object model = ((EditPart) element).getModel();
            if (model instanceof DataMappingConnectionType)
            {
               model = ((DataMappingConnectionType) model).getDataSymbol();
            }
            if (model instanceof DataSymbolType)
            {
               model = ((DataSymbolType) model).getData();
            }
            if (model instanceof DataType)
            {
               data = (DataType) model;
            }
         }
      }
      return data;
   }

   private String getPageId()
   {
      return isIn
            ? ActivityDataMappingsPropertyPage.IN_DATA_MAPPINGS_ID
            : ActivityDataMappingsPropertyPage.OUT_DATA_MAPPINGS_ID;
   }

   private ApplicationContextTypeType getApplicationContext(DataMappingType dataMapping)
   {
      return (ApplicationContextTypeType) ModelUtils.findIdentifiableElement(ModelUtils
            .findContainingModel(dataMapping), CarnotWorkflowModelPackage.eINSTANCE
            .getModelType_ApplicationContextType(), dataMapping.getContext());
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      viewer.setInput(getActivity());
      viewer.expandAll();
      outlineSynchronizer.init(getActivity());
      updateButtons(null, buttons);
      updateTable();
      expandTree();
      //rp: CRNT-19736
      postMouseClickEvent();
   }

   private void postMouseClickEvent()
   {
      Event e = new Event();
      e.type = SWT.MouseDown; 
      e.type = SWT.MouseDown;
      e.widget = viewer.getControl();
      Display.getCurrent().post(e);
      
      e = new Event();
      e.type = SWT.MouseUp;      
      e.button = SWT.MouseUp;
      e.widget = viewer.getControl();
      Display.getCurrent().post(e);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1);

      Tree tree = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultLimitedMultiLineWidgetGridData(200));
      FormBuilder.applyDefaultTextControlWidth(tree);

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
            if (selection instanceof DataMappingType)
            {
               selectPageForObject(selection);
            }
         }
      });

      viewer = new TreeViewer(tree);
      TableUtil.createColumns(tree, new String[] {
            Diagram_Messages.COL_NAME_Id, Diagram_Messages.COL_NAME_Name});
      
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public Image getImage(Object element)
         {
            Image image = super.getImage(element);
            return image == null
                  ? DiagramPlugin.getImage("icons/full/obj16/context.gif") : image; //$NON-NLS-1$
         }

         public String getText(String name, Object element)
         {
            if (name.equals("name") && element instanceof DataMappingType) //$NON-NLS-1$
            {
               DataMappingType dm = (DataMappingType) element;
               ModelType model = ModelUtils.findContainingModel(dm);
               ApplicationContextTypeType context = (ApplicationContextTypeType)
                  ModelUtils.findIdentifiableElement(
                     model.getApplicationContextType(), dm.getContext());
               DataType data = dm.getData();
               String ds = data == null ? null
                     : data.getName() == null ? data.getId() : data.getName();
               String as = dm.getApplicationAccessPoint();
               if (context != null && context.isHasApplicationPath())
               {
                  return (ds == null ? UNDEFINED : ds)
                     + (DirectionType.IN_LITERAL.equals(dm.getDirection()) ? ">>" : "<<") + //$NON-NLS-1$ //$NON-NLS-2$
                         (as == null ? UNDEFINED : as);
               }
               return (ds == null ? UNDEFINED : ds);
            }
            return super.getText(name, element);
         }
      };

      final DataType data = getData();
      TableUtil.setLabelProvider(viewer, labelProvider, new String[] {"id", "name"}); //$NON-NLS-1$ //$NON-NLS-2$
      viewer.setContentProvider(new DataMappingsTreeContentProvider(data, isIn));

      OutlineProvider op = new DefaultOutlineProvider(this,
            CarnotWorkflowModelPackage.eINSTANCE.getActivityType_DataMapping(),
            CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(), null,
            getPageId(), DataMappingPropertyPage.class.getName(), new IFilter()
            {
               public boolean select(Object object)
               {
                  DataMappingType type = (DataMappingType) object;
                  return isIn == AccessPointUtil.isIn(type.getDirection())
                        && (data == null || data.equals(type.getData()));
               }
            })
      {
         public ConfigurationElement createPageConfiguration(IModelElement element)
         {
            DataMappingType dataMapping = (DataMappingType) element;
            ApplicationContextTypeType ctxType = getApplicationContext(dataMapping);
            return ConfigurationElement.createPageConfiguration(dataMapping.getId(),
                  dataMapping.getContext() + ":" + dataMapping.getId(), //$NON-NLS-1$
                  getEditor().getIconFactory().getIconFor(ctxType),
                  DataMappingPropertyPage.class.getName());
         }

         public void addNodeTo(String parentNodeId, final CarnotPreferenceNode node)
         {
            page.addNodeTo(parentNodeId, node, new EObjectLabelProvider(getEditor())
            {
               public String getText(String name, Object element)
               {
                  DataMappingType dataMapping = (DataMappingType) node.getAdaptable()
                        .getAdapter(IModelElement.class);
                  return dataMapping.getContext() + ":" + dataMapping.getId(); //$NON-NLS-1$
               }
            });
         }
      };

      outlineSynchronizer = new ModelElementsOutlineSynchronizer(op);
      addModelElementsOutlineSynchronizer(outlineSynchronizer);
      return composite;
   }

   public Point computeSize()
   {
      TableUtil.setInitialColumnSizesDirect(viewer.getTree(), new int[] {35, 65});
      return super.computeSize();
   }

   private boolean isValidContext(String context)
   {
      List contexts = ActivityUtil.getContextTypes(getActivity(), isIn
            ? DirectionType.IN_LITERAL
            : DirectionType.OUT_LITERAL);
      for (Iterator iter = contexts.iterator(); iter.hasNext();)
      {
         ApplicationContextTypeType applicationContext = (ApplicationContextTypeType) iter
               .next();
         if (applicationContext.getId().equals(context))
         {
            return true;
         }
      }
      return false;
   }
}