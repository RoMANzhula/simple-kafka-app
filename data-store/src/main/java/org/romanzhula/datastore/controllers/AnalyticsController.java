package org.romanzhula.datastore.controllers;

import lombok.RequiredArgsConstructor;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.models.enums.SummaryType;
import org.romanzhula.datastore.dto.SummaryDTO;
import org.romanzhula.datastore.services.SummaryService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final SummaryService summaryService;

    @GetMapping("/summary/{sensorId}")
    public SummaryDTO getSummary(
            @PathVariable Long sensorId,
            @RequestParam(required = false) Set<MeasurementType> measurementTypes,
            @RequestParam(required = false) Set<SummaryType> summaryTypes
    ) {
        return summaryService.getSummary(sensorId, measurementTypes, summaryTypes);
    }
}
