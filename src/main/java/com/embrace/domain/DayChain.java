package com.embrace.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DayChain {
    void addNext(DayChain day);

    void addActivity(int day, long user);

    void appendFrequencies(ArrayList<HashMap<Integer, Integer>> frequencies);
}
