package com.embrace.domain;


import java.util.HashMap;
import java.util.Objects;

public class Day implements DayChain {
    private HashMap<Long, Integer> userRetention = new HashMap<>();
    private final int number;
    private DayChain next = new LastDay();

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
    public int hashCode() {
        return Objects.hash(userRetention, number, next);
    }

    @Override
    public String toString() {
        return "Day " + number +
                " userRetention=" + userRetention +
                ", next=" + next +
                '}';
    }
}
