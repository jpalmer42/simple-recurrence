package com.androidpigeon.recurrence.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecurrenceRule {
    Frequency                     frequency;
    Integer                       interval;
    Integer                       count;

    // Weekly
    List<Integer>                 daysOfWeek;

    // Monthly
    // Set Day in Month
    Integer                       dayOfMonth;               // 1 - 31

    // Position of Week on a Specific Weekday
    Integer                       weekOfMonth;              // -1 is Last Week
    Integer                       ordinalDayOfWeek;         // 0-MO, 6-SU

    protected static final String FREQ       = "FREQ";
    protected static final String INTERVAL   = "INTERVAL";
    protected static final String COUNT      = "COUNT";
    protected static final String MONTHLY    = "MONTHLY";
    protected static final String BYDAY      = "BYDAY";
    protected static final String BYMONTHDAY = "BYMONTHDAY";

    protected static final String SEMI       = ";";
    protected static final String EQUAL      = "=";
    protected static final String ONE        = "1";
    protected static final String TEN        = "10";
    protected static final String COMMA      = ",";

    public static RecurrenceRule parse( String ruleString ) {
        RecurrenceRule response = new RecurrenceRule();

        Map<String, String> kvPairs = new HashMap<>();
        List.of( ruleString.trim().split( SEMI ) ).forEach( item -> {
            String[] parts = item.toUpperCase().split( EQUAL );
            if( parts.length == 2 )
                kvPairs.put( parts[0].trim(), parts[1].trim() );
        } );

        String freq = kvPairs.get( FREQ );
        String interval = kvPairs.get( INTERVAL );
        String count = kvPairs.get( COUNT );
        response.frequency = Frequency.valueOf( freq != null ? freq : MONTHLY );
        response.interval = Integer.parseInt( interval != null ? interval : ONE );
        response.count = Integer.parseInt( count != null ? count : TEN );

        switch ( response.frequency ) {
            case WEEKLY: {
                String byDay = kvPairs.get( BYDAY );
                response.daysOfWeek = List.of( byDay.split( COMMA ) ).stream().map( item -> Weekday.valueOf( item ).ordinal ).toList();
            }
            break;

            case MONTHLY: {

                String weekAndDayOfWeek = kvPairs.get( BYDAY );
                if( weekAndDayOfWeek != null ) {
                    int len = weekAndDayOfWeek.length();
                    response.ordinalDayOfWeek = Weekday.valueOf( weekAndDayOfWeek.substring( len - 2 ) ).ordinal;
                    response.weekOfMonth = Integer.parseInt( weekAndDayOfWeek.substring( 0, len - 2 ) );
                }
                else {
                    String byMonthDay = kvPairs.get( BYMONTHDAY );
                    if( byMonthDay != null ) {
                        response.dayOfMonth = Integer.parseInt( byMonthDay );
                    }
                }
//                String byMonthDay = kvPairs.get( BYMONTHDAY );
//                if( byMonthDay != null ) {
//                    response.dayOfMonth = Integer.parseInt( byMonthDay );
//                }
//                else {
//                    String weekAndDayOfWeek = kvPairs.get( BYDAY );
//                    int len = weekAndDayOfWeek.length();
//                    response.ordinalDayOfWeek = Weekday.valueOf( weekAndDayOfWeek.substring( len - 2 ) ).ordinal;
//                    response.weekOfMonth = Integer.parseInt( weekAndDayOfWeek.substring( 0, len - 2 ) );
//                }
            }
            break;

            default:
            break;
        }
        return response;
    }
}