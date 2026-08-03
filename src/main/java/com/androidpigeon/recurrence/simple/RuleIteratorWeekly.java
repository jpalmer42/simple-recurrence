package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;

public class RuleIteratorWeekly extends RuleIterator {

    public RuleIteratorWeekly( RecurrenceRule rule, LocalDateTime dateTimeStart ) {
        super( rule, dateTimeStart );

        weekBegins = this.dateTimeStart = this.dateTimeStart.minusDays( 1 );

        indexLen = rule.daysOfWeek.size();
        days = new int[indexLen];
        for( int index = 0 ; index < indexLen ; index++ ) {
            days[index] = rule.daysOfWeek.get( index );
        }
    }

    protected LocalDateTime weekBegins;

    protected int           indexLen;
    protected int[]         days;
    protected int           index = 0;

    @Override
    public LocalDateTime next() {
        if( index >= indexLen ) {
            weekBegins = dateTimeStart = weekBegins.plusWeeks( rule.interval );
            index = 0;
        }

        do {
            dateTimeStart = dateTimeStart.plusDays( 1 );
        }
        while( !exists( dateTimeStart.getDayOfWeek().ordinal() ) );

        index++;

        return dateTimeStart;
    }

    private boolean exists( int day ) {
        boolean found = false;
        for( int index = 0 ; index < indexLen ; index++ ) {
            if( day == days[index] ) {
                found = true;
                break;
            }
        }

        return found;
    }
}