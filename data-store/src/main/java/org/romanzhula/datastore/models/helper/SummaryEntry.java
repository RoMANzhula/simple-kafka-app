package org.romanzhula.datastore.models.helper;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.romanzhula.datastore.models.enums.SummaryType;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SummaryEntry {

    private SummaryType summaryType;
    private Double value;
    private Long counter;

}
