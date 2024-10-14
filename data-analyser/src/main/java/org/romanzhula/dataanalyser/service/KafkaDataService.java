package org.romanzhula.dataanalyser.service;

import org.romanzhula.dataanalyser.model.Data;

public interface KafkaDataService {

    void handleData(Data data);

}
