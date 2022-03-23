package com.embrace.dataprocessor.csvprocessor;

import com.embrace.domain.RetentionModel;

public class EventUserActivityProcessor implements EventProcessor {
    private final RetentionModel model = new RetentionModel();
    private final DateParser parser = new DateParser();

    @Override
    public RetentionModel build() {
        return model;
    }

    @Override
    public void addEvent(String[] event) {
        if (event.length < 2) {
            return;
        }
        
        int day = parser.parse(event[0]);
        long user = Long.parseLong(event[1]);

        model.addActivity(day, user);
    }
}
