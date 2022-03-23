package com.embrace.usecases;

import com.embrace.domain.RetentionModel;

public class UserRetention {
    private final CSVProcessor csvProcessor;
    private final RetentionModelPresenter presenter;

    public UserRetention(CSVProcessor csvProcessor, RetentionModelPresenter presenter) {
        this.csvProcessor = csvProcessor;
        this.presenter = presenter;
    }

    public void process(String path) {
        RetentionModel retentionModel = this.csvProcessor.read(path);

        RetentionFrequenciesResponse retentionFrequencies = new RetentionFrequenciesResponse(retentionModel.createFrequencies());

        this.presenter.present(retentionFrequencies);
    }
}
