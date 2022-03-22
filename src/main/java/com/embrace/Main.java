package com.embrace;

import com.embrace.csvprocessor.CSVProcessorImpl;
import com.embrace.csvprocessor.EventUserActivityProcessor;
import com.embrace.presenters.RetentionModelPresenterStdOut;

import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        UserRetention userRetention = new UserRetention(
                new CSVProcessorImpl(new EventUserActivityProcessor()),
                new RetentionModelPresenterStdOut()
        );

        Optional<String> csvPath = Arrays.stream(args).findFirst();

        String path = csvPath.orElseThrow(() -> new IllegalArgumentException("Please provide a csv path"));

        userRetention.process(path);
    }
}
