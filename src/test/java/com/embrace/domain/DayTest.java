package com.embrace.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    @Test
    public void test_build_empty_model() {
        Day day = new Day(1);

        assertEquals(new Day(1), day);
    }

    @Test
    public void test_add_single_event() {
        Day day = new Day(1);

        day.addActivity(1, 1L);

        assertEquals(this.createDayWithActivity(1L, 1), day);
    }

    @Test
    public void test_add_duplicated_event() {
        Day day = new Day(1);

        day.addActivity(1, 1L);
        day.addActivity(1, 1L);

        assertEquals(this.createDayWithActivity(1L, 1), day);
    }

    private Day createDayWithActivity(long user, int consecutiveDays) {
        HashMap<Long, Integer> activities = new HashMap<>();
        activities.put(user, consecutiveDays);

        Day day = new Day(1, activities);

        return day;
    }
}