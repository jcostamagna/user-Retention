package com.embrace.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetentionModelTest {

    @Test
    public void test_add_single_event() {
        RetentionModel retentionModel = new RetentionModel();
        retentionModel.addActivity(1, 1L);

        assertEquals(this.createRetentionModelWithUserActivity(1L, 1), retentionModel);
    }

    @Test
    public void test_add_duplicated_event() {
        RetentionModel retentionModel = new RetentionModel();

        retentionModel.addActivity(1, 1L);
        retentionModel.addActivity(1, 1L);

        assertEquals(this.createRetentionModelWithUserActivity(1L, 1), retentionModel);
    }

    @Test
    public void test_add_consecutive_events() {
        RetentionModel retentionModel = new RetentionModel();

        retentionModel.addActivity(1, 1L);
        retentionModel.addActivity(2, 1L);

        assertEquals(this.createRetentionModelWithUserActivity(1L, 2), retentionModel);
    }

    private RetentionModel createRetentionModelWithUserActivity(long user, int consecutiveDays) {
        HashMap<Long, Integer> activities = new HashMap<>();
        activities.put(user, consecutiveDays);

        Day day = new Day(1, activities);

        DayChain lastDay = new LastDay();
        for(int i = 14; i >= 2; i--) {
            Day currentDay = new Day(i);
            currentDay.addNext(lastDay);
            lastDay = currentDay;
        }

        day.addNext(lastDay);

        return new RetentionModel(day);
    }

    @Test
    public void test_add_event_in_second_day() {
        RetentionModel retentionModel = new RetentionModel();

        retentionModel.addActivity(2, 2L);

        assertEquals(this.createRetentionModelWithSecondDayActivity(2L, 1), retentionModel);
    }

    private RetentionModel createRetentionModelWithSecondDayActivity(long user, int consecutiveDays) {
        HashMap<Long, Integer> activities = new HashMap<>();
        activities.put(user, consecutiveDays);

        Day day = new Day(1);
        Day secondDay = new Day(2, activities);

        DayChain lastDay = new LastDay();
        for(int i = 14; i >= 3; i--) {
            Day currentDay = new Day(i);
            currentDay.addNext(lastDay);
            lastDay = currentDay;
        }

        secondDay.addNext(lastDay);
        day.addNext(secondDay);

        return new RetentionModel(day);
    }
}