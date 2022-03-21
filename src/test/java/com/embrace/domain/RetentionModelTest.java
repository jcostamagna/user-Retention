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

    @Test
    public void test_add_14_consecutive_events() {
        RetentionModel retentionModel = new RetentionModel();

        for(int i = 1; i <= 14; i++) {
            retentionModel.addActivity(i, 1L);
        }

        assertEquals(this.createRetentionModelWithUserActivity(1L, 14), retentionModel);
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

    @Test
    public void test_add_two_events_in_second_day() {
        RetentionModel retentionModel = new RetentionModel();

        retentionModel.addActivity(2, 2L);
        retentionModel.addActivity(2, 2L);

        assertEquals(this.createRetentionModelWithSecondDayActivity(2L, 1), retentionModel);
    }

    @Test
    public void test_add_two_events_consecutive_in_second_day() {
        RetentionModel retentionModel = new RetentionModel();

        retentionModel.addActivity(2, 2L);
        retentionModel.addActivity(3, 2L);

        assertEquals(this.createRetentionModelWithSecondDayActivity(2L, 2), retentionModel);
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

    @Test
    public void test_add_two_events_in_different_days() {
        RetentionModel retentionModel = new RetentionModel();

        retentionModel.addActivity(1, 2L);
        retentionModel.addActivity(3, 2L);

        assertEquals(this.createRetentionModelWithThirdDayActivity(2L, 1), retentionModel);
    }

    @Test
    public void test_add_multiple_events_in_different_days() {
        RetentionModel retentionModel = new RetentionModel();

        retentionModel.addActivity(1, 2L);
        retentionModel.addActivity(3, 2L);
        retentionModel.addActivity(4, 2L);
        retentionModel.addActivity(5, 2L);

        assertEquals(this.createRetentionModelWithThirdDayActivity(2L, 3), retentionModel);
    }

    private RetentionModel createRetentionModelWithThirdDayActivity(long user, int consecutiveDays) {
        HashMap<Long, Integer> activities = new HashMap<>();
        activities.put(user, 1);

        HashMap<Long, Integer> activitiesThirdDay = new HashMap<>();
        activitiesThirdDay.put(user, consecutiveDays);

        Day day = new Day(1, activities);
        Day secondDay = new Day(2);
        Day thirdDay = new Day(3, activitiesThirdDay);

        DayChain lastDay = new LastDay();
        for(int i = 14; i >= 4; i--) {
            Day currentDay = new Day(i);
            currentDay.addNext(lastDay);
            lastDay = currentDay;
        }

        thirdDay.addNext(lastDay);
        secondDay.addNext(thirdDay);
        day.addNext(secondDay);

        return new RetentionModel(day);
    }
}