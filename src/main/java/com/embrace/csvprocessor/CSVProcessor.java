package com.embrace.csvprocessor;

import com.embrace.domain.RetentionModel;

public interface CSVProcessor {
    RetentionModel read(String path);
}
