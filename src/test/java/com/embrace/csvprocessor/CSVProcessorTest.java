package com.embrace.csvprocessor;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVProcessorTest {
    private final String ROOT_PATH = "src/test/resources/";

    @Test
    public void test_read_a_basic_txt() throws FileNotFoundException {

        CSVProcessor csvProcessor = new CSVProcessorImpl(new EventUserActivityProcessor());

        csvProcessor.read(ROOT_PATH + "basic.txt");

        assertEquals("","");
    }
}
