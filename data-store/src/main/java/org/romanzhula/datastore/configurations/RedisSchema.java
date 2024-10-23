package org.romanzhula.datastore.configurations;

import org.romanzhula.datastore.models.enums.MeasurementType;

import java.util.Locale;

public class RedisSchema {

    // set
    public static String sensorKeys() {
        return AppKey.getKey("sensors");
    }

    // hash (as Map<O,O>) with summary types
    public static String summaryKey(
            Long sensorId,
            MeasurementType measurementType
    ) {
        String measurementTypeNameLowerCase = measurementType.name().toLowerCase(Locale.ROOT);

        return AppKey.getKey(
                "sensors:" +
                sensorId +
                ":" +
                measurementTypeNameLowerCase
        );
    }

}
