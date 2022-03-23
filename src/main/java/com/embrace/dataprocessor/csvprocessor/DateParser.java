package com.embrace.dataprocessor.csvprocessor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateParser {
    public int parse(String date_seconds) {
        Long date_miliseconds = Long.parseLong(date_seconds) * 1000;
        Date date = new Date(date_miliseconds);

        LocalDateTime localDateTime = date.toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime();
        OffsetDateTime offset = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);

        return offset.getDayOfMonth();
    }
}
