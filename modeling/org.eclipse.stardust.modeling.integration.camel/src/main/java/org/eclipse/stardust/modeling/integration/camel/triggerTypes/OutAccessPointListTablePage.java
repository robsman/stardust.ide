package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import static org.eclipse.stardust.engine.extensions.camel.CamelConstants.CAT_BODY_OUT_ACCESS_POINT;
import static org.eclipse.stardust.modeling.integration.camel.Constants.*;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.extensions.camel.CamelConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.CarnotPreferenceNode;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.TableUtil;
import org.eclipse.stardust.modeling.core.properties.*;
import org.eclipse.stardust.modeling.integration.camel.Camel_Messages;

public class OutAccessPointListTablePage extends AbstractModelElementPropertyPage
      implements IButtonManager
{
   private static final String NONE = "None";

   private static final String[] labelProperties = {"name"};

   private static final int[] elementFeatureIds = {
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__ID,
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__NAME,
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__DATA,
         CarnotWorkflowModelPackage.DATA_PATH_TYPE__DATA_PATH};

   private Button[] buttons;

   private Object selection;

   private EObjectLabelProvider labelProvider;

   private TableViewer viewer;

   private CamelModelElementsOutlineSynchronizer outlineSynchronizer;

   private ComboViewer outBodyAccessPointViewer;

   private TriggerType trigger;

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

   @Override
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      resetContent();
      trigger = (TriggerType) element;
      outBodyAccessPointViewer.add(Camel_Messages.label_None);
      List<AccessPointType> availableAccessPoint = trigger.getAccessPoint();
      for (AccessPointType accessPointType : availableAccessPoint)
      {
         outBodyAccessPointViewer.add(accessPointType.getId());
      }
      if (AttributeUtil.getAttributeValue((IExtensibleElement) element,
            CAT_BODY_OUT_ACCESS_POINT) != null)
      {
         outBodyAccessPointViewer.setSelection(new StructuredSelection(
               AttributeUtil.getAttributeValue((IExtensibleElement) element,
                     CAT_BODY_OUT_ACCESS_POINT)));
      }
      else
      {
         outBodyAccessPointViewer.setSelection(new StructuredSelection(
               Camel_Messages.label_None));
      }
   }

   @Override
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      String selection = (String) ((StructuredSelection) outBodyAccessPointViewer
            .getSelection()).getFirstElement();
      if (StringUtils.isNotEmpty(selection) && !selection.equalsIgnoreCase(NONE))
      {
         AttributeUtil.setAttribute((IExtensibleElement) element,
               CamelConstants.CAT_BODY_OUT_ACCESS_POINT, "String", selection);
      }
      else
      {
         AttributeUtil.setAttribute((IExtensibleElement) element,
               CamelConstants.CAT_BODY_OUT_ACCESS_POINT, "String", null);
      }
   }

   @Override
   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      FormBuilder.createLabel(composite, Camel_Messages.label_Body_Output_Access_Point);
      outBodyAccessPointViewer = new ComboViewer(FormBuilder.createCombo(composite));
      outBodyAccessPointViewer.setContentProvider(ArrayContentProvider.getInstance());
      outBodyAccessPointViewer.setLabelProvider(new LabelProvider()
      {
         @Override
         public String getText(Object element)
         {
            if (element instanceof AccessPointType)
               return ((AccessPointType) element).getName();
            return (String) element;
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
      TableUtil.createColumns(table, new String[] {Diagram_Messages.COL_NAME_Name});
      TableUtil.setInitialColumnSizes(table, new int[] {70, 29});
      labelProvider = new EObjectLabelProvider(getEditor())
      {
         public String getText(String name, Object element)
         {
            return super.getText(name, element);
         }
      };
      TableUtil.setLabelProvider(viewer, labelProvider, labelProperties);
      ModelElementsTableContentProvider provider = new ModelElementsTableContentProvider(
            CarnotWorkflowModelPackage.eINSTANCE.getIAccessPointOwner_AccessPoint(),
            elementFeatureIds, labelProperties);
      viewer.setContentProvider(provider);
      outlineSynchronizer = new CamelModelElementsOutlineSynchronizer(
            new CamelOutlineProvider(this, CarnotWorkflowModelPackage.eINSTANCE
                  .getIAccessPointOwner_AccessPoint(),
                  CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(),
                  CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name(),
                  getParentNodeId(), OutAccessPointDetailsPage.class.getName())
            {
               protected List retrievePagesFor(ModelElementAdaptable adaptable)
               {
                  IModelElement accessPoint = (IModelElement) adaptable
                        .getAdapter(IModelElement.class);
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

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         updateButtons(getSelectedItem(), buttons);
      }
      super.setVisible(visible);
   }

   @Override
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
      buttons[DELETE_BUTTON] = FormBuilder.createButton(parent,
            Diagram_Messages.B_Delete, new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  performDelete(buttons);
               }
            });
      return buttons;
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
         List<ParameterMappingType> parameterMappings = new ArrayList<ParameterMappingType>();
         parameterMappings.addAll(trigger.getParameterMapping());
         trigger.getParameterMapping().clear();
         for (ParameterMappingType parameterMapping : parameterMappings)
         {
            if (!parameterMapping.getParameter().equalsIgnoreCase(ap.getId()))
               trigger.getParameterMapping().add(parameterMapping);
         }
         triggerType.getAccessPoint().remove(ap);
         updateButtons(null, buttons);
         selectPage(getParentNodeId());
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

   private void performAdd(Button[] buttons)
   {
      TriggerType triggerType = getTrigger();
      List<AccessPointType> points = triggerType.getAccessPoint();
      IdFactory factory = new IdFactory("out", Diagram_Messages.BASENAME_OutAccessPoint);
      factory.computeNames(points);
      AccessPointType ap = AccessPointUtil.createIntrinsicAccessPoint(factory.getId(),
            factory.getName(), null, DirectionType.OUT_LITERAL, true, null, null);
      triggerType.getAccessPoint().add(ap);
      if (preselect)
      {
         selectPageForObject(ap);
      }

   }

   private String getParentNodeId()
   {
      return OUT_ACCESS_POINT_LIST_PAGE_ID;
   }

   private void resetContent()
   {
      TriggerType element = getTrigger();
      viewer.setInput(element);
      outlineSynchronizer.init(element);
      updateButtons(null, buttons);
      expandTree();
   }

   public void selectPageForObject(Object selection)
   {
      if (selection == null)
      {
         return;
      }
      PreferenceManager manager = getPreferenceManager();
      List<IPreferenceNode> list = manager.getElements(PreferenceManager.PRE_ORDER);
      for (int i = 0; i < list.size(); i++)
      {
         IPreferenceNode node = list.get(i);
         if (node instanceof CarnotPreferenceNode)
         {

            CarnotPreferenceNode cpn = (CarnotPreferenceNode) node;
            if (selection.equals(cpn.getAdaptable().getAdapter(IModelElement.class)))
            {
               selectPage(node);
               break;
            }
            if (cpn.getAdaptable().getAdapter(IModelElement.class) instanceof Proxy)
            {
               Proxy proxy = (Proxy) cpn.getAdaptable().getAdapter(IModelElement.class);
               if (proxy.equals(selection))
               {
                  selectPage(node);
                  break;
               }
            }
         }
      }
   }

   public void selectPage(IPreferenceNode node)
   {
      this.viewer.setSelection(new StructuredSelection(node));
   }
}
