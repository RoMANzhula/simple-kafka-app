package org.romanzhula.dataanalyser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.romanzhula.dataanalyser.model.enums.MeasurementType;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    private Long sensorId;
    private LocalDateTime timestamp;
    private Double measurement;
    private MeasurementType measurementType;

}
