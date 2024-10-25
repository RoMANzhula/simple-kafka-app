package org.romanzhula.datastore.utils.implementations;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.romanzhula.datastore.models.Data;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.services.SummaryService;
import org.romanzhula.datastore.utils.CDCEventConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class DebeziumCDCEventConsumerImpl implements CDCEventConsumer {

    private final SummaryService summaryService;

    @Override
    @KafkaListener(topics = "data")
    public void handle(String message) {
        try {
            JsonObject payload = JsonParser
                                    .parseString(message)
                                    .getAsJsonObject()
                                    .get("payload")
                                    .getAsJsonObject()
            ;

            Data data = new Data();

            data.setId(payload.get("id").getAsLong());
            data.setSensorId(payload.get("sensor_id").getAsLong());
            data.setMeasurement(payload.get("measurement").getAsDouble());
            data.setMeasurementType(MeasurementType.valueOf(payload.get("type").getAsString()));
            data.setTimestamp(LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(payload.get("timestamp").getAsLong() / 1000),
                    TimeZone.getDefault().toZoneId()
            ));

            summaryService.handle(data);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
