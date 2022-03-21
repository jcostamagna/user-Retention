package com.embrace.csvprocessor;

import com.embrace.domain.Day;
import com.embrace.domain.DayChain;
import com.embrace.domain.LastDay;
import com.embrace.domain.RetentionModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventRetentionProcessorTest {

    @Test
    public void test_build_empty_model() {
        EventProcessor eventRetentionProcessor = new EventUserActivityProcessor();

        assertEquals(new RetentionModel(), eventRetentionProcessor.build());
    }

    @Test
    public void test_add_single_event() {
        EventProcessor eventRetentionProcessor = new EventUserActivityProcessor();
        String[] line = {"1609459200", "1"};
        eventRetentionProcessor.addEvent(line);

        assertEquals(this.createRetentionModelWithUserActivity(1L, 1), eventRetentionProcessor.build());
    }

    @Test
    public void test_add_duplicated_event() {
        EventProcessor eventRetentionProcessor = new EventUserActivityProcessor();
        String[] line = {"1609459200", "1"};
        String[] line2 = {"1609459200", "1"};
        eventRetentionProcessor.addEvent(line);
        eventRetentionProcessor.addEvent(line2);

        assertEquals(this.createRetentionModelWithUserActivity(1L, 1), eventRetentionProcessor.build());
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
}