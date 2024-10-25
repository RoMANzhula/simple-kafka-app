package org.romanzhula.datastore.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.romanzhula.datastore.models.enums.MeasurementType;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Data {

    private Long id;

    private Long sensorId;
    private LocalDateTime timestamp;
    private Double measurement;

    private MeasurementType measurementType;

}
