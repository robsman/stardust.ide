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

import static org.eclipse.stardust.common.CollectionUtils.newHashMap;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import com.gface.date.DatePickerCombo;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.*;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.Verifier;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.editors.ui.SwtDatePickerAdapter;
import org.eclipse.stardust.modeling.core.editors.ui.SwtVerifierTextAdapter;
import org.eclipse.stardust.modeling.core.ui.*;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;

/**
 * @author fherinean
 * @version $Revision$
 */
public class DataMappingPropertyPage extends AbstractModelElementPropertyPage
{
   private static final Type[] TYPES = fetchTypes();
      
   protected LabeledText txtId;
   protected LabeledText txtName;
   
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

   private Button isConstantStateButton;
   private Button isDataStateButton;
   private Composite stackLayoutComposite;
   private StackLayout stackLayout;
   
   private Group dataGroup;
   private Group constantGroup;
   private ComboViewer typeViewer;
   private Composite valueComposite;
   private Map<Type, Object> valueControlsMap = newHashMap();
      
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if (GenericUtils.getAutoIdValue())
         {
            String computedId = NameIdUtils.createIdFromName(null, getModelElement());
            txtId.getText().setText(computedId);
         }
      }
   };

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, final IModelElement element)
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

      if (org.eclipse.stardust.common.StringUtils.isEmpty(dataMapping.getName()))
      {
         dataMapping.setName(dataMapping.getId());
      }

      // this mapping must be last!!!      
      binding.bind(txtName, element, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Name());
      txtName.getText().addModifyListener(listener);
      
      binding.bind(txtId, element, CarnotWorkflowModelPackage.eINSTANCE.getIIdentifiableElement_Id());
                  
      
      if(DirectionType.IN_LITERAL.equals(dataMapping.getDirection())
            && PredefinedConstants.DEFAULT_CONTEXT.equals(dataMappingContext))
      {
         // bind types, typeViewer and valueComposites
         BindingManager mgr = binding.getModelBindingManager();
         mgr.bind(
               new DataMappingModelAdapter(ModelUtils.findContainingModel(element), Arrays
                     .asList(TYPES), (DataMappingType) element),
                  new PrimitiveDataWidgetAdapter(typeViewer, valueComposite, valueControlsMap)
                  {
                     public void updateModel(Object value)
                     {
                        super.updateModel(value);
                     }
                  }
               );      
         
         // bind valueComposites and value attribute of DataType
         for (int i = 0; i < TYPES.length; i++)
         {
            Object control = valueControlsMap.get(TYPES[i]);
            if ((TYPES[i].equals(Type.Timestamp)) || (TYPES[i].equals(Type.Calendar)))
            {
               Button resetBtn = null;
               DatePickerCombo datePickerCombo;
               datePickerCombo = ((DatePickerComposite) control).getCalendarCombo();
               resetBtn = ((DatePickerComposite) control).getResetBtn();
   
               mgr.bind(
                     new DataMappingModelAdapter(ModelUtils.findContainingModel(element), 
                           TYPES[i], (DataMappingType) element),
                           getSwtDatePickerAdapter(datePickerCombo, resetBtn));
            }
            else if (TYPES[i].equals(Type.Boolean))
            {
               Button button = (Button) control;
               mgr.bind(
                     new DataMappingModelAdapter(ModelUtils.findContainingModel(element), 
                           TYPES[i], (DataMappingType) element),
                           getSwtButtonAdapter(button));
            }
            else
            {
               final Type type = TYPES[i];
               Text text = (Text) control;
               mgr.bind(
                     new DataMappingModelAdapter(ModelUtils.findContainingModel(element), 
                           TYPES[i], (DataMappingType) element),
                           getSwtVerifierTextAdapter(type, text));
            }
         }
         
         if(dataMapping.getData() != null)
         {
            isConstantStateButton.setSelection(false);
            isDataStateButton.setSelection(true);         
            dataGroup.setVisible(true);
         }  
         else if(dataMapping.getData() == null 
               && dataMapping.getDataPath() != null
               && dataMapping.getDataPath().startsWith("(")) //$NON-NLS-1$
         {
            isConstantStateButton.setSelection(true);
            isDataStateButton.setSelection(false);         
            constantGroup.setVisible(true);
            
            String constantString = dataMapping.getDataPath();
            Object type = getType(constantString, false);
            
            typeViewer.setSelection(new StructuredSelection(type));
            String constantValue = (String) getType(constantString, true);
            
            Object control = valueControlsMap.get(type);
            if (type.equals(Type.Timestamp) || type.equals(Type.Calendar))
            {            
               Date dateValue = new Date();
               try
               {
                  dateValue.parse(constantValue);
               }
               catch (Exception e)
               {
               }
               
               DatePickerCombo datePickerCombo = ((DatePickerComposite) control).getCalendarCombo();            
               datePickerCombo.setDate(dateValue);
            }
            else if (type.equals(Type.Boolean))
            {
               boolean booleanValue = false; 
               try
               {
                  booleanValue = Boolean.parseBoolean(constantValue);
               }
               catch (Exception e)
               {
               }
               
               Button button = (Button) control;
               button.setSelection(booleanValue);
            }
            else
            {
               if (type.equals(Type.Double) || type.equals(Type.Float))
               {            
                  constantValue = constantValue.replace(".", ","); //$NON-NLS-1$ //$NON-NLS-2$
               }            
               
               Text text = (Text) control;
               text.setText(constantValue != null ? constantValue : ""); //$NON-NLS-1$
            }
         }
         else
         {
            dataGroup.setVisible(false);            
            constantGroup.setVisible(false);               
         }
      }
      else
      {
         isConstantStateButton.setVisible(false);
         isDataStateButton.setVisible(false);         
         dataGroup.setVisible(true);         
      }
   }
   
   private SwtVerifierTextAdapter getSwtVerifierTextAdapter(final Type type, Text text)
   {
      return new SwtVerifierTextAdapter(text)
      {
         public void updateControl(Object value)
         {
            Type selectedType = (Type) ((IStructuredSelection) typeViewer
                  .getSelection()).getFirstElement();
            if (type.equals(selectedType))
            {
               super.updateControl(value);
            }
         }
      };
   }

   private SwtButtonAdapter getSwtButtonAdapter(Button b)
   {
      return new SwtButtonAdapter(b)
      {
         public void bind(IBindingMediator manager)
         {
            super.bind(manager);
            final Button button = (Button) getWidget();
            button.addSelectionListener(new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  updateModel(button.getSelection()
                        ? Boolean.TRUE.toString()
                        : Boolean.FALSE.toString());
               }
            });
         }

         public void updateControl(Object value)
         {
            if (value != null && !(value instanceof Boolean))
            {
               value = "true".equalsIgnoreCase(value.toString()) ? Boolean.TRUE : Boolean.FALSE; //$NON-NLS-1$
            }
            Type selectedType = (Type) ((IStructuredSelection) typeViewer
                  .getSelection()).getFirstElement();
            if (Type.Boolean.equals(selectedType))
            {
               super.updateControl(value);
            }
         }
      };
   }

   private SwtDatePickerAdapter getSwtDatePickerAdapter(DatePickerCombo datePickerCombo, Button resetBtn)
   {
      return new SwtDatePickerAdapter(datePickerCombo, resetBtn)
      {
         public void updateControl(Object value)
         {
            Type selectedType = (Type) ((IStructuredSelection) typeViewer.getSelection())
                  .getFirstElement();
            if (Type.Calendar.equals(selectedType) || Type.Timestamp.equals(selectedType))
            {
               super.updateControl(value);
            }
         }
      };
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

      boolean autoIdButtonValue = GenericUtils.getAutoIdValue();
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }

      createConstantStateGroup(composite);
      
      stackLayoutComposite = new Composite(composite, SWT.NONE);
      GridData gridData = new GridData(SWT.FILL, SWT.NONE, false, false);
      gridData.horizontalSpan = 2;
      stackLayoutComposite.setLayoutData(gridData);
      stackLayout = new StackLayout();
      stackLayoutComposite.setLayout(stackLayout);
            
      createDataGroup(stackLayoutComposite);
      stackLayout.topControl = dataGroup;
      createConstantGroup(stackLayoutComposite);
      stackLayout.topControl = constantGroup;
            
      return composite;
   }

   private void createConstantStateGroup(Composite composite)
   {
      isConstantStateButton = FormBuilder.createRadioButton(composite,
            Diagram_Messages.TXT_DataMapping_Constant);
      isConstantStateButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            if(isConstantStateButton.getSelection())
            {
               resetElement();
               dataGroup.setVisible(false);                              
               constantGroup.setVisible(true);
               
            }
         }
      });
      
      isDataStateButton = FormBuilder.createRadioButton(composite,
            Diagram_Messages.TXT_DataMapping_DataBound);
      isDataStateButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            if(isDataStateButton.getSelection())
            {
               resetElement();
               
               constantGroup.setVisible(false);               
               dataGroup.setVisible(true);               
            }
         }
      });      
   }   
   
   private void resetElement()
   {      
      typeViewer.setSelection(null);
      for (int i = 0; i < TYPES.length; i++)
      {
         Object control = valueControlsMap.get(TYPES[i]);
         if ((TYPES[i].equals(Type.Timestamp)) || (TYPES[i].equals(Type.Calendar)))
         {
            DatePickerCombo datePickerCombo = ((DatePickerComposite) control).getCalendarCombo();
            datePickerCombo.setData(null);
         }
         else if (TYPES[i].equals(Type.Boolean))
         {
            Button button = (Button) control;
            button.setSelection(false);
         }
         else
         {
            Text text = (Text) control;
            text.setText(""); //$NON-NLS-1$
         }
      }
      
      dataMapping.setData(null);
      dataMapping.setDataPath(null);
      dataMapping.setApplicationPath(null);
      dataMapping.setApplicationAccessPoint(null);      
   }
   
   private void createDataGroup(Composite composite)
   {
      dataGroup = FormBuilder.createGroup(composite, "", 2); //$NON-NLS-1$
      GridData gridDataGroup = new GridData(SWT.FILL, SWT.NONE, false, false);
      gridDataGroup.horizontalSpan = 2;
      dataGroup.setLayoutData(gridDataGroup);
      
      dataLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
            Diagram_Messages.LB_Data);
      dataText = new ComboViewer(FormBuilder.createCombo(dataGroup));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));

      dataPathLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
    		  Diagram_Messages.LB_DataPath);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), dataGroup,
    		  Diagram_Messages.LB_DataPath, supportsPrimitiveBrowsing());

      apLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
    		  Diagram_Messages.LB_AccessPoint);
      accessPointText = new ComboViewer(FormBuilder.createCombo(dataGroup));
      accessPointText.setSorter(new ViewerSorter());
      accessPointText.setContentProvider(new ArrayContentProvider());
      accessPointText.setLabelProvider(new EObjectLabelProvider(getEditor()));

      accessPathLabel = FormBuilder.createLabelWithRightAlignedStatus(dataGroup,
    		  Diagram_Messages.LB_AccessPointPath);
      accessPathBrowser = new AccessPathBrowserComposite(getEditor(), dataGroup,
    		  Diagram_Messages.LB_AccessPointPath, supportsPrimitiveBrowsing());
   }

   private void createConstantGroup(Composite composite)
   {
      constantGroup = FormBuilder.createGroup(composite, "", 2); //$NON-NLS-1$
      GridData gridDataGroup = new GridData(SWT.FILL, SWT.NONE, false, false);
      gridDataGroup.horizontalSpan = 2;
      constantGroup.setLayoutData(gridDataGroup);
      
      FormBuilder.createLabel(constantGroup, Diagram_Messages.LB_SPI_Type);

      typeViewer = new ComboViewer(FormBuilder.createCombo(constantGroup));
      typeViewer.setContentProvider(new ArrayContentProvider());
      typeViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object type)
         {
            return ((Type) type).getId();
         }
      });
      
      FormBuilder.createLabel(constantGroup, Diagram_Messages.LB_DefaultValue);

      valueComposite = new Composite(constantGroup, SWT.NONE);
      GridData gd = new GridData();
      gd.grabExcessHorizontalSpace = true;
      if (0 < typeViewer.getCombo().getBounds().height)
      {
         gd.heightHint = typeViewer.getCombo().getBounds().height;
      }
      gd.horizontalAlignment = SWT.FILL;
      valueComposite.setLayoutData(gd);
      
      StackLayout layout = new StackLayout();
      valueComposite.setLayout(layout);

      valueControlsMap.put(TYPES[0], createDatePickerComposite());
      valueControlsMap.put(TYPES[1], FormBuilder.createText(valueComposite));
      valueControlsMap.put(TYPES[2], createDatePickerComposite());
      valueControlsMap.put(TYPES[3], new Button(valueComposite, SWT.CHECK));
      valueControlsMap.put(TYPES[4], createVerifiedText(VerifierFactory.byteVerifier));
      valueControlsMap.put(TYPES[5], createLimitedText(1));
      valueControlsMap.put(TYPES[6], createVerifiedText(VerifierFactory.doubleVerifier));
      valueControlsMap.put(TYPES[7], createVerifiedText(VerifierFactory.floatVerifier));
      valueControlsMap.put(TYPES[8], createVerifiedText(VerifierFactory.intVerifier));
      valueControlsMap.put(TYPES[9], createVerifiedText(VerifierFactory.longVerifier));
      valueControlsMap.put(TYPES[10], createVerifiedText(VerifierFactory.shortVerifier));      
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
	
   private static Type[] fetchTypes()
   {
      List<Type> tmp = Type.getTypes();
      List<Type> types = CollectionUtils.newArrayList();
      for(Type type : tmp)
      {
         if(!type.equals(Type.Enumeration))
         {
            types.add(type);
         }
      }
      
      Type[] array = types.toArray(new Type[types.size()]);
      Arrays.sort(array);
      return array;
   }	
   
   private Text createVerifiedText(Verifier verifier)
   {
      Text text = FormBuilder.createText(valueComposite);
      text.addVerifyListener(verifier);
      return text;
   }

   private Text createLimitedText(int limit)
   {
      Text text = FormBuilder.createText(valueComposite);
      text.setTextLimit(limit);
      return text;
   }

   private DatePickerComposite createDatePickerComposite()
   {
      Composite calendarComposite = new Composite(valueComposite, SWT.NONE);
      GridData gdCal = new GridData();
      gdCal.grabExcessHorizontalSpace = true;
      gdCal.horizontalAlignment = SWT.FILL;
      calendarComposite.setLayoutData(gdCal);
      GridLayout gl = new GridLayout();
      gl.numColumns = 2;
      gl.marginHeight = 0;
      gl.marginWidth = 0;
      calendarComposite.setLayout(gl);
      GridData gdDP = new GridData();
      gdDP.grabExcessHorizontalSpace = true;
      gdDP.horizontalAlignment = SWT.FILL;

      final DatePickerCombo calendarCombo = new DatePickerCombo(calendarComposite, SWT.BORDER);
      calendarCombo.setLayoutData(gdDP);
      calendarCombo.setEditable(false);
      calendarCombo.setDateFormat(new SimpleDateFormat(Diagram_Messages.SIMPLE_DATE_FORMAT,
            Locale.GERMANY));

      Button resetButton = new Button(calendarComposite, SWT.NONE);
      resetButton.setText(Diagram_Messages.BUT_RESET);
      GridData gdBtn = new GridData();
      gdBtn.grabExcessVerticalSpace = true;
      resetButton.setLayoutData(gdBtn);
      DatePickerComposite datePickerComposite = new DatePickerComposite(calendarComposite, calendarCombo, resetButton);
      return datePickerComposite;
   }
   
   private Object getType(String path, boolean returnValue)
   {
      if(StringUtils.isEmpty(path))
      {
         return null;
      }
      
      String tmpType = null;
      String constantValue = null;            
      path = path.trim();
      if(path.startsWith("(")) //$NON-NLS-1$
      {
         path = path.substring(1, path.length());
         String[] split = path.split("\\)"); //$NON-NLS-1$
         tmpType = split[0];
         if(split.length > 1)
         {
            constantValue = split[1];
            constantValue = constantValue.trim();      
         }
         
         if(returnValue)
         {
            return constantValue;
         }
         
         List<Type> tmp = Type.getTypes();
         for(Type type : tmp)
         {
            if(type.getId().equals(tmpType))
            {
               return type;
            }
         }
      }
      
      return null;
   }   
}