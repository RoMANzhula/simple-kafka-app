package org.romanzhula.datagenerator.web.controller;

import lombok.RequiredArgsConstructor;
import org.romanzhula.datagenerator.model.Data;
import org.romanzhula.datagenerator.model.DataTestOptions;
import org.romanzhula.datagenerator.service.KafkaDataService;
import org.romanzhula.datagenerator.service.TestDataService;
import org.romanzhula.datagenerator.web.dto.DataDTO;
import org.romanzhula.datagenerator.web.dto.DataTestOptionsDTO;
import org.romanzhula.datagenerator.web.mapper.DataMapper;
import org.romanzhula.datagenerator.web.mapper.DataTestOptionsMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final DataMapper dataMapper;
    private final KafkaDataService kafkaDataService;
    private final DataTestOptionsMapper dataTestOptionsMapper;
    private final TestDataService testDataService;

    @PostMapping("/send")
    public void send(
            @RequestBody DataDTO dataDTO
    ) {
        Data data = dataMapper.toEntity(dataDTO);
        kafkaDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(
            @RequestBody DataTestOptionsDTO dataTestOptionsDTO
    ) {
        DataTestOptions dataTestOptions = dataTestOptionsMapper.toEntity(dataTestOptionsDTO);
        testDataService.sendMessages(dataTestOptions);
    }

}
