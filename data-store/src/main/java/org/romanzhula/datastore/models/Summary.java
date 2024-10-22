package org.romanzhula.datastore.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.models.helper.SummaryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Summary {

    private Long sensorId;
    private Map<MeasurementType, List<SummaryEntry>> values;

    public Summary() {
        this.values = new HashMap<>();
    }

    public void addValue(MeasurementType measurementType, SummaryEntry value) {
        if (values.containsKey(measurementType)) {
            List<SummaryEntry> summaryEntries = new ArrayList<>(values.get(measurementType));
            summaryEntries.add(value);
            values.put(measurementType, summaryEntries);
        } else {
            values.put(measurementType, List.of(value));
        }
    }

}
