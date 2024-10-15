package org.romanzhula.dataanalyser.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.romanzhula.dataanalyser.model.enums.MeasurementType;

import java.time.LocalDateTime;


@Entity
@Table(name = "data")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sensorId;
    private LocalDateTime timestamp;
    private Double measurement;

    @Column(name = "meas_type")
    @Enumerated(value = EnumType.STRING)
    private MeasurementType measurementType;

}
