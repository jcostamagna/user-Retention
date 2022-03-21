package com.embrace.csvprocessor;

import com.embrace.domain.Day;
import com.embrace.domain.DayChain;
import com.embrace.domain.LastDay;
import com.embrace.domain.RetentionModel;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVProcessorTest {
    private final String ROOT_PATH = "src/test/resources/";

    @Test
    public void test_file_not_found() {

        CSVProcessor csvProcessor = new CSVProcessorImpl(new EventUserActivityProcessor());

        RetentionModel retentionModel = csvProcessor.read(ROOT_PATH + "unknown.txt");

        assertEquals(new RetentionModel(), retentionModel);
    }

    @Test
    public void test_read_a_basic_txt() {

        CSVProcessor csvProcessor = new CSVProcessorImpl(new EventUserActivityProcessor());

        RetentionModel retentionModel = csvProcessor.read(ROOT_PATH + "basic.txt");

        assertEquals(this.createExpectedRetentionModel(), retentionModel);
    }

    private RetentionModel createExpectedRetentionModel() {
        Day firstDay = new Day(1, this.dayOneActivities());
        Day secondDay = new Day(2);
        Day thirdDay = new Day(3, this.singleActivity(2L, 2));
        Day fourthDay = new Day(4);
        Day fifthDay = new Day(5, this.singleActivity(5L, 1));

        DayChain lastDay = new LastDay();
        for(int i = 14; i >= 6; i--) {
            Day currentDay = new Day(i);
            currentDay.addNext(lastDay);
            lastDay = currentDay;
        }

        fifthDay.addNext(lastDay);
        fourthDay.addNext(fifthDay);
        thirdDay.addNext(fourthDay);
        secondDay.addNext(thirdDay);
        firstDay.addNext(secondDay);

        return new RetentionModel(firstDay);
    }

    private HashMap<Long, Integer> singleActivity(long user, int consecutiveDays) {
        HashMap<Long, Integer> activities = new HashMap<>();
        activities.put(user, consecutiveDays);

        return activities;
    }

    private HashMap<Long, Integer> dayOneActivities() {
        HashMap<Long, Integer> activities = new HashMap<>();
        activities.put(1L, 5);
        activities.put(2L, 1);
        activities.put(3L, 3);
        activities.put(4L, 1);

        return activities;
    }
}
