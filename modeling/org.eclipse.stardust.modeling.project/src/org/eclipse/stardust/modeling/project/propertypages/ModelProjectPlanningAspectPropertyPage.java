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
package org.eclipse.stardust.modeling.project.propertypages;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Verifier;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.stardust.modeling.project.ProjectPlanningAspectPlugin;
import org.eclipse.stardust.modeling.project.effort.EffortByKeyParameter;
import org.eclipse.stardust.modeling.project.effort.EffortByQuantityParameter;
import org.eclipse.stardust.modeling.project.effort.EffortCalculator;
import org.eclipse.stardust.modeling.project.effort.EffortEntry;
import org.eclipse.stardust.modeling.project.effort.EffortEvent;
import org.eclipse.stardust.modeling.project.effort.EffortKey;
import org.eclipse.stardust.modeling.project.effort.EffortListener;
import org.eclipse.stardust.modeling.project.effort.EffortParameter;
import org.eclipse.stardust.modeling.project.effort.EffortParameterScope;
import org.eclipse.stardust.modeling.project.effort.EffortParameters;
import org.eclipse.stardust.modeling.project.effort.EffortPerUnit;
import org.eclipse.stardust.modeling.project.i18n.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

/**
 * 
 * @author gille
 * 
 */
public class ModelProjectPlanningAspectPropertyPage
      extends AbstractProjectPlanningAspectPropertyPage
{
   private static final char SLASH = '/';
   private static final char GT = '>';
   private static final char LT = '<';
   private static final char EQUALS = '=';
   private static final char DOUBLE_QUOTE = '"';
   private static final char SINGLE_QUOTE = '\'';
   private static final char BLANK = ' ';

   private static final String VALIGN = "valign"; //$NON-NLS-1$
   private static final String TOP = "top"; //$NON-NLS-1$
   private static final String COLSPAN = "colspan"; //$NON-NLS-1$
   private static final String BORDER = "border"; //$NON-NLS-1$
   private static final String RIGHT = "right"; //$NON-NLS-1$
   private static final String ALIGN = "align"; //$NON-NLS-1$
   private static final String B = "b"; //$NON-NLS-1$
   private static final String TABLE = "table"; //$NON-NLS-1$
   private static final String TD = "td"; //$NON-NLS-1$
   private static final String TR = "tr"; //$NON-NLS-1$
   private static final String H1 = "h1"; //$NON-NLS-1$

   private static final String PARAMETER_LABEL = Messages.getString("PropertyPage.ParameterColumnLabel"); //$NON-NLS-1$
   private static final String[] EFFORT_LABELS = {
      Messages.getString("PropertyPage.ScopeColumnLabel"), //$NON-NLS-1$
      Messages.getString("PropertyPage.ObjectColumnLabel"), //$NON-NLS-1$
      Messages.getString("PropertyPage.DriverColumnLabel"), //$NON-NLS-1$
      Messages.getString("PropertyPage.ValueColumnLabel")}; //$NON-NLS-1$
   private static final Verifier verifier = VerifierFactory.doubleVerifier;

   private String dragData;
   private TableViewer effortTableViewer;
   private TreeViewer costDriversTreeViewer;

   private String[] columnProperties;
   
   public void loadFieldsFromElement(final IModelElementNodeSymbol symbol,
         final IModelElement element)
   {
      // nothing to do here
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol,
         IModelElement element)
   {
      if (!hasError())
      {
         getEffortParameters().saveToModel();
      }
   }

   public Control createBody(Composite parent)
   {
      setScope(getEffortParameters().MODEL_SCOPE);
      if (hasError())
      {
         Composite composite = FormBuilder.createComposite(parent, 1);
         Label label = FormBuilder.createLabel(composite, errorMessage);
         label.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());
         return composite;
      }
      else
      {
         TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
         createParametersTab(tabFolder);
         createCostDriversTab(tabFolder);
         createEffortCalculationTab(tabFolder);
         return tabFolder;
      }
   }

   private void createParametersTab(TabFolder tabFolder)
   {
      TabItem parametersTabItem = new TabItem(tabFolder, SWT.NONE);
      parametersTabItem.setText(Messages.getString("PropertyPage.ParametersTabLabel")); //$NON-NLS-1$
      parametersTabItem.setControl(createParameterComposite(tabFolder));
   }

   private void createEffortCalculationTab(TabFolder tabFolder)
   {
      Composite composite = FormBuilder.createComposite(tabFolder, 1);
      TabItem tabItem = new TabItem(tabFolder, SWT.NONE);

      tabItem.setText(Messages.getString("PropertyPage.EffortTabLabel")); //$NON-NLS-1$
      tabItem.setControl(composite);

      FormBuilder.createButton(composite, Messages.getString("PropertyPage.CalculateEffortButtonLabel"),	new SelectionListener() //$NON-NLS-1$
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            calculateEffort();
         }
      });

      Table effortTable = FormBuilder.createTable(composite, 5, null, null, 5);
      effortTable.setHeaderVisible(true);

      String[] columnLabels = new String[EFFORT_LABELS.length + EffortPerUnit.DEFAULT_LABELS.length];
      System.arraycopy(EFFORT_LABELS, 0, columnLabels, 0, EFFORT_LABELS.length);
      System.arraycopy(EffortPerUnit.DEFAULT_LABELS, 0, columnLabels,
            EFFORT_LABELS.length, EffortPerUnit.DEFAULT_LABELS.length);
      for (int i = 0; i < columnLabels.length; i++)
      {
         addTableColumn(effortTable, columnLabels[i], 75);
      }

      effortTableViewer = new TableViewer(effortTable);
      effortTableViewer.setLabelProvider(new EffortLabelProvider());
      effortTableViewer.setContentProvider(new ArrayContentProvider());

      effortTableViewer.addDragSupport(DND.DROP_COPY, new Transfer[]
                                                                   { HTMLTransfer.getInstance() }, new DragSourceAdapter()
      {
         public void dragSetData(DragSourceEvent event)
         {
            event.data = dragData;
         }
      });
   }

   private void addTableColumn(Table table, String label, int width)
   {
      TableColumn column = new TableColumn(table, SWT.LEFT);
      column.setText(label);
      column.setWidth(width);
   }

   private void createCostDriversTab(TabFolder tabFolder)
   {
      Composite composite = FormBuilder.createComposite(tabFolder, 1);
      TabItem tabItem = new TabItem(tabFolder, SWT.NONE);

      tabItem.setText(Messages.getString("PropertyPage.CostDriversTabLabel")); //$NON-NLS-1$
      tabItem.setControl(composite);

      Tree tree = FormBuilder.createTree(composite,
            SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
      tree.setHeaderVisible(true);

      // create columns
      columnProperties = new String[EffortPerUnit.DEFAULT_LABELS.length + 1];
      columnProperties[0] = PARAMETER_LABEL;
      System.arraycopy(EffortPerUnit.DEFAULT_LABELS, 0, columnProperties, 1,
            EffortPerUnit.DEFAULT_LABELS.length);
      addTreeColumn(tree, columnProperties[0], 300);
      for (int i = 1; i < columnProperties.length; i++)
      {
         addTreeColumn(tree, columnProperties[i], 70);
      }

      costDriversTreeViewer = new TreeViewer(tree);

      // content
      costDriversTreeViewer.setContentProvider(new EffortParametersContentProvider());
      costDriversTreeViewer.setLabelProvider(new EffortParametersLabelProvider());
      costDriversTreeViewer.setSorter(new EffortSorter());

      // editing
      costDriversTreeViewer.setColumnProperties(columnProperties);
      costDriversTreeViewer.setCellModifier(new Modifier());
      CellEditor[] editors = new CellEditor[columnProperties.length];
      for (int i = 0; i < editors.length; i++)
      {
         editors[i] = new TextCellEditor(costDriversTreeViewer.getTree(), SWT.BORDER);
      }
      costDriversTreeViewer.setCellEditors(editors);

      // only numbers (double) are accepted in the values column
      for (int i = 1; i < editors.length; i++)
      {
         ((Text) editors[i].getControl()).addVerifyListener(verifier);
      }

      // tree menu
      MenuManager menuManager = new MenuManager();
      menuManager.addMenuListener(new ContextMenuListener(costDriversTreeViewer));
      tree.setMenu(menuManager.createContextMenu(tree));
      
      final EffortParameters parameters = getEffortParameters();
      costDriversTreeViewer.setInput(parameters);
      final EffortListener listener = new EffortListener()
      {
         public void handleEvent(EffortEvent event)
         {
            costDriversTreeViewer.setInput(getEffortParameters());
         }
      };
      parameters.addListener(listener);
      composite.addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            parameters.removeListener(listener);
         }
      });
   }

   private void addTreeColumn(Tree tree, String label, int width)
   {
      TreeColumn column = new TreeColumn(tree, SWT.LEFT);
      column.setText(label);
      column.setWidth(width);
   }

   private static String getImagePathFromModelElement(EffortParameterScope scope)
   {
      if (scope.getScopeClass() == ModelType.class)
      {
         return "icons/model.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == ApplicationType.class)
      {
         return "icons/application.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == DataType.class)
      {
         return "icons/data.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == ProcessDefinitionType.class)
      {
         return "icons/process.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == ActivityType.class)
      {
         return "icons/activity.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == TransitionType.class)
      {
         return "icons/transition.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == ConditionalPerformerType.class)
      {
         return "icons/conditional.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == RoleType.class)
      {
         return "icons/role.gif"; //$NON-NLS-1$
      }
      else if (scope.getScopeClass() == OrganizationType.class)
      {
         return "icons/organization.gif"; //$NON-NLS-1$
      }
      else
      {
         return "icons/missing.gif"; //$NON-NLS-1$
      }
   }

   private void calculateEffort()
   {
      List<EffortEntry> items = CollectionUtils.newList();

      EffortCalculator effortCalculator = new EffortCalculator();
      List<EffortEntry> entryList = effortCalculator.calculateEfforts(getEffortParameters());

      List<String> rows = CollectionUtils.newList();
      String[] columns = new String[2 + EffortPerUnit.DEFAULT_LABELS.length];
      columns[0] = column(bold(Messages.getString("ModelProjectPlanningAspectPropertyPage.CostDriverExcelColumnLabel"))); //$NON-NLS-1$
      columns[1] = column(bold(Messages.getString("ModelProjectPlanningAspectPropertyPage.ValueExcelColumnLabel"))); //$NON-NLS-1$
      for (int i = 0; i < EffortPerUnit.DEFAULT_LABELS.length; i++)
      {
         columns[2 + i] = column(bold(EffortPerUnit.DEFAULT_LABELS[i]));
      }
      rows.add(row(columns));

      double[] effort = new double[EffortPerUnit.DEFAULT_LABELS.length];
      String previousElement = null;

      for (int n = 0; n < entryList.size(); ++n)
      {
         EffortEntry effortEntry = (EffortEntry) entryList.get(n);
         for (int i = 0; i < effort.length; i++)
         {
            effort[i] += effortEntry.getEffort()[i];
         }
         items.add(effortEntry);

         if (previousElement == null
               || !previousElement.equals(effortEntry.getName()))
         {
            int colspan = 2 + EffortPerUnit.DEFAULT_LABELS.length;
            String label2 = effortEntry.getName();
            String label = Messages.getString("ModelProjectPlanningAspectPropertyPage.ExcelEffortLabel") + //$NON-NLS-1$
            BLANK + effortEntry.getSimpleName() +
            BLANK + quote(DOUBLE_QUOTE, label2);
            rows.add(row(new String[] {column(colspan, bold(label))}));
            previousElement = effortEntry.getName();
         }

         columns[0] = column(effortEntry.getCostDriver());
         columns[1] = column(effortEntry.getValueString());
         for (int i = 0, len = effortEntry.getEffort().length; i < len; i++)
         {
            columns[2 + i] = column(effortEntry.getEffort()[i], false);
         }

         rows.add(row(columns));
      }

      columns = new String[1 + EffortPerUnit.DEFAULT_LABELS.length];
      columns[0] = column(2, bold(Messages.getString("ModelProjectPlanningAspectPropertyPage.TotalEffortExcelLabel"))); //$NON-NLS-1$
      for (int i = 0, len = effort.length; i < len; i++)
      {
         columns[1 + i] = column(effort[i], true);
      }
      rows.add(row(columns));

      dragData = title(Messages.getString("ModelProjectPlanningAspectPropertyPage.ExcelTitle")) + table(1, rows); //$NON-NLS-1$

      effortTableViewer.setInput(items);
   }

   private String quote(char c, String value)
   {
      return c + value + c;
   }

   private String title(String title)
   {
      return element(H1, title);
   }

   private String row(String[] columns)
   {
      StringBuffer body = new StringBuffer();
      for (int i = 0; i < columns.length; i++)
      {
         body.append(columns[i]);
      }
      return element(TR, new String[] {attribute(VALIGN,
            quote(SINGLE_QUOTE, TOP))}, body.toString());
   }

   private String column(int span, String body)
   {
      return element(TD, new String[] {attribute(COLSPAN,
            quote(SINGLE_QUOTE, Integer.toString(span)))}, body);
   }

   private String table(int border, List<String> rows)
   {
      StringBuffer buffer = new StringBuffer();
      if (rows != null)
      {
         for (int i = 0; i < rows.size(); i++)
         {
            buffer.append(rows.get(i));
         }
      }
      return element(TABLE, new String[] {attribute(BORDER,
            quote(SINGLE_QUOTE, Integer.toString(border)))}, buffer.toString());
   }

   private String column(double value, boolean bold)
   {
      String string = Double.toString(value);
      return element(TD, new String[] {attribute(ALIGN,
            quote(SINGLE_QUOTE, RIGHT))}, bold ? bold(string) : string);
   }

   private String attribute(String name, String value)
   {
      return name + EQUALS + value;
   }

   private String column(String body)
   {
      return element(TD, body);
   }

   private String bold(String body)
   {
      return element(B, body);
   }

   private String element(String name, String body)
   {
      return element(name, null, body);
   }

   private String element(String name, String[] attributes, String body)
   {
      StringBuffer buffer = new StringBuffer();
      buffer.append(LT);
      buffer.append(name);
      if (attributes != null)
      {
         for (int i = 0; i < attributes.length; i++)
         {
            buffer.append(BLANK);
            buffer.append(attributes[i]);
         }
      }
      buffer.append(GT);
      buffer.append(body);
      buffer.append(LT);
      buffer.append(SLASH);
      buffer.append(name);
      buffer.append(GT);
      return buffer.toString();
   }

   /**
    * Helper class to dynamically register actions to the tree menu manager.
    * 
    * @author herinean
    * @version $Revision$
    */
   private static class ContextMenuListener implements IMenuListener
   {
      private List<Action> actions;

      public ContextMenuListener(TreeViewer viewer)
      {
         actions = CollectionUtils.newList();
         // register all possible actions
         actions.add(new CreateEffortParameterAction(viewer,
               CreateEffortParameterAction.BY_KEY_PARAMETER_TYPE));
         actions.add(new CreateEffortParameterAction(viewer,
               CreateEffortParameterAction.BY_VALUE_PARAMETER_TYPE));
         actions.add(new CreateEffortKeyAction(viewer));
         actions.add(new DeleteAction(viewer, DeleteAction.EFFORT_PARAMETER_TYPE));
         actions.add(new DeleteAction(viewer, DeleteAction.EFFORT_KEY_TYPE));
      }

      public void menuAboutToShow(IMenuManager manager)
      {
         manager.removeAll();
         // adds only valid actions
         for (int i = 0, size = actions.size(); i < size; i++)
         {
            IAction action = actions.get(i);
            if (action.isEnabled())
            {
               manager.add(action);
            }
         }
      }
   }

   /**
    * Creates a new EffortKey.
    * 
    * @author herinean
    * @version $Revision$
    */
   private static class CreateEffortKeyAction extends Action
   {
      private TreeViewer treeViewer;

      public CreateEffortKeyAction(TreeViewer treeViewer)
      {
         this.treeViewer = treeViewer;
         setText(Messages.getString("PropertyPage.NewKeyActionLabel")); //$NON-NLS-1$
      }

      public boolean isEnabled()
      {
         IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
         return selection.size() == 1 && matchesType(selection.getFirstElement());
      }

      private boolean matchesType(Object element)
      {
         return element instanceof EffortByKeyParameter;
      }

      public void run()
      {
         IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
         Object object = selection.getFirstElement();
         createEffortKey((EffortByKeyParameter) object);
      }

      private void createEffortKey(EffortByKeyParameter parameter)
      {
         String baseName = Messages.getString("PropertyPage.EffortKeyBaseName"); //$NON-NLS-1$
         int start = parameter.keyCount();
         while (parameter.getKey(baseName + start) != null)
         {
            start++;
         }
         EffortKey key = new EffortKey(parameter, baseName + start, 1.0);
         parameter.addKey(key);
         treeViewer.refresh(parameter);
      }
   }

   /**
    * Creates a new EffortParameter. Depending on the type, it can be an ByKey or an
    * ByValue EffortParameter. 
    * 
    * @author herinean
    * @version $Revision$
    */
   private static class CreateEffortParameterAction extends Action
   {
      private static final int BY_KEY_PARAMETER_TYPE = 0;
      private static final int BY_VALUE_PARAMETER_TYPE = 1;

      private static final String[] LABELS = {
         Messages.getString("PropertyPage.NewByKeyParameterActionLabel"), //$NON-NLS-1$
         Messages.getString("PropertyPage.NewByQuantityParameterActionLabel") //$NON-NLS-1$
      };

      private TreeViewer treeViewer;
      private int type;

      public CreateEffortParameterAction(TreeViewer treeViewer, int type)
      {
         this.treeViewer = treeViewer;
         this.type = type;
         setText(LABELS[type]);
      }

      public boolean isEnabled()
      {
         IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
         return selection.size() == 1 && matchesType(selection.getFirstElement());
      }

      private boolean matchesType(Object element)
      {
         return element instanceof EffortParameterScope;
      }

      public void run()
      {
         IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
         Object object = selection.getFirstElement();
         switch (type)
         {
         case BY_KEY_PARAMETER_TYPE:
            createByKeyParameter((EffortParameterScope) object);
            break;
         case BY_VALUE_PARAMETER_TYPE:
            createByValueParameter((EffortParameterScope) object);
            break;
         }
      }

      private void createByKeyParameter(EffortParameterScope scope)
      {
         // TODO: label generation algorithm may create duplicate labels
         EffortByKeyParameter parameter = new EffortByKeyParameter(scope,
               createParameterName(Messages.getString("PropertyPage.ByKeyParameterBaseName"), scope), //$NON-NLS-1$ 
               new String[] {}, new double[] {});
         scope.addParameter(parameter);
         treeViewer.refresh(scope);
      }

      private void createByValueParameter(EffortParameterScope scope)
      {
         EffortByQuantityParameter parameter = new EffortByQuantityParameter(scope,
               createParameterName(Messages.getString("PropertyPage.ByValueParameterBaseName"), scope), 1.0); //$NON-NLS-1$ 
         scope.addParameter(parameter);
         treeViewer.refresh(scope);
      }

      private String createParameterName(String baseName, EffortParameterScope scope)
      {
         int start = scope.parameterCount();
         while (scope.hasParameter(baseName + start))
         {
            start++;
         }
         return baseName + start;
      }
   }

   /**
    * Deletes a key or parameter.
    * 
    * @author herinean
    * @version $Revision$
    */
   private static class DeleteAction extends Action
   {
      private static final int EFFORT_PARAMETER_TYPE = 0;
      private static final int EFFORT_KEY_TYPE = 1;

      private static final String[] LABELS = {
         Messages.getString("PropertyPage.DeleteParameterActionLabel"), //$NON-NLS-1$
         Messages.getString("PropertyPage.DeleteKeyActionLabel") //$NON-NLS-1$
      };

      private TreeViewer treeViewer;
      private int type;

      public DeleteAction(TreeViewer treeViewer, int type)
      {
         this.treeViewer = treeViewer;
         this.type = type;
         setText(LABELS[type]);
      }

      public boolean isEnabled()
      {
         IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
         return selection.size() == 1 && matchesType(selection.getFirstElement());
      }

      private boolean matchesType(Object element)
      {
         switch (type)
         {
         case EFFORT_PARAMETER_TYPE: return element instanceof EffortParameter;
         case EFFORT_KEY_TYPE: return element instanceof EffortKey;
         default: return false;
         }
      }

      public void run()
      {
         IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
         Object object = selection.getFirstElement();
         switch (type)
         {
         case EFFORT_PARAMETER_TYPE:
            deleteEffortParameter((EffortParameter) object);
            break;
         case EFFORT_KEY_TYPE:
            deleteEffortKey((EffortKey) object);
            break;
         }
      }

      private void deleteEffortParameter(EffortParameter parameter)
      {
         EffortParameterScope scope = parameter.getScope();
         scope.removeParameter(parameter);
         treeViewer.refresh(scope);
      }

      private void deleteEffortKey(EffortKey key)
      {
         EffortByKeyParameter parameter = key.getParameter(); 
         parameter.removeKey(key);
         treeViewer.refresh(parameter);
      }
   }

   /**
    * Sorts entries in the tree.
    * 
    * @author herinean
    * @version $Revision$
    */
   public class EffortSorter extends ViewerSorter
   {
      // make sure that the Model scope is always first,
      // all other entries are alphabetically sorted  
      public int compare(Viewer viewer, Object e1, Object e2)
      {
         if (getEffortParameters().MODEL_SCOPE.equals(e1))
         {
            return -1;
         }
         if (getEffortParameters().MODEL_SCOPE.equals(e2))
         {
            return 1;
         }
         if (!(e1 instanceof EffortParameterScope) || !(e2 instanceof EffortParameterScope))
         {
            return 0;
         }
         return super.compare(viewer, e1, e2);
      }
   }

   /**
    * Cell modifier to propagate the in-place edited values to the model.
    * 
    * @author herinean
    * @version $Revision$
    */
   private class Modifier implements ICellModifier
   {
      public boolean canModify(Object element, String property)
      {
         if (PARAMETER_LABEL.equals(property))
         {
            return element instanceof EffortParameter || element instanceof EffortKey;
         }
         else if (element instanceof EffortByQuantityParameter || element instanceof EffortKey)
         {
            for (int i = 1; i < columnProperties.length; i++)
            {
               if (property.equals(columnProperties[i]))
               {
                  return true;
               }
            }
         }
         return false;
      }

      public Object getValue(Object element, String property)
      {
         ITableLabelProvider labelProvider = (ITableLabelProvider)
         costDriversTreeViewer.getLabelProvider();
         for (int i = 0; i < columnProperties.length; i++)
         {
            if (property.equals(columnProperties[i]))
            {
               return labelProvider.getColumnText(element, i);
            }
         }
         return ""; //$NON-NLS-1$
      }

      public void modify(Object element, String property, Object value)
      {
         if (element instanceof TreeItem)
         {
            element = ((TreeItem) element).getData();
         }
         if (PARAMETER_LABEL.equals(property))
         {
            String newName = (String) value;
            if (StringUtils.isEmpty(newName))
            {
               // empty labels are not allowed
               return;
            }
            if (element instanceof EffortParameter)
            {
               EffortParameter parameter = (EffortParameter) element;
               parameter.setName(newName);
            }
            else if (element instanceof EffortKey)
            {
               EffortKey key = (EffortKey) element;
               key.setName(newName);                                    
            }
         }
         else
         {
            EffortPerUnit effort = null;
            if (element instanceof EffortByQuantityParameter)
            {
               effort = ((EffortByQuantityParameter) element).getEffortPerUnit();
            }
            else if (element instanceof EffortKey)
            {
               effort = ((EffortKey) element).getEffortPerUnit();
            }
            if (effort != null)
            {
               try
               {
                  double newValue = Double.parseDouble(verifier.getInternalValue((String) value));
                  if (newValue < 0)
                  {
                     throw new NumberFormatException(Messages.getString("ModelProjectPlanningAspectPropertyPage.NegativeValuesErrorMessage")); //$NON-NLS-1$
                  }
                  else
                  {
                     effort.setEffort(property, newValue);
                  }
               }
               catch (NumberFormatException nfe)
               {
                  IStatus status = new Status(IStatus.ERROR,
                        ProjectPlanningAspectPlugin.getDefault().getBundle().getSymbolicName(),
                        IStatus.ERROR, nfe.getMessage(), nfe);
                  ErrorDialog.openError(getShell(), Messages.getString("ModelProjectPlanningAspectPropertyPage.ErrorDialogTitle"), Messages.getString("ModelProjectPlanningAspectPropertyPage.ErrorDialogText"), status); //$NON-NLS-1$ //$NON-NLS-2$
               }
            }
         }
         costDriversTreeViewer.update(element, new String[] {property});
      }
   }

   /**
    * Tree content provider that uses as model an EffortParameters object.
    * 
    * @author herinean
    * @version $Revision$
    */
   private static class EffortParametersContentProvider implements ITreeContentProvider
   {
      public Object[] getChildren(Object parentElement)
      {
         if (parentElement instanceof EffortParameterScope)
         {
            return collectParameters((EffortParameterScope) parentElement);
         }
         if (parentElement instanceof EffortByKeyParameter)
         {
            return collectKeys((EffortByKeyParameter) parentElement);
         }
         return new Object[] {};
      }

      private Object[] collectKeys(EffortByKeyParameter parameter)
      {
         Object[] result = new Object[parameter.keyCount()];
         int i = 0;
         for (Iterator<String> itr = parameter.getKeyNames(); itr.hasNext(); i++)
         {
            result[i] = parameter.getKey(itr.next());
         }
         return result;
      }

      private Object[] collectParameters(EffortParameterScope scope)
      {
         Object[] result = new Object[scope.parameterCount()];
         int i = 0;
         for (Iterator<String> itr = scope.getParameterNames(); itr.hasNext(); i++)
         {
            result[i] = scope.getParameter(itr.next());
         }
         return result;
      }

      public Object getParent(Object element)
      {
         if (element instanceof EffortParameterScope)
         {
            return ((EffortParameterScope) element).getEffortParameters();
         }
         if (element instanceof EffortParameter)
         {
            return ((EffortParameter) element).getScope();
         }
         if (element instanceof EffortKey)
         {
            return ((EffortKey) element).getParameter();
         }
         return null;
      }

      public boolean hasChildren(Object element)
      {
         return (element instanceof EffortParameterScope)
            || (element instanceof EffortByKeyParameter);
      }

      public Object[] getElements(Object inputElement)
      {
         if (inputElement instanceof EffortParameters)
         {
            return ((EffortParameters) inputElement).SCOPE_LIST.toArray();
         }
         return new Object[] {};
      }

      public void dispose()
      {
      }

      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
      }
   }

   /**
    * Tree label provider for EffortParameterScopes, EffortParameters and EffortKeys.
    * 
    * @author herinean
    * @version $Revision$
    */
   private static class EffortParametersLabelProvider extends LabelProvider
      implements ITableLabelProvider
   {
      public Image getColumnImage(Object element, int columnIndex)
      {
         if (columnIndex > 0)
         {
            return null;
         }
         if (element instanceof EffortParameterScope)
         {
            return ProjectPlanningAspectPlugin.getDefault()
            .getImage(getImagePathFromModelElement((EffortParameterScope) element));
         }
         if (element instanceof EffortParameter)
         {
            return ProjectPlanningAspectPlugin.getDefault()
            .getImage("icons/parameter.gif"); //$NON-NLS-1$
         }
         if (element instanceof EffortKey)
         {
            return ProjectPlanningAspectPlugin.getDefault()
            .getImage("icons/key.gif"); //$NON-NLS-1$
         }
         return null;
      }

      public String getColumnText(Object element, int columnIndex)
      {
         if (element instanceof EffortParameterScope)
         {
            return columnIndex == 0 ?
                  ((EffortParameterScope) element).getDisplayName() : ""; //$NON-NLS-1$
         }
         else if (element instanceof EffortParameter)
         {
            switch (columnIndex)
            {
            case 0: return ((EffortParameter) element).getName();
            default: return element instanceof EffortByQuantityParameter ?
                  getEffort(((EffortByQuantityParameter) element).getEffortPerUnit(),
                        columnIndex - 1) : ""; //$NON-NLS-1$
            }
         }
         else if (element instanceof EffortKey)
         {
            switch (columnIndex)
            {
            case 0: return ((EffortKey) element).getName();
            default: return getEffort(((EffortKey) element).getEffortPerUnit(),
                  columnIndex - 1);
            }
         }
         return ""; //$NON-NLS-1$
      }

      private String getEffort(EffortPerUnit effortPerUnit, int index)
      {
         if (effortPerUnit != null && index < effortPerUnit.elementCount())
         {
            return verifier.getExternalValue(
                  Double.toString(effortPerUnit.getEffort(index)));
         }
         return ""; //$NON-NLS-1$
      }

      public Image getImage(Object element)
      {
         return getColumnImage(element, 0);
      }

      public String getText(Object element)
      {
         return getColumnText(element, 0);
      }
   }

   /**
    * Table label provider for EffortEntries.
    * 
    * @author herinean
    * @version $Revision$
    */
   private static class EffortLabelProvider extends LabelProvider implements ITableLabelProvider
   {
      public String getColumnText(Object element, int columnIndex)
      {
         EffortEntry entry = (EffortEntry) element;
         switch (columnIndex)
         {
         case 0: return entry.getSimpleName();
         case 1: return entry.getName();
         case 2: return entry.getCostDriver();
         case 3: return entry.getValueString();
         default: return verifier.getExternalValue(Double.toString(entry.getEffort()[columnIndex - 4]));
         }
      }

      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }
   }
}
