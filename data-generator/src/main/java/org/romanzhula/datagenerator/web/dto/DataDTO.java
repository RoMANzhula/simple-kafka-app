package org.romanzhula.datagenerator.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.romanzhula.datagenerator.model.enums.MeasurementType;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class DataDTO {

    private Long sensorId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    private Double measurement;

    private MeasurementType measurementType;

}
