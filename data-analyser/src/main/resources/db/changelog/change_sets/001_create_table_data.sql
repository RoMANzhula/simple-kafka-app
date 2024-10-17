create table data
    (
        id          BIGSERIAL PRIMARY KEY ,
        sensor_id   BIGINT NOT NULL,
        timestamp   TIMESTAMP NOT NULL,
        measurement FLOAT NOT NULL,
        meas_type   VARCHAR NOT NULL
    );