package com.embrace.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class LastDay implements DayChain {
    @Override
    public void addNext(DayChain day) {}

    @Override
    public void addActivity(int day, long user) {}

    @Override
    public void appendFrequencies(ArrayList<HashMap<Integer, Integer>> frequencies) {}

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
