package org.romanzhula.datastore.repositories.implementations;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.romanzhula.datastore.configurations.RedisSchema;
import org.romanzhula.datastore.models.Data;
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

                String counter = jedis.hget(
                        RedisSchema.summaryKey(sensorId, measurementType),
                        "counter"
                );

                if (counter != null) {
                    summaryEntry.setCounter(Long.parseLong(counter));
                }

                summary.addValue(measurementType, summaryEntry);
            }
        }

        return Optional.of(summary);
    }

    @Override
    public void handle(Data data) {
        try(Jedis jedis = jedisPool.getResource()) {
            if (!jedis.sismember(RedisSchema.sensorKeys(), String.valueOf(data.getSensorId()))) {
                jedis.sadd(RedisSchema.sensorKeys(), String.valueOf(data.getSensorId()));
            }

            updateMinValue(data, jedis);
            updateMaxValue(data, jedis);
            updateSumAndAvgValues(data, jedis);
        }
    }


    private void updateMinValue(Data data, Jedis jedis) {
        String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());
        String summaryType = SummaryType.MIN.name().toLowerCase();

        String value = jedis.hget(key, summaryType);

        boolean measurementLessValue = data.getMeasurement() < Double.parseDouble(value);
        String measurement = String.valueOf(data.getMeasurement());

        if (measurementLessValue) {
            jedis.hset(key, summaryType, measurement);
        }
    }

    private void updateMaxValue(Data data, Jedis jedis) {
        String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());
        String summaryType = SummaryType.MAX.name().toLowerCase();

        String value = jedis.hget(key, summaryType);

        boolean measurementLessValue = data.getMeasurement() > Double.parseDouble(value);
        String measurement = String.valueOf(data.getMeasurement());

        if (measurementLessValue) {
            jedis.hset(key, summaryType, measurement);
        }
    }

    private void updateSumAndAvgValues(Data data, Jedis jedis) {
        updateSumValue(data, jedis);

        String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());
        String summaryTypeSum = SummaryType.SUM.name().toLowerCase();
        String summaryTypeAvg = SummaryType.AVG.name().toLowerCase();


        String counter = jedis.hget(key, "counter");

        if (counter == null) {
            counter = String.valueOf(jedis.hset(key, "counter", "1"));
        } else {
            counter = String.valueOf(jedis.hincrBy(key, "counter", 1));
        }

        String sum = jedis.hget(key, summaryTypeSum);

        String average = String.valueOf(Double.parseDouble(sum) / Double.parseDouble(counter));

        jedis.hset(key, summaryTypeAvg, average);

    }

    private void updateSumValue(Data data, Jedis jedis) {
        String key = RedisSchema.summaryKey(data.getSensorId(), data.getMeasurementType());
        String summaryType = SummaryType.SUM.name().toLowerCase();

        String value = jedis.hget(key, summaryType);

        String measurement = String.valueOf(data.getMeasurement());

        if (value == null) { // for first income
            jedis.hset(key, summaryType, measurement);
        } else {
            jedis.hincrByFloat(key, summaryType, data.getMeasurement());
        }
    }

}
