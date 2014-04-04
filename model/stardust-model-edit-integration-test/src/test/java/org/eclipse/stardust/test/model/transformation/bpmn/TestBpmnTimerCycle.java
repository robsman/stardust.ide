/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.test.model.transformation.bpmn;

import java.util.Calendar;

import javax.xml.datatype.Duration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.stardust.model.bpmn2.transform.util.BpmnTimerCycle;

public class TestBpmnTimerCycle  extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite(TestBpmnTimerCycle.class);
        return suite;
    }

    public void testFromLexicalValueDurationOnly() {
        String lex = "P3Y6M4DT12H30M5S";
        BpmnTimerCycle cycle = BpmnTimerCycle.getCycle(lex);

        Duration duration = cycle.getCycleDuration();

        assertNull(cycle.getStartDate());
        assertEquals(-1, cycle.getRecurrences());

        assertEquals(3, duration.getYears());
        assertEquals(6, duration.getMonths());
        assertEquals(4, duration.getDays());
        assertEquals(12, duration.getHours());
        assertEquals(30, duration.getMinutes());
        assertEquals(5, duration.getSeconds());

    }

    public void testFromLexicalValueDurationWithRepeatSign() {
        String lex = "R/P3Y6M4DT12H30M5S";
        BpmnTimerCycle cycle = BpmnTimerCycle.getCycle(lex);
        Duration duration = cycle.getCycleDuration();

        assertNull(cycle.getStartDate());
        assertEquals(-1, cycle.getRecurrences());

        assertEquals(3, duration.getYears());
        assertEquals(6, duration.getMonths());
        assertEquals(4, duration.getDays());
        assertEquals(12, duration.getHours());
        assertEquals(30, duration.getMinutes());
        assertEquals(5, duration.getSeconds());
    }

    public void testFromLexicalValueDurationWithStartdate() {
        String lex = "2012-09-01T11:00:00/P1Y2M1DT20H30M";
        BpmnTimerCycle cycle = BpmnTimerCycle.getCycle(lex);
        Duration duration = cycle.getCycleDuration();

        Calendar cal = Calendar.getInstance();
        cal.setTime(cycle.getStartDate());

        assertEquals(2012, cal.get(Calendar.YEAR));
        assertEquals(9, cal.get(Calendar.MONTH)+1);
        assertEquals(1, cal.get(Calendar.DATE));
        assertEquals(11, cal.get(Calendar.HOUR));

        assertEquals(-1, cycle.getRecurrences());

        assertEquals(1, duration.getYears());
        assertEquals(2, duration.getMonths());
        assertEquals(1, duration.getDays());
        assertEquals(20, duration.getHours());
        assertEquals(30, duration.getMinutes());
    }

    public void testFromLexicalValueDurationWithNRepeatsAndStartdate() {
        String lex = "R5/2012-09-01T11:00:00/P1Y2M1DT20H30M";
        BpmnTimerCycle cycle = BpmnTimerCycle.getCycle(lex);
        Duration duration = cycle.getCycleDuration();

        Calendar cal = Calendar.getInstance();
        cal.setTime(cycle.getStartDate());

        assertEquals(2012, cal.get(Calendar.YEAR));
        assertEquals(9, cal.get(Calendar.MONTH)+1);
        assertEquals(1, cal.get(Calendar.DATE));
        assertEquals(11, cal.get(Calendar.HOUR));

        assertEquals(5, cycle.getRecurrences());

        assertEquals(1, duration.getYears());
        assertEquals(2, duration.getMonths());
        assertEquals(1, duration.getDays());
        assertEquals(20, duration.getHours());
        assertEquals(30, duration.getMinutes());
    }


}
