package org.romanzhula.dataanalyser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.romanzhula.dataanalyser.model.Data;
import org.romanzhula.dataanalyser.repository.DataRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final DataRepository dataRepository;

    @Override
    public void handleData(Data data) {
        log.info("Data was saved to DB successfully! Data: {}", data);
        dataRepository.save(data);
    }

}
