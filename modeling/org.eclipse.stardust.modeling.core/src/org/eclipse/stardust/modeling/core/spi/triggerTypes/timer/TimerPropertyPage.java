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
package org.eclipse.stardust.modeling.core.spi.triggerTypes.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.NumericFieldVerifier;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.DefaultModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.utils.TimeAttrUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.gface.date.DatePickerCombo;

import ag.carnot.base.StringUtils;
import ag.carnot.reflect.Reflect;
import ag.carnot.workflow.model.PredefinedConstants;

public class TimerPropertyPage extends DefaultModelElementPropertyPage
{
   private static final String DAYS = Diagram_Messages.DAYS; 

   private static final String MONTHS = Diagram_Messages.MONTHS; 

   private static final String YEARS = Diagram_Messages.YEARS; 

   private DatePickerCombo timestampStartCombo;
   
   private Text hoursStartTimestampText;

   private Text minutesStartTimestampText;

   private Text secondsStartTimestampText;

   private Button periodicalButton;
   
   private final Text[] txtPeriodParts = new Text[6];
   
   private DatePickerCombo timestampStopCombo;
   
   private Text hoursStopTimestampText;

   private Text minutesStopTimestampText;

   private Text secondsStopTimestampText;
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement node)
   {
      if (node instanceof IExtensibleElement)
      {
         IExtensibleElement eNode = (IExtensibleElement) node;
         
         Calendar startTime = null;
         String tsStart = AttributeUtil.getAttributeValue(eNode,
               PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT);
         if ( !StringUtils.isEmpty(tsStart))
         {
            startTime = Calendar.getInstance();
            startTime.setTime(new Date(Long.parseLong(tsStart)));

         }
         initTmestamp(startTime, timestampStartCombo, hoursStartTimestampText,
               minutesStartTimestampText, secondsStartTimestampText);

         String tsPeriodicity = AttributeUtil.getAttributeValue(eNode,
               PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT);
         if ( !StringUtils.isEmpty(tsPeriodicity))
         {
            // TODO
            enablePeriodicity(true);
            
            periodicalButton.setSelection(true);
            AttributeType attrPeriod = AttributeUtil.getAttribute(eNode,
                  PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT);
            if (null != attrPeriod)
            {
               initPeriod(node);
            }

            Calendar stopTime = null;
            String tsStop = AttributeUtil.getAttributeValue(eNode,
                  PredefinedConstants.TIMER_TRIGGER_STOP_TIMESTAMP_ATT);
            if ( !StringUtils.isEmpty(tsStop))
            {
               stopTime = Calendar.getInstance();
               stopTime.setTime(new Date(Long.parseLong(tsStop)));
            }
            initTmestamp(stopTime, timestampStopCombo, hoursStopTimestampText,
                  minutesStopTimestampText, secondsStopTimestampText);
         }
         else
         {
            periodicalButton.setSelection(false);
            enablePeriodicity(false);
            
            initTmestamp(null, timestampStopCombo, hoursStopTimestampText,
                  minutesStopTimestampText, secondsStopTimestampText);
         }
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      updateTimestampAttr((IExtensibleElement) element,
            PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT, timestampStartCombo,
            hoursStartTimestampText, minutesStartTimestampText, secondsStartTimestampText);
      
      if (periodicalButton.getSelection())
      {
         updatePeriodAttribute((IExtensibleElement) element);

         updateTimestampAttr((IExtensibleElement) element,
               PredefinedConstants.TIMER_TRIGGER_STOP_TIMESTAMP_ATT, timestampStopCombo,
               hoursStopTimestampText, minutesStopTimestampText, secondsStopTimestampText);
      }
      else
      {
         TimeAttrUtils.updatePeriodAttr((IExtensibleElement) element,
               PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT, (Text[]) null);
         updateTimestampAttr((IExtensibleElement) element,
               PredefinedConstants.TIMER_TRIGGER_STOP_TIMESTAMP_ATT, null, null, null,
               null);
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 5);
      FormBuilder.createLabel(composite, Diagram_Messages.LB_StartTimestamp); 

      this.timestampStartCombo = new DatePickerCombo(composite, SWT.BORDER);
      Calendar timestampStartCalendar = Calendar.getInstance();
      timestampStartCalendar.roll(Calendar.YEAR, 0);
      Date timestampStartDate = timestampStartCalendar.getTime();
      timestampStartCombo.setDate(timestampStartDate);
      timestampStartCombo
            .setDateFormat(new SimpleDateFormat(Diagram_Messages.SIMPLE_DATE_FORMAT, Locale.GERMANY)); 
      timestampStartCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

      hoursStartTimestampText = FormBuilder.createText(composite);
      hoursStartTimestampText.setTextLimit(2);
      GridData gdHoursStartTimestamp = new GridData();
      gdHoursStartTimestamp.widthHint = FormBuilder.getTextSize(composite, 4);
      hoursStartTimestampText.setLayoutData(gdHoursStartTimestamp);
      hoursStartTimestampText.addVerifyListener(new NumericFieldVerifier(0, 23));

      minutesStartTimestampText = FormBuilder.createText(composite);
      minutesStartTimestampText.setTextLimit(2);
      GridData gdMinutesStartTimestamp = new GridData();
      gdMinutesStartTimestamp.widthHint = FormBuilder.getTextSize(composite, 4);
      minutesStartTimestampText.setLayoutData(gdMinutesStartTimestamp);
      minutesStartTimestampText.addVerifyListener(new NumericFieldVerifier(0, 59));

      secondsStartTimestampText = FormBuilder.createText(composite);
      secondsStartTimestampText.setTextLimit(2);
      GridData gdSecondsStartTimestamp = new GridData();
      gdSecondsStartTimestamp.widthHint = FormBuilder.getTextSize(composite, 4);
      secondsStartTimestampText.setLayoutData(gdSecondsStartTimestamp);
      secondsStartTimestampText.addVerifyListener(new NumericFieldVerifier(0, 59));

      FormBuilder.createLabel(composite, Diagram_Messages.LB_Periodical); 
      this.periodicalButton = FormBuilder.createCheckBox(composite, "", 4); //$NON-NLS-1$

      FormBuilder.createLabel(composite, Diagram_Messages.LB_Periodicity);
      createConstantComposite(composite);

      FormBuilder.createLabel(composite, Diagram_Messages.LB_StopTimestamp); 
      this.timestampStopCombo = new DatePickerCombo(composite, SWT.BORDER);
      Calendar timestampStopCalendar = Calendar.getInstance();
      timestampStopCalendar.roll(Calendar.YEAR, 0);
      Date timestampStopDate = timestampStopCalendar.getTime();
      timestampStopCombo.setDate(timestampStopDate);
      timestampStopCombo
            .setDateFormat(new SimpleDateFormat(Diagram_Messages.SIMPLE_DATE_FORMAT, Locale.GERMANY)); 
      timestampStopCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

      hoursStopTimestampText = FormBuilder.createText(composite, SWT.BORDER);
      hoursStopTimestampText.setTextLimit(2);
      GridData gdHoursStopTimestamp = new GridData();
      gdHoursStopTimestamp.widthHint = FormBuilder.getTextSize(composite, 4);
      hoursStopTimestampText.setLayoutData(gdHoursStopTimestamp);
      hoursStopTimestampText.addVerifyListener(new NumericFieldVerifier(0, 23));

      minutesStopTimestampText = FormBuilder.createText(composite, SWT.BORDER);
      minutesStopTimestampText.setTextLimit(2);
      GridData gdMinutesStopTimestamp = new GridData();
      gdMinutesStopTimestamp.widthHint = FormBuilder.getTextSize(composite, 4);
      minutesStopTimestampText.setLayoutData(gdMinutesStopTimestamp);
      minutesStopTimestampText.addVerifyListener(new NumericFieldVerifier(0, 59));

      secondsStopTimestampText = FormBuilder.createText(composite, SWT.BORDER);
      secondsStopTimestampText.setTextLimit(2);
      GridData gdSecondsStopTimestamp = new GridData();
      gdSecondsStopTimestamp.widthHint = FormBuilder.getTextSize(composite, 4);
      secondsStopTimestampText.setLayoutData(gdSecondsStopTimestamp);
      secondsStopTimestampText.addVerifyListener(new NumericFieldVerifier(0, 59));
      
      periodicalButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent e)
         {
            enablePeriodicity(periodicalButton.getSelection());
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {
            enablePeriodicity(periodicalButton.getSelection());
         }
      });

      return composite;
   }

   private void createConstantComposite(Composite parent)
   {
      Composite pnlPeriod = FormBuilder.createComposite(parent, 12, 4);
      pnlPeriod.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData(4));
      GridLayout periodGridLayout = new GridLayout(12, false);
      periodGridLayout.marginWidth = 0;
      periodGridLayout.marginHeight = 0;
      pnlPeriod.setLayout(periodGridLayout);

      GridData gdPeriod = new GridData();
      gdPeriod.widthHint = FormBuilder.getTextSize(parent, 4);

      createYearsText(pnlPeriod, gdPeriod);
      FormBuilder.createLabel(pnlPeriod, YEARS);

      createMonthText(pnlPeriod, gdPeriod);
      FormBuilder.createLabel(pnlPeriod, MONTHS);

      createDaysText(pnlPeriod, gdPeriod);
      FormBuilder.createLabel(pnlPeriod, DAYS);

      createHoursText(pnlPeriod, gdPeriod);
      FormBuilder.createLabel(pnlPeriod, "hh"); //$NON-NLS-1$

      createMinText(pnlPeriod, gdPeriod);
      FormBuilder.createLabel(pnlPeriod, "mm"); //$NON-NLS-1$

      createSecText(pnlPeriod, gdPeriod);
      FormBuilder.createLabel(pnlPeriod, "ss"); //$NON-NLS-1$

   }

   private void createYearsText(Composite parent, GridData gdPeriod)
   {
      txtPeriodParts[0] = FormBuilder.createText(parent);
      txtPeriodParts[0].setTextLimit(3);
      txtPeriodParts[0].setLayoutData(gdPeriod);
      txtPeriodParts[0].addVerifyListener(new NumericFieldVerifier(0, 999));
   }

   private void createMonthText(Composite parent, GridData gdPeriod)
   {
      txtPeriodParts[1] = FormBuilder.createText(parent);
      txtPeriodParts[1].setTextLimit(2);
      txtPeriodParts[1].setLayoutData(gdPeriod);
      txtPeriodParts[1].addVerifyListener(new NumericFieldVerifier(0, 11));
   }

   private void createDaysText(Composite parent, GridData gdPeriod)
   {
      txtPeriodParts[2] = FormBuilder.createText(parent);
      txtPeriodParts[2].setTextLimit(3);
      txtPeriodParts[2].setLayoutData(gdPeriod);
      txtPeriodParts[2].addVerifyListener(new NumericFieldVerifier(0, 365));
   }

   private void createHoursText(Composite parent, GridData gdPeriod)
   {
      txtPeriodParts[3] = FormBuilder.createText(parent);
      txtPeriodParts[3].setTextLimit(2);
      txtPeriodParts[3].setLayoutData(gdPeriod);
      txtPeriodParts[3].addVerifyListener(new NumericFieldVerifier(0, 23));
   }

   private void createMinText(Composite parent, GridData gdPeriod)
   {
      txtPeriodParts[4] = FormBuilder.createText(parent);
      txtPeriodParts[4].setTextLimit(2);
      txtPeriodParts[4].setLayoutData(gdPeriod);
      txtPeriodParts[4].addVerifyListener(new NumericFieldVerifier(0, 59));
   }

   private void createSecText(Composite parent, GridData gdPeriod)
   {
      txtPeriodParts[5] = FormBuilder.createText(parent);
      txtPeriodParts[5].setTextLimit(2);
      txtPeriodParts[5].setLayoutData(gdPeriod);
      txtPeriodParts[5].addVerifyListener(new NumericFieldVerifier(0, 59));
   }

   private void initPeriod(IModelElement element)
   {
      AttributeType attrPeriod = AttributeUtil.getAttribute((IExtensibleElement) element,
            PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT);
      if (null != attrPeriod)
      {
         TimeAttrUtils.initPeriod(txtPeriodParts, attrPeriod);
      }
   }

   private void updatePeriodAttribute(IExtensibleElement element)
   {
      TimeAttrUtils.updatePeriodAttr(element,
            PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT, txtPeriodParts);
   }
   
   private void enablePeriodicity(boolean enabled)
   {
      for (int i = 0; i < txtPeriodParts.length; i++ )
      {
         if ((null != txtPeriodParts[i]) && !txtPeriodParts[i].isDisposed())
         {
            txtPeriodParts[i].setEnabled(enabled);
         }
      }

      if ( !timestampStopCombo.isDisposed())
      {
         timestampStopCombo.setEnabled(enabled);
      }
      if ( !hoursStopTimestampText.isDisposed())
      {
         hoursStopTimestampText.setEnabled(enabled);
      }
      if ( !minutesStopTimestampText.isDisposed())
      {
         minutesStopTimestampText.setEnabled(enabled);
      }
      if ( !secondsStopTimestampText.isDisposed())
      {
         secondsStopTimestampText.setEnabled(enabled);
      }
   }
   
   private static void initTmestamp(Calendar timeStamp,
         DatePickerCombo dateCombo, Text hours, Text minutes, Text seconds)
   {
      dateCombo.setDate((null != timeStamp) ? timeStamp.getTime() : null);
      hours.setText((null != timeStamp)
            ? Integer.toString(timeStamp.get(Calendar.HOUR_OF_DAY))
            : ""); //$NON-NLS-1$
      minutes.setText((null != timeStamp)
            ? Integer.toString(timeStamp.get(Calendar.MINUTE))
            : ""); //$NON-NLS-1$
      seconds.setText((null != timeStamp)
            ? Integer.toString(timeStamp.get(Calendar.SECOND))
            : ""); //$NON-NLS-1$
   }

   private static void updateTimestampAttr(IExtensibleElement element, String attrName,
         DatePickerCombo dateCombo, Text hours, Text minutes, Text seconds)
   {
      Calendar timeStamp = null;
      if ((null != dateCombo) && (null != dateCombo.getDate()))
      {
         timeStamp = Calendar.getInstance();
         timeStamp.setTime(dateCombo.getDate());
         timeStamp.add(Calendar.HOUR_OF_DAY, TimeAttrUtils.parseShort(hours.getText()));
         timeStamp.add(Calendar.MINUTE, TimeAttrUtils.parseShort(minutes.getText()));
         timeStamp.add(Calendar.SECOND, TimeAttrUtils.parseShort(seconds.getText()));
      }
      
      AttributeUtil.setAttribute(element, attrName,
            Reflect.getAbbreviatedName(Long.TYPE), (null != timeStamp)
                  ? Long.toString(timeStamp.getTime().getTime())
                  : ""); //$NON-NLS-1$
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }   
}