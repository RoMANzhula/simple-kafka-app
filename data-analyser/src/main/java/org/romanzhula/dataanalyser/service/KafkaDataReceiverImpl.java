package org.romanzhula.dataanalyser.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.romanzhula.dataanalyser.helpers.LocalDateTimeDeserializer;
import org.romanzhula.dataanalyser.model.Data;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaDataReceiverImpl implements KafkaDataReceiver {

    private final KafkaReceiver<String, Object> kafkaReceiver;
    private final KafkaDataService kafkaDataService;
    private final LocalDateTimeDeserializer localDateTimeDeserializer;

    @PostConstruct //for automatic method call after bean creation
    private void init() {
        consumeData();
    }

    @Override
    public void consumeData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(
                        LocalDateTime.class,
                        localDateTimeDeserializer
                )
                .create()
        ;

        kafkaReceiver
                .receive()
                .subscribe(record -> {
                    Data data = gson.fromJson(record.value().toString(), Data.class);
                    kafkaDataService.handleData(data);
                    record.receiverOffset().acknowledge(); //step to next message from Kafka
                })
        ;
    }

}
