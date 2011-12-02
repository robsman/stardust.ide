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

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;
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
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.AbstractWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EFeatureAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import ag.carnot.base.CollectionUtils;

/**
 * @author fherinean
 * @version $Revision$
 */
public class DataMappingPropertyPage extends AbstractModelElementPropertyPage
{
   protected LabeledText txtId;
   protected LabeledText txtName;

   protected Button autoIdButton;
   
   private LabelWithStatus dataLabel;
   private ComboViewer dataText;
   private LabelWithStatus dataPathLabel;
   private AccessPathBrowserComposite dataPathBrowser;
   private LabelWithStatus apLabel;
   private ComboViewer accessPointText;
   private LabelWithStatus accessPathLabel;
   private AccessPathBrowserComposite accessPathBrowser;
   private Button[] buttons;

   private WidgetBindingManager binding;
   private List<AccessPointType> accessPoints = CollectionUtils.newList();
   private ModelType model;
   private ActivityType activity;
   private DataMappingType dataMapping;
   private DirectionType direction;
   
   private String dataMappingContext;
   private boolean startup = true;
   
   private SelectionListener autoIdListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {
      }

      public void widgetSelected(SelectionEvent e)
      {
         boolean selection = ((Button) e.widget).getSelection();
         if(selection)
         {
            txtId.getText().setEditable(false);
            String computedId = ModelUtils.computeId(txtName.getText().getText());
            txtId.getText().setText(computedId);            
         }
         else
         {
            txtId.getText().setEditable(true);            
         }         
      }
   };         
   
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         Text text = (Text) e.widget;
         String name = text.getText();
         if (autoIdButton.getSelection())
         {
            String computedId = ModelUtils.computeId(name);
            txtId.getText().setText(computedId);
         }
      }
   };

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      txtName.getText().removeModifyListener(listener);
      
      binding = getWidgetBindingManager();
      dataMapping = (DataMappingType) element;

      model = ModelUtils.findContainingModel(dataMapping);

      ApplicationContextTypeType ctxType = (ApplicationContextTypeType) ModelUtils
            .findIdentifiableElement(model, CarnotWorkflowModelPackage.eINSTANCE
                  .getModelType_ApplicationContextType(), dataMapping.getContext());
      dataText.getCombo().setEnabled(getData() == null);

      dataMappingContext = dataMapping.getContext();
      
      // data & data path bindings
      binding.getModelBindingManager().bind(
            new Data2DataPathModelAdapter2(model, model.getData()),
            new Data2DataPathWidgetAdapter2(dataText, dataPathBrowser, dataMapping.getDirection()));

      binding.bind(new LabeledViewer(dataText, dataLabel), dataMapping,
            CarnotWorkflowModelPackage.eINSTANCE.getDataMappingType_Data());

      binding.getModelBindingManager().bind(dataMapping,
            CarnotWorkflowModelPackage.eINSTANCE.getDataMappingType_Data(),
            new AbstractWidgetAdapter()
            {
               public void updateVisuals(Object value)
               {
                  ActivityType activity = (ActivityType) dataMapping.eContainer();
                  if (null != activity)
                  {
                     ActivityUtil.updateConnections(activity);
                     computeDataMappingId(dataMapping, activity);                      
                     if (autoIdButton.getSelection() && !startup)
                     {
                        dataMapping.setId(dataMapping.getName());
                        startup = false;
                  }
               }
               }
            });

      binding.bind(new LabeledText(dataPathBrowser.getMethodText(), dataPathLabel),
            dataMapping, CarnotWorkflowModelPackage.eINSTANCE.getDataMappingType_DataPath());

      // access point && access point path binding
      boolean hasApplicationPath = ctxType.isHasApplicationPath();
      activity = (ActivityType) dataMapping.eContainer();
      if (hasApplicationPath)
      {
         accessPointText.getCombo().setEnabled(true);

         updateAccessPoints();
         
         direction = dataMapping.getDirection();
         if (DirectionType.IN_LITERAL.equals(direction))
         {
            direction = DirectionType.OUT_LITERAL;
         }
         else if (DirectionType.OUT_LITERAL.equals(direction))
         {
            direction = DirectionType.IN_LITERAL;
         }
         
         
         binding.getModelBindingManager().bind(
               new Data2DataPathModelAdapter2(activity, accessPoints),
               new Data2DataPathWidgetAdapter2(accessPointText, accessPathBrowser,
                     direction));
                     

         binding.bind(new LabeledViewer(accessPointText, apLabel), dataMapping,
               CarnotWorkflowModelPackage.eINSTANCE
                     .getDataMappingType_ApplicationAccessPoint(), new EFeatureAdapter()
               {
                  public Object toModel(EObjectAdapter binding, Object value)
                  {
                     return value == null ? null : ((AccessPointType) value).getId();
                  }

                  public Object fromModel(EObjectAdapter binding, Object value)
                  {
                     for (AccessPointType ap : accessPoints)
                     {
                        if (ap.getId().equals(value))
                        {
                           return ap;
                        }
                     }
                     return null;
                  }
               });

         binding.bind(new LabeledText(accessPathBrowser.getMethodText(), accessPathLabel),
               dataMapping, CarnotWorkflowModelPackage.eINSTANCE.getDataMappingType_ApplicationPath());
      }
      else if (ActivityImplementationType.ROUTE_LITERAL.equals(activity
            .getImplementation()))
      {
         accessPointText.getCombo().setEnabled(
               !AccessPointUtil.isIn(dataMapping.getDirection()));
         accessPathBrowser.setAccessPoint(null, null);

         if (AccessPointUtil.isIn(dataMapping.getDirection()))
         {
            txtId.getText().addModifyListener(new ModifyListener()
            {
               public void modifyText(ModifyEvent e)
               {
                  String value = dataMapping.getApplicationAccessPoint();
                  dataMapping.setApplicationAccessPoint(txtId.getText().getText());
                  accessPointText.getCombo().setText(txtId.getText().getText());

                  // update referencing out mappings
                  List<DataMappingType> dataMappings = ActivityUtil.getDataMappings(activity,
                        !AccessPointUtil.isIn(dataMapping.getDirection()), "default"); //$NON-NLS-1$
                  
                  for (DataMappingType ap : dataMappings)
                  {
                     if (ap.getApplicationAccessPoint() != null
                           && ap.getApplicationAccessPoint().equals(value))
                     {
                        ap.setApplicationAccessPoint(txtId.getText().getText());
                     }
                  }
               }
            });
         }
         else
         {
            binding.getModelBindingManager().bind(activity,
                  CarnotWorkflowModelPackage.eINSTANCE.getActivityType_DataMapping(),
                  new AbstractWidgetAdapter()
                  {
                     public void updateVisuals(Object value)
                     {
                        List<DataMappingType> dataMappings = ActivityUtil.getDataMappings(
                              activity,
                              !AccessPointUtil.isIn(dataMapping.getDirection()),
                              "default"); //$NON-NLS-1$
                        ISelection selection = accessPointText.getSelection();
                        if (!accessPointText.getCombo().isDisposed())
                        {
                           if (null == accessPointText.getContentProvider())
                           {
                              accessPointText
                                    .setContentProvider(new ArrayContentProvider());
                           }
                           accessPointText.setInput(dataMappings);
                           accessPointText.setSelection(selection);
                        }
                     }
                  });

            binding.bind(new LabeledViewer(accessPointText, apLabel), dataMapping,
                  CarnotWorkflowModelPackage.eINSTANCE
                        .getDataMappingType_ApplicationAccessPoint(),
                  new EFeatureAdapter()
                  {
                     public Object toModel(EObjectAdapter binding, Object value)
                     {
                        if (value instanceof DataMappingType)
                        {
                           return ((DataMappingType) value).getId();
                        }
                        return null;
                     }

                     public Object fromModel(EObjectAdapter binding, Object value)
                     {
                        List<DataMappingType> dataMappings = ActivityUtil.getDataMappings(
                              activity,
                              !AccessPointUtil.isIn(dataMapping.getDirection()),
                              "default"); //$NON-NLS-1$
                        for (DataMappingType ap : dataMappings)
                        {
                           if (ap.getId().equals(value))
                           {
                              return ap;
                           }
                        }
                        return null;
                     }
                  });
         }
      }
      else
      {
         accessPointText.getCombo().setEnabled(false);
         accessPointText.setSelection(null);
         accessPathBrowser.setAccessPoint(null, null);
      }

      if (ag.carnot.base.StringUtils.isEmpty(dataMapping.getName()))
      {
         dataMapping.setName(dataMapping.getId());
      }

      // this mapping must be last!!!      
      binding.bind(txtName, element, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
      binding.bind(txtId, element, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id());

      txtName.getText().addModifyListener(listener);
   }

   private void updateAccessPoints()
   {
      accessPoints.clear();
      accessPoints.addAll(ActivityUtil.getAccessPoints(activity,
            DirectionType.IN_LITERAL == dataMapping.getDirection(), dataMapping.getContext()));
   }

   private DataType getData()
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
            return (DataType) model;
         }
      }
      return null;
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         manager.updateButtons(getModelElement(), buttons);
         
         refreshAccessPoint();
      }
      super.setVisible(visible);
   }

   public void refreshAccessPoint()
   {
      if (dataMapping == null)
      {
         dataMapping = (DataMappingType) getModelElement();
      }

      if (!dataMapping.getContext().equals(dataMappingContext))
      {
         loadFieldsFromElement(null, dataMapping);
      }

      // workaround to preserve selection when id is changed
      // (rpi: reintroduced this code for CRNT-19734 - substituted with Surge POC merge)

      String ap = dataMapping.getApplicationAccessPoint();
      accessPointText.refresh();
      dataMapping.setApplicationAccessPoint(ap);
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      buttons = manager.createButtons(parent);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      txtName = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_Name);
      txtName.setTextLimit(80);
      txtId = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);
      txtId.setTextLimit(80);      

      autoIdButton = FormBuilder.createCheckBox(composite, Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION);
      autoIdButton.setSelection(autoIdButtonValue);
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);

      dataLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.LB_Data);
      dataText = new ComboViewer(FormBuilder.createCombo(composite));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));

      dataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
    		  Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), composite,
    		  Diagram_Messages.LB_DataPath, supportsPrimitiveBrowsing());

      apLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
    		  Diagram_Messages.LB_AccessPoint);
      accessPointText = new ComboViewer(FormBuilder.createCombo(composite));
      accessPointText.setSorter(new ViewerSorter());
      accessPointText.setContentProvider(new ArrayContentProvider());
      accessPointText.setLabelProvider(new EObjectLabelProvider(getEditor()));

      accessPathLabel = FormBuilder.createLabelWithRightAlignedStatus(composite,
    		  Diagram_Messages.LB_AccessPointPath);
      accessPathBrowser = new AccessPathBrowserComposite(getEditor(), composite,
    		  Diagram_Messages.LB_AccessPointPath, supportsPrimitiveBrowsing());

      return composite;
   }
   
	private boolean supportsPrimitiveBrowsing() {
		boolean browsePrimAllowed = true;
		if (getElement().getAdapter(IModelElement.class) != null) {
			IModelElement modelElement = (IModelElement) getElement()
					.getAdapter(IModelElement.class);
			if (modelElement instanceof DataMappingType) {
				DataMappingType dmt = (DataMappingType) modelElement;
				ActivityType act = (ActivityType) dmt.eContainer();
				if (act.getApplication() != null) {
					ApplicationType app = (ApplicationType) act
							.getApplication();
					if (app.isInteractive()) {
						if (app.getContext() != null
								&& !app.getContext().isEmpty()) {
							ContextType contextType = app.getContext().get(0);
							if (contextType.getType() != null) {
								if (contextType.getType().getId()
										.equalsIgnoreCase("externalWebApp")) { //$NON-NLS-1$
									browsePrimAllowed = false;
								}
							}
						}
					}
				}
			}
		}
		return browsePrimAllowed;
	}

   private void computeDataMappingId(final DataMappingType dataMapping, ActivityType activity)
   {
      DataType data = dataMapping.getData();
      if (data != null)
      {
         String mappingId = StringUtils.isEmpty(data.getId())
               ? dataMapping.getDirection().toString().toLowerCase()
               : data.getId();
         IdFactory idFactory = new IdFactory(mappingId, null,
               CarnotWorkflowModelPackage.eINSTANCE.getDataMappingType(),
               CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id(), null);
         List<DataMappingType> mappings = activity.getDataMapping();
         List<DataMappingType> idDomain = CollectionUtils.newList(mappings.size());
         for (DataMappingType mapping : mappings)
         {
            if (dataMapping != mapping && dataMapping.getDirection().equals(mapping.getDirection()))
            {
               idDomain.add(mapping);
            }
         }
         idFactory.computeNames(idDomain, false);
         dataMapping.setName(idFactory.getId());         
      }      
      }
}