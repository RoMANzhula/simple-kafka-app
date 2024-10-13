package org.romanzhula.datagenerator.web.mapper;

import org.mapstruct.Mapper;
import org.romanzhula.datagenerator.model.DataTestOptions;
import org.romanzhula.datagenerator.web.dto.DataTestOptionsDTO;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDTO> {
}
