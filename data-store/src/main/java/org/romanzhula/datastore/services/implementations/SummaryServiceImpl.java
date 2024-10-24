package org.romanzhula.datastore.services.implementations;

import lombok.RequiredArgsConstructor;
import org.romanzhula.datastore.dto.SummaryDTO;
import org.romanzhula.datastore.mappers.SummaryMappable;
import org.romanzhula.datastore.models.Summary;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.models.enums.SummaryType;
import org.romanzhula.datastore.exception_handler.SensorNotFoundException;
import org.romanzhula.datastore.repositories.SummaryRepository;
import org.romanzhula.datastore.services.SummaryService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;
    private final SummaryMappable summaryMappable;

    @Override
    public SummaryDTO getSummary(
            Long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    ) {
        Summary summary =
                summaryRepository
                        .findBySensorId(
                            sensorId,
                            measurementTypes == null ? Set.of(MeasurementType.values()) : measurementTypes,
                            summaryTypes == null ? Set.of(SummaryType.values()) : summaryTypes
                        )
                        .orElseThrow(SensorNotFoundException::new)
        ;

        return summaryMappable.toDto(summary);
    }

}
