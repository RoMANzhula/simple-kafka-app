package org.romanzhula.datastore.mappers;

import org.mapstruct.Mapper;
import org.romanzhula.datastore.models.Summary;
import org.romanzhula.datastore.dto.SummaryDTO;

@Mapper(componentModel = "spring")
public interface SummaryMappable {

    Summary toEntity(SummaryDTO summaryDTO);

    SummaryDTO toDto(Summary summary);

}
