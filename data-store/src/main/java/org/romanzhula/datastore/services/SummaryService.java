package org.romanzhula.datastore.services;


import org.romanzhula.datastore.dto.SummaryDTO;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.models.enums.SummaryType;

import java.util.Set;

public interface SummaryService {

    SummaryDTO getSummary(
            Long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );

}
