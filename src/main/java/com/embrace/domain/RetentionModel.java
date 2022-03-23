package com.embrace.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RetentionModel {
    private final DayChain daysChain;
    private final int RETENTION_DAYS = 14;

    public RetentionModel() {
        DayChain lastDay = new LastDay();
        for(int i = RETENTION_DAYS; i >= 1; i--) {
            Day currentDay = new Day(i);
            currentDay.addNext(lastDay);
            lastDay = currentDay;
        }

        this.daysChain = lastDay;
    }

    public RetentionModel(Day day) {
        this.daysChain = day;
    }

    public void addActivity(int day, long user) {
        this.daysChain.addActivity(day, user);
    }

    public List<HashMap<Integer, Integer>> createFrequencies() {
        ArrayList<HashMap<Integer, Integer>> frequencies = new ArrayList<>();
        daysChain.appendFrequencies(frequencies);
        return frequencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetentionModel that = (RetentionModel) o;
        return Objects.equals(daysChain, that.daysChain);
    }
}
