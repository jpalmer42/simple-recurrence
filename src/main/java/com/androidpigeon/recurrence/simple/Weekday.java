package com.androidpigeon.recurrence.simple;

public enum Weekday {
    MO("MO", 0), TU("MO", 1), WE("MO", 2), TH("MO", 3), FR("MO", 4), SA("MO", 5), SU("MO", 6);

    String code;
    int    ordinal;

    Weekday( String code, int ordinal ) {
        this.code = code;
        this.ordinal = ordinal;
    }
}
