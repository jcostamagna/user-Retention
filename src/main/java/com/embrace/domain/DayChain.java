package com.embrace.domain;

public interface DayChain {
    void addNext(DayChain day);

    void addActivity(int day, long user);
}
