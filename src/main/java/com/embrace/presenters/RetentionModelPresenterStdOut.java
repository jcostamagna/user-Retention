package com.embrace.presenters;

import com.embrace.usecases.RetentionFrequenciesResponse;
import com.embrace.usecases.RetentionModelPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class RetentionModelPresenterStdOut implements RetentionModelPresenter {
    private final int RETENTION_DAYS = 14;

    @Override
    public void present(RetentionFrequenciesResponse retentionModel) {
        List<HashMap<Integer, Integer>> frequencies = retentionModel.getFrequencies();

        int dayNumber = 1;

        for(HashMap<Integer, Integer> frequency: frequencies) {
            System.out.print(this.presentToString(dayNumber, frequency));
            dayNumber++;
        }
    }

    private String presentToString(int index, HashMap<Integer, Integer> dayFrequency) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(index);

        IntStream.rangeClosed(1,RETENTION_DAYS).forEach(integer -> {
            stringBuffer.append(",").append(dayFrequency.getOrDefault(integer, 0));
        });

        return stringBuffer
                .append(System.lineSeparator())
                .toString();
    }
}
