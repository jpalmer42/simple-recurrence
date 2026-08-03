package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;

public class RuleIteratorMonthlyByWeek extends RuleIterator {

    public RuleIteratorMonthlyByWeek( RecurrenceRule rule, LocalDateTime dateTimeStart ) {
        super( rule, dateTimeStart );

//        if( now.isAfter( this.dateTimeStart ) )
//            this.dateTimeStart = this.dateTimeStart.plusDays( 1 );

        currentMonth = this.dateTimeStart.withDayOfMonth( 1 );

//        this.dateTimeStart = this.dateTimeStart.withDayOfMonth( 1 );

        target = rule.ordinalDayOfWeek;
        while( this.dateTimeStart.getDayOfWeek().ordinal() != target )
            this.dateTimeStart = this.dateTimeStart.plusDays( 1 );
    }

    int           target;
    LocalDateTime currentMonth;

    @Override
    public LocalDateTime next() {
        if( rule.weekOfMonth == -1 ) {
            this.dateTimeStart = currentMonth.plusMonths( 1 ).minusDays( 1 );
            while( this.dateTimeStart.getDayOfWeek().ordinal() != target )
                this.dateTimeStart = this.dateTimeStart.minusDays( 1 );
        }
        else {
            this.dateTimeStart = currentMonth;
            while( this.dateTimeStart.getDayOfWeek().ordinal() != target )
                this.dateTimeStart = this.dateTimeStart.plusDays( 1 );
            this.dateTimeStart = this.dateTimeStart.plusDays( 7 * ( rule.weekOfMonth - 1 ) );
        }

        currentMonth = currentMonth.plusMonths( rule.interval );

        return this.dateTimeStart;
    }
}
