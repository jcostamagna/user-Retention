package com.embrace.usecases;

import com.embrace.domain.RetentionModel;

public interface CSVProcessor {
    RetentionModel read(String path);
}
