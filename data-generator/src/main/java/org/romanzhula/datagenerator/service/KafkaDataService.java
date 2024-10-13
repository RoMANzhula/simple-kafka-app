package org.romanzhula.datagenerator.service;

import org.romanzhula.datagenerator.model.Data;

public interface KafkaDataService {

    void send(Data data);

}
