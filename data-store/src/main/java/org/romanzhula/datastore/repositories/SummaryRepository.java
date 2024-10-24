package org.romanzhula.datastore.repositories;

import org.romanzhula.datastore.models.Summary;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.models.enums.SummaryType;

import java.util.Optional;
import java.util.Set;


public interface SummaryRepository {

    Optional<Summary> findBySensorId(
            Long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );

}
