package com.embrace;

import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        UserRetention userRetention = new UserRetention();
        Optional<String> csvPath = Arrays.stream(args).findFirst();

        String path = csvPath.orElseThrow(() -> new IllegalArgumentException("Please provide a csv path"));

        userRetention.process(path);
    }
}
