package com.embrace.usecases;

import java.util.HashMap;
import java.util.List;

public class RetentionFrequenciesResponse {
    private final List<HashMap<Integer, Integer>> frequencies;

    public RetentionFrequenciesResponse(List<HashMap<Integer, Integer>> frequencies) {
        this.frequencies = frequencies;
    }

    public List<HashMap<Integer, Integer>> getFrequencies() {
        return frequencies;
    }
}
