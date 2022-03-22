package com.embrace.domain;


import java.util.*;
import java.util.stream.IntStream;

public class Day implements DayChain {
    private final int RETENTION_DAYS = 14;
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
    public void addActivity(int day, long user) {
        Integer userActivity = userRetention.getOrDefault(user, 0);

        if (day == number) {
            this.userRetention.put(user, 1);
            return;
        }

        if(userActivity == (day - number) ){
            userRetention.put(user, userActivity + 1);
            return;
        }

        if (day > number) {
            next.addActivity(day, user);
        }
    }

    @Override
    public void addNext(DayChain day) {
        this.next = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Objects.equals(userRetention, day.userRetention) && Objects.equals(next, day.next);
    }

    @Override
    public String toString() {
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        this.userRetention.values().forEach(value -> frequencies.put(value, frequencies.getOrDefault(value, 0) + 1));

        ArrayList<Integer> retentions = new ArrayList<>();
        retentions.add(this.number);

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.number);

        IntStream.rangeClosed(1,RETENTION_DAYS).forEach(integer -> stringBuffer.append(",").append(frequencies.getOrDefault(integer, 0)));

        return stringBuffer
                .append(System.lineSeparator())
                .append(this.next.toString())
                .toString();
    }
}
