# Very Basic Implementation or RRULE (RFC5545)

Made from necessity, as the MVN repo became impossible to search!

- No AI was used in the creation of this module. (except for one README Commit, <thanks copilot!>

## Support for:

- FREQ - YEARLY, MONTHLY, WEEKLY and DAILY;
- INTERVAL (defaults to 1 if omitted)
- COUNT (defaults to 10 if omitted)

## Usage

List<LocalDateTime> dates = new RecurrenceProcessor().getDays(LocalDateTime, rule);

## Rules implemented:

### DAILY

The results starts from the passed LocalDateTime

```plaintext
FREQ=DAILY;COUNT=10
```

### WEEKLY

Weeks start on Monday

```plaintext
FREQ=WEEKLY;INTERVAL=2;COUNT=10;BYDAY=SU,TU,WE,FR
FREQ=WEEKLY;COUNT=10;BYDAY=MO,SA,TH
FREQ=WEEKLY;INTERVAL=2;COUNT=10;BYDAY=MO
FREQ=WEEKLY;COUNT=10;BYDAY=SU
```

### MONTHLY by Day of Month

The results start from the day passed LocalDateTime if BYMONTHDAY is not populated.

```plaintext
FREQ=MONTHLY;BYMONTHDAY=31;COUNT=12
FREQ=MONTHLY;COUNT=8
```

### MONTHLY by Weekday Name and Week #

Processing starts from the passed LocalDateTime

```plaintext
FREQ=MONTHLY;INTERVAL=2;BYDAY=-1SA;COUNT=10
FREQ=MONTHLY;BYDAY=1MO;COUNT=10
FREQ=MONTHLY;BYDAY=2SA;COUNT=10
FREQ=MONTHLY;BYDAY=3TU;COUNT=10
FREQ=MONTHLY;BYDAY=4WE;COUNT=10
```

### YEARLY

The results starts from the passed LocalDateTime

```plaintext
FREQ=YEARLY;INTERVAL=1;COUNT=10
```

Note:

The pom.xml has a version that actually means something. You know, like the date of publish.

```xml
<version>2026-07-31</version>
```
