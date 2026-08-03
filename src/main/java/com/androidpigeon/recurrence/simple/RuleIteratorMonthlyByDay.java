package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;

public class RuleIteratorMonthlyByDay extends RuleIterator {
    public RuleIteratorMonthlyByDay( RecurrenceRule rule, LocalDateTime dateTimeStart ) {
        super( rule, dateTimeStart );

        if( rule.dayOfMonth == null )
            rule.dayOfMonth = this.dateTimeStart.getDayOfMonth();

//        if( now.isBefore( this.dateTimeStart ) )
        this.dateTimeStart = this.dateTimeStart.minusMonths( rule.interval );
    }

    @Override
    public LocalDateTime next() {
        dateTimeStart = setDay( dateTimeStart.plusMonths( rule.interval ) );
        return dateTimeStart;
    }

    private LocalDateTime setDay( LocalDateTime date ) {
        if( date.getDayOfMonth() != rule.dayOfMonth ) {
            switch ( date.getMonth() ) {
                case FEBRUARY: // 28/29
                    date = date.withDayOfMonth( Math.min( date.getYear() % 4 == 0 ? 29 : 28, rule.dayOfMonth ) );
                break;

                case APRIL: // 30
                case JUNE:
                case SEPTEMBER:
                case NOVEMBER:
                    date = date.withDayOfMonth( Math.min( 30, rule.dayOfMonth ) );
                break;

                case JANUARY:
                case MARCH:
                case MAY:
                case JULY:
                case AUGUST:
                case OCTOBER:
                case DECEMBER:
                    date = date.withDayOfMonth( Math.min( 31, rule.dayOfMonth ) );
                break;
            }
        }
        return date;
    }
}
