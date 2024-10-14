package org.romanzhula.dataanalyser.service;

import lombok.RequiredArgsConstructor;
import org.romanzhula.dataanalyser.model.Data;
import org.romanzhula.dataanalyser.repository.DataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final DataRepository dataRepository;

    @Override
    public void handleData(Data data) {
        dataRepository.save(data);

        System.out.println("Data is received: " + data.toString());
    }

}
