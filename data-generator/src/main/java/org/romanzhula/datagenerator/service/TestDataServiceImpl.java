package org.romanzhula.datagenerator.service;

import lombok.RequiredArgsConstructor;
import org.romanzhula.datagenerator.model.Data;
import org.romanzhula.datagenerator.model.DataTestOptions;
import org.romanzhula.datagenerator.model.enums.MeasurementType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TestDataServiceImpl implements TestDataService {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final KafkaDataService kafkaDataService;

    @Override
    public void sendMessages(DataTestOptions dataTestOptions) {
        if (dataTestOptions.getMeasurementTypes().length > 0) {
            scheduledExecutorService.scheduleAtFixedRate(
                    () -> {
                        Data data = new Data();

                        data.setSensorId((long) getRandomNumber(1, 10));
                        data.setMeasurement(getRandomNumber(15, 100));
                        data.setMeasurementType(getRandomMeasurementType(dataTestOptions.getMeasurementTypes()));
                        data.setTimestamp(LocalDateTime.now());

                        kafkaDataService.send(data);
                    },
                    0,
                    dataTestOptions.getDelayInSecond(),
                    TimeUnit.SECONDS
            );
        }
    }

    private double getRandomNumber(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    private MeasurementType getRandomMeasurementType(MeasurementType[] measurementTypes) {
        int randomTypeId = (int) (Math.random() * measurementTypes.length);

        return measurementTypes[randomTypeId];
    }

}
