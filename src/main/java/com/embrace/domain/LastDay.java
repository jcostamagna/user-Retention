package com.embrace.domain;

public class LastDay implements DayChain {
    @Override
    public void addNext(DayChain day) {}

    @Override
    public void addActivity(int day, long user) {}

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
