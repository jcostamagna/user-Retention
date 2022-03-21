package com.embrace.csvprocessor;

import java.io.FileNotFoundException;

public interface CSVProcessor {
    void read(String path) throws FileNotFoundException;
}
