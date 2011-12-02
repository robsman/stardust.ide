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
package org.eclipse.stardust.modeling.core.spi.dataTypes.primitive;

import static ag.carnot.base.CollectionUtils.newHashMap;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IDataPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtButtonAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtComboAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.stardust.modeling.core.editors.ui.SwtDatePickerAdapter;
import org.eclipse.stardust.modeling.core.editors.ui.SwtVerifierTextAdapter;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.ui.PrimitiveDataModelAdapter;
import org.eclipse.stardust.modeling.core.ui.PrimitiveDataWidgetAdapter;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.gface.date.DatePickerCombo;

import ag.carnot.workflow.spi.providers.data.java.Type;

/**
 * @author fherinean
 * @version $Revision$
 */

public class PrimitivePropertyPage extends AbstractModelElementPropertyPage
      implements IDataPropertyPage
{
   private static final Type[] TYPES = {
         Type.Calendar, Type.String, Type.Timestamp, Type.Boolean, Type.Byte, Type.Char,
         Type.Double, Type.Float, Type.Integer, Type.Long, Type.Short};

   private ComboViewer typeViewer;

   private Composite valueComposite;

   private Map<Type, Object> valueControlsMap = newHashMap();

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      WidgetBindingManager binding = getWidgetBindingManager();

      // bind types, typeViewer and valueComposites
      binding.getModelBindingManager().bind(
            new PrimitiveDataModelAdapter(ModelUtils.findContainingModel(element), Arrays
                  .asList(TYPES), (IExtensibleElement) element),
            new PrimitiveDataWidgetAdapter(typeViewer, valueComposite, valueControlsMap));

      // bind typeViewer and type attribute of DataType
      binding.getModelBindingManager().bind(
            WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                  CarnotConstants.TYPE_ATT, false), getSwtComboAdapter());

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
            binding.getModelBindingManager().bind(
                  WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                        CarnotConstants.DEFAULT_VALUE_ATT, false),
                  getSwtDatePickerAdapter(datePickerCombo, resetBtn));
         }
         else if (TYPES[i].equals(Type.Boolean))
         {
            Button button = (Button) control;
            binding.getModelBindingManager().bind(
               WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                  // The default value of a primitive is always stored in the model
                  // as of type String and never boolean
                  CarnotConstants.DEFAULT_VALUE_ATT, false),
                  getSwtButtonAdapter(button));
         }
         else
         {
            final Type type = TYPES[i];
            Text text = (Text) control;
            binding.getModelBindingManager().bind(
                  WidgetBindingManager.createModelAdapter((IExtensibleElement) element,
                        CarnotConstants.DEFAULT_VALUE_ATT, false),
                  getSwtVerifierTextAdapter(type, text));
         }
      }

      if (isPredefined(element))
      {
         disableControls();
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

   private SwtComboAdapter getSwtComboAdapter()
   {
      return new SwtComboAdapter(typeViewer.getCombo())
      {
         public void updateControl(Object value)
         {
            super.updateControl(value);

            Type selectedType = (Type) ((IStructuredSelection) typeViewer.getSelection())
                  .getFirstElement();
            selectedType = (selectedType == null) ? Type.String : selectedType;
            Object object = valueControlsMap.get(selectedType);
            ((StackLayout) valueComposite.getLayout()).topControl = object instanceof DatePickerComposite
                  ? ((DatePickerComposite) object).getCalendarComposite()
                  : (Control) object;

            valueComposite.layout();
         }
      };
   }

   private void disableControls()
   {
      typeViewer.getCombo().setEnabled(false);
      for (Iterator iter = valueControlsMap.values().iterator(); iter.hasNext();)
      {
         Object obj = iter.next();
         Control control = obj instanceof DatePickerComposite
               ? ((DatePickerComposite) obj).getCalendarComposite()
               : (Control) obj;

         if (control instanceof Text)
         {
            ((Text) control).setEditable(false);
         }
         else if (control instanceof Button)
         {
            ((Button) control).setEnabled(false);
         }
         else if (control instanceof DatePickerCombo)
         {
            ((DatePickerCombo) control).setEnabled(false);
         }
      }
   }

   private boolean isPredefined(IModelElement element)
   {
      return ((DataType) element).isPredefined();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_SPI_Type);

      typeViewer = new ComboViewer(FormBuilder.createCombo(composite));
      typeViewer.setContentProvider(new ArrayContentProvider());
      typeViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object type)
         {
            return ((Type) type).getId();
         }
      });

      FormBuilder.createLabel(composite, Diagram_Messages.LB_DefaultValue);

      valueComposite = new Composite(composite, SWT.NONE);
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

      DatePickerComposite calendarComposite = createDatePickerComposite();
      
      valueControlsMap.put(TYPES[0], calendarComposite);

      valueControlsMap.put(TYPES[1], FormBuilder.createText(valueComposite));

      DatePickerComposite timestampComposite = createDatePickerComposite();
      valueControlsMap.put(TYPES[2], timestampComposite);

      valueControlsMap.put(TYPES[3], new Button(valueComposite, SWT.CHECK));

      valueControlsMap.put(TYPES[4], FormBuilder.createText(valueComposite));
      ((Text) valueControlsMap.get(TYPES[4]))
            .addVerifyListener(VerifierFactory.byteVerifier);

      valueControlsMap.put(TYPES[5], FormBuilder.createText(valueComposite));
      ((Text) valueControlsMap.get(TYPES[5])).setTextLimit(1);

      valueControlsMap.put(TYPES[6], FormBuilder.createText(valueComposite));
      ((Text) valueControlsMap.get(TYPES[6]))
            .addVerifyListener(VerifierFactory.doubleVerifier);

      valueControlsMap.put(TYPES[7], FormBuilder.createText(valueComposite));
      ((Text) valueControlsMap.get(TYPES[7]))
            .addVerifyListener(VerifierFactory.floatVerifier);

      valueControlsMap.put(TYPES[8], FormBuilder.createText(valueComposite));
      ((Text) valueControlsMap.get(TYPES[8]))
            .addVerifyListener(VerifierFactory.intVerifier);

      valueControlsMap.put(TYPES[9], FormBuilder.createText(valueComposite));
      ((Text) valueControlsMap.get(TYPES[9]))
            .addVerifyListener(VerifierFactory.longVerifier);

      valueControlsMap.put(TYPES[10], FormBuilder.createText(valueComposite));
      ((Text) valueControlsMap.get(TYPES[10]))
            .addVerifyListener(VerifierFactory.shortVerifier);

      return composite;
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
   
   public class DatePickerComposite
   {
      private final Composite calendarComposite;

      private final DatePickerCombo calendarCombo;

      private final Button resetBtn;

      public DatePickerComposite(Composite calendarComposite,
            DatePickerCombo calendarCombo, Button resetBtn)
      {
         this.calendarComposite = calendarComposite;
         this.calendarCombo = calendarCombo;
         this.resetBtn = resetBtn;
      }

      public Button getResetBtn()
      {
         return resetBtn;
      }

      public Composite getCalendarComposite()
      {
         return calendarComposite;
      }

      public DatePickerCombo getCalendarCombo()
      {
         return calendarCombo;
      }
   }
}
