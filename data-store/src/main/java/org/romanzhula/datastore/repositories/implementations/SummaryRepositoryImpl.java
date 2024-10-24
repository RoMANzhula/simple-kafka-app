package org.romanzhula.datastore.repositories.implementations;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.romanzhula.datastore.configurations.RedisSchema;
import org.romanzhula.datastore.models.Summary;
import org.romanzhula.datastore.models.enums.MeasurementType;
import org.romanzhula.datastore.models.enums.SummaryType;
import org.romanzhula.datastore.models.helper.SummaryEntry;
import org.romanzhula.datastore.repositories.SummaryRepository;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SummaryRepositoryImpl implements SummaryRepository {

    private final JedisPool jedisPool; // analog JDBC connection

    @Override
    public Optional<Summary> findBySensorId(
            Long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    ) {
        try(Jedis jedis = jedisPool.getResource()) {
            if (!jedis.sismember(
                    RedisSchema.sensorKeys(),
                    String.valueOf(sensorId)
            )) {
                return Optional.empty();
            }

            if (measurementTypes.isEmpty() && !summaryTypes.isEmpty()) {

                return getSummary(
                        sensorId,
                        Set.of(MeasurementType.values()),
                        summaryTypes,
                        jedis
                );
            } else if (!measurementTypes.isEmpty() && summaryTypes.isEmpty()) {

                return getSummary(
                        sensorId,
                        measurementTypes,
                        Set.of(SummaryType.values()),
                        jedis
                );
            } else {

                return getSummary(
                        sensorId,
                        measurementTypes,
                        summaryTypes,
                        jedis
                );
            }
        }
    }

    private Optional<Summary> getSummary(
            Long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes,
            Jedis jedis
    ) {
        Summary summary = new Summary();

        summary.setSensorId(sensorId);

        for (MeasurementType measurementType: measurementTypes) {
            for (SummaryType summaryType: summaryTypes) {
                SummaryEntry summaryEntry = new SummaryEntry();

                summaryEntry.setSummaryType(summaryType);

                String value = jedis.hget(
                        RedisSchema.summaryKey(sensorId, measurementType),
                        summaryType.name().toLowerCase()
                );

                if (value != null) {
                    summaryEntry.setValue(Double.parseDouble(value));
                }

                summary.addValue(measurementType, summaryEntry);
            }
        }

        return Optional.of(summary);
    }
}
