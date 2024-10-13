package org.romanzhula.datagenerator.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.romanzhula.datagenerator.model.enums.MeasurementType;

@NoArgsConstructor
@Getter
@Setter
public class DataTestOptionsDTO {

    private Integer delayInSecond;
    private MeasurementType[] measurementTypes;

}
