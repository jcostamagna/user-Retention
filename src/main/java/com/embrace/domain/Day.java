package com.embrace.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Day implements DayChain {
    private HashMap<Long, Integer> userRetention = new HashMap<>();
    private DayChain next = new LastDay();
    private final int number;

    public Day(int number) {
        this.number = number;
    }

    public Day(int number, HashMap<Long, Integer> userRetention) {
        this.number = number;
        this.userRetention = userRetention;
    }

    @Override
    public void addNext(DayChain day) {
        this.next = day;
    }

    @Override
    public void addActivity(int day, long user) {
        Integer userActivity = userRetention.getOrDefault(user, 0);

        int distanceDays = day - number;

        if(userActivity >= distanceDays){
            userRetention.put(user, distanceDays + 1);
            return;
        }

        if (number < day) {
            next.addActivity(day, user);
        }
    }

    @Override
    public void appendFrequencies(ArrayList<HashMap<Integer, Integer>> frequencies) {
        HashMap<Integer, Integer> dayFrequency = new HashMap<>();

        this.userRetention.values().forEach(value -> {
            Integer currentCount = dayFrequency.getOrDefault(value, 0);
            dayFrequency.put(value, currentCount + 1);
        });

        frequencies.add(dayFrequency);

        this.next.appendFrequencies(frequencies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Objects.equals(userRetention, day.userRetention) && Objects.equals(next, day.next);
    }
}
