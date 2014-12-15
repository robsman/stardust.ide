package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event;

import javax.xml.datatype.Duration;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnTimerCycle;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;

public class EventDefinitions2Stardust {

	private EventDefinitions2Stardust() {}

	public static Period getPeriod(TimerEventDefinition def) {
		Period period = null;
		Duration dur = getTimerDuration(def);
		if (dur != null) {
			period = new Period((short)dur.getYears(), (short)dur.getMonths(), (short)dur.getDays(), (short)dur.getHours(), (short)dur.getMinutes(), (short)dur.getSeconds());
		}
		return period;
	}

	public static String getErrorCode(Definitions defs, ErrorEventDefinition eventDef) {
		org.eclipse.bpmn2.Error err = eventDef.getErrorRef();
		if (null == err) return null;
		err = Bpmn2ProxyResolver.resolveError(err, defs);
		return err.getErrorCode();
	}

    private static Duration getTimerDuration(TimerEventDefinition eventDef) {
        Expression durationExpression = null;
        String durationLexValue = null;
        Duration duration = null;
        if (eventDef.getTimeDuration() != null) {
        	durationExpression = eventDef.getTimeDuration();
        } else if (eventDef.getTimeCycle()!= null) {
        	durationExpression = eventDef.getTimeCycle();
        }
        if (durationExpression == null) return null;

        if (durationExpression instanceof FormalExpression) {
            FormalExpression formalCycle = (FormalExpression)durationExpression;
            durationLexValue = formalCycle.getBody();
        } else {
        	durationLexValue = DocumentationTool.getInformalExpressionValue(durationExpression);
        }
        if (durationLexValue == null) return null;

        try {
        	BpmnTimerCycle cycle = BpmnTimerCycle.getCycle(durationLexValue);
        	duration = cycle.getCycleDuration();
        } catch (Exception e) {
        }
        return duration;
    }

}
