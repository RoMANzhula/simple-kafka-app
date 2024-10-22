package org.romanzhula.datastore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.models.helper.SummaryEntry;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
public class SummaryDTO {

    private Long sensorId;
    private Map<MeasurementType, List<SummaryEntry>> values;

}
