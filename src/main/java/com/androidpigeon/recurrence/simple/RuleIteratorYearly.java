package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;

public class RuleIteratorYearly extends RuleIterator {

    public RuleIteratorYearly( RecurrenceRule rule, LocalDateTime dateTimeStart ) {
        super( rule, dateTimeStart );

//        if( now.isBefore( this.dateTimeStart ) )
//            this.dateTimeStart = this.dateTimeStart.minusYears( rule.interval );
    }

    @Override
    public LocalDateTime next() {
        dateTimeStart = dateTimeStart.plusYears( rule.interval );
        return dateTimeStart;
    }

}
