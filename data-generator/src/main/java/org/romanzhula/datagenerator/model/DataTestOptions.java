package org.romanzhula.datagenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.romanzhula.datagenerator.model.enums.MeasurementType;

@NoArgsConstructor
@Getter
@Setter
public class DataTestOptions {

    private Integer delayInSecond;
    private MeasurementType[] measurementTypes;

}
