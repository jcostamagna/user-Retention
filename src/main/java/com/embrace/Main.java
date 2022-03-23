package com.embrace;

import com.embrace.dataprocessor.csvprocessor.CSVProcessorImpl;
import com.embrace.dataprocessor.csvprocessor.EventUserActivityProcessor;
import com.embrace.presenters.RetentionModelPresenterStdOut;
import com.embrace.usecases.UserRetention;

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
