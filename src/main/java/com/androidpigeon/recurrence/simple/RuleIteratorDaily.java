package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;

public class RuleIteratorDaily extends RuleIterator {

    public RuleIteratorDaily( RecurrenceRule rule, LocalDateTime dateTimeStart ) {
        super( rule, dateTimeStart );

//        if( now.isBefore( this.dateTimeStart ) )
        this.dateTimeStart = this.dateTimeStart.minusDays( rule.interval );
    }

    @Override
    public LocalDateTime next() {
        dateTimeStart = dateTimeStart.plusDays( rule.interval );
        return dateTimeStart;
    }
}
