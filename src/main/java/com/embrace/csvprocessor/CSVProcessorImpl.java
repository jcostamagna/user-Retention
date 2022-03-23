package com.embrace.csvprocessor;

import com.embrace.domain.RetentionModel;
import com.embrace.usecases.CSVProcessor;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CSVProcessorImpl implements CSVProcessor {
    private EventProcessor eventProcessor;

    public CSVProcessorImpl(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public RetentionModel read(String path) {
        try {
            FileReader filereader = new FileReader(path);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                this.eventProcessor.addEvent(nextRecord);
            }
        }
        catch (IOException | CsvValidationException e) {
            System.err.println("Error found while processing csv file. Please provide a valid csv path.");
        }
        return eventProcessor.build();
    }
}
