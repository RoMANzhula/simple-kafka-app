package org.romanzhula.datagenerator.web.mapper;

import org.mapstruct.Mapper;
import org.romanzhula.datagenerator.model.Data;
import org.romanzhula.datagenerator.web.dto.DataDTO;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDTO> {

}
