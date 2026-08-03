package com.androidpigeon.recurrence.simple;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Test {
    private static DateTimeFormatter   dtf = DateTimeFormatter.ofPattern( "yyyy-MM-dd - E" );

    private static RecurrenceProcessor rp  = new RecurrenceProcessor();

    public static void main( String[] args ) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTimeStart = now;
//        LocalDateTime dateTimeStart = LocalDateTime.of( now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 20, 00, 00 );
//        LocalDateTime dateTimeStart = LocalDateTime.of( now.getYear(), now.getMonthValue(), 1, 20, 00, 00 );

        test( dateTimeStart, "FREQ=YEARLY;INTERVAL=2;COUNT=10" );

        test( dateTimeStart, "FREQ=DAILY;INTERVAL=1;COUNT=10" );

        test( dateTimeStart, "FREQ=MONTHLY;BYMONTHDAY=31;COUNT=12" );
        test( dateTimeStart, "FREQ=MONTHLY;COUNT=8" );

        test( dateTimeStart, "FREQ=WEEKLY;INTERVAL=2;COUNT=10;BYDAY=SU,TU,WE,FR" );
        test( dateTimeStart, "FREQ=WEEKLY;INTERVAL=1;COUNT=10;BYDAY=MO,SA,TH" );
        test( dateTimeStart, "FREQ=WEEKLY;INTERVAL=2;COUNT=10;BYDAY=MO" );
        test( dateTimeStart, "FREQ=WEEKLY;INTERVAL=2;COUNT=10;BYDAY=SU" );

        test( dateTimeStart, "FREQ=MONTHLY;INTERVAL=2;BYDAY=-1SA;COUNT=10" );
        test( dateTimeStart, "FREQ=MONTHLY;BYDAY=1MO;COUNT=10" );
        test( dateTimeStart, "FREQ=MONTHLY;BYDAY=2SA;COUNT=10" );
        test( dateTimeStart, "FREQ=MONTHLY;BYDAY=3TU;COUNT=10" );
        test( dateTimeStart, "FREQ=MONTHLY;BYDAY=4WE;COUNT=10" );
    }

    private static void test( LocalDateTime dateTimeStart, String rule ) {
        System.out.println( String.format( "\nRule: %s", rule ) );
        List<LocalDateTime> resp = rp.getDays( dateTimeStart, rule );
        resp.forEach( item -> System.out.println( item.format( dtf ) ) );
    }
}
