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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.timer;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.spi.IConditionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledViewer;
import org.eclipse.stardust.modeling.common.ui.jface.utils.NumericFieldVerifier;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.AccessPathBrowserComposite;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathModelAdapter2;
import org.eclipse.stardust.modeling.core.ui.Data2DataPathWidgetAdapter2;
import org.eclipse.stardust.modeling.core.utils.TimeAttrUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import ag.carnot.workflow.model.PredefinedConstants;

public class TimerPropertyPage extends AbstractModelElementPropertyPage
      implements IConditionPropertyPage
{
   private static final String DATA_PATH = Diagram_Messages.DATA_PATH;

   private static final String DATA = Diagram_Messages.DATA;

   private static final String CONSTANT_RADIO = Diagram_Messages.CONSTANT_RADIO;

   private static final String DATA_RADIO = Diagram_Messages.DATA_RADIO;

   private static final String USE = Diagram_Messages.USE;

   private static final String SECS = Diagram_Messages.SECS;

   private static final String MINS = Diagram_Messages.MINS;

   private static final String HOURS = Diagram_Messages.HOURS;

   private static final String DAYS = Diagram_Messages.DAYS;

   private static final String MONTHS = Diagram_Messages.MONTHS;

   private static final String YEARS = Diagram_Messages.YEARS;

   private static final String PERIOD = Diagram_Messages.PERIOD;

   private static final int MAX_SECS = 2;

   private static final int MAX_MINS = 2;

   private static final int MAX_HOURS = 2;

   private static final int MAX_DAYS = 3;

   private static final int MAX_MONTHS = 2;

   private static final int MAX_YEARS = 3;

   private Text hoursText;

   private Text daysText;

   private Text monthsText;

   private Text yearsText;

   private Text minText;

   private Text secsText;

   private StackLayout useStackLayout;

   private Composite useComposite;
   private Composite constantComposite;
   private Composite dataComposite;
   private Button dataDataCompButton;

   private Button constantDataCompButton;

   private Button dataConstCompButton;

   private Button constantConstCompButton;

   private LabeledViewer dataLabel;
   private AccessPathBrowserComposite dataPathBrowser;
   private LabeledText dataPathLabel;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      WidgetBindingManager binding = getWidgetBindingManager();
      binding.getModelBindingManager().bind(
         new Data2DataPathModelAdapter2(model, model.getData()),
         new Data2DataPathWidgetAdapter2(dataLabel.getViewer(), dataPathBrowser, DirectionType.IN_LITERAL));

      if (AttributeUtil.getBooleanValue((IExtensibleElement) element,
         PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT))
      {
         selectDataComposite();
      }
      else
      {
         selectConstantComposite();
         AttributeType attrPeriod = AttributeUtil.getAttribute((IExtensibleElement) element,
               CarnotConstants.TIMER_PERIOD_ATT);
         TimeAttrUtils.initPeriod(new Text[] {
            yearsText, monthsText, daysText, hoursText, minText, secsText}, attrPeriod);
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      IExtensibleElement extElement = (IExtensibleElement) element;
      if (AttributeUtil.getAttribute(extElement,
            PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT) == null)
      {
         AttributeUtil.setAttribute(extElement,
               PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT,
               Reflect.getAbbreviatedName(Boolean.class), Boolean.FALSE.toString());
      }
      if (!AttributeUtil.getBooleanValue(extElement,
                  PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT))
      {
         TimeAttrUtils.updatePeriodAttr(extElement, PredefinedConstants.TIMER_PERIOD_ATT,
               new Text[] {yearsText, monthsText, daysText, hoursText, minText, secsText});
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1, 3);
      ((GridLayout) composite.getLayout()).marginHeight = 0;
      ((GridLayout) composite.getLayout()).marginWidth = 0;

      useComposite = createUseComposite(composite);
      createConstantComposite(parent);
      createDataComposite();
      createRadioButtonSelectionListener();
      useStackLayout.topControl = constantComposite;
      return composite;
   }

   private void createConstantComposite(Composite parent)
   {
      constantComposite = FormBuilder.createComposite(useComposite, 13);
      GridLayout constantGridLayout = new GridLayout();
      constantGridLayout.numColumns = 13;
      constantGridLayout.marginWidth = 0;
      constantGridLayout.marginHeight = 0;
      constantComposite.setLayout(constantGridLayout);

      FormBuilder.createLabel(constantComposite, USE);

      Composite constantButtonComposite = FormBuilder.createComposite(constantComposite,
            2, 12);
      ((GridLayout) constantButtonComposite.getLayout()).marginWidth = 0;
      constantButtonComposite.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false,
            false, 12, 1));
      dataConstCompButton = FormBuilder.createRadioButton(constantButtonComposite,
            DATA_RADIO);
      dataConstCompButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1,
            1));

      constantConstCompButton = FormBuilder.createRadioButton(constantButtonComposite,
            CONSTANT_RADIO);
      constantConstCompButton.setSelection(true);
      constantConstCompButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false,
            1, 1));

      GridData gdPeriod = new GridData();
      gdPeriod.widthHint = FormBuilder.getTextSize(parent, 4);

      FormBuilder.createLabel(constantComposite, PERIOD);

      createYearsText(gdPeriod);
      FormBuilder.createLabel(constantComposite, YEARS);

      createMonthText(gdPeriod);
      FormBuilder.createLabel(constantComposite, MONTHS);

      createDaysText(gdPeriod);
      FormBuilder.createLabel(constantComposite, DAYS);

      createHoursText(gdPeriod);
      FormBuilder.createLabel(constantComposite, HOURS);

      createMinText(gdPeriod);
      FormBuilder.createLabel(constantComposite, MINS);

      createSecText(gdPeriod);
      FormBuilder.createLabel(constantComposite, SECS);

   }

   private void createDataComposite()
   {
      dataComposite = FormBuilder.createComposite(useComposite, 3);
      GridLayout dataGridLayout = new GridLayout();
      dataGridLayout.numColumns = 3;
      dataGridLayout.marginWidth = 0;
      dataGridLayout.marginHeight = 0;
      dataComposite.setLayout(dataGridLayout);

      FormBuilder.createLabel(dataComposite, USE);

      dataDataCompButton = FormBuilder.createRadioButton(dataComposite, DATA_RADIO);
      dataDataCompButton.setSelection(true);

      constantDataCompButton = FormBuilder.createRadioButton(dataComposite,
            CONSTANT_RADIO);

      LabelWithStatus label = FormBuilder.createLabelWithRightAlignedStatus(dataComposite, DATA);
      ComboViewer dataText = new ComboViewer(FormBuilder.createCombo(dataComposite));
      dataText.setSorter(new ViewerSorter());
      dataText.setContentProvider(new ArrayContentProvider());
      dataText.setLabelProvider(new EObjectLabelProvider(getEditor()));
      dataText.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
      dataLabel = new LabeledViewer(dataText, label);

      label = FormBuilder.createLabelWithRightAlignedStatus(dataComposite, DATA_PATH);
      dataPathBrowser = new AccessPathBrowserComposite(getEditor(), dataComposite, DATA_PATH, 2);
      dataPathLabel = new LabeledText(dataPathBrowser.getMethodText(), label);
   }

   private Composite createUseComposite(Composite composite)
   {
      final Composite useComposite = FormBuilder.createComposite(composite, 1);
      useComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      useStackLayout = new StackLayout();
      useComposite.setLayout(useStackLayout);
      return useComposite;
   }

   private void createRadioButtonSelectionListener()
   {
      dataConstCompButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            selectDataComposite();
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {
         }
      });

      constantDataCompButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            selectConstantComposite();
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {
         }
      });
   }

   private void selectConstantComposite()
   {
      useStackLayout.topControl = constantComposite;
      useComposite.layout();
      dataDataCompButton.setSelection(true);
      constantDataCompButton.setSelection(false);

      IExtensibleElement element = (IExtensibleElement) getModelElement();

      AttributeUtil.setBooleanAttribute(element,
            PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT,
            false);

      WidgetBindingManager binding = getWidgetBindingManager();
      binding.unbind(dataLabel, element,
         PredefinedConstants.TIMER_CONDITION_DATA_ATT);
      binding.unbind(dataPathLabel, element,
         PredefinedConstants.TIMER_CONDITION_DATA_PATH_ATT);

      AttributeUtil.setAttribute(element,
         PredefinedConstants.TIMER_CONDITION_DATA_ATT, null);
      AttributeUtil.setAttribute(element,
         PredefinedConstants.TIMER_CONDITION_DATA_PATH_ATT, null);
   }

   private void selectDataComposite()
   {
      useStackLayout.topControl = dataComposite;
      useComposite.layout();
      constantConstCompButton.setSelection(true);
      dataConstCompButton.setSelection(false);

      IExtensibleElement element = (IExtensibleElement) getModelElement();

      AttributeUtil.setBooleanAttribute(element,
            PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT,
            true);

      WidgetBindingManager binding = getWidgetBindingManager();
      binding.bind(dataLabel, element,
         PredefinedConstants.TIMER_CONDITION_DATA_ATT,
         ModelUtils.findContainingModel(element),
         CarnotWorkflowModelPackage.eINSTANCE.getModelType_Data());
      binding.bind(dataPathLabel, element,
         PredefinedConstants.TIMER_CONDITION_DATA_PATH_ATT);

      AttributeUtil.setAttribute(element,
         PredefinedConstants.TIMER_PERIOD_ATT, null);
   }

   private void createYearsText(GridData gdPeriod)
   {
      yearsText = FormBuilder.createText(constantComposite);
      yearsText.setTextLimit(MAX_YEARS);
      yearsText.setLayoutData(gdPeriod);
      yearsText.addVerifyListener(new NumericFieldVerifier(0, 999));
   }

   private void createMonthText(GridData gdPeriod)
   {
      monthsText = FormBuilder.createText(constantComposite);
      monthsText.setTextLimit(MAX_MONTHS);
      monthsText.setLayoutData(gdPeriod);
      monthsText.addVerifyListener(new NumericFieldVerifier(0, 11));
   }

   private void createDaysText(GridData gdPeriod)
   {
      daysText = FormBuilder.createText(constantComposite);
      daysText.setTextLimit(MAX_DAYS);
      daysText.setLayoutData(gdPeriod);
      daysText.addVerifyListener(new NumericFieldVerifier(0, 999));
   }

   private void createHoursText(GridData gdPeriod)
   {
      hoursText = FormBuilder.createText(constantComposite);
      hoursText.setTextLimit(MAX_HOURS);
      hoursText.setLayoutData(gdPeriod);
      hoursText.addVerifyListener(new NumericFieldVerifier(0, 23));
   }

   private void createMinText(GridData gdPeriod)
   {
      minText = FormBuilder.createText(constantComposite);
      minText.setTextLimit(MAX_MINS);
      minText.setLayoutData(gdPeriod);
      minText.addVerifyListener(new NumericFieldVerifier(0, 59));
   }

   private void createSecText(GridData gdPeriod)
   {
      secsText = FormBuilder.createText(constantComposite);
      secsText.setTextLimit(MAX_SECS);
      secsText.setLayoutData(gdPeriod);
      secsText.addVerifyListener(new NumericFieldVerifier(0, 59));
   }
}
