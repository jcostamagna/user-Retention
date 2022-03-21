package com.embrace.csvprocessor;

import com.embrace.domain.RetentionModel;

public interface EventProcessor {
    RetentionModel build();

    void addEvent(String[] event);
}
