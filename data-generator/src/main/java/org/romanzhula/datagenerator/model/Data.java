package org.romanzhula.datagenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.romanzhula.datagenerator.model.enums.MeasurementType;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Data {

    private Long sensorId;
    private LocalDateTime timestamp;
    private Double measurement;
    private MeasurementType measurementType;

}
