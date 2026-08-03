package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;
import java.util.Iterator;

public abstract class RuleIterator implements Iterator<LocalDateTime> {
    public RuleIterator( RecurrenceRule rule, LocalDateTime dateTimeStart ) {
        this.rule = rule;
        this.dateTimeStart = dateTimeStart;
    }

    protected RecurrenceRule rule          = null;
    protected LocalDateTime  dateTimeStart = null;

//    protected LocalDateTime  now           = LocalDateTime.now();

    @Override
    public boolean hasNext() {
        return true;
    }

    public static RuleIterator getInstance( RecurrenceRule recurrenceRule, LocalDateTime dateTimeStart ) {
        RuleIterator response = null;

        switch ( recurrenceRule.frequency ) {
            case DAILY:
                response = new RuleIteratorDaily( recurrenceRule, dateTimeStart );
            break;

            case MONTHLY:
                if( recurrenceRule.weekOfMonth != null && recurrenceRule.weekOfMonth >= -1 && recurrenceRule.weekOfMonth <= 4 )
                    response = new RuleIteratorMonthlyByWeek( recurrenceRule, dateTimeStart );
                else
                    response = new RuleIteratorMonthlyByDay( recurrenceRule, dateTimeStart );
            break;

            case WEEKLY:
                response = new RuleIteratorWeekly( recurrenceRule, dateTimeStart );

            break;

            case YEARLY:
                response = new RuleIteratorYearly( recurrenceRule, dateTimeStart );
            break;
        }

        return response;
    }
}
