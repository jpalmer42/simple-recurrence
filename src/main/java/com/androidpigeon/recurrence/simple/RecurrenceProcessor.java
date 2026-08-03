package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecurrenceProcessor {

    public List<LocalDateTime> getDays( LocalDateTime dateTimeStart, String ruleString ) {

        List<LocalDateTime> response = new ArrayList<>();

        RecurrenceRule recurrenceRule = RecurrenceRule.parse( ruleString );

        RuleIterator processor = RuleIterator.getInstance( recurrenceRule, dateTimeStart );

        for( int count = 0 ; count < recurrenceRule.count ; count++ ) {
            response.add( processor.next() );
        }

        return response;
    }

}
