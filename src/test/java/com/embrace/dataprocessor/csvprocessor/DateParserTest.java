package com.embrace.dataprocessor.csvprocessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {

    @Test
    public void test_parse_seconds_to_day_1() {
        DateParser dateParser = new DateParser();
        String date = "1609459200";

        assertEquals(1, dateParser.parse(date));
    }

    @Test
    public void test_parse_seconds_to_day_2() {
        DateParser dateParser = new DateParser();
        String date = "1609545600";

        assertEquals(2, dateParser.parse(date));
    }

    @Test
    public void test_parse_seconds_to_day_1_last_second() {
        DateParser dateParser = new DateParser();
        String date = "1609545599";

        assertEquals(1, dateParser.parse(date));
    }
}
