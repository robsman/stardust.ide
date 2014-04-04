/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ITpearls AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/**
 * @author Simon Nikles
 *
 */
public class BpmnTimerCycle {
    // REGEX_RECURRENCE: Start of the String with "R", optional signs and a slash ("/")
    private final String REGEX_RECURRENCE = "\\A[R][^/]*/";
    // REGEX_STARTDATE (after replace REGEX_RECURRENCE from input-string): anything until the next slash
    private final String REGEX_STARTDATE = ".+/";
    private final Pattern PATTERN_RECURRENCE = Pattern.compile(REGEX_RECURRENCE);
    private final Pattern PATTERN_STARTDATE = Pattern.compile(REGEX_STARTDATE);

    private int recurrences;
    private Duration cycleDuration;
    private Date startDate;

    public static BpmnTimerCycle getCycle(String lexicalRepresentation) {
        return new BpmnTimerCycle(lexicalRepresentation);
    }

    public Duration getCycleDuration() {
        return cycleDuration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getRecurrences() {
        return recurrences;
    }

    private BpmnTimerCycle(String lexicalRepresentation) {
        parseLexicalRepresentation(lexicalRepresentation);
    }

    private void parseLexicalRepresentation(String lexicalRepresentation) {
        String timeCycle = lexicalRepresentation;
        String recurrenceValue = "";
        String startDateValue = "";
        String durationValue = "";

        Matcher recurrenceMatcher = PATTERN_RECURRENCE.matcher(timeCycle);
        if (recurrenceMatcher.find()) {
             recurrenceValue = recurrenceMatcher.group().replaceAll("/", "").replaceAll("R", "");
             timeCycle = timeCycle.replaceFirst(REGEX_RECURRENCE, "");
        }

        Matcher startDateMatcher = PATTERN_STARTDATE.matcher(timeCycle);
        if (startDateMatcher.find()) {
             startDateValue = startDateMatcher.group().replaceAll("/", "");
             timeCycle = timeCycle.replaceFirst(REGEX_STARTDATE, "");
        }

        durationValue = timeCycle;

        recurrences = castRecurrences(recurrenceValue);
        cycleDuration = castDuration(durationValue);
        startDate = castStartDate(startDateValue);
    }

    private int castRecurrences(String recurrenceValue) {
        int rec = -1;
        try {
            rec = Integer.valueOf(recurrenceValue);
        } catch (Exception e) {}
        return rec;
    }

    private Duration castDuration(String durationValue) {
        Duration dur = null;
        try {
            dur = DatatypeFactory.newInstance().newDuration(durationValue);
        } catch (Exception e) { }
        return dur;
    }

    private Date castStartDate(String startDateValue) {
        Date d = null;
        try {
            d = DatatypeFactory.newInstance().newXMLGregorianCalendar(startDateValue).toGregorianCalendar().getTime();
        } catch (Exception e) { }
        return d;
    }

}
