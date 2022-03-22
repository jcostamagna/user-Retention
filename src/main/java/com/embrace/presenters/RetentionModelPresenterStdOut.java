package com.embrace.presenters;

import com.embrace.domain.RetentionModel;

public class RetentionModelPresenterStdOut implements RetentionModelPresenter {
    @Override
    public void present(RetentionModel retentionModel) {
        System.out.print(retentionModel.toString());
    }
}
