package com.embrace;

import com.embrace.csvprocessor.CSVProcessorImpl;
import com.embrace.csvprocessor.EventUserActivityProcessor;
import com.embrace.presenters.RetentionModelPresenterStdOut;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRetentionTest {
    private final String ROOT_PATH_INPUT = "src/test/resources/";
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void test_basic_case() throws IOException {
        UserRetention userRetention = new UserRetention(new CSVProcessorImpl(new EventUserActivityProcessor()), new RetentionModelPresenterStdOut());

        userRetention.process(ROOT_PATH_INPUT + "input/basic.txt");

        String expectedResponse = Files.readString(Path.of(ROOT_PATH_INPUT + "output/basic_response.txt"));

        assertEquals(expectedResponse, outContent.toString());
    }
}