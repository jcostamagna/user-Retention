package com.embrace;

import com.embrace.csvprocessor.CSVProcessor;
import com.embrace.domain.RetentionModel;
import com.embrace.presenters.RetentionModelPresenter;

public class UserRetention {
    private final CSVProcessor csvProcessor;
    private final RetentionModelPresenter presenter;

    public UserRetention(CSVProcessor csvProcessor, RetentionModelPresenter presenter) {
        this.csvProcessor = csvProcessor;
        this.presenter = presenter;
    }

    public void process(String path) {
        RetentionModel retentionModel = this.csvProcessor.read(path);

        this.presenter.present(retentionModel);
    }
}
