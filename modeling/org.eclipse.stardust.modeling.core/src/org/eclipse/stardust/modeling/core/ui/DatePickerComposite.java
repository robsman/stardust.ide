/*******************************************************************************
* Copyright (c) 2015 SunGard CSA LLC and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Barry.Grotjahn (SunGard CSA LLC) - initial API and implementation and/or initial documentation
*******************************************************************************/

package org.eclipse.stardust.modeling.core.ui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.gface.date.DatePickerCombo;

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