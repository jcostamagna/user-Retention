package com.embrace.csvprocessor;

import com.opencsv.CSVReader;

import java.io.FileReader;

public class CSVProcessorImpl implements CSVProcessor {
    private EventProcessor eventProcessor;

    public CSVProcessorImpl(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void read(String path) {
        try {
            FileReader filereader = new FileReader(path);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
